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

<div id="ipDiv" class="easyui-panel"></div>

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
