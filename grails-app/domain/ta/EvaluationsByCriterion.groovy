package ta
/**
 * Created by dlr4 on 12/05/2016.
 */
class EvaluationsByCriterion {
    Criterion criterion
    List<Evaluation> evaluations = new LinkedList<Evaluation>()
    double media

    static constraints = {
        criterion nullable: false
    }

    /*public EvaluationsByCriterion(String description){
        this.criterion = Criterion.findByDescription(description);
        this.evaluations = new LinkedList<>();
    }*/
    public EvaluationsByCriterion(Criterion criterion){
        this.criterion = criterion;
        this.evaluations = new LinkedList<>();
        this.media = 0;
    }

    public void addEvaluation(Evaluation evaluationInstance) {
        this.evaluations.add(evaluationInstance);
        this.media = media();
    }

    public double media(){
        double mediaCriterio = 0;
        double tempMedia = 0;
        int qtdEvaluations = 0;
        for(int i = 0; i < evaluations.size(); i++){
            String eval = evaluations.get(i).value
            if (eval.equals("MA")) tempMedia += 9
            else if (eval.equals("MPA")) tempMedia += 6
            else tempMedia += 3
            qtdEvaluations++
        }
        mediaCriterio = (tempMedia/qtdEvaluations);
        return mediaCriterio;
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

    }
}