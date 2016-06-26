package steps

import pages.StudentPages.StudentPage
import ta.Evaluation
import ta.Student
import steps.EvaluationDataAndOperations
import pages.*
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


String criterionNameGlobal, originGlobal;
String dateGlobal;

/*Then(~'^the evaluation criterion with name "([^"]*)" is properly stored in the system$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) != null
}*/

/*Scenario: Add evaluations using incomplete data
Given there are no evaluations to all students to the "X" criterion, dated from "28/03/2016", with any origin
        When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
        Then all evaluations will not be stored in on the "X" criterion history of each student*/
//////////////////////////////////

//////////////////////////////////

/*Scenario: Add evaluation more than once with same origin
Given evaluations for every student on the "X" criteria, originated form "Test" and dated from "28/03/2016" are already in the system
   When I want to evaluate all students on the "X" criteria, originated from "Test" and dated from "28/03/2016"
   Then all the marks will not be stored in on the "X" criteria's history of each student*/
Boolean stored = false;


/*Scenario: Error related to add a  evaluation
Given I am at the "Add concept" screen
When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
Then an error message related to trying to add a evaluation with missing values will be displayed*/
////////////////////////////////
/*Given(~'^I am on the "Evaluation page"$') { ->
    to EvaluationPage
    at EvaluationPage
}

When(~'^I want to evaluate all students to a the "([^"]*)" criteria, without a specific origin and dated from"([^"]*)"$') {
    String criteriondName, dateInString ->
    page.fillEvaluationDetails(criterionName,dateInString)
}

Then(~'^an error message related to trying to add a evaluation with missing values will be displayed$') {
    page.showErrorMensagem("Missing values")
}*/

/*Scenario: Import evaluations
Given I organized all evaluations for the "X" criterion originated from "Midterm", dated from "31/03/2016" in a spreedsheet
When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
Then all the marks will be stored in on the "X" criteria's history of each student*/

/////////////////////////////////////
/*Given(~'^I organized all evaluations for the "([^"]*)" criterion originated from "([^"]*)", date from "([^"]*)" in a spreedsheet named "([^"]*)"$') {
    String criterionName, origin, dateInString, fileName ->
        EvaluationDataAndOperations.checkFileExistence(criterionName,origin,dateInString,fileName)
}
And(~'^there are no evaluations for the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)" in the system') {
    String criterionName, origin, dateInString ->
        assert EvaluationDataAndOperations.existEvaluation(criterionName, origin, dateInString) == false
}
When(~'^I want to import all evaluations from the spreedsheet named"([^"]*)" to add to all students "([^"]*)" criterias history, originated from "([^"]*)" and dated from "([^"]*)"$') {
    String fileName, criterionName, origin, dateInString ->
        EvaluationDataAndOperations.importSpreedSheet(criterionName,origin,dateInString,fileName)
}

Then(~'^all evaluations will be stored on the "([^"]*)" criterias history of each student $') {
    String criterionName ->
        assert EvaluationDataAndOperations.checkImportCriterion(criterionName) == true;
}

Given(~'^I organized all evaluations for the "([^"]*)" criterion originated from "([^"]*)", date from "([^"]*)" in a spreedsheet named "([^"]*)"$') {
    String criterionName, origin, dateInString, fileName ->
        EvaluationDataAndOperations.checkFileExistence(criterionName,origin,dateInString,fileName)
}
And(~'^there already are evaluations for the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)" in the system') {
    String criterionName, origin, dateInString ->
        assert EvaluationDataAndOperations.checkEvaluationAllStudents(criterionName,origin,dateInString) == true
}
When(~'^I want to import all evaluations from the spreedsheet named"([^"]*)" to add to all students "([^"]*)" criterias history, originated from "([^"]*)" and dated from "([^"]*)"$') {
    String fileName, criterionName, origin, dateInString ->
        EvaluationDataAndOperations.importSpreedSheet(criterionName,origin,dateInString,fileName)
}

Then(~'^all evaluations will be stored on the "([^"]*)" criterias history of each student $') {
    String criterionName ->
        assert EvaluationDataAndOperations.checkImportCriterion(criterionName) == false;
}*/
def numberOfEvaluationsBeforeStep = []
Given(~/^there are no evaluations to all students to the "([^"]*)" criterion, originated from a "([^"]*)" and dated from "([^"]*)"$/) {
    String criterionName, origin, dateInString ->
        EvaluationDataAndOperations.createStudents(1);
        EvaluationDataAndOperations.createCriterion(criterionName);
        numberOfEvaluationsBeforeStep = EvaluationDataAndOperations.numberOfEvaluationsBeforeTest();
        assert EvaluationDataAndOperations.checkEvaluationAllStudents(numberOfEvaluationsBeforeStep,"same");

}
When(~/^I want to evaluate all students to the "([^"]*)" criterion, originated from a "([^"]*)" and dated from "([^"]*)"\.$/) {
    String criterionName, origin, dateInString ->
        dateGlobal = dateInString
        criterionNameGlobal = criterionName
        originGlobal = origin
        String value = "MA";
        EvaluationDataAndOperations.createEvaluation(value, criterionName, origin, dateInString)

}
Then(~/^all the evaluations will be stored in on the "([^"]*)" criterion history of each student$/) {
    String criterionName -> assert EvaluationDataAndOperations.checkEvaluationAllStudents(numberOfEvaluationsBeforeStep,"more")

}
///
Given(~/^there are no evaluations to all students to the "([^"]*)" criterion,$/) {
    String criterionName ->
        assert EvaluationDataAndOperations.createStudents(2);
        assert EvaluationDataAndOperations.createCriterion(criterionName);
        numberOfEvaluationsBeforeStep = EvaluationDataAndOperations.numberOfEvaluationsBeforeTest()
        assert EvaluationDataAndOperations.checkEvaluationAllStudents(numberOfEvaluationsBeforeStep, "same")
}

