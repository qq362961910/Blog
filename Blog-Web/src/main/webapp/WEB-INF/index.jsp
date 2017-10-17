<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人博客模板</title>
    <meta name="keywords" content="个人博客模板,博客模板"/>
    <meta name="description" content="寻梦主题的个人博客模板，优雅、稳重、大气,低调。"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/index.css" rel="stylesheet">
    <script src="<%=static_file_path%>js/common.js"></script>
    <script src="<%=static_file_path%>js/entity/article.js"></script>
    <!--[if lt IE 9]><script src="<%=static_file_path%>js/modernizr.js"></script><![endif]-->
    <%--login--%>
    <link href="<%=static_file_path%>component/login/login.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/static/component/navbar/navbar.jsp" %>
</header>
<%@include file="/static/component/banner/banner.jsp" %>
<div class="template">
    <div class="box">
        <h3>
            <p><span>个人博客</span>模板 Templates</p>
        </h3>
        <%@include file="/static/component/recommend-template-horizontal-list/recommend-template-horizontal-list.jsp" %>
    </div>
</div>
<article>
    <h2 class="title_tj">
        <p>文章<span>推荐</span></p>
    </h2>
    <%@include file="/static/component/recommend-template-vertical-list/recommend-template-vertical-list.jsp"%>
    <aside class="right">
        <div class="weather">
            <iframe width="250" scrolling="no" height="60" frameborder="0" allowtransparency="true"
                    src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1"></iframe>
        </div>
        <div class="news">
            <%@include file="/static/component/latest-article/latest-article.jsp" %>
            <%@include file="/static/component/ranked-article/ranked-article.jsp" %>
            <h3 class="links">
                <p>友情<span>链接</span></p>
            </h3>
            <ul class="website">
                <li><a href="/">个人博客</a></li>
                <li><a href="/">谢泽文个人博客</a></li>
                <li><a href="/">3DST技术网</a></li>
                <li><a href="/">思衡网络</a></li>
            </ul>
        </div>
        <a href="/" class="weixin"></a>
    </aside>
</article>
<%@include file="/static/component/login/login.jsp"%>
<footer>
    <p>
        Design by DanceSmile
        <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a>
        <a href="/">网站统计</a>
    </p>
</footer>
<script type="text/javascript" src="/static/js/index.js"></script>
<script type="text/javascript" src="<%=static_file_path%>component/login/login.js"></script>
</body>
</html>
