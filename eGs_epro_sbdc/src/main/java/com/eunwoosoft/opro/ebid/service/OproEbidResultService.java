package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 전자입찰 > 입찰결과 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidResultService.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 13:55:11
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface OproEbidResultService {

	/**
	 * 입찰결과 목록 Key
	 */
	static final String BID_RESULT_LIST = "bidResultList";
	
	/**
	 * 입찰결과 목록 총건수
	 */
	static final String BID_RESULT_LIST_TOTCNT = "bidResultListTotcnt";
	
	/**
	 * 유찰 낙찰 예정자 목록
	 */
	static final String FIB_SCSBID_PREARNGER_LIST = "fibScsbidPrearngerList";
	
	/**
	 * 입찰 정보 상세
	 */
	static final String BID_INFO_DETAIL = "bidInfoDetail";
	
	/**
	 * 협상 낙찰 예정자 목록
	 */
	static final String NTAT_SCSBID_PREARNGER_LIST = "ntatScsbidPrearngerList";
	
	/**
	 * 최저 낙찰예정자 목록
	 */
	static final String LWET_SCSBID_PREARNGER_LIST = "lwetScsbidPrearngerList";
	
	/**
	 * 낙찰예정자 목록
	 */
	static final String SCSBID_PREARNGER_LIST = "scsbidPrearngerList";
	
	/**
	 * 적격심사업체 상세 조회
	 */
	static final String PROPER_JDGMN_ENTRPS_DETAIL ="properJdgmnEntrpsDetail";
	
	/**
	 * 사업참여이력 목록
	 */
	static final String BSNS_PARTCPTN_HIST_LIST = "bsnsPartcptnHistList";
	
	/**
	 * 업체 심사 평가 목록
	 */
	static final String ENTRPS_JDGMN_EVL_LIST = "entrpsJdgmnEvlList"; 
	
	/**
	 * 업체 예가 선택 조회
	 */
	static final String PRDPRC_CHOISE_ENTRPS_INQIRE = "prdprcChoiseEntrpsInqire";
	
	/**
	 * 업체 정보
	 */
	static final String ENTRPS_INFO_DETAIL = "entrpsInfoDetail";
	
	/**
	 * 입찰 품목 목록
	 */
	static final String BID_THNG_LIST = "bidThngList";
	
	/**
	 * 품목별 세부입찰 내역 조회
	 */
	static final String PRDLST_ACCTO_DETAIL_BID_DTLS_INQIRE = "prdlstAcctoDetailBidDtlsInqire";
	
	/**
	 * 제안서 조회
	 */
	static final String ENATPA_INQIRE = "enatpaInqire";
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰결과 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultListInqireWithPgng
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject bidResultListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultDetailInqire
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidResultDetailInqire(final FwkParameterMap parameterMap);
}
