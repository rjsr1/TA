#Esta feature é a feature inicial, idealizada antes da iteração de imlementar features.
#Portanto, será descartada dos testes finais visto que não faz uso de métodos de controlador que não sejam
#Gerados automaticamente.

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
    Given the criterion named "P2" already exists on the system
    When I create the criterion with description "P2"
    Then the system does nothing

#GUI Scenario
  Scenario: Error when registering a criterion that already exists
    Given the criterion "P3" already exists
    And I am on the Add Criterion page
    When I add the criterion "P3"
    Then I should see a message related to the criterion registration failure
