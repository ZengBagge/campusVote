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
    <title>编辑投票项目-异形投票网</title>
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
		<script SRC="dwr/interface/voteManage.js"></script>
		<script src ="<%=basePath%>inc/js/common.js"></script>
  </head>
  
  <body class="index-body">
		  <jsp:include page="../header.jsp" flush="true" />
			  <main class="container addForm">
			     <form method="post" name="vote">
			            <article class="form-group">
			               <label for="title">活动名称</label>
			               <input name="title" value="<s:property value="voteProject.title"/>"  class="form-control" id="title" placeholder="填写活动名称" check-type="required" required-message="活动名称不能为空"/>
			                <input name="id" value="<s:property value="voteProject.id"/>"  class="form-control" id="id" type="hidden"/>
			            </article>
			              <article class="form-group ">
			               <label for="voteType">投票类别</label>
			              <s:action name="voteType!getList" namespace="/" var="vt"></s:action>
			               <select name="voteType" class="typeSelect" id="voteType">
			               	<option value="<s:property value="voteProject.voteType.id"/>"><s:property value="voteProject.voteType.name"/></option>
			               <s:iterator value="#vt.voteTypeList"  var="v">
			                 <option value="<s:property value="#v.id"/>"><s:property value="#v.name"/></option>
			               </s:iterator>
			               </select>
			            </article>
			              <article class="form-group">
			               <label for="organization">组织方</label>
			               <input name="organization" value="<s:property value="voteProject.organization"/>"  class="form-control" id="organization" placeholder="填写活动举办方" check-type="required" required-message="活动举办方不能为空" />
			            </article>
			            
			             <article class="form-group">
			               <label for="contact">联系方式</label>
			               <input name="contact" value="<s:property value="voteProject.contact"/>"  class="form-control" id="contact" placeholder="填写活动举办方联系方式" check-type="number" required-message="活动举办方联系方式不能为空" />
			            </article>
			               <article class="form-group ">
			               <label for="model">防票模式</label>
			               <select name="model" class="typeSelect" id="model">
			               <s:if test="voteProject.model==0">
			                 <option value="0">一人一票</option>
			                 <option value="1">一天一票</option>			               
			               </s:if><s:else>
			                <option value="1">一天一票</option>
			               	<option value="0">一人一票</option>
			               </s:else>

			               </select>
			            </article>
			             <article class="form-group ">
			               <label for="maxNumber">最大投票量</label>
			                  <input name="maxNumber" value="<s:property value="voteProject.maxNumber"/>"  class="form-control" id="maxNumber" placeholder="最大可支持人数" check-type="number" required-message="必须为数字" type="number"/>
			            </article>
			              <article class="form-group pr  input-append date form_datetime">
			               <label for="beginDate">开始时间</label>
			               <input id="beginDate" name="beginDate" readonly   value="<s:date name="voteProject.beginDate" format="yyyy-MM-dd hh:mm" />"  class="form-control" id="beginDate" placeholder="填写活动正式开始时间"  check-type="required" required-message="开始时间不能为空"/>
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
			               <input id="endDate" name="endDate" readonly  value="<s:date name="voteProject.endDate" format="yyyy-MM-dd hh:mm" />"  class="form-control" id="endDate" placeholder="填写活动正式结束时间" check-type="required" required-message="结束时间不能为空"/>
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
			               <textarea name="explain"  class="form-control" id="explain" placeholder="填写活动介绍"  ><s:property value="voteProject.explains"/></textarea>
			            </article>
			            <article class="form-group">
			               <label for="attention">注意事项</label>
			               <textarea name="attention"  class="form-control" id="attention" placeholder="填写活动注意事项"  ><s:property value="voteProject.attention"/></textarea>
			            </article>
			     </form>
			     		<article class="form-group">
			               <button  class="btn btn-lg mt20 bg" id="uploadForm" style="width:100%;color:#fff;">提交</button>
			               <span style="color: #FF0000;" class="help-block" id="validerrmsg"></span>
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
				          info[1]=$("#voteType").val();
				          info[2]=$("#organization").val();
				           info[3]=$("#contact").val();
				            info[4]=$("#beginDate").val();
				             info[5]=$("#endDate").val();
				              info[6]=$("#explain").val();
				             info[7]=$("#attention").val();
				             info[8]=1; 
				             var id=$("#id").val();
				             var model=$("#model").val();
				             var maxNumber = $("#maxNumber").val();
				             voteManage.updateProject(info,id,model,maxNumber,function(result){
				               if(result[9]=="1"){
				                 $("#uploadForm").html("提交成功,页面将跳转");
				                 go_to(2,"voteManage!getMyProject");
				               }else{
				                  $("#uploadForm").html("提交");
				                  message(result[8]);
				               }
				             });
				       }
				   }); });
				   
        </script>
		  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
