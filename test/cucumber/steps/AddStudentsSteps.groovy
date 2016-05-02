/**
 * Created by rodrigocalegario on 4/28/16.
 */

//Scenario: Register a new students
//Given the student "Roberto Alves" with login "ra" is not register in the system
Given(~'^the student "([^"]*)" with login "([^"]*)" is not register in the system$'){String name, login ->
    assert Student.findByLogin(login) == null;
}

//When I register "Roberto Alves" with login "ra"
When (~'^I register "([^"]*)" with login "([^"]*)"$'){String name, login ->

}

//Then the student "Roberto Alves" with login "ra" is registered in the system
Then (~'^the student "([^"]*)" with login "([^"]*)" is registered in the system$'){String name, login ->

}

//Scenario: Message from the new student registration
//Given I am in the add student page
Given(~'^I am in the add student page$'){

}

//When I add the "Roberto Alves" with login "ra"
When(~'^I add the "([^"]*)" with login "([^"]*)"$'){String name, login ->

}

//Then a mensager with the name of "Roberto Alves" and the login "ra" warns me that this student was registered
Then(~'^a mensager with the name of "([^"]*)" and the login "([^"]*)" warns me that this student was registered$'){String name, login ->

}

//Scenario: Register a student twice
//Given the student "Roberto Alves" with login "ra" is register in the system
Given(~'^the student "([^"]*)" with login "([^"]*)" is register in the system$'){String name, login ->
    assert Student.findByLogin(login) != null;
}

//When I register "Roberto Alves" with login "ra"
When(~'^I register "([^"]*)" with login "([^"]*)"$'){String name, login ->

}

//Then the system does not register "Roberto Alves" with login "ra"
Then(~'^the system does not register "([^"]*)" with login "([^"]*)"$'){String name, login ->

}

//Scenario: Error message when registering a student twice
//Given I am in the add student page
Given(~'^I am in the add student page$'){

}

//When I add the "Roberto Alves" with login "ra"
When(~'^I add the "([^"]*)" with login "([^"]*)"$'){String name, login ->

}

//Then a error mensager with the name of "Roberto Alves" and the login "ra" warns me that this student was not registered
Then(~'^Then a error mensager with the name of "([^"]*)" and the login "([^"]*)" warns me that this student was not registered$'){String name, login ->

}