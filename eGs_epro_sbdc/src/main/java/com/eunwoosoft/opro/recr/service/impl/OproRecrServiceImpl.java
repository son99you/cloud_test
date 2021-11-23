package com.eunwoosoft.opro.recr.service.impl;

import java.sql.SQLException;

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
import com.eunwoosoft.opro.recr.dao.OproRecrDao;
import com.eunwoosoft.opro.recr.service.OproRecrService;

/**
 * 
 * com.eunwoosoft.opro.recr.service.impl
 * OproRecrServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 29.
 *
 */
@Service(value="oproRecrService")
public class  OproRecrServiceImpl extends AbstractFwkService implements OproRecrService {
	private static final Logger LOG = LoggerFactory.getLogger(OproRecrServiceImpl.class); 
	
	@Resource(name="oproRecrDao") 
	private OproRecrDao oproRecrDao;  

	@Resource(name="comAtmaAtchFileService") 
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;


	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.opro.recr.service.OprorecrGnrlService#recrGnrlListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */

	@Override
	public FwkTransferObject recrGnrlListWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put(OproRecrService.RECR_GNRL_LIST, oproRecrDao.recrGnrlListWithPgng(parameterMap));
		trans.put(OproRecrService.RECR_GNRL_LIST_TOTCNT, oproRecrDao.recrGnrlListTotCnt(parameterMap));
		return trans;
	}
	
	@Override
	public FwkTransferObject recrAnncListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put(OproRecrService.RECR_GNRL_LIST, oproRecrDao.recrAnncListExcelDwld(parameterMap));
		return trans;
	}
	
	
	

	//평가공고 상세
	@Override
	public FwkTransferObject recrGnrlDetail(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		// recrAnncRegForm.jsp에서 크리에이터 신청, 수정할 수 있도록 데이터를 불러오는 과정
		
		/*<< 크리에이터 신청할 수 있는 폼>>*/
		FwkDataEntity recrGnrlDetail = (FwkDataEntity)oproRecrDao.recrGnrlDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put(OproRecrService.RECR_GNRL_DETAIL, recrGnrlDetail);
		
		parameterMap.put("P_DEL_AT",  "N");
		parameterMap.put("P_ESTM_FSCD", "MST");
		
		FwkDataEntity recrFile = (FwkDataEntity)oproRecrDao.recrGnrlFile(parameterMap); //T_ESTM_FILE
		trans.put("recrFile", recrFile);
		
		// 키 값 불러오는지 확인 
		int P_ESTM_OBJ_SEQ = parameterMap.getInt("P_ESTM_OBJ_SEQ");
				
		/*<<기존 크리에이터 정보 있는 경우 값 불러오는 과정>>*/  
		if(P_ESTM_OBJ_SEQ  > 0){
			
			// 크리에이터 정보 불러오기 
			FwkDataEntity recrAnncDetail = (FwkDataEntity)oproRecrDao.recrAnncDetail(parameterMap);   // T_ESTM_CRTR_MST
			parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
			
			//크리에이터 주민번호 불러주는 역할
			FwkDataEntity estmObjRsdnNo =  (FwkDataEntity)oproRecrDao.recrResdNo(parameterMap);   // T_ESTM_OBJ 
			trans.put(OproRecrService.RECR_ANNC_DETAIL, recrAnncDetail);
			trans.put(OproRecrService.ESTM_RSDN_NO, estmObjRsdnNo);
		}
		return trans;
	}
	
	//평가공고 신청 중복확인 - 크리에이터 
	@Override
	public FwkTransferObject recrAnncCheck(final FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_ESTM_OBJ_SEQ").equals("")) { //평가대상 키값이 없다면
			
			parameterMap.put("P_RSDN_NO||P_TEL_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2") + "" + parameterMap.get("P_TEL_NO"));
			FwkDataEntity estmObjPeNoYn =   (FwkDataEntity)oproRecrDao.recrAnncCheck(parameterMap); //크리에이터 주민번호, 전화번호 중복확인하는 역할
			FwkDataEntity recrGnrlDetail = (FwkDataEntity)oproRecrDao.recrGnrlDetail(parameterMap); // T_ESTM_MNG_MST M
			
			if(estmObjPeNoYn != null){ //주민번호와 전화번호가 DB에 동일하게 있다면
				
				trans.put("estmObjPeNo", estmObjPeNoYn.get("ESTM_OBJ_PE_NO"));
				trans.put("ESTM_OBJ_SEQ", estmObjPeNoYn.get("ESTM_OBJ_SEQ"));
				
				trans.put(OproRecrService.ESTM_PENO_YN, estmObjPeNoYn);
				trans.put(OproRecrService.RECR_GNRL_DETAIL, recrGnrlDetail);
				trans.put("res", 1);
			}else{
				trans.put("res", 0);
			}
		}
		return trans;
	}

	//평가공고 신청 - 크리에이터 
	@Override
	public FwkTransferObject recrAnncReg(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		/*<< 평가공고 크리에이터 정보 추가하기 >> */
		 oproRecrDao.recrAnncReg(parameterMap);   // T_ESTM_CRTR_MST INSERT
		System.out.println("평가모집 신청 parameterMap::" + parameterMap);
		
		/*<< 평가대상에 크리에이터 정보(주민번호, 전화번호) 추가하기 >> */
		if(parameterMap.get("P_ESTM_OBJ_SEQ").equals("")) {  //평가대상 키 값이 앖다면  ==> 즉, 평가대상/크리에이터 정보 저장 
			
			parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
			System.err.println("@@@ T_ESTM_OBJ 등록 @@@" + parameterMap.get("P_RSDN_NO"));
			
			parameterMap.put("P_DEL_AT",  "N");
			oproRecrDao.insertRecrObj(parameterMap); // T_ESTM_OBJ INSERT
		}
		
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
		
		return trans;
	}

	//평가공고 신청 - 업체 
	@Override
	public FwkTransferObject recrAnncEstmReg(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		oproRecrDao.insertRecrEstmObj(parameterMap);
		
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
		
		return trans;
	}


	//평가공고 수정 - 크리에이터
	@Override
	public FwkTransferObject recrUpdt(final FwkParameterMap parameterMap) throws Exception,  SQLException{
		FwkTransferObject trans = new FwkTransferObject();
		
			
		oproRecrDao.recrAnncUpdt(parameterMap);  //T_ESTM_CRTR_MST INSERT - 크리에이터 정보 
			
		parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
		parameterMap.put("P_DEL_AT",  "N");
		oproRecrDao.recrObjUpdt(parameterMap); // T_ESTM_OBJ INSERT - 전화번호, 주민번호 

		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
		
		return trans;
	}

}
