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
		<script SRC="dwr/interface/fileUpload.js"></script>
		<script SRC="dwr/interface/voteManage.js"></script>
		<script src ="<%=basePath%>inc/js/common.js"></script>
		<script src ="<%=basePath%>inc/js/ajaxfileupload.js"></script>
		<script>
		 var pic = new Array();
		  function file(){
			  var file=document.getElementById('file');
		    	file.click();
		  }
		  /**
		  * 从 file 域获取 本地图片 url
		  */
		  function getFileUrl(sourceId) {
		  var url;
		  if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
		  url = document.getElementById(sourceId).value;
		  } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
		  url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
		  } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
		  url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
		  }
		  return url;
		  }

		  /**
		  * 将本地图片 显示到浏览器上
		  */
		  function preImg(sourceId) {
		  var url = getFileUrl(sourceId);
		  var html='<li class="col-xs-3" ><img src="'+url+'"></li>';
		   $(".votepic").prepend(html);
		     $.ajaxFileUpload( {
					url : 'fileUpload!imgUpload',//用于文件上传的服务器端请求地址
					secureuri : false,          //一般设置为false
					fileElementId : 'file',     //文件上传空间的id属性  <input type="file" id="file" name="file" />
					dataType : 'json',          //返回值类型 一般设置为json
					//data : {fileFileName:$("#sourceId").val()}, // 其它请求参数 
					success : function(data) {  
						pic.push(data.fileFileName);
						message(data.msg);
                    },
                   error : function(data, status, e) {  
                        message(data.msg);
                 }  
    });   
		  } 
		</script>
  </head>
  
  <body class="index-body">
		  <jsp:include page="../header.jsp" flush="true" />
			  <main class="container addForm">
			     <form method="post" name="vote" enctype="multipart/form-data">
			            <article class="form-group">
			               <label for="name">选手姓名</label>
			               <input name="name" value="<s:property value="session.voteUserSession.name"/>"  class="form-control" id="name" placeholder="填写选手姓名" check-type="required" required-message="活动名称不能为空"/>
			                  <input name="projectId" value="<s:property value="projectId"/>"  class="form-control" id="projectId" type="hidden"/>
			            </article>
			              <article class="form-group">
			               <label for="college">学院</label>
			               <input name="college" value="<s:property value="session.voteUserSession.college"/>"  class="form-control" id="college" placeholder="填写选手所在学院" check-type="required" required-message="活动举办方不能为空" />
			            </article>   
			             <article class="form-group">
			               <label for="mail">邮箱地址</label>
			               <input name="mail" value="<s:property value="session.voteUserSession.mail"/>"  class="form-control" id="mail" placeholder="填写选手邮箱地址" check-type="mail" required-message="活动举办方联系方式不能为空" />
			            </article>
		                <article class="form-group">
			               <label for="explain">参赛宣言</label>
			               <textarea name="explain"  class="form-control" id="explain" placeholder="参赛宣言"  ></textarea>
			            </article>
			             <article class="form-group"  class="file-hidden" style="display:none;">
			               <input type="file" name="file"  id="file" onchange="preImg(this.id);"/>
			            </article>
			     </form>
			     	    <article class="form-group">
			               <label for="img">选手风采</label>
			                 <ul class="votepic row">
			                 <li class="col-xs-3 add" onclick="file()"><img src="<%=basePath%>inc/image/new-group.png"></li>
			                </ul> 
			            </article>
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
				          info[0] = $("#projectId").val();
				          info[1]=$("#name").val();
				          info[2]=$("#college").val();
				           info[3]=$("#mail").val();
				           info[4]=$("#explain").val();
				            voteManage.addPersonVote(info,pic,function(result){
				               if(result=="1"){
				                 message("提交成功,页面将跳转");
				                 go_to(2,"vote!index");
				               }else{
				                  $("#uploadForm").html("提交");
                                  message("报名失败");				                  
				               }
				             });
				       }
				   }); });
				   
        </script>
		  <jsp:include page="../footer.jsp" flush="true" />
  </body>
</html>
