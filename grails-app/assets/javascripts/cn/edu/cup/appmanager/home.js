$(function() {
    console.info("程序启动...");
    //显示IP
    showClientIP();
    //var roles = $("#rolesDiv").text().split(',')
    var tempString = $("#rolesDiv").text();
    var tempA = tempString.replace("[","");
    var tempB = tempA.replace("]","");
    var roles = tempB.split(",");
    console.info(roles);
    console.info(typeof(roles));

    tabPagesManager("appTabsDiv", roles);
    tabDisplaySettings("paginationListAppsRunningDiv", "operation4UserApp/countAppsRunning", "listAppsRunning")

});


function showClientIP() {
    console.info("客户端IP...");
    ajaxRun("operation4UserApp/getClientIP", 0, "ipDiv")
}

function listAppsRunning(pageNumber, pageSize) {
    console.info("正在运行的程序...");
    ajaxRun("operation4UserApp/getAppsRunning", 0, "appsRunningDiv");
}
