package com.eunwoosoft.pki;

/**************************************************************

Copyright (c) 2016 KTNET

http://www.ktnet.co.kr

Korea Paperless Trade Center(Pangyo TechnoVally), 338, Pangyo-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, South Korea 

All rights reserved.

프로그램에 대한 저작권을 포함한 지적재산권은 KTNET에 있으며,
KTNET이 명시적으로 허용하지 않은 사용, 복사, 변경, 제3자에의 공개,
배포는 엄격히 금지되며, KTNET의 지적재산권 침해에 해당됩니다.

You are strictly prohibited to copy, disclose, distribute,
modify, or use this program in part or as a whole without
the prior written consent of KTNET.

KTNET owns the intellectual property rights in and to this program.

***************************************************************/
//package tradesign.sample.cipher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import tradesign.crypto.provider.JeTS;
import tradesign.crypto.provider.key.RawSecretKey;
import tradesign.pki.asn1.ASN1Exception;
import tradesign.pki.pkix.KtnetPBKDF;
import tradesign.pki.util.JetsUtil;
import tradesign.pki.x509.X509ExtensionException;


public class SEED_b64{
	
	
//	static String[] algorithm = {"SEED/CBC/PKCS5Padding", "SEED/CBC/NoPadding"};
	static String[] algorithm = {"SEED/CBC/PKCS5Padding"};

