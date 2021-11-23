package com.eunwoosoft.ipro.noti.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.dao.IproBidNotiDao;
import com.eunwoosoft.ipro.noti.service.IproBidNotiService;

/**
 * 
 * com.eunwoosoft.ipro.noti.service.impl
 * IproBidNotiServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 24.
 *
 */
@Service(value="iproBidNotiService")
public class IproBidNotiServiceImpl extends AbstractFwkService implements IproBidNotiService {
	
	@Resource(name="iproBidNotiDao") 
	private IproBidNotiDao iproBidNotiDao;
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproBidNotiService#bidNotiListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	public FwkTransferObject bidNotiListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "BID_NOTI");
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put(IproBidNotiService.BID_NOTI_LIST, iproBidNotiDao.bidNotiListWithPgng(parameterMap));
		trans.put(IproBidNotiService.BID_NOTI_LIST_TOTCNT, iproBidNotiDao.bidNotiListTotCnt(parameterMap));
	
		return trans;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproBidNotiService#bidNotiDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject bidNotiDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "BID_NOTI");

		FwkDataEntity bidNotiDetail = iproBidNotiDao.bidNotiDetail(parameterMap);
		
		trans.put(IproBidNotiService.BID_NOTI_DETAIL, bidNotiDetail);

		// 첨부파일 상세조회
		if (bidNotiDetail.getString("FILE_GRP_NO") != null) {
			parameterMap.put("P_FILE_GRP_NO", bidNotiDetail.get("FILE_GRP_NO"));
			trans.put("fileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}

		System.err.println(" <=== fileList ===> " + trans.get("fileList"));

		return trans;

	}


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproBidNotiService#usrInfoDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity usrInfoDetail = iproBidNotiDao.usrInfoDetail(parameterMap);
		
		trans.put(IproBidNotiService.USR_INFO_DETAIL, usrInfoDetail);
		
		return trans;
	}


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproBidNotiService#bidNotiRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject bidNotiRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "BID_NOTI");
		parameterMap.put("P_USE_YN", "Y");

		parameterMap.put("P_PPUP_ENDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_ENDE"), "yyyy-MM-dd", "yyyyMMdd"));
		parameterMap.put("P_PPUP_STDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_STDE"), "yyyy-MM-dd", "yyyyMMdd"));
		
		
		// 게시판 마스터 등록  
		iproBidNotiDao.bidNotiRegist(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		
		return trans;
	}


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproBidNotiService#bidNotiDelete(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject bidNotiDelete(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		// 삭제여부 수정
		parameterMap.put("P_DEL_AT", "Y");
		iproBidNotiDao.bidNotiDelete(parameterMap);
		
		return trans;
	}


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproBidNotiService#bidNotiUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject bidNotiUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "BID_NOTI");
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_DEL_AT", "N");

		parameterMap.put("P_PPUP_ENDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_ENDE"), "yyyy-MM-dd", "yyyyMMdd"));
		parameterMap.put("P_PPUP_STDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_STDE"), "yyyy-MM-dd", "yyyyMMdd"));
		
		
		// 게시판 마스터 수정
		iproBidNotiDao.bidNotiUpdt(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		
		return trans;
	}

}