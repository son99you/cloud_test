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

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import egovframework.rte.fdl.string.EgovStringUtil;

/**
 * 문자열을 처리하는 유틸 클래스
 * 
 * @author : 
 * @date : 2014. 11. 10.
 * @version : 1.0
 */
public class FwkStringUtil {
	
	
	
	/**
	 * Default Constructor
	 */
	private FwkStringUtil() {		
	}
	
	/**
	 * 입력값이 null인지 확인하고 null인 경우 true를 반환한다.
	 * <p><pre>
	 * FwkStringUtil.isNull(null) = true
	 * FwkStringUtil.isNull("\t") = true
	 * FwkStringUtil.isNull("  ") = true
	 * FwkStringUtil.isNull("a") = false
	 * </pre>
	 * @Method Name : isNull 
	 * @param str - 체크대상 문자열
	 * @return - Null 인경우 true, Null이 아닌경우 false
	 * @author :  
	 * @date : 2014. 11. 10.
	 * @see #egovframework.rte.fdl.string.EgovStringUtil.isNull(String)
	 */
	public static boolean isNull(String str) {
		return EgovStringUtil.isNull(str);
	}
	
	/**
	 * 입력값이 null인지 확인하고 null인 경우 ""를 반환한다.
	 * <p><pre>
	 * FwkStringUtil.nvl(null) = ""
	 * FwkStringUtil.nvl("\t") = ""
	 * FwkStringUtil.nvl("  ") = ""
	 * FwkStringUtil.nvl("src") = "src"
	 * </pre>
	 * @Method Name : nvl
	 * @param src - 체크대상문자열
	 * @return
	 * @author :  
	 * @date : 2014. 11. 11.
	 * @see #egovframework.rte.fdl.string.EgovStringUtil.null2void(String)
	 */
	public static String nvl(String src) {
		return EgovStringUtil.null2void(src);
	}
	
