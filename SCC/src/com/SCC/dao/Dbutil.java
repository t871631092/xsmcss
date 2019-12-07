package com.SCC.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dbutil {
	public static final String url = "jdbc:mysql://47.96.131.166/SC?useSSL=false&useUnicode=true&characterEncoding=GBK&useOldAliasMetadataBehavior=true";//localhost
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "";

	public Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public Dbutil() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String sql, Object... params) {
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				this.stmt.setObject(i + 1, params[i]);
			}
			rs = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rs;
	}

	public int executeUpdate(String sql, Object... params) {
		int result = 0;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				this.stmt.setObject(i + 1, params[i]);
			}
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static List populate(ResultSet rs , Class clazz) throws SQLException, InstantiationException, IllegalAccessException{
        //�������Ԫ�ض���
        ResultSetMetaData rsmd = rs.getMetaData();
        //��ȡ�������Ԫ�ظ���
         int colCount = rsmd.getColumnCount();
//         System.out.println("#");
//         for(int i = 1;i<=colCount;i++){
//             System.out.println(rsmd.getColumnName(i));
//             System.out.println(rsmd.getColumnClassName(i));
//             System.out.println("#");
//         }
         //���ؽ�����б���
         List list = new ArrayList();
         //ҵ��������������
         Field[] fields = clazz.getDeclaredFields();
         while(rs.next()){//��ÿһ����¼���в���
             Object obj = clazz.newInstance();//����ҵ�����ʵ��
             //��ÿһ���ֶ�ȡ�����и�ֵ
             for(int i = 1;i<=colCount;i++){
                 Object value = rs.getObject(i);
                 if (value != null) {
                     //Ѱ�Ҹ��ж�Ӧ�Ķ�������
                     for(int j=0;j<fields.length;j++){
                         Field f = fields[j];
                         //���ƥ����и�ֵ
                         if(f.getName().equalsIgnoreCase(rsmd.getColumnName(i))){
                             boolean flag = f.isAccessible();
                             f.setAccessible(true);
                             f.set(obj, value);
                             f.setAccessible(flag);
                         }
                     }
				}
             }
             list.add(obj);
         }
        return list;
    }
}

/**
 * Write by Xsm
 */
