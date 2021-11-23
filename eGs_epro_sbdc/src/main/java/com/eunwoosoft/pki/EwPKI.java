package com.eunwoosoft.pki;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateException;
import java.security.cert.PKIXCertPathValidatorResult;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.springframework.context.support.MessageSourceAccessor;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;

import tradesign.crypto.cert.validator.ExtendedPKIXParameters;
import tradesign.crypto.cert.validator.PKIXCertPath;
import tradesign.crypto.provider.JeTS;
import tradesign.pki.asn1.ASN1Exception;
import tradesign.pki.pkix.SignedData;
import tradesign.pki.pkix.X509CRL;
import tradesign.pki.pkix.X509Certificate;
import tradesign.pki.util.JetsUtil;
import tradesign.pki.x509.X509ExtensionException;


public class EwPKI {
	
	//private static MessageSourceAccessor messageSourceAccessor ;
	private static EwPKI s_instance = null;
	private MessageDigest md = null;

	
	public static final int EV_OK = 1;
	public static final int EV_CANCEL = 0;
	public static final int EV_ERROR = -1;
	public static final int EV_CHECK = 2;
	public static final int EV_SN_CERT = 2;
	public static final int EV_KM_CERT = 1;
	public static final int EV_CERT_Version = 1;
	public static final int EV_CERT_Serial = 2;
	public static final int EV_CERT_SignatureAlgId = 3;
	public static final int EV_CERT_Issuer = 4;
	public static final int EV_CERT_Subject = 5;
	public static final int EV_CERT_SubjectPublicKeyAlgId = 6;
	public static final int EV_CERT_From = 7;
	public static final int EV_CERT_To = 8;
	public static final int EV_CERT_publicKey = 9;
	public static final int EV_CERT_Signature = 10;
	public static final int EV_CERT_KeyUsage = 11;
	public static final int EV_CERT_AuthorityKeyId = 12;
	public static final int EV_CERT_SubjectKeyId = 13;
	public static final int EV_CERT_ExtKeyUsage = 14;
	public static final int EV_CERT_SubjectAltName = 15;
	public static final int EV_CERT_BasicConstraint = 16;
	public static final int EV_CERT_Policy = 17;
	public static final int EV_CERT_DistributionPoints = 18;
	public static final int EV_CERT_AuthorityInfoAccess = 19;
	public static final int EV_CA_ALL = 0;
	public static final int EV_CA_KICA = 1;
	public static final int EV_CA_KOSCOM = 2;
	public static final int EV_CA_KFTC = 3;
	public static final int EV_CA_KECA = 4;
	public static final int EV_CA_KNCA = 5;
	public static final int EV_CA_TRADESIGN = 6;
	public static final String EV_CA_STR_KICA = "정보인증";
	public static final String EV_CA_STR_KOSCOM = "증권전산";
	public static final String EV_CA_STR_KFTC = "금융결제원";
	public static final String EV_CA_STR_KECA = "한국전자인증";
	public static final String EV_CA_STR_KNCA = "한국전산원";
	public static final String EV_CA_STR_TRADESIGN = "한국무역정보통신";
	private static String rootFolder = "";
	private static final String fld_KICA = "/KICA/USER";
	private static final String fld_KOSCOM = "/SIGNKOREA/USER";
	private static final String fld_KFTC = "/YESSIGN/USER";
	private static final String fld_KECA = "/CROSSCERT/USER";
	private static final String fld_KNCA = "/NCASign/USER";
	private static final String fld_TRADESIGN = "/TRADESIGN/USER";
	public static final int EV_STORAGE_SMARTCARD = 1;
	public static final int EV_STORAGE_FDD = 2;
	public static final int EV_STORAGE_HDD = 3;
	public static final int EV_STORAGE_LDAP = 4;
	public static final int EV_DN = 0;
	public static final int EV_PEMCERTIFICATE = 1;
	
