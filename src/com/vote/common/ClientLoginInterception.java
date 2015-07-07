package com.vote.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.vote.action.UserAction;

@Component("clientLoginInterception")
public class ClientLoginInterception implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		commonUtil.p("登录拦截器销毁");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		commonUtil.p("登录拦截器启动");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String, Object> session=ActionContext.getContext().getSession();   
		  if(session.get(UserAction.USER_SESSION)==null||"".equals(session.get(UserAction.USER_SESSION))){
		   commonUtil.p("发现没有登陆，返回到登录页面");
		   HttpServletRequest request = ServletActionContext.getRequest();  
           String currentURL = request.getRequestURI();  
	   		//访问服务器所带有的参数信息
	   		String queryInfo=request.getQueryString();
	   		if(queryInfo!=null&&(!queryInfo.equals(""))){
	   			currentURL=currentURL+"?"+queryInfo;
	   		}
            commonUtil.p(currentURL);
            session.put("old_url",currentURL);
		   return "ClientLogin";
		  }  
		  else{
			  String result=invocation.invoke(); 
			  return result;
		  }
	}

}
