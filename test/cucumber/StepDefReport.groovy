
import pages.StudentPages.StudentPage
import steps.EvaluationDataAndOperations
import steps.ReportsDataAndOperations
import ta.ReportController
import steps.AddStudentsTestDataAndOperations
import steps.StudentConsultTestDataAndOperations
import pages.ReportsPages.ShowReportsPage
import static steps.EvaluationDataAndOperations.*
import static steps.StudentConsultTestDataAndOperations.lookForStudent

/**
 * Created by Milena Carneiro on 16/06/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^that "([^"]*)" and "([^"]*)" are on the system$/) {
    String report1, String report2 ->
    def contro = new ReportController()
    ReportsDataAndOperations.createSaveResetResponse(contro, report1)
    ReportsDataAndOperations.createSaveResetResponse(contro, report2)
    def relat1 = ReportsDataAndOperations.findByName(report1)
    def relat2 = ReportsDataAndOperations.findByName(report2)
    assert ReportsDataAndOperations.compatibleTo(relat1,report1)
    assert ReportsDataAndOperations.compatibleTo(relat2,report2)
}
When(~'^I add the evaluation "([^"]*)" in the criterion "([^"]*)" with origin "([^"]*)" and date "([^"]*)" to the student with name "([^"]*)" and the login "([^"]*)"$'){
    String eval, String criteName, String origin, String dat, String nomeA, String loginA->
        AddStudentsTestDataAndOperations.createStudent(nomeA,loginA)
        assert StudentConsultTestDataAndOperations.compatibleSearch(loginA)
        createCritAndAddToStudents(criteName)
        createEvaluation(criteName, origin, dat)
        assert existEvaluation(criteName, origin, dat)
        ReportsDataAndOperations.addEvalToStudent(loginA, origin, eval, dat, criteName)
}
Then(~'^70% of the student "([^"]*)" evaluations are composed of "([^"]*)"$'){
    String loginA, String evalType-> assert ReportsDataAndOperations.checkCondition(loginA, evalType)
}
And(~'^the report "([^"]*)" is updated$'){String relato1 ->
    assert ReportsDataAndOperations.checkUpdate(relato1)
}


