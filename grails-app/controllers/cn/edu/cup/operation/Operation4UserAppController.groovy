package cn.edu.cup.operation

import cn.edu.cup.AppRoles
import cn.edu.cup.UserApp
import grails.converters.JSON

class Operation4UserAppController {

    def listAppsRunning(params) {
        def userAppList = []

        println("${params}")
        if (params.title) {
            def role = AppRoles.findByName(params.title)
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
            render(template: templateFile, model:result)
        } else {
            respond result
        }

    }

    def countUserApp() {
        def count = 0
        if (params.title) {
            def role = AppRoles.findByName(params.title)
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
        def roles = AppRoles.list()
        def tabList = []

        roles.each { e->
            def total = UserApp.countByAppRoles(e)
            def tab = [:]
            tab.title = "${e}"
            tab.total = total
            tabList.add(tab)
        }

        def result = [tabList: tabList, roles: roles]
    }
}
