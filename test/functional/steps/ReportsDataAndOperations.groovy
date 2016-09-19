package steps

import ta.Report
import ta.ReportController
import ta.StudentController
import ta.Student

/**
 * Created by Milena Carneiro on 07/05/2016.
 */
class ReportsDataAndOperations {
    static boolean needsUpdate

    public static boolean checkCondition(String loginA, String evalType){
        def contS = new StudentController()
        def repo = Report.findByAvaliacao(evalType)
       double aux = contS.checkPercentageEvaluationStudent(evalType,loginA)
        /*if(aux>=repo.valor){ Refatoração para simplificar
            needsUpdate = true
        }else{
            needsUpdate = false
        }*/
        return needsUpdate = (aux*100>=repo.valor)
    }

//#if($the report "" is updated)
    public static boolean checkUpdate(String relatorio, String login){
        def contro = new ReportController()
        def rel = this.acheNome(relatorio)
        if(rel==null){
            return false
        }else{
            if(needsUpdate) contro.addStudentToReport(Student.findByLogin(login),rel)
            return true
        }
    }
//#end
    public static void createSaveResetResponse(ReportController control, String nome, String eval, String tipo, double valor){
        control.params << [name: nome, tipo: tipo, valor: valor, avaliacao: eval]
        control.save()
        control.response.reset()
    }

    public static boolean compatibleTo(Report relatorio, String nome){
        if(acheNome(nome)==relatorio) {
            return true
        }else {
            return false
        }
    }

    public static acheNome(String relatorioN){
        return Report.findByName(relatorioN)
    }

}
