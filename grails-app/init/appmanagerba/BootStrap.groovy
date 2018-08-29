package appmanagerba

import cn.edu.cup.AppRole

class BootStrap {

    def initService

    def init = { servletContext ->
        environments {
            development {
                initRole()
                configureForDevelopment(servletContext);
            }
            production {
                configureForDevelopment(servletContext);
            }
        }
    }

    def destroy = {
    }

    /*
    * 初始化用户程序类型
    * */
    def initRole() {
        if (AppRole.count<1) {
            println "AppRole"
            def appRole = new AppRole(name: '一般程序', appRight: '0')
            appRole.save()
            def bppRole = new AppRole(name: '用户程序', appRight: '1')
            bppRole.save()
        }
    }

    /**
     * 初始化代码__开发环境下的初始化代码
     */
    def configureForDevelopment(servletContext) {
        println "这是开发环境..."
        def webRootDir = servletContext.getRealPath("/")
        def scriptPaths = ["${webRootDir}initSql"]
        println "BootStrap ${webRootDir}"
        scriptPaths.each {e->
            initService.loadScripts(e)
        }
    }

    /**
     * 发布后的初始化代码
     */
   def configureForProduction() {
        println "这是发布环境..."
    }

}
