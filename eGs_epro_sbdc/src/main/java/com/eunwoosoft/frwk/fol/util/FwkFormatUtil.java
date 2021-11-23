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

import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 문자열포맷을 처리하는 유틸 클래스
 * 
 * @author : 
 * @date : 2014. 11. 10.
 * @version : 1.0
 */
public class FwkFormatUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkFormatUtil.class);
	
	/**
	 * Default Constructor
	 */
	private FwkFormatUtil() {		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 날짜 포맷 지정 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : formatDate
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
	 * @param oFormat
	 * @param nFormat
	 * @return
	 */
	public static String formatDate(String str, String oFormat, String nFormat) {
		
		if(FwkStringUtil.isNull(str)) {
			return "";
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat (oFormat, Locale.KOREA);		
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
			LOG.error(e.toString());			
		}		
		formatter.applyPattern(nFormat);
		return formatter.format(date);		
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 금액포맷지정 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : formatMoney
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
	public static String formatMoney(String str) {
		
		if(FwkStringUtil.isNull(str)) {
			return "";
		}
		
		/*long money = Long.parseLong(str);
		if(money > -1000 && money < 1000) {
			return money + "";
		}*/
		
		BigInteger money = new BigInteger(str);
		BigInteger min = new BigInteger("-1000");
		BigInteger max = new BigInteger("1000");
		if( money.compareTo(min) == 1 && money.compareTo(max) == -1 ){
			return money.toString();
		}
		
		NumberFormat numFormat = NumberFormat.getNumberInstance(); 
		numFormat.setMinimumIntegerDigits(3) ; 
		return numFormat.format(money);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 금액포맷지정(소수점)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : formatMoney
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
	public static String formatMoneyDp(String str) {
		
		if(FwkStringUtil.isNull(str)) {
			return "";
		}
		
		int dpl = str.indexOf(".");
		String dpt = "";
		
		if(dpl > -1){
			dpt = str.substring(dpl, str.length());
			str = str.substring(0, dpl);
		}
		
		/*long money = Long.parseLong(str);
		if(money > -1000 && money < 1000) {
			return money + "";
		}*/
		
		BigInteger money = new BigInteger(str);
		BigInteger min = new BigInteger("-1000");
		BigInteger max = new BigInteger("1000");
		if( money.compareTo(min) == 1 && money.compareTo(max) == -1 ){
			return money.toString()+dpt;
		}
		
		NumberFormat numFormat = NumberFormat.getNumberInstance(); 
		numFormat.setMinimumIntegerDigits(3) ; 
		return numFormat.format(money)+dpt;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 우편번호포맷지정 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : formatPostNumber
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
	public static String formatPostNumber(String str) {
		
		if(FwkStringUtil.isNull(str)) {
			return "";
		}
		
		int len = str.length();
		
		if(len != 6) {
			return str;
		}
		
		String message = "{0}-{1}";		 
        Object[] args = new String[2];        
        
        args[0] = str.substring(0, 3); 
        args[1] = str.substring(3); 
		
        return MessageFormat.format(message, args);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사업자등록번호 포맷 지정 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : formatBizNumber
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
	public static String formatBizNumber(String str) {
		if(FwkStringUtil.isNull(str)) {
			return "";
		}
		int len = str.length();
		
		if(len != 10) {
			return str;
		}
		 
		String message = "{0}-{1}-{2}";
		 
        Object[] args = { str.substring(0, 3), str.substring(3, 5), str.substring(5, 10) }; 
        return MessageFormat.format(message, args); 
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 전화번호포맷지정 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : formatPhoneNumber
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
	public static String formatPhoneNumber(String str) {
		if(FwkStringUtil.isNull(str)) {
			return "";
		}
		if(str == null) {
			return "";
		}
		
		int len = str.length();
		
		if(len < 9) {
			return str;
		}
		
		String message = "{0}-{1}-{2}";		 
        Object[] args = new String[3]; 
        String body = null;
        
        if (str.startsWith("01")) {        	 
            args[0] = str.substring(0, 3); 
            body = str.substring(3); 
        } else if (str.startsWith("02")) { //서울 
            args[0] = str.substring(0, 2); 
            body = str.substring(2); 
        } else { 
            args[0] = str.substring(0, 3); 
            body = str.substring(3);
        }
        
        if (body.length() == 7) {        	 
            args[1] = body.substring(0, 3); 
            args[2] = body.substring(3); 
        } else if (body.length() == 8) { 
            args[1] = body.substring(0, 4); 
            args[2] = body.substring(4); 
        } else { 
            message = "{0}-{1}"; 
            args[1] = body; 
        }
        
        return MessageFormat.format(message, args);
	}

}
