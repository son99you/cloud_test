package com.eunwoosoft.opro.noti.service;

import javax.servlet.http.HttpServletRequest;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.service
 * IproNotiService.java
 * 
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface OproQnaService { 
	
	
	static final String QNA_LIST = "qnaList"; 
	static final String QNA_LIST_TOTCNT = "qnaListTotCnt";
	static final String QNA_DETAIL = "qnaDetail"; 
	static final String QNA_FILE_LIST = "fileList"; 
	static final String USR_INFO_DETAIL = "usrInfoDetail"; 

	FwkTransferObject qnaListWithPgng(FwkParameterMap parameterMap);

	FwkTransferObject qnaDetail(FwkParameterMap parameterMap);
	
	FwkTransferObject qnaRegist(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	
	FwkTransferObject qnaUpdt(FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject qnaDelete(final FwkParameterMap parameterMap);

	FwkTransferObject qnaListExcelDwld(final FwkParameterMap parameterMap);
	
	FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap);
}
