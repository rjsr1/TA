#Pedro Henrique

Feature: Evaluate the students
  As a teacher
  I want to evaluate my students with respect to various criteria
  So I want to tell him what I think about him on these criteria

#Stakeholder: Paulo Borba
#[16:26:54] Paulo Borba: eu quero avaliar um aluno com rela��o a v�rios criterios
#[16:27:06] Paulo Borba: n�o quero dar uma simples nota a ele
#[16:27:26] Paulo Borba: quero dizer a ele o que eu achei dele com rela��o a v�rios crit�rios

#Controller Scenario
  Scenario: Registering an evaluation criterion that does not exist
    Given the system does not have an evaluation criterion with name "Requirements"
    When I create an evaluation criterion with name "Requirements"
    Then the evaluation criterion with name "Requirements" is properly stored in the system

#Controller Scenario
  Scenario: Registering an evaluation criterion that already exists
    Given the system already has an evaluation criterion named "requirements"
    When I create an evaluation criterion with name "Requirements"2
    Then the evaluation criterion with name "Requirements" was not stored in the system

#Controller Scenario
  Scenario: Updating the list of criteria for registered students
    Given the system does not have an evaluation criterion with name "Project management"2
    And the student "Peter Parker" with login "pp2" is registered in the system
    When I create an evaluation criterion with name "Project management"3
    Then the system evaluates "Peter Parker" also using the criterion "Project management"

#GUI Scenario
  Scenario: Evaluate a registered student
    Given I am on the Students Page
    And the student "Peter Parker" with login "pp2" is registered in the system2
    And there is a criterion called "Requirements" registered in the system
    When I go to the Students Page
    Then I am should see a table with "pp2" in a row and "Requirements" in a column

#GUI Scenario
  Scenario: Add a new evaluation criterion
    Given I am on the Evaluation Criterion Page
    And I follow new evaluation criterion
    When I fill "Requirements" in the Name field
    And I click Save
    Then I am should see the Students page with a new column named "Requirements"


#Other scenarios gui to do: remove the evaluation criterion