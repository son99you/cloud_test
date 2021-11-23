/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.exception;

import java.util.Locale;

import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;



/**
 * <pre>
 * ew.framework.exception 
 *    |_ SysErrorCode.java
 * 
 * </pre>
 * @date : 2014. 12. 16. 오후 7:28:38
 * @version : 1.0
 * @author : 
 */
public class FwkSysErrorCode {
	
	
	private String errorCode;
	private String errorMessage;
	
	public FwkSysErrorCode(String errorCode) {
		this(errorCode, null, null, Locale.getDefault());		
	}	
	public FwkSysErrorCode(String errorCode, Object[] messageParams) {
		this(errorCode, messageParams, null, Locale.getDefault());		
	}	
	public FwkSysErrorCode(String errorCode, Object[] messageParams, String defaultMessage) {
		this(errorCode, messageParams, defaultMessage, Locale.getDefault());		
	}	
	public FwkSysErrorCode(String errorCode, Object[] messageParams, String defaultMessage, Locale locale) {
		this.errorCode = errorCode;
		this.errorMessage = FwkMessageUtil.getMessage(errorCode, messageParams, defaultMessage, locale);
	}	
	public String getErrorCode() {
		return this.errorCode;
	}	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
