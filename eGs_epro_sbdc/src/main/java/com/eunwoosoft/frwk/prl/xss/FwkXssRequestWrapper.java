/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.prl.xss;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * <pre>
 * com.eunwoosoft.frwk.prl.xss 
 *    |_ FwkXssRequestWrapper.java
 * 
 * </pre>
 * @date : 2015. 9. 25. 오전 9:12:19
 * @version : 1.0
 * @author : 
 */
public class FwkXssRequestWrapper extends HttpServletRequestWrapper {
	
	public FwkXssRequestWrapper(HttpServletRequest request) {
		super(request);		
	}
	
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);		
		if(values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for(int i=0 ; i<count ; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}		
		return encodedValues;
	}
	
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);		
		if(value == null) {
			return null;
		}
		return cleanXSS(value);
	}
	
	public String getHeader(String name) {
		String value = super.getHeader( name);		
		if(value == null) {
			return null;
		}
		return cleanXSS(value);
	}
	
	public String cleanXSS(String value) {
		
		if(value == null) {
			return "";
		}
		value = value.replaceAll("", "");
		Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		value = HtmlUtils.htmlEscape(value);
		value = StringEscapeUtils.escapeSql(value);
		
		return value;
		
	}

}
