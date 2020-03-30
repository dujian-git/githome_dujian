
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增管理</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="../../plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../js/jquery-3.3.1.min.js"></script>
     
</head>
<script>
	$(function(){
		var hBase = $("#hBase").val();
		$("#base").val(hBase);
	});
	
	function save(){
		var sys =  $("#sys").val();
		var base =  $("#base").val();
		var env =  $("#env").val();
		var ip =  $("#ip").val();
		var sernm =  $("#sernm").val();
		var port =  $("#port").val();
		var user =  $("#user").val();
		var pwd =  $("#pwd").val();
		
		if (sys == null || sys == '') {
			alert("填写归属系统！");
			return ;
		}
		if (base == null || base == '') {
			alert("选择是否基板！");
			return ;
		}
		if (env == null || env == '') {
			alert("填写环境！");
			return ;
		}
		if (ip == null || ip == '') {
			alert("填写IP！");
			return ;
		}
		if (sernm == null || sernm == '') {
			alert("填写数据库实例名！");
			return ;
		}
		if (port == null || port == '') {
			alert("填写数据库端口！");
			return ;
		}
		if (user == null || user == '') {
			alert("填写数据库用户名！");
			return ;
		}
		if (pwd == null || pwd == '') {
			alert("填写数据库密码！");
			return ;
		}
		
		$.post("../dbcl/save.do",
				{	
					"id" : $("#id").val(),
					"sys" : sys,
					"base" : base,
					"env" : env,
					"ip" : ip,
					"sernm" : sernm,
					"port" : port,
					"user" : user,
					"pwd" : pwd,
				},
				function(result){
					if(result.success){
						alert("保存成功！");
						window.location.href="list";
					}else{
						alert(result.msg);
					}
		},'json');
	}
	
	function fallback(){
		window.location.href="list";
	}
</script>
<body class="hold-transition skin-red sidebar-mini" >
  <!-- .box-body -->
                
                    <div class="box-header with-border">
                    </div>

                    <div class="box-body">
                  			 <ol class="breadcrumb">	                        	
                        		<li class="disabled">
		                        	<a>添加或修改数据库配置信息</a> 
		                        </li>
                                 <li>
                                     <p1>欢迎您</p1>
                                 </li>
	                        </ol>

                        <!-- 数据表格 -->
                        <div class="table-box">
				
							<!--数据列表-->
							<table id="dataList"
								class="table table-striped table-hover dataTable">
								<tr>
									<td><font>归属系统</font></td>
									<td><input type="text" id="sys" name="sys" value="${root.dbMsg.sys!}"></td>
									<td>是否基版</td>
									<td>
										<input type="hidden" id="hBase" name="hBase" value="">
										<select name="base" id="base" name="sys">
											<option value="">--请选择--</option>
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>环境</td>
									<td><input type="text" id="env" name="env" value="${root.dbMsg.env!}"></td>
									<td>IP</td>
									<td><input type="text" id="ip" name="ip" value="${root.dbMsg.ip!}"></td>
								</tr>
								<tr>
									<td>数据库实例名</td>
									<td><input type="text" id="sernm" name="sernm" value="${root.dbMsg.sernm!}"></td>
									<td>端口</td>
									<td><input type="text" id="port" name="port" value="${root.dbMsg.port!}"></td>
								</tr>
								<tr>
									<td>用户名</td>
									<td><input type="text" id="user" name="user" value="${root.dbMsg.user!}"></td>
									<td>用户密码</td>
									<td><input type="text" id="pwd" name="pwd" value="${root.dbMsg.pwd!}"></td>
								</tr>
								<tr>
									<td colspan="4">
										<input type="hidden" id="id" name="id" value="${root.dbMsg.id!}">
										<button class="btn btn-sm btn-primary" onclick="save();">保存</button>
										<button class="btn btn-sm btn-primary" onclick="fallback();">取消</button>
									</td>
								</tr>
							</table>
							<!--数据列表/-->   
			                  
                        </div>
                        <!-- 数据表格 /-->
                        
                     </div>
                    <!-- /.box-body -->
                                
</body>
</html>