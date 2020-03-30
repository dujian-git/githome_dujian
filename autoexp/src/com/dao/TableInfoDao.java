package com.dao;

import com.model.AllSource;
import com.model.TableInfo;
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
 *
 * **/




public class TableInfoDao {


    public static List<TableInfo> getTableInfoWithDdlAndComList(Map<String, Object> map) throws SQLException {
        //连接数据库
        String hostName = (String) map.get("hostName");
        String dbName = (String) map.get("dbName");
        String userName = (String) map.get("userName");
        String password = (String) map.get("password");
        Connection conn = null;
        List<TableInfo> list = new ArrayList<>();
        try {
            conn = ConnectionUtil.getOracleConnection(hostName, dbName, userName, password);
            Statement sts = conn.createStatement();
            String sql = "select utc.*, DBMS_METADATA.GET_DDL('TABLE',utc.table_name)" + " as ddl from user_tab_comments utc WHERE table_type=" + "'" + map.get("table_type") + "'";
            ResultSet rs = sts.executeQuery(sql);
            while (rs.next()) {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTable_name(rs.getString("table_name"));
                tableInfo.setTable_type(rs.getString("table_type"));
                tableInfo.setComments(rs.getString("comments"));
                tableInfo.setDdl(rs.getString("ddl"));
                list.add(tableInfo);
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

    public static List<TableInfo> getTableInfoWithIndexList(Map<String, Object> map) throws SQLException {
        //连接数据库
        String hostName = (String) map.get("hostName");
        String dbName = (String) map.get("dbName");
        String userName = (String) map.get("userName");
        String password = (String) map.get("password");
        Connection conn = null;
        List<TableInfo> list = new ArrayList<>();
        try {
            conn = ConnectionUtil.getOracleConnection(hostName, dbName, userName, password);
            Statement sts = conn.createStatement();
            String sql = "select ui.index_name,ui.index_type,ui.table_owner,ui.table_name,ui.uniqueness,uic.INDEX_NAME,listagg(uic.COLUMN_NAME, ',')" + " within group (order by column_position) as columns " + "from user_indexes ui, user_ind_columns uic where ui.index_name=uic.INDEX_NAME and ui.table_name= " + "'" + map.get("table_name") + "'" + "group by ui.index_name,ui.index_type,ui.table_owner,ui.table_name,ui.uniqueness,uic.INDEX_NAME";
            ResultSet rs = sts.executeQuery(sql);
//            System.out.println(sql);
            while (rs.next()) {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTable_name(rs.getString("table_name"));
                tableInfo.setIndex_name(rs.getString("index_name"));
                tableInfo.setIndex_type(rs.getString("index_type"));
                tableInfo.setTable_owner(rs.getString("table_owner"));
                tableInfo.setUniqueness(rs.getString("uniqueness"));
                tableInfo.setColumns(rs.getString("columns"));
                list.add(tableInfo);
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
        map.put("table_type", "TABLE");
        map.put("table_name", "t_db_msg");
        try {
            List list = TableInfoDao.getTableInfoWithIndexList(map);
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
