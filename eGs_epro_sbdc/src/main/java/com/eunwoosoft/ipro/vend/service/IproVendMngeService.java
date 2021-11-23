package com.eunwoosoft.ipro.vend.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.service
 * IproVendMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 20.
 *
 */
public interface IproVendMngeService {
	
	// 협력사현황 목록
	static final String VEND_MNGE_LIST = "vendMngeList";
	// 협력사현황 목록 총 건수
	static final String VEND_MNGE_LIST_TOTCNT = "vendMngeListTotcnt";
	// 협력사상세 - 기본정보
	static final String VEND_MNGE_DETAIL = "vendMngeDetail";
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.service.IproVendMngeService.java
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
	 * 1.개요 : 협력사등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.service.IproVendMngeService.java
	 * @Method : vendMngeRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject vendMngeRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.service.IproVendMngeService.java
	 * @Method : vendMngeDetailInqire
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject vendMngeDetailInqire(final FwkParameterMap parameterMap) throws Exception;

	/**
	 * <pre>
	 * 1.개요 : 협력사현황 수정 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.service.IproVendMngeService.java
	 * @Method : vendMngeUpdt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject vendMngeUpdt(final FwkParameterMap parameterMap) throws Exception;
}
