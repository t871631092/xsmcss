package com.SCC.dao;

import java.sql.ResultSet;
import java.util.List;

import com.SCC.model.Page;
import com.SCC.model.Result;
import com.SCC.model.User;
import com.SCC.model.coures;
import com.SCC.model.student;
import com.SCC.model.teacher;
import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

import xsm520.interceptor.isloginInterceptor;

public class AdminDAO {

	Dbutil db = null;
	String sql = null;
	static String student = "student";
	// ��ȡѧ��
	public Result getStudent(Page p) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			sql = "select * from student LIMIT ?,?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql,p.getRow(),p.getSize());
			List dataList = Dbutil.populate(ret, student.class);
			int count =count(student);
			System.out.println(count);
			result.setCount(count);
			result.setData(dataList);
			result.setSuccess(true);
			System.out.println("Student�� ��ȡ����(��): " + dataList.size());
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			result.setMsg(e.toString());
		}
		return result;
	}

	// ����ѧ��
	public Result insStudent(student s) {
		// �������
		if (s.unVali()) {
			return Result.bad("������������");
		}
		// ���id�ظ�
		try {
			sql = "select count(*) from student where id = ? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, s.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
				} else {
					db.close();
					return Result.bad("ID�ظ�");
				}
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
		// ����
		try {
			sql = "insert into student (id,name,password) values(?,?,?)";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, s.getId(), s.getName(), s.getPassword());
			System.out.println(s.getName());
			if (ret == 1) {
				db.close();
				Result dataResult = getStudent(Page.d());
				dataResult.setMsg("���ӳɹ�");
				return dataResult;
			} else {
				db.close();
				return Result.bad("����ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	public Result updStudent() {
		return Result.bad("����+������");
	}

	// ɾ��ѧ��
	public Result delStudent(student s) {
		// �������
		if (s.getId().isEmpty()) {
			return Result.bad("������������");
		}
		// ���id�ظ�
		try {
			sql = "select count(*) from student where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, s.getId());
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
		// ����
		try {
			sql = "delete from student where id = ?";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, s.getId());
			if (ret == 1) {
				db.close();
				Result dataResult = getStudent(Page.d());
				dataResult.setMsg("ɾ���ɹ�");
				return dataResult;
			} else {
				db.close();
				return Result.bad("ɾ��ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	// ��ȡ��ʦ
	public Result getTeacher(Page p) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			sql = "select * from teacher LIMIT ?,?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql,p.getRow(),p.getSize());
			List dataList = Dbutil.populate(ret, teacher.class);
			result.setCount(count("teacher"));
			result.setData(dataList);
			result.setSuccess(true);
			System.out.println("Teacher�� ��ȡ����(��): " + dataList.size());
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			result.setMsg(e.toString());
		}
		return result;
	}

	// ���ӽ�ʦ
	public Result insTeacher(teacher t) {
		// �������
		if (t.unVali()) {
			return Result.bad("������������");
		}
		// ���id�ظ�
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, t.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
				} else {
					db.close();
					return Result.bad("ID�ظ�");
				}
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
		// ����
		try {
			sql = "insert into teacher (id,name,password) values(?,?,?)";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, t.getId(), t.getName(), t.getPassword());
			if (ret == 1) {
				db.close();
				Result dataResult = getTeacher(Page.d());
				dataResult.setMsg("���ӳɹ�");
				return dataResult;
			} else {
				db.close();
				return Result.bad("����ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	public Result updTeacher() {
		return Result.bad("����+������");
	}

	// ɾ����ʦ
	public Result delTeacher(teacher t) {
		// �������
		if (t.getId().isEmpty()) {
			return Result.bad("������������");
		}
		// ���id�ظ�
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, t.getId());
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
		// ����
		try {
			sql = "delete from teacher where id = ?";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, t.getId());
			if (ret == 1) {
				db.close();
				Result dataResult = getTeacher(Page.d());
				dataResult.setMsg("ɾ���ɹ�");
				return dataResult;
			} else {
				db.close();
				return Result.bad("ɾ��ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	public Result getCourse(Page p) {
		Result result = new Result();
		try {
			sql = "select * from coures LIMIT ?,?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql,p.getRow(),p.getSize());
			List dataList = Dbutil.populate(ret, coures.class);
			result.setCount(count("coures"));
			result.setData(dataList);
			result.setSuccess(true);
			System.out.println("Coures�� ��ȡ����(��): " + dataList.size());
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			result.setSuccess(false);
			result.setMsg(e.toString());
		}
		return result;
	}

	public Result insCourse(coures c) {
		// �������
		if (c.unVali()) {
			return Result.bad("������������");
		}
		// ���id�ظ�
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, c.getTeacher_id());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("�Ҳ����γ�ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
		if (c.getId()==0) {
			// ����
			try {
				sql = "insert into coures (name,teacher_id,capacity,category,period,description) values(?,?,?,?,?,?)";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, c.getName(), c.getTeacher_id(), c.getCapacity(),c.getCategory(),c.getPeriod(),c.getDescription());
				if (ret == 1) {
					db.close();
					Result dataResult = getCourse(Page.d());
					dataResult.setMsg("���ӳɹ�");
					return dataResult;
				} else {
					db.close();
					return Result.bad("����ʧ��");
				}
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}
		} else {
			//�޸�
			try {
				sql = "UPDATE coures set name =?,teacher_id=?,capacity=?,category=?,period=?,description=? where id = ?";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, c.getName(), c.getTeacher_id(), c.getCapacity(),c.getCategory(),c.getPeriod(),c.getDescription(),c.getId());
				if (ret == 1) {
					db.close();
					Result dataResult = getCourse(Page.d());
					dataResult.setMsg("�޸ĳɹ�");
					return dataResult;
				} else {
					db.close();
					return Result.bad("�޸�ʧ��");
				}
			} catch (Exception e) {
				System.out.println("����" + e.toString());
				return Result.bad("����" + e.toString());
			}
		}
	}

	public Result updCourse() {
		return Result.bad("����+������");
	}
	// ɾ���γ�
	public Result delCourse(coures c) {
		// �������
		if (c.getId()==0) {
			return Result.bad("������������");
		}
		// ���id�ظ�
		try {
			sql = "select count(*) from coures where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, c.getId());
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
		// ����
		try {
			sql = "delete from coures where id = ?";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, c.getId());
			if (ret == 1) {
				db.close();
				Result dataResult = getCourse(Page.d());
				dataResult.setMsg("ɾ���ɹ�");
				return dataResult;
			} else {
				db.close();
				return Result.bad("ɾ��ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}
	// ����������
	public int count(String id) {
		try {
			sql = "select count(*) from "+id;
			db = new Dbutil();
			System.out.println(id);
			ResultSet ret = db.executeQuery(sql);
			if (ret.next()) {
				int count =ret.getInt(1);
				db.close();
				return count;
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return 0;
		}
		return 0;
	}
}

/**
 * Write by Xsm
 */