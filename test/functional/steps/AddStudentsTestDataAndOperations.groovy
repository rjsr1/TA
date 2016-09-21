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
        cont.response.reset()
    }

    static public boolean compatibleTo(Student student, String name, String login){
        if (name.equals("Milena Cabral")){
            boolean b = true
        }
        boolean compatible = false
        if(student.getName().equals(name) && student.getLogin().equals(login)) compatible = true;
        String no = student.getName()
        String lo = student.getLogin()
        boolean n = student.getName().equals(name)
        boolean l = student.getLogin().equals(login)
        n = no.equals(name)
        l = lo.equals(login)
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

    static public int alunoQtd(String login){
        return Student.findAllByLogin(login).size()
    }


}
