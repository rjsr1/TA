#Arthur Lapprand

Feature: Remove Evaluation
  As the teacher
  I want to remove an Evaluation from a Student
  So I can correct the Student overall evaluation

#GUI Scenario
Scenario: Remove evaluations in different criterion from a student
  Given the system already has a student with name "Student 1" and login "s1"
  And this student has "MA" evaluation in criterion "SI"
  And has a "MANA" evaluation in criterion "PLC"
  When I remove the evaluations "MA" and "MANA"
  Then I should not see them listed in the student

##Controller Scenario
#Scenario: Remove an Evaluation from a Student
#  Given the system has a student registered with name "Fulano Detal" and login "fd"
#  And this student has a "MA" evaluation in criterion "ESS" with origin "Test" and applicationDate "21/12/1992"
#  When I remove the evaluation "MA" in criterion "ESS" with origin "Test" and applicationDate "21/12/1992" from the student "Fulano Detal" with login "fd"
#  Then the system correctly removes the evaluation