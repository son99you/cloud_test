package com.eunwoosoft.ipro.info.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.info.dao.IproInfoApprSetDao;
import com.eunwoosoft.ipro.info.service.IproInfoApprSetService;
import com.eunwoosoft.ipro.vend.dao.IproVendMngeDao;


/**
 * <pre>
 * com.eunwoosoft.ipro.info.service.impl 
 *    |_ InfoVendMngeServiceImpl.java
 * 
 * </pre>
 * @date : 2018. 11. 01.
 * @version : 1.0
 * @author : 명정현
 */
@Transactional
@Service("iproInfoApprSetService")
public class IproInfoApprSetServiceImpl extends AbstractFwkService implements IproInfoApprSetService{
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(IproInfoApprSetServiceImpl.class);
	
	@Resource(name="iproVendMngeDao")
	private IproVendMngeDao iproVendMngeDao;
	
	@Resource(name="iproInfoApprSetDao")
	private IproInfoApprSetDao iproInfoApprSetDao;
	
	
	@Override
	public FwkTransferObject getApprSetYnDetail(FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		trans.put(IproInfoApprSetService.APPR_YN_DETAIL, iproVendMngeDao.selectVendMngeDetail(parameterMap));
		
		return trans;
		
	}
	
	
	@Override
	public void apprSetRegist(final FwkParameterMap parameterMap) throws Exception{
//		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_VEND_REG_NO", loginInfo.get("VEND_REG_NO"));
		parameterMap.put("P_USR_ID", loginInfo.get("USR_ID"));
		parameterMap.put("P_USR_NM", FwkStringUtil.castEuc2Iso(loginInfo.get("USR_NM")));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_DEL_AT", "N");
		
		iproInfoApprSetDao.apprSetYnUpdt(parameterMap);
	}
	
}
