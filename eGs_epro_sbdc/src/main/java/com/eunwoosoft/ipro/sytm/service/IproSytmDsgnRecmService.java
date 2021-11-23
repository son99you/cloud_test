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
public interface IproSytmDsgnRecmService {

	static final String DSGN_RECM_LIST = "dsgnRecmList";  
	static final String DSGN_RECM_LIST_TOTCNT = "dsgnRecmListTotCnt";
	static final String DSGN_RECM_DETAIL = "dsgnRecmDetail";  
	static final String DSGN_RECM_FILE_LIST = "fileList";

	FwkTransferObject dsgnRecmListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject dsgnRecmFileDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject dsgnRecmFileRegist(final FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject dsgnRecmFileUpdate(FwkParameterMap parameterMap)throws Exception;
	
}
