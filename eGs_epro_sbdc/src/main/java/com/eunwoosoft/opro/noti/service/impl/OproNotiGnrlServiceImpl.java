package com.eunwoosoft.opro.noti.service.impl;

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
import com.eunwoosoft.opro.noti.dao.OproNotiGnrlDao;
import com.eunwoosoft.opro.noti.service.OproNotiGnrlService;

/**
 * 
 * com.eunwoosoft.opro.noti.service.impl
 * OproNotiGnrlServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 29.
 *
 */
@Service(value="oproNotiGnrlService")
public class OproNotiGnrlServiceImpl extends AbstractFwkService implements OproNotiGnrlService {
	private static final Logger LOG = LoggerFactory.getLogger(OproNotiGnrlServiceImpl.class); 
	
	@Resource(name="oproNotiGnrlDao") 
	private OproNotiGnrlDao oproNotiGnrlDao;  

	@Resource(name="comAtmaAtchFileService") 
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.noti.service.OproNotiGnrlService#notiGnrlListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject notiGnrlListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put(OproNotiGnrlService.NOTI_GNRL_LIST, oproNotiGnrlDao.notiGnrlListWithPgng(parameterMap));
		trans.put(OproNotiGnrlService.NOTI_GNRL_LIST_TOTCNT, oproNotiGnrlDao.notiGnrlListTotCnt(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject notiListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put("dataList", oproNotiGnrlDao.notiGnrlExcelList(parameterMap));
		
		return trans;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.noti.service.OproNotiGnrlService#notiGnrlDetail(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject notiGnrlDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		FwkDataEntity notiGnrlDetail = oproNotiGnrlDao.notiGnrlDetail(parameterMap);
		
		trans.put(OproNotiGnrlService.NOTI_GNRL_DETAIL, notiGnrlDetail);

		// 첨부파일 상세조회
		if (notiGnrlDetail.getString("FILE_GRP_NO") != null) {
			parameterMap.put("P_FILE_GRP_NO", notiGnrlDetail.get("FILE_GRP_NO"));
			trans.put("fileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		//조회수 카운트
		oproNotiGnrlDao.updateInqCntBaMst(parameterMap);

		return trans;
	}
}
