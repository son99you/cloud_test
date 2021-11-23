package com.eunwoosoft.opro.user.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.noti.service.impl.OproNotiGnrlServiceImpl;
import com.eunwoosoft.opro.user.dao.OproUserEstmCmtmInfoDao;
import com.eunwoosoft.opro.user.service.OproUserEstmCmtmInfoService; 


@Service(value="OproUserEstmCmtmInfoService")
public class OproUserEstmCmtmInfoServiceImpl  extends AbstractFwkService implements OproUserEstmCmtmInfoService{
	private static final Logger LOG = LoggerFactory.getLogger(OproUserEstmCmtmInfoServiceImpl.class); 
	
	@Resource(name="oproUserEstmCmtmInfoDao") 
	private OproUserEstmCmtmInfoDao oproUserEstmCmtmInfoDao;  
	
	
	@Override
	public FwkTransferObject estmCmtmInfoDetail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
//		System.err.println("@@@ session ==> " + session);
		
//		session.get("USR_ID")
//		session.get("USR_NM")
//		session.get("EMAL")
		
		parameterMap.put("P_ESTM_CMTM_NO", session.get("USR_ID"));
		
		FwkDataEntity estmCmtmInfoDetail = oproUserEstmCmtmInfoDao.estmCmtmInfoDetail(parameterMap);
		
//		System.out.println("서비스임플@@전" + estmGnrlDetail);
		trans.put("estmCmtmInfoDetail", estmCmtmInfoDetail	);
//		System.out.println("서비스임플@@후" + estmGnrlDetail);
		
		return trans;
	}
	
}
