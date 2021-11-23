package com.eunwoosoft.comm.pki;


import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.util.encoders.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.comm.pki.*;
import com.eunwoosoft.comm.util.LicenseUtil;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;


/**
 * 
 * <pre>
 * 1. 개요 :  은우소프트 라이센스 발급 PKI
 * 2. 처리내용 : 
 * </pre>
 * @date : 2021. 09. 01
 * @author : 은우소프트 손연우
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2021. 09. 01.		은우소프트 손연우				최초 작성 
 *	-----------------------------------------------------------------------
 */

public class ewLicensePki {
	private static final Logger LOG = LoggerFactory.getLogger(ewLicensePki.class);
	private Key publicKey;
	private Key privateKey;
	
	String privateKeySpec =  "12932436154824878462139106078042546067739956273054381073"
			+"06846608816275620934341725607564783823695128640426483264"
			+"90104582772378490412787229281925620032032403146098808369"
			+"79259884927845594288251731195408682529231278169561314865"
			+"27229342168913952732696814412411829897514778600886880837"
			+"64074863895802068188409970733/47004078491223065591673940"
			+"94617902060417256792714884067938582208829162959954837710"
			+"60197951549207592111554676463218379108234746119235331461"
			+"84528865666158220508976070870860023665918744503905504555"
			+"56548917501796845384212632573167995073130313618965900163"
			+"4410410883459208102675762321410987922158381252100921931473";

	

	/**
	 * 개인키로 복호화 한다.
	 * @param txt
	 * @return
	 * @throws Exception
	 */
	public String decode(String txt) throws Exception {
		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE, privateKey);
		//return new String(c.doFinal(Base64.decode(txt.getBytes("UTF-8"))));
		return new String(c.doFinal(Base64.decode(txt.getBytes())));
	}


	/**
	 * 공개키로 암호화 한다.
	 * @param txt
	 * @return
	 * @throws Exception
	 */
	public String encode(String txt) throws Exception {
		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE, publicKey);
		//return new String(Base64.encode(c.doFinal(txt.getBytes("UTF-8"))));
		return new String(Base64.encode(c.doFinal(txt.getBytes())));
	}


	/**
	 * 공개키, 개인키 한쌍을 생성한다.
	 * @throws Exception
	 */
	public void generatorKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}

	

	/**
	 * 공개키 문자열을 가져온다.
	 * @return
	 * @throws Exception
	 */

	public String getPublicKeySpecStr() throws Exception {
		RSAPublicKeySpec publicKeySpec	= KeyFactory.getInstance("RSA").getKeySpec(publicKey, RSAPublicKeySpec.class);
		return publicKeySpec.getModulus() + "/" + publicKeySpec.getPublicExponent();
	}


	/**
	 * 공개키 문자열로 공개키를 생성한다.
	 * @param specStr
	 * @throws Exception
	 */
	public void setPublicKeySpecStr(String specStr) throws Exception {
		
		//Decoder decoder = Base64.decode(specStr);
		
		
		String[] specArr	= specStr.split("/");		
		setPublicKeySpecStr(specArr[0], specArr[1]);
	}

	/**
	 * 공개키 문자열로 공개키를 생성한다.
	 * @param modulus
	 * @param exponent
	 * @throws Exception
	 */
	public void setPublicKeySpecStr(String modulus, String exponent) throws Exception {
		RSAPublicKeySpec publicKeySpec	= new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(exponent));
		publicKey	= KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
	}

	

	/**
	 * 개인키 문자열을 가져온다.
	 * @return
	 * @throws Exception
	 */
	public String getPrivatekeySpecStr() throws Exception {
		RSAPrivateKeySpec privateKeySpec	= KeyFactory.getInstance("RSA").getKeySpec(privateKey, RSAPrivateKeySpec.class);
		return privateKeySpec.getModulus() + "/" + privateKeySpec.getPrivateExponent();
	}

	/**
	 * 개인키 문자열로 개인키를 생성한다.
	 * @param specStr
	 * @throws Exception
	 */
	public void setPrivateKeySpecStr(String specStr) throws Exception {
		String[] specArr	= specStr.split("/");
		setPrivateKeySpecStr(specArr[0], specArr[1]);
	}

	/**
	 * 개인키 문자열로 개인키를 생성한다.
	 * @param modulus
	 * @param exponent
	 * @throws Exception
	 */
	public void setPrivateKeySpecStr(String modulus, String exponent) throws Exception {
		RSAPrivateKeySpec privateKeySpec	= new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(exponent));
		privateKey	= KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);
	}
	
	
//	public String encodeTxtRead(String encodeTxt) throws Exception {
//		
//		
//		encodeTxt = encodeTxt.replaceAll("\n", "");
//		String encodeTxtSplit[] = encodeTxt.split("<-- license msg -->");
//		System.err.println("encodeTxtSplit[1] :::: " + encodeTxtSplit[1].toString());
//		return encodeTxtSplit[1].toString();
//		
////		// 서버에서 새 자원 생성해서 개인키로 복호화
////		ewLicensePki serverRSARe	= new ewLicensePki();
////		serverRSARe.setPrivateKeySpecStr(privateKeySpec);
////		String decodeTxtRe	= serverRSARe.decode(encodeTxtSplit[1].toString());
////
//////		System.out.println("privateKeySpec : " + privateKeySpec);
////		System.err.println("decodeTxtRe : " + decodeTxtRe);
//	}
	


}
