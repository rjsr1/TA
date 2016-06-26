package steps

import ta.Student
import ta.StudentController

/**
 * Created by joao on 19/05/16.
 */
class StudentConsultTestDataAndOperations {

    public static Student lookForStudent (String login){
        def controller = new StudentController()
        controller.params << [login: login]
        return controller.searchStudent()
    }

    public static boolean compatibleSearch(String login) {
        def controller = new StudentController()
        controller.params << [login:login]
        def studentInstance = controller.searchStudent()
        return studentInstance.login.equalsIgnoreCase(login)
    }

    public static boolean compatibleSearch2(String loginA){
        def controllerS = new StudentController()
        def aluno = controllerS.searchStudent2(loginA)
        return aluno.login.equalsIgnoreCase(loginA)
    }

    public static void createAndSaveStudent(String name, String login){
        def controller = new StudentController()
        controller.params << [name:name, login:login]
        controller.createAndSaveStudent()
        controller.response.reset()
    }
}

