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
	
	/* ��ȡ�γ��б� */
	public String query() {
		CourseService cService = new CourseService();
		String id = (String)this.request.getSession().getAttribute("id");
		Page p = Page.fromReq(request);
		setResult(cService.query(p, id));
		return Action.SUCCESS;
	}
	/* ѡ��γ�*/
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
	/* ȷ��ѡ��γ�*/
	public String comfirm() {
		CourseService cService = new CourseService();
		setResult(cService.comfirm((String)session.getAttribute("id")));
		return Action.SUCCESS;
	}
	/* ��ȡ�γ̳ɼ� */
	public String score() {
		return Action.SUCCESS;
	}
	/* ��ȡ�γ�ѧ���б� �ύ�γ�ID */
	public String list() throws IOException {
		CourseService cService = new CourseService();		
		JSONObject data = this.getRequestPostData(request);
		Page p = Page.fromReq(request);
		setResult(cService.listC((String)request.getParameter("id"),p));
		return Action.SUCCESS;
	}
	/* ¼��ѧ���ɼ� */
	public String addscore() throws IOException {
		CourseService cService = new CourseService();	
		JSONObject data = this.getRequestPostData(request);
		String cid = data.getString("course_id");
		int score = data.getInteger("score");
		String sid = data.getString("student_id");
		setResult(cService.mark(cid, sid, score,(String)session.getAttribute("id")));
		return Action.SUCCESS;
	}
	/* ��ȡѧ��רҵ */
	public String studep() throws IOException {
		CourseService cService = new CourseService();	
		if (request.getParameter("adv")!=null) {
			setResult(cService.stuDepAdv());
		}else {
			setResult(cService.stuDep());
		}
		return Action.SUCCESS;
	}

	/* ��ȡ�γ̷��� */
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