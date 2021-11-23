package com.eunwoosoft.opro.main.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dreammirae.gt.radius.client.GptwrAuthService;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.fol.util.SDBCryptoUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.main.dao.OproMainLoginFormDao;
import com.eunwoosoft.opro.main.service.OproMainLoginFormService;
import com.eunwoosoft.opro.noti.dao.OproNotiGnrlDao;
import com.eunwoosoft.opro.noti.service.OproNotiGnrlService;

/**	
 * 
 * com.eunwoosoft.opro.main.service.impl
 * OproMainLoginFormServiceImpl.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
@Service(value="oproMainLoginFormService")
public class OproMainLoginFormServiceImpl extends AbstractFwkService implements OproMainLoginFormService{
	private static final Logger LOG = LoggerFactory.getLogger(OproMainLoginFormServiceImpl.class);

	@Resource(name="oproMainLoginFormDao")
	private OproMainLoginFormDao oproMainLoginFormDao;

	@Resource(name="oproNotiGnrlDao")
	private OproNotiGnrlDao oproNotiGnrlDao;
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.main.service.OproMainLoginFormService#loginByBizrno(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject loginByBizrno(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
	
		FwkDataEntity dataEntity = oproMainLoginFormDao.selectEntrpsMberInqireByBizrNo(parameterMap);
		trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);

		return trans;
	}
	
	@Override
	public FwkTransferObject loginByRsdnNo(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity = oproMainLoginFormDao.selectEstmCmtmInqireByRsdnNo(parameterMap);   // V_MM_ESTM_CMTM_MST
		trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
		
		return trans;
	}
	
	/**
	 * 평가위원 비상용 로그인
	 */
	@Override
	public FwkTransferObject emgncCmtmEmplyrLoginByRsdnNo(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 1. 평가담당자 아이디/비밀번호 체크
		 */
		String resultMessage = "";
		int resultCode = 0;

		// 사용자 조회
		FwkDataEntity entityById = oproMainLoginFormDao.selectEmplyrInqireById(parameterMap);
		
		if(entityById == null) {
			//계정이 없는 경우
			resultCode = -9999;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";

			/**
			 * 2. 평가위원 체크
			 */
			trans.put("resultCode",resultCode);
			trans.put(OproMainLoginFormService.LOGIN_RESULT, "");
			
		}else if( entityById != null ){ 		
				// 비밀번호 해시값 변환
				parameterMap.put("P_USR_ID",  entityById.getString("USR_ID"));		//계정ID
				
				//아이디, 패스워드로 사용자 조회
				FwkDataEntity entityByIdAndPw = oproMainLoginFormDao.selectEmplyrInqireByIdAndPassword(parameterMap);
				
				if(resultCode != -9999 && entityByIdAndPw == null){
					//비밀번호가 틀린경우
					resultCode = -44;
					resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
				}else {
					entityById = entityByIdAndPw;
					entityById.put("P_SSO_STATUS", "OK");	
				}	

				/**
				 * 2. 평가위원 체크
				 */
				FwkDataEntity dataEntity = oproMainLoginFormDao.selectEstmCmtmInqireByRsdnNo(parameterMap);   // V_MM_ESTM_CMTM_MST
				trans.put("resultCode",resultCode);
				trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
				
		}
		
		
		return trans;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.main.service.OproMainLoginFormService#joinRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject joinRegist(final FwkParameterMap parameterMap) throws Exception {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "vend";
		FwkParameterMap vendAcctFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_ACCT_FILE", contextPath);   // 통장사본
		FwkParameterMap vendAcqsFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_ACQS_FILE", contextPath);   // 기업정보
		FwkParameterMap vendRedFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_RED_VEND_FILE", contextPath);   // 필수첨부파일
		FwkParameterMap vendFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_VEND_FILE", contextPath);   // 기타첨부파일
		String baseFileGrpNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		
		FwkTransferObject trans = new FwkTransferObject();
		try{
			
			String BIZRNO = parameterMap.getString("P_BIZRNO1") + parameterMap.getString("P_BIZRNO2") + parameterMap.getString("P_BIZRNO3");
			parameterMap.put("P_VEND_REG_NO", BIZRNO);   // 업체등록번호 = 사업자등록번호
			parameterMap.put("P_BIZRNO", BIZRNO);   // 업체등록번호 = 사업자등록번호
			
			parameterMap.put("P_CPTL_AMT", FwkStringUtil.deleteComma(FwkStringUtil.nvl(parameterMap.get("P_CPTL_AMT").toString(), "0")));   // 자본금액
			
			if(parameterMap.get("P_ESTB_DE") != null && !"".equals(parameterMap.get("P_ESTB_DE"))){ // 설립일자
				parameterMap.put("P_ESTB_DE", FwkFormatUtil.formatDate(parameterMap.get("P_ESTB_DE").toString(), "yyyy-MM-dd", "yyyyMMdd"));
			}
			
			parameterMap.put("P_DEL_AT", "N");
			parameterMap.put("P_VEND_STCD", "A");
			parameterMap.put("P_USE_YN", "Y");
			
			parameterMap.put("P_REGR_ID", BIZRNO);
			parameterMap.put("P_REGR_NM", parameterMap.getString("P_VEND_NM"));
			parameterMap.put("P_MODR_ID", BIZRNO);
			parameterMap.put("P_MODR_NM", parameterMap.getString("P_VEND_NM"));
			
			
			
			// 2019-03-14 은잔디 수정 : SHA-512 => SHA-256
			String PW = parameterMap.getString("P_PWD");
			parameterMap.put("P_LOGIN_PASSWORD", SDBCryptoUtil.encryptSha256(PW));
			
			//System.err.println("비밀번호 암호화 ==> " + SDBCryptoUtil.encryptSha256(PW));
			parameterMap.put("P_PWD", SDBCryptoUtil.encryptSha256(PW));
			
			
			// 업체마스터 - T_CU_MST
			oproMainLoginFormDao.insertVendInfoRegist(parameterMap);

			// 업체사용자 - T_CU_USER
			parameterMap.put("P_EMAL_ADDR", parameterMap.get("P_EMAL_ADDR_USER"));
			parameterMap.put("P_TEL_NO", parameterMap.get("P_TEL_NO_USER"));
			parameterMap.put("P_USR_SN", 1);
			parameterMap.put("P_DLGT_YN", "Y");
			oproMainLoginFormDao.insertVendChrgrInfoRegist(parameterMap);
			
			// 업체계좌
			String atchFileGroupNoACC = "";
			
			//atchFileGroupNoACC = vendAcctFileParameterMap.getString("atchFileGroupNo");
			parameterMap.put("P_ACC_NEW", "Y");
			
			parameterMap.put("P_ACC_SN", "1");   // 계좌일련번호
			parameterMap.put("P_ORD_SN", "1");   // 정렬일련번호
			
			if(parameterMap.get("P_BKCD") != null && !"".equals(parameterMap.get("P_BKCD"))){
				FwkDataEntity P_BKNM = oproMainLoginFormDao.getBknm(parameterMap);
				parameterMap.put("P_BKNM", P_BKNM.getString("P_BKNM"));
			}
			
			if(vendAcctFileParameterMap != null){
				
				List<Map<String, Object>> list = (List<Map<String, Object>>)vendAcctFileParameterMap.get("list");
				List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
				
				
				Object P_FILE_DOC_NM =  parameterMap.get("P_ACCT_FILE_DOC_NM");
				Object P_FILE_DOC_SECD =  parameterMap.get("P_ACCT_FILE_DOC_SECD");
				//Object P_TSK_VKEY1 =  parameterMap.get("P_ACNO_NEW");
				
				
				if(P_FILE_DOC_NM instanceof String) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", P_FILE_DOC_NM.toString());
						map.put("P_FILE_DOC_SECD", P_FILE_DOC_SECD.toString());
						//map.put("P_TSK_VKEY1", P_TSK_VKEY1.toString());
						newList.add(map);
					}
				}else if(P_FILE_DOC_NM instanceof String[]) {
					String[] fileDocSecdArray = (String[]) P_FILE_DOC_SECD;
					String[] fileDocNmArray = (String[]) P_FILE_DOC_NM;
					//String[] tskKey1Array = (String[]) P_TSK_VKEY1;
					
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", fileDocNmArray[i]);
						map.put("P_FILE_DOC_SECD", fileDocSecdArray[i]);
						//map.put("P_TSK_VKEY1", tskKey1Array[i]);
						newList.add(map);
					}
				}else if(P_FILE_DOC_NM instanceof ArrayList) {
					ArrayList<String> fileDocSecdList = (ArrayList<String>)P_FILE_DOC_SECD;
					ArrayList<String> fileDocNmList = (ArrayList<String>)P_FILE_DOC_NM;
					//ArrayList<String> tskKey1List = (ArrayList<String>)P_TSK_VKEY1;
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
						map.put("P_FILE_DOC_SECD", fileDocSecdList.get(i));
						//map.put("P_TSK_VKEY1", tskKey1List.get(i));
						newList.add(map);
					}
				}
				
				// 공통 파일정보 저장
				parameterMap.put("atchFileGroupNo", atchFileGroupNoACC);
				parameterMap.put("fileList", newList);
				parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
				comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
			}
			
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNoACC);
			
			oproMainLoginFormDao.insertVendAcctInfoList(parameterMap);

			/*Object ACNO = parameterMap.get("P_ACNO");   // 계좌번호
			Object BKCD = parameterMap.get("P_BKCD");   // 은행코드
			Object DPSO = parameterMap.get("P_DPSO");   // 예금주
			Object BNK_BRNC_NM = parameterMap.get("P_BNK_BRNC_NM");   // 은행지점명
			
			if(ACNO != null) {
				
				atchFileGroupNoACC = vendAcctFileParameterMap.getString("atchFileGroupNo");
				parameterMap.put("P_ACC_NEW", "Y");
				
				
				if(ACNO instanceof String){
					parameterMap.put("P_ACC_SN", "1");   // 계좌일련번호
					parameterMap.put("P_ORD_SN", "1");   // 정렬일련번호
					parameterMap.put("P_ACNO", parameterMap.getString("P_ACNO"));
					parameterMap.put("P_BKCD", parameterMap.getString("P_BKCD"));
					
					if(parameterMap.get("P_BKCD") != null && !"".equals(parameterMap.get("P_BKCD"))){
						FwkDataEntity P_BKNM = oproMainLoginFormDao.getBknm(parameterMap);
						parameterMap.put("P_BKNM", P_BKNM.getString("P_BKNM"));
					}
					
					parameterMap.put("P_DPSO", parameterMap.getString("P_DPSO"));
					parameterMap.put("P_BNK_BRNC_NM", parameterMap.getString("P_BNK_BRNC_NM"));
					
					if(vendAcctFileParameterMap != null){
						
						List<Map<String, Object>> list = (List<Map<String, Object>>)vendAcctFileParameterMap.get("list");
						List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
						
						Object P_ACCT_FILE_DOC_SECD =  parameterMap.get("P_ACCT_FILE_DOC_SECD");
						
						if(ACNO instanceof String) {
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_FILE_DOC_SECD", P_ACCT_FILE_DOC_SECD.toString());
								map.put("P_TSK_VKEY1", parameterMap.getString("P_ACNO"));
								newList.add(map);
							}
						}
						
						// 공통 파일정보 저장
						parameterMap.put("atchFileGroupNo", atchFileGroupNoACC);
						parameterMap.put("fileList", newList);
						parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
						comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
					}
					
					parameterMap.put("P_FILE_GRP_NO", atchFileGroupNoACC);
					
					// 업체계좌 등록 - T_CU_ACCT
					oproMainLoginFormDao.insertVendAcctInfoList(parameterMap);
					
					
				}else{
					String[] ACNO_LIST = (String[]) ACNO;
					String[] BKCD_LIST = (String[]) BKCD;
					String[] DPSO_LIST = (String[]) DPSO;
					String[] BNK_BRNC_NM_LIST = (String[]) BNK_BRNC_NM;
					
					for(int i = 0; i < ACNO_LIST.length; i++) {
						
						if(ACNO_LIST != null && ACNO_LIST[i] != null) {
							parameterMap.put("P_ACNO", ACNO_LIST[i]);
						}
						
						if(BKCD_LIST != null && BKCD_LIST[i] != null) {
							parameterMap.put("P_BKCD", BKCD_LIST[i]);
							
							if(parameterMap.get("P_BKCD") != null && !"".equals(parameterMap.get("P_BKCD"))){
								FwkDataEntity P_BKNM = oproMainLoginFormDao.getBknm(parameterMap);
								parameterMap.put("P_BKNM", P_BKNM.getString("P_BKNM"));
							}
						}
						
						if(DPSO_LIST != null && DPSO_LIST[i] != null) {
							parameterMap.put("P_DPSO", DPSO_LIST[i]);
						}
						if(BNK_BRNC_NM_LIST != null && BNK_BRNC_NM_LIST[i] != null) {
							parameterMap.put("P_BNK_BRNC_NM", BNK_BRNC_NM_LIST[i]);
						}
						
						parameterMap.put("P_ACC_SN", i+1);
						parameterMap.put("P_ORD_SN", i+1);
						parameterMap.put("P_FILE_GRP_NO", atchFileGroupNoACC);
						
						// 업체계좌 등록 - T_CU_ACCT
						oproMainLoginFormDao.insertVendAcctInfoList(parameterMap);
						
					}
					
					
					if(vendAcctFileParameterMap != null){
						
						List<Map<String, Object>> list = (List<Map<String, Object>>)vendAcctFileParameterMap.get("list");
						List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
						
						Object P_ACCT_FILE_DOC_SECD =  parameterMap.get("P_ACCT_FILE_DOC_SECD");
						Object P_ACNO_NEW =  parameterMap.get("P_ACNO_NEW");
						
						if(P_ACCT_FILE_DOC_SECD instanceof String) {
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_TSK_VKEY1", P_ACNO_NEW.toString());
								map.put("P_FILE_DOC_SECD", P_ACCT_FILE_DOC_SECD.toString());
								newList.add(map);
							}
						}else if(P_ACCT_FILE_DOC_SECD instanceof String[]) {
							String[] tskKey1Array = (String[]) P_ACNO_NEW;
							String[] fileDocSecdArray = (String[]) P_ACCT_FILE_DOC_SECD;
							
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_TSK_VKEY1", tskKey1Array[i]);
								map.put("P_FILE_DOC_SECD", fileDocSecdArray[i]);
								newList.add(map);
							}
						}else if(P_ACCT_FILE_DOC_SECD instanceof ArrayList) {
							ArrayList<String> tskKey1List = (ArrayList<String>)P_ACNO_NEW;
							ArrayList<String> fileDocSecdList = (ArrayList<String>)P_ACCT_FILE_DOC_SECD;
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_TSK_VKEY1", tskKey1List.get(i));
								map.put("P_FILE_DOC_SECD", fileDocSecdList.get(i));
								newList.add(map);
							}
						}
						
						// 공통 파일정보 저장
						if("Y".equals(parameterMap.getString("P_ACC_NEW"))){
							parameterMap.put("atchFileGroupNo", atchFileGroupNoACC);
							parameterMap.put("fileList", newList);
							parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
							
							comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
						}
						
					}
					
				}
			}*/
			
			
			// 기업정보
			String atchFileGroupNoACQS = "";
			
			Object DATA_CD = parameterMap.get("P_DATA_CD");   // 자료코드
			Object VLD_STDE = parameterMap.get("P_VLD_STDE");   // 유효시작일자
			Object VLD_ENDE = parameterMap.get("P_VLD_ENDE");   // 유효종료일자
			
			if(DATA_CD != null) {
				
				//atchFileGroupNoACQS = vendAcqsFileParameterMap.getString("atchFileGroupNo");
				parameterMap.put("P_ACQS_NEW", "Y");
				
				
				if(DATA_CD instanceof String){
					parameterMap.put("P_CTT_ACQS_SN", "1");   // 인증취득일련번호
					parameterMap.put("P_DATA_CD", parameterMap.getString("P_DATA_CD"));
					
					if(parameterMap.get("P_DATA_CD") != null && !"".equals(parameterMap.get("P_DATA_CD"))){
						FwkDataEntity P_CTT_NM = oproMainLoginFormDao.getCttNm(parameterMap);
						parameterMap.put("P_CTT_NM", P_CTT_NM.getString("P_CTT_NM"));
					}
					
					if(parameterMap.get("P_VLD_STDE") != null && !"".equals(parameterMap.get("P_VLD_STDE"))){
						parameterMap.put("P_VLD_STDE", FwkFormatUtil.formatDate(parameterMap.get("P_VLD_STDE").toString(), "yyyy-MM-dd", "yyyyMMdd"));
					}
					
					if(parameterMap.get("P_VLD_ENDE") != null && !"".equals(parameterMap.get("P_VLD_ENDE"))){
						parameterMap.put("P_VLD_ENDE", FwkFormatUtil.formatDate(parameterMap.get("P_VLD_ENDE").toString(), "yyyy-MM-dd", "yyyyMMdd"));
					}
					
					if(vendAcqsFileParameterMap != null){
						List<Map<String, Object>> list = (List<Map<String, Object>>)vendAcqsFileParameterMap.get("list");
						List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();

						Object P_ACQS_FILE_DOC_SECD =  parameterMap.get("P_ACQS_FILE_DOC_SECD");
						
						if(DATA_CD instanceof String) {
							
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_FILE_DOC_SECD", P_ACQS_FILE_DOC_SECD.toString());
								map.put("P_TSK_VKEY1", parameterMap.getString("P_DATA_CD"));
								newList.add(map);
							}
						}
						
						// 공통 파일정보 저장
						parameterMap.put("atchFileGroupNo", atchFileGroupNoACQS);
						parameterMap.put("fileList", newList);
						parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
						comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
					}
					
					
					parameterMap.put("P_FILE_GRP_NO", atchFileGroupNoACQS);
					
					// 기업정보 등록 - T_CU_CTT_ACQS
					oproMainLoginFormDao.insertVendAcqsInfoList(parameterMap);
					
					
				}else{
					String[] DATA_CD_LIST = (String[]) DATA_CD;
					String[] VLD_STDE_LIST = (String[]) VLD_STDE;
					String[] VLD_ENDE_LIST = (String[]) VLD_ENDE;
					
					for(int i = 0; i < DATA_CD_LIST.length; i++) {
						if(DATA_CD_LIST != null && DATA_CD_LIST[i] != null) {
							parameterMap.put("P_DATA_CD", DATA_CD_LIST[i]);
						}
						if(DATA_CD_LIST != null && DATA_CD_LIST[i] != null) {
							parameterMap.put("P_DATA_CD", DATA_CD_LIST[i]);
							
							if(parameterMap.get("P_DATA_CD") != null && !"".equals(parameterMap.get("P_DATA_CD"))){
								FwkDataEntity P_CTT_NM = oproMainLoginFormDao.getCttNm(parameterMap);
								parameterMap.put("P_CTT_NM", P_CTT_NM.getString("P_CTT_NM"));
							}
						}
						
						if(VLD_STDE_LIST != null && VLD_STDE_LIST[i] != null) {
							//parameterMap.put("P_VLD_STDE", VLD_STDE_LIST[i]);
							parameterMap.put("P_VLD_STDE", FwkFormatUtil.formatDate(VLD_STDE_LIST[i], "yyyy-MM-dd", "yyyyMMdd"));
						}
						
						if(VLD_ENDE_LIST != null && VLD_ENDE_LIST[i] != null) {
							//parameterMap.put("P_VLD_ENDE", VLD_ENDE_LIST[i]);
							parameterMap.put("P_VLD_ENDE", FwkFormatUtil.formatDate(VLD_ENDE_LIST[i], "yyyy-MM-dd", "yyyyMMdd"));
						}
						
						parameterMap.put("P_CTT_ACQS_SN", i+1);
						parameterMap.put("P_FILE_GRP_NO", atchFileGroupNoACQS);
						
						// 기업정보 등록 - T_CU_CTT_ACQS
						oproMainLoginFormDao.insertVendAcqsInfoList(parameterMap);
						
					}
					
					if(vendAcqsFileParameterMap != null){
						
						List<Map<String, Object>> list = (List<Map<String, Object>>)vendAcqsFileParameterMap.get("list");
						List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
						
						Object P_ACQS_FILE_DOC_SECD =  parameterMap.get("P_ACQS_FILE_DOC_SECD");
						Object P_DATA_CD_NEW =  parameterMap.get("P_DATA_CD_NEW");
						
						if(P_ACQS_FILE_DOC_SECD instanceof String) {
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_TSK_VKEY1", P_DATA_CD_NEW.toString());
								map.put("P_FILE_DOC_SECD", P_ACQS_FILE_DOC_SECD.toString());
								newList.add(map);
							}
						}else if(P_ACQS_FILE_DOC_SECD instanceof String[]) {
							String[] tskKey1Array = (String[]) P_DATA_CD_NEW;
							String[] fileDocSecdArray = (String[]) P_ACQS_FILE_DOC_SECD;
							
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_TSK_VKEY1", tskKey1Array[i]);
								map.put("P_FILE_DOC_SECD", fileDocSecdArray[i]);
								newList.add(map);
							}
						}else if(P_ACQS_FILE_DOC_SECD instanceof ArrayList) {
							ArrayList<String> tskKey1List = (ArrayList<String>)P_DATA_CD_NEW;
							ArrayList<String> fileDocSecdList = (ArrayList<String>)P_ACQS_FILE_DOC_SECD;
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> map = list.get(i);
								map.put("P_TSK_VKEY1", tskKey1List.get(i));
								map.put("P_FILE_DOC_SECD", fileDocSecdList.get(i));
								newList.add(map);
							}
						}
						
						
						// 공통 파일정보 저장
						if("Y".equals(parameterMap.getString("P_ACQS_NEW"))){
							parameterMap.put("atchFileGroupNo", atchFileGroupNoACQS);
							parameterMap.put("fileList", newList);
							parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
							
							comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
						}
						
					}
					
					
				}
			}
			

			// 업체물품정보
			Object ITEM_NO = parameterMap.get("P_ITEM_NO");   // 품목번호
			Object ITEM_NM = parameterMap.get("P_ITEM_NM");   // 품목명
			Object LLF_CD = parameterMap.get("P_LLF_CD");   // 대분류코드
			Object MLF_CD = parameterMap.get("P_MLF_CD");   // 중분류코드
			Object SLF_CD = parameterMap.get("P_SLF_CD");   // 소분류코드
			Object DLF_CD = parameterMap.get("P_DLF_CD");   // 세분류코드
