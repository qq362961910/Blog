<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文章笔记</title>
    <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青"/>
    <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/style.css" rel="stylesheet">
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/common/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/pageable.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
    <!--[if lt IE 9]><script src="<%=static_file_path%>js/modernizr.js"></script><![endif]-->
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
    </aside>
</article>
<footer>
    <p>Design by DanceSmile <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a> <a href="/">网站统计</a>
    </p>
</footer>
<script type="text/javascript" src="<%=static_file_path%>js/articles.js"></script>
<script type="text/javascript">
    init();
</script>
</body>
</html>
