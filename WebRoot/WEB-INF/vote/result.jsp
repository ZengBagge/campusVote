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
    <title>报名会员-异形投票网</title>
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
  </head>
  
  <body class="index-body">
  <jsp:include page="../header.jsp" flush="true" />
    <main class="container-full">
	  <table class="table">
	         <tbody>
	       <s:if test="voteResults.size()==0">
	       <tr class="bg-white">
	        <td style="width:100%;text-align:center" >暂无结果产生</td>
	        </tr>
	       </s:if>
	       <s:else>  
	       <tr>
	        <th>投票体</th>
	        <th>支持率</th>
	        <th>总票数</th>
	       </tr>
	      <s:iterator value="voteResults" var="a">
	           <tr class="bg-white">
	              <td style="width:40%"><s:property value="#a.name"/></td>
	              <td style="width:40%">
	              <div class="progress m0">
                      <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:  <s:property value="#a.proporion"/>%;">
                      <s:property value="#a.proporion"/>%
                   </div>
                 </div>
	              </td>
	              <td style="width:20%;text-align:center;"><s:property value="#a.number"/>票</td>
	           </tr>
	      </s:iterator>
	     </s:else> 
	 </tbody>
 </table> 	    
 </main>  
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
