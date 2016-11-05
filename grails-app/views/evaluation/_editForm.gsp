<%@ page import="ta.Evaluation" %>



<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'origin', 'error')} required">
    <label for="origin">
        <g:message code="evaluation.origin.label" default="Origin" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="origin" from="${evaluationInstance.constraints.origin.inList}" required="" value="${evaluationInstance?.origin}" valueMessagePrefix="evaluation.origin"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'criterion', 'error')} required">
    <label for="criterion">
        <g:message code="evaluation.criterion.label" default="Criterion" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="criterion" name="criterion.id" from="${ta.Criterion.list()}" optionKey="id" required="" optionValue="${{it.description}}" value="${evaluationInstance?.criterion?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'applicationDate', 'error')} required">
    <label for="applicationDate">
        <g:message code="evaluation.applicationDate.label" default="Application Date" />
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="applicationDate" precision="day"  value="${evaluationInstance?.applicationDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'value', 'error')} required">
    <label for="value">
        <g:message code="evaluation.value.label" default="Value" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="value" from="${evaluationInstance.constraints.value.inList}" required="" value="${evaluationInstance?.value}" valueMessagePrefix="evaluation.value"/>

</div>