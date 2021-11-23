package com.eunwoosoft.ipro.ebid.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.cont.service
 * IproContAccpnRsrvService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 2. 27.
 *
 */
public interface IproEbidBfStndOpenComplService {
	
	static final String BF_STND_OPEN_LIST = "bfStndOpenComplList";
	static final String BF_STND_OPEN_LIST_TOT_CNT = "bfStndOpenComplListTotCnt";
	
	static final String BF_STND_OPEN_DETAIL = "bfStndOpenComplDetail";
	static final String BF_STND_OPEN_ITEM_LIST = "bfStndOpenItemList";
	static final String BFAN_FILE = "bfanFile";
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전규격진행현황 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.cont.service.IproBfStndOpenService.java
	 * @Method : bfStndOpenComplList
	 * @author : 
	 * @date : 2020. 8. 25. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bfStndOpenPrcnList(final FwkParameterMap parameterMap);
	
	FwkTransferObject bfStndOpenComplExcelList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전규격완료현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.cont.service.IproBfStndOpenService.java
	 * @Method : bfStndOpenComplDetail
	 * @author : 
	 * @date : 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bfStndOpenComplDetail(final FwkParameterMap parameterMap);
}