When(~/^I want to evaluate all students to a the "([^"]*)" criteria, without a specific origin and dated from "([^"]*)"\.$/) { String criterionName, dateInString ->
    criterionNameGlobal = criterionName
    dateGlobal = dateInString;
    assert EvaluationDataAndOperations.createEvaluation(null, criterionName, "", dateInString) == false
}
Then(~/^all evaluations will not be stored in on the "([^"]*)" criterion history of each student$/) { String criterionName ->
    assert EvaluationDataAndOperations.checkEvaluationAllStudents(numberOfEvaluationsBeforeStep, "same")
}
///
Given(~'^evaluations for every student on the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)" are already in the system$') {
    String criterionName, origin, dateInString ->
        EvaluationDataAndOperations.createStudents(5);
        EvaluationDataAndOperations.createCriterion(criterionName);
        EvaluationDataAndOperations.createEvaluation("MANA",criterionName, origin, dateInString);
        numberOfEvaluationsBeforeStep = EvaluationDataAndOperations.numberOfEvaluationsBeforeTest();
        assert EvaluationDataAndOperations.checkEvaluationAllStudents(numberOfEvaluationsBeforeStep, "same") == true
}
When(~/^I want to add a mark to all students to a the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)"$/) {
    String criterionName, origin, dateInString ->
        dateGlobal = dateInString;
        originGlobal = origin;
        EvaluationDataAndOperations.createEvaluation("MANA", criterionName, origin, dateInString) == false
}
Then(~/^all the marks will not be stored in on the "([^"]*)" criteria's history of each student$/) {
    String criterionName ->
        assert EvaluationDataAndOperations.checkEvaluationAllStudents(numberOfEvaluationsBeforeStep, "same")
}

String studentNameGlobal, studentLoginGlobal;
Given(~/^I see the student "([^"]*)", login "([^"]*)" and the criterion "([^"]*)"$/) {
    String studentName, studentLogin, criterionName ->
        to AddStudentsPage
        at AddStudentsPage
        studentLoginGlobal = studentLogin;
        studentNameGlobal = studentName;
        page.fillStudentDetails(studentName, studentLogin)
        page.selectCreateStudent()

        to StudentPage

        assert page.confirmStudent(studentName, studentLogin)

        to CreateCriterionPage
        at CreateCriterionPage

        page.fillCriterionDetails(criterionName)
        page.selectCreateCriterion()

        to CriterionPage

        assert page.confirmCriterion(criterionName)

}

When(~/^I request the system to add the evaluation valued "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$/) {
    String value, criterionName, evaluationOrigin, evaluationDate, studentName, studentLogin ->
        to AddEvaluationPage
        at AddEvaluationPage
        page.chooseCriterion(criterionName)
        page.chooseValue(value)
        page.chooseOrigin(evaluationOrigin)
        page.chooseEvaluationDate(evaluationDate)
        assert page.selectCreateStudent()
}

Then(~/^I can see the evaluation valued "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)" in the evaluation screen$/) {
    String criterionName, evaluationOrigin, evaluationDate ->
        to StudentPage
        at StudentPage

        page.selectStudent(studentNameGlobal, studentLoginGlobal)

        to ShowStudentPage

        assert page.confirmStudent(studentName, studentLogin)
        page.selectCriterion(criterionName)

        to ShowEvaluationByCriterionPage

        assert page.checkForEvaluation(evaluationOrigin)
}

/*
Given(~/^I am at the "([^"]*)" screen$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions

}
And(~/^there already are evaluations for the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)" in the system$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions

}
Then(~/^an error message related to trying to add a repetead mark will be displayed$/) { ->
    // Write code here that turns the phrase above into concrete actions

}
//
Given(~/^I organized all evaluations for the "([^"]*)" criterion originated from "([^"]*)", dated from "([^"]*)" in a spreedsheet$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions

}
When(~/^I want to import all evaluations from the spreedsheet to add to all students "([^"]*)" criterias history, originated from "([^"]*)" and dated from "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions

}
Then(~/^all the marks will be stored in on the "([^"]*)" criteria's history of each student$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
//
Given(~/^I organized all evaluations for the "([^"]*)" criterion originated from "([^"]*)" dated from "([^"]*)" in a spreedsheet$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions

}
And(~/^there already are evaluations for the "([^"]*)" criterion, originated from "([^"]*)" and dated from "([^"]*)" in the system$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions

}
Then(~/^all the evaluations will not be stored in on the "([^"]*)" criteria's history of each student$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions

}*/
