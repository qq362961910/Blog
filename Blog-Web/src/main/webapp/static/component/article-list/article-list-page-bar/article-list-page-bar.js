/**
 * Created by amen on 17-10-16.
 */
function renderArticleListPageBar(total_items, items_per_page, total_page, current_page, callback){
    var opt = initPageBar(total_items,items_per_page, total_page, current_page, callback);
    new Pagination("articleListPageBar", opt);
}

function initPageBar(total_items,items_per_page, total_page, current_page, callback){
    var opt = {};
    if(total_items) {
        opt.total_items = total_items;
    }
    if (items_per_page) {
        opt.items_per_page = items_per_page;
    }
    if (total_page) {
        opt.total_page = total_page;
    }
    if (current_page) {
        opt.current_page = current_page;
    }
    opt.prev_text = "&lt;";
    opt.next_text = "&gt;";
    opt.callback = callback;
    return opt;
}
