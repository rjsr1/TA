##João Adherval
#Feature: Search and Consult a student
#  As a teacher
#  I want to search a student and consult that student's information
#  So I can see the student's performance and evaluations
#
##Controller Scenario
#  Scenario: Search a student that is registered in the system
#    Given the student "Jose Maria" with login "jm" is in the system
#    When I search for "jm" login
#    Then the system will return the information about "jm"
#
##Controller Scenario
#  Scenario: Search a student that isn't registered in the system
#    Given the student "Maria Jose" with login "mj" is not registered in the system
#    When I search for "mj"
#    Then the system will not return anything
#
##GUI Scenario
#  Scenario: Consult a student average evaluation criteria
#    Given I'm on the "Search" page
#    And I want to consult the student "Jose Maria" with login "jm"
#    When I search for the student
#    Then the students will appear
#
##GUI Scenario
#  Scenario: Consult a non registered student's average evaluation criteria
#    Given I'm on the "Alunos" page
#    And I want to consult the student "Jose Maria" with login "jm"
#    When I search for the student
#    Then no results will appear
#
##GUI Scenario
#  Scenario: Search for a student with just a part of the student's name
#    Given I'm on the "Alunos" page
#    And I want to consult the students "Jose Maria" and "Jose da Silva"
#    When I consult for "Jose"
#    Then the results will contain the names of "Jose Maria" and "Jose da Silva"
#
##GUI Scenario
#  Scenario: Look for the average evaluation and information of a student
#    Given I'm on the "Search" page
#    And I searched for "Jose Maria" with login "jm"
#    When I click on the student name
#    Then the details about the student will appear
