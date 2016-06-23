package pages.StudentPages

/**
 * Created by Danilo on 23/06/2016.
 */
class ShowStudentPage {
    static url = "TA/student/show"
    static at = {
        title ==~ /Ver Estudante/
    }

    def selectCriterion(String name){
        $("tr").find("td").has("a",text: name).click()
    }
}
