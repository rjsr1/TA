package ta


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AlternativeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Alternative.list(params), model: [alternativeInstanceCount: Alternative.count()]
    }

    def show(Alternative alternativeInstance) {
        respond alternativeInstance
    }

    def create() {
        respond new Alternative(params)
    }

    @Transactional
    def save(Alternative alternativeInstance) {
        if (alternativeInstance == null) {
            notFound()
            return
        }

        if (alternativeInstance.hasErrors()) {
            respond alternativeInstance.errors, view: 'create'
            return
        }

        alternativeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alternative.label', default: 'Alternative'), alternativeInstance.id])
                redirect alternativeInstance
            }
            '*' { respond alternativeInstance, [status: CREATED] }
        }
    }

    def edit(Alternative alternativeInstance) {
        respond alternativeInstance
    }

    @Transactional
    def update(Alternative alternativeInstance) {
        if (alternativeInstance == null) {
            notFound()
            return
        }

        if (alternativeInstance.hasErrors()) {
            respond alternativeInstance.errors, view: 'edit'
            return
        }

        alternativeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Alternative.label', default: 'Alternative'), alternativeInstance.id])
                redirect alternativeInstance
            }
            '*' { respond alternativeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Alternative alternativeInstance) {

        if (alternativeInstance == null) {
            notFound()
            return
        }

        alternativeInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Alternative.label', default: 'Alternative'), alternativeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alternative.label', default: 'Alternative'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
