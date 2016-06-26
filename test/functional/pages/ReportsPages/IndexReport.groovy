package pages.ReportsPages

import geb.Page
/**
 * Created by Milena Carneiro on 25/06/2016.
 */
class IndexReport extends Page {
    static url = "/TA/report/index"
    static at={
        title ==~/Report Listagem/
    }

    def selectReport(String name){
        $("td").find("a", name: name).click()
    }
}
