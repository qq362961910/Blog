<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<h3>
    <p>最新<span>文章</span></p>
</h3>
<ul class="rank" id="latestArticle"></ul>
<h3 class="ph">
    <p>点击<span>排行</span></p>
</h3>
<ul class="paih" id="readCountRankArticle"></ul>
<script type="text/javascript">
    //最新文章
    var queryUrl = "/article/latestArticle";
    var param = {username: username,pageSize: 11, currentPage: 1};
    var method = POST;
    var queryLatestArticleCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            var div = $("#latestArticle");
            for (var i = 0; i < articleList.length; i++) {
                var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
                var html = item.getIndexLatestArticleHtmlContent();
                div.innerHTML = div.innerHTML + html;
            }
        }
        else {
            if (result.code == SERVER_INTERNAL_EXCEPTION_CODE) {
                alert("服务器内部异常");
            }
            else {
                alert(result.msg);
            }
        }
    }
    executeRequest(queryUrl, param, method, queryLatestArticleCallback);

    //查看排行
    var queryUrl = "/article/readCountRankArticle";
    var param = {username: username, pageSize: 5, currentPage: 1};
    var method = POST;
    var queryReadCountArticleCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            var div = $("#readCountRankArticle");
            for (var i = 0; i < articleList.length; i++) {
                var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
                var html = item.getIndexRankedArticleHtml();
                div.innerHTML = div.innerHTML + html;
            }
        }
        else {
            if (result.code == SERVER_INTERNAL_EXCEPTION_CODE) {
                $.redirect500();
            }
            else if (result.code == RESOURCE_NOT_FOUND_EXCEPTION_CODE) {
                $.redirect404();
            }
            else {
                alert(result.msg);
            }
        }
    }
    executeRequest(queryUrl, param, method, queryReadCountArticleCallback);
</script>
