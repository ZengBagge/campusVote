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
    <title>问卷题目-异形投票网</title>
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
    <script type="text/javascript">
      function add(projectId){
    	  window.location.href="questionnaireManage!apply?projectId="+projectId;
       }
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
      function del(id){
    	  if(confirm("确认删除本题么？")){
    		  questionnaireManage.del(id,function(result){
    			  if(result){
    				  message("删除成功");
    				  $("#leve1_"+id).remove();
    			  }
    			  else
    				  message("删除失败");
    		  });
    	  }
      }
      function edit(id){
    	  window.location.href="questionnaireManage!edit?questionnaireId="+id;
      }
      function move(id){
    	  if($("#leve1_"+id).prev(".leve1")!=null && $("#leve1_"+id).prev(".leve1").attr("data-id") != null)
    		  {
    		    var beforeId=$("#leve1_"+id).prev(".leve1").attr("data-id");
    		    questionnaireManage.move(id,beforeId,function(result){
      			  if(result){
      				  message("移动成功");
      				 exchange($("#leve1_"+id),$("#leve1_"+id).prev(".leve1"));
      			  }
      			  else
      				  message("移动失败");
      		  });
    		  }
    	  else{
    		  message("已经最前面了！");
    	  }
      }
      function exchange(a,b){
          var n = a.next(), p = b.prev();
          b.insertBefore(n);
          a.insertAfter(p);
          }; 
    </script>
  </head>
  
  <body class="index-body">
  <jsp:include page="../header.jsp" flush="true" />
    <main class="container-full">
	          <s:if test="allQuestionnaires.size()==0">
	          <div class="inner pb40 pt40"><span class="spanblock pb20 "><i class="icon80_smile w80 h80"></i></span><div class="msg_content"><h4>暂无题目，赶快添加吧</h4></div></div>
	       </s:if>
	       <s:else>
	       <ul class="list-group">
	      <s:iterator value="allQuestionnaires" var="a">
	        <li class="list-group-item leve1" id="leve1_<s:property value="#a.id"/>" data-id="<s:property value="#a.id"/>">
	            <a class="leve1_a"><s:if test="#a.model==0"><font style="color:red;">*</font></s:if><s:property value="#a.uid"/>:<s:property value="#a.title"/><span class="biaoqian"><s:if test="#a.type==0">单选题</s:if><s:elseif test="#a.type==1">多选题</s:elseif><s:elseif test="#a.type==2">简答</s:elseif><s:elseif test="#a.type==3">多选简答</s:elseif></span></a>
	            <span class="util"><i class="fa fa-chevron-down"></i></span>
	            <ul class="p0 dn">
	            <s:iterator value="#a.questionnaireOptions" var="q">
	            <li class="list-group-item">
	            <s:property value="#q.uid"/>:<s:property value="#q.body"/><span style="" class="bg badge">支持人数：<s:property value="#q.number"/></span>
	            </li>
	            </s:iterator>
	           <li class="cz ">
	          <span onclick="del(<s:property value="#a.id"/>)" class="">删除题目</span>
	          <span onclick="edit(<s:property value="#a.id"/>)" class="mr20">修改题目</span>
	          <span onclick="move(<s:property value="#a.id"/>)" class="mr20 bu">顺序上移</span>
	          </li>
	            </ul>
	           
	        </li>
	      </s:iterator>
	      </ul>
	     </s:else> 
 </main>
 <div class="container mt15">
  <button class="btn btn-lg mt20 bg" style="width:100%" onclick="add(<s:property value="projectId"/>)" data-id="<s:property value="projectId"/>">添加题目</button>  
 </div>  
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
