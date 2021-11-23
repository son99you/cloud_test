package com.eunwoosoft.ipro.estm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.exception.FwkSysException;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmMngPoolDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngPoolService;

@Service(value="iproEstmCmtmMngPoolService")
public class IproEstmCmtmMngPoolServiceImpl extends AbstractFwkService implements IproEstmCmtmMngPoolService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmMngPoolServiceImpl.class);
	
	static final String contextPath = "estm/pool";
	
	@Resource(name="comAtmaAtchFileService") 
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="iproEstmCmtmMngPoolDao") 
	private IproEstmCmtmMngPoolDao iproEstmCmtmMngPoolDao;

	@Resource(name="comAtmaAtchFileDao") 
	private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Override
	public FwkTransferObject cmtmMngPoolList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_CMTM_REG_DEPT_NO_S", user.getString("DEPT_NO"));
		
		trans.put("cmtmMngPoolList", iproEstmCmtmMngPoolDao.selectCmtmMngPoolList(parameterMap));
		trans.put("cmtmMngPoolListTotCnt", iproEstmCmtmMngPoolDao.selectCmtmMngPoolListTotCnt(parameterMap));
		//trans.put("realEstmYnList", iproEstmCmtmMngPoolDao.selectRealEstmYnList(parameterMap));
		return trans;
	}
	
	@Override
	public FwkTransferObject cmtmMngPoolListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		
		parameterMap.put("P_DEL_AT", "N");
		
		parameterMap.put("P_ESTM_CMTM_REG_DEPT_NO_S", user.getString("DEPT_NO"));
		
		trans.put("cmtmMngPoolList", iproEstmCmtmMngPoolDao.selectCmtmMngPoolListExcelDwld(parameterMap));
	
		return trans;
	}
	

	@Override
	public FwkTransferObject cmtmMngPoolRegist(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		
		String fileGrpNo = fileUpload(parameterMap, multipartRequest, "P_FILE");
		parameterMap.put("P_MST_FILE_GRP_NO", fileGrpNo);
		parameterMap.put("P_FILE_GRP_NO", "");
		
		// 평가위원POOL 등록
		iproEstmCmtmMngPoolDao.insertEstmCmtmPoolMst(parameterMap);   // T_ESTM_CMTM_POOL_MST INSERT
		
		/********** 평가위원자격증 Start **********/
		if(parameterMap.get("P_CRQF_NM") != null) {
			
			Object P_CRQF_NM = parameterMap.get("P_CRQF_NM");   // 자격증명
			Object P_PBLS_AGNC = parameterMap.get("P_PBLS_AGNC");   // 발행기관
			Object P_ACQS_DE = parameterMap.get("P_ACQS_DE");   // 취득일
			
			if(P_CRQF_NM != null){
				if(P_CRQF_NM instanceof String){
					
					parameterMap.put("P_ACQS_DE", FwkFormatUtil.formatDate(parameterMap.get("P_ACQS_DE").toString(), "yyyy-MM-dd", "yyyyMMdd")); 

					// 평가위원자격증 등록
					iproEstmCmtmMngPoolDao.insertEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF INSERT
				
				}else if(P_CRQF_NM instanceof ArrayList){
					ArrayList<String> crqfNmList = (ArrayList<String>) P_CRQF_NM;
					ArrayList<String> pblsAgncList = (ArrayList<String>) P_PBLS_AGNC;
					ArrayList<String> acqsDeList = (ArrayList<String>) P_ACQS_DE;
					
					for(int i =0; i < crqfNmList.size(); i++){
						
						parameterMap.put("P_CRQF_NM", crqfNmList.get(i));
						parameterMap.put("P_PBLS_AGNC", pblsAgncList.get(i));
						
						if(!"".equals(acqsDeList.get(i))){
							parameterMap.put("P_ACQS_DE", FwkFormatUtil.formatDate(acqsDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd")); 
						}
						
						// 평가위원자격증 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF INSERT
						
					}
				}else if(P_CRQF_NM instanceof String[]){
					
					String[] crqfNmStr = (String[]) P_CRQF_NM;
					String[] pblsAgncStr = (String[]) P_PBLS_AGNC;
					String[] acqsDeStr = (String[]) P_ACQS_DE;
					
					for (int i = 0; i < crqfNmStr.length; i++) {
						
						parameterMap.put("P_CRQF_NM", crqfNmStr[i]);
						parameterMap.put("P_PBLS_AGNC", pblsAgncStr[i]);

						if(!"".equals(acqsDeStr[i])){
							parameterMap.put("P_ACQS_DE", FwkFormatUtil.formatDate(acqsDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd")); 
						}
						
						// 평가위원자격증 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF INSERT
						
					}
				}
			}
		}
		/********** 평가위원자격증 end **********/
		
		
		
		/********** 평가위원직장경력 Start **********/
		if(parameterMap.get("P_OFC_NM") != null) {
			
			Object P_OFC_NM = parameterMap.get("P_OFC_NM");   // 직장명
			Object P_WRK_DEPT_NM = parameterMap.get("P_WRK_DEPT_NM");   // 근무부서
			Object P_OPNM = parameterMap.get("P_OPNM");   // 직위
			Object P_WRK_YEAR_CNT = parameterMap.get("P_WRK_YEAR_CNT");   // 근속년수
			Object P_CHRG_TSK_ACPS = parameterMap.get("P_CHRG_TSK_ACPS");   // 담당업무_실적
			
			if(P_OFC_NM != null){
				if(P_OFC_NM instanceof String){
					// 평가위원직장경력 등록
					iproEstmCmtmMngPoolDao.insertEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE INSERT
				}else if(P_OFC_NM instanceof ArrayList){
					ArrayList<String> ofcNmList = (ArrayList<String>) P_OFC_NM;
					ArrayList<String> wrkDeptNmList = (ArrayList<String>) P_WRK_DEPT_NM;
					ArrayList<String> opnmList = (ArrayList<String>) P_OPNM;
					ArrayList<String> wrkYearCntList = (ArrayList<String>) P_WRK_YEAR_CNT;
					ArrayList<String> chrgTskAcpsList = (ArrayList<String>) P_CHRG_TSK_ACPS;
					
					for(int i =0; i < ofcNmList.size(); i++){
						
						parameterMap.put("P_OFC_NM", ofcNmList.get(i));
						parameterMap.put("P_WRK_DEPT_NM", wrkDeptNmList.get(i));
						parameterMap.put("P_OPNM", opnmList.get(i));
						parameterMap.put("P_WRK_YEAR_CNT", wrkYearCntList.get(i));
						parameterMap.put("P_CHRG_TSK_ACPS", chrgTskAcpsList.get(i));
						
						// 평가위원직장경력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE INSERT
						
					}
				}else if(P_OFC_NM instanceof String[]){
					
					String[] ofcNmStr = (String[]) P_OFC_NM;
					String[] wrkDeptNmStr = (String[]) P_WRK_DEPT_NM;
					String[] opnmStr = (String[]) P_OPNM;
					String[] wrkYearCntStr = (String[]) P_WRK_YEAR_CNT;
					String[] chrgTskAcpsStr = (String[]) P_CHRG_TSK_ACPS;
					
					for (int i = 0; i < ofcNmStr.length; i++) {
						
						parameterMap.put("P_OFC_NM", ofcNmStr[i]);
						parameterMap.put("P_WRK_DEPT_NM", wrkDeptNmStr[i]);
						parameterMap.put("P_OPNM", opnmStr[i]);
						parameterMap.put("P_WRK_YEAR_CNT", wrkYearCntStr[i]);
						parameterMap.put("P_CHRG_TSK_ACPS", chrgTskAcpsStr[i]);

						
						// 평가위원직장경력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE INSERT
						
					}
				}
			}
		}
		/********** 평가위원직장경력 end **********/
		
		
		/********** 평가위원학력 Start **********/
		
		if(parameterMap.get("P_MSDG") != null) {
			
			Object P_MSDG = parameterMap.get("P_MSDG");   // 학위
			Object P_SHL = parameterMap.get("P_SHL");   // 학교
			Object P_SCCT = parameterMap.get("P_SCCT");   // 전공
			Object P_TE_FROM_Y = parameterMap.get("P_TE_FROM_Y");   // 시작 기간 년
			Object P_TE_FROM_M = parameterMap.get("P_TE_FROM_M");   // 시작 기간 월
			Object P_TE_TO_Y = parameterMap.get("P_TE_TO_Y");   // 종료 기간 년
			Object P_TE_TO_M = parameterMap.get("P_TE_TO_M");   // 종료 기간 월
			
			int no = 0;
			if(P_MSDG != null){
				if(P_MSDG instanceof String){
					//SEQ selectkey
					int educSeq = iproEstmCmtmMngPoolDao.selectEducSeq(parameterMap);
					parameterMap.put("P_EDUC_SEQ", educSeq);
					parameterMap.put("P_FILE_DOC_SECD", educSeq);
					parameterMap.put("P_TE_FROM", parameterMap.getString("P_TE_FROM_Y") + parameterMap.getString("P_TE_FROM_M"));
					parameterMap.put("P_TE_TO", parameterMap.getString("P_TE_TO_Y") + parameterMap.getString("P_TE_TO_M"));
					//파일저장
					parameterMap.put("P_NO", no);
					String educfileGrpNo = fileUpload(parameterMap, multipartRequest, "P_EDUC_FILE");
					parameterMap.put("P_EDUC_FILE_GRP_NO", educfileGrpNo);
					// 평가위원학력 등록
					iproEstmCmtmMngPoolDao.insertEstmCmtmEduc(parameterMap);   // T_ESTM_CMTM_EDUC INSERT
					
				}else if(P_MSDG instanceof ArrayList){
					ArrayList<String> msdgList = (ArrayList<String>) P_MSDG;
					ArrayList<String> shlList = (ArrayList<String>) P_SHL;
					ArrayList<String> scctList = (ArrayList<String>) P_SCCT;
					ArrayList<String> teFromYList = (ArrayList<String>) P_TE_FROM_Y;
					ArrayList<String> teFromMList = (ArrayList<String>) P_TE_FROM_M;
					ArrayList<String> teToYList = (ArrayList<String>) P_TE_TO_Y;
					ArrayList<String> teToMList = (ArrayList<String>) P_TE_TO_M;
					
					for(int i =0; i < msdgList.size(); i++){
						
						parameterMap.put("P_MSDG", msdgList.get(i));
						parameterMap.put("P_SHL", shlList.get(i));
						parameterMap.put("P_SCCT", scctList.get(i));
						parameterMap.put("P_TE_FROM_Y", teFromYList.get(i));
						parameterMap.put("P_TE_FROM_M", teFromMList.get(i));
						parameterMap.put("P_TE_TO_Y", teToYList.get(i));
						parameterMap.put("P_TE_TO_M", teToMList.get(i));
						
						//SEQ selectkey
						int educSeq = iproEstmCmtmMngPoolDao.selectEducSeq(parameterMap);
						parameterMap.put("P_EDUC_SEQ", educSeq);
						parameterMap.put("P_FILE_DOC_SECD", educSeq);
						parameterMap.put("P_TE_FROM", parameterMap.getString("P_TE_FROM_Y") + parameterMap.getString("P_TE_FROM_M"));
						parameterMap.put("P_TE_TO", parameterMap.getString("P_TE_TO_Y") + parameterMap.getString("P_TE_TO_M"));
						
						//파일저장
						parameterMap.put("P_NO", no);
						String educfileGrpNo = fileUpload(parameterMap, multipartRequest,"P_EDUC_FILE");
						parameterMap.put("P_EDUC_FILE_GRP_NO", educfileGrpNo);
						// 평가위원학력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmEduc(parameterMap);   // T_ESTM_CMTM_EDUC INSERT
						no += 1;
					}
					
				}else if(P_MSDG instanceof String[]){
					String[] msdgStr = (String[]) P_MSDG;
					String[] shlStr = (String[]) P_SHL;
					String[] scctStr = (String[]) P_SCCT;
					String[] teFromYStr = (String[]) P_TE_FROM_Y;
					String[] teFromMStr = (String[]) P_TE_FROM_M;
					String[] teToYStr = (String[]) P_TE_TO_Y;
					String[] teToMYStr = (String[]) P_TE_TO_M;
					
					for (int i = 0; i < msdgStr.length; i++) {
						parameterMap.put("P_MSDG", msdgStr[i]);
						parameterMap.put("P_SHL", shlStr[i]);
						parameterMap.put("P_SCCT", scctStr[i]);
						parameterMap.put("P_TE_FROM_Y", teFromYStr[i]);
						parameterMap.put("P_TE_FROM_M", teFromMStr[i]);
						parameterMap.put("P_TE_TO_Y", teToYStr[i]);
						parameterMap.put("P_TE_TO_M", teToMYStr[i]);

						//SEQ selectkey
						int educSeq = iproEstmCmtmMngPoolDao.selectEducSeq(parameterMap);
						parameterMap.put("P_EDUC_SEQ", educSeq);
						parameterMap.put("P_FILE_DOC_SECD", educSeq);
						parameterMap.put("P_TE_FROM", parameterMap.getString("P_TE_FROM_Y") + parameterMap.getString("P_TE_FROM_M"));
						parameterMap.put("P_TE_TO", parameterMap.getString("P_TE_TO_Y") + parameterMap.getString("P_TE_TO_M"));
						//파일저장
						parameterMap.put("P_NO", no);						
						String educfileGrpNo = fileUpload(parameterMap, multipartRequest,"P_EDUC_FILE");
						parameterMap.put("P_EDUC_FILE_GRP_NO", educfileGrpNo);
						// 평가위원학력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmEduc(parameterMap);   // T_ESTM_CMTM_EDUC INSERT
						no += 1;
					}
				}
			}
		}
		/********** 평가위원학력 end **********/
		
		
		/********** 평가위원활동경력 Start **********/
		if(parameterMap.get("P_BSNS_NM") != null) {
			
			Object P_BSNS_NM = parameterMap.get("P_BSNS_NM");   // 사업명
			Object P_ESTM_SPHE = parameterMap.get("P_ESTM_SPHE");   // 평가분야
			Object P_RUN_YR = parameterMap.get("P_RUN_YR");   // 수행년도
			Object P_AGNC_NM = parameterMap.get("P_AGNC_NM");   // 기관명
			
			
			if(P_BSNS_NM != null){
				if(P_BSNS_NM instanceof String){

					// 평가위원활동경력
					iproEstmCmtmMngPoolDao.insertEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE INSERT
				
				}else if(P_BSNS_NM instanceof ArrayList){
					ArrayList<String> bsnsNmList = (ArrayList<String>) P_BSNS_NM;
					ArrayList<String> estmSpheList = (ArrayList<String>) P_ESTM_SPHE;
					ArrayList<String> runYrList = (ArrayList<String>) P_RUN_YR;
					ArrayList<String> agncNmList = (ArrayList<String>) P_AGNC_NM;
					
					for(int i =0; i < bsnsNmList.size(); i++){
						
						parameterMap.put("P_BSNS_NM", bsnsNmList.get(i));
						parameterMap.put("P_ESTM_SPHE", estmSpheList.get(i));
						parameterMap.put("P_RUN_YR", runYrList.get(i));
						parameterMap.put("P_AGNC_NM", agncNmList.get(i));
						
						// 평가위원활동경력
						iproEstmCmtmMngPoolDao.insertEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE INSERT
						
					}
					
				}else if(P_BSNS_NM instanceof String[]){
					
					String[] bsnsNmStr = (String[]) P_BSNS_NM;
					String[] estmSpheStr = (String[]) P_ESTM_SPHE;
					String[] runYrStr = (String[]) P_RUN_YR;
					String[] agncNmStr = (String[]) P_AGNC_NM;
					
					for (int i = 0; i < bsnsNmStr.length; i++) {
						
						parameterMap.put("P_BSNS_NM", bsnsNmStr[i]);
						parameterMap.put("P_ESTM_SPHE", estmSpheStr[i]);
						parameterMap.put("P_RUN_YR", runYrStr[i]);
						parameterMap.put("P_AGNC_NM", agncNmStr[i]);

						
						// 평가위원활동경력
						iproEstmCmtmMngPoolDao.insertEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE INSERT
						
					}
				}
			}
		}
		/********** 평가위원활동경력 end **********/
		
		trans.put("P_ESTM_CMTM_NO_TRANS", parameterMap.get("P_ESTM_CMTM_NO"));
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngPoolUpdt(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		
		String fileGrpNo = parameterMap.getString("P_FILE_GRP_NO");
		if("Y".equals(parameterMap.get("P_FILE_MOD_YN"))){
			fileGrpNo = fileUpload(parameterMap, multipartRequest, "P_FILE");
		}
		parameterMap.put("P_MST_FILE_GRP_NO", fileGrpNo);
		parameterMap.put("P_FILE_GRP_NO", "");
		
		// 평가위원POOL 등록
		iproEstmCmtmMngPoolDao.updateEstmCmtmPoolMst(parameterMap);   // T_ESTM_CMTM_POOL_MST update
		
		// 평가위원자격증 삭제
		iproEstmCmtmMngPoolDao.deleteEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF delete
		// 평가위원직장경력 삭제
		iproEstmCmtmMngPoolDao.deleteEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE delete
		// 평가위원활동경력 삭제
		iproEstmCmtmMngPoolDao.deleteEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE delete
		
		/********** 평가위원자격증 Start **********/
		if(parameterMap.get("P_CRQF_NM") != null) {
			
			Object P_CRQF_NM = parameterMap.get("P_CRQF_NM");   // 자격증명
			Object P_PBLS_AGNC = parameterMap.get("P_PBLS_AGNC");   // 발행기관
			Object P_ACQS_DE = parameterMap.get("P_ACQS_DE");   // 취득일
			
			if(P_CRQF_NM != null){
				
				if(P_CRQF_NM instanceof String){
					
					parameterMap.put("P_ACQS_DE", FwkFormatUtil.formatDate(parameterMap.get("P_ACQS_DE").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
					
					// 평가위원자격증 등록
					iproEstmCmtmMngPoolDao.insertEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF INSERT
					
				}else if(P_CRQF_NM instanceof ArrayList){
					ArrayList<String> crqfNmList = (ArrayList<String>) P_CRQF_NM;
					ArrayList<String> pblsAgncList = (ArrayList<String>) P_PBLS_AGNC;
					ArrayList<String> acqsDeList = (ArrayList<String>) P_ACQS_DE;
					
					for(int i =0; i < crqfNmList.size(); i++){
						
						
						parameterMap.put("P_CRQF_NM", crqfNmList.get(i));
						parameterMap.put("P_PBLS_AGNC", pblsAgncList.get(i));
						
						if(!"".equals(acqsDeList.get(i))){
							parameterMap.put("P_ACQS_DE", FwkFormatUtil.formatDate(acqsDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd")); 
						}
						// 평가위원자격증 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF INSERT
						
					}
				}else if(P_CRQF_NM instanceof String[]){
					
					String[] crqfNmStr = (String[]) P_CRQF_NM;
					String[] pblsAgncStr = (String[]) P_PBLS_AGNC;
					String[] acqsDeStr = (String[]) P_ACQS_DE;
					
					for (int i = 0; i < crqfNmStr.length; i++) {
						
						parameterMap.put("P_CRQF_NM", crqfNmStr[i]);
						parameterMap.put("P_PBLS_AGNC", pblsAgncStr[i]);
						
						if(!"".equals(acqsDeStr[i])){
							parameterMap.put("P_ACQS_DE", FwkFormatUtil.formatDate(acqsDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd")); 
						}
						// 평가위원자격증 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmCrqf(parameterMap);   // T_ESTM_CMTM_CRQF INSERT
						
					}
				}
			}
		}
		/********** 평가위원자격증 end **********/
		
		
		
		/********** 평가위원직장경력 Start **********/
		if(parameterMap.get("P_OFC_NM") != null) {
			
			Object P_OFC_NM = parameterMap.get("P_OFC_NM");   // 직장명
			Object P_WRK_DEPT_NM = parameterMap.get("P_WRK_DEPT_NM");   // 근무부서
			Object P_OPNM = parameterMap.get("P_OPNM");   // 직위
			Object P_WRK_YEAR_CNT = parameterMap.get("P_WRK_YEAR_CNT");   // 근속년수
			Object P_CHRG_TSK_ACPS = parameterMap.get("P_CHRG_TSK_ACPS");   // 담당업무_실적
			
			if(P_OFC_NM != null){
				if(P_OFC_NM instanceof String){
					
					// 평가위원직장경력 등록
					iproEstmCmtmMngPoolDao.insertEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE INSERT
					
				}else if(P_OFC_NM instanceof ArrayList){
					ArrayList<String> ofcNmList = (ArrayList<String>) P_OFC_NM;
					ArrayList<String> wrkDeptNmList = (ArrayList<String>) P_WRK_DEPT_NM;
					ArrayList<String> opnmList = (ArrayList<String>) P_OPNM;
					ArrayList<String> wrkYearCntList = (ArrayList<String>) P_WRK_YEAR_CNT;
					ArrayList<String> chrgTskAcpsList = (ArrayList<String>) P_CHRG_TSK_ACPS;
					
					for(int i =0; i < ofcNmList.size(); i++){
						
						
						parameterMap.put("P_OFC_NM", ofcNmList.get(i));
						parameterMap.put("P_WRK_DEPT_NM", wrkDeptNmList.get(i));
						parameterMap.put("P_OPNM", opnmList.get(i));
						parameterMap.put("P_WRK_YEAR_CNT", wrkYearCntList.get(i));
						parameterMap.put("P_CHRG_TSK_ACPS", chrgTskAcpsList.get(i));
						
						// 평가위원직장경력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE INSERT
						
					}
				}else if(P_OFC_NM instanceof String[]){
					
					String[] ofcNmStr = (String[]) P_OFC_NM;
					String[] wrkDeptNmStr = (String[]) P_WRK_DEPT_NM;
					String[] opnmStr = (String[]) P_OPNM;
					String[] wrkYearCntStr = (String[]) P_WRK_YEAR_CNT;
					String[] chrgTskAcpsStr = (String[]) P_CHRG_TSK_ACPS;
					
					for (int i = 0; i < ofcNmStr.length; i++) {
						
						parameterMap.put("P_OFC_NM", ofcNmStr[i]);
						parameterMap.put("P_WRK_DEPT_NM", wrkDeptNmStr[i]);
						parameterMap.put("P_OPNM", opnmStr[i]);
						parameterMap.put("P_WRK_YEAR_CNT", wrkYearCntStr[i]);
						parameterMap.put("P_CHRG_TSK_ACPS", chrgTskAcpsStr[i]);
						
						
						// 평가위원직장경력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmOfcCare(parameterMap);   // T_ESTM_CMTM_OFC_CARE INSERT
						
					}
				}
			}
		}
		/********** 평가위원직장경력 end **********/
		
		
		/********** 평가위원학력 Start **********/
		
		parameterMap.put("P_FILE_GRP_NO", parameterMap.get("P_EDUC_FILE_GRP_NO"));
		if(!"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			//파일삭제
			comAtmaAtchFileDao.deleteEducFileDelete(parameterMap);
		}
		//평가위원학력 삭제
		iproEstmCmtmMngPoolDao.deleteEstmCmtmEduc(parameterMap);
	
		if(parameterMap.get("P_MSDG") != null) {
			Object P_MSDG = parameterMap.get("P_MSDG");   // 학위
			Object P_SHL = parameterMap.get("P_SHL");   // 학교
			Object P_SCCT = parameterMap.get("P_SCCT");   // 전공
			Object P_TE_FROM_Y = parameterMap.get("P_TE_FROM_Y");   // 시작 기간 년
			Object P_TE_FROM_M = parameterMap.get("P_TE_FROM_M");   // 시작 기간 월
			Object P_TE_TO_Y = parameterMap.get("P_TE_TO_Y");   // 종료 기간 년
			Object P_TE_TO_M = parameterMap.get("P_TE_TO_M");   // 종료 기간 월
			Object P_EDUC_SEQ = parameterMap.get("P_EDUC_SEQ"); 
			Object P_FILE_SZ = parameterMap.get("P_FILE_SZ");
			Object P_SV_FILE_NM = parameterMap.get("P_SV_FILE_NM");
			Object P_SYS_FILE_NM = parameterMap.get("P_SYS_FILE_NM");
			Object P_FILE_LCTN = parameterMap.get("P_FILE_LCTN");
			int no = 0;
			if(P_MSDG != null){
				if(P_MSDG instanceof String){
					if("".equals(parameterMap.get("P_EDUC_SEQ"))){
						//파일저장
						parameterMap.put("P_NO", no);
						no += 1;
					}else{
						//파일저장
						parameterMap.put("P_NO", "");
					}
					int educSeq = iproEstmCmtmMngPoolDao.selectEducSeq(parameterMap); 		//select seq
					parameterMap.put("P_EDUC_SEQ", educSeq);
					parameterMap.put("P_FILE_DOC_SECD", educSeq);
					parameterMap.put("P_TE_FROM", parameterMap.getString("P_TE_FROM_Y") + parameterMap.getString("P_TE_FROM_M"));
					parameterMap.put("P_TE_TO", parameterMap.getString("P_TE_TO_Y") + parameterMap.getString("P_TE_TO_M"));
					
					//파일저장(T_MM_FILE_MST)
					String educfileGrpNo = fileUpload(parameterMap, multipartRequest,"P_EDUC_FILE");
					parameterMap.put("P_EDUC_FILE_GRP_NO", educfileGrpNo);
					parameterMap.put("P_FILE_GRP_NO", educfileGrpNo);
					// 평가위원학력 등록
					iproEstmCmtmMngPoolDao.insertEstmCmtmEduc(parameterMap);   // T_ESTM_CMTM_EDUC INSERT
				}else if(P_MSDG instanceof ArrayList){
					ArrayList<String> msdgList = (ArrayList<String>) P_MSDG;
					ArrayList<String> shlList = (ArrayList<String>) P_SHL;
					ArrayList<String> scctList = (ArrayList<String>) P_SCCT;
					
					ArrayList<String> teFromYList = (ArrayList<String>) P_TE_FROM_Y;
					ArrayList<String> teFromMList = (ArrayList<String>) P_TE_FROM_M;
					ArrayList<String> teToYList = (ArrayList<String>) P_TE_TO_Y;
					ArrayList<String> teToMList = (ArrayList<String>) P_TE_TO_M;
					
					ArrayList<String> educSeqList = (ArrayList<String>) P_EDUC_SEQ;
					ArrayList<String> fileSzList = (ArrayList<String>) P_FILE_SZ;
					ArrayList<String> svFileNmList = (ArrayList<String>) P_SV_FILE_NM;
					ArrayList<String> sysFileNmList = (ArrayList<String>) P_SYS_FILE_NM;
					ArrayList<String> fileLctnList = (ArrayList<String>) P_FILE_LCTN;
					
					for(int i =0; i < msdgList.size(); i++){
						parameterMap.put("P_MSDG", msdgList.get(i));
						parameterMap.put("P_SHL", shlList.get(i));
						parameterMap.put("P_SCCT", scctList.get(i));
						
						parameterMap.put("P_TE_FROM_Y", teFromYList.get(i));
						parameterMap.put("P_TE_FROM_M", teFromMList.get(i));
						parameterMap.put("P_TE_TO_Y", teToYList.get(i));
						parameterMap.put("P_TE_TO_M", teToMList.get(i));
						
						parameterMap.put("P_EDUC_SEQ", educSeqList.get(i));
						parameterMap.put("P_FILE_SZ", fileSzList.get(i));
						parameterMap.put("P_SV_FILE_NM", svFileNmList.get(i));
						parameterMap.put("P_SYS_FILE_NM", sysFileNmList.get(i));
						parameterMap.put("P_FILE_LCTN", fileLctnList.get(i));

						if("".equals(parameterMap.get("P_EDUC_SEQ"))){
							//파일저장
							parameterMap.put("P_NO", no);
							no += 1;
						}else{
							//파일저장
							parameterMap.put("P_NO", "");
						}
						int educSeq = iproEstmCmtmMngPoolDao.selectEducSeq(parameterMap); 		//select seq
						parameterMap.put("P_EDUC_SEQ", educSeq);
						parameterMap.put("P_FILE_DOC_SECD", educSeq);
						parameterMap.put("P_TE_FROM", parameterMap.getString("P_TE_FROM_Y") + parameterMap.getString("P_TE_FROM_M"));
						parameterMap.put("P_TE_TO", parameterMap.getString("P_TE_TO_Y") + parameterMap.getString("P_TE_TO_M"));
						
						//파일저장(T_MM_FILE_MST)
						String educfileGrpNo = fileUpload(parameterMap, multipartRequest,"P_EDUC_FILE");
						parameterMap.put("P_EDUC_FILE_GRP_NO", educfileGrpNo);
						parameterMap.put("P_FILE_GRP_NO", educfileGrpNo);
						// 평가위원학력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmEduc(parameterMap);   // T_ESTM_CMTM_EDUC INSERT
					}
					
				}else if(P_MSDG instanceof String[]){
					String[] msdgStr = (String[]) P_MSDG;
					String[] shlStr = (String[]) P_SHL;
					String[] scctStr = (String[]) P_SCCT;
					
					String[] teFromYStr = (String[]) P_TE_FROM_Y;
					String[] teFromMStr = (String[]) P_TE_FROM_M;
					String[] teToYStr = (String[]) P_TE_TO_Y;
					String[] teToMYStr = (String[]) P_TE_TO_M;
					
					String[] seqStr = (String[]) P_EDUC_SEQ;
					String[] fileSzStr = (String[]) P_FILE_SZ;
					String[] svFileNmStr = (String[]) P_SV_FILE_NM;
					String[] sysFileNmStr = (String[]) P_SYS_FILE_NM;
					String[] fileLctnStr = (String[]) P_FILE_LCTN;
					
					for (int i = 0; i < seqStr.length; i++) {
						parameterMap.put("P_MSDG", msdgStr[i]);
						parameterMap.put("P_SHL", shlStr[i]);
						parameterMap.put("P_SCCT", scctStr[i]);
						
						parameterMap.put("P_TE_FROM_Y", teFromYStr[i]);
						parameterMap.put("P_TE_FROM_M", teFromMStr[i]);
						parameterMap.put("P_TE_TO_Y", teToYStr[i]);
						parameterMap.put("P_TE_TO_M", teToMYStr[i]);
						
						parameterMap.put("P_EDUC_SEQ", seqStr[i]);
						parameterMap.put("P_FILE_SZ", fileSzStr[i]);
						parameterMap.put("P_SV_FILE_NM", svFileNmStr[i]);
						parameterMap.put("P_SYS_FILE_NM", sysFileNmStr[i]);
						parameterMap.put("P_FILE_LCTN", fileLctnStr[i]);
						
						if("".equals(parameterMap.get("P_EDUC_SEQ"))){
							parameterMap.put("P_NO", no);
							no += 1;
						}else{
							parameterMap.put("P_NO", "");
						}
						int educSeq = iproEstmCmtmMngPoolDao.selectEducSeq(parameterMap);	 		//select seq
						parameterMap.put("P_EDUC_SEQ", educSeq);
						parameterMap.put("P_FILE_DOC_SECD", educSeq);
						parameterMap.put("P_TE_FROM", parameterMap.getString("P_TE_FROM_Y") + parameterMap.getString("P_TE_FROM_M"));
						parameterMap.put("P_TE_TO", parameterMap.getString("P_TE_TO_Y") + parameterMap.getString("P_TE_TO_M"));
						
						//파일저장(T_MM_FILE_MST)
						String educfileGrpNo = fileUpload(parameterMap, multipartRequest,"P_EDUC_FILE");
						parameterMap.put("P_EDUC_FILE_GRP_NO", educfileGrpNo);
						parameterMap.put("P_FILE_GRP_NO", educfileGrpNo);
						// 평가위원학력 등록
						iproEstmCmtmMngPoolDao.insertEstmCmtmEduc(parameterMap);   // T_ESTM_CMTM_EDUC INSERT
					}
				}
			}
		}
		/********** 평가위원학력 end **********/
		
		
		/********** 평가위원활동경력 Start **********/
		if(parameterMap.get("P_BSNS_NM") != null) {
			
			Object P_BSNS_NM = parameterMap.get("P_BSNS_NM");   // 사업명
			Object P_ESTM_SPHE = parameterMap.get("P_ESTM_SPHE");   // 평가분야
			Object P_RUN_YR = parameterMap.get("P_RUN_YR");   // 수행년도
			Object P_AGNC_NM = parameterMap.get("P_AGNC_NM");   // 기관명
			
			if(P_BSNS_NM != null){
				if(P_BSNS_NM instanceof String){
					
					// 평가위원활동경력
					iproEstmCmtmMngPoolDao.insertEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE INSERT
					
				}else if(P_BSNS_NM instanceof ArrayList){
					ArrayList<String> bsnsNmList = (ArrayList<String>) P_BSNS_NM;
					ArrayList<String> estmSpheList = (ArrayList<String>) P_ESTM_SPHE;
					ArrayList<String> runYrList = (ArrayList<String>) P_RUN_YR;
					ArrayList<String> agncNmList = (ArrayList<String>) P_AGNC_NM;
					
					for(int i =0; i < bsnsNmList.size(); i++){
						
						parameterMap.put("P_BSNS_NM", bsnsNmList.get(i));
						parameterMap.put("P_ESTM_SPHE", estmSpheList.get(i));
						parameterMap.put("P_RUN_YR", runYrList.get(i));
						parameterMap.put("P_AGNC_NM", agncNmList.get(i));
						
						// 평가위원활동경력
						iproEstmCmtmMngPoolDao.insertEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE INSERT
						
					}
					
				}else if(P_BSNS_NM instanceof String[]){
					
					String[] bsnsNmStr = (String[]) P_BSNS_NM;
					String[] estmSpheStr = (String[]) P_ESTM_SPHE;
					String[] runYrStr = (String[]) P_RUN_YR;
					String[] agncNmStr = (String[]) P_AGNC_NM;
					
					for (int i = 0; i < bsnsNmStr.length; i++) {
						
						parameterMap.put("P_BSNS_NM", bsnsNmStr[i]);
						parameterMap.put("P_ESTM_SPHE", estmSpheStr[i]);
						parameterMap.put("P_RUN_YR", runYrStr[i]);
						parameterMap.put("P_AGNC_NM", agncNmStr[i]);
						
						
						// 평가위원활동경력
						iproEstmCmtmMngPoolDao.insertEstmCmtmCare(parameterMap);   // T_ESTM_CMTM_CARE INSERT
						
					}
				}
			}
		}
		/********** 평가위원활동경력 end **********/
		trans.put("P_ESTM_CMTM_NO_TRANS", parameterMap.get("P_ESTM_CMTM_NO"));
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngPoolDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가위원POOL 조회
		FwkDataEntity estmCmtmPoolMstDetail = iproEstmCmtmMngPoolDao.selectEstmCmtmPoolMstDetail(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmCmtmPoolMstDetail", estmCmtmPoolMstDetail);
		parameterMap.put("P_DEL_AT", "N");
		
		if("Y".equals(estmCmtmPoolMstDetail.get("HNDW_REG_YN"))){
			parameterMap.put("P_FILE_GRP_NO", estmCmtmPoolMstDetail.get("FILE_GRP_NO"));
			parameterMap.put("P_FILE_DOC_SECD_S", "HFFC");
			trans.put("estmCmtmPoolHffcFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			parameterMap.put("P_FILE_DOC_SECD_S", "CRQFC");
			trans.put("estmCmtmPoolCrqfcFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 평가위원자격증
		List<FwkDataEntity> estmCmtmCrqfList =  iproEstmCmtmMngPoolDao.selectEstmCmtmCrqfList(parameterMap);   // T_ESTM_CMTM_CRQF
		trans.put("estmCmtmCrqfList", estmCmtmCrqfList);

		// 평가위원직장경력
		List<FwkDataEntity> estmCmtmOfcCareList =  iproEstmCmtmMngPoolDao.selectEstmCmtmOfcCareList(parameterMap);   // T_ESTM_CMTM_OFC_CARE
		trans.put("estmCmtmOfcCareList", estmCmtmOfcCareList);
		
		// 평가위원학력 등록
		List<FwkDataEntity> estmCmtmEducList =  iproEstmCmtmMngPoolDao.selectEstmCmtmEducList(parameterMap);   // T_ESTM_CMTM_EDUC
		trans.put("estmCmtmEducList", estmCmtmEducList);
		
		// 평가위원활동경력
		List<FwkDataEntity> estmCmtmCareList =  iproEstmCmtmMngPoolDao.selectEstmCmtmCareList(parameterMap);   // T_ESTM_CMTM_CARE
		trans.put("estmCmtmCareList", estmCmtmCareList);
		
		return trans;
	}

	public String fileUpload(final FwkParameterMap parameterMap, MultipartHttpServletRequest multipartRequest, String file) throws Exception{
		//구분에 따라 파일업로드
		FwkParameterMap fileParameterMap = new FwkParameterMap();
		if("P_FILE".equals(file)){
			fileParameterMap =  CmmnUtil.fileGbnMultiUpload(multipartRequest, file, contextPath, parameterMap);
		}else{
			fileParameterMap =  CmmnUtil.fileGbnUpload(multipartRequest, file, contextPath, parameterMap);
		}
		String atchFileGroupNo  = "";
		if(fileParameterMap != null){
			List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
			
			atchFileGroupNo = (String) fileParameterMap.get("atchFileGroupNo");
			parameterMap.put("atchFileGroupNo", fileParameterMap.get("atchFileGroupNo"));
			parameterMap.put("P_FILE_DOC_SECD", fileParameterMap.get("P_FILE_DOC_SECD"));
			parameterMap.put("fileList", pfileList);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			
			ArrayList fileDocSecd = CmmnUtil.paramSet(fileParameterMap.get("P_FILE_DOC_SECD"));
			//공통 첨부파일에 저장 T_MM_FILE_MST
			
			if(fileDocSecd.size() == pfileList.size()){
				for(int i=0; i< pfileList.size(); i++){
					parameterMap.put("P_FILE_DOC_SECD", pfileList.get(i).get("P_FILE_DOC_SECD"));
					comAtmaAtchFileDao.deleteAtchFileUpdt(parameterMap);	//DEL_AT ='Y'처리
				}
				
			}else{
				for(int i=0; i< fileDocSecd.size(); i++){
					parameterMap.put("P_FILE_DOC_SECD", fileDocSecd.get(i));
					comAtmaAtchFileDao.deleteAtchFileUpdt(parameterMap);	//DEL_AT ='Y'처리
				}
			}
			comAtmaAtchFileDao.insertMMFileRegist(parameterMap);
			
		}else{
			atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
			parameterMap.put("atchFileGroupNo", parameterMap.get("P_FILE_GRP_NO"));
			parameterMap.put("P_FILE_DOC_SECD", parameterMap.get("P_FILE_DOC_SECD"));
			comAtmaAtchFileDao.deleteAtchFileUpdt(parameterMap);	//DEL_AT ='Y'처리
		}
		
		// atchFileGroupNo는 같더라도 FILE_SN이 늘어나므로 INSERT하는데 상관없음
		
		return atchFileGroupNo;
	}

	@Override
	public FwkTransferObject cmtmMngPoolExcelUpld(final FwkParameterMap parameterMap, final HttpServletResponse response){ // throws Exception 
		
		FwkTransferObject trans = new FwkTransferObject();
		String excelResultCode = "";
		
		if("Y".equals(parameterMap.get("fileFlag"))){
			
			
			try {
				boolean Excelflag = false; 
				boolean ExcelStyleFlag = false;
				boolean displayFlag = true;
				
				excelResultCode = "Success";
				
				FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
				
				System.err.println("========== excelUploadList ==========> " + parameterMap.toString());
				parameterMap.put("P_ESTM_CMTM_REGR_ID", session.get("USR_ID"));   // 평가위원등록자
				parameterMap.put("P_ESTM_CMTM_REGR_NM", session.get("USR_NM"));   // 평가위원등록자
				parameterMap.put("P_ESTM_CMTM_REG_DEPT_NO", session.get("DEPT_NO"));   // 평가위원등록부서
				parameterMap.put("P_ESTM_CMTM_REG_DEPT_NM", session.get("DEPT_NM"));   // 평가위원등록부서
				
				Excelflag = true;
				trans.put("Excelflag", Excelflag);
				
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
				MultipartFile excelFile = multipartRequest.getFile("excelFileUpload"); 
				Workbook workbook = null; 
				Sheet sheet = null; 
				
				//스트림 설정 , 스트림으로 파일을 전송
				InputStream	iStream = null;
				iStream = excelFile.getInputStream();	// 스트림을 얻어올 수 있다. 
				workbook = WorkbookFactory.create( iStream ); //WookbookFactory를 이용하면 처음부터 xls와 xlsx를 가리지 않는다.
			
				int cnt =0;
				int columnIdx = 0; 
				
				if ( workbook != null ) {
					sheet	= workbook.getSheetAt(0);

					if ( sheet != null ) {
						int nRowStartIndex = 1; 
						int rows = sheet.getLastRowNum();	//행 갯수 (14개)
						
						System.err.println("rows (행 갯수) ==> " + rows);
						
						for ( int nRow = nRowStartIndex, count = 0; nRow <= rows ; nRow++ ,count++) {
							
							FwkParameterMap poolParameterMap = new FwkParameterMap();
							poolParameterMap.putAll(parameterMap);
							
							// 구매
							Row row = sheet.getRow(nRow);
							
							String ESTM_CMTM_NO = "";   // 평가위원번호
							String ESTM_CMTM_NM = "";   // 평가위원명
							String INO_CMTM_SECD_NM = "";   // 내/외부 구분
							String BRDT = "";   // 생년월일
							String CP_NO = "";   // 휴대폰전화번호
							String TEL_NO = "";   // 전화번호
							String EMAL ="";   // 이메일
							String FX_NO ="";   // 팩스번호
							String ZIP = "";   // 우편번호
							String ADDR_1 = "";   // 주소
							String JOB_NM = "";   // 직업
							String LT_EDUC = "";   // 최종학력
							String ESTM_CMTM_BLNG_AGNC = "";   // 소속회사명
							String BLNG_AGNC_BIZRNO = "";   // 사업자번호
							String ESTM_CMTM_BLNG_DEPT = "";   // 부서
							String ESTM_CMTM_OFPS = "";   // 직위
							String BLNG_AGNC_FX_NO = "";   // 회사 팩스번호
							String BLNG_AGNC_TEL_NO = "";   // 회사 전화번호
							String BLNG_AGNC_ZIP = "";   // 회사 우편번호
							String BLNG_AGNC_ADDR_1 = "";   // 회사 주소
							String BLNG_AGNC_HMPG_ADDR = "";   // 회사 홈페이지 주소
							
							if(row != null){
								
								System.err.println("@@@ row ==> " + row);
								 int cells = row.getPhysicalNumberOfCells(); 	// cell 갯수 가져오기
								 //System.err.println("cells = > 셀 개수 " + cells);   // 
								 
								for(columnIdx = 0; columnIdx <= cells; columnIdx++){
									Cell cell = row.getCell(columnIdx); 	// cell가져오기
									String value = "";
									
									System.err.println("cell ==> " + cell);
									
									
									if(cell == null) {
										continue;	
									}
									
									switch (cell.getCellType()){
										case Cell.CELL_TYPE_STRING :
											value = cell.getStringCellValue();
											break;
										case Cell.CELL_TYPE_NUMERIC :
											cell.setCellType( cell.CELL_TYPE_STRING );
											Long l = Math.round(cell.getNumericCellValue());
											value = String.valueOf(l);
											break;	
										case Cell.CELL_TYPE_BLANK :
											value = ""; 
											break;
										case Cell.CELL_TYPE_ERROR :
											value = cell.getErrorCellValue()+"";
											break;
										default: break;
									}
									
									
									/*switch (columnIdx) {
										case 0 :
											ESTM_CMTM_NO = value;   // 평가위원번호
											break;
										case 1 :
											ESTM_CMTM_NM = value; 	// 평가위원명
											break;
										case 2 : 
											INO_CMTM_SECD_NM = value;	// 내/외부 구분
											break;
										case 3 : 
											BRDT  = value;   // 생년월일
											break;
										case 4 : 
											CP_NO = value;   // 휴대폰전화번호
											break;
										case 5 : 
											TEL_NO = value;   // 전화번호
											break;
										case 6 : 
											EMAL = value;   // 이메일
											break;
										case 7 : 
											FX_NO = value;   // 팩스번호
											break;
										case 8 : 
											ZIP = value;   // 우편번호
											break;
										case 9 : 
											ADDR_1 = value;   // 주소
											break;
										case 10 : 
											JOB_NM = value;   // 직업
											break;
										case 11 : 
											LT_EDUC = value;   // 최종학력
											break;
										case 12 : 
											ESTM_CMTM_BLNG_AGNC = value;   // 소속회사명
											break;
										case 13 : 
											BLNG_AGNC_BIZRNO = value;   // 사업자번호
											break;
										case 14 : 
											ESTM_CMTM_BLNG_DEPT = value;   // 부서
											break;
										case 15 : 
											ESTM_CMTM_OFPS = value;   // 직위
											break;
										case 16 : 
											BLNG_AGNC_FX_NO = value;   // 회사 팩스번호
											break;
										case 17 : 
											BLNG_AGNC_TEL_NO = value;   // 회사 전화번호
											break;
										case 18 : 
											BLNG_AGNC_ZIP = value;   // 회사 우편번호
											break;
										case 19 : 
											BLNG_AGNC_ADDR_1 = value;   // 회사 주소
											break;
										case 20 : 
											BLNG_AGNC_HMPG_ADDR = value;   // 회사 홈페이지 주소
											break;
									}*/
									
									
									if (columnIdx == 0) {
										//poolParameterMap.put("P_ESTM_CMTM_NO", ESTM_CMTM_NO);
										poolParameterMap.put("P_ESTM_CMTM_NO", value);
									} else if (columnIdx == 1) {
										//poolParameterMap.put("P_ESTM_CMTM_NM", ESTM_CMTM_NM);
										poolParameterMap.put("P_ESTM_CMTM_NM", value);
									} else if (columnIdx == 2) {
										//poolParameterMap.put("P_INO_CMTM_SECD_NM", INO_CMTM_SECD_NM);
										if("내부".equals(value)){
											poolParameterMap.put("P_INO_CMTM_SECD", "INN");
										}else{
											poolParameterMap.put("P_INO_CMTM_SECD", "OUT");
										}
										poolParameterMap.put("P_INO_CMTM_SENM", value);
									} else if (columnIdx == 3) {
										//poolParameterMap.put("P_BRDT", BRDT);
										poolParameterMap.put("P_BRDT", value);
									} else if (columnIdx == 4) {
										//poolParameterMap.put("P_CP_NO", CP_NO);
										poolParameterMap.put("P_CP_NO", value);
									} else if (columnIdx == 5) {
										//poolParameterMap.put("P_TEL_NO", TEL_NO);
										poolParameterMap.put("P_TEL_NO", value);
									} else if (columnIdx == 6) {
										//poolParameterMap.put("P_EMAL", EMAL);
										poolParameterMap.put("P_EMAL", value);
									} else if (columnIdx == 7) {
										//poolParameterMap.put("P_FX_NO", FX_NO);
										poolParameterMap.put("P_FX_NO", value);
									} else if (columnIdx == 8) {
										//poolParameterMap.put("P_ZIP", ZIP);
										poolParameterMap.put("P_ZIP", value);
									} else if (columnIdx == 9) {
										//poolParameterMap.put("P_ADDR_1", ADDR_1);
										poolParameterMap.put("P_ADDR_1", value);
									} else if (columnIdx == 10) {
										//poolParameterMap.put("P_JOB_NM", JOB_NM);
										poolParameterMap.put("P_JOB_NM", value);
									} else if (columnIdx == 11) {
										//poolParameterMap.put("P_LT_EDUC", LT_EDUC);
										poolParameterMap.put("P_LT_EDUC", value);
									} else if (columnIdx == 12) {
										//poolParameterMap.put("P_ESTM_CMTM_BLNG_AGNC", ESTM_CMTM_BLNG_AGNC);
										poolParameterMap.put("P_ESTM_CMTM_BLNG_AGNC", value);
									} else if (columnIdx == 13) {
										//poolParameterMap.put("P_BLNG_AGNC_BIZRNO", BLNG_AGNC_BIZRNO);
										poolParameterMap.put("P_BLNG_AGNC_BIZRNO", value);
									} else if (columnIdx == 14) {
										//poolParameterMap.put("P_ESTM_CMTM_BLNG_DEPT", ESTM_CMTM_BLNG_DEPT);
										poolParameterMap.put("P_ESTM_CMTM_BLNG_DEPT", value);
									} else if (columnIdx == 15) {
										//poolParameterMap.put("P_ESTM_CMTM_OFPS", ESTM_CMTM_OFPS);
										poolParameterMap.put("P_ESTM_CMTM_OFPS", value);
									} else if (columnIdx == 16) {
										//poolParameterMap.put("P_BLNG_AGNC_FX_NO", BLNG_AGNC_FX_NO);
										poolParameterMap.put("P_BLNG_AGNC_FX_NO", value);
									} else if (columnIdx == 17) {
										//poolParameterMap.put("P_BLNG_AGNC_TEL_NO", BLNG_AGNC_TEL_NO);
										poolParameterMap.put("P_BLNG_AGNC_TEL_NO", value);
									} else if (columnIdx == 18) {
										//poolParameterMap.put("P_BLNG_AGNC_ZIP", BLNG_AGNC_ZIP);
										poolParameterMap.put("P_BLNG_AGNC_ZIP", value);
									} else if (columnIdx == 19) {
										//poolParameterMap.put("P_BLNG_AGNC_ADDR_1", BLNG_AGNC_ADDR_1);
										poolParameterMap.put("P_BLNG_AGNC_ADDR_1", value);
									} else if (columnIdx == 20) {
										//poolParameterMap.put("P_BLNG_AGNC_HMPG_ADDR", BLNG_AGNC_HMPG_ADDR);
										poolParameterMap.put("P_BLNG_AGNC_HMPG_ADDR", value);
									}
									
									poolParameterMap.put("P_HNDW_REG_YN", "Y");
								}
								
								// 평가위원POOL 등록
								iproEstmCmtmMngPoolDao.insertEstmCmtmPoolMst(poolParameterMap);   // T_ESTM_CMTM_POOL_MST INSERT
								
								/*try {
									
								} catch (Exception e) {
									// TODO: handle exception
									excelResultCode = "fail";
									//throw new Exception(e.getMessage());
									e.printStackTrace();
									
									TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								}*/
							}
						}	//for end 
					}  
				} 
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				excelResultCode = "fail";
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.println("<script>alert('양식을 확인해주세요.'); history.go(-1);</script>");
					out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.err.println("fail!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	            
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	            
			}
		}
		
		trans.put("excelResultCode", excelResultCode);
		
		return trans;
	}

	@Override
	public void cmtmMngPoolDelete(final FwkParameterMap parameterMap) {
		iproEstmCmtmMngPoolDao.updateCmtmMngPoolDelAt(parameterMap);   // T_ESTM_CMTM_POOL_MST UPDATE
	}
	
}
