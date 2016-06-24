#Arthur Lapprand

Feature: Remove Evaluation
  As the teacher
  I want to remove an Evaluation from a Student
  So I can correct the Student overall evaluation

#Controller Scenario
Scenario: Remove an Evaluation from a Student
  Given the system has a student registered with name "Fulano Detal" and login "fd"
  And this student has a "MA" evaluation in criterion "ESS" with origin "Test" and applicationDate "21/12/1992"
  When I remove the evaluation "MA" in criterion "ESS" with origin "Test" and applicationDate "21/12/1992" from the student "Fulano Detal" with login "fd"
  Then the system correctly removes the evaluation