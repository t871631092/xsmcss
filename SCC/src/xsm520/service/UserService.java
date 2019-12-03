package xsm520.service;

import javax.servlet.http.HttpSession;

import com.SCC.dao.UserDAO;
import com.SCC.model.Result;


public class UserService {
	UserDAO uDao = new UserDAO();
	public Result login(String username,String password,HttpSession session) {
		return uDao.login(username,password,session);
	}
}
