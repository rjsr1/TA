package pages.StudentPages

import geb.Page

class StudentPage extends Page {

    static url = "/TA/student/index"

    static at =  {
        title ==~ /Student List/
    }

    boolean confirmStudent(String name, String login) {
        boolean r = false
        boolean findName = $("tr").find("td").has("a",text: name)
        boolean findLogin = $("tr").has("td",text: login)
        if(findName && findLogin){
            r = true
        }
        return r
    }

    def selectStudent(String name){
        $("tr").find("td").has("a",text: name).click()
    }

    def selectStudentByLogin(String login){
        $("a", name: login).click()
    }
}

