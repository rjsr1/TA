package ta

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import ta.Evaluation

@Transactional(readOnly = true)
class EvaluationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Evaluation.list(params), model:[evaluationInstanceCount: Evaluation.count()]
    }

    def show(Evaluation evaluationInstance) {
        respond evaluationInstance
    }

    def create() {
        respond new Evaluation(params)
    }

    public Evaluation createEvaluation(){
        Evaluation evaluation = new Evaluation(params)
        return evaluation
    }

    @Transactional
    def save(Evaluation evaluationInstance) {
        if (evaluationInstance == null) {
            notFound()
            return
        }

        if (evaluationInstance.hasErrors()) {
            respond evaluationInstance.errors, view:'create'
            return
        }

        evaluationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evaluation.label', default: 'Evaluation'), evaluationInstance.id])
                redirect evaluationInstance
            }
            '*' { respond evaluationInstance, [status: CREATED] }
        }
    }

    @Transactional
    def saveAll() {
        def allValues = params.list('value')
        List<Evaluation> listEvaluation = new LinkedList<Evaluation>()

        StudentController student = new StudentController()
        for(int i = 0; i < allValues.size(); i++){
            Evaluation newEvaluation = new Evaluation(params.origin, allValues.get(i), params.applicationDate, params.criterion.id)
            newEvaluation.save flush: true
            listEvaluation.add(newEvaluation)
        }
        student.addEvaluationsToAllStudents(listEvaluation)
        redirect action:"index", method:"GET"
    }

    def edit(Evaluation evaluationInstance) {
        respond evaluationInstance
    }

    @Transactional
    def update(Evaluation evaluationInstance) {
        if (evaluationInstance == null) {
            notFound()
            return
        }

        if (evaluationInstance.hasErrors()) {
            respond evaluationInstance.errors, view:'edit'
            return
        }

        evaluationInstance.save flush:true

        StudentController sc = new StudentController()
        sc.updateAllAverages()

        EvaluationsByCriterionController ecc = new EvaluationsByCriterionController()
        ecc.updateAllCriterionAverages()

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Evaluation.label', default: 'Evaluation'), evaluationInstance.id])
                redirect evaluationInstance
            }
            '*'{ respond evaluationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Evaluation evaluationInstance) {

        if (evaluationInstance == null) {
            notFound()
            return
        }

        LinkedList<EvaluationsByCriterion> eccList = EvaluationsByCriterion.list()
        for (int i = 0; i < eccList.size(); i++) {
            eccList.get(i).removeFromEvaluations(evaluationInstance)
        }

        evaluationInstance.delete flush:true

        StudentController sc = new StudentController()
        sc.updateAllAverages()

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Evaluation.label', default: 'Evaluation'), evaluationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluation.label', default: 'Evaluation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    public static Date formattedDate(String dateInString){
        def formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = formatter.parse(dateInString);
        return date;
    }
}