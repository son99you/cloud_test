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

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 공통유틸리티자바스크립트생성 클래스
 * 
 * @author : 
 * @date : 2014. 11. 13.
 * @version : 1.0
 */
@Controller
@RequestMapping(value = "/fwk")
public class FwkCmmnUtilJavascriptController__ {
	
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
	@RequestMapping(value = "/CmmnUtil__.js",method = RequestMethod.POST)
	public static void cmmnUtil(Writer out, HttpServletRequest request) throws IOException {
		out.write("var FwkCmmnUtil = {};");
		out.write("FwkCmmnUtil['contextPath'] = '" + request.getContextPath() + "';");		
		out.write("FwkCmmnUtil['fileSeparator'] = '" + ("\\".equals(File.separator) ? "\\"+ File.separator :  File.separator) + "';");
		
		serializeObject(out);
		
		
		nvl(out);
		trim(out);
		isNull(out);
		isNotNull(out);
		isNumeric(out);
		isFloat(out);
		isEmail(out);
		isBizNumber(out);
		isMobilePhoneNumber(out);
		isTelePhoneNumber(out);
		isPostNumber(out);
		isEnglish(out);
		isEnglishAndNumber(out);
		setEnterKeyBinding(out);		
		setAllCheck(out);
		getCheckedCount(out);
		getCheckedIndex(out);
		getCheckedValue(out);
		deleteComma(out);
		addComma(out);
		getBytes(out);
		setEventHandler(out);
		
		validate(out);		
		submitAjax(out);
		submitForm(out);
		
		addHiddenParam(out);
		
	}
	
	
	/**
	 * 입력값에 대해 Null 여부를 반환한다.
	 * <p><pre>
	 * FwkCmmnUtil.isNull(null) = true	 
	 * FwkCmmnUtil.isNull() = true
	 * FwkCmmnUtil.isNull('') = true
	 * FwkCmmnUtil.isNull('a') = false
	 * </pre>
	 * @Method Name : isNull 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 13.
	 */
	private static void isNull(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isNull = function (str) {");
		out.write("str = $.trim(str);");
		out.write("if(str == null || str == 'undefined' || str.length == 0) { return true; } return false;");
		out.write("};");		
	}
	
	/**
	 * 입력값에 대해 Not Null 여부를 반환한다.
	 * <p><pre>
	 * FwkCmmnUtil.isNotNull(null) = false	 
	 * FwkCmmnUtil.isNotNull() = false
	 * FwkCmmnUtil.isNotNull('') = false	 
	 * </pre>
	 * @Method Name : isNotNull 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 13.
	 */
	private static void isNotNull(Writer out) throws IOException {
		out.write("FwkCmmnUtil.isNotNull = function (str) {");
		out.write("str = FwkCmmnUtil.trim(str);");
		out.write("if(str == null || str == 'undefined' || str.length == 0) { return false; } return true;");
		out.write("};");		
	}
	
	/**
	 * nvl
	 * 
	 * @Method Name : nvl
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 13.
	 */
	private static void nvl(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.nvl = function (str, rstr) {");
		out.write("if(FwkCmmnUtil.isNull(str)) { return rstr == null || rstr == 'undefined' ? '' : rstr; } return str;");
		out.write("};");		
	}
	
	/**
	 * trim
	 * 
	 * @Method Name : trim 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 24.
	 */
	private static void trim(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.trim = function (str) {");
		out.write("return $.trim(str);");
		out.write("};");		
	}
	
	
	
	/**
	 * isNumeric
	 * 
	 * @Method Name : isNumeric 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 24.
	 */
	private static void isNumeric(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isNumeric = function (str) {");
		out.write("str += '';");
		out.write("str = str.replace(/^\\s*|\\s*$/g, '');");
		out.write("if (str == '' || isNaN(str)) return false;");
		out.write("return true;");
		out.write("};");
	}
	
	/**
	 * isFloat
	 * 
	 * @Method Name : isFloat 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 24.
	 */
	private static void isFloat(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isFloat = function (str) {");
		out.write("var re = /^[\\+-]?[0-9]*[.]?[0-9]*[0-9]$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	private static void isEmail(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isEmail = function (str) {");
		out.write("var re =/^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	private static void isMobilePhoneNumber(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isMobilePhoneNumber = function (str) {");
		out.write("if(FwkCmmnUtil.isNull(str)) {return false;} ");
		out.write("var re =/^(010|011|016|017|018|019)-\\d{3,4}-\\d{4}$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	private static void isTelePhoneNumber(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isTelePhoneNumber = function (str) {");
		out.write("if(FwkCmmnUtil.isNull(str)) {return false;} ");
		out.write("var re =/^(070|02|031|032|033|041|042|043|051|052|053|054|055|061|062|063|064)-\\d{3,4}-\\d{4}$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	private static void isPostNumber(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isPostNumber = function (str) {");
		out.write("if(FwkCmmnUtil.isNull(str)) {return false;} ");
		out.write("var re =/^\\d{3}-?\\d{3}$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	private static void isBizNumber(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isBizNumber = function (str) {");
		out.write("if(FwkCmmnUtil.isNull(str)) {return false;} ");
		out.write("var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); ");
		out.write("var tmpBizID, i, chkSum=0, c2, remander; ");
		out.write("str = str.replace(/-/gi,''); ");
		out.write("for (i=0; i<=7; i++) {chkSum += checkID[i] * str.charAt(i);}");
		out.write("c2 = \"0\" + (checkID[8] * str.charAt(8)); ");
		out.write("c2 = c2.substring(c2.length - 2, c2.length); ");
		out.write("chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); ");
		out.write("remander = (10 - (chkSum % 10)) % 10 ; ");
		out.write("chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); ");
		out.write("if (Math.floor(str.charAt(9)) == remander) {return true; }");
		out.write("return false;");
		
		
		out.write("};");
	}
	
	/**
	 * isEnglish
	 * 
	 * @Method Name : isEnglish 
	 * @param out
	 * @throws IOException
	 * @author : 케이사인 김장호 
	 * @date : 2015. 06. 26.
	 */
	private static void isEnglish(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isEnglish = function (str) {");
		out.write("var re = /^[a-zA-Z_\\-\\.\\s]*$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	/**
	 * isEnglishAndNumber
	 * 
	 * @Method Name : isEnglishAndNumber 
	 * @param out
	 * @throws IOException
	 * @author : 케이사인 김장호 
	 * @date : 2015. 06. 26.
	 */
	private static void isEnglishAndNumber(Writer out) throws IOException {		
		out.write("FwkCmmnUtil.isEnglishAndNumber = function (str) {");
		out.write("var re = /^[a-zA-Z0-9_\\-\\.]*$/;");
		out.write("if (re.test(str)) { return	true; }");
		out.write("return false;");
		out.write("};");
	}
	
	/**
	 * 엔터키바인딩
	 * 
	 * @Method Name : setEnterKeyBinding 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 24.
	 */
	private static void setEnterKeyBinding(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.setEnterKeyBinding = function (formId, formFields, callback) {");		
		out.write("$.each(formFields, function() {");
		out.write("var obj = $( \"#\" + formId + \" #\" + this);");
		out.write("obj.on(\"keypress\", function(e) {");
		out.write("var key = window.event ? e.keyCode : e.which;");
		out.write("if(key == 13) { eval(callback)(); return false; }");
		out.write("});");
		out.write("});");
		out.write("};");
	}
	
	/**
	 * 비동기 Submit
	 * 
	 * @Method Name : submitAjax 
	 * @param out
	 * @throws IOException
	 * @author :  
	 * @date : 2014. 11. 24.
	 */
	private static void submitAjax(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.submitAjax = function (actionUrl, jsonParam, callbackSuccess, callbackBeforeSend, callbackComplete, callbackError) {");		
		out.write("var jsonData = JSON.stringify(jsonParam);");
		out.write("actionUrl = FwkCmmnUtil.contextPath + actionUrl + \".json\";");
		out.write("$.ajax({");
		out.write("type : \"POST\",");
		out.write("contentType : \"application/json;charset=UTF-8\",");
		out.write("url : actionUrl,");
		out.write("data : jsonData,");		
		out.write("async:false,");
		out.write("beforeSend : function() { if(typeof callbackBeforeSend == 'function') callbackBeforeSend(); },");
		out.write("success : function(res) { if(typeof callbackSuccess == 'function') callbackSuccess(res); },");
		out.write("complete : function() { if(typeof callbackComplete == 'function') callbackComplete(); },");
		out.write("error : function(xhr , textStatus , error) { if(typeof callbackError == 'function') {callbackError(JSON.parse(xhr.responseText), status, error);} else {alert(JSON.parse(xhr.responseText).errorMessage);} },");
		out.write("});");		
		out.write("};");
	}
	
	private static void setAllCheck(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.setAllCheck = function (chkAllId, chkValName, tableId) {");	
		out.write("var selector;");
		out.write("if(undefined !== tableId && tableId != \"\" ){");
		out.write("selector = \"#\"+tableId+\" input[name='\"+chkValName+\"']\";");
		out.write("}else{");
		out.write("selector = \"input[name='\"+chkValName+\"']\";");
		out.write("}");
		out.write("if($(selector).length < 1) {");
		out.write("$(\"#\" + chkAllId).removeAttr(\"checked\");");
		out.write("return ;");
		out.write("}");		
		out.write("if( $(\"#\" + chkAllId).is(\":checked\")) {");
		out.write("$(selector).each(");
		out.write("function(idx, obj) {");
		out.write("if(FwkCmmnUtil.isNull($(obj).prop(\"disabled\")) || $(obj).prop(\"disabled\") == false) {");
		out.write("$(obj).prop(\"checked\", true);");
		out.write("}});");
		out.write("} else {");
		out.write("$(selector).removeAttr(\"checked\");");
		out.write("}");
		out.write("$(selector).click(function() {");		
		out.write("var allCnt = $(selector).length;");
		out.write("var checkedCnt = $(selector).filter(\":checked\").length;");
		out.write("var disableCnt = $(selector).filter(\":disabled\").length;");
		out.write("var totCnt = checkedCnt + disableCnt;");
		out.write("if(allCnt==totCnt) {");
		out.write("$(\"#\" + chkAllId).prop(\"checked\", \"checked\");");
		out.write("} else {");
		out.write("$(\"#\" + chkAllId).removeAttr(\"checked\");");
		out.write("}");
		out.write("});");
		
		out.write("};");
	}
	
	private static void getCheckedCount(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.getCheckedCount = function (chkValName) {");		
		
		out.write("var cnt = 0;");
		out.write("$(\"input[name='\" + chkValName + \"']\").each(");		
		out.write("function(index) {");
		out.write("if($(this).is(':checked')) { cnt++; }");
		out.write("}");
		out.write(");");
		out.write("return cnt;");		
		
		out.write("};");
	}

	private static void getCheckedIndex(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.getCheckedIndex = function (chkValName) {");		
		out.write("var rtn = {};");
		out.write("var cnt = 0;");
		out.write("$(\"input[name='\" + chkValName + \"']\").each(");		
		out.write("function(index) {");
		out.write("if($(this).is(':checked')) { rtn[cnt] = index; }");
		out.write("cnt++;");
		out.write("}");
		out.write(");");
		out.write("return rtn;");		
		
		out.write("};");
	}
	
	private static void getCheckedValue(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.getCheckedValue = function (chkValName) {");		
		out.write("var rtn = [];");
		out.write("$(\"input[name='\" + chkValName + \"']:checked\").each(");		
		out.write("function(index) {");
		out.write("rtn.push($(this).val());");
		out.write("}");
		out.write(");");
		out.write("return rtn;");		

		out.write("};");
	}
	
	private static void submitForm(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.submitForm = function (formId, actionUrl, hiddenParam) {");		
		
		out.write("var $frm = $(\"#\" + formId);");
		out.write("actionUrl = FwkCmmnUtil.contextPath + actionUrl;");
		
		out.write("if(hiddenParam) {");
		out.write("FwkCmmnUtil.addHiddenParam(formId, hiddenParam);");
		out.write("}");
		
		out.write("$frm.attr(\"method\",  \"POST\");");
		out.write("$frm.attr(\"action\", actionUrl);");		
		out.write("$frm.submit();");		
		out.write("return false;");
		
		out.write("};");
	}
	
	private static void addHiddenParam(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.addHiddenParam = function (formId, params) {");
		out.write("var formObj = $(\"#\" + formId);");
		
		out.write("$.each(params, function(i, item) {");
		out.write("if($(\"#\" + formId + \" #\" + item.id).length == 0) {");
		out.write("formObj.append('<input type=\"hidden\" id=\"' + item.id + '\" name=\"' + item.name + '\" value=\"' + item.value + '\" />');");
		out.write("}");
		out.write("});");		
		out.write("};");
	}
	
	private static void deleteComma(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.deleteComma = function (str) {");
		
		out.write("str = FwkCmmnUtil.nvl(str);");
//		out.write("return str.replace(/[^\\d]+/g, '');");
		out.write("return str.replace(/,/gi,\"\");");
		
		out.write("};");
	}
	
	private static void addComma(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.addComma = function (str) {");
		out.write("str = String(FwkCmmnUtil.nvl(str));");
		out.write("return str.replace(/(\\d)(?=(?:\\d{3})+(?!\\d))/g, '$1,');");
		
		out.write("};");
	}
	
	private static void getBytes(Writer out) throws IOException {	
		out.write("FwkCmmnUtil.getBytes = function (str) {");		
		out.write("str = FwkCmmnUtil.nvl(str);");
		
		out.write("var str_character;");
		out.write("var int_char_count = 0;");
		out.write("var int_contents_length = str.length;");
		
		out.write("for (k = 0; k < int_contents_length; k++) {");
		out.write("str_character = str.charAt(k);");
		out.write("if (escape(str_character).length > 4) {int_char_count +=3}");
		out.write("else {int_char_count++;}}");
		
		out.write("return int_char_count;");
		
		out.write("};");
	}
	

	private static void setEventHandler(Writer out) throws IOException {	
		out.write("	FwkCmmnUtil.setEventHandler = function (formId, checkData) {										");
		out.write("		$.each(checkData, function(i, item) {																		");
		out.write("			$.each(item, function(key, value) {																		");
		out.write("				var required = value.required;																			");
		out.write("				var number = value.number;																			");		
		out.write("				var float = value.float;																					");
		out.write("				var date = value.date;																					");
		out.write("				var biz = value.biz;																						");
		out.write("				var obj = $(\"#\" +formId + \"\").find(\"input[name='\" + key + \"']\");					");		
		out.write("				if(obj.length == 0) {																						");
		out.write("					obj = $(\"#\" +formId + \"\").find(\"select[name='\" + key + \"']\");					");
		out.write("				}																												");
		out.write("				if(obj.length == 0) {																						");
		out.write("					obj = $(\"#\" +formId + \"\").find(\"textarea[name='\" + key + \"']\");					");
		out.write("				}																												");
		out.write("				obj.each(function() {																					");
		out.write("					if( FwkCmmnUtil.isNull(number) == false ) {													");
		out.write("						$(this).css(\"ime-mode\", \"disabled\"); 													");		
		out.write("						$(this).keypress(function(event) {		 													");
		out.write("							if(event.which && (event.which < 48 || event.which > 57) ) {					");
		out.write("								event.preventDefault();					 												");
		out.write("							}												 													");
		out.write("						});												 													");
		out.write("						if(number.format == \"comma\") {			 												");
		out.write("							$(this).keyup(function(event) {		 													");
		out.write("								if( $(this).val() != null && $(this).val() != '' ) {									");
		out.write("									var tmps = $(this).val().replace(/[^0-9]/g, '');								");		
		out.write("									var tmps2 = tmps.replace(/(\\d)(?=(\\d\\d\\d)+(?!\\d))/g, \"$1,\");	");
		out.write("									$(this).val(tmps2);																	");
		out.write("								}												 												");
		out.write("							});												 												");
		out.write("						} else {											 													");
		out.write("							$(this).keyup(function(event) {		 													");
		out.write("								if( $(this).val() != null && $(this).val() != '' ) {									");
		out.write("									$(this).val( $(this).val().replace(/[^0-9]/g, '') );							");
		out.write("								}												 												");
		out.write("							});												 												");
		out.write("						}													 													");		
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(float) == false ) {														");
		out.write("						$(this).css(\"ime-mode\", \"disabled\"); 													");		
		out.write("						$(this).keypress(function(event) {		 													");
		out.write("							if(event.which && (event.which < 45 || event.which > 57) ) {					");
		out.write("								event.preventDefault();					 												");
		out.write("							}												 													");
		out.write("						});												 													");
		out.write("						if(float.format == \"comma\") {			 													");
		out.write("							$(this).keyup(function(event) {		 													");
		out.write("								if( $(this).val() != null && $(this).val() != '' ) {									");
		out.write("									var tmps = $(this).val().replace(/[^\\.0-9]/g, '');							");		
		out.write("									var tmps2 = tmps.replace(/(\\d)(?=(\\d\\d\\d)+(?!\\d))/g, \"$1,\");	");
		out.write("									$(this).val(tmps2);																	");
		out.write("								}												 												");
		out.write("							});												 												");
		out.write("						} else {											 													");
		out.write("							$(this).keyup(function(event) {		 													");
		out.write("								if( $(this).val() != null && $(this).val() != '' ) {									");
		out.write("									$(this).val( $(this).val().replace(/[^\\.0-9]/g, '') );						");
		out.write("								}												 												");
		out.write("							});												 												");
		out.write("						}													 													");		
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(date) == false ) {														");
		out.write("						$(this).css(\"ime-mode\", \"disabled\"); 													");
		out.write("						$(this).keypress(function(event) {		 													");
		out.write("							if(event.which && (event.which < 45 || event.which > 57) ) {					");
		out.write("								event.preventDefault();					 												");
		out.write("							}												 													");
		out.write("						});												 													");
		out.write("						$(this).keyup(function(event) {		 														");
		out.write("							if( $(this).val() != null && $(this).val() != '' ) {										");
		out.write("								$(this).val( $(this).val().replace(/[^0-9:\\-]/gi,\"\")  );						");
		out.write("							}												 													");
		out.write("						});												 													");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(biz) == false ) {															");
		out.write("						$(this).css(\"ime-mode\", \"disabled\"); 													");
		out.write("						$(this).keypress(function(event) {		 													");
		out.write("							if(event.which && (event.which < 45 || event.which > 57) ) {					");
		out.write("								event.preventDefault();					 												");
		out.write("							}												 													");
		out.write("						});												 													");
		out.write("						$(this).keyup(function(event) {		 														");
		out.write("							if( $(this).val() != null && $(this).val() != '' ) {										");
		out.write("								$(this).val( $(this).val().replace(/[^0-9:\\-]/gi,\"\")  );						");
		out.write("							}												 													");
		out.write("						});												 													");
		out.write("					}																											");	
		
		out.write("				});																											");
		out.write("			});																												");
		out.write("		});																													");
		out.write("		return rtn;																											");		
		out.write("	};																															");
	}
		
		
	
	private static void validate(Writer out) throws IOException {	
		out.write("	FwkCmmnUtil.validate = function (formId, checkData) {													");				
		out.write("		var rtn = true;																										");
		out.write("		$.each(checkData, function(i, item) {																		");
		out.write("			$.each(item, function(key, value) {																		");
		out.write("				var required = value.required;																			");
		
		out.write("				var number = value.number;																			");
		out.write("				var english = value.english;																			");
		out.write("				var englishAndNumber = value.englishAndNumber;																			");
		out.write("				var float = value.float;																					");
		out.write("				var biz = value.biz;																						");
		out.write("				var len = value.len;																						");
		out.write("				var date = value.date;																					");
		out.write("				var obj = $(\"#\" +formId + \"\").find(\"input[name='\" + key + \"']\");					");		
		out.write("				if(obj.length == 0) {																						");
		out.write("					obj = $(\"#\" +formId + \"\").find(\"select[name='\" + key + \"']\");					");
		out.write("				}																												");
		out.write("				if(obj.length == 0) {																						");
		out.write("					obj = $(\"#\" +formId + \"\").find(\"textarea[name='\" + key + \"']\");					");
		out.write("				}																												");
		out.write("				obj.each(function() {																					");
		out.write("					var val = FwkCmmnUtil.nvl($(this).val());														");
		out.write("					if( FwkCmmnUtil.isNull(required) == false ) {													");		 
		out.write("						if( FwkCmmnUtil.isNull(val) == true) { 														");
		
		out.write("							alert(required.message);																	");
		out.write("							$(this).focus();																				");		
		out.write("							rtn = false;																						");		
		out.write("							return false;																					");
		out.write("						}																										");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(number) == false ) {													");
		out.write("						val = FwkCmmnUtil.deleteComma(val); 														");
		out.write("						if(val != null && val.length > 0 ) {															");		
		out.write("							if(FwkCmmnUtil.isNumeric(val) == false) {												");
		out.write("								alert(number.message);																	");
		out.write("								$(this).focus();																			");
		out.write("								rtn = false;																					");		
		out.write("								return false;																				");
		out.write("							}																									");
		out.write("						}																										");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(english) == false ) {													");
		out.write("						if(val != null && val.length > 0 ) {															");		
		out.write("							if(FwkCmmnUtil.isEnglish(val) == false) {												");
		out.write("								alert(english.message);																	");
		out.write("								$(this).focus();																			");
		out.write("								rtn = false;																					");		
		out.write("								return false;																				");
		out.write("							}																									");
		out.write("						}																										");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(englishAndNumber) == false ) {													");
		out.write("						if(val != null && val.length > 0 ) {															");		
		out.write("							if(FwkCmmnUtil.isEnglishAndNumber(val) == false) {												");
		out.write("								alert(englishAndNumber.message);																	");
		out.write("								$(this).focus();																			");
		out.write("								rtn = false;																					");		
		out.write("								return false;																				");
		out.write("							}																									");
		out.write("						}																										");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(float) == false ) {														");
		out.write("						val = FwkCmmnUtil.deleteComma(val); 														");
		out.write("						if(val != null && val.length > 0 ) {															");
		out.write("							if(FwkCmmnUtil.isFloat(val) == false) {														");
		out.write("								alert(float.message);																		");
		out.write("								$(this).focus();																				");
		out.write("								rtn = false;																						");		
		out.write("								return false;																					");
		out.write("							}																										");
		out.write("						}																											");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(biz) == false ) {															");
		out.write("						if(val != null && val.length > 0 ) {															");
		out.write("							if(FwkCmmnUtil.isBizNumber(val) == false) {												");		
		out.write("								alert(biz.message);																			");
		out.write("								$(this).focus();																				");
		out.write("								rtn = false;																						");		
		out.write("								return false;																					");
		out.write("							}																										");
		out.write("						}																											");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(len) == false ) {															");
		out.write("						if(val != null && val.length > 0 ) {															");
		out.write("							var max = len.max;																				");
		out.write("							var min = len.min;																					");		
		out.write("							var blen = FwkCmmnUtil	.getBytes(val);													");
		out.write("							if(blen < min || blen > max) {																	");
		out.write("								alert(len.message);																			");
		out.write("								$(this).focus();																				");
		out.write("								rtn = false;																						");		
		out.write("								return false;																					");
		out.write("							}																										");		
		out.write("						}																											");
		out.write("					}																											");
		
		out.write("					if( FwkCmmnUtil.isNull(date) == false ) {														");
		out.write("						if(val != null && val.length > 0 ) {															");
		out.write("							if(FwkDateUtil.isDate(val, date.format) == false) {										");
		out.write("								alert(date.message);																		");
		out.write("								$(this).focus();																				");
		out.write("								rtn = false;																						");		
		out.write("								return false;																					");
		out.write("							}																										");
		out.write("						}																										");
		out.write("					}																											");		
		out.write("				});																											");
		out.write("				if(rtn == false) return false;																			");
		out.write("			});																												");
		out.write("		});																													");
		out.write("		return rtn;																											");		
		out.write("	};																															");
	}	
			
	
	private static void  serializeObject(Writer out) throws IOException {	
		out.write("$.fn.serializeObject = function() {");
		out.write("var arrayData, objectData;");
		out.write("arrayData = this.serializeArray();");
		
		out.write(" objectData = {};");
		out.write("$.each(arrayData, function() {");
		out.write("var value;");
		out.write(" if (this.value != null) { value = this.value; } else { value = ''; }");
		out.write("if (objectData[this.name] != null) {");
		out.write("if (!objectData[this.name].push) { objectData[this.name] = [objectData[this.name]];  }");
		out.write("objectData[this.name].push(value);");
		out.write("} else {");
		out.write("objectData[this.name] = value;");
		out.write("}});");
		out.write("return objectData;");

		
		out.write("};");
		
		
		
	}	
}

