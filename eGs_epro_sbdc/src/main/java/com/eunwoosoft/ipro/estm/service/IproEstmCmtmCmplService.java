package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmCmplService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 01.
 *
 */
public interface IproEstmCmtmCmplService {

	FwkTransferObject estmCmtmCmplList(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmCmplListExcelDwld(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmCmplDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplObjDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplCmtmDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplProcdADetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplProcdBDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplResultDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmCmplResultDetailExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplVidoMtngDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplExbePayDetail(final FwkParameterMap parameterMap);

}
