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
    <!--[if lt IE 9]>
    <script src="<base href="<%=static_file_path%>js/modernizr.js"></script>
    <![endif]-->
</head>
<body>
<header>
    <%@include file="/static/component/navbar/navbar.jsp" %>
</header>
<div class="banner">
    <section class="box">
        <ul class="texts">
            <p>打了死结的青春，捆死一颗苍白绝望的灵魂。</p>
            <p>为自己掘一个坟墓来葬心，红尘一梦，不再追寻。</p>
            <p>加了锁的青春，不会再因谁而推开心门。</p>
        </ul>
        <div class="avatar">
            <a id="avatar" href="javascript:void(0);">
                <span id="nickname"></span>
            </a>
        </div>
    </section>
</div>
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
            <%@include file="/WEB-INF/share/latestAndRankArticle.jsp" %>
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
        <a href="/" class="weixin"> </a></aside>
</article>
<footer>
    <p>
        Design by DanceSmile
        <a href="http://www.mycodes.net/" title="源码之家" target="_blank">源码之家</a>
        <a href="/">网站统计</a>
    </p>
</footer>
<script type="text/javascript" src="<%=static_file_path%>js/index.js"></script>
<script type="text/javascript">
    indexInit();
</script>
</body>
</html>
