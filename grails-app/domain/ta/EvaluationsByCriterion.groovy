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
        evaluations.add(evaluationInstance);
    }
}