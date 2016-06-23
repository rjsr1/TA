package pages.StudentPages

import geb.Page

class StudentPage extends Page {

    static url = "/TA/student/index"

    static at =  {
        title ==~ /Student Listagem/
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

    boolean qtdStudentTable(int qtdStudent){
        boolean r = false
        int qtdTable = $("tr").find("td").allElements().size()
        if(qtdStudent == (qtdTable)/3) r = true
        return r
    }
}

