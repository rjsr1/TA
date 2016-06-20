<%@ page import="ta.EvaluationsByCriterion" %>



<div class="fieldcontain ${hasErrors(bean: evaluationsByCriterionInstance, field: 'criterion', 'error')} required">
	<label for="criterion">
		<g:message code="evaluationsByCriterion.criterion.label" default="Criterion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="criterion" name="criterion.id" from="${ta.Criterion.list()}" optionKey="id" required="" value="${evaluationsByCriterionInstance?.criterion?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationsByCriterionInstance, field: 'evaluations', 'error')} required">
	<label for="evaluations">
		<g:message code="evaluationsByCriterion.evaluations.label" default="Evaluations" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="evaluations" from="${ta.Evaluation.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${evaluationsByCriterionInstance?.evaluations*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationsByCriterionInstance, field: 'criterionAverage', 'error')} required">
	<label for="criterionAverage">
		<g:message code="evaluationsByCriterion.criterionAverage.label" default="Criterion Average" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="criterionAverage" value="${fieldValue(bean: evaluationsByCriterionInstance, field: 'criterionAverage')}" required=""/>

</div>

