package pages.CriterionPages

import geb.Page

/**
 * Created by lapp on 07/05/2016.
 */


class CreateCriterionPage extends Page {

    static url = "TA/Criterion/create"

    static at = {
        title ==~ /Create Criterion/
    }

    def fillCriterionDetails(String desc) {
        $("form").description = desc
    }

    def selectCreateCriterion() {
        $("input", name: "create").click()
    }

}