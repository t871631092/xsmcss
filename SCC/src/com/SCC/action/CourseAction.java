package com.SCC.action;

import java.io.IOException;

import com.SCC.model.Page;
import com.SCC.model.Result;
import com.SCC.model.student;
import com.SCC.model.teacher;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;

import xsm520.service.CourseService;
public class CourseAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Result result;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	
	/* 获取课程列表 */
	public String query() {
		CourseService cService = new CourseService();
		String id = (String)this.request.getSession().getAttribute("id");
		Page p = Page.fromReq(request);
		setResult(cService.query(p, id));
		return Action.SUCCESS;
	}
	/* 选择课程*/
	public String select() throws IOException {
		CourseService cService = new CourseService();
		String method = this.request.getMethod();
		JSONObject data = this.getRequestPostData(request);
		String id = data.getString("id");
		String uid = (String)session.getAttribute("id");
		if(method.equals("POST")) {
			setResult(cService.selectCourse(id, uid));
		}else if(method.equals("DELETE")) {
			setResult(cService.delCourse(id, uid));
		}
		return Action.SUCCESS;
	}
	/* 确认选择课程*/
	public String comfirm() {
		CourseService cService = new CourseService();
		setResult(cService.comfirm((String)session.getAttribute("id")));
		return Action.SUCCESS;
	}
	/* 获取课程成绩 */
	public String score() {
		return Action.SUCCESS;
	}
	/* 获取课程学生列表 提交课程ID */
	public String list() throws IOException {
		CourseService cService = new CourseService();		
		JSONObject data = this.getRequestPostData(request);
		Page p = Page.fromReq(request);
		setResult(cService.listC((String)request.getParameter("id"),p));
		return Action.SUCCESS;
	}
	/* 录入学生成绩 */
	public String addscore() throws IOException {
		CourseService cService = new CourseService();	
		JSONObject data = this.getRequestPostData(request);
		String cid = data.getString("course_id");
		int score = data.getInteger("score");
		String sid = data.getString("student_id");
		setResult(cService.mark(cid, sid, score,(String)session.getAttribute("id")));
		return Action.SUCCESS;
	}
	/* 获取学生专业 */
	public String studep() throws IOException {
		CourseService cService = new CourseService();	
		if (request.getParameter("adv")!=null) {
			setResult(cService.stuDepAdv());
		}else {
			setResult(cService.stuDep());
		}
		return Action.SUCCESS;
	}

	/* 获取课程分类 */
	public String couCate() throws IOException {
		CourseService cService = new CourseService();	
		if (request.getParameter("adv")!=null) {
			setResult(cService.couCateAdv());
		}else {
			setResult(cService.couCate());
		}
		return Action.SUCCESS;
	}
	
}
/**
 * Write by Xie
 */