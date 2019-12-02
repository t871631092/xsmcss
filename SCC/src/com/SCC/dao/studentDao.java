package com.SCC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SCC.model.student;

public class studentDao {
	public student signin (Connection con,student stu) throws SQLException{
		student resultstudent = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "select * from student where id = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stu.getId());
			pstmt.setString(2, stu.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				resultstudent = new student();
				resultstudent.setId(rs.getInt("id"));
				resultstudent.setPassword(rs.getString("password"));
				resultstudent.setName(rs.getString("name"));
				resultstudent.setCredits(rs.getString("credits"));
				resultstudent.setMajor(rs.getString("major"));
				resultstudent.setDescription(rs.getString("description"));
			}
			return resultstudent;
		}catch(Exception e){
            e.printStackTrace();
        }
		return resultstudent;
	}
	

}
