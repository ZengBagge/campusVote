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
    <script type="text/javascript">
      function add(projectId){
    	  window.location.href="voteManage!apply?projectId="+projectId;
       }
    </script>
  </head>
  
  <body class="index-body">
  <jsp:include page="../header.jsp" flush="true" />
    <main class="container-full">
    <s:if test="voteProject.voteType.typeEntity=='WordVote'">
     <table class="table">
	         <tbody>
	          <s:if test="allUser.size()==0">
	       <tr class="bg-white">
	        <td style="width:100%;text-align:center" >暂无投票项</td>
	        </tr>
	       </s:if>
	       <s:else>
	        <tr class="bg-white">
	              <th style="width:20%" class="sortInput">编号</th>
	               <th style="width:60%;text-align:center;">投票体</th>
	              <th style="width:20%">操作</th>
	           </tr>  
	      <s:iterator value="allUser" var="a">
	           <tr class="bg-white">
	                <td style="width:20%" class="sortInput"><input name="sortNumber[<s:property value="#a.id"/>]" value="<s:property value="#a.sortNumber"/>" ></td>
	                <td style="width:60%"><a href="#"><s:property value="#a.content"/></a></td>
	                <td style="width:20%;text-align:center;"><a href="voteManage!applyEdit?vid=<s:property value="#a.id"/>&projectId=<s:property value="projectId"/>">修改</a></td>
	           </tr>
	      </s:iterator>
	     </s:else> 
	 </tbody>
 </table> 	 
    </s:if>
    <s:elseif test="voteProject.voteType.typeEntity=='PersonVote'">
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
	                <span class="lh50"><s:property value="#a.name"/> <s:if test="#a.sex==0"><i class="fa fa-mars lh50" style="color:#0066FF "></i></s:if><s:else><i class="fa fa-venus" style="color:#FF6699 "></i></s:else></span>
	                <br/><span class="lh50"><s:property value="#a.college"/></span>
	              </td>
	              <td></td>
	           </tr>
	      </s:iterator>
	     </s:else> 
	 </tbody>
 </table> 	 
 </s:elseif>
 </main>
 <div class="container mt15">
  <button class="btn btn-lg mt20 bg" style="width:100%" onclick="add(<s:property value="projectId"/>)" data-id="<s:property value="projectId"/>">添加投票体</button>  
 </div>  
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
