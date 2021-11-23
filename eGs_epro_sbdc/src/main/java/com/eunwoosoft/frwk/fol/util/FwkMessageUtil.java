/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.fol.util; 

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;


/**
 * Spring Message 처리 유틸리티
 * 
 * @author : 
 * @date : 2014. 11. 10.
 * @version : 1.0
 */
@Component
public class FwkMessageUtil {
	
	private static MessageSourceAccessor messageSourceAccessor;
	
	private static FwkReloadableResourceBundleMessageSource messageSource;
	
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	@Autowired(required = true)
	public FwkMessageUtil( @Qualifier("messageSource")  FwkReloadableResourceBundleMessageSource messageSource) {
		FwkMessageUtil.messageSource = messageSource;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Message 전체를 Json 형태의 문자열로 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessages
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param locale
	 * @return
	 */
	public static String getMessages(Locale locale) {		
		Gson gson = new Gson();		
		return gson.toJson(messageSource.getAllProperties(locale));		
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 메시지 코드에 대한 메시지 값을 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessage
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessage(String code) {
		return messageSourceAccessor.getMessage(code);
		//return FwkMessageUtil.getMessage(code, null, null, Locale.getDefault());		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메시지 코드에 대한 메시지 값을 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessage
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param code
	 * @param messageParams
	 * @return
	 */
	public static String getMessage(String code, Object[] messageParams) {
		return FwkMessageUtil.getMessage(code, messageParams, null, Locale.getDefault());		
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 메시지 코드에 대한 메시지 값을 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessage
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param code
	 * @param messageParams
	 * @param defaultMessage
	 * @return
	 */
	public static String getMessage(String code, Object[] messageParams, String defaultMessage) {
		return FwkMessageUtil.getMessage(code, messageParams, defaultMessage, Locale.getDefault());		
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 메시지 코드에 대한 메시지 값을 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessage
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param code
	 * @param messageParams
	 * @param defaultMessage
	 * @param locale
	 * @return
	 */
	public static String getMessage(String code, Object[] messageParams, String defaultMessage, Locale locale) {
		return messageSource.getMessage(code, messageParams, defaultMessage, locale);
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 메시지 코드에 대한 메시지 값을 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessageAdd
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessageAdd(String code) {
		return messageSourceAccessor.getMessage(code) + messageSourceAccessor.getMessage(code+"_2");
		//return FwkMessageUtil.getMessage(code, null, null, Locale.getDefault());		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메시지 코드에 대한 메시지 값을 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMessageAdd
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessageAdd(String code, String codetwo) {
		return messageSourceAccessor.getMessage(code) + messageSourceAccessor.getMessage(codetwo);
		//return FwkMessageUtil.getMessage(code, null, null, Locale.getDefault());		
	}

}
