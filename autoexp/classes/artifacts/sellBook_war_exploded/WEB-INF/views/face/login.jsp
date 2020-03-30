<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>自助图书馆</title>
<link href="../face/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--theme-style-->
<link href="../face/css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<!--//fonts-->
<script type="text/javascript" src="../themes/js/jquery-1.11.3.min.js"></script>
<script src="../face/js/jquery.easydropdown.js"></script>
<!--script-->
</head>
<script type="text/javascript">
var sessionInfo_userId = '${sessionInfo.id}';

function login(){
	 //登录
    	var userName = $("#userName").val();
		var password = $("#password").val();
		var flag = true;
		if (userName == "" 
			|| password == ""){
              alert("所有的输入框都要填入！");
              flag =  false;
		}
		 
       
		if(flag){
			var formData = new FormData();
			formData.append("userName", userName);
			formData.append("password", password);
			$.ajax({
				  url: '${ctx}/face/doLogin',
				  dataType: 'json',
				  method: 'POST',
				  data: formData,
				  cache: false,
			      processData: false,
			      contentType: false,
				  async: false,
				  success: function (result) {
					  if(result.success){
							  alert("登录成功");
							  window.location.href='${ctx}/face/gologin';
					  }else{
						  alert("失败："+result.msg);
						  }
					  },
				  error:function (XMLHttpRequest, textStatus, errorThrown) {      
				        alert("请求失败！");
				    }
				});
		}
};
//创建用户
function creatUser(){
	 window.location.href = '${ctx}/face/creatUser';
};
//忘记密码
function findPwd(){
	 window.location.href = '${ctx}/face/findPwd';
};

</script>
<body> 
	<!--header-->
	<div class="header">
		<div class="top-header">
			<div class="container">
				<div class="top-header-left">
					<ul class="support">
						<li><aonclick="javascript:window.location.href='${ctx}/face/index'"><label> </label></a></li>
						<li><a onclick="javascript:window.location.href='${ctx}/face/index'">首页<span class="live"></span></a></li>
					</ul>
					<div class="clearfix"> </div>
				</div>
				<div class="top-header-right">
				 <div class="down-top">
						
					 </div>
					<div class="down-top top-down">
						
					 </div>
					
					 <!---->
					<div class="clearfix"> </div>	
				</div>
				<div class="clearfix"> </div>		
			</div>
		</div>
	</div>
	<!---->
	<div class="container">
		 	   <div class="account_grid">
			   <div class=" login-right">
			  	<h2>登录</h2>
				<p>若您已经有账号, 请登录</p>
				<form>
				  <div>
					<span>账号<label>*</label></span>
					<input type="text" id="userName" /> 
				  </div>
				  <div>
					<span>密码<label>*</label></span>
					<input type="password" id="password" /> 
				  </div>
				  <a class="wangji" onclick="findPwd()">忘记密码?</a>
				  <input type="submit" value="登录" onclick="login();">
			    </form>
			   </div>	
			    <div class=" login-left">
			  	 <h3>凯勒说：一本新书像一艘船，带领着我们从狭隘的地方，驶向生活的无限广阔的海洋。</h3>
				 <a class="acount-btn" onclick="creatUser()">创建用户</a>
			   </div>
			   <div class="clearfix"> </div>
			 </div>
				<!--initiate accordion-->
			  <div class="clearfix"> </div>
	</div>
	<!---->
 

</body>
</html>