package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmMngCmplService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 01.
 *
 */
public interface IproEstmCmtmMngCmplService {

	FwkTransferObject cmtmCmplList(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmCmplListExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplObjDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplCmtmDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplProcdADetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplProcdBDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplResultDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmMngCmplResultDetailExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCmplVidoMtngDetail(final FwkParameterMap parameterMap);

	/*FwkTransferObject estmCmplExbePayDetail(final FwkParameterMap parameterMap);*/

}
