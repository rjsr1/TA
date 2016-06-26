/**
 * Created by TMB on 23/06/2016.
 */


import pages.AddGroupCriteriaPage
import pages.CriterionPages.CriterionPage
import pages.CreateCriterionPage
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

Criterion crit1, crit2
String tempDesc, descriptionCrit1, descriptionCrit2
int qtCriteria = 0

/*
#Controller Scenario
Scenario: Register a criterion that does not exist
Given the criterion with name "P1" isn't on the system
When I create the criterion "P1"
Then the criterion "P1" is properly added to the system
*/
Given(~'^the criterion "([^"]*)" is not on the system$') {
    String description ->
        Criterion crit = CriterionTestDataAndOperations.retrieveCriterion(description)
        assert crit == null
        descriptionCrit1 = description
}

And(~'^the criterion with name "([^"]*)" is also not on the system$') {
    String description ->
        Criterion crit = CriterionTestDataAndOperations.retrieveCriterion(description)
        assert crit == null
        descriptionCrit2 = description
}

When(~'^I create the group of criteria "([^"]*)"$') {
    String description ->
        CriterionTestDataAndOperations.createGroupCriteria(description)
        crit1 = CriterionTestDataAndOperations.retrieveCriterion(descriptionCrit1)
        crit2 = CriterionTestDataAndOperations.retrieveCriterion(descriptionCrit2)
}

Then(~'^the criterion "([^"]*)" is added to the system$') {
    String description ->
        assert CriterionTestDataAndOperations.compatibleTo(description, crit1)
}

And(~'^the criterion "([^"]*)" is also properly added to the system$') {
    String description ->
        assert CriterionTestDataAndOperations.compatibleTo(description, crit2)
}
/*
#GUI Scenario
Scenario: Register a non-existent group of criteria
Given I am at the Add Group of Criteria page
And the criterion "C3" does not exist
And the criterion "C4" does not exist
When I fill the field Nome with the name "C3;C4"
And I finalize the criteria registration
Then I should see the "C3" criterion available on the criteria list
And I should see the "C4" criterion available on the criteria list
*/
Given(~'^I can not see the criterion "([^"]*)"$') {
    String description ->
        to CriterionPage
        at CriterionPage

        assert !page.confirmCriterion(description)
}

And(~'^I am at the Add Group of Criteria page$') { ->
    to AddGroupCriteriaPage
    at AddGroupCriteriaPage
}

When(~'^I fill the field Nome with the name "([^"]*)"$') {
    String description ->
        page.fillGroupCriteriaDetails(description)
}

And(~'^I finalize the criteria registration$') { ->
    qtCriteria = CriterionTestDataAndOperations.countCriteria()
    page.selectAddGroupCriteria()
    qtCriteria++
}

Then(~'^I should see the "([^"]*)" criterion available on the criteria list$') {
    String description ->
        to CriterionPage
        at CriterionPage

        assert page.confirmCriterion(description)
}

/*
#Controller Scenario
Scenario: Register a group of criterion that some of them already exists
Given the criterion with name "C1" is not on the system
And the criterion with name "C2" is on the system
When I create the group of criteria "C1;C2"
Then the criterion "C1" is properly added to the system
And the criterion "C2" is not added to the system
*/
Criterion c

Given(~'^the criterion with name "([^"]*)" isnt on the system$') {
    String desc -> c = CriterionTestDataAndOperations.getCriterion(desc)
        tempDesc = desc
        assert c == null
}

And(~'^the criterion with name "([^"]*)" is on the system$') {
    String description ->
        CriterionTestDataAndOperations.createCriterion(description)
        Criterion crit = CriterionTestDataAndOperations.retrieveCriterion(description)
        assert crit.description.equals(description)
        descriptionCrit2 = description
}

When(~'^I register the group of criteria "([^"]*)"$') {
    String description ->
        CriterionTestDataAndOperations.createGroupCriteria(description)
        c = CriterionTestDataAndOperations.retrieveCriterion(tempDesc)
}

Then(~'^the criterion "([^"]*)" is in the system$') {
    String desc -> assert CriterionTestDataAndOperations.compatibleTo(desc, c)
}

And(~'^the criterion "([^"]*)" is not added to the system$') {
    String description ->
        assert CriterionTestDataAndOperations.checkNumbersDescription(description) == 1
}

/*
#GUI Scenario
Scenario: Register a group of criteria that some elements of it already exists
Given the criterion "C6" does not exist
And the criterion "C5" does exist
And I am at the Add Group of Criteria page
When I fill the field Nome with the name "C6;C5"
And I finalize the criteria registration
Then I should see the "C6" criterion available on the criteria list
And I should see the "C5" criterion only one time available on the criteria list
*/
And(~'^I can see the criterion "([^"]*)"$') {
    String description ->
        to CreateCriterionPage
        at CreateCriterionPage

        page.fillCriterionDetails(description)
        page.selectCreateCriterion()

        to CriterionPage
        at CriterionPage

        assert page.confirmCriterion(description)
}

Then(~'^I should see the "([^"]*)" criterion only one time available on the criteria list$') {
    String description ->
        to CriterionPage
        at CriterionPage

        assert page.confirmEqualCriteria(qtCriteria)
}
