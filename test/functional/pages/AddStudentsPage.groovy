package pages

import geb.Page

/**
 * Created by rodrigocalegario on 5/28/16.
 */
class AddStudentsPage extends Page{

    static url = "/TA/student/create"

    static at =  {
        title ==~ /Create Student/
    }

    def fillStudentDetails(String name, String login) {
        $("form").name = name
        $("form").login = login

    }

    def selectAddStudent() {
        $("input", name: "create").click()
    }
}
