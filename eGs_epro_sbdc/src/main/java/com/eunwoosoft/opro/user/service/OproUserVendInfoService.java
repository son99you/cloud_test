package com.eunwoosoft.opro.user.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.opro.user.service
 * OproUserVendInfoService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 8.
 *
 */
public interface OproUserVendInfoService {
	
	static final String VEND_INFO_DETAIL = "vendInfoDetail";
	static final String VEND_CHRGR_INFO_DETAIL = "vendChrgrInfoDetail";
	static final String VEND_ACCT_INFO_LIST = "vendAcctInfoList";
	static final String VEND_ACCT_INFO = "vendAcctInfo";
	static final String VEND_ACQS_INFO_LIST = "vendAcqsInfoList";
	static final String VEND_ITEM_LIST = "vendItemList";
	
	static final String VEND_ATCH_DOC_LIST = "vendAtchDocList";

	// 상세
	FwkTransferObject vendInfoDetail(final FwkParameterMap parameterMap);

	// 수정
	FwkTransferObject vendInfoUpdt(final FwkParameterMap parameterMap) throws Exception;

	// 비밀번호 변경
	FwkTransferObject vendPwdChngRegist(final FwkParameterMap parameterMap);
	
	FwkTransferObject bakFileSave(final FwkParameterMap parameterMap)throws Exception;

}
