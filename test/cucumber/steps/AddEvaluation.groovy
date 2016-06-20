package steps

import cucumber.api.PendingException
import pages.EvaluationCriterionPages.CreateEvaluationCriterionPage
import pages.EvaluationCriterionPages.EvaluationCriterionPage
import pages.StudentPages.CreateStudentPage
import pages.StudentPages.StudentPage
import ta.Evaluation
import ta.Student
import steps.EvaluationDataAndOperations

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*Feature AddEvaluation
As a teacher, I want to evaluate each student by criteria, to show them their progress in class
Scenario: Add evaluation to a cri teria
Given there are no evaluations to all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016"
When I want to evaluate all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016".
        Then all the evaluations will be stored in on the "X" criterion history of each student

Scenario: Add evaluations using incomplete data
Given there are no evaluations to all students to the "X" criterion,
        When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
        Then all evaluations will not be stored in on the "X" criterion history of each student

Scenario: Add evaluation more than once with same origin
Given evaluations for every student on the "X" criteria, originated form "Test" and dated from "28/03/2016" are already in the system
When I want to add a mark to all students to a the "X" criteria, originated from "Test" and dated from "28/03/2016"
Then all the marks will not be stored in on the "X" criteria's history of each student

Scenario: Error related to add a  evaluation
Given I am at the "Add concept" screen
When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
Then an error message related to trying to add a evaluation with missing values will be displayed

Scenario: Import evaluations
Given I organized all evaluations for the "X" criteron originated from "Midterm", dated from "31/03/2016" in a spreedsheet
And there are no evaluations for the "X" criteria, originated from "Midterm" and dated from "31/03/2016" in the system
When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
Then all the evaluations will be stored in on the "X" criteria's history of each student


Scenario: Import repeated evaluations
Given I organized all evaluations for the "X" criteria originated from "Midterm", dated from "31/03/2016" in a spreedsheet
And there already are evaluations for the "X" criteria, originated from "Midterm" and dated from "31/03/2016" in the system
When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
Then all the evaluations will not be stored in on the "X" criteria's history of each student
*/
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
Given(~'^evaluations for every student on the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)" are already in the system$') {
    String criterionName, origin, dateInString ->
        EvaluationDataAndOperations.createStudents();
        EvaluationDataAndOperations.createCriterionX();
        EvaluationDataAndOperations.createEvaluationNoValue(criterionName,origin,dateInString);
    assert EvaluationDataAndOperations.checkEvaluationAllStudents(criterionName,origin, dateInString) == true
}

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

Given(~/^there are no evaluations to all students to the "([^"]*)" criterion, originated from a "([^"]*)" and dated from "([^"]*)"$/) {
    String criterionName, origin, dateInString ->
    EvaluationDataAndOperations.createStudents();
    EvaluationDataAndOperations.createCriterionX();
    assert EvaluationDataAndOperations.findEvaluationAndCount(criterionName,origin,dateInString);

}
When(~/^I want to evaluate all students to the "([^"]*)" criterion, originated from a "([^"]*)" and dated from "([^"]*)"\.$/) {
    String criterionName, origin, dateInString ->
    dateGlobal = dateInString
    criterionNameGlobal = criterionName
    originGlobal = origin
        String value = "--";
    EvaluationDataAndOperations.createEvaluation(value,criterionName,origin,dateInString)

}
Then(~/^all the evaluations will be stored in on the "([^"]*)" criterion history of each student$/) {
    String criterionName -> assert EvaluationDataAndOperations.checkEvaluationAllStudents(criterionName,originGlobal,dateGlobal)

}
///
Given(~/^there are no evaluations to all students to the "([^"]*)" criterion,$/) {
    String criterionName, dateInString ->
    EvaluationDataAndOperations.createStudents();
    EvaluationDataAndOperations.createCriterionXandAddToStudents();
    assert EvaluationDataAndOperations.checkEvaluationAllStudents(criterionName,"--",dateInString) == false
}
When(~/^I want to evaluate all students to a the "([^"]*)" criteria, without a specific origin and dated from "([^"]*)"\.$/) { String criterionName, dateInString ->
    EvaluationDataAndOperations.createEvaluation("--",criterionName,'--',dateInString)
    criterionNameGlobal = criterionName
    dateGlobal = dateInString;
}
Then(~/^all evaluations will not be stored in on the "([^"]*)" criterion history of each student$/) {  String criterionName ->
    assert EvaluationDataAndOperations.checkEvaluationAllStudents(criterionName,"--",dateGlobal) == false
}
///
 When(~/^I want to add a mark to all students to a the "([^"]*)" criteria, originated from "([^"]*)" and dated from "([^"]*)"$/) {
    String criterionName, origin, dateInString ->
    stored = EvaluationDataAndOperations.createEvaluation("--",criterionName,origin,dateInString);
    dateGlobal = dateInString;
    originGlobal = origin;
    assert EvaluationDataAndOperations.createEvaluation(null,criterionName,origin,dateInString) == false
}
Then(~/^all the marks will not be stored in on the "([^"]*)" criteria's history of each student$/) {
    String criterionName->
    assert EvaluationDataAndOperations.checkEvaluationRedundantAllStudents(criterionName,originGlobal,dateGlobal)
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
