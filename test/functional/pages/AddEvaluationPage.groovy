package pages

import geb.Page
/**
 * Created by TMB on 20/06/2016.
 */
class AddEvaluationPage extends Page {
    static url = "/TA/evaluation/create"

    static at = {
        title ==~ /Criar Evaluation/
    }

    def fillEvaluationDetails(String value, String origin, Date applicationDate,Long criterionId){
        $("form").origin = origin
        $("form").value = value
        $("form").applicationDate = applicationDate
        $("form").criterion = criterionId;
    }

    def selectAddEvaluation(){
        $("input", name: "create").click()
    }
}
