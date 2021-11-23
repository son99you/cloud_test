package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmMngProgService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 28.
 *
 */
public interface IproEstmCmtmMngProgService {

	FwkTransferObject cmtmMngProgList(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngProgListExcelDwld(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmMngMstClcRulSave(final FwkParameterMap parameterMap) throws Exception;
	
	void estmCmtmCntcObjList(final FwkParameterMap parameterMap);
	
	void estmCmtmCntcObjFileDown(final FwkParameterMap parameterMap);
	
	
	FwkTransferObject cmtmMngProgRegist(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject cmtmMngProgDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngProgObjDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmProgCmtmDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngProgUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject cmtmMngProgProcdADetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngProgResultDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmMngProgResultDetailExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngProgVidoMtngDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngProgVidoMtngSave(final FwkParameterMap parameterMap);

	void cmtmMngProgEstmEnd(final FwkParameterMap parameterMap);

}
