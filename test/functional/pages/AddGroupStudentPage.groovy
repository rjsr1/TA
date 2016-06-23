package pages

/**
 * Created by rodrigocalegario on 6/23/16.
 */
class AddGroupStudentPage {
    static url = "/TA/student/createGroup"

    static at =  {
        title ==~ /Create Student/
    }

    def fillGroupStudentDetails(String text) {
        $("form").name = text

    }

    def selectAddGroup() {
        $("input", name: "create").click()
    }
}

