package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmIfcMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmIfcMngeService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmIfcMngeServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
@Transactional
@Service("iproSytmIfcMngeService")
public class IproSytmIfcMngeServiceImpl extends AbstractFwkService implements IproSytmIfcMngeService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmIfcMngeServiceImpl.class);
	
	@Resource(name="iproSytmIfcMngeDao")
	private IproSytmIfcMngeDao iproSytmIfcMngeDao;

	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmIfcMngeService#intfMngeListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject intfMngeListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmIfcMngeService.INTF_MNGE_LIST, iproSytmIfcMngeDao.selectIntfMngeListWithPgng(parameterMap));
		trans.put(IproSytmIfcMngeService.INTF_MNGE_LIST_TOTCNT, iproSytmIfcMngeDao.selectIntfMngeListTotCnt(parameterMap));
		return trans;
	}

}
