package pages.ReportsPages

import geb.Page
/**
 * Created by Milena Carneiro on 25/06/2016.
 */
class CreateReportsPage extends Page {
    static url = "/TA/report/create"
    static at ={
        title ==~/Create Report/
    }

    def fillReportDetails(String name, String type, String eval, double value){
        $("form").name = name
        chooseType(type)
        chooseEval(eval)
        $("form").valor = value
    }

    def chooseType(String type){
        $("select", name: "tipo").click()
        $("select", name: "tipo").find("option").find{it.value().equals(type)}.click()

    }

    def chooseEval(String eval){
        $("select", name: "avaliacao").click()
        $("select", name: "avaliacao").find("option").find{it.value().equals(eval)}.click()
    }

    def createReport(){
        $("input", name:"create").click()
    }
}
