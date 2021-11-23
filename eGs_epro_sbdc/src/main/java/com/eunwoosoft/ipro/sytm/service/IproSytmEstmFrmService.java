package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmContFormService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
public interface IproSytmEstmFrmService {

	//static final String CONT_FORM_LIST = "contFormList";  
	
	FwkTransferObject dataBaseRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmFrmRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmFrmListWithPgng(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject licenseListWithPgng(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject licenseRegist(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject licenseDetail(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject licenseUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject licenseDelete(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject licenseIssue(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject licenseListExcelDwld(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmFrmDetail(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmFrmUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmFrmDelete(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject estmFrmListExcelDwld(final FwkParameterMap parameterMap) throws Exception;
	
	
	
	
	
}
