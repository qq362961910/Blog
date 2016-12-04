<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="gb2312">
    <title>文章笔记</title>
    <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青"/>
    <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="<%=static_file_path%>js/modernizr.js"></script>
    <![endif]-->
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
</head>
<body>
<header>
    <div id="logo"><a href="/"></a></div>
    <%@include file="/WEB-INF/base-page/navigation.jsp" %>
</header>
<article class="blogs">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="newblog left">
        <div id="articleDiv">
            <%--<h2>程序员请放下你的技术情节，与你的同伴一起进步</h2>

            <p class="dateview"><span>发布时间：2013-11-04</span><span>作者：杨青</span><span>分类：[<a
                    href="/news/life/">程序人生</a>]</span></p>
            <figure><img src="<%=static_file_path%>images/001.png"></figure>
            <ul class="nlist">
                <p>如果说掌握一门赖以生计的技术是技术人员要学会的第一课的话， 那么我觉得技术人员要真正学会的第二课，不是技术，而是业务、交流与协作，学会关心其他工作伙伴的工作情况和进展...</p>
                <a title="/" href="/" target="_blank" class="readmore">阅读全文>></a>
            </ul>
            <div class="line"></div>
            <h2>程序员请放下你的技术情节，与你的同伴一起进步</h2>

            <p class="dateview"><span>发布时间：2013-11-04</span><span>作者：杨青</span><span>分类：[<a
                    href="/news/life/">程序人生</a>]</span></p>
            <figure><img src="<%=static_file_path%>images/001.png"></figure>
            <ul class="nlist">
                <p>如果说掌握一门赖以生计的技术是技术人员要学会的第一课的话， 那么我觉得技术人员要真正学会的第二课，不是技术，而是业务、交流与协作，学会关心其他工作伙伴的工作情况和进展...</p>
                <a title="/" href="/" target="_blank" class="readmore">阅读全文>></a>
            </ul>
            <div class="line"></div>
            <h2>程序员请放下你的技术情节，与你的同伴一起进步</h2>

            <p class="dateview"><span>发布时间：2013-11-04</span><span>作者：杨青</span><span>分类：[<a
                    href="/news/life/">程序人生</a>]</span></p>
            <figure><img src="<%=static_file_path%>images/001.png"></figure>
            <ul class="nlist">
                <p>如果说掌握一门赖以生计的技术是技术人员要学会的第一课的话， 那么我觉得技术人员要真正学会的第二课，不是技术，而是业务、交流与协作，学会关心其他工作伙伴的工作情况和进展...</p>
                <a title="/" href="/" target="_blank" class="readmore">阅读全文>></a>
            </ul>
            <div class="line"></div>
            <h2>程序员请放下你的技术情节，与你的同伴一起进步</h2>

            <p class="dateview"><span>发布时间：2013-11-04</span><span>作者：杨青</span><span>分类：[<a
                    href="/news/life/">程序人生</a>]</span></p>
            <figure><img src="<%=static_file_path%>images/001.png"></figure>
            <ul class="nlist">
                <p>如果说掌握一门赖以生计的技术是技术人员要学会的第一课的话， 那么我觉得技术人员要真正学会的第二课，不是技术，而是业务、交流与协作，学会关心其他工作伙伴的工作情况和进展...</p>
                <a title="/" href="/" target="_blank" class="readmore">阅读全文>></a>
            </ul>
            <div class="line"></div>--%>
        </div>
        <div class="blank"></div>
        <div class="ad">
            <img src="<%=static_file_path%>images/ad.png">
        </div>
        <div class="page" id="pageBar">
            <%--<a title="Total record">
                <b id="total">41</b>
            </a>
                <b>1</b>
                <a href="/news/s/index_2.html">2</a>
                <a href="/news/s/index_2.html">&gt;</a>
                <a href="/news/s/index_2.html">&gt;&gt;</a>--%>
        </div>
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
            <%@include file="/WEB-INF/share/latestAndRankArticle.jsp"%>
        </div>
        <div class="visitors">
            <h3><p>最近访客</p></h3>
            <ul>

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