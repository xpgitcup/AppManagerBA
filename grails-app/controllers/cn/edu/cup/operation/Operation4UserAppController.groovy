package cn.edu.cup.operation


import cn.edu.cup.AppRole
import cn.edu.cup.TomcatInstance
import cn.edu.cup.UserApp
import grails.converters.JSON

class Operation4UserAppController {

    def commonNetService

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
                                    println("ip:${v}")
                                    def w = ip.find("10.\\d+.\\d+.\\d+")
                                    def tomcat = new TomcatInstance(tomcatPath: item.path,
                                            wanIP: w,
                                            lanIP: v,
                                            port: 8080
                                    )
                                    tomcat.save(true)
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

    def scanWebApp() {
        def systemApp = ["docs", "examples", "host-manager", "manager", "ROOT"]
        def tomcatList = TomcatInstance.list()
        tomcatList.each { e ->
            def dir = new File(e.tomcatPath)
            if (dir.exists()) {
                dir.listFiles().each { item ->
                    if (item.isDirectory()) {
                        def name = item.name
                        if (!systemApp.contains(name)) {
                            println("${name}")
                            //登记应用程序

                        }
                    }
                }
            }
        }
        redirect(action: "index")
    }

    def listAppsRunning(params) {
        def userAppList = []

        println("${params}")
        if (params.title) {
            def role = AppRole.findByName(params.title)
            userAppList = UserApp.findAllByAppRoles(role, params)
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

    def index() {
        println("start at operation4UserApp...")
        def roles = AppRole.list()
        def tabList = []

        roles.each { e ->
            def total = UserApp.countByAppRoles(e)
            def tab = [:]
            tab.title = "${e}"
            tab.total = total
            tabList.add(tab)
        }

        def result = [tabList: tabList, roles: roles]
    }
}
