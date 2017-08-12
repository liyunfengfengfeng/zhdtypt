package com.newage.iep.util;

import java.util.List;

/**
 * 分页支持对象,包含当前页面数据和相关信息 如：页面总数、起始页号、前后页等信息 注：页号起始位置为 1、数据起始位置为 0
 */
public class PaginationSupport {
	public final static int DEFAULT_PAGE_SIZE = 10;

	private int pageSize = DEFAULT_PAGE_SIZE;// 每页的记录数

	private List records;// 记录集合 一般为List或 Iterator

	private int totalCount = 0;// 记录总数

	private int pageIndex = 1;// 当前页号 默认为1

	private int startOfPage = 0;// 每页起始条数

	private int endOfpage = 0; // 每页结束条数

	/**
	 * 取得当前页起始条数
	 * 
	 * @return 每页起始条数
	 */
	public int getStartOfPage() {
		this.startOfPage = getStartOfPage(pageIndex, DEFAULT_PAGE_SIZE) + 1;
		return startOfPage;
	}

	/**
	 * 取得当前页结束条数
	 * 
	 * @return 每页结束条数
	 */
	public int getEndOfPage() {
		if (getPageIndex() < getTotalPageCount()) {
			this.endOfpage = getStartOfPage() + pageSize - 1;
		} else {
			if (getTotalCount() % pageSize > 0) {
				this.endOfpage = getStartOfPage() + getTotalCount() % pageSize - 1;
			} else
				this.endOfpage = getStartOfPage() + pageSize - 1;
		}
		return this.endOfpage;
	}

	private int totalPageCount = 0;// 总页数

	/**
	 * <p>
	 * 初始化分页支持对象
	 * <p>
	 * 
	 * @param records
	 *            当前页面结果集
	 * @param totalCount
	 *            数据库中总记录条数
	 */
	public PaginationSupport(List records, int totalCount) {
		this.setRecords(records);
		this.setTotalCount(totalCount);
		this.setPageIndex(1);
		buildTotalPageCount(totalCount, DEFAULT_PAGE_SIZE);
	}

	/**
	 * <p>
	 * 初始化分页支持对象
	 * <p>
	 * 
	 * @param records
	 *            当前页面结果集
	 * @param totalCount
	 *            数据库中总记录条数
	 * @param pageIndex
	 *            当前页号码
	 */
	public PaginationSupport(List records, int totalCount, int pageIndex) {
		this.setRecords(records);
		this.setTotalCount(totalCount);
		this.setPageIndex(pageIndex);
		buildTotalPageCount(totalCount, DEFAULT_PAGE_SIZE);
	}

	/**
	 * <p>
	 * 初始化分页支持对象
	 * <p>
	 * 
	 * @param records
	 *            当前页面结果集
	 * @param totalCount
	 *            数据库中总记录条数
	 * @param pageIndex
	 *            当前页号码
	 * @param pageSize
	 *            每页的记录数
	 */
	public PaginationSupport(List records, int totalCount, int pageIndex, int pageSize) {
		buildTotalPageCount(totalCount, pageSize);
		this.setRecords(records);
		this.setTotalCount(totalCount);
		this.setPageIndex(pageIndex);
		this.setPageSize(pageSize);

	}

	/**
	 * 生成页面总数
	 * 
	 * @param totalCount
	 *            数据库中总记录条数
	 * @param pageSize
	 *            每页的记录数
	 */
	protected void buildTotalPageCount(int totalCount, int pageSize) {
		this.totalPageCount = totalCount / pageSize;
		this.totalPageCount = (totalCount > this.totalPageCount * pageSize) ? this.totalPageCount + 1 : totalPageCount;
	}

	/**
	 * 获取总页数
	 * 
	 * @return int 总页数
	 */
	public int getTotalPageCount() {

		return this.totalPageCount;
	}

	/**
	 * 获取总页数 此方法分页页面专用
	 * 
	 * @return int 总页数
	 */
	public int getPageCount() {
		if (this.pageIndex == 1 && this.totalCount == 0) {
			return 1;
		} else {
			return this.totalPageCount;
		}
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageIndex) {
		return getStartOfPage(pageIndex, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageIndex
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据的位置
	 */
	public static int getStartOfPage(int pageIndex, int pageSize) {
		return (pageIndex - 1 > 0 ? (pageIndex - 1) * pageSize : 0);
	}

	/**
	 * <p>
	 * 返回下一页 页号 如果没有后一页 则返回最后一页页号
	 * <p>
	 * 
	 * @return int下一页 页号
	 */
	public int getNextIndex() {
		return getPageIndex() - 1 < 1 ? 1 : getPageIndex() - 1;
	}

	/**
	 * <p>
	 * 获取上一页页号如果没有上一页 则返回当前页号(也就是 第一页)
	 * <p>
	 * 
	 * @return int 上一页页号
	 */
	public int getPreviousIndex() {
		return getPageIndex() + 1 > getTotalPageCount() ? getTotalPageCount() : getPageIndex() + 1;
	}

	// -------------------------------------------------------------------------
	// setter an getter
	// -------------------------------------------------------------------------
	/**
	 * 获取每页记录数
	 * 
	 * @return int 每页记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 *            每页记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页面结果集
	 * 
	 * @return List 集合
	 */
	public List getRecords() {
		return records;
	}

	/**
	 * 设置当前页面结果集
	 * 
	 * @param records
	 *            List
	 */
	public void setRecords(List records) {
		this.records = records;
	}

	/**
	 * 获取当前页面号码
	 * 
	 * @return Int 当前页号
	 */
	public int getPageIndex() {
		// System.out.println("当前页码"+this.pageIndex);
		return this.pageIndex;
	}

	/**
	 * 设置当前页号
	 * 
	 * @param pageIndex
	 *            int 页号
	 */
	public void setPageIndex(int pageIndex) {
		if (pageIndex <= 1)
			this.pageIndex = 1;

		else {
			this.pageIndex = getTotalPageCount() > pageIndex ? pageIndex : getTotalPageCount();
		}
	}

	/**
	 * 获取记录总数
	 * 
	 * @return int 记录总数
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置记录总数
	 * 
	 * @param totalCount
	 *            int 记录总数
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
