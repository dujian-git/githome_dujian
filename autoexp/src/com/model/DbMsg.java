package com.model;

import java.util.Date;

/**
 * 数据库配置表的实体类
 * @author dujian
 *
 */
public class DbMsg {

	private String id; //编号
	private String sys; //归属系统
	private String base; //是否基础版本，0否，1是
	private String env; //环境
	private String ip; //数据库IP地址
	private String sernm; //数据库实例名
	private String user; //数据库用户名
	private String pwd; //数据库用户密码
	private String port; //端口
	private Date crtTm; //创建时间
	private String crtTmStr; //创建时间的字符串表达
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSys() {
		return sys;
	}
	public void setSys(String sys) {
		this.sys = sys;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSernm() {
		return sernm;
	}
	public void setSernm(String sernm) {
		this.sernm = sernm;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Date getCrtTm() {
		return crtTm;
	}
	public void setCrtTm(Date crtTm) {
		this.crtTm = crtTm;
	}

	public String getCrtTmStr() {
		return crtTmStr;
	}
	public void setCrtTmStr(String crtTmStr) {
		this.crtTmStr = crtTmStr;
	}


}
