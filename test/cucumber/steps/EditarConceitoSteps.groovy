import cucumber.api.PendingException
import pages.EditEvaluationPage
//import pages.StudentPages.CreateStudentPage
import pages.AddStudentsPage
import pages.StudentPages.StudentPage
import pages.CriterionPages.CriterionPage
import pages.CreateCriterionPage
import steps.EvaluationDataAndOperations
import ta.Student

/**
 * Created by TMB on 19/05/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

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
        //EvaluationdDataAndOperations.createAndGiveEvaluation(studentName, studentLogin, studentEvaluation, criterionName, evaluationOrigin, evaluationDate)
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
        EvaluationDataAndOperations.updateEvaluationInStudent(studentLogin, newEvaluation, criterionName, evaluationOrigin)
        globalStudent = EvaluationDataAndOperations.getStudent(studentLogin)
}

Then(~'^the system stores the modification in the student with login "([^"]*)"$'){
    String login ->
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

//Given(~'^I see the student "([^"]*)", login "([^"]*)", has a "([^"]*)" evaluation in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)"$'){
//    String studentName, studentLogin, studentEvaluation, criterionName, evaluationOrigin, evaluationDate ->
//        to AddStudentsPage
//        at AddStudentsPage
//
//        page.fillStudentDetails(studentName, studentLogin)
//        page.selectAddStudent()
//
//        to StudentPage
//
//        assert page.confirmStudent(studentName, studentLogin)
//
//        to CreateCriterionPage
//        at CreateCriterionPage
//
//        page.fillCriterionDetails(criterionName)
//        page.selectCreateCriterion()
//
//        to CriterionPage
//
//        assert page.confirmCriterion(criterionName)
//
//        to AddEvaluationPage
//        at AddEvaluationPage
//
//        //date = formattedDate(criterionDate)
//        page.fillEvaluationDetails(criterionName, evaluationOrigin, evaluationDate, studentEvaluation)
//        page.selectAddEvaluationToStudent()
//
//        to EvaluationPage
//
//        assert page.confirmEvaluation(criterionName, evaluationOrigin, evaluationDate, studentEvaluation)
//}
//
//When(~'^I request the system to modify the evaluation "([^"]*)" to "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)", from student "([^"]*)", login "([^"]*)"$'){
//    String oldEvaluation, newEvaluation, criterionName, evaluationOrigin, evaluationDate, studentName, studentLogin ->
//        to ListStudentPage
//        at ListStudentPage
//
//        page.selectStudent(studentName, studentLogin)
//
//        to StudentPage
//
//        assert page.confirmStudent(studentName, studentLogin)
//        page.selectCriterion(criterionName)
//
//        to CriterionPage
//
//        assert page.confirmCriterion(criterionName)
//        page.selectEvaluation(evaluationOrigin, evaluationDate, oldEvaluation)
//
//        to EvaluationPage
//
//        assert page.confirmEvaluation(criterionName, evaluationOrigin, evaluationDate, oldEvaluation)
//
//        to EditEvaluationPage
//        at EditEvaluationPage
//
//        //date = formattedDate(criterionDate)
//        page.fillEvaluationFromStudentDetails(criterionName, evaluationOrigin, evaluationDate, newEvaluation)
//        page.selectUpdateEvaluationFromStudent()
//
//        to EvaluationPage
//
//        assert page.confirmEvaluation(criterionName, evaluationOrigin, evaluationDate, newEvaluation)
//}
//
//Then(~'^I can see the evaluation "([^"]*)" in the criterion "([^"]*)", from "([^"]*)", date "([^"]*)", from student "([^"]*)", login "([^"]*)"$'){
//    String newEvaluation, criterionName, evaluationOrigin, evaluationDate, studentName, studentLogin ->
//        to ListStudentPage
//        at ListStudentPage
//
//        page.selectStudent(studentName, studentLogin)
//
//        to StudentPage
//
//        assert page.confirmStudent(studentName, studentLogin)
//        page.selectCriterion(criterionName)
//
//        to CriterionPage
//
//        assert page.confirmCriterion(criterionName)
//        page.selectEvaluation(evaluationOrigin, evaluationDate, newEvaluation)
//
//        to EvaluationPage
//
//        assert page.confirmEvaluation(criterionName, evaluationOrigin, evaluationDate, newEvaluation)
//}
