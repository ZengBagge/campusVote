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
    <title>圈子主页-异形投票网</title>
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
                  <script SRC="dwr/engine.js"></script>
		<script SRC="dwr/util.js"></script>
		<script SRC="dwr/interface/discuzPortal.js"></script>
		<script SRC="dwr/interface/discuzForum.js"></script>
    <script>

    $(function(){
    	$(".circleHead .mn").click(function(){

    		if($(".hidden_mn").css("display")=="none")
    			$(".hidden_mn").slideDown();
    		else
    			$(".hidden_mn").slideUp();
    	});
      
    	$(".circleMore").click(function(){
    		var page = $(this).attr("data-page");
    		var circleId = $(this).attr("data-id");
    		discuzPortal.getMoreCircleArticles(page,circleId,function(data){
    			 for(var i=0;i<data.length;i++){
                   var html =' <div class="media m0" style="border-bottom:1px solid #ccc;padding:10px 15px;">'+
                   '   <div class="media-left">'+
                   '  <a href="discuzPortal!circleArticle?circleArticleId='+data[i][3]+'">'+
                   '  <img class="media-object" src="<%=basePath%>inc/image/avator/'+data[i][1]+'/small.jpg" alt="'+data[2]+'">'+
                   '  </a>'+
                   ' </div>'+
                   '  <div class="media-body" style="width:100%;">'+
                   '   <p class="media-heading line fs12" style="width:100%;" onclick="goArticle('+data[i][3]+')">'+data[i][4]+'</p>'+
                   '   <div class="info mt8 " style="width:100%;float:left;color:#ccc"><span>'+data[i][2]+'    '+data[i][5]+'</span> <span style="float:right;"><i class="fa fa-comment-o"></i> '+data[i][6]+'</span>';
                  if(data[i][7]=="0"){
                	  html +=                    '   <i class="fa fa-arrow-circle-o-up top" data-id="<s:property value="#a.id"/>" onclick="toTop('+data[i][3]+')">置顶</i>'+
                      '   <i class="fa fa-trash-o del" data-id="<s:property value="#a.id"/>" onclick="del('+data[i][3]+')">删除</i> ';
                  }else{
                	  html +=                    '   <i class="fa fa-arrow-circle-o-up top" data-id="<s:property value="#a.id"/>" onclick="deltop('+data[i][3]+')">取消置顶</i>'+
                      '   <i class="fa fa-trash-o del" data-id="<s:property value="#a.id"/>" onclick="del('+data[i][3]+')">删除</i> ';
                  }
                  html +=
                   ' </div> </div>'+
                   ' </div>';
                   $("#list").append(html);
    			 }    		
        });
    })	;
    })	;    	
    function hou(){
    	history.go(-1);
    }
    
    function refresh(){
    	location.reload(true);
    }
    function editArticle(id){
    	if(id != null)
    	window.location.href="discuzForum!editArticle?circleId="+id;
    }
    function goArticle(id){
    	if(id != null)
    	window.location.href="discuzPortal!circleArticle?circleArticleId="+id;
    }
 function toTop(id){
   	 discuzForum.top(id,function(result){
   		 if(result){
   			 location.reload(true);
   		 }else{
   			 message("置顶失败");
   		 }
   	 });
   	}
function deltop(id){

      	 discuzForum.deltop(id,function(result){
      		 if(result){
      			 location.reload(true);
      		 }else{
      			 message("取消置顶失败");
      		 }
      	 });
      	}
