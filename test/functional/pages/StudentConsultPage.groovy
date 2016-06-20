package pages

import geb.Page

/**
 * Created by joao on 19/05/16.
 */
class StudentConsultPage extends Page {
    static url = "/TA/student/search"

    static at = {
        title ==~ /Consult Student/
    }

    def static fillStudentSearch(login){
        $("form").title = login
    }

    def static selectSearch(){
        $("input", name: "search").click()
    }

    def static selectStudent(){

    }

    def static showStudentDetails(){

    }
}
