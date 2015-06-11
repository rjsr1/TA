package pages

import geb.Page

class ShowEvaluationPage extends Page{

    static url = "/TA/evaluation/show"

    static at = {
        title ==~ /Ver Evaluation/
    }
}