//			Object ITEM_UPRC = parameterMap.get("P_ITEM_UPRC");   // 단가
			
			if(ITEM_NO != null) {
				if(ITEM_NO instanceof String){
					parameterMap.put("P_ITEM_NO", parameterMap.getString("P_ITEM_NO"));
					parameterMap.put("P_ITEM_NM", parameterMap.getString("P_ITEM_NM"));
					parameterMap.put("P_LLF_CD", parameterMap.getString("P_LLF_CD"));
					parameterMap.put("P_MLF_CD", parameterMap.getString("P_MLF_CD"));
					parameterMap.put("P_SLF_CD", parameterMap.getString("P_SLF_CD"));
					parameterMap.put("P_DLF_CD", parameterMap.getString("P_DLF_CD"));
					
//					if(parameterMap.get("P_ITEM_UPRC") != null && !"".equals(parameterMap.get("P_ITEM_UPRC"))){
//						parameterMap.put("P_ITEM_UPRC", FwkStringUtil.deleteComma(FwkStringUtil.nvl(parameterMap.get("P_ITEM_UPRC").toString(), "0")));
//					}
					
					// 업체물품등록 등록 - T_CU_VEND_ITEM
					oproMainLoginFormDao.insertVendItemList(parameterMap);
					
				}else{
					
					String[] ITEM_NO_LIST = (String[]) ITEM_NO;
					String[] ITEM_NM_LIST = (String[]) ITEM_NM;
					String[] LLF_CD_LIST = (String[]) LLF_CD;
					String[] MLF_CD_LIST = (String[]) MLF_CD;
					String[] SLF_CD_LIST = (String[]) SLF_CD;
					String[] DLF_CD_LIST = (String[]) DLF_CD;
//					String[] ITEM_UPRC_LIST = (String[]) ITEM_UPRC;
					
					for(int i = 0; i < ITEM_NO_LIST.length; i++) {
						if(ITEM_NO_LIST != null && ITEM_NO_LIST[i] != null) {
							parameterMap.put("P_ITEM_NO", ITEM_NO_LIST[i]);
						}
						if(ITEM_NM_LIST != null && ITEM_NM_LIST[i] != null) {
							parameterMap.put("P_ITEM_NM", ITEM_NM_LIST[i]);
						}
						if(LLF_CD_LIST != null && LLF_CD_LIST[i] != null) {
							parameterMap.put("P_LLF_CD", LLF_CD_LIST[i]);
						}
						if(MLF_CD_LIST != null && MLF_CD_LIST[i] != null) {
							parameterMap.put("P_MLF_CD", MLF_CD_LIST[i]);
						}
						if(SLF_CD_LIST != null && SLF_CD_LIST[i] != null) {
							parameterMap.put("P_SLF_CD", SLF_CD_LIST[i]);
						}
						if(DLF_CD_LIST != null && DLF_CD_LIST[i] != null) {
							parameterMap.put("P_DLF_CD", DLF_CD_LIST[i]);
						}
//						if(ITEM_UPRC_LIST != null && ITEM_UPRC_LIST[i] != null) {
//							parameterMap.put("P_ITEM_UPRC", FwkStringUtil.deleteComma(FwkStringUtil.nvl(ITEM_UPRC_LIST[i], "0")));
//						}

						// 업체물품등록 등록 - T_CU_VEND_ITEM
						oproMainLoginFormDao.insertVendItemList(parameterMap);
						
					}
					
				}
			}
			
			
			/***** 필수 첨부파일 START *****/

			parameterMap.put("P_FILE_GRP_NO", baseFileGrpNo);
			parameterMap.put("P_RED_FILE_GRP_NO", baseFileGrpNo);
			
			// 필수첨부파일 삭제
			if(vendRedFileParameterMap != null){
				parameterMap.put("P_VEND_DOC_FSCD", "DC01");
				String atchFileGroupNo = "";
				
				atchFileGroupNo = parameterMap.getString("P_RED_FILE_GRP_NO");	
				List<Map<String, Object>> list = (List<Map<String, Object>>)vendRedFileParameterMap.get("list");
				List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> amtList = new ArrayList<Map<String,Object>>();
				
				Object P_RED_FILE_DOC_SECD =  parameterMap.get("P_RED_FILE_DOC_SECD");
				Object P_RED_FILE_DOC_NM =  parameterMap.get("P_RED_FILE_DOC_NM");
				
				if(P_RED_FILE_DOC_SECD instanceof String) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_SECD", P_RED_FILE_DOC_SECD.toString());
						map.put("P_FILE_DOC_NM", P_RED_FILE_DOC_NM.toString());
						newList.add(map);
						if("DC01".equals(P_RED_FILE_DOC_SECD.toString())){
							amtList.add(map);
						}
					}
				}
				
				// 공통 파일정보 저장
				if(amtList != null && amtList.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("fileList", amtList);
					parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
					comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
					
					parameterMap.put("P_VEND_DOC_FSCD", "DC01");   // 사업자등록증

					// 업체첨부문서 등록 - T_CU_FILE_MST
					oproMainLoginFormDao.insertVendAtchDocList(parameterMap);
				}
				
			}
			
			/***** 필수 첨부파일 END *****/
			
			/***** 기타 첨부파일 START *****/
			if(vendFileParameterMap != null){
				// 기타첨부파일 삭제
				parameterMap.put("P_VEND_DOC_FSCD", "DC99");

				String atchFileGroupNo = vendFileParameterMap.getString("atchFileGroupNo");
				
				List<Map<String, Object>> list = (List<Map<String, Object>>)vendFileParameterMap.get("list");
				List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
				
				Object P_ETC_FILE_DOC_NM =  parameterMap.get("P_ETC_FILE_DOC_NM");
				Object P_ETC_FILE_DOC_SECD =  parameterMap.get("P_ETC_FILE_DOC_SECD");
				
				
				if(P_ETC_FILE_DOC_NM instanceof String) {
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", P_ETC_FILE_DOC_NM.toString());
						map.put("P_FILE_DOC_SECD", P_ETC_FILE_DOC_SECD.toString());
						newList.add(map);
					}
				}else if(P_ETC_FILE_DOC_NM instanceof String[]) {
					String[] fileDocNmArray = (String[])P_ETC_FILE_DOC_NM;
					String[] fileDocSecdArray = (String[])P_ETC_FILE_DOC_SECD;
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", fileDocNmArray[i]);
						map.put("P_FILE_DOC_SECD", fileDocSecdArray[i]);
						newList.add(map);
					}
				}else if(P_ETC_FILE_DOC_NM instanceof ArrayList) {
					ArrayList<String> fileDocNmList = (ArrayList<String>)P_ETC_FILE_DOC_NM;
					ArrayList<String> fileDocSecdList = (ArrayList<String>)P_ETC_FILE_DOC_SECD;
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
						map.put("P_FILE_DOC_SECD", fileDocSecdList.get(i));
						newList.add(map);
					}
				}
				
				// 공통 파일정보 저장
				parameterMap.put("atchFileGroupNo", atchFileGroupNo);
				parameterMap.put("fileList", newList);
				parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
				comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
				

				// 첨부파일 저장
				parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
				parameterMap.put("P_VEND_DOC_FSCD", "DC99");
				
				// 업체첨부문서 등록 - T_CU_FILE_MST
				oproMainLoginFormDao.insertVendAtchDocList(parameterMap);
			}
			/***** 기타 첨부파일 END *****/
			
			// 업체승인이력
			parameterMap.put("P_VEND_APPR_STCD", "A");
			
			// 업체승인이력 - T_CU_APPR_HIST
			oproMainLoginFormDao.insertApprVendProgHist(parameterMap);
			
			trans.put("P_VEND_REG_NO", parameterMap.get("P_VEND_REG_NO"));
			trans.put("mberRegistCompt", "compt");
			trans.put("loginId", parameterMap.get("P_LOGIN_ID"));
			
		} catch (Exception e) { 
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== OproMainLoginFormServiceImpl.joinRegist Error == ");
		}
		
		return trans;
		
	}
	
	@Override
	public FwkTransferObject loginByIdPw_dev(FwkParameterMap parameterMap) {
		FwkParameterMap retObjectMap = new FwkParameterMap();
		String resultMessage = "";
		int resultCode = 0; 

		// 사용자 조회
		FwkDataEntity entityById = oproMainLoginFormDao.selectEntrpsMberInqireById(parameterMap);

		
		if(entityById == null){
			//계정이 없는 경우
			resultCode = -9999;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
		}else if( entityById != null ){ 		
			// 비밀번호 해시값 변환
			//String PW = "ew"+parameterMap.getString("P_LOGIN_PASSWORD")+"soft";
			String PW = parameterMap.getString("P_PWD");
			//parameterMap.put("P_PWD", SDBCryptoUtil.encryptSha512(PW));
			parameterMap.put("P_PWD", SDBCryptoUtil.encryptSha256(PW));
			
		}
		
		//아이디, 패스워드로 사용자 조회
		//FwkDataEntity entityByIdAndPw = oproMainLoginFormDao.selectEntrpsMberInqireByIdAndPassword(parameterMap);
		
		FwkTransferObject trans = new FwkTransferObject();
		/*
		if(resultCode != -9999 && entityByIdAndPw == null){
			//비밀번호가 틀린경우
			resultCode = -44;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
		}*/
		
		retObjectMap.put(OproMainLoginFormService.RESULT_CODE, String.valueOf(resultCode));
		retObjectMap.put(OproMainLoginFormService.RESULT_MESSAGE, resultMessage);
		
		retObjectMap.put(OproMainLoginFormService.RESULT_DATA, entityById);
		
		/*if(entityByIdAndPw == null) { 
			retObjectMap.put(OproMainLoginFormService.RESULT_DATA, entityById);
		}else{
			entityByIdAndPw.put("LOGIN_ID", entityByIdAndPw.get("USR_ID"));
			entityByIdAndPw.put("LOGIN_GBN", "IDPW");
			retObjectMap.put(OproMainLoginFormService.RESULT_DATA, entityByIdAndPw);
		}
		
		if(resultCode == 0){		//로그인 성공!!!
			super.getCurrentHttpServletRequest().getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, entityByIdAndPw);
		}*/		

		trans.put(OproMainLoginFormService.LOGIN_RESULT, retObjectMap);

		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 회원가입 한 업체인지 체크 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.opro.main.service.impl.OproMainLoginFormServiceImpl.java
	 * @Method : mberSbscrbManage
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 12. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject joinEnpaCheck(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();

		String msg = "";
		
		String BIZRNO = parameterMap.getString("P_BIZRNO1") + parameterMap.getString("P_BIZRNO2") + parameterMap.getString("P_BIZRNO3");
		parameterMap.put("P_BIZRNO", BIZRNO);

		FwkDataEntity mber = oproMainLoginFormDao.joinEnpaCheck(parameterMap);
		
		if(mber!=null){
			msg = "join";
		}else{
			msg = "Nojoin";
			trans.put("P_BIZRNO_CHECK", "Y");   // 사업자등록번호 중복확인 체크 여부
		}
	
		trans.put("joinCheck",CmmnUtil.cleanXSS( msg )); 
		
		trans.put("P_BIZRNO1", CmmnUtil.cleanXSS(parameterMap.getString("P_BIZRNO1")));
		trans.put("P_BIZRNO2", CmmnUtil.cleanXSS(parameterMap.getString("P_BIZRNO2")));
		trans.put("P_BIZRNO3", CmmnUtil.cleanXSS(parameterMap.getString("P_BIZRNO3")));
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : ID PW 로그인 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.opro.main.service.impl.OproMainLoginFormServiceImpl.java
	 * @Method : loginByIdPw
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject loginByIdPw(FwkParameterMap parameterMap) {
		FwkParameterMap retObjectMap = new FwkParameterMap();
		String resultMessage = "";
		int resultCode = 0;
		FwkDataEntity entityById = null;

		
		
		// 사용자 조회
		entityById = oproMainLoginFormDao.selectEntrpsMberInqireById(parameterMap);	
		
		if(entityById == null){
			//계정이 없는 경우
			resultCode = -9999;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
		}else if( entityById != null ){ 		
			/*OTP 관련 시작*/
			String serverIp = FwkMessageUtil.getMessage("OTP.SERVER.IP");
			String port = FwkMessageUtil.getMessage("OTP.SERVER.PORT");
			String sharedSecret = FwkMessageUtil.getMessage("OTP.SERVER.SECRET");
			String timeout = "3";
			String success = "";
			
			String result = null; //응답 문자열
			String rCode = ""; //응답 코드
			int authFailCnt = 0; //연속오류횟수
			String rMsg = ""; //응답 메시지
			String info = null;
			String nasid = FwkMessageUtil.getMessage("OTP.SERVER.NASID");
			String username = entityById.get("EMPL_NO") + "";
			String otptoken = parameterMap.getString("P_OTP_NO");
			String retryCnt = "";

			//OTP 인증요청
		   	result = GptwrAuthService.auth(serverIp, port, sharedSecret, username, otptoken, "1", timeout, nasid);

			//처리결과는 "응답코드#연속오류횟수#응답메시지" 형식으로 반환
			String[] tmp = result.split("#");  

			//처리결과 문자열을 파싱함.
			if ( tmp.length >= 3)
			{
				rCode = tmp[0];  
				authFailCnt = Integer.parseInt(tmp[1]);
				rMsg  = tmp[2];
			}

			if ( "0".equals(rCode) ){
				success = "success";
				
				/*
				   0 : ID / PWD 일치
				  -1 : 계정이 잠겨있음
				  -2 : 사용자 ID 또는 비밀번호 불일치
				  -3 : 사용자 ID 또는 비밀번호 불일치가 4번 발생(한번더틀리면 계정이 잠긴다는 알림표시용 구분)
				  -4 : 사용자 ID 또는 비밀번호 불일치가 5번 발생하여 계정이 잠김( KTO-NET 접속하여 초기화 알림용 구분) 
				  */
				//ID,PW 확인
				parameterMap.put("P_RETURN", "");
				oproMainLoginFormDao.selectPasswordChk(parameterMap);
				String chkNum = parameterMap.get("P_RETURN")+"";
				
				if(chkNum.equals("0")){
					resultCode = 0;
				}else if(chkNum.equals("-1")){
					resultCode = -1;
					resultMessage = "계정이 잠겨있음";
				}else if(chkNum.equals("-2")){
					resultCode = -2;
					resultMessage = "사용자 ID 또는 비밀번호 불일치";
				}else if(chkNum.equals("-3")){
					resultCode = -3;
					resultMessage = "사용자 ID 또는 비밀번호 불일치가 4번 발생";
				}else if(chkNum.equals("-4")){
					resultCode = -4;
					resultMessage = "사용자 ID 또는 비밀번호 불일치가 5번 발생하여 계정이 잠김";
				}else{
					resultCode = -9999;
					resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
				}
				
			}else{
				success = "failed";
				//계정이 없는 경우
				resultCode = -9999;
				resultMessage = rCode;
			}
			info = GptwrAuthService.info();
			
			/*OTP 관련 끝*/
						
		}
		
		
		
		
		//아이디, 패스워드로 사용자 조회
		//FwkDataEntity entityByIdAndPw = oproMainLoginFormDao.selectEntrpsMberInqireByIdAndPassword(parameterMap);
		
		FwkTransferObject trans = new FwkTransferObject();
		/*
		if(resultCode != -9999 && entityByIdAndPw == null){
			//비밀번호가 틀린경우
			resultCode = -44;
			resultMessage = "계정이 없거나 아이디와 비밀번호가 맞지 않습니다.";
		}*/
		
		retObjectMap.put(OproMainLoginFormService.RESULT_CODE, String.valueOf(resultCode));
		retObjectMap.put(OproMainLoginFormService.RESULT_MESSAGE, resultMessage);
		
		retObjectMap.put(OproMainLoginFormService.RESULT_DATA, entityById);
		
		/*if(entityByIdAndPw == null) { 
			retObjectMap.put(OproMainLoginFormService.RESULT_DATA, entityById);
		}else{
			entityByIdAndPw.put("LOGIN_ID", entityByIdAndPw.get("USR_ID"));
			entityByIdAndPw.put("LOGIN_GBN", "IDPW");
			retObjectMap.put(OproMainLoginFormService.RESULT_DATA, entityByIdAndPw);
		}
		
		if(resultCode == 0){		//로그인 성공!!!
			super.getCurrentHttpServletRequest().getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, entityByIdAndPw);
		}*/		

		trans.put(OproMainLoginFormService.LOGIN_RESULT, retObjectMap);

		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인전 메인페이지 게시물 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMainInfoList
	 * @date : 2018. 04. 03.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 04. 03.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */	
	public FwkTransferObject getMainInfoList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(OproNotiGnrlService.NOTI_GNRL_LIST, oproNotiGnrlDao.notiGnrlListWithPgng(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메인페이지 카운트 받아오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMainInfoCnt
	 * @date : 2018. 04. 03.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 04. 03.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */	
	public FwkTransferObject getMainInfoCnt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		/****************************************  계약설계 및 대금지급 진행현황START ****************************************/
			if(parameterMap.get("P_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_BEGIN_DT_S"))){ // 요청일자 시작
				parameterMap.put("P_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
			} 
			
			if(parameterMap.get("P_END_DT_S") != null && !"".equals(parameterMap.get("P_END_DT_S"))){ // 요청일자 종료
				parameterMap.put("P_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
			} 
		/**************************************** 1. 계약설계 _ 검토요청상태  ****************************************/
		//검토요청 C006
		trans.put("myContReqMainCnt", oproMainLoginFormDao.selectMyContReqMainTotcnt(parameterMap));	
		/**************************************** 2. 계약관리 _ 검토요청상태  ****************************************/
		//검토요청 C018
		trans.put("myContPrcnMainCnt", oproMainLoginFormDao.selectMyContPrcnMainTotcnt(parameterMap));	
		/**************************************** ****************************************/
		//서명요청  C021
		/**************************************** 3. 계약 _ 서명요청상태  ****************************************/
		trans.put("myContSignMainCnt", oproMainLoginFormDao.selectMyContSignMainTotcnt(parameterMap));	

		//대금지급요청 PAY_STCD P003
		/**************************************** 4. 대금 _ 지급요청상태  ****************************************/
		trans.put("myPayReqMainCnt", oproMainLoginFormDao.selectMyPayReqMainTotcnt(parameterMap));	
		return trans;
	}
	


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.main.service.OproMainLoginFormService#toDoList(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject toDoList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		List<FwkDataEntity> toDoList = oproMainLoginFormDao.selectToDoList(parameterMap);
		
		trans.put(OproMainLoginFormService.TO_DO_LIST, toDoList);
		
		if(toDoList.size() > 0){
			trans.put("toDoListTotcnt", toDoList.size());
		}else{
			trans.put("toDoListTotcnt", 0);
		}
		
		return trans;
	}

	@Override
	public FwkTransferObject estmAnncList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		List<FwkDataEntity> estmAnncList = oproMainLoginFormDao.selectEstmAnncMainList(parameterMap);
		
		trans.put("estmAnncList", estmAnncList);
		
		if(estmAnncList.size() > 0){
			trans.put("estmAnncListTotCnt", estmAnncList.size());
		}else{
			trans.put("estmAnncListTotCnt", 0);
		}
		
		return trans;
	}


}
