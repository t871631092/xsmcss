package com.SCC.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.SCC.model.User;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected HttpSession session = request.getSession();
	protected User user;
	protected boolean isLogin;

	// 解析请求的Json数据
	protected JSONObject getRequestPostData(HttpServletRequest request) throws IOException {

        BufferedReader streamReader = new BufferedReader( new 
        InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
             responseStrBuilder.append(inputStr);
        JSONObject json = JSONObject.parseObject(responseStrBuilder.toString());
		return json;
	}

	public BaseAction() {
		this.response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		this.response.setHeader("Access-Control-Allow-Credentials", "true");
		this.response.setHeader("Access-Control-Allow-Headers", "content-type");
		this.response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE");
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
		          SerializerFeature.WriteNullListAsEmpty,
		          SerializerFeature.WriteNullStringAsEmpty,
		          SerializerFeature.WriteNullBooleanAsFalse,
		          SerializerFeature.WriteNullNumberAsZero
		  );
	}
}
