/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
function Pagination(id, maxentries, options){

    var scope = this;
    var panel = $("#" + id);
    var opts = $.extend({
        items_per_page:6,//每页显示的记录数
        num_display_entries:5,//前半部分显示多少个页码
        current_page:0,//当前页
        num_edge_entries:1,//尾显示多少个页码
        link_to:"#",
        prev_text:"上一页",
        next_text:"下一页",
        ellipse_text:"...",
        prev_show_always:true,
        next_show_always:true,
        callback:function(){return false;}
    },options||{});
    var current_page = opts.current_page;

    /**
     * Calculate the maximum number of pages
     */
    this.numPages = function() {
        return Math.ceil(maxentries/opts.items_per_page);
    };

    /**
     * Calculate start and end point of pagination links depending on
     * current_page and num_display_entries.
     * @return {Array}
     */
    this.getInterval = function()  {
        var ne_half = Math.ceil(opts.num_display_entries/2);
        var np = scope.numPages();
        var upper_limit = np-opts.num_display_entries;
        var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
        var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
        return [start,end];
    };

    /**
     * This is the event handling function for the pagination links.
     * @param {int} page_id The new page number
     */
    function pageSelected(page_id, evt){
        current_page = page_id++;
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

    /**
     * This function inserts the pagination links into the container element
     */
    this.drawLinks = function() {
        panel.innerHTML = "";
        var interval = scope.getInterval();
        var np = scope.numPages();
        // This helper function returns a handler function that calls pageSelected with the right page_id
        var getClickHandler = function(page_id) {
            return function(evt){ return pageSelected(page_id,evt); }
        };
        // Helper function for generating a single link (or a span tag if it's the current page)
        var appendItem = function(page_id, appendopts) {
            page_id = page_id < 0 ? 0 : (page_id<np?page_id : np - 1); // Normalize page id to sane value
            appendopts = $.extend({text:page_id + 1, classes:""}, appendopts||{});
            var link;
            if(page_id === current_page){
                link = document.createElement("a");
                link.innerHTML = appendopts.text;
                $.addClass(link, "ds-current");
            }
            else {
                link = document.createElement("a");
                link.innerHTML = appendopts.text;
                $.registerEvent(page_id, "click", getClickHandler(page_id));
                link.href = opts.link_to.replace(/__id__/,page_id);
            }
            if (appendopts.classes) {
                $.addClass(link, appendopts.classes);
            }
            panel.appendChild(link);
        };

        var beginDiv = document.createElement("div");
        beginDiv.setAttribute("class", "ds-border");
        panel.appendChild(beginDiv);

        // Generate "Previous"-Link
        if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
            appendItem(current_page - 1,{text:opts.prev_text, classes:"prev"});
        }
        // Generate starting points
        if (interval[0] > 0 && opts.num_edge_entries > 0) {
            var end = Math.min(opts.num_edge_entries, interval[0]);
            for(var i=0; i<end; i++) {
                appendItem(i);
            }
            if(opts.num_edge_entries < interval[0] && opts.ellipse_text) {
                var span = document.createElement("a");
                span.innerHTML = opts.ellipse_text;
                panel.appendChild(span);
            }
        }
        // Generate interval links
        for(var i=interval[0]; i<interval[1]; i++) {
            appendItem(i);
        }
        // Generate ending points
        if (interval[1] < np && opts.num_edge_entries > 0) {
            if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text) {
                var span = document.createElement("span");
                span.innerHTML = opts.ellipse_text;
                panel.appendChild(span);
            }
            var begin = Math.max(np-opts.num_edge_entries, interval[1]);
            for(var i=begin; i<np; i++) {
                appendItem(i);
            }
        }
        // Generate "Next"-Link
        if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
            appendItem(current_page+1,{text:opts.next_text, classes:"next"});
        }
    };


    // Extract current_page from options
    // Create a sane value for maxentries and items_per_page
    maxentries = (!maxentries || maxentries < 0)?1:maxentries;
    opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
    // Store DOM element for easy access from all inner functions
    // Attach control functions to the DOM element
    scope.selectPage = function(page_id){ opts.callback(page_id);}
    scope.prevPage = function(){
        if (current_page > 0) {
            pageSelected(current_page - 1);
            return true;
        }
        else {
            return false;
        }
    };
    scope.nextPage = function(){
        if(current_page < numPages()-1) {
            pageSelected(current_page+1);
            return true;
        }
        else {
            return false;
        }
    };
    // When all initialisation is done, draw the links
    scope.drawLinks();
    // call callback function
//     opts.callback(current_page, this);

}


