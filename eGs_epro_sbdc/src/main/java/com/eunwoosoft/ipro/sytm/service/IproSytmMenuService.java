package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmMenuService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
public interface IproSytmMenuService {
	
	static final String MENU_MGR_LRG_LIST = "menuMgrLrgList";
	// 메뉴관리 목록
	static final String MENU_MGR_LIST = "menuMgrList";
	// 메뉴관리 목록 총 건수
	static final String MENU_MGR_LIST_TOTCNT = "menuMgrListTotcnt";
	
	
	FwkTransferObject menuMgrListInqireWithPgng(final FwkParameterMap parameterMap) throws Exception;

	FwkTransferObject menuMgrListExcelDwld(final FwkParameterMap parameterMap) throws Exception;



}
