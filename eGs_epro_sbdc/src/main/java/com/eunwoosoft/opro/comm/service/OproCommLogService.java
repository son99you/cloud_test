package com.eunwoosoft.opro.comm.service;

import javax.servlet.http.HttpServletRequest;



import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.acpr.service
 * OproAcprService.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 26.
 *
 */
public interface OproCommLogService { 
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : log등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : logInfoInsert
	 * @date : 2018.08.23
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	 2018.08.23.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */	
	void logInfoInsert(final FwkParameterMap parameterMap);
}
