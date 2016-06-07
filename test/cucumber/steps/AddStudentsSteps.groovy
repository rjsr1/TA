package steps

import cucumber.api.PendingException
import ta.Student
import steps.AddStudentsTestDataAndOperations
import pages.AddStudentsPage
import pages.StudentPages.StudentPage

/**
 * Created by rodrigocalegario on 5/28/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~'^the student "([^"]*)" with login "([^"]*)" is not registered in the system$') { String name, String login ->
    assert Student.findByLogin(login) == null
}

When(~'^I register "([^"]*)" with login "([^"]*)"$') { String name, String login ->
    AddStudentsTestDataAndOperations.createStudent(name, login)
}

Then(~'^the student "([^"]*)" with login "([^"]*)" is saved in the system$') { String name, String login ->
    Student student = Student.findByLogin(login)
    assert AddStudentsTestDataAndOperations.compatibleTo(student, name, login)
}

Given(~'^I am in the add student page$') { ->
    to AddStudentsPage
    //at AddStudentsPage
}

When(~'^I add the "([^"]*)" with login "([^"]*)"$') { String name, String login ->
    //at AddStudentsPage
    page.fillStudentDetails(name, login)
    page.selectAddStudent()
}

Then(~'^I can see the name of "([^"]*)" and the login "([^"]*)" in the list of students$') { String name, String login ->
    to StudentPage
    assert page.confirmStudent(name, login)
}

def countStudent

Given(~'^the student "([^"]*)" with login "([^"]*)" is registered in the system$') { String name, String login ->
    countStudent = AddStudentsTestDataAndOperations.countStudent()
    AddStudentsTestDataAndOperations.createStudent(name, login)
    assert Student.findByLogin(login) != null
}

Then(~'^the system does not register "([^"]*)" with login "([^"]*)"$') { String name, String login ->
    assert countStudent == AddStudentsTestDataAndOperations.countStudent()
}
Then(~'^I can\'t see the name of "([^"]*)" and the login "([^"]*)" in the list of students$') { String name, String login ->
    to StudentPage
    //falta a funcionalidade aqui ainda
}