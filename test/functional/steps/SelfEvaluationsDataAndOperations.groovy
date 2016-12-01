package steps

import ta.EvaluationController
import ta.Student
import ta.Evaluation
import ta.Criterion
import ta.StudentController
/**
 * Created by ess on 19/11/16.
 */
class SelfEvaluationsDataAndOperations {


   static public Evaluation CreateEvaluation(String origin, String value, Date applicationDate, String criterion){
         Evaluation eval=new Evaluation(origin,value,applicationDate,criterion)
         eval.save(flush: true)
         return eval
    }

    static public boolean AddSelfEvaluation(String nome,String criterio,String value,Date data){

        CriterionTestDataAndOperations.createCriterion(criterio)
        if(CriterionTestDataAndOperations.getCriterion(criterio)==null){
            return false;
        }
        Evaluation self=new Evaluation("SelfEvaluation",value,data,criterio)
        self.save(flush: true)
        Student.findByName(nome).addSelfEvaluation(self)
        if(Student.findByName(nome).criteriaAndSelfEvaluations.find{it->it.criterion.description==null}){
            return false
        }else{
            return true
        }
        }
    static public boolean AddEvaluationToStudent(String nome,String criterio,String value,Date data){
        Student student=Student.findByName(nome)
        Evaluation eval=new Evaluation("Test",value,data,criterio)
        eval.save(flush: true)
        student.addEvaluation(eval)
        return (student.findEvaluationByCriterion(criterio)!=null)
    }
}