	/**
	 * 입력값이 null인지 확인하고 null인 경우 대체문자열 's' 를 반환한다.
	 * <p><pre>
	 * FwkStringUtil.nvl(null, "s") = "s"
	 * FwkStringUtil.nvl("\t", "s") = "s"
	 * FwkStringUtil.nvl("  ", "s") = "s"
	 * FwkStringUtil.nvl("src", "s") = "src"
	 * </pre>
	 * @Method Name : nvl
	 * @param src - 체크대상 문자열
	 * @param s - 대체 문자열
	 * @return - 입력문자열이 null 인경우 대체문자열로 바꾸어 리턴
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static String nvl(String src, String s) {
		return EgovStringUtil.null2string(src, s);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : lpad 처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : lpad
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param len
	 * @param pad
	 * @return
	 */
	public static String lpad(String str, int len, char pad) {
		return EgovStringUtil.lPad(str, len, pad);		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : lpad 처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : lpad
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param len
	 * @param pad
	 * @return
	 */
	public static String lpad(String str, int len, String pad) {
		String str1 = FwkStringUtil.nvl(str);		
		for (int i = str1.length(); i < len; i++) {
			str1 = pad + str1;
		}		
		return str1;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : rpad 처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : rpad
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param len
	 * @param pad
	 * @return
	 */
	public static String rpad(String str, int len, char pad) {
		return EgovStringUtil.rPad(str, len, pad);		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : rpad 처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : rpad
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param len
	 * @param pad
	 * @return
	 */
	public static String rpad(String str, int len, String pad) {
		StringBuilder sb = new StringBuilder();		
		int strLen = str.length();		
		int len1 = len - strLen;
		for (int i = 1; i <= len1; i++) { 
			sb.append(pad);
		}		
		return	str + sb.toString();		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : trim 처리 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : trim
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return EgovStringUtil.trim(str);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 말줄임 처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : toShorten
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String toShorten(String str, int len) {
		if(isNull(str)) {
			return "";
		}
		return toShorten(str, len, "...");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 말줄임처리 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : toShorten
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param len
	 * @param shorter
	 * @return
	 */
	public static String toShorten(String str, int len, String shorter) { 
		if(isNull(str)) {
			return "";
		}		
		shorter = nvl(shorter, "...");
    	return (str.length() > len) ? str.substring(0, len) + shorter : str; 
    }
	
	/**
	 * <pre>
	 * 1. 개요 : replace 처리 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : replace
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String replace(String str, String s1, String s2) { 
		if(isNull(str)) {
			return "";
		}		
		return StringUtils.replace(str, s1, s2);		
    }
	
	/**
	 * <pre>
	 * 1. 개요 : Comma 제거 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteComma
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static String deleteComma(String str) { 
		if(isNull(str)) {
			return "";
		}		
		return replace(str, ",", "");
    }
	
	/**
	 * <pre>
	 * 1. 개요 : Hypen 제거 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteHypen
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static String deleteHypen(String str) { 
		if(isNull(str)) {
			return "";
		}		
		return replace(str, "-", "");
    }
	
	/**
	 * 입력값이 숫자로만 구성되어져 있는 체크 한후 숫자로만 구성된 경우 true를 반환한다 .
	 * <p><pre>
	 * FwkStringUtil.isNumber(null) = false
	 * FwkStringUtil.isNumber("\t") = false
	 * FwkStringUtil.isNumber("  ") = false
	 * FwkStringUtil.isNumber("1 234") = false
	 * FwkStringUtil.isNumber("1234") = true
	 * FwkStringUtil.isNumber("001234") = true
	 * </pre>
	 * @param str - 체크대상문자열
	 * @return - 입력문자열이 숫자로만 구성된 경우 true
	 */
	public static boolean isNumber(String str) {
		  
		if(isNull(str)) {
			return false;
		}
		
		return Pattern.matches("^[+-]?\\d*(\\.?\\d*)$", str);
	}
	
	/**
	 * 영문으로만 구성되어 있는지 체크한 후 영문으로만 구성된 경우 true를 반환한다.
	 * <p><pre>
	 * FwkStringUtil.isEnglish(null) = false	 
	 * FwkStringUtil.isEnglish("abc ") = false
	 * FwkStringUtil.isEnglish("abcCdD") = true	 
	 * </pre>
	 * @param str - 체크대상 문자열
	 * @return- 입력문자열이 영문으로만 구성된 경우 true
	 */
	public static boolean isEnglish(String str) {
		if(isNull(str)) {
			return false;
		}
		return Pattern.matches("^[a-zA-Z]*$", str);
	}
	
	/**
	 * 한글로만 구성되어 있는지 체크한 후 한글로만 구성된 경우 true를 반환한다.
	 * <p><pre>
	 * FwkStringUtil.isKorean(null) = false	 
	 * FwkStringUtil.isKorean("abc ") = false
	 * FwkStringUtil.isKorean(" 한글로만") = false	 
	 * FwkStringUtil.isKorean("한글로만") = true
	 * </pre> 
	 * @Method Name : isKorean 
	 * @param str - 체크대상문자열
	 * @return - 입력문자열이 한글로만 구성된 경우 true
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static boolean isKorean(String str) {
		if(isNull(str)) {
			return false;
		}
		return Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str);
	}
	
	/**
	 * 한글이 포함되어 있는지 체크한 후 한글포함 경우 true를 반환한다.
	 * <p><pre>
	 * FwkStringUtil.isKorean(null) = false	 
	 * FwkStringUtil.isKorean("abc") = false
	 * FwkStringUtil.isKorean(" 한글ab11") = true	 
	 * FwkStringUtil.isKorean("한글로만") = true
	 * </pre> 
	 * @Method Name : containsKorean 
	 * @param str - 체크대상문자열
	 * @return - 입력문자열에 한글리 있는 경우 true
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static boolean containsKorean(String str) {
		if(isNull(str)) {
			return false;
		}
		return Pattern.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*", str);
	}
	
	/**
	 * 영문+숫자 로만 구성되어 있는지 체크한 후 영문+숫자 로만 구성된 경우 true를 반환한다.
	 * 
	 * @Method Name : isEnglishAndNumber 
	 * @param str - 체크대상문자열
	 * @return 입력문자열이 영문+숫자 로만 구성된 경우 true
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static boolean isEnglishAndNumber(String str) {
		if(isNull(str)) {
			return false;
		}
		return Pattern.matches("^[a-zA-Z0-9]*$", str);
	}
	
	/**
	 * 한글+숫자 로만 구성되어 있는지 체크한 후 한글+숫자 로만 구성된 경우 true를 반환한다.
	 * 
	 * @Method Name : isKoreanAndNumber 
	 * @param str - 체크대상문자열
	 * @return 입력문자열이 한글+숫자 로만 구성된 경우 true
	 * @author : 
	 * @date : 2014. 11. 11.
	 */
	public static boolean isKoreanAndNumber(String str) {
		if(isNull(str)) {
			return false;
			
		}
		return Pattern.matches("^[ㄱ-ㅎ가-힣0-9]*$", str);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 이메일형식체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isEmail
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(final String str) {
		if(isNull(str)) {
			return false;			
		}
		return Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", str);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 휴대전화번호형식체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isMobilePhoneNumber
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobilePhoneNumber(final String str) {
		if(isNull(str)) {
			return false;			
		}
		return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", str);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 전화번호형식체크 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isTelephoneNumber
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTelephoneNumber(String str) {
		if(isNull(str)) {
			return false;			
		}
		return Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", str);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사업자등록번호형식체크 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isBizNumber
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBizNumber(String str) {
		if(isNull(str)) {
			return false;			
		}
		str = trim(str);
		
		int sum = 0;
		int temp = 0;
		int check[] = {1,3,7,1,3,7,1,3,5};
		for(int i=0; i < 9; i++) {
			sum = sum + (Character.getNumericValue(str.charAt(i)) * check[temp]); 
			temp++;
		}
		sum += (Character.getNumericValue(str.charAt(8))*5)/10;
		if ((10 - (sum%10))%10 == Character.getNumericValue(str.charAt(9))) {
			return true;
		}
		return false;		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 랜덤 UUID 생성 
	 * 2. 처리내용 : UUID '-' 문자를 제거한 문자열을 반환
	 * </pre>
	 * @Method Name : getRandomUUID
	 * @date : 2015. 3. 5.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 5.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public static String getRandomUUID() {
		return replace(UUID.randomUUID().toString(), "-", "");
	}
	
	/**
	 * 신세계 DB가 US7ASCII여서 한글이 있는 경우 ISO8859_1로 변환하여 전달
	 * @param strOrgValue
	 * @return
	 */
//	public static Object castEuc2Iso(Object strOrgValue){
//		Object rtnValue = "";	
//		try{
//			String[] strArr = ((String) strOrgValue).split("\r\n|\r|\n\r");
//			if(strArr.length <2){		
//				
//				if (containsKorean((String)strOrgValue)){			
//					try {
//						rtnValue = new String(((String)strOrgValue).getBytes("EUC_KR"),"ISO8859_1");
//					} catch (UnsupportedEncodingException e) {
//						
//					}
//				}else{
//					rtnValue = strOrgValue;
//				}		
//			}else{
//				try {
//					rtnValue = new String(((String)strOrgValue).getBytes("EUC_KR"),"ISO8859_1");
//				} catch (UnsupportedEncodingException e) {
//					return strOrgValue;
//				}
//			}
//		}catch(Exception ex){
//			return strOrgValue;
//		}
//		return rtnValue;
//	}
	
	/**
	 * 신세계 DB가 US7ASCII여서 한글이 있는 경우 ISO8859_1로 변환하여 전달(강제)
	 * @param strOrgValue
	 * @return
	 */
//	public static Object castEuc2IsoDefault(Object strOrgValue){
//		Object rtnValue = "";		
//		
//		try {
//			rtnValue = new String(((String)strOrgValue).getBytes("EUC_KR"),"ISO8859_1");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			
//		}
//		
//		return rtnValue;
//	}

	/**
	 * 신세계 DB가 US7ASCII여서 ISO8859_1로 저장된 데이터를 한글로 변환하여 전달
	 * @param strOrgValue
	 * @return
	 */
//	public static String fmIso2Euc(String strOrgValue){
//		
//		String rtnValue = "";
//		
//		try {
//			if(strOrgValue != null && !strOrgValue.equals("")){
//				rtnValue = new String(strOrgValue.getBytes("ISO8859_1"),"EUC_KR");
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			
//		}
//		
//		return rtnValue;
//	}
	
	
	// 기존 EUC-KR 인경우 원 데이터 리턴

	public static Object castEuc2Iso(Object strOrgValue){
		return strOrgValue;
	}
	

	public static Object castEuc2IsoDefault(Object strOrgValue){
		return strOrgValue;
	}

 	public static String fmIso2Euc(String strOrgValue){
		return strOrgValue;
	}

	
}
