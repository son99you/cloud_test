package com.eunwoosoft.ipro.sytm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmEstmFrmDao;
import com.eunwoosoft.ipro.sytm.dao.IproSytmDsgnRecmDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmFrmService;
import com.eunwoosoft.ipro.sytm.service.IproSytmDsgnRecmService;

/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmContFormServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 26.
 *
 */
@Service(value="iproSytmDsgnRecmService")
public class IproSytmDsgnRecmServiceImpl extends AbstractFwkService implements IproSytmDsgnRecmService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmDsgnRecmServiceImpl.class);
	
	@Resource(name="iproSytmDsgnRecmDao") 
	private IproSytmDsgnRecmDao iproSytmDsgnRecmDao;
	
	@Resource(name = "comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	static final String contextPath = "form";
	
	

	@Override
	public FwkTransferObject dsgnRecmListWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_CD_ID", "PCRQ_RECM_FSCD");
		trans.put(IproSytmDsgnRecmService.DSGN_RECM_LIST, iproSytmDsgnRecmDao.selectDsgnRecmListWithPgng(parameterMap));
		trans.put(IproSytmDsgnRecmService.DSGN_RECM_LIST_TOTCNT, iproSytmDsgnRecmDao.selectDsgnRecmListTotCnt(parameterMap));

		return trans;
	}
	
	
	public FwkTransferObject dsgnRecmFileDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_CD_ID", "PCRQ_RECM_FSCD");
		
		// 설계추천파일관리
		trans.put(IproSytmDsgnRecmService.DSGN_RECM_DETAIL, iproSytmDsgnRecmDao.selectDsgnRecmDetail(parameterMap));
		// 설계추천파일조회
		trans.put(IproSytmDsgnRecmService.DSGN_RECM_FILE_LIST, iproSytmDsgnRecmDao.selectDsgnRecmFileList(parameterMap));

		return trans;
	}
	
	
	public FwkTransferObject dsgnRecmFileRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		// 서식번호 생성
		parameterMap.put("P_USE_YN", "Y"); 
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_CD_ID", "PCRQ_RECM_FSCD");
		parameterMap.put("P_CD_NM", "설계추천파일");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		/*---------------파일서식필수문서 업로드 ---------------*/
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload2(multipartRequest, "P_FILE", contextPath, parameterMap);
		
		if(fileParameterMap != null) {
			List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
			
			parameterMap.put("atchFileGroupNo", fileParameterMap.get("atchFileGroupNo"));
			parameterMap.put("fileList", pfileList);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertMMFileRegist(parameterMap);
		}else{
			parameterMap.put("atchFileGroupNo", "");
		}
		 /*-------------------------------------------------*/
	
		// 마스터 등록
		iproSytmDsgnRecmDao.insertDsgnRecmMst(parameterMap);
		
		trans.put("P_CD_DTL_ID", parameterMap.get("P_CD_DTL_ID"));
		
		return trans;
	}



	public FwkTransferObject dsgnRecmFileUpdate(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		try {
			// 계약서식 내용
			//String pFrmCntn = (String) parameterMap.get("P_FRM_CNTN");
			//parameterMap.put("P_FRM_CNTN", pFrmCntn);
			parameterMap.put("P_USE_YN", 'Y');
			parameterMap.put("P_DEL_AT", 'N'); 
			parameterMap.put("P_CD_ID", "PCRQ_RECM_FSCD"); 
			if (parameterMap.get("P_CD_DTL_ID") != null) {
				
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
				
				/*---------------파일서식필수문서 업로드 ---------------*/
				
				//파일삭제 
				ArrayList delList = CmmnUtil.paramSet(parameterMap.get("P_FILE_SN"));
				System.err.println("delList  >> " + delList);
				if(delList != null){
					FwkParameterMap param = new FwkParameterMap();
					if (delList.size() > 0) {
						param.put("delList", delList);
						param.put("P_FILE_GRP_NO", parameterMap.get("P_FILE_GRP_NO"));
						iproSytmDsgnRecmDao.updateDsgnRecmFileDel(param); 
					}
				}
				
				FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload2(multipartRequest, "P_FILE", contextPath, parameterMap);
				
				if(fileParameterMap != null) {
					List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
					
					parameterMap.put("fileList", pfileList);
					parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
					//공통 첨부파일에 저장 T_MM_FILE_MST
					comAtmaAtchFileDao.insertMMFileRegist(parameterMap);
					parameterMap.put("P_FILE_GRP_NO",parameterMap.get("atchFileGroupNo"));
				}else{
					
				}
				
				// 마스터 수정
				iproSytmDsgnRecmDao.updateDsgnRecmMst(parameterMap);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== IproContFormMngeServiceImpl.contFormModi Error == ");
		}

		trans.put("P_CD_DTL_ID", parameterMap.get("P_CD_DTL_ID"));
		return trans;
	}


}
