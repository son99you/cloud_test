/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * ew.framework.exception 
 *    |_ CustomExceptionResolver.java
 * 
 * </pre>
 * @date : 2014. 12. 16. 오전 8:20:21
 * @version : 1.0
 * @author : 
 */
public class FwkCustomExceptionResolver implements HandlerExceptionResolver {
	private static final Logger LOG = LoggerFactory
			.getLogger(FwkCustomExceptionResolver.class);
	
	private String view = null; 
	
	public void setView(String view) { 
		this.view = view; 
	}
		
	
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		LOG.error("exce : ", ex);
		ModelAndView mav = new ModelAndView("forward:/erro/error.do");
		mav.addObject("message", "예기치 않은 오류가 발생했습니다." +
                " 오류내용：【" + ex + "】");
		mav.addObject("ex", ex);
		return mav;
	}
}
