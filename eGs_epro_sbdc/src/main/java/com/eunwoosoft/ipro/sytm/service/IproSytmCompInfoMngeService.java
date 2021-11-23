package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmCompInfoMngeService.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 27.
 *
 */
public interface IproSytmCompInfoMngeService {
	
	/**
	 * <pre>
	 * 1.개요 : 본사정보관리 등록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmCompInfoMngeService.java
	 * @Method : compInfoMngeRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject compInfoMngeRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 본사정보관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.service.IproSytmCompInfoMngeService.java
	 * @Method : compInfoMngeDetail
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	FwkTransferObject compInfoMngeDetail(final FwkParameterMap parameterMap) throws Exception;

}
