package ta

class Evaluation {
    String origin;
    String value;
    Date applicationDate;
    static constraints = {
        criterion blank : false
        origin inList :["Test","Mini-Test","Form","Final"], blank: false
        value inList :["MA","MPA","MANAA","--"], blank :false
    }
}
