package com.eunwoosoft.ipro.estm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.estm.service
 * IproEstmProgService.java
 *
 * @Author : 
 * @Date   : 2021. 3. 11.
 *
 */
public interface IproEstmProgService {
	
	FwkTransferObject estmProgList(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgListExcelDwld(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgRegist(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject estmProgDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject createMeetingRoom(final FwkParameterMap parameterMap);

	void estmDelete(final FwkParameterMap parameterMap);
	
	void estmRsdnNoDelete(final FwkParameterMap parameterMap);
	

	FwkTransferObject estmProgObjDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject excelObjUpload(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject estmProgCmtmDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgProcdADetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgProcdBDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmMngMstClcRulSave(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmProgResultDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgResultDetailExcelDwld(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgVidoMtngDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmProgVidoMtngSave(final FwkParameterMap parameterMap) throws Exception;

	void estmPscdUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject estmProgUpdt(final FwkParameterMap parameterMap) throws Exception;

	void estmProgObjRegist(final FwkParameterMap parameterMap) throws Exception;
	
	void estmProgObjDelete(final FwkParameterMap parameterMap) throws Exception;

	void estmCntcObjList(final FwkParameterMap parameterMap) throws Exception;

	void estmObjFileUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	void estmVidoFileUpdt(final FwkParameterMap parameterMap) throws Exception;

	void estmProcdPscdUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject getEstmSpheSecd(final FwkParameterMap parameterMap) throws Exception;

	void estmCmtmSlctYnUpdt(final FwkParameterMap parameterMap);

	void estmCmtmAutoSlct(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmSetDetail(final FwkParameterMap parameterMap);

	void estmCmtmRegist(final FwkParameterMap parameterMap);

	FwkTransferObject estmStPsblYnChck(final FwkParameterMap parameterMap);

	void estmCmtmSlctCmplYnUpdt(final FwkParameterMap parameterMap);

	FwkTransferObject estmSecdProcdSetList(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmSecdSetList(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmEnd(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmASlct(final FwkParameterMap parameterMap);
	
	void estmCmtmReAutoSlct(final FwkParameterMap parameterMap);

	void estmCmtmRsdnNoRegist(final FwkParameterMap parameterMap);

	void estmCmtmHldfYnUpdt(final FwkParameterMap parameterMap);

	FwkTransferObject estmStUpdt(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject cmtmRsdnNoRegistPsblChck(final FwkParameterMap parameterMap);

}