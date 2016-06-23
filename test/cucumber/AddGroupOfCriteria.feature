#Thiago Bastos
Feature: Add Criterion
  As the teacher
  I want to be able to register new criteria
  So I can evaluate the students with these criteria

#Controller Scenario
  Scenario: Register a group of criterion that does not exist
    Given the criterion with name "C1" is not on the system
    And the criterion with name "C2" is not on the system
    When I create the group of criteria "C1;C2"
    Then the criterion "C1" is properly added to the system
    And the criterion "C2" is properly added to the system

##Controller Scenario
#  Scenario: Register a criterion that already exists
#    Given the criterion named "P2" already exists on the system
#    When I create the criterion with description "P2"
#    Then the system does nothing
#
##GUI Scenario
#  Scenario: Error when registering a criterion that already exists
#    Given the criterion "P3" already exists
#    And I am on the Add Criterion page
#    When I add the criterion "P3"
#    Then I should see a message related to the criterion registration failure

#GUI Scenario
  Scenario: Register a non-existent group of criteria
    Given I am at the Add Group of Criteria page
    When I fill the field Nome with the name "C3;C4"
    And I finalize the criteria registration
    Then I should see the "C3" criterion available on the criteria list
    And I should see the "C4" criterion available on the criteria list