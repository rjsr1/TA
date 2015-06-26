package pages

import geb.Page

class RegisterAlternativePage extends Page{
    static url = "/TA/alternative/create"

    static at = {
        title ==~ /Criar Alternative/
    }

    def fillData(text) {
        $("form").alternative(text)
    }

    def click(){
        $("input", name:"create").click()
    }
}
