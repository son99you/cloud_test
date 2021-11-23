/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.prl; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eunwoosoft.frwk.fol.exception.FwkSysException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



/**
 * 각 업무 Controller 클래스 가 상속받는 추상클래스
 * 
 * @author : 
 * @date : 2014. 11. 17.
 * @version : 1.0
 */
public abstract class AbstractFwkController {	

	
	/**
	 * HttpServletRequest 파라미터 키
	 */
	protected static final String PARAMETER_MAP = "parameterMap";
	
	/**
	 * JsonView
	 */
	protected static final String JSON_VIEW = "jsonView";
	
	/**
	 * excelView
	 */
	protected static final String EXCEL_VIEW = "excelView";
	
	/**
	 * excelView
	 */
	protected static final String FILE_DOWNLOAD_VIEW = "fileDownloadView";
	
	@ExceptionHandler( value=FwkSysException.class )
	@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView sysExceptionHandler(HttpServletRequest request, HttpServletResponse response, FwkSysException e) {		
		
		ModelAndView model=null;
		
		String reason= HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		int statusCode= HttpStatus.INTERNAL_SERVER_ERROR.value();
		
		if(isAjax(request)) {
			model = new ModelAndView(JSON_VIEW);
			ResponseStatus annotation = e.getClass().getAnnotation(ResponseStatus.class);
			if(annotation!=null) {
				reason = annotation.reason();
				statusCode = annotation.value().value();
			}
		} else {
			model = new ModelAndView("error.jsp");
		}
		
		model.addObject("errorCode", e.getErrorCode());
		model.addObject("errorMessage", e.getMessage());
		model.addObject("reason",reason);
        model.addObject("statusCode",statusCode);
        response.setStatus(statusCode);
		
        return model;
	}
	
	private boolean isAjax(HttpServletRequest request) {
		
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
	
	private String getExceptionMessage(Throwable e) {
		String message = "";
		while( e != null ) {
			message += e.getMessage() + "\n";
			e = e.getCause();
		}		
		return message;
	}
	
	
}
