<!DOCTYPE html>
<html lang="en">
<head>
    <#-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据库配置信息管理</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="../../plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../js/jquery-3.3.1.min.js"></script>

</head>
<script>

    function exp(){
        var check_value=[];
        $("input[name='id']:checked").each(function(){
            check_value.push($(this).val());
        });
        if(check_value.length == 0){
            alert("请您选择要导出的数据");
            return ;
        }
        if(check_value.length != 1){
            alert("请您选择1条需要导出的数据");
            return ;
        }
        var ids=check_value[0];
        $.post("../dbcl/exp.do",{
            ids:ids
        },function(result){
            if(result.success){
                alert("导出成功！");
            }else{
                alert("导出失败！");
            }
        },'json');
    }
    function resetValue() {
        var f_sys = $("#sSys").val("");
        var f_ip = $("#sIp").val("");
    }
    function reflash() {
        window.location.href = "list";
    }

    function addDbMsg() {
        window.location.href = "preSave";
    }

    function modifyDbMsg(){
        var check_value=[];
        $("input[name='id']:checked").each(function(){
            check_value.push($(this).val());
        });
        if(check_value.length != 1){
            alert("请您选择1条需要编辑的数据");
            return false ;
        }
        var id=$("[name='id']").val();
        alert("id :.."+id)

           return true;
<#--      window.location.href = "preEdit?id="+check_value[0];-->
    }

    function checkAllOrNone(){
        if($("#checkAll").prop("checked") == true){
            $("input[name='id']").prop("checked",true);
        }else{
            $("input[name='id']").prop("checked",false);
        }
    }

    function quy() {
        var sSys = $("#sSys").val();
        var sIp = $("#sIp").val();
        if($.trim(sSys)=="" &&$.trim(sIp)==""){
             alert("请输入查询条件");
            return false;
        }else {
            return true;
        }

    }


    function deleteDbMsg(){
        var check_value=[];
        $("input[name='id']:checked").each(function(){
            check_value.push($(this).val());
        });
        if(check_value.length == 0){
            alert("请您选择要删除的数据");
            return ;
        }
        var ids=check_value.join(",");

        if(confirm("您确定要删除这"+check_value.length+"条数据吗？")){
            $.post("deleteDbMsg", {"ids" : ids}, function(result){
                if(result.success){
                    alert("删除成功！");
                    window.location.href="list";
                }else{
                    alert(result.msg);
                }
            },'json');
        }
    }

</script>
<body class="hold-transition skin-red sidebar-mini">
<div class="box-header with-border">
</div>

<div class="box-body">
    <ol class="breadcrumb">
        <li>
            <a>数据库配置信息</a>
        </li>
    </ol>
    <div class="table-box">

            <form action="/dbcl/qryDbList.do" method="post" onsubmit="return quy()">
            <table class="table table-striped table-hover dataTable">
                <tr>
                    <td style="width: 10%; height: 100%"></td>
                    <th style="width: 7%; height: 100%;text-align: right" class="sorting">归属系统：</th>
                    <td style="width: 10%; height: 100%" >
                        <input type="text" style="width: 100%; height: 100%"  value="" id="sSys" name="sSys"/>
                    </td>
                    <th style="width: 5%; height: 100%;text-align: right" class="sorting">IP：</th>
                    <td style="width: 10%; height: 100%" >
                        <input type="text" style="width: 100%; height: 100%"  value="" id="sIp" name="sIp"/>
                    </td>
                    <td style="width: 7%"><button type="submit" class="btn btn-default"  >查询</button>
                        <button type="button" class="btn btn-default" onclick="resetValue();">重置</button>
                    </td>
                    <td style="width: 7%"></td>
                </tr>



            </table>


            </form>

    </div>
    <#--工具栏-->
<form action="/dbcl/preEdit.do" method="post" onsubmit="return modifyDbMsg()">
    <div class="table-box">
        <#--工具栏-->

        <div class="pull-left">
            <div class="form-group form-inline">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" title="新增" onclick="addDbMsg();"><i
                            class="fa fa-file-o"></i> 新增
                    </button>
                    <button type="submit" class="btn btn-default" title="修改" ><i
                            class="fa fa-edit"></i> 修改
                    </button>

                    <button type="button" class="btn btn-default" title="删除" onclick="deleteDbMsg();"><i
                            class="fa fa-trash-o"></i> 删除
                    </button>
                    <button type="button" class="btn btn-default" title="刷新" onclick="reflash();"><i
                            class="fa fa-trash-o"></i> 刷新
                    </button>
                    <button type="button" class="btn btn-default" title="导出" onclick="exp();"><i
                            class="fa fa-trash-o"></i> 导出信息
                    </button>

                </div>
            </div>
        </div>

        <#--工具栏-->

        <#---- 数据列表 -->
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable" >
            <thead>
            <tr>
                <th class="">
                    <input type='checkbox' id="checkAll" name="checkAll" onclick="checkAllOrNone()"/>
                </th>
                <th class="sorting">序号</th>
                <th class="sorting">归属系统</th>
                <th class="sorting">基版</th>
                <th class="sorting">环境</th>
                <th class="sorting">IP(数据库地址)</th>
                <th class="sorting">数据库实例名</th>
                <th class="sorting">数据库用户名</th>
                <th class="sorting">数据库密码</th>
                <th class="sorting">数据库短信</th>
                <th class="sorting">创建时间</th>
            </tr>
            </thead>

            <tbody id="prolist">
            <#if root.success>

                <#list root.dbList as dbList>
                    <#assign i=dbList_index>
                <tr>
                    <#--form表单提交时就选中的checkbox的值会被提交-->
                    <td><input type='checkbox' name='id' value="${dbList.id}" /></td>
                    <td>${i+1}</td>
                    <td>${dbList.sys}</td>
                    <#if dbList.base == "1">
                        <td>是</td>
                    <#else >
                        <td>否</td>
                    </#if>
                    <td>${dbList.env}</td>
                    <td>${dbList.ip}</td>
                    <td>${dbList.sernm}</td>
                    <td>${dbList.user}</td>
                    <td>${dbList.pwd}</td>
                    <td>${dbList.port}</td>
                    <td>${dbList.crtTm?string("yyyy-MM-dd")}</td>
                </tr>

                </#list>
                <#else >
                <tr>
                    <td colspan="11" align="center">  <h5>查询失败,没有查到信息!!!</h5> </td>
                </tr>

            </#if>

            </tbody>

        </table>
</form>

    </div>


</div>

</body>
</html>