package com.SCC.dao;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
	
	  public static <T> ArrayList<T> putResult(ResultSet rs, Class<T> obj) {
	        try {
	            ArrayList<T> arrayList = new ArrayList<T>();
	            ResultSetMetaData metaData = rs.getMetaData();
	            /**
	             * ��ȡ������
	             */
	            int count = metaData.getColumnCount();
	            while (rs.next()) {
	                /**
	                 * ��������ʵ��
	                 */
	                T newInstance = obj.newInstance();
	                for (int i = 1; i <= count; i++) {
	                    /**
	                     * �������ĳ�����Ը�ֵ
	                     */
	                    String name = metaData.getColumnName(i).toLowerCase();
	                    name = toJavaField(name);// �ı�������ʽ��java������ʽ
	                    String substring = name.substring(0, 1);// ����ĸ��д
	                    String replace = name.replaceFirst(substring, substring.toUpperCase());
	                    Class<?> type = null;
	                    try {
	                        type = obj.getDeclaredField(name).getType();// ��ȡ�ֶ�����
	                    } catch (NoSuchFieldException e) { // Class����δ������ֶ�ʱ,����
	                        continue;
	                    }

	                    Method method = obj.getMethod("set" + replace, type);
	                    /**
	                     * �ж϶�ȡ���ݵ�����
	                     */
	                    if (type.isAssignableFrom(String.class)) {
	                        method.invoke(newInstance, rs.getString(i));
	                    } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
	                        method.invoke(newInstance, rs.getByte(i));// byte ����������8λ���з��ŵģ��Զ����Ʋ����ʾ������
	                    } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
	                        method.invoke(newInstance, rs.getShort(i));// short ���������� 16 λ���з��ŵ��Զ����Ʋ����ʾ������
	                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
	                        method.invoke(newInstance, rs.getInt(i));// int ����������32λ���з��ŵ��Զ����Ʋ����ʾ������
	                    } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
	                        method.invoke(newInstance, rs.getLong(i));// long ���������� 64 λ���з��ŵ��Զ����Ʋ����ʾ������
	                    } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
	                        method.invoke(newInstance, rs.getFloat(i));// float ���������ǵ����ȡ�32λ������IEEE 754��׼�ĸ�����
	                    } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
	                        method.invoke(newInstance, rs.getDouble(i));// double ����������˫���ȡ�64 λ������IEEE 754��׼�ĸ�����
	                    } else if (type.isAssignableFrom(BigDecimal.class)) {
	                        method.invoke(newInstance, rs.getBigDecimal(i));
	                    } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
	                        method.invoke(newInstance, rs.getBoolean(i));// boolean�������ͱ�ʾһλ����Ϣ
	                    } else if (type.isAssignableFrom(Date.class)) {
	                        method.invoke(newInstance, rs.getDate(i));
	                    }
	                }
	                arrayList.add(newInstance);
	            }
	            return arrayList;

	        } catch (InstantiationException | IllegalAccessException | SQLException | SecurityException | NoSuchMethodException | IllegalArgumentException
	                | InvocationTargetException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        return null;
	    }

	  public static String toJavaField(String str) {

	        String[] split = str.split("_");
	        StringBuilder builder = new StringBuilder();
	        builder.append(split[0]);// ƴ�ӵ�һ���ַ�

	        // ������鲻ֹһ������
	        if (split.length > 1) {
	            for (int i = 1; i < split.length; i++) {
	                // ȥ���»��ߣ�����ĸ��Ϊ��д
	                String string = split[i];
	                String substring = string.substring(0, 1);
	                split[i] = string.replaceFirst(substring, substring.toUpperCase());
	                builder.append(split[i]);
	            }
	        }

	        return builder.toString();
	    }

	
}

/**
 * Write by Xsm
 */
