package pages

import geb.Page

class RegisterEvaluationPage extends Page {
    static url = "/TA/evaluation/create"

    static at = {
        title ==~ /Create Evaluation/
    }

    def fillData(text) {
        $("form").title = text
    }
    /*def fillEvaluationDetais(String origin, String value, String criterionName,String dateInString){
        $("form").
    }*/

    def click(){
        $("input", name: "create").click()
    }

}