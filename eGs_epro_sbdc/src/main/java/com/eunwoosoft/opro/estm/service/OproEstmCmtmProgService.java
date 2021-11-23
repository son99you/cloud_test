package com.eunwoosoft.opro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.estm.service
 * OproEstmCmtmProgService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 16.
 *
 */
public interface OproEstmCmtmProgService {

	FwkTransferObject estmCmtmProgList(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmProgListExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmProgDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmfileRegist(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmfileUpdt(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmProgProcdADetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmProgProcdBDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmProgVidoMtngDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmSign(final FwkParameterMap parameterMap);


}
