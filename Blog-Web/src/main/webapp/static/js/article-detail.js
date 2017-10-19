function queryArticle(id) {
    var queryUrl = "/article/" + id;
    var method = GET;

    var queryArticleCallback = function (result) {
        if (result.success) {
            var article = result.data.article;
            article = new Article(article.id, article.createTime, article.content, article.keyworks, article.likeCount, article.readCount, article.summary, article.title, article.coverImage, article.owner)
            var bindParam = {
                title: "title",
                publish_time: "createTime",
                article_owner: "owner.username",
                read_count: "readCount",
                article_content: "content",
                keywords: "keyworks"
            };
            article.bindHtml(bindParam);
        }
        else {
            $.redirect404();
        }
    };
    executeRequest(queryUrl, null, method, queryArticleCallback);
}
var uri = $.getUri();
var id = uri.substring(uri.lastIndexOf("/"));
queryArticle(id);
loadLatestArticle();
loadRankedArticle();
