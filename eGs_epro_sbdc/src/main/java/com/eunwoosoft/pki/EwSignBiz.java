package com.eunwoosoft.pki;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import tradesign.crypto.provider.key.RawSecretKey;
import tradesign.pki.asn1.ASN1Exception;
import tradesign.pki.pkcs.PKCSException;
import tradesign.pki.pkix.EnvelopedData;
import tradesign.pki.pkix.KtnetPBKDF;
import tradesign.pki.util.Base64Exception;
import tradesign.pki.util.JetsUtil;

import org.apache.commons.logging.*;

//import org.springframework.context.support.MessageSourceAccessor;
//import com.eunwoosoft.base.Logger;
//import com.eunwoosoft.db.vo.ValueObject;
import com.eunwoosoft.pki.CertificateFailException;


/**
 * <pre>
 *  
 * <br>
 *  인증서 관련 업무의 처리를 위한 Object
 *  &lt;pre&gt;
 *  
 *  @author			:	모상세
 *  @version			:	1.0
 *  @since			:	JDK1.4.2
 *  @see				:
 * 
 */
public class EwSignBiz {
	//private MessageSourceAccessor messageSourceAccessor;
	protected final Log	logger	= LogFactory.getLog(getClass());
	
	
	/**
	 * GdtiSignBiz 생성자
	 */
	public EwSignBiz() {
	}

	/**
	 * DAO객체를 얻는다.
	 */
	protected void initDAO(String name) {
		System.err.println("[DtiSignBiz.initDAO(" + name + ")] Called");
	}
	/*public void setMessageSourceAccessor(
			MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}*/
	/**
	 * 검증( 서명와 인증서 ) 서명 검증한다. ( 인증서와 서명 값 모두 검증한다. ) Connection을 인자로 받는것은 검증작업이
	 * 하나의 트랜잭션으로 처리되어야 하기때문이다.
	 * 
	 * @param originHash
	 *            해쉬값
	 * @param signValue
	 *            서명값
	 * @return String 검증 메세지.
	 */
	public String verifySignedData(String originHash, String signValue) throws CertificateFailException, Exception {
		String msg = "";
		StringBuffer hashValue = new StringBuffer();
		
		EwPKI pki = EwPKI.getInstance();
		//EVCSP.evPKI pki = EVCSP.evPKI.getInstance();

		try {
			int code = pki.EVVerifySignedData(hashValue, signValue);
			
			if (code == EwPKI.EV_OK) {
				msg = pki.getErrorMsg();
				System.out.println("EwSignBiz.verifySignedData() --> 검증 성공 : " + msg);
			} else if (code == EwPKI.EV_CHECK) {
				msg = pki.getErrorMsg();
				System.out.println("EwSignBiz.verifySignedData() --> 검증 끝 : " + pki.getErrorCode() + ", " + msg);
			} else {
				msg = "EwSignBiz.verifySignedData() --> 검증 실패 : [" + pki.getErrorCode() + "] " + pki.getErrorMsg() +
				      " :: hashValue = " + hashValue.toString() + " :: signValue = " + signValue;
				throw new CertificateFailException(msg);
			}

			if (originHash.equals(hashValue.toString())) {
				msg = "EwSignBiz.verifySignedData() --> 검증 성공";
			} else {
				msg = "EwSignBiz.verifySignedData() --> 검증 오류 : [" + pki.getErrorCode() + "] " + pki.getErrorMsg();
				throw new CertificateFailException(msg);
			}

		} catch (CertificateFailException cfe) {
			logger.error( cfe.getMessage() );
			
			throw new CertificateFailException(cfe.getMessage());
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		} 
		
		return msg;
	}

