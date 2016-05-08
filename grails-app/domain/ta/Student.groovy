package ta

class Student {
    String login
    String name

    // tentei um enumerador primeiro mas da erro
    static class Concept {
        public static final List<String> CONCEPTS = ["MA", "MPA", "MANA", "XX"]
    }

    Map<String, String> evaluations

    static constraints = {
        login unique: true
        name blank: false
    }

    public void afterCreateAddCriteria(List<Criterion> evaluationCriteria) {
        evaluations = new HashMap<>()
        for(Criterion evaluationCriterion : evaluationCriteria) {
            if(this.evaluations.get(evaluationCriterion.name) == null) {
                this.evaluations.put(evaluationCriterion.name, Concept.CONCEPTS.get(3))
            }
        }
    }

    public void addCriterion(Criterion evaluationCriterion) {
        if(evaluations == null) {
            evaluations = new HashMap<>()
        }
        if(this.evaluations.get(evaluationCriterion.name) == null) {
            this.evaluations.put(evaluationCriterion.name, Concept.CONCEPTS.get(3))
        }
    }
}
