package cn.edu.cup

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AppRolesController {

    AppRolesService appRolesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond appRolesService.list(params), model:[appRolesCount: appRolesService.count()]
    }

    def show(Long id) {
        respond appRolesService.get(id)
    }

    def create() {
        respond new AppRoles(params)
    }

    def save(AppRoles appRoles) {
        if (appRoles == null) {
            notFound()
            return
        }

        try {
            appRolesService.save(appRoles)
        } catch (ValidationException e) {
            respond appRoles.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appRoles.label', default: 'AppRoles'), appRoles.id])
                redirect appRoles
            }
            '*' { respond appRoles, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond appRolesService.get(id)
    }

    def update(AppRoles appRoles) {
        if (appRoles == null) {
            notFound()
            return
        }

        try {
            appRolesService.save(appRoles)
        } catch (ValidationException e) {
            respond appRoles.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'appRoles.label', default: 'AppRoles'), appRoles.id])
                redirect appRoles
            }
            '*'{ respond appRoles, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        appRolesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'appRoles.label', default: 'AppRoles'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appRoles.label', default: 'AppRoles'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
