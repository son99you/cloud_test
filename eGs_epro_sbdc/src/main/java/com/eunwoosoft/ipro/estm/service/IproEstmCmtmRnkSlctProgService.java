package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmRnkSlctProgService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 12.
 *
 */
public interface IproEstmCmtmRnkSlctProgService {

	FwkTransferObject cmtmRnkSlctProgList(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmRnkSlctProgListExcelDwld(final FwkParameterMap parameterMap);
	

	FwkTransferObject cmtmRnkSlctProgDetail(final FwkParameterMap parameterMap);

	void cmtmPrioRnkAutoSlct(final FwkParameterMap parameterMap);

	void estmCmtmSlctNgrDetail(final FwkParameterMap parameterMap);
	
}
