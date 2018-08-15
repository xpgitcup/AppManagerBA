<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--设置Base-->
    <base href="<%=basePath%>"/>

    <asset:stylesheet src="application.css"/>

    <!--引入easyui的相关内容-->
    <asset:stylesheet src="easyui/themes/default/easyui.css"/>
    <asset:stylesheet src="easyui/themes/icon.css"/>
    <asset:stylesheet src="easyui/themes/color.css"/>

    <!--引入CUP的相关内容-->
    <asset:stylesheet src="cn/edu/cup/cupEasyUi.css"/>

    <!--JS加载-->
    <asset:javascript src="jquery-2.2.0.min.js"/>

    <asset:javascript src="easyui/jquery.easyui.min.js"/>
    <asset:stylesheet src="jquery-ui/jquery-ui.min.css"/>

    <asset:javascript src="jquery/jquery.cookie.js"/>

    <!--用户自定义的-->
    <asset:javascript src="cn/edu/cup/common/common.js"/>

    <g:layoutHead/>
</head>

<body>
<div id="grailsLogo" role="banner"><a href="http://grails.org"><asset:image src="sylogo.gif" alt="Grails"/></a></div>
<g:layoutBody/>
<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
</body>
</html>
