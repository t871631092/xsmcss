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
	// 获取学生
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
			System.out.println("Student表 获取数据(条): " + dataList.size());
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			result.setMsg(e.toString());
		}
		return result;
	}

	// 添加学生
	public Result insStudent(student s) {
		// 检查输入
		if (s.unVali()) {
			return Result.bad("请检查输入数据");
		}
		// 检查id重复
		try {
			sql = "select count(*) from student where id = ? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, s.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
				} else {
					db.close();
					return Result.bad("ID重复");
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
		// 插入
		try {
			sql = "insert into student (id,name,password) values(?,?,?)";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, s.getId(), s.getName(), s.getPassword());
			System.out.println(s.getName());
			if (ret == 1) {
				db.close();
				Result dataResult = getStudent(Page.d());
				dataResult.setMsg("添加成功");
				return dataResult;
			} else {
				db.close();
				return Result.bad("添加失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	public Result updStudent() {
		return Result.bad("错误+待编码");
	}

	// 删除学生
	public Result delStudent(student s) {
		// 检查输入
		if (s.getId().isEmpty()) {
			return Result.bad("请检查输入数据");
		}
		// 检查id重复
		try {
			sql = "select count(*) from student where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, s.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("找不到此ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
		// 插入
		try {
			sql = "delete from student where id = ?";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, s.getId());
			if (ret == 1) {
				db.close();
				Result dataResult = getStudent(Page.d());
				dataResult.setMsg("删除成功");
				return dataResult;
			} else {
				db.close();
				return Result.bad("删除失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	// 获取教师
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
			System.out.println("Teacher表 获取数据(条): " + dataList.size());
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			result.setMsg(e.toString());
		}
		return result;
	}

	// 添加教师
	public Result insTeacher(teacher t) {
		// 检查输入
		if (t.unVali()) {
			return Result.bad("请检查输入数据");
		}
		// 检查id重复
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, t.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
				} else {
					db.close();
					return Result.bad("ID重复");
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
		// 插入
		try {
			sql = "insert into teacher (id,name,password) values(?,?,?)";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, t.getId(), t.getName(), t.getPassword());
			if (ret == 1) {
				db.close();
				Result dataResult = getTeacher(Page.d());
				dataResult.setMsg("添加成功");
				return dataResult;
			} else {
				db.close();
				return Result.bad("添加失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	public Result updTeacher() {
		return Result.bad("错误+待编码");
	}

	// 删除教师
	public Result delTeacher(teacher t) {
		// 检查输入
		if (t.getId().isEmpty()) {
			return Result.bad("请检查输入数据");
		}
		// 检查id重复
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, t.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("找不到此ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
		// 插入
		try {
			sql = "delete from teacher where id = ?";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, t.getId());
			if (ret == 1) {
				db.close();
				Result dataResult = getTeacher(Page.d());
				dataResult.setMsg("删除成功");
				return dataResult;
			} else {
				db.close();
				return Result.bad("删除失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
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
			System.out.println("Coures表 获取数据(条): " + dataList.size());
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			result.setSuccess(false);
			result.setMsg(e.toString());
		}
		return result;
	}

	public Result insCourse(coures c) {
		// 检查输入
		if (c.unVali()) {
			return Result.bad("请检查输入数据");
		}
		// 检查id重复
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, c.getTeacher_id());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("找不到课程ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
		if (c.getId()==0) {
			// 插入
			try {
				sql = "insert into coures (name,teacher_id,capacity,category,period,description) values(?,?,?,?,?,?)";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, c.getName(), c.getTeacher_id(), c.getCapacity(),c.getCategory(),c.getPeriod(),c.getDescription());
				if (ret == 1) {
					db.close();
					Result dataResult = getCourse(Page.d());
					dataResult.setMsg("添加成功");
					return dataResult;
				} else {
					db.close();
					return Result.bad("添加失败");
				}
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}
		} else {
			//修改
			try {
				sql = "UPDATE coures set name =?,teacher_id=?,capacity=?,category=?,period=?,description=? where id = ?";
				db = new Dbutil();
				int ret = db.executeUpdate(sql, c.getName(), c.getTeacher_id(), c.getCapacity(),c.getCategory(),c.getPeriod(),c.getDescription(),c.getId());
				if (ret == 1) {
					db.close();
					Result dataResult = getCourse(Page.d());
					dataResult.setMsg("修改成功");
					return dataResult;
				} else {
					db.close();
					return Result.bad("修改失败");
				}
			} catch (Exception e) {
				System.out.println("错误" + e.toString());
				return Result.bad("错误" + e.toString());
			}
		}
	}

	public Result updCourse() {
		return Result.bad("错误+待编码");
	}
	// 删除课程
	public Result delCourse(coures c) {
		// 检查输入
		if (c.getId()==0) {
			return Result.bad("请检查输入数据");
		}
		// 检查id重复
		try {
			sql = "select count(*) from coures where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, c.getId());
			if (ret.next()) {
				if (ret.getInt(1) == 0) {
					db.close();
					return Result.bad("找不到此ID");
				} else {
					db.close();
				}
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
		// 插入
		try {
			sql = "delete from coures where id = ?";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, c.getId());
			if (ret == 1) {
				db.close();
				Result dataResult = getCourse(Page.d());
				dataResult.setMsg("删除成功");
				return dataResult;
			} else {
				db.close();
				return Result.bad("删除失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}
	// 计算总条数
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
			System.out.println("错误" + e.toString());
			return 0;
		}
		return 0;
	}
}

/**
 * Write by Xsm
 */