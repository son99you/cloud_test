package com.eunwoosoft.ipro.noti.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.service
 * IproFaqService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
public interface IproFaqService { 
	
	static final String FAQ_LIST = "faqList"; 
	static final String FAQ_LIST_TOTCNT = "faqListTotCnt";
	static final String FAQ_DETAIL = "faqDetail";  
	static final String USR_INFO_DETAIL = "usrInfoDetail"; 

	FwkTransferObject faqListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject faqDetail(final FwkParameterMap parameterMap); 
	
	FwkTransferObject faqRegist(final FwkParameterMap parameterMap) throws Exception;
	 
	FwkTransferObject faqUpdt(final FwkParameterMap parameterMap)throws Exception;

	FwkTransferObject faqListExcelDwld(final FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject faqDelete(final FwkParameterMap parameterMap); 
	
	FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap); 
}
