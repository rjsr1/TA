
Feature AddMark
  As a teacher, I want to give marks referring to each criteria to each student, to show them their progress in class
  Scenario: Add marks to a criteria
    When I want to add a mark to all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016".
    Then all the marks will be stored in on the "X" criterion's history of each student

  Scenario: Add marks using incomplete data
    When I want to add a mark to all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
    Then all the marks will not be stored in on the "X" criteria's history of each student
  Scenario: Add mark more than once with same origin
    And marks for every student on the "X" criteria, originated form "Test" and dated from "28/03/2016" are already in the system
    When I want to add a mark to all students to a the "X" criteria, without a specific origin and dated from "28/03/2016"
    Then all the marks will not be stored in on the "X" criteria's history of each student

  Scenario: Error related to add a repetead mark
    And I am at the "Add concept" screen
    And there already are marks for the "X" criteria, originated from "Test" and dated from "24/03/2016" in the system
    When I want to add a mark to all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
    Then an error message related to trying to add a repetead mark will be displayed

  Scenario: Import marks
    And I organized all marks for the "X" criteron originated from "Midterm", dated from "31/03/2016" in a spreedsheet
    When I want to import all marks from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
    Then all the marks will be stored in on the "X" criteria's history of each student


  Scenario: Import repeated marks
    And I organized all marks for the "X" criteria originated from "Midterm", dated from "31/03/2016" in a spreedsheet
    And there already are marks for the "X" criteria, originated from "Midterm" and dated from "31/03/2016" in the system
    When I want to import all marks from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
    Then all the marks will not be stored in on the "X" criteria's history of each student

