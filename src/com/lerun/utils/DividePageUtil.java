package com.lerun.utils;

/**
 * 分页处理工具类
 * 
 * @author wschenyongyin
 * 
 */
public class DividePageUtil {

	private int pageSize;// 每页条数

	private int dataSize;// 数据的总条数
	private int currentPage;// 当前页
	private int pageCount;// 总页数

	public DividePageUtil(int pageSize, int dateSize, int currentPage) {

		this.pageSize = pageSize;

		this.dataSize = dateSize;

		this.setCurrentPage(currentPage);

	}

	public int getPageSize() {

		return pageSize;

	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;

	}

	public int getDataSize() {

		return dataSize;

	}

	public void setDataSize(int dataSize) {

		this.dataSize = dataSize;

	}

	public int getCurrentPage() {

		return currentPage;

	}

	// 设置当前页

	public void setCurrentPage(int currentPage) {

		int activePage = currentPage <= 0 ? 1 : currentPage;

		activePage = currentPage > getPageCount() ? getPageCount() : activePage;

		this.currentPage = activePage;

	}

	// 获取总页数

	public int getPageCount() {

		pageCount = dataSize / pageSize;

		if ((dataSize % pageSize) != 0) {

			pageCount++;

		}

		return dataSize == 0 ? 1 : pageCount;

	}

	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;

	}

	// 获取指定页的起始索引

	public int getFromIndex() {

		return (currentPage - 1) * pageSize;

	}

	// 获取指定页的结束索引

	public int getToIndex() {

		return (currentPage * pageSize);

	}
	/**
	 * 解决分页重复问题
	 * 
	 * 每页分20张图，第一页出来20张，然后把第20张的那张图片id取出来， 然后把这个id放到你分页的开始那里，再做下一次的分页， 1---20
	 * id--(id+20)
	 */
}
