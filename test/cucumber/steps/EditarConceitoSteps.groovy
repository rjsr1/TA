import cucumber.api.PendingException
import pages.StudentPages.CreateStudentPage

/**
 * Created by TMB on 12/05/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*Given(~/^there is a student with the following information: student "([^"]*)", login "([^"]*)", has a "([^"]*)" evaluation in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4, String arg5, String arg6 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}*/

/*
Feature: Edit Evaluation
  As a teacher
  I want to edit the given evaluations with respect to various criteria
  So I can fix any errors
  */

/*
Scenario: Edit evaluation of a student
  Given there is a student with the following information: student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I modify the "MANA" evaluation to "MA" in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then the system stores the modification
  */
//String globalStudentName, globalStudentLogin, globalNewEvaluation, globalCriterionName, globalEvaluationOrigin/*, globalDate*/, globalEvaluationDate;
Student globalStudent;

Given(~'^there is a student with the following information: student "([^"]*)", login "([^"]*)", has a "([^"]*)" evaluation in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$'){
    String studentName, studentLogin, studentEvaluation, criterionName, evaluationOrigin, evaluationDate ->
        //date = formattedDate(criterionDate)
        //assert EvaluationDataAndOperations.findEvaluationInStudent(studentName, studentLogin, studentEvaluation, criterionName, criterionOrigin, date) != null
        EvaluationDataAndOperations.createAndGiveEvaluation(studentName, studentLogin, studentEvaluation, criterionName, evaluationOrigin, evaluationDate)

}

When(~'^I modify the "([^"]*)" evaluation to "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)", from student "([^"]*)", login "([^"]*)"$'){
    String oldEvaluation, newEvaluation, criterionName, evaluationOrigin, evaluationDate, studentName, studentLogin ->
        //date = formattedDate(criterionDate)
        //globalStudentName = studentName
        //globalStudentLogin = studentLogin
        //globalNewEvaluation = newEvaluation
        //globalCriterionName = criterionName
        //globalEvaluationOrigin = evaluationOrigin
        //globalDate = evaluationDate
        //globalEvaluationDate = evaluationDate
        EvaluationDataAndOperations.updateEvaluationInStudent(studentName, studentLogin, newEvaluation, criterionName, evaluationOrigin, evaluationDate)
        globalStudent = EvaluationDataAndOperations.getStudent(studentLogin)
}

Then(~'^the system stores the modification in the student with login "([^"]*)"$'){
    //date = formattedDate(globalDate)
    //assert EvaluationDataAndOperations.checkEvaluationInStudent(globalStudentName, globalStudentLogin, globalNewEvaluation, globalCriterionName, globalEvaluationOrigin, globalEvaluationDate) != null
    assert EvaluationDataAndOperations.compatibleTo(Student.findByLogin(login), globalStudent)
}

/*
Scenario: Edit an evaluation for a non existent one
  Given I see the student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I request the system to modify the evaluation "MANA" to "10" in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then an error message related to the evaluation appear
 */

Given(~'^I see the student "([^"]*)", login "([^"]*)", has a "([^"]*)" evaluation in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$'){
    String studentName, studentLogin, studentEvaluation, criterionName, evaluationOrigin, evaluationDate ->
        to CreateStudentPage
        at CreateStudentPage

        page.fillStudentDetails(studentName, studentLogin)
        page.selectCreateStudent()

        to AddEvaluationToStudentPage
        at AddEvaluationToStudentPage

        //date = formattedDate(criterionDate)
        page.fillEvaluationDetails(criterionName, evaluationOrigin, evaluationDate, studentEvaluation)
        page.selectAddEvaluationToStudent()
}

When(~'^I request the system to modify the evaluation "([^"]*)" to "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)", from student "([^"]*)", login "([^"]*)"$'){
    String oldEvaluation, newEvaluation, criterionName, evaluationOrigin, evaluationDate, studentName, studentLogin ->
        to UpdateEvaluationFromStudentPage
        at UpdateEvaluationFromStudentPage

        //date = formattedDate(criterionDate)
        page.fillEvaluationFromStudentDetails(studentName, studentLogin, criterionName, evaluationOrigin, evaluationDate, oldEvaluation, newEvaluation)
        page.selectUpdateEvaluationFromStudent()
}

Then(~'^an error message related to the evaluation appear$'){
    to UpdateEvaluationFromStudentPage
    at UpdateEvaluationFromStudentPage

    page.showErrorMessage("Invalid Evaluation")
}

