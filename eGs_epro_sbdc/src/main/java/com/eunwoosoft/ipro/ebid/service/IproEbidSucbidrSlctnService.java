package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;




/**
 * 전자입찰 > 낙찰자선정 서비스
 * <pre>
 * oda.iep.elbi.service 
 *    |_ IepElbiSucbidrSlctnService.java
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 10:02:21
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidSucbidrSlctnService {

	/**
	 * 낙찰자선정 목록 Key
	 */
	static final String SUCBIDR_SLCTN_LIST = "sucbidrSlctnList";
	
	/**
	 * 낙찰자선정 목록 총건수
	 */
	static final String SUCBIDR_SLCTN_LIST_TOTCNT = "sucbidrSlctnListTotcnt";
	
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
	 * 낙찰 예정자 목록
	 */
	static final String SCSBID_PREARNGER_LIST = "scsbidPrearngerList";
	
	/**
	 * 낙찰 예정자 목록 - 가능
	 */
	static final String SCSBID_PREARNGER_APPR_LIST = "scsbidPrearngerApprList";
	
	/**
	 * 사업참여이력 목록
	 */
	static final String BSNS_PARTCPTN_HIST_LIST = "bsnsPartcptnHistList";
	
	/**
	 * 업체 심사 평가 목록
	 */
	static final String ENTRPS_JDGMN_EVL_LIST = "entrpsJdgmnEvlList"; 
	
	/**
	 * 업체 정보 상세
	 */
	static final String ENTRPS_INFO_DETAIL = "entrpsInfoDetail";
	
	/**
	 * 품목별 세부입찰 내역 조회
	 */
	static final String PRDLST_ACCTO_DETAIL_BID_DTLS_INQIRE ="prdlstAcctoDetailBidDtlsInqire";
	
	/**
	 * 예가선택 업체 조회
	 */
	static final String PRDPRC_CHOISE_ENTRPS_INQIRE = "prdprcChoiseEntrpsInqire";
	
	/**
	 * 부적격 업체
	 */
	static final String NT_ELGB_VEND_LIST = "ntElgbVendList";
	
	/**
	 *	복수예가 목록
	 */
	static final String COMPNO_PRDPRC_LIST = "compnoPrdprcList";

	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnListInqireWithPgng
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject sucbidrSlctnListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnDetailInqire
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject sucbidrSlctnDetailInqire(final FwkParameterMap parameterMap);
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlRegistForm
	 * @date : 2015. 3. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject properJdgmnEvlRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 결격사유 등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnBrdoResnRegistForm
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject sucbidrSlctnBrdoResnRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상통보 등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ntatDspthRegistForm
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject ntatDspthRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 결격사유 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnBrdoResnRegist
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject sucbidrSlctnBrdoResnRegist(final FwkParameterMap parameterMap); 
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상 통보 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ntatDspthRegist
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject ntatDspthRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 이메일전송대상 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : emailTrnsmisTrgetRegist
	 * @date : 2015. 3. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject emailTrnsmisTrgetRegist(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  적격심사 평가등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlRegist
	 * @date : 2015. 3. 20.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 20.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject properJdgmnEvlRegist(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 예가선택업체 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultPrdprcChoiseEntrpsInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidResultPrdprcChoiseEntrpsInqire(final FwkParameterMap parameterMap);
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidInfoUpdt
	 * @date : 2015. 4. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject sucbidInfoUpdt(final FwkParameterMap parameterMap);
	
	FwkTransferObject ebidApprSendNego(final FwkParameterMap parameterMap);
	
	FwkTransferObject sameRankLot (final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰 - 오프라인 업체 입력
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : scsBidOfflineInsert
	 * @date : 2019. 03. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	FwkTransferObject scsBidOfflineInsert(final FwkParameterMap parameterMap);
	
	
}
