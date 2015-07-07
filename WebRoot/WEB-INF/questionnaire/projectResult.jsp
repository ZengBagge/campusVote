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
    <title>问卷数据-异形投票网</title>
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
		<script SRC="dwr/interface/questionnaireManage.js"></script>
		<script src ="<%=basePath%>inc/js/common.js"></script>
   <script>
   $(function(){
 	  var v=0;
 	  $(".util").click(function(){
 		  if(v==0)
 			 {
 		  $(this).next().slideDown();
 		  $(this).find("i").removeClass("fa-chevron-down").addClass("fa-chevron-up");
 		   v=1;}
 		  else{
 			  $(this).next().slideUp();
 			  $(this).find("i").addClass("fa-chevron-down").removeClass("fa-chevron-up");
    		   v=0;  
 		  }
 	  });
   });
   </script>
  </head>
  
  <body class="index-body">
  <jsp:include page="../header.jsp" flush="true" />
    <main class="container-full">
	          <s:if test="questionnaireProjectResult == null">
	          <div class="inner pb40 pt40"><span class="spanblock pb20 "><i class="icon80_smile w80 h80"></i></span><div class="msg_content"><h4>结果暂时没有出来哦</h4></div></div>
	          </s:if>
	       <s:else>
	       <header style="background:#3399FF;color:#fff;" class="p15">
	       <h3 class="fs15 tc"><s:property value="questionnaireProjectResult.questionnaireProject.title"/>结果分析</h3>
	       </header>
	      <article class="container mt10">
	       <ul class="list-group">
	        <li class="list-group-item" >
	           实名参与人数：<span class="badge bg" style="float:right"><s:property value="questionnaireProjectResult.personNumber"/>人</span>
	        </li>
	        <li class="list-group-item" >
	           匿名参与人数：<span class="badge bg"  style="float:right"><s:property value="questionnaireProjectResult.annoymityPersonNumber"/>人</span>
	        </li>
	        <li class="list-group-item" >
	           题目总量：<span class="badge bg"  style="float:right"><s:property value="questionnaireProjectResult.questionnaireNumber"/>道</span>
	        </li>
	        <li class="list-group-item" >
	           应获得数据量：<span class="badge bg"  style="float:right"><s:property value="questionnaireProjectResult.beforeAnswerNumber"/>条</span>
	        </li>
	        <li class="list-group-item" >
	          实际获得数据量：<span class="badge bg"   style="float:right"><s:property value="questionnaireProjectResult.afterAnswerNumber"/>条</span>
	        </li>
	        <li class="list-group-item tc bg-purple fs14" >
	          各题详细数据结果
	        </li>
	        	      <s:iterator value="questionnaireProjectResult.questionnaires" var="a">
	        <li class="list-group-item leve1" id="leve1_<s:property value="#a.id"/>" data-id="<s:property value="#a.id"/>">
	            <a class="leve1_a"><s:if test="#a.model==0"><font style="color:red;">*</font></s:if><s:property value="#a.uid"/>:<s:property value="#a.title"/><span class="biaoqian"><s:if test="#a.type==0">单选题</s:if><s:elseif test="#a.type==1">多选题</s:elseif><s:elseif test="#a.type==2">简答</s:elseif><s:elseif test="#a.type==3">多选简答</s:elseif></span></a>
	            <span class="util"><i class="fa fa-chevron-down"></i></span>
	            <ul class="p0 dn">
	            <s:iterator value="#a.questionnaireOptions" var="q">
	            <li class="list-group-item">
	            <s:property value="#q.uid"/>:<s:property value="#q.body"/><span style="" class="bg badge">支持率：<s:property value="#q.number"/>/<s:property value="#a.number"/></span>
	            </li>
	            </s:iterator>
	            <s:if test="#a.type==2">
	            
	                
	            </s:if>
	            </ul>
	           
	        </li>
	      </s:iterator>
	      </ul>
	      </article> 
	      
	     </s:else> 
 </main>
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
