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
    <title><s:property value="project.title"/>-异形投票网</title>
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="<s:property value="project.title"/>,异形网络工作室，基于校园，投票，问卷">
	<meta name="description" content="<s:property value="project.explains"/>">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/style.css">
	<script src ="<%=basePath%>inc/js/jquery.min.js"></script>
    <script src ="<%=basePath%>inc/js/bootstrap.min.js"></script>
    <script src ="<%=basePath%>inc/js/common.js"></script>
    <link href="<%=basePath%>inc/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript">
    var timerc=0;
    var a="";
    var tou = false; 
    $(function() { 
     var startDate = new Date("<s:date name="project.beginDate" format="yyyy/MM/dd hh:mm:ss"/>");
       var endDate = new Date("<s:date name="project.endDate" format="yyyy/MM/dd hh:mm:ss"/>");
       var now= new Date();
      if(startDate.getTime()>now.getTime())
    	  {
		   timerc=(startDate.getTime()-now.getTime())/1000; //全局时间变量（秒数）
		   a="活动开始倒计时：";
    	  }  
      else if(now.getTime()>startDate.getTime() && now.getTime()<endDate.getTime())
    	  {
    	   timerc=(endDate.getTime()-now.getTime())/1000;
		   a="活动结束倒计时：";
		   tou = true;
    	  }
      else{
    	   $("#date").html("（投票已结束）");
      }
       add(); //首次调用add函数
    }); 
	  function add(){ //加时函数
	        if(timerc > 0){ //如果不到3分钟
	            --timerc; //时间变量自剪1
	            var dd = parseInt(timerc/60/60/24)+"天";
	                var hh =parseInt((timerc/60/60)%24)+"时"; //写入小时数
	            var min = parseInt((timerc/60)%60)+"分"; //写入分钟数
	            var sec =parseInt(timerc%60/10).toString()+parseInt(timerc%10)+"秒"; //写入秒数（两位）
	             $("#date").html(a+dd+hh+min+sec);
	            setTimeout("add()", 1000); //设置1000毫秒以后执行一次本函数   
	       }
	    };
   </script>
        <script SRC="dwr/engine.js"></script>
		<script SRC="dwr/util.js"></script>
		<script SRC="dwr/interface/voteCon.js"></script>
		<script src ="<%=basePath%>inc/js/vote.js"></script>
  </head>
  <body class="index-body">
  	<div class="global_message bg"></div>
   <header class="bg-green pt10  m0 project-header">
       <h2 style="text-align:center;color:#CCFF00;font-weight:600;" class="fs14 m0"><s:property value="project.title"/></h2>
       <h4  style="text-align:center;color:#fff" class="fs10 m10">主办：<s:property value="project.organization"/></h4>
         <h4  style="text-align:center;color:#fff" class="fs10 m10" id="date"></h4>
   </header>
   <main class="container">
      <div class="row projectInfo mt15">
         <div class="col-xs-6 info">
             <div class="yj10 bg-white p10">
             <h5 class="title m0">活动介绍</h5>
             <h6 class="content"><s:property value="project.explains"/></h6>
             </div>
         </div>
          <div class="col-xs-6 info">
              <div class="yj10 bg-white p10">
             <h5 class="title m0">活动说明</h5>
              <h6 class="content"><s:property value="project.attention"/></h6>
              </div>
         </div>
      </div>
      <div class="row mt15">
          <s:action name="personVote!getPersons"  var="p">
          <s:param value="projectId" name="projectId"></s:param>
          </s:action>
          <s:iterator value="#p.list" var="pl">
		         <div class="col-md-3 col-xs-6 col-sm-6 vote_con">
		         <div class="thumbnail t300 ">
		         <a href="" target="_blank"><s:iterator value="#pl.picList" var="pp"><img src="<%=basePath%>upload/image/<s:property value="#pp.url"/>" class="img-responsive" alt="<s:property value="#pl.name"/>" style="width:100%;"></s:iterator></a>
		         <div class="caption m0 p0 mt5" style="position:relative;padding:0px;">
		           <div class="info">
		          <span><s:property value="#pl.name"/></span><br/>
		          <span><s:property value="#pl.college"/></span>
		         </div>
		           <button class="vote_buttoon yj5 vote_upload" data-id="<s:property value="#pl.vote.id"/>">投票（<s:property value="#pl.vote.poll"/>）</button>
		         </div>
		        </div>
		       </div>
		  </s:iterator>     
      </div>
   </main>
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
