package steps

import ta.Student
import ta.StudentController

/**
 * Created by rodrigocalegario on 4/28/16.
 */
class AddStudentsTestDataAndOperations {
    static public void createStudent(String name, String login){
        def cont = new StudentController()
        //cont.params
        cont.params << [name: name, login: login]
        cont.save()
        cont.response.reset()
    }

    static public void compatibleTo(Student student, String login){

    }
}
