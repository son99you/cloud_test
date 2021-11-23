package com.eunwoosoft.opro.user.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


public interface OproUserEstmCmtmInfoService {
	
	static final String ESTM_GNRL_LIST = "estmGnrlList"; 
	static final String ESTM_GNRL_LIST_TOTCNT = "estmGnrlListTotCnt";
	static final String ESTM_GNRL_DETAIL = "estmGnrlDetail";
	
	
	FwkTransferObject estmCmtmInfoDetail(final  FwkParameterMap parameterMap);
	

	
}
