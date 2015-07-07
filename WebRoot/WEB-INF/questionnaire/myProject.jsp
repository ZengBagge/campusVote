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
    <title>我的问卷-异形投票网</title>
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
     <script src ="<%=basePath%>inc/js/common.js"></script>
    <link href="<%=basePath%>inc/css/font-awesome.min.css" rel="stylesheet">
     <script SRC="dwr/engine.js"></script>
	 <script SRC="dwr/util.js"></script>
     <script SRC="dwr/interface/questionnaireManage.js"></script>
     <script type="text/javascript">
    var voteProjectId = null;
    $(function(){
         $(".userpic li").each(function(){
            var width=$(this).find("img").css("width");
           $(this).find("img").css("height",width);
           $(".user_more").css("height",width).css("line-height",width);
         });

         $(".edit_menu").click(function(){
        	 voteProjectId = $(this).attr("data-id");
        	 $(".menu_fu li").each(function(){
        		 $(this).attr("data-id",voteProjectId);
        	 });
         });
         
         $(".menu_fu").on("click","li",function(){
        	  var moth=$(this).attr("data-moth");
        	  if(moth=="edit")
        		{
        		  window.location.href="questionnaireManage!editQuestionnaire?projectId="+voteProjectId;
        		}
        	  else if(moth=="allUser")
        		  {
        		    window.location.href="questionnaireManage!allQuestionnaire?projectId="+voteProjectId;
        		  }
        	  else if(moth == "del")
        		  {
        		  if(confirm("确认删除么？")){
        			  questionnaireManage.deleteProject(voteProjectId,function(result){
        			   if(result){
        				   message("删除成功");
        			   }
        			   else{
        				   message("删除失败");
        			   }
        		   });
        		  }
        		  }
        	  else if(moth == "publishTrue"){
        		  if(confirm("确认要启动活动么？启动后用户可以浏览问卷页面，到达开始时间即可填写问卷")){
        			  questionnaireManage.publishTrue(voteProjectId,function(result){
           			   if(result){
           				   message("启动成功");
           			   }
           			   else{
           				   message("启动失败");
           			   }
           		   });
           		  }
        	  }
         });
         
         $(".more").click(function(){
        	 window.location.href="questionnaireManage!allUser?projectId="+$(this).attr("data-id");
         });
      });
    function result(projectId){
    	 window.location.href="questionnaireManage!projectResult?projectId="+projectId;
    }
    function stop(id){
    	questionnaireManage.stop(id,function(result){
    		if(result){
    			message("停止成功");
    			$(".isOpen").html("活动暂停");
    		}
    	});
    }
    </script>
    
    </head>
  
  <body class="index-body">
  <jsp:include page="../header.jsp" flush="true" />
	   <main class="container">
	      <s:iterator value="pageBean.list" var="p"> 
	       <article class="projectList bg-white yj5">
	         <header><i class="fa fa-book" style="color:#00FF66 "></i><span class="fs10"><s:property value="#p.title"/></span><i  class="fa fa-chevron-down cp edit_menu" style="float:right;color:#FF0000;line-height:35px;"data-toggle="modal" data-target=".bs-example-modal-sm" data-id="<s:property value="#p.id"/>"> </i></header>
	         <div class="content">
	           <div class="row h20  lh20"><div class="name col-xs-5"><i class="fa fa-heart-o" style="color:#99FF33 "></i><span>举办方</span></div><div class="value col-xs-7"><s:property value="#p.organization"/></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>开始时间</span></div><div class="value col-xs-7"><s:date name="#p.beginDate" /></div></div>
	           <div class="row h20 lh20"><div class="name col-xs-5"><i class="fa fa-bell-o" style="color:#CCCC00 "></i><span>结束时间</span></div><div class="value col-xs-7"><s:date name="#p.endDate" /></div></div>
	          <div class="row h20 lh20"> <div class="name col-xs-5"><i class="fa fa-star-o" style="color:#FF0066 "></i><span>状态</span></div><div class="value col-xs-7 isOpen"><s:if test="#p.isOpen == 0">编辑中</s:if><s:elseif test="#p.isOpen == 1">可报名</s:elseif><s:else>活动进行中</s:else></div></div>
	          <s:if test="#p.isOpen == 2">
	          <div class="row cz">
	          <span onclick="stop(<s:property value="#p.id"/>)" class="">停止</span>
	          <span onclick="result(<s:property value="#p.id"/>)" class="mr20 bu">查看结果</span>
	          </div>
	          </s:if>
	         </div>
	       </article>
	       </s:iterator>
	   </main>
	   <!--浮窗操作  -->
			 <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		        <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="gridSystemModalLabel">编辑项目</h4>
              </div>
              <ul class="list-group menu_fu">
				  <li class="list-group-item cp tc" data-id="" data-moth="edit">编辑活动</li>
				  <li class="list-group-item cp tc" data-id="" data-moth="allUser">查看问题</li>
				  <li class="list-group-item cp tc" data-id="" data-moth="publishTrue">发布活动</li>
				  <li class="list-group-item cp tc " style="color:red;" data-id="" data-moth="del">删除活动</li>
			</ul>
		    </div>
		  </div>
		</div>
  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
