package com.eunwoosoft.ipro.info.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * 
 * </pre>
 * @date : 2018. 02. 26
 * @version : 1.0
 * @author : 은우소프트 hong
 */
public interface IproInfoApprSetService {
	
	public static final String APPR_YN_DETAIL = "apprYnDetail";
	
	/**
	 * <pre>
	 * 1. 개요 : 결재설정 여부 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : getApprSetYnList
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject getApprSetYnDetail(final FwkParameterMap parameterMap)throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 결재여부 저장
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : apprSetRegist
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void apprSetRegist(final FwkParameterMap parameterMap)throws Exception;
	
}
