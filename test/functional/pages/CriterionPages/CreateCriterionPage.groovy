package pages.CriterionPages

import geb.Page

/**
 * Created by lapp on 07/05/2016.
 */


class CreateCriterionPage extends Page {

    static url = "TA/criterion/create"

    static at = {
        title ==~ /Create Criterion/
    }

    boolean createCriterion(String desc) {
        $("form").description = desc
        $("input", name: "create").click()
    }

}