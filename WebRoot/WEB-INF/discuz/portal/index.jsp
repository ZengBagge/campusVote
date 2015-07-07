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
    <title>圈子主页-异形投票网</title>
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
    	$("ul li.type_menu:nth-child(1)").addClass("active");
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
    function goArticle(id){
    	if(id != null)
    	window.location.href="discuzPortal!circleArticle?circleArticleId="+id;
    }
    </script>
  </head>
  
  <body class="index-body mb50">
  <jsp:include page="../../header.jsp" flush="true" />
	         <div class="notify bg-white container-full">
	         <div class="bg-white row m0 "><span class="col-xs-1 m0 p0" style=" text-align:center;color:#e7e8eb;"><i class="fa fa-volume-up  fa-2x" style="color:#36be66"></i></span>
	            <div class="col-xs-11 h25 av m0 pl5">
	            <ul class="m0 p0">
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            <li class="fs10 m0 ptb5">异形网络工作室出品异形投票网快要上线啦！</li>
	            </ul>
	            </div>
	         </div>
	         </div>
	       <div class="container-full">
	     <s:if test="discuzPortal.circleArticles != null">
	     <article class="myCircle bg-white mt5">
	       <header class="btb ">
	         <h4 class="fs10 m0 p0 lh20">我的圈子（<s:property value="discuzPortal.circles.size()"/>）<span style="float:right;">全部</span></h4>
	      </header>   
	         <ul class="myCicle_ul m0 p0 bg-white list-group row">
	         <s:iterator value="discuzPortal.circles" var="d">
	          <li class="bg-white tc">
	             <a class="tc" href="discuzPortal!circleIndex?circleId=<s:property value="#d.id"/>">
	              <img src="<%=basePath%>inc/image/circle/<s:property value="#d.pic"/>"/><br/>
	              <span class="fs8"><s:property value="#d.name"/></span>
	             </a>
	          </li>
	          </s:iterator>
	         </ul> 
	     </article>
	      <article class=" bg-white mt5">
	       <header class="btb ">
	         <h4 class="fs10 m0 p0 lh20">热门话题<span style="float:right;">全部</span></h4>
	      </header>   
	         <div class=" m0 p0 bg-white list-group row">
	         <s:iterator value="discuzPortal.hostArticles" var="dh">
	          <div class="media m0" style="border-bottom:1px solid #ccc;padding:10px 15px;" onclick="goArticle(<s:property value="#dh.id"/>)">
			  <div class="media-body" style="width:100%;">
			    <p class="media-heading line fs12" style="width:100%;"><span class="ic_host">热</span><s:property value="#dh.title"/></p>
                <div class="info mt8 " style="width:100%;float:left;color:#ccc"><span><s:property value="#dh.writer.name"/>  <s:property value="#dh.response"/>评论</span></div>
			  </div>
			</div>
	          </s:iterator>
	         </div> 
	     </article>
	     
	     <article class=" bg-white mt5">
	       <header class="btb ">
	         <h4 class="fs10 m0 p0 lh20">圈子动态</h4>
	      </header>   
	         <div class=" m0  bg-white" style="">
	         <s:iterator value="discuzPortal.circleArticles" var="a">
	          <div class="media m0" style="border-bottom:1px solid #ccc;padding:10px 15px;">
			  <div class="media-left">
			    <a href="#">
			      <img class="media-object" src="<%=basePath%>inc/image/avator/<s:property value="#a.writer.pic"/>/small.jpg" alt="<s:property value="#a.writer.name"/>">
			    </a>
			  </div>
			  <div class="media-body" style="width:100%;" onclick="goArticle(<s:property value="#a.id"/>)">
			    <p class="media-heading line fs12" style="width:100%;"><s:property value="#a.title"/></p>
                <div class="info mt8 " style="width:100%;float:left;color:#ccc"><span><s:property value="#a.writer.name"/>   <s:property value="#a.newTime"/></span> <span style="float:right;"><i class="fa fa-comment-o"></i> <s:property value="#a.response"/></span></div>
			  </div>
			</div>
	          </s:iterator>
	         </div> 
	     </article>
	     </s:if>
	     <s:else>
	     <div class="container">
	       <article class="mt10 ">
	        <ul class="m0  p0 row">
	        	 <s:iterator value="discuzPortal.circleTypes" var="da">
	        	  <li class="type_menu col-xs-4 bg-white" data-id="<s:property value="#da.id"/>"><s:property value="#da.name"/></li>
	        	 </s:iterator>
	        </ul>
	        </article>
	        <s:iterator value="discuzPortal.circles" var="af">
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
