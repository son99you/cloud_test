package com.eunwoosoft.ipro.sytm.service.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmPrdtMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmPrdtMngeService;

@Transactional
@Service("iproSytmPrdtMngeService")
public class IproSytmPrdtMngeServiceImpl extends AbstractFwkService implements IproSytmPrdtMngeService{
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmPrdtMngeServiceImpl.class);
	
	@Resource(name = "iproSytmPrdtMngeDao")
	private IproSytmPrdtMngeDao iproSytmPrdtMngeDao;
	
	@Override
	public FwkTransferObject prdtMngeListInqireWithPgng( FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
	
		trans.put(IproSytmPrdtMngeService.PRDT_MNG_LIST, iproSytmPrdtMngeDao.selectPrdtMngeListInqireWithPgng(parameterMap));
		trans.put(IproSytmPrdtMngeService.PRDT_MNG_LIST_TOTCNT,  iproSytmPrdtMngeDao.selectPrdtMngeListTotcnt(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject prdtMngDetailInqire(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmPrdtMngeService.PRDT_MNG_DETAIL, iproSytmPrdtMngeDao.selectPrdtMngDetail(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject prdtMngListExcelDwld(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("dataList", iproSytmPrdtMngeDao.selectPrdtMngeExcelList(parameterMap));
		
		return trans;
	}

}
