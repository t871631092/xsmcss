package com.SCC.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.json.JSONResult;

import com.SCC.model.Page;
import com.SCC.model.Result;
import com.SCC.model.User;
import com.SCC.model.coures;
import com.SCC.model.student;
import com.SCC.model.teacher;
import com.alibaba.fastjson.JSONObject;

import xsm520.interceptor.isloginInterceptor;

public class UserDAO {
	Dbutil db = null;
	String sql = null;

	public Result login(String username, String password, HttpSession session) {
		Result result = new Result();
		try {
			sql = "select id as username,name as nickname,password,3 as type from user " + "UNION "
					+ "select id as username,name as nickname,password,1 as type from student " + "UNION "
					+ "select id as username,name as nickname,password,2 as type from teacher " + " where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, username);
			System.out.print(ret.getRow());
			ret.last(); // 移到最后一行
			int rowCount = ret.getRow(); // 得到当前行号，也就是记录数
			ret.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置
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
						user.setNickname(ret.getString("nickname"));
						db.close();
						System.out.print("登陆成功");
						session.setAttribute("id", user.getUsername());
						session.setAttribute("name", user.getNickname());
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

	public Result getInfo(int type, String id) {
		Result result = new Result();
		if (type == 1) {
			try {
				sql = "select * from student where id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, id);
				List dataList = Dbutil.populate(ret, student.class);
				result.setCount(dataList.size());
				result.setData(dataList);
				result.setSuccess(true);
				System.out.println("student表 获取数据(条): " + dataList.size());
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}
		} else if (type == 2) {
			try {
				sql = "select * from teacher where id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, id);
				List dataList = Dbutil.populate(ret, teacher.class);
				result.setCount(dataList.size());
				result.setData(dataList);
				result.setSuccess(true);
				System.out.println("teacher表 获取数据(条): " + dataList.size());
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}
		}
		return Result.bad("未知错误");
	}

	public Result updateInfo(int type, JSONObject data) {
		if (type == 1) {
			student u = (student) JSONObject.toJavaObject(data, student.class);
			// 检查id重复
			try {
				sql = "select count(*) from student where id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, u.getId());
				if (ret.next()) {
					if (ret.getInt(1) == 0) {
						db.close();
						return Result.bad("找不到学生ID");
					} else {
						db.close();
					}
				}
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}
			// 修改个人信息
			try {
				sql = "UPDATE student set name =?,major=?,description=? where id = ?";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, u.getName(), u.getMajor(), u.getDescription(), u.getId());
				if (ret == 1) {
					db.close();
					return Result.ok(null, 0, "修改成功");
				} else {
					db.close();
					return Result.bad("修改失败");
				}
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}

		} else if (type == 2) {
			teacher u = (teacher) JSONObject.toJavaObject(data, teacher.class);
			// 检查id重复
			try {
				sql = "select count(*) from teacher where id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, u.getId());
				if (ret.next()) {
					if (ret.getInt(1) == 0) {
						db.close();
						return Result.bad("找不到教师ID");
					} else {
						db.close();
					}
				}
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}

			// 修改个人信息
			try {
				sql = "UPDATE coures set name =?,grade=?,category=?,department=? where id = ?";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, u.getName(), u.getGrade(), u.getCategory(), u.getDepartment(),
						u.getId());
				if (ret == 1) {
					db.close();
					return Result.ok(null, 0, "修改成功");
				} else {
					db.close();
					return Result.bad("修改失败");
				}
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}
		}
		return Result.bad("失败");
	}

	public Result updateInfo(int type, String id, String npw, String opw) {
		// 检查id重复
		try {
			sql = "select count(*) from ? where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, type==1?"student":"teacher",id);
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("找不到该ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}

		// 修改个人信息
		try {
			sql = "UPDATE ? set password = ? where id = ? and password = ? ";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, type==1?"student":"teacher",npw,id,opw);
			if (ret == 1) {
				db.close();
				return Result.ok(null, 0, "修改成功");
			} else {
				db.close();
				return Result.bad("修改失败,密码错误");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

}

/**
 * Write by Xsm
 */