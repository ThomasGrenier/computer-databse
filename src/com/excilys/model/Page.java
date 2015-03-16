package com.excilys.model;

import java.util.LinkedList;
import java.util.List;

public class Page<T> {
	private static final int NB_RESULT = 10;
	private List<T> list;
	private int totalPage;
	private int currentPage;
	
	public Page(int currentPage, List<T> list) {
		this.currentPage = currentPage;
		this.list = list;
		this.totalPage = (int) Math.floor(list.size() / NB_RESULT);
	}
	
	public List<T> getResults() {
		List<T> tmp = new LinkedList<T>();
		for (int i = (currentPage * NB_RESULT - 10); i < currentPage * NB_RESULT; i++) {
			tmp.add(list.get(i));
		}
		return tmp;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotalPages() {
		return totalPage;
	}
}
