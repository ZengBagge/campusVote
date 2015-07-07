package com.vote.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HtmlFilter implements Filter{

	@Override
	public void destroy() {
		
		System.out.println("Html过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		   
		   HttpServletRequest request = (HttpServletRequest) arg0;
		   HttpServletResponse response = (HttpServletResponse) arg1;
		   String url= request.getRequestURI();
		   String urlNew=url.replace(request.getContextPath()+"/","").replace(".html", "").replace("/", "!");
		   response.sendRedirect(request.getContextPath()+"/"+urlNew);
           arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("Html过滤器开启");
	}

}
