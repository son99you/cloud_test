package com.eunwoosoft.comm.mail.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * SMS,mail 관련
 */
public interface CommMailService {
	
	
	
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
	 * @return
	 * @throws Exception
	 * 
	 */
	FwkTransferObject insertSendSms(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : smtp 메일 전송
	 *	</pre>
	 *
	 * @method Name : sendMail
	 * @Author : joo
	 * @Date   : 2020. 10. 20.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 20.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @throws Exception
	 *
	 */
	void sendMail(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 메일전송 또는 알림전송
	 *	</pre>
	 *
	 * @method Name : commSend
	 * @Author : joo
	 * @Date   : 2020. 12. 15.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 12. 15.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @throws Exception
	 *
	 */
	void commSend(final FwkParameterMap parameterMap, String gbn) throws Exception;
	
}