package steps

import pages.AddEvaluationPage
import pages.AddStudentsPage
import pages.CreateCriterionPage
import pages.ShowCriterionPage
import pages.ShowEvaluationByCriterionPage
import pages.ShowEvaluationPage
import pages.StudentPages.ShowStudentPage
import pages.StudentPages.StudentPage
import ta.EvaluationsByCriterion
import ta.Student
import ta.Evaluation

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/**
 * Created by Lapp on 21/06/2016.
 */

// GUI Scenario
String tempLogin, tempCrit

Given(~'^the system already has a student with name "([^"]*)" and login "([^"]*)"$') {
    String name, String login ->
        tempLogin = login
        to AddStudentsPage
        at AddStudentsPage
        page.fillStudentDetails(name, login)
        page.selectAddStudent()
}

And(~'^this student has "([^"]*)" evaluation in criterion "([^"]*)"$') {
    String evaluationValue, String criterionDesc ->
        tempCrit = criterionDesc
        to CreateCriterionPage
        at CreateCriterionPage
        page.createCriterion(criterionDesc)
        at ShowCriterionPage

        to AddEvaluationPage
        at AddEvaluationPage
        addEvaluationToCriterion(criterionDesc, evaluationValue)
}

And(~'^has a "([^"]*)" evaluation in criterion "([^"]*)"$') {
    String evaluationValue, String criterionDesc ->
        to CreateCriterionPage
        at CreateCriterionPage
        page.createCriterion(criterionDesc)
        at ShowCriterionPage

        to AddEvaluationPage
        at AddEvaluationPage
        addEvaluationToCriterion(criterionDesc, evaluationValue)
}

private void addEvaluationToCriterion(String criterionDesc, String evaluationValue) {
    page.chooseCriterion(criterionDesc)
    page.chooseValue(evaluationValue)
    page.selectAddEvaluation()
}

String tempEval

When(~'^I remove the evaluation "([^"]*)"$') {
    String eval1 ->
        tempEval = eval1
        to StudentPage
        at StudentPage
        page.selectStudentByLogin(tempLogin)
        at ShowStudentPage
        page.selectCriterionByDesc(tempCrit)
        at ShowEvaluationByCriterionPage
        page.selectEvaluationByValue(eval1)
        at ShowEvaluationPage
        page.selectDeleteEvaluation()
}

Then(~'^I should not see it listed in the student$') { ->
    to StudentPage
    at StudentPage
    page.selectStudentByLogin(tempLogin)
    at ShowStudentPage
    page.selectCriterionByDesc(tempCrit)
    at ShowEvaluationByCriterionPage
    assert page.checkForEvaluation(tempEval)
}

// Controller Scenario
Student studentToCheck

Given(~'^the system has a student registered with name "([^"]*)" and login "([^"]*)"$') { String name, String login->
    AddStudentsTestDataAndOperations.createStudent(name, login)
    studentToCheck = Student.findByLogin(login)
    assert studentToCheck.login.equals(login)
    assert studentToCheck.name.equals(name)
}

And(~'^this student has a "([^"]*)" evaluation in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)"$') {
    String evaluationValue, String criterionDescription, String origin, String evaluationDate ->
        assert CommonTestDataAndOperations.giveEvaluationToCriterion(evaluationValue, criterionDescription, origin, evaluationDate, studentToCheck.login)
}

String tempEvaluationValue, tempCriterionDescription, tempOrigin, tempName
def date

When(~'^I remove the evaluation "([^"]*)" in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)" from the student "([^"]*)" with login "([^"]*)"$') {
    String evaluationValue, String criterionDescription, String origin, String evaluationDate, String name, String login ->
        tempEvaluationValue = evaluationValue
        tempCriterionDescription = criterionDescription
        tempOrigin = origin
        tempName = name
        tempLogin = login

        date = EvaluationDataAndOperations.formattedDate(evaluationDate)

        Student s = Student.findByLogin(tempLogin)
        List<EvaluationsByCriterion> ebcList = s.criteriaAndEvaluations
        for (int i = 0; i < ebcList.size(); i++) {
            if (ebcList.get(i).criterion.description.equals(tempCriterionDescription)) {
                List<Evaluation> eList = ebcList.get(i).evaluations
                for (int j = 0; j < eList.size(); j++) {
                    if (eList.get(j).origin.equals(tempOrigin) &&
                            eList.get(j).value.equals(tempEvaluationValue) &&
                            eList.get(j).applicationDate.equals(date)) {
                        RemoveEvaluationTestDataAndOperations.deleteEvaluation(eList.get(j))
                    }
                }
            }
        }
}

Then(~'^the system correctly removes the evaluation$') { ->
    boolean bool = true

    Student s = Student.findByLogin(tempLogin)
    List<EvaluationsByCriterion> ebcList = s.criteriaAndEvaluations
    for (int i = 0; i < ebcList.size(); i++) {
        if (ebcList.get(i).criterion.description.equals(tempCriterionDescription)) {
            List<Evaluation> eList = ebcList.get(i).evaluations
            for (int j = 0; j < eList.size(); j++) {
                if (eList.get(j).origin.equals(tempOrigin) &&
                        eList.get(j).value.equals(tempEvaluationValue) &&
                        eList.get(j).applicationDate.equals(date)) {
                    bool = false
                }
            }
        }
    }

    assert bool
}