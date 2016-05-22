package ta

class Criterion {
    String description

    static constraints = {
        description unique: true
        description blank: false
        description nullable: false
    }

}
