package appmanagerba

class BootStrap {

    def initService

    def init = { servletContext ->
        environments {
            development {
                configureForDevelopment(servletContext);
            }
            production {
                configureForDevelopment(servletContext);
            }
        }
    }

    def destroy = {
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
