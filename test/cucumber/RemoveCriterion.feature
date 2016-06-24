#Arthur Lapprand

Feature: Remove Criterion
  As the teacher
  I want to remove a Criterion from the system
  So that Criterion is not affecting student average anymore

#GUI Scenario
  Scenario: Remove a criterion with students and evaluations using it
    Given a student with name "Zeca" and login "zc" is already on the system
    And this student has an evaluation in criterion "TEORICA"
    When I remove the criterion "TEORICA"
    Then I should not see that criterion listed in the student

#Controller Scenario
  Scenario: Remove a criterion from the system
    Given the system has a student with name "Jose" and login "jj"
    And that student has a "MA" evaluation in criterion "GDI" with origin "Test" and applicationDate "21/12/1992"
    When I remove the criterion "GDI" from the student "Jose" with login "jj"
    Then the system correctly removes the criterion