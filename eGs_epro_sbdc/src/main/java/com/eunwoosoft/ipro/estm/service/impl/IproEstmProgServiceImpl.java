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
		
		// ????????????????????????
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_ST_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_MM"))){
			parameterMap.put("P_TOTL_ESTM_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_ST_HH") + parameterMap.get("P_TOTL_ESTM_ST_MM") + "00"); 
		}
		
		// ????????????????????????
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_END_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_MM"))){
			parameterMap.put("P_TOTL_ESTM_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_END_HH") + parameterMap.get("P_TOTL_ESTM_END_MM") + "00"); 
		}
		
		parameterMap.put("P_ESTM_PSCD", "A001");   // ????????????????????????
		
		// ???????????? ????????? ??????
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
					
					// ????????????????????????
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_ST_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_MM"))){
						parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_ST_HH") + parameterMap.get("P_ESTM_PROCD_ST_MM") + "00"); 
					}
					
					// ????????????????????????
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_END_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_MM"))){
						parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_END_HH") + parameterMap.get("P_ESTM_PROCD_END_MM") + "00"); 
					}
					
					// ???????????? ??????
					iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
					
					// ?????????????????? ??????
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
						
						// ????????????????????????
						if(!"".equals(estmProcdStDeList.get(i)) && !"".equals(estmProcdStHhList.get(i)) && !"".equals(estmProcdStMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhList.get(i) + estmProcdStMmList.get(i) + "00"); 
						}
						
						// ????????????????????????
						if(!"".equals(estmProcdEndDeList.get(i)) && !"".equals(estmProcdEndHhList.get(i)) && !"".equals(estmProcdEndMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhList.get(i) + estmProcdEndMmList.get(i) + "00"); 
						}
						
						// ???????????? ??????
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// ?????????????????? ??????
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
						
						// ????????????????????????
						if(!"".equals(estmProcdStDeStr[i]) && !"".equals(estmProcdStHhStr[i]) && !"".equals(estmProcdStMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhStr[i] + estmProcdStMmStr[i] + "00"); 
						}
						
						// ????????????????????????
						if(!"".equals(estmProcdEndDeStr[i]) && !"".equals(estmProcdEndHhStr[i]) && !"".equals(estmProcdEndMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhStr[i] + estmProcdEndMmStr[i] + "00"); 
						}
						
						// ???????????? ??????
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// ?????????????????? ??????
						iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}
				}
			}
		}
		
		// ???????????? ??????
		if(!"".equals(parameterMap.get("P_FILE_GRP_NO"))){
			parameterMap.put("P_ESTM_FSCD", "MST");
			parameterMap.put("P_RMK", "");
			iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT
		}
		
		/********** ???????????? ???????????? ?????? start **********/	
		
		// ???????????? > ????????????????????? ?????????????????? ???????????? ???????????? ??????
		String P_ESTM_SECD_S = parameterMap.get("P_ESTM_SECD") + "";
		String ESTM_SECD_FILE_GRP_NO = "";   // ???????????? ??????????????????
		String contextPath = "estm";
		
		parameterMap.put("P_ESTM_SECD_S", P_ESTM_SECD_S);
		FwkDataEntity estmSeMngDetail = (FwkDataEntity) iproEstmProgDao.selectEstmSeMngDetail(parameterMap);   // T_ESTM_SE_MST
		
		if (estmSeMngDetail != null) {
			ESTM_SECD_FILE_GRP_NO = estmSeMngDetail.getString("FILE_GRP_NO");   // T_ESTM_SE_MST : FILE_GRP_NO
			
			
			/*** ???????????? ?????? START ***/
			parameterMap.put("P_FILE_GRP_NO", ESTM_SECD_FILE_GRP_NO);
			List<FwkDataEntity> fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
			
			String fileSavePath = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");   // D:/edata1/
			String uploadDirPath = fileSavePath + FwkDateUtil.getCurrentDateAsString("yyyy") + "/" + FwkDateUtil.getCurrentDateAsString("MM") + "/" + FwkDateUtil.getCurrentDateAsString("dd");
			String copyPath = uploadDirPath + "/" + contextPath + "/";
			
			String ESTM_SIGN_FILE_GRP_NO = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			if(fileList.size() > 0){
				
				for(int i=0; i < fileList.size(); i++){
					FwkDataEntity fde = fileList.get(i);   // ?????? ??????
					
					String sourcePath = fde.get("FILE_LCTN") + "/";
					String sourceName = fde.get("SV_FILE_NM") + "";
					String erpFileName = fde.get("SV_FILE_NM") + "";
										
					try {
						CmmnUtil.FileCopy(sourceName, erpFileName, sourcePath, copyPath);
						LOG.debug("???????????? ????????? ?????????????????????.");
						LOG.debug("===== FILE COPY SUCCESS =====");
					} catch (Exception e) {
						LOG.debug("???????????? ????????? ?????????????????????.");
						LOG.debug("===== FILE COPY FAIL =====");
						e.printStackTrace();
					}
					
				}
				
				parameterMap.put("P_FILE_GRP_NO_ORG", ESTM_SECD_FILE_GRP_NO);   // T_ESTM_SE_MST : FILE_GRP_NO
				parameterMap.put("P_FILE_GRP_NO_NEW", ESTM_SIGN_FILE_GRP_NO);   // UUID
				parameterMap.put("P_FILE_LCTN", copyPath);   // ????????????
				parameterMap.put("P_TSK_VKEY1", parameterMap.get("P_ESTM_NO"));   // ????????????
				iproEstmProgDao.selectAtchFileInsert(parameterMap);   // T_MM_FILE_MST INSERT
				
				// ???????????? ???????????? ??????
				parameterMap.put("P_ESTM_FSCD", "SIGN");
				parameterMap.put("P_RMK", "");
				parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
				iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT
			}
		}
		
		/********** ???????????? ???????????? ?????? end **********/
		
		// ?????????????????? ??????
		//parameterMap.put("P_RMK", "????????????");
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
		
		// ?????? ??????
		
