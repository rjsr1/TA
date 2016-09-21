package steps

import pages.AddEvaluationPage
import pages.AddStudentsPage
import pages.CreateCriterionPage
import pages.CriterionPages.CriterionPage

import pages.ShowCriterionPage
import pages.StudentPages.ShowStudentPage
import pages.StudentPages.StudentPage
import ta.Criterion
import ta.Evaluation
import ta.EvaluationsByCriterion
import ta.Student

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/**
 * Created by Lapp on 24/06/2016.
 */

// GUI Scenario

Given(~'^a student with name "([^"]*)" and login "([^"]*)" is already on the system$') {
    String name, String login ->
        to AddStudentsPage
        at AddStudentsPage
        page.fillStudentDetails(name, login)
        page.selectAddStudent()
}

And(~'^this student has an evaluation in criterion "([^"]*)"$') {
    String criterionDesc ->
        to CreateCriterionPage
        at CreateCriterionPage
        page.createCriterion(criterionDesc)
        at ShowCriterionPage

        to AddEvaluationPage
        at AddEvaluationPage
        page.selectAddEvaluation()
}

When(~'^I remove the criterion "([^"]*)"$') {
    String criterionDesc ->
        to CriterionPage
        at CriterionPage
        page.selectCriterion(criterionDesc)
        at ShowCriterionPage
        assert page.selectDeleteCriterion()
}

Then(~'^I should not see the criterion "([^"]*)" listed in the student with login "([^"]*)"$') {
    String criterionDesc, String login ->
        to StudentPage
        at StudentPage
        page.selectStudentByLogin(login)
        at ShowStudentPage
        assert page.checkForCriterion(criterionDesc)
}

// Controller Scenario

Student studentToCheck

Given(~'^the system has a student with name "([^"]*)" and login "([^"]*)"$') { String name, String login->
    AddStudentsTestDataAndOperations.createStudent(name, login)
    studentToCheck = Student.findByLogin(login)
    assert studentToCheck.login.equals(login)
    assert studentToCheck.name.equals(name)
}

And(~'^that student has a "([^"]*)" evaluation in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)"$') {
    String evaluationValue, String criterionDescription, String origin, String evaluationDate ->
        assert CommonTestDataAndOperations.giveEvaluationToCriterion(evaluationValue, criterionDescription, origin, evaluationDate, studentToCheck.login)
}

String tempCritDesc

When(~'^I remove the criterion "([^"]*)" from the student "([^"]*)" with login "([^"]*)"$') {
    String criterionDescription, String name, String login ->
        tempCritDesc = criterionDescription
        Criterion criterionToRemove = Criterion.findByDescription(criterionDescription)
        CriterionTestDataAndOperations.removeCriterion(criterionToRemove)
}

Then(~'^the system correctly removes the criterion$') { ->
    assert Criterion.findByDescription(tempCritDesc) == null
    Student.list().each {
        List<EvaluationsByCriterion> ebc = it.criteriaAndEvaluations
        for (int i = 0; i < ebc.size(); i++) {
            assert !ebc.get(i).criterion.description.equals(tempCritDesc)
        }
    }
}