package cn.edu.cup

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserAppController {

    UserAppService userAppService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userAppService.list(params), model:[userAppCount: userAppService.count()]
    }

    def show(Long id) {
        respond userAppService.get(id)
    }

    def create() {
        respond new UserApp(params)
    }

    def save(UserApp userApp) {
        if (userApp == null) {
            notFound()
            return
        }

        try {
            userAppService.save(userApp)
        } catch (ValidationException e) {
            respond userApp.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userApp.label', default: 'UserApp'), userApp.id])
                redirect userApp
            }
            '*' { respond userApp, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userAppService.get(id)
    }

    def update(UserApp userApp) {
        if (userApp == null) {
            notFound()
            return
        }

        try {
            userAppService.save(userApp)
        } catch (ValidationException e) {
            respond userApp.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userApp.label', default: 'UserApp'), userApp.id])
                redirect userApp
            }
            '*'{ respond userApp, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userAppService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userApp.label', default: 'UserApp'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userApp.label', default: 'UserApp'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
