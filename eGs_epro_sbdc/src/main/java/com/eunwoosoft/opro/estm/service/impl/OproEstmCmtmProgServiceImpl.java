package com.eunwoosoft.opro.estm.service.impl;

import java.security.cert.CertPathValidator;
import java.security.cert.PKIXCertPathValidatorResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.comm.dao.OproCommDefaultDao;
import com.eunwoosoft.opro.estm.dao.OproEstmCmtmProgDao;
import com.eunwoosoft.opro.estm.service.OproEstmCmtmProgService;

import tradesign.crypto.cert.validator.ExtendedPKIXParameters;
import tradesign.crypto.cert.validator.PKIXCertPath;
import tradesign.crypto.provider.JeTS;
import tradesign.pki.pkix.SignedData;
import tradesign.pki.pkix.X509Certificate;
import tradesign.pki.util.JetsUtil;


@Service(value="oproEstmCmtmProgService")
public class OproEstmCmtmProgServiceImpl extends AbstractFwkService implements OproEstmCmtmProgService {
	private static final Logger LOG = LoggerFactory.getLogger(OproEstmCmtmProgServiceImpl.class);
	static final String contextPath = "estm/cmtm";
	
	@Resource(name="oproEstmCmtmProgDao") 
	private OproEstmCmtmProgDao oproEstmCmtmProgDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;

	@Resource(name="oproCommDefaultDao")
	private OproCommDefaultDao oproCommDefaultDao;
	
	
	@Override
	public FwkTransferObject estmCmtmProgList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		
		trans.put("estmCmtmProgList", oproEstmCmtmProgDao.selectEstmCmtmProgList(parameterMap));
		trans.put("estmCmtmProgListTotCnt", oproEstmCmtmProgDao.selectEstmCmtmProgListTotCnt(parameterMap));
	
		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmProgListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		trans.put("dataList", oproEstmCmtmProgDao.selectEstmCmtmProgExcelList(parameterMap));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmProgDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		// 심사위원 서명파일 첨부파일 그룹번호 조회
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// 심사위원 서명파일 조회
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		//심사위원첨부파일 코드 조회
		parameterMap.put("P_CD_ID", "FILE_DOC_SECD");
		List<FwkDataEntity> estmCmtmFileCodeList = oproCommDefaultDao.selectCodeList(parameterMap); 
		trans.put("estmCmtmFileCodeList", estmCmtmFileCodeList);

		//심사위원첨부파일 조회
		List<FwkDataEntity> estmCmtmFileList = oproEstmCmtmProgDao.selectCmtmFileList(parameterMap); 
		trans.put("estmCmtmFileList", estmCmtmFileList);
		
		// 파일 해시값 목록 조회
		List<FwkDataEntity> fileHashcdValueListInqire = oproEstmCmtmProgDao.selectFileHashcdValueListInqire(parameterMap);
		trans.put("fileHashcdValueListInqire", fileHashcdValueListInqire);
		
		// 서명여부
		FwkDataEntity FILE_SIGN_YN = (FwkDataEntity) oproEstmCmtmProgDao.selectCmtmFileSignYn(parameterMap);   // T_ESTM_FILE_SIGN
		trans.put("FILE_SIGN_YN", FILE_SIGN_YN);
		
		System.err.println("@@@ FILE_SIGN_YN ==> " + FILE_SIGN_YN);
		
		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmfileRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		
		String fileGrpNo = fileUpload(parameterMap, multipartRequest);
		parameterMap.put("P_FILE_GRP_NO", fileGrpNo);
		parameterMap.put("P_DEL_AT", "N");
		// 평가위원학력 등록
		oproEstmCmtmProgDao.updateEstmMngMst(parameterMap);   // T_ESTM_CMTM UPDATE
		
		parameterMap.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		parameterMap.put("resultCode", "Success");
		
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmfileUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		
		String fileGrpNo = fileUpload(parameterMap, multipartRequest);
		parameterMap.put("P_FILE_GRP_NO", fileGrpNo);
		parameterMap.put("P_DEL_AT", "N");
		
		parameterMap.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		parameterMap.put("resultCode", "Success");
		
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmProgProcdADetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = oproEstmCmtmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = oproEstmCmtmProgDao.selectEstmObjValueList(parameterMap);
		
		// 평가위원 목록 조회
		List<FwkDataEntity> estmCmtmLastList = oproEstmCmtmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// 평가위원+평가대상 목록 조회
		List<FwkDataEntity> estmValueList = oproEstmCmtmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", oproEstmCmtmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);

		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmProgProcdBDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = oproEstmCmtmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = oproEstmCmtmProgDao.selectEstmObjValueList(parameterMap);
		
