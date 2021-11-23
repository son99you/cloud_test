package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 입찰관리 > 사전규격공개진행 서비스
 * <pre>
 * oda.iep.elbi.service 
 *    |_ IproEbidBfStndOpenService.java
 * 
 * </pre>
 */
public interface IproEbidBfStndOpenPrcnService {

	/**
	 * 사전규격공개진행 목록 Key
	 */
	static final String BF_STND_OPEN_PRCN_LIST = "bfStndOpenPrcnList";
	
	/**
	 * 사전규격공개진행 목록 총건수
	 */
	static final String BF_STND_OPEN_LIST_PRCN_TOT_CNT = "bfStndOpenPrcnListTotCnt";
	
	static final String BF_STND_OPEN_PRCN_DETAIL = "bfStndOpenPrcnDetail";
	static final String BF_STND_OPEN_ITEM_LIST = "bfStndOpenitemList";
	static final String BFAN_FILE = "bfanFile";

	FwkTransferObject bfStndOpenPrcnList(final FwkParameterMap parameterMap);
	
	FwkTransferObject bfStndOpenPrcnDetail(final FwkParameterMap parameterMap);
	
	FwkTransferObject bfStndOpenUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject bidBfanStatUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject bfStndOpenPrcnExcelList(final FwkParameterMap parameterMap) throws Exception;
}
