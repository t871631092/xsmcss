package xsm520.service;

import javax.servlet.http.HttpSession;

import com.SCC.dao.UserDAO;
import com.SCC.model.Result;
import com.SCC.model.User;
import com.alibaba.fastjson.JSONObject;

import xsm520.interceptor.isloginInterceptor;


public class UserService {
	UserDAO uDao = new UserDAO();
	public Result login(String username,String password,HttpSession session) {
		return uDao.login(username,password,session);
	}
	public Result getInfo(String id,int type,JSONObject data) {
		User u = (User)JSONObject.toJavaObject(data, User.class);
		if (id.isEmpty()) {
			return Result.bad("��ȡidʧ��");
		}
		if (!(type==1||type==2)) {
			return Result.bad("��ȡ���ʧ��");
		}
		return uDao.getInfo(type,id);
	}
	public Result updateInfo(String id,int type,JSONObject data) {
		User u = (User)JSONObject.toJavaObject(data, User.class);
		if (id.isEmpty()) {
			return Result.bad("��ȡidʧ��");
		}
		if (!(type==1||type==2)) {
			return Result.bad("��ȡ���ʧ��");
		}
		return uDao.updateInfo(type,data);
	}
	public Result password(String id,int type,JSONObject data) {
		User u = (User)JSONObject.toJavaObject(data, User.class);
		System.out.println(id);
		if (id==null) {
			return Result.bad("��ȡidʧ��");
		}
		if (!(type==1||type==2)) {
			return Result.bad("��ȡ���ʧ��");
		}
		return uDao.password(type,id,u.getPassword(),u.getOldPassword());
	}
}
