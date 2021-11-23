package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 전자입찰 > 입찰참가현황 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidPartcptSttusService.java
 * 
 * </pre>
 * @date : 2015. 02. 23. 오후 05:03:21
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidPartcptSttusService {

	/**
	 * 입찰참가현황 목록 Key
	 */
	static final String BID_PARTCPT_STTUS_LIST = "bidPartcptSttusList";
							
	
	/**
	 * 입찰참가현황 목록 총건수
	 */
	static final String BID_PARTCPT_STTUS_LIST_TOTCNT = "bidPartcptSttusListTotcnt";
	
	/**
	 * 입찰참가현황 상세 
	 */
	static final String BID_PARTCPT_STTUS_DETAIL =  "bidPartcptSttusDetail";
	
	/**
	 * 참가업체 목록
	 */																				
	static final String BID_PARTCPT_ENTRPS_LIST =  "bidPartcptEntrpsList";
	
	/**
	 * 입찰포기신청서 상세 조회
	 */
	static final String BID_ABANDN_REQSTDOC_INQIRE = "bidAbandnReqstdocInqire";
	
	/**
	 * 입찰참가신청서 상세 조회
	 */
	static final String BID_PARTCPT_REQSTDOC_INQIRE = "bidPartcptReqstdocInqire";
	
	/**
	 * 청렴이행각서 조회
	 */
	static final String INTGTY_FLFL_MMRD_INQIRE = "intgtyFlflMmrdInqire";
	
	/**
	 * 입찰보증 정보 조회
	 */
	static final String BID_ASSRNC_INFO_INQIRE = "bidAssrncInfoInqire";
	/**
	 * 업체 보증 정보 목록
	 */
	static final String ENTRPS_GRNTY_INFO_LIST = "entrpsGrntyInfoList";
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰참가현황 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptSttusListInqireWithPgng
	 * @date : 2015. 02. 13.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject bidPartcptSttusListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가현황 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptSttusDetailInqire
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidPartcptSttusDetailInqire(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰포기신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAbandnReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidAbandnReqstdocInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	
	FwkTransferObject bidPartcptReqstdocInqire(final FwkParameterMap parameterMap) ; 
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intgtyFlflMmrdInqire
	 * @date : 2015. 2. 27.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject intgtyFlflMmrdInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAssrncInfoInqire
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidAssrncInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAssrncInfoRegist
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidAssrncInfoRegist(final FwkParameterMap parameterMap);
	
	
}
