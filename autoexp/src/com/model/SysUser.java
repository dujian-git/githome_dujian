package com.model;

import java.math.BigDecimal;
import java.util.Date;

public class SysUser {
    private Long id;

    private Integer age;

    private Date createdatetime;

    private String email;

    private Integer isdefault;

    private String loginname;

    private String addr;

    private String realname;

    private String no;

    private Integer optype;

    private String password;

    private String paypwd;

    private String quesanswer;

    private Integer sex;

    private Integer state;

    private String tel;

    private Integer usertype;

    private Long organizationId;

    private String content;

    private String bir;
    
    private BigDecimal accountAmt;
    
    private String organizationName;
    
    public BigDecimal getAccountAmt() {
		return accountAmt;
	}

	public void setAccountAmt(BigDecimal accountAmt) {
		this.accountAmt = accountAmt;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Integer getOptype() {
        return optype;
    }

    public void setOptype(Integer optype) {
        this.optype = optype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd == null ? null : paypwd.trim();
    }

    public String getQuesanswer() {
        return quesanswer;
    }

    public void setQuesanswer(String quesanswer) {
        this.quesanswer = quesanswer == null ? null : quesanswer.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBir() {
		return bir;
	}

	public void setBir(String bir) {
		this.bir = bir;
	}
 
}