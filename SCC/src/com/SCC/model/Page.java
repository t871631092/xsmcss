package com.SCC.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.Parameter.Request;

public class Page {


	public Integer getPage() {
		return page==null?0:page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getSize() {
		return size==null?10:size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}

	public int getRow() {
		return getPage()*getSize();
	}
	private Integer page;
	private Integer size;
	
	public static Page fromReq(HttpServletRequest request) {
		System.out.println("page"+request.getParameter("page")+"size"+request.getParameter("size"));
		int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size")==null?"10":request.getParameter("size"));
		System.out.println("page"+page+"size"+size);
		Page page2 = new Page();
		page2.setPage(page);
		page2.setSize(size);
		return page2;
	}
	
	public static Page d() {
		Page page =  new Page();
		return page;
	}
}
