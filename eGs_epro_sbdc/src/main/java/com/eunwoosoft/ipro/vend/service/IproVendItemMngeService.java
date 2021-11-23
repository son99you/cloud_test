package com.eunwoosoft.ipro.vend.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.service
 * IproVendItemMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 22.
 *
 */
public interface IproVendItemMngeService {
	
	// 협력사별 품목관리 목록
	static final String VEND_ITEM_MNGE_LIST = "vendItemMngeList";

	// 협력사별 품목관리 목록 총 건수
	static final String VEND_ITEM_MNGE_LIST_TOTCNT = "vendItemMngeListTotcnt";

	/**
	 * <pre>
	 * 1.개요 : 협력사별품목관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.service.IproVendItemMngeService.java
	 * @Method : vendItemMngeListInqireWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 22. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject vendItemMngeListInqireWithPgng(final FwkParameterMap parameterMap) throws Exception;

}
