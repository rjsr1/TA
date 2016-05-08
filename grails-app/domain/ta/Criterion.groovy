package ta

class Criterion {
    String description

    static constraints = {
        description unique: true
    }
}
