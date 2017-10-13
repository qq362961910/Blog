/**
 * Created by amen on 17-10-13.
 */

function renderLatestArticle(articleList) {
    var div = $("#latestArticle");
    for (var i = 0; i < articleList.length; i++) {
        var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
        var html = item.getIndexLatestArticleHtmlContent();
        div.innerHTML = div.innerHTML + html;
    }
}
function loadLatestArticle() {
    var queryUrl = "/article/latestArticle";
    var param = {username: username, pageSize: 11, currentPage: 1};
    var method = POST;
    var queryLatestArticleCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            renderLatestArticle(articleList);
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
    executeRequest(queryUrl, param, method, queryLatestArticleCallback);
}
