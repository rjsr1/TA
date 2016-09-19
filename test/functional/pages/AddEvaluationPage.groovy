package pages

import geb.Page
import ta.Criterion

/**
 * Created by TMB on 20/06/2016.
 */
class AddEvaluationPage extends Page {
    static url = "/TA/evaluation/create"

    static at = {
        title ==~ /Create Evaluation/
    }

    def selectAddEvaluation(){
        $("input", name: "create").click()
    }

    def chooseCriterion(String desc) {
        //$("select", id: "criterion").click()
        def la = (String) Criterion.findByDescription(desc).id
        $("select", id: "criterion").value(la)
    }

    def chooseValue(String value) {
        $("select", name: "value").click()
        $("select", name: "value").find("option").find{it.value().equals(value)}.click()
    }

    def chooseEvaluationDate(Date evaluationDate){
        def day = (evaluationDate.getDay() < 10) ? "0" + evaluationDate.getDay() : evaluationDate.getDay();
        def date_string = day + '-' + (evaluationDate.getMonth() + 1) + '-' + evaluationDate.getFullYear();
        $("select", name:"applicationDate").click()
        $("select", name:"applicationDate").value(date_string)
    }
    def chooseOrigin(String origin){
        $("select", name:"origin").click()
        $("select", name: "origin").find("option").find{it.value().equals(origin)}.click()
    }


}
