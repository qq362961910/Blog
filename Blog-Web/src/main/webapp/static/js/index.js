function indexInit() {

    //头像设置
    var queryUrl = "/user/" + username + "/userinfo";
    var method = GET;
    var queryUserAvatarCallback = function (result) {
        if (result.success) {
            $("#avatar").style.background = "url(" + result.data.avatar + ")  no-repeat";
            $("#avatar").style.backgroundSize = "130px 130px";
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
    executeRequest(queryUrl, null, method, queryUserAvatarCallback);

    //推荐文章
    var queryUrl = "/user/" + username + "/recommendArticle";
    var param = {};
    var method = POST;
    var queryRecommendCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            var div = $("#article_div");
            for (var i = 0; i < articleList.length; i++) {
                var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
                var html = item.getHtmlContent();
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
    executeRequest(queryUrl, param, method, queryRecommendCallback);

    //最新文章
    var queryUrl = "/user/" + username + "/latestArticle";
    var param = {pageSize: 11, currentPage: 1};
    var method = POST;
    var queryLatestArticleCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            var div = $("#latestArticle");
            for (var i = 0; i < articleList.length; i++) {
                var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
                var html = "<li><a href=\"/\" title=\"" + item.title + "\" target=\"_blank\">" + item.title + "</a></li>";
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
    var queryUrl = "/user/" + username + "/readCountRankArticle";
    var param = {pageSize: 5, currentPage: 1};
    var method = POST;
    var queryReadCountArticleCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            var div = $("#readCountRankArticle");
            for (var i = 0; i < articleList.length; i++) {
                var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
                var html = "<li><a href=\"/\" title=\"" + item.title + "\" target=\"_blank\">" + item.title + "</a></li>";
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
    executeRequest(queryUrl, param, method, queryReadCountArticleCallback);

    //个人模板
    var queryUrl = "/user/" + username + "/htmlTemplateList";
    var param = {pageSize: 6, currentPage: 1};
    var method = POST;
    var queryPersonHtmlTemplateCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            var div = $("#personal_html_template");
            for (var i = 0; i < articleList.length; i++) {
                var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
                var html = "<li><a href=\"/\" target=\"_blank\"><img src=\"" + item.coverImage + "\"></a><span>" + item.title + "</span></li>";
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
    executeRequest(queryUrl, param, method, queryPersonHtmlTemplateCallback);
}