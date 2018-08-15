/**
 * Created by LiXiaoping on 2017/1/12.
 */

var pageSize = 10

/*
* 对象数组查找
* */
function arrayFind(arrayToSearch, attr, value) {
    for (var i = 0; i < arrayToSearch.length; i++) {
        if (arrayToSearch[i][attr] == value) {
            return arrayToSearch[i];
        }
    }
    return null;
}

function arrayFindIndex(arrayToSearch, attr, value) {
    for (var i = 0; i < arrayToSearch.length; i++) {
        if (arrayToSearch[i][attr] == value) {
            return i;
        }
    }
    return -1;
}

/*
 * Cookie读取，如果有，就读取；如果没有，赋值为1
 * */
function readCookie(cName, defaultValue) {
    var result = $.cookie(cName);
    if (!result) {
        result = defaultValue;
    }
    return result
}

/*
 * 分页计算函数
 * */
function getParams(pageNumber, pageSize) {
    var offset
    if (pageNumber > 1) {
        offset = (pageNumber - 1) * pageSize
    } else {
        offset = 0
    }
    var params = "?offset=" + offset + "&max=" + pageSize
    return params
}

/*
 * 通用ajax函数，统计某个数值
 * */
function ajaxCall(url, data) {
    //console.info("开始计算--" + url);
    var result = 0;
    $.ajax({
        type: 'POST',
        url: url,
        data: {data: data},
        async: false,
        success: function (data, textStatus) {
            //console.info("ajax状态：" + textStatus);
            //console.info("ajax结果：" + data);
            //console.info("ajax结果：" + data.count);
            result = data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.info(XMLHttpRequest);
            console.info(textStatus);
            console.info(errorThrown);
        }
    });
    //console.info("返回的结果：" + result);
    return result;
}

/*
 * 通用ajax函数，统计某个数值
 * */
function ajaxCalculate(url) {
    //console.info("开始计算--" + url);
    var result = 0;
    $.ajax({
        type: 'POST',
        url: url,
        async: false,
        success: function (data, textStatus) {
            //console.info("ajax状态：" + textStatus);
            //console.info("ajax结果：" + data);
            //console.info("ajax结果：" + data.count);
            result = parseInt(data.count);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.info(XMLHttpRequest);
            console.info(textStatus);
            console.info(errorThrown);
        }
    });
    //console.info("返回的结果：" + result);
    return result;
}


/*
 * 通用的ajax执行函数
 * */
function ajaxRun(url, id, divId) {
    //console.info(url + '---' + id);
    //console.info("当前路径:" + window.location)
    if (id != null) {
        $.ajax({
            type: 'POST',
            url: url,
            data: {id: id},
            success: function (data, textStatus) {
                $('#' + divId).html(data);
                //console.info(data);   //调试用的
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.info(XMLHttpRequest);
                console.info(textStatus);
                console.info(errorThrown);
            }
        });
    } else {
        $.ajax({
            type: 'POST',
            url: url,
            success: function (data, textStatus) {
                $('#' + divId).html(data);
                //console.info(data);   //调试用的
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.info(XMLHttpRequest);
                console.info(textStatus);
                console.info(errorThrown);
            }
        });
    }
}

/*
* 通用的tab页管理函数
* */

function tabPagesManager(tabsName, tabNameList) {
    var defaultTab = tabNameList[0];
    var currentTab = readCookie("current" + tabsName, defaultTab);
    var tabsDiv = $("#" + tabsName);

    tabsDiv.tabs("select", currentTab);

    tabsDiv.tabs({
            onSelect: function (title, index) {
                console.info(tabsName + "--选择标签：" + title + "--" + index);
                $.cookie("current" + tabsName, title, {path: '/'});
            }
        }
    );

}

/*
* 通用标签页显示程序
* */
function tabDisplaySettings(tabName, counter, lister) {
    var tabDiv = $("#" + tabName);
    console.info(tabDiv);

    var listFunction = eval(lister);
    console.info("传入的参数：" + lister);
    console.info(listFunction);

    var currentPage = readCookie("currentPage" + tabName, 1);
    var pageSize = readCookie("pageSize" +  tabName, pageSize);
    var totalCount = ajaxCalculate(counter)

    //分页
    tabDiv.pagination({
        pageSize: pageSize,
        total: totalCount,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listFunction(pageNumber, pageSize);
            $.cookie("currentPgae" + tabName, pageNumber);
        }
    });
    tabDiv.pagination("select", currentPage);
}

