package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 전자입찰 > 나의 적격심사 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidMyProperJdgmnService.java
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 10:02:21
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface OproEbidMyProperJdgmnService {


	/**
	 * 나의 적격심사 목록 Key
	 */
	static final String MY_PROPER_JDGMN_LIST = "myProperJdgmnList";
	
	/**
	 * 나의 적격심사 목록 총건수
	 */
	static final String MY_PROPER_JDGMN_LIST_TOTCNT = "myProperJdgmnListTotcnt";
	
	/**
	 * 나의 적격심사 상세
	 */
	static final String MY_PROPER_JDGMN_DETAIL =  "myProperJdgmnDetail";
	
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
	 * 1. 개요 : 나의 적격심사 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnListInqireWithPgng
	 * @date : 2015. 03. 23.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject myProperJdgmnListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnDetailInqire
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
	 * @throws SDBException 
	 */
	
	FwkTransferObject myProperJdgmnDetailInqire(final FwkParameterMap parameterMap) ;
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnRegist
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject myProperJdgmnRegist(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 통보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnScoreDspth
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject myProperJdgmnScoreDspth(final FwkParameterMap parameterMap);
	
}
