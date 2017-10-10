function initBookPage() {
    queryMostLikeCountLeaveMessage();
    queryLeaveMessage ();
}



function queryMostLikeCountLeaveMessage () {
    var queryUrl = "/leave_message/rest/query_user_most_like_count_leave_message";
    var param = {username: username};
    var method = POST;
    var queryArticleCallback = function (result) {
        if (result.success) {
            var leaveMessages = result.data.leaveMessages;
            for (var i=0; i<leaveMessages.length; i++) {
                var leaveMessage = leaveMessages[i];
                var item = new LeaveMessage(leaveMessage.fromUser.avatar,
                                 leaveMessage.fromUser.nickname,
                                 leaveMessage.content,
                                 leaveMessage.createTime,
                                 null,
                                 leaveMessage.likeCount
                                );
                var dom = $("#mostLikeCountLeaveMessage");
                dom.innerHTML = dom.innerHTML + item.getLeaveMessageItem();
            }
        }
    }
    executeRequest(queryUrl, param, method, queryArticleCallback);
}

function queryLeaveMessage () {
    var queryUrl = "/leave_message/rest/query_user_leave_message";
    var param = {username: username};
    var method = POST;
    var queryArticleCallback = function (result) {
        if (result.success) {
            var leaveMessages = result.data.leaveMessages;
            for (var i=0; i<leaveMessages.length; i++) {
                var leaveMessage = leaveMessages[i];
                var item = new LeaveMessage(leaveMessage.fromUser.avatar,
                    leaveMessage.fromUser.nickname,
                    leaveMessage.content,
                    leaveMessage.createTime,
                    null,
                    leaveMessage.likeCount
                );
                var dom = $("#leaveMessages");
                dom.innerHTML = dom.innerHTML + item.getLeaveMessageItem();
            }
        }
    }
    executeRequest(queryUrl, param, method, queryArticleCallback);
}
