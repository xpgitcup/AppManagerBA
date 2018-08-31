<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="mainCup"/>
    <title>Tomcat扫描</title>
</head>

<body>
<g:form controller="operation4UserApp" action="scanTomcat">
    <fieldset class="form">
        <label>请输入根目录：</label>
        <g:textField name="rootPath" required="" value="/home/usr/share/"/>
    </fieldset>
    <fieldset class="form">
        <label>路由器地址：</label>
        <g:textField name="routeIP" required="" value="10.1.16.50"/>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton class="save"  name="扫描" />
    </fieldset>
</g:form>
</body>
</html>
