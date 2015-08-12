<%@ page import="ta.Alternative" %>



<div class="fieldcontain ${hasErrors(bean: alternativeInstance, field: 'alternative', 'error')} required">
	<label for="alternative">
		<g:message code="alternative.alternative.label" default="Alternative" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="alternative" required="" value="${alternativeInstance?.alternative}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: alternativeInstance, field: 'questions', 'error')} ">
	<label for="questions">
		<g:message code="alternative.questions.label" default="Questions" />
		
	</label>
	

</div>

