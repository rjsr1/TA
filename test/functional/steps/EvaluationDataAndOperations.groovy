/**
 * Created by Danilo on 01/05/2016.
 */
package steps

import ta.Criterion
import ta.CriterionController
import ta.Evaluation
import ta.EvaluationController
import ta.EvaluationsByCriterion
import ta.Student

import java.text.SimpleDateFormat
import ta.StudentController
<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
=======

class EvaluationDataAndOperations{
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy

class EvaluationDataAndOperations {

    public static Date formattedDate(String dateInString) {
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

    public static void createStudents() {
        def controller = new StudentController();
        Student stu1 = new Student("abc", "abc")
        Student stu2 = new Student("def", "def")
        Student stu3 = new Student("ghi", "ghi");
        controller.save(stu1);
        controller.response.reset();
        controller.save(stu2);
        controller.response.reset();
        controller.save(stu3);
        controller.response.reset();
    }

    public static void createCriterionXandAddToStudents() {
        def controller = new CriterionController()
        Criterion criterion = new Criterion("X");
        controller.save(criterion)
        controller.response.reset();
        def controller2 = new StudentController()
        controller2.addCriterionToAllStudent("X");
    }

<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
    public static void createCritAndAddToStudents(String desc) {
=======
    public static void createCritAndAddToStudents(String desc){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
        def controller = new CriterionController()
        Criterion criterion = new Criterion(desc);
        controller.save(criterion)
        controller.response.reset();
        //def controller2 = new StudentController()
        def EvaluationsByCriterion ec = new EvaluationsByCriterion(criterion)
<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
        for (Student student : Student.list()) {
=======
        for(Student student : Student.list()){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
            student.criterionsAndEvaluations.add(ec)
        }
    }


<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
    public static boolean findEvaluationAndCount(String criterionName, String origin, String dateInString) {
=======
    public static boolean findEvaluationAndCount(String criterionName, String origin, String dateInString){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
        def applicationDate = formattedDate(dateInString)
        def controller = new EvaluationController()
        def controller2 = new StudentController()
        def listEvaluations = controller2.countStudentsEvaluated(criterionName, origin, dateInString)
        def countStudents = controller2.countAllStudents();
        if (countStudents == listEvaluations.size()) return true;
        else return false

    }

<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
    public static boolean existEvaluation(String criterionName, String dateInString) {
=======
    public static boolean existEvaluation(String criterionName, String dateInString){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
        def applicationDate = formattedDate(dateInString)
        def found = false;
        for (Student student : Student.findAll()) {
            def counter = 0
<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
            student.each(student.criteriaAndEvaluations) {
                if (criterionName == student.criterions.get(counter).name) {
=======
            student.each(student.criteriaAndEvaluations){
                if(criterionName == student.criterions.get(counter).name){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
                    def studentCriterions = student.getCriterions().get(counter);
                    def counter2 = 0;
                    studentCriterions.each(studentCriterions.evaluations) {
                        if (studentCriterions.getEvaluations().get(counter2).applicationDate == date) {
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

    public static boolean existEvaluation(String criterionName, String origin, String dateInString) {
        def applicationDate = formattedDate(dateInString)
<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
        for (Evaluation evaluation : Evaluation.findAll()) {
            if (evaluation.origin == origin && evaluation.applicationDate == applicationDate)
=======
        for(Evaluation evaluation : Evaluation.findAll()){
            if(evaluation.origin == origin && evaluation.applicationDate == applicationDate)
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
                return true;
        }
        return false;
    }

<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
    public static boolean createEvaluation(String value, String criterionName, String origin, String dateInString) {
        if (value == null) return false;
=======
    public static boolean createEvaluation(String value, String criterionName, String origin, String dateInString){
        if(value == null) return false;
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
        def applicationDate = formattedDate(dateInString)
        def cont = new StudentController()
        def cont2 = new EvaluationController();
        def values = [value, value, value];
<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
        cont2.params << [value: values] << [origin: origin] << [applicationDate: applicationDate];
        cont2.saveAll()
        return true;
    }

    public static boolean checkEvaluationAllStudents(String criterionName, String origin, String dateInString) {
=======
        cont2.params<<[value : values] <<[origin: origin] << [applicationDate : applicationDate];
        cont2.saveAll()
        return true;
    }
    public static boolean checkEvaluationAllStudents(String criterionName,String origin,String dateInString){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
        def cont = new StudentController()
        return cont.checkEvaluationsAllStudents(criterionName, origin, dateInString)
    }

    public
    static boolean checkEvaluationRedundantAllStudents(String criterionName, String origin, String dateInString) {
        def cont = new StudentController()
        return cont.checkRedundantEvaluationAllStudents(criterionName, origin, dateInString)
    }

<<<<<<< HEAD:test/functional/steps/EvaluationdDataAndOperations.groovy
    public static boolean createStudent(String login, String name) {
=======
    public static boolean createStudent(String login, String name){
>>>>>>> 49046cce259c367cf3df2ee6e9e160019f0268ed:test/functional/steps/EvaluationDataAndOperations.groovy
        def cont = new StudentController()
        cont.params << [login: login] << [name: name]
        boolean saved = cont.saveStudent(cont.createStudent())
        cont.response.reset()
        return saved
    }

}
