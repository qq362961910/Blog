<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<nav class="topnav" id="topnav"><a href="/user/${username}/index"><span>首页</span><span class="en">Protal</span></a><a
        href="/user/${username}/about"><span>关于我</span><span class="en">About</span></a><a
        href="/user/${username}/articles"><span>文章笔记</span><span class="en">Life</span></a><a
        href="/user/${username}/moodlist"><span>碎言碎语</span><span class="en">Doing</span></a><a
        href="/user/${username}/share"><span>模板分享</span><span class="en">Share</span></a><a
        href="/user/${username}/knowledge"><span>学无止境</span><span class="en">Learn</span></a><a
        href="/user/${username}/book"><span>留言版</span><span
        class="en">Gustbook</span></a></nav>
</nav>
<script type="text/javascript">
    var username = ${username};
    username = username.toString();
</script>