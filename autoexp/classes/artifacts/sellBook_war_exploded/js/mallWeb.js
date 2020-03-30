
/**
 * @author 鸟
 * 
 * @requires jQuery
 * 
 * 当页面加载完毕关闭加载进度
 * **/
$(window).load(function(){
	 
});

function addCardFun(productid){
	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		$.ajax({
			  url: 'addCar?productid='+productid,
			  dataType: 'json',
			  method: 'GET',
			  cache: false,
		      processData: false,
		      contentType: false,
			  async: false,
			  success: function (result) {
				  if(result.success){
						  alert("添加成功");
						  window.location.reload();
				  }else{
					   if(result.msg == '1'){
						   alert("未登录,不能加入购物车");
							  window.location.href='goLogin';
					   }
					 }
				  },
			  error:function (XMLHttpRequest, textStatus, errorThrown) {      
			        alert("请求失败！");
			    }
			});
	}else{
		window.location.href='goLogin';
	}
	
}

//修改会员信息
function chgUser(typ){
	var formData = new FormData();
	var flag = true;
	if(typ == 1){
		formData.append("niming", $("#niName").val());
	}
	if(typ == 2){
		var newPwd1 =  $("#newPwd1").val()
		var newPwd2 =  $("#newPwd2").val()
		if (newPwd1 == "" 
			|| newPwd2 == ""){
              alert("所有的输入框都要填入！");
              flag =  false;
		}
		if(flag && newPwd1 != newPwd2){
			 alert("两次密码不一致！");
             flag =  false;
		}
		if(flag){
			formData.append("pwd", newPwd1);
		}
	}
	if(flag){
		$.ajax({
			  url: 'chgUser',
			  dataType: 'json',
			  method: 'POST',
			  data: formData,
			  cache: false,
		      processData: false,
		      contentType: false,
			  async: false,
			  success: function (result) {
				  if(result.success){
						  alert("修改成功");
						  window.location.href = 'gobuycenter';
				  }else{
					  alert("失败："+result.msg);
					  }
				  },
			  error:function (XMLHttpRequest, textStatus, errorThrown) {      
			        alert("请求失败！");
			    }
			});
	}
	
} 

//首页
function goHello(){
	 window.location.href = 'hello';
 };
 
//买家登录
function goLoginUser(){
	 window.location.href = 'goLogin';
 };
 
//买家退出
 function goLoginOut(){
 	$.ajax({
		  url: 'goLoginOut',
		  dataType: 'json',
		  method: 'GET',
		  cache: false,
	      processData: false,
	      contentType: false,
		  async: false,
		  success: function (result) {
			  if(result.success){
					  alert("退出成功");
					  window.location.href = 'hello';
			  }else{
				  alert("失败："+result.msg);
				  }
			  },
		  error:function (XMLHttpRequest, textStatus, errorThrown) {      
		        alert("请求失败！");
		    }
		});
};
//   //购物车界面
 function goShopCar(){
   	 window.location.href = 'goShopCar';
    };
//商品详细
function goShopDetail(id){
    window.location.href = 'goShopDetail?id='+id;
};
//留言
function goUserLiuyan(){
   	 window.location.href = 'goUserLiuyan';
};
//会员中心
function goUserCenter(){
	window.location.href='gobuycenter';
};

//会员中心
function aboutHelp(){
	window.location.href='aboutHelp';
};


//收藏本站
function goShoucang(){
	alert("恭喜您！收藏成功");
};