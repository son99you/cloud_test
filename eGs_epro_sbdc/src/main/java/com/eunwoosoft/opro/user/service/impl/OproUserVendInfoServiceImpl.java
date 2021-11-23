package com.eunwoosoft.opro.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.fol.util.SDBCryptoUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.user.dao.OproUserVendInfoDao;
import com.eunwoosoft.opro.user.service.OproUserVendInfoService;

@Service("oproUserVendInfoService")
@Transactional
public class OproUserVendInfoServiceImpl extends AbstractFwkService implements OproUserVendInfoService {
	private static final Logger LOG = LoggerFactory.getLogger(OproUserVendInfoServiceImpl.class);
	
	@Resource(name="oproUserVendInfoDao")
	private OproUserVendInfoDao oproUserVendInfoDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.user.service.OproUserVendInfoService#vendInfoDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject vendInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_VEND_REG_NO", session.get("USR_ID"));
		
		// 업체마스터
		FwkDataEntity vendInfoDetail = oproUserVendInfoDao.selectVendInfoDetail(parameterMap);
		trans.put(OproUserVendInfoService.VEND_INFO_DETAIL, vendInfoDetail);
		
		// 업체사용자
		trans.put(OproUserVendInfoService.VEND_CHRGR_INFO_DETAIL, oproUserVendInfoDao.selectVendChrgrInfoDetail(parameterMap));
		
		// 계좌정보
		//List<FwkDataEntity> vendAcctInfoList = oproUserVendInfoDao.selectVendAcctInfoList(parameterMap);
		FwkDataEntity vendAcctInfo = oproUserVendInfoDao.selectVendAcctInfo(parameterMap);
		trans.put(OproUserVendInfoService.VEND_ACCT_INFO, vendAcctInfo);
		
		// 계좌정보 첨부파일
		/*FwkDataEntity acctFileGrpNoInqire =  oproUserVendInfoDao.selectVendAcctFileGrpNoInqire(parameterMap);
		if(acctFileGrpNoInqire != null){
			String fileGrpNo = acctFileGrpNoInqire.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO_ACC", fileGrpNo);
			trans.put("P_ACC_FILE_GRP_NO", fileGrpNo);
		}*/
		
