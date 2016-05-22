package pages

/**
 * Created by rodrigocalegario on 5/2/16.
 */

import geb.Page

class AddStudentsPage extends Page{

    static url = "/TA/student/create"

    static at =  {
        title ==~ /Create Student/
    }

    def fillStudentDetails(String login, String name) {
        $("form").login = login
        $("form").name = name
    }

    def selectAddStudent() {
        $("input", name: "create").click()
    }
}
