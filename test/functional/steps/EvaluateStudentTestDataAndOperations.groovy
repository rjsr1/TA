package steps


import ta.StudentController

class EvaluateStudentTestDataAndOperations{

    public static boolean createEvaluationCriterion(String name){
        def cont = new CriterionController()
        cont.params << [name: name]
        boolean saved = cont.saveEvaluationCriterion(cont.createEvaluationCriterion())
        cont.response.reset()
        return saved
    }

    public static boolean createStudent(String login, String name){
        def cont = new StudentController()
        cont.params << [login: login] << [name: name] << [evaluations: new HashMap<String, String>()]
        boolean saved = cont.saveStudent(cont.createStudent())
        cont.response.reset()
        return saved
    }
}
