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

	/* ����½ */
	public String isLogin() {
		Boolean islogin=(Boolean)this.request.getSession().getAttribute("isLogin");
		if(islogin==null||islogin.equals(false)) {
			User user = new User();
			user.setIsLogin(false);
			Result result = new Result();
			result.setSuccess(false);
			result.setData(user);
			setResult(result);
			System.out.print("\nδ��¼");
			return Action.SUCCESS;
		}else {
			User user = new User();
			user.setIsLogin(true);
			user.setUsername((String)session.getAttribute("username"));
			user.setNickname((String)session.getAttribute("nickname"));
			user.setType((int)session.getAttribute("type"));
			Result result = new Result();
			result.setSuccess(true);
			result.setData(user);
			setResult(result);
			System.out.print("\n�ѵ�¼");
			return Action.SUCCESS;
		}
	}
	/* ��½ */
	public String login() throws IOException {
		JSONObject data = this.getRequestPostData(request);
		String username = data.getString("username");
		String password = data.getString("password");
		System.out.println(username+"���Ե�½");
		setResult(uService.login(username, password, session));
		return Action.SUCCESS;
	}
	/* ע�� */
	public String logout() {
		this.session.removeAttribute("username");
		this.session.removeAttribute("nickname");
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
	/* ������Ϣ*/	
	public String info() throws IOException {
		String method = this.request.getMethod();
		JSONObject data = this.getRequestPostData(request);
		if (method.equals("GET")) {
			setResult(uService.getInfo(data));
		} else if(method.equals("POST")) {
			setResult(uService.updateInfo(data));
		}
		return Action.SUCCESS;
	}
	/* �޸�����*/	
	public String password() throws IOException {
		JSONObject data = this.getRequestPostData(request);
		setResult(uService.password((String)session.getAttribute("username"),(int)session.getAttribute("type"),data));
		return Action.SUCCESS;
	}
}

/**
 * Write by Shi
 */