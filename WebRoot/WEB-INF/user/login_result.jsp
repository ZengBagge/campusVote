<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String url = (String)session.getAttribute("old_url");
if(url !=null)
response.sendRedirect(url);
else
response.sendRedirect(path);
 %>
