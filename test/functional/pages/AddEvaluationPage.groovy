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
        def day = evaluationDate.getDay()
        def month = evaluationDate.getMonth()
        def year = evaluationDate.getYear()
        $("select", name:"applicationDate_day").click()
        $("select", name: "applicationDate_day").find("option").find{it.value().equals(day)}.click()
        $("select", name:"applicationDate_month").click()
        $("select", name: "applicationDate_month").find("option").find{it.value().equals(month)}.click()
        $("select", name:"applicationDate_year").click()
        $("select", name: "applicationDate_year").find("option").find{it.value().equals(year)}.click()
    }
    def chooseOrigin(String origin){
        $("select", name:"origin").click()
        $("select", name: "origin").find("option").find{it.value().equals(origin)}.click()
    }


}
