package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmLogMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
public interface IproSytmLogMngeService {
	
	// 로그관리 목록
	static final String LOG_MNGE_LIST = "logMngeList";
	// 로그관리 목록 총 건수
	static final String LOG_MNGE_LIST_TOTCNT = "logMngeListTotCnt";
	
	// 로그관리 상세
	static final String LOG_MNGE_DETAIL = "logMngeDetail";
	
	/**
	 * <pre>
	 * 1.개요 : 로그관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmLogMngeService.java
	 * @Method : logMngeListWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject logMngeListWithPgng(final FwkParameterMap parameterMap) throws Exception;

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmLogMngeService.java
	 * @Method : logMngeDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject logMngeDetail(final FwkParameterMap parameterMap);

	FwkTransferObject logMngeListExcelDwld(final FwkParameterMap parameterMap);

}
