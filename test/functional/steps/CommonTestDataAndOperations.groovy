package steps

import ta.Evaluation
import ta.Student

/**
 * Created by Lapp on 25/06/2016.
 */
class CommonTestDataAndOperations {
    def static boolean giveEvaluationToCriterion(String evaluationValue, String criterionDescription, String origin, String evaluationDate, String login) {
        boolean bool = false

        def date = EvaluationDataAndOperations.formattedDate(evaluationDate)

        CriterionTestDataAndOperations.createCriterion(criterionDescription)

        EvaluationDataAndOperations.createEvaluation(evaluationValue, criterionDescription, origin, evaluationDate)

        Student studentToCheck = Student.findByLogin(login)

        studentToCheck.findEvaluationByCriterion(criterionDescription).each {
            List<Evaluation> l = it.evaluations
            for (int i = 0; i < l.size(); i++) {
                if (l[i].origin.equals(origin) && l[i].applicationDate.equals(date) && l[i].value.equals(evaluationValue)) bool = true
            }
        }

        return bool
    }
}
