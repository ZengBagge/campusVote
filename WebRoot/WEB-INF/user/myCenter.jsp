<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
   <html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>会员之家|异形投票网</title>
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="expires" content="0">    
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
	       <div class="container">
	       <div class="info">
	          <h4 class="title pb10 mb0">动态</h4>
	         <table class="table table-hover bg-white userInfo">
	          <tr class="p10">
	          <s:action name="voteManage!getMyProjectNum" var="v"></s:action>
	           <s:action name="questionnaireManage!getMyProjectNum" var="q"></s:action>
	          <td class="name" style="color:#FF0033  "><i class="fa fa-toggle-right" style="color:#FF0033  "></i>我的投票</td><td class="value tr"><a href="voteManage!getMyProject" class="project-door"><s:property value="#v.projectNum"/>个投票活动<i class="fa fa-arrow-circle-right"></i></a></td>
	          </tr>
	          <tr class="p10">
	          <td class="name" style="color:#FF0099   "><i class="fa fa-users" style="color:#FF0099   "></i>我的问卷</td><td class="value tr"><a href="questionnaireManage!getMyProject" class="project-door"><s:property value="#q.projectNum"/>个问卷活动<i class="fa fa-arrow-circle-right"></i></a></td>
	          </tr>
	           <tr class="p10">
	          <td class="name" ><i class="fa fa-at" ></i>投票记录</td><td class="value tr"><a href="userCenter!voteInfo" class="project-door">查看<i class="fa fa-arrow-circle-right"></i></a></td>
	          </tr>
	         </table>
	      </div>   
	       <div class="info">
	          <h4 class="title pb10 mb0">基本信息</h4>
	         <table class="table table-hover bg-white userInfo">
	          <tr class="p10">
	          <td class="name">真实姓名</td><td class="value"><s:if test="userCommon.name == null">(未填写)</s:if><s:else><s:property value="userCommon.name"/></s:else></td>
	          <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	          <tr class="p10">
	             <td class="name">性别</td><td class="value"><s:if test="userCommon.sex == 0">男</s:if><s:else>女</s:else></td>
	           <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	          <tr class="p10">
	             <td class="name">积分</td><td class="value"><s:if test="userCommon.point == null">(未填写)</s:if><s:else><s:property value="userCommon.point"/></s:else></td>
	           <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	           <tr class="p10">
	             <td class="name">座右铭</td><td class="value"><s:if test="userCommon.signature == null">(未填写)</s:if><s:else><s:property value="userCommon.signature"/></s:else></td>
	           <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	         </table>
	      </div>   
	      <div class="info">
	          <h4 class="title pb10 mb0">教育信息</h4>
	         <table class="table table-hover bg-white userInfo">
	          <tr class="p10">
	          <td class="name">学校</td><td class="value"><s:if test="userCommon.school == null">(未填写)</s:if><s:else><s:property value="userCommon.school.title"/></s:else></td>  
	           <td></td></tr>
	          <tr class="p10">
	             <td class="name">学院</td><td class="value"><s:if test="userCommon.college == null">(未填写)</s:if><s:else><s:property value="userCommon.college"/></s:else></td>
	              <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	            <tr class="p10">
	             <td class="name">学号</td><td class="value"><s:if test="userCommon.uid == null">(未填写)</s:if><s:else><s:property value="userCommon.uid"/></s:else></td>
	              <td></td>
	          </tr>
	            <tr class="p10">
	             <td class="name">入学年份</td><td class="value"><s:if test="userCommon.year == null">(未填写)</s:if><s:else><s:property value="userCommon.year"/>年</s:else></td>
	              <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	         </table>
	      </div>  
          <div class="info">
	          <h4 class="title pb10 mb0">联系方式</h4>
	         <table class="table table-hover bg-white userInfo">
	          <tr class="p10">
	          <td class="name">邮箱</td><td class="value"><s:if test="userCommon.mail == null">(未填写)</s:if><s:else><s:property value="userCommon.mail"/></s:else></td>
	           <td><i class="fa fa-cog fa-1x" ></i></td>
	          </tr>
	         </table>
	      </div>   
	      <a class="btn btn-lg  bg" style="width:100%" href="user!logout"> 退出登录</a>
	       </div>
	       	        <footer class="common-footer bg-green">
	          <h4 class="fs10">湖南科技异形网络工作室（SRIP项目）</h4>
	        </footer>
  </body>
</html>
