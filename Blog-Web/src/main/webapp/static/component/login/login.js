/**
 * Created by amen on 17-10-11.
 */
var login = $(".login");

/**
 * login.show()
 * */
login.show = function() {
    for (var i=0; i<login.length; i++) {
        login[i].style.display = "block";
    }
};
/**
 * login.hide()
 * */
login.hide = function() {
    for (var i=0; i<login.length; i++) {
        login[i].style.display = "none";
    }
};

/**
 * closeBtn bind close event
 * */
var closeBtn = $(".login-close-btn");
if(closeBtn.length && closeBtn.length > 0) {
    for (var i=0; i<closeBtn.length; i++) {
        closeBtn[i].onclick = function() {
            login.hide();
        };
    }
}

/**
 * request password btn bind click event
 * */
var requestPasswordBtn = $(".login-request-password-btn");
if (requestPasswordBtn.length && requestPasswordBtn.length > 0) {
    for (var i=0; i<requestPasswordBtn.length; i++) {
        requestPasswordBtn[i].onclick = function() {
            var loginPhoneInput = $(".login-phone-input")[0];
            if(loginPhoneInput) {
                var loginPhoneInputValue = loginPhoneInput.value;
                if(loginPhoneInputValue === null || loginPhoneInputValue.trim() === "") {
                    alert("请填写手机号码");
                    return;
                }
                if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(loginPhoneInputValue))){
                    alert("不是完整的11位手机号或者正确的手机号前七位");
                    return false;
                }
                //请求发送验证码
                var queryUrl = "/third_party/request_phone_code?phone=" + loginPhoneInputValue.trim();
                var param = null;
                var method = GET;
                var requestPhoneCodeCallback = function (result) {
                    if (result.success) {
                        alert("验证码已发送");
                    }
                    else {
                        alert(result.msg);
                    }
                };
                executeRequest(queryUrl, param, method, requestPhoneCodeCallback);
            }
        }
    }
}

/**
 * login btn bind click event
 * */
var loginBtn = $(".login-btn");
if(loginBtn.length && loginBtn.length > 0) {
    for (var i=0; i<loginBtn.length; i++) {
        loginBtn[i].onclick = function() {
            var loginPhoneInput = $(".login-phone-input")[0];
            var loginPhoneInputValue = loginPhoneInput.value;
            if(loginPhoneInputValue === null || loginPhoneInputValue.trim() === "") {
                alert("请填写手机号码");
                return;
            }
            var loginRequestPasswordBtn = $(".login-password")[0];
            if(loginRequestPasswordBtn) {
                var phoneCode = loginRequestPasswordBtn.value;
                if(phoneCode === null || phoneCode.trim() === "") {
                    alert("请填写验证码");
                }
            }
            //登录
            var queryUrl = "/rest/phone_reg_and_login";
            var param = {
                phoneNumber: loginPhoneInputValue.trim(),
                code: phoneCode.trim()
            };
            var method = POST;
            var requestPhoneCodeCallback = function (result) {
                if (result.success) {
                    console.info(result.data);
                    login.hide();
                }
                else {
                    alert(result.msg);
                }
            };
            executeRequest(queryUrl, param, method, requestPhoneCodeCallback);
        }
    }
}

