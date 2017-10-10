/**
 * Created by amen on 17-10-10.
 */
function LeaveMessage(avatar, nickname, content, createTime, replies, likeCount) {
    this.avatar = avatar;
    this.nickname = nickname;
    this.content = content;
    this.createTime =createTime;
    this.replies = replies;
    this.likeCount = likeCount;

    this.getLeaveMessageItem = function() {
       return  "<li class='ds-post'>" +
                    "    <div class='ds-post-self' data-root-id='0' data-source='duoshuo'>" +
                    "        <div class='ds-avatar'>" +
                    "            <img src='"+ this.avatar +"' alt='小丑'>" +
                    "        </div>" +
                    "        <div class='ds-comment-body'>" +
                    "            <div class='ds-comment-header'>" +
                    "                <span class='ds-user-name'>"+ this.nickname +"</span>" +
                    "            </div>" +
                    "            <p>"+ this.content +"</p>" +
                    "            <div class='ds-comment-footer ds-comment-actions'>" +
                    "                <span class='ds-time' title='"+ this.createTime +"'>"+ this.createTime +"</span>" +
                    "                <a class='ds-post-reply' href='javascript:void(0);'>" +
                    "                    <span class='ds-icon ds-icon-reply'></span>回复" +
                    "                </a>" +
                    "                <a class='ds-post-likes' href='javascript:void(0);'>" +
                    "                    <span class='ds-icon ds-icon-like'></span>顶("+ this.likeCount +")" +
                    "                </a>" +
                    "                <a class='ds-post-repost' href='javascript:void(0);'>" +
                    "                    <span class='ds-icon ds-icon-share'></span>转发" +
                    "                </a>" +
                    "                <a class='ds-post-report' href='javascript:void(0);'>" +
                    "                    <span class='ds-icon ds-icon-report'></span>举报" +
                    "                </a>" +
                    "            </div>" +
                    "        </div>" +
                    "    </div>" +
                    "</li>";
    }
}
