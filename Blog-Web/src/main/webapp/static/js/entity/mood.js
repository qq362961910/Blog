function Mood(id, coverImage, content, createTime) {
    this.id = id;
    this.coverImage = coverImage;
    this.content = content;
    this.createTime = createTime;

    /**
     * 闲言碎语页面列表item
     * */
    this.getMoodListItem = function () {
        var content = "<ul class='arrow_box'>" +
            "<div class='sy'>" +
            (this.coverImage ? "<img src='" + this.coverImage + "'>" : "") +
            "<p>" + this.content + "</p>" +
            "</div>" +
            "<span class='dateview'>" + this.createTime + "</span>" +
            "</ul>";
        return content;
    }


}
