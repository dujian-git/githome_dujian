package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

import com.utils.PropertiesUtil;

public class OjdbcUtil {
	
	public Connection getCon() throws Exception{
		Class.forName(PropertiesUtil.getValue("driver"));
		Connection con=DriverManager.getConnection(PropertiesUtil.getValue("url"),
					PropertiesUtil.getValue("username"), PropertiesUtil.getValue("password"));	
		/*Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("数据库驱动加载成功！"); //输出的信息
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"scott", "tiger");*/
		return con;
	}

	public void close(Connection con) throws Exception{
		
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		OjdbcUtil ojdbcUtil=new OjdbcUtil();
		try {
			ojdbcUtil.getCon();//无需获取返回值
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连失败");
		}
	}
}
