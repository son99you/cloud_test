package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 전자입찰 > 입찰결과 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidMyResultService.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 13:55:11
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface OproEbidMyResultService {

	/**
	 * 나의 입찰결과 목록 Key
	 */
	static final String MY_BID_RESULT_LIST = "myBidResultList";
	
	/**
	 * 나의 입찰결과 목록 총건수
	 */
	static final String MY_BID_RESULT_LIST_TOTCNT = "myBidResultListTotcnt";
	
	/**
	 * 유찰 낙찰 예정자 목록
	 */
	static final String FIB_SCSBID_PREARNGER_LIST = "fibScsbidPrearngerList";
	
	/**
	 *  나의 입찰결과 상세 
	 */
	static final String MY_BID_RESULT_DETAIL =  "myBidResultDetail";
	
	/**
	 * 최저 낙찰예정자 목록
	 */
	static final String LWET_SCSBID_PREARNGER_LIST = "lwetScsbidPrearngerList";
	
	/**
	 * 협상 낙찰 예정자 목록
	 */
	static final String NTAT_SCSBID_PREARNGER_LIST = "ntatScsbidPrearngerList";
	
	/**
	 * 입찰 참여 업체 목록
	 */
	static final String BID_PARTCPTN_ENTRPS_LIST = "bidPartcptnEntrpsList";

	/**
	 * <pre>
	 * 1. 개요 : 입찰결과 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myBidResultListWithPgng
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
	FwkTransferObject myBidResultListWithPgng(final FwkParameterMap parameterMap);
	
	
		
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 입찰결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myBidResultDetailInqire
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
	
	FwkTransferObject myBidResultDetailInqire(final FwkParameterMap parameterMap);
	

	
}
