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

    public static Evaluation findEvaluation(String criterionName, String origin, String dateInString){
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

    public static boolean existEvaluation(String criterionName, String origin, String dateInString){
        def applicationDate = formattedDate(dateInString)
        for(Evaluation evaluation : Evaluation.findAll){
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
    public static boolean checkEvaluation(String criterionName,String origin,String dateInString){
        def cont = new StudentController()
        return cont.checkEvaluations(criterionName,origin,dateInString)
    }


      public static boolean createStudent(String login, String name){
        def cont = new StudentController()
        cont.params << [login: login] << [name: name] << [evaluations: new HashMap<String, String>()]
        boolean saved = cont.saveStudent(cont.createStudent())
        cont.response.reset()
        return saved
    }
}
