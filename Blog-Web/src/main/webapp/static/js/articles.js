var currentPage = 1;
var size = 0;
var maxPageBtnCount = 5;
var totalPage = 0;

function queryArticles(param) {
    //头像设置
    var queryUrl = "/user/" + username + "/article/list";
    var method = POST;
    var param = param || {username: username}
    var queryUserAvatarCallback = function (result) {
        if (result.success) {
            var lastPage = currentPage;
            var articleList = result.data.articles;
            currentPage = result.data.currentPageKey;
            size =  result.data.size;
            totalPage = result.data.totalPageKey;
            changePageBar(lastPage,currentPage,size,totalPage);
            var div = $("#articleDiv");
            div.innerHTML = "";
            for (var i = 0; i < articleList.length; i++) {
                var html = "<h2>" + articleList[i].title +"</h2>" +
                    "<p class=\"dateview\"><span>发布时间："+ articleList[i].createTime +"</span><span>作者：" + articleList[i].owner.username +"</span><span>分类：[<ahref=\"/news/life/\">程序人生</a>]</span></p>" +
                    "<figure><img src=\""+ articleList[i].coverImage +"\"></figure>" +
                    "<ul class=\"nlist\">" +
                    "<p>"+ articleList[i].summary +"</p>" +
                    "<a title=\"/\" href=\"/\" target=\"_blank\" class=\"readmore\">阅读全文>></a>" +
                    "</ul>" +
                    "<div class=\"line\"></div>";
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
    executeRequest(queryUrl, param, method, queryUserAvatarCallback);
}

function changePageBar(lastPage,currentPage,size,totalPage) {
    var pageBar = $("#pageBar");
    var totalShow = "<a title=\"Total record\"><b>"+ size +"</b></a>";
    var pageBegin = Math.trunc((currentPage-1)/maxPageBtnCount) * maxPageBtnCount + 1;
    var pageEnd = pageBegin + maxPageBtnCount - 1;
    var pageBtnShow = "";
    if (totalPage != 0) {
        if (currentPage != 1) {
            pageBtnShow +="<a href='javascript:void(0);' onclick='queryArticles({currentPageKey: "+ 1 +"})'>&lt;&lt;</a><a href='javascript:void(0);' onclick='queryArticles({currentPageKey: "+ (currentPage-1) +"})'>&lt;</a>";
        }
        for (; pageBegin<=pageEnd && pageBegin<=totalPage; pageBegin++) {
            if (pageBegin == currentPage) {
                pageBtnShow += ("<b>"+ pageBegin +"</b>");
            }
            else {
                pageBtnShow += "<a href='javascript:void(0);' onclick='queryArticles({currentPageKey: "+ pageBegin +"})'>"+ pageBegin +"</a>";
            }
        }
        pageBtnShow +="<a href='javascript:void(0);' onclick='queryArticles({currentPageKey: "+ (currentPage+1) +"})'>&gt;</a><a href='javascript:void(0);' onclick='queryArticles({currentPageKey: "+ totalPage +"})'>&gt;&gt;</a>";
    }
    pageBar.innerHTML = totalShow.concat(pageBtnShow);
}

function init() {
    queryArticles();
}