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
    <title>添加投票体-异形投票网</title>
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
       <link rel="stylesheet" href="<%=basePath%>inc/css/bootstrap-datetimepicker.min.css">
	<script src ="<%=basePath%>inc/js/jquery.min.js"></script>
    <script src ="<%=basePath%>inc/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>inc/js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>inc/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src ="<%=basePath%>inc/js/form.js"></script>
    <link href="<%=basePath%>inc/css/font-awesome.min.css" rel="stylesheet">
         <script SRC="dwr/engine.js"></script>
		<script SRC="dwr/util.js"></script>
		<script SRC="dwr/interface/fileUpload.js"></script>
		<script SRC="dwr/interface/voteManage.js"></script>
		<script src ="<%=basePath%>inc/js/common.js"></script>
		<script src ="<%=basePath%>inc/js/ajaxfileupload.js"></script>
  </head>
  
  <body class="index-body">
		  <jsp:include page="../header.jsp" flush="true" />
			  <main class="container addForm">
			     <form method="post" name="vote" enctype="multipart/form-data">
			            <article class="form-group">
			               <label for="name">排序</label>
			               <input name="sortNumber" value="<s:property value="wordVote.sortNumber"/>"  class="form-control" id="sortNumber" placeholder="排序数字" check-type="number" required-message="排序数字"/>
			               <input name="projectId" value="<s:property value="projectId"/>"  class="form-control" id="projectId" type="hidden"/>
			               <s:if test="wordVote !=null">
			                  <input name="id" value="<s:property value="wordVote.id"/>"  class="form-control" id="id" type="hidden"/>
			               </s:if>
			            </article>
		                <article class="form-group">
			               <label for="explain">内容</label>
			               <textarea name="content"  class="form-control" id="content" placeholder="投票内容"  check-type="required" required-message="投票内容不能为空" ><s:property value="wordVote.content"/></textarea>
			            </article>
			     </form>
			     		<article class="form-group">
			     		 <s:if test="wordVote != null">
			     		 <button  class="btn btn-lg mt20 bg" id="updateForm" style="width:100%;color:#fff;">更新</button>
			     		 </s:if><s:else>
			               <button  class="btn btn-lg mt20 bg" id="uploadForm" style="width:100%;color:#fff;">提交</button>
			               </s:else>
			            </article>
			  </main>    
			  <script type="text/javascript">
				 $(function(){
				   //1. 简单写法：
				   $("form").validation();
				   $("#uploadForm").on('click',function(event){
				   	  $(this).html('<i class="fa fa-spin fa-spinner fa-2x" style="color:#fff;">');
				     // 2.最后要调用 valid()方法。
				     if ($("form").valid(this,"error!")==false){
				       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
				       return false;
				     }
				     else
				     {
				          var info = new Array();
				          info[0] = $("#projectId").val();
				          info[1]=$("#sortNumber").val();
				          info[2]=$("#content").val();
				            voteManage.addWordVote(info,function(result){
				               if(result=="1"){
				                 message("提交成功,页面将跳转");
				                 go_to(2,"voteManage!allUser?projectId="+info[0]);
				               }else{
				                  $("#uploadForm").html("提交");
                                  message("报名失败");				                  
				               }
				             });
				       }
				   });
				   $("#updateForm").on('click',function(event){
					   	  $(this).html('<i class="fa fa-spin fa-spinner fa-2x" style="color:#fff;">');
					     // 2.最后要调用 valid()方法。
					     if ($("form").valid(this,"error!")==false){
					       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
					       return false;
					     }
					     else
					     {
					          var info = new Array();
					          info[0] = $("#projectId").val();
					          info[1]=$("#sortNumber").val();
					          info[2]=$("#content").val();
					          var id= $("#id").val();
					          if(id.length>0){
					            voteManage.updateWordVote(info,id,function(result){
					               if(result=="1"){
					                 message("提交成功,页面将跳转");
					                 go_to(2,"voteManage!allUser?projectId="+info[0]);
					               }else{
					                  $("#updateForm").html("提交");
	                                  message("报名失败");				                  
					               }
					             });
					          }
					          else
					        	  message("获取文字投票体对象错误");
					       }
					   });
				 
				 
				 });
				   
        </script>
		  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
