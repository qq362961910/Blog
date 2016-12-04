<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/base-page/header.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>【匆匆那些年】总结个人博客经历的这四年… - 一个站在web前端设计之路的女技术员个人博客网站</title>
    <meta name="keywords" content="个人博客"/>
    <meta name="description" content="【匆匆那些年】总结个人博客经历的这四年…"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">
    <link href="<%=static_file_path%>css/new.css" rel="stylesheet">
    <!-- 返回顶部调用 begin -->
    <link href="<%=static_file_path%>css/lrtk.css" rel="stylesheet"/>
    <link href="<%=static_file_path%>css/base.css" rel="stylesheet">`
    <link href="<%=static_file_path%>css/style.css" rel="stylesheet">
    <!-- 返回顶部调用 end-->
</head>
<body>
<header>
    <div id="logo"><a href="/"></a></div>
    <%@include file="/WEB-INF/base-page/navigation.jsp" %>
</header>
<article class="blogs">
    <h1 class="t_nav"><span>您当前的位置：<a href="/">首页</a>&nbsp;>&nbsp;<a href="/jstt/">学无止境</a>&nbsp;>&nbsp;<a
            href="/jstt/bj/">心得笔记</a></span><a href="/" class="n1">网站首页</a><a href="#" class="n2">心得笔记</a></h1>
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
        <!-- Duoshuo Comment BEGIN -->
        <div class="ds-thread" data-category="8" data-thread-key="740"
             data-title="【匆匆那些年】总结个人博客经历的这四年…" data-author-key="" data-url=""></div>
        <!-- Duoshuo Comment END -->
    </div>
    <aside class="right">

        <div class="rnav">
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
            <h3>
                <p>栏目<span>最新</span></p>
            </h3>
            <ul class="rank">
                <li><a href="/jstt/bj/2015-02-14/744.html" title="【郑重申明】本站只提供静态模板下载！"
                       target="_blank">【郑重申明】本站只提供静态模板下载！</a></li>
                <li><a href="/jstt/bj/2015-01-09/740.html" title="【匆匆那些年】总结个人博客经历的这四年…" target="_blank">【匆匆那些年】总结个人博客经历的这四年…</a>
                </li>
                <li><a href="/jstt/bj/2014-11-06/732.html" title="分享我的个人博客访问量如何做到IP从10到600的(图文)" target="_blank">分享我的个人博客访问量如何做到IP从10到600的(图文)</a>
                </li>
                <li><a href="/jstt/bj/2014-10-18/731.html" title="帝国cms常用标签调用方法总结（不得不收藏哦）" target="_blank">帝国cms常用标签调用方法总结（不得不收藏哦）</a>
                </li>
                <li><a href="/jstt/bj/2014-06-17/692.html" title="使用ASPCMS建站网站被黑" target="_blank">使用ASPCMS建站网站被黑</a>
                </li>
                <li><a href="/jstt/bj/2014-05-26/682.html" title="IE6到底哪里不好？你还继续用IE6吗？" target="_blank">IE6到底哪里不好？你还继续用IE6吗？</a>
                </li>
                <li><a href="/jstt/bj/2014-05-14/666.html" title="css技巧以及经验总结" target="_blank">css技巧以及经验总结</a></li>
                <li><a href="/jstt/bj/2014-05-14/665.html" title="IE常见bugs以及解决方案列表" target="_blank">IE常见bugs以及解决方案列表</a>
                </li>
            </ul>
            <h3 class="ph">
                <p>点击<span>排行</span></p>
            </h3>
            <ul class="paih">
                <li><a href="/jstt/bj/2013-07-28/530.html" title="如果要学习web前端开发，需要学习什么？" target="_blank">如果要学习web前端开发，需要学习什么？</a>
                </li>
                <li><a href="/jstt/bj/2015-01-09/740.html" title="【匆匆那些年】总结个人博客经历的这四年…" target="_blank">【匆匆那些年】总结个人博客经历的这四年…</a>
                </li>
                <li><a href="/jstt/bj/2014-11-06/732.html" title="分享我的个人博客访问量如何做到IP从10到600的(图文)" target="_blank">分享我的个人博客访问量如何做到IP从10到600的(图文)</a>
                </li>
                <li><a href="/jstt/bj/2014-10-18/731.html" title="帝国cms常用标签调用方法总结（不得不收藏哦）" target="_blank">帝国cms常用标签调用方法总结（不得不收藏哦）</a>
                </li>
                <li><a href="/jstt/bj/2015-02-14/744.html" title="【郑重申明】本站只提供静态模板下载！"
                       target="_blank">【郑重申明】本站只提供静态模板下载！</a></li>
            </ul>
        </div>
        <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone"
                                                                                          data-cmd="qzone"
                                                                                          title="分享到QQ空间"></a><a
                href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq"
                                                                                   data-cmd="tqq" title="分享到腾讯微博"></a><a
                href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_weixin"
                                                                                    data-cmd="weixin" title="分享到微信"></a>
        </div>
        <script>window._bd_share_config = {
            "common": {
                "bdSnsKey": {},
                "bdText": "",
                "bdMini": "2",
                "bdMiniList": false,
                "bdPic": "",
                "bdStyle": "1",
                "bdSize": "32"
            }, "share": {}
        };
        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
        <script type="text/javascript">
            var cpro_id = "u2063915";
            (window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id] = {
                at: "3",
                rsi0: "250",
                rsi1: "250",
                pat: "6",
                tn: "baiduCustNativeAD",
                rss1: "#FFFFFF",
                conBW: "1",
                adp: "1",
                ptt: "1",
                ptc: "%E7%8C%9C%E4%BD%A0%E6%84%9F%E5%85%B4%E8%B6%A3",
                ptFS: "14",
                ptFC: "#000000",
                ptBC: "#F2F2F2",
                titFF: "%E5%BE%AE%E8%BD%AF%E9%9B%85%E9%BB%91",
                titFS: "14",
                rss2: "#000000",
                titSU: "0",
                ptbg: "90",
                piw: "0",
                pih: "0",
                ptp: "0"
            }
        </script>
        <script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>

        <div class="blank"></div>
        <div class="visitors">
            <h3>
                <p>最近访客</p>
            </h3>
            <ul class="ds-recent-visitors" data-num-items="24"></ul>
            <script type="text/javascript">
                var duoshuoQuery = {short_name: "yangqq"};
                (function () {
                    var ds = document.createElement('script');
                    ds.type = 'text/javascript';
                    ds.async = true;
                    ds.src = 'http://static.duoshuo.com/embed.js';
                    ds.charset = 'UTF-8';
                    (document.getElementsByTagName('head')[0]
                    || document.getElementsByTagName('body')[0]).appendChild(ds);
                })();
            </script>
        </div>
    </aside>
</article>
<div id="tbox"><a id="togbook" href="/e/tool/gbook/?bid=1"></a> <a id="gotop" href="javascript:void(0)"
                                                                   style="display: block;"></a></div>
<footer>
    <p>Design by DanceSmile <a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a>
    </p>
</footer>
<script type="text/javascript" src="<%=static_file_path%>js/entity/article.js"></script>
<script type="text/javascript" src="<%=static_file_path%>js/common.js"></script>
<script type="text/javascript" src="<%=static_file_path%>js/articleDetail.js"></script>
<script type="text/javascript">
    init();
</script>
</body>
</html>