package com.SCC.dao;

import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import com.SCC.model.Result;
import com.SCC.model.User;



public class UserDAO {
	Dbutil db = null;
	String sql = null;
	public Result login(String username, String password, HttpSession session) {
		Result result = new Result();
		try {
			sql = "select id as username,name,password,3 as type from user "
					+ "UNION "
					+ "select id as username,name,password,1 as type from student "
					+ "UNION "
					+ "select id as username,name,password,2 as type from teacher "
					+ " where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, username);
			System.out.print(ret.getRow());
			ret.last(); //移到最后一行     
			int rowCount = ret.getRow(); //得到当前行号，也就是记录数     
			ret.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置
			if (rowCount == 0) {
				db.close();
				System.out.print("登陆失败");
				session.setAttribute("isLogin", false);
				result.setSuccess(false);
				result.setMsg("用户不存在");
				return result;
			} else {
				if (ret.next()) {
					if (ret.getString("password").equals(password)) {
						int type = ret.getInt("type");
						User user = new User();
						user.setIsLogin(true);
						user.setType(type);
						user.setUsername(username);
						db.close();
						System.out.print("登陆成功");
						session.setAttribute("username", user.getUsername());
						session.setAttribute("type", type);
						session.setAttribute("isLogin", true);
						result.setSuccess(true);
						result.setData(user);
						return result;
					} else {
						db.close();
						System.out.print("登陆失败");
						session.setAttribute("isLogin", false);
						result.setSuccess(false);
						result.setMsg("密码错误");
						return result;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
		}
		result.setSuccess(false);
		result.setMsg("未知错误");
		return result;
	}
}
