<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	         <header class=" bg-white">
	            <div class="menu row">
	                <div class="col-xs-3 menu-list">
	                     <a href="index">
	                       <span class="circle100 bg-yellow fs15">异</span>
	                       <span class="fs8 ws tc">活动主页</span>
	                     </a>  
	                </div>
	                 <div class="col-xs-3 menu-list">
	                 <a href="vote!index">
	                       <span class="circle100 bg-green fs15">形</span>
	                       <span class="fs8 ws tc">微投票</span>
	                  </a>     
	                </div>
	                 <div class="col-xs-3 menu-list">
	                 <a href="questionnaire!index">
	                       <span class="circle100 bg-purple fs15">投</span>
	                       <span class="fs8 ws tc">微问卷</span>
	                  </a>     
	                </div>
	                 <div class="col-xs-3 menu-list">
	                  <a href="discuzPortal">
	                       <span class="circle100 bg-yellow fs15">票</span>
	                      <span class="fs8 ws tc">微圈子</span>
	                  </a>     
	                </div>
	            </div>
	         </header>
	<div class="global_message bg"></div>