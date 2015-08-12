package pages

import geb.Page

class RegisterQuestionPage extends Page{
    static url = "/TA/question/create"

    static at = {
        title ==~ /Create Question/
    }

    def fillData(text) {
        $("form").question(text)
    }

    def click(){
        $("input", name:"create").click()
    }

}