function del(id){
         	 discuzForum.del(id,function(result){
         		 if(result){
         			$(".article"+id).remove();
         		 }else{
         			 message("删除失败");
         		 }
         	 });
         	}
    </script>
  </head>
  
  <body class=" index-body" style="padding:0;">
	       <div class="container-full">
	       <header class="circleHead bg tc">
	         <span class="mn"><i class="fa fa-align-justify "></i></span>
	         <span class="title"><s:property value="circleIndex.circle.name"/></span>
	         <span class="edit" onclick="editArticle(<s:property value="circleIndex.circle.id"/>)"><i class="fa fa-text-width"></i></span>
	       </header>
	       <div class="hidden_mn">
	          <ul class="m0 p0">
	          <s:if test="session.voteUserSession != null ">
	          <li><a href="discuzPortal"><i class="fa fa-user"></i><s:property value="session.voteUserSession.name"/></a></li>
	          </s:if>
	          <s:else>
	          <li><a href="userCenter!myCenter">个人中心</a></li>
	          </s:else>
	            <li><a href="discuzPortal">回首页</a></li>
	            <li><a href="discuzPortal!typeIndex">所有圈子</a></li>
	          </ul>
	       </div>
	     <s:if test="circleIndex != null">
	     <article class=" bg-white" style="border:1px solid #ccc;">
	           <div class="media m0 " style="padding:10px;">
			  <div class="media-left">
			    <a href="discuzPortal!circleIndex?circleId=<s:property value="circleIndex.circle.id"/>">
			      <img class="media-object w60" src="<%=basePath%>inc/image/circle/<s:property value="circleIndex.circle.pic"/>" alt="<s:property value="circleIndex.circle.name"/>">
			    </a>
			  </div>
			  <div class="media-body" style="width:100%; color:#ccc;">
			    <p class="media-heading line fs12" style="width:100%;color:#000;"><s:property value="circleIndex.circle.name"/>
			    </p>
			    <s:property value="circleIndex.circle.shortMessage"/>
              </div>
			  </div>
	        </article>
	     <article class=" bg-white ">
	         <div class=" m0  bg-white" style="" id="list">
	         <s:iterator value="circleIndex.pageBean.list" var="a">
	          <div class="media m0 article<s:property value="#a.id"/>" style="border-bottom:1px solid #ccc;padding:10px 15px;">
			  <div class="media-left">
			    <a href="discuzPortal!circleArticle?circleArticleId=<s:property value="#a.id"/>" >
			      <img class="media-object" src="<%=basePath%>inc/image/avator/<s:property value="#a.writer.pic"/>/small.jpg" alt="<s:property value="#a.writer.name"/>">
			    </a>
			  </div>
			  <div class="media-body" style="width:100%;"  >
			    <p class="media-heading line fs12" style="width:100%;" onclick="goArticle(<s:property value="#a.id"/>)"><s:property value="#a.title"/></p>
                <div class="info mt8 " style="width:100%;float:left;color:#ccc">
                <span><s:property value="#a.writer.name"/>   
                 <s:property value="#a.newTime"/></span>
                  <span style="float:right;"><i class="fa fa-comment-o"></i> <s:property value="#a.response"/></span>
                 <s:if test="#a.writer.id==circleIndex.userCommon.id or circleIndex.userCommon.rule.sort>2">
                   <s:if test ="#a.top == 0">
                   <i class="fa fa-arrow-circle-o-up top" data-id="<s:property value="#a.id"/>" onclick="toTop(<s:property value="#a.id"/>)">置顶</i>
                   <i class="fa fa-trash-o del" data-id="<s:property value="#a.id"/>" onclick="del(<s:property value="#a.id"/>)">删除</i>
                   </s:if><s:else>
                   <i class="fa fa-arrow-circle-o-down deltop" data-id="<s:property value="#a.id"/>" onclick="deltop(<s:property value="#a.id"/>)">取消置顶</i>
                   <i class="fa fa-trash-o del" data-id="<s:property value="#a.id"/>" onclick="del(<s:property value="#a.id"/>)">删除</i>
                   </s:else>
                 </s:if>
                 </div>
			  </div>
			</div>
	          </s:iterator>
	         </div> 
	     </article>
	     </s:if>
	     <s:else>

	     </s:else>
	   </div>    
	   <div class="circleFoot">
	   <span class="hou" onclick="hou()"><i class="fa fa-chevron-left fa-1x"></i></span>
	   <span class="refresh" onclick="refresh()"><i class="fa fa-refresh fa-1x"></i></span>
	   </div>
	   <s:if test="circleIndex.pageBean.list.size()>9">
	    <div class="container">
	     <button class="btn circleMore" data-page="<s:property value="page+1"/>" data-id="<s:property value="circleIndex.circle.id"/>">加载更多</button>
	    </div>
	   </s:if>
  </body>
</html>
