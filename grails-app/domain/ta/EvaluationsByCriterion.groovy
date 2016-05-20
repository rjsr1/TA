package ta
/**
 * Created by dlr4 on 12/05/2016.
 */
class EvaluationsByCriterion {
    Criterion criterion
    List<Evaluation> evaluations

    static constrainst = {
        criterion nullable : false
    }

    public static void addEvaluation(Evaluation evaluationInstance){
        this.evaluations.add(evaluationInstance);
    }

    public static void deleteEvaluation(Evaluation evaluationInstance){
        for(int i= 0; i<this.evaluations.size();i++){
            if(this.evaluations.get(i).compatibleTo(evaluationInstance)){
                this.evaluations.remove(i)
            }
        }
    }

}