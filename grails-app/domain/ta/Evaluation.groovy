package ta

class Evaluation {
    String origin;
    String value;
    Date applicationDate;
    String criterion;
    static constraints = {
        origin inList :["Test","Mini-Test","Form","Final","--"], blank: false
        value inList :["MA","MPA","MANA","--"], blank :false
        criterion nullable : false, blank : false
    }

    public Evaluation(String origin, String value, Date applicationDate, String criterion){
        this.origin = origin;
        this.value = value;
        this.applicationDate = applicationDate;
        this.criterion = Criterion.findById(Long.parseLong(criterion));
    }

    public boolean compatibleTo(Evaluation evaluationInstance){
<<<<<<< HEAD
        return this.origin.equals(evaluationInstance.getOrigin()) && this.value.equals(evaluationInstance.getValue()) && this.applicationDate.compareTo(evaluationInstance.getApplicationDate()) == 0 && this.criterion.equals(evaluationInstance.getCriterion())
    }
    public boolean compatibleToNoValue(Evaluation evaluationInstance){
        return this.origin.equals(evaluationInstance.getOrigin()) && this.applicatinoDate.compareTo(evaluationInstance.getApplicationDate()) == 0 && this.criterion.equals(evaluationInstance.getCriterion())
    }
}
=======
        if(this.origin.equals(evaluationInstance.getOrigin()) && this.value.equals(evaluationInstance.getValue()) && this.applicationDate.compareTo(evaluationInstance.getApplicationDate())==0 && this.criterion.getDescription().equals(evaluationInstance.getCriterion().getDescription()))
        {
            return true
        }else {
            return false
        }
    }
}
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed
