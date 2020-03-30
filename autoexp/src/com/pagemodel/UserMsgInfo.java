package com.pagemodel;

import java.io.Serializable;

public class UserMsgInfo implements Serializable {

	private static final long serialVersionUID = 8881531372815606969L;
	
	private String userName;
	
	private String msgContent;
	
	private Integer level;
	
	private String levelName;
	
	private String orderId;
	
	private String address;
	
	private Long productId;
	
	private String productName;
	
	private String url;
	private String huifu;
	private Long msgId;
	private String niName;
	
	public String getNiName() {
		return niName;
	}

	public void setNiName(String niName) {
		this.niName = niName;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getHuifu() {
		return huifu;
	}

	public void setHuifu(String huifu) {
		this.huifu = huifu;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
