/**
 * Created by amen on 17-10-12.
 */
/**
 * 获取留言item
 * */
function renderLeaveMessageItem(leaveMessageList) {
    var dom = $("#leaveMessages");
    dom.innerHTML = "";
    for (var i=0; i<leaveMessageList.length; i++) {
        var leaveMessage = leaveMessageList[i];
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
