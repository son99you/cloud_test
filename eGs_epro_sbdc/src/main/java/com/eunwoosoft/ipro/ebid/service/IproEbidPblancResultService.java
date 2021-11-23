package com.eunwoosoft.ipro.ebid.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEbidPblancResultService {
	
	/**
	 * 입찰공고목록결과 Key
	 */
	static final String BID_PBLANC_RESULT_LIST = "bidPblancResultList";
	
	/**
	 * 입찰공고목록결과 총건수
	 */
	static final String BID_PBLANC_RESULT_LIST_TOTCNT = "bidPblancResultListTotcnt";
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 입찰공고결과 목록 조회
	 *	</pre>
	 *
	 * @method Name : bidPblancResultListInqireWithPgng
	 * @Author : joo
	 * @Date   : 2020. 9. 22.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 22.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 *
	 */
	FwkTransferObject bidPblancResultListInqireWithPgng(final FwkParameterMap parameterMap);
	
	FwkTransferObject bidPblancResultExcelList(final FwkParameterMap parameterMap);
}
