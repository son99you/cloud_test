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
import com.eunwoosoft.ipro.sytm.dao.IproSytmG2bCntcDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmG2bCntcService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmG2bCntcServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 22.
 *
 */
@Transactional
@Service("iproSytmG2bCntcService")
public class IproSytmG2bCntcServiceImpl extends AbstractFwkService implements IproSytmG2bCntcService {
	
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmG2bCntcServiceImpl.class);
	
	@Resource(name="iproSytmG2bCntcDao")
	private IproSytmG2bCntcDao iproSytmG2bCntcDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmG2bCntcService#g2bCntcHstyList(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject g2bCntcHstyList(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_CNTC_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_CNTC_BEGIN_DT_S"))){
			parameterMap.put("P_CNTC_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_CNTC_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_CNTC_END_DT_S") != null && !"".equals(parameterMap.get("P_CNTC_END_DT_S"))){
			parameterMap.put("P_CNTC_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_CNTC_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproSytmG2bCntcService.G2B_CNTC_HSTY_LIST, iproSytmG2bCntcDao.selectG2bCntcHstyListWithPgng(parameterMap));
		trans.put(IproSytmG2bCntcService.G2B_CNTC_HSTY_LIST_TOTCNT, iproSytmG2bCntcDao.selectG2bCntcHstyListTotCnt(parameterMap));
		
		return trans;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmG2bCntcService#g2bCntcHstyDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject g2bCntcHstyDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmG2bCntcService.G2B_CNTC_HSTY_DETAIL, iproSytmG2bCntcDao.selectG2bCntcHstyDetail(parameterMap));
		
		return trans;
	}
}
