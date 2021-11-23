package com.eunwoosoft.ipro.sytm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.sytm.service
 * IproSytmEstmSpheMngService.java
 *
 * @Author : 
 * @Date   : 2021. 4. 09.
 *
 */
public interface IproSytmEstmSpheMngService {

	FwkTransferObject estmSpheMngList(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmSpheMngDetail(final FwkParameterMap parameterMap);

	void estmSpheMngRegist(final FwkParameterMap parameterMap);

	void estmSpheMngUpdt(final FwkParameterMap parameterMap);

	void estmSpheMngDelete(final FwkParameterMap parameterMap);


}
