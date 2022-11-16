package com;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.bootstrap.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter{
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean shouldFilter() {
		
			
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//getting the current HTTP request that is to be handle
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		//printing the detail of the request
		logger.info("request -> {} request uri ->{}",request,request.getRequestURI());
		System.out.println("Req Method: "+request.getMethod()+"Req. URL: "+request.getRequestURI().toString());
		return null;
	}

	@Override
	public String filterType() {
		
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
