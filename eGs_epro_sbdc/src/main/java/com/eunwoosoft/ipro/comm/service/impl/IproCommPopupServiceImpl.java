package com.eunwoosoft.ipro.comm.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.MailSendUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.comm.dao.IproCommPopupDao;
import com.eunwoosoft.ipro.comm.service.IproCommPopupService;
import com.eunwoosoft.ipro.estm.dao.IproEstmProgDao;


/**
 * 
 * com.eunwoosoft.ipro.comm.service.impl
 * IproCommPopupServiceImpl.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Service(value="iproCommPopupService")
public class IproCommPopupServiceImpl extends AbstractFwkService implements IproCommPopupService {
	private static final Logger LOG = LoggerFactory.getLogger(IproCommPopupServiceImpl.class);
	
	@Resource(name="iproCommPopupDao")
	private IproCommPopupDao iproCommPopupDao;
	 
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao;
	
	@Resource(name="iproEstmProgDao")
	private IproEstmProgDao iproEstmProgDao;
	
	
	@Override
	public FwkTransferObject canclCurstatRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if("prpo".equals(parameterMap.get("P_PAGE_GBN"))) { //구매 취소
			parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());
			
			parameterMap.put("P_PROGRS_STTUS_CD", "0");
			
			// 구매요구진행상태 구매접수대기로 변경
//			iproPrpoPustRegstDao.updatePurchsRceptUpdt(parameterMap);
			
			// 구매접수 취소시 TEPO 테이블 모두 삭제
			// DELETE TEPO_CNPREN
//			iproPrpoPustRegstDao.deletePrpoTepoPrvstlEntrps(parameterMap);
			// DELETE TEPO_PRDUCT
//			iproPrpoPustRegstDao.deleteTepoPrpoThngPrduct(parameterMap);
			// DELETE TEPO_BALA
//			iproPrpoPustRegstDao.deleteTepoPrpoLaword(parameterMap);
			// DELETE TEPO_SECNTA
//			iproPrpoPustRegstDao.deletePrpoTepoSecnta(parameterMap);
			// DELETE TEPO_PUDEFI
//			iproPrpoPustRegstDao.deletePrpoTepoPudefi(parameterMap);
			// DELETE TEPO_PUDEMA
//			iproPrpoPustRegstDao.deletePrpoTepoPudema(parameterMap);
			

			parameterMap.put("P_PURDMD_PRST_CD", "0");
			parameterMap.put("P_REGISTER_ID", parameterMap.get("P_USER_ID")); // 등록자 ID
			parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 등록일자
			
			// 구매요구진행이력 등록
//			iproPrpoPustRegstDao.insertPrpoPrstRegist(parameterMap);
			
		}
		/*else{ //입찰 취소
			parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_BID_PRST_CD","P");	//입찰진행상태 == 유찰
			parameterMap.put("P_DELETE_AT","N");	// 기본값 
			
			// 입찰진행이력 등록
			iproCommPopupDao.insertBidProgrsHistRegist(parameterMap);
 
			// 입찰계획마스터 입찰진행상태 변경 'P' = 입찰취소
			iproCommPopupDao.updateBidProgrsSttusUpdt(parameterMap);
			 
		}
		*/
		return trans;
	}

	
	//업체 목록 조회
	public FwkTransferObject entrpsInqireList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.ENTRPS_INQIRE_LIST, iproCommPopupDao.selectEntrpsInqireList(parameterMap));		//mainGnrlNoticeList
		trans.put(IproCommPopupService.ENTRPS_INQIRE_LIST_TOTCNT, iproCommPopupDao.selectEntrpsInqireListTotcnt(parameterMap));	//unityNttListTotcnt

		return trans; 
	} 
	
	//업체 목록 조회
	public FwkTransferObject fileSampleLIst(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		//전체list
		trans.put(IproCommPopupService.FILE_SAMPLE_LIST, iproCommPopupDao.selectFileSampleInqireList(parameterMap));		
		
		//추천
		trans.put("fileRecmId", iproCommPopupDao.selectFileSampleRecmCdId(parameterMap));	
		
		return trans; 
	} 
	
	
	// 이전 자동첨부파일 조회
	public FwkTransferObject contFormFileList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		//fileList
		trans.put(IproCommPopupService.CONT_FORM_FILE_LIST, iproCommPopupDao.selectContFormFileList(parameterMap));	
		
		return trans; 
	} 
		
	
	
	//견적업체 목록 조회
	public FwkTransferObject prpoEntrpsInqireList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.ENTRPS_INQIRE_LIST, iproCommPopupDao.selectPrpoEntrpsInqireList(parameterMap));		
		trans.put(IproCommPopupService.ENTRPS_INQIRE_LIST_TOTCNT, iproCommPopupDao.selectPrpoEntrpsInqireListTotcnt(parameterMap));	
		return trans; 
	} 
	
	
	// 해당계약의 계약업체를 제외한 업체 목록 조회 팝업
	public FwkTransferObject entrpsInqireNotInList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.ENTRPS_INQIRE_NOT_LIST, iproCommPopupDao.selectEntrpsInqireNotInList(parameterMap));		//mainGnrlNoticeList
		trans.put(IproCommPopupService.ENTRPS_INQIRE_NOT_LIST_TOTCNT, iproCommPopupDao.selectEntrpsInqireNotInListTotcnt(parameterMap));	//unityNttListTotcnt
		
		trans.put("P_CNTRCT_NO", parameterMap.get("P_CNTRCT_NO"));
		trans.put("P_CHANGE_ODR", parameterMap.get("P_CHANGE_ODR"));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 담당자조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : chargerListInqireWithPgng
	 * @date : 2018. 02. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 20.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject chargerListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.CHARGER_LIST , iproCommPopupDao.selectChargerListWithPgng(parameterMap));
		trans.put(IproCommPopupService.CHARGER_LIST_TOTCNT, iproCommPopupDao.selectChargerListTotcnt(parameterMap));
		return trans;
	}
	
	//근거법령 목록 조회
	public FwkTransferObject lawordInqireList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.LAWORD_INQIRE_LIST, iproCommPopupDao.selectLawordInqireList(parameterMap));		//mainGnrlNoticeList
		trans.put(IproCommPopupService.LAWORD_INQIRE_LIST_TOTCNT, iproCommPopupDao.selectLawordInqireListTotcnt(parameterMap));	//unityNttListTotcnt
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 : 품목등록 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : itemListInqireWithPgng
	 * @date : 2018. 02. 21.
	 * @author : jandi_Eun
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 21.		은우소프트 은잔디    			   최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject itemListInqireWithPgng(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproCommPopupService.ITEM_LIST, iproCommPopupDao.selectItemListWithPgng(parameterMap));
		trans.put(IproCommPopupService.ITEM_LIST_TOTCNT, iproCommPopupDao.selectItemListTotcnt(parameterMap));	
		return trans;
	}

	@Override
	public FwkTransferObject returnRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		 
		if("acpr".equals(parameterMap.get("P_RETURN_GBN"))) {	// 실적증명 반려
			
			parameterMap.put("P_ACPR_PRST_CD", "3"); //접수반려
			
			parameterMap.put("P_REGISTER_ID", parameterMap.get("P_USER_ID")); // 등록자 ID
			parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 등록일자
			
		}else { //구매요구 반려
		
			parameterMap.put("P_PROGRS_STTUS_CD", "9");
			
			// 구매요구진행상태 반려로 변경
			parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 반려일자
			
//			iproPrpoPustRegstDao.updatePurchsRceptUpdt(parameterMap);

			parameterMap.put("P_PURDMD_PRST_CD", "9");
			parameterMap.put("P_REGISTER_ID", parameterMap.get("P_USER_ID")); // 등록자 ID
			
			// 구매요구진행이력 등록
//			iproPrpoPustRegstDao.insertPrpoPrstRegist(parameterMap);
		}
		return trans;
	}

	
	//부서 목록 조회
	public FwkTransferObject deptInqireList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.DEPT_INQIRE_LIST, iproCommPopupDao.selectDeptInqireList(parameterMap));		
		trans.put(IproCommPopupService.DEPT_INQIRE_LIST_TOTCNT, iproCommPopupDao.selectDeptInqireListTotcnt(parameterMap));
		//계약진행현황에 작성부서와 계약부서(PRCH)를 구분
		trans.put("GBN", parameterMap.get("P_GBN"));
		return trans;
	}
	
	//데이터베이스 조회
	public FwkTransferObject dataBaseList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO",session.get("VEND_REG_NO"));
		
		trans.put("dataBaseList", iproCommPopupDao.selectDataBaseList(parameterMap));		
		trans.put("dataBaseListTotcnt", iproCommPopupDao.selectDataBaseListTotcnt(parameterMap));
		//계약진행현황에 작성부서와 계약부서(PRCH)를 구분
		trans.put("GBN", parameterMap.get("P_GBN"));
		return trans;
	}
	
	
	
	//화상회의메일전송
	public FwkTransferObject meetSendMail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		//참가자 정보 조회
		FwkDataEntity mailSendDetail = iproCommPopupDao.selectEstmSendDetail(parameterMap);
		
		FwkParameterMap mailParameterMap = new FwkParameterMap();
		MailSendUtil mailSendUtil = new MailSendUtil();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		//메일정보 조회
		mailParameterMap.put("P_MSG_SECD", "MAIL");
		mailParameterMap.put("P_MSG_SN", "3");
		//parameterMap.put("P_MSG_OBJ_ID", "");
		FwkDataEntity msgDetail = (FwkDataEntity) iproCommDefaultDao.selectMsgContents(mailParameterMap);   // T_ESTM_MNG_MST
		String mailCntn = "";
		String mailTitle = "";
		
		//메일 제목
		mailTitle = msgDetail.getString("MSG_TTL");
		mailTitle = mailTitle.replace("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
		mailParameterMap.put("P_MSG_TTL",mailTitle);
		
		String ext_room_id = parameterMap.getString("P_ESTM_NO") + "-" + parameterMap.getString("P_ESTM_PROCD_SEQ") + "-" + parameterMap.getString("P_VIDO_MTNG_SEQ");
		// 평가담당자 메일호출
			//메일 수신자
			mailParameterMap.put("P_TO_MAIL", parameterMap.get("P_EMAL"));
			
			String ext_user_id = parameterMap.getString("P_MAIL_USER_ID");
			String ext_password = mailSendDetail.getString("PASSWORD");
			String nickname = mailSendDetail.getString("USER_NM");
			
			try {
				nickname = URLEncoder.encode(nickname, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("인코딩 실패");
			}
			
			
			String LINK_URL = 
							FwkMessageUtil.getMessage("VIDO.LINK.URL")+"?"+
							 "ext_room_id=" + ext_room_id
							+ "&ext_user_id=" + ext_user_id
							+ "&ext_password=" + ext_password
							+ "&nickname=" + nickname
							;
			
			
			
			//메일 내용
			mailCntn = msgDetail.getString("MSG_CNTN");
			mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
			mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
			mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
			mailCntn = mailCntn.replaceAll("#LINK_URL#", LINK_URL);
			mailCntn = mailCntn.replaceAll("#VIDO_ST_DT#", parameterMap.getString("P_VIDO_ST_DT"));
			mailCntn = mailCntn.replaceAll("#VIDO_END_DT#", parameterMap.getString("P_VIDO_END_DT"));
			mailCntn = mailCntn.replaceAll("#VIDO_MTNG_NM#", parameterMap.getString("P_VIDO_MTNG_NM"));
			
			
			System.out.println("mailCntn :::: " + mailCntn);
			
			mailParameterMap.put("P_MSG_CNTN",mailCntn);
			
			parameterMap.put("P_CD_ID_S", "SEND_MAIL");
			List<FwkDataEntity> mailSendList = iproCommDefaultDao.selectCodeList(parameterMap);
			LOG.debug("========== estmSendMail Start ==========");
			
			if("Y".equals(mailSendList.get(0).get("CD_DTL_ID").toString())){
				//메일전송
				try{
					
					mailSendUtil.sendMailException(mailParameterMap);
					trans.put("successAt", "Y");
				}catch(Exception e){
					trans.put("successAt", "N");
					e.printStackTrace();
				}				
			}else {
				trans.put("successAt", "N");
			}
				

		return trans;
	}
	
	
	
	//월별부서 목록 조회
	public FwkTransferObject deptYMList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		String flag = "F";
		if(
				parameterMap.get("P_YM_S") != null && !"".equals(parameterMap.get("P_YM_S"))
				|| parameterMap.get("P_DEPT_NM_S") != null && !"".equals(parameterMap.get("P_DEPT_NM_S")) ) {
			flag = "T";
		}
		
		
		System.err.println("123 flag  :: " + flag);
		parameterMap.put("flag", flag);
		
		
		
		if("T".equals(flag)) {
			trans.put(IproCommPopupService.DEPT_INQIRE_LIST, iproCommPopupDao.selectDeptYMList(parameterMap));		
			trans.put(IproCommPopupService.DEPT_INQIRE_LIST_TOTCNT, iproCommPopupDao.selectDeptYMListTotcnt(parameterMap));
		}
		
		//계약진행현황에 작성부서와 계약부서(PRCH)를 구분
		trans.put("GBN", parameterMap.get("P_GBN"));
		return trans;
	}
		
	
	

	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.comm.service.IproCommPopupService#menuAuthMgrListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	public FwkTransferObject menuAuthMgrListWithPgng(final FwkParameterMap parameterMap) {
		// 권한별메뉴관리 목록
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.MENU_AUTH_MGR_LIST, iproCommPopupDao.selectMenuAuthMgrListWithPgng(parameterMap));
		trans.put(IproCommPopupService.MENU_AUTH_MGR_LIST_TOTCNT, iproCommPopupDao.selectMenuAuthMgrListTotcnt(parameterMap));
		return trans;
	}
	
 	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : apprLineListInqireWithPgng
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */	
	@Override
	public FwkTransferObject apprLineListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		List<FwkDataEntity> dataEntity = (List<FwkDataEntity>) iproCommPopupDao.selectApprLineListWithPgng(parameterMap);
		
		trans.put(IproCommPopupService.APPR_LINE_LIST ,dataEntity);
		trans.put(IproCommPopupService.APPR_LINE_LIST_TOTCNT, iproCommPopupDao.selectApprLineListTotcnt(parameterMap));
		return trans;
	}	

	
	/**
	 * <pre>
	 * 1. 개요 : 계약서식조회상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : contFormDetail
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */	
	@Override
	public FwkTransferObject contFormDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.CONT_FORM_DETAIL, iproCommPopupDao.selectContFormDetail(parameterMap));
		return trans;
	}	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : prchRqstList
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 19. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject prchRqstList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproCommPopupService.PRCH_RQST_LIST, iproCommPopupDao.selectPrchRqstListWithPang(parameterMap));
		trans.put(IproCommPopupService.PRCH_RQST_LIST_TOTCNT, iproCommPopupDao.selectPrchRqstListTotCnt(parameterMap));
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : prchRqstDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 20. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject prchRqstDetail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproCommPopupService.PRCH_RQST_DETAIL, iproCommPopupDao.selectPrchRqstDetail(parameterMap));
		trans.put(IproCommPopupService.PRCH_RQST_ITEM_LIST, iproCommPopupDao.selectPrchRqstItemList(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 수의계약사유정보 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : getPvrsMstList
	 * @author : 맹경열
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject getPvrsMstList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproCommPopupService.PVRS_MST_LIST, iproCommPopupDao.selectPvrsMstListWithPgng(parameterMap));
		trans.put(IproCommPopupService.PVRS_MST_LIST_TOTCNT, iproCommPopupDao.selectPvrsMstListTotcnt(parameterMap));
		return trans;
	}	
	
	/**
	 * <pre>
	 * 1.개요 :  업체그룹관리 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : compGroupRegist
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject compGroupRegist(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
				iproCommPopupDao.compGroupRegist(parameterMap);			
		} catch (Exception e) {
				trans.put("result","fail");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return trans;
	}	
	
	/**
	 * <pre>
	 * 1.개요 :  업체그룹관리 상세조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : compGroupDetail
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject compGroupDetail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.COMP_GROUP_DETAIL, iproCommPopupDao.compGroupDetail(parameterMap));
		
		return trans;
	}	
	/**
	 * <pre>
	 * 1.개요 :  업체그룹관리 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : compGroupRegist
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject compGroupUpdate(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
				iproCommPopupDao.compGroupUpdate(parameterMap);			
		} catch (Exception e) {
				trans.put("result","fail");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return trans;
	}	
	/**
	 * <pre>
	 * 1.개요 :  업체그룹관리 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : compGroupDelete
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject compGroupDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			iproCommPopupDao.compGroupListDelete(parameterMap);			
			iproCommPopupDao.compGroupMstDelete(parameterMap);			
		} catch (Exception e) {
			trans.put("result","fail");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return trans;
	}
	
 	/**
	 * <pre>
	 * 1. 개요 : 프로그램목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prgmInqireList
	 * @date : 2018. 09. 13.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 09. 13.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	public FwkTransferObject prgmInqireList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproCommPopupService.PRGM_LIST, iproCommPopupDao.selectPrgmListWithPgng(parameterMap));
		trans.put(IproCommPopupService.PRGM_LIST_TOTCNT, iproCommPopupDao.selectPrgmListTotCnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 :  BIS 발주 디자인 목록_페이징
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : dngnListInqireWithPgng
	 * @date : 2018. 11. 21.
	 * @author : 은우소프트 맹경열	
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap - 협력업체목록 조회조건
	 * @return - 협력업체목록
	 */
	@Override
	public FwkTransferObject dngnListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproCommPopupService.DSGN_LIST, iproCommPopupDao.selectBisDsgnListWithPgng(parameterMap));
		trans.put(IproCommPopupService.DSGN_LIST_TOTCNT, iproCommPopupDao.selectBisDsgnListTotcnt(parameterMap));
		
		return trans;		
	}
	
 	/**
	 * <pre>
	 * 1. 개요 : 근로계약 대상자 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : laborerListInqireWithPgng
	 * @date : 2018. 12. 28.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 12. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject laborerListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproCommPopupService.LABORER_LIST, iproCommPopupDao.selectLaborerListWithPgng(parameterMap));
		trans.put(IproCommPopupService.LABORER_LIST_TOTCNT, iproCommPopupDao.selectLaborerListTotcnt(parameterMap));
		
		return trans;				
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.comm.service.IproCommPopupService#mjrHndlItemListInqireWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject mjrHndlItemListWithPgng( FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproCommPopupService.MJR_HNDL_ITEM_LIST, iproCommPopupDao.selectMjrHndlItemListWithPgng(parameterMap));
		trans.put(IproCommPopupService.MJR_HNDL_ITEM_LIST_TOTCNT, iproCommPopupDao.selectMjrHndlItemListTotcnt(parameterMap));
		
		return trans;	
	}
	
 	/**
	 * <pre>
	 * 1. 개요 : 일괄공고 대상 공고대기 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : uniAnncItemListWithPgng
	 * @date : 2019. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 08.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject uniAnncItemListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 견적(EST), 입찰(BID), 수의시담(PRVT) 구분에 따른 일괄공고리스트
		List<FwkDataEntity> itemList = null;
		int itemCnt = 0;
		if("EST".equals(parameterMap.get("gbn"))){
			itemList = iproCommPopupDao.selectUniEstItemListWithPgng(parameterMap);
			itemCnt = iproCommPopupDao.selectUniEstItemListTotcnt(parameterMap);
		}else if("BID".equals(parameterMap.get("gbn"))){
			itemList = iproCommPopupDao.selectUniAnncItemListWithPgng(parameterMap);
			itemCnt = iproCommPopupDao.selectUniAnncItemListTotcnt(parameterMap);
		}else{
			itemList = iproCommPopupDao.selectUniPrvtItemListWithPgng(parameterMap);
			itemCnt = iproCommPopupDao.selectUniPrvtItemListTotcnt(parameterMap);
		}
		
		//입찰대기 중 일괄처리가능 리스트 조회
		trans.put(IproCommPopupService.UNI_ANNC_PSBL_LIST, itemList);
		trans.put(IproCommPopupService.UNI_ANNC_PSBL_LIST_TOTCNT, itemCnt);
		
		return trans;
	}
	
 	/**
	 * <pre>
	 * 1. 개요 : 공급품목관리 대상 입찰공고 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidVendItemRefListWithPgng
	 * @date : 2019. 05. 28.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */	
	public FwkTransferObject bidVendItemRefListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); 
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		Object itemNo = parameterMap.get("P_ITEM_NO");
		Object vendRegNo = parameterMap.get("P_VEND_REG_NO");
		
		ArrayList<String> P_ITEM_NO_LIST = new ArrayList<String>();
		ArrayList<String> P_VEND_REG_NO_LIST = new ArrayList<String>();
		
		if(itemNo instanceof String) {
			P_ITEM_NO_LIST.add((String) itemNo);
			P_VEND_REG_NO_LIST.add((String) vendRegNo);
		}else {
			if(itemNo instanceof ArrayList) {
				ArrayList<String> itemNoList = (ArrayList) itemNo;
				ArrayList<String> vendRegNoList = (ArrayList) vendRegNo;
				for (int idx = 0; idx < itemNoList.size(); idx++) {
					P_ITEM_NO_LIST.add(itemNoList.get(idx));
				}
				for (int idx = 0; idx < vendRegNoList.size(); idx++) {
					P_VEND_REG_NO_LIST.add(vendRegNoList.get(idx));
				}
			}else if(itemNo instanceof String[]) {
				String[] itemNoList = (String[]) itemNo;
				String[] vendRegNoList = (String[]) vendRegNo;
				
				for (int idx = 0; idx < itemNoList.length; idx++) {
					P_ITEM_NO_LIST.add(itemNoList[idx]);
				}
				for (int idx = 0; idx < vendRegNoList.length; idx++) {
					P_VEND_REG_NO_LIST.add(vendRegNoList[idx]);
				}
			}
		}
		
		parameterMap.put("P_ITEM_NO_LIST", P_ITEM_NO_LIST);
		parameterMap.put("P_VEND_REG_NO_LIST", P_VEND_REG_NO_LIST);
		
		trans.put(IproCommPopupService.VEND_ITEM_ANNC_LIST, iproCommPopupDao.bidVendItemRefListWithPgng(parameterMap));
		trans.put(IproCommPopupService.VEND_ITEM_ANNC_LIST_TOTCNT, iproCommPopupDao.bidVendItemRefListTotcnt(parameterMap));		
		trans.put(IproCommPopupService.VEND_ITEM_NO_LIST, P_ITEM_NO_LIST);		
		
		return trans;
	}

 	/**
	 * <pre>
	 * 1. 개요 : 입찰 업체 열람 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidVendOpenList
	 * @date : 2019. 07. 22.
	 * @author : 은우소프트 은잔디
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */	
	@Override
	public FwkTransferObject bidVendOpenList(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproCommPopupService.BID_VEND_OPEN_LIST, iproCommPopupDao.selectBidVendOpenList(parameterMap));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject postListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproCommPopupService.POST_LIST, iproCommPopupDao.selectPostListWithPgng(parameterMap));
		trans.put(IproCommPopupService.POST_LIST_TOTCNT, iproCommPopupDao.selectPostListTotcnt(parameterMap));	
		return trans;
	}


	@Override
	public FwkTransferObject estmInfoList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmInfoList", iproCommPopupDao.selectEstmInfoList(parameterMap));
		trans.put("estmInfoListTotCnt", iproCommPopupDao.selectEstmInfoListTotCnt(parameterMap));	
		return trans;
	}
	
	@Override
	public FwkTransferObject estmHistList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmHistList", iproCommPopupDao.selectEstmHistList(parameterMap));
		trans.put("estmHistListTotCnt", iproCommPopupDao.selectEstmHistListTotCnt(parameterMap));	
		return trans;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가서식목록 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.service.impl.IproCommPopupServiceImpl.java
	 * @Method : estmFrmPopupList
	 * @author : 손연우
	 * @date : 2021. 04. 01. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject estmFrmPopupList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("estmFrmPopupList", iproCommPopupDao.selectEstmFrmPopupList(parameterMap));
		trans.put("estmFrmPopupListTotCnt", iproCommPopupDao.selectEstmFrmPopupListTotcnt(parameterMap));
		return trans;
	}

	public FwkTransferObject mtngPrtcRqstList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("vidoObjList", iproCommPopupDao.selectVidoObjList(parameterMap));
		trans.put("vidoCmtmList", iproCommPopupDao.selectVidoCmtmList(parameterMap));
		return trans;
	}

	
	
	public FwkTransferObject estmObjFileView(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("estmObjFileView", iproCommPopupDao.selectEstmObjFileView(parameterMap));
		return trans;
	}

	public FwkTransferObject estmVidoFileView(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		trans.put("estmVidoFileView", iproCommPopupDao.selectEstmVidoFileView(parameterMap));   // T_ESTM_VIDO_MTNG
		return trans;
	}
	
	
	
	//평가위원 위원평가
	public FwkTransferObject estmCmtmScrUpdtFrm(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("estmCmtmScrDetail", iproCommPopupDao.selectEstmCmtmScrDetail(parameterMap));   // T_ESTM_CMTM
		return trans;
	}

	//평가위원 위원평가
	public FwkTransferObject estmCmtmScrUpdt(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		iproCommPopupDao.selectEstmCmtmScrUpdt(parameterMap);
		
		return trans;
	}
	
	public FwkTransferObject estmCmtmEstmFrmDetail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmCmtmEstmFrmDetail", iproCommPopupDao.selectEstmCmtmEstmFrmDetail(parameterMap));
		trans.put("estmObjDetail", iproCommPopupDao.selectEstmObjDetail(parameterMap));   // T_ESTM_OBJ
		trans.put("estmCmtmEstmFrmItemList", iproCommPopupDao.selectEstmCmtmEstmFrmItemList(parameterMap));
		
		return trans;
	}
	
	public FwkTransferObject estmCmtmEstmFrmBSave(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 평가위원 점수저장
		 * 1. 평가대상점수 : T_ESTM_OBJ_SCR 저장
		 * 2. 평가위원평가대상점수 : T_ESTM_CMTM_OBJ_SCR 저장
		 * 
		 */
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_ESTM_NO") != null) {

			// 평가위원분야매핑 등록
			if(parameterMap.get("P_ESTM_CMTM_NO") != null) {
				
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가점수
				Object P_ESTM_DTL_ITEM_POINT = parameterMap.get("P_ESTM_DTL_ITEM_POINT");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
							// 평가위원분야매핑 등록
							//parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
							parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
							parameterMap.put("P_ESTM_SCR", parameterMap.get("P_ESTM_DTL_ITEM_POINT"));
							iproCommPopupDao.mergeIntoEstmObjScr(parameterMap);   // T_ESTM_OBJ_SCR
							iproCommPopupDao.insertEstmObjScrHist(parameterMap);   // T_ESTM_OBJ_SCR_HIST
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가항목번호
						ArrayList<String> P_ESTM_DTL_ITEM_NOList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가점수
						ArrayList<String> P_ESTM_DTL_ITEM_POINTList = (ArrayList<String>) P_ESTM_DTL_ITEM_POINT;
						
							for(int i =1; i < P_ESTM_DTL_ITEM_NOList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
								parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList.get(i));
								
								// 평가위원분야매핑 등록
								iproCommPopupDao.mergeIntoEstmObjScr(parameterMap);   // T_ESTM_OBJ_SCR
								iproCommPopupDao.insertEstmObjScrHist(parameterMap);   // T_ESTM_OBJ_SCR_HIST
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//평가항목번호
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						//평가점수
						String[] P_ESTM_DTL_ITEM_POINTList = (String[]) P_ESTM_DTL_ITEM_POINT;
						
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList[idx]);

							// 평가위원분야매핑 등록
							iproCommPopupDao.mergeIntoEstmObjScr(parameterMap);   // T_ESTM_OBJ_SCR
							iproCommPopupDao.insertEstmObjScrHist(parameterMap);   // T_ESTM_OBJ_SCR_HIST
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		
		
		// 평가위원평가대상점수 merge Into
		iproCommPopupDao.mergeIntoEstmCmtmObjScr(parameterMap);   // T_ESTM_CMTM_OBJ_SCR
		
		//trans.put("P_ESTM_SPHE_SECD", parameterMap.get("P_ESTM_SPHE_SECD"));
		
		return trans;
		
	}
	
	
	public FwkTransferObject estmCmtmEstmFrmBDetail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmCmtmEstmFrmDetail", iproCommPopupDao.selectEstmCmtmEstmFrmDetail(parameterMap));
		trans.put("estmObjDetail", iproCommPopupDao.selectEstmObjDetail(parameterMap));
		trans.put("estmCmtmEstmFrmItemList", iproCommPopupDao.selectEstmCmtmEstmFrmItemList(parameterMap));
		
		return trans;
	}
	
	
	
	
	/**
	 * 평가진행현황(평가절차) - 평가서식 상세
	 */
	public FwkTransferObject estmFrmDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmFrmDetail", iproCommPopupDao.selectEstmFrmDetail(parameterMap));   // T_ESTM_PROCD
		trans.put("estmFrmItemList", iproCommPopupDao.selectEstmFrmItemList(parameterMap));   // T_ESTM_PROCD_FRM
		trans.put("estmSeProcdDetail", iproCommPopupDao.selectEstmSeProcdDetail(parameterMap));   // T_ESTM_SE_PROCD
		
		return trans;
	}
	
	/**
	 * 평가구분 - 평가서식 상세
	 */
	public FwkTransferObject estmSeMngFrmDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmFrmDetail", iproCommPopupDao.selectEstmSeMngFrmDetail(parameterMap));   // T_ESTM_SE_PROCD
		trans.put("estmFrmItemList", iproCommPopupDao.selectEstmSeMngFrmItemList(parameterMap));   // T_ESTM_SE_FRM
		//trans.put("estmSeProcdDetail", iproCommPopupDao.selectEstmSeProcdDetail(parameterMap));   // T_ESTM_SE_PROCD
		
		return trans;
	}
	
	
	
	
	/**
	 * 평가서식 저장
	 */
	public FwkTransferObject estmFrmUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		

		if (parameterMap.get("P_ESTM_FRM_NO") != null) {
			// 평가서식항목 삭제
			iproCommPopupDao.deleteEstmFrmItemDelt(parameterMap);
			
			// 평가서식항목 등록
			if(parameterMap.get("P_ESTM_DTL_ITEM_NO") != null) {
				
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가항목명
				Object P_ESTM_DTL_ITEM_NM = parameterMap.get("P_ESTM_DTL_ITEM_NM");
				//평가항목내용
				Object P_ESTM_ITEM_DTL_CNTN = parameterMap.get("P_ESTM_ITEM_DTL_CNTN");
				//배점
				Object P_ESTM_DTL_ITEM_SCR = parameterMap.get("P_ESTM_DTL_ITEM_SCR");
				//평가방법구분코드				
				Object P_ESTM_DTL_ITEM_SCR_SECD = parameterMap.get("P_ESTM_DTL_ITEM_SCR_SECD");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					System.err.println("@@@ P_ESTM_DTL_ITEM_NO != null @@@");
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
						
						parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
						parameterMap.put("P_ESTM_ITEM_NM", parameterMap.get("P_ESTM_DTL_ITEM_NM"));
						parameterMap.put("P_ESTM_ITEM_DESC", parameterMap.get("P_ESTM_ITEM_DTL_CNTN"));
						parameterMap.put("P_ESTM_ITEM_DSMK", parameterMap.get("P_ESTM_DTL_ITEM_SCR"));
						parameterMap.put("P_ESTM_MTHD_SECD", parameterMap.get("P_ESTM_DTL_ITEM_SCR_SECD"));
						
						// 평가서식항목 등록
						iproCommPopupDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가항목번호
						ArrayList<String> estmDtlItemNoList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가항목명
						ArrayList<String> estmDtlItemNmList = (ArrayList<String>) P_ESTM_DTL_ITEM_NM;
						//평가항목내용
						ArrayList<String> estmItemDtlCntnList = (ArrayList<String>) P_ESTM_ITEM_DTL_CNTN;
						//배점
						ArrayList<String> estmDtlItemScrList = (ArrayList<String>) P_ESTM_DTL_ITEM_SCR;
						//평가방법구분코드				
						ArrayList<String> estmDtlItemScrSecdList = (ArrayList<String>) P_ESTM_DTL_ITEM_SCR_SECD;
						
							for(int i =0; i < estmDtlItemNoList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", estmDtlItemNoList.get(i));
								parameterMap.put("P_ESTM_ITEM_NM", estmDtlItemNmList.get(i));
								parameterMap.put("P_ESTM_ITEM_DESC", estmItemDtlCntnList.get(i));
								parameterMap.put("P_ESTM_ITEM_DSMK", estmDtlItemScrList.get(i));
								parameterMap.put("P_ESTM_MTHD_SECD", estmDtlItemScrSecdList.get(i));
								
								// 평가서식항목 등록
								iproCommPopupDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						String[] P_ESTM_DTL_ITEM_NMList = (String[]) P_ESTM_DTL_ITEM_NM;
						String[] P_ESTM_ITEM_DTL_CNTNList = (String[]) P_ESTM_ITEM_DTL_CNTN;
						String[] P_ESTM_DTL_ITEM_SCRList = (String[]) P_ESTM_DTL_ITEM_SCR;
						String[] P_ESTM_DTL_ITEM_SCR_SECDList = (String[]) P_ESTM_DTL_ITEM_SCR_SECD;
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_ITEM_NM", P_ESTM_DTL_ITEM_NMList[idx]);
							parameterMap.put("P_ESTM_ITEM_DESC", P_ESTM_ITEM_DTL_CNTNList[idx]);
							parameterMap.put("P_ESTM_ITEM_DSMK", P_ESTM_DTL_ITEM_SCRList[idx]);
							parameterMap.put("P_ESTM_MTHD_SECD", P_ESTM_DTL_ITEM_SCR_SECDList[idx]);

							// 평가서식항목 등록
							iproCommPopupDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_PROCD_FRM INSERT
						}
					}
					
				}//P_ESTM_DTL_ITEM_NO != null
				
			}//P_ESTM_DTL_ITEM_NO != null
		}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		
		return trans;
	}


	@Override
	public FwkTransferObject estmCmtmEstmFrmSave(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		/**
		 * 평가위원 점수저장
		 * 1. 평가대상점수 : T_ESTM_OBJ_SCR 저장
		 * 2. 평가위원평가대상점수 : T_ESTM_CMTM_OBJ_SCR 저장
		 * 
		 */
		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_ESTM_NO") != null) {

			// 평가위원분야매핑 등록
			if(parameterMap.get("P_ESTM_CMTM_NO") != null) {
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가점수
				Object P_ESTM_DTL_ITEM_POINT = parameterMap.get("P_ESTM_DTL_ITEM_POINT");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
							// 평가위원분야매핑 등록
							//parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
							parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
							parameterMap.put("P_ESTM_SCR", parameterMap.get("P_ESTM_DTL_ITEM_POINT"));
							iproCommPopupDao.mergeIntoEstmObjScr(parameterMap);   // T_ESTM_OBJ_SCR
							iproCommPopupDao.insertEstmObjScrHist(parameterMap);   // T_ESTM_OBJ_SCR_HIST
							
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						//평가항목번호
						ArrayList<String> P_ESTM_DTL_ITEM_NOList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가점수
						ArrayList<String> P_ESTM_DTL_ITEM_POINTList = (ArrayList<String>) P_ESTM_DTL_ITEM_POINT;
						
							for(int i =1; i < P_ESTM_DTL_ITEM_NOList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
								parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList.get(i));
								
								// 평가위원분야매핑 등록
								iproCommPopupDao.mergeIntoEstmObjScr(parameterMap);   // T_ESTM_OBJ_SCR
								iproCommPopupDao.insertEstmObjScrHist(parameterMap);   // T_ESTM_OBJ_SCR_HIST
									
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						
						//평가항목번호
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						//평가점수
						String[] P_ESTM_DTL_ITEM_POINTList = (String[]) P_ESTM_DTL_ITEM_POINT;
						
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList[idx]);
							// 평가위원분야매핑 등록
							iproCommPopupDao.mergeIntoEstmObjScr(parameterMap);   // T_ESTM_OBJ_SCR
							iproCommPopupDao.insertEstmObjScrHist(parameterMap);   // T_ESTM_OBJ_SCR_HIST
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		
		
		// 평가위원평가대상점수 merge Into
		iproCommPopupDao.mergeIntoEstmCmtmObjScr(parameterMap);   // T_ESTM_CMTM_OBJ_SCR
		
		//trans.put("P_ESTM_SPHE_SECD", parameterMap.get("P_ESTM_SPHE_SECD"));
		
		return trans;
	}


	@Override
	public FwkTransferObject estmCmtmList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_DEPT_NO", session.get("DEPT_NO"));
		
		List<FwkDataEntity> estmCmtmSlctList = iproCommPopupDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
		
		ArrayList<String> estmCmtmNoNotInList = new ArrayList<String>();
		
		if(estmCmtmSlctList != null){
			for (int i = 0; i < estmCmtmSlctList.size(); i++) {
				String ESTM_CMTM_NO = estmCmtmSlctList.get(i).getString("ESTM_CMTM_NO");
				estmCmtmNoNotInList.add(ESTM_CMTM_NO);
			}
			trans.put("estmCmtmSlctList", estmCmtmSlctList);
		}
		
		
		Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO_S");
		
		if(P_ESTM_CMTM_NO != null){
			
			if(P_ESTM_CMTM_NO instanceof String){

				String ESTM_CMTM_NO = parameterMap.getString("P_ESTM_CMTM_NO_S");
				estmCmtmNoNotInList.add(ESTM_CMTM_NO);
		
			}else if(P_ESTM_CMTM_NO instanceof ArrayList){
				
				ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;

				for(int i =0; i < estmCmtmNoList.size(); i++){
					String ESTM_CMTM_NO = estmCmtmSlctList.get(i).getString("P_ESTM_CMTM_NO_S");
					estmCmtmNoNotInList.add(ESTM_CMTM_NO);
				}
				
			}else if(P_ESTM_CMTM_NO instanceof String[]){
				
				String[] estmCmtmNoStr = (String[]) P_ESTM_CMTM_NO;
				
				for (int i = 0; i < estmCmtmNoStr.length; i++) {
					String ESTM_CMTM_NO = estmCmtmNoStr[i];
					estmCmtmNoNotInList.add(ESTM_CMTM_NO);
				}
			}
		}
		
		
		// 선정/비선정된 평가위원 제외
		System.err.println("@@@ estmCmtmNoNotInList ==> " + estmCmtmNoNotInList.toString());
		parameterMap.put("P_ESTM_CMTM_NO_NOT_IN_LIST", estmCmtmNoNotInList);
				
		
		// 자체선정일 경우 수기등록이 아닌 평가위원, 자기 부서가 수기등록한 평가위원 조회 
		List<FwkDataEntity> estmCmtmList = iproCommPopupDao.selectEstmCmtmMngPoolList(parameterMap);   // V_MM_ESTM_CMTM_MST
		trans.put("estmCmtmList", estmCmtmList);
		trans.put("estmCmtmListTotCnt", estmCmtmList.size());
		
		return trans;
	}


	@Override
	public FwkTransferObject noticeListPopup(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put("notiList", iproCommPopupDao.selectNoticeList(parameterMap));   // T_BA_MST
		trans.put("notiListTotCnt", iproCommPopupDao.selectNoticeListTotCnt(parameterMap));
		return trans;
	}
	
	@Override
	public FwkTransferObject noticePopup(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("notiDetail", iproCommPopupDao.selectNoticeDetail(parameterMap));   // T_BA_MST
		return trans;
	}


	@Override
	public FwkTransferObject estmCmtmFileView(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 통장사본
		parameterMap.put("P_FILE_DOC_SECD_S", "BNKB_CPY");
		trans.put("cmtmBnkbCpyFile", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));   // T_MM_FILE_MST
		
		// 재직증명서
		parameterMap.put("P_FILE_DOC_SECD_S", "HLDF_PFDC");
		trans.put("cmtmHldfPfdcFile", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));   // T_MM_FILE_MST
		
		return trans;
	}
	
	@Override
	public FwkTransferObject cmtmMngCrtrDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity estmCmtmCrtrMstDetail = iproCommPopupDao.selectEstmCmtmCrtrMstDetail(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmCmtmCrtrMstDetail", estmCmtmCrtrMstDetail);
		
		return trans;
	}


	@Override
	public FwkTransferObject estmObjImstarsMainView(final FwkParameterMap parameterMap) {
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity estmObjImstarsMainDetail = iproCommPopupDao.selectEstmObjImstarsMainDetail(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmObjImstarsMainDetail", estmObjImstarsMainDetail);

		List<FwkDataEntity> estmObjImstarsCmpnySelng = iproCommPopupDao.selectEstmObjImstarsCmpnySelng(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmObjImstarsCmpnySelng", estmObjImstarsCmpnySelng);
		
		List<FwkDataEntity> cmpnyFileList = null;
		if(estmObjImstarsMainDetail != null){
			String ATCH_FILE_ID = estmObjImstarsMainDetail.getString("ATCH_FILE_ID");   //  첨부 파일 아이디
			
			if(!"".equals(ATCH_FILE_ID)){
				parameterMap.put("P_FILE_ID", ATCH_FILE_ID);
				cmpnyFileList = iproCommPopupDao.selectEstmObjImstarsCmpnyFileView(parameterMap);   // SPORT_REQST_FILEDETAILINFO
			}
			
		}
		trans.put("cmpnyFileList", cmpnyFileList);
		
		return trans;
	}


	@Override
	public FwkTransferObject estmObjImstarsDetailView(final FwkParameterMap parameterMap) {
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity goodsDetail = iproCommPopupDao.selectEstmObjImstarsDetailView(parameterMap);   // V_ESTM_ANNC_TPI_INT2
		trans.put("goodsDetail", goodsDetail);
		
		List<FwkDataEntity> fileDetailList = null;
		
		if(goodsDetail != null){
			String IMG_DETAIL_FILE_ID = goodsDetail.getString("IMAGEFILEID");   //  이미지 파일 아이디
			
			parameterMap.put("P_FILE_ID", IMG_DETAIL_FILE_ID);
			fileDetailList = iproCommPopupDao.selectEstmObjImstarsDetailFileList(parameterMap);   // CMMN_FILEDETAILINFO
		}
		trans.put("fileDetailList", fileDetailList);
		
		// 상품인증
		List<FwkDataEntity> goodsCrtfcList = iproCommPopupDao.selectEstmObjImstarsGoodsCrtfcList(parameterMap);   // GOODS_CRTFC
		trans.put("goodsCrtfcList", goodsCrtfcList);
		
		// 상품매출액
		List<FwkDataEntity> goodsSelngList = iproCommPopupDao.selectEstmObjImstarsGoodssSelngList(parameterMap);   // GOODS_SELNG
		trans.put("goodsSelngList", goodsSelngList);
		
		return trans;
	}


	@Override
	public FwkTransferObject estmObjImstarsFileView(final FwkParameterMap parameterMap) {
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
		
		FwkTransferObject trans = new FwkTransferObject();
		List<FwkDataEntity> fileDetailInfo =  null;
		
		if(parameterMap.get("P_REQST_NO") != null && !"".equals(parameterMap.get("P_REQST_NO"))){
			fileDetailInfo = iproCommPopupDao.selectEstmObjImstarsFileView(parameterMap);   // SPORT_REQST_FILEDETAILINFO			
		}
		
		trans.put("fileDetailInfo", fileDetailInfo);
		
		return trans;
	}


	@Override
	public FwkTransferObject estmCmtmSvyDetail(final FwkParameterMap parameterMap) {
		// 평가완료현황_평가위원 - 상세 : 설문조사 팝업
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); 
		parameterMap.put("P_ESTM_CMTM_NO", session.get("USR_ID"));
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		trans.put("estmCmtmSvyFrmItemList", iproCommPopupDao.selectEstmCmtmSvyFrmItemList(parameterMap));
		
		return trans;
	}


	@Override
	public FwkTransferObject estmCmtmSvySave(final FwkParameterMap parameterMap) {
		// TODO Auto-generated method stub
		FwkTransferObject trans = new FwkTransferObject();

		// 저장 : T_ESTM_SVY_SCR
		parameterMap.put("P_DEL_AT", "N");
		
				
		//평가항목번호
		Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
		
		//평가점수
		Object P_ESTM_DTL_ITEM_POINT = parameterMap.get("P_ESTM_DTL_ITEM_POINT");
		
		if(P_ESTM_DTL_ITEM_NO != null){
			
			if(P_ESTM_DTL_ITEM_NO instanceof String){
				
				System.err.println("@@@ String @@@");
				
					// 평가설문조사점수 등록
					parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
					parameterMap.put("P_ESTM_SCR", parameterMap.get("P_ESTM_DTL_ITEM_POINT"));
					iproCommPopupDao.mergeIntoEstmSvyScr(parameterMap);   // T_ESTM_SVY_SCR
					
			}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
			
				System.err.println("@@@ ArrayList @@@");
				
				//평가항목번호
				ArrayList<String> P_ESTM_DTL_ITEM_NOList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
				//평가점수
				ArrayList<String> P_ESTM_DTL_ITEM_POINTList = (ArrayList<String>) P_ESTM_DTL_ITEM_POINT;
				
					for(int i =1; i < P_ESTM_DTL_ITEM_NOList.size(); i++){
						
						parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
						parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList.get(i));
						
						// 평가설문조사점수 등록
						iproCommPopupDao.mergeIntoEstmSvyScr(parameterMap);   // T_ESTM_SVY_SCR
						
					}
			
			}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
				System.err.println("@@@ String[] @@@");
				
				//평가항목번호
				String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
				//평가점수
				String[] P_ESTM_DTL_ITEM_POINTList = (String[]) P_ESTM_DTL_ITEM_POINT;
				
				for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
					parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
					parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList[idx]);

					// 평가설문조사점수 등록
					iproCommPopupDao.mergeIntoEstmSvyScr(parameterMap);   // T_ESTM_SVY_SCR
				}
			}
			
		}//P_ESTM_CMTM_NO != null
		
		return trans;
	}
}