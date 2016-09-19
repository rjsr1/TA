<%--
  Created by IntelliJ IDEA.
  User: TMB
  Date: 23/06/2016
  Time: 13:09
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'criterion.label', default: 'Criterion')}" />
    <title><g:message code="default.createGroup.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-criterion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="create-group" class="content scaffold-create" role="main">
    <h1><g:message code="default.createGroup.label" args="[entityName]" /></h1>
    <h2 id="create-group-h2">To import a group of criteria, use the following format: <span style="color: hotpink">Criterion Description;</span></h2>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form url="[action:'saveGroup']" >
        <div id="create-group-input">

            <label for="description">
                <g:message code="criterion.description.label" default="Formatted input" />
                <span class="required-indicator">*</span>
            </label>
            <g:textField name="description" required="" value="" size="60"/>

        </div>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>

</div>
</body>
</html>