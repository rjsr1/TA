#Arthur Lapprand

Feature: Add Criterion
  As the teacher
  I want to be able to register new criteria
  So I can evaluate the students with these criteria

#Controller Scenario
  Scenario: Register a criterion that does not exist
    Given the criterion with name "P1" is not on the system
    When I create the criterion "P1"
    Then the criterion "P1" is properly added to the system

#Controller Scenario
  Scenario: Register a criterion that already exists
    Given the criterion named "P1" already exists on the system
    When I create the criterion with description "P1"
    Then the system does nothing

#GUI Scenario
  Scenario: Error when registering a criterion that already exists
    Given the criterion "P1" already exists
    And I am on the Add Criterion page
    When I add the criterion "P1"
    Then I should see a message related to the criterion registration failure

#GUI Scenario

#  Scenario: Register a non-existent criterion
#    Given I am at the Add Criterion page
#    When I fill the field Nome with the name "P1"
#    And I finalize the criterion registration
#    Then I should see the new criterion available on the criteria list
