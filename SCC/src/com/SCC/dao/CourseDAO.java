package com.SCC.dao;

import java.sql.ResultSet;
import java.util.List;

import com.SCC.model.Page;
import com.SCC.model.Result;
import com.SCC.model.coures;
import com.SCC.model.elective;
import com.SCC.model.student;
import com.SCC.model.teacher;

public class CourseDAO {

	Dbutil db = null;
	String sql = null;

	public Result query(Page p, String id) {
		try {
			sql =  
					"select c.*,e.score,e.status,t.name as teacher_name,st.name as score_teacher from elective e "
					+ "LEFT JOIN coures c on e.coures_id = c.coures_id "
					+ "LEFT JOIN teacher t on c.teacher_id = t.id "
					+ "left join teacher st  on e.teacher_id = st.id where e.student_id =  ? LIMIT ?,?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, id, p.getRow(), p.getSize());
			List dataList = Dbutil.populate(ret, coures.class);
			System.out.println("elective 获取数据(条): " + dataList.size());
			db.close();
			return Result.ok(dataList, count("elective","student_id", id), "");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			Result.bad("错误" + e.toString());
		}
		return Result.bad("错误");
	}

	public Result selectCourse(String id, String uid) {

		// 查询学生选课记录是否存在
		try {
			sql = "select count(*) from elective where student_id = ? and coures_id = ? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid, id);
			ret.next();
			if (ret.getInt(1) != 0) {
				db.close();
				return Result.bad("课程已选");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			Result.bad("错误" + e.toString());
		}

		// 选择课程
		try {
			sql = "insert into elective (student_id,coures_id,status) values(?,?,0)";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, uid, id);
			if (ret == 1) {
				db.close();
				return Result.ok(this.query(new Page(), uid).getData(), 0, "选择成功");
			} else {
				db.close();
				return Result.bad("选择失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	public Result delCourse(String id, String uid) {
		// 查询学生选课记录是否存在
		try {
			sql = "select count(*) from elective where student_id = ? and coures_id = ? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid, id);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("课程不存在");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			Result.bad("错误" + e.toString());
		}
		// 查询学生选课记录是否确认存在
		try {
			sql = "select count(*) from elective where student_id = ? and coures_id = ? and status = 0 ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid, id);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("课程已确认");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			Result.bad("错误" + e.toString());
		}
		// 删除课程
		try {
			sql = "delete from elective  where student_id = ? and coures_id = ?  ;";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, uid, id);
			if (ret == 1) {
				db.close();
				return Result.ok(null, 0, "删除成功");
			} else {
				db.close();
				return Result.bad("删除失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	// 确认选课
	public Result comfirm(String uid) {
		// 查询学生选课记录是否存在
		try {
			sql = "select count(*) from elective where student_id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("没有选择课程");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			Result.bad("错误" + e.toString());
		}
		// 更新确认选课
		try {
			sql = " update elective set status = 1  where student_id = ?;";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, uid);
			if (ret > 0) {
				db.close();
				return Result.ok(null, 0, "确认成功");
			} else {
				db.close();
				return Result.bad("确认失败");
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	// 通过教师id 获取学生选课记录 ！！！！废弃
	public Result list(String tid, Page p) {
		// 查询学生选课记录是否存在
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, tid);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("没有选择课程");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			Result.bad("错误" + e.toString());
		}
		// 更新确认选课
		try {
			sql = " select * from elective e INNER JOIN (select coures_id from coures where teacher_id = '?') c on e.coures_id = c.coures_id limit ?,? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, tid, p.getRow(), p.getSize());
			List dataList = Dbutil.populate(ret, teacher.class);
			System.out.println("Teacher表 获取数据(条): " + dataList.size());
			db.close();
			sql = " select count(*) from elective e INNER JOIN (select coures_id from coures where teacher_id = '?') c on e.coures_id = c.coures_id ;";
			db = new Dbutil();
			ResultSet ret2 = db.executeQuery(sql);
			int count = 0;
			if (ret2.next()) {
				count = ret.getInt(1);
				db.close();
			}
			return Result.ok(dataList, count, "");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}

	// 通过课程id 获取学生选课记录
	public Result listC(String cid, Page p) {
//		// 查询学生选课记录是否存在
//		try {
//			sql = "select count(*) from elective where coures_id = ? and status =1 ;";
//			db = new Dbutil();
//			ResultSet ret = db.executeQuery(sql, cid);
//			ret.next();
//			if (ret.getInt(1) == 0) {
//				db.close();
//				return Result.bad("没有该课程");
//			}
//			db.close();
//		} catch (Exception e) {
//			System.out.println("错误" + e.toString());
//			return Result.bad("错误" + e.toString());
//		}
		// 获取课程
		try {
			sql = "select e.score,s.* from elective e left join student s on e.student_id=s.student_id where e.coures_id = ? and e.status = 1  limit ?,? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, cid, p.getRow(), p.getSize());
			List dataList = Dbutil.populate(ret, student.class);
			System.out.println("elective表 获取数据(条): " + dataList.size());
			db.close();
			return Result.ok(dataList, this.count("elective", "coures_id", cid,1), "获取成功");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}
	// 录入成绩
	public Result mark(String cid, String sid, int score,String tid) {
		// 
		try {
			sql = " update elective set score = ?,teacher_id = ?  where student_id = ? and coures_id = ?;";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, score,tid, sid, cid);
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

	// 计算总条数
	public int count(String id) {
		try {
			sql = "select count(*) from " + id;
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			if (ret.next()) {
				int count = ret.getInt(1);
				db.close();
				return count;
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return 0;
		}
		return 0;
	}
	// 气死我啦啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
	// 全部变量都要重写
	// 计算总条数 单参数
	public int count(String table, String tableName, String id) {
		try {
			sql = "select count(*) from " + table + " where " + tableName + " = ?";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, id);
			if (ret.next()) {
				int count = ret.getInt(1);
				db.close();
				return count;
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return 0;
		}
		return 0;
	}

	public int count(String table, String tableName, String id, int Status) {
		try {
			sql = "select count(*) from " + table + " where " + tableName + " = ? and status = ? ";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, id, Status);
			if (ret.next()) {
				int count = ret.getInt(1);
				db.close();
				return count;
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return 0;
		}
		return 0;
	}

	//啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊阿 SQL不能重用
	// 计算总条数 双参数
	public int count(String table, String tableName, String id,String tableName2,String id2) {
		try {
			sql = "select count(*) from " + table + " where " + tableName + " = ? and "+tableName2+" = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, id,id2);
			if (ret.next()) {
				int count = ret.getInt(1);
				db.close();
				return count;
			}
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return 0;
		}
		return 0;
	}
	// 查询专业
	public Result studentDep() {
		// 用学生类算了 ~~懒
		try {
			sql = "select student_major from student group by student_major;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, student.class);
			System.out.println("student表 获取数据(条): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "获取成功");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}
	// 查询课程分类
	public Result couCate() {
		// 用学生类算了 ~~懒
		try {
			sql = "select category from coures group by category;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, coures.class);
			System.out.println("coures表 获取数据(条): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "获取成功");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}
	// 查询专业 自定义查询 学生信息中超过平均人数的专业
	public Result studentDepAdv() {
		// 用学生类算了 ~~懒
		try {
			sql = "SELECT D.* FROM ( SELECT  CAST(ROUND(avg(C.mgco),0) AS signed) as avgScore,C.student_major FROM( "
					+ "SELECT COUNT(student_id) AS mgco,student_major FROM student GROUP BY student_major ) AS C GROUP BY C.student_major ) AS D "
					+ "WHERE D.avgScore>(select count(student_id)/count(distinct student_major) from student) ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, student.class);
			System.out.println("student表 获取数据(条): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "获取成功");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}
	// 查询课程分类  自定义查询 		○ 平均课程容纳人数最大最小类别

	public Result couCateAdv() {
		// 用课程类算了 ~~懒
		try {
			sql = "SELECT MAX(A.a) as avgScore,A.* FROM(SELECT " + 
					" CAST(ROUND(avg(capacity),0) AS signed) as a,coures.* FROM coures GROUP BY category) AS A " + 
					"UNION " + 
					"SELECT MIN(A.a) as avgScore,A.* FROM(SELECT  " + 
					" CAST(ROUND(avg(capacity),0) AS signed) as a,coures.* FROM coures GROUP BY category) AS A ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, coures.class);
			System.out.println("coures表 获取数据(条): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "获取成功");
		} catch (Exception e) {
			System.out.println("错误" + e.toString());
			return Result.bad("错误" + e.toString());
		}
	}
}