	public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException,
	InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, IllegalStateException, 
	InvalidKeyException, NoSuchPaddingException, X509ExtensionException, CertificateException, IOException, ASN1Exception, InvalidAlgorithmParameterException{
	
	String strFile = new String(Files.readAllBytes(Paths.get("D:/abc.pdf")));
		
		
	System.out.println(strFile);
	
	//JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
	JeTS.installProvider("D:/eGovFrameDev-3.6.0-64bit/workspace/eCdocu/src/main/resources/config/properties/tradesign3280.properties");
		
  	byte[] ciphertext = null;
  	byte[] decryptedtext = null;
  	
  	for (int i = 0 ; i < algorithm.length; i++){
  		System.out.println("Testing " + algorithm[i]);
  		
  		 
	    	//byte[] plainData = new byte[32];
  		String origilanString = strFile;  
  		
  		byte[] plainData = origilanString.getBytes()  ; // 원문 입력
	    	System.out.println("입력된원문 : " + origilanString );
	    	
			Cipher cipher = Cipher.getInstance(algorithm[i], "JeTS");
			KeyGenerator  kGen = KeyGenerator.getInstance("SEED", "JeTS");
			
	    	SecureRandom randKey = new SecureRandom();
			kGen.init(randKey);
			
			
		 	//SecretKey secretKey = kGen.generateKey();//키는 random으로 생성
			
			String strKey = "ecDocuEbidSys_18";
			
			byte[] keyData = strKey.getBytes();
		 	
		 	SecretKey secretKey = new SecretKeySpec(keyData,"SEED");
		 	
		 	String secretKeyB64 = JetsUtil.toBase64String(secretKey.getEncoded());
		 	
		 	System.out.println("secretKeyB64 : " + secretKeyB64 ); 
		 	
		 	//초기화 : 키, 초기화 벡터 사용
			SecureRandom rand = new SecureRandom();
			byte[] ivData = new byte[cipher.getBlockSize()];
			rand.nextBytes(ivData);
			IvParameterSpec iv = new IvParameterSpec(ivData);//decrypt 하기 위해서는 IV도 저장해야함
		 	String ivDataB64 = JetsUtil.toBase64String(ivData);
		 	System.out.println("ivDataB64 : " + ivDataB64 ); 
		 	
			//System.out.println("Initial Vector 생성 완료");
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			ciphertext = cipher.doFinal(plainData);
		 	String ciphertextB64 = JetsUtil.toBase64String(ciphertext);
		 	System.out.println("B64암호문 : "+ ciphertextB64 );  // DB 저장
		 	
		 	System.out.println("==============================================");
		 	
		 	
			String strKey1 = "ecDocuEbidSys_18";
			
			byte[] keyData1 = strKey1.getBytes();
		 	
		 	SecretKey secretKey1 = new SecretKeySpec(keyData1,"SEED");
		 	
		 	String secretKeyB641 = JetsUtil.toBase64String(secretKey1.getEncoded());
		 	
		 	
		 	// 복호화 시작
		 	secretKey1 = new RawSecretKey(JetsUtil.base64ToBytes(secretKeyB641), "SEED");  // 비밀키
		 	//secretKey = "ecDocuEbidSys_2018";
		 	iv = new IvParameterSpec(JetsUtil.base64ToBytes(ivDataB64));        // iv 값
		 	
		 	
		 	byte[] cipherbin = JetsUtil.base64ToBytes(ciphertextB64);   // 암호화문입력
		 	cipher.init(Cipher.DECRYPT_MODE, secretKey1, iv);    
			decryptedtext = cipher.doFinal(cipherbin);  
	   		
	   		//System.out.println("복호화 결과 : "+ new String(decryptedtext, "euc-kr") ); 
			System.out.println("복호화 결과 : "+ new String(decryptedtext, "utf-8") ); 
	   		
	   		System.out.println("==============================================");
	   			
//	   		System.out.print("Original Text  : ");
//	   		System.out.println(JetsUtil.toString(plainData));
//	   		System.out.print("Cipher Text    : ");
//	   		System.out.println(JetsUtil.toString(ciphertext));
//	   		System.out.print("Decrypted Text : ");
//	   		System.out.println(JetsUtil.toString(decryptedtext));
//	   		System.out.println("");
	   		
	   		
//		 	// 위미건설 샘플 데이터 테스트 
//	   		String keypwd = "nGjqYtxgz7lne4kWoG+gxw==" ;    // 일종의 salt 값
//	   		//String symkey = "2PdhM3MKuPoULVH9jzUn3w==" ;    // 내부적으로 사용된 값
//	   		String b64EncryptedData = "5O6aCVVBnItI+hvJwj7IHw==" ; 
//	   		
//	   		//1. 입력된값으로 키생성하는 경우 /////////////////////
//			KtnetPBKDF pbkdf = new KtnetPBKDF();
//			pbkdf.setEncAlgorithm("SEED");//or 3DES
//			String passStr = keypwd ;  //"password";
//			pbkdf.generateKey(passStr); 
//			byte[] key_1 = pbkdf.getKeyBytes();
//	   		////////////////////////////////////////
//		 	SecretKey secretKey_1 = new RawSecretKey(key_1, "SEED");  // 비밀키
//	   		IvParameterSpec iv_1 = new IvParameterSpec(key_1);        // iv 값		 	
//	   		System.out.println(" 실제 암호화 키값 : " + JetsUtil.toBase64String(key_1));
//	   		System.out.println(" 실제 암호화 IV값 : " + JetsUtil.toBase64String(key_1));	 		 	
//		 	
//		 	
//	   		// 2. 설정된 비밀키 바로 입력하는 경우 
//	   		//SecretKey secretKey_1 = new RawSecretKey(JetsUtil.base64ToBytes(symkey), "SEED");  // 비밀키
//	   		//IvParameterSpec iv_1 = new IvParameterSpec(JetsUtil.base64ToBytes(symkey));        // iv 값
//	   		//System.out.println(" 실제 암호화 키값 : " + symkey );
//	   		//System.out.println(" 실제 암호화 IV값 : " + symkey );	   		
//	   		
//	   		//////
//		 	byte[] cipherbin_1 = JetsUtil.base64ToBytes(b64EncryptedData);   // 암호화문입력
//		 	cipher.init(Cipher.DECRYPT_MODE, secretKey_1, iv_1);    
//		 	byte[] decryptedtext_1 = cipher.doFinal(cipherbin_1);  
//	   		
//	   		System.out.println("=========== 우미건설 샘플 데이터 테스트 =========");
//	   		System.out.println(" 암호화된 문자열(b64) : " + b64EncryptedData );
//	   		System.out.println(" 복호화 결과 : "+ new String(decryptedtext_1, "euc-kr") ); 
//	   		System.out.println("==============================================");	   		
  		}
	}
}
