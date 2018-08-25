/**
 * Created by LiXiaoping on 2018/8/25.
 */

var pageSize = 10

/*
* 说明：
*     listFunction -- 数据列表函数，用户自定义的
*     countFunction --- 数据统计函数，用户自定义
* */

/*
* 通用的tab页管理函数
* */

function tabPagesManager(tabsName, tabNameList, listFunction, countFunction, paginationDiv) {
    var defaultTab = tabNameList[0];
    var currentTab = readCookie("current" + tabsName, defaultTab);
    var tabsDiv = $("#" + tabsName);
    var listFunction = eval(listFunction);
    var countFunction = eval(countFunction);

    tabsDiv.tabs({
            onSelect: function (title, index) {
                //记录tabs的缺省页面，所以采用tabsName
                console.info(tabsName + "--选择标签：" + title + "--" + index);
                $.cookie("current" + tabsName, title, {path: '/'});
                //------------------------------------------------------------------------------------------------------
                console.info("加载数据...");
                loadTabPageDefaultData(title, listFunction, countFunction);
            }
        }
    );

    tabsDiv.tabs("select", currentTab);

    //循环设置各个分页
    for (var i = 0; i < tabNameList.length; i++) {
        var title = tabNameList[i].trim();
        var tabDiv = $("#" + paginationDiv +  title);
        console.info("设置tabs:" + title + tabDiv);

        var currentPage = readCookie("currentPage" + title, 1);
        var pageSize = readCookie("pageSize" + title, pageSize);
        var totalCount = countFunction(title);
        //分页
        tabDiv.pagination({
            pageSize: pageSize,
            total: totalCount,
            showPageList: true,
            displayMsg: '',
            layout: ['first', 'prev', 'links', 'next', 'last'],
            //翻页函数
            onSelectPage: function (pageNumber, pageSize) {
                //listFunction(pageNumber, pageSize);
                $.cookie("currentPgae" + title, pageNumber);
            }
        });
    }

}

/*
* 加载页面的数据
* */
function loadTabPageDefaultData(title, listFunction, countFunction) {

    console.info("加载缺省页面数据...");

    var countFunction = eval(countFunction);
    var listFunction = eval(listFunction);

    //首先获取缺省的页面，获取页大小，获取总数
    var currentPage = readCookie("currentPage" + title, 1);
    var pageSize = readCookie("pageSize" + title, pageSize);
    var totalCount = countFunction(title);
    console.info("当前页：" + title + ":" + currentPage + "总数：" + totalCount);
    listFunction(title, currentPage, pageSize)
}

/*
* 通用标签页显示程序
* */
function tabDisplaySettings(tabName, counter, lister) {

    var listFunction = eval(lister);
    console.info("传入的参数：" + lister);
    console.info(listFunction);


    tabDiv.pagination("select", currentPage);
}

