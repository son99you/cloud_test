  package com.eunwoosoft.opro.noti.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.noti.service
 * OproNotiGnrlService.java
 * 
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface OproNotiGnrlService { 
	
	 
	static final String NOTI_GNRL_LIST = "notiGnrlList"; 
	static final String NOTI_GNRL_LIST_TOTCNT = "notiGnrlListTotCnt";
	static final String NOTI_GNRL_DETAIL = "notiGnrlDetail"; 

	FwkTransferObject notiGnrlListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject notiGnrlDetail(final FwkParameterMap parameterMap);

	FwkTransferObject notiListExcelDwld(final FwkParameterMap parameterMap);
	
}
