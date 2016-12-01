package pages
import geb.Page
/**
 * Created by ess on 06/11/16.
 */
class ShowSelfEvaluationCompare extends Page{
    static url ="TA/ShowSelfEvaluationCompare"
    static at ={
        title ==~ /Self-Evaluation Compare Table/
    }

    def boolean findStudent(String nome){
         $("th",id:nome).text()==nome
    }
    def boolean findColumm(String conteudo){
        $("td").text()==conteudo
    }

    def boolean findRow(String nota){
        $("tr").text()==nota
    }







}
