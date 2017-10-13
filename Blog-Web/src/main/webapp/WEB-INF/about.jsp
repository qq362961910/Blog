<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>关于我</title>
    <meta name="keywords" content="个人博客"/>
    <meta name="description" content="个人博客"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/about.css" rel="stylesheet">
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
    <!--[if lt IE 9]><script src="<%=static_file_path%>js/modernizr.js"></script><![endif]-->
</head>
<body>
<header>
    <%@include file="/static/component/navbar/navbar.jsp" %>
</header>
<article class="aboutcon">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="about left" id="user_detail">
    </div>
    <aside class="right">
        <div class="about_c">
            <p>网名：<span id="nickname"></span></p>
            <p>姓名：<span id="name"></span></p>
            <p>生日：<span id="birthday"></span></p>
            <p>籍贯：<span id="native_place"></span></p>
            <p>现居：<span id="address"></span></p>
            <p>职业：<span id="occupation"></span></p>
            <p>喜欢的书：<span id="like_books"></span></p>
            <p>喜欢的音乐：<span id="like_musics"></span></p>
            <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=1a2fc8ad422c4df633bf38af83b81f1786977f9c7894aa119f158fb350ea3e0c">
                <img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="码农" title="码农">
            </a>
            <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=W2htaWJtamJqaxsqKnU4NDY" style="text-decoration:none;">
                <img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png"/>
            </a>
            <a target="_blank" href="tencent://message/?uin=362961910" rel="nofollow">QQ联系我</a>
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
