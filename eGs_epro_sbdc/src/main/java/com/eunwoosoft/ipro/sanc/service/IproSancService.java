package com.eunwoosoft.ipro.sanc.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * IproSancService
 * 
 * @author juyeon_Lee
 *
 */
public interface IproSancService {
	// 결재정보저장
	public FwkTransferObject apprInfoRegist(final FwkParameterMap parameterMap) throws Exception;
	// 일괄결재정보저장
	public FwkTransferObject apprInfoRegistAll(final FwkParameterMap parameterMap) throws Exception;
	
}
