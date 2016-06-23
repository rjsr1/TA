/**
 * Created by TMB on 23/06/2016.
 */


import pages.AddGroupCriteriaPage
import pages.CriterionPages.CriterionPage
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

Criterion crit1, crit2
String tempDesc, descriptionCrit1, descriptionCrit2

/*
#Controller Scenario
Scenario: Register a criterion that does not exist
Given the criterion with name "P1" isn't on the system
When I create the criterion "P1"
Then the criterion "P1" is properly added to the system
*/
Given(~'^the criterion with name "([^"]*)" is not on the system$') {
    String description ->
        crit = CriterionTestDataAndOperations.retrieveCriterion(description)
        assert crit == null
        descriptionCrit1 = description
}

And(~'^the criterion with name "([^"]*)" is also not on the system$') {
    String description ->
        crit = CriterionTestDataAndOperations.retrieveCriterion(description)
        assert crit == null
        descriptionCrit2 = description
}

When(~'^I create the group of criteria "([^"]*)"$') {
    String description ->
        CriterionTestDataAndOperations.createGroupCriteria(description)
        crit1 = CriterionTestDataAndOperations.retrieveCriterion(descriptionCrit1)
        crit2 = CriterionTestDataAndOperations.retrieveCriterion(descriptionCrit2)
}

Then(~'^the criterion "([^"]*)" is properly added to the system$') {
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
Given(~'^the criterion "([^"]*)" does not exist$') {
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
    page.selectAddGroupCriteria()
}

Then(~'^I should see the "([^"]*)" criterion available on the criteria list$') {
    String description ->
        to CriterionPage
        at CriterionPage

        assert page.confirmCriterion(description)
}
///*
//#Controller Scenario
//Scenario: Register a criterion that already exists
//Given the criterion named "P1" already exists on the system
//When I create the criterion "P1"
//Then system does nothing
//*/
//Given(~'^the criterion named "([^"]*)" already exists on the system$') {
//    String desc ->
//        CriterionTestDataAndOperations.createCriterion(desc)
//        assert CriterionTestDataAndOperations.getCriterion(desc) != null
//        tempDesc = desc
//}
//
//When(~'^I create the criterion with description "([^"]*)"$') {
//    String desc -> CriterionTestDataAndOperations.createCriterion(desc)
//}
//
//Then(~'^the system does nothing$') { ->
//    assert CriterionTestDataAndOperations.compatibleInCriteria(tempDesc)
//}
//
///*
//#GUI Scenario
//  Scenario: Error when registering a criterion that already exists
//    Given I am on the Add Criterion page
//    And the criterion "P1" already exists
//    When I add the criterion "P1"
//    Then I should see a message related to the criterion registration failure
// */
//Given(~'^the criterion "([^"]*)" already exists$') {
//    String desc ->
//        to CreateCriterionPage
//        at CreateCriterionPage
//        page.createCriterion(desc)
//        at ShowCriterionPage
//}
//
//And(~'^I am on the Add Criterion page$') { ->
//    to CreateCriterionPage
//    at CreateCriterionPage
//}
//
//When(~'^I add the criterion "([^"]*)"$') {
//    String desc ->
//        at CreateCriterionPage
//        page.createCriterion(desc)
//}
//
//Then(~'^I should see a message related to the criterion registration failure$') { ->
//    at CreateCriterionPage
//    assert page.checkForErrors()
//}
//
///*
//#GUI Scenario
//Scenario: Register a non-existent criterion
//Given I am at the Add Criterion page
//When I fill the field Nome with the name "P1"
//And I finalize the criterion registration
//Then I should see the new criterion available on the criteria list
//*/