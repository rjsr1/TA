@ignore
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
    Given I am in the "add student" page
    When I add the "Rodrigo Calegario" with login "rc"
    Then I can see the name of "Rodrigo Calegario" and the login "rc" in the list of students

#Cenário Controler
  Scenario: Register a student twice
    Given the student "Robertinho Alves" with login "raz" is registered in the system
    When I register "Robertinho Alves" with login "raz"
    Then the system does not register "Robertinho Alves" with login "raz"

#Cenário GUI
  Scenario: Error message when registering a student twice
    Given I am in the "sudent list" page
    And the name of "Roberto Alves" and the login "ra" is already in the list of student
    And I go to the "add student" page
    When I add the "Roberto Alves" with login "ra"
    Then I can see the name of "Roberto Alves" and the login "ra" in the list of students only once

#Cenario Controller
  Scenario: Register a group of student
    Given the student "João Adherval" with login "ja" is not registered in the system
    And the student "Milena Cabral" with login "mscc" is not registered in the system
    When I send a text with "João Adherval (jacb :: joaoadherval); Milena Cabral (mscc :: Milechwan)"
    Then the student "João Adherval" with login "jacb" is saved in the system
    And the student "Milena Cabral" with login "mscc" is saved in the system

#Cenario GUI
  Scenario: See the new group of students in the list
    Given I am in the "create group" page
    When I add the text "Danilo Ribeiro (dlr4 :: DLRibeiro); Arthur Lapprand (abl3 :: ArthurLapprand)"
    Then I can see the name of "Danilo Ribeiro" and the login "dlr4" in the list of students
    And I can see the name of "Arthur Lapprand" and the login "abl3" in the list of students

#Cenario Controller
  Scenario: Register a student twice in a group of student
    Given the student "Felipe Bormann" with login "fhab" is not registered in the system
    And the student "Arthur Jorge" with login "anew" is registered in the system
    When I send a text with "Felipe Bormann (fhab :: fbormann); Arthur Jorge (anew :: ajew)"
    Then the student "Felipe Bormann" with login "fhab" is saved in the system
    And the system does not register "Arthur Jorge" with login "anew"

#Cenario GUI
  Scenario: Register a student twice in a group of student in list
    Given I am in the "sudent list" page
    And the name of "Otávio Vera Cruz" and the login "ovcg" is already in the list of student
    And I go to the "create group" page
    When I add the text "Milton Vasconcelos (mvgn :: miltongneto); Otávio Vera Cruz (ovcg :: ovcg)"
    Then I can see the name of "Milton Vasconcelos" and the login "mvgn" in the list of students
    And I can see the name of "Otávio Vera Cruz" and the login "ovcg" in the list of students only once
