package ta

//import org.grails.datastore.mapping.query.Query​

class Student {
    String name;
    String login;
    double average;
    List<EvaluationsByCriterion> criterionsAndEvaluations

    static constraints = {
        name blank : false
        login unique : true, blank:false;
        //criterionsAndEvaluations nullable : false;
    }

    public Student(String name, String login){
        this.name = name;
        this.login = login;
        this.criterionsAndEvaluations = new LinkedList<>();
    }

    public void calcMedia() {
        int qtdEvaluations = 0
        double tempMedia = 0
        List<Evaluation> evaluationsInCriterion
        for (int i = 0; i < this.criterionsAndEvaluations.size(); i++) {
            evaluationsInCriterion = this.criterionsAndEvaluations.get(i).getEvaluations()
            for (int j = 0; j < evaluationsInCriterion.size(); j++) {
                String eval = evaluationsInCriterion.get(j).value
                if (eval.equals("MA")) tempMedia += 9
                else if (eval.equals("MPA")) tempMedia += 6
                else tempMedia += 3
                qtdEvaluations++
            }
        }
        if (qtdEvaluations > 0) {
            tempMedia /= qtdEvaluations
            this.average = tempMedia
        } else {
            this.average = 0
        }
    }

    public void addEvaluation(Evaluation evaluationInstance){
        for(int i = 0; i< this.criterionsAndEvaluations.size(); i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)){
                this.criterionsAndEvaluations.get(i).addEvaluation(evaluationInstance);
            }
        }
    }


    public void deleteEvaluation(Evaluation evaluationInstance){
        for(int i = 0; i< this.criterionsAndEvaluations.size(); i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)){
                this.criterionsAndEvaluations.get(i).deleteEvaluation(evaluationInstance);
            }
        }
    }

    public EvaluationsByCriterion findEvaluationByCriterion(String criterionName){

        for(int i =0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(criterionName)){
                return this.criterionsAndEvaluations.get(i);
            }
        }
        return null
    }

    public void addEvaluationsByCriterion(EvaluationsByCriterion evCriterion){
        if(!this.findEvaluationByCriterion(evCriterion.getCriterion().getDescription())){
            this.criterionsAndEvaluations.add(evCriterion);
        }
    }

    public boolean evaluationExist(Evaluation evaluationInstance){
        for(int i = 0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.getCriterion().getDescription())){
                List<Evaluation> evaluationsForThisCriterion = this.criterionsAndEvaluations.get(i).evaluations;
                for(int j=0; j<evaluationsForThisCriterion.size();j++){
                    if(evaluationsForThisCriterion.compatibleTo(evaluationInstance)){
                        return true
                    }
                }
            }
        }
        return false
    }

    /*private boolean criterionExists(String criterionDescription){
        for(int i=0;i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).criterion.description.equals(criterionDescription))
        }
    }*/
}
