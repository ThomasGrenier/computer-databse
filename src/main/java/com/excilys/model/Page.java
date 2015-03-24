package com.excilys.model;

import java.util.LinkedList;
import java.util.List;

public class Page<T> {
	private List<T> list;
	private int nbResult;
	private int totalPage;
	private int currentPage;
	private int totalResult;
	
	public Page(int currentPage, int nbResult) {
		this.currentPage = currentPage;
		this.nbResult = nbResult;
		this.list = new LinkedList<T>();
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPages(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getNbResult() {
		return nbResult;
	}
	
	public void setNbResult(int nbResult) {
		this.nbResult = nbResult;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).toString());
		}
		sb.append("\n page : " + currentPage + " / " + totalPage + "\n");
		return sb.toString();
	}
}
