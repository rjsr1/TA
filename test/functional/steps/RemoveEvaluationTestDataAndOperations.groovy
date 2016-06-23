package steps

import ta.EvaluationController
import ta.Student

/**
 * Created by Lapp on 21/06/2016.
 */
class RemoveEvaluationTestDataAndOperations {

    def addEvaluation(String evaluationValue) {
        EvaluationController eController = new EvaluationController()

        eController.saveAll()
    }
}
