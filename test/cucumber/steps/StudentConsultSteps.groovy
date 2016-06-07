package test.cucumber.steps

import pages.StudentConsultPage
import steps.StudentConsultTestDataAndOperations
import ta.Student

/**
 * Created by joao on 19/05/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//Controller Scenario
Student estudante;

When(~/^I search for "([^"]*)"$/) {
    String login ->
        estudante = StudentConsultTestDataAndOperations.searchStudent(login)
}

Then(~/^the system will not return anything$/){
    ->
    assert estudante == null
}

//GUI Scenario

Given(~/^I'm on the "([^"]*)" page$/){
    String pageName->
        to StudentConsultPage
        at StudentConsultPage
}

And(~/^I see the student "([^"]*)" with login "([^"]*)" in the list of students$/){
    String name, login ->
        at StudentConsultPage
        StudentConsultPage
}

When(~/^I request the student information$/){
    ->
    at StudentConsultPage
    StudentConsultPage.clickStudent()
}

Then(~/^all the student average evaluation in all criteria will appear in the screen$/){
    ->
    at StudentConsultPage
    StudentConsultPage.showStudentDetails()
}