package com.eunwoosoft.ipro.estm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmMngProgDao;
import com.eunwoosoft.ipro.estm.dao.IproEstmProgDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngProgService;

@Service(value="iproEstmCmtmMngProgService")
public class IproEstmCmtmMngProgServiceImpl extends AbstractFwkService implements IproEstmCmtmMngProgService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmMngProgServiceImpl.class);
	
	@Resource(name="iproEstmCmtmMngProgDao") 
	private IproEstmCmtmMngProgDao iproEstmCmtmMngProgDao;
	
	@Resource(name="iproEstmProgDao") 
	private IproEstmProgDao iproEstmProgDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;

	@Override
	public FwkTransferObject cmtmMngProgList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		
		trans.put("cmtmMngProgList", iproEstmCmtmMngProgDao.selectCmtmMngProgList(parameterMap));
		trans.put("cmtmMngProgListTotCnt", iproEstmCmtmMngProgDao.selectCmtmMngProgListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject cmtmMngProgRegist(final FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		// 전체평가시작일시
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_ST_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_MM"))){
			parameterMap.put("P_TOTL_ESTM_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_ST_HH") + parameterMap.get("P_TOTL_ESTM_ST_MM") + "00"); 
		}
		
		// 전체평가종료일시
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_END_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_MM"))){
			parameterMap.put("P_TOTL_ESTM_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_END_HH") + parameterMap.get("P_TOTL_ESTM_END_MM") + "00"); 
		}
		
		parameterMap.put("P_ESTM_PSCD", "A001");   // 평가진행상태코드
		
		// 평가관리 마스터 등록
		iproEstmProgDao.insertEstmMngMst(parameterMap);   // T_ESTM_MNG_MST INSERT
		
		if(parameterMap.get("P_ESTM_PROCD_SECD") != null) {
			
			Object P_ESTM_PROCD_SECD = parameterMap.get("P_ESTM_PROCD_SECD");
			Object P_ESTM_PROCD_SENM = parameterMap.get("P_ESTM_PROCD_SENM");
			Object P_ESTM_PROCD_NM = parameterMap.get("P_ESTM_PROCD_NM");
			Object P_ESTM_PROCD_ST_DE = parameterMap.get("P_ESTM_PROCD_ST_DE");
			Object P_ESTM_PROCD_ST_HH = parameterMap.get("P_ESTM_PROCD_ST_HH");
			Object P_ESTM_PROCD_ST_MM = parameterMap.get("P_ESTM_PROCD_ST_MM");
			Object P_ESTM_PROCD_END_DE = parameterMap.get("P_ESTM_PROCD_END_DE");
			Object P_ESTM_PROCD_END_HH = parameterMap.get("P_ESTM_PROCD_END_HH");
			Object P_ESTM_PROCD_END_MM = parameterMap.get("P_ESTM_PROCD_END_MM");
			Object P_ESTM_FRM_NO = parameterMap.get("P_ESTM_FRM_NO");
			Object P_ESTR_SECD = parameterMap.get("P_ESTR_SECD");

			if(P_ESTM_PROCD_SECD != null){
				
				if(P_ESTM_PROCD_SECD instanceof String){
					
					// 평가절차시작일시
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_ST_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_MM"))){
						parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_ST_HH") + parameterMap.get("P_ESTM_PROCD_ST_MM") + "00"); 
					}
					
					// 평가절차종료일시
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_END_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_MM"))){
						parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_END_HH") + parameterMap.get("P_ESTM_PROCD_END_MM") + "00"); 
					}
					
					// 평가절차 등록
					iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
					
					// 평가절차서식 등록
					iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					
					
				}else if(P_ESTM_PROCD_SECD instanceof ArrayList){
				
					ArrayList<String> estmProcdSecdList = (ArrayList<String>) P_ESTM_PROCD_SECD;
					ArrayList<String> estmProcdSenmList = (ArrayList<String>) P_ESTM_PROCD_SENM;
					ArrayList<String> estmProcdNmList = (ArrayList<String>) P_ESTM_PROCD_NM;
					ArrayList<String> estmProcdStDeList = (ArrayList<String>) P_ESTM_PROCD_ST_DE;
					ArrayList<String> estmProcdStHhList = (ArrayList<String>) P_ESTM_PROCD_ST_HH;
					ArrayList<String> estmProcdStMmList = (ArrayList<String>) P_ESTM_PROCD_ST_MM;
					ArrayList<String> estmProcdEndDeList = (ArrayList<String>) P_ESTM_PROCD_END_DE;
					ArrayList<String> estmProcdEndHhList = (ArrayList<String>) P_ESTM_PROCD_END_HH;
					ArrayList<String> estmProcdEndMmList = (ArrayList<String>) P_ESTM_PROCD_END_MM;
					ArrayList<String> estmFrmNoList = (ArrayList<String>) P_ESTM_FRM_NO;
					ArrayList<String> estrSecdList = (ArrayList<String>) P_ESTR_SECD;
					
					for(int i =0; i < estmProcdSecdList.size(); i++){
						
						if(estmProcdSecdList != null && estmProcdSecdList.get(i) != null){
							parameterMap.put("P_ESTM_PROCD_SECD", estmProcdSecdList.get(i));
						}

						if(estmProcdSenmList != null && estmProcdSenmList.get(i) != null){
							parameterMap.put("P_ESTM_PROCD_SENM", estmProcdSenmList.get(i));
						}
						
						if(estmProcdNmList != null && estmProcdNmList.get(i) != null){
							parameterMap.put("P_ESTM_PROCD_NM", estmProcdNmList.get(i));
						}
						
						if(estmFrmNoList != null && estmFrmNoList.get(i) != null){
							parameterMap.put("P_ESTM_FRM_NO", estmFrmNoList.get(i));
						}

						if(estrSecdList != null && estrSecdList.get(i) != null){
							parameterMap.put("P_ESTR_SECD", estrSecdList.get(i));							
						}
						
						// 평가절차시작일시
						if(!"".equals(estmProcdStDeList.get(i)) && !"".equals(estmProcdStHhList.get(i)) && !"".equals(estmProcdStMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhList.get(i) + estmProcdStMmList.get(i) + "00"); 
						}
						
						// 평가절차종료일시
						if(!"".equals(estmProcdEndDeList.get(i)) && !"".equals(estmProcdEndHhList.get(i)) && !"".equals(estmProcdEndMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhList.get(i) + estmProcdEndMmList.get(i) + "00"); 
						}
						
						// 평가절차 등록
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// 평가절차서식 등록
						iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}
				
				
				}else if(P_ESTM_PROCD_SECD instanceof String[]){
				
					String[] estmProcdSecdStr = (String[]) P_ESTM_PROCD_SECD;
					String[] estmProcdSenmStr = (String[]) P_ESTM_PROCD_SENM;
					String[] estmProcdNmStr = (String[]) P_ESTM_PROCD_NM; 
					String[] estmProcdStDeStr = (String[]) P_ESTM_PROCD_ST_DE;
					String[] estmProcdStHhStr = (String[]) P_ESTM_PROCD_ST_HH;
					String[] estmProcdStMmStr = (String[]) P_ESTM_PROCD_ST_MM;
					String[] estmProcdEndDeStr = (String[]) P_ESTM_PROCD_END_DE;
					String[] estmProcdEndHhStr = (String[]) P_ESTM_PROCD_END_HH;
					String[] estmProcdEndMmStr = (String[]) P_ESTM_PROCD_END_MM;
					String[] estmFrmNoStr = (String[]) P_ESTM_FRM_NO;
					String[] estrSecdStr = (String[]) P_ESTR_SECD;
					
					for (int i = 0; i < estmProcdSecdStr.length; i++) {
						
						if(estmProcdSecdStr[i] != null && !"".equals(estmProcdSecdStr[i])){
							parameterMap.put("P_ESTM_PROCD_SECD", estmProcdSecdStr[i]);	
						}
						
//						parameterMap.put("P_ESTM_PROCD_SENM", estmProcdSenmStr[i]);							
						
						if(estmProcdNmStr[i] != null && !"".equals(estmProcdNmStr[i])){
							parameterMap.put("P_ESTM_PROCD_NM", estmProcdNmStr[i]);							
						}
						
						if(estmFrmNoStr[i] != null && !"".equals(estmFrmNoStr[i])){
							parameterMap.put("P_ESTM_FRM_NO", estmFrmNoStr[i]);							
						}
						
						if(estrSecdStr[i] != null && !"".equals(estrSecdStr[i])){
							parameterMap.put("P_ESTR_SECD", estrSecdStr[i]);							
						}
						
						// 평가절차시작일시
						if(!"".equals(estmProcdStDeStr[i]) && !"".equals(estmProcdStHhStr[i]) && !"".equals(estmProcdStMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhStr[i] + estmProcdStMmStr[i] + "00"); 
						}
						
						// 평가절차종료일시
						if(!"".equals(estmProcdEndDeStr[i]) && !"".equals(estmProcdEndHhStr[i]) && !"".equals(estmProcdEndMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhStr[i] + estmProcdEndMmStr[i] + "00"); 
						}
						
						// 평가절차 등록
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// 평가절차서식 등록
						iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}
				}
			}
		}
		
		// 평가파일 등록
		if(!"".equals(parameterMap.get("P_FILE_GRP_NO"))){
			parameterMap.put("P_ESTM_FSCD", "MST");
			parameterMap.put("P_RMK", "");
			iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT
		}
		
		/********** 심사위원 서명파일 등록 start **********/	
		
		// 정보관리 > 평가구분관리에 등록되어있는 심사위원 서명파일 등록
		String P_ESTM_SECD_S = parameterMap.get("P_ESTM_SECD") + "";
		String ESTM_SECD_FILE_GRP_NO = "";   // 평가구분 파일그룹번호
		String contextPath = "estm";
		
		parameterMap.put("P_ESTM_SECD_S", P_ESTM_SECD_S);
		FwkDataEntity estmSeMngDetail = (FwkDataEntity) iproEstmProgDao.selectEstmSeMngDetail(parameterMap);   // T_ESTM_SE_MST
		
		if (estmSeMngDetail != null) {
			ESTM_SECD_FILE_GRP_NO = estmSeMngDetail.getString("FILE_GRP_NO");   // T_ESTM_SE_MST : FILE_GRP_NO
			
			
			/*** 첨부파일 복사 START ***/
			parameterMap.put("P_FILE_GRP_NO", ESTM_SECD_FILE_GRP_NO);
			List<FwkDataEntity> fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
			
			String fileSavePath = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");   // D:/edata1/
			String uploadDirPath = fileSavePath + FwkDateUtil.getCurrentDateAsString("yyyy") + "/" + FwkDateUtil.getCurrentDateAsString("MM") + "/" + FwkDateUtil.getCurrentDateAsString("dd");
			String copyPath = uploadDirPath + "/" + contextPath + "/";
			
			String ESTM_SIGN_FILE_GRP_NO = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			if(fileList.size() > 0){
				
				for(int i=0; i < fileList.size(); i++){
					FwkDataEntity fde = fileList.get(i);   // 파일 정보
					
					String sourcePath = fde.get("FILE_LCTN") + "/";
					String sourceName = fde.get("SV_FILE_NM") + "";
					String erpFileName = fde.get("SV_FILE_NM") + "";
										
					try {
						CmmnUtil.FileCopy(sourceName, erpFileName, sourcePath, copyPath);
						LOG.debug("첨부파일 복사에 성공하였습니다.");
						LOG.debug("===== FILE COPY SUCCESS =====");
					} catch (Exception e) {
						LOG.debug("첨부파일 복사에 실패하였습니다.");
						LOG.debug("===== FILE COPY FAIL =====");
						e.printStackTrace();
					}
					
				}
				
				parameterMap.put("P_FILE_GRP_NO_ORG", ESTM_SECD_FILE_GRP_NO);   // T_ESTM_SE_MST : FILE_GRP_NO
				parameterMap.put("P_FILE_GRP_NO_NEW", ESTM_SIGN_FILE_GRP_NO);   // UUID
				parameterMap.put("P_FILE_LCTN", copyPath);   // 파일경로
				parameterMap.put("P_TSK_VKEY1", parameterMap.get("P_ESTM_NO"));   // 파일경로
				iproEstmProgDao.selectAtchFileInsert(parameterMap);   // T_MM_FILE_MST INSERT
				
			}
			
			// 심사위원 서명파일 등록
			parameterMap.put("P_ESTM_FSCD", "SIGN");
			parameterMap.put("P_RMK", "");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT
			
		}
		
		/********** 심사위원 서명파일 등록 end **********/
		
		
		
		
		
		// 평가진행이력 등록
		//parameterMap.put("P_RMK", "평가작성");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
		parameterMap.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		parameterMap.put("resultCode", "Success");
		
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
			
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngProgDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가절차 조회
		List<FwkDataEntity> estmProcdList = iproEstmProgDao.selectEstmProcdList(parameterMap);   // T_ESTM_PROCD
		trans.put("estmProcdList", estmProcdList);
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) iproEstmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		// 심사위원 서명파일 첨부파일 그룹번호 조회
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) iproEstmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// 심사위원 서명파일 조회
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap);   // T_ESTM_PROCD 
		trans.put("estmTabList", estmTabList);
		
		
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngProgObjDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가대상정보 조회
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjList(parameterMap);   // T_ESTM_OBJ
		trans.put("estmObjList", estmObjList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		return trans;
	}

	@Override
	public FwkTransferObject estmProgCmtmDetail(final FwkParameterMap parameterMap) {
		/*FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
		}else{
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		}
		List<FwkDataEntity> outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// 지정평가위원정보 조회
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력			
			List<FwkDataEntity> fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("fixEstmCmtmList", fixEstmCmtmList);
		}else{
			trans.put("fixEstmCmtmList", "");
		}
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		List<FwkDataEntity> innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);*/
		
		FwkTransferObject trans = new FwkTransferObject();
		
		String P_PRIO_RNK_RE_SLCT_YN = parameterMap.getString("P_PRIO_RNK_RE_SLCT_YN");   // 재선정여부
		String P_CMPL_YN = parameterMap.getString("P_CMPL_YN");   // 최종여부
		parameterMap.put("P_PRIO_RNK_RE_SLCT_YN", P_PRIO_RNK_RE_SLCT_YN);
		parameterMap.put("P_CMPL_YN", P_CMPL_YN);
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		List<FwkDataEntity> outEstmCmtmList = new ArrayList<FwkDataEntity>();
		List<FwkDataEntity> fixEstmCmtmList = new ArrayList<FwkDataEntity>();
		List<FwkDataEntity> innEstmCmtmList = new ArrayList<FwkDataEntity>();
		
		if("Y".equals(P_PRIO_RNK_RE_SLCT_YN)){
			System.err.println("========== [재선정] ==========");
			
			// 외부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			}
			parameterMap.put("P_SLCT_YN", "Y");   // 선정여부
			outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			System.err.println("@@@ outEstmCmtmList ==> " + outEstmCmtmList.size());
			trans.put("outEstmCmtmSlctList", outEstmCmtmList);
			
			// 지정평가위원정보 조회
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력			
				parameterMap.put("P_SLCT_YN", "Y");   // 선정여부
				fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmSlctList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			parameterMap.put("P_SLCT_YN", "Y");   // 선정여부
			innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmSlctList", innEstmCmtmList);
			System.err.println("@@@ innEstmCmtmList ==> " + innEstmCmtmList.size());
			
			int P_ESTM_CMTM_RE_SLCT_NGR = parameterMap.getInt("P_ESTM_CMTM_SLCT_NGR") + 1;
			System.err.println("@@@ P_ESTM_CMTM_RE_SLCT_NGR ==> " + P_ESTM_CMTM_RE_SLCT_NGR);
			parameterMap.put("P_ESTM_CMTM_RE_SLCT_NGR", P_ESTM_CMTM_RE_SLCT_NGR);
			
		}else{
			System.err.println("========== [재선정 X] ==========");
			
			int P_ESTM_CMTM_SLCT_NGR_CLICK = parameterMap.getInt("P_ESTM_CMTM_SLCT_NGR_CLICK");
			
			// 외부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			}
			outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("outEstmCmtmList", outEstmCmtmList);
			
			// 지정평가위원정보 조회
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력			
				fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
			
			
			if(P_ESTM_CMTM_SLCT_NGR_CLICK > 0){
				
				outEstmCmtmList = null;
				fixEstmCmtmList = null;
				innEstmCmtmList = null;
				
				System.err.println("/********** 차수에 따른 평가위원정보 조회 **********/");
				parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_SLCT_NGR_CLICK);
				
				// 외부평가위원 조회
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				outEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("outEstmCmtmList", outEstmCmtmList);
				
				// 지정평가위원정보 조회
				if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
					parameterMap.put("P_INO_CMTM_SECD", "OUT");
					fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
					trans.put("fixEstmCmtmList", fixEstmCmtmList);
				}else{
					trans.put("fixEstmCmtmList", "");
				}
				
				// 내부평가위원 조회
				parameterMap.put("P_INO_CMTM_SECD", "INN");
				innEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("innEstmCmtmList", innEstmCmtmList);
				
			}
		}
		
		FwkDataEntity cmtmAutoBtnCtr = (FwkDataEntity) iproEstmProgDao.selectEstmCmtmAutoBtnCtr(parameterMap);   // T_ESTM_SE_MST
		System.err.println("@@@ cmtmAutoBtnCtr ==> " + cmtmAutoBtnCtr);
		trans.put("cmtmAutoBtnCtr", cmtmAutoBtnCtr);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		if("Y".equals(P_CMPL_YN)){   // 최종
			
			outEstmCmtmList = null;
			fixEstmCmtmList = null;
			innEstmCmtmList = null;
			
			System.err.println("========== [최종] ==========");
			
			parameterMap.put("P_SLCT_YN", "Y");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
			
			// 외부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			}
			outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("outEstmCmtmList", outEstmCmtmList);
			
			// 지정평가위원정보 조회
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력			
				fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
		}
		
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngProgProcdADetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjValueList(parameterMap);
		
		// 평가위원 목록 조회
		List<FwkDataEntity> estmCmtmLastList = iproEstmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// 평가위원+평가대상 목록 조회
		List<FwkDataEntity> estmValueList = iproEstmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}
	
	/**
	 * 평가구분관리 산술식 저장
	 */
	@Override
	public FwkTransferObject estmCmtmMngMstClcRulSave(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		iproEstmProgDao.updateEstmMngMstClcRulSave(parameterMap);
		
		parameterMap.put("resultCode", "Success");
		
		return trans;
	}
	
	
	@Override
	public FwkTransferObject cmtmMngProgListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		
		trans.put("cmtmMngProgList", iproEstmCmtmMngProgDao.selectCmtmMngProgExcelList(parameterMap));
	
		return trans;
	}
	
	@Override
	public void estmCmtmCntcObjList(final FwkParameterMap parameterMap) {
		
		// 평가대상 삭제
		iproEstmProgDao.deleteEstmObj(parameterMap);   // T_ESTM_OBJ DELETE

		// 평가공고참여정보_인터페이스 조회 -> T_ESTM_OBJ INSERT
		iproEstmCmtmMngProgDao.insertEstmCmtmCntcObj(parameterMap);   // V_ESTM_ANNC_TPI_INT INSERT
		

		iproEstmCmtmMngProgDao.deleteEstmCmtmPool(parameterMap);
		
		iproEstmCmtmMngProgDao.deleteEstmCmtmEduc(parameterMap);
		
		iproEstmCmtmMngProgDao.deleteEstmCmtmCare(parameterMap);
		
		iproEstmCmtmMngProgDao.deleteEstmCmtmCrqf(parameterMap);
		
		iproEstmCmtmMngProgDao.deleteEstmCmtmOfcCare(parameterMap);
		
		/**
		 * 평가위원POOL에 평가정보 담기
		 */
		// (T_ESTM_CMTM_POOL_MST 평가위원POOL 마스터) == (V_ESTM_CMTM_MST_IF 평가위원정보_연계)
		iproEstmCmtmMngProgDao.insertEstmCmtmPoolMst(parameterMap);
		// (T_ESTM_CMTM_EDUC 평가위원학력) == (V_ESTM_CMTM_EDUC_IF 평가위원학력정보_연계)
		iproEstmCmtmMngProgDao.insertEstmCmtmEduc(parameterMap);
		// (T_ESTM_CMTM_CARE 평가위원활동경력) == (V_ESTM_CMTM_CARE_IF 평가위원활동경력정보_연계)
		iproEstmCmtmMngProgDao.insertEstmCmtmCare(parameterMap);
		// (T_ESTM_CMTM_CRQF 평가위원자격증) == (V_ESTM_CMTM_CRQF_IF 평가위원자격증정보_연계)
		iproEstmCmtmMngProgDao.insertEstmCmtmCrqf(parameterMap);
		// (T_ESTM_CMTM_OFC_CARE 평가위원직장경력) == (V_ESTM_CMTM_OFC_IF 평가위원직장경력정보_연계)
		iproEstmCmtmMngProgDao.insertEstmCmtmOfcCare(parameterMap);
		
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상불러오기");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
	}
	
	@Override
	public void estmCmtmCntcObjFileDown(final FwkParameterMap parameterMap) {
		
		List<FwkDataEntity> objFileList = iproEstmCmtmMngProgDao.selectEstmCmtmPoolFileList(parameterMap);
		
		
		for(int i = 0; i < objFileList.size(); i++) {
			FwkDataEntity objFileDataEntity = objFileList.get(i);
			String atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			// 원본파일위치
			String origFilePath = FwkMessageUtil.getMessage("CMTM.ORIGFILE.PATH");
			// 복사파일위치
			String copyFilePath = FwkMessageUtil.getMessage("CMTM.COPYFILE.PATH");
//			CMTM.ORIGFILE.PATH = D:
//			# 카피 파일경로
//			CMTM.COPYFILE.PATH = D:/COPY
			
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
			
//			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

			//재직 hffc
			if(!"".equals(objFileDataEntity.get("HFFC_PRODDF_FILE_STRE_COURS")) && objFileDataEntity.get("HFFC_PRODDF_FILE_STRE_COURS") != null) {
				File origFileHffc = new File(origFilePath + objFileDataEntity.get("HFFC_PRODDF_FILE_STRE_COURS"));
				
				String[] copyHffcSplit = objFileDataEntity.get("HFFC_PRODDF_FILE_STRE_COURS").toString().split("/");
				
				String copyFilePathStr = copyFilePath;
				String copyFileNm = "";
				
				for(int j = 0; j < copyHffcSplit.length; j++) {
					
					if(j == 0) {
						copyFilePathStr += copyHffcSplit[j].toString();
					}else if(j < copyHffcSplit.length-1){
						copyFilePathStr += (File.separator + copyHffcSplit[j].toString());
					}else if(j == copyHffcSplit.length-1){
						copyFileNm = copyHffcSplit[j].toString();
					}
				}
				
				File copyFileHffcPath = new File(copyFilePathStr);
				
				boolean mkdirSuccess = true;
				if(!copyFileHffcPath.isDirectory()){
					copyFileHffcPath.setExecutable(true);
					copyFileHffcPath.setReadable(true);
					copyFileHffcPath.setWritable(true);
					mkdirSuccess = copyFileHffcPath.mkdirs();
				}
				
				File copyFileHffc = new File(copyFilePath + objFileDataEntity.get("HFFC_PRODDF_FILE_STRE_COURS"));
				
				// 재직파일 복사
				try {
					
					FileInputStream fis = new FileInputStream(origFileHffc);
					FileOutputStream fos = new FileOutputStream(copyFileHffc);
					
					int fileByte = 0;
					
					while((fileByte = fis.read()) != -1) {
						fos.write(fileByte);
					}
					
					fis.close();
					fos.close();
					
					FwkParameterMap fileMap = new FwkParameterMap();

					
					fileMap.put("USR_ID", session.get("USR_ID"));
					fileMap.put("USR_NM", session.get("USR_NM"));
					fileMap.put("CONN_IP", session.get("CONN_IP"));
					
					long fileSize = origFileHffc.length();
					
					fileMap.put("P_FILE_GRP_NO",atchFileGroupNo);
					fileMap.put("P_FILE_DOC_SECD", "HFFC");
					fileMap.put("P_FILE_SZ", fileSize); 										//용량
					fileMap.put("P_SV_FILE_NM",  copyFileNm); 					//파일변환명
					fileMap.put("P_SYS_FILE_NM",   objFileDataEntity.get("HFFC_PRODDF_WON_FILE_NM")); 			//파일명
					fileMap.put("P_FILE_LCTN",  copyFilePathStr );										//경로
					
//					System.out.println("fileMap ::" + fileMap.toString());
					comAtmaAtchFileDao.insertAtchFileRegistCmtm(fileMap);
//					list.add(fileMap);
					
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("HFFC_PRODDF_FILE ::: " + objFileDataEntity.get("HFFC_PRODDF_WON_FILE_NM"));
				}
				
				
			} // if (HFFC_PRODDF_FILE_STRE_COURS)
			
			//자격 crqfc
			if(!"".equals(objFileDataEntity.get("CRQFC_FILE_STRE_COURS")) && objFileDataEntity.get("CRQFC_FILE_STRE_COURS") != null) {
				File origFileCrqfc = new File(origFilePath + objFileDataEntity.get("CRQFC_FILE_STRE_COURS"));
				
				String[] copyCrqfcSplit = objFileDataEntity.get("CRQFC_FILE_STRE_COURS").toString().split("/");
				
				String copyFilePathStr = copyFilePath;
				String copyFileNm = "";
				
				for(int j = 0; j < copyCrqfcSplit.length; j++) {
					
					if(j == 0) {
						copyFilePathStr += copyCrqfcSplit[j].toString();
					}else if(j < copyCrqfcSplit.length-1){
						copyFilePathStr += (File.separator + copyCrqfcSplit[j].toString());
					}else if(j == copyCrqfcSplit.length-1){
						copyFileNm = copyCrqfcSplit[j].toString();
					}
					
				}
				
				File copyFileCrqfcPath = new File(copyFilePathStr);
				
				boolean mkdirSuccess = true;
				if(!copyFileCrqfcPath.isDirectory()){
					copyFileCrqfcPath.setExecutable(true);
					copyFileCrqfcPath.setReadable(true);
					copyFileCrqfcPath.setWritable(true);
					mkdirSuccess = copyFileCrqfcPath.mkdirs();
				}
				
				File copyFileCrqfc = new File(copyFilePath + objFileDataEntity.get("CRQFC_FILE_STRE_COURS"));
				
				// 자격파일 복사
				try {
					
					FileInputStream fis = new FileInputStream(origFileCrqfc);
					FileOutputStream fos = new FileOutputStream(copyFileCrqfc);
					
					int fileByte = 0;
					
					while((fileByte = fis.read()) != -1) {
						fos.write(fileByte);
					}
					
					fis.close();
					fos.close();
					
					FwkParameterMap fileMap = new FwkParameterMap();

					
					fileMap.put("USR_ID", session.get("USR_ID"));
					fileMap.put("USR_NM", session.get("USR_NM"));
					fileMap.put("CONN_IP", session.get("CONN_IP"));
					
					long fileSize = origFileCrqfc.length();
					
					fileMap.put("P_FILE_GRP_NO",atchFileGroupNo);
					fileMap.put("P_FILE_DOC_SECD", "CRQFC");
					fileMap.put("P_FILE_SZ", fileSize); 										//용량
					fileMap.put("P_SV_FILE_NM",  copyFileNm); 					//파일변환명
					fileMap.put("P_SYS_FILE_NM",   objFileDataEntity.get("CRQFC_WON_FILE_NM")); 			//파일명
					fileMap.put("P_FILE_LCTN",  copyFilePathStr );										//경로
					
					System.out.println("fileMap ::" + fileMap.toString());
					
					comAtmaAtchFileDao.insertAtchFileRegistCmtm(fileMap);
					
//					list.add(fileMap);
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("CRQFC_WON_FILE_NM ::: " + objFileDataEntity.get("CRQFC_WON_FILE_NM"));
				}
			} // if (CRQFC_FILE_STRE_COURS)
			
			// T_ESTM_CMTM_POOL_MST 에 UPDATE
			parameterMap.put("P_FILE_GRP_NO",atchFileGroupNo);
			parameterMap.put("P_ESTM_CMTM_NO",objFileDataEntity.get("ESTM_CMTM_NO"));
			
			iproEstmCmtmMngProgDao.updateEstmCmtmPoolMstFileGrpNoUpdt(parameterMap);
			
		} // for(int i)
		
		
	}
	
	
	
	

	@Override
	public FwkTransferObject cmtmMngProgUpdt(final FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		// 전체평가시작일시
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_ST_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_MM"))){
			parameterMap.put("P_TOTL_ESTM_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_ST_HH") + parameterMap.get("P_TOTL_ESTM_ST_MM") + "00"); 
		}
		
		// 전체평가종료일시
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_END_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_MM"))){
			parameterMap.put("P_TOTL_ESTM_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_END_HH") + parameterMap.get("P_TOTL_ESTM_END_MM") + "00"); 
		}
		
		// 평가관리 마스터 수정
		iproEstmProgDao.updateEstmMngMst(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		if(parameterMap.get("P_ESTM_PROCD_SECD") != null) {
			
			// 평가절차 삭제
			iproEstmProgDao.deleteEstmProcd(parameterMap);   // T_ESTM_PROCD DELETE
			
			// 평가절차서식 삭제
			iproEstmProgDao.deleteEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM DELETE
			
			Object P_ESTM_PROCD_SECD = parameterMap.get("P_ESTM_PROCD_SECD");
			Object P_ESTM_PROCD_SENM = parameterMap.get("P_ESTM_PROCD_SENM");
			Object P_ESTM_PROCD_NM = parameterMap.get("P_ESTM_PROCD_NM");
			Object P_ESTM_PROCD_ST_DE = parameterMap.get("P_ESTM_PROCD_ST_DE");
			Object P_ESTM_PROCD_ST_HH = parameterMap.get("P_ESTM_PROCD_ST_HH");
			Object P_ESTM_PROCD_ST_MM = parameterMap.get("P_ESTM_PROCD_ST_MM");
			Object P_ESTM_PROCD_END_DE = parameterMap.get("P_ESTM_PROCD_END_DE");
			Object P_ESTM_PROCD_END_HH = parameterMap.get("P_ESTM_PROCD_END_HH");
			Object P_ESTM_PROCD_END_MM = parameterMap.get("P_ESTM_PROCD_END_MM");
			Object P_ESTM_FRM_NO = parameterMap.get("P_ESTM_FRM_NO");
			Object P_ESTR_SECD = parameterMap.get("P_ESTR_SECD");

			if(P_ESTM_PROCD_SECD != null){
				
				if(P_ESTM_PROCD_SECD instanceof String){
					
					// 평가절차시작일시
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_ST_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_MM"))){
						parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_ST_HH") + parameterMap.get("P_ESTM_PROCD_ST_MM") + "00"); 
					}
					
					// 평가절차종료일시
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_END_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_MM"))){
						parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_END_HH") + parameterMap.get("P_ESTM_PROCD_END_MM") + "00"); 
					}
					
					// 평가절차 등록
					iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
					
					// 평가절차서식 등록
					iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					
					
				}else if(P_ESTM_PROCD_SECD instanceof ArrayList){
				
					ArrayList<String> estmProcdSecdList = (ArrayList<String>) P_ESTM_PROCD_SECD;
					ArrayList<String> estmProcdSenmList = (ArrayList<String>) P_ESTM_PROCD_SENM;
					ArrayList<String> estmProcdNmList = (ArrayList<String>) P_ESTM_PROCD_NM;
					ArrayList<String> estmProcdStDeList = (ArrayList<String>) P_ESTM_PROCD_ST_DE;
					ArrayList<String> estmProcdStHhList = (ArrayList<String>) P_ESTM_PROCD_ST_HH;
					ArrayList<String> estmProcdStMmList = (ArrayList<String>) P_ESTM_PROCD_ST_MM;
					ArrayList<String> estmProcdEndDeList = (ArrayList<String>) P_ESTM_PROCD_END_DE;
					ArrayList<String> estmProcdEndHhList = (ArrayList<String>) P_ESTM_PROCD_END_HH;
					ArrayList<String> estmProcdEndMmList = (ArrayList<String>) P_ESTM_PROCD_END_MM;
					ArrayList<String> estmFrmNoList = (ArrayList<String>) P_ESTM_FRM_NO;
					ArrayList<String> estrSecdList = (ArrayList<String>) P_ESTR_SECD;
					
					for(int i =0; i < estmProcdSecdList.size(); i++){
						
						if(estmProcdSecdList != null && estmProcdSecdList.get(i) != null){
							parameterMap.put("P_ESTM_PROCD_SECD", estmProcdSecdList.get(i));
						}
						
						if(estmProcdSenmList != null && estmProcdSenmList.get(i) != null){
							parameterMap.put("P_ESTM_PROCD_SENM", estmProcdSenmList.get(i));
						}
						
						if(estmProcdNmList != null && estmProcdNmList.get(i) != null){
							parameterMap.put("P_ESTM_PROCD_NM", estmProcdNmList.get(i));
						}
						
						if(estmFrmNoList != null && estmFrmNoList.get(i) != null){
							parameterMap.put("P_ESTM_FRM_NO", estmFrmNoList.get(i));
						}

						if(estrSecdList != null && estrSecdList.get(i) != null){
							parameterMap.put("P_ESTR_SECD", estrSecdList.get(i));							
						}
						
						// 평가절차시작일시
						if(!"".equals(estmProcdStDeList.get(i)) && !"".equals(estmProcdStHhList.get(i)) && !"".equals(estmProcdStMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhList.get(i) + estmProcdStMmList.get(i) + "00"); 
						}
						
						// 평가절차종료일시
						if(!"".equals(estmProcdEndDeList.get(i)) && !"".equals(estmProcdEndHhList.get(i)) && !"".equals(estmProcdEndMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhList.get(i) + estmProcdEndMmList.get(i) + "00"); 
						}
						
						// 평가절차 등록
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// 평가절차서식 등록
						iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}
				
				
				}else if(P_ESTM_PROCD_SECD instanceof String[]){
				
					String[] estmProcdSecdStr = (String[]) P_ESTM_PROCD_SECD;
					String[] estmProcdNmStr = (String[]) P_ESTM_PROCD_NM; 
					String[] estmProcdStDeStr = (String[]) P_ESTM_PROCD_ST_DE;
					String[] estmProcdStHhStr = (String[]) P_ESTM_PROCD_ST_HH;
					String[] estmProcdStMmStr = (String[]) P_ESTM_PROCD_ST_MM;
					String[] estmProcdEndDeStr = (String[]) P_ESTM_PROCD_END_DE;
					String[] estmProcdEndHhStr = (String[]) P_ESTM_PROCD_END_HH;
					String[] estmProcdEndMmStr = (String[]) P_ESTM_PROCD_END_MM;
					String[] estmFrmNoStr = (String[]) P_ESTM_FRM_NO;
					String[] estrSecdStr = (String[]) P_ESTR_SECD;
					
					for (int i = 0; i < estmProcdSecdStr.length; i++) {
						
						if(estmProcdSecdStr[i] != null && !"".equals(estmProcdSecdStr[i])){
							parameterMap.put("P_ESTM_PROCD_SECD", estmProcdSecdStr[i]);	
						}
						
//						parameterMap.put("P_ESTM_PROCD_SENM", estmProcdSenmStr[i]);							
						
						if(estmProcdNmStr[i] != null && !"".equals(estmProcdNmStr[i])){
							parameterMap.put("P_ESTM_PROCD_NM", estmProcdNmStr[i]);							
						}
						
						if(estmFrmNoStr[i] != null && !"".equals(estmFrmNoStr[i])){
							parameterMap.put("P_ESTM_FRM_NO", estmFrmNoStr[i]);							
						}
						
						if(estrSecdStr[i] != null && !"".equals(estrSecdStr[i])){
							parameterMap.put("P_ESTR_SECD", estrSecdStr[i]);							
						}
						
						// 평가절차시작일시
						if(!"".equals(estmProcdStDeStr[i]) && !"".equals(estmProcdStHhStr[i]) && !"".equals(estmProcdStMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhStr[i] + estmProcdStMmStr[i] + "00"); 
						}
						
						// 평가절차종료일시
						if(!"".equals(estmProcdEndDeStr[i]) && !"".equals(estmProcdEndHhStr[i]) && !"".equals(estmProcdEndMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhStr[i] + estmProcdEndMmStr[i] + "00"); 
						}
						
						// 평가절차 등록
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// 평가절차서식 등록
						iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}
				}
			}
		}
		
		if(!"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			// 평가파일 등록
			parameterMap.put("P_ESTM_FSCD", "MST");
			parameterMap.put("P_RMK", "");
			iproEstmProgDao.deleteEstmFile(parameterMap);   // T_ESTM_FILE DELETE
			iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT			
		}
		
		/********** 심사위원 서명파일 등록 start **********/
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "estm";
		FwkParameterMap signFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_SIGN_FILE", contextPath);   // 심사위원 서명파일
		
		
		String SIGN_FILE_GRP_NO = "";
		
		if(signFileParameterMap != null) {
			
			parameterMap.put("P_ESTM_FSCD", "SIGN");
			parameterMap.put("P_RMK", "");			
			iproEstmProgDao.deleteEstmFile(parameterMap);   // T_ESTM_FILE DELETE
			
			 
			if("".equals(parameterMap.getString("P_SIGN_FILE_GRP_NO"))){
				SIGN_FILE_GRP_NO = signFileParameterMap.getString("atchFileGroupNo");
			}else{
				SIGN_FILE_GRP_NO = parameterMap.getString("P_SIGN_FILE_GRP_NO");	
			}

			List<Map<String, Object>> list = (List<Map<String, Object>>)signFileParameterMap.get("list");
			List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
			
			Object P_FILE_DOC_NM =  parameterMap.get("P_FILE_DOC_NM");
			
			System.err.println("@@@ list ==> " + list.toString());
			
			if(P_FILE_DOC_NM instanceof String) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", P_FILE_DOC_NM.toString());
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof String[]) {
				System.err.println("@@@ String[] @@@");
				String[] fileDocNmArray = (String[])P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmArray[i]);
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof ArrayList) {
				System.err.println("@@@ ArrayList @@@");
				ArrayList<String> fileDocNmList = (ArrayList<String>)P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
					newList.add(map);
				}
			}

			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", SIGN_FILE_GRP_NO);
			parameterMap.put("fileList", newList);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			
			parameterMap.put("P_TSK_VKEY1", parameterMap.getString("P_ESTM_NO"));
			comAtmaAtchFileDao.insertMMFileRegist(parameterMap);   // T_MM_FILE_MST INSERT
			
			parameterMap.put("P_FILE_GRP_NO", SIGN_FILE_GRP_NO);
			parameterMap.put("P_ESTM_FSCD", "SIGN");
			parameterMap.put("P_RMK", "");
			iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT
		}
		
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
				
		/********** 심사위원 서명파일 등록 end **********/
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가수정");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
		parameterMap.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		parameterMap.put("resultCode", "Success");
		
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		trans.put("resultCode", "Success");
		
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngProgResultDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmProgDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
		
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmProgDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmProgDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "Y");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmProgDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		trans.put("estmResultObjAllListTotCnt", iproEstmProgDao.selectEstmResultObjAllListTotCnt(parameterMap));
		
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmProgDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());
		
		//(평가항목)P_SEARCH_ITEM
		//(평가위원총점)P_SEARCH_ITEM_CMTMTOT
		//(평가총점)P_SEARCH_ESTM_PROCD_SEQ_TOT
		//(최종점수합계적용여부)P_SEARCH_ITEM_TOTSUMAT
		//(최종점수합계적용비율)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");
