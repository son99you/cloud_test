package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 전자입찰 > 입찰설명회 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidDcPeoService.java
 * 
 * </pre>
 * @date : 2015. 02. 13. 오전 11:35:35
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidDcPeoService {

	/**
	 * 입찰설명회 목록 Key
	 */
	static final String BID_DC_PEO_LIST = "bidDcPeoList";
	
	/**
	 * 입찰설명회 목록 총건수
	 */
	static final String BID_DC_PEO_LIST_TOTCNT = "bidDcPeoListTotcnt";
	
	/**
	 * 입찰정보 상세 
	 */
	static final String BID_DC_PEO_DETAIL =  "bidDcPeoDetail";
	
	/**
	 * 입찰설명회 참가업체 목록
	 */
	static final String BID_DC_PEO_PARTCPT_ENTRPS_LIST =  "bidDcPeoPartcptEntrpsList";
	
	/**
	 * 입찰설명회 참가업체 목록 총건수
	 */
	static final String BID_DC_PEO_PARTCPT_ENTRPS_LIST_TOTCNT = "bidDcPeoPartcptEntrpsListTotcnt";
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰설명회 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidDcPeoListInqireWithPgng
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
	FwkTransferObject bidDcPeoListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰정보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidDcPeoDetailInqire
	 * @date : 2015. 2. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidDcPeoDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가업체 등록폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsRegistForm
	 * @date : 2015. 2. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidPartcptEntrpsRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가업체 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsRegist
	 * @date : 2015. 2. 17.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 17.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidPartcptEntrpsRegist(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가업체 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsDelete
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
	
	FwkTransferObject bidPartcptEntrpsDelete(final FwkParameterMap parameterMap);

	
}
