/**
 * Created by amen on 17-10-12.
 */
function Pageable(pageable) {
    this.currentPage = pageable.currentPage;
    this.pageSize = pageable.pageSize;
    this.totalPage = pageable.totalPage;
    this.totalSize = pageable.totalSize;
    this.objectList = pageable.objectList;
}
