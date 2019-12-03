package xsm520.service;

import com.SCC.dao.AdminDAO;
import com.SCC.model.Result;

public class AdminService {
	
	AdminDAO aDao = new AdminDAO();
	public Result getStudent() {
		return aDao.getStudent();
	}
	public Result insStudent() {
		return aDao.insStudent();
	}
	public Result updStudent() {
		return aDao.updStudent();
	}
	public Result delStudent() {
		return aDao.delStudent();
	}
	
	public Result getCourse() {
		return aDao.getCourse();
	}
	
	public Result insCourse() {
		return aDao.insCourse();
	}
	public Result updCourse() {
		return aDao.updCourse();
	}
	public Result delCourse() {
		return aDao.delCourse();
	}
	
	public Result getTeacher() {
		return aDao.getTeacher();
	}
	public Result insTeacher() {
		return aDao.insTeacher();
	}
	public Result updTeacher() {
		return aDao.updTeacher();
	}
	public Result delTeacher() {
		return aDao.delTeacher();
	}
}
