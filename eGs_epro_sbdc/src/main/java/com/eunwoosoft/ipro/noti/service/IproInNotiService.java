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
public interface IproInNotiService { 
	
	
	static final String IN_NOTICE_BOARD_LIST = "inNoticeBoardList"; 
	static final String IN_NOTICE_BOARD_LIST_TOTCNT = "inNoticeBoardListTotCnt";
	static final String IN_NOTICE_BOARD_DETAIL = "inNoticeBoardDetail";  

	FwkTransferObject inNoticeBoardListWithPgng(FwkParameterMap parameterMap);
	
	FwkTransferObject inNoticeBoardDetail(FwkParameterMap parameterMap);
	
	FwkTransferObject inNoticeBoardRegist(FwkParameterMap parameterMap,final HttpServletRequest request) throws Exception;
	 
	FwkTransferObject inNoticeBoardUpdt(FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject inNoticeBoardDelete(final FwkParameterMap parameterMap); 
}