	/**
	 * 서명값, 원본해쉬값, 파일이름을 받아서 해당 파일의 서명을 검증한다.
	 * 
	 * @param String -
	 *            sSignInfo : 파일의 서명 값
	 * @param String -
	 *            inDbHash : 파일의 기존 해쉬값
	 * @param String -
	 *            inOrgFilename : 파일 경로와 파일명
	 * @return
	 */
	public String signVerifyDataFile(String sSignInfo, String inDbHash, String inOrgFilename) throws CertificateFailException, Exception {
		EwPKI pki = EwPKI.getInstance();
		//EVCSP.evPKI pki = EVCSP.evPKI.getInstance();
		StringBuffer rtnVerifySignData = new StringBuffer("");
		StringBuffer curtDn = new StringBuffer();
		String msg = "";
		
		try {
			try {
				// 1.파일의 Hash값을 가지고 옴
				String orgFileHash = getHashFile(inOrgFilename);
				
				
				if ( sSignInfo == null || "".equalsIgnoreCase(sSignInfo) ){
					msg = "서명값이 존재하지 않습니다.";
				}else{
					// 2.파일의 Hash값과 서명값을 Hash값으로 바꾸어 비교함
					
					int nErrorCode = pki.EVVerifySignedData(rtnVerifySignData, curtDn, sSignInfo);
	
					if(nErrorCode == EwPKI.EV_ERROR ){
						msg = "서명값 검증에 실패 했습니다 ";
						throw new CertificateFailException( msg + "\n" + pki.getErrorMessage());
						
					}       				
					// 3.파일의 Hash값과 저장된 서명값이 다른 경우
					
					if (!orgFileHash.equals(rtnVerifySignData.toString())) {
						
						msg = "원본데이타가 변조되었습니다. : "+curtDn.toString();
						System.err.println(" 서명검증 실패 : Message: " + pki.getErrorMsg() + ",서명에서추출값:" + rtnVerifySignData.toString() + ",파일에서추출값:" + orgFileHash);
					} else {
						
						msg = "검증 성공";
						if (nErrorCode == EwPKI.EV_OK) {
							
							System.err.println("검증 성공");
						} else if (nErrorCode == EwPKI.EV_CHECK) {
							
							System.err.println("검증끝" + pki.getErrorCode() + pki.getRevokedTime());
							msg += " 서명 검증 주의 " + pki.getErrorCode() + pki.getRevokedTime() + ":" + pki.getErrorMsg() + ",인증서:" + curtDn.toString();
						}
					}
				}	
				
			} catch (Exception ex) {
				//msg = "원본이 존재하지 않습니다. ";
				System.err.println("[EwSignBiz.signVerifyDataFile()] Exception " + ex);
				throw new CertificateFailException(ex.getMessage());
			}
		} catch (Exception e) {
			System.err.println("[EwSignBiz.signVerifyDataFile()] Exception " + "검증 오류 " + pki.getErrorMsg() + ", code=" + pki.getErrorCode());
			throw new CertificateFailException(e.getMessage());
		} finally {
			System.err.println("[EwSignBiz.signVerifyDataFile()] End");
		}
		return msg;
	}

	/**
	 * 서명 검증한다 ( 인증서만 검증한다 )
	 * 
	 * @param originHash
	 *            해쉬값
	 * @param signValue
	 *            서명값
	 * @return String 검증 메세지.
	 */
	
	public String verifyCert(String certdn) throws CertificateFailException, Exception {
		
		EwPKI pki = EwPKI.getInstance();
		String msg = "";

		try {
			int code = pki.EVCertificateValidation(certdn, EwPKI.EV_SN_CERT);

			if (code == EwPKI.EV_OK) {
				System.err.println("[EwSignBiz.verifyCert] 검증 성공 : " + pki.getErrorMsg());
				msg = pki.getErrorMsg();
			} else {
				msg = pki.getErrorMsg();
				throw new CertificateFailException(msg);
			}
			
			System.err.println("[EwSignBiz.verifyCert] 검증 성공 : " + pki.getErrorMsg());

		} catch (CertificateFailException cfe) {
			System.err.println("[EwSignBiz.verifyCert] 검증 오류 " + pki.getErrorMsg() + ", code=" + pki.getErrorCode());
			throw new CertificateFailException(cfe.getMessage());
		} catch (Exception e) {
			System.err.println("[EwSignBiz.verifyCert()] Exception");
			System.err.println(e.getMessage());
			throw new Exception("|" + e.getMessage() + "|");
		} finally {
			System.err.println("[EwSignBiz.verifyCert()] End");
		}

		return msg;
	}
	