//		FwkParameterMap mailParameterMap = new FwkParameterMap();
//		MailSendUtil mailSendUtil = new MailSendUtil();
//		
//		// ???????????? ????????? ??????
//		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
//		
//		//???????????? ??????
//		mailParameterMap.put("P_MSG_SECD", "MAIL");
//		mailParameterMap.put("P_MSG_SN", "3");
//		//parameterMap.put("P_MSG_OBJ_ID", "");
//		FwkDataEntity msgDetail = (FwkDataEntity) iproCommDefaultDao.selectMsgContents(mailParameterMap);   // T_ESTM_MNG_MST
//		String mailCntn = "";
//		String mailTitle = "";
//		
//		//?????? ??????
//		mailTitle = msgDetail.getString("MSG_TTL");
//		mailTitle = mailTitle.replace("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
//		mailParameterMap.put("P_MSG_TTL",mailTitle);
//		
//		
//		
//		// ???????????? ??????????????? ????????????.
//		List<FwkDataEntity> estmVidoMtngMailList = iproEstmProgDao.selectEstmVidoMtngCreatMeetList(parameterMap);
//		
//		String ext_room_id = parameterMap.getString("P_ESTM_NO") + "-" + parameterMap.getString("P_ESTM_PROCD_SEQ") + "-" + parameterMap.getString("P_VIDO_MTNG_SEQ");
//		// ??????????????? ????????????
//		for(int i = 0; i < estmVidoMtngMailList.size(); i++) {
//			FwkDataEntity mtngEntity = estmVidoMtngMailList.get(i);
//			//?????? ?????????
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
//			//?????? ??????
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
//			//????????????
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
//		// ???????????? ????????????
//		for(int i = 0; i < estmVidoMtngCmtmMailList.size(); i++) {
//			FwkDataEntity mtngCmtmEntity = estmVidoMtngCmtmMailList.get(i);
//			//?????? ?????????
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
//			//?????? ??????
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
//			//????????????
//			try{
//				mailSendUtil.sendMail(mailParameterMap);
//			}catch(Exception e){
//			}
//		}
		
		
		// ?????? ??????
		
		return trans;
	}

	@Override
	public FwkTransferObject estmProgDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// ???????????? ??????
		List<FwkDataEntity> estmProcdList = iproEstmProgDao.selectEstmProcdList(parameterMap);   // T_ESTM_PROCD
		trans.put("estmProcdList", estmProcdList);
		
		// ???????????? ??????
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) iproEstmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		// ???????????? ???????????? ???????????? ???????????? ??????
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) iproEstmProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// ???????????? ???????????? ??????
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap);   // T_ESTM_PROCD 
		trans.put("estmTabList", estmTabList);
		
		
		return trans;
	}

	@Override
	public void estmDelete(final FwkParameterMap parameterMap) {

		iproEstmProgDao.updateEstmDelAt(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "????????????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	@Override
	public void estmRsdnNoDelete(final FwkParameterMap parameterMap) {

		iproEstmProgDao.updateEstmRsdnNoDelete(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "??????????????????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	

	@Override
	public FwkTransferObject estmProgObjDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// ?????????????????? ??????
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjList(parameterMap);   // T_ESTM_OBJ
		trans.put("estmObjList", estmObjList);
		
		// ????????????????????? ??????(Tab)
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
		//????????? ?????? , ??????????????? ????????? ??????
		InputStream	iStream		= null;
		String excelResultCode = "";
		iStream = excelFile.getInputStream();	// ???????????? ????????? ??? ??????. 
		workbook	= WorkbookFactory.create( iStream ); //WookbookFactory??? ???????????? ???????????? xls??? xlsx??? ????????? ?????????.
		
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
						if(i==0) { //????????? row
							lastCellNum = row.getLastCellNum();
							if("A".equals(parameterMap.get("P_ESTM_OBJ_SECD")) && lastCellNum != 4) { // ????????? ??????
								//throw new Exception("????????? ?????????????????????.");
							}else if("B".equals(parameterMap.get("P_ESTM_OBJ_SECD")) && lastCellNum != 6) { // ????????? ??????
								//throw new Exception("????????? ?????????????????????.");
							}else if("C".equals(parameterMap.get("P_ESTM_OBJ_SECD")) && lastCellNum != 4) { // ????????? ??????
								//throw new Exception("????????? ?????????????????????.");
							}
							
							Cell cell = row.getCell(0);
							if("A".equals(parameterMap.get("P_ESTM_OBJ_SECD"))){
								if(!cell.getRichStringCellValue().getString().trim().equals("?????????")) {
									//throw new Exception("[?????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(1);
								if(!cell.getRichStringCellValue().getString().trim().equals("???????????????")) {
									//throw new Exception("[???????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(2);
								if(!cell.getRichStringCellValue().getString().trim().equals("????????????")) {
									//throw new Exception("[????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(3);
								if(!cell.getRichStringCellValue().getString().trim().equals("?????????")) {
									//throw new Exception("[?????????] ????????? ?????? ?????? ????????????.");
								}
							}else if("B".equals(parameterMap.get("P_ESTM_OBJ_SECD"))){
								if(!cell.getRichStringCellValue().getString().trim().equals("????????????")) {
									//throw new Exception("[????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(1);
								if(!cell.getRichStringCellValue().getString().trim().equals("?????????")) {
									//throw new Exception("[?????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(2);
								if(!cell.getRichStringCellValue().getString().trim().equals("?????????")) {
									//throw new Exception("[?????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(3);
								if(!cell.getRichStringCellValue().getString().trim().equals("???????????????")) {
									//throw new Exception("[???????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(4);
								if(!cell.getRichStringCellValue().getString().trim().equals("????????????")) {
									//throw new Exception("[????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(5);
								if(!cell.getRichStringCellValue().getString().trim().equals("?????????")) {
									//throw new Exception("[?????????] ????????? ?????? ?????? ????????????.");
								}
							}else if("C".equals(parameterMap.get("P_ESTM_OBJ_SECD"))){
								if(!cell.getRichStringCellValue().getString().trim().equals("????????????")) {
									//throw new Exception("[????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(1);
								if(!cell.getRichStringCellValue().getString().trim().equals("????????????")) {
									//throw new Exception("[????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(2);
								if(!cell.getRichStringCellValue().getString().trim().equals("????????????")) {
									//throw new Exception("[????????????] ????????? ?????? ?????? ????????????.");
								}
								cell = row.getCell(3);
								if(!cell.getRichStringCellValue().getString().trim().equals("?????????")) {
									//throw new Exception("[?????????] ????????? ?????? ?????? ????????????.");
								}
							}
						}else { //if(i==0)
							
							if("A".equals(parameterMap.get("P_ESTM_OBJ_SECD"))) { //??????
								FwkParameterMap param = new FwkParameterMap();
								
								param.put("P_VEND_NM", cellValue(row.getCell(0)));
								param.put("P_BIZRNO", cellValue(row.getCell(1)));
								param.put("P_TEL_NO", cellValue(row.getCell(2)));
								param.put("P_EMAL", cellValue(row.getCell(3)));
								param.put("P_ESTM_OBJ_SEQ", i+1);
								
								excelParamList.add(param);
							}else if("B".equals(parameterMap.get("P_ESTM_OBJ_SECD"))) { // ??????
								FwkParameterMap param = new FwkParameterMap();
								
								param.put("P_ITEM_NO", cellValue(row.getCell(0)));
								param.put("P_ITEM_NM", cellValue(row.getCell(1)));
								param.put("P_VEND_NM", cellValue(row.getCell(2)));
								param.put("P_BIZRNO", cellValue(row.getCell(3)));
								param.put("P_TEL_NO", cellValue(row.getCell(4)));
								param.put("P_EMAL", cellValue(row.getCell(5)));
								param.put("P_ESTM_OBJ_SEQ", i+1);
								
								excelParamList.add(param);
							}else if("C".equals(parameterMap.get("P_ESTM_OBJ_SECD"))) { // ??????
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
		
		String P_PRIO_RNK_RE_SLCT_YN = parameterMap.getString("P_PRIO_RNK_RE_SLCT_YN");   // ???????????????
		String P_CMPL_YN = parameterMap.getString("P_CMPL_YN");   // ????????????
		parameterMap.put("P_PRIO_RNK_RE_SLCT_YN", P_PRIO_RNK_RE_SLCT_YN);
		parameterMap.put("P_CMPL_YN", P_CMPL_YN);
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		System.err.println("@@@ ?????????????????????????????? ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		List<FwkDataEntity> outEstmCmtmList = new ArrayList<FwkDataEntity>();
		List<FwkDataEntity> fixEstmCmtmList = new ArrayList<FwkDataEntity>();
		List<FwkDataEntity> innEstmCmtmList = new ArrayList<FwkDataEntity>();
		
		if("Y".equals(P_PRIO_RNK_RE_SLCT_YN)){
			System.err.println("========== [?????????] ==========");
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			}
			parameterMap.put("P_SLCT_YN", "Y");   // ????????????
			outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			System.err.println("@@@ outEstmCmtmList ==> " + outEstmCmtmList.size());
			trans.put("outEstmCmtmSlctList", outEstmCmtmList);
			
			// ???????????????????????? ??????
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // ?????????????????? : ????????????			
				parameterMap.put("P_SLCT_YN", "Y");   // ????????????
				fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmSlctList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			parameterMap.put("P_SLCT_YN", "Y");   // ????????????
			innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmSlctList", innEstmCmtmList);
			System.err.println("@@@ innEstmCmtmList ==> " + innEstmCmtmList.size());
			
			int P_ESTM_CMTM_RE_SLCT_NGR = parameterMap.getInt("P_ESTM_CMTM_SLCT_NGR") + 1;
			System.err.println("@@@ P_ESTM_CMTM_RE_SLCT_NGR ==> " + P_ESTM_CMTM_RE_SLCT_NGR);
			parameterMap.put("P_ESTM_CMTM_RE_SLCT_NGR", P_ESTM_CMTM_RE_SLCT_NGR);
			
		}else{
			System.err.println("========== [????????? X] ==========");
			
			int P_ESTM_CMTM_SLCT_NGR_CLICK = parameterMap.getInt("P_ESTM_CMTM_SLCT_NGR_CLICK");
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			}
			outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("outEstmCmtmList", outEstmCmtmList);
			
			// ???????????????????????? ??????
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // ?????????????????? : ????????????			
				fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
			
			
			if(P_ESTM_CMTM_SLCT_NGR_CLICK > 0){
				
				outEstmCmtmList = null;
				fixEstmCmtmList = null;
				innEstmCmtmList = null;
				
				System.err.println("/********** ????????? ?????? ?????????????????? ?????? START **********/");
				parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_SLCT_NGR_CLICK);
				
				// ?????????????????? ??????
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
					parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
				}else{
					parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
				}
				outEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("outEstmCmtmList", outEstmCmtmList);
				
				// ???????????????????????? ??????
				if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
					parameterMap.put("P_INO_CMTM_SECD", "OUT");
					parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // ?????????????????? : ????????????
					fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
					trans.put("fixEstmCmtmList", fixEstmCmtmList);
				}else{
					trans.put("fixEstmCmtmList", "");
				}
				
				// ?????????????????? ??????
				parameterMap.put("P_INO_CMTM_SECD", "INN");
				parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
				innEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("innEstmCmtmList", innEstmCmtmList);
				
				
				System.err.println("/********** ????????? ?????? ?????????????????? ?????? END **********/");
			}
		}
		
		FwkDataEntity cmtmAutoBtnCtr = (FwkDataEntity) iproEstmProgDao.selectEstmCmtmAutoBtnCtr(parameterMap);   // T_ESTM_SE_MST
		System.err.println("@@@ cmtmAutoBtnCtr ==> " + cmtmAutoBtnCtr);
		trans.put("cmtmAutoBtnCtr", cmtmAutoBtnCtr);
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		if("Y".equals(P_CMPL_YN)){   // ??????
			
			outEstmCmtmList = null;
			fixEstmCmtmList = null;
			innEstmCmtmList = null;
			
			System.err.println("========== [??????] START ==========");
			
			parameterMap.put("P_SLCT_YN", "Y");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			}
			outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("outEstmCmtmList", outEstmCmtmList);
			
			// ???????????????????????? ??????
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // ?????????????????? : ????????????			
				fixEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
			
			System.err.println("========== [??????] END ==========");
			
		}
		
		
		return trans;
	}
	
	/**
	 * ?????????????????? ??????????????? ?????? ??????????????? ??????
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
		
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ???????????? ??????
		FwkDataEntity estmProcdDetail = iproEstmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjValueList(parameterMap);
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmCmtmLastList = iproEstmProgDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????+???????????? ?????? ??????
		List<FwkDataEntity> estmValueList = iproEstmProgDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????????????? ??????(Tab)
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
	 * ?????????????????? ??????????????? ?????? ??????????????? ??????
	 */
	
	@Override
	public FwkTransferObject estmProgProcdBDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ???????????? ??????
		FwkDataEntity estmProcdDetail = iproEstmProgDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// ???????????? ?????? ??????
		List<FwkDataEntity> estmObjList = iproEstmProgDao.selectEstmObjValueList(parameterMap);
		
		// ??????????????? ??????
		List<FwkDataEntity> estmChrgrList = iproEstmProgDao.selectEstmChrgrList(parameterMap);   // T_ESTM_CMTM
		
		// ???????????????+???????????? ?????? ??????
		List<FwkDataEntity> estmChrgrValueList = iproEstmProgDao.selectEstmChrgrValueList(parameterMap);   // T_ESTM_CMTM
		
		// ????????????????????? ??????(Tab)
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
	 * ?????????????????? ????????? ??????
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
		
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
//		List<FwkDataEntity> estmSeProcdList = iproEstmProgDao.selectEstmSeProcdList(parameterMap);
//		
//		trans.put("estmSeProcdList", estmSeProcdList);
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		// ??????????????? ?????? //
		// ???????????????????????? ?????????
		List<FwkDataEntity> searchColList = iproEstmProgDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
		
		// ???????????? ITEM MAX??? ??????
		int maxSearchColLength = iproEstmProgDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// ??????????????? ??????//
		

		// ?????????????????????????????? ??? ????????? ?????? ??????//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmProgDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// ?????????????????????????????? ??? ????????? ?????? ??????//
		
		
		
		// ?????????????????? ????????? ??????//
		/**
		 *  ???????????? ?????????
		 *  (?????? ??????????????? ????????????.)
		 */
		
		parameterMap.put("pageYn", "Y");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmProgDao.selectEstmResultObjAllList(parameterMap);
		trans.put("estmResultObjAllList", estmResultObjAllList);
		
		
		trans.put("estmResultObjAllListTotCnt", iproEstmProgDao.selectEstmResultObjAllListTotCnt(parameterMap));
//		List<FwkParameterMap> newEstmResultObjAllList = new ArrayList<FwkParameterMap>();
//		/**
//		 * ?????????????????? ???????????? ??????
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
//				if(pClcRulSplit[j].contains("ESTM_PROCD_SEQ")) { // ???????????????
//					System.err.println("pClcRulSplit["+j+"] ::" + pClcRulSplit[j]);
//					String seq = pClcRulSplit[j].replace("ESTM_PROCD_SEQ", "");
//					System.err.println("seq["+j+"] ::" + seq);
//					for(int totScr = 0 ; totScr < estmResultObjTotScrList.size(); totScr++) {
//						FwkDataEntity totScrEntity = estmResultObjTotScrList.get(totScr);
//						if(seq.equals(totScrEntity.getString("ESTM_PROCD_SEQ"))) {
//							sansool += totScrEntity.getString("ESTM_TOT_SCR");
//						}
//					}
//				}else { // ???????????????
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
//			// estmResultObjAllList ???????????? ???????????? ??????
//			
//			newEstmResultObjAllList.add(listParam);
//			System.err.println("listParam ::: " + listParam.toString());
//		}
//		
//		
//		
//		trans.put("estmResultObjAllList", newEstmResultObjAllList);
		
		/**
		 * ???????????? ?????????
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmProgDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());
		
		//(????????????)P_SEARCH_ITEM
		//(??????????????????)P_SEARCH_ITEM_CMTMTOT
		//(????????????)P_SEARCH_ESTM_PROCD_SEQ_TOT
		//(??????????????????????????????)P_SEARCH_ITEM_TOTSUMAT
		//(??????????????????????????????)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
		
		
		String SEARCH_CHECK = "";
		// ????????????
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
		
		//???????????? ??????
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

		//?????????????????? ?????? ????????? ????????? ?????? ?????? ?????? ??????//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmProgDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//?????????????????? ?????? ????????? ????????? ?????? ?????? ?????? ??????//
		
		//?????????????????? ?????? ????????? ??????????????? ?????? ?????? ?????? ??????//
		List<FwkDataEntity> estmResultItemNoList = iproEstmProgDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//?????????????????? ?????? ????????? ??????????????? ?????? ?????? ?????? ??????//
		
		
		/**
		 * ?????????????????????
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmProgDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// ?????????????????? ????????? ??????//
		
		System.err.println("estmResultItemAllList :: :" + estmResultItemAllList.toString());
		
		
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject estmProgResultDetailExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		// ????????????????????? ??????(Tab)
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		// ??????????????? ?????? //
		// ???????????????????????? ?????????
		List<FwkDataEntity> searchColList = iproEstmProgDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
		
		// ???????????? ITEM MAX??? ??????
		int maxSearchColLength = iproEstmProgDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// ??????????????? ??????//
		
		// ?????????????????????????????? ??? ????????? ?????? ??????//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmProgDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// ?????????????????????????????? ??? ????????? ?????? ??????//
		
		// ?????????????????? ????????? ??????//
		/**
		 *  ???????????? ?????????
		 *  (?????? ??????????????? ????????????.)
		 */
		
		parameterMap.put("pageYn", "N");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmProgDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		
		/**
		 * ???????????? ?????????
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmProgDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());
		
		//(????????????)P_SEARCH_ITEM
		//(??????????????????)P_SEARCH_ITEM_CMTMTOT
		//(????????????)P_SEARCH_ESTM_PROCD_SEQ_TOT
		//(??????????????????????????????)P_SEARCH_ITEM_TOTSUMAT
		//(??????????????????????????????)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");

		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
		
		
		String SEARCH_CHECK = "";
		// ????????????
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
		
		//???????????? ??????
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmProgDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}

		//?????????????????? ?????? ????????? ????????? ?????? ?????? ?????? ??????//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmProgDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//?????????????????? ?????? ????????? ????????? ?????? ?????? ?????? ??????//
		
		//?????????????????? ?????? ????????? ??????????????? ?????? ?????? ?????? ??????//
		List<FwkDataEntity> estmResultItemNoList = iproEstmProgDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//?????????????????? ?????? ????????? ??????????????? ?????? ?????? ?????? ??????//
		
		
		/**
		 * ?????????????????????
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmProgDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// ?????????????????? ????????? ??????//
		
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmProgVidoMtngDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT","N");
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ???????????? ????????? ??????
		// ????????? ""
		// ???????????? "CMTM"
		// ???????????? "parameterMap.put("P_ESTM_NO")
		parameterMap.put("P_USER_GBN","");
		parameterMap.put("P_ROLE","admin"); //admin , interviewer , interviewee
		parameterMap.put("P_VIDO_LINK_URL",FwkMessageUtil.getMessage("VIDO.LINK.URL"));
		
		
		//??????????????????
		List<FwkDataEntity> estmVidoList = iproEstmProgDao.selectEstmProgVidoMtngList(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmVidoList", estmVidoList);
		
		
//		parameterMap.put("P_ESTM_PROCD_SEQ","1");
		
//		//??????????????????????????? ??????
//		List<FwkDataEntity> estmVidoMemberList = iproEstmProgDao.selectEstmProgVidoMtngMemberList(parameterMap);   // T_ESTM_MNG_MST
//		trans.put("estmVidoMemberList", estmVidoMemberList);
		
		
		// ????????????????????? ??????
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmProgVidoMtngSave(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// ?????????????????? ??????
		Object P_VIDO_MTNG_NM = parameterMap.get("P_VIDO_MTNG_NM"); //???????????????
		Object P_VIDO_MTNG_PRST_SECD = parameterMap.get("P_VIDO_MTNG_PRST_SECD"); // ????????????????????????
		
		Object P_VIDO_ST_DE = parameterMap.get("P_VIDO_ST_DE");
		Object P_VIDO_ST_HH = parameterMap.get("P_VIDO_ST_HH");
		Object P_VIDO_ST_MM = parameterMap.get("P_VIDO_ST_MM");
		Object P_VIDO_END_DE = parameterMap.get("P_VIDO_END_DE");
		Object P_VIDO_END_HH = parameterMap.get("P_VIDO_END_HH");
		Object P_VIDO_END_MM = parameterMap.get("P_VIDO_END_MM");
		//P_ESTM_PROCD_END_MM
		
		iproEstmProgDao.deleteVidoMtngCmtmDelt(parameterMap); // T_ESTM_VIDO_MTNG_CMTM ??????
		iproEstmProgDao.deleteVidoMtngObjDelt(parameterMap); // T_ESTM_VIDO_MTNG_OBJ ??????
		iproEstmProgDao.deleteVidoMtngDelt(parameterMap); // T_ESTM_VIDO_MTNG ??????
		
		if(P_VIDO_MTNG_PRST_SECD != null){
			
			System.err.println("@@@ P_VIDO_MTNG_PRST_SECD != null @@@");
			
			if(P_VIDO_MTNG_PRST_SECD instanceof String){
				
				System.err.println("@@@ String @@@");
				
				//??????????????????????????? ???????????? insert ????????? ??????. ???, ?????????????????? insert
				if(parameterMap.get("P_VIDO_MTNG_PRST_SECD") == null || "".equals(parameterMap.get("P_VIDO_MTNG_PRST_SECD"))) {
					
					// ????????????????????????
					if(!"".equals(parameterMap.getString("P_VIDO_ST_DE")) && !"".equals(parameterMap.getString("P_VIDO_ST_HH")) && !"".equals(parameterMap.getString("P_VIDO_ST_MM"))){
						parameterMap.put("P_VIDO_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_VIDO_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_VIDO_ST_HH") + parameterMap.get("P_VIDO_ST_MM") + "00"); 
					}
					
					// ????????????????????????
					if(!"".equals(parameterMap.getString("P_VIDO_END_DE")) && !"".equals(parameterMap.getString("P_VIDO_END_HH")) && !"".equals(parameterMap.getString("P_VIDO_END_MM"))){
						parameterMap.put("P_VIDO_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_VIDO_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_VIDO_END_HH") + parameterMap.get("P_VIDO_END_MM") + "00"); 
					}
					
					// ???????????? ?????? MAX+1??? ?????? VIDO_MTNG_SEQ
					int vidoMtngSeq = iproEstmProgDao.selectVidoMtngSeq(parameterMap);
					parameterMap.put("P_VIDO_MTNG_SEQ", vidoMtngSeq);
					parameterMap.put("P_ESTM_PROCD_SEQ", "1");
					// ????????????????????? T_ESTM_VIDO_MTNG ??????
					iproEstmProgDao.insertVidoMtng(parameterMap);
					// ?????????????????????????????? T_ESTM_VIDO_MTNG_CMTM ??????
					iproEstmProgDao.insertVidoMtngCmtm(parameterMap);
					// ??????????????????????????????  T_ESTM_VIDO_MTNG_OBJ ??????
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
						//??????????????????????????? ???????????? insert ????????? ??????. ???, ?????????????????? insert
						if(parameterMap.get("P_VIDO_MTNG_PRST_SECD") == null || "".equals(parameterMap.get("P_VIDO_MTNG_PRST_SECD"))) {
							
							// ????????????????????????
							if(!"".equals(P_VIDO_ST_DEList.get(i)) && !"".equals(P_VIDO_ST_HHList.get(i)) && !"".equals(P_VIDO_ST_MMList.get(i))){
								parameterMap.put("P_VIDO_ST_DT", FwkFormatUtil.formatDate(P_VIDO_ST_DEList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_ST_HHList.get(i) + P_VIDO_ST_MMList.get(i) + "00"); 
							}
							
							// ????????????????????????
							if(!"".equals(P_VIDO_END_DEList.get(i)) && !"".equals(P_VIDO_END_HHList.get(i)) && !"".equals(P_VIDO_END_MMList.get(i))){
								parameterMap.put("P_VIDO_END_DT", FwkFormatUtil.formatDate(P_VIDO_END_DEList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_END_HHList.get(i) + P_VIDO_END_MMList.get(i) + "00"); 
							}
							
							// ???????????????????????? ??????
							//iproEstmProgDao.insertCmtmSpheMpgItemRegist(parameterMap);
							// ???????????? ?????? MAX+1??? ?????? VIDO_MTNG_SEQ
							int vidoMtngSeq = iproEstmProgDao.selectVidoMtngSeq(parameterMap);
							parameterMap.put("P_VIDO_MTNG_SEQ", vidoMtngSeq);
							parameterMap.put("P_ESTM_PROCD_SEQ", "1");
							// ????????????????????? T_ESTM_VIDO_MTNG ??????
							iproEstmProgDao.insertVidoMtng(parameterMap);
							// ?????????????????????????????? T_ESTM_VIDO_MTNG_CMTM ??????
							iproEstmProgDao.insertVidoMtngCmtm(parameterMap);
							// ??????????????????????????????  T_ESTM_VIDO_MTNG_OBJ ??????
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
					
					//??????????????????????????? ???????????? insert ????????? ??????. ???, ?????????????????? insert
					if(parameterMap.get("P_VIDO_MTNG_PRST_SECD") == null || "".equals(parameterMap.get("P_VIDO_MTNG_PRST_SECD"))) {
						
						// ????????????????????????
						if(!"".equals(P_VIDO_ST_DEList[idx]) && !"".equals(P_VIDO_ST_HHList[idx]) && !"".equals(P_VIDO_ST_MMList[idx])){
							parameterMap.put("P_VIDO_ST_DT", FwkFormatUtil.formatDate(P_VIDO_ST_DEList[idx].toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_ST_HHList[idx] + P_VIDO_ST_MMList[idx] + "00"); 
						}
						
						// ????????????????????????
						if(!"".equals(P_VIDO_END_DEList[idx]) && !"".equals(P_VIDO_END_HHList[idx]) && !"".equals(P_VIDO_END_MMList[idx])){
							parameterMap.put("P_VIDO_END_DT", FwkFormatUtil.formatDate(P_VIDO_END_DEList[idx].toString(), "yyyy-MM-dd", "yyyyMMdd") + P_VIDO_END_HHList[idx] + P_VIDO_END_MMList[idx] + "00"); 
						}
						
						// ???????????????????????? ??????
						//iproEstmProgDao.insertCmtmSpheMpgItemRegist(parameterMap);
						// ???????????? ?????? MAX+1??? ?????? VIDO_MTNG_SEQ
						int vidoMtngSeq = iproEstmProgDao.selectVidoMtngSeq(parameterMap);
						parameterMap.put("P_VIDO_MTNG_SEQ", vidoMtngSeq);
						parameterMap.put("P_ESTM_PROCD_SEQ", "1");
						// ????????????????????? T_ESTM_VIDO_MTNG ??????
						iproEstmProgDao.insertVidoMtng(parameterMap);
						// ?????????????????????????????? T_ESTM_VIDO_MTNG_CMTM ??????
						iproEstmProgDao.insertVidoMtngCmtm(parameterMap);
						// ??????????????????????????????  T_ESTM_VIDO_MTNG_OBJ ??????
						iproEstmProgDao.insertVidoMtngObj(parameterMap);
					}
				}
			}
			
		}//P_ESTM_CMTM_NO != null
		
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ????????????????????? ??????
		List<FwkDataEntity> estmTabList = iproEstmProgDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		trans.put("estmMngMstDetail", estmMngMstDetail);
		trans.put("P_ESTM_NO_TRANS", parameterMap.get("P_ESTM_NO"));
		return trans;
	}
	

	@Override
	public void estmPscdUpdt(final FwkParameterMap parameterMap) throws Exception {
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		 
		
		// ?????????????????? T_ESTM_OBJ ???????????? ??????????????? UPDATE ?????????.
		if("C001".equals(parameterMap.getString("P_ESTM_PSCD"))){
			iproEstmProgDao.updateEstmEndObjSlctYn(parameterMap);
			
			parameterMap.put("P_ESTM_FRM_NO", "SVY2021-00001");
			
			// ???????????? ??? ?????????????????? ?????? T_ESTM_SVY_FRM
			iproEstmProgDao.insertEstmSvyFrm(parameterMap);   // T_ESTM_SVY_FRM INSERT
			
		}
		
		// ?????????????????? ??????
		if("C001".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "????????????");
		}else if("C002".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "????????????");
		}else if("A004".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "??????????????????????????????");
		}else if("A003".equals(parameterMap.getString("P_ESTM_PSCD"))){
			parameterMap.put("P_RMK", "??????????????????????????????");
		}
		
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	@Override
	public FwkTransferObject estmProgUpdt(final FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		// ????????????????????????
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_ST_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_ST_MM"))){
			parameterMap.put("P_TOTL_ESTM_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_ST_HH") + parameterMap.get("P_TOTL_ESTM_ST_MM") + "00"); 
		}
		
		// ????????????????????????
		if(!"".equals(parameterMap.getString("P_TOTL_ESTM_END_DE")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_HH")) && !"".equals(parameterMap.getString("P_TOTL_ESTM_END_MM"))){
			parameterMap.put("P_TOTL_ESTM_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TOTL_ESTM_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_TOTL_ESTM_END_HH") + parameterMap.get("P_TOTL_ESTM_END_MM") + "00"); 
		}
		
		// ???????????? ????????? ??????
		iproEstmProgDao.updateEstmMngMst(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		if(parameterMap.get("P_ESTM_PROCD_SECD") != null) {
			
			// ???????????? ??????
			iproEstmProgDao.deleteEstmProcd(parameterMap);   // T_ESTM_PROCD DELETE
			
			// ?????????????????? ??????
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
					
					// ????????????????????????
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_ST_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_ST_MM"))){
						parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_ST_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_ST_HH") + parameterMap.get("P_ESTM_PROCD_ST_MM") + "00"); 
					}
					
					// ????????????????????????
					if(!"".equals(parameterMap.getString("P_ESTM_PROCD_END_DE")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_HH")) && !"".equals(parameterMap.getString("P_ESTM_PROCD_END_MM"))){
						parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ESTM_PROCD_END_DE").toString(), "yyyy-MM-dd", "yyyyMMdd") + parameterMap.get("P_ESTM_PROCD_END_HH") + parameterMap.get("P_ESTM_PROCD_END_MM") + "00"); 
					}
					
					// ???????????? ??????
					iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
					
					// ?????????????????? ??????
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
						
						// ????????????????????????
						if(!"".equals(estmProcdStDeList.get(i)) && !"".equals(estmProcdStHhList.get(i)) && !"".equals(estmProcdStMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhList.get(i) + estmProcdStMmList.get(i) + "00"); 
						}
						
						// ????????????????????????
						if(!"".equals(estmProcdEndDeList.get(i)) && !"".equals(estmProcdEndHhList.get(i)) && !"".equals(estmProcdEndMmList.get(i))){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeList.get(i).toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhList.get(i) + estmProcdEndMmList.get(i) + "00"); 
						}
						
						// ???????????? ??????
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// ?????????????????? ??????
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
						
						// ????????????????????????
						if(!"".equals(estmProcdStDeStr[i]) && !"".equals(estmProcdStHhStr[i]) && !"".equals(estmProcdStMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_ST_DT", FwkFormatUtil.formatDate(estmProcdStDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdStHhStr[i] + estmProcdStMmStr[i] + "00"); 
						}
						
						// ????????????????????????
						if(!"".equals(estmProcdEndDeStr[i]) && !"".equals(estmProcdEndHhStr[i]) && !"".equals(estmProcdEndMmStr[i])){
							parameterMap.put("P_ESTM_PROCD_END_DT", FwkFormatUtil.formatDate(estmProcdEndDeStr[i].toString(), "yyyy-MM-dd", "yyyyMMdd") + estmProcdEndHhStr[i] + estmProcdEndMmStr[i] + "00"); 
						}
						
						// ???????????? ??????
						iproEstmProgDao.insertEstmProcd(parameterMap);   // T_ESTM_PROCD INSERT
						
						// ?????????????????? ??????
						iproEstmProgDao.insertEstmProcdFrm(parameterMap);   // T_ESTM_PROCD_FRM INSERT
					}
				}
			}
		}
		
		if(!"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			// ???????????? ??????
			parameterMap.put("P_ESTM_FSCD", "MST");
			parameterMap.put("P_RMK", "");
			iproEstmProgDao.deleteEstmFile(parameterMap);   // T_ESTM_FILE DELETE
			iproEstmProgDao.insertEstmFile(parameterMap);   // T_ESTM_FILE INSERT			
		}
		
		/********** ???????????? ???????????? ?????? start **********/
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "estm";
		FwkParameterMap signFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_SIGN_FILE", contextPath);   // ???????????? ????????????
		
		
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

			// ?????? ???????????? ??????
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
		
		// ????????? ?????? DELETE_AT = 'N' ??????
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
				
		/********** ???????????? ???????????? ?????? end **********/
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "????????????");
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
			
			Object P_OBJ_YN = parameterMap.get("P_OBJ_YN");   // ???????????? ??? ?????????????????? ????????? ??????????????? ??????
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
						// ???????????? ??????
						
						parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
						System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
						
						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
					}else{
						parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						
						System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
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
								// ???????????? ??????
								System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
								iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
							}else{
								parameterMap.put("P_ESTM_OBJ_SEQ", estmObjSeqList.get(i));
								parameterMap.put("P_DEL_AT", delAtList.get(i));
								
								System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
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
//						// ???????????? ??????
//						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT
//					}
//				}
			}
		}
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "???????????? ??????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	@Override
	public void estmProgObjDelete(final FwkParameterMap parameterMap) throws Exception {
		
		if(parameterMap.get("P_OBJ_YN") != null) {
			
			Object P_OBJ_YN = parameterMap.get("P_OBJ_YN");   // ???????????? ??? ?????????????????? ????????? ??????????????? ??????
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
						// ???????????? ??????
						
						parameterMap.put("P_RSDN_NO", parameterMap.get("P_RSDN_NO_1") + "" + parameterMap.get("P_RSDN_NO_2"));
						System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
						
						//iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
					}else{
						parameterMap.put("P_ESTM_OBJ_SEQ", parameterMap.get("P_ESTM_OBJ_SEQ"));
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						
						System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
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
								// ???????????? ??????
								System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
								//iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT						
							}else{
								parameterMap.put("P_ESTM_OBJ_SEQ", estmObjSeqList.get(i));
								parameterMap.put("P_DEL_AT", delAtList.get(i));
								
								System.err.println("@@@ T_ESTM_OBJ ?????? @@@");
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
//						// ???????????? ??????
//						iproEstmProgDao.insertEstmObj(parameterMap);   // T_ESTM_OBJ INSERT
//					}
//				}
			}
		}
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "???????????? ??????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	

	@Override
	public void estmCntcObjList(final FwkParameterMap parameterMap) {
		
		// ???????????? ??????
		iproEstmProgDao.deleteEstmObj(parameterMap);   // T_ESTM_OBJ DELETE

		// ????????????????????????_??????????????? ?????? -> T_ESTM_OBJ INSERT
		iproEstmProgDao.insertEstmCntcObj(parameterMap);   // V_ESTM_ANNC_TPI_INT INSERT
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "????????????????????????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
	}

	@Override
	public void estmObjFileUpdt(final FwkParameterMap parameterMap) throws Exception {
		
		iproEstmProgDao.updateEstmObjFileGrpNo(parameterMap);   // T_ESTM_OBJ UPDATE
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "???????????? ???????????? ??????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	@Override
	public void estmVidoFileUpdt(final FwkParameterMap parameterMap) throws Exception {
		
		iproEstmProgDao.updateEstmVidoFileGrpNo(parameterMap);   // T_ESTM_OBJ UPDATE
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "???????????? ???????????? ??????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	

	@Override
	public void estmProcdPscdUpdt(final FwkParameterMap parameterMap) throws Exception {
		iproEstmProgDao.updateEstmProcdPscd(parameterMap);   // T_ESTM_PROCD UPDATE
		
		String P_ESTM_PROCD_NM = parameterMap.getString("P_ESTM_PROCD_NM");   // ???????????????
		
		// ?????????????????? ??????
		if("B001".equals(parameterMap.getString("P_ESTM_PROCD_PSCD"))){
			parameterMap.put("P_RMK", "[ " + P_ESTM_PROCD_NM + " ] ????????????");
		}else if("B002".equals(parameterMap.getString("P_ESTM_PROCD_PSCD"))){
			parameterMap.put("P_RMK", "[ " + P_ESTM_PROCD_NM + " ] ????????????");
		}
		
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
		/**
		 * 2021-05-04
		 * ?????????
		 * ??????????????? ?????????????????? ????????? ?????? ??????
		 */
		if("B002".equals(parameterMap.getString("P_ESTM_PROCD_PSCD"))){
			FwkDataEntity thisProcdEntity = iproEstmProgDao.selectEstmProcdSecdDetail(parameterMap);
			
			if(!"C".equals(thisProcdEntity.getString("ESTM_PROCD_SECD"))) {
				
				/**
				 * ???????????? ????????????
				 */
				// ??????????????? ??????????????????????????? ????????????
				FwkDataEntity mxmnScrExcpYnData = iproEstmProgDao.selectMxMnScrExcpYnColumn(parameterMap);
				
				// ??????????????? A:????????????, B:????????????
				// ???????????? ?????????????????? ???????????? MXMN_SCR_EXCP_YN????????? ???????????? N ??????
				if("A".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", mxmnScrExcpYnData.getString("MXMN_SCR_EXCP_YN"));
				}else if("B".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", "N");
				}
					
				// T_ESTM_OBJ_SLCT ??????
				iproEstmProgDao.deleteEstmObjSlct(parameterMap);
				
				// T_ESTM_OBJ_SLCT ??????
				iproEstmProgDao.insertEstmObjSlct(parameterMap);
				
				// ????????? ???????????? ????????????.
				List<FwkDataEntity> estmObjSlctList = iproEstmProgDao.selectEstmObjSlctList(parameterMap);
				
				for(int i = 0; i < estmObjSlctList.size(); i++) {
					FwkDataEntity objSlctDataEntity = estmObjSlctList.get(i);
					parameterMap.put("P_ESTM_OBJ_SEQ", objSlctDataEntity.getString("ESTM_OBJ_SEQ"));
					parameterMap.put("P_ESTM_RNK", objSlctDataEntity.getString("RANK"));
					
					iproEstmProgDao.updateEstmObjSlctRankUpdt(parameterMap);
				}
				
				// ?????? ???????????? ??????
				parameterMap.put("P_SLCT_YN", "Y");
				iproEstmProgDao.updateEstmObjSlctYn(parameterMap);


				// ??????????????? ??????????????? ??????
				FwkDataEntity nextProcdSeqEnt =  iproEstmProgDao.selectEstmNextProcdSeqAt(parameterMap);
				
				/**
				 * ??????????????? ??????????????????????????? ????????? ?????? ????????????
				 * ????????? ????????? ?????????????????????????????? ???????????? ???????????? ??????????????? ?????? ??? ??? ????????? insert?????????.
				 * 
				 */
				if(!"0".equals(nextProcdSeqEnt.get("CNT"))) {
					
					iproEstmProgDao.deleteEstmObjNextProcdSeq(parameterMap);
					// ??????????????? ???????????? ??????????????? ??????????????? insert ?????????.		
					iproEstmProgDao.insertEstmObjNextProcdSeq(parameterMap);
				}
			}else {
				/**
				 * ???????????? ????????????
				 */
				// ??????????????? ??????????????????????????? ????????????
				FwkDataEntity mxmnScrExcpYnData = iproEstmProgDao.selectMxMnScrExcpYnColumn(parameterMap);
				
				// ??????????????? A:????????????, B:????????????
				// ???????????? ?????????????????? ???????????? MXMN_SCR_EXCP_YN????????? ???????????? N ??????
				if("A".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", mxmnScrExcpYnData.getString("MXMN_SCR_EXCP_YN"));
				}else if("B".equals(thisProcdEntity.getString("ESTR_SECD"))) {
					parameterMap.put("P_MXMN_SCR_EXCP_YN", "N");
				}
					
				// T_ESTM_OBJ_SLCT ??????
				iproEstmProgDao.deleteEstmObjSlct(parameterMap);
				
				// T_ESTM_OBJ_SLCT ??????
				iproEstmProgDao.insertEstmObjSlct(parameterMap);
				
				// ????????? ???????????? ????????????.
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
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		
		// ESTM_CMTM_SLCT_MTHD A : ????????????
		// ESTM_CMTM_SLCT_MTHD B	 : ????????????
		// ESTM_CMTM_SLCT_MTHD C	 : ????????????+????????????

		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		String INN_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("INN_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		
		int OUT_ESTM_CMTM_CNT = estmMngMstDetail.getInt("OUT_ESTM_CMTM_CNT");   // ?????????????????????
		int OUT_ESTM_CMTM_TMES = estmMngMstDetail.getInt("OUT_ESTM_CMTM_TMES");   // ????????????????????????
		
		int FIX_ESTM_CMTM_CNT = estmMngMstDetail.getInt("FIX_ESTM_CMTM_CNT");   // ?????????????????????
		
		int INN_ESTM_CMTM_CNT = estmMngMstDetail.getInt("INN_ESTM_CMTM_CNT");   // ?????????????????????
		int INN_ESTM_CMTM_TMES = estmMngMstDetail.getInt("INN_ESTM_CMTM_TMES");   // ????????????????????????
		
		
		System.err.println("@@@ ?????????????????????????????? ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ ????????????????????? ==> " + OUT_ESTM_CMTM_CNT);
		System.err.println("@@@ ???????????????????????? ==> " + OUT_ESTM_CMTM_TMES);
		
		System.err.println("@@@ ????????????????????? ==> " + FIX_ESTM_CMTM_CNT);
		
		System.err.println("@@@ ?????????????????????????????? ==> " + INN_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ ????????????????????? ==> " + INN_ESTM_CMTM_CNT);
		System.err.println("@@@ ???????????????????????? ==> " + INN_ESTM_CMTM_TMES);
		
		int OUT_SLCT_CNT = 0;
		int INN_SLCT_CNT = INN_ESTM_CMTM_CNT * INN_ESTM_CMTM_TMES;   // ????????????????????? * ????????????????????????
		
		// ?????????????????? ???????????? Random?????? ???????????? insert
		parameterMap.put("P_ESTM_SPHE_SECD", estmMngMstDetail.get("ESTM_SPHE_SECD"));   // ??????????????????
		
		if("OUT".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE
			
			
			if("B".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // ????????????
				
				OUT_SLCT_CNT = OUT_ESTM_CMTM_CNT * OUT_ESTM_CMTM_TMES;   // ????????????????????? * ????????????????????????
				System.err.println("@@@ (????????????) ????????????????????? ==> " + OUT_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "OUT"); 
				parameterMap.put("P_SLCT_CNT", OUT_SLCT_CNT);   // ???????????????????????? ?????? 
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // ?????????????????? - T_ESTM_CMTM INSERT
			}
			
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // ????????????+????????????
				
				OUT_SLCT_CNT = ( OUT_ESTM_CMTM_CNT - FIX_ESTM_CMTM_CNT ) * OUT_ESTM_CMTM_TMES;
				
				System.err.println("@@@ (????????????+????????????) ????????????????????? ==> " + OUT_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "OUT"); 
				parameterMap.put("P_SLCT_CNT", OUT_SLCT_CNT);   // ???????????????????????? ?????? 
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // ?????????????????? - T_ESTM_CMTM INSERT
			}
		}
		
		if("INN".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			
			parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE
			if(!"A".equals(INN_CMTM_SLCT_MTHD_SECD)){
				System.err.println("@@@ ????????????????????? ==> " + INN_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "INN");
				parameterMap.put("P_SLCT_CNT", INN_SLCT_CNT);   // ???????????????????????? ??????
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // ?????????????????? - T_ESTM_CMTM INSERT
			}
		}
				
		parameterMap.put("P_RMK", "????????????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	
	@Override
	public void estmCmtmReAutoSlct(final FwkParameterMap parameterMap) {
		
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ESTM_CMTM_SLCT_MTHD A : ????????????
		// ESTM_CMTM_SLCT_MTHD B	 : ????????????
		// ESTM_CMTM_SLCT_MTHD C	 : ????????????+????????????
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		String INN_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("INN_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		
		
		// ?????????????????? ??????
		parameterMap.put("P_INO_CMTM_SECD_S", "OUT");
		parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
		parameterMap.put("P_SLCT_SECD", "");
//		parameterMap.put("P_ESTM_CMTM_RE_SLCT_NGR", parameterMap.getInt("P_ESTM_CMTM_RE_SLCT_NGR"));   //
//		List<FwkDataEntity> outEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		
		List<FwkDataEntity> outEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
		
		
		// ?????????????????? ??????
		parameterMap.put("P_INO_CMTM_SECD_S", "INN");
//		List<FwkDataEntity> innEstmCmtmList = iproEstmProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
		parameterMap.put("P_SLCT_SECD", "");
		List<FwkDataEntity> innEstmCmtmList = iproEstmProgDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
		
		
		int OUT_ESTM_CMTM_CNT = estmMngMstDetail.getInt("OUT_ESTM_CMTM_CNT");   // ?????????????????????
		int OUT_ESTM_CMTM_TMES = estmMngMstDetail.getInt("OUT_ESTM_CMTM_TMES");   // ????????????????????????
		int OUT_CMTM_SLCT_CNT = estmMngMstDetail.getInt("OUT_CMTM_SLCT_CNT");   // ????????? ?????????????????????
		
		int INN_ESTM_CMTM_CNT = estmMngMstDetail.getInt("INN_ESTM_CMTM_CNT");   // ?????????????????????
		int INN_ESTM_CMTM_TMES = estmMngMstDetail.getInt("INN_ESTM_CMTM_TMES");   // ????????????????????????
		int INN_CMTM_SLCT_CNT = estmMngMstDetail.getInt("INN_CMTM_SLCT_CNT");   // ????????? ?????????????????????
		
		int FIX_ESTM_CMTM_CNT = estmMngMstDetail.getInt("FIX_ESTM_CMTM_CNT");   // ?????????????????????
		
		int P_ESTM_CMTM_RE_SLCT_NGR = parameterMap.getInt("P_ESTM_CMTM_RE_SLCT_NGR");   // ???????????????????????? ????????????????????????
		
		System.err.println("@@@ ???????????????????????? ???????????????????????? ==> " + P_ESTM_CMTM_RE_SLCT_NGR);
		
		
		
		parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_RE_SLCT_NGR);
		
		System.err.println("@@@ ?????????????????????????????? ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ ????????????????????? ==> " + OUT_ESTM_CMTM_CNT);
		System.err.println("@@@ ???????????????????????? ==> " + OUT_ESTM_CMTM_TMES);
		System.err.println("@@@ ????????? ????????????????????? ==> " + OUT_CMTM_SLCT_CNT);
		
		System.err.println("@@@ ?????????????????????????????? ==> " + INN_CMTM_SLCT_MTHD_SECD);
		System.err.println("@@@ ????????????????????? ==> " + INN_ESTM_CMTM_CNT);
		System.err.println("@@@ ???????????????????????? ==> " + INN_ESTM_CMTM_TMES);
		System.err.println("@@@ ????????? ????????????????????? ==> " + INN_CMTM_SLCT_CNT);
		
		System.err.println("@@@ ????????????????????? ==> " + FIX_ESTM_CMTM_CNT);
		
		int OUT_SLCT_CNT = 0;
		int INN_SLCT_CNT = 0;
		
		
		// ( ???????????? ??? - ????????? ???????????? ??? ) * ?????????????????? ?????? Random?????? ???????????? insert
		parameterMap.put("P_ESTM_SPHE_SECD", estmMngMstDetail.get("ESTM_SPHE_SECD"));   // ??????????????????
		
		if("OUT".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE

			
			OUT_SLCT_CNT = (OUT_ESTM_CMTM_CNT - OUT_CMTM_SLCT_CNT) * OUT_ESTM_CMTM_TMES;   // ( ???????????? ??? - ????????? ???????????? ??? ) * ??????????????????
			
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			parameterMap.put("P_SLCT_CNT", OUT_SLCT_CNT);   // ???????????????????????? ??????
			
			if("B".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // ????????????
				System.err.println("@@@ (????????????) ????????????????????? ==> " + OUT_SLCT_CNT);	
			}else if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){   // ???????????? + ????????????
				System.err.println("@@@ (???????????? + ????????????) ????????????????????? ==> " + OUT_SLCT_CNT);
			}
			
			
			ArrayList<String> estmCmtmNoNotInList = new ArrayList<String>();
			for (int i = 0; i < outEstmCmtmList.size(); i++) {
				String ESTM_CMTM_NO = outEstmCmtmList.get(i).getString("ESTM_CMTM_NO");
				estmCmtmNoNotInList.add(ESTM_CMTM_NO);
			}
			parameterMap.put("P_ESTM_CMTM_NO_NOT_IN_LIST", estmCmtmNoNotInList);
			
			System.err.println("@@@ ??????/????????? ???????????????????????? ::: " + estmCmtmNoNotInList.toString());
			
			iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // ?????????????????? - T_ESTM_CMTM INSERT
			
		}
		
		if("INN".equals(parameterMap.get("P_INO_CMTM_SECD"))){
			
			parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			iproEstmProgDao.deleteEstmCmtm(parameterMap);   // T_ESTM_CMTM DELETE
			
			if("B".equals(INN_CMTM_SLCT_MTHD_SECD)){   // ????????????
				
				INN_SLCT_CNT = (INN_ESTM_CMTM_CNT - INN_CMTM_SLCT_CNT) * INN_ESTM_CMTM_TMES;   // ( ???????????? ??? - ????????? ???????????? ??? ) * ??????????????????
				System.err.println("@@@ (????????????) ????????????????????? ==> " + INN_SLCT_CNT);
				parameterMap.put("P_INO_CMTM_SECD", "INN"); 
				parameterMap.put("P_SLCT_CNT", INN_SLCT_CNT);   // ???????????????????????? ??????
				
				ArrayList<String> estmCmtmNoNotInList = new ArrayList<String>();
				for (int i = 0; i < innEstmCmtmList.size(); i++) {
					String ESTM_CMTM_NO = innEstmCmtmList.get(i).getString("ESTM_CMTM_NO");
					estmCmtmNoNotInList.add(ESTM_CMTM_NO);
				}
				parameterMap.put("P_ESTM_CMTM_NO_NOT_IN_LIST", estmCmtmNoNotInList);
				
				System.err.println("@@@ ??????/????????? ???????????????????????? ::: " + estmCmtmNoNotInList.toString());
				
				iproEstmProgDao.insertEstmCmtmAutoSlct(parameterMap);   // ?????????????????? - T_ESTM_CMTM INSERT
			}
		}
				
		parameterMap.put("P_RMK", "???????????????????????????");
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
		
		// ????????? ?????? DELETE_AT = 'N' ??????
		String deleteEstmCmtmNo = parameterMap.getString("P_DELETE_ESTM_CMTM_NO");
		
		if(!"".equals(deleteEstmCmtmNo)){
			String[] arrDeleteEstmCmtmNo = deleteEstmCmtmNo.split(",");
			
			for (int i = 0; i < arrDeleteEstmCmtmNo.length; i++) {
				parameterMap.put("P_ESTM_CMTM_NO", arrDeleteEstmCmtmNo[i]);
				
				iproEstmProgDao.deleteEstmCmtm(parameterMap);
			}
		}
		
		parameterMap.put("P_RMK", "??????????????????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public FwkTransferObject estmStPsblYnChck(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		String rsn_msg = "";
		String rsn_code = "";
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		String CMTM_SLCT_CMPL_YN = estmMngMstDetail.getString("CMTM_SLCT_CMPL_YN");   // ??????????????????????????????
		System.err.println("@@@ CMTM_SLCT_CMPL_YN ==> " + CMTM_SLCT_CMPL_YN);
		
		// ?????????????????? ?????????????????? ????????? ??? count ??????
		int outCmtmRsdnNoNullCnt = iproEstmProgDao.selectOutCmtmRsdnNoNullCnt(parameterMap);   // T_ESTM_CMTM
		System.err.println("@@@ outCmtmRsdnNoNullCnt ==> " + outCmtmRsdnNoNullCnt);
		
		// ???????????? count
		int estmObjCnt = iproEstmProgDao.selectEstmObjCnt(parameterMap);   // T_ESTM_OBJ
		System.err.println("@@@ estmObjCnt ==> " + estmObjCnt);
		
		if(outCmtmRsdnNoNullCnt > 0){
			rsn_msg = "?????????????????? ????????????????????? ?????????????????? ????????????.";
			rsn_code = "outCmtmRsdnNoNullCnt";
		}
		
		if(!"Y".equals(CMTM_SLCT_CMPL_YN)){
			rsn_msg = "????????????????????? ???????????? ?????? ????????????.";
			rsn_code = "cmtmSlctCmplYN_N";
		}
		
		if(estmObjCnt == 0){
			rsn_msg = "??????????????? ???????????? ????????????.";
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

	// ???????????? ??? ?????? ??????
	public void estmSendMail(final FwkParameterMap parameterMap) throws Exception {
		
		MailSendUtil mailSendUtil = new MailSendUtil();
		
		FwkParameterMap mailParameterMap = new FwkParameterMap();
		
		mailParameterMap.put("P_ESTM_NO", parameterMap.get("P_ESTM_NO"));
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmProgDao.selectEstmMngMstDetail(mailParameterMap);   // T_ESTM_MNG_MST
		
		//???????????? ??????
		mailParameterMap.put("P_MSG_SECD", "MAIL");
		mailParameterMap.put("P_MSG_SN", "1");
		//parameterMap.put("P_MSG_OBJ_ID", "");
		FwkDataEntity msgDetail = (FwkDataEntity) iproCommDefaultDao.selectMsgContents(mailParameterMap);   // T_ESTM_MNG_MST
		String mailCntn = "";
		String mailTitle = "";
		
		//?????? ??????
		mailTitle = msgDetail.getString("MSG_TTL");
		mailTitle = mailTitle.replace("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
		mailParameterMap.put("P_MSG_TTL",mailTitle);
		
		//?????? ??????
		mailCntn = msgDetail.getString("MSG_CNTN");
		mailCntn = mailCntn.replaceAll("#ESTM_NM#", estmMngMstDetail.getString("ESTM_NM"));
		mailCntn = mailCntn.replaceAll("#ESTM_CHRG_DEPT_NM#", estmMngMstDetail.getString("ESTM_CHRG_DEPT_NM"));
		mailCntn = mailCntn.replaceAll("#ESTM_CHRGR_NM#", estmMngMstDetail.getString("ESTM_CHRGR_NM"));
		mailParameterMap.put("P_MSG_CNTN",mailCntn);
		
		//???????????? ??????
		mailParameterMap.put("P_SLCT_YN", "Y");
		List<FwkDataEntity> estmCmtmList =  iproEstmProgDao.selectEstmCmtmList(mailParameterMap);   // T_ESTM_CMTM
		
		for(int i = 0; i<estmCmtmList.size(); i++){
			//?????? ?????????
			mailParameterMap.put("P_TO_MAIL", estmCmtmList.get(i).getString("EMAL"));
			//????????????
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
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "????????????????????????");
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
						
					// ????????? ???????????? ?????????????????? update
					iproEstmProgDao.updateEstmCmtmRsdnNo(parameterMap);   // T_ESTM_CMTM UPDATE
				}
			}
						
		}
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "???????????? ?????????????????? ??????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	
	
	/**
	 * ?????????????????? ??? ????????? ????????? ?????? ??????
	 * @param cell
	 * @return
	 */
	public String cellValue(Cell cell) {
		 
        String value = null;
        if (cell == null) value = "";
        else {
            switch (cell.getCellType()) { //cell ????????? ?????? ????????? ??????
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
						
					// ????????? ???????????? ???????????? update
					System.err.println("@@@ ArrayList parameterMap ==> " + parameterMap);
					iproEstmProgDao.updateEstmCmtmHldfYn(parameterMap);   // T_ESTM_CMTM UPDATE
					
					iproEstmProgDao.updateEstmCmtmPoolHldfYn(parameterMap);   // T_ESTM_CMTM_POOL_MST UPDATE
				}
			}
						
		}
		
		// ?????????????????? ??????
		parameterMap.put("P_RMK", "???????????? ???????????? ??????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	@Override
	public FwkTransferObject estmEnd(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 2021-05-04
		 * ?????????
		 * ??????????????? ???????????? ????????? ?????? ??????
		 */
		//??????
		// ??????????????? ??????????????????????????? ????????????
		FwkDataEntity mxmnScrExcpYnData = iproEstmProgDao.selectMxMnScrExcpYnColumn(parameterMap);
		
		parameterMap.put("P_MXMN_SCR_EXCP_YN", mxmnScrExcpYnData.getString("MXMN_SCR_EXCP_YN"));
		
		// T_ESTM_OBJ_SLCT ??????
		iproEstmProgDao.deleteEstmObjSlct(parameterMap);
		
		// T_ESTM_OBJ_SLCT ??????
		iproEstmProgDao.insertEstmObjSlct(parameterMap);
		
		// ????????? ???????????? ????????????.
		List<FwkDataEntity> estmObjSlctList = iproEstmProgDao.selectEstmObjSlctList(parameterMap);
		
		for(int i = 0; i < estmObjSlctList.size(); i++) {
			FwkDataEntity objSlctDataEntity = estmObjSlctList.get(i);
			parameterMap.put("P_ESTM_OBJ_SEQ", objSlctDataEntity.getString("ESTM_OBJ_SEQ"));
			parameterMap.put("P_ESTM_RNK", objSlctDataEntity.getString("RANK"));
			
			iproEstmProgDao.updateEstmObjSlctRankUpdt(parameterMap);
		}
			
		//??????
		
		trans.put("resultCode", 1);
		
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmASlct(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 2021-05-04
		 * ?????????
		 * ??????????????? ?????????????????? ????????? ?????? ??????
		 */
		
		Object P_ESTM_SLCT_YN = parameterMap.get("P_ESTM_SLCT_YN");
		Object P_ESTM_OBJ_SEQ = parameterMap.get("P_ESTM_OBJ_SEQ");
		
		
		/**
		 * ???????????? 
		 * ??????????????? ?????????????????? ??????????????? ????????????????????? ???
		 * ??????????????? N?????? ???????????? ????????? ????????? ????????? ?????????. 
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
		

		// ??????????????? ??????????????? ??????
		FwkDataEntity nextProcdSeqEnt =  iproEstmProgDao.selectEstmNextProcdSeqAt(parameterMap);
		if(!"0".equals(nextProcdSeqEnt.get("CNT"))) {
			// ??????????????? ???????????? ??????????????? ??????????????? insert ?????????.
			iproEstmProgDao.deleteEstmObjNextProcdSeq(parameterMap);
			iproEstmProgDao.insertEstmObjNextProcdSeq(parameterMap);
		}
		
		
		trans.put("resultCode", 1);
		return trans;
	}

	@Override
	public FwkTransferObject estmStUpdt(final FwkParameterMap parameterMap) throws CertificateFailException, Exception {
		// ????????????
		
		FwkTransferObject trans = new FwkTransferObject();
		
		String errMsg = "";
		String errCode = "";
		EwSignBiz pki = new EwSignBiz();
		
		String filePath ="";   // ????????????
		String fileHashValue = "";   // ???????????????
		String ESTM_SIGN_FILE_GRP_NO = "";   // ???????????? ???????????? FILE_GRP_NO
		
		if("A005".equals(parameterMap.get("P_ESTM_PSCD"))){				//????????????
			//????????????
			
			parameterMap.put("P_CD_ID_S", "SEND_MAIL");
			List<FwkDataEntity> mailSendList = iproCommDefaultDao.selectCodeList(parameterMap);
			LOG.debug("========== estmSendMail Start ==========");
			if("Y".equals(mailSendList.get(0).get("CD_DTL_ID").toString())){
				estmSendMail(parameterMap);
			}
			LOG.debug("========== estmSendMail End ==========");
		}
		
		try {
			
			
			// ???????????? ???????????? ???????????? ???????????? ??????
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
							String FILE_SN = signFileInfo.getString("FILE_SN"); // ??????????????????
							filePath = signFileInfo.getString("FILE_LCTN") + File.separator + signFileInfo.getString("SV_FILE_NM");   // ????????????		
							
							FwkParameterMap fileParam = new FwkParameterMap();
							filePath = filePath.replace("\\", "/");
							String FILE_GRP_NO = signFileInfo.getString("FILE_GRP_NO"); // ??????????????????
							
							fileParam.put("P_FILE_SN", FILE_SN);   // ??????????????????
							fileParam.put("P_FILE_GRP_NO", FILE_GRP_NO);   // ??????????????????
							
							fileHashValue = "";
							
							try{
								fileHashValue = pki.getHashFile(filePath);//??????????????????
								
								System.err.println("@@@ fileHashValue ==> " + fileHashValue);
								
								//if(fileHashValue == null || fileHashValue == "" || "".equals(fileHashValue)){
								if("".equals(fileHashValue)){
									System.err.println("=== null ===");
									errCode = "fail";
									errMsg = "???????????? ?????? ????????? ??????????????????.";
									
									throw new CertificateFailException(errMsg);
								}else{
									System.err.println("=== not null ===");
									errCode = "success";
									errMsg = "???????????? ?????? ????????? ??????????????????.";
								}
								
							} catch (Exception e){
								errCode = "fail";
								errMsg = "???????????? ?????? ????????? ??????????????????.";
								LOG.debug("???????????? ?????? ????????? ??????????????????.");
								 
								System.err.println(">>> ???????????? ?????? ????????? ??????????????????. <<<");
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
			
			
			// ????????? ???/?????? ???????????? ??????
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
						parameterMap.put("P_ESTM_CMTM_NO", cmtmSlctInfo.get("ESTM_CMTM_NO")); 	  // ??????????????????
						
						if(estmSignFileGrpNo != null){
							for(int k=0; k < setFileHash.size(); k++){
								FwkParameterMap fileMap = setFileHash.get(k);
								
								if(!"".equals(fileMap.getString("P_FILE_HASH_VAL"))){
									
									parameterMap.put("P_FILE_GRP_NO", fileMap.getString("P_FILE_GRP_NO"));		// ?????? ????????????
									parameterMap.put("P_FILE_SN", fileMap.getString("P_FILE_SN")); 			// ?????? ????????????
									parameterMap.put("P_FILE_HASH_VAL", fileMap.getString("P_FILE_HASH_VAL")); 	// ?????? ?????? ???
									
									System.err.println("@@@ fileMap ==> " + fileMap);
									iproEstmProgDao.insertEstmFileSign(parameterMap);   // T_ESTM_FILE_SIGN INSERT
									
									errCode = "success";
								}else{
									errCode = "fail";
									errMsg = "???????????? ?????? ????????? ??????????????????.";
									
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
		// CASE_1 : ????????? ????????????????????? ?????? ??????????????? ??????
		// CASE_2 : ?????? ??????????????? ????????????????????? ????????? ????????????????????? ??????
		
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
				
				System.err.println("@@@ ?????????????????? : " + parameterMap.get("P_ESTM_CMTM_NO") + ", ??????????????? : " + parameterMap.get("P_ESTM_CMTM_NM") + ", ?????????????????? : " + parameterMap.get("P_RSDN_NO"));
				
				System.err.println("@@@ CASE_1 ==> " + CASE_1);
				System.err.println("@@@ CASE_2 ==> " + CASE_2);

				if(CASE_1 > 0){
					msg += "[ " + parameterMap.get("P_ESTM_CMTM_NM") + " ]?????? ????????????????????? ?????? ??????????????? ???????????????.";
				}
				
				if(CASE_2 > 0){
					msg += "\n[ " + parameterMap.get("P_ESTM_CMTM_NM") + " ]?????? ????????????????????? ????????? ????????? ????????????????????? ????????????.";
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
					
//					System.err.println("@@@ ?????????????????? : " + estmCmtmNoList.get(i) + ", ??????????????? : " + estmCmtmNmList.get(i) + ", ?????????????????? : " + rsdnNo_1_List.get(i) + "" + rsdnNo_2_List.get(i));
					
					System.err.println("@@@ CASE_1 ==> " + CASE_1);
					System.err.println("@@@ CASE_2 ==> " + CASE_2);

					if(CASE_1 > 0){
						msg += "[ " + estmCmtmNmList.get(i) + " ]?????? ????????????????????? ?????? ??????????????? ???????????????.";
					}
					
					if(CASE_2 > 0){
						msg += "\n[ " + estmCmtmNmList.get(i) + " ]?????? ????????????????????? ????????? ????????? ????????????????????? ????????????.";
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