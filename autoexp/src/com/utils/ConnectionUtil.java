package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "test";
		String userName = "root";
		String password = "root";
//		String hostName = "127.0.0.1";
//		String dbName = "orcl";
//		String userName = "scott";
//		String password = "tiger";
		
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getOracleConnection(String hostName, String dbName,
			String userName, String password) throws SQLException,
			ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String connectionURL = "jdbc:oracle:thin:@//" + hostName + ":1521/" + dbName;
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?useUnicode=true&characterEncoding=utf8&useSSL=false";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

    public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Connection conn = null;
			try {
//				conn = getMySQLConnection();
//				Statement sts = conn.createStatement();
//				ResultSet rs = sts.executeQuery("select * from book");
				conn=getOracleConnection("127.0.0.1","root","scott","123456");
				Statement sts = conn.createStatement();
                ResultSet rs = sts.executeQuery("select * from t_db_msg");
				while (rs.next()) {
					System.out.println(rs.getString("db_sys"));
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(conn != null){
					try {
						conn.close();
						conn = null;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	  
	  
}
