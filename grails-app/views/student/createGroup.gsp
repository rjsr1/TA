<%--
  Created by IntelliJ IDEA.
  User: rodrigocalegario
  Date: 6/14/16
  Time: 4:05 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.createGroup.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="create-student" class="content scaffold-create" role="main">
    <h1><g:message code="default.createGroup.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--g:include controller="student" action="groupSave"/-->
    <g:form url="[action:'saveGroup']" >
        <div class="fieldcontain required">

            <label for="name">
                <g:message code="student.name.label" default="Name" />
            </label>
            <g:textField name="name" required="" value=""/>

        </div>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>

</div>
</body>
</html>
