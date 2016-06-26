package pages.ReportsPages

import geb.Page

/**
 * Created by Milena Carneiro on 07/05/2016.
 */
class ShowReportsPage extends Page {
    static url = "/TA/report/show"
    static at = {
        title ==~/Ver Report/
    }

    def boolean checkName(String name){
        def s = $("span", id: "n").text()
        return $("span", id: "n").text().equals(name)
    }
    def boolean checkType(String type){
        def s = $("span", id: "t").text()
        return $("span", id: "t").text().equals(type)
    }
    def boolean checkAvaliacao(String avaliacao){
        def s = $("span", id: "a").text()
        return $("span", id: "a").text().equals(avaliacao)
    }

}
