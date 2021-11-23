package com.eunwoosoft.frwk.fol.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import egovframework.rte.fdl.string.EgovStringUtil;

public class UtilTest {

	public static void main(String[] args) {
		//해당 temp를 DB에 저장 
		String temp = "한글 테스트";
        temp = (String) castEuc2Iso(temp);
        
        //DB에서 한글 불러와서 화면이나 자바에서 처리
        String dbData = ""; // DB에서 조회해온 US7ASCII 데이터
        dbData = fmIso2Euc(dbData);
        
	}
	
	/**
	 * 신세계 DB가 US7ASCII여서 한글이 있는 경우 ISO8859_1로 변환하여 전달 - 소스에서 DB 한글 저장시
	 * @param strOrgValue
	 * @return
	 */
	public static Object castEuc2Iso(Object strOrgValue){
		Object rtnValue = "";	
		try{
			String[] strArr = ((String) strOrgValue).split("\r\n|\r|\n\r");
			if(strArr.length <2){		
				
				if (containsKorean((String)strOrgValue)){			
					try {
						rtnValue = new String(((String)strOrgValue).getBytes("EUC_KR"),"ISO8859_1");
					} catch (UnsupportedEncodingException e) {
						
					}
				}else{
					rtnValue = strOrgValue;
				}		
			}else{
				try {
					rtnValue = new String(((String)strOrgValue).getBytes("EUC_KR"),"ISO8859_1");
				} catch (UnsupportedEncodingException e) {
					return strOrgValue;
				}
			}
		}catch(Exception ex){
			return strOrgValue;
		}
		return rtnValue;
	}



	/**
	 * 신세계 DB가 US7ASCII여서 ISO8859_1로 저장된 데이터를 한글로 변환하여 전달 - DB에서 한글 불러올 경우
	 * @param strOrgValue
	 * @return
	 */
	public static String fmIso2Euc(String strOrgValue){
		
		String rtnValue = "";
		
		try {
			if(strOrgValue != null && !strOrgValue.equals("")){
				rtnValue = new String(strOrgValue.getBytes("ISO8859_1"),"EUC_KR");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			
		}
		
		return rtnValue;
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

}
