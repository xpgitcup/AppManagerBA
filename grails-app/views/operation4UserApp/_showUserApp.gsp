<!DOCTYPE html>
<div id="show-userApp" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection=""/>
    <table>
        <thead>
        <th>名称</th>
        <th>说明</th>
        <th>日期</th>
        <th>操作</th>
        <th>Debug</th>
        </thead>
        <tbody>
        <g:each in="${userAppList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.appName}</td>
                <td>${item.description}</td>
                <td>${item.date}</td>
                <td>
                    <g:link action="edit" id="${item.id}" controller="userApp">
                        编辑
                    </g:link>
                </td>
                <td></td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
