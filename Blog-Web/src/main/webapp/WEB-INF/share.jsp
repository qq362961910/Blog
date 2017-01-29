<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>模板分享</title>
    <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青"/>
    <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/share.css" rel="stylesheet">
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
<article class="blogs">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="newblog left" id="shareHtmlTemplateDiv">
        <%--<h2>仿新浪博客风格・梅――古典个人博客模板</h2>
        <p class="dateview"><span>发布时间：2013-11-04</span><span>作者：杨青</span><span>模板分类：[<a href="/news/life/">个人博客模板</a>]</span>
        </p>
        <figure><img src="<%=static_file_path%>images/01.jpg"></figure>
        <ul class="nlist">
            <p>界面设计非原创，出自新浪博客风格・梅作者水云心。在原来的设计上增加了梅花飘落的flash动画。原设计的边界阴影部分用图片实现，本例采用css3新的规则属性，box-shadow</p>
            <a title="/" href="/" target="_blank" class="readmore">详细信息>></a>
        </ul>
        <div class="line"></div>--%>
    </div>
    <div class="blank"></div>
    <div class="ad">
        <img src="<%=static_file_path%>images/ad.png">
    </div>
    <div class="page" id="pageBar"></div>
    <aside class="right">
        <div class="rnav">
            <h2>栏目导航</h2>
            <ul>
                <li><a href="/download/" target="_blank">个人博客模板</a></li>
                <li><a href="/newsfree/" target="_blank">国外Html5模板</a></li>
                <li><a href="/download/" target="_blank">企业网站模板</a></li>
                <li><a href="/newsfree/" target="_blank">作品展示</a></li>
            </ul>
        </div>
        <div class="news">
            <h3>
                <p>最新<span>模板</span></p>
            </h3>
            <ul class="rank">
                <li><a href="/" title="Column 三栏布局 个人网站模板" target="_blank">Column 三栏布局 个人网站模板</a></li>
                <li><a href="/" title="with love for you 个人网站模板" target="_blank">with love for you 个人网站模板</a></li>
                <li><a href="/" title="免费收录网站搜索引擎登录口大全" target="_blank">免费收录网站搜索引擎登录口大全</a></li>
                <li><a href="/" title="做网站到底需要什么?" target="_blank">做网站到底需要什么?</a></li>
                <li><a href="/" title="企业做网站具体流程步骤" target="_blank">企业做网站具体流程步骤</a></li>
                <li><a href="/" title="建站流程篇――教你如何快速学会做网站" target="_blank">建站流程篇――教你如何快速学会做网站</a></li>
                <li><a href="/" title="box-shadow 阴影右下脚折边效果" target="_blank">box-shadow 阴影右下脚折边效果</a></li>
                <li><a href="/" title="打雷时室内、户外应该需要注意什么" target="_blank">打雷时室内、户外应该需要注意什么</a></li>
            </ul>
            <h3 class="ph">
                <p>点击<span>排行</span></p>
            </h3>
            <ul class="paih">
                <li><a href="/" title="Column 三栏布局 个人网站模板" target="_blank">Column 三栏布局 个人网站模板</a></li>
                <li><a href="/" title="withlove for you 个人网站模板" target="_blank">with love for you 个人网站模板</a></li>
                <li><a href="/" title="免费收录网站搜索引擎登录口大全" target="_blank">免费收录网站搜索引擎登录口大全</a></li>
                <li><a href="/" title="做网站到底需要什么?" target="_blank">做网站到底需要什么?</a></li>
                <li><a href="/" title="企业做网站具体流程步骤" target="_blank">企业做网站具体流程步骤</a></li>
            </ul>
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
</body>
<script type="text/javascript" src="<%=static_file_path%>js/share.js"></script>
<script type="text/javascript">
    initShareUserHtmlTemplate();
</script>
</html>