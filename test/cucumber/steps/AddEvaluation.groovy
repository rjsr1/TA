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
When I want to add a mark to all students to a the "X" criteria, without a specific origin and dated from "28/03/2016"
Then all the marks will not be stored in on the "X" criteria's history of each student

Scenario: Error related to add a repetead evaluation
Given I am at the "Add concept" screen
And there already are evaluations for the "X" criteria, originated from "Test" and dated from "24/03/2016" in the system
When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
Then an error message related to trying to add a repetead mark will be displayed

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


Given(~'^ there are no evaluations to all students to the "([^"]*)" criterion, originated from a "([^"]*)" and dated from "([^"]*)"$') {
    String criterionName, origin, dateInString;
    Date date = formattedDate(dateInString);

}

When(~'^I create an evaluation criterion with name "([^"]*)"$') { String criterionName ->
    EvaluateStudentTestDataAndOperations.createEvaluationCriterion(criterionName)
}

Then(~'^the evaluation criterion with name "([^"]*)" is properly stored in the system$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) != null
}

//////////////////////////////////
Given(~'^the system already has an evaluation criterion named "([^"]*)"$') { String criterionName ->
    EvaluateStudentTestDataAndOperations.createEvaluationCriterion(criterionName)
    assert EvaluationCriterion.findByName(criterionName) != null
}

When(~'^I create an evaluation criterion with name "([^"]*)"2$') { String criterionName ->
    saved = EvaluateStudentTestDataAndOperations.createEvaluationCriterion(criterionName)
}

Then(~'^the evaluation criterion with name "([^"]*)" was not stored in the system$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) != null && !saved
}

//////////////////////////////////
Given(~'^the system does not have an evaluation criterion with name "([^"]*)"2$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) == null
}

And(~'^the student "([^"]*)" with login "([^"]*)" is registered in the system$') { String studentName, String studentLogin ->
    EvaluateStudentTestDataAndOperations.createStudent(studentLogin, studentName)
    assert Student.findByLogin(studentLogin) != null
}

When(~'^I create an evaluation criterion with name "([^"]*)"3$') { String criterionName ->
    EvaluateStudentTestDataAndOperations.createEvaluationCriterion(criterionName)
}

Then(~'^the system evaluates "([^"]*)" also using the criterion "([^"]*)"$') { String studentName, String criterionName ->
    def evaluationCriterion = EvaluationCriterion.findByName(criterionName)
    assert Student.findByName(studentName).evaluations.get(evaluationCriterion.name) != null
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