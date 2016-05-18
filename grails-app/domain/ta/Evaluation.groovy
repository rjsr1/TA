package ta

class Evaluation {
    String origin;
    String value;
    Date applicationDate;
    Criterion criterion;
    static constraints = {
        origin inList :["Test","Mini-Test","Form","Final"], blank: false
        value inList :["MA","MPA","MANA","--"], blank :false
        criterion nullable : false
    }

}
