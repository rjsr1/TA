package ta



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EvaluationCriterionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond EvaluationCriterion.list(params), model:[evaluationCriterionInstanceCount: EvaluationCriterion.count()]
    }

    def show(EvaluationCriterion evaluationCriterionInstance) {
        respond evaluationCriterionInstance
    }

    def create() {
        respond new EvaluationCriterion(params)
    }

    public EvaluationCriterion createEvaluationCriterion() {
        return new EvaluationCriterion(params)
    }

    public boolean saveEvaluationCriterion(EvaluationCriterion evaluationCriterion) {
        if(EvaluationCriterion.findByName(evaluationCriterion.name) == null) {
            evaluationCriterion.save(flush: true)
            new StudentController().updateStudentEvaluationCriteria()
            return true
        }
        return false
    }

    @Transactional
    def save(EvaluationCriterion evaluationCriterionInstance) {
        if (evaluationCriterionInstance == null) {
            notFound()
            return
        }

        if (evaluationCriterionInstance.hasErrors()) {
            respond evaluationCriterionInstance.errors, view:'create'
            return
        }

        evaluationCriterionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evaluationCriterion.label', default: 'EvaluationCriterion'), evaluationCriterionInstance.id])
                redirect evaluationCriterionInstance
            }
            '*' { respond evaluationCriterionInstance, [status: CREATED] }
        }
    }

    def edit(EvaluationCriterion evaluationCriterionInstance) {
        respond evaluationCriterionInstance
    }

    @Transactional
    def update(EvaluationCriterion evaluationCriterionInstance) {
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'EvaluationCriterion.label', default: 'EvaluationCriterion'), evaluationCriterionInstance.id])
                redirect evaluationCriterionInstance
            }
            '*'{ respond evaluationCriterionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(EvaluationCriterion evaluationCriterionInstance) {

        if (evaluationCriterionInstance == null) {
            notFound()
            return
        }

        evaluationCriterionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'EvaluationCriterion.label', default: 'EvaluationCriterion'), evaluationCriterionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluationCriterion.label', default: 'EvaluationCriterion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
