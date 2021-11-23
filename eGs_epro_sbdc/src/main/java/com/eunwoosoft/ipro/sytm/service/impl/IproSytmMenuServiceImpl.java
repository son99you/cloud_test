package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmMenuDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmMenuService;

/**
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmMenuServiceImpl.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
@Transactional
@Service("iproSytmMenuService")
public class IproSytmMenuServiceImpl extends AbstractFwkService implements IproSytmMenuService{
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmMenuServiceImpl.class);
	
	@Resource(name="iproSytmMenuDao")
	private IproSytmMenuDao iproSytmMenuDao;
	
	@Override
	public FwkTransferObject menuMgrListInqireWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmMenuService.MENU_MGR_LRG_LIST, iproSytmMenuDao.selectMenuLrgListInqire(parameterMap));
		
		trans.put(IproSytmMenuService.MENU_MGR_LIST, iproSytmMenuDao.selectMenuMgrListInqireWithPgng(parameterMap));
		trans.put(IproSytmMenuService.MENU_MGR_LIST_TOTCNT, iproSytmMenuDao.selectMenuMgrListTotcnt(parameterMap));
		
		return trans;
		
	}

	@Override
	public FwkTransferObject menuMgrListExcelDwld(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("dataList", iproSytmMenuDao.selectMenuMgrExcelList(parameterMap));
		
		return trans;
	}

}
