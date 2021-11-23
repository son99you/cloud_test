package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancResultDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancPrcnService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancResultService;

/**
 * 입찰공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidPblancResultService")
public class IproEbidPblancResultServiceImpl extends AbstractFwkService implements IproEbidPblancResultService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidPblancResultServiceImpl.class);
	
	@Resource(name="iproEbidPblancResultDao")
	private IproEbidPblancResultDao iproEbidPblancResultDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;

	/**
	 * 입찰공고 결과 목록
	 * 2020-09-22
	 * 
	 * joo
	 * 
	 */
	@Override
	public FwkTransferObject bidPblancResultListInqireWithPgng(FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_RQR_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_RQR_BEGIN_DT_S"))){ // 공고일자 시작
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_RQR_END_DT_S") != null && !"".equals(parameterMap.get("P_RQR_END_DT_S"))){ // 공고일자 종료
			parameterMap.put("P_RQR_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproEbidPblancResultService.BID_PBLANC_RESULT_LIST, iproEbidPblancResultDao.selectBidPblancResultListWithPgng(parameterMap));
		trans.put(IproEbidPblancResultService.BID_PBLANC_RESULT_LIST_TOTCNT, iproEbidPblancResultDao.selectBidPblancResultListTotcnt(parameterMap));
		return trans;
	}

	@Override
	public FwkTransferObject bidPblancResultExcelList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		if(parameterMap.get("P_RQR_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_RQR_BEGIN_DT_S"))){ // 작성일자 시작
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		if(parameterMap.get("P_RQR_END_DT_S") != null && !"".equals(parameterMap.get("P_RQR_END_DT_S"))){ // 작성일자 종료
			parameterMap.put("P_RQR_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		} 
		List<FwkDataEntity> bidPblancResultList = iproEbidPblancResultDao.selectBidPblancResultExcelList(parameterMap);
		
		for(int i=0; i < bidPblancResultList.size(); i++){
			FwkDataEntity bidPblancResult = bidPblancResultList.get(i);

			bidPblancResultList.get(i).put("ESTT_AMT", FwkFormatUtil.formatMoney(bidPblancResult.getString("ESTT_AMT")));
			bidPblancResultList.get(i).put("RQR_DE", FwkFormatUtil.formatDate(bidPblancResult.getString("RQR_DE"), "yyyyMMdd", "yyyy-MM-dd"));
		}
		
		trans.put(IproEbidPblancResultService.BID_PBLANC_RESULT_LIST, bidPblancResultList);
		
		return trans;
	}

}