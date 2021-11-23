package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmVendMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmVendMngeService;

/**
 * <pre>
 * com.eunwoosoft.ipro.sytm.service.impl 
 *    |_ IproSytmVendMngeServiceImpl.java
 * 
 * </pre>
 * 
 * @date : 2018. 2. 20.
 * @version : 1.0
 * @author : jandi_Eun
 */

@Transactional
@Service("iproSytmVendMngeService")
public class IproSytmVendMngeServiceImpl extends AbstractFwkService implements IproSytmVendMngeService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmVendMngeServiceImpl.class);

	@Resource(name = "iproSytmVendMngeDao")
	private IproSytmVendMngeDao iproSytmVendMngeDao;

	@Override
	public FwkTransferObject vendMngeListInqireWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproSytmVendMngeService.VEND_MNGE_LIST, iproSytmVendMngeDao.selectVendMngeListInqireWithPgng(parameterMap));
		trans.put(IproSytmVendMngeService.VEND_MNGE_LIST_TOTCNT, iproSytmVendMngeDao.selectVendMngeListTotcnt(parameterMap));
		return trans;

	}

	@Override
	public FwkTransferObject vendMngeDetailInqire(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproSytmVendMngeService.VEND_MNGE_DETAIL, iproSytmVendMngeDao.selectVendMngeDetail(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject vendMngeExcelList(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put("dataList", iproSytmVendMngeDao.selectVendMngeExcelList(parameterMap));
		return trans;
	}

}
