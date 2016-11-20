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

    this.getHtmlContent = function () {
        var content = "<h3>" + this.title + "</h3>" +
            "<figure><img src=\"" + this.coverImage + "\"></figure>" +
            "<ul>" +
            "<p>" + this.summary + "</p>" +
            "<a title=\"/\" href=\"/\" target=\"_blank\" class=\"readmore\">阅读全文>></a>" +
            "</ul>" +
            "<p class=\"dateview\"><span>" + this.createTime + "</span><span>作者：" + this.owner.name + "</span><span>个人博客：[<a href=\"\">程序人生</a>]</span>" +
            "</p>";
        return content;
    }
}