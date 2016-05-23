/**
 * Created by Milena Carneiro on 28/04/2016.
 */
package steps


import funciotnal.pages.ReportsPages.ReportsPage
import functional.pages.StudentPages.StudentPage
import pages.ReportsPages.ShowReportsPage
import ta.ReportController
import ta.StudentController

//Cenários de controlador
//Atualizar um relatório
Given(~'^that "70% of evaluations are MANA" and "70% of evaluations are under the class average" are on the the system$'){
    String report1, String report2 ->
        def contro = new ReportController()
        ReportsDataAndOperations.createSaveResetResponse(contro, report1)
        ReportsDataAndOperations.createSaveResetResponse(contro, report2)
        relat1 = contro.findByName(report1)
        relat2 = contro.findByName(report2)
        assert ReportsDataAndOperations.compatibleTo(relat1,report1)
        assert ReportsDataAndOperations.compatibleTo(relat2,report2)
}
When(~'^I add the evaluation "([^"]*)" in the criterion "([^"]*)" with origin "([^"]*)" and date "([^"]*)" to the student with name "([^"]*)" and the login "([^"]*)"$'){
    String eval, String criteName, String origin, String dat, String nomeA, String loginA->
        def controS = new StudentController()
        AddStudentsTestDataAndOperations.createStudent(nomeA,loginA)
        StudentConsultTestDataAndOperations.studentExists(loginA)
        EvaluationTestDataAndOperations.createCritAndAddToStudents(criteName)
        EvaluationDataAndOperations.createEvaluation(criteName, origin, dat)//ele retorna um booleano que verifica se foi criado ou não
        EvaluationDataAndOperations.existEvaluation(criteName, origin, dat)
        controS.addEvaluationToStudent2(login,dat)
}
Then(~'^70% of the student "([^"]*)" evaluations are composed of "([^"]*)" in the criterion "([^"]*)"$'){
   String loginA, String evalType, String crit -> ReportsDataAndOperations.checkCondition(loginA, evalType, crit)
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
    to ShowReportsPage
    at ShowReportsPage
    ShowReportsPage.selectNotifications()
}


