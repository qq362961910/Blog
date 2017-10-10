/**
 * Created by wxsk100 on 2016/11/15.
 */
function Article(id, createTime, content, keyworks, likeCount, readCount, summary, title, coverImage, owner) {

    this.id = id;
    this.createTime = createTime;
    this.content = content;
    this.keyworks = keyworks;
    this.likeCount = likeCount;
    this.readCount = readCount;
    this.summary = summary;
    this.title = title;
    this.coverImage = coverImage;
    this.owner = owner;

    this.getArticleDetailUrl = function () {
        return "/user/" + this.owner.username + "/article/" + this.id;
    }

    /**
     * 首页文章推荐
     * */
    this.getIndexRecommendHtmlContent = function () {
        var content = "<h3>" + this.title + "</h3>" +
            "<figure><img src='" + this.coverImage + "'></figure>" +
            "<ul>" +
            "<p>" + this.summary + "</p>" +
            "<a title='" + this.title + "' " +
            "href=" + this.getArticleDetailUrl() +
            " target='_blank' class='readmore'>阅读全文>></a>" +
            "</ul>" +
            "<p class='dateview'><span>" + this.createTime + "</span><span>作者：" + this.owner.username + "</span><span>个人博客：[<a href=''>程序人生</a>]</span>" +
            "</p>";
        return content;
    }

    /**
     * 首页最新文章
     * */
    this.getIndexLatestArticleHtmlContent = function () {
        var html = "<li><a href=" + this.getArticleDetailUrl() + " title='" + this.title + "' target='_blank'>" + this.title + "</a></li>";
        return html;
    }

    /**
     * 首页文章排行
     * */
    this.getIndexRankedArticleHtml = function () {
        var html = "<li><a href=" + this.getArticleDetailUrl() + " title='" + this.title + "' target='_blank'>" + this.title + "</a></li>";
        return html;
    }
    /**
     * 首页个人模板
     * */
    this.getIndexPersonalTemplateHtml = function () {
        var html = "<li><a href=" + this.getArticleDetailUrl() + " target='_blank'><img src='" + this.coverImage + "'></a><span>" + this.title + "</span></li>";
        return html;
    }

    /**
     * 文章列表页面
     * */
    this.getArticlesItemHtml = function () {
        var html = "<h2>" + this.title + "</h2>" +
            "<p class='dateview'><span>发布时间：" + this.createTime + "</span><span>作者：" + this.owner.username + "</span><span>分类：[<ahref='/news/life/'>程序人生</a>]</span></p>" +
            "<figure><img src='" + this.coverImage + "'></figure>" +
            "<ul class='nlist'>" +
            "<p>" + this.summary + "</p>" +
            "<a title='" + this.title + "'" +
            "href=" + this.getArticleDetailUrl() +
            " target='_blank' class='readmore'>阅读全文>></a>" +
            "</ul>" +
            "<div class='line'></div>";
        return html;
    }

    /**
     * html模板分享页面item
     * */
    this.getShareHtmlTemplateListItem = function () {
        var html = "<h2>" + this.title + "</h2>" +
            "<p class='dateview'><span>发布时间：" + this.createTime + "</span><span>作者：" + this.owner.username + "</span><span>分类：[<ahref='/news/life/'>程序人生</a>]</span></p>" +
            "<figure><img src='" + this.coverImage + "'></figure>" +
            "<ul class='nlist'>" +
            "<p>" + this.summary + "</p>" +
            "<a title='" + this.title + "'" +
            "href=" + this.getArticleDetailUrl() +
            " target='_blank' class='readmore'>阅读全文>></a>" +
            "</ul>" +
            "<div class='line'></div>";
        return html;
    }


    /**
     * 绑定html
     * */
    this.bindHtml = function (bindParam) {
        for (var key in bindParam) {
            var fieldName = bindParam[key].split(".");
            var showItem = this;
            for (var i = 0; i < fieldName.length; i++) {
                showItem = showItem[fieldName[i]];
            }
            $("#" + key).innerHTML = showItem;
        }
    }
}
