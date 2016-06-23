package ta

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import ta.Evaluation

@Transactional(readOnly = true)
class EvaluationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    public static Date formattedDate(String dateInString){
        def formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = formatter.parse(dateInString);
        return date;
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Evaluation.list(params), model:[evaluationInstanceCount: Evaluation.count()]
    }

    /*public boolean createEvaluation(String criterionName, String evaluationOrigin, String evaluationDate, String studentEvaluation){
        def applicationDate = formattedDate(evaluationDate)
        //createEvaluation([origin: evaluationOrigin, value: ])
        cont2.params<<[value : "--"] <<[origin: origin] << [applicationDate : applicationDate];
        Evaluation evaluation = cont2.createEvaluation()
        def returningValue= cont.addEvaluations(criterionName,Evaluation)
        cont.response.reset()
        cont2.response.reset()
        return returningValue
    }*/

    public boolean saveEvaluation(Evaluation evaluation){
        if(Evaluation.findByCriterion(evaluation.criterion) == null && Evaluation.findByOrigin(evaluation.origin) == null){
            evaluation.save flush: true
            return true
        }else{
            return false
        }
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

    /* COMENTADO POR CALEGARIO A PEDIDO DE DANILO
    public Evaluation createEvaluation(String criterionName, String origin,String dateInString){
        def criterion = Criterion.findByDescription(criterionName)
        def date = this.formattedDate(dateInString)
        Evaluation evaluation = new Evaluation(origin, null, date, criterion)
        evaluation.save flush : true
        return evaluation;
    }
    */

    public Evaluation createAndSaveEvaluationWithoutParam(/*String evaluationDate*/){
        //def applicationDate = formattedDate(evaluationDate)
        //params << [applicationDate: applicationDate]
        Evaluation evaluation = new Evaluation(params)
        //saveStudent(student)
        //if(Evaluation.findByLogin(evaluation.get()) == null) {
            evaluation.save flush: true
        //}
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

        String[] todos = evaluationInstance.value.split(",")
        log.info(todos[0])
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
        def list = Student.list()
        for(int i = 0; i < allValues.size(); i++){
            Evaluation newEvaluation = new Evaluation(params.origin, allValues.get(i), params.applicationDate, params.criterion.id)
            if(list.get(i).findEvaluationByCriterion(newEvaluation.criterion.description).findSpecificEvaluation(newEvaluation)) return
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
}