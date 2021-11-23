package com.eunwoosoft.opro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyResultDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidPblancDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidResultDao;
import com.eunwoosoft.opro.ebid.service.OproEbidMyResultService;
import com.eunwoosoft.opro.ebid.service.OproEbidResultService;

/**
 * 입찰결과 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 02. 13.
 * @version : 1.0
 */
@Service("oproEbidResultService")
public class OproEbidResultServiceImpl extends AbstractFwkService implements OproEbidResultService {
	
	@Resource(name="oproEbidResultDao")
	private OproEbidResultDao oproEbidResultDao;
	
	@Resource(name="oproEbidPblancDao")
	private OproEbidPblancDao oproEbidPblancDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="oproEbidMyResultDao")
	private OproEbidMyResultDao oproEbidMyResultDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnListInqireWithPgng
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidResultListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(user != null){
			parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		}
		
		trans.put(OproEbidResultService.BID_RESULT_LIST, 	oproEbidResultDao.selectBidResultListWithPgng(parameterMap));
		trans.put(OproEbidResultService.BID_RESULT_LIST_TOTCNT, oproEbidResultDao.selectBidResultListTotcnt(parameterMap));
			
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnDetailInqire
	 * @date : 2015. 04. 03.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 03.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IproEbidDcPeoService#bidDcPeoDetailInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	
	@Override
	public FwkTransferObject bidResultDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			@SuppressWarnings("unchecked")
			FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
			//parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		}
		
		//입찰정보상세
		FwkDataEntity biMst = oproEbidMyResultDao.selectMyBidResultDetail(parameterMap);
		trans.put(OproEbidMyResultService.MY_BID_RESULT_DETAIL, biMst );
		
		//개찰일 경우 복수예가 목록조회
		FwkDataEntity bidPlanEntity = (FwkDataEntity) trans.get(OproEbidMyResultService.MY_BID_RESULT_DETAIL);
		if("180000".equals(biMst.get("ESTPC_SECD"))){
			List<FwkDataEntity> mList =  oproEbidMyResultDao.selectCompnoPrdprcList(parameterMap);
			
			ArrayList aList = new ArrayList();
			ArrayList bList = new ArrayList();
			ArrayList cList = new ArrayList();
			ArrayList dList = new ArrayList();
			for(int i=0; i<mList.size(); i++){
				FwkDataEntity cEntity = mList.get(i);
				aList.add(cEntity.get("PLR_ESTPC_NO"));
				bList.add(cEntity.get("PREP_PRCE_AMT"));
				cList.add(cEntity.get("DRLT_CNT"));
				dList.add(cEntity.get("ESTPC_RNK"));
			}
			
			trans.put("aList", aList);
			trans.put("bList", bList);
			trans.put("cList", cList);
			trans.put("dList", dList);			
		}

		trans.put(OproEbidResultService.SCSBID_PREARNGER_LIST , oproEbidMyResultDao.selectNtatScsbidPrearngerList(parameterMap));
		
		//유찰사유
		parameterMap.put("P_BID_PSCD", "PF99");
		List<FwkDataEntity> histList = oproEbidPblancDao.selectPblancNtcnInfoList(parameterMap);
		if(histList != null && histList.size() > 0 ){
			FwkDataEntity hist = histList.get(0);
			trans.put("pf99Detail", hist);
		}
			
		return trans;
	}
}