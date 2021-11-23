package com.eunwoosoft.ipro.vend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.vend.dao.IproVendMngeDao;
import com.eunwoosoft.ipro.vend.service.IproVendMngeService;

/**
 * <pre>
 * com.eunwoosoft.ipro.vend.service.impl 
 *    |_ IproVendMngeServiceImpl.java
 * 
 * </pre>
 * @date : 2018. 2. 20.
 * @version : 1.0
 * @author : jandi_Eun
 */

@Transactional
@Service("iproVendMngeService")
public class IproVendMngeServiceImpl extends AbstractFwkService implements IproVendMngeService{
	private static final Logger LOG = LoggerFactory.getLogger(IproVendMngeServiceImpl.class);
	
	@Resource(name="iproVendMngeDao")
	private IproVendMngeDao iproVendMngeDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Override
	public FwkTransferObject vendMngeListInqireWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_REGIST_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_REGIST_BEGIN_DT_S"))){
			parameterMap.put("P_REGIST_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REGIST_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_REGIST_END_DT_S") != null && !"".equals(parameterMap.get("P_REGIST_END_DT_S"))){
			parameterMap.put("P_REGIST_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REGIST_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		if("N".equals(parameterMap.get("P_CCN_SPHE_1_S"))){
			parameterMap.put("P_CCN_SPHE_1_S", "");
		}
		if("N".equals(parameterMap.get("P_CCN_SPHE_2_S"))){
			parameterMap.put("P_CCN_SPHE_2_S", "");
		}
		if("N".equals(parameterMap.get("P_CCN_SPHE_3_S"))){
			parameterMap.put("P_CCN_SPHE_3_S", "");
		}
		if("N".equals(parameterMap.get("P_CCN_SPHE_4_S"))){
			parameterMap.put("P_CCN_SPHE_4_S", "");
		}
		if("N".equals(parameterMap.get("P_CCN_SPHE_5_S"))){
			parameterMap.put("P_CCN_SPHE_5_S", "");
		}
				
		trans.put(IproVendMngeService.VEND_MNGE_LIST, iproVendMngeDao.selectVendMngeListInqireWithPgng(parameterMap));
		trans.put(IproVendMngeService.VEND_MNGE_LIST_TOTCNT, iproVendMngeDao.selectVendMngeListTotcnt(parameterMap));
		return trans;
		
	}
	
	@Override
	public FwkTransferObject vendMngeRegist(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		try {
			
			
			FwkDataEntity dataEntity = (FwkDataEntity) iproVendMngeDao.selectEntrpsRegistNoMax(parameterMap);
			System.err.println("업체등록번호 ===> " + dataEntity.get("VEND_REG_NO"));
			parameterMap.put("P_VEND_REG_NO", dataEntity.get("VEND_REG_NO"));   // 업체등록번호
			
			String bizRno = (String) parameterMap.get("P_BIZRNO_1") + (String) parameterMap.get("P_BIZRNO_2") + (String) parameterMap.get("P_BIZRNO_3");
			parameterMap.put("P_BIZRNO", bizRno);   // 사업자등록번호
			
//			parameterMap.put("P_CPR_FOND_DE", FwkFormatUtil.formatDate((String)parameterMap.get("P_CPR_FOND_DE"), "yyyy-MM-dd", "yyyyMMdd"));
//			parameterMap.put("P_MMBR_CNT", FwkStringUtil.deleteComma(parameterMap.get("P_MMBR_CNT").toString()));   // 종업원수
			
			System.err.println("insertVendMngeRegist START ========> ");
			parameterMap.put("P_EMAL_ADDR", parameterMap.get("P_EMAL_ADDR1"));   // 
			parameterMap.put("P_TEL_NO", parameterMap.get("P_TEL_NO1"));   // 
			parameterMap.put("P_ESTB_YR", parameterMap.getString("P_ESTB_YR").substring(0,4));   // 
			iproVendMngeDao.insertVendMngeRegist(parameterMap);   // T_CU_MST INSERT
			
			System.err.println("insertVendMngeBankRegist START ========> ");
			parameterMap.put("P_ACC_SN", "1");
			iproVendMngeDao.insertVendMngeBankRegist(parameterMap);   // T_CU_ACCT INSERT
			
			Object SG_CD = parameterMap.get("P_SG_CD");
			Object MAIN_SG_YN = parameterMap.get("P_MAIN_SG_YN");
			
			
			if(SG_CD != null) {
				if(SG_CD instanceof String){
					System.err.println(" :::::::::::::::: SG코드 1개 등록 :::::::::::::::: ");
					parameterMap.put("P_SG_SN", "1");
					
					String P_GET_SG_CD = (String) parameterMap.get("P_SG_CD");
					System.err.println("P_GET_SG_CD ==> " + P_GET_SG_CD );
					
					if(P_GET_SG_CD != "" || P_GET_SG_CD != null){
					
					
						/*parameterMap.put("P_GET_SG_CD", P_GET_SG_CD);
						FwkDataEntity dataEntity2 = (FwkDataEntity) iproVendMngeDao.selectSgCodeName(parameterMap);
						parameterMap.put("P_SG_NAME", dataEntity2.get("CD_VALUE_NM"));*/
						
						
						System.err.println("insertVendMngeSgCodeRegist START ========> ");
						iproVendMngeDao.insertVendMngeSgCodeRegist(parameterMap);   // T_CO_VENDOR_SG INSERT
					}
				}else{
					System.err.println(" :::::::::::::::: SG코드 여러개 등록 :::::::::::::::: ");
					
					String[] SG_CD_LIST = (String[]) SG_CD;
					String[] MAIN_SG_YN_LIST =  (String[]) MAIN_SG_YN; 

					
					for(int i = 0; i < SG_CD_LIST.length; i++) {
						parameterMap.put("P_SG_SN", i+1);
						
						if(SG_CD_LIST != null && SG_CD_LIST[i] != null) {
							parameterMap.put("P_SG_CD", SG_CD_LIST[i]);
							
							/*String P_GET_SG_CODE = SG_CD_LIST[i];
							parameterMap.put("P_GET_SG_CODE", P_GET_SG_CODE);
							
							System.err.println("P_GET_SG_CODE ==> " + P_GET_SG_CODE);
							
							FwkDataEntity dataEntity2 = (FwkDataEntity) iproVendMngeDao.selectSgCodeName(parameterMap);
							parameterMap.put("P_SG_NAME", dataEntity2.get("CD_VALUE_NM"));*/
							
						}
						
						if(MAIN_SG_YN_LIST != null && MAIN_SG_YN_LIST[i] != null) {
							parameterMap.put("P_MAIN_SG_YN", MAIN_SG_YN_LIST[i]);
						}
						
						
						System.err.println("insertVendMngeSgCodeRegist START ========> ");
						iproVendMngeDao.insertVendMngeSgCodeRegist(parameterMap);   // T_CO_VENDOR_SG INSERT
					}
				}
			}// T_CU_SG_GRP INSERT END
				
			Object USR_NM = parameterMap.get("P_USR_NM");
			Object OPNM = parameterMap.get("P_OPNM");
			Object TEL_NO = parameterMap.get("P_TEL_NO2");
			Object CP_NO = parameterMap.get("P_CP_NO");
			Object EMAL_ADDR = parameterMap.get("P_EMAL_ADDR2");
			Object DEPT_NM = parameterMap.get("P_DEPT_NM");
			
			
			if(USR_NM != null) {
				if(USR_NM instanceof String){
					System.err.println(" :::::::::::::::: 담당자 1개 등록 :::::::::::::::: ");
					System.err.println("insertVendMngeUserRegist START ========> ");
					parameterMap.put("P_TEL_NO", parameterMap.get("P_TEL_NO2"));
					parameterMap.put("P_EMAL_ADDR", parameterMap.get("P_EMAL_ADDR2"));
					iproVendMngeDao.insertVendMngeUserRegist(parameterMap);   // T_CU_USER INSERT
				}else{
					System.err.println(" :::::::::::::::: 담당자 여러개 등록 :::::::::::::::: ");
					
					String[] USR_NM_LIST = (String[]) USR_NM;
					String[] OPNM_LIST = (String[]) OPNM;
					String[] TEL_NO_LIST = (String[]) TEL_NO;
					String[] CP_NO_LIST = (String[]) CP_NO;
					String[] EMAL_ADDR_LIST = (String[]) EMAL_ADDR;
					String[] DEPT_NM_LIST = (String[]) DEPT_NM;
					
					for(int i = 0; i < USR_NM_LIST.length; i++) {
						if(USR_NM_LIST != null && USR_NM_LIST[i] != null) {
							parameterMap.put("P_USR_NM", USR_NM_LIST[i]);
						}
						if(OPNM_LIST != null && OPNM_LIST[i] != null){
							parameterMap.put("P_OPNM", OPNM_LIST[i]);
						}
						if(TEL_NO_LIST != null && TEL_NO_LIST[i] != null){
							parameterMap.put("P_TEL_NO", TEL_NO_LIST[i]);
						}
						if(CP_NO_LIST != null && CP_NO_LIST[i] != null){
							parameterMap.put("P_CP_NO", CP_NO_LIST[i]);
						}
						if(EMAL_ADDR_LIST != null && EMAL_ADDR_LIST[i] != null){
							parameterMap.put("P_EMAL_ADDR", EMAL_ADDR_LIST[i]);
						}
						if(DEPT_NM_LIST != null && DEPT_NM_LIST[i] != null){
							parameterMap.put("P_DEPT_NM", DEPT_NM_LIST[i]);
						}
						
						System.err.println("insertVendMngeUserRegist START ========> ");
						iproVendMngeDao.insertVendMngeUserRegist(parameterMap);   // T_CU_USER INSERT
					}
				}
			}// T_CO_VENDOR_USER INSERT END
			
			
			Object ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object LLF_NM = parameterMap.get("P_LLF_NM");
			Object MLF_NM = parameterMap.get("P_MLF_NM");
			Object SLF_NM = parameterMap.get("P_SLF_NM");
			Object ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object ITEM_UPRC = parameterMap.get("P_ITEM_UPRC");
			
			if(ITEM_NO != null) {
				if(ITEM_NO instanceof String){
					System.err.println(" :::::::::::::::: 물품 1개 등록 :::::::::::::::: ");
					parameterMap.put("P_ITEM_SN", "1");
					
					System.err.println("insertVendMngeItemRegist START ========> ");
					parameterMap.put("P_ITEM_UPRC", FwkStringUtil.deleteComma(parameterMap.getString("P_ITEM_UPRC")));
					System.err.println("parameterMap ==> " + parameterMap);
					iproVendMngeDao.insertVendMngeItemRegist(parameterMap);   // T_CO_ITEM INSERT
				}else{
				
					System.err.println(" :::::::::::::::: 물품 여러개 등록 :::::::::::::::: ");
				
					String[] ITEM_NO_LIST = (String[]) ITEM_NO;
					String[] LLF_NM_LIST = (String[]) LLF_NM;
					String[] MLF_NM_LIST = (String[]) MLF_NM;
					String[] SLF_NM_LIST = (String[]) SLF_NM;
					String[] ITEM_NM_LIST = (String[]) ITEM_NM;
					String[] ITEM_UPRC_LIST = (String[]) ITEM_UPRC;
					
					for(int i = 0; i < ITEM_NO_LIST.length; i++) {					
						parameterMap.put("P_ITEM_SN", i+1);
						
						if(ITEM_NO_LIST != null && ITEM_NO_LIST[i] != null) {
							parameterMap.put("P_ITEM_NO", ITEM_NO_LIST[i]);
						}
						if(LLF_NM_LIST != null && LLF_NM_LIST[i] != null) {
							parameterMap.put("P_LLF_NM", LLF_NM_LIST[i]);
						}
						if(MLF_NM_LIST != null && MLF_NM_LIST[i] != null) {
							parameterMap.put("P_MLF_NM", MLF_NM_LIST[i]);
						}
						if(SLF_NM_LIST != null && SLF_NM_LIST[i] != null) {
							parameterMap.put("P_SLF_NM", SLF_NM_LIST[i]);
						}
						if(ITEM_NM_LIST != null && ITEM_NM_LIST[i] != null) {
							parameterMap.put("P_ITEM_NM", ITEM_NM_LIST[i]);
						}
						if(ITEM_UPRC_LIST != null && ITEM_UPRC_LIST[i] != null) {
							parameterMap.put("P_ITEM_UPRC", FwkStringUtil.deleteComma(ITEM_UPRC_LIST[i]));
						}
	
						System.err.println("insertVendMngeItemRegist START ========> ");
						iproVendMngeDao.insertVendMngeItemRegist(parameterMap);   // T_CO_ITEM INSERT
					}
						
				}
						
			}// T_CO_ITEM INSERT END
			
			Object SNCT_STCD = parameterMap.get("P_SNCT_STCD");
			Object SNCT_STDE = parameterMap.get("P_SNCT_STDE");
			Object SNCT_ENDE = parameterMap.get("P_SNCT_ENDE");
			Object SNCT_RSN_CNTN = parameterMap.get("P_SNCT_RSN_CNTN");
			Object SNCT_BSS_CNTN = parameterMap.get("P_SNCT_BSS_CNTN");
			Object RMK = parameterMap.get("P_RMK");
			
			
			if(SNCT_STCD != null) {
				if(SNCT_STCD instanceof String){
					System.err.println(" :::::::::::::::: 제재 1개 등록 :::::::::::::::: ");
					
					System.err.println("insertVendSnctRegist START ========> ");
					System.err.println("parameterMap" + parameterMap);
					parameterMap.put("P_SNCT_STDE", parameterMap.getString("P_SNCT_STDE").replaceAll("-", ""));
					parameterMap.put("P_SNCT_ENDE", parameterMap.getString("P_SNCT_ENDE").replaceAll("-", ""));
					iproVendMngeDao.insertVendSnctRegist(parameterMap);   // 
				}else{
					System.err.println(" :::::::::::::::: 제재 여러개 등록 :::::::::::::::: ");
					System.err.println("parameterMap" + parameterMap);
					
					String[] SNCT_STCD_LIST = (String[]) SNCT_STCD;
					String[] SNCT_STDE_LIST = (String[]) SNCT_STDE;
					String[] SNCT_ENDE_LIST = (String[]) SNCT_ENDE;
					String[] SNCT_RSN_CNTN_LIST = (String[]) SNCT_RSN_CNTN;
					String[] SNCT_BSS_CNTN_LIST = (String[]) SNCT_BSS_CNTN;
					String[] RMK_LIST = (String[]) RMK;
					for(int i = 0; i < SNCT_STCD_LIST.length; i++) {
						
						if(SNCT_STCD_LIST != null && SNCT_STCD_LIST[i] != null) {
							parameterMap.put("P_SNCT_STCD", SNCT_STCD_LIST[i]);
						}
						if(SNCT_STDE_LIST != null && SNCT_STDE_LIST[i] != null) {
							parameterMap.put("P_SNCT_STDE", SNCT_STDE_LIST[i].replaceAll("-", ""));
						}
						if(SNCT_ENDE_LIST != null && SNCT_ENDE_LIST[i] != null) {
							parameterMap.put("P_SNCT_ENDE", SNCT_ENDE_LIST[i].replaceAll("-", ""));
						}
						if(SNCT_RSN_CNTN_LIST != null && SNCT_RSN_CNTN_LIST[i] != null) {
							parameterMap.put("P_SNCT_RSN_CNTN", SNCT_RSN_CNTN_LIST[i]);
						}
						if(SNCT_BSS_CNTN_LIST != null && SNCT_BSS_CNTN_LIST[i] != null) {
							parameterMap.put("P_SNCT_BSS_CNTN", SNCT_BSS_CNTN_LIST[i]);
						}
						if(RMK_LIST != null && RMK_LIST[i] != null) {
							parameterMap.put("P_RMK", RMK_LIST[i]);
						}
						
						System.err.println("insertVendSnctRegist START ========> ");
						iproVendMngeDao.insertVendSnctRegist(parameterMap);   // 
						
					}
				}
			}
			
			Object CTT_NM = parameterMap.get("P_CTT_NM");
			Object ACQS_DE = parameterMap.get("P_ACQS_DE");
			Object ACQS_AGNM = parameterMap.get("P_ACQS_AGNM");
			
			if(CTT_NM != null) {
				if(CTT_NM instanceof String){
					System.err.println(" :::::::::::::::: 인증 1개 등록 :::::::::::::::: ");
					parameterMap.put("P_CTT_ACQS_SN", "1");
					
					System.err.println("insertVendMngeCttAcqsRegist START ========> ");
					System.err.println("parameterMap" + parameterMap);
					iproVendMngeDao.insertVendMngeCttAcqsRegist(parameterMap);   // T_CO_CTT_ACQS INSERT
				}else{
					System.err.println(" :::::::::::::::: 인증 여러개 등록 :::::::::::::::: ");
					System.err.println("parameterMap" + parameterMap);
					
					String[] CTT_NM_LIST = (String[]) CTT_NM;
					String[] ACQS_DE_LIST = (String[]) ACQS_DE;
					String[] ACQS_AGNM_LIST = (String[]) ACQS_AGNM;
					
//					System.err.println("CTT_NM_LIST ==> " + CTT_NM_LIST);
//					System.err.println("CTT_NM_LIST.length ==> " + CTT_NM_LIST.length);
					
					for(int i = 0; i < CTT_NM_LIST.length; i++) {
						parameterMap.put("P_CTT_ACQS_SN", i+1);
						
						if(CTT_NM_LIST != null && CTT_NM_LIST[i] != null) {
							parameterMap.put("P_CTT_NM", CTT_NM_LIST[i]);
						}
						if(ACQS_DE_LIST != null && ACQS_DE_LIST[i] != null) {
							parameterMap.put("P_ACQS_DE", ACQS_DE_LIST[i]);
						}
						if(ACQS_AGNM_LIST != null && ACQS_AGNM_LIST[i] != null) {
							parameterMap.put("P_ACQS_AGNM", ACQS_AGNM_LIST[i]);
						}
						
						System.err.println("insertVendMngeCttAcqsRegist START ========> ");
						iproVendMngeDao.insertVendMngeCttAcqsRegist(parameterMap);   // T_CO_CTT_ACQS INSERT
						
					}
				}
				
			}// T_CO_CTT_ACQS INSERT END
			
			// 첨부파일 등록 START
			String fileGrpNoA = parameterMap.getString("fileGrpNoA");
			String fileGrpNoB = parameterMap.getString("fileGrpNoB");
			String fileGrpNoC = parameterMap.getString("fileGrpNoC");
			String fileGrpNoD = parameterMap.getString("fileGrpNoD");
			String fileGrpNoE = parameterMap.getString("fileGrpNoE");
			String fileGrpNoF = parameterMap.getString("fileGrpNoF");
			String fileGrpNoG = parameterMap.getString("fileGrpNoG");
			
			
			// 첨부파일 : 사업자등록증
			List<Map<String, Object>> fileList1 = (List<Map<String, Object>>) parameterMap.get("fileList1");
			// 첨부파일 : 신용평가등급자료
			List<Map<String, Object>> fileList2 = (List<Map<String, Object>>) parameterMap.get("fileList2");
			// 첨부파일 : 최근년도 결산 재무재표
			List<Map<String, Object>> fileList3 = (List<Map<String, Object>>) parameterMap.get("fileList3");
			// 첨부파일 : 회사소개자료 카타로그
			List<Map<String, Object>> fileList4 = (List<Map<String, Object>>) parameterMap.get("fileList4");
			// 첨부파일 : 인증서 등 자료 
			List<Map<String, Object>> fileList5 = (List<Map<String, Object>>) parameterMap.get("fileList5");
			// 첨부파일 : 면허수첩, 면허증사본
			List<Map<String, Object>> fileList6 = (List<Map<String, Object>>) parameterMap.get("fileList6");
			// 첨부파일 : 기타자료
			List<Map<String, Object>> fileList7 = (List<Map<String, Object>>) parameterMap.get("fileList7");
			

			parameterMap.put("fileGrpNoA", fileGrpNoA);
			parameterMap.put("P_FILE_GRP_NO_A", fileGrpNoA);
			
			parameterMap.put("fileGrpNoB", fileGrpNoB); 
			parameterMap.put("P_FILE_GRP_NO_B", fileGrpNoB);
			
			parameterMap.put("fileGrpNoC", fileGrpNoC); 
			parameterMap.put("P_FILE_GRP_NO_C", fileGrpNoC);
			
			parameterMap.put("fileGrpNoD", fileGrpNoD); 
			parameterMap.put("P_FILE_GRP_NO_D", fileGrpNoD);
			
			parameterMap.put("fileGrpNoE", fileGrpNoE); 
			parameterMap.put("P_FILE_GRP_NO_E", fileGrpNoE);
			
			parameterMap.put("fileGrpNoF", fileGrpNoF); 
			parameterMap.put("P_FILE_GRP_NO_F", fileGrpNoF);
			
			parameterMap.put("fileGrpNoG", fileGrpNoG); 
			parameterMap.put("P_FILE_GRP_NO_G", fileGrpNoG);
			
			if(fileList1 != null){
				if(fileList1.size() > 0){
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_A"))){
						parameterMap.put("P_VEND_DOC_FSCD", "A");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoA);
						
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoA, fileList1 , parameterMap);
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoA, fileList1 , parameterMap);
					}
				}
			}
			
			if(fileList2 != null){ 
				if(fileList2.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_B"))){
						parameterMap.put("P_VEND_DOC_FSCD", "B");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoB); 
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoB, fileList2 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoB, fileList2 , parameterMap);
					}
				}    
			}
			
			if(fileList3 != null){ 
				if(fileList3.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_C"))){
						parameterMap.put("P_VEND_DOC_FSCD", "C");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoC); 
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoC, fileList3 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoC, fileList3 , parameterMap);
					}
				}    
			}
			
			if(fileList4 != null){ 
				if(fileList4.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_D"))){
						parameterMap.put("P_VEND_DOC_FSCD", "D");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoD); 
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoD, fileList4 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoD, fileList4 , parameterMap);
					}
				}    
			}
			
			if(fileList5 != null){ 
				if(fileList5.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_E"))){
						parameterMap.put("P_VEND_DOC_FSCD", "E");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoE); 
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoE, fileList5 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoE, fileList5 , parameterMap);
					}
				}    
			}
			
			if(fileList6 != null){ 
				if(fileList6.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_F"))){
						parameterMap.put("P_VEND_DOC_FSCD", "F");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoF); 
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoF, fileList6 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoF, fileList6 , parameterMap);
					}
				}    
			}
			
			if(fileList7 != null){ 
				if(fileList7.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_G"))){
						parameterMap.put("P_VEND_DOC_FSCD", "G");
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoG); 
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoG, fileList7 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoG, fileList7 , parameterMap);
					}
				}    
			}
			// 첨부파일 등록 END
			
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== IproVendMngeServiceImpl.vendMngeRegist Error == ");
		}
		
		return trans;
	}

	@Override
	public FwkTransferObject vendMngeDetailInqire(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity vendMngeDetail =  iproVendMngeDao.selectVendMngeDetail(parameterMap);   // T_CU_MST , T_CU_ACCT SELECT
		trans.put(IproVendMngeService.VEND_MNGE_DETAIL, vendMngeDetail);
		
		List<FwkDataEntity> vendMngeSgCodeList =  iproVendMngeDao.selectVendMngeSgCodeList(parameterMap);   // T_CO_VENDOR_SG SELECT
		
		List<FwkDataEntity> vendMngeUserList =  iproVendMngeDao.selectVendMngeUserList(parameterMap);   // T_CU_USER SELECT
		List<FwkDataEntity> vendMngeItemList =  iproVendMngeDao.selectVendMngeItemList(parameterMap);   // T_CO_ITEM SELECT
		
		List<FwkDataEntity> vendMngeCttAcqsList =  iproVendMngeDao.selectVendMngeCttAcqsList(parameterMap);   // T_CO_CTT_ACQS SELECT
		
		List<FwkDataEntity> vendSnctList =  iproVendMngeDao.selectVendSnctList(parameterMap);   // 
		
		// 첨부파일 : 사업자등록증
		parameterMap.put("P_VEND_DOC_FSCD", "A");
		FwkDataEntity vendMngeFileListA =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // T_CO_VENDOR_FILE SELECT
		if(vendMngeFileListA != null){
			String atchmnflGroupNo = vendMngeFileListA.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo);
			parameterMap.put("P_FILE_GRP_NO_A", atchmnflGroupNo);
			trans.put("vendMngeFileListA", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 첨부파일 : 신용평가등급자료
		parameterMap.put("P_VEND_DOC_FSCD", "B");
		FwkDataEntity vendMngeFileListB =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // 첨부파일(시방서) 조회
		if(vendMngeFileListB != null){
			String atchmnflGroupNo = vendMngeFileListB.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo); 
			parameterMap.put("P_FILE_GRP_NO_B", atchmnflGroupNo);
			System.err.println("=== 신용평가등급자료 첨부파일 START ===>" + parameterMap.get("P_FILE_GRP_NO_B"));
			trans.put("vendMngeFileListB", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 첨부파일 : 최근년도 결산 재무재표
		parameterMap.put("P_VEND_DOC_FSCD", "C");
		FwkDataEntity vendMngeFileListC =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // 첨부파일(시방서) 조회
		if(vendMngeFileListC != null){
			String atchmnflGroupNo = vendMngeFileListC.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo);
			parameterMap.put("P_FILE_GRP_NO_C", atchmnflGroupNo);
			trans.put("vendMngeFileListC", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 첨부파일 : 회사소개자료 카타로그
		parameterMap.put("P_VEND_DOC_FSCD", "D");
		FwkDataEntity vendMngeFileListD =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // 첨부파일(시방서) 조회
		if(vendMngeFileListD != null){
			String atchmnflGroupNo = vendMngeFileListD.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo);
			parameterMap.put("P_FILE_GRP_NO_D", atchmnflGroupNo);
			trans.put("vendMngeFileListD", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 첨부파일 : 인증서 등 자료
		parameterMap.put("P_VEND_DOC_FSCD", "E");
		FwkDataEntity vendMngeFileListE =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // 첨부파일(시방서) 조회
		if(vendMngeFileListE != null){
			String atchmnflGroupNo = vendMngeFileListE.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo);
			parameterMap.put("P_FILE_GRP_NO_E", atchmnflGroupNo);
			trans.put("vendMngeFileListE", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 첨부파일 : 면허수첩, 면허증사본
		parameterMap.put("P_VEND_DOC_FSCD", "F");
		FwkDataEntity vendMngeFileListF =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // 첨부파일(시방서) 조회
		if(vendMngeFileListF != null){
			String atchmnflGroupNo = vendMngeFileListF.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo);
			parameterMap.put("P_FILE_GRP_NO_F", atchmnflGroupNo);
			trans.put("vendMngeFileListF", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 첨부파일 : 기타자료
		parameterMap.put("P_VEND_DOC_FSCD", "G");
		FwkDataEntity vendMngeFileListG =  iproVendMngeDao.selectVendMngeFile(parameterMap);   // 첨부파일(시방서) 조회
		if(vendMngeFileListG != null){
			String atchmnflGroupNo = vendMngeFileListG.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo);
			parameterMap.put("P_FILE_GRP_NO_G", atchmnflGroupNo);
			trans.put("vendMngeFileListG", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		trans.put("vendMngeSgCodeList", vendMngeSgCodeList);
		trans.put("vendMngeUserList", vendMngeUserList);
		trans.put("vendMngeItemList", vendMngeItemList);
		trans.put("vendMngeCttAcqsList", vendMngeCttAcqsList);
		trans.put("vendSnctList", vendSnctList);
		
		// 계약실적
		List<FwkDataEntity> vendMngeCntrctList =  iproVendMngeDao.selectVendMngeCntrctList(parameterMap);
		trans.put("vendMngeCntrctList", vendMngeCntrctList);
		
		// 입찰정보
		List<FwkDataEntity> vendMngeEbidList =  iproVendMngeDao.selectVendMngeEbidList(parameterMap);
		trans.put("vendMngeEbidList", vendMngeEbidList);
		
		return trans;
		
	}

	@Override
	public FwkTransferObject vendMngeUpdt(FwkParameterMap parameterMap) throws Exception {
		System.err.println("================================ vendMngeUpdt START ================================");
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity loginInfo = (FwkDataEntity) parameterMap.get("loginResult");
		
		try {
			
			
			parameterMap.put("P_CPR_FOND_DE", FwkFormatUtil.formatDate((String)parameterMap.get("P_CPR_FOND_DE"), "yyyy-MM-dd", "yyyyMMdd"));
			
			parameterMap.put("P_UPDUSR_ID", loginInfo.get("USER_ID"));
			parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 수정일시
			parameterMap.put("P_DELETE_AT", "N"); // 삭제여부
			parameterMap.put("P_CRTFC_AT", "N"); // 인증여부
			
			System.err.println("updateVendMngeUpdt START ========> ");
			parameterMap.put("P_EMAL_ADDR", parameterMap.get("P_EMAL_ADDR1"));   // 
			parameterMap.put("P_TEL_NO", parameterMap.get("P_TEL_NO1"));   // 
			parameterMap.put("P_ESTB_YR", parameterMap.getString("P_ESTB_YR").substring(0,4));   // 
			iproVendMngeDao.updateVendMngeUpdt(parameterMap);   // T_CU_MST UPDATE
			
			System.err.println("deleteVendMngeBank START ========> ");
			iproVendMngeDao.deleteVendMngeBank(parameterMap);   // T_CU_ACCT DELETE
			
			System.err.println("insertVendMngeBankRegist START ========> ");
			parameterMap.put("P_ACC_SN", "1");
			iproVendMngeDao.insertVendMngeBankRegist(parameterMap);   // T_CU_ACCT INSERT
			
			iproVendMngeDao.deleteVendMngeSgCode(parameterMap);   // T_CO_VENDOR_SG DELETE
			
			Object SG_CD = parameterMap.get("P_SG_CD");
			Object MAIN_SG_YN = parameterMap.get("P_MAIN_SG_YN");
			
			
			if(SG_CD != null) {
				if(SG_CD instanceof String){
					System.err.println(" :::::::::::::::: SG코드 1개 등록 :::::::::::::::: ");
					parameterMap.put("P_SG_SN", "1");
					
					String P_GET_SG_CD = (String) parameterMap.get("P_SG_CD");
					System.err.println("P_GET_SG_CD ==> " + P_GET_SG_CD );
					
					if(P_GET_SG_CD != "" || P_GET_SG_CD != null){
					
					
						/*parameterMap.put("P_GET_SG_CD", P_GET_SG_CD);
						FwkDataEntity dataEntity2 = (FwkDataEntity) iproVendMngeDao.selectSgCodeName(parameterMap);
						parameterMap.put("P_SG_NAME", dataEntity2.get("CD_VALUE_NM"));*/
						
						
						System.err.println("insertVendMngeSgCodeRegist START ========> ");
						iproVendMngeDao.insertVendMngeSgCodeRegist(parameterMap);   // T_CO_VENDOR_SG INSERT
					}
				}else{
					System.err.println(" :::::::::::::::: SG코드 여러개 등록 :::::::::::::::: ");
					
					String[] SG_CD_LIST = (String[]) SG_CD;
					String[] MAIN_SG_YN_LIST =  (String[]) MAIN_SG_YN; 

					
					for(int i = 0; i < SG_CD_LIST.length; i++) {
						parameterMap.put("P_SG_SN", i+1);
						
						if(SG_CD_LIST != null && SG_CD_LIST[i] != null) {
							parameterMap.put("P_SG_CD", SG_CD_LIST[i]);
							
							/*String P_GET_SG_CODE = SG_CD_LIST[i];
							parameterMap.put("P_GET_SG_CODE", P_GET_SG_CODE);
							
							System.err.println("P_GET_SG_CODE ==> " + P_GET_SG_CODE);
							
							FwkDataEntity dataEntity2 = (FwkDataEntity) iproVendMngeDao.selectSgCodeName(parameterMap);
							parameterMap.put("P_SG_NAME", dataEntity2.get("CD_VALUE_NM"));*/
							
						}
						
						if(MAIN_SG_YN_LIST != null && MAIN_SG_YN_LIST[i] != null) {
							parameterMap.put("P_MAIN_SG_YN", MAIN_SG_YN_LIST[i]);
						}
						
						
						System.err.println("insertVendMngeSgCodeRegist START ========> ");
						iproVendMngeDao.insertVendMngeSgCodeRegist(parameterMap);   // T_CO_VENDOR_SG INSERT
					}
				}
			}// T_CU_SG_GRP INSERT END
		
			
			System.err.println("deleteVendMngeUser START ========> ");
			iproVendMngeDao.deleteVendMngeUser(parameterMap);   // T_CU_USER DELETE
			
			Object USR_NM = parameterMap.get("P_USR_NM");
			Object OPNM = parameterMap.get("P_OPNM");
			Object TEL_NO = parameterMap.get("P_TEL_NO2");
			Object CP_NO = parameterMap.get("P_CP_NO");
			Object EMAL_ADDR = parameterMap.get("P_EMAL_ADDR2");
			Object DEPT_NM = parameterMap.get("P_DEPT_NM");
			
				
			
			if(USR_NM != null) {
				if(USR_NM instanceof String){
					System.err.println(" :::::::::::::::: 담당자 1개 등록 :::::::::::::::: ");
					System.err.println("insertVendMngeUserRegist START ========> ");
					parameterMap.put("P_TEL_NO", parameterMap.get("P_TEL_NO2"));
					parameterMap.put("P_EMAL_ADDR", parameterMap.get("P_EMAL_ADDR2"));
					iproVendMngeDao.insertVendMngeUserRegist(parameterMap);   // T_CU_USER INSERT
				}else{
					System.err.println(" :::::::::::::::: 담당자 여러개 등록 :::::::::::::::: ");
					
					String[] USR_NM_LIST = (String[]) USR_NM;
					String[] OPNM_LIST = (String[]) OPNM;
					String[] TEL_NO_LIST = (String[]) TEL_NO;
					String[] CP_NO_LIST = (String[]) CP_NO;
					String[] EMAL_ADDR_LIST = (String[]) EMAL_ADDR;
					String[] DEPT_NM_LIST = (String[]) DEPT_NM;
					
					for(int i = 0; i < USR_NM_LIST.length; i++) {
						if(USR_NM_LIST != null && USR_NM_LIST[i] != null) {
							parameterMap.put("P_USR_NM", USR_NM_LIST[i]);
						}
						if(OPNM_LIST != null && OPNM_LIST[i] != null){
							parameterMap.put("P_OPNM", OPNM_LIST[i]);
						}
						if(TEL_NO_LIST != null && TEL_NO_LIST[i] != null){
							parameterMap.put("P_TEL_NO", TEL_NO_LIST[i]);
						}
						if(CP_NO_LIST != null && CP_NO_LIST[i] != null){
							parameterMap.put("P_CP_NO", CP_NO_LIST[i]);
						}
						if(EMAL_ADDR_LIST != null && EMAL_ADDR_LIST[i] != null){
							parameterMap.put("P_EMAL_ADDR", EMAL_ADDR_LIST[i]);
						}
						if(DEPT_NM_LIST != null && DEPT_NM_LIST[i] != null){
							parameterMap.put("P_DEPT_NM", DEPT_NM_LIST[i]);
						}
						
						System.err.println("insertVendMngeUserRegist START ========> ");
						iproVendMngeDao.insertVendMngeUserRegist(parameterMap);   // T_CU_USER INSERT
					}
				}
			}// T_CU_USER INSERT END
			
			
			System.err.println("deleteVendMngeItem START ========> ");
			iproVendMngeDao.deleteVendMngeItem(parameterMap);   // T_CO_ITEM DELETE
			
			Object ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object LLF_NM = parameterMap.get("P_LLF_NM");
			Object MLF_NM = parameterMap.get("P_MLF_NM");
			Object SLF_NM = parameterMap.get("P_SLF_NM");
			Object ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object ITEM_UPRC = parameterMap.get("P_ITEM_UPRC");
			
			if(ITEM_NO != null) {
				if(ITEM_NO instanceof String){
					System.err.println(" :::::::::::::::: 물품 1개 등록 :::::::::::::::: ");
					parameterMap.put("P_ITEM_SN", "1");
					
					System.err.println("insertVendMngeItemRegist START ========> ");
					System.err.println("parameterMap ==> " + parameterMap);
					parameterMap.put("P_ITEM_UPRC", FwkStringUtil.deleteComma(parameterMap.getString("P_ITEM_UPRC")));
					
					iproVendMngeDao.insertVendMngeItemRegist(parameterMap);   // T_CO_ITEM INSERT
				}else{
				
					System.err.println(" :::::::::::::::: 물품 여러개 등록 :::::::::::::::: ");
				
					String[] ITEM_NO_LIST = (String[]) ITEM_NO;
					String[] LLF_NM_LIST = (String[]) LLF_NM;
					String[] MLF_NM_LIST = (String[]) MLF_NM;
					String[] SLF_NM_LIST = (String[]) SLF_NM;
					String[] ITEM_NM_LIST = (String[]) ITEM_NM;
					String[] ITEM_UPRC_LIST = (String[]) ITEM_UPRC;
					
					for(int i = 0; i < ITEM_NO_LIST.length; i++) {					
						parameterMap.put("P_ITEM_SN", i+1);
						
						if(ITEM_NO_LIST != null && ITEM_NO_LIST[i] != null) {
							parameterMap.put("P_ITEM_NO", ITEM_NO_LIST[i]);
						}
						if(LLF_NM_LIST != null && LLF_NM_LIST[i] != null) {
							parameterMap.put("P_LLF_NM", LLF_NM_LIST[i]);
						}
						if(MLF_NM_LIST != null && MLF_NM_LIST[i] != null) {
							parameterMap.put("P_MLF_NM", MLF_NM_LIST[i]);
						}
						if(SLF_NM_LIST != null && SLF_NM_LIST[i] != null) {
							parameterMap.put("P_SLF_NM", SLF_NM_LIST[i]);
						}
						if(ITEM_NM_LIST != null && ITEM_NM_LIST[i] != null) {
							parameterMap.put("P_ITEM_NM", ITEM_NM_LIST[i]);
						}
						if(ITEM_UPRC_LIST != null && ITEM_UPRC_LIST[i] != null) {
							parameterMap.put("P_ITEM_UPRC", FwkStringUtil.deleteComma(ITEM_UPRC_LIST[i]));
						}
	
						System.err.println("insertVendMngeItemRegist START ========> ");
						iproVendMngeDao.insertVendMngeItemRegist(parameterMap);   // T_CO_ITEM INSERT
					}
						
				}
						
			}// T_CO_ITEM INSERT END
			
			System.err.println("deleteVendSnct START ========> ");
			iproVendMngeDao.deleteVendSnct(parameterMap);
			
			Object SNCT_STCD = parameterMap.get("P_SNCT_STCD");
			Object SNCT_STDE = parameterMap.get("P_SNCT_STDE");
			Object SNCT_ENDE = parameterMap.get("P_SNCT_ENDE");
			Object SNCT_RSN_CNTN = parameterMap.get("P_SNCT_RSN_CNTN");
			Object SNCT_BSS_CNTN = parameterMap.get("P_SNCT_BSS_CNTN");
			Object RMK = parameterMap.get("P_RMK");
			
			
			if(SNCT_STCD != null) {
				if(SNCT_STCD instanceof String){
					System.err.println(" :::::::::::::::: 제재 1개 등록 :::::::::::::::: ");
					
					System.err.println("insertVendSnctRegist START ========> ");
					System.err.println("parameterMap" + parameterMap);
					parameterMap.put("P_SNCT_STDE", parameterMap.getString("P_SNCT_STDE").replaceAll("-", ""));
					parameterMap.put("P_SNCT_ENDE", parameterMap.getString("P_SNCT_ENDE").replaceAll("-", ""));
					iproVendMngeDao.insertVendSnctRegist(parameterMap);   // 
				}else{
					System.err.println(" :::::::::::::::: 제재 여러개 등록 :::::::::::::::: ");
					System.err.println("parameterMap" + parameterMap);
					
					String[] SNCT_STCD_LIST = (String[]) SNCT_STCD;
					String[] SNCT_STDE_LIST = (String[]) SNCT_STDE;
					String[] SNCT_ENDE_LIST = (String[]) SNCT_ENDE;
					String[] SNCT_RSN_CNTN_LIST = (String[]) SNCT_RSN_CNTN;
					String[] SNCT_BSS_CNTN_LIST = (String[]) SNCT_BSS_CNTN;
					String[] RMK_LIST = (String[]) RMK;
					for(int i = 0; i < SNCT_STCD_LIST.length; i++) {
						
						if(SNCT_STCD_LIST != null && SNCT_STCD_LIST[i] != null) {
							parameterMap.put("P_SNCT_STCD", SNCT_STCD_LIST[i]);
						}
						if(SNCT_STDE_LIST != null && SNCT_STDE_LIST[i] != null) {
							parameterMap.put("P_SNCT_STDE", SNCT_STDE_LIST[i].replaceAll("-", ""));
						}
						if(SNCT_ENDE_LIST != null && SNCT_ENDE_LIST[i] != null) {
							parameterMap.put("P_SNCT_ENDE", SNCT_ENDE_LIST[i].replaceAll("-", ""));
						}
						if(SNCT_RSN_CNTN_LIST != null && SNCT_RSN_CNTN_LIST[i] != null) {
							parameterMap.put("P_SNCT_RSN_CNTN", SNCT_RSN_CNTN_LIST[i]);
						}
						if(SNCT_BSS_CNTN_LIST != null && SNCT_BSS_CNTN_LIST[i] != null) {
							parameterMap.put("P_SNCT_BSS_CNTN", SNCT_BSS_CNTN_LIST[i]);
						}
						if(RMK_LIST != null && RMK_LIST[i] != null) {
							parameterMap.put("P_RMK", RMK_LIST[i]);
						}
						
						System.err.println("insertVendSnctRegist START ========> ");
						iproVendMngeDao.insertVendSnctRegist(parameterMap);   // 
						
					}
				}
			}
			System.err.println("deleteVendMngeCttAcqs START ========> ");
			iproVendMngeDao.deleteVendMngeCttAcqs(parameterMap);   // T_CO_CTT_ACQS DELETE
			
			Object CTT_NM = parameterMap.get("P_CTT_NM");
			Object ACQS_DE = parameterMap.get("P_ACQS_DE");
			Object ACQS_AGNM = parameterMap.get("P_ACQS_AGNM");
			
			if(CTT_NM != null) {
				if(CTT_NM instanceof String){
					System.err.println(" :::::::::::::::: 인증 1개 등록 :::::::::::::::: ");
					parameterMap.put("P_CTT_ACQS_SN", "1");
					
					System.err.println("insertVendMngeCttAcqsRegist START ========> ");
					System.err.println("parameterMap" + parameterMap);
					iproVendMngeDao.insertVendMngeCttAcqsRegist(parameterMap);   // T_CO_CTT_ACQS INSERT
				}else{
					System.err.println(" :::::::::::::::: 인증 여러개 등록 :::::::::::::::: ");
					System.err.println("parameterMap" + parameterMap);
					
					String[] CTT_NM_LIST = (String[]) CTT_NM;
					String[] ACQS_DE_LIST = (String[]) ACQS_DE;
					String[] ACQS_AGNM_LIST= (String[]) ACQS_AGNM;
					
//					System.err.println("CTT_NM_LIST ==> " + CTT_NM_LIST);
//					System.err.println("CTT_NM_LIST.length ==> " + CTT_NM_LIST.length);
					
					for(int i = 0; i < CTT_NM_LIST.length; i++) {
						parameterMap.put("P_CTT_ACQS_SN", i+1);
						
						if(CTT_NM_LIST != null && CTT_NM_LIST[i] != null) {
							parameterMap.put("P_CTT_NM", CTT_NM_LIST[i]);
						}
						if(ACQS_DE_LIST != null && ACQS_DE_LIST[i] != null) {
							parameterMap.put("P_ACQS_DE", ACQS_DE_LIST[i]);
						}
						if(ACQS_AGNM_LIST != null && ACQS_AGNM_LIST[i] != null) {
							parameterMap.put("P_ACQS_AGNM", ACQS_AGNM_LIST[i]);
						}
						
						System.err.println("insertVendMngeCttAcqsRegist START ========> ");
						iproVendMngeDao.insertVendMngeCttAcqsRegist(parameterMap);   // T_CO_CTT_ACQS INSERT
						
					}
				}
				
			}// T_CO_CTT_ACQS INSERT END
			
			// 첨부파일 등록 START
			String fileGrpNoA = parameterMap.getString("fileGrpNoA");
			String fileGrpNoB = parameterMap.getString("fileGrpNoB");
			String fileGrpNoC = parameterMap.getString("fileGrpNoC");
			String fileGrpNoD = parameterMap.getString("fileGrpNoD");
			String fileGrpNoE = parameterMap.getString("fileGrpNoE");
			String fileGrpNoF = parameterMap.getString("fileGrpNoF");
			String fileGrpNoG = parameterMap.getString("fileGrpNoG");
			
			// 첨부파일 : 사업자등록증
			List<Map<String, Object>> fileList1 = (List<Map<String, Object>>) parameterMap.get("fileList1");
			// 첨부파일 : 신용평가등급자료
			List<Map<String, Object>> fileList2 = (List<Map<String, Object>>) parameterMap.get("fileList2");
			// 첨부파일 : 최근년도 결산 재무재표
			List<Map<String, Object>> fileList3 = (List<Map<String, Object>>) parameterMap.get("fileList3");
			// 첨부파일 : 회사소개자료 카타로그
			List<Map<String, Object>> fileList4 = (List<Map<String, Object>>) parameterMap.get("fileList4");
			// 첨부파일 : 인증서 등 자료 
			List<Map<String, Object>> fileList5 = (List<Map<String, Object>>) parameterMap.get("fileList5");
			// 첨부파일 : 면허수첩, 면허증사본
			List<Map<String, Object>> fileList6 = (List<Map<String, Object>>) parameterMap.get("fileList6");
			// 첨부파일 : 기타자료
			List<Map<String, Object>> fileList7 = (List<Map<String, Object>>) parameterMap.get("fileList7");
			
			
			parameterMap.put("fileGrpNoA", fileGrpNoA);
			parameterMap.put("P_FILE_GRP_NO_A", fileGrpNoA);
			
			parameterMap.put("fileGrpNoB", fileGrpNoB); 
			parameterMap.put("P_FILE_GRP_NO_B", fileGrpNoB);
			
			parameterMap.put("fileGrpNoC", fileGrpNoC); 
			parameterMap.put("P_FILE_GRP_NO_C", fileGrpNoC);
			
			parameterMap.put("fileGrpNoD", fileGrpNoD); 
			parameterMap.put("P_FILE_GRP_NO_D", fileGrpNoD);
			
			parameterMap.put("fileGrpNoE", fileGrpNoE); 
			parameterMap.put("P_FILE_GRP_NO_E", fileGrpNoE);
			
			parameterMap.put("fileGrpNoF", fileGrpNoF); 
			parameterMap.put("P_FILE_GRP_NO_F", fileGrpNoF);
			
			parameterMap.put("fileGrpNoG", fileGrpNoG); 
			parameterMap.put("P_FILE_GRP_NO_G", fileGrpNoG);
			
			
			if(fileList1 != null){
				if(fileList1.size() > 0){
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_A"))){
						parameterMap.put("P_VEND_DOC_FSCD", "A");
						parameterMap.put("P_ORD_SN", 1);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoA);
						
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoA, fileList1 , parameterMap);
					}else{
						System.err.println("P_ATCH_NEW_A ! Y  " + parameterMap.get("P_ATCH_NEW_A"));
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoA, fileList1 , parameterMap);
					}
				}
			}
			
			if(fileList2 != null){ 
				if(fileList2.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_B"))){
						parameterMap.put("P_VEND_DOC_FSCD", "B");
						parameterMap.put("P_ORD_SN", 2);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoB); 
						
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CU_FILE_MST INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoB, fileList2 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoB, fileList2 , parameterMap);
					}
				}    
			}
			
			if(fileList3 != null){ 
				if(fileList3.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_C"))){
						parameterMap.put("P_VEND_DOC_FSCD", "C");
						parameterMap.put("P_ORD_SN", 3);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoC);
						
						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoC, fileList3 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoC, fileList3 , parameterMap);
					}
				}    
			}
			
			if(fileList4 != null){ 
				if(fileList4.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_D"))){
						parameterMap.put("P_VEND_DOC_FSCD", "D");
						parameterMap.put("P_ORD_SN", 4);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoD); 

						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoD, fileList4 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoD, fileList4 , parameterMap);
					}
				}    
			}
			
			if(fileList5 != null){ 
				if(fileList5.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_E"))){
						parameterMap.put("P_VEND_DOC_FSCD", "E");
						parameterMap.put("P_ORD_SN", 5);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoE); 

						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoE, fileList5 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoE, fileList5 , parameterMap);
					}
				}    
			}
			
			if(fileList6 != null){ 
				if(fileList6.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_F"))){
						parameterMap.put("P_VEND_DOC_FSCD", "F");
						parameterMap.put("P_ORD_SN", 6);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoF); 

						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoF, fileList6 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoF, fileList6 , parameterMap);
					}
				}    
			}
			
			if(fileList7 != null){ 
				if(fileList7.size() > 0){ 
					if("Y".equals(parameterMap.getString("P_ATCH_NEW_G"))){
						parameterMap.put("P_VEND_DOC_FSCD", "G");
						parameterMap.put("P_ORD_SN", 7);
						parameterMap.put("P_FILE_GRP_NO", fileGrpNoG);

						iproVendMngeDao.insertVendMngeFileRegist(parameterMap);   // T_CO_VENDOR_FILE INSERT
						comAtmaAtchFileService.atchFileMapRegist(fileGrpNoG, fileList7 , parameterMap);	//	TMDM_ATFIGR	, TMDM_ATFI
					}else{
						comAtmaAtchFileService.atchFileAddRegist(fileGrpNoG, fileList7 , parameterMap);
					}
				}    
			}
			// 첨부파일 등록 END
			
			// 삭제한 파일 DELETE_AT = 'N' 처리
			String deleteFileSn = parameterMap.getString("P_DELETE_FILE_SN");
			if(!"".equals(deleteFileSn)){
				String[] arrDeleteFileSn = deleteFileSn.split(",");
				List<Map<String, Object>> deleteFileSnList = new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < arrDeleteFileSn.length; i++) {
					FwkParameterMap pmap = new FwkParameterMap();
					pmap.put("P_FILE_SN", arrDeleteFileSn[i]);
					deleteFileSnList.add(pmap);
				} 
				comAtmaAtchFileService.atchFileDelete(deleteFileSnList);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("== IproVendMngeServiceImpl.vendMngeUpdt Error == ");
		}
		
		
		return trans;
	}


}
