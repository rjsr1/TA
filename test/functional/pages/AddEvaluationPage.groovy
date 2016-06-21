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
}
