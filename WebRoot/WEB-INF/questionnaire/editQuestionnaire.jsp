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
    <title>报名活动-异形投票网</title>
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
		<script>
		var number=1;
		   $(function(){
			   $("#type").change(function(){
				   $(".type").show();
				   if($(this).val()==2)
					   $(".type").hide();
			   });
			   $(".add").click(function(){
				   number++;
				  var  $html =$( ' <article class="form-group">'+
				  '  <label for="option">选项'+number+'</label>'+
				  ' <input name="option['+number+']" value=""  class="form-control option" id="option'+number+'" placeholder="选项"  />'+
				  '  </article>');
				 $(this).parent().parent().before($html);
			   });
			   $(".remove").on("click" ,function(){
				   $(this).parent().parent().remove(); 
			   });
		   });
		</script>
  </head>
  
  <body class="index-body">
		  <jsp:include page="../header.jsp" flush="true" />
		    <s:if test="questionnaire !=null">
			  <main class="container addForm">
			     <form method="post" name="vote" enctype="multipart/form-data">
			            <article class="form-group">
			               <label for="uid">编号</label>
			               <input name="uid" value="<s:property value="questionnaire.uid"/>"  class="form-control" id="uid" placeholder="填写题目编号" check-type="number" required-message="排序编号为数字"/>
			               <input name="id" id="id" value="<s:property value="questionnaire.id"/>" type="hidden"/>
			            </article>
			              <article class="form-group">
			               <label for="title">题目</label>
			               <input name="title" value="<s:property value="questionnaire.title"/>"  class="form-control" id="title" placeholder="问题" check-type="required" required-message="问题不能为空" />
			            </article>
			            <article class="form-group">
			               <label for="type">题目类型</label>
			               <select name ="type" id ="type" class="form-control" >
			               <option value="<s:property value="questionnaire.type"/>"><s:if test="questionnaire.type==0">单选题</s:if><s:elseif test="questionnaire.type==1">多选题</s:elseif><s:elseif test="questionnaire.type==2">简答题</s:elseif><s:elseif test="questionnaire.type==3">多选简答题</s:elseif></option>
			                <option value="0" >单选题</option>
			                <option value="1">多选题</option>
			                <option value="2">简答题</option>
			                <option value="3">多选加简答</option>
			               </select>
			            </article>   
			            <article class="form-group">
			               <label for="model">控制属性</label>
			               <select name ="model" id ="model" class="form-control" >
			               <s:if test="questionnaire.model==0">
			                <option value="0">必填</option>
			                <option value="1">选填</option>
			                </s:if>
			                 <s:else>
			                <option value="1">选填</option>
			                <option value="0">必填</option>
			                </s:else>
			               </select>
			            </article>  
                        <div class="type">
                         <s:iterator value="questionnaire.questionnaireOptions" var="qq">
                           <article class="form-group">
			               <label for="option">选项<s:property value="#qq.uid"/></label>
			               <input name="option[<s:property value="#qq.uid"/>]" value="<s:property value="#qq.body"/>"  class="form-control option" id="option<s:property value="#qq.uid"/>" placeholder="选项"  />
			              </article>
			              </s:iterator>
			                <article class="form-group">
			               <label for="option"><i class="fa fa-plus-circle fa-2x mr10 add"></i>添加选项</label>
			              </article>
                        </div>
			     </form>
			     		<article class="form-group">
			               <button  class="btn btn-lg mt20 bg" id="uploadForm" style="width:100%;color:#fff;">更新</button>
			            </article>
			  </main>   
			  </s:if> 
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
				          var option = new Array();
				          info[0] = $("#id").val();
				          info[1]=$("#uid").val();
				          info[2]=$("#title").val();
				          info[3]=$("#type").val();
				          info[4]=$("#model").val();
				          if(info[3]==0 || info[3]== 1 || info[3]==3)
				          {
				        	  $(".option").each(function(){
				        		  if($(this).val()!=null && $(this).val()!="")
				        		  option.push($(this).val());
				        	  });
				        	  questionnaireManage.updateQuestionnaire(info,option,function(result){
					               if(result=="1"){
					                 message("提交成功,页面将跳转");
					                 go_to(2,"questionnaireManage!allQuestionnaire?projectId=<s:property value="questionnaire.questionnaireProject.id"/>");
					               }else{
					                  $("#uploadForm").html("更新");
	                                  message("更新失败");				                  
					               }
					             }); 
				          }
				          else{
				        	  questionnaireManage.updateQuestionnaireTwo(info,function(result){
					               if(result=="1"){
					                 message("提交成功,页面将跳转");
					                 go_to(2,"questionnaireManage!allQuestionnaire?projectId=<s:property value="questionnaire.questionnaireProject.id"/>");
					               }else{
					                  $("#uploadForm").html("更新");
	                                  message("更新失败");				                  
					               }
					             });  
				          }
				       }
				   }); });
				   
        </script>
		  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
