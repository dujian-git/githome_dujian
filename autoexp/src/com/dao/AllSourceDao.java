package com.dao;


import com.model.AllSource;
import com.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author dujian
 * @date  20200325
 *
 *对allSource做sql操作,
 * **/

public class AllSourceDao {


    public static List<AllSource> getGroupList(Map<String, Object> map) throws SQLException {
        //连接数据库
        String hostName = (String) map.get("hostName");
        String dbName = (String) map.get("dbName");
        String userName = (String) map.get("userName");
        String password = (String) map.get("password");
        Connection conn = null;
        List<AllSource> list = new ArrayList<>();
        try {
            conn = ConnectionUtil.getOracleConnection(hostName, dbName, userName, password);
            Statement sts = conn.createStatement();
            String sql = "select owner, type, name from all_source where owner in " + "'" + userName + "'" + " group by owner, type, name order by type";
            ResultSet rs = sts.executeQuery(sql);
            while (rs.next()) {
                AllSource allSource = new AllSource();
                allSource.setName(rs.getString("name"));
                allSource.setOwner(rs.getString("owner"));
                allSource.setType(rs.getString("type"));
                list.add(allSource);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return list;

    }

    public static List<AllSource> getAllSourceList(Map<String, Object> map) throws SQLException {
        //连接数据库
        String hostName = (String) map.get("hostName");
        String dbName = (String) map.get("dbName");
        String userName = (String) map.get("userName");
        String password = (String) map.get("password");
        Connection conn = null;
        List<AllSource> list = new ArrayList<>();
        try {
            conn = ConnectionUtil.getOracleConnection(hostName, dbName, userName, password);
            Statement sts = conn.createStatement();
            ResultSet rs = sts.executeQuery("select * from all_source where owner in " + "'" + userName + "'" + " and TYPE =" + "'" + map.get("type") + "'" + " and name =" + "'" + map.get("name") + "'" + " order by line");

            while (rs.next()) {
                AllSource allSource = new AllSource();
                allSource.setName(rs.getString("name"));
                allSource.setOwner(rs.getString("owner"));
                allSource.setType(rs.getString("type"));
                allSource.setLine(rs.getInt("line"));
                allSource.setText(rs.getString("text"));
                list.add(allSource);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return list;


    }


    public static void main(String arg[]) {

        Map map = new HashMap();
        map.put("hostName", "127.0.0.1");
        map.put("dbName", "root");
        map.put("userName", "scott");
        map.put("password", "123456");
        try {
            List list = AllSourceDao.getAllSourceList(map);
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
