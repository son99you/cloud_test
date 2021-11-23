/*
 * Copyright eunwoo.co.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of eunwoo.co.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.comm.code.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 결재상태 서비스 인터페이스
 * 
 * @author : 
 * @date : 2017. 02. 02.
 * @version : 1.0
 */
public interface ComSancSttusService {
	
	/**
	 * 코드값목록 Key
	 */
	static final String SANC_INFO = "sancInfo";
	
	
	
	/**
     * <pre>
     * 1. 개요 : 결재상태정보
     * 2. 처리내용 :      
     * </pre>
     * @Method Name : sancSttusInfo
     * @date : 2017. 02. 02.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2017. 02. 02.    하성윤                    최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return {@link TransferObject}
     */
	FwkTransferObject sancSttusInfo(final FwkParameterMap parameterMap);
}
