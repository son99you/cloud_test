package com.eunwoosoft.ipro.estm.service.impl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.comm.util.MailSendUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.estm.dao.IproEstmProgDao;
import com.eunwoosoft.ipro.estm.service.IproEstmProgService;
import com.eunwoosoft.pki.CertificateFailException;
import com.eunwoosoft.pki.EwSignBiz;

@Service(value="iproEstmProgService")
public class IproEstmProgServiceImpl extends AbstractFwkService implements IproEstmProgService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmProgServiceImpl.class);
	
	@Resource(name="iproEstmProgDao") 
	private IproEstmProgDao iproEstmProgDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;

	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao;
	
	@Override
	public FwkTransferObject estmProgList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		
		trans.put("estmProgList", iproEstmProgDao.selectEstmProgList(parameterMap));
		trans.put("estmProgListTotCnt", iproEstmProgDao.selectEstmProgListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject estmProgListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		
		trans.put("estmProgList", iproEstmProgDao.selectEstmProgListExcelDwld(parameterMap));
	
		return trans;
	}
	
	
	@Override
	public FwkTransferObject estmProgRegist(final FwkParameterMap parameterMap) throws Exception{
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
				
				// 심사위원 서명파일 등록
				parameterMap.put("P_ESTM_FSCD", "SIGN");
				parameterMap.put("P_RMK", "");
				parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
				iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT
			}
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
	public FwkTransferObject createMeetingRoom(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// T_ESTM_VIDO_MTNG
		// T_ESTM_VIDO_MTNG_CMTM
		// T_ESTM_VIDO_MTNG_OBJ
		
		
		List<FwkDataEntity> estmVidoMtngList = iproEstmProgDao.selectEstmVidoMtngCreatMeetList(parameterMap);
		List<FwkDataEntity> estmVidoMtngCmtmList = iproEstmProgDao.selectEstmVidoMtngCmtmCreatMeetList(parameterMap);
		List<FwkDataEntity> estmVidoMtngObjList = iproEstmProgDao.selectEstmVidoMtngObjCreatMeetList(parameterMap);
		
		
		
		for(int i = 0; i < estmVidoMtngList.size(); i++) {
			
			FwkDataEntity mtngEntity = estmVidoMtngList.get(i);
			FwkParameterMap vidoMtngMap = new FwkParameterMap();
			
			vidoMtngMap.put("P_ESTM_NO", mtngEntity.get("ESTM_NO").toString());
			vidoMtngMap.put("P_ESTM_PROCD_SEQ",mtngEntity.get("ESTM_PROCD_SEQ").toString());
			vidoMtngMap.put("P_VIDO_MTNG_SEQ",mtngEntity.get("VIDO_MTNG_SEQ").toString());
			vidoMtngMap.put("P_VIDO_MTNG_PRST_SECD","V001");
			
			
			String randomPassword = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			vidoMtngMap.put("P_VIDO_ENTR_KEY", randomPassword.substring(0, 10));
			
			iproEstmProgDao.updateEstmVidoMtngEntrKey(vidoMtngMap);
		}
		
		
		
		for(int i = 0; i < estmVidoMtngCmtmList.size(); i++) {
			FwkDataEntity mtngCmtmEntity = estmVidoMtngCmtmList.get(i);
			
			FwkParameterMap vidoMtngCmtmMap = new FwkParameterMap();
			vidoMtngCmtmMap.put("P_ESTM_NO", mtngCmtmEntity.get("ESTM_NO").toString());
			vidoMtngCmtmMap.put("P_ESTM_PROCD_SEQ",mtngCmtmEntity.get("ESTM_PROCD_SEQ").toString());
			vidoMtngCmtmMap.put("P_VIDO_MTNG_SEQ",mtngCmtmEntity.get("VIDO_MTNG_SEQ").toString());
			vidoMtngCmtmMap.put("P_ESTM_CMTM_NO",mtngCmtmEntity.get("ESTM_CMTM_NO").toString());
			
			String randomPassword = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			vidoMtngCmtmMap.put("P_VIDO_ENTR_KEY", randomPassword.substring(0, 10));
			
			iproEstmProgDao.updateEstmVidoMtngCmtmEntrKey(vidoMtngCmtmMap);
		}
		
		for(int i = 0; i < estmVidoMtngObjList.size(); i++) {
			FwkDataEntity mtngObjEntity = estmVidoMtngObjList.get(i);
			
			FwkParameterMap vidoMtngObjMap = new FwkParameterMap();
			vidoMtngObjMap.put("P_ESTM_NO", mtngObjEntity.get("ESTM_NO").toString());
			vidoMtngObjMap.put("P_ESTM_PROCD_SEQ",mtngObjEntity.get("ESTM_PROCD_SEQ").toString());
			vidoMtngObjMap.put("P_VIDO_MTNG_SEQ",mtngObjEntity.get("VIDO_MTNG_SEQ").toString());
			vidoMtngObjMap.put("P_ESTM_OBJ_SEQ",mtngObjEntity.get("ESTM_OBJ_SEQ").toString());
			
			String randomPassword = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			vidoMtngObjMap.put("P_VIDO_ENTR_KEY", randomPassword.substring(0, 10));
			
			iproEstmProgDao.updateEstmVidoMtngObjEntrKey(vidoMtngObjMap);
		}
		
		// 메일 시작
		
//		FwkParameterMap mailParameterMap = new FwkParameterMap();
//		MailSendUtil mailSendUtil = new MailSendUtil();
//		
//		// 평가관리 마스터 조회
//		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
//		
//		//메일정보 조회
//		mailParameterMap.put("P_MSG_SECD", "MAIL");
//		mailParameterMap.put("P_MSG_SN", "3");
//		//parameterMap.put("P_MSG_OBJ_ID", "");
//		FwkDataEntity msgDetail = (FwkDataEntity) iproCommDefaultDao.selectMsgContents(mailParameterMap);   // T_ESTM_MNG_MST
//		String mailCntn = "";
//		String mailTitle = "";
//		
//		//메일 제목
//		mailTitle = msgDetail.getString("MSG_TTL");
//		mailTitle = mailTitle.replace("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//		mailParameterMap.put("P_MSG_TTL",mailTitle);
//		
//		
//		
//		// 다시한번 데이터들을 호출한다.
//		List<FwkDataEntity> estmVidoMtngMailList = iproEstmProgDao.selectEstmVidoMtngCreatMeetList(parameterMap);
//		
//		String ext_room_id = parameterMap.getString("P_ESTM_NO") + "-" + parameterMap.getString("P_ESTM_PROCD_SEQ") + "-" + parameterMap.getString("P_VIDO_MTNG_SEQ");
//		// 평가담당자 메일호출
//		for(int i = 0; i < estmVidoMtngMailList.size(); i++) {
//			FwkDataEntity mtngEntity = estmVidoMtngMailList.get(i);
//			//메일 수신자
//			mailParameterMap.put("P_TO_MAIL", mtngEntity.getString("EMAL"));
//			
//			String ext_user_id = mtngEntity.getString("ESTM_CHRGR_ID");
//			String ext_password = mtngEntity.getString("VIDO_ENTR_KEY");
//			String nickname = mtngEntity.getString("ESTM_CHRGR_NM");
//			
//			String LINK_URL = FwkMessageUtil.getMessage("VIDO.LINK.URL") 
//							+ "ext_room_id=" + ext_user_id
//							+ "&ext_password=" + ext_password
//							+ "&nickname=" + nickname
//							;
//			//메일 내용
//			mailCntn = msgDetail.getString("MSG_CNTN");
//			mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
//			mailCntn = mailCntn.replaceAll("#LINK_URL#", LINK_URL);
//			
//			System.out.println("mailCntn :::: " + mailCntn);
//			
//			mailParameterMap.put("P_MSG_CNTN",mailCntn);
//			
//			//메일전송
//			try{
//				mailSendUtil.sendMail(mailParameterMap);
//			}catch(Exception e){
//			}
//		}
//		
//		
//		
//		List<FwkDataEntity> estmVidoMtngCmtmMailList = iproEstmProgDao.selectEstmVidoMtngCmtmCreatMeetList(parameterMap);
//		//List<FwkDataEntity> estmVidoMtngObjMailList = iproEstmProgDao.selectEstmVidoMtngObjCreatMeetList(parameterMap);
//		
//		// 평가위원 메일호출
//		for(int i = 0; i < estmVidoMtngCmtmMailList.size(); i++) {
//			FwkDataEntity mtngCmtmEntity = estmVidoMtngCmtmMailList.get(i);
//			//메일 수신자
//			mailParameterMap.put("P_TO_MAIL", mtngCmtmEntity.getString("EMAL"));
//			
//			String ext_user_id = "CMTM-" + mtngCmtmEntity.getString("ESTM_CMTM_NO");
//			String ext_password = mtngCmtmEntity.getString("VIDO_ENTR_KEY");
//			String nickname = mtngCmtmEntity.getString("ESTM_CHRGR_NM");
//			
//			String LINK_URL = FwkMessageUtil.getMessage("VIDO.LINK.URL") 
//							+ "ext_room_id=" + ext_user_id
//							+ "&ext_password=" + ext_password
//							+ "&nickname=" + nickname
//							;
//			//메일 내용
//			mailCntn = msgDetail.getString("MSG_CNTN");
//			mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
//			mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
//			mailCntn = mailCntn.replaceAll("#LINK_URL#", LINK_URL);
//			
//			System.out.println("mailCntn :::: " + mailCntn);
//			
//			mailParameterMap.put("P_MSG_CNTN",mailCntn);
//			
//			//메일전송
//			try{
//				mailSendUtil.sendMail(mailParameterMap);
//			}catch(Exception e){
//			}
//		}
		
		
		// 메일 종료
		
		return trans;
	}

	@Override
	public FwkTransferObject estmProgDetail(final FwkParameterMap parameterMap) {
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
	public void estmDelete(final FwkParameterMap parameterMap) {

		iproEstmProgDao.updateEstmDelAt(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가삭제");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	@Override
	public void estmRsdnNoDelete(final FwkParameterMap parameterMap) {

		iproEstmProgDao.updateEstmRsdnNoDelete(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "개인정보삭제");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	

	@Override
	public FwkTransferObject estmProgObjDetail(final FwkParameterMap parameterMap) {
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
	public FwkTransferObject excelObjUpload(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		
		MultipartFile excelFile = multipartRequest.getFile("excelFileUpload"); 
		Workbook	workbook	= null; 
		Sheet		sheet		= null;
		//스트림 설정 , 스트림으로 파일을 전송
		InputStream	iStream		= null;
		String excelResultCode = "";
		iStream = excelFile.getInputStream();	// 스트림을 얻어올 수 있다. 
		workbook	= WorkbookFactory.create( iStream ); //WookbookFactory를 이용하면 처음부터 xls와 xlsx를 가리지 않는다.
		
		//List<FwkDataEntity> excelParamList =  new ArrayList<FwkDataEntity>();
		List<Map<String, Object>> excelParamList = new ArrayList<Map<String,Object>>();		//excelParamList
		
		try {
			if ( workbook != null ) { //if ( workbook
				sheet	= workbook.getSheetAt(0);
				int lastCellNum = 0;
				
				System.err.println("sheet.getFirstRowNum() ::" + sheet.getFirstRowNum());
				System.err.println("sheet.getLastRowNum() ::" + sheet.getLastRowNum());
				
				
				for(int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) { //for(int i
					Row row = sheet.getRow(i);
					
					//System.err.println("row ::" + row.toString());
					if(row != null){// row != null
						if(i==0) { //첫번째 row
							lastCellNum = row.getLastCellNum();
							if("A".equals(parameterMap.get("P_ESTM_OBJ_SECD")) && lastCellNum != 4) { // 업체인 경우
								//throw new Exception("양식이 잘못되었습니다.");
							}else if("B".equals(parameterMap.get("P_ESTM_OBJ_SECD")) && lastCellNum != 6) { // 상품인 경우
								//throw new Exception("양식이 잘못되었습니다.");
							}else if("C".equals(parameterMap.get("P_ESTM_OBJ_SECD")) && lastCellNum != 4) { // 사람인 경우
								//throw new Exception("양식이 잘못되었습니다.");
							}
							
							Cell cell = row.getCell(0);
							if("A".equals(parameterMap.get("P_ESTM_OBJ_SECD"))){
								if(!cell.getRichStringCellValue().getString().trim().equals("업체명")) {
									//throw new Exception("[업체명] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(1);
								if(!cell.getRichStringCellValue().getString().trim().equals("사업자번호")) {
									//throw new Exception("[사업자번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(2);
								if(!cell.getRichStringCellValue().getString().trim().equals("전화번호")) {
									//throw new Exception("[전화번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(3);
								if(!cell.getRichStringCellValue().getString().trim().equals("이메일")) {
									//throw new Exception("[이메일] 헤더가 일치 하지 않습니다.");
								}
							}else if("B".equals(parameterMap.get("P_ESTM_OBJ_SECD"))){
								if(!cell.getRichStringCellValue().getString().trim().equals("상품번호")) {
									//throw new Exception("[상품번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(1);
								if(!cell.getRichStringCellValue().getString().trim().equals("상품명")) {
									//throw new Exception("[상품명] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(2);
								if(!cell.getRichStringCellValue().getString().trim().equals("업체명")) {
									//throw new Exception("[업체명] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(3);
								if(!cell.getRichStringCellValue().getString().trim().equals("사업자번호")) {
									//throw new Exception("[사업자번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(4);
								if(!cell.getRichStringCellValue().getString().trim().equals("전화번호")) {
									//throw new Exception("[전화번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(5);
								if(!cell.getRichStringCellValue().getString().trim().equals("이메일")) {
									//throw new Exception("[이메일] 헤더가 일치 하지 않습니다.");
								}
							}else if("C".equals(parameterMap.get("P_ESTM_OBJ_SECD"))){
								if(!cell.getRichStringCellValue().getString().trim().equals("신청자명")) {
									//throw new Exception("[신청자명] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(1);
								if(!cell.getRichStringCellValue().getString().trim().equals("주민번호")) {
									//throw new Exception("[주민번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(2);
								if(!cell.getRichStringCellValue().getString().trim().equals("전화번호")) {
									//throw new Exception("[전화번호] 헤더가 일치 하지 않습니다.");
								}
								cell = row.getCell(3);
								if(!cell.getRichStringCellValue().getString().trim().equals("이메일")) {
									//throw new Exception("[이메일] 헤더가 일치 하지 않습니다.");
								}
							}
						}else { //if(i==0)
							
							if("A".equals(parameterMap.get("P_ESTM_OBJ_SECD"))) { //업체
								FwkParameterMap param = new FwkParameterMap();
								
								param.put("P_VEND_NM", cellValue(row.getCell(0)));
								param.put("P_BIZRNO", cellValue(row.getCell(1)));
								param.put("P_TEL_NO", cellValue(row.getCell(2)));
								param.put("P_EMAL", cellValue(row.getCell(3)));
								param.put("P_ESTM_OBJ_SEQ", i+1);
								
								excelParamList.add(param);
							}else if("B".equals(parameterMap.get("P_ESTM_OBJ_SECD"))) { // 상품
								FwkParameterMap param = new FwkParameterMap();
								
								param.put("P_ITEM_NO", cellValue(row.getCell(0)));
								param.put("P_ITEM_NM", cellValue(row.getCell(1)));
								param.put("P_VEND_NM", cellValue(row.getCell(2)));
								param.put("P_BIZRNO", cellValue(row.getCell(3)));
								param.put("P_TEL_NO", cellValue(row.getCell(4)));
								param.put("P_EMAL", cellValue(row.getCell(5)));
								param.put("P_ESTM_OBJ_SEQ", i+1);
								
								excelParamList.add(param);
							}else if("C".equals(parameterMap.get("P_ESTM_OBJ_SECD"))) { // 사람
								FwkParameterMap param = new FwkParameterMap();
								
								param.put("P_ESTM_OBJ_PE_NM", cellValue(row.getCell(0)));
								param.put("P_RSDN_NO", cellValue(row.getCell(1)));
								param.put("P_TEL_NO", cellValue(row.getCell(2)));
								param.put("P_EMAL", cellValue(row.getCell(3)));
								param.put("P_ESTM_OBJ_SEQ", i+1);
								
								
								excelParamList.add(param);
							}
							
						}//}else
					
					}// row != null
				} //for(int i
			}//if ( workbook
			
			parameterMap.put("excelParamList", excelParamList);
			
			iproEstmProgDao.deleteEstmObj(parameterMap);
			
			if("fail".equals(excelResultCode)) {
				
			}else {
				try {
					iproEstmProgDao.insertEstmObjList(parameterMap);
					excelResultCode = "success";
					
				} catch (Exception e) {
					// TODO: handle exception
					excelResultCode = "fail";
					//e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			excelResultCode = "fail";
		}
		
		
		
		
		trans.put("excelResultCode", excelResultCode);
		return trans;
	}
	
	

	@Override
	public FwkTransferObject estmProgCmtmDetail(final FwkParameterMap parameterMap) {
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
				
				System.err.println("/********** 차수에 따른 평가위원정보 조회 START **********/");
				parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_SLCT_NGR_CLICK);
				
				// 외부평가위원 조회
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
					parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
				}else{
					parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
				}
				outEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("outEstmCmtmList", outEstmCmtmList);
				
				// 지정평가위원정보 조회
				if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
					parameterMap.put("P_INO_CMTM_SECD", "OUT");
					parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력
					fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
					trans.put("fixEstmCmtmList", fixEstmCmtmList);
				}else{
					trans.put("fixEstmCmtmList", "");
				}
				
				// 내부평가위원 조회
				parameterMap.put("P_INO_CMTM_SECD", "INN");
				parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
				innEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("innEstmCmtmList", innEstmCmtmList);
				
				
				System.err.println("/********** 차수에 따른 평가위원정보 조회 END **********/");
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
			
			System.err.println("========== [최종] START ==========");
			
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
			
			System.err.println("========== [최종] END ==========");
			
		}
		
		
		return trans;
	}
	
	/**
	 * 평가자구분이 평가위원인 경우 평가페이지 호출
	 */
	
	@Override
	public FwkTransferObject estmProgProcdADetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		/*if(parameterMap.get("P_PAGE_NO") == null || "".equals(parameterMap.get("P_PAGE_NO"))) {
			parameterMap.put("P_PAGE_NO", "1");	
		}
		if(parameterMap.get("P_PAGE_SIZE") == null || "".equals(parameterMap.get("P_PAGE_SIZE"))) {
			parameterMap.put("P_PAGE_SIZE", "2");
		}*/
		
		
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
	 * 평가자구분이 평가담당인 경우 평가페이지 호출
	 */
	
	@Override
	public FwkTransferObject estmProgProcdBDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjValueList(parameterMap);
		
		// 평가담당자 조회
		List<FwkDataEntity> estmChrgrList = iproEstmProgDao.selectEstmChrgrList(parameterMap);   // T_ESTM_CMTM
		
		// 평가담당자+평가대상 목록 조회
		List<FwkDataEntity> estmChrgrValueList = iproEstmProgDao.selectEstmChrgrValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmProgDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmChrgrList", estmChrgrList);
		trans.put("estmChrgrValueList", estmChrgrValueList);
		trans.put("estmMngMstDetail", estmMngMstDetail);
		trans.put("estmTabList", estmTabList);
		
		return trans;
	}
	
	
	/**
	 * 평가구분관리 산술식 저장
	 */
	@Override
	public FwkTransferObject estmMngMstClcRulSave(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		iproEstmProgDao.updateEstmMngMstClcRulSave(parameterMap);
		
		parameterMap.put("resultCode", "Success");
		
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject estmProgResultDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
//		List<FwkDataEntity> estmSeProcdList = iproEstmProgDao.selectEstmSeProcdList(parameterMap);
//		
//		trans.put("estmSeProcdList", estmSeProcdList);
		
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
//		List<FwkParameterMap> newEstmResultObjAllList = new ArrayList<FwkParameterMap>();
//		/**
//		 * 최종결과점수 뽑아내는 로직
//		 */
//		for(int i = 0; i < estmResultObjAllList.size(); i++) {
//			FwkParameterMap param = new  FwkParameterMap();
//			FwkDataEntity objAllEntity = estmResultObjAllList.get(i);
//			param.put("P_ESTM_NO", parameterMap.get("P_ESTM_NO"));
//			param.put("P_ESTM_OBJ_SEQ", objAllEntity.get("ESTM_OBJ_SEQ"));
//			
//			List<FwkDataEntity> estmResultObjTotScrList = iproEstmProgDao.selectEstmResultObjTotScrList(param);
//			
//			String pClcRul = estmMngMstDetail.getString("CLC_RUL");
//			String[] pClcRulSplit = pClcRul.split(",");
//			String sansool = "";
//			for (int j = 0; j < pClcRulSplit.length; j++) {
//				if(pClcRulSplit[j].contains("ESTM_PROCD_SEQ")) { // 절차인경우
//					System.err.println("pClcRulSplit["+j+"] ::" + pClcRulSplit[j]);
//					String seq = pClcRulSplit[j].replace("ESTM_PROCD_SEQ", "");
//					System.err.println("seq["+j+"] ::" + seq);
//					for(int totScr = 0 ; totScr < estmResultObjTotScrList.size(); totScr++) {
//						FwkDataEntity totScrEntity = estmResultObjTotScrList.get(totScr);
//						if(seq.equals(totScrEntity.getString("ESTM_PROCD_SEQ"))) {
//							sansool += totScrEntity.getString("ESTM_TOT_SCR");
//						}
//					}
//				}else { // 산술인경우
//					sansool += pClcRulSplit[j];  
//				}
//			}
//			
//			param.put("sansool" , sansool);
//			FwkDataEntity sansoolEnti = iproEstmProgDao.selectSanSoolData(param);
//			FwkParameterMap listParam = new FwkParameterMap();
//
//			listParam.put("ESTM_NO", objAllEntity.get("ESTM_NO"));
//			listParam.put("ESTM_OBJ_SEQ", objAllEntity.get("ESTM_OBJ_SEQ"));
//			listParam.put("VEND_NM", objAllEntity.get("VEND_NM"));
//			listParam.put("ITEM_NM", objAllEntity.get("ITEM_NM"));
//			listParam.put("ESTM_CMTM_NM", objAllEntity.get("ESTM_CMTM_NM"));
//			listParam.put("ESTM_OBJ_PE_NM", objAllEntity.get("ESTM_OBJ_PE_NM"));
//			listParam.put("OBJ_NM", objAllEntity.get("OBJ_NM"));
//			listParam.put("CLC_TOT_SCR", sansoolEnti.get("SANSOOL"));
//			// estmResultObjAllList 리스트에 평가결과 담기
//			
//			newEstmResultObjAllList.add(listParam);
//			System.err.println("listParam ::: " + listParam.toString());
//		}
//		
//		
//		
//		trans.put("estmResultObjAllList", newEstmResultObjAllList);
		
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

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
		
		
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
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

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
		
		System.err.println("estmResultItemAllList :: :" + estmResultItemAllList.toString());
		
		
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject estmProgResultDetailExcelDwld(final FwkParameterMap parameterMap) {
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

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
		
		
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
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

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
	public FwkTransferObject estmProgVidoMtngDetail(final FwkParameterMap parameterMap) {
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
		
		
//		parameterMap.put("P_ESTM_PROCD_SEQ","1");
		
//		//화상회의입장가능자 목록
//		List<FwkDataEntity> estmVidoMemberList = iproEstmProgDao.selectEstmProgVidoMtngMemberList(parameterMap);   // T_ESTM_MNG_MST
//		trans.put("estmVidoMemberList", estmVidoMemberList);
		
		
		// 평가절차리스트 조회
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmProgVidoMtngSave(final FwkParameterMap parameterMap) {
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
		trans.put("P_ESTM_NO_TRANS", parameterMap.get("P_ESTM_NO"));
		return trans;
	}
	

	@Override
	public void estmPscdUpdt(final FwkParameterMap parameterMap) throws Exception {
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		 
		
		// 평가종료이면 T_ESTM_OBJ 테이블에 선정여부를 UPDATE 해준다.
		if("C001".equals(parameterMap.getString("P_ESTM_PSCD"))){
			iproEstmProgDao.updateEstmEndObjSlctYn(parameterMap);
			
			parameterMap.put("P_ESTM_FRM_NO", "SVY2021-00001");
			
			// 평가종료 시 설문조사양식 저장 T_ESTM_SVY_FRM
			iproEstmProgDao.insertEstmSvyFrm(parameterMap);   // T_ESTM_SVY_FRM INSERT
			
		}
		
		// 평가진행이력 등록
		if("C001".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "평가종료");
		}else if("C002".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "평가취소");
		}else if("A004".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "평가위원순위선정완료");
		}else if("A003".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "평가위원순위선정요청");
		}
		
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	@Override
	public FwkTransferObject estmProgUpdt(final FwkParameterMap parameterMap) throws Exception{
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
	public void estmProgObjRegist(final FwkParameterMap parameterMap) throws Exception {
		
		if(parameterMap.get("P_OBJ_YN") != null) {
			
			Object P_OBJ_YN = parameterMap.get("P_OBJ_YN");   // 평가대상 별 저장데이터가 달라서 대상여부로 판단
			Object P_DEL_AT = parameterMap.get("P_DEL_AT");
			Object P_ESTM_OBJ_SEQ = parameterMap.get("P_ESTM_OBJ_SEQ");
			Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
			Object P_BIZRNO = parameterMap.get("P_BIZRNO");
			Object P_VEND_NM = parameterMap.get("P_VEND_NM");
			Object P_ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object P_ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
			Object P_ESTM_CMTM_NM = parameterMap.get("P_ESTM_CMTM_NM");
			Object P_ESTM_OBJ_PE_NO = parameterMap.get("P_ESTM_OBJ_PE_NO");
			Object P_ESTM_OBJ_PE_NM = parameterMap.get("P_ESTM_OBJ_PE_NM");
			Object P_EMPL_NO = parameterMap.get("P_EMPL_NO");
			Object P_RSDN_NO_1 = parameterMap.get("P_RSDN_NO_1");
			Object P_RSDN_NO_2 = parameterMap.get("P_RSDN_NO_2");
			Object P_TEL_NO = parameterMap.get("P_TEL_NO");
			Object P_EMAL = parameterMap.get("P_EMAL");

			if(P_OBJ_YN != null){
				
				if(P_OBJ_YN instanceof String){
					
					if(parameterMap.get("P_ESTM_OBJ_SEQ").equals("")) {
						// 평가대상 등록
						
						parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
						System.err.println("@@@ T_ESTM_OBJ 등록 @@@");
						
						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
					}else{
						parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						
						System.err.println("@@@ T_ESTM_OBJ 삭제 @@@");
						//iproEstmProgDao.updateEstmObjDelAt(parameterMap);   // T_ESTM_OBJ INSERT
					}
					
				}else if(P_OBJ_YN instanceof ArrayList){
				
					ArrayList<String> objYnList = (ArrayList<String>) P_OBJ_YN;
					ArrayList<String> delAtList = (ArrayList<String>) P_DEL_AT;
					ArrayList<String> estmObjSeqList = (ArrayList<String>) P_ESTM_OBJ_SEQ;
					ArrayList<String> vendRegNoList = (ArrayList<String>) P_VEND_REG_NO;
					ArrayList<String> bizrnoList = (ArrayList<String>) P_BIZRNO;
					ArrayList<String> vendNmList = (ArrayList<String>) P_VEND_NM;
					ArrayList<String> itemNoList = (ArrayList<String>) P_ITEM_NO;
					ArrayList<String> itemNmList = (ArrayList<String>) P_ITEM_NM;
					ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
					ArrayList<String> estmCmtmNmList = (ArrayList<String>) P_ESTM_CMTM_NM;
					ArrayList<String> estmObjPeNoList = (ArrayList<String>) P_ESTM_OBJ_PE_NO;
					ArrayList<String> estmObjPeNmList = (ArrayList<String>) P_ESTM_OBJ_PE_NM;
					ArrayList<String> emplNoList = (ArrayList<String>) P_EMPL_NO;
					ArrayList<String> rsdnNo_1_List = (ArrayList<String>) P_RSDN_NO_1;
					ArrayList<String> rsdnNo_2_List = (ArrayList<String>) P_RSDN_NO_2;
					ArrayList<String> telNoList = (ArrayList<String>) P_TEL_NO;
					ArrayList<String> emalList = (ArrayList<String>) P_EMAL;
					
					System.err.println("@@@ objYnList.size() ==> " + objYnList.size());
					for(int i =0; i < objYnList.size(); i++){
						
						String estmObjSeq = estmObjSeqList.get(i);
						
						if(objYnList != null){
							if(vendRegNoList != null) parameterMap.put("P_VEND_REG_NO", vendRegNoList.get(i));
							if(bizrnoList != null) parameterMap.put("P_BIZRNO", bizrnoList.get(i));
							if(vendNmList != null) parameterMap.put("P_VEND_NM", vendNmList.get(i));
							if(itemNoList != null) parameterMap.put("P_ITEM_NO", itemNoList.get(i));
							if(itemNmList != null) parameterMap.put("P_ITEM_NM", itemNmList.get(i));
							if(estmCmtmNoList != null) parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));
							if(estmCmtmNmList != null) parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmList.get(i));
							if(estmObjPeNoList != null) parameterMap.put("P_ESTM_OBJ_PE_NO", estmObjPeNoList.get(i));
							if(estmObjPeNmList != null) parameterMap.put("P_ESTM_OBJ_PE_NM", estmObjPeNmList.get(i));
							if(emplNoList != null) parameterMap.put("P_EMPL_NO", emplNoList.get(i));

							if(rsdnNo_1_List != null && rsdnNo_2_List != null)
								parameterMap.put("P_RSDN_NO", rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));						
							
							if(telNoList != null) parameterMap.put("P_TEL_NO", telNoList.get(i));
							if(emalList != null) parameterMap.put("P_EMAL", emalList.get(i));
							
							System.err.println("@@@ estmObjSeq ==> " + estmObjSeq);
							if(estmObjSeq.equals("")){
								// 평가대상 등록
								System.err.println("@@@ T_ESTM_OBJ 등록 @@@");
								iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
							}else{
								parameterMap.put("P_ESTM_OBJ_SEQ", estmObjSeqList.get(i));
								parameterMap.put("P_DEL_AT", delAtList.get(i));
								
								System.err.println("@@@ T_ESTM_OBJ 삭제 @@@");
								//iproEstmProgDao.updateEstmObjDelAt(parameterMap);   // T_ESTM_OBJ INSERT
							}
						}
					}
				}
//				else if(P_OBJ_YN instanceof String[]){
//					
//					String[] objYnStr = (String[]) P_OBJ_YN;
//					String[] vendRegNoStr = (String[]) P_VEND_REG_NO;
//					String[] bizrnoStr = (String[]) P_BIZRNO;
//					String[] vendNmStr = (String[]) P_VEND_NM;
//					String[] itemNoStr = (String[]) P_ITEM_NO;
//					String[] itemNmStr = (String[]) P_ITEM_NM;
//					String[] estmCmtmNoStr = (String[]) P_ESTM_CMTM_NO;
//					String[] estmCmtmNmStr = (String[]) P_ESTM_CMTM_NM;
//					String[] estmObjPeNoStr = (String[]) P_ESTM_OBJ_PE_NO;
//					String[] estmObjPeNmStr = (String[]) P_ESTM_OBJ_PE_NM;
//					String[] emplNoStr = (String[]) P_EMPL_NO;
//					String[] rsdnNoStr = (String[]) P_RSDN_NO;
//					String[] telNoStr = (String[]) P_TEL_NO;
//					String[] emalStr = (String[]) P_EMAL;
//					String[] fileGrpNoStr = (String[]) P_FILE_GRP_NO;
//					
//					System.err.println("@@@ objYnStr.length ==> " + objYnStr.length);
//					for (int i = 0; i < objYnStr.length; i++) {
//						
//						parameterMap.put("P_VEND_REG_NO", vendRegNoStr[i]);
//						parameterMap.put("P_BIZRNO", bizrnoStr[i]);
//						parameterMap.put("P_VEND_NM", vendNmStr[i]);
//						parameterMap.put("P_ITEM_NO", itemNoStr[i]);
//						parameterMap.put("P_ITEM_NM", itemNmStr[i]);
//						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoStr[i]);
//						parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmStr[i]);
//						parameterMap.put("P_ESTM_OBJ_PE_NO", estmObjPeNoStr[i]);
//						parameterMap.put("P_ESTM_OBJ_PE_NM", estmObjPeNmStr[i]);
//						parameterMap.put("P_EMPL_NO", emplNoStr[i]);
//						parameterMap.put("P_RSDN_NO", rsdnNoStr[i]);
//						parameterMap.put("P_TEL_NO", telNoStr[i]);
//						parameterMap.put("P_EMAL", emalStr[i]);
//						parameterMap.put("P_FILE_GRP_NO", fileGrpNoStr[i]);
//						
//						// 평가대상 등록
//						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT
//					}
//				}
			}
		}
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상 저장");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	@Override
	public void estmProgObjDelete(final FwkParameterMap parameterMap) throws Exception {
		
		if(parameterMap.get("P_OBJ_YN") != null) {
			
			Object P_OBJ_YN = parameterMap.get("P_OBJ_YN");   // 평가대상 별 저장데이터가 달라서 대상여부로 판단
			Object P_DEL_AT = parameterMap.get("P_DEL_AT");
			Object P_ESTM_OBJ_SEQ = parameterMap.get("P_ESTM_OBJ_SEQ");
			Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
			Object P_BIZRNO = parameterMap.get("P_BIZRNO");
			Object P_VEND_NM = parameterMap.get("P_VEND_NM");
			Object P_ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object P_ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
			Object P_ESTM_CMTM_NM = parameterMap.get("P_ESTM_CMTM_NM");
			Object P_ESTM_OBJ_PE_NO = parameterMap.get("P_ESTM_OBJ_PE_NO");
			Object P_ESTM_OBJ_PE_NM = parameterMap.get("P_ESTM_OBJ_PE_NM");
			Object P_EMPL_NO = parameterMap.get("P_EMPL_NO");
			Object P_RSDN_NO_1 = parameterMap.get("P_RSDN_NO_1");
			Object P_RSDN_NO_2 = parameterMap.get("P_RSDN_NO_2");
			Object P_TEL_NO = parameterMap.get("P_TEL_NO");
			Object P_EMAL = parameterMap.get("P_EMAL");

			if(P_OBJ_YN != null){
				
				if(P_OBJ_YN instanceof String){
					
					if(parameterMap.get("P_ESTM_OBJ_SEQ").equals("")) {
						// 평가대상 등록
						
						parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
						System.err.println("@@@ T_ESTM_OBJ 등록 @@@");
						
						//iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
					}else{
						parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						
						System.err.println("@@@ T_ESTM_OBJ 삭제 @@@");
						iproEstmProgDao.updateEstmObjDelAt(parameterMap);   // T_ESTM_OBJ INSERT
					}
					
				}else if(P_OBJ_YN instanceof ArrayList){
				
					ArrayList<String> objYnList = (ArrayList<String>) P_OBJ_YN;
					ArrayList<String> delAtList = (ArrayList<String>) P_DEL_AT;
					ArrayList<String> estmObjSeqList = (ArrayList<String>) P_ESTM_OBJ_SEQ;
					ArrayList<String> vendRegNoList = (ArrayList<String>) P_VEND_REG_NO;
					ArrayList<String> bizrnoList = (ArrayList<String>) P_BIZRNO;
					ArrayList<String> vendNmList = (ArrayList<String>) P_VEND_NM;
					ArrayList<String> itemNoList = (ArrayList<String>) P_ITEM_NO;
					ArrayList<String> itemNmList = (ArrayList<String>) P_ITEM_NM;
					ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
					ArrayList<String> estmCmtmNmList = (ArrayList<String>) P_ESTM_CMTM_NM;
					ArrayList<String> estmObjPeNoList = (ArrayList<String>) P_ESTM_OBJ_PE_NO;
					ArrayList<String> estmObjPeNmList = (ArrayList<String>) P_ESTM_OBJ_PE_NM;
					ArrayList<String> emplNoList = (ArrayList<String>) P_EMPL_NO;
					ArrayList<String> rsdnNo_1_List = (ArrayList<String>) P_RSDN_NO_1;
					ArrayList<String> rsdnNo_2_List = (ArrayList<String>) P_RSDN_NO_2;
					ArrayList<String> telNoList = (ArrayList<String>) P_TEL_NO;
					ArrayList<String> emalList = (ArrayList<String>) P_EMAL;
					
					System.err.println("@@@ objYnList.size() ==> " + objYnList.size());
					for(int i =0; i < objYnList.size(); i++){
						
						String estmObjSeq = estmObjSeqList.get(i);
						
						if(objYnList != null){
							if(vendRegNoList != null) parameterMap.put("P_VEND_REG_NO", vendRegNoList.get(i));
							if(bizrnoList != null) parameterMap.put("P_BIZRNO", bizrnoList.get(i));
							if(vendNmList != null) parameterMap.put("P_VEND_NM", vendNmList.get(i));
							if(itemNoList != null) parameterMap.put("P_ITEM_NO", itemNoList.get(i));
							if(itemNmList != null) parameterMap.put("P_ITEM_NM", itemNmList.get(i));
							if(estmCmtmNoList != null) parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));
							if(estmCmtmNmList != null) parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmList.get(i));
							if(estmObjPeNoList != null) parameterMap.put("P_ESTM_OBJ_PE_NO", estmObjPeNoList.get(i));
							if(estmObjPeNmList != null) parameterMap.put("P_ESTM_OBJ_PE_NM", estmObjPeNmList.get(i));
							if(emplNoList != null) parameterMap.put("P_EMPL_NO", emplNoList.get(i));

							if(rsdnNo_1_List != null && rsdnNo_2_List != null)
								parameterMap.put("P_RSDN_NO", rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));						
							
							if(telNoList != null) parameterMap.put("P_TEL_NO", telNoList.get(i));
							if(emalList != null) parameterMap.put("P_EMAL", emalList.get(i));
							
							System.err.println("@@@ estmObjSeq ==> " + estmObjSeq);
							if(estmObjSeq.equals("")){
								// 평가대상 등록
								System.err.println("@@@ T_ESTM_OBJ 등록 @@@");
								//iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
							}else{
								parameterMap.put("P_ESTM_OBJ_SEQ", estmObjSeqList.get(i));
								parameterMap.put("P_DEL_AT", delAtList.get(i));
								
								System.err.println("@@@ T_ESTM_OBJ 삭제 @@@");
								iproEstmProgDao.updateEstmObjDelAt(parameterMap);   // T_ESTM_OBJ INSERT
							}
						}
					}
				}
//				else if(P_OBJ_YN instanceof String[]){
//					
//					String[] objYnStr = (String[]) P_OBJ_YN;
//					String[] vendRegNoStr = (String[]) P_VEND_REG_NO;
//					String[] bizrnoStr = (String[]) P_BIZRNO;
//					String[] vendNmStr = (String[]) P_VEND_NM;
//					String[] itemNoStr = (String[]) P_ITEM_NO;
//					String[] itemNmStr = (String[]) P_ITEM_NM;
//					String[] estmCmtmNoStr = (String[]) P_ESTM_CMTM_NO;
//					String[] estmCmtmNmStr = (String[]) P_ESTM_CMTM_NM;
//					String[] estmObjPeNoStr = (String[]) P_ESTM_OBJ_PE_NO;
//					String[] estmObjPeNmStr = (String[]) P_ESTM_OBJ_PE_NM;
//					String[] emplNoStr = (String[]) P_EMPL_NO;
//					String[] rsdnNoStr = (String[]) P_RSDN_NO;
//					String[] telNoStr = (String[]) P_TEL_NO;
//					String[] emalStr = (String[]) P_EMAL;
//					String[] fileGrpNoStr = (String[]) P_FILE_GRP_NO;
//					
//					System.err.println("@@@ objYnStr.length ==> " + objYnStr.length);
//					for (int i = 0; i < objYnStr.length; i++) {
//						
//						parameterMap.put("P_VEND_REG_NO", vendRegNoStr[i]);
//						parameterMap.put("P_BIZRNO", bizrnoStr[i]);
//						parameterMap.put("P_VEND_NM", vendNmStr[i]);
//						parameterMap.put("P_ITEM_NO", itemNoStr[i]);
//						parameterMap.put("P_ITEM_NM", itemNmStr[i]);
//						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoStr[i]);
//						parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmStr[i]);
//						parameterMap.put("P_ESTM_OBJ_PE_NO", estmObjPeNoStr[i]);
//						parameterMap.put("P_ESTM_OBJ_PE_NM", estmObjPeNmStr[i]);
//						parameterMap.put("P_EMPL_NO", emplNoStr[i]);
//						parameterMap.put("P_RSDN_NO", rsdnNoStr[i]);
//						parameterMap.put("P_TEL_NO", telNoStr[i]);
//						parameterMap.put("P_EMAL", emalStr[i]);
//						parameterMap.put("P_FILE_GRP_NO", fileGrpNoStr[i]);
//						
//						// 평가대상 등록
//						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT
//					}
//				}
			}
		}
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상 삭제");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	

	@Override
	public void estmCntcObjList(final FwkParameterMap parameterMap) {
		
		// 평가대상 삭제
		iproEstmProgDao.deleteEstmObj(parameterMap);   // T_ESTM_OBJ DELETE

		// 평가공고참여정보_인터페이스 조회 -> T_ESTM_OBJ INSERT
		iproEstmProgDao.insertEstmCntcObj(parameterMap);   // V_ESTM_ANNC_TPI_INT INSERT
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상불러오기");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
	}

	@Override
	public void estmObjFileUpdt(final FwkParameterMap parameterMap) throws Exception {
		
		iproEstmProgDao.updateEstmObjFileGrpNo(parameterMap);   // T_ESTM_OBJ UPDATE
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가대상 첨부파일 수정");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	@Override
	public void estmVidoFileUpdt(final FwkParameterMap parameterMap) throws Exception {
		
		iproEstmProgDao.updateEstmVidoFileGrpNo(parameterMap);   // T_ESTM_OBJ UPDATE
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "화상회의 첨부파일 수정");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	

	@Override
	public void estmProcdPscdUpdt(final FwkParameterMap parameterMap) throws Exception {
		iproEstmProgDao.updateEstmProcdPscd(parameterMap);   // T_ESTM_PROCD UPDATE
		
		String P_ESTM_PROCD_NM = parameterMap.getString("P_ESTM_PROCD_NM");   // 평가절차명
		
		// 평가진행이력 등록
		if("B001".equals(parameterMap.getString("P_ESTM_PROCD_PSCD"))){
			parameterMap.put("P_RMK", "[ " + P_ESTM_PROCD_NM + " ] 평가진행");
		}else if("B002".equals(parameterMap.getString("P_ESTM_PROCD_PSCD"))){
			parameterMap.put("P_RMK", "[ " + P_ESTM_PROCD_NM + " ] 평가완료");
		}
		
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
		/**
		 * 2021-05-04
		 * 손연우
		 * 평가완료시 평가대상선정 등록을 위한 코딩
		 */
		if("B002".equals(parameterMap.getString("P_ESTM_PROCD_PSCD"))){
			FwkDataEntity thisProcdEntity = iproEstmProgDao.selectEstmProcdSecdDetail(parameterMap);
			
			if(!"C".equals(thisProcdEntity.getString("ESTM_PROCD_SECD"))) {
				
				/**
				 * 결과총점 넣어주기
				 */
				// 마스터에서 최고죄저점제외여부 가져오기
				FwkDataEntity mxmnScrExcpYnData = iproEstmProgDao.selectMxMnScrExcpYnColumn(parameterMap);
				
				// 평가자구분 A:평가위원, B:평가담당
				// 평가자가 평가담당자인 경우에는 MXMN_SCR_EXCP_YN여부에 상관없이 N 세팅
				if("A".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", mxmnScrExcpYnData.getString("MXMN_SCR_EXCP_YN"));
				}else if("B".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", "N");
				}
					
				// T_ESTM_OBJ_SLCT 삭제
				iproEstmProgDao.deleteEstmObjSlct(parameterMap);
				
				// T_ESTM_OBJ_SLCT 등록
				iproEstmProgDao.insertEstmObjSlct(parameterMap);
				
				// 등록된 데이터를 뽑아낸다.
				List<FwkDataEntity> estmObjSlctList = iproEstmProgDao.selectEstmObjSlctList(parameterMap);
				
				for(int i = 0; i < estmObjSlctList.size(); i++) {
					FwkDataEntity objSlctDataEntity = estmObjSlctList.get(i);
					parameterMap.put("P_ESTM_OBJ_SEQ", objSlctDataEntity.getString("ESTM_OBJ_SEQ"));
					parameterMap.put("P_ESTM_RNK", objSlctDataEntity.getString("RANK"));
					
					iproEstmProgDao.updateEstmObjSlctRankUpdt(parameterMap);
				}
				
				// 현재 평가절차 선정
				parameterMap.put("P_SLCT_YN", "Y");
				iproEstmProgDao.updateEstmObjSlctYn(parameterMap);


				// 다음차수가 존재하는지 확인
				FwkDataEntity nextProcdSeqEnt =  iproEstmProgDao.selectEstmNextProcdSeqAt(parameterMap);
				
				/**
				 * 평가절차에 평가절차구분코드가 적격이 아닌 경우에는
				 * 기존에 평가한 평가대상선정테이블의 데이터를 자동으로 다음절차를 진행 할 수 있도록 insert해준다.
				 * 
				 */
				if(!"0".equals(nextProcdSeqEnt.get("CNT"))) {
					
					iproEstmProgDao.deleteEstmObjNextProcdSeq(parameterMap);
					// 다음차수가 존재하면 다음차수의 평가대상을 insert 해준다.		
					iproEstmProgDao.insertEstmObjNextProcdSeq(parameterMap);
				}
			}else {
				/**
				 * 결과총점 넣어주기
				 */
				// 마스터에서 최고죄저점제외여부 가져오기
				FwkDataEntity mxmnScrExcpYnData = iproEstmProgDao.selectMxMnScrExcpYnColumn(parameterMap);
				
				// 평가자구분 A:평가위원, B:평가담당
				// 평가자가 평가담당자인 경우에는 MXMN_SCR_EXCP_YN여부에 상관없이 N 세팅
				if("A".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", mxmnScrExcpYnData.getString("MXMN_SCR_EXCP_YN"));
				}else if("B".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", "N");
				}
					
				// T_ESTM_OBJ_SLCT 삭제
				iproEstmProgDao.deleteEstmObjSlct(parameterMap);
				
				// T_ESTM_OBJ_SLCT 등록
				iproEstmProgDao.insertEstmObjSlct(parameterMap);
				
				// 등록된 데이터를 뽑아낸다.
				List<FwkDataEntity> estmObjSlctList = iproEstmProgDao.selectEstmObjSlctList(parameterMap);
				
				for(int i = 0; i < estmObjSlctList.size(); i++) {
					FwkDataEntity objSlctDataEntity = estmObjSlctList.get(i);
					parameterMap.put("P_ESTM_OBJ_SEQ", objSlctDataEntity.getString("ESTM_OBJ_SEQ"));
					parameterMap.put("P_ESTM_RNK", objSlctDataEntity.getString("RANK"));
					
					iproEstmProgDao.updateEstmObjSlctRankUpdt(parameterMap);
				}
			}
		}
		
		
	}

	@Override
	public FwkTransferObject getEstmSpheSecd(final FwkParameterMap parameterMap) throws Exception {
		
		FwkTransferObject transferObject = new FwkTransferObject();

		parameterMap.put("P_DEL_AT", "N");
		
		List<FwkDataEntity> estmSpheSecdList = iproEstmProgDao.selectEstmSpheSecdList(parameterMap);   // T_ESTM_SPHE_MST
		
        List<Map<String, Object>> temp = new ArrayList<>();
        for(int i=0; i<estmSpheSecdList.size(); i++) {
        	Map<String, Object> secdMap = new HashMap<>();	//NOPMD
        	secdMap.put("ESTM_SPHE_SECD", estmSpheSecdList.get(i).get("ESTM_SPHE_SECD"));
        	if (estmSpheSecdList.get(i).get("ESTM_SPHE_SENM") != null) {
        		secdMap.put("ESTM_SPHE_SENM", FwkStringUtil.fmIso2Euc((String) estmSpheSecdList.get(i).get("ESTM_SPHE_SENM")));
        	}
        	temp.add(secdMap);
        }
        
        transferObject.put("listEstmSpheSecd", temp);
        
		return transferObject;
	}

	@Override
	public void estmCmtmSlctYnUpdt(final FwkParameterMap parameterMap) {

		iproEstmProgDao.updateEstmCmtmSlctYn(parameterMap);   // T_ESTM_CMTM UPDATE
		
	}

	@Override
	public void estmCmtmAutoSlct(final FwkParameterMap parameterMap) {
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		
		// ESTM_CMTM_SLCT_MTHD A : 자체선정
		// ESTM_CMTM_SLCT_MTHD B	 : 우선순위
		// ESTM_CMTM_SLCT_MTHD C	 : 자체선정+우선순위

		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		String INN_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("INN_CMTM_SLCT_MTHD_SECD");   // 내부평가위원선정방법
		
		int OUT_ESTM_CMTM_CNT = estmMngMstDetail.getInt("OUT_ESTM_CMTM_CNT");   // 외부평가위원수
		int OUT_ESTM_CMTM_TMES = estmMngMstDetail.getInt("OUT_ESTM_CMTM_TMES");   // 외부평가위원배수
		
		int FIX_ESTM_CMTM_CNT = estmMngMstDetail.getInt("FIX_ESTM_CMTM_CNT");   // 지정평가위원수
		
		int INN_ESTM_CMTM_CNT = estmMngMstDetail.getInt("INN_ESTM_CMTM_CNT");   // 내부평가위원수
		int INN_ESTM_CMTM_TMES = estmMngMstDetail.getInt("INN_ESTM_CMTM_TMES");   // 내부평가위원배수
		
		
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ 외부평가위원수 ==> " + OUT_ESTM_CMTM_CNT);
		System.err.println("@@@ 외부평가위원배수 ==> " + OUT_ESTM_CMTM_TMES);
		
		System.err.println("@@@ 지정평가위원수 ==> " + FIX_ESTM_CMTM_CNT);
		
		System.err.println("@@@ 내부평가위원선정방법 ==> " + INN_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ 내부평가위원수 ==> " + INN_ESTM_CMTM_CNT);
		System.err.println("@@@ 내부평가위원배수 ==> " + INN_ESTM_CMTM_TMES);
		
		int OUT_SLCT_CNT = 0;
		int INN_SLCT_CNT = INN_ESTM_CMTM_CNT * INN_ESTM_CMTM_TMES;   // 내부평가위원수 * 내부평가위원배수
		
		// 평가위원수의 배수만큼 Random으로 조회하여 insert
		parameterMap.put("P_ESTM_SPHE_SECD", estmMngMstDetail.get("ESTM_SPHE_SECD"));   // 평가분야코드
		
		if("OUT".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE
			
			
			if("B".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // 우선순위
				
				OUT_SLCT_CNT = OUT_ESTM_CMTM_CNT * OUT_ESTM_CMTM_TMES;   // 외부평가위원수 * 외부평가위원배수
				System.err.println("@@@ (우선순위) 외부선정위원수 ==> " + OUT_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "OUT"); 
				parameterMap.put("P_SLCT_CNT", OUT_SLCT_CNT);   // 외부평가위원선정 랜덤 
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // 외부평가위원 - T_ESTM_CMTM INSERT
			}
			
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // 자체선정+우선순위
				
				OUT_SLCT_CNT = ( OUT_ESTM_CMTM_CNT - FIX_ESTM_CMTM_CNT ) * OUT_ESTM_CMTM_TMES;
				
				System.err.println("@@@ (자체선정+우선순위) 외부선정위원수 ==> " + OUT_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "OUT"); 
				parameterMap.put("P_SLCT_CNT", OUT_SLCT_CNT);   // 외부평가위원선정 랜덤 
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // 외부평가위원 - T_ESTM_CMTM INSERT
			}
		}
		
		if("INN".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE
			if(!"A".equals(INN_CMTM_SLCT_MTHD_SECD)){
				System.err.println("@@@ 내부선정위원수 ==> " + INN_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "INN");
				parameterMap.put("P_SLCT_CNT", INN_SLCT_CNT);   // 내부평가위원선정 랜덤
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // 내부평가위원 - T_ESTM_CMTM INSERT
			}
		}
				
		parameterMap.put("P_RMK", "자동선별");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	
	@Override
	public void estmCmtmReAutoSlct(final FwkParameterMap parameterMap) {
		
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ESTM_CMTM_SLCT_MTHD A : 자체선정
		// ESTM_CMTM_SLCT_MTHD B	 : 우선순위
		// ESTM_CMTM_SLCT_MTHD C	 : 자체선정+우선순위
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		String INN_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("INN_CMTM_SLCT_MTHD_SECD");   // 내부평가위원선정방법
		
		
		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD_S", "OUT");
		parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
		parameterMap.put("P_SLCT_SECD", "");
//		parameterMap.put("P_ESTM_CMTM_RE_SLCT_NGR", parameterMap.getInt("P_ESTM_CMTM_RE_SLCT_NGR"));   //
//		List<FwkDataEntity> outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		
		List<FwkDataEntity> outEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
		
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD_S", "INN");
//		List<FwkDataEntity> innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
		parameterMap.put("P_SLCT_SECD", "");
		List<FwkDataEntity> innEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
		
		
		int OUT_ESTM_CMTM_CNT = estmMngMstDetail.getInt("OUT_ESTM_CMTM_CNT");   // 외부평가위원수
		int OUT_ESTM_CMTM_TMES = estmMngMstDetail.getInt("OUT_ESTM_CMTM_TMES");   // 외부평가위원배수
		int OUT_CMTM_SLCT_CNT = estmMngMstDetail.getInt("OUT_CMTM_SLCT_CNT");   // 선정된 외부평가위원수
		
		int INN_ESTM_CMTM_CNT = estmMngMstDetail.getInt("INN_ESTM_CMTM_CNT");   // 내부평가위원수
		int INN_ESTM_CMTM_TMES = estmMngMstDetail.getInt("INN_ESTM_CMTM_TMES");   // 내부평가위원배수
		int INN_CMTM_SLCT_CNT = estmMngMstDetail.getInt("INN_CMTM_SLCT_CNT");   // 선정된 내부평가위원수
		
		int FIX_ESTM_CMTM_CNT = estmMngMstDetail.getInt("FIX_ESTM_CMTM_CNT");   // 지정평가위원수
		
		int P_ESTM_CMTM_RE_SLCT_NGR = parameterMap.getInt("P_ESTM_CMTM_RE_SLCT_NGR");   // 우선순위재선정할 평가위원선정차수
		
		System.err.println("@@@ 우선순위재선정할 평가위원선정차수 ==> " + P_ESTM_CMTM_RE_SLCT_NGR);
		
		
		
		parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_RE_SLCT_NGR);
		
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ 외부평가위원수 ==> " + OUT_ESTM_CMTM_CNT);
		System.err.println("@@@ 외부평가위원배수 ==> " + OUT_ESTM_CMTM_TMES);
		System.err.println("@@@ 선정된 외부평가위원수 ==> " + OUT_CMTM_SLCT_CNT);
		
		System.err.println("@@@ 내부평가위원선정방법 ==> " + INN_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ 내부평가위원수 ==> " + INN_ESTM_CMTM_CNT);
		System.err.println("@@@ 내부평가위원배수 ==> " + INN_ESTM_CMTM_TMES);
		System.err.println("@@@ 선정된 내부평가위원수 ==> " + INN_CMTM_SLCT_CNT);
		
		System.err.println("@@@ 지정평가위원수 ==> " + FIX_ESTM_CMTM_CNT);
		
		int OUT_SLCT_CNT = 0;
		int INN_SLCT_CNT = 0;
		
		
		// ( 평가위원 수 - 선정된 평가위원 수 ) * 평가위원배수 만큼 Random으로 조회하여 insert
		parameterMap.put("P_ESTM_SPHE_SECD", estmMngMstDetail.get("ESTM_SPHE_SECD"));   // 평가분야코드
		
		if("OUT".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE

			
			OUT_SLCT_CNT = (OUT_ESTM_CMTM_CNT - OUT_CMTM_SLCT_CNT) * OUT_ESTM_CMTM_TMES;   // ( 평가위원 수 - 선정된 평가위원 수 ) * 평가위원배수
			
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			parameterMap.put("P_SLCT_CNT", OUT_SLCT_CNT);   // 외부평가위원선정 랜덤
			
			if("B".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // 우선순위
				System.err.println("@@@ (우선순위) 외부선정위원수 ==> " + OUT_SLCT_CNT);	
			}else if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // 자체선정 + 우선순위
				System.err.println("@@@ (자체선정 + 우선순위) 외부선정위원수 ==> " + OUT_SLCT_CNT);
			}
			
			
			ArrayList<String> estmCmtmNoNotInList = new ArrayList<String>();
			for (int i = 0; i < outEstmCmtmList.size(); i++) {
				String ESTM_CMTM_NO = outEstmCmtmList.get(i).getString("ESTM_CMTM_NO");
				estmCmtmNoNotInList.add(ESTM_CMTM_NO);
			}
			parameterMap.put("P_ESTM_CMTM_NO_NOT_IN_LIST", estmCmtmNoNotInList);
			
			System.err.println("@@@ 선정/비선정 외부평가위원번호 ::: " + estmCmtmNoNotInList.toString());
			
			iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // 외부평가위원 - T_ESTM_CMTM INSERT
			
		}
		
		if("INN".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE
			
			if("B".equals(INN_CMTM_SLCT_MTHD_SECD)){   // 우선순위
				
				INN_SLCT_CNT = (INN_ESTM_CMTM_CNT - INN_CMTM_SLCT_CNT) * INN_ESTM_CMTM_TMES;   // ( 평가위원 수 - 선정된 평가위원 수 ) * 평가위원배수
				System.err.println("@@@ (우선순위) 내부선정위원수 ==> " + INN_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "INN"); 
				parameterMap.put("P_SLCT_CNT", INN_SLCT_CNT);   // 외부평가위원선정 랜덤
				
				ArrayList<String> estmCmtmNoNotInList = new ArrayList<String>();
				for (int i = 0; i < innEstmCmtmList.size(); i++) {
					String ESTM_CMTM_NO = innEstmCmtmList.get(i).getString("ESTM_CMTM_NO");
					estmCmtmNoNotInList.add(ESTM_CMTM_NO);
				}
				parameterMap.put("P_ESTM_CMTM_NO_NOT_IN_LIST", estmCmtmNoNotInList);
				
				System.err.println("@@@ 선정/비선정 내부평가위원번호 ::: " + estmCmtmNoNotInList.toString());
				
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // 내부평가위원 - T_ESTM_CMTM INSERT
			}
		}
				
		parameterMap.put("P_RMK", "평가위원순위재선정");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public FwkTransferObject estmCmtmSetDetail(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity estmCmtmSetDetail = (FwkDataEntity) iproEstmProgDao.selectEstmCmtmSetDetail(parameterMap);
		
		if(estmCmtmSetDetail != null){
			trans.put("resultCode", 1);
		}else{
			trans.put("resultCode", 0);
		}
		
		trans.put("estmCmtmSetDetail", estmCmtmSetDetail);
		
		return trans;
	}

	@Override
	public void estmCmtmRegist(final FwkParameterMap parameterMap) {
		
		Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
		Object P_ESTM_CMTM_NM = parameterMap.get("P_ESTM_CMTM_NM");
		Object P_INO_CMTM_SENM = parameterMap.get("P_INO_CMTM_SENM");
		Object P_ESTM_SPHE_SECD = parameterMap.get("P_ESTM_SPHE_SECD");
		Object P_ESTM_SPHE_SENM = parameterMap.get("P_ESTM_SPHE_SENM");
		Object P_LLF_SECD = parameterMap.get("P_LLF_SECD");
		Object P_LLF_NM = parameterMap.get("P_LLF_NM");
		Object P_CNTN_SECD = parameterMap.get("P_CNTN_SECD");
		Object P_CNTN_NM = parameterMap.get("P_CNTN_NM");
		Object P_MLF_SECD = parameterMap.get("P_MLF_SECD");
		Object P_MLF_NM = parameterMap.get("P_MLF_NM");
		Object P_SLF_SECD = parameterMap.get("P_SLF_SECD");
		Object P_SLF_NM = parameterMap.get("P_SLF_NM");
		Object P_CP_NO = parameterMap.get("P_CP_NO");
		Object P_EMAL = parameterMap.get("P_EMAL");
		Object P_LINE_TYP_SECD = parameterMap.get("P_LINE_TYP_SECD");
		Object P_LINE_TYP_SENM = parameterMap.get("P_LINE_TYP_SENM");
		Object P_SLCT_SECD = parameterMap.get("P_SLCT_SECD");
		Object P_SLCT_SENM = parameterMap.get("P_SLCT_SENM");
		Object P_ESTM_TOT_SCR = parameterMap.get("P_ESTM_TOT_SCR");
		Object P_ESTM_OPNN = parameterMap.get("P_ESTM_OPNN");
		
		
		if(P_ESTM_CMTM_NO != null){
			
			if(P_ESTM_CMTM_NO instanceof String){
		
				parameterMap.put("P_RMK", "");
				parameterMap.put("P_SLCT_YN", "Y");
				iproEstmProgDao.insertEstmCmtm(parameterMap);   // T_ESTM_CMTM INSERT
				
			}else if(P_ESTM_CMTM_NO instanceof ArrayList){
				System.err.println("@@@ 2 @@@");
				
				ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
				ArrayList<String> estmCmtmNmList = (ArrayList<String>) P_ESTM_CMTM_NM;
				ArrayList<String> inoCmtmSeNmList = (ArrayList<String>) P_INO_CMTM_SENM;
				ArrayList<String> estmSpheSecdList = (ArrayList<String>) P_ESTM_SPHE_SECD;
				ArrayList<String> estnSpheSenmList = (ArrayList<String>) P_ESTM_SPHE_SENM;
				ArrayList<String> llfSecdList = (ArrayList<String>) P_LLF_SECD;
				ArrayList<String> llfNmList = (ArrayList<String>) P_LLF_NM;
				ArrayList<String> cntnSecdList = (ArrayList<String>) P_CNTN_SECD;
				ArrayList<String> cntnNmList = (ArrayList<String>) P_CNTN_NM;
				ArrayList<String> mlfSecdList = (ArrayList<String>) P_MLF_SECD;
				ArrayList<String> mlfNmList = (ArrayList<String>) P_MLF_NM;
				ArrayList<String> slfSecdList = (ArrayList<String>) P_SLF_SECD;
				ArrayList<String> slfNmList = (ArrayList<String>) P_SLF_NM;
				ArrayList<String> cpNoList = (ArrayList<String>) P_CP_NO;
				ArrayList<String> emalList = (ArrayList<String>) P_EMAL;
//				ArrayList<String> lineTypSecdList = (ArrayList<String>) P_LINE_TYP_SECD;
//				ArrayList<String> lineTypSenmList = (ArrayList<String>) P_LINE_TYP_SENM;
//				ArrayList<String> slctSecdList = (ArrayList<String>) P_SLCT_SECD;
//				ArrayList<String> slctSenmList = (ArrayList<String>) P_SLCT_SENM;
//				ArrayList<String> estmTotScrList = (ArrayList<String>) P_ESTM_TOT_SCR;
//				ArrayList<String> estmOpnnList = (ArrayList<String>) P_ESTM_OPNN;
				
				
				for(int i =0; i < estmCmtmNoList.size(); i++){
					
					if(estmCmtmNoList != null && estmCmtmNoList.get(i) != null){
						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));						
					}
					
					if(estmCmtmNmList != null && estmCmtmNmList.get(i) != null){
						parameterMap.put("P_ESTM_CMTM_NM", estmCmtmNmList.get(i));						
					}
					
					if(inoCmtmSeNmList != null && inoCmtmSeNmList.get(i) != null){
						parameterMap.put("P_INO_CMTM_SENM", inoCmtmSeNmList.get(i));
					}
					
					if(estmSpheSecdList != null && estmSpheSecdList.get(i) != null){
						parameterMap.put("P_ESTM_SPHE_SECD", estmSpheSecdList.get(i));
					}
					
					if(estnSpheSenmList != null && estnSpheSenmList.get(i) != null){
						parameterMap.put("P_ESTM_SPHE_SENM", estnSpheSenmList.get(i));						
					}
					
					if(llfSecdList != null && llfSecdList.get(i) != null){
						parameterMap.put("P_LLF_SECD", llfSecdList.get(i));						
					}
					
					if(llfNmList != null && llfNmList.get(i) != null){
						parameterMap.put("P_LLF_NM", llfNmList.get(i));						
					}
					
					if(cntnSecdList != null && cntnSecdList.get(i) != null){
						parameterMap.put("P_CNTN_SECD", cntnSecdList.get(i));
					}
					
					if(cntnNmList != null && cntnNmList.get(i) != null){
						parameterMap.put("P_CNTN_NM", cntnNmList.get(i));						
					}
					
					if(mlfSecdList != null && mlfSecdList.get(i) != null){
						parameterMap.put("P_MLF_SECD", mlfSecdList.get(i));
					}
					
					if(mlfNmList != null && mlfNmList.get(i) != null){
						parameterMap.put("P_MLF_NM", mlfNmList.get(i));
					}
					
					if(slfSecdList != null && slfSecdList.get(i) != null){
						parameterMap.put("P_SLF_SECD", slfSecdList.get(i));						
					}
					
					if(slfNmList != null && slfNmList.get(i) != null){
						parameterMap.put("P_SLF_NM", slfNmList.get(i));						
					}
					
					if(cpNoList != null && cpNoList.get(i) != null){
						parameterMap.put("P_CP_NO", cpNoList.get(i));						
					}
					
					if(emalList != null && emalList.get(i) != null){
						parameterMap.put("P_EMAL", emalList.get(i));						
					}
					
					parameterMap.put("P_RMK", "");
					parameterMap.put("P_SLCT_YN", "Y");
					iproEstmProgDao.insertEstmCmtm(parameterMap);   // T_ESTM_CMTM INSERT
				}
			}
		}
		
		// 삭제한 파일 DELETE_AT = 'N' 처리
		String deleteEstmCmtmNo = parameterMap.getString("P_DELETE_ESTM_CMTM_NO");
		
		if(!"".equals(deleteEstmCmtmNo)){
			String[] arrDeleteEstmCmtmNo = deleteEstmCmtmNo.split(",");
			
			for (int i = 0; i < arrDeleteEstmCmtmNo.length; i++) {
				parameterMap.put("P_ESTM_CMTM_NO", arrDeleteEstmCmtmNo[i]);
				
				iproEstmProgDao.deleteEstmCmtm(parameterMap);
			}
		}
		
		parameterMap.put("P_RMK", "평가위원저장");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public FwkTransferObject estmStPsblYnChck(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		String rsn_msg = "";
		String rsn_code = "";
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		String CMTM_SLCT_CMPL_YN = estmMngMstDetail.getString("CMTM_SLCT_CMPL_YN");   // 평가위원선정완료여부
		System.err.println("@@@ CMTM_SLCT_CMPL_YN ==> " + CMTM_SLCT_CMPL_YN);
		
		// 외부평가위원 주민등록번호 입력안 한 count 조회
		int outCmtmRsdnNoNullCnt = iproEstmProgDao.selectOutCmtmRsdnNoNullCnt(parameterMap);   // T_ESTM_CMTM
		System.err.println("@@@ outCmtmRsdnNoNullCnt ==> " + outCmtmRsdnNoNullCnt);
		
		// 평가대상 count
		int estmObjCnt = iproEstmProgDao.selectEstmObjCnt(parameterMap);   // T_ESTM_OBJ
		System.err.println("@@@ estmObjCnt ==> " + estmObjCnt);
		
		if(outCmtmRsdnNoNullCnt > 0){
			rsn_msg = "외부평가위원 주민등록번호가 저장되어있지 않습니다.";
			rsn_code = "outCmtmRsdnNoNullCnt";
		}
		
		if(!"Y".equals(CMTM_SLCT_CMPL_YN)){
			rsn_msg = "평가위원선정이 완료되어 있지 않습니다.";
			rsn_code = "cmtmSlctCmplYN_N";
		}
		
		if(estmObjCnt == 0){
			rsn_msg = "평가대상이 존재하지 않습니다.";
			rsn_code = "estmObjCnt";
		}
		
		boolean flag = false;
		
		if("Y".equals(CMTM_SLCT_CMPL_YN) && outCmtmRsdnNoNullCnt == 0 && estmObjCnt != 0){
			flag= true;
		}else{
			flag= false;
		}
		
		if(flag){
			trans.put("psblYn", "Y");
		}else{
			trans.put("psblYn", "N");
		}
		
		trans.put("rsn_msg", rsn_msg);
		trans.put("rsn_code", rsn_code);
		
		System.err.println("@@@ trans ==> " + trans);
		
		return trans;		
		
	}

	// 평가시작 시 메일 전송
	public void estmSendMail(final FwkParameterMap parameterMap) throws Exception {
		
		MailSendUtil mailSendUtil = new MailSendUtil();
		
		FwkParameterMap mailParameterMap = new FwkParameterMap();
		
		mailParameterMap.put("P_ESTM_NO", parameterMap.get("P_ESTM_NO"));
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(mailParameterMap);   // T_ESTM_MNG_MST
		
		//메일정보 조회
		mailParameterMap.put("P_MSG_SECD", "MAIL");
		mailParameterMap.put("P_MSG_SN", "1");
		//parameterMap.put("P_MSG_OBJ_ID", "");
		FwkDataEntity msgDetail = (FwkDataEntity) iproCommDefaultDao.selectMsgContents(mailParameterMap);   // T_ESTM_MNG_MST
		String mailCntn = "";
		String mailTitle = "";
		
		//메일 제목
		mailTitle = msgDetail.getString("MSG_TTL");
		mailTitle = mailTitle.replace("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
		mailParameterMap.put("P_MSG_TTL",mailTitle);
		
		//메일 내용
		mailCntn = msgDetail.getString("MSG_CNTN");
		mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
		mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
		mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
		mailParameterMap.put("P_MSG_CNTN",mailCntn);
		
		//평가위원 조회
		mailParameterMap.put("P_SLCT_YN", "Y");
		List<FwkDataEntity> estmCmtmList =  iproEstmProgDao.selectEstmCmtmList(mailParameterMap);   // T_ESTM_CMTM
		
		for(int i = 0; i<estmCmtmList.size(); i++){
			//메일 수신자
			mailParameterMap.put("P_TO_MAIL", estmCmtmList.get(i).getString("EMAL"));
			//메일전송
			try{
				mailSendUtil.sendMail(mailParameterMap);
			}catch(Exception e){
			}
		}
		
	}
	
	@Override
	public void estmCmtmSlctCmplYnUpdt(final FwkParameterMap parameterMap) {
		
		// CMTM_SLCT_CMPL_YN = 'Y' update
		iproEstmProgDao.updateEstmCmtmSlctCmplYn(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가위원선정완료");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
	}

	@Override
	public FwkTransferObject estmSecdProcdSetList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		List<FwkDataEntity> estmSecdProcdSetList = iproEstmProgDao.selectEstmSecdProcdSetList(parameterMap);
		
		if(estmSecdProcdSetList != null){
			trans.put("resultCode", 1);
		}else{
			trans.put("resultCode", 0);
		}
		
		trans.put("estmSecdProcdSetList", estmSecdProcdSetList);
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmSecdSetList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		List<FwkDataEntity> estmSecdSetList = iproEstmProgDao.selectEstmSecdSetList(parameterMap);
		
		if(estmSecdSetList != null){
			trans.put("resultCode", 1);
		}else{
			trans.put("resultCode", 0);
		}
		
		trans.put("estmSecdSetList", estmSecdSetList);
		
		return trans;
	}
	

	@Override
	public void estmCmtmRsdnNoRegist(final FwkParameterMap parameterMap) {
		
		Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
		Object P_RSDN_NO_1 = parameterMap.get("P_RSDN_NO_1");
		Object P_RSDN_NO_2 = parameterMap.get("P_RSDN_NO_2");
		
		
		if(P_ESTM_CMTM_NO != null){
			
			if(P_ESTM_CMTM_NO instanceof String){
		
				parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
				
				iproEstmProgDao.updateEstmCmtmRsdnNo(parameterMap);   // T_ESTM_CMTM UPDATE
				
			}else if(P_ESTM_CMTM_NO instanceof ArrayList){
				
				ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
				ArrayList<String> rsdnNo_1_List = (ArrayList<String>) P_RSDN_NO_1;
				ArrayList<String> rsdnNo_2_List = (ArrayList<String>) P_RSDN_NO_2;
				
				for(int i =0; i < estmCmtmNoList.size(); i++){
					
					if(estmCmtmNoList != null && estmCmtmNoList.get(i) != null){
						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));						
					}
					
					if(rsdnNo_1_List != null && rsdnNo_1_List.get(i) != null && rsdnNo_2_List != null && rsdnNo_2_List.get(i) != null){
						parameterMap.put("P_RSDN_NO", rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));
					}
						
					// 선정된 평가위원 주민등록번호 update
					iproEstmProgDao.updateEstmCmtmRsdnNo(parameterMap);   // T_ESTM_CMTM UPDATE
				}
			}
						
		}
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가위원 주민등록번호 저장");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	
	
	/**
	 * 엑셀업로드시 셀 타입별 데이터 추출 로직
	 * @param cell
	 * @return
	 */
	public String cellValue(Cell cell) {
		 
        String value = null;
        if (cell == null) value = "";
        else {
            switch (cell.getCellType()) { //cell 타입에 따른 데이타 저장
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    //you should change this to your application date format
                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    value = "" + objSimpleDateFormat.format(cell.getDateCellValue());
                } else {
                    value = "" + String.format("%.0f", new Double(cell.getNumericCellValue()));
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = "" + cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                //value=""+cell.getBooleanCellValue();
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "" + cell.getErrorCellValue();
                break;
            default:
            }
        }
 
        //return value.trim();
        return value;
    }
	
	
	
	

	@Override
	public void estmCmtmHldfYnUpdt(FwkParameterMap parameterMap) {
		
		Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
		Object P_HLDF_YN = parameterMap.get("P_HLDF_YN");
		
		
		if(P_ESTM_CMTM_NO != null){
			
			if(P_ESTM_CMTM_NO instanceof String){
				
				iproEstmProgDao.updateEstmCmtmHldfYn(parameterMap);   // T_ESTM_CMTM UPDATE

				iproEstmProgDao.updateEstmCmtmPoolHldfYn(parameterMap);   // T_ESTM_CMTM_POOL_MST UPDATE
				
			}else if(P_ESTM_CMTM_NO instanceof ArrayList){
				
				ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
				ArrayList<String> hldfYnList = (ArrayList<String>) P_HLDF_YN;
				
				for(int i =0; i < estmCmtmNoList.size(); i++){
					
					if(estmCmtmNoList != null && estmCmtmNoList.get(i) != null){
						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));						
					}
					
					parameterMap.put("P_HLDF_YN", hldfYnList.get(i));
						
					// 선정된 평가위원 재직여부 update
					System.err.println("@@@ ArrayList parameterMap ==> " + parameterMap);
					iproEstmProgDao.updateEstmCmtmHldfYn(parameterMap);   // T_ESTM_CMTM UPDATE
					
					iproEstmProgDao.updateEstmCmtmPoolHldfYn(parameterMap);   // T_ESTM_CMTM_POOL_MST UPDATE
				}
			}
						
		}
		
		// 평가진행이력 등록
		parameterMap.put("P_RMK", "평가위원 재직여부 저장");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	@Override
	public FwkTransferObject estmEnd(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 2021-05-04
		 * 손연우
		 * 평가완료시 평가결과 등록을 위한 코딩
		 */
		//시작
		// 마스터에서 최고죄저점제외여부 가져오기
		FwkDataEntity mxmnScrExcpYnData = iproEstmProgDao.selectMxMnScrExcpYnColumn(parameterMap);
		
		parameterMap.put("P_MXMN_SCR_EXCP_YN", mxmnScrExcpYnData.getString("MXMN_SCR_EXCP_YN"));
		
		// T_ESTM_OBJ_SLCT 삭제
		iproEstmProgDao.deleteEstmObjSlct(parameterMap);
		
		// T_ESTM_OBJ_SLCT 등록
		iproEstmProgDao.insertEstmObjSlct(parameterMap);
		
		// 등록된 데이터를 뽑아낸다.
		List<FwkDataEntity> estmObjSlctList = iproEstmProgDao.selectEstmObjSlctList(parameterMap);
		
		for(int i = 0; i < estmObjSlctList.size(); i++) {
			FwkDataEntity objSlctDataEntity = estmObjSlctList.get(i);
			parameterMap.put("P_ESTM_OBJ_SEQ", objSlctDataEntity.getString("ESTM_OBJ_SEQ"));
			parameterMap.put("P_ESTM_RNK", objSlctDataEntity.getString("RANK"));
			
			iproEstmProgDao.updateEstmObjSlctRankUpdt(parameterMap);
		}
			
		//종료
		
		trans.put("resultCode", 1);
		
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmASlct(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 2021-05-04
		 * 손연우
		 * 평가완료시 평가대상선정 등록을 위한 코딩
		 */
		
		Object P_ESTM_SLCT_YN = parameterMap.get("P_ESTM_SLCT_YN");
		Object P_ESTM_OBJ_SEQ = parameterMap.get("P_ESTM_OBJ_SEQ");
		
		
		/**
		 * 재진행시 
		 * 다음차수에 저장되어있는 평가대상이 선청취소되었을 때
		 * 선정여부가 N으로 변경되는 작업을 해주기 위해서 추가됨. 
		 */
		
		iproEstmProgDao.updateEstmAslctAllN(parameterMap);   // T_ESTM_OBJ INSERT
		

		if(P_ESTM_OBJ_SEQ != null){
			
			if(P_ESTM_OBJ_SEQ instanceof String){
				
				if(parameterMap.get("slctCheck").equals("Y")) {
				}else{
					parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
					parameterMap.put("P_ESTM_SLCT_YN", parameterMap.get("P_ESTM_SLCT_YN"));
					
					System.err.println("P_ESTM_OBJ_SEQ instanceof String");
					iproEstmProgDao.updateEstmAslctYn(parameterMap);   // T_ESTM_OBJ INSERT
				}
				
			}
			else if(P_ESTM_OBJ_SEQ instanceof ArrayList){
			
				ArrayList<String> P_ESTM_OBJ_SEQList = (ArrayList<String>) P_ESTM_OBJ_SEQ;
				ArrayList<String> P_SLCT_YNList = (ArrayList<String>) P_ESTM_SLCT_YN;
				
				for(int i =0; i < P_ESTM_OBJ_SEQList.size(); i++){
					
						if(P_ESTM_OBJ_SEQList != null) { parameterMap.put("P_ESTM_OBJ_SEQ", P_ESTM_OBJ_SEQList.get(i)); }
						if(P_SLCT_YNList != null) { parameterMap.put("P_ESTM_SLCT_YN", P_SLCT_YNList.get(i)); }

						System.err.println("P_ESTM_OBJ_SEQ instanceof ArrayList");
						iproEstmProgDao.updateEstmAslctYn(parameterMap);   // T_ESTM_OBJ INSERT
				}
			}
			else if(P_ESTM_OBJ_SEQ instanceof String[]){
				
				String[] P_ESTM_OBJ_SEQStr = (String[]) P_ESTM_OBJ_SEQ;
				String[] P_SLCT_YNStr = (String[]) P_ESTM_SLCT_YN;
				
				for (int i = 0; i < P_ESTM_OBJ_SEQStr.length; i++) {
					
					parameterMap.put("P_ESTM_OBJ_SEQ", P_ESTM_OBJ_SEQStr[i]);
					parameterMap.put("P_ESTM_SLCT_YN", P_SLCT_YNStr[i]);
					
					System.err.println("P_ESTM_OBJ_SEQ instanceof String[]");
					iproEstmProgDao.updateEstmAslctYn(parameterMap);   // T_ESTM_OBJ INSERT
				}
			}
		}
		

		// 다음차수가 존재하는지 확인
		FwkDataEntity nextProcdSeqEnt =  iproEstmProgDao.selectEstmNextProcdSeqAt(parameterMap);
		if(!"0".equals(nextProcdSeqEnt.get("CNT"))) {
			// 다음차수가 존재하면 다음차수의 평가대상을 insert 해준다.
			iproEstmProgDao.deleteEstmObjNextProcdSeq(parameterMap);
			iproEstmProgDao.insertEstmObjNextProcdSeq(parameterMap);
		}
		
		
		trans.put("resultCode", 1);
		return trans;
	}

	@Override
	public FwkTransferObject estmStUpdt(final FwkParameterMap parameterMap) throws CertificateFailException, Exception {
		// 평가시작
		
		FwkTransferObject trans = new FwkTransferObject();
		
		String errMsg = "";
		String errCode = "";
		EwSignBiz pki = new EwSignBiz();
		
		String filePath ="";   // 파일위치
		String fileHashValue = "";   // 파일해쉬값
		String ESTM_SIGN_FILE_GRP_NO = "";   // 심사위원 서명파일 FILE_GRP_NO
		
		if("A005".equals(parameterMap.get("P_ESTM_PSCD"))){				//평가시작
			//메일전송
			
			parameterMap.put("P_CD_ID_S", "SEND_MAIL");
			List<FwkDataEntity> mailSendList = iproCommDefaultDao.selectCodeList(parameterMap);
			LOG.debug("========== estmSendMail Start ==========");
			if("Y".equals(mailSendList.get(0).get("CD_DTL_ID").toString())){
				estmSendMail(parameterMap);
			}
			LOG.debug("========== estmSendMail End ==========");
		}
		
		try {
			
			
			// 심사위원 서명파일 첨부파일 그룹번호 조회
			parameterMap.put("P_DEL_AT", "N");
			parameterMap.put("P_ESTM_FSCD", "SIGN");
			FwkDataEntity estmSignFileGrpNo = (FwkDataEntity) iproEstmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
			
			List<FwkParameterMap> setFileHash = new ArrayList<FwkParameterMap>();
			
			
			
			if(estmSignFileGrpNo != null){
				ESTM_SIGN_FILE_GRP_NO = estmSignFileGrpNo.getString("FILE_GRP_NO");
				parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
				
				List<FwkDataEntity> estmSignFileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
				
				if(estmSignFileList != null){
					if(estmSignFileList.size() > 0){
						for(int i=0; i < estmSignFileList.size(); i++){
							FwkDataEntity signFileInfo = estmSignFileList.get(i);
							String FILE_SN = signFileInfo.getString("FILE_SN"); // 파일일련번호
							filePath = signFileInfo.getString("FILE_LCTN") + File.separator + signFileInfo.getString("SV_FILE_NM");   // 파일경로		
							
							FwkParameterMap fileParam = new FwkParameterMap();
							filePath = filePath.replace("\\", "/");
							String FILE_GRP_NO = signFileInfo.getString("FILE_GRP_NO"); // 파일그룹번호
							
							fileParam.put("P_FILE_SN", FILE_SN);   // 파일일련번호
							fileParam.put("P_FILE_GRP_NO", FILE_GRP_NO);   // 파일그룹번호
							
							fileHashValue = "";
							
							try{
								fileHashValue = pki.getHashFile(filePath);//파일해쉬생성
								
								System.err.println("@@@ fileHashValue ==> " + fileHashValue);
								
								//if(fileHashValue == null || fileHashValue == "" || "".equals(fileHashValue)){
								if("".equals(fileHashValue)){
									System.err.println("=== null ===");
									errCode = "fail";
									errMsg = "서명파일 해쉬 생성에 실패했습니다.";
									
									throw new CertificateFailException(errMsg);
								}else{
									System.err.println("=== not null ===");
									errCode = "success";
									errMsg = "서명파일 해쉬 생성에 성공했습니다.";
								}
								
							} catch (Exception e){
								errCode = "fail";
								errMsg = "서명파일 해쉬 생성에 실패했습니다.";
								LOG.debug("서명파일 해쉬 생성에 실패했습니다.");
								 
								System.err.println(">>> 서명파일 해쉬 생성에 실패했습니다. <<<");
								System.err.println(">>> e.toString() ::: " + e.toString());
								
								throw new CertificateFailException(e.getMessage());
							}
							
							
							fileParam.put("P_FILE_HASH_VAL", fileHashValue);
							System.err.println("@@@ fileParam ==> " + fileParam);
							setFileHash.add(fileParam);
						}
					}
				}
			}
			
			
			// 선정된 내/외부 평가위원 조회
			parameterMap.put("P_SLCT_YN", "Y");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
			List<FwkDataEntity> estmCmtmSlctList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
			
			System.err.println("@@@ setFileHash.size() ==> " + setFileHash.size());
			
			if(estmCmtmSlctList != null){
				System.err.println("@@@ estmCmtmSlctList.size() ==> " + estmCmtmSlctList.size());
				if(estmCmtmSlctList.size() > 0){
					iproEstmProgDao.deleteEstmFileSign(parameterMap);   // T_ESTM_FILE_SIGN DELETE
					
					for(int i=0; i < estmCmtmSlctList.size(); i++){
						FwkDataEntity cmtmSlctInfo = estmCmtmSlctList.get(i);
						parameterMap.put("P_ESTM_CMTM_NO", cmtmSlctInfo.get("ESTM_CMTM_NO")); 	  // 평가위원번호
						
						if(estmSignFileGrpNo != null){
							for(int k=0; k < setFileHash.size(); k++){
								FwkParameterMap fileMap = setFileHash.get(k);
								
								if(!"".equals(fileMap.getString("P_FILE_HASH_VAL"))){
									
									parameterMap.put("P_FILE_GRP_NO", fileMap.getString("P_FILE_GRP_NO"));		// 파일 그룹번호
									parameterMap.put("P_FILE_SN", fileMap.getString("P_FILE_SN")); 			// 파일 일련번호
									parameterMap.put("P_FILE_HASH_VAL", fileMap.getString("P_FILE_HASH_VAL")); 	// 파일 해시 값
									
									System.err.println("@@@ fileMap ==> " + fileMap);
									iproEstmProgDao.insertEstmFileSign(parameterMap);   // T_ESTM_FILE_SIGN INSERT
									
									errCode = "success";
								}else{
									errCode = "fail";
									errMsg = "서명파일 해쉬 생성에 실패했습니다.";
									
									throw new CertificateFailException(errMsg);
								}
								
							}
						}
						
						
					}
				}
			}
			
			iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
			
			iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
			
		} catch (CertificateFailException cfe) {
			LOG.error( cfe.getMessage() );
			
			throw new CertificateFailException(cfe.getMessage());
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		
		
		System.err.println("==============================");
		System.err.println(">>> errCode : " + errCode);
		System.err.println(">>> errMsg : " + errMsg);
		System.err.println("==============================");
		
		trans.put("errCode", errCode);
		trans.put("errMsg", errMsg);
		
		return trans;
	}
	
	
	@Override
	public FwkTransferObject cmtmRsdnNoRegistPsblChck(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity cmtmRsdnNoRegistPsblChck = null;
		// CASE_1 : 입력한 주민등록번호가 다른 평가위원과 중복
		// CASE_2 : 해당 평가위원의 주민등록번호와 입력한 주민등록번호가 다름
		
		int CASE_1;
		int CASE_2;
		
		String resultCode = "success";
		String msg = "";
		
		Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
		Object P_ESTM_CMTM_NM = parameterMap.get("P_ESTM_CMTM_NM");
		Object P_RSDN_NO_1 = parameterMap.get("P_RSDN_NO_1");
		Object P_RSDN_NO_2 = parameterMap.get("P_RSDN_NO_2");
		
		if(P_ESTM_CMTM_NO != null){
			
			if(P_ESTM_CMTM_NO instanceof String){
		
				parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
				parameterMap.put("P_ESTM_CMTM_NO", parameterMap.get("P_ESTM_CMTM_NO"));

				cmtmRsdnNoRegistPsblChck = iproEstmProgDao.selectCmtmRsdnNoRegistPsblChck(parameterMap);
				

				CASE_1 = cmtmRsdnNoRegistPsblChck.getInt("CASE_1");
				CASE_2 = cmtmRsdnNoRegistPsblChck.getInt("CASE_2");
				
				System.err.println("@@@ 평가위원번호 : " + parameterMap.get("P_ESTM_CMTM_NO") + ", 평가위원명 : " + parameterMap.get("P_ESTM_CMTM_NM") + ", 주민등록번호 : " + parameterMap.get("P_RSDN_NO"));
				
				System.err.println("@@@ CASE_1 ==> " + CASE_1);
				System.err.println("@@@ CASE_2 ==> " + CASE_2);

				if(CASE_1 > 0){
					msg += "[ " + parameterMap.get("P_ESTM_CMTM_NM") + " ]님의 주민등록번호가 다른 평가위원과 중복됩니다.";
				}
				
				if(CASE_2 > 0){
					msg += "\n[ " + parameterMap.get("P_ESTM_CMTM_NM") + " ]님의 주민등록번호가 이전에 입력한 주민등록번호와 다릅니다.";
				}

				if(CASE_1 > 0 || CASE_2 > 0){
					resultCode = "fail";
				}
				
			}else if(P_ESTM_CMTM_NO instanceof ArrayList){
				
				ArrayList<String> estmCmtmNmList = (ArrayList<String>) P_ESTM_CMTM_NM;
				ArrayList<String> estmCmtmNoList = (ArrayList<String>) P_ESTM_CMTM_NO;
				ArrayList<String> rsdnNo_1_List = (ArrayList<String>) P_RSDN_NO_1;
				ArrayList<String> rsdnNo_2_List = (ArrayList<String>) P_RSDN_NO_2;
				
				for(int i =0; i < estmCmtmNoList.size(); i++){
					
					if(estmCmtmNoList != null && estmCmtmNoList.get(i) != null){
						parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNoList.get(i));						
					}
					
					if(rsdnNo_1_List != null && rsdnNo_1_List.get(i) != null && rsdnNo_2_List != null && rsdnNo_2_List.get(i) != null){
						parameterMap.put("P_RSDN_NO", rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));
					}
					
					cmtmRsdnNoRegistPsblChck = iproEstmProgDao.selectCmtmRsdnNoRegistPsblChck(parameterMap);
					CASE_1 = cmtmRsdnNoRegistPsblChck.getInt("CASE_1");
					CASE_2 = cmtmRsdnNoRegistPsblChck.getInt("CASE_2");
					
//					System.err.println("@@@ 평가위원번호 : " + estmCmtmNoList.get(i) + ", 평가위원명 : " + estmCmtmNmList.get(i) + ", 주민등록번호 : " + rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));
					
					System.err.println("@@@ CASE_1 ==> " + CASE_1);
					System.err.println("@@@ CASE_2 ==> " + CASE_2);

					if(CASE_1 > 0){
						msg += "[ " + estmCmtmNmList.get(i) + " ]님의 주민등록번호가 다른 평가위원과 중복됩니다.";
					}
					
					if(CASE_2 > 0){
						msg += "\n[ " + estmCmtmNmList.get(i) + " ]님의 주민등록번호가 이전에 입력한 주민등록번호와 다릅니다.";
					}
					
					if(CASE_1 > 0 || CASE_2 > 0){
						resultCode = "fail";
						break;
					}
				}
			}
		}
		
		
		trans.put("resultCode", resultCode);
		trans.put("msg", msg);
		
		System.err.println("@@@ trans ==> " + trans);
		
		return trans;		
		
	}
}