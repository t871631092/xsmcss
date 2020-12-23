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
			System.out.println("elective ��ȡ����(��): " + dataList.size());
			db.close();
			return Result.ok(dataList, count("elective","student_id", id), "");
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			Result.bad("����" + e.toString());
		}
		return Result.bad("����");
	}

	public Result selectCourse(String id, String uid) {

		// ��ѯѧ��ѡ�μ�¼�Ƿ����
		try {
			sql = "select count(*) from elective where student_id = ? and coures_id = ? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid, id);
			ret.next();
			if (ret.getInt(1) != 0) {
				db.close();
				return Result.bad("�γ���ѡ");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			Result.bad("����" + e.toString());
		}

		// ѡ��γ�
		try {
			sql = "insert into elective (student_id,coures_id,status) values(?,?,0)";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, uid, id);
			if (ret == 1) {
				db.close();
				return Result.ok(this.query(new Page(), uid).getData(), 0, "ѡ��ɹ�");
			} else {
				db.close();
				return Result.bad("ѡ��ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	public Result delCourse(String id, String uid) {
		// ��ѯѧ��ѡ�μ�¼�Ƿ����
		try {
			sql = "select count(*) from elective where student_id = ? and coures_id = ? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid, id);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("�γ̲�����");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			Result.bad("����" + e.toString());
		}
		// ��ѯѧ��ѡ�μ�¼�Ƿ�ȷ�ϴ���
		try {
			sql = "select count(*) from elective where student_id = ? and coures_id = ? and status = 0 ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid, id);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("�γ���ȷ��");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			Result.bad("����" + e.toString());
		}
		// ɾ���γ�
		try {
			sql = "delete from elective  where student_id = ? and coures_id = ?  ;";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, uid, id);
			if (ret == 1) {
				db.close();
				return Result.ok(null, 0, "ɾ���ɹ�");
			} else {
				db.close();
				return Result.bad("ɾ��ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	// ȷ��ѡ��
	public Result comfirm(String uid) {
		// ��ѯѧ��ѡ�μ�¼�Ƿ����
		try {
			sql = "select count(*) from elective where student_id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, uid);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("û��ѡ��γ�");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			Result.bad("����" + e.toString());
		}
		// ����ȷ��ѡ��
		try {
			sql = " update elective set status = 1  where student_id = ?;";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, uid);
			if (ret > 0) {
				db.close();
				return Result.ok(null, 0, "ȷ�ϳɹ�");
			} else {
				db.close();
				return Result.bad("ȷ��ʧ��");
			}
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	// ͨ����ʦid ��ȡѧ��ѡ�μ�¼ ������������
	public Result list(String tid, Page p) {
		// ��ѯѧ��ѡ�μ�¼�Ƿ����
		try {
			sql = "select count(*) from teacher where id = ?;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, tid);
			ret.next();
			if (ret.getInt(1) == 0) {
				db.close();
				return Result.bad("û��ѡ��γ�");
			}
			db.close();
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			Result.bad("����" + e.toString());
		}
		// ����ȷ��ѡ��
		try {
			sql = " select * from elective e INNER JOIN (select coures_id from coures where teacher_id = '?') c on e.coures_id = c.coures_id limit ?,? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, tid, p.getRow(), p.getSize());
			List dataList = Dbutil.populate(ret, teacher.class);
			System.out.println("Teacher�� ��ȡ����(��): " + dataList.size());
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
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}

	// ͨ���γ�id ��ȡѧ��ѡ�μ�¼
	public Result listC(String cid, Page p) {
//		// ��ѯѧ��ѡ�μ�¼�Ƿ����
//		try {
//			sql = "select count(*) from elective where coures_id = ? and status =1 ;";
//			db = new Dbutil();
//			ResultSet ret = db.executeQuery(sql, cid);
//			ret.next();
//			if (ret.getInt(1) == 0) {
//				db.close();
//				return Result.bad("û�иÿγ�");
//			}
//			db.close();
//		} catch (Exception e) {
//			System.out.println("����" + e.toString());
//			return Result.bad("����" + e.toString());
//		}
		// ��ȡ�γ�
		try {
			sql = "select e.score,s.* from elective e left join student s on e.student_id=s.student_id where e.coures_id = ? and e.status = 1  limit ?,? ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql, cid, p.getRow(), p.getSize());
			List dataList = Dbutil.populate(ret, student.class);
			System.out.println("elective�� ��ȡ����(��): " + dataList.size());
			db.close();
			return Result.ok(dataList, this.count("elective", "coures_id", cid,1), "��ȡ�ɹ�");
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}
	// ¼��ɼ�
	public Result mark(String cid, String sid, int score,String tid) {
		// 
		try {
			sql = " update elective set score = ?,teacher_id = ?  where student_id = ? and coures_id = ?;";
			db = new Dbutil();
			int ret = db.executeUpdate(sql, score,tid, sid, cid);
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

	// ����������
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
			System.out.println("����" + e.toString());
			return 0;
		}
		return 0;
	}
	// ��������������������������������������
	// ȫ��������Ҫ��д
	// ���������� ������
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
			System.out.println("����" + e.toString());
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
			System.out.println("����" + e.toString());
			return 0;
		}
		return 0;
	}

	//���������������������������������������������� SQL��������
	// ���������� ˫����
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
			System.out.println("����" + e.toString());
			return 0;
		}
		return 0;
	}
	// ��ѯרҵ
	public Result studentDep() {
		// ��ѧ�������� ~~��
		try {
			sql = "select student_major from student group by student_major;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, student.class);
			System.out.println("student�� ��ȡ����(��): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "��ȡ�ɹ�");
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}
	// ��ѯ�γ̷���
	public Result couCate() {
		// ��ѧ�������� ~~��
		try {
			sql = "select category from coures group by category;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, coures.class);
			System.out.println("coures�� ��ȡ����(��): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "��ȡ�ɹ�");
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}
	// ��ѯרҵ �Զ����ѯ ѧ����Ϣ�г���ƽ��������רҵ
	public Result studentDepAdv() {
		// ��ѧ�������� ~~��
		try {
			sql = "SELECT D.* FROM ( SELECT  CAST(ROUND(avg(C.mgco),0) AS signed) as avgScore,C.student_major FROM( "
					+ "SELECT COUNT(student_id) AS mgco,student_major FROM student GROUP BY student_major ) AS C GROUP BY C.student_major ) AS D "
					+ "WHERE D.avgScore>(select count(student_id)/count(distinct student_major) from student) ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, student.class);
			System.out.println("student�� ��ȡ����(��): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "��ȡ�ɹ�");
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}
	// ��ѯ�γ̷���  �Զ����ѯ 		�� ƽ���γ��������������С���

	public Result couCateAdv() {
		// �ÿγ������� ~~��
		try {
			sql = "SELECT MAX(A.a) as avgScore,A.* FROM(SELECT " + 
					" CAST(ROUND(avg(capacity),0) AS signed) as a,coures.* FROM coures GROUP BY category) AS A " + 
					"UNION " + 
					"SELECT MIN(A.a) as avgScore,A.* FROM(SELECT  " + 
					" CAST(ROUND(avg(capacity),0) AS signed) as a,coures.* FROM coures GROUP BY category) AS A ;";
			db = new Dbutil();
			ResultSet ret = db.executeQuery(sql);
			List dataList = Dbutil.populate(ret, coures.class);
			System.out.println("coures�� ��ȡ����(��): " + dataList.size());
			db.close();
			return Result.ok(dataList, dataList.size(), "��ȡ�ɹ�");
		} catch (Exception e) {
			System.out.println("����" + e.toString());
			return Result.bad("����" + e.toString());
		}
	}
}
