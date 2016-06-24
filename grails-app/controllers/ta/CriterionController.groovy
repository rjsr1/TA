package ta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CriterionController {

    static allowedMethods = [update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        respond Criterion.list(params), model:[criterionInstanceCount: Criterion.count()]
    }

    /*def addEvaluation(Criterion criterionInstance,Evaluation evaluationInstance){
        criterionInstance.evaluations.add(evaluationInstance)
        edit(criterionInstance)
    }*/

    public Criterion createCriterion(){
        Criterion criterion = new Criterion(params)
        return criterion
    }

    public boolean saveCriterion(Criterion criterion){
        if(Criterion.findByDescription(criterion.description) == null){
            criterion.save flush: true
            return true
        }else{
            return false
        }
    }

    def show(Criterion criterionInstance) {
        respond criterionInstance
    }

    def create() {
        respond new Criterion(params)
    }

    public Criterion retrieveCriterion() {
        def criterionInstance = new Criterion(params)
        return Criterion.findByDescription(criterionInstance.description)
    }

    public boolean compatibleInCriteria() {
        def criterionInstance = new Criterion(params)
        Criterion c = Criterion.findByDescription(criterionInstance.description)
        return criterionInstance.description.equals(c.description)
    }

    @Transactional
    def createAndSaveCriterion() {
        Criterion criterionInstance = new Criterion(params)
        if (Criterion.findByDescription(criterionInstance.getDescription()) == null) {
            if (criterionInstance.hasErrors()) {
                respond criterionInstance.errors, view: 'create'
                return
            }
            if(!criterionInstance.save(flush: true)){
                render(view: "create", model: [criterionInstance: criterionInstance])
                return
            }
            flash.message = message(code: 'default.created.message', args: [message(code: 'criterion.label', default: 'Criterion'), criterionInstance.id])
            redirect(action: "show", id: criterionInstance.id)
        }
    }

    public List<Criterion> getCriteriaList() {
        return Criterion.list()
    }

    def saveGroup(){
        String group = params.description
        String[] criteria = group.split(";")
        for (int i = 0; i < criteria.size(); i++) {
            Criterion novo = new Criterion(criteria[i])

            if (Criterion.findByDescription(novo.getDescription()) == null) {
                novo.save flush: true
            }
        }

        flash.message = message(code: 'default.created.message', args: [message(code: criteria.length, 'criterion.label', default: 'Criterion')])

        redirect action: "index", method: "GET"
    }

    def createGroup(){
        respond view: 'createGroup'
    }

    @Transactional
    def save(Criterion criterionInstance) {
        if (criterionInstance == null) {
            notFound()
            return
        }

        if (criterionInstance.hasErrors()) {
            respond criterionInstance.errors, view:'create'
            return
        }

        criterionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'criterion.label', default: 'Criterion'), criterionInstance.id])
                redirect criterionInstance
            }
            '*' { respond criterionInstance, [status: CREATED] }
        }
    }

    def edit(Criterion criterionInstance) {
        respond criterionInstance
    }

    @Transactional
    def update(Criterion criterionInstance) {
        if (criterionInstance == null) {
            notFound()
            return
        }

        if (criterionInstance.hasErrors()) {
            respond criterionInstance.errors, view:'edit'
            return
        }

        criterionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Criterion.label', default: 'Criterion'), criterionInstance.id])
                redirect criterionInstance
            }
            '*'{ respond criterionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Criterion criterionInstance) {

        if (criterionInstance == null) {
            notFound()
            return
        }

        LinkedList<Student> students = Student.list()
        for (int i = 0; i < students.size(); i++) {
            LinkedList<EvaluationsByCriterion> l = students.get(i).getCriteriaAndEvaluations()
            for (int j = 0; j < l.size(); j++) {
                if (l.get(j).criterion.id == criterionInstance.id) {
                    LinkedList<Evaluation> e = l.get(j).evaluations
                    for (int k = 0; k < e.size(); k++) {
                        e.get(k).delete()
                    }
                }
            }
            for (int j = 0; j < l.size(); j++) {
                if (l.get(j).criterion.id == criterionInstance.id) {
                    students.get(i).removeFromCriteriaAndEvaluations(l.get(j))
                    l.get(j).delete()
                }
            }
        }
        StudentController sc = new StudentController()
        sc.updateAllAverages()

        criterionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Criterion.label', default: 'Criterion'), criterionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'criterion.label', default: 'Criterion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}