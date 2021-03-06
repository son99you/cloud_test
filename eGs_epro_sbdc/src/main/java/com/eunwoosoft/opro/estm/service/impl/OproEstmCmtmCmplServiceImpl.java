package com.eunwoosoft.opro.estm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.comm.dao.OproCommDefaultDao;
import com.eunwoosoft.opro.estm.dao.OproEstmCmtmProgDao;
import com.eunwoosoft.opro.estm.service.OproEstmCmtmCmplService;

@Service(value="oproEstmCmtmCmplService")
public class OproEstmCmtmCmplServiceImpl extends AbstractFwkService implements OproEstmCmtmCmplService {
	
	@Resource(name="oproEstmCmtmProgDao") 
	private OproEstmCmtmProgDao oproEstmCmtmProgDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;

	@Resource(name="oproCommDefaultDao")
	private OproCommDefaultDao oproCommDefaultDao;
	
	@Override
	public FwkTransferObject estmCmtmCmplList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_CMPL");
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		trans.put("estmCmtmCmplList", oproEstmCmtmProgDao.selectEstmCmtmProgList(parameterMap));
		trans.put("estmCmtmCmplListTotCnt", oproEstmCmtmProgDao.selectEstmCmtmProgListTotCnt(parameterMap));
	
		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmCmplListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_CMPL");
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		trans.put("dataList", oproEstmCmtmProgDao.selectEstmCmtmProgExcelList(parameterMap));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmtmCmplDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// ???????????? ??????
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		// ???????????? ???????????? ???????????? ???????????? ??????
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// ???????????? ???????????? ??????
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		//???????????????????????? ?????? ??????
		parameterMap.put("P_CD_ID", "FILE_DOC_SECD");
		List<FwkDataEntity> estmCmtmFileCodeList = oproCommDefaultDao.selectCodeList(parameterMap); 
		trans.put("estmCmtmFileCodeList", estmCmtmFileCodeList);

		//???????????????????????? ??????
		List<FwkDataEntity> estmCmtmFileList = oproEstmCmtmProgDao.selectCmtmFileList(parameterMap); 
		trans.put("estmCmtmFileList", estmCmtmFileList);
		
		// ????????????
		FwkDataEntity FILE_SIGN_YN = (FwkDataEntity) oproEstmCmtmProgDao.selectCmtmFileSignYn(parameterMap);   // T_ESTM_FILE_SIGN
		trans.put("FILE_SIGN_YN", FILE_SIGN_YN);
		
		System.err.println("@@@ FILE_SIGN_YN ==> " + FILE_SIGN_YN);
		
		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmCmplProcdADetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ???????????? ??????
		FwkDataEntity estmProcdDetail = oproEstmCmtmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmObjList = oproEstmCmtmProgDao.selectEstmObjValueList(parameterMap);
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmCmtmLastList = oproEstmCmtmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????+???????????? ?????? ??????
		List<FwkDataEntity> estmValueList = oproEstmCmtmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", oproEstmCmtmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);

		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmCmplProcdBDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
parameterMap.put("P_DEL_AT", "N");
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ???????????? ??????
		FwkDataEntity estmProcdDetail = oproEstmCmtmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmObjList = oproEstmCmtmProgDao.selectEstmObjValueList(parameterMap);
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmCmtmLastList = oproEstmCmtmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????+???????????? ?????? ??????
		List<FwkDataEntity> estmValueList = oproEstmCmtmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", oproEstmCmtmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);

		return trans;
	}

	@Override
	public FwkTransferObject estmCmtmCmplVidoMtngDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT","N");
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		//??????????????????
		List<FwkDataEntity> estmVidoList = oproEstmCmtmProgDao.selectEstmMngProgVidoMtngList(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmVidoList", estmVidoList);
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = oproEstmCmtmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);

		return trans;
	}
}
