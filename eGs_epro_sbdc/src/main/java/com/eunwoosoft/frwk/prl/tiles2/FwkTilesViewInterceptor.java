/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.prl.tiles2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * ew.framework.web.tiles2 
 *    |_ TilesViewInterceptor.java
 * 
 * </pre>
 * @date : 2014. 12. 16. 오후 12:49:12
 * @version : 1.0
 * @author : 
 */
public class FwkTilesViewInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = LoggerFactory
			.getLogger(FwkTilesViewInterceptor.class);
	
	@Autowired
	private FwkTilesUrlBasedViewResolver tilesUrlBasedViewResolver;
	
	/**
	 * tiles definition name
	 */
	private String tilesDefinitionName;
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : setTilesDefinitionName
	 * @date : 2014. 12. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param tilesDefinitionName
	 */
	public void setTilesDefinitionName(final String tilesDefinitionName) {
		this.tilesDefinitionName = tilesDefinitionName;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : postHandle
	 * @date : 2014. 12. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
		tilesUrlBasedViewResolver.setTilesDefinitionName(tilesDefinitionName);
		
		LOG.debug("tilesDefinitionName - " + tilesDefinitionName);
	}
}
