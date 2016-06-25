package steps

import net.sf.ehcache.search.expression.And
import pages.AddEvaluationPage
import pages.AddStudentsPage
import pages.CreateCriterionPage
import pages.ShowCriterionPage
import ta.EvaluationsByCriterion
import ta.Student
import ta.Evaluation
import steps.RemoveEvaluationTestDataAndOperations

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/**
 * Created by Lapp on 21/06/2016.
 */

// GUI Scenario

Given(~'^the system already has a student with name "([^"]*)" and login "([^"]*)"$') {
    String name, String login ->
        to AddStudentsPage
        at AddStudentsPage
        page.fillStudentDetails(name, login)
        page.selectAddStudent()
}

And(~'^this student has "([^"]*)" evaluation in criterion "([^"]*)"$') {
    String evaluationValue, String criterionDesc ->
        to CreateCriterionPage
        at CreateCriterionPage
        page.createCriterion(criterionDesc)
        at ShowCriterionPage

        to AddEvaluationPage
        at AddEvaluationPage
        page.chooseCriterion(criterionDesc)
        page.chooseValue(evaluationValue)
        page.selectAddEvaluation()
}

And(~'^has a "([^"]*)" evaluation in criterion "([^"]*)"$') {
    String evaluationValue, String criterionDesc ->
        to CreateCriterionPage
        at CreateCriterionPage
        page.createCriterion(criterionDesc)
        at ShowCriterionPage

        to AddEvaluationPage
        at AddEvaluationPage
        page.chooseCriterion(criterionDesc)
        page.chooseValue(evaluationValue)
        page.selectAddEvaluation()
}

When(~'^I remove the evaluations "([^"]*)" and "([^"]*)"$') {

}

Then(~'^I should not see them listed in the student$') {}

// Controller Scenario
//Student studentToCheck
//
//Given(~'^the system has a student registered with name "([^"]*)" and login "([^"]*)"$') { String name, String login->
//    AddStudentsTestDataAndOperations.createStudent(name, login)
//    studentToCheck = Student.findByLogin(login)
//    assert studentToCheck.login.equals(login)
//    assert studentToCheck.name.equals(name)
//}
//
//And(~'^this student has a "([^"]*)" evaluation in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)"$') {
//    String evaluationValue, String criterionDescription, String origin, String evaluationDate ->
//
//        boolean bool = false
//
//        def date = EvaluationDataAndOperations.formattedDate(evaluationDate)
//
//        CriterionTestDataAndOperations.createCriterion(criterionDescription)
//
//        EvaluationDataAndOperations.createEvaluation(evaluationValue, criterionDescription, origin, evaluationDate)
//
//        studentToCheck = Student.findByLogin(studentToCheck.login)
//
//        studentToCheck.findEvaluationByCriterion(criterionDescription).each {
//            List<Evaluation> l = it.evaluations
//            for (int i = 0; i < l.size(); i++) {
//                if (l[i].origin.equals(origin) && l[i].applicationDate.equals(date) && l[i].value.equals(evaluationValue)) bool = true
//            }
//        }
//
//        assert bool
//}
//
//String tempEvaluationValue, tempCriterionDescription, tempOrigin, tempName, tempLogin
//def date
//
//When(~'^I remove the evaluation "([^"]*)" in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)" from the student "([^"]*)" with login "([^"]*)"$') {
//    String evaluationValue, String criterionDescription, String origin, String evaluationDate, String name, String login ->
//        tempEvaluationValue = evaluationValue
//        tempCriterionDescription = criterionDescription
//        tempOrigin = origin
//        tempName = name
//        tempLogin = login
//
//        date = EvaluationDataAndOperations.formattedDate(evaluationDate)
//
//        Student s = Student.findByLogin(tempLogin)
//        List<EvaluationsByCriterion> ebcList = s.criteriaAndEvaluations
//        for (int i = 0; i < ebcList.size(); i++) {
//            if (ebcList.get(i).criterion.description.equals(tempCriterionDescription)) {
//                List<Evaluation> eList = ebcList.get(i).evaluations
//                for (int j = 0; j < eList.size(); j++) {
//                    if (eList.get(j).origin.equals(tempOrigin) &&
//                            eList.get(j).value.equals(tempEvaluationValue) &&
//                            eList.get(j).applicationDate.equals(date)) {
//                        RemoveEvaluationTestDataAndOperations.deleteEvaluation(eList.get(j))
//                    }
//                }
//            }
//        }
//}
//
//Then(~'^the system correctly removes the evaluation$') { ->
//    boolean bool = true
//
//    Student s = Student.findByLogin(tempLogin)
//    List<EvaluationsByCriterion> ebcList = s.criteriaAndEvaluations
//    for (int i = 0; i < ebcList.size(); i++) {
//        if (ebcList.get(i).criterion.description.equals(tempCriterionDescription)) {
//            List<Evaluation> eList = ebcList.get(i).evaluations
//            for (int j = 0; j < eList.size(); j++) {
//                if (eList.get(j).origin.equals(tempOrigin) &&
//                        eList.get(j).value.equals(tempEvaluationValue) &&
//                        eList.get(j).applicationDate.equals(date)) {
//                    bool = false
//                }
//            }
//        }
//    }
//
//    assert bool
//}