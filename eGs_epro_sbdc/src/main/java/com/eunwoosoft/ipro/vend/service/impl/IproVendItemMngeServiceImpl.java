package com.eunwoosoft.ipro.vend.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.vend.dao.IproVendItemMngeDao;
import com.eunwoosoft.ipro.vend.service.IproVendItemMngeService;

/**
 * com.eunwoosoft.ipro.vend.service.impl
 * IproVendItemMngeServiceImpl.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 22.
 *
 */

@Transactional
@Service("iproVendItemMngeService")
public class IproVendItemMngeServiceImpl extends AbstractFwkService implements IproVendItemMngeService{
	
	@Resource(name="iproVendItemMngeDao")
	private IproVendItemMngeDao iproVendItemMngeDao;
	
	
	@Override
	public FwkTransferObject vendItemMngeListInqireWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproVendItemMngeService.VEND_ITEM_MNGE_LIST, iproVendItemMngeDao.selectVendItemMngeListInqireWithPgng(parameterMap));
		trans.put(IproVendItemMngeService.VEND_ITEM_MNGE_LIST_TOTCNT, iproVendItemMngeDao.selectVendItemMngeListTotcnt(parameterMap));
		return trans;
		
	}
	

}
