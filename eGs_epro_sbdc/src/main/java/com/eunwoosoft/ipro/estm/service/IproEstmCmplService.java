package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmplService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 01.
 *
 */
public interface IproEstmCmplService {

	FwkTransferObject estmCmplList(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmplListExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplObjDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplCmtmDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplProcdADetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplProcdBDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplResultDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgResultDetailExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplVidoMtngDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmplExbePayDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmplExbePaySave(final FwkParameterMap parameterMap);

	void estmCmplEndCnclUpdt(final FwkParameterMap parameterMap)throws Exception;
	
	

}
