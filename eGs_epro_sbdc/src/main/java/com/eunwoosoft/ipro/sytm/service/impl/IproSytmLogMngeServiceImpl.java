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
import com.eunwoosoft.ipro.sytm.dao.IproSytmLogMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmLogMngeService;

/**
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmLogMngeServiceImpl.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
@Transactional
@Service("iproSytmLogMngeService")
public class IproSytmLogMngeServiceImpl extends AbstractFwkService implements IproSytmLogMngeService{
	
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmLogMngeServiceImpl.class);
	
	@Resource(name="iproSytmLogMngeDao")
	private IproSytmLogMngeDao iproSytmLogMngeDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmLogMngeService#logMngeListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject logMngeListWithPgng(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_CONN_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_CONN_BEGIN_DT_S"))){
			parameterMap.put("P_CONN_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_CONN_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_CONN_END_DT_S") != null && !"".equals(parameterMap.get("P_CONN_END_DT_S"))){
			parameterMap.put("P_CONN_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_CONN_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		
		if(parameterMap.get("P_REG_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_REG_END_DT_S") != null && !"".equals(parameterMap.get("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproSytmLogMngeService.LOG_MNGE_LIST, iproSytmLogMngeDao.selectLogMngeListWithPgng(parameterMap));
		trans.put(IproSytmLogMngeService.LOG_MNGE_LIST_TOTCNT, iproSytmLogMngeDao.selectLogMngeListTotCnt(parameterMap));
		return trans;
		
	}

	@Override
	public FwkTransferObject logMngeListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_CONN_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_CONN_BEGIN_DT_S"))){
			parameterMap.put("P_CONN_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_CONN_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_CONN_END_DT_S") != null && !"".equals(parameterMap.get("P_CONN_END_DT_S"))){
			parameterMap.put("P_CONN_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_CONN_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		
		if(parameterMap.get("P_REG_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_REG_END_DT_S") != null && !"".equals(parameterMap.get("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put("dataList", iproSytmLogMngeDao.selectLogMngeExcelList(parameterMap));
		return trans;
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmLogMngeService#logMngeDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject logMngeDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmLogMngeService.LOG_MNGE_DETAIL, iproSytmLogMngeDao.selectLogMngeDetail(parameterMap));
		
		return trans;
	}

}
