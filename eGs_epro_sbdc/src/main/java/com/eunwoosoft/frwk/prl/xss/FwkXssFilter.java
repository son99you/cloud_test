/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.prl.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * com.eunwoosoft.frwk.prl.xss 
 *    |_ FwkXssFilter.java
 * 
 * </pre>
 * @date : 2015. 9. 25. 오전 9:09:01
 * @version : 1.0
 * @author : 
 */
public class FwkXssFilter implements Filter {
	
	private FilterConfig filterConfig;


	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		filterChain.doFilter(new FwkXssRequestWrapper((HttpServletRequest) request), response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
