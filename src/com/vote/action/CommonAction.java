package com.vote.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
public class CommonAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 00000L;
	public static final String LOGIN ="ClientLogin";
    protected Map< String, Object>session;
    
    protected HttpServletRequest request;
    
    protected HttpServletResponse response;
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request =arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

}
