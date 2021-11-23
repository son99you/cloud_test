package com.eunwoosoft.ipro.noti.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.trm.service
 * IprotrmService.java
 * 
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface IproTrmService { 
	
	static final String TRM_LIST = "trmList"; 
	static final String TRM_LIST_TOTCNT = "trmListTotCnt";
	static final String TRM_DETAIL = "trmDetail"; 
	static final String USR_INFO_DETAIL = "usrInfoDetail"; 

	FwkTransferObject trmListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject trmDetail(final FwkParameterMap parameterMap);
	 
	FwkTransferObject trmRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject trmDelete(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject trmUpdt(final FwkParameterMap parameterMap)throws Exception;

	FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject trmDataAll(final FwkParameterMap parameterMap);
	
	FwkTransferObject trmCnt(final FwkParameterMap parameterMap);

	FwkTransferObject trmListExcelDwld(final FwkParameterMap parameterMap);
}
