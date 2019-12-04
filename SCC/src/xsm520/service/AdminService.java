package xsm520.service;

import com.SCC.dao.AdminDAO;
import com.SCC.model.Result;
import com.SCC.model.coures;
import com.SCC.model.student;
import com.SCC.model.teacher;

public class AdminService {
	
	AdminDAO aDao = new AdminDAO();
	public Result getStudent() {
		return aDao.getStudent();
	}
	public Result insStudent(student s) {
		return aDao.insStudent(s);
	}
	public Result updStudent() {
		return aDao.updStudent();
	}
	public Result delStudent(student s) {
		return aDao.delStudent(s);
	}
	
	public Result getCourse() {
		return aDao.getCourse();
	}
	
	public Result insCourse(coures c) {
		return aDao.insCourse(c);
	}
	public Result updCourse() {
		return aDao.updCourse();
	}
	public Result delCourse(coures c) {
		return aDao.delCourse(c);
	}
	
	public Result getTeacher() {
		return aDao.getTeacher();
	}
	public Result insTeacher(teacher t) {
		return aDao.insTeacher(t);
	}
	public Result updTeacher() {
		return aDao.updTeacher();
	}
	public Result delTeacher(teacher t) {
		return aDao.delTeacher(t);
	}
}
