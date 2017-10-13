/**
 * Created by amen on 17-10-13.
 */

function renderRecommendTemplateVerticalList(articleList) {
    var div = $("#article_div");
    for (var i = 0; i < articleList.length; i++) {
        var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
        var html = item.getIndexRecommendHtmlContent();
        div.innerHTML = div.innerHTML + html;
    }
}

function loadRecommendTemplateVerticalList() {
    var queryUrl = "/article/recommendArticle";
    var param = {username: username};
    var method = POST;
    var loadRecommendTemplateVerticalListCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            renderRecommendTemplateVerticalList(articleList);
        }
        else {
            if (result.code == SERVER_INTERNAL_EXCEPTION_CODE) {
                alert("服务器内部异常");
            }
            else {
                alert(result.msg);
            }
        }
    };
    executeRequest(queryUrl, param, method, loadRecommendTemplateVerticalListCallback);
}
