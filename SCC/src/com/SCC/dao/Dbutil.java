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
        //结果集的元素对象
        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的元素个数
         int colCount = rsmd.getColumnCount();
//         System.out.println("#");
//         for(int i = 1;i<=colCount;i++){
//             System.out.println(rsmd.getColumnName(i));
//             System.out.println(rsmd.getColumnClassName(i));
//             System.out.println("#");
//         }
         //返回结果的列表集合
         List list = new ArrayList();
         //业务对象的属性数组
         Field[] fields = clazz.getDeclaredFields();
         while(rs.next()){//对每一条记录进行操作
             Object obj = clazz.newInstance();//构造业务对象实体
             //将每一个字段取出进行赋值
             for(int i = 1;i<=colCount;i++){
                 Object value = rs.getObject(i);
                 if (value != null) {
                     //寻找该列对应的对象属性
                     for(int j=0;j<fields.length;j++){
                         Field f = fields[j];
                         //如果匹配进行赋值
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
	             * 获取总列数
	             */
	            int count = metaData.getColumnCount();
	            while (rs.next()) {
	                /**
	                 * 创建对象实例
	                 */
	                T newInstance = obj.newInstance();
	                for (int i = 1; i <= count; i++) {
	                    /**
	                     * 给对象的某个属性赋值
	                     */
	                    String name = metaData.getColumnName(i).toLowerCase();
	                    name = toJavaField(name);// 改变列名格式成java命名格式
	                    String substring = name.substring(0, 1);// 首字母大写
	                    String replace = name.replaceFirst(substring, substring.toUpperCase());
	                    Class<?> type = null;
	                    try {
	                        type = obj.getDeclaredField(name).getType();// 获取字段类型
	                    } catch (NoSuchFieldException e) { // Class对象未定义该字段时,跳过
	                        continue;
	                    }

	                    Method method = obj.getMethod("set" + replace, type);
	                    /**
	                     * 判断读取数据的类型
	                     */
	                    if (type.isAssignableFrom(String.class)) {
	                        method.invoke(newInstance, rs.getString(i));
	                    } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
	                        method.invoke(newInstance, rs.getByte(i));// byte 数据类型是8位、有符号的，以二进制补码表示的整数
	                    } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
	                        method.invoke(newInstance, rs.getShort(i));// short 数据类型是 16 位、有符号的以二进制补码表示的整数
	                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
	                        method.invoke(newInstance, rs.getInt(i));// int 数据类型是32位、有符号的以二进制补码表示的整数
	                    } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
	                        method.invoke(newInstance, rs.getLong(i));// long 数据类型是 64 位、有符号的以二进制补码表示的整数
	                    } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
	                        method.invoke(newInstance, rs.getFloat(i));// float 数据类型是单精度、32位、符合IEEE 754标准的浮点数
	                    } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
	                        method.invoke(newInstance, rs.getDouble(i));// double 数据类型是双精度、64 位、符合IEEE 754标准的浮点数
	                    } else if (type.isAssignableFrom(BigDecimal.class)) {
	                        method.invoke(newInstance, rs.getBigDecimal(i));
	                    } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
	                        method.invoke(newInstance, rs.getBoolean(i));// boolean数据类型表示一位的信息
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
	        builder.append(split[0]);// 拼接第一个字符

	        // 如果数组不止一个单词
	        if (split.length > 1) {
	            for (int i = 1; i < split.length; i++) {
	                // 去掉下划线，首字母变为大写
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
