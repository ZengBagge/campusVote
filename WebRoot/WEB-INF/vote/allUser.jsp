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
	       <s:if test="allUser.size()==0">
	       <tr class="bg-white">
	        <td style="width:100%;text-align:center" >暂无同学报名</td>
	        </tr>
	       </s:if>
	       <s:else>  
	      <s:iterator value="allUser" var="a">
	           <tr class="bg-white">
	              <td style="width:100px"><a href="#"><img style="width:100%;" src="<%=basePath%>inc/image/avator/<s:property value="#a.pic"/>/middle.jpg" class="yj10"></a></td>
	              <td>
	                <span class="lh42"><s:property value="#a.name"/> <s:if test="#a.sex==0"><i class="fa fa-mars lh42" style="color:#0066FF "></i></s:if><s:else><i class="fa fa-venus" style="color:#FF6699 "></i></s:else></span>
	                <br/><span class="lh42"><s:property value="#a.college"/></span>
	              </td>
	              <td></td>
	           </tr>
	      </s:iterator>
	     </s:else> 
	 </tbody>
 </table> 	    
 </main>  
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
