package pages

import geb.Page

/**
 * Created by joao on 19/05/16.
 */
class StudentConsultPage extends Page {
    static url = "/TA/student/index"

    static at = {
        title ==~ /Consult Student/
    }

    def static clickStudent(){
        $("input", name:"pesquisar").click()
    }

    def static showStudentDetails(text){
        $("form").title = text
    }
}
