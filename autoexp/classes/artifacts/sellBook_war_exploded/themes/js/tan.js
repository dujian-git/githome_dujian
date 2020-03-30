$(document).ready(function(){
	/*搜索*/
		  $(".index_search").click(function(){
		  $(".none").css("display","block");
		  });
		$(".close").click(function(){
		  $(".none").css("display","none");
		 });
	
	/*注册*/
		$(".fb").click(function(){
		  $(".ft").css("display","block");
		  });
		$(".close").click(function(){
		  $(".ft").css("display","none");
		 });
	
	
$(".starlist").each(function(){
	var num=parseInt($(this).attr("data-star"));
	var apoint=$(this).children("a");
	for(var i=0;i<num;i++){
		$(apoint[i]).addClass("dq");
	}
});	
	var comcontent=$(".comcontent").children("li");
	$(".comnav li").on("click",function(){
		$(this).addClass("dq").siblings("li").removeClass("dq");
		var indexs=$(this).index();
		$(comcontent[indexs]).fadeIn().siblings("li").fadeOut();
		event.preventDefault();
	});
	 	
	});