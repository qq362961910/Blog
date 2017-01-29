function queryUserInfo() {

    var queryUrl = "/user/" + username + "/userinfo";
    var method = GET;
    var queryUserAvatarCallback = function (result) {
        if (result.success) {
            var userInfo = result.data;
            var bindParam = {};
            bindParam["user_detail"] = userInfo.profile.selfIntroduction;
            bindParam["nickname"] = userInfo.nickname;
            bindParam["name"] = userInfo.profile.realName;
            bindParam["birthday"] = userInfo.profile.birthday;
            bindParam["native_place"] = userInfo.profile.nativePlace;
            bindParam["address"] = userInfo.profile.address;
            bindParam["occupation"] = userInfo.profile.occupation;
            var books = userInfo.profile.likeBooks;
            var bookContent = '';
            if (typeof books != UNDEFINED && books != null) {
                for (var i=0; i<books.length; i++) {
                    bookContent += "&lt;&lt;" + books[i].name + "&gt;&gt; ";
                }
            }
            bindParam["like_books"] = bookContent;

            var musics = userInfo.profile.likeMusics;
            var musicContent = '';
            if (typeof musics != UNDEFINED && musics != null) {
                for (var i=0; i<musics.length; i++) {
                    musicContent += "&lt;&lt;" + musics[i].name + "&gt;&gt; ";
                }
            }
            bindParam["like_musics"] = musicContent;
            $.bindHtml(bindParam);
        }
        else {
            if (result.code == SERVER_INTERNAL_EXCEPTION_CODE) {
                alert("服务器内部异常");
            }
            else {
                alert(result.msg);
            }
        }
    }
    executeRequest(queryUrl, null, method, queryUserAvatarCallback);
}