/**
 * Created by Danilo on 01/05/2016.
 */
package steps

import ta.Criterion
import ta.CriterionController

//import ta.Criterion
//import ta.CriterionController
import ta.Evaluation
import ta.EvaluationController
import ta.EvaluationsByCriterion
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
        //def controller2 = new StudentController()
        def EvaluationsByCriterion ec = new EvaluationsByCriterion(criterion)
        for(Student student : Student.list()){
            student.criterionsAndEvaluations.add(ec)
        }
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
            student.each(student.criteriaAndEvaluations){
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

    public static boolean createEvaluation(String value, String criterionName, String origin, String dateInString){
        if(value == null || origin == "") return false;
        def applicationDate = formattedDate(dateInString)
        def cont2 = new EvaluationController();
        def list = Student.list().size();
        def values = []
        for(int i = 0; i<list;i++){
            values.add(value)
        }
        cont2.params<<[value : values, origin: origin, applicationDate : applicationDate, criterion : Criterion.findByDescription(criterionName)];
        cont2.saveAll()
        return true;
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

    //MEUS METODOS

    public static void createAndGiveEvaluation(String studentName, String studentLogin, String studentEvaluation, String criterionName, String evaluationOrigin, String evaluationDate){
        def student = new StudentController()
        //student.params << [login: studentLogin] << [name: studentName]
        Student studentCreated = student.createAndSaveStudent2(studentName, studentLogin)

        def criterion = new CriterionController()
        criterion.params << [description : criterionName]
        Criterion criterionCreated = criterion.createAndSaveCriterion()

        Date applicationDate = formattedDate(evaluationDate)

        def evaluation = new EvaluationController()
        evaluation.params << [/*description : criterionName,*/ origin : evaluationOrigin, value : studentEvaluation, applicationDate : applicationDate, criterion : criterionCreated]
        Evaluation evaluationCreated = evaluation.createAndSaveEvaluationWithoutParam(/*evaluationOrigin, studentEvaluation, evaluationDate*/)
        //student.addEvaluationTests(studentLogin, criterionName, evaluationOrigin)
        //student.addEvaluationToStudent2(studentLogin, applicationDate)
        student.evaluationTests(studentLogin, evaluationOrigin)
        student.response.reset()
        evaluation.response.reset()
        criterion.response.reset()
    }

    public static void updateEvaluationInStudent(String studentLogin, String newEvaluation, String criterionName, String evaluationOrigin){
        def student = new StudentController()
        student.updateEvaluation(studentLogin, newEvaluation, criterionName, evaluationOrigin)
        student.response.reset()
    }

    public static Student getStudent(String studentLogin){
        def student = new StudentController()
        return student.getStudent(studentLogin)
    }

    public static boolean compatibleTo(Student stu1, Student stu2){
        boolean compatible = false
        if(stu1.name.equals(stu2.name) && stu1.login.equals(stu2.login) && stu1.criteriaAndEvaluations == stu2.criteriaAndEvaluations) compatible = true
        return compatible
    }
}

