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
    <title>话题-异形投票网</title>
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
          <script src ="<%=basePath%>inc/js/common.js"></script>
        <script SRC="dwr/engine.js"></script>
	<script SRC="dwr/util.js"></script>
	<script SRC="dwr/interface/discuzForum.js"></script>
		<script SRC="dwr/interface/user.js"></script>
    <script>
    $(function(){
    	$(".circleHead .mn").click(function(){

    		if($(".hidden_mn").css("display")=="none")
    			$(".hidden_mn").slideDown();
    		else
    			$(".hidden_mn").slideUp();
    	});

    })	;
    function hou(){
    	history.go(-1);
    }
    
    function refresh(){
    	location.reload(true);
    }
      $(function(){
    	   $("#response").click(function(){
    		   $(this).html('<i class="fa fa-spin fa-refresh fa-1x"></i>');
    		   user.getUserState(function(commonUser){
    				 if(!commonUser){
    					   location.href="user!login?old_url="+window.location.href;
    				  }
    				 else
    					 {
    		    		  var id=$("#response").attr("data-id");
    		    		  var content = $("input[name='content']").val();
    		    		  if(content.length<2){
    		    			 message("回复语句太短了啊");  
    		    		  }else{
    		    		  discuzForum.response(id,content,function(result){
    		    			  if(result[0]=="1"){
    		    				  refresh();
    		    			  }else
    		    			  {
    		    				  message(result[1]);
    		    			  }
    		    			  
    		    		  });
    		    		  }
    		    		  $("#response").html("回复"); 
    					 }
    			 });
    		  
    	   });
    	   $(".zan").click(function(){
    		  var moth = $(this).attr("data-moth");
    		  var id = $(this).attr("data-id");
    		  var dom = $(this);
    		  if(id!=null &&moth == 0){
    			  discuzForum.zan(id,function(result){
    				  if(result){
    					  dom.addClass("active");
    					  dom.find("i").removeClass("fa-heart-o").addClass("fa-heart");
    					  var num =Number(dom.find("b").html())+1;
    					  dom.find("b").html(num);
    					  dom.attr("data-moth",1);
    				  }else
    						message("没有登录或出现错误");
    			  });
    		  }
    		  else if(id!=null &&moth == 1){
                 discuzForum.cancleZan(id,function(result){
                	 if(result){
                		 dom.removeClass("active");
   					  dom.find("i").removeClass("fa-heart").addClass("fa-heart-o");
					  var num =Number(dom.find("b").html())-1;
					  dom.find("b").html(num);
					  dom.attr("data-moth",0);
   				    }else
   				    	message("没有登录或出现错误");
    			  });
    		  }else{
    			  message("系统错误");
    		  }
    	   });
      });
    </script>
  </head>
  
  <body class=" index-body" style="padding:0;">
  	<div class="global_message bg"></div>
	       <div class="container-full">
	       <header class="circleHead bg tc">
	         <span class="mn"><i class="fa fa-align-justify "></i></span>
	         <span class="title"><s:property value="circleArticleIndex.circle.name"/></span>
	         
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
	     <s:if test="circleArticleIndex != null">
	     <article class=" bg-white" style="border:1px solid #ccc;">
	           <div class="media m0 " style="padding:10px;">
			  <div class="media-left">
			    <a href="discuzPortal!circleIndex?circleId=<s:property value="circleArticleIndex.circle.id"/>">
			      <img class="media-object w60" src="<%=basePath%>inc/image/circle/<s:property value="circleArticleIndex.circle.pic"/>" alt="<s:property value="circleArticleIndex.circle.name"/>">
			    </a>
			  </div>
			  <div class="media-body" style="width:100%; color:#ccc;">
			    <p class="media-heading line fs12" style="width:100%;color:#000;"><s:property value="circleArticleIndex.circle.name"/>
			    </p>
			    <s:property value="circleArticleIndex.circle.shortMessage"/>
              </div>
			  </div>
	        </article>
	        
	     </s:if>
	   </div>  
	   	     <s:if test="circleArticleIndex != null">
	      <div class="container mt15">
	         <article class="article bg-white">
	             <header class="p10 fs12 bb" style="color:#000;"><span><s:property value="circleArticleIndex.circleArticle.title"/></span></header>
	             <div class="media mt10">
	                 <div class="media-left">
					    <a href="#">
					      <img class="media-object w60" src="<%=basePath%>inc/image/avator/<s:property value="circleArticleIndex.circleArticle.writer.pic"/>/small.jpg" alt="<s:property value="circleArticleIndex.circleArticle.writer.name"/>">
					    </a>
			        </div>
			        <div class="media-body" style="width:100%; color:#ccc;padding-right:15px;">
					    <p class="media-heading fs10" style="color:#000;margin-bottom:15px;"><s:property value="circleArticleIndex.circleArticle.writer.name"/>(<s:property value="circleArticleIndex.circleArticle.writer.college"/>)
					    <span class="fs8" style="color:#36be66;">楼主</span>
					    <span class="fs8 zan" style="float:right;" data-moth="0" data-id="<s:property value="circleArticleIndex.circleArticle.id"/>"><i class="fa fa-heart-o"></i><b><s:property value="circleArticleIndex.circleArticle.zan"/></b></span>
					    </p>
					    <s:date name="circleArticleIndex.circleArticle.addDate" format="yyyy-MM-dd  hh:mm:ss"/> <s:property value="circleArticleIndex.circleArticle.response"/>条回复
                   </div>
	             </div>
	             <div class="p10 content">
	                <span class="fs12"><s:property value="circleArticleIndex.circleArticle.content"/></span>
	                <s:iterator value="circleArticleIndex.circleArticle.images" var="a">
	                <img style="width:100%;" alt="" src="<%=basePath%>upload/circle/image/<s:property value="#a.url"/>">
	                </s:iterator>
	             </div>
	         </article>
	         <div id="articleList" class="mb70">
	         <s:iterator value="circleArticleIndex.response" var="r">
	           <div class="responseList mt10">
	             <div class="responseList_left">
	              <a href="#">
					      <img class="media-object w60" src="<%=basePath%>inc/image/avator/<s:property value="#r.writer.pic"/>/small.jpg" alt="<s:property value="#r.writer.name"/>">
					      <span class="uid"><s:property value="#r.uid"/>楼</span>
				  </a>
	             </div>
	             <div class="responseList_right">
	               <div class="fs12"><s:property value="#r.content"/></div>
	               <div class="info mt20">
	               <span class="fs10" style="color:#ccc;"><s:property value="#r.writer.name"/> </span><s:if test="#r.writer.id==circleArticleIndex.circleArticle.writer.id"><span class="fs8" style="color:#36be66;">楼主</span></s:if>
	               <span class="fs10" style="color:#ccc;"><s:property value="#r.newTime"/></span>
	               <span class="fs8 zan" style="float:right;" data-moth="0" data-id="<s:property value="#r.id"/>"><i class="fa fa-heart-o"></i><b><s:property value="#r.zan"/></b></span>
	               </div>
	             </div>
	           </div>
	         </s:iterator>
	         </div>
	      </div>
	           <div class="bg-white response">
	              <div class="response-left"><input name="content"  placeholder="回复楼主："/></div>
	               <div class="response-right"><span id="response" data-id="<s:property value="circleArticleIndex.circleArticle.id"/>">回复</span></div>
	           </div>
	     </s:if>  
  </body>
</html>
