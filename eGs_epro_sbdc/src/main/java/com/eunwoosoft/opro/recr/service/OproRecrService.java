package com.eunwoosoft.opro.recr.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.recr.service
 * OproRecrService.java
 * 
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface OproRecrService { 
	
	 
	static final String RECR_GNRL_LIST = "recrGnrlList"; 
	static final String RECR_GNRL_LIST_TOTCNT = "recrGnrlListTotCnt";
	static final String RECR_GNRL_DETAIL = "recrGnrlDetail"; 
	static final String ESTM_GNRL_DETAIL = "estmGnrlDetail";
	static final String RECR_ANNC_DETAIL = "recrAnncDetail"; 
	static final String ESTM_RSDN_NO = "estmObjRsdnNo";
	static final String ESTM_PENO_YN="estmObjPeNoYn";
/*	static final Stringt RECR_GNRL_INFO_DETAIL = "recrGnrlInfoDetail";*/
	
	FwkTransferObject recrGnrlListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject recrAnncListExcelDwld(final FwkParameterMap parameterMap);
			
	
	FwkTransferObject recrGnrlDetail(final FwkParameterMap parameterMap);

	FwkTransferObject recrAnncReg(final FwkParameterMap parameterMap)throws Exception;

	FwkTransferObject recrAnncEstmReg(final FwkParameterMap parameterMap)throws Exception;

	FwkTransferObject recrAnncCheck(final FwkParameterMap parameterMap)throws Exception;

	FwkTransferObject recrUpdt(final FwkParameterMap parameterMap)throws Exception;



	
}
