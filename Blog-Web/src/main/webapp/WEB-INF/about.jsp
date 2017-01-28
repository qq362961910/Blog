<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>关于我</title>
    <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青"/>
    <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/about.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="<%=static_file_path%>js/modernizr.js"></script>
    <![endif]-->
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
</head>
<body>
<header>
    <%@include file="/WEB-INF/base-page/navigation.jsp" %>
</header>
<article class="aboutcon">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="about left" id="user_detail">
    </div>
    <aside class="right">
        <div class="about_c">
            <p>网名：<span id="nickname"></span></p>
            <p>姓名：<span id="name"></span> </p>
            <p>生日：<span id="birthday"></span></p>
            <p>籍贯：<span id="native_place"></span></p>
            <p>现居：<span id="address"></span></p>
            <p>职业：<span id="occupation"></span></p>
            <p>喜欢的书：<span id="like_books"></span></p>
            <p>喜欢的音乐：<span id="like_musics"></span></p>
            <a target="_blank"
               href="http://wp.qq.com/wpa/qunwpa?idkey=d4d4a26952d46d564ee5bf7782743a70d5a8c405f4f9a33a60b0eec380743c64">
                <img src="http://pub.idqqimg.com/wpa/images/group.png" alt="杨青个人博客网站" title="杨青个人博客网站"></a>
            <a target="_blank"
               href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=HHh9cn95b3F1cHVye1xtbTJ-c3E"><img
                    src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_22.png"
                    alt="杨青个人博客网站"></a>
        </div>
    </aside>
</article>
<footer>
    <p>Design by DanceSmile <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a> <a href="/">网站统计</a>
    </p>
</footer>
</body>
<script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
<script type="text/javascript" src="<%=static_file_path%>js/about.js"></script>
<script type="text/javascript">
    queryUserInfo();
</script>
</html>