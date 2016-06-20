/**
 * Created by Arthur Lapprand on 03/05/2016.
 */


import pages.CreateCriterionPage
import pages.ShowCriterionPage
import steps.CriterionTestDataAndOperations
import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks
import ta.Criterion

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

/*
Feature: Add Criterion
  As the teacher
  I want to be able to register new criteria
  So I can evaluate the students with these criteria
*/

Criterion crit
String tempDesc

/*
#Controller Scenario
Scenario: Register a criterion that does not exist
Given the criterion with name "P1" isn't on the system
When I create the criterion "P1"
Then the criterion "P1" is properly added to the system
*/
Given(~'^the criterion with name "([^"]*)" is not on the system$') {
    String desc -> crit = CriterionTestDataAndOperations.getCriterion(desc)
        assert crit == null
}

When(~'^I create the criterion "([^"]*)"$') {
    String desc -> CriterionTestDataAndOperations.createCriterion(desc)
        crit = CriterionTestDataAndOperations.getCriterion(desc)
}

Then(~'^the criterion "([^"]*)" is properly added to the system$') {
    String desc -> assert CriterionTestDataAndOperations.compatibleTo(desc, crit)
}

/*
#Controller Scenario
Scenario: Register a criterion that already exists
Given the criterion named "P1" already exists on the system
When I create the criterion "P1"
Then system does nothing
*/
Given(~'^the criterion named "([^"]*)" already exists on the system$') {
    String desc ->
        CriterionTestDataAndOperations.createCriterion(desc)
        assert CriterionTestDataAndOperations.getCriterion(desc) != null
        tempDesc = desc
}

When(~'^I create the criterion with description "([^"]*)"$') {
    String desc -> CriterionTestDataAndOperations.createCriterion(desc)
}

Then(~'^the system does nothing$') { ->
    assert CriterionTestDataAndOperations.compatibleInCriteria(tempDesc)
}

/*
#GUI Scenario
  Scenario: Error when registering a criterion that already exists
    Given I am on the Add Criterion page
    And the criterion "P1" already exists
    When I add the criterion "P1"
    Then I should see a message related to the criterion registration failure
 */
Given(~'^the criterion "([^"]*)" already exists$') {
    String desc ->
        to CreateCriterionPage
        at CreateCriterionPage
        page.createCriterion(desc)
        at ShowCriterionPage
}

And(~'^I am on the Add Criterion page$') { ->
    to CreateCriterionPage
    at CreateCriterionPage
}

When(~'^I add the criterion "([^"]*)"$') {
    String desc ->
        at CreateCriterionPage
        page.createCriterion(desc)
}

Then(~'^I should see a message related to the criterion registration failure$') { ->
    at CreateCriterionPage
    assert page.checkForErrors()
}

/*
#GUI Scenario
Scenario: Register a non-existent criterion
Given I am at the Add Criterion page
When I fill the field Nome with the name "P1"
And I finalize the criterion registration
Then I should see the new criterion available on the criteria list
*/