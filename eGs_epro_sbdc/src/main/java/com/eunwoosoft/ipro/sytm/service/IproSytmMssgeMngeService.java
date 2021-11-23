package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmMssgeMngeService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
public interface IproSytmMssgeMngeService {
	
	// 메세지관리 목록
	static final String MSSGE_LOG_LIST = "mssgeLogList";
	// 메세지관리 목록 총 건수
	static final String MSSGE_LOG_LIST_TOTCNT = "mssgeLogListTotCnt";
	
	// 메세지관리 상세
	static final String MSSGE_LOG_DETAIL = "mssgeLogDetail";
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmMssgeMngeService.java
	 * @Method : mssgeLogListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject mssgeLogListWithPgng(final FwkParameterMap parameterMap) throws Exception;

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmMssgeMngeService.java
	 * @Method : mssgeLogDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject mssgeLogDetail(final FwkParameterMap parameterMap);

}
