<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文章笔记</title>
    <meta name="keywords" content="个人博客"/>
    <meta name="description" content="Amen个人博客"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/style.css" rel="stylesheet">
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/common/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/pageable.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
    <!--[if lt IE 9]><script src="<%=static_file_path%>js/modernizr.js"></script><![endif]-->
    <%--login--%>
    <link href="<%=static_file_path%>component/login/login.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/static/component/navbar/navbar.jsp" %>
</header>
<article class="blogs">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="newblog left">
        <%@include file="/static/component/article-list/article-list.jsp" %>
        <div class="blank"></div>
    </div>
    <aside class="right">
        <div class="rnav">
            <ul>
                <li class="rnav1"><a href="/download/" target="_blank">日记</a></li>
                <li class="rnav2"><a href="/newsfree/" target="_blank">程序人生</a></li>
                <li class="rnav3"><a href="/webapp/" target="_blank">欣赏</a></li>
                <li class="rnav4"><a href="/newshtml5/" target="_blank">短信祝福</a></li>
            </ul>
        </div>
        <div class="news">
            <%@include file="/static/component/latest-article/latest-article.jsp" %>
            <%@include file="/static/component/ranked-article/ranked-article.jsp" %>
        </div>
        <div class="visitors">
            <h3>
                <p>最近访客</p>
            </h3>
            <ul></ul>
        </div>
        <!-- Baidu Button BEGIN -->
        <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a
            class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a
            class="shareCount"></a></div>
        <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585"></script>
        <script type="text/javascript" id="bdshell_js"></script>
        <script type="text/javascript">
            document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date() / 3600000)
        </script>
        <!-- Baidu Button END -->
    </aside>
</article>
<%@include file="/static/component/login/login.jsp"%>
<footer>
    <p>Design by DanceSmile <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a> <a href="/">网站统计</a>
    </p>
</footer>
<script type="text/javascript" src="<%=static_file_path%>js/articles.js"></script>
<script type="text/javascript" src="<%=static_file_path%>component/login/login.js"></script>
</body>
</html>
