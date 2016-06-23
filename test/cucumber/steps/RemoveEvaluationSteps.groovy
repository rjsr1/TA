package steps

import javafx.beans.binding.When
import net.sf.ehcache.search.expression.And
import ta.EvaluationController
import ta.CriterionController
import ta.Student
import ta.Evaluation
import ta.Criterion

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/**
 * Created by Lapp on 21/06/2016.
 */

Student studentToCheck

Given(~'^the system has a student registered with name "([^"]*)" and login "([^"]*)"$') { String name, String login->
    AddStudentsTestDataAndOperations.createStudent(name, login)
    studentToCheck = Student.findByLogin(login)
    assert studentToCheck.login.equals(login)
    assert studentToCheck.name.equals(name)
}

And(~'this student has a "([^"]*)" evaluation in criterion "([^"]*)" with origin "([^"]*)" and applicationDate "([^"]*)"$') {
    String evaluationValue, String criterionDescription, String origin, String evaluationDate ->

        CriterionTestDataAndOperations.createCriterion(criterionDescription)
        Criterion criterionCreated = Criterion.findByDescription(criterionDescription)

        List<Evaluation> evaluationsToGive = new LinkedList<Evaluation>()
        EvaluationController evCon = new EvaluationController()
        Evaluation evaluationCreated
        Student.list().each {
            if(it.login.equals(studentToCheck.login)){
                evCon.params << [
                        origin: origin,
                        value: evaluationValue,
                        criterion: criterionCreated,
                        applicationDate: EvaluationDataAndOperations.formattedDate(evaluationDate)
                ]
            }else{
                evCon.params << [origin: origin,
                                 value: "--",
                                 criterion: criterionCreated,
                                 applicationDate: EvaluationDataAndOperations.formattedDate(evaluationDate)
                ]
            }
            evaluationCreated = evCon.createEvaluation()
            evCon.save(evaluationCreated)
            evaluationsToGive.add(evaluationCreated)
        }
        evCon.params << [value: evaluationsToGive]
        evCon.saveAll()
}

When(~'I remove the evaluation "([^"]*)"$') {

}

Then(~'the system correctly removes the evaluation$') {

}