package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;





/**
 * 전자입찰 > 나의 협상통보 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidMyNtatDspthService.java
 * 
 * </pre>
 * @date : 2015. 03. 24. 오후 7:18:44
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface OproEbidMyNtatDspthService {

	/**
	 * 나의 협상통보 목록 Key
	 */
	static final String MY_NTAT_DSPTH_LIST = "myNtatDspthList";
	
	/**
	 * 나의 협상통보 목록 총건수
	 */
	static final String MY_NTAT_DSPTH_LIST_TOTCNT = "myNtatDspthListTotcnt";
	
	
	/**
	 * 나의 협상통보 상세
	 */
	static final String MY_NTAT_DSPTH_DETAIL =  "myNtatDspthDetail";
	
	/**
	 * 업체 심사 평가 목록
	 */
	
	static final String ENTRPS_JDGMN_EVL_LIST = "entrpsJdgmnEvlList";
	
	
	/**
	 * 입찰정보 상세 
	 */
	static final String BID_INFO_DETAIL =  "bidInfoDetail";
	
	/**
	 * 예가관리 상세
	 */
	static final String BID_PLAN_DETAIL= "bidPlanDetail";
	
	/**
	 * 최저 낙찰예정자 목록
	 */
	static final String LWET_SCSBID_PREARNGER_LIST = "lwetScsbidPrearngerList";
	
	/**
	 * 협상 낙찰 예정자 목록
	 */
	static final String NTAT_SCSBID_PREARNGER_LIST = "ntatScsbidPrearngerList";
	
	/**
	 * 적격심사업체 상세 조회
	 */
	static final String PROPER_JDGMN_ENTRPS_DETAIL ="properJdgmnEntrpsDetail";
	
	/**
	 * 사업참여이력 목록
	 */
	
	static final String BSNS_PARTCPTN_HIST_LIST = "bsnsPartcptnHistList";
	
	 
	
	/**
	 * 업체 정보 상세
	 */
	static final String ENTRPS_INFO_DETAIL = "entrpsInfoDetail";
	
	/**
	 * 예가선택 업체 조회
	 */
	static final String PRDPRC_CHOISE_ENTRPS_INQIRE = "prdprcChoiseEntrpsInqire";
	

	/**
	 * <pre>
	 * 1. 개요 : 나의 협상통보 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthListInqireWithPgng
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject myNtatDspthListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상통보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthDetailInqire
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject myNtatDspthDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상 통보 응답 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthRspnsRegist
	 * @date : 2015. 3. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 25.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */

	FwkTransferObject myNtatDspthRspnsRegist(final FwkParameterMap parameterMap);
	
}
