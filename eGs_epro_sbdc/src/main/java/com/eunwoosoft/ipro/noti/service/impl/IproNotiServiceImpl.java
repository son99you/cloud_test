package com.eunwoosoft.ipro.noti.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.dao.IproNotiDao;
import com.eunwoosoft.ipro.noti.service.IproNotiService;

/**
 * 
 * com.eunwoosoft.ipro.noti.service.impl
 * IproNotiServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
@Service(value="iproNotiService")
public class IproNotiServiceImpl extends AbstractFwkService implements IproNotiService {
	private static final Logger LOG = LoggerFactory.getLogger(IproNotiServiceImpl.class); 
	
	@Resource(name="iproNotiDao") 
	private IproNotiDao iproNotiDao; 

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject notiListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "NOTI");
		parameterMap.put("P_DEL_AT", "N");

		trans.put(IproNotiService.NOTI_LIST, iproNotiDao.notiListWithPgng(parameterMap));
		trans.put(IproNotiService.NOTI_LIST_TOTCNT, iproNotiDao.notiListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject notiListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "NOTI");
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put("dataList", iproNotiDao.notiExcelList(parameterMap));
		
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject notiRegist(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "NOTI");
		parameterMap.put("P_USE_YN", "Y");
		
		// 게시판 마스터 등록    
		iproNotiDao.notiRegist(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		
		return trans;
	} 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject notiDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap .get("loginResult");
		
		parameterMap.put("P_BBS_SECD", "NOTI");

		FwkDataEntity notiDetail = iproNotiDao.notiDetail(parameterMap);
		trans.put(IproNotiService.NOTI_DETAIL, notiDetail);

		//조회수 카운트
		iproNotiDao.updateInqCntBaMst(parameterMap);
		
		return trans; 
	} 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiDelete(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject notiDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();

		// 삭제여부 수정
		parameterMap.put("P_DEL_AT", "Y");
		iproNotiDao.notiDelete(parameterMap);
		
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproNotiService#notiUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override 
	public FwkTransferObject notiUpdt(final FwkParameterMap parameterMap)throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "NOTI");
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_DEL_AT", "N");

		// 게시판 마스터 수정
		iproNotiDao.notiUpdt(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		trans.put("P_BBS_SN_TRANS", parameterMap.get("P_BBS_SN"));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity usrInfoDetail = iproNotiDao.usrInfoDetail(parameterMap);
		trans.put(IproNotiService.USR_INFO_DETAIL, usrInfoDetail);

		return trans;
	}
}
