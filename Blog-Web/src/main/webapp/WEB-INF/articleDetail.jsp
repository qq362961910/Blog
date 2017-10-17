<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文章详情</title>
    <meta name="keywords" content="个人博客"/>
    <meta name="description" content="【匆匆那些年】总结个人博客经历的这四年…"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/style.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/new.css" rel="stylesheet">
    <link href="/static/component/nav-bar/nav-bar.css" rel="stylesheet"/>
    <link href="/static/component/banner/banner.css" rel="stylesheet"/>
    <!-- 返回顶部调用 end-->
    <script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
    <script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
    <%--login--%>
    <link href="<%=static_file_path%>component/login/login.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/static/component/nav-bar/nav-bar.jsp" %>
</header>
<article class="blogs">
    <%@include file="/WEB-INF/base-page/headerTab.jsp" %>
    <div class="index_about">
        <h2 class="c_titile" id="title"></h2>

        <p class="box_c">
            <span class="d_time">发布时间：
                <span id="publish_time"></span>
            </span>
            <span>编辑:<span id="article_owner"></span></span>
            <span>阅读（<span id="read_count"></span>）</span>
        </p>
        <ul class="infos">
            <div id="article_content"></div>
            <p align="center" class="pageLink"></p>
        </ul>
        <div class="keybq">
            <p><span>关键字词</span>：<span id="keywords"></span></p>
        </div>
        <div class="nextinfo">
            <p>上一篇：<a href='/jstt/bj/2014-11-06/732.html'>分享我的个人博客访问量如何做到IP从10到600的(图文)</a></p>

            <p>下一篇：<a href='/jstt/bj/2015-02-14/744.html'>【郑重申明】本站只提供静态模板下载！</a></p>
        </div>
        <div class="otherlink">
            <h2>相关文章</h2>
            <ul>
                <li><a href="/jstt/web/2015-01-01/739.html" title=" 2014年度优秀个人博客排名公布"> 2014年度优秀个人博客排名公布</a></li>
                <li><a href="/jstt/web/2014-12-18/736.html" title="2014年度优秀个人博客评选活动">2014年度优秀个人博客评选活动</a></li>
                <li><a href="/jstt/bj/2014-11-06/732.html" title="分享我的个人博客访问量如何做到IP从10到600的(图文)">分享我的个人博客访问量如何做到IP从10到600的(图文)</a>
                </li>
                <li><a href="/download/div/2014-06-13/689.html" title="个人博客模板《世界杯来袭》">个人博客模板《世界杯来袭》</a></li>
                <li><a href="/download/div/2014-04-17/661.html" title="黑色Html5个人博客模板主题《如影随形》">黑色Html5个人博客模板主题《如影随形》</a>
                </li>
                <li><a href="/download/div/2014-02-19/649.html" title=" 个人博客模板（2014草根寻梦）"> 个人博客模板（2014草根寻梦）</a></li>
                <li><a href="/download/div/2014-01-22/647.html" title="响应式个人博客模板（蓝色清新）">响应式个人博客模板（蓝色清新）</a></li>
                <li><a href="/jstt/web/2014-01-18/644.html" title="我的个人博客之——阿里云空间选择">我的个人博客之——阿里云空间选择</a></li>
                <li><a href="/news/s/2014-01-08/635.html" title="个人博客从简不繁">个人博客从简不繁</a></li>
                <li><a href="/download/div/2013-11-28/626.html" title="仿新浪博客风格·梅——古典个人博客模板">仿新浪博客风格·梅——古典个人博客模板</a></li>
            </ul>
        </div>
        <div class="blank"></div>
        <div class="ad">
            <script type="text/javascript">
                var cpro_id = "u2064486";
                (window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id] = {
                    at: "3",
                    rsi0: "700",
                    rsi1: "250",
                    pat: "6",
                    tn: "baiduCustNativeAD",
                    rss1: "#FFFFFF",
                    conBW: "1",
                    adp: "1",
                    ptt: "0",
                    titFF: "%E5%BE%AE%E8%BD%AF%E9%9B%85%E9%BB%91",
                    titFS: "",
                    rss2: "#000000",
                    titSU: "0",
                    ptbg: "90",
                    piw: "0",
                    pih: "0",
                    ptp: "0"
                }
            </script>
            <script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
        </div>
    </div>
    <aside class="right">
        <div class="rnav">
            <h2>栏目导航</h2>
            <ul>
                <li><a href="javascript:void(0);" target="_blank">日记</a></li>
                <li><a href="javascript:void(0);" target="_blank">程序人生</a></li>
                <li><a href="javascript:void(0);" target="_blank">欣赏</a></li>
                <li><a href="javascript:void(0);" target="_blank">短信祝福</a></li>
            </ul>
        </div>
        <div class="rnavs">
            <h2>栏目导航</h2>
            <ul>
                <li><a href="/jstt/bj/">心得笔记</a></li>
                <li><a href="http://www.ip3q.com/">IP查询</a></li>
                <li><a href="/jstt/css3/">CSS3|Html5</a></li>
                <li><a href="/jstt/web/">网站建设</a></li>
                <li><a href="/news/jsex/">JS经典实例</a></li>
                <li><a href="/jstt/t/">推荐工具</a></li>
            </ul>
        </div>
        <div class="blank"></div>
        <div class="news">
            <%@include file="/static/component/latest-article/latest-article.jsp" %>
            <%@include file="/static/component/ranked-article/ranked-article.jsp" %>
        </div>
        <div class="bdsharebuttonbox">
            <a href="#" class="bds_more" data-cmd="more"></a>
            <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
            <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
            <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
            <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
            <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
        </div>
        <div class="blank"></div>
        <div class="visitors">
            <h3>
                <p>最近访客</p>
            </h3>
            <ul class="ds-recent-visitors" data-num-items="24"></ul>
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
        </div>
    </aside>
</article>
<div id="tbox">
    <a id="togbook" href="/e/tool/gbook/?bid=1"></a>
    <a id="gotop" href="javascript:void(0)" style="display: block;"></a>
</div>
<%@include file="/static/component/login/login.jsp"%>
<footer>
    <p>Design by DanceSmile <a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a>
    </p>
</footer>
<script type="text/javascript" src="<%=static_file_path%>js/articleDetail.js"></script>
<script type="text/javascript" src="<%=static_file_path%>component/login/login.js"></script>
</body>
</html>
