package steps

import ta.Criterion
import ta.CriterionController

/**
 * Created by lapp on 07/05/2016.
 */

class CriterionTestDataAndOperations {

    public static void createCriterion(String description) {
        def controller = new CriterionController()
        controller.params << [description: description]
        controller.createAndSaveCriterion()
        controller.response.reset()
    }

    public static boolean compatibleTo(String desc, Criterion crit) {
        if (desc.equals(crit.description)) return true
        return false
    }

    public static boolean compatibleInCriteria(String desc) {
        def controller = new CriterionController()
        controller.params << [description: desc]
        return controller.compatibleInCriteria()
    }

    public static Criterion getCriterion(String desc) {
        def controller = new CriterionController()
        controller.params << [description: desc]
        return controller.retrieveCriterion()
    }
}