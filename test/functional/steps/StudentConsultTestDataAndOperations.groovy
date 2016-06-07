package steps

import ta.Student
import ta.StudentController

/**
 * Created by joao on 19/05/16.
 */
class StudentConsultTestDataAndOperations {
    public static Student searchStudent(String login) {
        def controller = new StudentController()
        //controller.params << [login:login]
        controller.searchStudent(login)
    }

    public static boolean compatibleSearch(String login) {
        def controller = new StudentController()
        //controller.params << [login:login]
        return controller.searchStudent(login).login == login
    }

    public static void createAndSaveStudent(String name, String login){
        def controller = new StudentController()
        controller.params << [name:name, login:login]
        controller.createAndSaveStudent()
        controller.response.reset()
    }
}

