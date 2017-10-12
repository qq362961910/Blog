/**
 * Created by amen on 17-10-12.
 */
/**
 * 查询并渲染留言
 * */
function queryLeaveMessage (pageIndex) {
    var queryUrl = "/leave_message/rest/query_user_leave_message";
    var param = {username: username, pageSize: 3, currentPageKey: pageIndex || 1};
    var method = POST;
    var queryLeaveMessageCallback = function (result) {
        if (result.success) {
            var pageable = new Pageable(result.data.pageable);
            //渲染留言title
            var leaveMessageTitle = new LeaveMessageTitle(pageable.totalSize, 0, 0);
            renderLeaveMessageTitle(leaveMessageTitle);
            var leaveMessageList = pageable.objectList;
            //渲染留言列表
            renderLeaveMessageItem(leaveMessageList);
            //渲染page bar
            renderLeaveMessageListPageBar(pageable.totalSize, pageable.pageSize, pageable.currentPage, queryLeaveMessage);
        }
    };
    executeRequest(queryUrl, param, method, queryLeaveMessageCallback);
}