	private String m_nErrorCode;
	private String m_nErrorMsg;
	private String m_nArg1;
	private String m_nArg2;
	private String m_Time;
	
		
	//public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
	//	this.messageSourceAccessor = messageSourceAccessor;
	//}
	//evPKI pki = evPKI.getInstance() => EwPKI pki = EwPKI.getInstance()
	public static EwPKI getInstance() {
		
		try{
			
		//	if (s_instance == null)
				//JeTS.installProvider(messageSourceAccessor.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
				
				JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
			//FwkMessageUtil.getMessage("EW.EPRO.ENTRPS_REGIST_NO")
				/*
				// HASH 알고리즘
				String[] algs = {
					"MD5",
					"SHA-1",
					"HAS160"
				};
				*/		
				//md = MessageDigest.getInstance("SHA-1", "JeTS");
				s_instance = new EwPKI();
			
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
		return s_instance;
	}

	private EwPKI() {	
		try{
			
			m_nErrorCode = "";
			m_nErrorMsg = "";
			m_nArg1 = "";
			m_nArg2 = "";
			m_Time = "";
			
			
			

		}catch(Exception e){
			e.printStackTrace();
		
		}
	}
	
	//해쉬값 생성
	public synchronized int EVHash(StringBuffer stringbuffer, String s) {
		stringbuffer.delete(0, stringbuffer.length());
		clearMember();
		try{		
			// 원문 데이타	
			md = MessageDigest.getInstance("SHA-1", "JeTS");
			byte[] result = md.digest(s.getBytes());
			
			String HashStr = new String(JetsUtil.encodeBase64(result));
			
			stringbuffer.append(HashStr);	
			
			return 1;
		}catch(Exception e){
			m_nErrorMsg = e.getMessage();
			e.printStackTrace();
			return -1;
		}
	}
	
	//해쉬값 생성
	public synchronized String EVHash(String s, String charset) {
		clearMember();		
		try{		
			// 원문 데이타	
			String data = new String(s.getBytes(charset));			
			md = MessageDigest.getInstance("SHA-1", "JeTS");
			byte[] result = md.digest(data.getBytes());
			
			String HashStr = new String(JetsUtil.encodeBase64(result));
						
			return HashStr;
		}catch(Exception e){
			m_nErrorMsg = e.getMessage();
			e.printStackTrace();
			return "";
		}
	}
		
	//해쉬값 생성
	public synchronized int EVHash_File(StringBuffer stringbuffer, String s) {
		stringbuffer.delete(0, stringbuffer.length());
		clearMember();
			try {    		    		
	    		byte[] result = null;    	
	    		File file = new File(s);				
			    FileInputStream fis = new FileInputStream(file);  
	
	        int nFileSize = (int)file.length();
	        byte[] buf = new byte [nFileSize];
	        fis.read(buf);
	        md = MessageDigest.getInstance("SHA-1", "JeTS");   
	        result = md.digest(buf);
	        String temps = new String(JetsUtil.encodeBase64(result));
        	
        	stringbuffer.append(temps);	
        	        	                
          return 1;
          
    		} catch (Exception e){
    			m_nErrorMsg = e.getMessage();
    			e.printStackTrace();
    			return -1;
    		} 
	}
			
	//서명검증
	public synchronized int EVVerifySignedData(StringBuffer stringbuffer,	String s) {
		
		stringbuffer.delete(0, stringbuffer.length());
		clearMember();
		try{		
			
			//String Strsigned_msg = new String(s.getBytes("ISO-8859-1"));
			
			byte[] signed_msg = JetsUtil.decodeBase64(s.getBytes());
			
			SignedData sd = new SignedData(signed_msg);			
			
			byte[] veryfi_msg = sd.getContent();
			
			String Strveryfi_msg = new String(veryfi_msg);
			
			X509Certificate[] certs = sd.verify();
			
			String[] CertDn  = new String[certs.length];
			
			for (int i = 0; i < certs.length; i++)
			{
				CertDn[i] = certs[i].getSubjectDNStr();				
			}
			
			// 서명 시각
			String SignTime = "서명시각 : " + sd.getSigningTime().toString();
			
			stringbuffer.append(Strveryfi_msg);
			
			return 1;
		}catch(Exception e){
			m_nErrorMsg = e.getMessage();
			e.printStackTrace();
			return -1;
		}
	}
	
	//서명검증
	public synchronized int EVVerifySignedData(StringBuffer stringbuffer,	StringBuffer stringbuffer1, String s) {
		
		stringbuffer.delete(0, stringbuffer.length());
		
		stringbuffer1.delete(0, stringbuffer1.length());
		
		clearMember();
					
		try{		
			
			//String Strsigned_msg = new String(s.getBytes("ISO-8859-1"));
						
	
			byte[] signed_msg = JetsUtil.decodeBase64(s.getBytes());
			
			SignedData sd = new SignedData(signed_msg);
			
			byte[] veryfi_msg = sd.getContent();
			
			String Strveryfi_msg = new String(veryfi_msg);
			
			X509Certificate[] certs = sd.verify();
			
			String CertDn[]  = new String[certs.length];
			
			for (int i = 0; i < certs.length; i++)
			{
				CertDn[i] = certs[i].getSubjectDNStr();
				stringbuffer1.append(CertDn[i]);
			}
		
			// 서명 시각
			String SignTime = "서명시각 : " + sd.getSigningTime().toString();
		
			stringbuffer.append(Strveryfi_msg);
			
			return 1;
		}catch(Exception e){
			m_nErrorMsg = e.getMessage();
			e.printStackTrace();
			return -1;
		}
	}
	
	//서명
	//stringbuffer 리턴값, s 해쉬값, s1 인증서 DN값,s2 인증서 비밀번호
	public synchronized int EVSignData(StringBuffer stringbuffer, String s,	String s1, String s2) {
		stringbuffer.delete(0, stringbuffer.length());
		clearMember();
		
		try{		
			
			
			
			// 서명할 원문 데이타
			String plaintext = s;								
			// 서명 암호화
			SignedData sd = new SignedData(plaintext.getBytes(), true);

			//Configuration conf = new Configuration();	
				
			System.err.println("EW.SERV.SIGN.CERT.PATH :::: " + FwkMessageUtil.getMessage("EW.SERV.SIGN.CERT.PATH"));
			
			String signCert = FwkMessageUtil.getMessage("EW.SERV.SIGN.CERT.PATH"); 
					//messageSourceAccessor.getMessage("ServerSignCertPath");
			//FwkMessageUtil.getMessage("EW.EPRO.ENTRPS_REGIST_NO")
			String signKey = FwkMessageUtil.getMessage("EW.SERV.SIGN.KEY.PATH");
			//messageSourceAccessor.getMessage("ServerSignKeyPath");
			String signPass = FwkMessageUtil.getMessage("EW.SERV.SIGN.PASSWORD"); 
			//messageSourceAccessor.getMessage("ServerSignPassword");
			
			System.err.println("signCert :::" + signCert);
			System.err.println("signKey :::" + signKey);
			System.err.println("signPass :::" + signPass);
			

			//sd.setsignCert(JeTS.getServerSignCert(0), JeTS.getServerSignPriKey(0), new String(JeTS.getServerSignKeyPassword(0)));
			sd.setsignCert(signCert, signKey, signPass);
			
			byte[] signed_msg = sd.sign();			
			
			X509Certificate[] certs = sd.verify();
			
			String CertDn[]  = new String[certs.length];
			
			for (int k = 0; k < certs.length; k++)
			{
				CertDn[k] = certs[k].getSubjectDNStr();
			}
			
			String Strsigned_msg = new String(JetsUtil.encodeBase64(signed_msg));
		
			stringbuffer.append(Strsigned_msg);
			
			return 1;
		}catch(Exception e){
			m_nErrorMsg = e.getMessage();
			System.err.println(e.getMessage());
			return -1;
		}
	}	
	public synchronized int getSignValue(StringBuffer stringbuffer, String s,	String s1, String s2) {
		
		return EVSignData(stringbuffer, s,	s1, s2);
	}
	
	private void clearMember() {
		m_nErrorCode = "";
		m_nErrorMsg = "";
		m_nArg1 = "";
		m_nArg2 = "";
		m_Time = "";
	}
	
	public String getRevokedTime() {
		return m_Time;
	}

	public String getErrorCode() {
		return m_nErrorCode;
	}

	public String getErrorMsg() {
		return m_nErrorMsg;
	}

	public String getErrorMessage() {
		return m_nErrorMsg;
	}

	public String returnArg(int i) {
		switch (i) {
		case 1: // '\001'
			return m_nArg1;

		case 2: // '\002'
			return m_nArg2;
		}
		return "";
	}
	
	public synchronized int EVCertificateValidation(String s, int i) {
		clearMember();
		try{	
			
			//Configuration conf = new Configuration();	
			//File inputFile = new File(conf.get("ServerSignCertPath"));
			//File inputFile = new File(messageSourceAccessor.getMessage("ServerSignCertPath"));
			File inputFile = new File(FwkMessageUtil.getMessage("EW.SERV.SIGN.CERT.PATH"));
			//FwkMessageUtil.getMessage("EW.SERV.SIGN.CERT.PATH");
			
			FileInputStream der = new FileInputStream(inputFile);
			//사용자 인증서
			X509Certificate cert = new X509Certificate(der);
			der.close();
//			FileInputStream fis = new FileInputStream(conf.get("ServerSignCertPath"));
//			X509Certificate cert = new X509Certificate(fis);
//			fis.close();
			boolean isCrl = true;
/////////////////////////////////인증서 폐기 체크////////////////////////////////////////////////////////			
			X509CRL crl = new X509CRL(cert.getCrlDp(), isCrl);
			
			if (crl.isRevoked(cert.getSerialNumber()))
			{
				System.out.println("폐지된 인증서");
				System.out.println("폐지일:" + crl.getRevokedDate());
				System.out.println("폐지사유:" + crl.getRevokedReason());
				
				//ldap에서 폐지인증서 검색
				X509Certificate[] certs = crl.getCert(cert.getSubjectDNStr());
				System.out.println("폐지인증서 목록");
				for (int k = 0 ; k <certs.length ; k++){
					System.out.println("[ " + k + "th Certificate ]");
					System.out.println(certs[0]);
					System.out.println("");
				}
								
				return -1;
			}
			else
			{
				System.out.println("인증서 폐지 안 됨");
			}
			/////////////////////////////////////////////////////////////////////////////////////////
			
			//==============================================인증서 검증=======================================
			PKIXCertPath cp = new PKIXCertPath(cert, "PkiPath");
			ExtendedPKIXParameters cpp = new ExtendedPKIXParameters(JeTS.getTAnchorSet());
			CertPathValidator cpvi = CertPathValidator.getInstance("PKIX","JeTS");
			PKIXCertPathValidatorResult cpvr = (PKIXCertPathValidatorResult) cpvi.validate(cp, cpp);
			// 검증 결과 VerifyStr 에 할당
			String VerifyStr = cpvr.toString();			
			System.out.println("검증결과 : "+VerifyStr);
			
			Date notAfter = cert.getNotAfter();
			if (notAfter != null)
			{
				System.out.println("유효기간 종료시각: " + notAfter);
				
				SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(simf.format(notAfter));
				Date today = new Date();
				System.out.println(simf.format(today));
				if(today.after(notAfter)){
					System.out.println("유효기간 종료시각이 지났습니다.");
					return -1;
				}
				System.out.println("유효기간 종료시각 정상.");
			}					
			
			
			return 1;
		}catch(Exception e){
			m_nErrorMsg = e.getMessage();
			e.printStackTrace();
			return -1;
		}
	}	

	public String getPemKmCert(){
		String serverDn = "";
		try {
			serverDn = JeTS.getPemKmCert(0);
		} catch (X509ExtensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ASN1Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverDn;
	}
	
	public byte[] getServerkmCert(){
		byte[] kmCert = null;
		try {
			kmCert = JeTS.getServerkmCert(0);
		} catch (X509ExtensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ASN1Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kmCert;
	}
	
	public byte[] getServerkmPriKey(){
		byte[] kmPriKey = null;
		try {
			kmPriKey = JeTS.getServerkmPriKey(0);
		} catch (X509ExtensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ASN1Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kmPriKey;
	}
	
	public String getServerKmKeyPassword(){
		String kmKeyPassword = "";
		try {
			kmKeyPassword = JeTS.getServerKmKeyPassword(0);
		} catch (X509ExtensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ASN1Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kmKeyPassword;
	}
}
