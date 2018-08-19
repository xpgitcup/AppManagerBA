<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${roles}</title>
    </head>
    <body>
        <g:each in="${roles}" var="role" status="i">
            <div title="${role}" id="appsDiv${role}">
                <div id="listAppsDiv${role}"></div>
                <div id="paginationListAppsDiv${role}" class="easyui-pagination"></div>
            </div>
        </g:each>
    </body>
</html>