		if(vendAcctInfo != null){
			parameterMap.put("P_FILE_GRP_NO", vendAcctInfo.getString("FILE_GRP_NO"));
			parameterMap.put("P_FILE_GRP_NO_ACC", vendAcctInfo.getString("FILE_GRP_NO"));
			trans.put("vendAcctDocList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			trans.put("P_ACC_FILE_GRP_NO", vendAcctInfo.getString("FILE_GRP_NO"));
		}
		
		//업체 반려 내역
		if(vendInfoDetail.get("VEND_STCD") != null && vendInfoDetail.get("VEND_STCD").equals("D")) {
			trans.put("vendRtnProgList", oproUserVendInfoDao.selectVendRtnProgList(parameterMap));
		}
		
		// 기업정보
		List<FwkDataEntity> vendAcqsInfoList = oproUserVendInfoDao.selectVendAcqsInfoList(parameterMap);
		trans.put(OproUserVendInfoService.VEND_ACQS_INFO_LIST, vendAcqsInfoList);
		
		// 기업정보 첨부파일
		FwkDataEntity acqsFileGrpNoInqire =  oproUserVendInfoDao.selectVendAcqsFileGrpNoInqire(parameterMap);
		if(acqsFileGrpNoInqire != null){
			String fileGrpNo = acqsFileGrpNoInqire.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO_ACQS", fileGrpNo);
			trans.put("P_ACQS_FILE_GRP_NO", fileGrpNo);
		}
		
		// 업체물품
		List<FwkDataEntity> vendItemList = oproUserVendInfoDao.selectVendItemList(parameterMap);
		trans.put(OproUserVendInfoService.VEND_ITEM_LIST, vendItemList);
		
		
		// 필수첨부파일
		parameterMap.put("P_VEND_DOC_FSCD", "DC01");
		parameterMap.put("P_FILE_DOC_SECD", "DC01");
		parameterMap.put("P_FILE_GRP_NO", "");
		
		FwkDataEntity redFileDataEntity = oproUserVendInfoDao.selectVendAtchDocList(parameterMap);
		trans.put(OproUserVendInfoService.VEND_ATCH_DOC_LIST+"Red", redFileDataEntity);
		
		if(redFileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", redFileDataEntity.getString("FILE_GRP_NO"));
			trans.put(OproUserVendInfoService.VEND_ATCH_DOC_LIST+"Red", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			trans.put("P_RED_FILE_GRP_NO", redFileDataEntity.getString("FILE_GRP_NO"));
		}
		
		// 기타첨부파일
		parameterMap.put("P_VEND_DOC_FSCD", "DC99");
		parameterMap.put("P_FILE_DOC_SECD", "DC99");
		parameterMap.put("P_FILE_GRP_NO", "");
		FwkDataEntity fileDataEntity = oproUserVendInfoDao.selectVendAtchDocList(parameterMap);
		
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(OproUserVendInfoService.VEND_ATCH_DOC_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			trans.put("P_ETC_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
		}

		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.user.service.OproUserVendInfoService#vendInfoUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject vendInfoUpdt(final FwkParameterMap parameterMap) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "vend";
		FwkParameterMap vendAcctFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_ACCT_FILE", contextPath);   // 통장사본
		FwkParameterMap vendAcqsFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_ACQS_FILE", contextPath);   // 기업정보
		FwkParameterMap vendRedFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_RED_VEND_FILE", contextPath);   // 필수첨부파일
		FwkParameterMap vendFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_VEND_FILE", contextPath);   // 기타첨부파일
		String baseFileGrpNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
			parameterMap.put("P_VEND_REG_NO", session.get("USR_ID"));
			
			/*
			 * 원본데이터와 비교하여 대조 후 상태변경 2020-03-24
			 */
			parameterMap.put("P_DEL_AT", "N");
			parameterMap.put("P_VEND_STCD", "B");   // 업체수정
			//업체마스터
			FwkDataEntity vendInfoDetail = oproUserVendInfoDao.selectVendInfoDetail(parameterMap);
			if(vendInfoDetail.get("VEND_STCD").equals("E")) {
				parameterMap.put("P_VEND_STCD", "E");   // 업체수정	
			}
			//계좌정보		
			FwkDataEntity vendAcctInfo = oproUserVendInfoDao.selectVendAcctInfo(parameterMap);
			
			StringBuilder pModRmk = new StringBuilder();
			if(vendInfoDetail.get("MOD_RMK") != null && !vendInfoDetail.get("MOD_RMK").equals("")) {
				pModRmk.append(vendInfoDetail.get("MOD_RMK"));
			}
			if(!vendInfoDetail.get("VEND_NM").equals(parameterMap.get("P_VEND_NM"))) {				//업체명 변경
				pModRmk.append((pModRmk.length() == 0)?"업체명 변경":", 업체명 변경"); parameterMap.put("P_VEND_STCD", "E");
			}
			if(!vendInfoDetail.get("RPRS_NM").equals(parameterMap.get("P_RPRS_NM"))) {				//대표자 변경
				pModRmk.append((pModRmk.length() == 0)?"대표자명 변경":", 대표자명 변경"); parameterMap.put("P_VEND_STCD", "E");
			}
			if(vendAcctInfo == null) {
				pModRmk.append((pModRmk.length() == 0)?"은행정보 입력":", 은행정보 입력"); parameterMap.put("P_VEND_STCD", "E");
			}else {
				if((vendAcctInfo != null && vendAcctInfo.get("BKCD") == null) || (vendAcctInfo != null && !vendAcctInfo.get("BKCD").equals(parameterMap.get("P_BKCD")))) {				//은행 변경 변경
					pModRmk.append((pModRmk.length() == 0)?"은행명 변경":", 은행명 변경"); parameterMap.put("P_VEND_STCD", "E");
				}
				if((vendAcctInfo != null && vendAcctInfo.get("ACNO") == null) || (vendAcctInfo != null && !vendAcctInfo.get("ACNO").equals(parameterMap.get("P_ACNO")))) {				//계좌번호 변경 변경
					pModRmk.append((pModRmk.length() == 0)?"계좌번호 변경":", 계좌번호 변경"); parameterMap.put("P_VEND_STCD", "E");
				}
				if((vendAcctInfo != null && vendAcctInfo.get("DPSO") == null) || (vendAcctInfo != null && !vendAcctInfo.get("DPSO").equals(parameterMap.get("P_DPSO")))) {				//예금주 변경 변경
					pModRmk.append((pModRmk.length() == 0)?"예금주 변경":", 예금주 변경"); parameterMap.put("P_VEND_STCD", "E");
				}
				if(vendAcctFileParameterMap != null){				//계좌정보 파일 변경
					pModRmk.append((pModRmk.length() == 0)?"계좌정보파일 변경":", 계좌정보파일 변경"); parameterMap.put("P_VEND_STCD", "E");
				}
				if(vendRedFileParameterMap != null){				//사업자등록증 변경
					pModRmk.append((pModRmk.length() == 0)?"사업자등록증 변경":", 사업자등록증 변경"); parameterMap.put("P_VEND_STCD", "E");
				}				
			}

			parameterMap.put("P_MOD_RMK", pModRmk.toString());
			

			parameterMap.put("P_CPTL_AMT", FwkStringUtil.deleteComma(FwkStringUtil.nvl(parameterMap.get("P_CPTL_AMT").toString(), "0")));   // 자본금액
			
			if(parameterMap.get("P_ESTB_DE") != null && !"".equals(parameterMap.get("P_ESTB_DE"))){ // 설립일자
				parameterMap.put("P_ESTB_DE", FwkFormatUtil.formatDate(parameterMap.get("P_ESTB_DE").toString(), "yyyy-MM-dd", "yyyyMMdd"));
			}
			
			// 업체마스터
			oproUserVendInfoDao.updateVendInfo(parameterMap);   // T_CU_MST UPDATE

			// 업체사용자
			parameterMap.put("P_EMAL_ADDR", parameterMap.get("P_EMAL_ADDR_USER"));
			parameterMap.put("P_TEL_NO", parameterMap.get("P_TEL_NO_USER"));
			oproUserVendInfoDao.updateVendChrgrInfo(parameterMap);

			
			// 업체계좌
			oproUserVendInfoDao.deleteVendAcctInfoList(parameterMap);
			
			String atchFileGroupNoACC = "";
			
			if("".equals(parameterMap.getString("P_ACC_FILE_GRP_NO"))){
				atchFileGroupNoACC = vendAcctFileParameterMap.getString("atchFileGroupNo");
				parameterMap.put("P_ACC_NEW", "Y");
			}else{
				atchFileGroupNoACC = parameterMap.getString("P_ACC_FILE_GRP_NO");	
				parameterMap.put("P_ACC_NEW", "N");
			}
			
			parameterMap.put("P_ACC_SN", "1");   // 계좌일련번호
			parameterMap.put("P_ORD_SN", "1");   // 정렬일련번호
			
			if(parameterMap.get("P_BKCD") != null && !"".equals(parameterMap.get("P_BKCD"))){
				FwkDataEntity P_BKNM = oproUserVendInfoDao.getBknm(parameterMap);
				parameterMap.put("P_BKNM", P_BKNM.getString("P_BKNM"));
			}
			
			System.err.println("vendAcctFileParameterMap ==> " + vendAcctFileParameterMap);
			
			if(vendAcctFileParameterMap != null){
				
				List<Map<String, Object>> list = (List<Map<String, Object>>)vendAcctFileParameterMap.get("list");
				List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
				
				
				Object P_FILE_DOC_NM =  parameterMap.get("P_ACCT_FILE_DOC_NM");
				Object P_FILE_DOC_SECD =  parameterMap.get("P_ACCT_FILE_DOC_SECD");
				//Object P_TSK_VKEY1 =  parameterMap.get("P_ACNO_NEW");
				
				
				if(P_FILE_DOC_NM instanceof String) {
					System.err.println("11111");
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						map.put("P_FILE_DOC_NM", P_FILE_DOC_NM.toString());
						map.put("P_FILE_DOC_SECD", P_FILE_DOC_SECD.toString());
						//map.put("P_TSK_VKEY1", P_TSK_VKEY1.toString());
						newList.add(map);
					}
				}else if(P_FILE_DOC_NM instanceof String[]) {
					System.err.println("22222");
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
					System.err.println("33333");
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
			
			oproUserVendInfoDao.insertVendAcctInfoList(parameterMap);
			
			
			if(!"".equals(parameterMap.getString("P_ACC_DEL_SN"))){
				String delFileSn = parameterMap.getString("P_ACC_DEL_SN");
				if(!"".equals(delFileSn)){
					String[] snArray = delFileSn.split(",");
					List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
					
					for (int i = 0; i < snArray.length; i++) {
						if(!"".equals(snArray[i])){
							FwkParameterMap delMap = new FwkParameterMap();
							delMap.put("P_FILE_SN", snArray[i]);
							delList.add(delMap);
						}
					}
					parameterMap.put("fileList", delList);
					parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_ACC_FILE_GRP_NO"));
					comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
				}
			}
			
			/*Object ACNO = parameterMap.get("P_ACNO");   // 계좌번호
			Object BKCD = parameterMap.get("P_BKCD");   // 은행코드
			Object DPSO = parameterMap.get("P_DPSO");   // 예금주
			Object BNK_BRNC_NM = parameterMap.get("P_BNK_BRNC_NM");   // 은행지점명
			
			if(ACNO != null) {
				
				if("".equals(parameterMap.getString("P_ACC_FILE_GRP_NO"))){
					atchFileGroupNoACC = vendAcctFileParameterMap.getString("atchFileGroupNo");
					parameterMap.put("P_ACC_NEW", "Y");
				}else{
					atchFileGroupNoACC = parameterMap.getString("P_ACC_FILE_GRP_NO");	
					parameterMap.put("P_ACC_NEW", "N");
				}
				
				
				if(ACNO instanceof String){
					parameterMap.put("P_ACC_SN", "1");   // 계좌일련번호
					parameterMap.put("P_ORD_SN", "1");   // 정렬일련번호
					parameterMap.put("P_ACNO", parameterMap.getString("P_ACNO"));
					parameterMap.put("P_BKCD", parameterMap.getString("P_BKCD"));
					
					if(parameterMap.get("P_BKCD") != null && !"".equals(parameterMap.get("P_BKCD"))){
						FwkDataEntity P_BKNM = oproUserVendInfoDao.getBknm(parameterMap);
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
					oproUserVendInfoDao.insertVendAcctInfoList(parameterMap);
					
					
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
								FwkDataEntity P_BKNM = oproUserVendInfoDao.getBknm(parameterMap);
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
						
						oproUserVendInfoDao.insertVendAcctInfoList(parameterMap);
						
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
						}else{
							
							comAtmaAtchFileService.fileListAddRegist(atchFileGroupNoACC, newList, parameterMap);
						}
						
					}else{
						// 첨부파일 추가 등록 없이 삭제만 하였을 경우
						if(!"".equals(parameterMap.getString("P_ACC_DEL_SN"))){
							String delFileSn = parameterMap.getString("P_ACC_DEL_SN");
							if(!"".equals(delFileSn)){
								String[] snArray = delFileSn.split(",");
								List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
								
								for (int i = 0; i < snArray.length; i++) {
									if(!"".equals(snArray[i])){
										FwkParameterMap delMap = new FwkParameterMap();
										delMap.put("P_FILE_SN", snArray[i]);
										delList.add(delMap);
									}
								}
								parameterMap.put("fileList", delList);
								parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_ACC_FILE_GRP_NO"));
								comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
							}
						}
						
					}
					
				}
			}*/
			
			
			if(!"".equals(parameterMap.getString("P_ACC_DEL_SN"))){
				String delFileSn = parameterMap.getString("P_ACC_DEL_SN");
				if(!"".equals(delFileSn)){
					String[] snArray = delFileSn.split(",");
					List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
					
					for (int i = 0; i < snArray.length; i++) {
						if(!"".equals(snArray[i])){
							FwkParameterMap delMap = new FwkParameterMap();
							delMap.put("P_FILE_SN", snArray[i]);
							delList.add(delMap);
						}
					}
					parameterMap.put("fileList", delList);
					parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_ACC_FILE_GRP_NO"));
					comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
				}
			}
			
			

			// 기업정보
			oproUserVendInfoDao.deleteVendAcqsInfoList(parameterMap);
			
			String atchFileGroupNoACQS = "";
			
			Object DATA_CD = parameterMap.get("P_DATA_CD");   // 자료코드
			Object VLD_STDE = parameterMap.get("P_VLD_STDE");   // 유효시작일자
			Object VLD_ENDE = parameterMap.get("P_VLD_ENDE");   // 유효종료일자
			
			if(DATA_CD != null) {
				
				if("".equals(parameterMap.getString("P_ACQS_FILE_GRP_NO"))){
					atchFileGroupNoACQS = vendAcqsFileParameterMap.getString("atchFileGroupNo");
					parameterMap.put("P_ACQS_NEW", "Y");
				}else{
					atchFileGroupNoACQS = parameterMap.getString("P_ACQS_FILE_GRP_NO");	
					parameterMap.put("P_ACQS_NEW", "N");
				}
				
					
				if(DATA_CD instanceof String){
					parameterMap.put("P_CTT_ACQS_SN", "1");   // 인증취득일련번호
					parameterMap.put("P_DATA_CD", parameterMap.getString("P_DATA_CD"));
					
					if(parameterMap.get("P_DATA_CD") != null && !"".equals(parameterMap.get("P_DATA_CD"))){
						FwkDataEntity P_CTT_NM = oproUserVendInfoDao.getCttNm(parameterMap);
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
					
					
					oproUserVendInfoDao.insertVendAcqsInfoList(parameterMap);
					
					
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
								FwkDataEntity P_CTT_NM = oproUserVendInfoDao.getCttNm(parameterMap);
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
						
						oproUserVendInfoDao.insertVendAcqsInfoList(parameterMap);
						
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
						}else{
							
							comAtmaAtchFileService.fileListAddRegist(atchFileGroupNoACQS, newList, parameterMap);
						}
						
					}else{
						
						// 첨부파일 추가 등록 없이 삭제만 하였을 경우
						if(!"".equals(parameterMap.getString("P_ACQS_DEL_SN"))){
							String delFileSn = parameterMap.getString("P_ACQS_DEL_SN");
							if(!"".equals(delFileSn)){
								String[] snArray = delFileSn.split(",");
								List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
								
								for (int i = 0; i < snArray.length; i++) {
									if(!"".equals(snArray[i])){
										FwkParameterMap delMap = new FwkParameterMap();
										delMap.put("P_FILE_SN", snArray[i]);
										delList.add(delMap);
									}
								}
								parameterMap.put("fileList", delList);
								parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_ACQS_FILE_GRP_NO"));
								
								comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
							}
						}
						
						
					}
					
					
				}
			}
			
			
			
			if(!"".equals(parameterMap.getString("P_ACQS_DEL_SN"))){
				String delFileSn = parameterMap.getString("P_ACQS_DEL_SN");
				if(!"".equals(delFileSn)){
					String[] snArray = delFileSn.split(",");
					List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
					
					for (int i = 0; i < snArray.length; i++) {
						if(!"".equals(snArray[i])){
							FwkParameterMap delMap = new FwkParameterMap();
							delMap.put("P_FILE_SN", snArray[i]);
							delList.add(delMap);
						}
					}
					parameterMap.put("fileList", delList);
					parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_ACQS_FILE_GRP_NO"));
					
					comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
				}
			}
			
			// 업체물품정보
			oproUserVendInfoDao.deleteVendItemList(parameterMap);
			
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
					
					oproUserVendInfoDao.insertVendItemList(parameterMap);
					
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

						oproUserVendInfoDao.insertVendItemList(parameterMap);
						
					}
					
				}
			}
			
			
			
