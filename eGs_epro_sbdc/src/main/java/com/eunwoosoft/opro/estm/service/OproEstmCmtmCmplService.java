package com.eunwoosoft.opro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface OproEstmCmtmCmplService {
	
	FwkTransferObject estmCmtmCmplList(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplProcdADetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplProcdBDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplVidoMtngDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmCmplListExcelDwld(final FwkParameterMap parameterMap);
}
