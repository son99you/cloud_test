package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmVendMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 20.
 *
 */
public interface IproSytmVendMngeService {
	
	// 업체정보관리 목록
	static final String VEND_MNGE_LIST = "vendMngeList";
	// 업체정보관리 목록 총 건수
	static final String VEND_MNGE_LIST_TOTCNT = "vendMngeListTotcnt";
	// 협력사상세 - 기본정보
	static final String VEND_MNGE_DETAIL = "vendMngeDetail";
	
	/**
	 * <pre>
	 * 1.개요 : 업체정보관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.service.IproSytmVendMngeService.java
	 * @Method : vendMngeListInqireWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject vendMngeListInqireWithPgng(final FwkParameterMap parameterMap) throws Exception;
	/**
	 * <pre>
	 * 1.개요 : 업체정보관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.service.IproSytmVendMngeService.java
	 * @Method : vendMngeDetailInqire
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject vendMngeDetailInqire(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject vendMngeExcelList(final FwkParameterMap parameterMap) throws Exception;

}
