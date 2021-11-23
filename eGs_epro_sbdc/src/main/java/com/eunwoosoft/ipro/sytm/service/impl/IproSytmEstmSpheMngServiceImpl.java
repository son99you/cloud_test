package com.eunwoosoft.ipro.sytm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmEstmSpheMngDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmSpheMngService;


@Service(value="iproSytmEstmSpheMngService")
public class IproSytmEstmSpheMngServiceImpl extends AbstractFwkService implements IproSytmEstmSpheMngService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmEstmSpheMngServiceImpl.class);
	
	@Resource(name="iproSytmEstmSpheMngDao") 
	private IproSytmEstmSpheMngDao iproSytmEstmSpheMngDao;
	
	
	@Override
	public FwkTransferObject estmSpheMngList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put("estmSpheMngList", iproSytmEstmSpheMngDao.selectEstmSpheMngList(parameterMap));
		trans.put("estmSpheMngListTotCnt", iproSytmEstmSpheMngDao.selectEstmSpheMngListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject estmSpheMngDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가분야 조회
		FwkDataEntity estmSpheMngDetail = (FwkDataEntity) iproSytmEstmSpheMngDao.selectEstmSpheMngDetail(parameterMap);   // T_ESTM_SPHE_MST
		trans.put("estmSpheMngDetail", estmSpheMngDetail);
		
		return trans;
	}
	
	@Override
	public void estmSpheMngRegist(final FwkParameterMap parameterMap) {

		// 평가분야 등록
		iproSytmEstmSpheMngDao.insertEstmSpheMng(parameterMap);   // T_ESTM_SPHE_MST INSERT
	}

	@Override
	public void estmSpheMngUpdt(final FwkParameterMap parameterMap) {
		
		// 평가분야 수정
		iproSytmEstmSpheMngDao.updateEstmSpheMng(parameterMap);   // T_ESTM_SPHE_MST UPDATE
		
	}

	@Override
	public void estmSpheMngDelete(final FwkParameterMap parameterMap) {

		// 평가분야 수정
		iproSytmEstmSpheMngDao.deleteEstmSpheMng(parameterMap);   // T_ESTM_SPHE_MST DELETE
	}

}