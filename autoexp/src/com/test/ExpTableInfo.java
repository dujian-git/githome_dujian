package com.test;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.utils.ConnectionUtil;

public class ExpTableInfo {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			conn =ConnectionUtil. getMySQLConnection();
			Statement sts = conn.createStatement();
			String sql="select dbms_metadata.get_ddl('TABLE','T_DATE') as ddlInfo from dual";
			ResultSet rs = sts.executeQuery(sql);
			Statement sts2 = conn.createStatement();
			String sql2="select * from T_DATE";
			ResultSet rs2 = sts2.executeQuery(sql2);
			ResultSetMetaData rs2MetaData=rs2.getMetaData();
			StringBuffer sb=new StringBuffer();
			File file=new File("D:/z/SCOTT/TABLE/");
			if(!file.exists()){
				file.mkdir();
			}
			File file2=new File("D:/z/SCOTT/TABLE/T_DATE.sql");
			PrintWriter pw=new PrintWriter(file2);
			while (rs.next()) {
				sb.append(rs.getString("ddlInfo"));
				String[] strArr=rs.getString("ddlInfo").split("\n");
				for(String str:strArr){
					if(!"".equals(str.trim())){
						pw.println(str);
					}
				}
			}
			sb.append("\n");
			pw.println();
			sb.append("insert into "+"T_DATE"+" values ");
			pw.print("insert into "+"T_DATE"+" values ");
			while(rs2.next()){
				sb.append("(");
				pw.print("(");
				for(int i=1;i<=rs2MetaData.getColumnCount();i++){
					if(i==1){
						sb.append(rs2.getObject(i));
						pw.print(rs2.getObject(i));
					}else{
						sb.append(","+rs2.getObject(i));
						pw.print(","+rs2.getObject(i));
					}
				}
				sb.append("),");
				pw.print("),");
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
