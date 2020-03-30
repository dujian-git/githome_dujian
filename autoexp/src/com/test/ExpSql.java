package com.test;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utils.ConnectionUtil;

public class ExpSql {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			conn =ConnectionUtil. getMySQLConnection();
			Statement sts = conn.createStatement();
			String sql="select text from all_source where owner in ('SCOTT') and type='PROCEDURE' and name='INCRESAL'";
			ResultSet rs = sts.executeQuery(sql);
			StringBuffer sb=new StringBuffer();
			File file=new File("D:/z/NABS/PROCEDURE/");
			if(!file.exists()){
				file.mkdir();
			}
			File file2=new File("D:/z/NABS/PROCEDURE/INCRESAL.sql");
			PrintWriter pw=new PrintWriter(file2);
			while (rs.next()) {
				sb.append(rs.getString("text"));
				pw.println(rs.getString("text"));
			}
			pw.flush();
			pw.close();
			System.out.println(sb.toString());
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
