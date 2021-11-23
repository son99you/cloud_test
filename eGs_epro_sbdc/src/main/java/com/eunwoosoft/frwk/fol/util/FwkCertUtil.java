/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.util;


import com.eunwoosoft.pki.EwPKI;
import com.yettiesoft.vestsign.external.VidVerifier;

import signgate.crypto.util.Base64Util;
import signgate.crypto.util.CipherUtil;
import signgate.crypto.util.FileUtil;

/**
 * <pre>
 * com.eunwoosoft.frwk.fol.util 
 *    |_ FwkCertUtil.java
 * 
 * </pre>
 * @date : 2015. 3. 12. 오후 12:07:08
 * @version : 1.0
 * @author : 
 */
public class FwkCertUtil {
	
	private FwkCertUtil() {
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getServerCert
	 * @date : 2015. 3. 12.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 12.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getServerCert() {		
//		String userHome = System.getProperty("user.home");
//		String servercertpath = userHome + "/app_config/kica/kmCert.der";
//		byte[] severcert_byte = FileUtil.readBytesFromFileName(servercertpath);
//		CertUtil cu = new CertUtil(severcert_byte);
//		@SuppressWarnings("static-access")
//		String strservercert = cu.derToPem(severcert_byte);
		String strservercert = "";
		try {
			EwPKI.getInstance();
//			strservercert = EwPKI.getPemKmCert();
			VidVerifier verifier = new VidVerifier();
			strservercert = verifier.writeServerCertPem(); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strservercert;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자인증서확인 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isUserCertValid
	 * @date : 2015. 3. 13.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param usercert
	 * @return
	 * @throws Exception
	 */
	public static boolean isUserCertValid(String usercert) throws Exception {		
		boolean valid = false;		
//		String userHome = System.getProperty("user.home");		
//		String CRLCacheDirectory = userHome + "/app_config/kica/crl";
//		CertUtil cu = new CertUtil(usercert.getBytes());
//		valid = cu.isValid(true , CRLCacheDirectory);
		EwPKI.getInstance();
		
		
		return valid;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 신원 확인 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : isValidUser
	 * @date : 2015. 3. 13.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param enckey
	 * @param encrandom
	 * @param encssn
	 * @param usercert
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidUser(String enckey, String encrandom, String encssn, String usercert) throws Exception {
		boolean valid = false;
		
//		String userHome = System.getProperty("user.home");
//		String certpath = userHome + "/app_config/kica/kmPri.key";
//		//String passwd = "a123456A";
//		String passwd = "koica1991$!";
//		String CRLCacheDirectory = userHome + "/app_config/kica/crl";
//		
//		byte[] keyBytes = FileUtil.readBytesFromFileName(certpath);
//		
//		
//		CipherUtil cipher = new CipherUtil();
//		
//		byte[] decKeyBytes = cipher.decryptRSA( keyBytes, passwd, enckey );
//		
//		if(decKeyBytes==null){
//			System.out.println("대칭키 복호화에 실패하였습니다.");
//		}
//		
//		
//		byte[] decRandomBytes = null;
//		byte[] userssn = null;
//		
//		cipher.decryptInit(decKeyBytes);		
//		decRandomBytes = cipher.decryptUpdate(Base64Util.decode(encrandom));
//		userssn = cipher.decryptUpdate(Base64Util.decode(encssn));
//		cipher.decryptFinal();
//		
//		CertUtil cu = new CertUtil(usercert.getBytes());
//		valid = cu.isValid(true , CRLCacheDirectory);
//		
//		if (!valid) {
//			System.out.println("인증서 유효성 검증 실패 : "+  cu.getErrorMsg());			
//		}
//		
//		valid = cu.isValidUser(new String(userssn), new String(decRandomBytes));
			
		return valid;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 복호화 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : decrypt
	 * @date : 2015. 3. 13.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param enckey
	 * @param encdata
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String enckey, String encdata) throws Exception {
	
		
		String userHome = System.getProperty("user.home");
		String certpath = userHome + "/app_config/kica/kmPri.key";
		//String passwd = "a123456A";	
		String passwd = "koica1991$!";
		byte[] keyBytes = FileUtil.readBytesFromFileName(certpath);		
		
		CipherUtil cipher = new CipherUtil();		
		byte[] decKeyBytes = cipher.decryptRSA( keyBytes, passwd, enckey );		
		if(decKeyBytes==null){
			System.out.println("대칭키 복호화에 실패하였습니다.");
		}		
		byte[] decdata = null;		
		cipher.decryptInit(decKeyBytes);		
		decdata = cipher.decryptUpdate(Base64Util.decode(encdata));
		cipher.decryptFinal();		
		return new String(decdata);		
	}
}
