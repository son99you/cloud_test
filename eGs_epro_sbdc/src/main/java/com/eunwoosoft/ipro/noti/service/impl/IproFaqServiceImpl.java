package com.eunwoosoft.ipro.noti.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidBfStndOpenComplService;
import com.eunwoosoft.ipro.noti.dao.IproFaqDao;
import com.eunwoosoft.ipro.noti.service.IproFaqService;
import com.eunwoosoft.ipro.noti.service.IproNotiService;

/**
 * 
 * com.eunwoosoft.ipro.noti.service.impl
 * IproFaqServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
@Service(value="iproFaqService")
public class IproFaqServiceImpl extends AbstractFwkService implements IproFaqService {
	
	@Resource(name="iproFaqDao") 
	private IproFaqDao iproFaqDao;
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproFaqService#faqListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	public FwkTransferObject faqListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "FAQ");
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put(IproFaqService.FAQ_LIST, iproFaqDao.faqListWithPgng(parameterMap));
		trans.put(IproFaqService.FAQ_LIST_TOTCNT, iproFaqDao.faqListTotCnt(parameterMap));
	
		return trans;
	}

	public FwkTransferObject faqListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "FAQ");
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put("dataList", iproFaqDao.faqExcelList(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject usrInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity usrInfoDetail = iproFaqDao.usrInfoDetail(parameterMap);
		
		trans.put(IproFaqService.USR_INFO_DETAIL, usrInfoDetail);
		
		return trans;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproFaqService#faqDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject faqDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_BBS_SECD", "FAQ");
		
		FwkDataEntity faqDetail = iproFaqDao.faqDetail(parameterMap);
		trans.put(IproFaqService.FAQ_DETAIL, faqDetail);
		
		//조회수 카운트
		iproFaqDao.updateInqCntBaMst(parameterMap);
		
		return trans;

	}


	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproFaqService#faqRegist(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject faqRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "FAQ");
		parameterMap.put("P_USE_YN", "Y");

		parameterMap.put("P_PPUP_ENDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_ENDE"), "yyyy-MM-dd", "yyyyMMdd"));
		parameterMap.put("P_PPUP_STDE", FwkFormatUtil.formatDate((String)parameterMap.get("P_PPUP_STDE"), "yyyy-MM-dd", "yyyyMMdd"));
		
		
		// 게시판 마스터 등록
		iproFaqDao.faqRegist(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		return trans;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproFaqService#faqDelete(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject faqDelete(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		// 삭제여부 수정
		parameterMap.put("P_DEL_AT", "Y");
		iproFaqDao.faqDelete(parameterMap);
		
		return trans;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.noti.service.IproFaqService#faqUpdt(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject faqUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_BBS_SECD", "FAQ");
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_DEL_AT", "N");
		
		// 게시판 마스터 수정
		iproFaqDao.faqUpdt(parameterMap);
		
		trans.put("P_BBS_SECD", parameterMap.get("P_BBS_SECD"));
		trans.put("P_BBS_SN", parameterMap.get("P_BBS_SN"));
		trans.put("P_BBS_SN_TRANS", parameterMap.getInt("P_BBS_SN"));
		return trans;
	}

}