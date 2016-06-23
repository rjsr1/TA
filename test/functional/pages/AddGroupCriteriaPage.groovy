package pages

import geb.Page
/**
 * Created by TMB on 23/06/2016.
 */
class AddGroupCriteriaPage extends Page {
    static url = "/TA/criterion/createGroup"

    static at = {
        title ==~ /Import Criterion/
    }

    def fillGroupCriteriaDetails(String description){
        $("form").description = description
    }

    def selectAddGroupCriteria(){
        $("input", name: "create").click()
    }
}
