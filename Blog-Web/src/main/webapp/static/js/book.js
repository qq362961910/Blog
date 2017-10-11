function initBookPage() {
    queryMostLikeCountLeaveMessage();
    queryLeaveMessage ();
}



function queryMostLikeCountLeaveMessage () {
    var queryUrl = "/leave_message/rest/query_user_most_like_count_leave_message";
    var param = {username: username};
    var method = POST;
    var queryMostLikeCountLeaveMessageCallback = function (result) {
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
    };
    executeRequest(queryUrl, param, method, queryMostLikeCountLeaveMessageCallback);
}

function queryLeaveMessage () {
    var queryUrl = "/leave_message/rest/query_user_leave_message";
    var param = {username: username};
    var method = POST;
    var queryLeaveMessageCallback = function (result) {
        if (result.success) {
            var leaveMessages = result.data.leaveMessages;
            var dom = $("#leaveMessages");
            dom.innerHTML = "";
            for (var i=0; i<leaveMessages.length; i++) {
                var leaveMessage = leaveMessages[i];
                var item = new LeaveMessage(leaveMessage.fromUser.avatar,
                    leaveMessage.fromUser.nickname,
                    leaveMessage.content,
                    leaveMessage.createTime,
                    null,
                    leaveMessage.likeCount
                );
                dom.innerHTML = dom.innerHTML + item.getLeaveMessageItem();
            }
        }
    };
    executeRequest(queryUrl, param, method, queryLeaveMessageCallback);
}

function publishLeaveMessage() {
    var content = $("#replyContent").value;
    if (content === null || content.trim() === "") {
        return;
    }
    var queryUrl = "/leave_message";
    var param = {username: username, content: content};
    var method = POST;
    var publishLeaveMessageCallback = function (result) {
        if (result.success) {
            //重新加载留言板
            queryLeaveMessage ();
        }
        else {
            //未登录
            if (result.code === "check_0001") {
                login.show();
            }
        }
    };
    executeRequest(queryUrl, param, method, publishLeaveMessageCallback);
}
