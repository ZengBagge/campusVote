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
    <title>添加问卷活动-异形投票网</title>
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
		<script SRC="dwr/interface/questionnaireManage.js"></script>
		<script src ="<%=basePath%>inc/js/common.js"></script>
  </head>
  
  <body class="index-body">
		  <jsp:include page="../header.jsp" flush="true" />
			  <main class="container addForm">
			     <form method="post" name="vote">
			            <article class="form-group">
			               <label for="title">活动名称</label>
			               <input name="title" value=""  class="form-control" id="title" placeholder="填写活动名称" check-type="required" required-message="活动名称不能为空"/>
			            </article>
			              <article class="form-group">
			               <label for="organization">组织方</label>
			               <input name="organization" value=""  class="form-control" id="organization" placeholder="填写活动举办方" check-type="required" required-message="活动举办方不能为空" />
			            </article>
			            
			             <article class="form-group">
			               <label for="contact">联系方式</label>
			               <input name="contact" value=""  class="form-control" id="contact" placeholder="填写活动举办方联系方式" check-type="number" required-message="活动举办方联系方式不能为空" />
			            </article>
			              <article class="form-group pr  input-append date form_datetime">
			               <label for="beginDate">开始时间</label>
			               <input id="beginDate" name="beginDate" readonly  value=""  class="form-control" id="beginDate" placeholder="填写活动正式开始时间"  check-type="required" required-message="开始时间不能为空"/>
                            <span class="add-on"><i class="fa fa-calendar-o" style="color:#ccc;"></i></span>			               
                               <script type="text/javascript">
							    $("#beginDate").datetimepicker({
							     language:'zh-CN',
							    format: 'yyyy-mm-dd hh:ii',
                               autoclose: true,
                               todayBtn: true,
                                });
							    </script> 
			            </article>
			           <article class="form-group pr input-append date form_datetime">
			               <label for="endDate">结束时间</label>
			               <input id="endDate" name="endDate" readonly  value=""  class="form-control" id="endDate" placeholder="填写活动正式结束时间" check-type="required" required-message="结束时间不能为空"/>
			               <span class="add-on"><i class="fa fa-calendar-o" style="color:#ccc;"></i></span>
			            </article>
			              <script type="text/javascript">
							    $("#endDate").datetimepicker({
							     language:'zh-CN',
							    format: 'yyyy-mm-dd hh:ii',
                               autoclose: true,
                               todayBtn: true,
                               todayHighlight:true,
                                });
							    </script> 
		                <article class="form-group">
			               <label for="explain">活动说明</label>
			               <textarea name="explain" value=""  class="form-control" id="explain" placeholder="填写活动介绍"  ></textarea>
			            </article>
			            <article class="form-group">
			               <label for="attention">注意事项</label>
			               <textarea name="attention" value=""  class="form-control" id="attention" placeholder="填写活动注意事项"  ></textarea>
			            </article>
			     </form>
			     		<article class="form-group">
			               <button  class="btn btn-lg mt20 bg" id="uploadForm" style="width:100%;color:#fff;">提交</button>
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
				          info[0] = $("#title").val();
				          info[1] = 0;
				          info[2]=$("#organization").val();
				           info[3]=$("#contact").val();
				            info[4]=$("#beginDate").val();
				             info[5]=$("#endDate").val();
				              info[6]=$("#explain").val();
				             info[7]=$("#attention").val();
				             info[8]=1; 			             
				             questionnaireManage.addProject(info,function(result){
				               if(result[9]=="1"){
				                 $("#uploadForm").html("提交成功,页面将跳转");
				                 go_to(2,"questionnaireManage!getMyProject");
				               }else{
				                  $("#uploadForm").html("提交");
				                  $("#uploadForm").next().html(result[8]);
				                  
				               }
				             });
				       }
				   }); });
				   
        </script>
		  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
