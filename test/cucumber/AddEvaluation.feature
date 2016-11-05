Feature: AddEvaluation
  As a teacher
  I want to evaluate each student by criteria
  to show them their progress in class

  Scenario: Add evaluation to a criteria
    Given there are no evaluations to all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016"
    When I want to evaluate all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016".
    Then all the evaluations will be stored in on the "X" criterion history of each student

  Scenario: Add evaluations using incomplete data
    Given there are no evaluations to all students to the "Y" criterion,
    When I want to evaluate all students to a the "Y" criteria, without a specific origin and dated from "28/03/2016".
    Then all evaluations will not be stored in on the "Y" criterion history of each student

  Scenario: Add evaluation more than once with same origin
    Given evaluations for every student on the "X" criteria, originated from "Test" and dated from "28/03/2016" are already in the system
    When I want to add a mark to all students to a the "X" criteria, originated from "Test" and dated from "28/03/2016"
    Then all the marks will not be stored in on the "X" criteria's history of each student
##GUI
#  Scenario: Add evaluation to all students
#    Given I see the student "Rodrigo", login "rcac" and the criterion "W"
#    When I request the system to add the evaluation valued "MANA" in the criterion "W", from "Test", date "28/03/2016"
#    Then I can see the evaluation valued "MANA" in the criterion "W", from "Test", date "28/03/2016" in the evaluation screen
#
