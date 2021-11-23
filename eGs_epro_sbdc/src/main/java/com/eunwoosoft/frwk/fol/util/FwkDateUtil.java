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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import egovframework.rte.fdl.string.EgovDateUtil;

/**
 * 날짜를 처리하는 유틸 클래스
 * 
 * @author : 
 * @date : 2014. 11. 11.
 * @version : 1.0
 */
public class FwkDateUtil {
	
	public static final String DEFAULT_FORMAT = "yyyyMMdd";
	
	/**
	 * 현재날짜를 'yyyyMMdd' 형태로 반환한다.
	 * 
	 * @Method Name : getCurrentDateAsString 
	 * @return
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static String getCurrentDateAsString() {		
		return EgovDateUtil.getCurrentDateAsString();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 현재 날짜를 입력 pattern 형태로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getCurrentDateAsString
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateAsString(String pattern) {		
		return convertToString(new Date(), pattern);
	}
	
	/**
	 * 현재시간을 'HHmmss' 형태로 반환한다.
	 * 
	 * @Method Name : getCurrentTimeAsString 
	 * @return
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static String getCurrentTimeAsString() {		
		return EgovDateUtil.getCurrentTimeAsString();		
	}
	
	/**
	 * 현재밀리초를 'SSS' 형태로 반환한다.
	 * 
	 * @Method Name : getCurrentDateTimeAsString 
	 * @return
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static String getCurrentMilliSecondAsString() {
		return EgovDateUtil.getCurrentMilliSecondAsString();
	}
	
	/**
	 * 현재일시를 'yyyyMMddHHmmss' 형태로 반환한다.
	 * 
	 * @Method Name : getCurrentDateTimeAsString 
	 * @return
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static String getCurrentDateTimeAsString() {
		return EgovDateUtil.getCurrentDateTimeAsString();		
	}
	
	/**
	 * 현재일시를 'yyyyMMddHHmmssSSS' 형태로 반환한다.
	 * 
	 * @Method Name : getCurrentDateTimeMilliSecondAsString 
	 * @return
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public static String getCurrentDateTimeMilliSecondAsString() {		
		return EgovDateUtil.getCurrentDateTimeAsString() + getCurrentMilliSecondAsString();		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Date 타입을 'yyyyMMdd' String 타입으로 변환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : convertToString
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToString(Date date) {
		return convertToString(date, DEFAULT_FORMAT);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Date 타입을 입력 pattern 형태의 String 타입으로 변환하여 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : convertToString
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertToString(Date date, String pattern) {		
		SimpleDateFormat formatter = new SimpleDateFormat (pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(date);
		return dateString;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : String 을 Data 타입으로 변환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : convertToDate
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date convertToDate(String s) throws ParseException {
		return convertToDate(s, DEFAULT_FORMAT);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Strring 타입을 패턴현태의 Data타입으로 변환 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : convertToDate
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param s
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date convertToDate(String s, String pattern) throws ParseException {
		if ( FwkStringUtil.isNull(s) ) {
			throw new ParseException("date string to check is null", 0);
		}
		if ( FwkStringUtil.isNull(pattern) ) {
			throw new ParseException("pattern string to check date is null", 0);
		}
		SimpleDateFormat formatter = new SimpleDateFormat (pattern, Locale.KOREA);		
		Date date = formatter.parse(s);		
		return date;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입력 Date 값에서 amout 값만큼 년도를 증감하여 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addYears
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 입력 Date 값에서 amout 값만큼 월을 증감하여 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addMonths
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 입력 Date 값에서 amout 값만큼 일을 증감하여 반환한다 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addDate
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDate(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
    }
	
	/**
	 * <pre>
	 * 1. 개요 :입력 Date 값에서 amout 값만큼 주를 증감하여 반환한다
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addWeeks
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 시간계산 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addHours
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 분계산 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addMinutes
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 초계산 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addSeconds
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : add
	 * @date : 2015. 1. 19.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 19.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param date
	 * @param calendarField
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 날짜 형식 체크 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isDate
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
	 * @param pattern
	 * @return
	 */
	public static boolean isDate(String str, String pattern) {
		
		if(FwkStringUtil.isNull(str)) {
			return false;
		}
		if(FwkStringUtil.isNull(pattern)) {
			return false;
		}		
		try {
			EgovDateUtil.dateFormatCheck(str, pattern);
		} catch(ParseException e) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 날짜계산(두 날짜의 차이)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : diffOfDate
	 * @date : 2015. 7. 28.
	 * @author : 야긴스텍 유경민
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 7. 28.		야긴스텍 유경민			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws ParseException 
	 */ 	
	public static long diffOfDate(String begin, String end) throws ParseException
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	 
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);
	 
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	 
	    return diffDays + 1;
	 }
	
	public static boolean isLeapYear(String year){
		boolean isLeapYY = false;
		
		GregorianCalendar gregori = new GregorianCalendar();
		
		if(gregori.isLeapYear(Integer.parseInt(year))){
			isLeapYY = true;
		}		
		
		return isLeapYY; 
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 날짜크기비교(int 반환)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : compareDate
	 * @date : 2019. 05. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 08.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param begin
	 * @param end
	 * @return
	 * @throws ParseException 
	 */ 	
	public static int compareDate(String firstDate, String secondDate) throws ParseException {
		/**
		 * if firstDate > secondDate -> 1
		 * if firstDate == secondDate -> 0
		 * if firstDate < secondDate -> -1
		 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.parse(firstDate).compareTo(formatter.parse(secondDate));
	}
}
