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
    <title>编辑主题-异形投票网</title>
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
      <script src ="<%=basePath%>inc/js/common.js"></script>
              <script SRC="dwr/engine.js"></script>
		<script SRC="dwr/util.js"></script>
		<script SRC="dwr/interface/discuzForum.js"></script>
		<script src ="<%=basePath%>inc/js/ajaxfileupload.js"></script>
    <script>
		 var pic = new Array();
       $(function(){
    	   
    	   var circleId=<s:property value="circleId"/>;
    	   setInterval(function(){
    	   $(".articleContext").css("height",$(window).height()-$(".articleTitle").outerHeight()-$(".circleHead").outerHeight()-80+"px");
    	   if($(".articleContext").outerHeight()<$(".articleContext textarea").outerHeight())
    		   $(".articleContext").css("height",$(".articleContext textarea").outerHeight()+"px");
    	   },100);
    	   
    	   $(".publish").click(function(){
    		   var context = $("textarea[name='context']").val();
    		   var title = $("input[name='title']").val();
    		   if(title==null || title.length<3){
    			   message("标题不能小于三个字符");
    			   $("input[name='title']").focus();
    			   return false;
    		   }
    		   if(context==null || context.length<5){
    			   message("正文不能小于五个字符");
    			   $("textarea[name='context']").focus();
    			   return false;
    		   }
    		   discuzForum.addArticle(title,context,circleId,pic,function(result){
    			   if(result[0]=="0")
    				   {message(result[1]);}
    			   else
    				  {
    				   message("发表成功，稍后跳转");
    				   go_to(2,"discuzPortal!circleIndex?circleId="+circleId);
    				  }
    		   });
    	   });
    	  
    	   $(".no").click(function(){
    		   window.location.href="discuzPortal!circleIndex?circleId="+$(this).attr("data-id");
    	   });
    	  $("#ghost-file-btn").click(function(){
    		  $(".uploader-input").click();
    	  });
    	  $("#ghost-file-upload").click(function(){
    		  $("#upload-filename").html("上传中。。").show();
    		     $.ajaxFileUpload( {
    					url : 'fileUpload!imgCircleUpload',//用于文件上传的服务器端请求地址
    					secureuri : false,          //一般设置为false
    					fileElementId : 'file',     //文件上传空间的id属性  <input type="file" id="file" name="file" />
    					dataType : 'json',          //返回值类型 一般设置为json
    					//data : {fileFileName:$("#sourceId").val()}, // 其它请求参数 
    					success : function(data) {  
    						pic.push(data.fileFileName);
    						$("#upload-filename").html(data.msg).show();
    						 $("#ghost-file-btn").show();
    			    		  $("#ghost-file-upload").hide();
    						$("#dropable-tip").html("选择照片直接上传~");
    						$(".pic .number").html(pic.length).show();
                        },
                       error : function(data, status, e) {  
                    	   $("#upload-filename").html(data.msg).show();
                     }  
           });   
    	  });
       });
       function imgShow(img){
    	   var html ='<div class="col-xs-6">'+
    	          '<img class="show" src="<%=basePath%>upload/circle/image/'+img+'"/>'+
           '</div>';
           $(".imgShow").append(html);
       }
 	  function changeFile(dom){
		  $("#ghost-file-btn").hide();
		  $("#ghost-file-upload").show();
		  $("#dropable-tip").html($(dom).val());
	  }
    </script>
  </head>
  
  <body class=" index-body" style="padding:0;">
  	  <div class="global_message bg"></div>
	       <div class="container-full ">
	       	 <header class="circleHead bg tc">
	         <span class="no" data-id="<s:property value="circle.id"/>">取消</span>
	         <span class="title"><s:property value="circle.name"/></span>
	         <span class="publish" >发表</span>
	       </header>
	       <div class="container mt5">
	         <form name="article">
	           <div class="articleTitle"><input class="title" type="text" name="title" placeholder="标题："/></div>
	            <div class="articleContext">
	            <textarea name="context" class="context" placeholder="正文："  style="height:100%;"></textarea>
	            </div>
	           <div class="bg-white p15 action"><span class="pic" data-toggle="modal" data-target="#picUpload" ><i class="fa fa-picture-o" style="color:#36be66;font-size:1.5em;"> </i><b class="number">0</b></span></div>
	            
	         </form>
	       </div>
	       </div>
	 <!-- Modal -->
<div class="modal fade" id="picUpload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加照片</h4>
      </div>
      <div class="modal-body">
        <div id="upload-ctn">
         <form enctype="multipart/form-data" target="att_upload_iframe" method="POST" id="upload-form" class="dropable" action="">
         <p class="tip" data-lang="docDocument.label.attachmentDropUpload" id="dropable-tip">选择照片直接上传~</p>
         <input type="file" name="file" class="uploader-input" id="file" style="display:none;" onchange="changeFile(this)"/>
         <a class="btn btn-primary" data-lang="docDocument.label.attachmentChooseFile" href="javascript:void(0)" id="ghost-file-btn">选择文件</a>
          <a class="btn btn-primary" data-lang="docDocument.label.attachmentChooseFile" href="javascript:void(0)" id="ghost-file-upload">点击上传</a>
         <p class="tip" id="upload-filename">附件大小不能超过 6M</p>
        <div class="imgShow mt10">
        
        </div>
        </form>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">确认</button>
      </div>
    </div>
  </div>
</div>
  </body>
</html>
