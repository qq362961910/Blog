<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="gb2312">
    <title>闲言碎语</title>
    <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青"/>
    <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/mood.css" rel="stylesheet">
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
</head>
<body>
<header>
    <%@include file="/WEB-INF/base-page/navigation.jsp" %>
</header>
<div class="moodlist">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="bloglist" id="moodlist">
        <%--<ul class="arrow_box">
            <div class="sy">
                <img src="<%=static_file_path%>images/001.png">
                <p> 我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。</p>
            </div>
            <span class="dateview">2014-1-1</span>
        </ul>
        <ul class="arrow_box">
            <div class="sy">
                <p> 我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。我在岁月中找到他，依靠他，将一生交付给他。做他的妻子，他孩子的母亲，为他做饭，洗衣服，缝一颗掉了的纽扣。然后，我们一起在时光中变老。</p>
            </div>
            <span class="dateview">2014-1-1</span>
        </ul>
        <ul class="arrow_box">
            <div class="sy">
                <img src="<%=static_file_path%>images/001.png">
                <p> 我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。我在岁月中找到他，依靠他，将一生交付给他。做他的妻子，他孩子的母亲，为他做饭，洗衣服，缝一颗掉了的纽扣。然后，我们一起在时光中变老。</p>
                <span class="dateview">2014-1-1</span>
            </div>
        </ul>--%>
    </div>
    <div class="page" id="pageBar"></div>
</div>
<footer>
    <p>Design by DanceSmile <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a> <a href="/">网站统计</a>
    </p>
</footer>
</body>
<script type="text/javascript" src="<%=static_file_path%>js/entity/mood.js"></script>
<script type="text/javascript" src="<%=static_file_path%>js/moodlist.js"></script>
<script type="text/javascript">
    initUserMoodList();
</script>
</html>