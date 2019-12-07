package com.SCC.action;


import java.io.IOException;

import com.SCC.model.Page;
import com.SCC.model.Result;
import com.opensymphony.xwork2.Action;

import com.SCC.model.User;
import com.SCC.model.coures;
import com.alibaba.fastjson.JSONObject;

import xsm520.service.UserService;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111L;
	
	UserService uService = new UserService();
	private Result result;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}

	/* ¼ì²éµÇÂ½ */
	public String isLogin() {
		Boolean islogin=(Boolean)this.request.getSession().getAttribute("isLogin");
		if(islogin==null||islogin.equals(false)) {
			User user = new User();
			user.setIsLogin(false);
			Result result = new Result();
			result.setSuccess(false);
			result.setData(user);
			setResult(result);
			System.out.print("\nÎ´µÇÂ¼");
			return Action.SUCCESS;
		}else {
			User user = new User();
			user.setIsLogin(true);
			user.setUsername((String)session.getAttribute("id"));
			user.setNickname((String)session.getAttribute("name"));
			user.setType((int)session.getAttribute("type"));
			Result result = new Result();
			result.setSuccess(true);
			result.setData(user);
			setResult(result);
			System.out.print("\nÒÑµÇÂ¼");
			return Action.SUCCESS;
		}
	}
	/* µÇÂ½ */
	public String login() throws IOException {
		JSONObject data = this.getRequestPostData(request);
		String username = data.getString("username");
		String password = data.getString("password");
		System.out.println(username+"³¢ÊÔµÇÂ½");
		setResult(uService.login(username, password, session));
		return Action.SUCCESS;
	}
	/* ×¢Ïú */
	public String logout() {
		this.session.removeAttribute("name");
		this.session.removeAttribute("id");
		this.session.removeAttribute("isLogin");
		this.session.removeAttribute("type");
		User user = new User();
		user.setIsLogin(false);
		Result result = new Result();
		result.setSuccess(true);
		result.setData(this.user);
		setResult(result);
		return Action.SUCCESS;
	}
	/* ¸öÈËÐÅÏ¢*/	
	public String info() throws IOException {
		String method = this.request.getMethod();
		JSONObject data = this.getRequestPostData(request);
		String idString = (String)this.request.getSession().getAttribute("id");
		int type = (int)this.request.getSession().getAttribute("type");
		if (method.equals("GET")) {
			setResult(uService.getInfo(idString,type,data));
		} else if(method.equals("POST")) {
			setResult(uService.updateInfo(idString,type,data));
		}
		return Action.SUCCESS;
	}
	/* ÐÞ¸ÄÃÜÂë*/	
	public String password() throws IOException {
		JSONObject data = this.getRequestPostData(request);
		String idString = (String)this.request.getSession().getAttribute("id");
		int type = (int)this.request.getSession().getAttribute("type");
		setResult(uService.password(idString,type,data));
		return Action.SUCCESS;
	}
}

/**
 * Write by Shi
 */