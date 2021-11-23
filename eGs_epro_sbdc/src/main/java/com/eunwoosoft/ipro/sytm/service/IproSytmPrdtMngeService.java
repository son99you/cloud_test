package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproSytmPrdtMngeService {

	static final String PRDT_MNG_LIST = "prdtMngList";
	
	static final String PRDT_MNG_LIST_TOTCNT = "prdtMngListTotcnt";
	
	static final String PRDT_MNG_DETAIL = "prdtMngDetail";

	 FwkTransferObject prdtMngeListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception;
	
	 FwkTransferObject prdtMngDetailInqire(final FwkParameterMap parameterMap)throws Exception;

	 FwkTransferObject prdtMngListExcelDwld(final FwkParameterMap parameterMap)throws Exception;

}
