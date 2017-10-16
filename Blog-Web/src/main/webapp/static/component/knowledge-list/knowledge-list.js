/**
 * Created by amen on 17-10-16.
 */
function renderKnowledgeList(knowledgeList) {
    var div = $("#knowledgeListDiv");
    div.innerHTML = "";
    for (var i = 0; i < knowledgeList.length; i++) {
        var item = new Article(knowledgeList[i].id, knowledgeList[i].createTime, knowledgeList[i].content, knowledgeList[i].keyworks, knowledgeList[i].likeCount, knowledgeList[i].readCount, knowledgeList[i].summary, knowledgeList[i].title, knowledgeList[i].coverImage, knowledgeList[i].owner);
        var html = item.getArticlesItemHtml();
        div.innerHTML = div.innerHTML + html;
    }
}

function loadKnowledgeList(pageIndex) {
    var queryUrl = "/article/knowledge_list";
    var method = POST;
    var param= {username: username, currentPageKey: pageIndex || 1};
    var loadKnowledgeListCallback = function (result) {
        if (result.success) {
            var pageable = new Pageable(result.data.pageable);
            var knowledgeList = pageable.objectList;
            //渲染article列表
            renderKnowledgeList(knowledgeList);
            //渲染page bar
            renderKnowledgeListPageBar(pageable.totalSize, pageable.pageSize,pageable.totalPage, pageable.currentPage, loadKnowledgeList);
        }
        else {
            if (result.code === SERVER_INTERNAL_EXCEPTION_CODE) {
                alert("服务器内部异常");
            }
            else {
                alert(result.msg);
            }
        }
    };
    executeRequest(queryUrl, param, method, loadKnowledgeListCallback);
}
