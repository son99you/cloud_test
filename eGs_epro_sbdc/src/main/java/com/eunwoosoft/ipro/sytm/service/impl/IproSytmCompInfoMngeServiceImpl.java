package com.eunwoosoft.ipro.sytm.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.service.IproSytmCompInfoMngeService;

/**
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmCompInfoMngeServiceImpl.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 27.
 *
 */

@Transactional
@Service("iproSytmCompInfoMngeService")
public class IproSytmCompInfoMngeServiceImpl extends AbstractFwkService implements IproSytmCompInfoMngeService{
	
	@Override
	public FwkTransferObject compInfoMngeRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		try {
			String bizRno = (String) parameterMap.get("P_BIZRNO_1") + (String) parameterMap.get("P_BIZRNO_2") + (String) parameterMap.get("P_BIZRNO_3");
			parameterMap.put("P_BIZRNO", bizRno);   // 사업자등록번호
			
			parameterMap.put("P_UPDUSR_ID", loginInfo.get("USER_ID"));
			parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 수정일시
			parameterMap.put("P_DELETE_AT", "N"); // 삭제여부
			parameterMap.put("P_CRTFC_AT", "N"); // 인증여부
			
			String entrpsRegistNo = (String) loginInfo.get("ENTRPS_REGIST_NO");
			System.err.println("entrpsRegistNo ==> " + entrpsRegistNo);
			parameterMap.put("P_ENTRPS_REGIST_NO", entrpsRegistNo);
			
			System.err.println("updateVendMngeUpdt START ========> ");
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== IproSytmCompInfoMngeServiceImpl.compInfoMngeRegist Error == ");
		}
		
		return trans;
		
	}

	@Override
	public FwkTransferObject compInfoMngeDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		String entrpsRegistNo = (String) loginInfo.get("ENTRPS_REGIST_NO");
		System.err.println("entrpsRegistNo ==> " + entrpsRegistNo);
		parameterMap.put("P_ENTRPS_REGIST_NO", entrpsRegistNo);
		
		return trans;
	}
	

}
