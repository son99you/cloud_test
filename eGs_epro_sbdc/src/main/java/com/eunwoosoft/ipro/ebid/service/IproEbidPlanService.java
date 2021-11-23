package com.eunwoosoft.ipro.ebid.service; 

import java.io.IOException;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.ebid.service
 * IproEbidPlanService.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 3. 2.
 *
 */
public interface IproEbidPlanService {
	
	/**
	 * 입찰계획목록 Key
	 */
	static final String BID_PLAN_LIST = "bidPlanList";
	
	/**
	 * 입찰계획목록 총건수
	 */
	static final String BID_PLAN_LIST_TOTCNT = "bidPlanListTotcnt";
	
	/**
	 * 입찰계획상세
	 */
	static final String BID_PLAN_DETAIL = "bidPlanDetail";
	
	/**
	 * 입찰품목 목록 조회
	 */
	static final String BID_PRDLS_LIST = "bidPrdlsList";
	
	/**
	 * 입찰지명업체 조회
	 */
	static final String BID_NMFPC_ENTRPS_LIST = "bidNmfpcEntrpsList";
	
	/**
	 * 입찰첨부문서 조회
	 */
	static final String BID_ATCH_DOC = "bidAtchDoc";
	
	/**
	 * 입찰첨부문서 목록 조회
	 */
	static final String BID_ATCH_DOC_LIST = "bidAtchDocList";
	
	/**
	 * 로그인 사용자 정보
	 */
	static final String LOGIN_EMPLYR_INFO = "loginEmplyrInfo";
	
	/**
	 * 입찰규정및 지침 첨부파일
	 */
	static final String BIREND_MANUAL_ADD_FILE_LIST = "birendManualAddFileList";
	
	/**
	 *  유찰 목록
	 */
	static final String FIB_LIST = "fibList";
	
	/**
	 *  유찰 목록 총건수
	 */
	static final String FIB_LIST_TOTCNT = "fibListTotcnt";
	
	/**
	 *  사전규격공개 정보
	 */
	static final String BFAN_INFO = "bfanInfo";
	
	/**
	 *  사전규격공개 정보
	 */
	static final String ELGB_ESTM_KD = "elgbEstmKd";
	
	/**
	 *  사전규격공개 정보
	 */
	static final String PA_ITEM_LIST = "paItemList";
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanListInqireWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 * @throws IOException
	 */
	FwkTransferObject bidPlanRegist(final FwkParameterMap parameterMap) throws IOException;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanDetailInqire
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성대기현황 등록 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 수정 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanUpdtForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 현재 진행중인 재공고건이 있는지 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : reAnnoCheck
	 * @author : 하성윤
	 * @date : 2019. 8. 8. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject reAnnoCheck(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @throws Exception
	 */
	FwkTransferObject bidPlanUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidPlanDelete
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 */
	void bidPlanDelete(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 상태 변경
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : bidSttusChange
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 */
	void bidSttusChange(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 유찰 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPlanService.java
	 * @Method : fibListInqireWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject fibListInqireWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject ebidApprSendPlan(final FwkParameterMap parameterMap);
	
	FwkTransferObject elgbEstmKd(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 입찰대기목록 아이템 리스트 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPaItemList
	 * @date : 2019. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 08.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	FwkTransferObject selectPaItemList(final FwkParameterMap parameterMap);
}
