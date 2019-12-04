package com.SCC.action;

import com.SCC.model.Result;
import com.opensymphony.xwork2.Action;
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
		return Action.SUCCESS;
	}
	/* 选择课程*/
	public String select() {
		return Action.SUCCESS;
	}
	/* 获取课程成绩 */
	public String score() {
		return Action.SUCCESS;
	}
	/* 获取课程学生列表 */
	public String list() {
		return Action.SUCCESS;
	}
	/* 录入学生成绩 */
	public String mark() {
		return Action.SUCCESS;
	}
}
/**
 * Write by Xie
 */