	/**
	 * signValue를 디비에 저장하기 위해 1024크기로 자른다.
	 * 
	 * @param signValue
	 *            서명값
	 * @return String[] - 1024로 자른 signValue
	 */
	public String[] getSignedValues(String signValue) throws Exception {
		String[] signDatas = new String[] { "", "", "", "", "", "", "", "", "", "" };
		int offset = 0;
		int size = 1024;
		int end = 0;
		int originLen = signValue.length();

		try {
			for (int i = 0; i < 10; i++, offset += size) {
				end = offset + size;
				if (end > originLen) {
					end = originLen;
					signDatas[i] = signValue.substring(offset, end);
					return signDatas;
				}
				signDatas[i] = signValue.substring(offset, end);
			}
		} catch (Exception e) {
			System.err.println("[GdtiSignBiz.getSignedValues] Exception" + e.getMessage());
		}
		return signDatas;
	}

	/**
	 * 서명한다.
	 * 
	 * @param signData
	 *            서명값
	 * @param hashValue
	 *            해쉬값
	 * @param certDN
	 *            인증서 DN값
	 * @param password
	 *            인증서 비밀번호
	 * @return String 검증 메세지.
	 */
	public String getSignValue(StringBuffer signValue, String hashValue, String certDN, String password) throws CertificateFailException, Exception {
		EwPKI pki = EwPKI.getInstance();
		String msg = "";

		try {
			
			System.err.println("getSignValue 1111111111111111");
			
			int code = pki.EVSignData(signValue, hashValue, certDN, password);

			System.err.println("getSignValue 222222222222222");
			if (EwPKI.EV_OK == code) {
				System.err.println("[GdtiSignBiz.getSignValue] 서명 성공 : " + pki.getErrorMsg());
				msg = pki.getErrorMsg();
			} else if (EwPKI.EV_ERROR == code) {
				System.out.println("code :: " + code + " ");
				System.err.println("[GdtiSignBiz.getSignValue] 서명 실패 " + pki.getErrorCode() + " : " + pki.getErrorMsg());
				msg = pki.getErrorMsg();
				throw new CertificateFailException(msg);
			}

		} catch (CertificateFailException cfe) {
			System.out.println("[GdtiSignBiz.getSignValue] 검증 오류 " + cfe.getMessage());
			throw new CertificateFailException(cfe.getMessage());
		} catch (Exception e) {
			System.err.println("[DtiSignBiz.getSignValue()] Exception");
			System.err.println(e.getMessage());
			throw new Exception("|" + e.getMessage() + "|");
		} finally {
			System.err.println("[DtiSignBiz.getSignValue()] End");
		}
		return msg;
	}

	/**
	 * 내부 사용자 비밀번호을 암호화한다. .
	 * 
	 * @param String -
	 *            orgpasswd 서명값
	 * @return String - 암호화 Password .
	 */
	public String getEncryptPasswd(String password) throws CertificateFailException, Exception {
		//EwPKI cipher = EwPKI.getInstance();
		StringBuffer msg = new StringBuffer();
		//StringBuffer key = new StringBuffer();

		
		return msg.toString();
	}

	/**
	 * 내부 사용자 비밀번호을 복호화한다. .
	 * 
	 * @param String -
	 *            orgpasswd 서명값
	 * @return String - 복호화 Password .
	 */
	public String getDecryptPasswd(String orgpassword) throws CertificateFailException, Exception {
		//EwPKI cipher = EwPKI.getInstance();
		StringBuffer msg = new StringBuffer();
		//StringBuffer key = new StringBuffer();
		return msg.toString();
	}

	/**
	 * 난수를 생성한다.
	 * 
	 * @param StringBuffer
	 *            난수
	 * @return String 생성 메세지.
	 */
	public String getRandomNumber(StringBuffer rendomnumber) throws CertificateFailException, Exception {
		//EVCSP.evCipher eCipr = EVCSP.evCipher.getInstance();
		String msg = "";
//		try {
//			int code = eCipr.EVGenerateRandomNumber(rendomnumber, 4);
//
//			if (EVCSP.evCipher.EV_OK == code) {
//				System.err.println("[GdtiSignBiz.getRandomNumber] 난수생성 성공 : " + eCipr.getErrorMsg());
//				msg = eCipr.getErrorMsg();
//			} else if (EVCSP.evCipher.EV_ERROR == code) {
//				System.err.println("[GdtiSignBiz.getRandomNumber] 난수생성 실패 " + eCipr.getErrorCode() + " : " + eCipr.getErrorMsg());
//				msg = eCipr.getErrorMsg();
//				throw new CertificateFailException(msg);
//			}
//		} catch (CertificateFailException cfe) {
//			System.out.println(this + " CertificateFailException 난수생성 실패 " + cfe.getMessage());
//			throw new CertificateFailException(cfe.getMessage());
//		} catch (Exception e) {
//			System.err.println(this + " Exception 난수생성 실패 " + e.getMessage());
//			throw new Exception("|" + e.getMessage() + "|");
//		} finally {
//			System.err.println(" End");
//		}
		return msg;
	}

