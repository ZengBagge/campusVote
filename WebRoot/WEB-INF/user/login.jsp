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
    <title>手机统一登录|异形投票网</title>
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
    <script>
    function  showRegit(){
        $("#login").hide();
        $("#reg").show();
    }
    function showLogin(){
            $("#login").show();
        $("#reg").hide();
    }
    </script>
  </head>
  
  <body class="index-body">
  
  <jsp:include page="../header.jsp" flush="true" />
	        <div class="container mt30" id="login">
	            <form action="user!loginForm"  method ="post" id="loginFormAction">
	                  <div class="login-content">
	                    <input class="form-control first fs15"  name ="uid" type="text" placeholder="请输入学号"  value='<s:property value="#cookie.user.cookie.uid"/>'/>
	                    <input  class="form-control last fs15" name ="pwd" type="password" placeholder="请输入密码" value=""/>
	                   <button type="submit" class="btn btn-lg mt20 bg" id="loginForm">登录</button>
	                    <span id="validerrmsg" class="help-block" style="color: #FF0000;"><s:property  value="errorMesageString" /></span>
	                   <div class="foot mt20">
	                   <span class="first"><a href="">忘记密码</a></span>
	                    <span><a href="javascript:void" onclick="showRegit()">注册新帐号</a></span>
	                  
	                   </div>
	                  <div class="open" style="display:none;"><a href="<s:property  value="weiurl" />"><span class="login"><i class="fa fa-wechat fa-2x"></i></span><span>微信登录</span></a></div>
	                  </div>
	                  
	            </form>
	        </div>
	        <div class="container mt30" style="display:none;" id="reg">
	            <form action="user!regForm"  method ="post" id ="login" id="regFormAction">
	                  <div class="login-content">
	                    <select name ="school" class="form-comtrol bg-white fs15">
	                     <option value="1">湖南科技大学</option>
	                    </select>
	                    <input class="form-control first fs15"  name ="uid" type="text" placeholder="请输入学号" />
	                    <input  class="form-control last fs15" name ="pwd" type="password" placeholder="请输入密码" />
	                   <button type="submit" class="btn btn-lg mt20 bg" id="regForm">注册</button>
	                    <span id="validerrmsg" class="help-block" style="color: #FF0000;"><s:property  value="errorMesageString" /></span>
	                    <div class="foot mt20">
	                   <span class="first"><a href="javascript:void" onclick="showLogin()">已有帐号，返回登录</a></span>
	                   </div>
	                 
	                  </div>
	                  
	            </form>
	        </div>
	        <footer class="common-footer bg-green">
	          <h4 class="fs10">湖南科技异形网络工作室（SRIP项目）</h4>
	        </footer>
  </body>
</html>
