import cucumber.api.PendingException
import pages.ReportsPages.ShowReportsPage
import pages.StudentPages.StudentPage
import steps.AddStudentsTestDataAndOperations
import steps.CriterionTestDataAndOperations
import steps.EvaluationDataAndOperations
import steps.ReportsDataAndOperations
import ta.CriterionController
import ta.Report
import ta.ReportController
import ta.StudentController

/**
 * Created by Milena Carneiro on 12/05/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//Cenários de controlador
//Atualizar um relatório
  Given(~/^that "[(^")*]" and "[(^")*]" are on the system$/) { String report1, String report2 ->
    def control = new ReportController()
    ReportsDataAndOperations.createSaveResetResponse(control,report1)
    ReportsDataAndOperations.createSaveResetResponse(control,report2)
    def relat1 = ReportsDataAndOperations.findByName(report1)
    def relat2 = ReportsDataAndOperations.findByName(report2)
    assert ReportsDataAndOperations.compatibleTo(relat1,report1)
    assert ReportsDataAndOperations.compatibleTo(relat2,report2)
}

When(~/^I add the evaluation "([^"]*)" in the criterion "([^"]*)" with origin "([^"]*)" and date "([^"]*)" to the student with name "([^"]*)" and the login "([^"]*)"$/) { String eval, String criteName, String origin, String dat,String nomeA, String loginA->
    def controS = new StudentController()
    def controC = new CriterionController()
    CriterionTestDataAndOperations.createCriterion(criteName)
    AddStudentsTestDataAndOperations.createStudent(nomeA, loginA)
    EvaluationDataAndOperations.createEvaluation(criteName, origin, dat)//ele retorna um booleano que verifica se foi criado ou não
    EvaluationDataAndOperations.checkEvaluation(criteName, origin, dat)

}

Then(~/^70% of the student "([^"]*)" evaluations are composed of "([^"]*)" in the criterion "([^"]*)"$/) {
    String loginA, String evalType, String crit -> ReportsDataAndOperations.checkCondition(loginA, evalType, crit)
}

And(~/^the report "([^"]*)" is updated$/) { String relato1 ->
    assert ReportsDataAndOperations.checkUpdate(relato1)
}

//Cenários de GUI
//Ser notificado sobre os problemas de desempenho dos alunos

Given(~/^I am at the home page$/) { ->
    to StudentPage
    at StudentPage
}

When(~/^I go to the Reports page$/) { ->
    to ShowReportsPage
    at ShowReportsPage
}

Then(~/^the item "Reports" on the menu will show if there are new notifications$/) { ->
    ShowReportsPage.selectNotifications()
}