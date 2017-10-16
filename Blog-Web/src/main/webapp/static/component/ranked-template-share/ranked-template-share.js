/**
 * Created by amen on 17-10-13.
 */
function renderRankedTemplateShare (articleList) {
    var div = $("#readCountRankTemplateShare");
    for (var i = 0; i < articleList.length; i++) {
        var item = new Article(articleList[i].id, articleList[i].createTime, articleList[i].content, articleList[i].keyworks, articleList[i].likeCount, articleList[i].readCount, articleList[i].summary, articleList[i].title, articleList[i].coverImage, articleList[i].owner);
        var html = item.getIndexRankedArticleHtml();
        div.innerHTML = div.innerHTML + html;
    }
}

function loadRankedTemplateShare() {
    var queryUrl = "/article/read_count_rank_template_share";
    var param = {username: username, pageSize: 5, currentPage: 1};
    var method = POST;
    var loadRankedTemplateShareCallback = function (result) {
        if (result.success) {
            var articleList = result.data;
            renderRankedTemplateShare(articleList);
        }
        else {
            if (result.code === SERVER_INTERNAL_EXCEPTION_CODE) {
                $.redirect500();
            }
            else if (result.code === RESOURCE_NOT_FOUND_EXCEPTION_CODE) {
                $.redirect404();
            }
            else {
                alert(result.msg);
            }
        }
    };
    executeRequest(queryUrl, param, method, loadRankedTemplateShareCallback);
}

