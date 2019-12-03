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
			ret.last(); //�Ƶ����һ��     
			int rowCount = ret.getRow(); //�õ���ǰ�кţ�Ҳ���Ǽ�¼��     
			ret.beforeFirst(); //�����Ҫ�ý�������Ͱ�ָ�����Ƶ���ʼ����λ��
			if (rowCount == 0) {
				db.close();
				System.out.print("��½ʧ��");
				session.setAttribute("isLogin", false);
				result.setSuccess(false);
				result.setMsg("�û�������");
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
						System.out.print("��½�ɹ�");
						session.setAttribute("username", user.getUsername());
						session.setAttribute("type", type);
						session.setAttribute("isLogin", true);
						result.setSuccess(true);
						result.setData(user);
						return result;
					} else {
						db.close();
						System.out.print("��½ʧ��");
						session.setAttribute("isLogin", false);
						result.setSuccess(false);
						result.setMsg("�������");
						return result;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
		}
		result.setSuccess(false);
		result.setMsg("δ֪����");
		return result;
	}
}
