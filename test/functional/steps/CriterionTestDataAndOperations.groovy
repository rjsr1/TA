package steps

import ta.Criterion
import ta.CriterionController

/**
 * Created by lapp on 07/05/2016.
 */

class CriterionTestDataAndOperations {

    public static boolean createCriterion(String description) {
        def controller = new CriterionController()
        controller.params << [description: description]
        boolean saved = controller.saveCriterion(controller.createCriterion(description))
        controller.response.reset()
    }

    public static boolean compatibleTo(String desc, Criterion crit) {
        if (desc.equals(crit.description)) return true
        return false
    }
}