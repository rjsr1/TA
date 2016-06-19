package ta
/**
 * Created by dlr4 on 12/05/2016.
 */
class EvaluationsByCriterion {
    Criterion criterion
    List evaluations
    static hasMany = [evaluations:Evaluation]
    double criterionAverage

    static constraints = {
        criterion nullable: false
        evaluations nullable : false
    }

    public EvaluationsByCriterion(Criterion criterion){
        this.criterion = criterion;
        this.evaluations = []
        this.criterionAverage = 0;
    }

    public void addEvaluation(Evaluation evaluationInstance) {
        addToEvaluations(evaluationInstance)
        this.criterionAverage = doMedia();
    }

    public double doMedia(){
        double mediaCriterio = 0;
        double tempMedia = 0;
        int qtdEvaluations = 0;
        for(int i = 0; i < evaluations.size(); i++){
            String eval = evaluations[i].value
            if (eval.equals("MA")) tempMedia += 9
            else if (eval.equals("MPA")) tempMedia += 6
            else tempMedia += 3
            qtdEvaluations++
        }
        mediaCriterio = (tempMedia/qtdEvaluations);
        return mediaCriterio;
    }


    /*  ------------------------
      | MÉTODOS USADOS EM TESTES |
        ------------------------  */

    public void deleteEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i < this.evaluations.size(); i++) {
            if (this.evaluations[i].compatibleTo(evaluationInstance)) {
                removeFromEvaluations(evaluationInstance)
            }
        }
    }

    public Evaluation findSpecificEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i < this.evaluations.size(); i++) {
            if (this.evaluations[i].compatibleTo(evaluationInstance)) {
                return this.evaluations[i]
            }
        }
       	return null
    }
}
