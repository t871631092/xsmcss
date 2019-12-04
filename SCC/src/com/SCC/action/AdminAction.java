package com.SCC.action;

import java.io.IOException;

import com.SCC.model.Page;
import com.SCC.model.Result;
import com.SCC.model.coures;
import com.SCC.model.student;
import com.SCC.model.teacher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;

import xsm520.service.AdminService;

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
	public String student() throws IOException {
		String method = this.request.getMethod();
		JSONObject data = this.getRequestPostData(request);
		student s = (student)JSONObject.toJavaObject(data, student.class);
		Page p = Page.fromReq(request);
		if (method.equals("GET")) {
			setResult(aService.getStudent(p));
		} else if(method.equals("POST")) {
			setResult(aService.insStudent(s));
		} else if(method.equals("PUT")) {
			setResult(aService.updStudent());
		} else if(method.equals("DELETE")) {
			setResult(aService.delStudent(s));
		}
		return Action.SUCCESS;
		
	}
	/* 教师 */
	public String teacher() throws IOException {
		String method = this.request.getMethod();
		JSONObject data = this.getRequestPostData(request);
		teacher s = (teacher)JSONObject.toJavaObject(data, teacher.class);
		Page p = Page.fromReq(request);
		if (method.equals("GET")) {
			setResult(aService.getTeacher(p));
		} else if(method.equals("POST")) {
			setResult(aService.insTeacher(s));
		} else if(method.equals("PUT")) {
			setResult(aService.updTeacher());
		} else if(method.equals("DELETE")) {
			setResult(aService.delTeacher(s));
		}
		return Action.SUCCESS;
	}
	/* 课程 */
	public String course() throws IOException {
		String method = this.request.getMethod();
		JSONObject data = this.getRequestPostData(request);
		coures c = (coures)JSONObject.toJavaObject(data, coures.class);
		Page p = Page.fromReq(request);
		if (method.equals("GET")) {
			setResult(aService.getCourse(p));
		} else if(method.equals("POST")) {
			setResult(aService.insCourse(c));
		} else if(method.equals("PUT")) {
			setResult(aService.updCourse());
		} else if(method.equals("DELETE")) {
			setResult(aService.delCourse(c));
		}
		return Action.SUCCESS;
	}
}
