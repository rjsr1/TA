package ta

class Criterion {
    String description
    static constraints = {
<<<<<<< HEAD
        description unique: true, blank: false, nullable: false
    }
}
=======
        description unique: true, blank : false, nullable : false
    }

    public Criterion(String description){
        this.description = description;
    }
}

>>>>>>> refs/heads/pr/2
