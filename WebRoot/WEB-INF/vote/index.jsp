<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts2.components.Include"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
   <html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>投票首页-异形投票网</title>
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="异形网络工作室，基于校园，投票，问卷">
	<meta name="description" content="异形网络工作室，基于校园，投票，问卷，异形网络工作室是湖南科技大学学生创业工作室。">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/common.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/style.css">
			<script src ="<%=basePath%>inc/js/jquery.min.js"></script>
    <script src ="<%=basePath%>inc/js/bootstrap.min.js"></script>
    <link href="<%=basePath%>inc/css/font-awesome.min.css" rel="stylesheet">
    <script>
    $(function(){
    $(".menu_fu").on("click","li",function(){
  	  var moth=$(this).attr("data-moth");
  	  if(moth=="apply")
  		{
  		  window.location.href="voteManage!apply?projectId="+voteProjectId;
  		}

   });
    $(".userpic li").each(function(){
        var width=$(this).find("img").css("width");
       $(this).find("img").css("height",width);
       $(".user_more").css("height",width).css("line-height",width);
     });
    $(".more").click(function(){
   	 window.location.href="vote!allUser?projectId="+$(this).attr("data-id");
    });
});
    
    function vote(id){
    	 window.location.href="vote!project?projectId="+id;
    }
    function apply(id){
    	 window.location.href="voteManage!apply?projectId="+id;
    }
