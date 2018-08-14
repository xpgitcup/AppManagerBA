package cn.edu.cup

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AppPrefixController {

    AppPrefixService appPrefixService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond appPrefixService.list(params), model:[appPrefixCount: appPrefixService.count()]
    }

    def show(Long id) {
        respond appPrefixService.get(id)
    }

    def create() {
        respond new AppPrefix(params)
    }

    def save(AppPrefix appPrefix) {
        if (appPrefix == null) {
            notFound()
            return
        }

        try {
            appPrefixService.save(appPrefix)
        } catch (ValidationException e) {
            respond appPrefix.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appPrefix.label', default: 'AppPrefix'), appPrefix.id])
                redirect appPrefix
            }
            '*' { respond appPrefix, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond appPrefixService.get(id)
    }

    def update(AppPrefix appPrefix) {
        if (appPrefix == null) {
            notFound()
            return
        }

        try {
            appPrefixService.save(appPrefix)
        } catch (ValidationException e) {
            respond appPrefix.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'appPrefix.label', default: 'AppPrefix'), appPrefix.id])
                redirect appPrefix
            }
            '*'{ respond appPrefix, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        appPrefixService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'appPrefix.label', default: 'AppPrefix'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appPrefix.label', default: 'AppPrefix'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
