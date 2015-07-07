$(function(){
	
	$(".vote_upload").click(function(){
	   
		if(tou){//验证时间
		   toupiao($(this).attr("data-id"),$(this));
	    }
	   else
		   alert("本时刻禁止投票");
	});	
});

function toupiao(id,dom){
if(id != "0" && id !=0){	
    voteCon.toupiao(id,function(result){
    	if(result==3){
    		message("投票已记录");
    		dom.html("已投票");
    		dom.attr("data-id","0");
        }else if(result==0)
        {
    		message("投票失败");
        }else if(result == 2){
        	message("已经投过票了，感谢你的支持");
        }else if(result == -2){
        	message("活动还没有开始哦");
        }else if(result == -1){
        	message("活动已经结束了哦");
        }
        else {
        	location.href="user!login?old_url="+window.location.href;
        }
    });
}else
	message("不能连续投票");
	
}
