package steps

import ta.EvaluationController
import ta.Evaluation
import ta.Report
import ta.Criterion
import ta.ReportController
import ta.StudentController


/**
 * Created by Milena Carneiro on 07/05/2016.
 */
class ReportsDataAndOperations {
    static boolean needsUpdate

    public static boolean checkCondition(String loginA, String evalType){
       def contS = new StudentController()
       double aux = contS.checkPercentageEvaluationStudent(evalType,loginA)
        if(aux>=0.7){
            needsUpdate = true
        }else{
            needsUpdate = false
        }
        return needsUpdate
    }

//#if($the report "" is updated)
    public static boolean checkUpdate(String relatorio){
        def rel = findByName(relatorio)
        def contro = new ReportController()
        if(needsUpdate) contro.update(rel)
        if(rel==null){
            return false
        }else if(!rel.save(flush: true)){
            return false
        }else if(!needsUpdate){
            return false
        }else{
            return true
        }
    }
//#end
    public static void createSaveResetResponse(ReportController control, String nome){
        control.params << [name: nome, students: null]
        control.createSaveResetResponse()
    }


    public static Report findByName(String nome){
        def contro = new ReportController()
        return contro.findByName(nome)
    }

    public static boolean compatibleTo(Report relatorio, String nome){
        def contro = new ReportController()
        if(contro.findByName(nome)==relatorio) {
            return true
        }else {
            return false
        }
    }

    public static void addEvalToStudent(String login, String origin, String eval, String data, String criteName){
        def controS = new StudentController()
        def controE = new EvaluationController()
        Date dat = controE.formattedDate(data)
        Criterion crit = Criterion.findByDescription(criteName)
        controE.params << [origin: origin, value: eval, applicationDate: dat, criterion: crit]
        controS.addEvaluationToStudent(login)
    }

}
