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

function tabPagesManager(tabsName, tabNameList, listFunction) {
    // 初始设置
    var defaultTab = tabNameList[0];
    var currentTab = readCookie("current" + tabsName, defaultTab);
    var tabsDiv = $("#" + tabsName);

    // 设置标签管理函数
    tabsDiv.tabs({
            onSelect: function (title, index) {
                //记录tabs的缺省页面，所以采用tabsName
                console.info(tabsName + "--选择标签：" + title + "--" + index);
                $.cookie("current" + tabsName, title, {path: '/'});
                //------------------------------------------------------------------------------------------------------
                loadFirstData(title, listFunction);
            }
        }
    );

    // 打开缺省的标签
    tabsDiv.tabs("select", currentTab);
    loadFirstData(currentTab, listFunction);
}

function loadFirstData(title, listFunction) {
    var tabPagination = "paginationListAppsDiv" + title;
    var tp = $("#" + tabPagination);
    console.info("加载数据..." + tabPagination + tp);
    tp.pagination('refresh');
    var page = readCookie("curregtPage" + title, 1);
    var listFunction = eval(listFunction);
    listFunction(title, page, pageSize);
}

/*
    //循环设置各个分页---这是设置
    for (var i = 0; i < tabNameList.length; i++) {
        var title = tabNameList[i].trim();
        var tabDiv = $("#" + paginationDiv +  title);

        var currentPage = readCookie("currentPage" + title, 1);
        var cpageSize = readCookie("pageSize" + title, pageSize);
        var totalCount = countFunction(title);

        console.info("具体设置标签页:" + title + tabDiv + "具体参数：" + currentPage + "/" + cpageSize);

        //分页
        tabDiv.pagination({
            pageSize: cpageSize,
            total: totalCount,
            showPageList: true,
            displayMsg: '',
            layout: ['first', 'prev', 'links', 'next', 'last'],
            //翻页函数
            onSelectPage: function (pageNumber, pageSize) {
                console.info("调用来自onSelectPage:" + pageNumber + "/" + cpageSize);
                listFunction(title, pageNumber, pageSize);
                $.cookie("currentPgae" + title, pageNumber);
                $.cooke("pageSize" + title, cpageSize);
            }
        });
    }

* */

/*
* 加载页面的数据
* */
function loadTabPageDefaultData(title, listFunction, countFunction) {

    console.info("加载缺省页面数据...");

    var countFunction = eval(countFunction);
    var listFunction = eval(listFunction);

    //首先获取缺省的页面，获取页大小，获取总数
    var currentPage = readCookie("currentPage" + title, 1);
    var cpageSize = readCookie("pageSize" + title, pageSize);
    var totalCount = countFunction(title);
    console.info("当前页：" + title + ":" + currentPage + "总数：" + totalCount);
    listFunction(title, currentPage, cpageSize)
}

