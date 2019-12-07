package xsm520.service;

import com.SCC.dao.CourseDAO;
import com.SCC.model.Page;
import com.SCC.model.Result;

public class CourseService {
	
	CourseDAO cDAO = new CourseDAO();
	
	public Result query(Page p,String id) {
		return cDAO.query(p,id);
	}
	public Result selectCourse(String id,String uid) {
		return cDAO.selectCourse(id,uid);
	}
	public Result delCourse(String id,String uid) {
		return cDAO.delCourse(id,uid);
	}
	public Result comfirm(String uid) {
		return cDAO.comfirm(uid);
	}
	public Result list(String uid,Page p) {
		return cDAO.list(uid,p);
	}
	public Result listC(String uid,Page p) {
		return cDAO.listC(uid,p);
	}
	public Result mark(String cid, String sid, int score) {
		return cDAO.mark(cid,sid,score);
	}
}
