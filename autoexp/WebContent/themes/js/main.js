$(document).ready(function(){

	
$('.navbar-toggle').on("click", function() {
	$(this).toggleClass("navbar-toggle1")
	$(".menu").fadeToggle("slow");
});
	
	
	
$('.date').each(function(){
		var dtStr = $(this).text();
		var dt = dtStr.split('-');
		$(this).html('<span class="d1">'+dt[1].toUpperCase()+'</span> - <span class="d2">'+dt[2]+'</span> - <span class="d3">'+dt[0]+'</span>'+'<em></em>');
});//截取日期从新排版

$(window).scroll(function(){
			if($(document).scrollTop()>100){
				$(".top").css("background","rgba(255, 255, 255, 1)")
				$(".logo").addClass("logo1");
				$(".top").addClass("topai");
				$(".menu").addClass("menu1");
			}else{
				$(".top").css("background","rgb(255,255, 255)")
				$(".logo").removeClass("logo1");
				$(".top").removeClass("topai");
				$(".menu").removeClass("menu1");
			}
})//顶部100

var changdu = $('.pag_nav').children().length -1;
if(changdu == 1){
	$('.pag_nav').addClass("pag_nav1");
}else if(changdu == 2){
	$('.pag_nav').addClass("pag_nav2");
}else if(changdu == 3){
	$('.pag_nav').addClass("pag_nav3");
}else if(changdu == 4){
	$('.pag_nav').addClass("pag_nav4");
}else if(changdu == 5){
	$('.pag_nav').addClass("pag_nav5");
}else if(changdu == 6){
	$('.pag_nav').addClass("pag_nav6");
}

});
//初始化

$(window).load(function(){
	//$('.index_about_r').height($('.index_about_l img').height());	
	//$('.main_left').height($(window).height()-80 );	
	//$('.main_right').height($(window).height()-200 );	
	//if($(window).width()<=767){
	//	}else if($(window).width()>768){		
	//};
})
//加载
	
if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
	new WOW().init();
};