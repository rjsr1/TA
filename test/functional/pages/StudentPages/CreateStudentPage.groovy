package pages.StudentPages

import geb.Page

class CreateStudentPage extends Page {

    static url = "/TA/student/create"

    static at =  {
        title ==~ /Create Student/
    }

    def fillStudentDetails(String login, String name) {
        $("form").login = login
        $("form").name = name
    }

    def selectCreateStudent() {
        $("input", name: "create").click()
    }
}
