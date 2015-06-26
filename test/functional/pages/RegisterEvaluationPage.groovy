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

    def click(){
        $("input", name: "create").click()
    }

}