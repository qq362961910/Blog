function renderUserMoodList(moodList) {
    var moodListDiv = $("#moodList");
    moodListDiv.innerHTML = '';
    for (var i = 0; i < moodList.length; i++) {
        var mood = new Mood(moodList[i].id, moodList[i].coverImage, moodList[i].content, moodList[i].createTime);
        moodListDiv.innerHTML = moodListDiv.innerHTML + mood.getMoodListItem();
    }
}

function loadUserMoodList(pageIndex) {
    var queryUrl = "/mood/list";
    var method = POST;
    var param = {username: username, currentPageKey: pageIndex || 1, pageSize: 1};
    var queryUserMoodListCallback = function (result) {
        if (result.success) {
            var pageable = new Pageable(result.data.pageable);
            var moodList = pageable.objectList;
            //渲染article列表
            renderUserMoodList(moodList);
            //渲染page bar
            renderMoodListPageBar(pageable.totalSize, pageable.pageSize,pageable.totalPage, pageable.currentPage, loadUserMoodList);
        }
        else {
            $.alertError(result.msg);
        }
    };
    executeRequest(queryUrl, param, method, queryUserMoodListCallback);
}
