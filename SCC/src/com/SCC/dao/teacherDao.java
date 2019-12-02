package com.SCC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SCC.model.teacher;

public class teacherDao {
	public teacher signin(Connection con, teacher tea) throws SQLException {
		teacher resultteacher = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from teacher where id = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tea.getId());
			pstmt.setString(2, tea.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				resultteacher = new teacher();
				resultteacher.setId(rs.getInt("id"));
				resultteacher.setPassword(rs.getString("password"));
				resultteacher.setName(rs.getString("name"));
				resultteacher.setCategory(rs.getString("category"));
				resultteacher.setDepartment(rs.getString("department"));
				resultteacher.setDescription(rs.getString("description"));
			}
			return resultteacher;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultteacher;
	}

}
