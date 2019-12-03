package com.SCC.action;


import java.io.IOException;

import com.SCC.model.Result;
import com.opensymphony.xwork2.Action;

import com.SCC.model.User;
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
			user.setUsername((String)session.getAttribute("username"));
			user.setType((int)session.getAttribute("type"));
			Result result = new Result();
			result.setSuccess(true);
			result.setData(user);
			setResult(result);
			System.out.print("\nÒÑµÇÂ¼");
			return Action.SUCCESS;
		}
	}
	public String login() throws IOException {
		JSONObject data = this.getRequestPostData(request);
		String username = data.getString("username");
		String password = data.getString("password");
		System.out.println(username+"³¢ÊÔµÇÂ½");
		setResult(uService.login(username, password, session));
		return Action.SUCCESS;
	}
	public String logout() {
		this.session.removeAttribute("username");
		this.session.removeAttribute("nickname");
		this.session.removeAttribute("isLogin");
		User user = new User();
		user.setIsLogin(false);
		Result result = new Result();
		result.setSuccess(true);
		result.setData(this.user);
		setResult(result);
		return Action.SUCCESS;
	}
}
