package com.transferobj;

public class PaginationTo extends com.baomidou.mybatisplus.plugins.pagination.Pagination{

	private static final long serialVersionUID = 1L;

	private int currentPage;
	
	private int pageSize;
	
	private int count;
	
	private int PageCount;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}
}
