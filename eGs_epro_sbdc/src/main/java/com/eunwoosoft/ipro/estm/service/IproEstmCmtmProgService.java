package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmProgService.java
 *
 * @Author : 
 * @Date   : 2021. 3. 11.
 *
 */
public interface IproEstmCmtmProgService {
	

	FwkTransferObject estmCmtmProgListExcelDwld(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmMngCreateMeetingRoom(final FwkParameterMap parameterMap);
	

	FwkTransferObject estmCmtmProgList(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmProgDetail(final FwkParameterMap parameterMap);
	

	void estmDelete(final FwkParameterMap parameterMap);

	FwkTransferObject estmProgObjDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmProgCmtmDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmProgProcdADetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmProgProcdBDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmProgResultDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmCmtmProgResultDetailExcelDwld(final FwkParameterMap parameterMap);
	
	
	FwkTransferObject estmCmtmProgVidoMtngDetail(final FwkParameterMap parameterMap);

	void estmProgObjRegist(final FwkParameterMap parameterMap) throws Exception;

	void estmCntcObjList(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject getEstmSpheSecd(final FwkParameterMap parameterMap) throws Exception;



	
}
