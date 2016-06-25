package pages.StudentPages

import geb.Page

/**
 * Created by Lapp on 24/06/2016.
 */
class ShowStudentPage extends Page {
    static url = "student/show"

    static at = {
        title ==~ /Show Student/
    }

    def boolean checkForCriterion(String desc) {
        if ($("tbody").has("a", name: desc)) return false
        return true
    }
}
