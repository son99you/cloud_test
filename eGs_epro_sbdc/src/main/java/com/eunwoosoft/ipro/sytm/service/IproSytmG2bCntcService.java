package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmG2bCntcService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 22.
 *
 */
public interface IproSytmG2bCntcService {
	
	// 나라장터연계이력 목록
	static final String G2B_CNTC_HSTY_LIST = "g2bCntcHstyList";
	// 나라장터연계이력 목록 총 건수
	static final String G2B_CNTC_HSTY_LIST_TOTCNT = "g2bCntcHstyListTotCnt";
	
	// 나라장터연계이력 상세
	static final String G2B_CNTC_HSTY_DETAIL = "g2bCntcHstyDetail";
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmG2bCntcService.java
	 * @Method : g2bCntcHstyList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject g2bCntcHstyList(final FwkParameterMap parameterMap) throws Exception;

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.service.IproSytmG2bCntcService.java
	 * @Method : g2bCntcHstyDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject g2bCntcHstyDetail(final FwkParameterMap parameterMap);

}
