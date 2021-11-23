package com.eunwoosoft.opro.comm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.comm.dao.OproCommLogDao;
import com.eunwoosoft.opro.comm.service.OproCommLogService;

/**
 * 
 * com.eunwoosoft.opro.acpr.service.impl
 * OproAcprServiceImpl.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 26.
 * 
 */
  
@Service(value="oproCommLogService")
public class OproCommLogServiceImpl extends AbstractFwkService implements OproCommLogService {
	private static final Logger LOG = LoggerFactory.getLogger(OproCommLogServiceImpl.class); 
	
	@Resource(name="oproCommLogDao")   
	private OproCommLogDao oproCommLogDao;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : log등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : logInfoInsert
	 * @date : 2018.08.23
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	 2018.08.23.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.masc.service.OepMascMainPageService#entrpsPrmanentLeavAtCeck(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	public void logInfoInsert(final FwkParameterMap parameterMap) {
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
			parameterMap.put("P_PRCURE_BSNS_SE_CD", session.get("PRCURE_BSNS_SE_CD"));
			parameterMap.put("P_CONN_DT", FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_CONN_ID", session.get("USR_ID"));
		}
		//oproCommLogDao.logInfoInsert(parameterMap);
	}
}
