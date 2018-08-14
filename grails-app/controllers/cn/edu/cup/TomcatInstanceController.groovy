package cn.edu.cup

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TomcatInstanceController {

    TomcatInstanceService tomcatInstanceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tomcatInstanceService.list(params), model:[tomcatInstanceCount: tomcatInstanceService.count()]
    }

    def show(Long id) {
        respond tomcatInstanceService.get(id)
    }

    def create() {
        respond new TomcatInstance(params)
    }

    def save(TomcatInstance tomcatInstance) {
        if (tomcatInstance == null) {
            notFound()
            return
        }

        try {
            tomcatInstanceService.save(tomcatInstance)
        } catch (ValidationException e) {
            respond tomcatInstance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tomcatInstance.label', default: 'TomcatInstance'), tomcatInstance.id])
                redirect tomcatInstance
            }
            '*' { respond tomcatInstance, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tomcatInstanceService.get(id)
    }

    def update(TomcatInstance tomcatInstance) {
        if (tomcatInstance == null) {
            notFound()
            return
        }

        try {
            tomcatInstanceService.save(tomcatInstance)
        } catch (ValidationException e) {
            respond tomcatInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tomcatInstance.label', default: 'TomcatInstance'), tomcatInstance.id])
                redirect tomcatInstance
            }
            '*'{ respond tomcatInstance, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        tomcatInstanceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tomcatInstance.label', default: 'TomcatInstance'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tomcatInstance.label', default: 'TomcatInstance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
