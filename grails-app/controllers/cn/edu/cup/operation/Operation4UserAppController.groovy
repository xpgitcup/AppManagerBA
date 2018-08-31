package cn.edu.cup.operation


import cn.edu.cup.AppRole
import cn.edu.cup.TomcatInstance
import cn.edu.cup.UserApp
import grails.converters.JSON

class Operation4UserAppController {

    def commonNetService
    def tomcatInstanceService
    def userAppService

    /*
    * 数据库中删除已经卸载的用户程序
    * */

    def checkApplication() {
        def appList = UserApp.list()
        appList.each { e ->
            def fn = "${e.tomcatInstance.tomcatPath}/webapps/${e.appName}"
            def appFile = new File(fn)
            if (!appFile.exists()) {
                userAppService.delete(e.id)
            }
        }

        redirect(action: "index")
    }

    /*
    * 全部删除用户的程序
    * */

    def clearUserApp() {
        def tlist = cn.edu.cup.UserApp.list()
        if (tlist) {
            tlist.each { e ->
                userAppService.delete(e.id)
            }
        }
        redirect(action: "index")
    }

    /*
    * 全部删除登记的Tomcat
    * */

    def clearTomcat() {
        def tlist = TomcatInstance.list()
        if (tlist) {
            tlist.each { e ->
                println("${e}")
                if (e.userApp.size() < 1) {
                    tomcatInstanceService.delete(e.id)
                }
            }
        }
        redirect(action: "index")
    }

    /*
    *  搜索Tomcat实例
    * */

    def scanTomcat(params) {
        if (params.rootPath) {
            def file = new File(params.rootPath)
            if (file.exists()) {
                file.listFiles().each { item ->
                    if (item.isDirectory()) {
                        if (item.name.contains("apache-tomcat-")) {
                            def bin = new File("${item.getAbsolutePath()}/bin")
                            if (bin.exists()) {
                                println("发现：${item}")
                                if (!TomcatInstance.findByTomcatPath(item.path)) {
                                    //获取本机的IP地址
                                    def ip = commonNetService.getHostIp()
                                    println(ip)
                                    def v = ip.find("192.168.1.\\d+")
                                    println("局域网：ip:${v}")
                                    def w = ip.find("10.\\d+.\\d+.\\d+")
                                    if (!w) {
                                        w = params.routeIP
                                    }
                                    println("广域网：ip:${w}")
                                    //解析服务器端口
                                    //def conf = new File("${item.path}/conf/server.xml")
                                    //def slurper= new XmlSlurper().parse(conf)
                                    //println("${slurper}")
                                    //println("----------------------------------------------------------------")
                                    def server = new XmlParser().parse("${item.path}/conf/server.xml")
                                    server.each { e ->
                                        println("元素：${e}")
                                        println("${e.name()} -- ${e.value()}")
                                        //println("${e.attributes()}")
                                    }
                                    def service = server.find { e -> e.name() == "Service" }
                                    println("服务器元素：${service}")
                                    println("${service.attributes()}")
                                    println("${service.value()}")
                                    def connector = service.findAll() { e -> e.name() == "Connector" }
                                    println("链接器：${connector}")
                                    connector.each { e ->
                                        println("${e}")
                                        println("${e.name()}")
                                        println("${e.attributes()}")
                                    }
                                    def mainConnector = connector.find() { e -> e.attributes().protocol == "HTTP/1.1" }
                                    println("主链接器：${mainConnector}")
                                    def mainPort = mainConnector.attributes().port
                                    println("主端口：${mainPort}")
                                    //登记tomcat
                                    def tomcat = new TomcatInstance(tomcatPath: item.path,
                                            wanIP: w,
                                            lanIP: v,
                                            port: mainPort
                                    )
                                    //tomcat.save(true)
                                    tomcatInstanceService.save(tomcat)
                                    println("登记${item}")
                                }
                            }
                        }
                    }
                }
            }
        }
        redirect(controller: "tomcatInstance", action: "index")
    }

    def scanTomcatUI() {}

    /*
    *  搜索用户程序
    * */

    def scanWebApp() {
        def systemApp = ["docs", "examples", "host-manager", "manager", "ROOT"]
        def tomcatList = TomcatInstance.list()
        def role = AppRole.findByName("一般程序")
        tomcatList.each { e ->
            def dir = new File("${e.tomcatPath}/webapps")
            if (dir.exists()) {
                dir.listFiles().each { item ->
                    if (item.isDirectory()) {
                        def name = item.name
                        if (!systemApp.contains(name)) {
                            if (!UserApp.findByAppName(name)) {
                                println("${name}")
                                //登记应用程序
                                def userApp = new UserApp(
                                        appName: name,
                                        description: name,
                                        date: item.lastModified(),
                                        appRole: role,
                                        tomcatInstance: e
                                )
                                userAppService.save(userApp)
                            }
                        }
                    }
                }
            }
        }
        redirect(action: "index")
    }

    /*
    * 显示正在运行的用户程序...
    * */

    def listAppsRunning(params) {
        def userAppList = []

        println("${params}")
        if (params.title) {
            def role = AppRole.findByName(params.title)
            userAppList = UserApp.findAllByAppRole(role, params)
        } else {
            userAppList = UserApp.list(params)
        }

        println("${userAppList}")
        def result = [userAppList: userAppList]

        def templateFile = "showUserApp"
        if (params.view) {
            templateFile = "${params.view}"
        }
        if (request.xhr) {
            render(template: templateFile, model: result)
        } else {
            respond result
        }

    }

    /*
    *  统计用户程序数量
    * */

    def countUserApp() {
        def count = 0
        if (params.title) {
            def role = AppRole.findByName(params.title)
            count = UserApp.countByAppRoles(role)
        } else {
            count = UserApp.count()
        }
        def result = [count: count]
        println("${result}")

        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 获取客户端IP
    * */

    def getClientIP() {
        /*
        * 获取客户端的IP
        * */
        def ip = request.getHeader("Client-IP")
        println "${ip} -- 1"
        //def ip =
        if (!ip) {
            request.getHeader("X-Forwarded-For")
            println "${ip} -- 2"
            if (!ip) {
                ip = request.getRemoteAddr()
                println "${ip} -- 3"
            }
        }

        def result = [ip: ip]

        if (request.xhr) {
            render(template: "showClientIP", model: result)
        } else {
            result
        }
    }

    /*
    * 程序的起点
    * */

    def index() {
        println("start at operation4UserApp...")
        def roles = AppRole.list()
        def tabList = []

        roles.each { e ->
            def total = UserApp.countByAppRole(e)
            def tab = [:]
            tab.title = "${e}"
            tab.total = total
            tabList.add(tab)
        }

        def result = [tabList: tabList, roles: roles]
    }
}
