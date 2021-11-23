package com.eunwoosoft.ipro.stts.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.service
 * IproVendMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 20.
 *
 */
public interface IproSttsEstmMngService {
	
	FwkTransferObject sttsList(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmSttsListExcelDwld(final FwkParameterMap parameterMap) throws Exception;
	

}
