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
	
	/* ��ȡ�γ��б� */
	public String query() {
		return Action.SUCCESS;
	}
	/* ѡ��γ�*/
	public String select() {
		return Action.SUCCESS;
	}
	/* ��ȡ�γ̳ɼ� */
	public String score() {
		return Action.SUCCESS;
	}
	/* ��ȡ�γ�ѧ���б� */
	public String list() {
		return Action.SUCCESS;
	}
	/* ¼��ѧ���ɼ� */
	public String mark() {
		return Action.SUCCESS;
	}
}
/**
 * Write by Xie
 */