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
<div id="rolesDiv" class="hidden">${roles}</div>

<!--顶部按钮-->
<div class="nav">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li>
            <g:link class="create" action="scanWebApp" controller="operation4UserApp">
                刷新
            </g:link>
        </li>
        <li>
            <a class="edit" href="appRoles/index">程序类型编辑</a>
        </li>
        <li>
            <a class="edit" href="tomcatInstance/index">Tomcat维护</a>
        </li>
        <li>
            <div id="ipDiv"></div>
        </li>
    </ul>
</div>

<div id="appRolesDiv" class="easyui-tabs">
    <g:each in="${tabList}" var="tab" status="i">
        <div title="${tab.title}" id="appsDiv${tab.title}">
            <div id="listAppsDiv${tab.title}"></div>

            <div id="paginationListAppsDiv${tab.title}" class="easyui-pagination"
                 data-options="
                 total: ${tab.total},
                 pageSize: 10,
                 onSelectPage:
                     function (pageNumber, pageSize) {
                         console.info('页码：' + pageNumber + '长度：' + pageSize);
                         $(this).pagination('loading');
                         var tt = '${tab.title}';
                         loadData(tt, pageNumber, pageSize);
                         $(this).pagination('loaded');
                     }

               ">
            </div>
        </div>
    </g:each>
</div>

</body>
</html>
