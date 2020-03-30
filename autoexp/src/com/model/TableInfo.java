package com.model;

public class TableInfo {

	private String table_name; //表名称
	private String table_type; //表类型，TABLE或VIEW
	private String comments; //表注释
	private String ddl; //表结构，表的定义性SQL语句
	private String index_name; //索引名称
	private String index_type; //索引类型
	private String table_owner; //数据库连接用户
	private String uniqueness; //唯一约束性
	private String columns; //表索引关联字段的连接字符串
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_type() {
		return table_type;
	}
	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDdl() {
		return ddl;
	}
	public void setDdl(String ddl) {
		this.ddl = ddl;
	}
	public String getIndex_name() {
		return index_name;
	}
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	public String getIndex_type() {
		return index_type;
	}
	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}
	public String getTable_owner() {
		return table_owner;
	}
	public void setTable_owner(String table_owner) {
		this.table_owner = table_owner;
	}
	public String getUniqueness() {
		return uniqueness;
	}
	public void setUniqueness(String uniqueness) {
		this.uniqueness = uniqueness;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
}
