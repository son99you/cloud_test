package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmEstmSeMngService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
public interface IproSytmEstmSeMngService {

	FwkTransferObject estmSeMngListWithPgng(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmSeMngRegForm(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmSeMngRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmSeMngDetail(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmSeMngDelete(final FwkParameterMap parameterMap) throws Exception;
	
	
	FwkTransferObject estmSeMngClcRulSave(final FwkParameterMap parameterMap) throws Exception;
	
	
	FwkTransferObject estmSeMngUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject estmSeMngListExcelDwld(final FwkParameterMap parameterMap) throws Exception;
	
}