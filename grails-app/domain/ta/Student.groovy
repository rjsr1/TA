package ta

//import org.grails.datastore.mapping.query.Query​

class Student {
    String name;
    String login;
    List<EvaluationsByCriterion> criterionsAndEvaluations
    static constraints = {
        name blank : false
        login unique : true, blank:false;
        criterionsAndEvaluations nullable : false;
    }

    public static void addEvaluation(Evaluation evaluationInstance){
        for(int i = 0; i< this.criterionsAndEvaluations.size(); i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)){
                this.criterionsAndEvaluations.get(i).addEvaluation(evaluationInstance);
            }
        }
    }

    public static EvaluationsByCriterion findEvaluationByCriterion(String criterionName){
        for(int i =0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(criterionName)){
                return this.criterionsAndEvaluations.get(i);
            }
        }
        return null
    }

    public static boolean evaluationExist(Evaluation evaluationInstance){
        for(int i = 0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.getCriterion().getDescription())){
                List<Evaluation> evaluationsForThisCriterion = this.criterionsAndEvaluations.get(i).evaluations;
                for(int j=0; j<evaluationsForThisCriterion.size();j++){
                    if(evaluationsForThisCriterion.compatibleTo(evaluationInstance)){
                        return true
                    }
                }
            }
        }
        return false
    }

    /*private boolean criterionExists(String criterionDescription){
        for(int i=0;i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).criterion.description.equals(criterionDescription))
        }
    }*/
}