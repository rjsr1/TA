#@ignore
Feature: Notification
    As an administrator
    I want to be notified about the students issues without searching for their historic
    So I can know who has its performance under the class evaluations average quickly

##GUI
#  Scenario: Be notified about the students's performance issues
#    Given I am at the home page
#    When I go to the Reports page
#    Then the item "Reports" on the menu will show if there are new notifications
#
#
##GUI
#  Scenario: Notify new report type
#    Given that the report with name "70% of evaluations are MPA", evaluation "MPA", type "Porcentagem", value "0.7" is in the system
#    And I am in the Evaluation page
#    When I add the evaluation "MPA" to the student "Joan", login "jsd"
#    Then 70% of his evaluations are composed of "MPA"
#    And I should see a new notification related to the new report type
#
#Controller
  Scenario: Update a report
    Given that report with name "70% of evaluations are MANA", evaluation "MANA", type "Porcentagem", value "70" is in the system
   When I add the evaluation "MANA" in the criterion "blah blah" with origin "Mini-Test" and date "26/04/2016" to the student with name "Saulo Henrique Dias" and the login "shd2"
    Then 70% of the student "shd2" evaluations are composed of "MANA"
    And the report "70% of evaluations are MANA" is updated adding the student with login "shd2"

#GUI
Scenario: Show the students list in the "70% of evaluations are over the class average" report
  Given I am in the Report page
  And I try to create the report named "70% of evaluations are over the class average", type "Media", value "0", evaluation "MA"
  When I select the "70% of evaluations are over the class average" report
  Then I should see the details related to the "70% of evaluations are over the class average" report