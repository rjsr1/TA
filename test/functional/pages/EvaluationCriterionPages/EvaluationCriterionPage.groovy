package pages.EvaluationCriterionPages

import geb.Page

class EvaluationCriterionPage extends Page {

    static url = "/TA/evaluationCriterion/index"

    static at =  {
        title ==~ /EvaluationCriterion List/
    }
}
