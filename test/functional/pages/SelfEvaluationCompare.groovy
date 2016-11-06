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
        Comparebutton(to:ShowSelfEvaluationCompare){String nome-> $("a",text:nome)}


    }




}
