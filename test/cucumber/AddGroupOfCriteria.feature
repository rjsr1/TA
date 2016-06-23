#Thiago Bastos
Feature: Add Criterion
  As the teacher
  I want to be able to register new criteria
  So I can evaluate the students with these criteria

#Controller Scenario
  Scenario: Register a group of criteria that does not exist
    Given the criterion with name "C1" is not on the system
    And the criterion with name "C2" is also not on the system
    When I create the group of criteria "C1;C2"
    Then the criterion "C1" is properly added to the system
    And the criterion "C2" is also properly added to the system

#GUI Scenario
  Scenario: Register a non-existent group of criteria
    Given the criterion "C3" does not exists
    And the criterion "C4" does not exists
    And I am at the Add Group of Criteria page
    When I fill the field Nome with the name "C3;C4"
    And I finalize the criteria registration
    Then I should see the "C3" criterion available on the criteria list
    And I should see the "C4" criterion available on the criteria list

#Controller Scenario
  Scenario: Register a group of criteria that some of them already exists
    Given the criterion with name "C5" is not on the system
    And the criterion with name "C2" is on the system
    When I create the group of criteria "C5;C2"
    Then the criterion "C5" is properly added to the system
    And the criterion "C2" is not added to the system

#GUI Scenario
  Scenario: Register a group of criteria that some elements of it already exists
    Given the criterion "C6" does not exists
    And the criterion "C4" does exists
    And I am at the Add Group of Criteria page
    When I fill the field Nome with the name "C6;C4"
    And I finalize the criteria registration
    Then I should see the "C6" criterion available on the criteria list
    And I should see the "C4" criterion only one time available on the criteria list