package ta

<<<<<<< HEAD
import org.grails.datastore.mapping.query.Query
=======
//import org.grails.datastore.mapping.query.Query​
>>>>>>> 4cc3882721d8e740ea2646a9d21dcc40f63bad03

class Student {
    String name;
    String login;
    List<EvaluationsByCriterion> criterionsAndEvaluations
<<<<<<< HEAD
    static constraints = {
        name blank: false
        login unique: true, blank: false;
        //criterionsAndEvaluations nullable: false;
    }

    public Student(String name, String login) {
        this.name = name;
        this.login = login;
        this.criterionsAndEvaluations = new LinkedList<>();
        def criterions = Criterion.findAll()
        for (int i = 0; i < criterions.size(); i++) {
            EvaluationsByCriterion crit = new EvaluationsByCriterion(criterions.get(i));
            this.criterionsAndEvaluations.add(crit)
        }
    }


    public void addEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i < this.criterionsAndEvaluations.size(); i++) {
            if (this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion)) {
                this.criterionsAndEvaluations.get(i).addEvaluation(evaluationInstance);
            }
        }
    }

    public void deleteEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i < this.criterionsAndEvaluations.size(); i++) {
            if (this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion)) {
                this.criterionsAndEvaluations.get(i).deleteEvaluation(evaluationInstance);
=======

    static constraints = {
        name blank : false
        login unique : true, blank:false;
        //criterionsAndEvaluations nullable : false;
    }

    public Student(String name, String login){
        this.name = name;
        this.login = login;
        this.criterionsAndEvaluations = new LinkedList<>();
    }

    public void addEvaluation(Evaluation evaluationInstance){
        for(int i = 0; i< this.criterionsAndEvaluations.size(); i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)){
                this.criterionsAndEvaluations.get(i).addEvaluation(evaluationInstance);
            }
        }
    }


    public void deleteEvaluation(Evaluation evaluationInstance){
        for(int i = 0; i< this.criterionsAndEvaluations.size(); i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)){
                this.criterionsAndEvaluations.get(i).deleteEvaluation(evaluationInstance);
            }
        }
    }

    public EvaluationsByCriterion findEvaluationByCriterion(String criterionName){

        for(int i =0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(criterionName)){
                return this.criterionsAndEvaluations.get(i);
>>>>>>> 4cc3882721d8e740ea2646a9d21dcc40f63bad03
            }
        }
        return null
    }

<<<<<<< HEAD
    public EvaluationsByCriterion findEvaluationByCriterion(String criterionName) {
        for (int i = 0; i < this.criterionsAndEvaluations.size(); i++) {
            if (this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(criterionName)) {
                return this.criterionsAndEvaluations.get(i);
            }
        }
        return null
    }

    public void addEvaluationsByCriterion(EvaluationsByCriterion evCriterion) {
        if (!this.findEvaluationByCriterion(evCriterion.getCriterion().getDescription())) {
            this.criterionsAndEvaluations.add(evCriterion);
        }
    }

    public boolean evaluationExist(Evaluation evaluationInstance) {
        for (int i = 0; i < this.criterionsAndEvaluations.size(); i++) {
            if (this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.getCriterion())) {
                List<Evaluation> evaluationsForThisCriterion = this.criterionsAndEvaluations.get(i).evaluations;
                for (int j = 0; j < evaluationsForThisCriterion.size(); j++) {
                    if (evaluationsForThisCriterion.compatibleTo(evaluationInstance)) {
=======
    public void addEvaluationsByCriterion(EvaluationsByCriterion evCriterion){
        if(!this.findEvaluationByCriterion(evCriterion.getCriterion().getDescription())){
            this.criterionsAndEvaluations.add(evCriterion);
        }
    }

    public boolean evaluationExist(Evaluation evaluationInstance){
        for(int i = 0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.getCriterion().getDescription())){
                List<Evaluation> evaluationsForThisCriterion = this.criterionsAndEvaluations.get(i).evaluations;
                for(int j=0; j<evaluationsForThisCriterion.size();j++){
                    if(evaluationsForThisCriterion.compatibleTo(evaluationInstance)){
>>>>>>> 4cc3882721d8e740ea2646a9d21dcc40f63bad03
                        return true
                    }
                }
            }
<<<<<<< HEAD
            return false
=======
>>>>>>> 4cc3882721d8e740ea2646a9d21dcc40f63bad03
        }
        return false
    }

    /*private boolean criterionExists(String criterionDescription){
        for(int i=0;i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).criterion.description.equals(criterionDescription))
        }
    }*/
}

<<<<<<< HEAD
/*private boolean criterionExists(String criterionDescription){
    for(int i=0;i<this.criterionsAndEvaluations.size();i++){
        if(this.criterionsAndEvaluations.get(i).criterion.description.equals(criterionDescription))
    }
}*/
=======
>>>>>>> 4cc3882721d8e740ea2646a9d21dcc40f63bad03