function allUser(id){
  	  window.location.href="vote!allUser?projectId="+id;
}
$(function(){
	var $this = $(".av");
	var scrollTimer;
	$this.hover(function(){
	clearInterval(scrollTimer);
	},function(){
	scrollTimer = setInterval(function(){
	scrollNews( $this );
	}, 3000 );
	}).trigger("mouseout");
	});
	function scrollNews(obj){
	var $self = obj.find("ul:first");
	var lineHeight = $self.find("li:first").height();
	$self.animate({ "margin-top" : -lineHeight +"px" },600 , function(){
	$self.css({"margin-top":"0px"}).find("li:first").appendTo($self);
	});
	} 
    </script>
  </head>
  
  <body class="index-body">
  <jsp:include page="../header.jsp" flush="true" />
	         <header class="notify bg-white">
	         <div class="bg-white row m0 "><span class="col-xs-1 m0 p0" style=" text-align:center;color:#e7e8eb;"><i class="fa fa-volume-up  fa-2x" style="color:#36be66"></i></span>
	            <div class="col-xs-11 h25 av m0 pl5">
	            <ul class="m0 p0">
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            </ul>
	            </div>
	         </div>
	         </header>
	         <s:if test="pageBeanUnderway.list.size()>0">
	         	    <header class="index-title bg-white mt10">
	                 <h4 class="fs15">投票活动</h4>
	              </header>
	         </s:if>
	        <main class="container">
	        <s:iterator value="pageBeanUnderway.list" var="p"> 
           <article class="projectList bg-white yj5" >
	         <header onclick="vote(<s:property value="#p.id"/>)"><span class="fs10 lineTitle"><i class="fa fa-diamond" style="color:#00FF66 "></i><s:property value="#p.title"/></span><i  class="fa fa-chevron-down cp edit_menu" style="float:right;color:#FF0000;line-height:35px;"data-toggle="modal" data-target=".bs-example-modal-sm" data-id="<s:property value="#p.id"/>"> </i></header>
	         <div class="content">
	           <div class="row h20  lh20"><div class="name col-xs-5"><i class="fa fa-heart-o" style="color:#99FF33 "></i><span>举办方</span></div><div class="value col-xs-7"><s:property value="#p.organization"/></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>开始时间</span></div><div class="value col-xs-7"><s:date name="#p.beginDate" format="yyyy-MM-dd HH:mm:ss" /></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>结束时间</span></div><div class="value col-xs-7"><s:date name="#p.endDate" format="yyyy-MM-dd HH:mm:ss" /></div></div>
	          <div class="row h20 lh20"> <div class="name col-xs-5"><i class="fa fa-star-o" style="color:#FF0066 "></i><span>状态</span></div><div class="value col-xs-7"><s:if test="#p.isOpen == 0">编辑中</s:if><s:elseif test="#p.isOpen == 1">可报名</s:elseif><s:else>投票中</s:else></div></div>
	          <div class="row">
	           <s:if test="#p.voteType.typeEntity=='PersonVote' || #p.voteType.typeEntity=='TeamVote'">
	            <ul class="userpic col-xs-8">
	            <s:action name="vote!getProjectVoteUser" var="u">
	                <s:param name="projectId"  value="#p.id"></s:param>
	            </s:action>
	            <s:iterator value="#u.users" var="pb" status="aa">
	            <s:if test="#aa.index lt 6">
	            <li class="col-xs-2"><a href="<s:property value="#pb.id"/>"><img src="<%=basePath%>inc/image/avator/<s:property value="#pb.pic"/>/small.jpg"/></a></li>
	            </s:if>
	            </s:iterator>
	            </ul>
	            <div class="col-xs-3 p3 more" data-id="<s:property value="#p.id"/>" >
	            <span class="user_more yj10 col-xs-12"><i class="fa fa-users"></i><s:property value="#u.users.size()"/></span>
	            </div>
	            </s:if>
	          </div>
	          <div class="row cz">
	          <span onclick="guanzhu()" class="">关注活动</span>
	          <span onclick="vote(<s:property value="#p.id"/>)" class="mr20 bu">查看活动</span>
	          </div>
	         </div>
	       </article>
	       </s:iterator>
	       </main>
	       <s:if test="pageBeanApply.list.size()>0">
	        <header class="index-title bg-white mt10">
	                 <h4 class="fs15">参加投票</h4>
	        </header>
	        </s:if>      
	        <main class="container">      
	       	        <s:iterator value="pageBeanApply.list" var="p"> 
           <article class="projectList bg-white yj5">
	         <header><span class="fs10 lineTitle"><i class="fa fa-diamond" style="color:#00FF66 "></i><s:property value="#p.title"/></span><i  class="fa fa-chevron-down cp edit_menu" style="float:right;color:#FF0000;line-height:35px;"data-toggle="modal" data-target=".bs-example-modal-sm" data-id="<s:property value="#p.id"/>"> </i></header>
	         <div class="content">
	           <div class="row h20  lh20"><div class="name col-xs-5"><i class="fa fa-heart-o" style="color:#99FF33 "></i><span>举办方</span></div><div class="value col-xs-7"><s:property value="#p.organization"/></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>开始时间</span></div><div class="value col-xs-7"><s:date name="#p.beginDate" /></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>结束时间</span></div><div class="value col-xs-7"><s:date name="#p.endDate" /></div></div>
	          <div class="row h20 lh20"> <div class="name col-xs-5"><i class="fa fa-star-o" style="color:#FF0066 "></i><span>状态</span></div><div class="value col-xs-7"><s:if test="#p.isOpen == 0">编辑中</s:if><s:elseif test="#p.isOpen == 1">可报名</s:elseif><s:else>投票中</s:else></div></div>
	          <div class="row">
	            <ul class="userpic col-xs-8">
	            <s:action name="vote!getProjectVoteUser" var="u">
	                <s:param name="projectId"  value="#p.id"></s:param>
	            </s:action>
	            <s:iterator value="#u.users" var="pb" status="aa">
	            <s:if test="#aa.index lt 6">
	            <li class="col-xs-2"><a href="<s:property value="#pb.id"/>"><img src="<%=basePath%>inc/image/avator/<s:property value="#pb.pic"/>/small.jpg"/></a></li>
	            </s:if>
	            </s:iterator>
	            </ul>
	            <div class="col-xs-3 p3 more" data-id="<s:property value="#p.id"/>" >
	            <span class="user_more yj10 col-xs-12"><i class="fa fa-users"></i><s:property value="#u.users.size()"/></span>
	            </div>
	          </div>
	          <div class="row cz">
	          <span onclick="guanzhu()" class="">关注活动</span>
	          <span onclick="apply(<s:property value="#p.id"/>)" class="mr20 bu">我要报名</span>
	          </div>
	         </div>
	       </article>
	       </s:iterator>
	       </main>
  	   <!--浮窗操作  -->
			 <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		        <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="gridSystemModalLabel">编辑项目</h4>
              </div>
              <ul class="list-group menu_fu">
                 
				  <li class="list-group-item cp tc" data-id="" data-moth="apply">我要报名</li>
			</ul>
		    </div>
		  </div>
		</div>	       
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
