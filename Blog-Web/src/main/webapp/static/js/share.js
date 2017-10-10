var currentPage = 1;
var size = 0;
var maxPageBtnCount = 5;
var totalPage = 0;

function queryUserHtmlTemplate(param) {

    var queryUrl = "/article/htmlTemplateList";
    var method = POST;
    var param = param || {}
    param.username = username;

    var queryUserHtmlTemplateCallback = function (result) {
        if (result.success) {
            var lastPage = currentPage;
            var htmlTemplates = result.data.htmlTemplates;
            currentPage = result.data.currentPageKey;
            size = result.data.size;
            totalPage = result.data.totalPageKey;
            changePageBar(lastPage, currentPage, size, totalPage);
            var div = $("#shareHtmlTemplateDiv");
            div.innerHTML = "";
            for (var i = 0; i < htmlTemplates.length; i++) {
                var item = new Article(htmlTemplates[i].id, htmlTemplates[i].createTime, htmlTemplates[i].content, htmlTemplates[i].keyworks, htmlTemplates[i].likeCount, htmlTemplates[i].readCount, htmlTemplates[i].summary, htmlTemplates[i].title, htmlTemplates[i].coverImage, htmlTemplates[i].owner);
                var html = item.getShareHtmlTemplateListItem();
                div.innerHTML = div.innerHTML + html;
            }
        }
        else {
            if (result.code == SERVER_INTERNAL_EXCEPTION_CODE) {
                $.alertError("服务器内部异常");
            }
            else {
                $.alertError(result.msg);
            }
        }
    }
    executeRequest(queryUrl, param, method, queryUserHtmlTemplateCallback);
}

function changePageBar(lastPage, currentPage, size, totalPage) {
    var pageBar = $("#pageBar");
    var totalShow = "<a title=\"Total record\"><b>" + size + "</b></a>";
    var pageBegin = Math.trunc((currentPage - 1) / maxPageBtnCount) * maxPageBtnCount + 1;
    var pageEnd = pageBegin + maxPageBtnCount - 1;
    var pageBtnShow = "";
    if (totalPage != 0) {
        if (currentPage != 1) {
            pageBtnShow += "<a href='javascript:void(0);' onclick='queryUserHtmlTemplate({currentPageKey: " + 1 + "})'>&lt;&lt;</a><a href='javascript:void(0);' onclick='queryUserHtmlTemplate({currentPageKey: " + (currentPage - 1) + "})'>&lt;</a>";
        }
        for (; pageBegin <= pageEnd && pageBegin <= totalPage; pageBegin++) {
            if (pageBegin == currentPage) {
                pageBtnShow += ("<b>" + pageBegin + "</b>");
            }
            else {
                pageBtnShow += "<a href='javascript:void(0);' onclick='queryUserHtmlTemplate({currentPageKey: " + pageBegin + "})'>" + pageBegin + "</a>";
            }
        }
        pageBtnShow += "<a href='javascript:void(0);' onclick='queryUserHtmlTemplate({currentPageKey: " + (currentPage + 1) + "})'>&gt;</a><a href='javascript:void(0);' onclick='queryUserHtmlTemplate({currentPageKey: " + totalPage + "})'>&gt;&gt;</a>";
    }
    pageBar.innerHTML = totalShow.concat(pageBtnShow);
}

function initShareUserHtmlTemplate() {
    queryUserHtmlTemplate();
}
