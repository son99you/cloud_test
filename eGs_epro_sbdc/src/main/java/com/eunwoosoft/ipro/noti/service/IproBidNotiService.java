package com.eunwoosoft.ipro.noti.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.service
 * IproBidNotiService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 24.
 *
 */
public interface IproBidNotiService {
	
	static final String BID_NOTI_LIST = "bidNotiList"; 
	static final String BID_NOTI_LIST_TOTCNT = "bidNotiListTotCnt";
	static final String BID_NOTI_DETAIL = "bidNotiDetail"; 
	static final String USR_INFO_DETAIL = "usrInfoDetail"; 

	FwkTransferObject bidNotiListWithPgng(final FwkParameterMap parameterMap);

	FwkTransferObject bidNotiDetail(final FwkParameterMap parameterMap);

	FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap);

	FwkTransferObject bidNotiRegist(final FwkParameterMap parameterMap);

	FwkTransferObject bidNotiDelete(final FwkParameterMap parameterMap);

	FwkTransferObject bidNotiUpdt(final FwkParameterMap parameterMap);

}
