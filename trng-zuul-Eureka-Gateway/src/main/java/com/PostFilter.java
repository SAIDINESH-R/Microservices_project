package com;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
	
		return true;
	}

	@Override
	public Object run() throws ZuulException {
	System.out.println(" *******Route is Using POST filter***************8");
		return null;
	}

	@Override
	public String filterType() {
		
		return "post";
	}

	@Override
	public int filterOrder() {

		return 0;
	}

}
