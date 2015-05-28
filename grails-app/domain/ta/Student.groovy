package ta

class Student {
    String login
    String name
    List evaluationCriteria = []
    static hasMany = [evaluationCriteria: EvaluationCriterion]

    static constraints = {
        login unique: true
        name blank: false
    }

    public void afterCreateAddCriteria(List<EvaluationCriterion> evaluationCriteria) {
        for(EvaluationCriterion evaluationCriterion : evaluationCriteria) {
            this.evaluationCriteria.add(evaluationCriterion)
        }
    }

    public void addCriterion(EvaluationCriterion evaluationCriterion) {
        if(!evaluationCriteria.contains(evaluationCriterion)) {
            evaluationCriteria.add(evaluationCriterion)
        }
    }
}
