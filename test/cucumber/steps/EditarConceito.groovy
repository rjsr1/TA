import pages.StudentPages.CreateStudentPage

/**
 * Created by TMB on 02/05/2016.
 */

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
/*
String globalStudentName, globalStudentLogin, globalNewEvaluation, globalCriterionName, globalCriterionOrigin, globalDate;

Given(~'^there is a student with the following information: student "([^"]*)", login "([^"]*)", has a "([^"]*)" evaluation in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$'){
    String studentName, studentLogin, studentEvaluation, criterionName, criterionOrigin, criterionDate ->
        date = formattedDate(criterionDate)
        assert EvaluationDataAndOperations.findEvaluationInStudent(studentName, studentLogin, studentEvaluation, criterionName, criterionOrigin, date) != null
}

When(~'^I modify the "([^"]*)" evaluation to "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)", from student "([^"]*)", login "([^"]*)"$'){
    String oldEvaluation, newEvaluation, criterionName, criterionOrigin, criterionDate, studentName, studentLogin ->
        date = formattedDate(criterionDate)
        globalStudentName = studentName
        globalStudentLogin = studentLogin
        globalNewEvaluation = newEvaluation
        globalCriterionName = criterionName
        globalCriterionOrigin = criterionOrigin
        globalDate = criterionDate
        EvaluationDataAndOperations.updateEvaluationInStudent(studentName, studentLogin, newEvaluation, criterionName, criterionOrigin, date)
}

Then(~'^the system stores the modification$'){
    date = formattedDate(globalDate)
    assert EvaluationDataAndOperations.checkEvaluationInStudent(globalStudentName, globalStudentLogin, globalNewEvaluation, globalCriterionName, globalCriterionOrigin, globalDate) != null
}

/*
Scenario: Edit an evaluation for a non existent one
  Given I see the student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I request the system to modify the evaluation "MANA" to "10" in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then an error message related to the evaluation appear
 */
/*
Given(~'^there is a student with the following information: student "([^"]*)", login "([^"]*)", has a "([^"]*)" evaluation in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$'){
    String studentName, studentLogin, studentEvaluation, criterionName, criterionOrigin, criterionDate ->
        to CreateStudentPage
        at CreateStudentPage

        page.fillStudentDetails(studentName, studentLogin)
        page.selectCreateStudent()

        to AddEvaluationToStudentPage
        at AddEvaluationToStudentPage

        date = formattedDate(criterionDate)
        page.fillEvaluationDetails(criterionName, criterionOrigin, date, studentEvaluation)
        page.selectAddEvaluationToStudent()
}

When(~'^I request the system to modify the evaluation "([^"]*)" to "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)", from student "([^"]*)", login "([^"]*)"$'){
    String oldEvaluation, newEvaluation, criterionName, criterionOrigin, criterionDate, studentName, studentLogin ->
        to UpdateEvaluationFromStudentPage
        at UpdateEvaluationFromStudentPage

        date = formattedDate(criterionDate)
        page.fillEvaluationFromStudentDetails(studentName, studentLogin, criterionName, criterionOrigin, criterionDate, oldEvaluation, newEvaluation)
        page.selectUpdateEvaluationFromStudent()
}

Then(~'^an error message related to the evaluation appear$'){
    to UpdateEvaluationFromStudentPage
    at UpdateEvaluationFromStudentPage

    page.showErrorMessage("Invalid Evaluation")
}
*/
