package com.vote.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component("mobileInterceptor")
public class MobileInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		  System.out.println("====================拦截器destroy()=========================");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		  System.out.println("====================浏览器检查拦截器 init()=========================");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		String browserType=request.getHeader("user-agent");
		 //commonUtil.p("浏览器类型为"+browserType);
		  if(!(browserType.indexOf("MicroMessenger")>0)){
		   System.out.println("不是微信浏览器浏览，然回错误");   
		   return "browserError";
		  }  
		  else{
			  String result=invocation.invoke(); 
			  return result;
		  }
	}




	

	
}
