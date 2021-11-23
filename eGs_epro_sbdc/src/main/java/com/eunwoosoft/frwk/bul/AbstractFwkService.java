/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.bul; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 각 업무 Service 클래스 가 상속받는 추상클래스
 * 
 * @author : 
 * @date : 2014. 11. 17.
 * @version : 1.0
 */
public abstract class AbstractFwkService {
	
	/**
	 * <pre>
	 * 1. 개요 : 현재 기준의 HttpServletRequest를 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getCurrentHttpServletRequest
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	protected HttpServletRequest getCurrentHttpServletRequest() {		 
	       ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();	 
	       HttpServletRequest hsr = sra.getRequest();
	       return hsr;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 현재 기준의 HttpSession을 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getCurrentHttpSession
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	protected HttpSession getCurrentHttpSession() {		 
		return getCurrentHttpServletRequest().getSession();		
	}

}
