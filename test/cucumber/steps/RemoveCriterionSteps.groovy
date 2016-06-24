package steps

import javafx.beans.binding.When
import pages.AddEvaluationPage
import pages.AddStudentsPage
import pages.CreateCriterionPage
import pages.CriterionPages.CriterionPage
import pages.ShowCriterionPage
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
        //page.selectDeleteCriterion()
}

Then(~'^I should not see that criterion listed in the student$') { ->

}

Student studentToCheck

Given(~'^the system has a student with name "([^"]*)" and login "([^"]*)"$') { String name, String login->
    AddStudentsTestDataAndOperations.createStudent(name, login)
    studentToCheck = Student.findByLogin(login)
    assert studentToCheck.login.equals(login)
    assert studentToCheck.name.equals(name)
}

And(~'^that student has a "([^"]*)" evaluation in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)"$') {
    String evaluationValue, String criterionDescription, String origin, String evaluationDate ->

        boolean bool = false

        def date = EvaluationDataAndOperations.formattedDate(evaluationDate)

        CriterionTestDataAndOperations.createCriterion(criterionDescription)

        EvaluationDataAndOperations.createEvaluation(evaluationValue, criterionDescription, origin, evaluationDate)

        studentToCheck = Student.findByLogin(studentToCheck.login)

        studentToCheck.findEvaluationByCriterion(criterionDescription).each {
            List<Evaluation> l = it.evaluations
            for (int i = 0; i < l.size(); i++) {
                if (l[i].origin.equals(origin) && l[i].applicationDate.equals(date) && l[i].value.equals(evaluationValue)) bool = true
            }
        }

        assert bool
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