/**
 * Created by Danilo on 01/05/2016.
 */
package steps

import ta.Criterion
import ta.CriterionController
import ta.Evaluation
import ta.EvaluationController
import ta.Student

import java.text.SimpleDateFormat
import ta.StudentController

class EvaluationDataAndOperations{

    public static Date formattedDate(String dateInString){
        def formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = formatter.parse(dateInString);
        return date;
    }

    /*public static Evaluation findEvaluation(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        for(Student student : Student.findAll()){
            def counter = 0
            student.each(student.criterions){
                if(criterionName == student.criterions.get(counter).name){
                    def studentCriterions = student.getCriterions().get(counter);
                    def counter2 = 0;
                    studentCriterions.each(studentCriterions.evaluations){
                        if(studentCriterions.getEvaluations().get(counter2).origin == origin && studentCriterions.getEvaluations().get(counter2).applicationDate == date){
                            return studentCriterions.getEvaluations().get(counter2);
                        }
                    }
                    counter2++
                }
                counter++
            }
        }
        return null;
    }*/

    public static void createStudents(){
        def controller = new StudentController();
        Student stu1 = new Student("abc","abc")
        Student stu2 = new Student("def","def")
        Student stu3 = new Student("ghi","ghi");
        controller.save(stu1);
        controller.response.reset();
        controller.save(stu2);
        controller.response.reset();
        controller.save(stu3);
        controller.response.reset();
    }

    public static void createCriterionXandAddToStudents(){
        def controller = new CriterionController()
        Criterion criterion = new Criterion("X");
        controller.save(criterion)
        controller.response.reset();
        def controller2 = new StudentController()
        controller2.addCriterionToAllStudent("X");
    }

    public static void createCritAndAddToStudents(String desc){
        def controller = new CriterionController()
        Criterion criterion = new Criterion(desc);
        controller.save(criterion)
        controller.response.reset();
        def controller2 = new StudentController()
        controller2.addCriterionToAllStudent(desc);
    }


    public static boolean findEvaluationAndCount(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        def controller = new EvaluationController()
        def controller2 = new StudentController()
        def listEvaluations = controller2.countStudentsEvaluated(criterionName,origin,dateInString)
        def countStudents = controller2.countAllStudents();
        if(countStudents==listEvaluations.size()) return true;
        else return false

    }

    public static boolean existEvaluation(String criterionName, String dateInString){
        def applicationDate = formattedDate(dateInString)
        def found = false;
        for(Student student : Student.findAll()){
            def counter = 0
            student.each(student.criterionsAndEvaluations){
                if(criterionName == student.criterions.get(counter).name){
                    def studentCriterions = student.getCriterions().get(counter);
                    def counter2 = 0;
                    studentCriterions.each(studentCriterions.evaluations){
                        if(studentCriterions.getEvaluations().get(counter2).applicationDate == date){
                            return true
                        }
                    }
                    counter2++
                }
                counter++
            }
        }
        return false
    }

    public static boolean existEvaluation(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        for(Evaluation evaluation : Evaluation.findAll()){
            if(evaluation.origin == origin && evaluation.applicationDate == applicationDate)
                return true;
        }
        return false;
    }

    public static boolean createEvaluation(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        def cont = new StudentController()
        def cont2 = new EvaluationController();
        cont2.params<<[value : "--"] <<[origin: origin] << [applicationDate : applicationDate];
        Evaluation evaluation = cont2.createEvaluation()
        def returningValue= cont.addEvaluations(criterionName,Evaluation)
        cont.response.reset()
        cont2.response.reset()
        return returningValue
    }
    public static boolean createEvaluationNoValue(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        def cont = new StudentController()
        def cont2 = new EvaluationController();
        cont2.params<<[value : null] <<[origin: origin] << [applicationDate : applicationDate];
        Evaluation evaluation = cont2.createEvaluation(criterionName,origin,dateInString)
        cont.params<<[origin:origin,applicationDate : evaluation.applicationDate, Criterion:evaluation.criterion, value : null]
        def returningValue= cont.addEvaluations()
        cont.response.reset()
        cont2.response.reset()
        return returningValue
    }


    public static boolean checkEvaluationAllStudents(String criterionName,String origin,String dateInString){
        def cont = new StudentController()
        return cont.checkEvaluationsAllStudents(criterionName,origin,dateInString)
    }

    public static boolean checkEvaluationRedundantAllStudents(String criterionName,String origin,String dateInString){
        def cont = new StudentController()
        return cont.checkRedundantEvaluationAllStudents(criterionName,origin,dateInString)
    }


    public static boolean createStudent(String login, String name){
        def cont = new StudentController()
        cont.params << [login: login] << [name: name]
        boolean saved = cont.saveStudent(cont.createStudent())
        cont.response.reset()
        return saved
    }

}