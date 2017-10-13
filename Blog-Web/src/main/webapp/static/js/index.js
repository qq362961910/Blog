function indexInit() {

    //头像设置
    var queryUrl = "/user/" + username + "/userinfo";
    var method = GET;
    var queryUserAvatarCallback = function (result) {
        if (result.success) {
            $("#avatar").style.background = "url(" + result.data.avatar + ")  no-repeat";
            $("#avatar").style.backgroundSize = "130px 130px";
            $("#nickname").innerText = result.data.nickname;
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
    loadRecommendTemplateHorizontalList();
    loadRecommendTemplateVerticalList();
}
