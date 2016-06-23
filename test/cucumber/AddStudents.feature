Feature: Add Student
  As a professor
  I want to register students in the system
  So I can evaluate the students with respect to various criteria

#Cenário Controler
  Scenario: Register a new student
    Given the student "Roberto Alves" with login "ra" is not registered in the system
    When I register "Roberto Alves" with login "ra"
    Then the student "Roberto Alves" with login "ra" is saved in the system

#Cenário GUI
  Scenario: Message from the new student registration
    Given I am in the add student page
    When I add the "Rodrigo Calegario" with login "rc"
    Then I can see the name of "Rodrigo Calegario" and the login "rc" in the list of students

#Cenário Controler
  Scenario: Register a student twice
    Given the student "Roberto Alves" with login "ra" is registered in the system
    When I register "Roberto Alves" with login "ra"
    Then the system does not register "Roberto Alves" with login "ra"

#Cenário GUI
  Scenario: Error message when registering a student twice
    Given  I am in the add student page
    When I add the "Roberto Alves" with login "ra"
    Then I can't see the name of "Roberto Alves" and the login "ra" in the list of students

#Cenario Controller
  Scenario: Register a group of student
    Given the student "João Adherval" with login "ja" is not registered in the system
    And the student "Milena Cabral" with login "mscc" is not registered in the system
    When I send a text with "João Adherval (jacb :: joaoadherval); Milena Cabral (mscc :: Milechwan)"
    Then the student "João Adherval" with login "jacb" is saved in the system
    And the student "Milena Cabral" with login "mscc" is saved in the system

#Cenario GUI
  Scenario: See the new group of students in the list
    Given I am in the create group page
    When I add the text "João Adherval (jacb :: joaoadherval); Milena Cabral (mscc :: Milechwan)"
    Then I can see the name of "João Adherval" and the login "jacb" in the list of students
    And I can see the name of "Milena Cabral" and the login "mscc" in the list of students