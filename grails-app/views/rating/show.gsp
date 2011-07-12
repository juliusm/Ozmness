
<%@ page import="com.orangeandbronze.ozmness.Rating" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rating.label', default: 'Rating')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                      <tr>
                        <th>Technology</th>
                        <th colspan="${creatorList.size}">Ratings</th>
                      </tr>
                      <tr>
                        <th>&nbsp;</th>
                        <g:each in="${creatorList}" status="j" var="creator">
                          <th>${creator.username}</td>                         
                        </g:each>
                      </tr>
                    <g:each in="${com.orangeandbronze.ozmness.Technology.list()}" status="i" var="technology">
                      <tr>
                        <td>${technology.name}</td>
                        <g:each in="${creatorList}" status="j" var="creator">
                          <g:each in="${ratingsList}" status="k" var="ratings">
                            <g:if test="${ratings.technology.id == technology.id && creator.id == ratings.creator.id}">
                            <td style="text-align: center">${ratings.value}</td>
                            </g:if>
                          </g:each>
                        </g:each>
                      </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
               &nbsp;
            </div>
        </div>
    </body>
</html>
