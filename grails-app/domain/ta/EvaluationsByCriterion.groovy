package ta
/**
 * Created by dlr4 on 12/05/2016.
 */
class EvaluationsByCriterion {
    Criterion criterion
    List<Evaluation> evaluations

    static constrainst = {
        criterion nullable: false
        evaluations nullable : false
    }

    public EvaluationsByCriterion(String description){
        this.criterion = Criterion.findByDescription(description);
        this.evaluations = new LinkedList<>();
    }
    public EvaluationsByCriterion(Criterion criterion){
        this.criterion = criterion;
        this.evaluations = new LinkedList<>();
    }

    public void addEvaluation(Evaluation evaluationInstance) {
        this.evaluations.add(evaluationInstance);
    }

    public void deleteEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i < this.evaluations.size(); i++) {
            if (this.evaluations.get(i).compatibleTo(evaluationInstance)) {
                this.evaluations.remove(i)
            }
        }
    }

    public Evaluation findSpecificEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i <this.evaluations.size(); i++) {
            if (this.evaluations.get(i).compatibleToNoValue(evaluationInstance)) {
                return this.evaluations.get(i)
            }
        }
       return null
    }
}