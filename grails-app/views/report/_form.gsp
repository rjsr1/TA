<%@ page import="ta.Report" %>



<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="report.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${reportInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="report.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipo" from="${reportInstance.constraints.tipo.inList}" required="" value="${reportInstance?.tipo}" valueMessagePrefix="report.tipo"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'avaliacao', 'error')} required">
	<label for="avaliacao">
		<g:message code="report.avaliacao.label" default="Avaliacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="avaliacao" from="${reportInstance.constraints.avaliacao.inList}" required="" value="${reportInstance?.avaliacao}" valueMessagePrefix="report.avaliacao"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="report.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type ="number" name="valor" value="${fieldValue(bean: reportInstance, field: 'valor')}" required=""/>

</div>

