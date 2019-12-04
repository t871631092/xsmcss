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
	public Result getInfo(JSONObject data) {
		User u = (User)JSONObject.toJavaObject(data, User.class);
		if (u.getUsername().isEmpty()) {
			return Result.bad("获取id失败");
		}
		if (!(u.getType()==1||u.getType()==2)) {
			return Result.bad("获取身份失败");
		}
		return uDao.getInfo(u.getType(),u.getUsername());
	}
	public Result updateInfo(JSONObject data) {
		User u = (User)JSONObject.toJavaObject(data, User.class);
		if (u.getUsername().isEmpty()) {
			return Result.bad("获取id失败");
		}
		if (!(u.getType()==1||u.getType()==2)) {
			return Result.bad("获取身份失败");
		}
		return uDao.updateInfo(u.getType(),data);
	}
	public Result password(String id,int type,JSONObject data) {
		User u = (User)JSONObject.toJavaObject(data, User.class);
		if (u.getUsername().isEmpty()) {
			return Result.bad("获取id失败");
		}
		if (!(u.getType()==1||u.getType()==2)) {
			return Result.bad("获取身份失败");
		}
		return uDao.updateInfo(type,id,u.getOldPassword(),u.getPassword());
	}
}
