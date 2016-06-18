package pages.ReportsPages

import geb.Page

/**
 * Created by Milena Carneiro on 07/05/2016.
 */
class ShowReportsPage extends Page {
    static url = "/TA/report/show"
    static at = {
        title ==~/Show Reports/
    }

    def static selectNotifications(){
        $("input", name: "slct").click()
    }


}
