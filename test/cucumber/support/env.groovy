package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import ta.Criterion
import ta.CriterionController
import ta.Evaluation
import ta.EvaluationsByCriterion
import ta.Student

import static cucumber.api.groovy.Hooks.*
import ta.Report

Before () {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
}

After () {
    Report.list().each { relatorio ->
        relatorio.delete(flush: true)
    }
    CriterionController criterionController = new CriterionController()
    Criterion.list().each { crit ->
        criterionController.delete(crit)
    }
    EvaluationsByCriterion.list().each { evbc ->
        evbc.delete(fush: true)
    }
    Student.list().each { s ->
        s.delete(flush: true)
    }
    scenarioInterceptor.destroy ()
    bindingUpdater.remove ()
}