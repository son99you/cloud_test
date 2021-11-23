package com.eunwoosoft.ipro.ebid.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEbidPblancPrcnService {
	
	/**
	 * 입찰공고목록 Key
	 */
	static final String BID_PBLANC_PRCN_LIST = "bidPblancPrcnList";
	
	/**
	 * 입찰공고목록 총건수
	 */
	static final String BID_PBLANC_PRCN_LIST_TOTCNT = "bidPblancPrcnListTotcnt";
	
	FwkTransferObject bidPblancPrcnListInqireWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject bidReqRtnStatUpdt(final FwkParameterMap parameterMap) throws Exception;
	//기술평가등록
	FwkTransferObject tchnEstmRegist(final FwkParameterMap parameterMap) throws Exception;
	//제안/규격서 등록
	FwkTransferObject tchnFileRegist(final FwkParameterMap parameterMap) throws Exception;
	//산출서 등록
	FwkTransferObject clcCntnFileRegist(final FwkParameterMap parameterMap) throws Exception;
	//협상결과 등록
	FwkTransferObject negoFileRegist(final FwkParameterMap parameterMap) throws Exception;
	//적격심사 등록
	FwkTransferObject elgbEstmRegist(final FwkParameterMap parameterMap) throws Exception;
	//엑셀다운로드
	FwkTransferObject bidPblancPrcnExcelList(final FwkParameterMap parameterMap) throws Exception;
	//개찰등록
	FwkTransferObject openResultRegist(final FwkParameterMap parameterMap) throws Exception;
}