			/***** 필수 첨부파일 START *****/
			if("".equals(parameterMap.getString("P_RED_FILE_GRP_NO"))){
				parameterMap.put("P_FILE_GRP_NO", baseFileGrpNo);
				parameterMap.put("P_RED_FILE_GRP_NO", baseFileGrpNo);
			}else{
				parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_RED_FILE_GRP_NO"));
			}
			
			// 필수첨부파일 삭제
			if(vendRedFileParameterMap != null){
				parameterMap.put("P_VEND_DOC_FSCD", "DC01");
				oproUserVendInfoDao.deleteVendAtchDocList(parameterMap);
				
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
					oproUserVendInfoDao.insertVendAtchDocList(parameterMap);
				}
				
			}
			
			
			if(!"".equals(parameterMap.getString("P_RED_DEL_SN"))){
				String delFileSn = parameterMap.getString("P_RED_DEL_SN");
				if(!"".equals(delFileSn)){
					String[] snArray = delFileSn.split(",");
					List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
					for (int i = 0; i < snArray.length; i++) {
						if(!"".equals(snArray[i])){
							FwkParameterMap delMap = new FwkParameterMap();
							delMap.put("P_FILE_SN", snArray[i]);
							delList.add(delMap);
						}
					}
					parameterMap.put("fileList", delList);
					comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
				}
			}
			System.err.println("==================== 필수 첨부파일 END ====================");
			/***** 필수 첨부파일 END *****/
			
			/***** 기타 첨부파일 START *****/
			if(vendFileParameterMap != null){
				// 기타첨부파일 삭제
				parameterMap.put("P_VEND_DOC_FSCD", "DC99");
				oproUserVendInfoDao.deleteVendAtchDocList(parameterMap);

				String atchFileGroupNo = ""; 
				if("".equals(parameterMap.getString("P_ETC_FILE_GRP_NO"))){
					atchFileGroupNo = vendFileParameterMap.getString("atchFileGroupNo");
				}else{
					atchFileGroupNo = parameterMap.getString("P_ETC_FILE_GRP_NO");	
				}
				
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
				oproUserVendInfoDao.insertVendAtchDocList(parameterMap);
			}
			
			if(!"".equals(parameterMap.getString("P_ETC_DEL_SN"))){
				String delFileSn = parameterMap.getString("P_ETC_DEL_SN");
				if(!"".equals(delFileSn)){
					String[] snArray = delFileSn.split(",");
					List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
					for (int i = 0; i < snArray.length; i++) {
						FwkParameterMap delMap = new FwkParameterMap();
						delMap.put("P_FILE_SN", snArray[i]);
						delList.add(delMap);
					}
					parameterMap.put("fileList", delList);
					parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_ETC_FILE_GRP_NO"));
					comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
				}
			}
			/***** 기타 첨부파일 END *****/
			
			
			
			// 삭제한 파일 DELETE_AT = 'N' 처리
			String deleteFileSn = parameterMap.getString("P_DELETE_FILE_SN");
			if(!"".equals(deleteFileSn)){
				String[] arrDeleteFileSn = deleteFileSn.split(",");
				List<Map<String, Object>> deleteFileSnList = new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < arrDeleteFileSn.length; i++) {
					FwkParameterMap pmap = new FwkParameterMap();
					pmap.put("P_FILE_SN", arrDeleteFileSn[i]);
					deleteFileSnList.add(pmap);
				}
				
				comAtmaAtchFileService.atchFileDelete(deleteFileSnList);
			}
			
			// 업체승인이력
			parameterMap.put("P_APPR_PE_ID", session.get("USR_ID"));
			parameterMap.put("P_APPR_DT", FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_VEND_APPR_STCD", parameterMap.get("P_VEND_STCD"));
			oproUserVendInfoDao.insertApprVendProgHist(parameterMap);
			
			trans.put("P_VEND_REG_NO", parameterMap.get("P_VEND_REG_NO"));
			trans.put("P_VEND_STCD", parameterMap.get("P_VEND_STCD"));

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== OproUserVendInfoServiceImpl.vendInfoUpdt Error == ");
		}
		
		return trans;
	}


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.user.service.OproUserVendInfoService#vendPwdChngRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject vendPwdChngRegist(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 2019-03-14 은잔디 수정 : SHA-512 => SHA-256
		// 변경한 비밀번호
		String PW = parameterMap.getString("P_CHG_PWD");
		//System.err.println("비밀번호 암호화 ==> " + SDBCryptoUtil.encryptSha256(PW));
		parameterMap.put("P_PWD", SDBCryptoUtil.encryptSha256(PW));
		
		 
		// 업체마스터 - T_CU_MST
		oproUserVendInfoDao.updtVendPwd(parameterMap);
		
		trans.put("P_VEND_REG_NO", parameterMap.get("P_VEND_REG_NO"));
		
		return trans;
	}

	
	public FwkTransferObject bakFileSave(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		String contextPath = "bak";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest, "P_FILE", contextPath);
		
		if(fileParameterMap != null) {
			List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
			
			parameterMap.put("atchFileGroupNo", fileParameterMap.get("atchFileGroupNo")); 
			parameterMap.put("fileList", pfileList);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap); 
		}
		return trans;
	}

}
