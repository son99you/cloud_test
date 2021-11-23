package com.eunwoosoft.ipro.noti.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.service
 * IproRssService.java
 * 
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface IproRssService { 
	
	static final String RSS_LIST = "rssList"; 
	static final String RSS_LIST_TOTCNT = "rssListTotCnt";
	static final String RSS_DETAIL = "rssDetail";  
	static final String USR_INFO_DETAIL = "usrInfoDetail"; 

	FwkTransferObject rssListWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject rssDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject rssRegist(final FwkParameterMap parameterMap) throws Exception;
	 
	FwkTransferObject rssUpdt(final FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject rssDelete(final FwkParameterMap parameterMap); 
	
	FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap); 
}
