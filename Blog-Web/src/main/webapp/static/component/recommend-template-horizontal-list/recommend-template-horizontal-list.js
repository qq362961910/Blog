/**
 * Created by amen on 17-10-13.
 */
function renderRecommendTemplateHorizontalList(articleList) {
    var div = $("#personal_html_template");
    for (var i = 0; i < articleList.length; i++) {
        var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
        var html = item.getIndexPersonalTemplateHtml();
        div.innerHTML = div.innerHTML + html;
    }
}
function loadRecommendTemplateHorizontalList() {
    var queryUrl = "/article/indexHtmlTemplateList";
    var param = {username: username, pageSize: 6, currentPage: 1};
    var method = POST;
    var loadRecommendTemplateHorizontalListCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            renderRecommendTemplateHorizontalList(articleList);
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
    executeRequest(queryUrl, param, method, loadRecommendTemplateHorizontalListCallback);
}