	/**
	 * 거래선 비밀번호을 복호화한다. .
	 * 
	 * @param String -
	 *            orgpasswd 서명값
	 * @return String - 복호화 Password .
	 */
	public String getDecryptOutUserPasswd(String orgpassword, String key) throws CertificateFailException, Exception {
		//EVCSP.evCipher cipher = EVCSP.evCipher.getInstance();
		StringBuffer msg = new StringBuffer();
//		int code = 0;
//
//		try {
//			// 암호화 Password 생성
//			code = cipher.EVDecrypt(msg, orgpassword, key);
//			if (EVCSP.evCipher.EV_OK == code) {
//				System.err.println("[GdtiSignBiz.getEncryptPasswd] 복호화 Password 생성 성공 : ");
//			} else if (EVCSP.evCipher.EV_ERROR == code) {
//				System.err.println("[GdtiSignBiz.getEncryptPasswd] 복호화 Password 생성 실패 " + cipher.getErrorCode() + " : " + cipher.getErrorMsg());
//				throw new CertificateFailException(" 복호화 Password 생성 실패 " + cipher.getErrorCode() + " : " + cipher.getErrorMsg());
//			}
//		} catch (CertificateFailException cfe) {
//			System.out.println("[GdtiSignBiz.getEncryptPasswd] 비밀번호을 복호화 오류 " + cfe.getMessage());
//			throw new CertificateFailException(cfe.getMessage());
//		} catch (Exception e) {
//			System.err.println("[DtiSignBiz.getEncryptPasswd()] Exception");
//			System.err.println(e.getMessage());
//			throw new Exception("|" + e.getMessage() + "|");
//		} finally {
//			System.err.println("[DtiSignBiz.getEncryptPasswd()] End");
//		}
		return msg.toString();
	}

