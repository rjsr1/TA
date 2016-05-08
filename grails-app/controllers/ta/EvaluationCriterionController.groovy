package ta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EvaluationCriterionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Criterion.list(params), model:[evaluationCriterionInstanceCount: Criterion.count()]
    }

    def show(Criterion evaluationCriterionInstance) {
        respond evaluationCriterionInstance
    }

    def create() {
        respond new Criterion(params)
    }

    public Criterion createEvaluationCriterion() {
        return new Criterion(params)
    }

    public boolean saveEvaluationCriterion(Criterion evaluationCriterion) {
        if(Criterion.findByName(evaluationCriterion.name) == null) {
            evaluationCriterion.save(flush: true)
            new StudentController().updateStudentEvaluationCriteria()
            return true
        }
        return false
    }

    @Transactional
    def save(Criterion evaluationCriterionInstance) {
        if (evaluationCriterionInstance == null) {
            notFound()
            return
        }

        if (evaluationCriterionInstance.hasErrors()) {
            respond evaluationCriterionInstance.errors, view:'create'
            return
        }

        evaluationCriterionInstance.save flush:true

        /////////////////////////
        new StudentController().updateStudentEvaluationCriteria()
        /////////////////////////

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evaluationCriterion.label', default: 'Criterion'), evaluationCriterionInstance.id])
                redirect evaluationCriterionInstance
            }
            '*' { respond evaluationCriterionInstance, [status: CREATED] }
        }
    }

    def edit(Criterion evaluationCriterionInstance) {
        respond evaluationCriterionInstance
    }

    @Transactional
    def update(Criterion evaluationCriterionInstance) {
        if (evaluationCriterionInstance == null) {
            notFound()
            return
        }

        if (evaluationCriterionInstance.hasErrors()) {
            respond evaluationCriterionInstance.errors, view:'edit'
            return
        }

        evaluationCriterionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Criterion.label', default: 'Criterion'), evaluationCriterionInstance.id])
                redirect evaluationCriterionInstance
            }
            '*'{ respond evaluationCriterionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Criterion evaluationCriterionInstance) {

        if (evaluationCriterionInstance == null) {
            notFound()
            return
        }

        evaluationCriterionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Criterion.label', default: 'Criterion'), evaluationCriterionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluationCriterion.label', default: 'Criterion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
