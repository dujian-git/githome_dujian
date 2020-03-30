package com.model.base;

public class Json implements java.io.Serializable {

	private boolean success = false;

	private String msg = "";

	private Object obj = null;
	private Object obj1 = null;
	private Object obj2 = null;
	private Object obj3 = null;

	
	public Object getObj3() {
		return obj3;
	}

	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}

	public Object getObj1() {
		return obj1;
	}

	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}

	public Object getObj2() {
		return obj2;
	}

	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
