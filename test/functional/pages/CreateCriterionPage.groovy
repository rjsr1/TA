package pages

import geb.Page

/**
 * Created by lapp on 07/05/2016.
 */


class CreateCriterionPage extends Page {

    static url = "criterion/create"

    static at = {
        title ==~ /Create Criterion/
    }

    def createCriterion(desc) {
        $("form").description = desc
        $("input", name: "create").click()
    }

    def boolean checkForErrors() {
        return $("ul", class: "errors").isDisplayed()
    }
}