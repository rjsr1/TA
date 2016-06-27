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

    public static boolean createStudents(int i){
        def controller = new StudentController();
        String students;
        switch(i) {
            case 2: students = "João Adherval (jacb :: joaoadherval); Milena Cabral (mscc :: Milechwan); Danilo Ribeiro (dlr4 :: DLRibeiro); Arthur Lapprand (abl3 :: ArthurLapprand); Rodrigo Calegario (rcac :: rcalegario); Thiago Bastos (tmb2 :: TMotaBastos)"
                break;
            case 1: students = "Edymir Semedo (eebls :: edymiretienne); Thiago da Fonte Bastos (tfb :: ThiagoPrime); Emanuel(efis :: silvaemanuel); Thiago(tas4 :: ThiagoAquino); José Murilo(jmsmf :: zehmurilo)"
                break;
            case 3: students = "Felipe Henrique de Almeida Bormann (fhab :: fbormann); Arthur Jorge Ebrahim Wanderley (anew :: ajew); Milton Vasconcelos da Gama Neto ( mvgn :: miltongneto); Otávio Vera Cruz Gomes ( ovcg :: ovcg); Allyson Manoel Nascimento Venceslau (amnv :: amnv); Wilquer Torres Lima (wtl :: wilquerlima)"
                break
            case 5: students = "Luiz Felipe Véras Gonçalves (lfvg :: lfvg); Walber Rodrigues de Oliveria (wro :: WalberRodri); Augusto César Aragão de Bulhões (acab2 :: acab2); Jadson Torres de Lucena (jtl :: --); Matheus Hermínio de Carvalho(mhc :: Derzet)"
        }
        controller.params <<[name: students];
        controller.saveGroup();
        controller.response.reset();
        return true
    }

    public static boolean createCriterion(String criterionName){
        CriterionTestDataAndOperations.createCriterion(criterionName);
        return true
    }

    public static void createCritAndAddToStudents(String desc){
        def controller = new CriterionController()
        controller.params << [description : desc]
        Criterion criterion = controller.createAndSaveCriterion2()
        controller.response.reset()
        //def controller2 = new StudentController()

    }


    public static boolean checkEvaluationAllStudents(List<Integer> valuesPerStudentBeforeInsertion, String parameter){
        def returningValue = true;
        def students = Student.list()
        for(int i = 0; i<students.size();i++){
            if(students.get(i).criteriaAndEvaluations == null){
                if(valuesPerStudentBeforeInsertion.get(i)== 0){
                    if(parameter.equalsIgnoreCase("same")){
                        returningValue = true
                    }else{
                        returningValue = false
                        break
                    }
                }else{
                    def size = students.get(i).criteriaAndEvaluations.size();
                    if(size == valuesPerStudentBeforeInsertion.get(i)){
                        if(parameter.equalsIgnoreCase("same")){
                            returningValue = true
                        }else{
                            returningValue = false
                            break
                        }
                    }else{
                        if(parameter.equalsIgnoreCase("same")){
                            returningValue = false
                            break
                        }else{
                            returningValue = true
                        }
                    }
                }
            }
        }
        return returningValue
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
        def evaluationList = Evaluation.list();
        for(int i = 0; i<list;i++){
            values.add(value)
        }
        cont2.params<<[value : values, origin: origin, applicationDate : applicationDate, criterion : Criterion.findByDescription(criterionName)];
        cont2.saveAll()
        return true;
    }

    public static boolean checkEvaluationAllStudents(String criterionName,String origin,String dateInString) {
        def cont = new StudentController()
        return cont.checkEvaluationsAllStudents(criterionName, origin, dateInString)
    }

    public static List<Integer> numberOfEvaluationsBeforeTest(){
        def students = Student.list()
        def returningList = new LinkedList<Integer>()
        for(int i = 0; i<students.size();i++){
            if(students.get(i).criteriaAndEvaluations == null){
                returningList.add(new Integer(0))
            }else {
                returningList.add(new Integer(students.get(i).criteriaAndEvaluations.size()))
            }
        }
        return returningList
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

