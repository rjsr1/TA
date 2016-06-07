package steps

import pages.EvaluationCriterionPages.CreateEvaluationCriterionPage
import pages.EvaluationCriterionPages.EvaluationCriterionPage
import pages.StudentPages.CreateStudentPage
import pages.StudentPages.StudentPage
import ta.EvaluationCriterion
import ta.Student

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~'^the system does not have an evaluation criterion with name "([^"]*)"$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) == null
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

/* COMENTADO POR RODRIGO CALEGARIO 28/05/16
Then(~'^the evaluation criterion with name "([^"]*)" was not stored in the system$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) != null && !saved
}
*/

//////////////////////////////////
Given(~'^the system does not have an evaluation criterion with name "([^"]*)"2$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) == null
}

/* COMENTADO POR RODRIGO CALEGARIO 28/05/16
And(~'^the student "([^"]*)" with login "([^"]*)" is registered in the system$') { String studentName, String studentLogin ->
    EvaluateStudentTestDataAndOperations.createStudent(studentLogin, studentName)
    assert Student.findByLogin(studentLogin) != null
}
*/

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