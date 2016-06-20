import pages.StudentConsultPage
import ta.Student
import steps.StudentConsultTestDataAndOperations

/**
 * Created by joao on 02/06/16.
*/

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Student globalStudent
//Controller
Given(~/^the student "([^"]*)" with login "([^"]*)" is registered in the system$/) { String nome, String login ->
    StudentConsultTestDataAndOperations.createAndSaveStudent(nome, login)
    assert Student.findByLogin(login) != null
}

Then(~/^the system will return the information about "([^"]*)"$/) { String login->
    assert StudentConsultTestDataAndOperations.compatibleSearch(login)
}

//Controller
Given(~/^the student "([^"]*)" with login "([^"]*)" is not registered in the system$/) { String nome, String login ->
    assert Student.findByLogin(login) == null
}

When(~/^I search for "([^"]*)"$/) { String login ->
    globalStudent = StudentConsultTestDataAndOperations.lookForStudent()Student(login)
}

Then(~/^the system will not return anything$/) { ->
    assert globalStudent == null
}

//GUI
String global

Given(~/^I'm on the "([^"]*)" page$/) { String pageName ->
    to StudentConsultPage
    at StudentConsultPage
}

And(~/^I see the student "([^"]*)" with login "([^"]*)" in the list of students$/) { String nome, String login ->
    at StudentConsultPage
    page.fillStudentSearch(global)
    page.selectSearch()
}

When(~/^I request the student information$/) { ->
    at StudentConsultPage
    page.selectStudent()
}

Then(~/^all the student average evaluation in all criteria will appear in the screen$/) { ->
    at StudentConsultPage
    page.showStudentDetails()
}
