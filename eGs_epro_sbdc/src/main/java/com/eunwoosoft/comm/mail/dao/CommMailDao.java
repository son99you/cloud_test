package com.eunwoosoft.comm.mail.dao;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * SMS, mail 관련
 */
public interface CommMailDao {
	/**
	 *  
	 * 
	 * 	<pre>
	 *  1. 개요 : MESSENGER 전송
	 * 	2. 처리내용 : M_NOTICE_MSG INSERT 한다. 
	 *	</pre>
	 *
	 * @method Name : insertSendSms
	 * @Author : joo
	 * @Date   : 2020. 10. 15.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 15.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 *
	 */
	void insertSendSms(final FwkParameterMap parameterMap);

	//T_MM_MSG 조회 
	FwkDataEntity getContents(FwkParameterMap paramMap);
	FwkDataEntity selectBidInfoDetail(FwkParameterMap paramMap);
	
}
