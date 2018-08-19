package appmanagerba

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //这里其实是定义程序的入口点
        //"/"(view:"/index")    //缺省的入口
        //"/"(view:"/indexAppManager")      //自定义的入口
        "/"(controller:"operation4UserApp", action:"index")     //自定义的入口，---这一次是一个action
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
