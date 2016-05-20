Feature: Register Student
  As a professor
  I want to register students in the system
  So I can evaluate the students with respect to various criteria

#Cenário Controler
  Scenario: Register a new student
    Given the student "Roberto Alves" with login "ra" is not registered in the system
    When I register "Roberto Alves" with login "ra"
    Then the student "Roberto Alves" with login "ra" is registered in the system

#Cenário GUI
  Scenario: Message from the new student registration
    Given I am in the add student page
<<<<<<< HEAD
    When I add the "Roberto Alves" with login "ra"
    Then a mensager with the name of "Roberto Alves" and the login "ra" warns me that this student was registered
=======
    And the student "Roberto Alves" with login "ra" is not registered in the system
    When I add the "Roberto Alves" with login "ra"
    And I finalize the registration of the student
    Then a message with the name of "Roberto Alves" and the login "ra" warns me that this student was registered
>>>>>>> 6cc1fd420b7df9ccbc70e9819c0398fdc877763b

#Cenário Controler
  Scenario: Register a student twice
    Given the student "Roberto Alves" with login "ra" is registered in the system
    When I register "Roberto Alves" with login "ra"
    Then the system does not register "Roberto Alves" with login "ra"

#Cenário GUI
  Scenario: Error message when registering a student twice
    Given  I am in the add student page
<<<<<<< HEAD
    When I add the "Roberto Alves" with login "ra"
    Then a error mensager with the name of "Roberto Alves" and the login "ra" warns me that this student was not registered
=======
    And the student "Roberto Alves" with login "ra" is registered in the system
    When I add the "Roberto Alves" with login "ra"
    And I finalize the registration of the student
    Then an error message with the name of "Roberto Alves" and the login "ra" warns me that this student was not registered
>>>>>>> 6cc1fd420b7df9ccbc70e9819c0398fdc877763b
