/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * <pre>
 * com.eunwoosoft.frwk.fol.util 
 *    |_ SDBCryptoUtil.java
 * 
 * </pre>
 * @date : 2015. 2. 9. 오후 8:43:44
 * @version : 1.0
 * @author : 
 */
public class SDBCryptoUtil {
	
	private static MessageSourceAccessor comPropertiesAccessor = null;

	public void setMessageSourceAccessor(MessageSourceAccessor comPropertiesAccessor) {
		SDBCryptoUtil.comPropertiesAccessor = comPropertiesAccessor;
	}

	
	
	/**
	 * <pre>
	 * 1. 개요 : encrypt 처리
	 * 2. 처리내용 : 
	 * 
	 * @param objName
	 * @param plainText
	 * @return
	 */
	public static String encryptEx(String objName, String plainText) {		
	
		String encData = "";
		String crypto = "";
		
		try {			

			crypto = plainText;

		} catch (Exception e) {
			
		}
		
		try {
			encData = plainText;			
		} catch (Exception e) {
			
		}
		
		return encData;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : decrtyp 처리
	 * 2. 처리내용 : 
	 * @param objName
	 * @param cipherText
	 * @return
	 */
	public static String decrtypEx(String objName, String cipherText) {		
	
		String encData = "";
		String crypto = "";
		
		try {			
			
			crypto = cipherText;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}

		try {
			encData = cipherText;
		} catch (Exception e) {
			
		}
		
		return encData;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : SHA-512 단방향 암호화
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.frwk.fol.util.SDBCryptoUtil.java
	 * @Method : encryptSha512
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 19. 
	 * @param plainText
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptSha512(String plainText) {
		String encData = "";
	    try {
	    	MessageDigest md = MessageDigest.getInstance("SHA-512");
	    	md.update(plainText.getBytes(Charset.forName("UTF-8")));
	    	byte[] msgb = md.digest();
	    	encData = new String(Base64.encodeBase64(msgb));
		} catch (Exception e) {
//			
		}
	    return encData;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : SHA-256 암호화
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.frwk.fol.util.SDBCryptoUtil.java
	 * @Method : encryptSha256
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 14. 
	 * @param plainText
	 * @return
	 */
	public static String encryptSha256(String plainText) {
		String encData = "";
	    try {
	    	MessageDigest md = MessageDigest.getInstance("SHA-256");
	    	md.update(plainText.getBytes(Charset.forName("UTF-8")));
	    	byte[] msgb = md.digest();
	    	encData = new String(Base64.encodeBase64(msgb));
		} catch (Exception e) {
			System.out.println(">>>>> encryptSha256 ::::: 로그인 암호화 에러 <<<<<");
//			e.printStackTrace();
		}
	    return encData;
	}
}
