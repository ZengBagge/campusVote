<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<header class="menu-footer">
           <ul class="nav p0 m0">
             <li class="nav-menu-li "><a href="index" title="主页"  class="fs8"><i class="fa fa-university fa-2x"></i><br/>活动主页</a></li>
              <li class="nav-menu-li "><a href="voteManage!addVoteProject"  onclick="addVote()" class="fs8"><i class="fa fa-edit  fa-2x"></i><br/>发起投票</a><br/></li>
               <li class="nav-menu-li "><a href="questionnaireManage!addQuestionnaireProject" onclick="" title="" class="fs8"><i class="fa fa-group  fa-2x"></i><br/>发起调查</a><br/></li>
                <li class="nav-menu-li "><a href="userCenter!myCenter" title="" class="fs8"><i class="fa fa-user fa-2x"></i><br/>个人中心</a></li>
           </ul>
</header>
