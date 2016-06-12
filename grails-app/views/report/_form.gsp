<%@ page import="ta.Report" %>



<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="report.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${reportInstance?.name}"/>

</div>

