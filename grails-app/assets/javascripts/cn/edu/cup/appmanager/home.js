$(function() {
    console.info("程序启动...");
    //显示IP
    showClientIP();
    //显示
    showAppRoles();
});


function showClientIP() {
    console.info("客户端IP...");
    ajaxRun("operation4UserApp/getClientIP", 0, "ipDiv")
}

function showAppRoles() {
    console.info("显示程序分类...");
    ajaxRun("operation4UserApp/liseAppRolles", 0, "appRolesDiv")
}