package com.SCC.action;

import com.SCC.dao.studentDao;
import com.SCC.dao.teacherDao;

import com.opensymphony.xwork2.ActionSupport;


public class SigninAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resultstudent;
	private String resultteacher;
	private int id;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResultstudent() {
		return resultstudent;
	}
	public void setResultstudent(String resultstudent) {
		this.resultstudent = resultstudent;
	}
	public String getResultteacher() {
		return resultteacher;
	}
	public void setResultteacher(String resultteacher) {
		this.resultteacher = resultteacher;
	}
	public String execute() {
		if(resultstudent.equals(id)||resultstudent.equals(password)) {
			return SUCCESS;
		}else if(resultteacher.equals(id)||resultteacher.equals(password)) {
			return INPUT;
		}else {
			return INPUT;
		}
	}
}
