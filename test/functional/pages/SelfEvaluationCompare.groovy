package pages

import geb.Page

/**
 * Created by ess on 06/11/16.
 */
class SelfEvaluationCompare extends Page{
    static url ="selfEvaluationCompare/index"
    static at ={
        title ==~ /Self Evaluation Compare/
    }


    def compareSelfEvaluation(){
        $("button").click()

    }



}
