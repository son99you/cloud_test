package com.eunwoosoft.ipro.appr.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 결재관리
 * 
 * <pre>
 * com.eunwoosoft.ipro.appr.service 
 *    |_ IproApprMngService.java
 * 
 * </pre>
 */
public interface IproApprMngService {
	
	FwkTransferObject apprRegList(final FwkParameterMap parameterMap);

	void apprRegist(final FwkParameterMap parameterMap);

	void applMstDelete(final FwkParameterMap parameterMap);
	
	FwkTransferObject apprAprpList(final FwkParameterMap parameterMap);
	
	void updateApplAprp(final FwkParameterMap parameterMap);

	
	/**
	 * 결재대상 목록, 상세
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject apprObjList(final FwkParameterMap parameterMap);
	FwkTransferObject apprObjDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 결재여부
	 * @param parameterMap
	 * @throws Exception
	 */
	void apprYnUpdate(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 결재대상_상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getApprTarget
	 * @date : 2018. 03. 26.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 26.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */	
	public FwkTransferObject getApprTarget(final FwkParameterMap parameterMap);
	
	/**
	 * 결재완료 목록
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject apprCmplList(final FwkParameterMap parameterMap);
}
