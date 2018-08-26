$(function() {
    console.info("程序启动...");
    //显示IP
    showClientIP();
    //var roles = $("#rolesDiv").text().split(',')
    var tempString = $("#rolesDiv").text();
    var tempA = tempString.replace("[","");
    var tempB = tempA.replace("]","");
    var roles = tempB.split(",");
    //console.info(roles);
    //console.info(typeof(roles));

    tabPagesManager("appRolesDiv", roles, countFunction, listFunction, "paginationListAppsDiv");

});

function countFunction(title) {
    var count = ajaxCalculate("operation4UserApp/countUserApp?title=" + title)
    console.info("自定义统计函数：" + title + " " + count);
    return count
}

function listFunction(title, page, pageSize) {
    console.info("自定义列表函数：" + title + " 页码 " + page + "页大小" + pageSize);
    var param = "?title=" + title +  getParams(page, pageSize)
    ajaxRun("operation4UserApp/listAppsRunning" + param, 0, "listAppsDiv" + title);
}

function showClientIP() {
    console.info("客户端IP...");
    ajaxRun("operation4UserApp/getClientIP", 0, "ipDiv")
}

