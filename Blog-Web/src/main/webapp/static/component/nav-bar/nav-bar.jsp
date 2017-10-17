<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<div>
    <a class="logo" href="javascript:void(0);"></a>
</div>
<div class="login-div login-icon" title="登录" id="login"></div>
<nav id="nav-bar">
    <a href="/user/${username}/index">
        <span>首页</span>
        <span class="en">Protal</span>
    </a>
    <a href="/user/${username}/about">
        <span>关于我</span>
        <span class="en">About</span>
    </a>
    <a href="/user/${username}/articles">
        <span>文章笔记</span>
        <span class="en">Life</span>
    </a>
    <a href="/user/${username}/mood_list">
        <span>碎言碎语</span>
        <span class="en">Doing</span>
    </a>
    <a href="/user/${username}/template_share">
        <span>模板分享</span>
        <span class="en">templateShare</span>
    </a>
    <a href="/user/${username}/knowledge">
        <span>学无止境</span>
        <span class="en">Learn</span>
    </a>
    <a href="/user/${username}/book">
        <span>留言版</span>
        <span class="en">GuestBook</span>
    </a>
</nav>
<script type="text/javascript" src="/static/component/nav-bar/nav-bar.js"></script>
<script type="text/javascript">
    var username = ${username};
    username = username.toString();
</script>
