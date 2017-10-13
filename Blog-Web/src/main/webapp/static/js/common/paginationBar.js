/**
 *
 * @param show_elem_id 分页展示元素id
 * @param callback	点击页码的js回调方法
 * @param total_items	总记录数
 * @param items_per_page 一页显示的记录数
 * @param current_page	当前页
 */
function paginationBar(show_elem_id, total_items, items_per_page, total_page, current_page, callback){
	var opt = initPageBar(total_items,items_per_page, total_page, current_page, callback);
    new Pagination(show_elem_id, opt);
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
	opt.callback = callback;
	return opt;
}
