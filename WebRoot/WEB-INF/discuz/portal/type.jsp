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
    <title>圈子分类-异形投票网</title>
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
    <script>
    $(function(){
    	var $this = $(".av");
    	var scrollTimer;
    	$this.hover(function(){
    	clearInterval(scrollTimer);
    	},function(){
    	scrollTimer = setInterval(function(){
    	scrollNews( $this );
    	}, 3000 );
    	}).trigger("mouseout");
    	});
    	function scrollNews(obj){
    	var $self = obj.find("ul:first");
    	var lineHeight = $self.find("li:first").height();
    	$self.animate({ "margin-top" : -lineHeight +"px" },600 , function(){
    	$self.css({"margin-top":"0px"}).find("li:first").appendTo($self);
    	});
    	} 
        $(function(){
        	$(".type_menu").click(function(){
        	   var id=$(this).attr("data-id");
        	   if(id !=null){
        		   window.location.href="discuzPortal!typeIndex?typeId="+id;	
            	}
        	});
        })	;
    </script>
  </head>
  
  <body class="index-body mb50">
  <jsp:include page="../../header.jsp" flush="true" />
	         <div class="notify bg-white container-full">
	         <div class="bg-white row m0" style=""><span class="col-xs-1" style=" text-align:center;color:#e7e8eb;"><i class="fa fa-volume-up  fa-2x" style="color:#36be66"></i></span>
	            <div class="h25 av col-xs-11">
	            <ul class="m0 p0" >
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            </ul>
	            </div>
	         </div>
	         </div>
	       <div class="container-full">
	     <s:if test="circleTypeIndex==null">
	         
	     </s:if>
	     <s:else>
	     <div class="container">
	       <article class="mt10 ">
	        <ul class="m0  p0 row">
	        	 <s:iterator value="circleTypeIndex.circleTypes" var="da">
	        	 <s:if test="circleTypeIndex.circleTypeId == #da.id">
	        	 <li class="type_menu col-xs-4 bg-white active" data-id="<s:property value="#da.id"/>"><s:property value="#da.name"/></li>
	        	 </s:if><s:else>
	        	  <li class="type_menu col-xs-4 bg-white" data-id="<s:property value="#da.id"/>"><s:property value="#da.name"/></li>
	        	  </s:else>
	        	 </s:iterator>
	        </ul>
	        </article>
	        <s:iterator value="circleTypeIndex.circles" var="af">
	        <article class="mt10 bg-white" style="border:1px solid #ccc;" onclick="window.location.href='discuzPortal!circleIndex?circleId=<s:property value="#af.id"/>'">
	           <div class="media m0 " style="padding:10px;">
			  <div class="media-left">
			    <a href="discuzPortal!circleIndex?circleId=<s:property value="#af.id"/>">
			      <img class="media-object w60" src="<%=basePath%>inc/image/circle/<s:property value="#af.pic"/>" alt="<s:property value="#af.name"/>">
			    </a>
			  </div>
			  <div class="media-body" style="width:100%; color:#ccc;">
			    <p class="media-heading line fs12" style="width:100%;color:#000;"><s:property value="#af.name"/>
			    </p>
			    <s:property value="#af.shortMessage"/>
              </div>
			  </div>
	        </article>
	        </s:iterator>
	     </div>
	     </s:else>
	   </div>    
  <jsp:include page="../../footer.jsp" flush="true" />
  </body>
</html>
