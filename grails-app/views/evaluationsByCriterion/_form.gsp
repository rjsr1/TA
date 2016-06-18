<%@ page import="ta.EvaluationsByCriterion" %>



<div class="fieldcontain ${hasErrors(bean: evaluationsByCriterionInstance, field: 'criterion', 'error')} required">
	<label for="criterion">
		<g:message code="evaluationsByCriterion.criterion.label" default="Criterion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="criterion" name="criterion.id" from="${ta.Criterion.list()}" optionKey="id" required="" value="${evaluationsByCriterionInstance?.criterion?.id}" class="many-to-one"/>

</div>

