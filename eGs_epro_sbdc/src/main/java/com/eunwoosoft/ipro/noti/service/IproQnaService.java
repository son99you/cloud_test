package com.eunwoosoft.ipro.noti.service;

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
public interface IproQnaService { 
	
	
	static final String QNA_LIST = "qnaList"; 
	static final String QNA_LIST_TOTCNT = "qnaListTotCnt";
	static final String QNA_DETAIL = "qnaDetail"; 
	static final String QNA_FILE_LIST = "fileList";  
	static final String QNA_REPLY_INFO = "qnaReplyInfo";
	static final String QNA_REPLY_LIST="qnaReplyList";
	static final String QNA_REPLY_LIST_TOTCNT ="qnaReplyListTotCnt";
	static final String USR_INFO_DETAIL = "usrInfoDetail";

	FwkTransferObject qnaListWithPgng(FwkParameterMap parameterMap);
	
	FwkTransferObject qnaDetail(FwkParameterMap parameterMap);
	
	FwkTransferObject qnaReplyRegist(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	 
	FwkTransferObject qnaReplyUpdt(FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject qnaReplyDelete(final FwkParameterMap parameterMap); 
	
	FwkTransferObject iproQnaReplyInfo(FwkParameterMap parameterMap);

	FwkTransferObject qnaListExcelDwld(FwkParameterMap parameterMap);

}
