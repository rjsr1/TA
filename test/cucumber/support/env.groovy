package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor

import static cucumber.api.groovy.Hooks.*
import ta.Report

Before () {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
}

After () {
<<<<<<< HEAD
    Report.list().each { relatorio ->
        relatorio.delete(flush: true)
=======
    Vaga.list().each { vaga ->
        vaga.delete(flush:true)
>>>>>>> 399b655284b0cf25700ea5b4a77c1f3fb45ec05b
    }
    scenarioInterceptor.destroy ()
    bindingUpdater.remove ()
}