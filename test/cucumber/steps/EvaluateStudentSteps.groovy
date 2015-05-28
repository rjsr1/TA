package steps

import cucumber.api.PendingException
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

Then(~'^the evaluation criterion with name "([^"]*)" was not stored in the system$') { String criterionName ->
    assert EvaluationCriterion.findByName(criterionName) != null && !saved
}

//////////////////////////////////
Given(~'^the system does not have an evaluation criterion with name "([^"]*)"2$') { String criterionName ->
    throw new PendingException()
}

And(~'^the student "([^"]*)" with login "([^"]*)" is registered in the system$') { String studentName, String studentLogin ->
    throw new PendingException()
}

When(~'^I create an evaluation criterion with name "([^"]*)"3$') { String criterionName ->
    throw new PendingException()
}

Then(~'^the system evaluates "([^"]*)" also using the criterion "([^"]*)"$') { String studentName, String criterionName ->
    throw new PendingException()
}

////////////////////////////////
