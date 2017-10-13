/**
 * Created by amen on 17-10-13.
 */

function renderBannerUserInfo(user) {
    $("#nickname").innerText = user.nickname;
    $("#avatar").style.background = "url(" + user.avatar + ")  no-repeat";
    $("#avatar").style.backgroundSize = "130px 130px";
}
function loadBannerUserInfo() {
    var queryUrl = "/user/" + username + "/userinfo";
    var method = GET;
    var queryUserAvatarCallback = function (result) {
        if (result.success) {
            var user = new User(result.data.nickname, username, result.data.avatar);
            renderBannerUserInfo(user);
        }
        else {
            if (result.code == SERVER_INTERNAL_EXCEPTION_CODE) {
                alert("服务器内部异常");
            }
            else {
                alert(result.msg);
            }
        }
    };
    executeRequest(queryUrl, null, method, queryUserAvatarCallback);
}

