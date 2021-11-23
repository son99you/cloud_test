package com.eunwoosoft.ipro.info.dao;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * @author : 은우소프트 hong
 * @date : 2018. 02. 26.
 * @version : 1.0
 */
public interface IproInfoApprSetDao {


	/**
	 * <pre>
	 * 1. 개요 : 결재여부 등록 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : apprSetYnUpdt
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 hong
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 hong			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
  	void apprSetYnUpdt(final FwkParameterMap parameterMap);
  	
}