//		Object SEARCH_ITEM_CMTMTOT = parameterMap.get("P_SEARCH_ITEM_CMTMTOT");
//		Object SEARCH_ESTM_PROCD_SEQ_TOT = parameterMap.get("P_SEARCH_ESTM_PROCD_SEQ_TOT");
//		Object SEARCH_ITEM_TOTSUMAT = parameterMap.get("P_SEARCH_ITEM_TOTSUMAT");

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST = new ArrayList<String>();
		
		
		String SEARCH_CHECK = "";
		// 평가항목
		if(SEARCH_ITEM instanceof String) {
			P_SEARCH_ITEM_LIST.add((String) SEARCH_ITEM);
			SEARCH_CHECK = SEARCH_ITEM.toString();
			P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
		}else {
			if(SEARCH_ITEM instanceof ArrayList) {
				ArrayList<String> SEARCH_ITEMList = (ArrayList) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.size(); idx++) {
					SEARCH_CHECK = SEARCH_ITEMList.get(idx).toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList.get(idx));
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}else if(SEARCH_ITEM instanceof String[]) {
				String[] SEARCH_ITEMList = (String[]) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.length; idx++) {
					SEARCH_CHECK = SEARCH_ITEMList[idx].toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList[idx]);
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}
		}
		
		// 평가위원총합
