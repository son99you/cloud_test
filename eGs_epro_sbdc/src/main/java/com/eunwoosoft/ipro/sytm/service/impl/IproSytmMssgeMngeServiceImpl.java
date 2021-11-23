package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmMssgeMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmMssgeMngeService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmMssgeMngeServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
@Transactional
@Service("iproSytmMssgeMngeService")
public class IproSytmMssgeMngeServiceImpl extends AbstractFwkService implements IproSytmMssgeMngeService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmMssgeMngeServiceImpl.class);
	
	@Resource(name="iproSytmMssgeMngeDao")
	private IproSytmMssgeMngeDao iproSytmMssgeMngeDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmMssgeMngeService#mssgeLogListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject mssgeLogListWithPgng(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmMssgeMngeService.MSSGE_LOG_LIST, iproSytmMssgeMngeDao.selectMssgeLogListWithPgng(parameterMap));
		trans.put(IproSytmMssgeMngeService.MSSGE_LOG_LIST_TOTCNT, iproSytmMssgeMngeDao.selectMssgeLogListTotCnt(parameterMap));
		
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmMssgeMngeService#mssgeLogDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject mssgeLogDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmMssgeMngeService.MSSGE_LOG_DETAIL, iproSytmMssgeMngeDao.selectMssgeLogDetail(parameterMap));
		
		return trans;
	}
}
