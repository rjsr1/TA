package steps

import ta.Evaluation
import ta.EvaluationController

/**
 * Created by Lapp on 21/06/2016.
 */
class RemoveEvaluationTestDataAndOperations {

    def static deleteEvaluation(Evaluation evalInstance) {
        EvaluationController eController = new EvaluationController()
        eController.delete(evalInstance)
        eController.response.reset()
    }
}