//		if(SEARCH_ITEM_CMTMTOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ITEM_CMTMTOT);
//			SEARCH_CHECK = (String) SEARCH_ITEM_CMTMTOT;
//			P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ITEM_CMTMTOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ITEM_CMTMTOTList = (ArrayList) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList.get(idx).toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList.get(idx));
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ITEM_CMTMTOT instanceof String[]) {
//				String[] SEARCH_ITEM_CMTMTOTList = (String[]) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList[idx].toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList[idx]);
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
		// 평가총점
//		if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ESTM_PROCD_SEQ_TOT);
//			SEARCH_CHECK = (String) SEARCH_ESTM_PROCD_SEQ_TOT;
//			P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ESTM_PROCD_SEQ_TOTList = (ArrayList) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx).toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx));
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String[]) {
//				String[] SEARCH_ESTM_PROCD_SEQ_TOTList = (String[]) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList[idx].toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList[idx]);
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
//		System.out.println("P_SEARCH_ITEM_LIST ::: " + P_SEARCH_ITEM_LIST.size());
//		System.out.println("P_SEARCH_ITEM_CMTMTOT_LIST ::: " + P_SEARCH_ITEM_CMTMTOT_LIST.size());
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

		//평가위원총점		
//		if(P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ITEM_CMTMTOT_LIST", P_SEARCH_ITEM_CMTMTOT_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CMTMTOT_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ITEM_CMTMTOT_CHECK_LIST", estmResultSearchCheckList);
//		}
		
		//평가총점		
//		if(P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ESTM_PROCD_SEQ_LIST", P_SEARCH_ESTM_PROCD_SEQ_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ESTM_PROCD_SEQ_CHECK_LIST", estmResultSearchCheckList);
//		}
		
			
//		if(P_SEARCH_ITEM_LIST.size() > 0 || P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0 || P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0) {
//			
//		}
		
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmProgDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmProgDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmProgDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//
		
		
		return trans;
	}
	
	
	@Override
	public FwkTransferObject cmtmMngProgResultDetailExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmProgDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
		
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmProgDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmProgDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "N");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmProgDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmProgDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());
		
		//(평가항목)P_SEARCH_ITEM
		//(평가위원총점)P_SEARCH_ITEM_CMTMTOT
		//(평가총점)P_SEARCH_ESTM_PROCD_SEQ_TOT
		//(최종점수합계적용여부)P_SEARCH_ITEM_TOTSUMAT
		//(최종점수합계적용비율)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");
