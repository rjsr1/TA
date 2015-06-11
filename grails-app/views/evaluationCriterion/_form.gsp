<%@ page import="ta.EvaluationCriterion" %>



<div class="fieldcontain ${hasErrors(bean: evaluationCriterionInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="evaluationCriterion.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${evaluationCriterionInstance?.name}"/>

</div>

