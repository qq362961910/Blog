/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.2
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
function Pagination(id, options) {

    var scope = this;
    var panel = $("#" + id);
    var opts = $.extend({
        items_per_page: 6,//每页显示的记录数
        num_display_items_left: 3,//坐半部分显示多少个页码
        num_display_items_right: 1,
        current_page: 1,//当前页
        total_items: 0,
        total_page: 0,
        link_to: "#",
        prev_text: "上一页",
        next_text: "下一页",
        ellipse_text: "...",
        prev_show_always: true,
        next_show_always: true,
        callback: function () {
            return false;
        }
    }, options || {});
    var current_page = opts.current_page;

    /**
     * This is the event handling function for the pagination links.
     * @param {int} page_id The new page number
     */
    this.pageSelected = function(page_id, evt) {
        if (page_id < 1 || page_id > opts.total_page) {
            return false;
        }
        current_page = page_id;
        scope.drawLinks();
        var continuePropagation = opts.callback(page_id, panel);
        if (!continuePropagation) {
            if (evt.stopPropagation) {
                evt.stopPropagation();
            }
            else {
                evt.cancelBubble = true;
            }
        }
        return continuePropagation;
    }
    this.getClickHandler = function (page_id) {
        return function (evt) {
            return scope.pageSelected(page_id, evt);
        }
    };
    this.appendItem = function (page_id, appendopts) {
        appendopts = $.extend({text: page_id, classes: ""}, appendopts || {});
        var link;
        if (page_id === current_page) {
            link = document.createElement("a");
            link.innerHTML = appendopts.text;
            $.addClass(link, "ds-current");
        }
        else {
            link = document.createElement("a");
            link.innerHTML = appendopts.text;
            $.registerEvent(link, "click", scope.getClickHandler(page_id));
            link.href = opts.link_to.replace(/__id__/, page_id);
        }
        if (appendopts.classes) {
            $.addClass(link, appendopts.classes);
        }
        panel.appendChild(link);
    };
    /**
     * This function inserts the pagination links into the container element
     */
    this.drawLinks = function () {
        panel.innerHTML = "";
        // var left = opt.num_display_items_left;
        // var total_page = opts.total_page;

        var beginDiv = document.createElement("div");
        beginDiv.setAttribute("class", "ds-border");
        panel.appendChild(beginDiv);

        // Generate "Previous"-Link
        if (opts.prev_text && (current_page > 0 || opts.prev_show_always)) {
            scope.appendItem(current_page - 1, {text: opts.prev_text, classes: "prev"});
        }
        // Generate interval links
        var magic = Math.ceil(current_page / opts.num_display_items_left);
        var lEnd = magic * opts.num_display_items_left;
        var lBegin = lEnd - opts.num_display_items_left + 1;
        if (lEnd > opts.total_page) {
            lEnd = opts.total_page;
        }
        for (; lBegin <= lEnd; lBegin++) {
            scope.appendItem(lBegin);
        }
        if (lEnd < opts.total_page && opts.num_display_items_right > 0) {
            var span = document.createElement("span");
            span.innerHTML = opts.ellipse_text;
            panel.appendChild(span);
            var rEnd = opts.total_page;
            var rBegin;
            if (opts.total_page - lEnd > opts.num_display_items_right) {
                rBegin = rEnd - opts.num_display_items_right + 1;
            }
            else {
                rBegin = rEnd - ( opts.total_page - lEnd ) + 1;
            }
            for ( ;rBegin <= rEnd; rBegin++ ) {
                scope.appendItem(rBegin);
            }
        }
        // Generate "Next"-Link
        if (opts.next_text && (current_page < opts.total_page - 1 || opts.next_show_always)) {
            scope.appendItem(current_page + 1, {text: opts.next_text, classes: "next"});
        }
    };

    this.selectPage = function (page_id) {
        opts.callback(page_id);
    };
    this.prevPage = function () {
        if (current_page > 1) {
            scope.pageSelected(current_page - 1);
            return true;
        }
        else {
            return false;
        }
    };
    this.nextPage = function () {
        if (current_page < opts.total_page - 1) {
            scope.pageSelected(current_page + 1);
            return true;
        }
        else {
            return false;
        }
    };
    // When all initialisation is done, draw the links
    scope.drawLinks();
}


