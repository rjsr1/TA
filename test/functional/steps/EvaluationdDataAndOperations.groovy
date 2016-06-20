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
    }
    public static boolean existEvaluation(String criterionName, String dateInString){
        def applicationDate = formattedDate(dateInString)
        def found = false;
        for(Student student : Student.findAll()){
            def counter = 0
            student.each(student.criterions){
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
    ​
    public static boolean existEvaluation(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        for(Evaluation evaluation : Evaluation.findAll){
            if(evaluation.origin == origin && evaluation.applicationDate == applicationDate)
                return true;
        }
        return false;
    }*/

    public static void createStudents(){
        def controller = new StudentController();
        controller.params<<[name:"abc",login:"abc"]
        controller.createAndSaveStudent();
        controller.response.reset();
        controller.params<<[name:"dfg",login:"dfg"]
        controller.createAndSaveStudent();
        controller.response.reset();
        controller.params<<[name:"hji",login:"hji"]
        controller.createAndSaveStudent();
        controller.response.reset();
    }

    public static void createCriterionX(){
        def controller = new CriterionController()
        controller.params << [description : "X"];
        controller.createCriterion()
        controller.response.reset();

    }

    public static boolean findEvaluationAndCount(String criterionName, String origin, String dateInString){
        def controller = new EvaluationController()
        def controller2 = new StudentController()
        def listEvaluations = controller2.countStudentsEvaluated(criterionName,origin,dateInString)
        def countStudents = controller2.countAllStudents();
        if(countStudents==listEvaluations.size()) return true;
        else return false

    }
   
    public static boolean createEvaluation(String value, String criterionName, String origin, String dateInString){
        if(value == null) return false;
        def applicationDate = formattedDate(dateInString)
        def cont = new StudentController()
        def cont2 = new EvaluationController();
        def values = [value, value, value];
        cont2.params<<[value : values] <<[origin: origin] << [applicationDate : applicationDate];
        cont2.saveAll()
        return true;
    }
    public static void createEvaluationNoValue(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        def cont = new StudentController()
        def cont2 = new EvaluationController();
        def values = [null, null, null]
        cont2.params<<[value : values] <<[origin: origin] << [applicationDate : applicationDate];
        Evaluation evaluation = cont2.createEvaluation()
        cont2.saveAll();
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
