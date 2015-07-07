/**
 * 公用函数
 * @param time
 * @param url
 */

//延时跳转
function go_to( time, url){
	
	window.setTimeout(function(){
		   time--;
		   if(time>0)
			   {
			     go_to(time,url);
			   }
		   else{
			   location.href=url;
		   }
		},1000);			   
	
 }

function message(message){
	$(".global_message").html(message).slideDown();
	
	window.setTimeout(function(){
		$(".global_message").slideUp();
	},3000);
	
}