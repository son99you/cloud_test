package com.eunwoosoft.opro.user.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.user.dao.OproUserSnctVendRegInfoDao;
import com.eunwoosoft.opro.user.service.OproUserSnctVendRegInfoService;


/**
 * 
 * com.eunwoosoft.opro.user.service.impl
 * OproUserSnctVendRegInfoServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 7.
 *
 */
@Service("oproUserSnctVendRegInfoService")
@Transactional
public class OproUserSnctVendRegInfoServiceImpl extends AbstractFwkService implements OproUserSnctVendRegInfoService {
	private static final Logger LOG = LoggerFactory.getLogger(OproUserSnctVendRegInfoServiceImpl.class);
	
	@Resource(name="oproUserSnctVendRegInfoDao")
	private OproUserSnctVendRegInfoDao oproUserSnctVendRegInfoDao;
	

	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.user.service.OproUserSnctVendRegInfoService#snctVendRegInfoList(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject snctVendRegInfoList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		 
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_VEND_REG_NO", session.get("USR_ID"));
		
		if(parameterMap.get("P_GNRT_BEGIN_DE_S") != null && !"".equals(parameterMap.get("P_GNRT_BEGIN_DE_S"))){
			parameterMap.put("P_GNRT_BEGIN_DE_S", FwkFormatUtil.formatDate(parameterMap.get("P_GNRT_BEGIN_DE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_GNRT_END_DE_S") != null && !"".equals(parameterMap.get("P_GNRT_END_DE_S"))){
			parameterMap.put("P_GNRT_END_DE_S", FwkFormatUtil.formatDate(parameterMap.get("P_GNRT_END_DE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(OproUserSnctVendRegInfoService.SNCT_VEND_REG_INFO_LIST, oproUserSnctVendRegInfoDao.selectSnctVendRegInfoListWithPgng(parameterMap));
		trans.put(OproUserSnctVendRegInfoService.SNCT_VEND_REG_INFO_LIST_TOTCNT, oproUserSnctVendRegInfoDao.selectSnctVendRegInfoListTotCnt(parameterMap));
		
		return trans;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.user.service.OproUserSnctVendRegInfoService#snctVendRegInfoDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject snctVendRegInfoDetail(FwkParameterMap parameterMap) {

		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");

		parameterMap.put("P_VEND_REG_NO", session.get("USR_ID"));
		
		trans.put(OproUserSnctVendRegInfoService.SNCT_VEND_REG_INFO_DETAIL, oproUserSnctVendRegInfoDao.selectSnctVendRegInfoDetail(parameterMap));
		
		return trans;
		
	}

}
