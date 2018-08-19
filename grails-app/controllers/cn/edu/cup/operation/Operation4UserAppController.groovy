package cn.edu.cup.operation

import cn.edu.cup.AppRoles

class Operation4UserAppController {

    def liseAppRolles() {
        def roles = AppRoles.list()
        def result = [roles: roles]
        println("${roles}")
        if (request.xhr) {
            render(template: "showAppRoles", model: result)
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

    def index() { }
}
