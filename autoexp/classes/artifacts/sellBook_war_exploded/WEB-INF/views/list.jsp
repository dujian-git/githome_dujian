<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html class="index_html">
<head>
<jsp:include page="inc.jsp"></jsp:include>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<link rel="stylesheet" type="text/css" href="../css/webbase.css" />
<link rel="stylesheet" type="text/css" href="../css/pages-login-manage.css" />
</head>
<style>
</style>
<script>
 
</script>

<body>
	<div align="center" style="margin-top:100px;">
		<button type="button" onclick="exp()"><font color='blue' size="6">导出数据库资源于本地</font></button>
	</div>

<script type="text/javascript" src="../js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../js/plugins/sui/sui.min.js"></script>
<script type="text/javascript" src="../js/plugins/jquery-placeholder/jquery.placeholder.min.js"></script>
<script src="../js/pages/jquery.slideunlock.js"></script>
<script>
	 function exp(){
		 $.post("../user/exp.do",{},function(result){
			 if(result.success){
				 alert("导出成功！");
			 }else{
				 alert("导出失败！");
			 }
		 },'json');
	 }
</script>
</body>
</html>