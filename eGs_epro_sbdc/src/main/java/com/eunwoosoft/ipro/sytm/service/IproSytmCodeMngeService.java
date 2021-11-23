package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmCodeMngeService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 30.
 *
 */
public interface IproSytmCodeMngeService {

	static final String CODE_MNGE_LIST = "codeMngeList";
	static final String CODE_MNGE_LIST_TOTCNT = "codeMngeListTotCnt";
	
	FwkTransferObject codeMngeListWithPgng(final FwkParameterMap parameterMap);

	FwkTransferObject codeMngeListExcelDwld(final FwkParameterMap parameterMap);
	
}
