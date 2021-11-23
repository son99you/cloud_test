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
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.dao.IproRssDao;
import com.eunwoosoft.ipro.noti.service.IproFaqService;
import com.eunwoosoft.ipro.noti.service.IproRssService;


/**
 * 
 * com.eunwoosoft.ipro.rss.service.impl
 * IproRssServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
@Service(value="iproRssService")
public class IproRssServiceImpl extends AbstractFwkService implements IproRssService {
	private static final Logger LOG = LoggerFactory.getLogger(IproRssServiceImpl.class); 
	
	@Resource(name="iproRssDao") 
	private IproRssDao iproRssDao; 

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.rss.service.IproRssService#rssListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject rssListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "CONT_RSS");
		parameterMap.put("P_DEL_AT", "N");

		trans.put(IproRssService.RSS_LIST, iproRssDao.rssListWithPgng(parameterMap));
		trans.put(IproRssService.RSS_LIST_TOTCNT, iproRssDao.rssListTotCnt(parameterMap));
	
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.rss.service.IproRssService#rssRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject rssRegist(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "CONT_RSS");
		parameterMap.put("P_USE_YN", "Y");
		
		parameterMap.put("P_PPUP_ENDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_ENDE"), "yyyy-MM-dd", "yyyyMMdd"));
		parameterMap.put("P_PPUP_STDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_STDE"), "yyyy-MM-dd", "yyyyMMdd"));
		
		
		// 게시판 마스터 등록    
		iproRssDao.rssRegist(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.rss.service.IproRssService#rssDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject rssDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity loginInfo = (FwkDataEntity)parameterMap.get("loginResult");
		
		parameterMap.put("P_BBS_SECD", "CONT_RSS");

		FwkDataEntity rssDetail = iproRssDao.rssDetail(parameterMap);
		
		trans.put(IproRssService.RSS_DETAIL, rssDetail);
		
		//조회수 카운트
		iproRssDao.updateInqCntBaMst(parameterMap);
		
		return trans; 
	} 
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.rss.service.IproRssService#rssDelete(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject rssDelete(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();

		// 삭제여부 수정
		parameterMap.put("P_DEL_AT", "Y");
		iproRssDao.rssDelete(parameterMap);
		
		return trans;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproRssService#rssUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override 
	public FwkTransferObject rssUpdt(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "CONT_RSS");
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_DEL_AT", "N");

		// 게시판 마스터 수정
		iproRssDao.rssUpdt(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity usrInfoDetail = iproRssDao.usrInfoDetail(parameterMap);
		
		trans.put(IproRssService.USR_INFO_DETAIL, usrInfoDetail);
		
		return trans;
	}
	
}
