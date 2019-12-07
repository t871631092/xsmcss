package com.SCC.action;

import com.SCC.model.Result;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction extends BaseAction {
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
	

	public String noLogin() {
		setResult(Result.bad("Î´µÇÂ¼"));
		return Action.SUCCESS;
	}
}