	/**
	 * 해쉬값을 얻는다.
	 * 
	 * @param hashValue
	 *            해쉬값을 리턴받을 변수
	 * @param originData
	 *            baseinvoice_tbl 하나의 레코드에 대한 문자열 조합값
	 * @return boolean 해쉬 생성 여부
	 */
	public boolean getHashValue(StringBuffer hashValue, String originData) {
		EwPKI hash = EwPKI.getInstance();
		if (EwPKI.EV_OK != hash.EVHash(hashValue, originData)) {
			System.err.println("[GdtiSignBiz.getHashValue] Hash Error " + hash.getErrorCode() + " : " + hash.getErrorMsg() + " : ");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 해쉬값을 얻기 위한 문자열 조합값 얻기.
	 * 
	 * @param vo
	 *            baseinvoice_tbl 하나의 레코드
	 * @return String 문자열 조합
	
	public String getHashString(HashMap vo) {
		StringBuffer sb = new StringBuffer();
		// ( 국세청양식 )
		sb.append("01:").append(vo.get("SUPREGNUM"));
		sb.append("+02:").append(vo.get("SUPCMPNAME"));
		sb.append("^").append(vo.get("SUPOWNR"));
		sb.append("+03:").append(vo.get("SUPBIZTYPE"));
		sb.append("^").append(vo.get("SUPBIZKIND"));
		sb.append("+04:").append(vo.get("SUPADDRESS"));
		// 공급받는자 정보
		sb.append("+05:").append(vo.get("DEMREGNUM"));
		sb.append("+06:").append(vo.get("DEMCMPNAME"));
		sb.append("^").append(vo.get("DEMOWNER"));
		sb.append("+07:").append(vo.get("DEMBIZTYPE"));
		sb.append("^").append(vo.get("DEMBIZKIND"));
		sb.append("+08:").append(vo.get("DEMADDRESS"));
		sb.append("+09:").append(vo.get("AGNREGNUM"));
		// 세금계산서 정보
		sb.append("+10:").append(vo.get("SUPTOTAMT"));
		sb.append("+11:").append(vo.get("SURTAX"));
		sb.append("+12:").append(vo.get("PUBDATE"));
		// 품목1
		sb.append("+13:").append(vo.get("ITEM1"));
		sb.append("+14:").append(vo.get("ITEMPRC1"));
		sb.append("+15:").append(vo.get("ITEMQTY1"));
		sb.append("+16:").append(vo.get("ITEMDATE1"));
		// 품목2
		sb.append("+17:").append(vo.get("ITEM2"));
		sb.append("+18:").append(vo.get("ITEMPRC2"));
		sb.append("+19:").append(vo.get("ITEMQTY2"));
		sb.append("+20:").append(vo.get("ITEMDATE2"));
		// 품목3
		sb.append("+21:").append(vo.get("ITEM3"));
		sb.append("+22:").append(vo.get("ITEMPRC3"));
		sb.append("+23:").append(vo.get("ITEMQTY3"));
		sb.append("+24:").append(vo.get("ITEMDATE3"));
		// 품목4
		sb.append("+25:").append(vo.get("ITEM4"));
		sb.append("+26:").append(vo.get("ITEMPRC4"));
		sb.append("+27:").append(vo.get("ITEMQTY4"));
		sb.append("+28:").append(vo.get("ITEMDATE4"));

		if ("1".equals(vo.get("DEALTYPE"))) {
			sb.append("+29:영수");
		} else {
			sb.append("+29:청구");
		}
		sb.append("+30:").append(vo.get("REMARK"));
		sb.append("+31:").append(vo.get("ITEMAMT1"));
		sb.append("+32:").append(vo.get("ITEMSTAX1"));
		sb.append("+33:").append(vo.get("ITEMAMT2"));
		sb.append("+34:").append(vo.get("ITEMSTAX2"));
		sb.append("+35:").append(vo.get("ITEMAMT3"));
		sb.append("+36:").append(vo.get("ITEMSTAX3"));
		sb.append("+37:").append(vo.get("ITEMAMT4"));
		sb.append("+38:").append(vo.get("ITEMSTAX4"));

		return sb.toString();
	}
 */
	/**
	 * 해쉬값을 얻기 위한 문자열 조합값 얻기.
	 *@param vo baseinvoice_tbl 하나의 레코드
	 *@return String 문자열 조합 
	 
	public String getHashString(ValueObject vo){
		StringBuffer sb = new StringBuffer();
		//( 국세청양식 )
		sb.append("01:").append(vo.get("SUPREGNUM"));
		sb.append("+02:").append(vo.get("SUPCMPNAME"));
		sb.append("^").append(vo.get("SUPOWNR"));
		sb.append("+03:").append(vo.get("SUPBIZTYPE"));
		sb.append("^").append(vo.get("SUPBIZKIND"));
		sb.append("+04:").append(vo.get("SUPADDRESS"));
		// 공급받는자 정보
		sb.append("+05:").append(vo.get("DEMREGNUM"));
		sb.append("+06:").append(vo.get("DEMCMPNAME"));
		sb.append("^").append(vo.get("DEMOWNER"));
		sb.append("+07:").append(vo.get("DEMBIZTYPE"));
		sb.append("^").append(vo.get("DEMBIZKIND"));
		sb.append("+08:").append(vo.get("DEMADDRESS"));
		sb.append("+09:").append(vo.get("AGNREGNUM"));
		// 세금계산서 정보
		sb.append("+10:").append(vo.get("SUPTOTAMT"));
		sb.append("+11:").append(vo.get("SURTAX"));
		sb.append("+12:").append(vo.get("PUBDATE"));
		// 품목1
		sb.append("+13:").append(vo.get("ITEM1"));
		sb.append("+14:").append(vo.get("ITEMPRC1"));
		sb.append("+15:").append(vo.get("ITEMQTY1"));
		sb.append("+16:").append(vo.get("ITEMDATE1"));
		// 품목2
		sb.append("+17:").append(vo.get("ITEM2"));
		sb.append("+18:").append(vo.get("ITEMPRC2"));
		sb.append("+19:").append(vo.get("ITEMQTY2"));
		sb.append("+20:").append(vo.get("ITEMDATE2"));
		// 품목3
		sb.append("+21:").append(vo.get("ITEM3"));
		sb.append("+22:").append(vo.get("ITEMPRC3"));
		sb.append("+23:").append(vo.get("ITEMQTY3"));
		sb.append("+24:").append(vo.get("ITEMDATE3"));
		// 품목4
		sb.append("+25:").append(vo.get("ITEM4"));
		sb.append("+26:").append(vo.get("ITEMPRC4"));
		sb.append("+27:").append(vo.get("ITEMQTY4"));
		sb.append("+28:").append(vo.get("ITEMDATE4"));

		if ("1".equals(vo.get("DEALTYPE"))) {
			sb.append("+29:영수");
		} else {
			sb.append("+29:청구");
		}
		sb.append("+30:").append(vo.get("REMARK"));
		sb.append("+31:").append(vo.get("ITEMAMT1"));
		sb.append("+32:").append(vo.get("ITEMSTAX1"));
		sb.append("+33:").append(vo.get("ITEMAMT2"));
		sb.append("+34:").append(vo.get("ITEMSTAX2"));
		sb.append("+35:").append(vo.get("ITEMAMT3"));
		sb.append("+36:").append(vo.get("ITEMSTAX3"));
		sb.append("+37:").append(vo.get("ITEMAMT4"));
		sb.append("+38:").append(vo.get("ITEMSTAX4"));
        return sb.toString();
	}

	
	
	
	
	public String getHashStringForUCESSDI(HashMap vo) {
		StringBuffer sb = new StringBuffer();
		// 국세청양식
		sb.append("01:").append(vo.get("SUPREGNUM"));
		sb.append("+02:").append(vo.get("SUPCMPNAME"));
		sb.append("^").append(vo.get("SUPOWNR"));
		sb.append("+03:").append(vo.get("SUPBIZTYPE"));
		sb.append("^").append(vo.get("SUPBIZKIND"));
		sb.append("+04:").append(vo.get("SUPADDRESS"));
		// 공급받는자 정보
		sb.append("+05:").append(vo.get("DEMREGNUM"));
		sb.append("+06:").append(vo.get("DEMCMPNAME"));
		sb.append("^").append(vo.get("DEMOWNER"));
		sb.append("+07:").append(vo.get("DEMBIZTYPE"));
		sb.append("^").append(vo.get("DEMBIZKIND"));
		sb.append("+08:").append(vo.get("DEMADDRESS"));
		sb.append("+09:").append(vo.get("AGNREGNUM"));
		// 세금계산서 정보
		sb.append("+10:").append(vo.get("SUPTOTAMT"));
		sb.append("+11:").append(vo.get("SURTAX"));
		sb.append("+12:").append(vo.get("PUBDATE"));
		// 품목1
		sb.append("+13:").append(vo.get("ITEM1"));
		sb.append("+14:").append(vo.get("ITEMPRC1"));
		sb.append("+15:").append(vo.get("ITEMQTY1"));
		sb.append("+16:").append(vo.get("ITEMDATE1"));
		// 품목2
		sb.append("+17:").append(vo.get("ITEM2"));
		sb.append("+18:").append(vo.get("ITEMPRC2"));
		sb.append("+19:").append(vo.get("ITEMQTY2"));
		sb.append("+20:").append(vo.get("ITEMDATE2"));
		// 품목3
		sb.append("+21:").append(vo.get("ITEM3"));
		sb.append("+22:").append(vo.get("ITEMPRC3"));
		sb.append("+23:").append(vo.get("ITEMQTY3"));
		sb.append("+24:").append(vo.get("ITEMDATE3"));
		// 품목4
		sb.append("+25:").append(vo.get("ITEM4"));
		sb.append("+26:").append(vo.get("ITEMPRC4"));
		sb.append("+27:").append(vo.get("ITEMQTY4"));
		sb.append("+28:").append(vo.get("ITEMDATE4"));

		if ("1".equals(vo.get("DEALTYPE"))) {
			sb.append("+29:영수");
		} else {
			sb.append("+29:청구");
		}
		sb.append("+30:").append(vo.get("REMARK"));
		sb.append("+31:").append(vo.get("ITEMAMT1"));
		sb.append("+32:").append(vo.get("ITEMSTAX1"));
		sb.append("+33:").append(vo.get("ITEMAMT2"));
		sb.append("+34:").append(vo.get("ITEMSTAX2"));
		sb.append("+35:").append(vo.get("ITEMAMT3"));
		sb.append("+36:").append(vo.get("ITEMSTAX3"));
		sb.append("+37:").append(vo.get("ITEMAMT4"));
		sb.append("+38:").append(vo.get("ITEMSTAX4"));

		sb.append("+39:").append(vo.get("ITEMSTD1"));
		sb.append("+40:").append(vo.get("ITEMSTD2"));
		sb.append("+41:").append(vo.get("ITEMSTD3"));
		sb.append("+42:").append(vo.get("ITEMSTD4"));

		sb.append("+43:").append(vo.get("ITEMMEMO1"));
		sb.append("+44:").append(vo.get("ITEMMEMO2"));
		sb.append("+45:").append(vo.get("ITEMMEMO3"));
		sb.append("+46:").append(vo.get("ITEMMEMO4"));

		return sb.toString();
	}
*/
	/**
	 * 파일이름을 받아서 해쉬값을 추출한다.
	 * 
	 * @param fullName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String getHashFile(String fullName) throws IOException, Exception {
		StringBuffer resultValue = new StringBuffer("");
		try {
			EwPKI hash = EwPKI.getInstance();
			//EVCSP.evHash hash = EVCSP.evHash.getInstance();
			File f1;
			System.err.println("file fullName for the hash_file : " + fullName);
			f1 = new File(fullName);

			if (f1.exists() == false) {
				System.err.println("file not found :  " + fullName);
			} else {
				if (EwPKI.EV_OK != hash.EVHash_File(resultValue, fullName)) {
					System.err.println("\tError Code: " + hash.getErrorCode());
					System.err.println("\tError Message: " + hash.getErrorMsg());
				}
			}
		} catch (Exception exc) {
			System.err.print("EwSignBiz.getHashFile()::Error" + exc.getMessage());
			throw exc;
		}
		return resultValue.toString();
	}
	
	/**
	 * 내부 사용자 비밀번호을 복호화한다. .
	 * 
	 * @param String -hashedCertVal 해쉬된 DN값
	 * @param String -orgPwd 암호화된 비밀번호
	 * 
	 * @return String - 복호화 Password .
	 */
	public String getOrgPasswd(String hashedCertVal, String orgPwd) throws CertificateFailException, Exception {
		EwPKI pki = EwPKI.getInstance();
				
		String algorithm = "3DES";
		
		KtnetPBKDF p = new KtnetPBKDF();
		p.setEncAlgorithm(algorithm);
		
		//키생성에 사용된 seed
		p.generateKey(hashedCertVal);
		
		//key & iv 구하기
		byte[] keyBytes = p.getKeyBytes();
		byte[] ivBytes = p.getKeyBytes();
		RawSecretKey secretKey = new RawSecretKey(keyBytes, algorithm);
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		
		//Cipher 설정
		Cipher cipher = Cipher.getInstance(algorithm + "/CBC/PKCS5Padding", "JeTS");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		
		//암호화 값
		String encrypted = orgPwd;
		byte[] encryptedBytes = JetsUtil.decodeBase64(encrypted.getBytes());
		
		//복호화 값
		byte[] decrypted = cipher.doFinal(encryptedBytes);
		System.out.println("복호화 값 :  "+ new String(decrypted));
		
		return new String(decrypted);
	}
	
	public byte[] envelopData(byte[] data){
		EwPKI pki = EwPKI.getInstance();
		byte[] msg = null;
		try {
			EnvelopedData ed = new EnvelopedData(data, null);
			ed.addRecipient(pki.getServerkmCert());
			msg = JetsUtil.encodeBase64(ed.envelop());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ASN1Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PKCSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public byte[] decryptEnvelopData(byte[] data){
		EwPKI pki = EwPKI.getInstance();
		byte[] msg = null;
		
		try {
			EnvelopedData ed = new EnvelopedData(JetsUtil.decodeBase64(data));
			ed.setupCipher(pki.getServerkmCert(), pki.getServerkmPriKey(), pki.getServerKmKeyPassword());
			msg = ed.getContent();
		} catch (Base64Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PKCSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public String EVVerifySignedData(String singData) throws CertificateFailException {
		EwPKI pki = EwPKI.getInstance();
		StringBuffer resultData = new StringBuffer();
		int resultCode = pki.EVVerifySignedData(resultData, singData);
		
		if(resultCode == EwPKI.EV_ERROR){
			throw new CertificateFailException( "검증실패 \n" + pki.getErrorMessage());
		}
		return resultData.toString();
	}
}
