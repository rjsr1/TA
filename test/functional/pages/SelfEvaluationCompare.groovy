package pages

import geb.Page

/**
 * Created by ess on 06/11/16.
 */
class SelfEvaluationCompare extends Page{
    static url ="TA/SelfEvaluationCompare"
    static at ={
        title ==~ /Self Evaluation/
    }
    static content={

        compareButton(to:ShowSelfEvaluationCompare){$("a","Compare Self-Evaluation")}
            }

    def CompareSelfEvaluation(String s){
        $(Name:s).click()

    }



}
