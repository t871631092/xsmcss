package com.SCC.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Result {
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	boolean success;

	Object data;
	String msg;
	int count;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public static Result ok(Object obj,int count,String msg) {
		Result result = new Result();
		result.setData(obj);
		result.setCount(count);
		result.setMsg(msg);
		result.setSuccess(true);
		return result;
	}
	public static Result bad(String msg) {
		Result result = new Result();
		result.setMsg(msg);
		result.setSuccess(false);
		return result;
	}
}
