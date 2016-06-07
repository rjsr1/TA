package steps

import ta.EvaluationController
import ta.Report
import ta.ReportController
import ta.StudentController


/**
 * Created by Milena Carneiro on 07/05/2016.
 */
class ReportsDataAndOperations {

    public static boolean checkCondition(String loginA, String evalType, String crit){
       def contS = new StudentController()
       //falta implementar, sei disso, mas irei dizer como faz :D

        /*double totalE =
                (ReportsDataAndOperations.countType(evalType)/ReportsDataAndOperations.count())*100
        if(totalE>=70){
            return true
        }*/
    }
//#if($the report "" is updated)
    public static boolean checkUpdate(String relatorio){
        def rel = findByName(relatorio)
        def contro = new ReportController()
        contro.update(rel)
        if(rel==null){
            return false
        }else if(!rel.save(flush: true)){
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
}
