package pages

import geb.Page

/**
 * Created by rodrigocalegario on 6/23/16.
 */
class AddGroupStudentPage extends Page{
    static url = "/TA/student/createGroup"

    static at =  {
        title ==~ /Import Student/
    }

    def fillGroupStudentDetails(String text) {
        $("form").name = text

    }

    def selectAddGroup() {
        $("input", name: "create").click()
    }
}

