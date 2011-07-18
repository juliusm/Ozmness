
<%@ page import="com.orangeandbronze.ozmness.Rating" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
       
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list">Rating List</g:link></span>
            <sec:ifAllGranted roles="ROLE_DEV">
            <span class="menuButton"><g:link class="create" action="create">New Rating</g:link></span>
            </sec:ifAllGranted>
        </div>
        <div class="body">
            <h1>Showing ${rated}'s Ratings</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                      <tr>
                        <th>Technology</th>
                        <th colspan="${creatorList.size()}">Ratings</th>
                      </tr>
                      <tr>
                        <th>&nbsp;</th>
                        <g:each in="${creatorList}" status="j" var="creator">
                          <th>${creator.name}</td>                         
                        </g:each>
                      </tr>
                    <g:each in="${com.orangeandbronze.ozmness.Technology.list()}" status="i" var="technology">
                      
                      <tr>
                        <td>${technology.name}</td>
                      
                        <g:each in="${creatorList}" status="j" var="creator">
                          <g:set var="hasCreatorRatings" value="false" />
                          <g:each in="${ratingsList}" status="k" var="ratings">
                            <g:if test="${ratings.technology.id == technology.id && creator.id == ratings?.creator.id}">
                            <td style="text-align: center" TITLE="${ratings.comment}">${ratings.value}</td>
                            <g:set var="hasCreatorRatings" value="true" />
                            </g:if>
                            
                          </g:each>
                          
                          <g:if test="${hasCreatorRatings.equals('false')}">
                            <td>&nbsp;</td>
                          </g:if>
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
