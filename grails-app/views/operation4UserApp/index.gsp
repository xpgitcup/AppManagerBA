<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="mainCup"/>
    <title>应用程序管理</title>
    <asset:javascript src="cn/edu/cup/appmanager/home.js"></asset:javascript>
</head>

<body>
<div id="rolesDiv">${roles}</div>
<div id="ipDiv" class="easyui-panel"></div>

<div id="appRolesDiv" class="easyui-tabs">
    <g:each in="${roles}" var="role" status="i">
        <div title="${role}" id="appsDiv${role}">
            <div id="listAppsDiv${role}"></div>

            <div id="paginationListAppsDiv${role}" class="easyui-pagination"></div>
        </div>
    </g:each>
</div>

</body>
</html>
