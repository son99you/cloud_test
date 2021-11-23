package com.eunwoosoft.ipro.estm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmRnkSlctCmplDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmRnkSlctCmplService;

@Service(value="iproEstmCmtmRnkSlctCmplService")
public class IproEstmCmtmRnkSlctCmplServiceImpl extends AbstractFwkService implements IproEstmCmtmRnkSlctCmplService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmRnkSlctCmplServiceImpl.class);

	@Resource(name="iproEstmCmtmRnkSlctCmplDao") 
	private IproEstmCmtmRnkSlctCmplDao iproEstmCmtmRnkSlctCmplDao;
	
	@Override
	public FwkTransferObject cmtmRnkSlctCmplList(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "RNK_SLCT_CMPL");
		
		trans.put("cmtmRnkSlctCmplList", iproEstmCmtmRnkSlctCmplDao.selectCmtmRnkSlctCmplList(parameterMap));
		trans.put("cmtmRnkSlctCmplListTotCnt", iproEstmCmtmRnkSlctCmplDao.selectCmtmRnkSlctCmplListTotCnt(parameterMap));
	
		return trans;
		
	}
	
	@Override
	public FwkTransferObject cmtmRnkSlctCmplListExcelDwld(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "RNK_SLCT_CMPL");
		
		trans.put("cmtmRnkSlctCmplList", iproEstmCmtmRnkSlctCmplDao.selectCmtmRnkSlctCmplListExcelDwld(parameterMap));
	
		return trans;
		
	}
	

	@Override
	public FwkTransferObject cmtmRnkSlctCmplDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
		}else{
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		}
		List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) iproEstmCmtmRnkSlctCmplDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		return trans;		
	}
	
}
