package steps

import pages.ReportsPages.CreateReportsPage
import steps.CriterionTestDataAndOperations
import steps.ReportsDataAndOperations
import ta.ReportController
import steps.AddStudentsTestDataAndOperations
import steps.StudentConsultTestDataAndOperations
import pages.ReportsPages.ShowReportsPage
import static steps.EvaluationDataAndOperations.*
import pages.ReportsPages.IndexReport


/**
 * Created by Milena Carneiro on 16/06/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
//Controler scenario: Update a report
Given(~/^that report with name "([^"]*)", evaluation "([^"]*)", type "([^"]*)", value "([^"]*)" is in the system$/) { String repoName, String repoEval,
                                                                                                                      String  repoType, double repoVal->
    def contro = new ReportController()
    ReportsDataAndOperations.createSaveResetResponse(contro, repoName, repoEval, repoType,repoVal)
    def relat1 = ReportsDataAndOperations.acheNome(repoName)
    assert ReportsDataAndOperations.compatibleTo(relat1,repoName)
}

When(~'^I add the evaluation "([^"]*)" in the criterion "([^"]*)" with origin "([^"]*)" and date "([^"]*)" to the student with name "([^"]*)" and the login "([^"]*)"$'){
    String eval, String criteName, String origin, String dat, String nomeA, String loginA->
        AddStudentsTestDataAndOperations.createStudent(nomeA,loginA)
        assert StudentConsultTestDataAndOperations.compatibleSearch2(loginA)
        CriterionTestDataAndOperations.createCriterion(criteName)
        createEvaluation(eval,criteName, origin, dat)//ele retorna um booleano que verifica se foi criado ou não
        assert existEvaluation(criteName, origin, dat)
}
Then(~'^70% of the student "([^"]*)" evaluations are composed of "([^"]*)"$'){
    String loginA, String evalType-> assert ReportsDataAndOperations.checkCondition(loginA, evalType)
}


And(~/^the report "([^"]*)" is updated adding the student with login "([^"]*)"$/) { String repoName, String loginA ->
   assert ReportsDataAndOperations.checkUpdate(repoName,loginA)
}
//end controller scenario

//Variáveis globais para fazer a checagem no passo final do teste de GUI
String nomeReport
String tipoReport
String avaliacaoReport
double valorReport
//GUI scenario: Show the students list in the "70% of evaluations are over the class average" report
Given(~/^I am in the Report page$/) { ->
    to IndexReport
    at IndexReport
}
And(~/^I try to create the report named "([^"]*)", type "([^"]*)", value "([^"]*)", evaluation "([^"]*)"$/) { String arg1, String arg2, double arg3, String arg4 ->
    nomeReport = arg1
    tipoReport = arg2
    valorReport = arg3
    avaliacaoReport = arg4

    to CreateReportsPage
    at CreateReportsPage
    page.fillReportDetails(arg1,arg2,arg4,arg3)
    page.createReport()
    to IndexReport
}
When(~/^I select the "([^"]*)" report$/) { String arg1 ->
    at IndexReport
    page.selectReport(arg1)
}
Then(~/^I should see the details related to the "([^"]*)" report$/) { String arg1 ->
    at ShowReportsPage
//    assert page.checkName(nomeReport)
//    assert page.checkType(tipoReport)
//    assert page.checkAvaliacao(avaliacaoReport)
}
//end GUI scenario