//		Object SEARCH_ITEM_CMTMTOT = parameterMap.get("P_SEARCH_ITEM_CMTMTOT");
//		Object SEARCH_ESTM_PROCD_SEQ_TOT = parameterMap.get("P_SEARCH_ESTM_PROCD_SEQ_TOT");
//		Object SEARCH_ITEM_TOTSUMAT = parameterMap.get("P_SEARCH_ITEM_TOTSUMAT");

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ITEM_CMTMTOT_CHECK_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_LIST = new ArrayList<String>();
//		ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST = new ArrayList<String>();
		
		
		String SEARCH_CHECK = "";
		// 평가항목
		if(SEARCH_ITEM instanceof String) {
			P_SEARCH_ITEM_LIST.add((String) SEARCH_ITEM);
			SEARCH_CHECK = SEARCH_ITEM.toString();
			P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
		}else {
			if(SEARCH_ITEM instanceof ArrayList) {
				ArrayList<String> SEARCH_ITEMList = (ArrayList) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.size(); idx++) {
					SEARCH_CHECK = SEARCH_ITEMList.get(idx).toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList.get(idx));
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}else if(SEARCH_ITEM instanceof String[]) {
				String[] SEARCH_ITEMList = (String[]) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.length; idx++) {
					SEARCH_CHECK = SEARCH_ITEMList[idx].toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList[idx]);
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}
		}
		
		// 평가위원총합
