package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.SDBCryptoUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmUserMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService;

/**
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmUserMngeServiceImpl.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 26.
 *
 */
@Transactional
@Service("iproSytmUserMngeService")
public class IproSytmUserMngeServiceImpl extends AbstractFwkService implements IproSytmUserMngeService{
	
	@Resource(name="iproSytmUserMngeDao")
	private IproSytmUserMngeDao iproSytmUserMngeDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService#userMgrListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject userMgrListWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmUserMngeService.USER_MGR_LIST, iproSytmUserMngeDao.selectUserMgrListWithPgng(parameterMap));
		trans.put(IproSytmUserMngeService.USER_MGR_LIST_TOTCNT, iproSytmUserMngeDao.selectUserMgrListTotcnt(parameterMap));
		return trans;
		
	}

	@Override
	public FwkTransferObject userMgrExcelList(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("dataList", iproSytmUserMngeDao.selectUserMgrExcelList(parameterMap));
		return trans;
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService#userMgrDetailInqire(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject userMgrDetailInqire(FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity userMgrDetail =  iproSytmUserMngeDao.selectUserMgrDetail(parameterMap);
		trans.put(IproSytmUserMngeService.USER_MGR_DETAIL, userMgrDetail);
		
		return trans;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService#userMgrUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject userMgrUpdt(FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID", user.get("USR_ID"));
		parameterMap.put("P_REGR_NM", user.get("USR_NM"));
		parameterMap.put("P_CONN_IP", user.get("CONN_IP"));
		
		try {
			iproSytmUserMngeDao.userMgrUpdt(parameterMap);
			
			trans.put("P_USR_ID", parameterMap.get("P_USR_ID"));
			trans.put("P_SUCC", "SUCC");
		} catch (Exception e) {
			trans.put("P_SUCC", "FAIL");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== IproSytmUserMngeServiceImpl.userMgrUpdt Error == ");
		}
		return trans;
		
	}
	
}
