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
}
