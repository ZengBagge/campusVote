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
    <title>问卷主页-异形投票网</title>
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
    <script type="text/javascript">
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
function look(id){
    		  window.location.href="questionnaire!project?projectId="+id;
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
	<s:action name="questionnaire!getIndexProject" var="q"></s:action>    
	   <main class="container">
	      <s:iterator value="#q.pageBean.list" var="p"> 
	       <article class="projectList bg-white yj5">
	         <header onclick="watch(<s:property value="#p.id"/>)"><i class="fa fa-book" style="color:#00FF66 "></i><span class="fs10"><s:property value="#p.title"/></span></header>
	         <div class="content">
	           <div class="row h20  lh20"><div class="name col-xs-5"><i class="fa fa-heart-o" style="color:#99FF33 "></i><span>举办方</span></div><div class="value col-xs-7"><s:property value="#p.organization"/></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>开始时间</span></div><div class="value col-xs-7"><s:date name="#p.beginDate" /></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>结束时间</span></div><div class="value col-xs-7"><s:date name="#p.endDate" /></div></div>
	          <div class="row h20 lh20"> <div class="name col-xs-5"><i class="fa fa-star-o" style="color:#FF0066 "></i><span>状态</span></div><div class="value col-xs-7 isOpen"><s:if test="#p.isOpen == 0">编辑中</s:if><s:elseif test="#p.isOpen == 1">可报名</s:elseif><s:else>活动进行中</s:else></div></div>
	          <s:if test="#p.isOpen == 2">
	          <div class="row cz">
	          <span onclick="shoucang(<s:property value="#p.id"/>)" class="">收藏</span>
	          <span onclick="look(<s:property value="#p.id"/>)" class="mr20 bu">查看活动</span>
	          </div>
	          </s:if>
	         </div>
	       </article>
	       </s:iterator>
	   </main>
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
