$(function () {
    console.info("第一个程序...");

    var tabNameList = ["appsRunning", "appsInHardDisk", "appsInDB"];
    tabPagesManager("appTabsDiv", tabNameList);
    tabDisplaySettings("paginationListAppsRunningDiv", "operation4UserApp/countAppsRunning", "listAppsRunning")
})


function listAppsRunning(pageNumber, pageSize) {
    console.info("正在运行的程序...");
    ajaxRun("operation4UserApp/getAppsRunning", 0, "appsRunningDiv");
}
