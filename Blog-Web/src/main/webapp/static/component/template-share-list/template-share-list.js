/**
 * Created by amen on 17-10-16.
 */
function renderTemplateShareList(templateShareList) {
    var div = $("#templateShareListDiv");
    div.innerHTML = "";
    for (var i = 0; i < templateShareList.length; i++) {
        var item = new Article(templateShareList[i].id, templateShareList[i].createTime, templateShareList[i].content, templateShareList[i].keyworks, templateShareList[i].likeCount, templateShareList[i].readCount, templateShareList[i].summary, templateShareList[i].title, templateShareList[i].coverImage, templateShareList[i].owner);
        var html = item.getShareHtmlTemplateListItem();
        div.innerHTML = div.innerHTML + html;
    }
}

function loadTemplateShareList(pageIndex) {
    var queryUrl = "/article/template_share_list";
    var method = POST;
    var param = {username: username, currentPageKey: pageIndex || 1};
    var loadTemplateShareListCallback = function (result) {
        if (result.success) {
            var pageable = new Pageable(result.data.pageable);
            var shareTemplateList = pageable.objectList;
            //渲染article列表
            renderTemplateShareList(shareTemplateList);
            //渲染page bar
            renderTemplateShareListPageBar(pageable.totalSize, pageable.pageSize,pageable.totalPage, pageable.currentPage, loadTemplateShareList);
        }
        else {
            if (result.code === SERVER_INTERNAL_EXCEPTION_CODE) {
                $.alertError("服务器内部异常");
            }
            else {
                $.alertError(result.msg);
            }
        }
    };
    executeRequest(queryUrl, param, method, loadTemplateShareListCallback);
}
