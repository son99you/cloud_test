/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.exception;

import org.springframework.core.NestedRuntimeException;


/**
 * <pre>
 * ew.framework.exception 
 *    |_ UnknownResourceException.java
 * 
 * </pre>
 * @date : 2014. 12. 15. 오후 2:21:10
 * @version : 1.0
 * @author : 
 */
@SuppressWarnings("serial")
public class FwkResourceNotFoundException extends NestedRuntimeException {
	
//	private static final long serialVersionUID = -3339876112818029194L;

	public FwkResourceNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}		
	
}
