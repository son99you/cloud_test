package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 예가관리 > 예가관리 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidPrprcManageService.java
 * 
 * </pre>
 * @date : 2015. 02. 23. 오후 07:03:53
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidPrprcManageService {

	/**
	 * 예가관리목록 Key
	 */
	static final String PRPRC_MANAGE_LIST = "prprcManageList";
	
	/**
	 * 예가관리목록 총건수
	 */
	static final String PRPRC_MANAGE_LIST_TOTCNT = "prprcManageListTotcnt";
	
	/**
	 * 입찰상세
	 */
	static final String BID_PRPRC_DETAIL = "bidPrprcDetail";
	
	/**
	 * 업체목록
	 */
	static final String BID_PRPRC_ENTRPS_LIST = "bidPrprcEntrpsList";
	
	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 검토 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcManageListInqireWithPgng
	 * @date : 2019. 02. 18.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 18.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prprcManageListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcManageDetailInqire
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prprcManageDetailInqire(final FwkParameterMap parameterMap);

}
