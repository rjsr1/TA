package steps

import pages.EvaluationCriterionPages.CreateEvaluationCriterionPage
import pages.EvaluationCriterionPages.EvaluationCriterionPage
import pages.StudentPages.CreateStudentPage
import pages.StudentPages.StudentPage
import ta.Evaluation
import ta.Student
import ta.funciontal.EvaluationdDataAndOperations

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*Feature AddEvaluation
As a teacher, I want to evaluate each student by criteria, to show them their progress in class
Scenario: Add evaluation to a criteria
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
When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
Then all the marks will be stored in on the "X" criteria's history of each student


Scenario: Import repeated evaluations
Given I organized all evaluations for the "X" criteria originated from "Midterm", dated from "31/03/2016" in a spreedsheet
And there already are evaluations for the "X" criteria, originated from "Midterm" and dated from "31/03/2016" in the system
When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
Then all the evaluations will not be stored in on the "X" criteria's history of each student
*/
String criterionNameGlobal, originGlobal
String dateGlobal;

Given(~'^ there are no evaluations to all students to the "([^"]*)" criterion, originated from a "([^"]*)" and dated from "([^"]*)"$') {
    String criterionName, origin, dateInString ->
    date = formattedDate(dateInString);
    assert EvaluationDataAndOperations.findEvaluation(criterionName,origin,date) == null;
}

/*When(~'^I create an evaluation criterion with name "([^"]*)"$') { String criterionName ->
    EvaluateStudentTestDataAndOperations.createEvaluationCriterion(criterionName)
}*/
When(~'^I want to evaluate all students to the  "([^"]*)" criterion, originated from a  "([^"]*)" and dated from  "([^"]*)".$'){
    String criterionName, origin, dateInString -> date = formattedDate(dateInString)
        dateGlobal = date
        criterionNameGlobal = criterionName
        originGlobal = origin
        EvaluationDataAndOperations.createEvaluation(criterionName,origin,date)
}

Then(~'^all the evaluations will be stored in on the "([^"]*)" criterion history of each student .$'){
    String criterionName -> assert EvaluationDataAndOperations.checkEvaluation(criterionName,originGlobal,dateGlobal)
}

/*Then(~'^the evaluation criterion with name "([^"]*)" is properly stored in the system$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) != null
}*/

/*Scenario: Add evaluations using incomplete data
Given there are no evaluations to all students to the "X" criterion, dated from "28/03/2016", with any origin
        When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
        Then all evaluations will not be stored in on the "X" criterion history of each student*/
//////////////////////////////////
Given(~'^there are no evaluations to all students to the "([^"]*)" criterion, dated from "([^"]*)", with any origin $') { String criterionName, dateInString ->
    assert EvaluationDataAndOperations.existEvaluation(criterionName,dateInString) == false
}

When(~'^I want to evaluate all studentes to the "([^"]*)" criterion, withou a specific origin and dated from "([^"]*)"$') { String criterionName, dateInString ->
    EvaluationDataAndOperations.createEvaluation(criterionName,dateInString)
    criterionNameGlobal = criterionName
    dateInStringGlobal = dateInString;
}

Then(~'^the evaluation criterion with name "([^"]*)" was not stored in the system$') { String criterionName ->
    assert EvaluationDataAndOperations.existEvaluation(criterionName,dateInString) == false
}

//////////////////////////////////

/*Scenario: Add evaluation more than once with same origin
Given evaluations for every student on the "X" criteria, originated form "Test" and dated from "28/03/2016" are already in the system
   When I want to evaluate all students on the "X" criteria, originated from "Test" and dated from "28/03/2016"
   Then all the marks will not be stored in on the "X" criteria's history of each student*/
Boolean stored = false;
Given(~'^evaluations for every student on the "([^"]*)" criteria, originated form "([^"]*)" and dated from "([^"]*)" are already in the system$') {
    String criterionName, origin, dateInString ->
    assert EvaluationDataAndOperations.existEvaluation(criterionName,dateInString) == true
}

When(~'^I want to evaluate all students on the"([^"]*)" criteria, originated from "([^"]*)" and date from "([^"]*)" $') {
    String criterionName, origin, dateInString ->
    assert EvaluationDataAndOperations.createEvaluation(criterionName,origin,dateInString) == false
        stored = EvaluationDataAndOperations.createEvaluation(criterionName,origin,dateInString)
}

Then(~'^all the marks will not be stored in on the"([^"]*)" criterias history of each student$') {
    String criterionName->
        assert EvaluationDataAndOperations.checkChangesEvaluations(criterionName)
}

////////////////////////////////
Given(~'^I am on the Students Page$') { ->
    to StudentPage
    at StudentPage
}

And(~'^the student "([^"]*)" with login "([^"]*)" is registered in the system2$') { String name, String login ->
    to CreateStudentPage
    at CreateStudentPage

    page.fillStudentDetails(login, name)
    page.selectCreateStudent()
}

And(~'^there is a criterion called "([^"]*)" registered in the system$') { String evaluationCriterion ->
    to CreateEvaluationCriterionPage
    at CreateEvaluationCriterionPage

    page.fillEvaluationCriterionDetails(evaluationCriterion)
    page.selectCreateEvaluationCriterion()
}

When(~'^I go to the Students Page$') { ->
    to StudentPage
    at StudentPage
}

Then(~'^I am should see a table with "([^"]*)" in a row and "([^"]*)" in a column$') { String arg1, String arg2->
    assert true
}

/////////////////////////////////////
Given(~'^I am on the Evaluation Criterion Page$') { ->
    to EvaluationCriterionPage
    at EvaluationCriterionPage
}

And(~'^I follow new evaluation criterion$') { ->
    to CreateEvaluationCriterionPage
    at CreateEvaluationCriterionPage
}

When(~'^I fill "([^"]*)" in the Name field$') { String criterionName ->
    at CreateEvaluationCriterionPage

    page.fillEvaluationCriterionDetails(criterionName)

    criterionSaved = criterionName
}

And(~'^I click Save$') { ->
    page.selectCreateEvaluationCriterion()
}
Then(~'^I am should see the Students page with a new column named "([^"]*)"$') { String criterionName ->
    to StudentPage
    at StudentPage

    assert criterionName == criterionSaved
}