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
    <title><s:property value="project.title"/>-异形投票网</title>
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="<s:property value="project.title"/>,异形网络工作室，基于校园，投票，问卷">
	<meta name="description" content="<s:property value="project.explains"/>">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>inc/css/style.css">
	<script src ="<%=basePath%>inc/js/jquery.min.js"></script>
    <script src ="<%=basePath%>inc/js/bootstrap.min.js"></script>
    <script src ="<%=basePath%>inc/js/common.js"></script>
    <script src ="<%=basePath%>inc/js/form.js"></script>
    <link href="<%=basePath%>inc/css/font-awesome.min.css" rel="stylesheet">
    <script SRC="dwr/engine.js"></script>
	<script SRC="dwr/util.js"></script>
	<script SRC="dwr/interface/questionnaire.js"></script>
		<script SRC="dwr/interface/user.js"></script>
    <script type="text/javascript">
    var timerc=0;
    var a="";
    var tou = false; 
    var projectId=<s:property value="project.id"/>
    $(function() { 
     var startDate = new Date("<s:date name="project.beginDate" format="yyyy/MM/dd hh:mm:ss"/>");
       var endDate = new Date("<s:date name="project.endDate" format="yyyy/MM/dd hh:mm:ss"/>");
       var now= new Date();
      if(startDate.getTime()>now.getTime())
    	  {
		   timerc=(startDate.getTime()-now.getTime())/1000; //全局时间变量（秒数）
		   a="活动开始倒计时：";
    	  }  
      else if(now.getTime()>startDate.getTime() && now.getTime()<endDate.getTime())
    	  {
    	   timerc=(endDate.getTime()-now.getTime())/1000;
		   a="活动结束倒计时：";
		   tou = true;
    	  }
      else{
    	   $("#date").html("（投票已结束）");
      }
       add(); //首次调用add函数
    }); 
	  function add(){ //加时函数
	        if(timerc > 0){ //如果不到3分钟
	            --timerc; //时间变量自剪1
	            var dd = parseInt(timerc/60/60/24)+"天";
	                var hh =parseInt((timerc/60/60)%24)+"时"; //写入小时数
	            var min = parseInt((timerc/60)%60)+"分"; //写入分钟数
	            var sec =parseInt(timerc%60/10).toString()+parseInt(timerc%10)+"秒"; //写入秒数（两位）
	             $("#date").html(a+dd+hh+min+sec);
	            setTimeout("add()", 1000); //设置1000毫秒以后执行一次本函数   
	       }
	 }

	 $(function(){
		 questionnaire.isAccess(projectId,function(result){
			if(!result){
				message("sorry,你已经提交过问卷或者没有权限再次答题");
		    	$(".access").html("<h4>sorry,你已经提交过问卷或者没有权限再次答题</h4>");
		     }	 
		 });
		 
		 user.getUserState(function(commonUser){
			 if(!commonUser){
				  if(confirm("你没有登录哦，不登录可能不能提交问卷哦！")){
				   location.href="user!login?old_url="+window.location.href;
				  }
			 }
		 });
	 });
   </script>
  </head>
  <body class="index-body">
  	<div class="global_message bg"></div>
   <header class="bg-green pt10  m0 project-header">
       <h2 style="text-align:center;color:#CCFF00;font-weight:600;" class="fs14 m0"><s:property value="project.title"/></h2>
       <h4  style="text-align:center;color:#fff" class="fs10 m10">主办：<s:property value="project.organization"/></h4>
         <h4  style="text-align:center;color:#fff" class="fs10 m10" id="date"></h4>
   </header>
   <main class="container mb40">
      <div class="row projectInfo mt15">
         <div class="col-xs-6 info">
             <div class="yj10 bg-white p10">
             <h5 class="title m0">活动介绍</h5>
             <h6 class="content"><s:property value="project.explains"/></h6>
             </div>
         </div>
          <div class="col-xs-6 info">
              <div class="yj10 bg-white p10">
             <h5 class="title m0">活动说明</h5>
              <h6 class="content"><s:property value="project.attention"/></h6>
              </div>
         </div>
      </div>
      <form>
      <div class= "row mt15">
          <s:iterator value="questionnaires" var="pl">
		  <div class="prl15 questionnaire" data-model="<s:property value="#pl.model"/>" data-id="<s:property value="#pl.id"/>" data-type="<s:property value="#pl.type"/>">
		    <h4><s:property value="#pl.uid"/>.<s:property value="#pl.title"/><s:if test="#pl.model==0"><span style="color:red;" >*</span></s:if><span class="ml10" style="color:#0033CC;">[<s:if test="#pl.type==0">单选题</s:if><s:elseif test="#pl.type==1">多选题</s:elseif><s:elseif test="#pl.type==2">简答</s:elseif><s:elseif test="#pl.type==3">多选简答</s:elseif>]</span></h4>
		    <s:if test="#pl.type==0">
		    <ul class="list-group">
		    <s:iterator value="#pl.questionnaireOptions" var="pq"> 
            <li class="list-group-item"><input type="radio" class="questionInput" name="radio<s:property value="#pl.id"/>" value="<s:property value="#pq.id"/>"/><s:property value="#pq.body"/></li>
            </s:iterator>
            </ul>
            </s:if>
              <s:elseif test="#pl.type==1 ">
		    <ul class="list-group">
		    <s:iterator value="#pl.questionnaireOptions" var="pq"> 
            <li class="list-group-item"><input type="checkbox" class="questionInput" name="checkbox<s:property value="#pl.id"/>" value="<s:property value="#pq.id"/>"/><s:property value="#pq.body"/></li>
            </s:iterator>
            </ul>
            </s:elseif>
            <s:elseif test="#pl.type==2">
            <div class="form-group">
             <s:if test="#pl.model==0"><textarea  style="width:100%;" class="form-control" check-type="required" required-message="必填选项" name="text<s:property value="#pl.id"/>"></textarea> </s:if><s:else>
             <textarea  style="width:100%;" class="form-control" ></textarea>
             </s:else>
             </div>
            </s:elseif>
            <s:elseif test="#pl.type==3">
            <ul class="list-group">
		    <s:iterator value="#pl.questionnaireOptions" var="pq"> 
            <li class="list-group-item"><input type="checkbox" class="questionInput" name="checkboxText<s:property value="#pl.id"/>" value="<s:property value="#pq.id"/>"/><s:property value="#pq.body"/></li>
            </s:iterator>
            <li class="list-group-item">
             其他：
             <input  style="width:100%;" class="form-control" name="moretext<s:property value="#pl.id"/>"></input>
            </li>
            </ul>
            </s:elseif>
		  </div>
		  </s:iterator>     
      </div>
      </form>
         <article class="form-group access">
	 <button  class="btn btn-lg mt20 bg" id="uploadForm" style="width:100%;color:#fff;">提交</button>
   </article>
   </main>
   <script>
   var upload =null;
   $(function(){
	   //1. 简单写法：
	   $("form").validation();
	   $("#uploadForm").on('click',function(event){
	   	  $(this).html('<i class="fa fa-spin fa-spinner fa-2x" style="color:#fff;">');
	     // 2.最后要调用 valid()方法。
	     if ($("form").valid(this,"error!")==false){
	       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
	       $("#uploadForm").html("提交");
	       return false;
	     }
	     else if( yanzheng()){
	    	if(upload.length>0){
	    		questionnaire.resultUpload(upload,projectId,function(m){
	    			if(m==2){
	    				message("数据提交成功，马上跳转");
	    				go_to(2,"questionnaire!index");
	    			}else if(m==-1){
	    				message("没有权限或者已经提交过问卷了");
	    				go_to(2,"questionnaire!index");
	    			}
	    			else{
	    				message("数据保存错误");
	    				$("#uploadForm").html("提交");
	    			}
	    		});
	    	}else
	    		{
	    		$("#uploadForm").html("提交");
		    	 message("客户端出现错误");
		         return false;
	    		}
	     }
	     else
	     {
	    	 $("#uploadForm").html("提交");
	    	 message("必选题没有做完哦！");
		       return false;
	       }
	   }); });
   function 	 yanzheng(){
	 var tmp = new Array();
	 var result =true;
	   $(".questionnaire").each(function(){
		   var value = $(this).attr("data-id")+",";
		   if($(this).attr("data-model")==0){
				if($(this).attr("data-type")==0){
					var val=$('input:radio[name="radio'+$(this).attr("data-id")+'"]:checked').val();
					if(val == null){
						result = false;
						return;
					}
					else
						value += val;
				}
				else if($(this).attr("data-type")==1){
					var val = $('input:checkbox[name="checkbox'+$(this).attr("data-id")+'"]:checked');
					if(val==null)
						{
						result = false;
						return;
						}else{
					          val.each(function(){
					        	  value+=$(this).val()+".";
					          });
						}
				}
               else if($(this).attr("data-type")==2){
            	   var val = $('textarea[name="text'+$(this).attr("data-id")+'"]').val();
            	   value += val;
				}
              else if($(this).attr("data-type")==3){
            	  var val = $('input:checkbox[name="checkboxText'+$(this).attr("data-id")+'"]:checked');
            	  var val2 = $('input:text[name="moretext'+$(this).attr("data-id")+'"]').val();
					if(val==null && val2==null)
						{
						result = false;
						return;
						}else{
					          val.each(function(){
					        	  value+=$(this).val()+".";
					          });
					        value =value+","+val2;  
						}
              }
		   }else{
				if($(this).attr("data-type")==0){
					var val=$('input:radio[name="radio'+$(this).attr("data-id")+'"]:checked').val();
						value += val;
				}
				else if($(this).attr("data-type")==1){
					var val = $('input:checkbox[name="checkbox'+$(this).attr("data-id")+'"]:checked');

					          val.each(function(){
					        	  value+=$(this).val()+".";
					          });
				}
               else if($(this).attr("data-type")==2){
            	   var val = $('textarea[name="text'+$(this).attr("data-id")+'"]').val();
            	   value += val;
				}
              else if($(this).attr("data-type")==3){
            	  var val = $('input:checkbox[name="checkboxText'+$(this).attr("data-id")+'"]:checked');
            	  var val2 = $('input:text[name="moretext'+$(this).attr("data-id")+'"]').val();
			          val.each(function(){
			        	  value+=$(this).val()+".";
			          });
			        value =value+","+val2;  
              }
		   }
		  tmp.push(value); 
	   });
	upload = tmp;
   return result;
  }
   </script>
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
