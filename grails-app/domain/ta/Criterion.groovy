package ta

class Criterion {
    String description

    static constraints = {
        description unique: true, blank : false, nullable : false
    }

    public Criterion(String description){
        this.description = description;
    }
}

