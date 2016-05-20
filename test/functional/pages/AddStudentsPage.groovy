package pages

/**
 * Created by rodrigocalegario on 5/2/16.
 */

import geb.Page

class AddStudentsPage extends Page{

    static url = "/TA/student/add"

    static at =  {
        title ==~ /Add Student/
    }

    def fillStudentDetails(String login, String name) {
        $("form").login = login
        $("form").name = name
    }

    def selectAddStudent() {
        $("input", name: "add").click()
    }

    def showConfirmationMessage(String name, String login) {

    }
}
