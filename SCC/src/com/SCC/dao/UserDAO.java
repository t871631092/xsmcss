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
	//��½ ����ȫҪ��
	public Result login(String username, String password, HttpSession session) {
		Result result = new Result();
		try {
			sql = "select id as username,name as nickname,password,3 as type from user WHERE user.id = ?" + "UNION "
					+ "select student_id as username,student_name as nickname,student_password as password,1 as type from student WHERE student.student_id = ?" + "UNION "
					+ "select id as username,name as nickname,password,2 as type from teacher WHERE  teacher.id = ?";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, username,username,username);
			System.out.print(ret.getRow());
			ret.last(); // �Ƶ����һ��
			int rowCount = ret.getRow(); // �õ���ǰ�кţ�Ҳ���Ǽ�¼��
			ret.beforeFirst(); // �����Ҫ�ý�������Ͱ�ָ�����Ƶ���ʼ����λ��
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
						user.setNickname(ret.getString("nickname"));
						db.close();
						System.out.print("��½�ɹ�");
						session.setAttribute("id", user.getUsername());
						session.setAttribute("name", user.getNickname());
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
	//��ȡѧ����ʦ������Ϣ  �����Ҳ���� SQLҲҪ�İ���������������������������
	public Result getInfo(int type, String id) {
		Result result = new Result();
		if (type == 1) {
			try {
				sql = "select * from student where student_id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, id);
				List dataList = Dbutil.populate(ret, student.class);
				result.setCount(dataList.size());
				result.setData(dataList);
				result.setSuccess(true);
				System.out.println("student�� ��ȡ����(��): " + dataList.size());
				return Result.ok(dataList, dataList.size(), "��ȡ�ɹ�");
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
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
				System.out.println("teacher�� ��ȡ����(��): " + dataList.size());
				return Result.ok(dataList, dataList.size(), "��ȡ�ɹ�");
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}
		}
		return Result.bad("����");
	}

	public Result updateInfo(int type, JSONObject data) {
		System.out.println(type);
		if (type == 1) {
			student u = (student) JSONObject.toJavaObject(data, student.class);
			// ���id�ظ�
			try {
				sql = "select count(*) from student where student_id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, u.getStudent_id());
				if (ret.next()) {
					if (ret.getInt(1) == 0) {
						db.close();
						return Result.bad("�Ҳ���ѧ��ID");
					} else {
						db.close();
					}
				}
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}
			// �޸ĸ�����Ϣ
			try {
				sql = "UPDATE student set student_name =?,student_major=?,student_description=? where student_id = ?";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, u.getStudent_name(), u.getStudent_major(), u.getStudent_description(), u.getStudent_id());
				if (ret == 1) {
					db.close();
					return Result.ok(null, 0, "�޸ĳɹ�");
				} else {
					db.close();
					return Result.bad("�޸�ʧ��");
				}
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}

		} else if (type == 2) {
			teacher u = (teacher) JSONObject.toJavaObject(data, teacher.class);
			// ���id�ظ�
			try {
				sql = "select count(*) from teacher where id = ?;";
				db = new Dbutil();
				ResultSet ret = db.executeQuery(sql, u.getId());
				if (ret.next()) {
					if (ret.getInt(1) == 0) {
						db.close();
						return Result.bad("�Ҳ�����ʦID");
					} else {
						db.close();
					}
				}
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}

			// �޸ĸ�����Ϣ
			try {
				sql = "UPDATE teacher set name =?,grade=?,category=?,department=? where id = ?";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, u.getName(), u.getGrade(), u.getCategory(), u.getDepartment(),
						u.getId());
				if (ret == 1) {
					db.close();
					return Result.ok(null, 0, "�޸ĳɹ�");
				} else {
					db.close();
					return Result.bad("�޸�ʧ��");
				}
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}
		}
		return Result.bad("ʧ��");
	}

	public Result password(int type, String id, String npw, String opw) {
		String tString = type==1?" student":"teacher";
		String idString = type==1?" student_id":"id";
		String paString = type==1?" student_password":"password";
		// ���id�ظ�
		try {
			sql = "select count(*) from " + tString + " where "+idString+" = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql,id);
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("�Ҳ�����ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}

		// �޸ĸ�����Ϣ
		try {
			sql = "UPDATE " + tString + " set "+paString+" = ? where "+idString+" = ? and "+paString+" = ? ";
			db = new Dbutil();
			int ret = db.executeUpdate(sql,npw,id,opw);
			if (ret == 1) {
				db.close();
				return Result.ok(null, 0, "�޸ĳɹ�");
			} else {
				db.close();
				return Result.bad("�޸�ʧ��,�������");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

}

/**
 * Write by Xsm
 */