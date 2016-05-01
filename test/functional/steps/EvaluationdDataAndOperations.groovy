/**
 * Created by Danilo on 01/05/2016.
 */
package steps

import ta.Criterion
import ta.Evaluation
import ta.Student

import java.text.SimpleDateFormat
import ta.StudentController
class EvaluationDataAndOperations{

    public static Date formattedDate(String dateInString){
        def formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = formatter.parse(dateInString);
        return date;
    }

    public static boolean findEvaluation(String criterionName, String origin, Date applicationDate){
        for(Student student : Student.findAll()){
           Criterion criterion =Student.findByName(criterionName)
            criterion.each(criterion.evaluations) {
                if(criterion.evaluations.origin == origin && criterion.evaluations.applicationDate == applicationDate){
                    return true;
                }
            }
        }
        return false;
    }


      public static boolean createStudent(String login, String name){
        def cont = new StudentController()
        cont.params << [login: login] << [name: name] << [evaluations: new HashMap<String, String>()]
        boolean saved = cont.saveStudent(cont.createStudent())
        cont.response.reset()
        return saved
    }
}
