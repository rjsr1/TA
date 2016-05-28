package pages.StudentPages

import geb.Page

class StudentPage extends Page {

    static url = "/TA/student/index"

    static at =  {
        title ==~ /Student List/
    }

    boolean confirmStudent(String name, String login) {
        boolean r = false
        boolean findName = $("td").has(text: name)
        boolean findLogin = $("td").has(text: login)
        if(findName && findLogin){
            r = true
        }
        return r
    }
}
