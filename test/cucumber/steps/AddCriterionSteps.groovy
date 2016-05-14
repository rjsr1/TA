/**
 * Created by Arthur Lapprand on 03/05/2016.
 */
package steps

import javafx.beans.binding.When
import pages.CriterionPages.CreateCriterionPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*
Feature: Add Criterion
  As the teacher
  I want to be able to register new criteria
  So I can evaluate the students with these criteria
*/

Criterion crit

/*
#Controller Scenario
Scenario: Register a criterion that does not exist
Given the criterion with name "P1" isn't on the system
When I create the criterion "P1"
Then the criterion "P1" is properly added to the system
*/
Given(~'^the criterion with name "([^"]*)" is not on the system$') {
    String name -> crit = Criterion.findByName(name)
        assert crit == null
}

When(~'^I create the criterion "([^"]*)"$') {
    String name -> CriterionTestDataAndOperations.createCriterion(name)
        crit = Criterion.findByName(name)
}

Then(~'^the criterion "([^"]*)" is properly added to the system$') {
    String name -> assert CriterionTestDataAndOperations.compatibleTo(name, crit)
}

/*
#Controller Scenario
Scenario: Register a criterion that already exists
Given the criterion named "P1" already exists on the system
When I create the criterion "P1"
Then system does nothing
*/
Given(~'^the criterion named "([^"]*)" already exists on the system$') {
    String name -> assert Criterion.findByName(name) != null
}

When(~'^I create the criterion "([^"]*)"$') {
    String name -> CriterionTestDataAndOperations.createCriterion(name)
}

Then(~'^the system does nothing$') { ->
    assert CriterionTestDataAndOperations.checkIfCriterionsChanged()
}

/*
#GUI Scenario
  Scenario: Error when registering a criterion that already exists
    Given I am on the Add Criterion page
    And the criterion "P1" already exists
    When I add the criterion "P1"
    Then I should see a message related to the criterion registration failure
 */
Given(~'^I am on the Add Criterion page$') {
    to CreateCriterionPage
    at CreateCriterionPage
}

And(~'^the criterion "([^"]*)" already exists$') {
    String desc ->
        at CreateCriterionPage
        CreateCriterionPage.createCriterion(desc)
        to CreateCriterionPage
        at CreateCriterionPage
}

When(~'^I add the criterion "([^"]*)"$') {
    String desc ->
        at CreateCriterionPage
        CreateCriterionPage.createCriterion(desc)
}

Then(~'^I should see a message related to the criterion registration failure$') {
    assert CreateCriterionPage
}

/*
#GUI Scenario
Scenario: Register a non-existent criterion
Given I am at the Add Criterion page
When I fill the field Nome with the name "P1"
And I finalize the criterion registration
Then I should see the new criterion available on the criteria list
*/