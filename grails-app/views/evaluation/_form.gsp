<%@ page import="ta.Evaluation" %>



<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="evaluation.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${evaluationInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: evaluationInstance, field: 'questions', 'error')} ">
	<label for="questions">
		<g:message code="evaluation.questions.label" default="Questions" />
		
	</label>
	<g:select name="questions" from="${ta.Question.list()}" multiple="multiple" optionKey="id" size="5" value="${evaluationInstance?.questions*.id}" class="many-to-many"/>

</div>

