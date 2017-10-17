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
    <%--login--%>
    <link href="<%=static_file_path%>component/login/login.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/static/component/navbar/navbar.jsp" %>
</header>
<article>
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="about left" id="user_detail">
    </div>
    <aside class="right aboutcon">
        <%@include file="/static/component/user-introduction/user-introduction.jsp" %>
    </aside>
</article>
<%@include file="/static/component/login/login.jsp"%>
<footer>
    <p>Design by DanceSmile <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a> <a href="/">网站统计</a>
    </p>
</footer>
</body>
<script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
<script type="text/javascript" src="<%=static_file_path%>js/about.js"></script>
<script type="text/javascript" src="<%=static_file_path%>component/login/login.js"></script>
</html>
