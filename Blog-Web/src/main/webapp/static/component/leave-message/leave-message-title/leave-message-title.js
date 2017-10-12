/**
 * Created by amen on 17-10-12.
 */
function renderLeaveMessageTitle(leaveMessageTitle) {
    if (leaveMessageTitle) {
        $("#leave-message-title-totalSize").innerHTML = leaveMessageTitle.totalSize;
        $("#leave-message-title-fromSinaBlogSize").innerHTML = leaveMessageTitle.fromSinaBlogSize;
        $("#leave-message-title-fromTencentBlogSize").innerHTML = leaveMessageTitle.fromTencentBlogSize;
    }
}
