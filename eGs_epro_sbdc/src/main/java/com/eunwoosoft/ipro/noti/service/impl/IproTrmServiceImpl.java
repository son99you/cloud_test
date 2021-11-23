package com.eunwoosoft.ipro.noti.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.dao.IproTrmDao;
import com.eunwoosoft.ipro.noti.service.IproBidNotiService;
import com.eunwoosoft.ipro.noti.service.IproNotiService;
import com.eunwoosoft.ipro.noti.service.IproTrmService;

/**
 * 
 * com.eunwoosoft.ipro.noti.service.impl
 * IproNotiServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
@Service(value="iproTrmService")
public class IproTrmServiceImpl extends AbstractFwkService implements IproTrmService {
	private static final Logger LOG = LoggerFactory.getLogger(IproTrmServiceImpl.class); 
	
	@Resource(name="iproTrmDao") 
	private IproTrmDao iproTrmDao; 

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject trmListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");

		trans.put(IproTrmService.TRM_LIST, iproTrmDao.trmListWithPgng(parameterMap));
		trans.put(IproTrmService.TRM_LIST_TOTCNT, iproTrmDao.trmListTotCnt(parameterMap));
	
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject trmRegist(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_TRM_SECD", "A");
		
		// 게시판 마스터 등록    
		iproTrmDao.trmRegist(parameterMap);
		
		trans.put("P_TRM_SN", parameterMap.get("P_TRM_SN"));
		return trans;
	} 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject trmDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_TRM_SECD","A");
		
		FwkDataEntity trmDetail = iproTrmDao.trmDetail(parameterMap);
		
		trans.put(IproTrmService.TRM_DETAIL, trmDetail);

		return trans; 
	} 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiDelete(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject trmDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();

		// 삭제여부 수정
		parameterMap.put("P_DEL_AT", "Y");
		iproTrmDao.trmDelete(parameterMap);
		
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override 
	public FwkTransferObject trmUpdt(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_TRM_SECD", "A");

		// 게시판 마스터 수정
		iproTrmDao.trmUpdt(parameterMap);
		
		trans.put("P_TRM_SN", parameterMap.get("P_TRM_SN"));
		
		return trans;
	}

	@Override
	public FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity usrInfoDetail = iproTrmDao.usrInfoDetail(parameterMap);
		
		trans.put(IproNotiService.USR_INFO_DETAIL, usrInfoDetail);
		
		return trans;
	}
	
	@Override
    public FwkTransferObject trmDataAll(FwkParameterMap parameterMap) { 
    	FwkTransferObject trans = new FwkTransferObject();
    	
    	 trans.put("trmDataAllList", iproTrmDao.selectTrmAll(parameterMap) );
      
    	 return trans;
    }


	@Override
	public FwkTransferObject trmCnt(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_DEL_AT", "N");
   	 	trans.put("trmCnt", iproTrmDao.trmListTotCnt(parameterMap) );
     
   	 	return trans;
	}

	@Override
	public FwkTransferObject trmListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");

		trans.put("dataList", iproTrmDao.trmExcelList(parameterMap));
	
		return trans;
	}
}	
