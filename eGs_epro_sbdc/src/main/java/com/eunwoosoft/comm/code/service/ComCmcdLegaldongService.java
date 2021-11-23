/*
 * <pre>
 * Copyright (c) 2015 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.comm.code.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 지역정보 서비스 인터페이스
 * 
 * @author : 야긴스텍 정윤교
 * @date : 2015. 2. 17.
 * @version : 1.0
 */
public interface ComCmcdLegaldongService {

	/**
	 * 지역정보목록 Key
	 */
	static final String AREA_LIST = "areaList";

	/**
	 * <pre>
	 * 1. 개요 : 시도목록조회  
	 * 2. 처리내용 :      
	 * </pre>
	 * @Method Name : legaldongBrtcListInqire
	 * @date : 2015. 02. 17.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 17.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject legaldongBrtcListInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 시군구목록조회
	 * 2. 처리내용 :      
	 * </pre>
	 * @Method Name : legaldongSignguListInqire
	 * @date : 2015. 02. 17.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 17.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject legaldongSignguListInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 읍면동목록조회 
	 * 2. 처리내용 :      
	 * </pre>
	 * @Method Name : legaldongEmdListInqire
	 * @date : 2015. 02. 17.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 17.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject legaldongEmdListInqire(final FwkParameterMap parameterMap);

}
