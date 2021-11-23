package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmCodeMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmCodeMngeService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmCodeMngeServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 30.
 *
 */
@Transactional
@Service("iproSytmCodeMngeService")
public class IproSytmCodeMngeServiceImpl extends AbstractFwkService implements IproSytmCodeMngeService{
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmCodeMngeServiceImpl.class);
	
	@Resource(name="iproSytmCodeMngeDao")
	private IproSytmCodeMngeDao iproSytmCodeMngeDao;

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmCodeMngeService#codeMngeListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject codeMngeListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmCodeMngeService.CODE_MNGE_LIST, iproSytmCodeMngeDao.codeMngeListWithPgng(parameterMap));
		trans.put(IproSytmCodeMngeService.CODE_MNGE_LIST_TOTCNT, iproSytmCodeMngeDao.codeMngeListTotCnt(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject codeMngeListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("dataList", iproSytmCodeMngeDao.codeMngeExcelList(parameterMap));
		
		return trans;
	}
}
