package com.SCC.action;

import com.SCC.model.Result;
import com.opensymphony.xwork2.Action;

import xsm520.service.AdminService;
import xsm520.service.UserService;

public class AdminAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11111L;

	AdminService aService = new AdminService();
	private Result result;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	/* 学生 */
	public String student() {
		String method = this.request.getMethod();
		if (method.equals("GET")) {
			setResult(aService.getStudent());
		} else if(method.equals("POST")) {
			setResult(aService.insStudent());
		} else if(method.equals("PUT")) {
			setResult(aService.updStudent());
		} else if(method.equals("DELETE")) {
			setResult(aService.delStudent());
		}
		return Action.SUCCESS;
		
	}
	/* 教师 */
	public String teacher() {
		String method = this.request.getMethod();
		if (method.equals("GET")) {
			setResult(aService.getTeacher());
		} else if(method.equals("POST")) {
			setResult(aService.insTeacher());
		} else if(method.equals("PUT")) {
			setResult(aService.updTeacher());
		} else if(method.equals("DELETE")) {
			setResult(aService.delTeacher());
		}
		return Action.SUCCESS;
	}
	/* 课程 */
	public String course() {
		String method = this.request.getMethod();
		if (method.equals("GET")) {
			setResult(aService.getCourse());
		} else if(method.equals("POST")) {
			setResult(aService.insCourse());
		} else if(method.equals("PUT")) {
			setResult(aService.updCourse());
		} else if(method.equals("DELETE")) {
			setResult(aService.delCourse());
		}
		return Action.SUCCESS;
	}
}
