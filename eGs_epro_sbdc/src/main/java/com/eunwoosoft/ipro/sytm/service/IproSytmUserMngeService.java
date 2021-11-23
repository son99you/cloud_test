package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmUserMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 26.
 *
 */
public interface IproSytmUserMngeService {
	
	// 사용자관리 목록
	static final String USER_MGR_LIST = "userMgrList";

	// 사용자관리 목록 총 건수
	static final String USER_MGR_LIST_TOTCNT = "userMgrListTotcnt";
	
	// 사용자관리 상세
	static final String USER_MGR_DETAIL = "userMgrDetail"; 
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService.java
	 * @Method : userMgrListWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject userMgrListWithPgng(final FwkParameterMap parameterMap) throws Exception;
	
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService.java
	 * @Method : userMgrDetailInqire
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject userMgrDetailInqire(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject userMgrExcelList(final FwkParameterMap parameterMap) throws Exception;
	
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 수정 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService.java
	 * @Method : userMgrUpdt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject userMgrUpdt(final FwkParameterMap parameterMap) throws Exception;
	
}
