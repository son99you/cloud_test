package com.eunwoosoft.ipro.estm.service;

import javax.servlet.http.HttpServletResponse;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmMngPoolService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 02.
 *
 */
public interface IproEstmCmtmMngPoolService {

	FwkTransferObject cmtmMngPoolList(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmtmMngPoolListExcelDwld(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngPoolRegist(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject cmtmMngPoolDetail(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngPoolUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject cmtmMngPoolExcelUpld(final FwkParameterMap parameterMap, final HttpServletResponse response);// throws Exception

	void cmtmMngPoolDelete(final FwkParameterMap parameterMap);

}
