package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmBatchMngeService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
public interface IproSytmBatchMngeService {
	
	// 배치조회 목록
	static final String BATCH_FORM_LIST = "batchFormList";
	// 배치조회 목록 총 건수
	static final String BATCH_FORM_LIST_TOTCNT = "batchFormListTotCnt";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 배치조회 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmBatchMngeService.java
	 * @Method : sytmBatchFormListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject sytmBatchFormListWithPgng(final FwkParameterMap parameterMap);

}
