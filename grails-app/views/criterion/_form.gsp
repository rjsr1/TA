<%@ page import="ta.Criterion" %>



<div class="fieldcontain ${hasErrors(bean: criterionInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="criterion.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${criterionInstance?.description}"/>

</div>
