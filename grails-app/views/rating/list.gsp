
<%@ page import="com.orangeandbronze.ozmness.Rating" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Employee list</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1>View Employee Ratings</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'rating.id.label', default: 'Id')}" />
                        
                            <th>Name</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${com.orangeandbronze.ozmness.Employee.list()}" status="i" var="employee">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${employee.id}">${fieldValue(bean: employee, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: employee, field: "name")}</td>
                        
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${com.orangeandbronze.ozmness.Employee.list().size()}" />
            </div>
        </div>
    </body>
</html>
