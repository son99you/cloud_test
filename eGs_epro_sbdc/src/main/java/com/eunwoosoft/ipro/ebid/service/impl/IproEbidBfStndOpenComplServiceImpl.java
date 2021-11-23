package com.eunwoosoft.ipro.ebid.service.impl; 

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
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidBfStndOpenComplDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidBfStndOpenComplService;

/**
 * 사전공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2018. 02. 19.
 * @version : 1.0
 */
@Service("iproEbidBfStndOpenComplService")
public class IproEbidBfStndOpenComplServiceImpl extends AbstractFwkService implements IproEbidBfStndOpenComplService {
	
	static final String contextPath = "Ebid/bfan";
	
	@Resource(name="iproEbidBfStndOpenComplDao")
	private IproEbidBfStndOpenComplDao iproEbidBfStndOpenComplDao;	
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidBfStndOpenComplServiceImpl.class);

	@Override
	public FwkTransferObject bfStndOpenPrcnList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidBfStndOpenComplService.BF_STND_OPEN_LIST, iproEbidBfStndOpenComplDao.selectBfStndOpenList(parameterMap));
		trans.put(IproEbidBfStndOpenComplService.BF_STND_OPEN_LIST_TOT_CNT, iproEbidBfStndOpenComplDao.selectBfStndOpenListTotCnt(parameterMap));
		return trans;
	}

	@Override
	public FwkTransferObject bfStndOpenComplDetail(FwkParameterMap parameterMap) {
		FwkTransferObject  trans = new FwkTransferObject();
		
		//MST정보
		trans.put(IproEbidBfStndOpenComplService.BF_STND_OPEN_DETAIL, iproEbidBfStndOpenComplDao.selectBfStndOpenDetail(parameterMap));
		//ITEM정보
		trans.put(IproEbidBfStndOpenComplService.BF_STND_OPEN_ITEM_LIST, iproEbidBfStndOpenComplDao.selectBfStndOpenItemList(parameterMap));
		//FILE정보
		trans.put(IproEbidBfStndOpenComplService.BFAN_FILE, iproEbidBfStndOpenComplDao.selectBfStndOpenFile(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject bfStndOpenComplExcelList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_RQR_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_RQR_BEGIN_DT_S"))){ // 작성일자 시작
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		if(parameterMap.get("P_RQR_END_DT_S") != null && !"".equals(parameterMap.get("P_RQR_END_DT_S"))){ // 작성일자 종료
			parameterMap.put("P_RQR_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		} 
		
		List<FwkDataEntity> stndOpenList = iproEbidBfStndOpenComplDao.selectBfStndOpenExcelList(parameterMap);
		
		for(int i=0; i < stndOpenList.size(); i++){
			FwkDataEntity stndOpen = stndOpenList.get(i);

    		stndOpenList.get(i).put("BSNS_BDG_AMT", FwkFormatUtil.formatMoney(stndOpen.getString("BSNS_BDG_AMT")));
    		stndOpenList.get(i).put("BFAN_STDE", FwkFormatUtil.formatDate(stndOpen.getString("BFAN_STDE"), "yyyyMMdd", "yyyy-MM-dd"));
    		stndOpenList.get(i).put("BFAN_ENDE", FwkFormatUtil.formatDate(stndOpen.getString("BFAN_ENDE"), "yyyyMMdd", "yyyy-MM-dd"));
    		stndOpenList.get(i).put("RQR_DE", FwkFormatUtil.formatDate(stndOpen.getString("RQR_DE"), "yyyyMMdd", "yyyy-MM-dd"));
		}
		
		trans.put(IproEbidBfStndOpenComplService.BF_STND_OPEN_LIST, stndOpenList);
		
		return trans;
	}

}