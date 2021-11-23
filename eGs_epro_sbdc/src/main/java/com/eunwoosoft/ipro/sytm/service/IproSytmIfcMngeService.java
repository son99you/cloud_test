package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmIfcMngeService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
public interface IproSytmIfcMngeService {
	
	// 인터페이스 목록
	static final String INTF_MNGE_LIST = "intfMngeList";
	// 인터페이스 목록 총 건수
	static final String INTF_MNGE_LIST_TOTCNT = "intfMngeListTotCnt";
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 인터페이스 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmIfcMngeService.java
	 * @Method : intfMngeListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject intfMngeListWithPgng(final FwkParameterMap parameterMap);

}
