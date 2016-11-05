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

    public Evaluation(String origin, String value, Date applicationDate, String criterion){
        this.origin = origin;
        this.value = value;
        this.applicationDate = applicationDate;
        this.criterion = Criterion.findById(Long.parseLong(criterion));
    }

    public boolean compatibleTo(Evaluation evaluationInstance){
        if(this.origin.equals(evaluationInstance.getOrigin()) && this.value.equals(evaluationInstance.getValue()) && this.applicationDate.compareTo(evaluationInstance.getApplicationDate())==0 && this.criterion.getDescription().equals(evaluationInstance.getCriterion().getDescription()))
        {
            return true
        }else {
            return false
        }
    }
}