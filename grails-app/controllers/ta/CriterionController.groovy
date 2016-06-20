package ta

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CriterionController {

    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
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

    public createAndSaveCriterion() {
        Criterion crit = new Criterion(params)
        if(Criterion.findByDescription(crit.description) == null) {
            crit.save(flush: true)
        }
    }

    public List<Criterion> getCriteriaList() {
        return Criterion.list()
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

        LinkedList<EvaluationsByCriterion> l = EvaluationsByCriterion.findAllByCriterion(criterionInstance)
        for (int i = 0; i < l.size(); i++) {
            LinkedList<Evaluation> e = l.get(i).evaluations
            for (int j = 0; j < e.size(); j++) {
                l.get(i).removeFromEvaluations(e.get(j))
            }
            l.get(i).delete(flush: true)
        }

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