/**
 * Created by Milena Carneiro on 28/04/2016.
 */
package steps

//criar um branch para ajeitar isso!!!

import funciotnal.pages.ReportsPages.ReportsPage
import functional.pages.StudentPages.StudentPage
import pages.ReportsPages.ShowReportsPage

//Cenários de controlador
//Atualizar um relatório
Given(~'^that "70% of evaluations are MANA" and "70% of evaluations are under the class average" are on the the system$'){
    String report1, String report2 ->
        relat1 = Report.findByName(report1)
        relat2 = Report.findByName(report2)
        assert ReportsDataAndOperations.compatibleTo(relat1,report1)
        assert ReportsDataAndOperations.compatibleTo(relat2,report2)
}
When(~'^I add the evaluation "([^"]*)" in the criterion "([^"]*)" with origin "([^"]*)" and date "([^"]*)" to the student with the login "([^"]*)"$'){
    String eval, String criteName, String origin, String dat, String loginA->
        EvaluationDataAndOperations.createEvaluation(criteName, origin, dat)//ele retorna um booleano que verifica se foi criado ou não
        eval = EvaluationDataAndOperations.findEvaluation(criteName, origin, dat)
        EvaluationDataAndOperations.addEvaluationtoStudent(criteName, loginA, eval)//falta implementar
}
Then(~'^70% of the student "([^"]*)" evaluations are composed of "([^"]*)"$'){
   String loginA, String evalType -> ReportsDataAndOperations.checkCondition(loginA, evalType)
}
And(~'^the report "([^"]*)" is updated$'){String relato1 ->
    assert ReportsDataAndOperations.checkUpdate(relato1)
}

//Cenários de GUI
//Ser notificado sobre os problemas de desempenho dos alunos
Given(~'^I am at the home page$'){ ->
    to StudentPage
    at StudentPage
}
When(~'^I go to the Reports page$'){ ->
    to ShowReportsPage
    at ShowReportsPage
}
Then(~'^the item "Reports" on the menu will show if there are new notifications$'){ ->
    /*to ShowReportsPage
    at ShowReportsPage*/
    ShowReportsPage.selectNotifications()
}


