#João Adherval

Feature: Search and Consult a student
  As a teacher
  I want to search a student and consult that student's information
  So I can see the student's performance and grades

#Controller Scenario
  Scenario: Search a student that is registered in the system
    Given the student "Jose Maria" with login "jm" is registered in the system
    When I search for "Jose Maria"
    Then the system will return the information about "Jose Maria"

#Controller Scenario
  Scenario: Search a student that isn't registered in the system
    Given the student "Maria Jose" with login "mj" isn't registered in the system
    When I search for "Maria Jose"
    Then the system will not return anything

#GUI Scenario
  Scenario: Consult a student's average grade concepts
    Given I'm on the "Alunos" page
    And the student "Jose Maria" with login "jm" is registered
    When I request the student's information
    Then all the student's average grades in all concepts will appear in the screen

#GUI Scenario
  Scenario: Consult a non registered student's average grade concepts
    Given I'm on the "Alunos" page
    And the student "Jose Maria" with login "jm" is not registered
    When I request the student's information
    Then a error message will appear in the screen

#GUI Scenario
  Scenario: Search the criteria's average with just a part of the student's name
    Given I'm on the "Alunos" page
    And the students "Jose Maria"  e "Jose da Silva" are registered in the system
    When I search for "Jose"
    Then a list with the names of "Jose Maria" e Jose sa Silva" will appear on the screen