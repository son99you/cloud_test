package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmRnkSlctCmplService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 15.
 *
 */
public interface IproEstmCmtmRnkSlctCmplService {

	FwkTransferObject cmtmRnkSlctCmplList(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmRnkSlctCmplListExcelDwld(final FwkParameterMap parameterMap);
	
	

	FwkTransferObject cmtmRnkSlctCmplDetail(final FwkParameterMap parameterMap);
	
}
