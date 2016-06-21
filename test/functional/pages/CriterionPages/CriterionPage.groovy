package pages.CriterionPages

import geb.Page
/**
 * Created by TMB on 20/06/2016.
 */
class CriterionPage extends Page {
    static url = "/TA/criterion/index"

    static at = {
        title ==~ /Criterion Listagem/
    }

    boolean confirmCriterion(String desc){
        boolean r = false
        boolean findDescription = $("tr").find("td").has("a",text: desc)
        if(findDescription){
            r = true
        }
        return r
    }
}
