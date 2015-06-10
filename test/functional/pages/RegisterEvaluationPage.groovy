package pages

import geb.Page

class RegisterEvaluationPage extends Page {
    static url = "evaluation/register"

    static at = {
        title ==~ /Criar Avaliação/
    }

    def fillData(elementId, text) {
    	elementId = ("#" + elementId)
        $(elementId).val(text)
    }

    def fillData(elementId, elementIndex, data) {
        elementId = ("#" + elementId + elementIndex)
        $(elementId).val(data)
    }

    def click(elementId) {
    	elementId = ("#" + elementId)
    	$(elementId).click()
    }

}