		// 평가위원 목록 조회
		List<FwkDataEntity> estmCmtmLastList = oproEstmCmtmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// 평가위원+평가대상 목록 조회
		List<FwkDataEntity> estmValueList = oproEstmCmtmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", oproEstmCmtmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmProgVidoMtngDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 화상회의 참여자 구분
		// 관리자 ""
		// 평가위원 "CMTM"
		// 평가대상 "parameterMap.put("P_ESTM_NO")
		parameterMap.put("P_USER_GBN","CMTM-"); // 내부평가위원는 CMTM 안붙음
		parameterMap.put("P_ROLE","interviewer"); //admin , interviewer , interviewee
		parameterMap.put("P_USR_ID",parameterMap.getString("USR_ID"));
		parameterMap.put("P_VIDO_LINK_URL",FwkMessageUtil.getMessage("VIDO.LINK.URL"));
		
		//화상회의정보
		List<FwkDataEntity> estmVidoList = oproEstmCmtmProgDao.selectEstmMngProgVidoMtngList(parameterMap);   // T_ESTM_MNG_MST   
		trans.put("estmVidoList", estmVidoList);
		
		// 평가절차리스트 조회
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}
	
	public String fileUpload(final FwkParameterMap parameterMap, MultipartHttpServletRequest multipartRequest){
		//구분에 따라 파일업로드
		FwkParameterMap fileParameterMap =  CmmnUtil.fileGbnMultiUpload(multipartRequest, "P_FILE", contextPath, parameterMap);

		List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
		
		String atchFileGroupNo = (String) fileParameterMap.get("atchFileGroupNo");
		parameterMap.put("atchFileGroupNo", fileParameterMap.get("atchFileGroupNo"));
		parameterMap.put("P_FILE_GRP_NO", fileParameterMap.get("atchFileGroupNo"));
		//parameterMap.put("P_FILE_DOC_SECD", fileParameterMap.get("P_FILE_DOC_SECD"));
		/*parameterMap.put("fileList", pfileList);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());*/
		
		//공통 첨부파일에 저장 T_MM_FILE_MST
		for(int i=0; i< pfileList.size(); i++){
			parameterMap.put("P_FILE_DOC_SECD", pfileList.get(i).get("P_FILE_DOC_SECD"));
			parameterMap.put("P_FILE_SZ", pfileList.get(i).get("P_FILE_SZ"));
			parameterMap.put("P_SV_FILE_NM", pfileList.get(i).get("P_SV_FILE_NM"));
			parameterMap.put("P_SYS_FILE_NM", pfileList.get(i).get("P_SYS_FILE_NM"));
			parameterMap.put("P_FILE_LCTN", pfileList.get(i).get("P_FILE_LCTN"));
			comAtmaAtchFileDao.deleteAtchFileUpdt(parameterMap);	//DEL_AT ='Y'처리
			
			comAtmaAtchFileDao.insertAtchFileRegistOne(parameterMap);	
		}
		
		return atchFileGroupNo;
	}

	@Override
	public FwkTransferObject estmCmtmSign(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		// 로그인한 session의 DN
		String LOGIN_DN = session.getString("LOGIN_DN");
		System.err.println("@@@ LOGIN_DN ==> " + LOGIN_DN);
		
		String resultCode = "";
		String msg = "";
		
		Object P_FILE_SN = parameterMap.get("P_FILE_SN");
		Object P_ELEC_SIGN_VAL = parameterMap.get("P_ELEC_SIGN_VAL");
		Object P_CERT_INHR_VAL = parameterMap.get("P_CERT_INHR_VAL");
		
		// 서명할 원문 데이타
		String plaintext = "";
		String Strsigned_msg = "", Strveryfi_msg = "", SignTime ="";
		
		String CertDn[] = null; 
		String Strcpvr[] = null;
		
		if(P_FILE_SN != null){
			if(P_FILE_SN instanceof String){

				try {
					
					JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
					
					// 서명할 원문 데이타
					plaintext = new String(parameterMap.getString("P_ELEC_SIGN_VAL").getBytes("ISO-8859-1"), "UTF-8");
					
					Strsigned_msg = new String(parameterMap.getString("P_ELEC_SIGN_VAL").getBytes("ISO-8859-1"));
					byte[] signed_msg = JetsUtil.decodeBase64(parameterMap.getString("P_ELEC_SIGN_VAL").getBytes("ISO-8859-1"));
					
					SignedData sd = new SignedData(signed_msg);
					
					byte[] veryfi_msg = sd.getContent();
					Strveryfi_msg = new String(veryfi_msg);
					
					X509Certificate[] certs = sd.verify();
					
					CertDn  = new String[certs.length];
					Strcpvr  = new String[certs.length];
					
					String verifyCertDN = "";   // 검증된 인증서 dn
					
					for (int i = 0; i < certs.length; i++)
					{
						CertDn[i] = certs[i].getSubjectDNStr();
						
						verifyCertDN = CertDn[i];
								
						PKIXCertPath cp = new PKIXCertPath(certs[i], "PkiPath");
						ExtendedPKIXParameters cpp = new ExtendedPKIXParameters(JeTS.getTAnchorSet());
						CertPathValidator cpvi = CertPathValidator.getInstance("PKIX","JeTS");
						PKIXCertPathValidatorResult cpvr = (PKIXCertPathValidatorResult) cpvi.validate(cp, cpp);
								
						Strcpvr[i] = cpvr.toString();
						
					}
					
					if(LOGIN_DN.equals(verifyCertDN)){
						oproEstmCmtmProgDao.updateEstmFileSignRegist(parameterMap);   // T_ESTM_FILE_SIGN UPDATE
						
						LOG.debug("전자서명에 성공하였습니다.");
						resultCode = "success";
						LOG.debug("===== SIGN SUCCESS =====");
						
						// 서명 시각
						SignTime = "서명시각 : " + sd.getSigningTime().toString();
						System.err.println("@@@ SignTime ==> " + SignTime);
						
						
					}else{
						LOG.debug("로그인한 인증서가 아닙니다. 로그인한 인증서로 서명해주세요.");
						resultCode = "fail";
						msg = "로그인한 인증서가 아닙니다. \n로그인한 인증서로 서명해주세요.";
						LOG.debug("===== SIGN FAIL 222 =====");
					}
					
				} catch (Exception e) {
					String ret = e.getMessage();
					LOG.debug("전자서명에 실패하였습니다.");
					resultCode = "fail";
					LOG.debug("===== SIGN FAIL 111 =====");
					e.printStackTrace();
					
					System.err.println("@@@ ret ==> " + ret);
				}
				
			}else{
				
				
				try {
					
					JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
					
					List<String> fileSnList = new ArrayList<String>();
					fileSnList = (ArrayList<String>)P_FILE_SN;
					
					List<String> elecSignValList = new ArrayList<String>();
					List<String> certInhrValList = new ArrayList<String>();
					elecSignValList = (ArrayList<String>) P_ELEC_SIGN_VAL;
					certInhrValList = (ArrayList<String>) P_CERT_INHR_VAL;
					
					
					for(int i=0; i<fileSnList.size(); i++){
						parameterMap.put("P_FILE_SN", fileSnList.get(i));
						
						// 서명할 원문 데이타
						plaintext = new String(elecSignValList.get(i).getBytes("ISO-8859-1"), "UTF-8");
						
						Strsigned_msg = new String(elecSignValList.get(i).getBytes("ISO-8859-1"));
						byte[] signed_msg = JetsUtil.decodeBase64(elecSignValList.get(i).getBytes("ISO-8859-1"));
						
						SignedData sd = new SignedData(signed_msg);
						
						byte[] veryfi_msg = sd.getContent();
						Strveryfi_msg = new String(veryfi_msg);
						
						X509Certificate[] certs = sd.verify();
						
						CertDn  = new String[certs.length];
						Strcpvr  = new String[certs.length];
						
						String verifyCertDN = "";   // 검증된 인증서 dn
						
						for (int j = 0; j < certs.length; j++)
						{
							CertDn[j] = certs[j].getSubjectDNStr();
							
							verifyCertDN = CertDn[j];
									
							PKIXCertPath cp = new PKIXCertPath(certs[j], "PkiPath");
							ExtendedPKIXParameters cpp = new ExtendedPKIXParameters(JeTS.getTAnchorSet());
							CertPathValidator cpvi = CertPathValidator.getInstance("PKIX","JeTS");
							PKIXCertPathValidatorResult cpvr = (PKIXCertPathValidatorResult) cpvi.validate(cp, cpp);
									
							Strcpvr[j] = cpvr.toString();
							
						}
						
						
						parameterMap.put("P_ELEC_SIGN_VAL", elecSignValList.get(i));
						parameterMap.put("P_CERT_INHR_VAL", certInhrValList.get(i));
						
						System.err.println("@@@ elecSignValList.get(i) ==> " + elecSignValList.get(i));
						
						if(LOGIN_DN.equals(verifyCertDN)){
							oproEstmCmtmProgDao.updateEstmFileSignRegist(parameterMap);   // T_ESTM_FILE_SIGN UPDATE
							
							LOG.debug("전자서명에 성공하였습니다.");
							resultCode = "success";
							LOG.debug("===== SIGN SUCCESS =====");
							
							// 서명 시각
							SignTime = "서명시각 : " + sd.getSigningTime().toString();
							System.err.println("@@@ SignTime ==> " + SignTime);
						}else{
							LOG.debug("로그인한 인증서가 아닙니다. 로그인한 인증서로 서명해주세요.");
							resultCode = "fail";
							msg = "로그인한 인증서가 아닙니다. \n로그인한 인증서로 서명해주세요.";
							LOG.debug("===== SIGN FAIL 222 =====");
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					
					String ret = e.getMessage();
					LOG.debug("전자서명에 실패하였습니다.");
					resultCode = "fail";
					LOG.debug("===== SIGN FAIL 111 =====");
					e.printStackTrace();
					
					System.err.println("@@@ ret ==> " + ret);
					
				}
				
				
				
			}
		}
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "심사위원 서명파일 전자서명");
		oproEstmCmtmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
		trans.put("resultCode", resultCode);
		trans.put("msg", msg);
		
		System.err.println("@@@ trans ==> " + trans);
		
		return trans;
	}
}
