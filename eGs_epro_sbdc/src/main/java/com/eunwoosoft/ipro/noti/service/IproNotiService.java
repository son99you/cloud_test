package com.eunwoosoft.ipro.noti.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.service
 * IproNotiService.java
 * 
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface IproNotiService { 
	
	static final String NOTI_LIST = "notiList"; 
	static final String NOTI_LIST_TOTCNT = "notiListTotCnt";
	static final String NOTI_DETAIL = "notiDetail"; 
	static final String USR_INFO_DETAIL = "usrInfoDetail"; 

	FwkTransferObject notiListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject notiDetail(final FwkParameterMap parameterMap);
	 
	FwkTransferObject notiRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject notiDelete(final FwkParameterMap parameterMap);
	
	FwkTransferObject notiUpdt(final FwkParameterMap parameterMap)throws Exception;

	FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap);

	FwkTransferObject notiListExcelDwld(final FwkParameterMap parameterMap);
}
