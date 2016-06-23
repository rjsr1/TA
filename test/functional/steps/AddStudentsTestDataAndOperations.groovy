package steps

import ta.Student
import ta.StudentController

/**
 * Created by rodrigocalegario on 5/28/16.
 */
class AddStudentsTestDataAndOperations {

    static public void createStudent(String name, String login){
        def cont = new StudentController()
        cont.params << [name: name, login: login]
        cont.createAndSaveStudent()
        //Student a = new Student(name, login)
        //cont.save(a)
        cont.response.reset()
    }

    static public boolean compatibleTo(Student student, String name, String login){
        boolean compatible = false
        if(student.name == name && student.login == login) compatible = true;
        return compatible
    }

    static public int countStudent(){
        return Student.list().size()
    }

    static public void createGroup(String group){
        def controller = new StudentController()
        controller.params << [name: group]
        controller.saveGroup()
    }

}
