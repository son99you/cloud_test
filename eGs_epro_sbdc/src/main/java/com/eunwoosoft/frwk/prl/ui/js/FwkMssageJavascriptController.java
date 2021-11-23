/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.prl.ui.js; 

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.eunwoosoft.frwk.fol.util.FwkReloadableResourceBundleMessageSource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

/**
 * 공통유틸리티자바스크립트생성 클래스
 * 
 * @author : 
 * @date : 2014. 11. 13.
 * @version : 1.0
 */
@Controller
@RequestMapping(value = "/fwk")
public class FwkMssageJavascriptController {
	
	@Resource(name="messageSource")
	private static FwkReloadableResourceBundleMessageSource messageSource;
	
	/**
	 * 공통 유틸리티 자바스크립 Write
	 * 
	 * @Method Name : writeJs 
	 * @param out
	 * @param request
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 13.
	 */
	@RequestMapping(value = "/MssageUtil.js",method = RequestMethod.POST)
	public static void messageUtil(Writer out, HttpServletRequest request) throws IOException {
		messageSource.getAllProperties(Locale.getDefault());
		
		Gson gson = new Gson();
		
		String jsonMessage = StringEscapeUtils.escapeJavaScript(gson.toJson(messageSource.getAllProperties(Locale.getDefault())));
		
		out.write("var FwkMssageUtil = {};");
		out.write("FwkMssageUtil['jsonMessage'] = JSON.parse('" + jsonMessage + "');");
		getMessage(out);				
	}
	
	private static void getMessage(Writer out) throws IOException {
		out.write("FwkMssageUtil.getMessage = function (key) {");
		out.write(" var s = FwkMssageUtil['jsonMessage'][key];");
		out.write(" var i = 1;");
		out.write(" while(i<arguments.length) {");
		out.write(" s = s.replace(\"{\" + (i-1) + \"}\", arguments[i++]);");
		out.write(" }");
		out.write(" return s;");
		out.write("};");
	}
		

}
