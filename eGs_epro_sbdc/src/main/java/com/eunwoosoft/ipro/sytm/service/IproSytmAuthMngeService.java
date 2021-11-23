package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmAuthMngeService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
public interface IproSytmAuthMngeService {
	
	// 권한관리 목록
	static final String AUTH_MGR_LIST = "authMgrList";
	// 권한관리 목록 총 건수
	static final String AUTH_MGR_LIST_TOTCNT = "authMgrListTotcnt";
	
	/**
	 * <pre>
	 * 1.개요 : 권한관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmAuthMngeService.java
	 * @Method : authMgrListWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject authMgrListWithPgng(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject authMgrListExcelDwld(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 권한별 메뉴관리 목록 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : authMgrListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject authMenuMgrListWithPgng(final FwkParameterMap parameterMap);
	
	
	FwkTransferObject authMgrUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	
	
}