//		if(SEARCH_ITEM_CMTMTOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ITEM_CMTMTOT);
//			SEARCH_CHECK = (String) SEARCH_ITEM_CMTMTOT;
//			P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ITEM_CMTMTOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ITEM_CMTMTOTList = (ArrayList) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList.get(idx).toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList.get(idx));
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ITEM_CMTMTOT instanceof String[]) {
//				String[] SEARCH_ITEM_CMTMTOTList = (String[]) SEARCH_ITEM_CMTMTOT;
//				for (int idx = 0; idx < SEARCH_ITEM_CMTMTOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ITEM_CMTMTOTList[idx].toString();
//					P_SEARCH_ITEM_CMTMTOT_LIST.add(SEARCH_ITEM_CMTMTOTList[idx]);
//					P_SEARCH_ITEM_CMTMTOT_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
		// 평가총점
//		if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String) {
//			P_SEARCH_ESTM_PROCD_SEQ_LIST.add((String) SEARCH_ESTM_PROCD_SEQ_TOT);
//			SEARCH_CHECK = (String) SEARCH_ESTM_PROCD_SEQ_TOT;
//			P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//		}else {
//			if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof ArrayList) {
//				ArrayList<String> SEARCH_ESTM_PROCD_SEQ_TOTList = (ArrayList) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.size(); idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx).toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList.get(idx));
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}else if(SEARCH_ESTM_PROCD_SEQ_TOT instanceof String[]) {
//				String[] SEARCH_ESTM_PROCD_SEQ_TOTList = (String[]) SEARCH_ESTM_PROCD_SEQ_TOT;
//				for (int idx = 0; idx < SEARCH_ESTM_PROCD_SEQ_TOTList.length; idx++) {
//					SEARCH_CHECK = SEARCH_ESTM_PROCD_SEQ_TOTList[idx].toString();
//					P_SEARCH_ESTM_PROCD_SEQ_LIST.add(SEARCH_ESTM_PROCD_SEQ_TOTList[idx]);
//					P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.add(SEARCH_CHECK);
//				}
//			}
//		}
		
//		System.out.println("P_SEARCH_ITEM_LIST ::: " + P_SEARCH_ITEM_LIST.size());
//		System.out.println("P_SEARCH_ITEM_CMTMTOT_LIST ::: " + P_SEARCH_ITEM_CMTMTOT_LIST.size());
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

		//평가위원총점		
//		if(P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ITEM_CMTMTOT_LIST", P_SEARCH_ITEM_CMTMTOT_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CMTMTOT_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ITEM_CMTMTOT_CHECK_LIST", estmResultSearchCheckList);
//		}
		
		//평가총점		
//		if(P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0){
//			parameterMap.put("P_SEARCH_ESTM_PROCD_SEQ_LIST", P_SEARCH_ESTM_PROCD_SEQ_LIST);
//			parameterMap.put("P_CHECK_LIST", P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST);
//			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
//			trans.put("SEARCH_ESTM_PROCD_SEQ_CHECK_LIST", estmResultSearchCheckList);
//		}
		
			
//		if(P_SEARCH_ITEM_LIST.size() > 0 || P_SEARCH_ITEM_CMTMTOT_LIST.size() > 0 || P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST.size() > 0) {
//			
//		}
		
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmProgDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmProgDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmProgDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//
		
		
		return trans;
	}
	
	
	

	@Override
	public FwkTransferObject cmtmMngProgVidoMtngDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT","N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 화상회의 참여자 구분
		// 관리자 ""
		// 평가위원 "CMTM"
		// 평가대상 "parameterMap.put("P_ESTM_NO")
		parameterMap.put("P_USER_GBN","");
		parameterMap.put("P_ROLE","admin"); //admin , interviewer , interviewee
		parameterMap.put("P_VIDO_LINK_URL",FwkMessageUtil.getMessage("VIDO.LINK.URL"));
		
		//화상회의정보
		List<FwkDataEntity> estmVidoList = iproEstmProgDao.selectEstmProgVidoMtngList(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmVidoList", estmVidoList);
		
		
		// 평가절차리스트 조회
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngProgVidoMtngSave(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 화상회의정보 저장
		Object P_VIDO_MTNG_NM = parameterMap.get("P_VIDO_MTNG_NM"); //화상회의명
		Object P_VIDO_MTNG_PRST_SECD = parameterMap.get("P_VIDO_MTNG_PRST_SECD"); // 화상회의진행상태
		
		Object P_VIDO_ST_DE = parameterMap.get("P_VIDO_ST_DE");
		Object P_VIDO_ST_HH = parameterMap.get("P_VIDO_ST_HH");
		Object P_VIDO_ST_MM = parameterMap.get("P_VIDO_ST_MM");
		Object P_VIDO_END_DE = parameterMap.get("P_VIDO_END_DE");
		Object P_VIDO_END_HH = parameterMap.get("P_VIDO_END_HH");
		Object P_VIDO_END_MM = parameterMap.get("P_VIDO_END_MM");
		//P_ESTM_PROCD_END_MM
		
		iproEstmProgDao.deleteVidoMtngCmtmDelt(parameterMap); // T_ESTM_VIDO_MTNG_CMTM 삭제
		iproEstmProgDao.deleteVidoMtngObjDelt(parameterMap); // T_ESTM_VIDO_MTNG_OBJ 삭제
		iproEstmProgDao.deleteVidoMtngDelt(parameterMap); // T_ESTM_VIDO_MTNG 삭제
		
		if(P_VIDO_MTNG_PRST_SECD != null){
			
			System.err.println("@@@ P_VIDO_MTNG_PRST_SECD != null @@@");
			
			if(P_VIDO_MTNG_PRST_SECD instanceof String){
				
				System.err.println("@@@ String @@@");
				
				//화상회의진행상태가 없을때만 insert 되도록 한다. 즉, 신규데이터만 insert
				if(parameterMap.get("P_VIDO_MTNG_PRST_SECD") == null || "".equals(parameterMap.get("P_VIDO_MTNG_PRST_SECD"))) {
					
					// 평가절차시작일시
					if(!"".equals(parameterMap.getString("P_VIDO_ST_DE")) && !"".equals(parameterMap.getString("P_VIDO_ST_HH")) && !"".equals(parameterMap.getString("P_VIDO_ST_MM"))){
						parameterMap.put("P_VIDO_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_VIDO_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_VIDO_ST_HH") + parameterMap.get("P_VIDO_ST_MM") + "00"); 
					}
					
					// 평가절차종료일시
					if(!"".equals(parameterMap.getString("P_VIDO_END_DE")) && !"".equals(parameterMap.getString("P_VIDO_END_HH")) && !"".equals(parameterMap.getString("P_VIDO_END_MM"))){
						parameterMap.put("P_VIDO_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_VIDO_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_VIDO_END_HH") + parameterMap.get("P_VIDO_END_MM") + "00"); 
					}
					
					// 화상회의 순번 MAX+1값 뽑기 VIDO_MTNG_SEQ
					int vidoMtngSeq = iproEstmProgDao.selectVidoMtngSeq(parameterMap);
					parameterMap.put("P_VIDO_MTNG_SEQ", vidoMtngSeq);
					parameterMap.put("P_ESTM_PROCD_SEQ", "1");
					// 화상회의마스터 T_ESTM_VIDO_MTNG 저장
					iproEstmProgDao.insertVidoMtng(parameterMap);
					// 평가위원화상회의참가 T_ESTM_VIDO_MTNG_CMTM 저장
					iproEstmProgDao.insertVidoMtngCmtm(parameterMap);
					// 평가대상화상회의참가  T_ESTM_VIDO_MTNG_OBJ 저장
					iproEstmProgDao.insertVidoMtngObj(parameterMap);
				}
			}else if(P_VIDO_MTNG_PRST_SECD instanceof ArrayList){
			
				System.err.println("@@@ ArrayList @@@");
				
				ArrayList<String> P_VIDO_MTNG_NMList = (ArrayList<String>) P_VIDO_MTNG_NM;
				ArrayList<String> P_VIDO_MTNG_PRST_SECDList = (ArrayList<String>) P_VIDO_MTNG_PRST_SECD;
				ArrayList<String> P_VIDO_ST_DEList = (ArrayList<String>) P_VIDO_ST_DE;
				ArrayList<String> P_VIDO_ST_HHList = (ArrayList<String>) P_VIDO_ST_HH;
				ArrayList<String> P_VIDO_ST_MMList = (ArrayList<String>) P_VIDO_ST_MM;
				ArrayList<String> P_VIDO_END_DEList = (ArrayList<String>) P_VIDO_END_DE;
				ArrayList<String> P_VIDO_END_HHList = (ArrayList<String>) P_VIDO_END_HH;
				ArrayList<String> P_VIDO_END_MMList = (ArrayList<String>) P_VIDO_END_MM;
				
					for(int i =1; i < P_VIDO_MTNG_PRST_SECDList.size(); i++){
						
						parameterMap.put("P_VIDO_MTNG_NM", P_VIDO_MTNG_NMList.get(i));
						parameterMap.put("P_VIDO_MTNG_PRST_SECD", P_VIDO_MTNG_PRST_SECDList.get(i));
						//화상회의진행상태가 없을때만 insert 되도록 한다. 즉, 신규데이터만 insert
						if(parameterMap.get("P_VIDO_MTNG_PRST_SECD") == null || "".equals(parameterMap.get("P_VIDO_MTNG_PRST_SECD"))) {
							
							// 평가절차시작일시
							if(!"".equals(P_VIDO_ST_DEList.get(i)) && !"".equals(P_VIDO_ST_HHList.get(i)) && !"".equals(P_VIDO_ST_MMList.get(i))){
								parameterMap.put("P_VIDO_ST_DT", FwkFormatUtil.formatDate(P_VIDO_ST_DEList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_ST_HHList.get(i) + P_VIDO_ST_MMList.get(i) + "00"); 
							}
							
							// 평가절차종료일시
							if(!"".equals(P_VIDO_END_DEList.get(i)) && !"".equals(P_VIDO_END_HHList.get(i)) && !"".equals(P_VIDO_END_MMList.get(i))){
								parameterMap.put("P_VIDO_END_DT", FwkFormatUtil.formatDate(P_VIDO_END_DEList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_END_HHList.get(i) + P_VIDO_END_MMList.get(i) + "00"); 
							}
							
							// 평가위원분야매핑 등록
							//iproEstmProgDao.insertCmtmSpheMpgItemRegist(parameterMap);
							// 화상회의 순번 MAX+1값 뽑기 VIDO_MTNG_SEQ
							int vidoMtngSeq = iproEstmProgDao.selectVidoMtngSeq(parameterMap);
							parameterMap.put("P_VIDO_MTNG_SEQ", vidoMtngSeq);
							parameterMap.put("P_ESTM_PROCD_SEQ", "1");
							// 화상회의마스터 T_ESTM_VIDO_MTNG 저장
							iproEstmProgDao.insertVidoMtng(parameterMap);
							// 평가위원화상회의참가 T_ESTM_VIDO_MTNG_CMTM 저장
							iproEstmProgDao.insertVidoMtngCmtm(parameterMap);
							// 평가대상화상회의참가  T_ESTM_VIDO_MTNG_OBJ 저장
							iproEstmProgDao.insertVidoMtngObj(parameterMap);
						}
					}
			
			}else if(P_VIDO_MTNG_PRST_SECD instanceof String[]){
				System.err.println("@@@ String[] @@@");
				
				String[] P_VIDO_MTNG_NMList = (String[]) P_VIDO_MTNG_NM;
				String[] P_VIDO_MTNG_PRST_SECDList = (String[]) P_VIDO_MTNG_PRST_SECD;
				
				String[] P_VIDO_ST_DEList = (String[]) P_VIDO_ST_DE;
				String[] P_VIDO_ST_HHList = (String[]) P_VIDO_ST_HH;
				String[] P_VIDO_ST_MMList = (String[]) P_VIDO_ST_MM;
				String[] P_VIDO_END_DEList = (String[]) P_VIDO_END_DE;
				String[] P_VIDO_END_HHList = (String[]) P_VIDO_END_HH;
				String[] P_VIDO_END_MMList = (String[]) P_VIDO_END_MM;
				
				
				for (int idx = 0; idx < P_VIDO_MTNG_PRST_SECDList.length; idx++) {
					parameterMap.put("P_VIDO_MTNG_NM", P_VIDO_MTNG_NMList[idx]);
					parameterMap.put("P_VIDO_MTNG_PRST_SECD", P_VIDO_MTNG_PRST_SECDList[idx]);
					
					//화상회의진행상태가 없을때만 insert 되도록 한다. 즉, 신규데이터만 insert
					if(parameterMap.get("P_VIDO_MTNG_PRST_SECD") == null || "".equals(parameterMap.get("P_VIDO_MTNG_PRST_SECD"))) {
						
						// 평가절차시작일시
						if(!"".equals(P_VIDO_ST_DEList[idx]) && !"".equals(P_VIDO_ST_HHList[idx]) && !"".equals(P_VIDO_ST_MMList[idx])){
							parameterMap.put("P_VIDO_ST_DT", FwkFormatUtil.formatDate(P_VIDO_ST_DEList[idx].toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_ST_HHList[idx] + P_VIDO_ST_MMList[idx] + "00"); 
						}
						
						// 평가절차종료일시
						if(!"".equals(P_VIDO_END_DEList[idx]) && !"".equals(P_VIDO_END_HHList[idx]) && !"".equals(P_VIDO_END_MMList[idx])){
							parameterMap.put("P_VIDO_END_DT", FwkFormatUtil.formatDate(P_VIDO_END_DEList[idx].toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_END_HHList[idx] + P_VIDO_END_MMList[idx] + "00"); 
						}
						
						// 평가위원분야매핑 등록
						//iproEstmProgDao.insertCmtmSpheMpgItemRegist(parameterMap);
						// 화상회의 순번 MAX+1값 뽑기 VIDO_MTNG_SEQ
						int vidoMtngSeq = iproEstmProgDao.selectVidoMtngSeq(parameterMap);
						parameterMap.put("P_VIDO_MTNG_SEQ", vidoMtngSeq);
						parameterMap.put("P_ESTM_PROCD_SEQ", "1");
						// 화상회의마스터 T_ESTM_VIDO_MTNG 저장
						iproEstmProgDao.insertVidoMtng(parameterMap);
						// 평가위원화상회의참가 T_ESTM_VIDO_MTNG_CMTM 저장
						iproEstmProgDao.insertVidoMtngCmtm(parameterMap);
						// 평가대상화상회의참가  T_ESTM_VIDO_MTNG_OBJ 저장
						iproEstmProgDao.insertVidoMtngObj(parameterMap);
					}
				}
			}
			
		}//P_ESTM_CMTM_NO != null
		
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차리스트 조회
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		
		trans.put("estmTabList", estmTabList);
		trans.put("estmMngMstDetail", estmMngMstDetail);
		trans.put("P_ESTM_NO_TRANS", parameterMap.getString("P_ESTM_NO"));
		
		return trans;
	}

	@Override
	public void cmtmMngProgEstmEnd(final FwkParameterMap parameterMap) {
		
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 2021-05-14 은잔디 : 평가위원평가진행현황 종료 시, 평가위원평가 Tab에서 선정여부 Y인 평가위원 T_ESTM_CMTM_POOL_MST SLCT_YN='Y' update 
		// 선정된 평가대상 목록 조회
		parameterMap.put("P_DEL_AT", "N");
		parameterMap.put("P_SLCT_YN_GBN", "Y");
		parameterMap.put("P_ESTM_PROCD_SEQ", "");
		parameterMap.put("P_PAGE_YN", "N");
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjValueList(parameterMap);
		
		
		if(estmObjList != null){
			
			if(estmObjList.size() > 0){
				
				for (int i = 0; i < estmObjList.size(); i++) {
					FwkDataEntity estmObjInfo = estmObjList.get(i);   // 평가대상
					
					String ESTM_CMTM_NO = estmObjInfo.getString("ESTM_CMTM_NO");
					System.err.println("@@@ ESTM_CMTM_NO ==> " + ESTM_CMTM_NO);
					
					parameterMap.put("P_ESTM_CMTM_NO", ESTM_CMTM_NO);
					iproEstmCmtmMngProgDao.updateEstmCmtmPoolSlctYn(parameterMap);   // T_ESTM_CMTM_POOL_MST
				}
			}
		}
		
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT		
		
	}
}
