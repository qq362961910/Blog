/**
 * Created by amen on 17-10-13.
 */
var As = document.getElementById('topnav').getElementsByTagName('a');
var obj = As[0];
for (var i = 1; i < As.length; i++) {
    if (window.location.href.indexOf(As[i].href) >= 0) {
        obj = As[i];
    }
}
obj.id = 'topnav_current';
var loginBtn = $("#login");
if (loginBtn) {
    var ticket = $.getCookie("ticket");
    if (ticket) {
        $.replaceClass(loginBtn, "login-icon", "logout-icon");
        loginBtn.title = "退出";
    }
    //登录状态
    if (ticket) {
        $.registerEvent(loginBtn, "click", function () {
            var bool = window.confirm("确认推出?");
            if (bool) {
                var queryUrl = "/rest/logout";
                var param = {};
                var method = POST;
                var logoutCallback = function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                    else {
                        alert(result.msg);
                    }
                };
                executeRequest(queryUrl, param, method, logoutCallback);
            }
        });
    }
    //未登录状态
    else {
        $.addClass(loginBtn, "loginShowBtn");
    }
}

