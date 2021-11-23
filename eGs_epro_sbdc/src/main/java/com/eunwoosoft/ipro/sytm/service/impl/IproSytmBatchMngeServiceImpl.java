package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmBatchMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmBatchMngeService;

@Transactional
@Service("iproSytmBatchMngeService")
public class IproSytmBatchMngeServiceImpl extends AbstractFwkService implements IproSytmBatchMngeService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmBatchMngeServiceImpl.class);
	
	@Resource(name="iproSytmBatchMngeDao")
	private IproSytmBatchMngeDao iproSytmBatchMngeDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmBatchMngeService#sytmBatchFormListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject sytmBatchFormListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmBatchMngeService.BATCH_FORM_LIST, iproSytmBatchMngeDao.selectSytmBatchFormListWithPgng(parameterMap));
		trans.put(IproSytmBatchMngeService.BATCH_FORM_LIST_TOTCNT, iproSytmBatchMngeDao.selectSytmBatchFormListTotCnt(parameterMap));
	
		return trans;
	}

}
