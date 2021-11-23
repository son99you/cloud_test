package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmCmtmSpheMpgService.java
 *
 * @Author : 
 * @Date   : 2021. 3. 11.
 *
 */
public interface IproEstmCmtmSpheMpgService {
	
	FwkTransferObject estmCmtmSpheMpgList(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmCmtmSpheMpgDetail(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmCmtmSpheMpgItemList(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmCmtmSpheMpgRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmCmtmSpheMpgUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject cmtmSpheMpgListExcelDwld(final FwkParameterMap parameterMap);
	
	
	
	
}
