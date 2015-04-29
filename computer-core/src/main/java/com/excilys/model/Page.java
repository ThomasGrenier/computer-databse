package com.excilys.model;

import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Page.
 *
 * @param <T> the generic type
 */
public class Page<T> {
	
	/** The list. */
	private List<T> list;
	
	/** The number of result. */
	private int nbResult;
	
	/** The total page. */
	private int totalPage;
	
	/** The current page. */
	private int currentPage;
	
	/** The total result. */
	private int totalResult;
	
	/** The search by. */
	private String searchBy;
	
	/** The option. */
	private String option;

	/** The order by. */
	private String orderBy;

	/**
	 * Instantiates a new page.
	 *
	 * @param currentPage the current page
	 * @param nbResult the nb result
	 * @param searchBy the search by
	 */
	public Page(int currentPage, int nbResult, String searchBy) {
		this.currentPage = currentPage;
		this.nbResult = nbResult;
		this.list = new LinkedList<T>();
		this.searchBy = searchBy;
		this.orderBy = "id";
		this.option = "DESC";
	}
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
	
	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * Gets the total page.
	 *
	 * @return the total page
	 */
	public int getTotalPage() {
		return totalPage;
	}
	
	/**
	 * Sets the total pages.
	 *
	 * @param totalPage the new total pages
	 */
	public void setTotalPages(int totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * Gets the nb result.
	 *
	 * @return the nb result
	 */
	public int getNbResult() {
		return nbResult;
	}
	
	/**
	 * Sets the nb result.
	 *
	 * @param nbResult the new nb result
	 */
	public void setNbResult(int nbResult) {
		this.nbResult = nbResult;
	}
	
	/**
	 * Gets the total result.
	 *
	 * @return the total result
	 */
	public int getTotalResult() {
		return totalResult;
	}
	
	/**
	 * Sets the total result.
	 *
	 * @param totalResult the new total result
	 */
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	/**
	 * Gets the search by.
	 *
	 * @return the search by
	 */
	public String getSearchBy() {
		return searchBy;
	}

	/**
	 * Sets the search by.
	 *
	 * @param searchBy the new search by
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
	/**
	 * Gets the option.
	 *
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Sets the option.
	 *
	 * @param option the new option
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).toString());
		}
		sb.append("\n page : " + currentPage + " / " + totalPage + "\n");
		return sb.toString();
	}
}
