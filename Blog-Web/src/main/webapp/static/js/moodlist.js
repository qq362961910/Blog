var currentPage = 1;
var size = 0;
var maxPageBtnCount = 5;
var totalPage = 0;
function queryUserMoodList(param) {

    var queryUrl = "/mood/list";
    var method = POST;
    var param = param || {};
    param.username = username;
    param.pageSize = 1;
    var queryUserMoodListCallback = function(result) {
        if (result.success) {
            var lastPage = currentPage;
            var moods = result.data.moods;
            currentPage = result.data.currentPageKey;
            size = result.data.size;
            totalPage = result.data.totalPageKey;
            changePageBar(lastPage, currentPage, size, totalPage);

            var moodlistDiv = $("#moodlist");
            moodlistDiv.innerHTML = '';
            for (var i=0; i<moods.length; i++) {
                var mood = new Mood(moods[i].id, moods[i].coverImage, moods[i].content, moods[i].createTime);
                moodlistDiv.innerHTML = moodlistDiv.innerHTML + mood.getMoodListItem();
            }
        }
        else {
            $.alertError(result.msg);
        }
    }
    executeRequest(queryUrl, param, method, queryUserMoodListCallback);
}

function changePageBar(lastPage, currentPage, size, totalPage) {
    var pageBar = $("#pageBar");
    var totalShow = "<a title=\"Total record\"><b>" + size + "</b></a>";
    var pageBegin = Math.trunc((currentPage - 1) / maxPageBtnCount) * maxPageBtnCount + 1;
    var pageEnd = pageBegin + maxPageBtnCount - 1;
    var pageBtnShow = "";
    if (totalPage != 0) {
        if (currentPage != 1) {
            pageBtnShow += "<a href='javascript:void(0);' onclick='queryUserMoodList({currentPageKey: " + 1 + "})'>&lt;&lt;</a><a href='javascript:void(0);' onclick='queryUserMoodList({currentPageKey: " + (currentPage - 1) + "})'>&lt;</a>";
        }
        for (; pageBegin <= pageEnd && pageBegin <= totalPage; pageBegin++) {
            if (pageBegin == currentPage) {
                pageBtnShow += ("<b>" + pageBegin + "</b>");
            }
            else {
                pageBtnShow += "<a href='javascript:void(0);' onclick='queryUserMoodList({currentPageKey: " + pageBegin + "})'>" + pageBegin + "</a>";
            }
        }
        pageBtnShow += "<a href='javascript:void(0);' onclick='queryUserMoodList({currentPageKey: " + (currentPage + 1) + "})'>&gt;</a><a href='javascript:void(0);' onclick='queryUserMoodList({currentPageKey: " + totalPage + "})'>&gt;&gt;</a>";
    }
    pageBar.innerHTML = totalShow.concat(pageBtnShow);
}

function initUserMoodList() {
    queryUserMoodList();
}