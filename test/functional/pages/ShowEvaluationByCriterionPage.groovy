package pages

import geb.Page

/**
 * Created by Lapp on 25/06/2016.
 */
class ShowEvaluationByCriterionPage extends Page {
    static url = "evaluationsByCriterion/show"

    static at = {
        title ==~ /Show EvaluationsByCriterion/
    }

    def selectEvaluationByValue(String value) {
        $("td").find("a", name: value).click()
    }

    def boolean checkForEvaluation(String value) {
        return !($("td").has("a", name: value))
    }
}

