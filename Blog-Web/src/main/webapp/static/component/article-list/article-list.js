/**
 * Created by amen on 17-10-16.
 */
function renderArticleList(articleList) {
    var div = $("#articleDiv");
    div.innerHTML = "";
    for (var i = 0; i < articleList.length; i++) {
        var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
        var html = item.getArticlesItemHtml();
        div.innerHTML = div.innerHTML + html;
    }
}

function queryArticleList(pageIndex) {
    var queryUrl = "/article/list";
    var method = POST;
    var param= {username: username, currentPageKey: pageIndex || 1};
    var queryArticleListCallback = function (result) {
        if (result.success) {
            var pageable = new Pageable(result.data.pageable);
            var articleList = pageable.objectList;
            //渲染article列表
            renderArticleList(articleList);
            //渲染page bar
            renderArticleListPageBar(pageable.totalSize, pageable.pageSize,pageable.totalPage, pageable.currentPage, queryArticleList);
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
    executeRequest(queryUrl, param, method, queryArticleListCallback);
}
