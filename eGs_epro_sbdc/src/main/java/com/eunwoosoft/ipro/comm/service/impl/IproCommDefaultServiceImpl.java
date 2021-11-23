package com.eunwoosoft.ipro.comm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.comm.dao.IproCommPopupDao;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;

/**
 * 
 * com.eunwoosoft.ipro.comm.service.impl
 * IproCommPopupServiceImpl.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Service(value="iproCommDefaultService")
public class IproCommDefaultServiceImpl extends AbstractFwkService implements IproCommDefaultService {
	
	@Resource(name="iproCommDefaultDao") 
	IproCommDefaultDao iproCommDefaultDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommPopupDao")
	private IproCommPopupDao iproCommPopupDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	
	/**
	 * <pre>
	 * 1. 개요 : 공통 첨부 파일 조회
	 * 2. 처리내용 : 
	 * </pre>
	 */
	@Override
	public FwkTransferObject cmmnFileInfoInqire(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		// 공고 상세
		trans.put(IproCommDefaultService.CMMN_FILE_INFO, 
				iproCommDefaultDao.selectCmmnFileInfoInqire(parameterMap));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject cmmnImstarsFileInfo(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 아임스타즈 연계 첨부파일 정보
		trans.put(IproCommDefaultService.CMMN_IMSTARS_FILE_INFO, iproCommDefaultDao.selectCmmnImstarsFileInfo(parameterMap));   // SPORT_REQST_FILEDETAILINFO
		
		return trans;
	}
	
	@Override
	public FwkTransferObject cmmnImstarsDetailFileInfo(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 아임스타즈 연계 첨부파일 정보
		trans.put(IproCommDefaultService.CMMN_IMSTARS_DETAIL_FILE_INFO, iproCommDefaultDao.selectCmmnImstarsDetailFileInfo(parameterMap));   // CMMN_FILEDETAILINFO
		
		return trans;
	}
	
	
	
	@Override
	public FwkTransferObject downloadZip(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		List<FwkDataEntity> fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
		
		String[] fileLctnSvFileNm = new String[fileList.size()];
		String[] sysFileNm = new String[fileList.size()];
		String zipPath = "";
		
		for (int i = 0; i < fileList.size(); i++) {
			FwkDataEntity file = fileList.get(i);
			fileLctnSvFileNm[i] = file.getString("FILE_LCTN")+File.separator+file.getString("SV_FILE_NM");
			sysFileNm[i] = file.getString("SYS_FILE_NM");
			if( i == 0){
				zipPath = file.getString("FILE_LCTN")+File.separator+parameterMap.getString("P_ZIP_FILE_NM")+".zip";
			}
		}
		
		parameterMap.put("fileLctnSvFileNm", fileLctnSvFileNm);
		parameterMap.put("sysFileNm", sysFileNm);
		parameterMap.put("zipPath", zipPath);
		
		CmmnUtil.createZip(parameterMap);
		
		trans.put(IproCommDefaultService.CMMN_FILE_INFO, zipPath);
		
		trans.put("vendDetail", iproCommDefaultDao.vendDetail(parameterMap));
		
		return trans;
	}
	
	//낙찰자 선정 방식
	@Override
    public FwkTransferObject comCmcdScsCdInqireByCdId(FwkParameterMap parameterMap) { 
    	FwkTransferObject trans = new FwkTransferObject();
    	
    	/*List<FwkDataEntity> cdValueList =  cmmnDefaultDao.selectcomCmcdScsCdInqireByCdId(parameterMap);
	    	List<FwkDataEntity> cdValueList3 = new ArrayList<FwkDataEntity>(); 
	        if( "44".equals(parameterMap.get("P_CD_VALUE")) ) {
	        	for(int i = 0; i < cdValueList.size(); i++){
	        		FwkDataEntity cdValueList2 = cdValueList.get(i);
	        		
	        		String cdValue= (String) cdValueList2.get("CD_VALUE");
	        		
	        		if("10".equals(cdValue) || "20".equals(cdValue)){
	        			cdValueList3.add(cdValueList2);
	        		}
	        	} 
	        }        
	        trans.put(ComCmcdDetailCdInqireService.CD_VALUE_LIST, cdValueList3 );
	        
	        
	        
        */
        
    	 trans.put(ComCmcdDetailCdInqireService.CD_VALUE_LIST, iproCommDefaultDao.selectcomCmcdScsCdInqireByCdId(parameterMap) );
      
    	 return trans;
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 업체정보상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsInqireDetail
	 * @date : 2018. 11. 28
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 28	은우소프트 맹경열				최초 작성 
	 *	----------- ------------------- --------------------------------------- 
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public FwkTransferObject entrpsInqireDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		List<FwkDataEntity> datalist = iproCommPopupDao.selectEntrpsInqireList(parameterMap);
		// 0:없음, 1:있음, 2:다수
		if(datalist == null) {
			trans.put(IproCommDefaultService.ENTRPS_INQIRE_RESULT_CODE, 0);	
		}else {
			if(datalist.size() > 1) {
				trans.put(IproCommDefaultService.ENTRPS_INQIRE_RESULT_CODE, 2);		
			}else if(datalist.size() == 1) {
				trans.put(IproCommDefaultService.ENTRPS_INQIRE_RESULT_CODE, 1);	
				trans.put(IproCommDefaultService.ENTRPS_INQIRE_DETAIL, datalist.get(0));	
			}else {
				trans.put(IproCommDefaultService.ENTRPS_INQIRE_RESULT_CODE, 0);	
			}
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : UBI REPORT 파일 생성 공통
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : makeUbiReportFile
	 * @date : 2018. 12. 26
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 12. 26	은우소프트 맹경열				최초 작성 
	 *	----------- ------------------- --------------------------------------- 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject makeUbiReportFile(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		String arg = "P_CONT_NO#"+parameterMap.getString("P_CONT_NO")+"#P_CHNG_NGR#"+parameterMap.getString("P_CHNG_NGR")+"#";
		String refFileNm = "";
		String refFilePath = "";
				
		refFileNm = parameterMap.getString("P_FORM_NM");		//jrf파일명
		refFilePath = parameterMap.getString("P_FORM_PATH");	//jrf파일경로
		
		FwkParameterMap ubiMap = CmmnUtil.ubiExportUrf(refFileNm, arg, "cont", refFilePath);
		String export_dir = ubiMap.getString("export_dir");		// 풀경로
		String export_file = ubiMap.getString("export_file");	// 계약파일명
		String temp_file = ubiMap.getString("temp_file");		// 계약파일명	

//		if(ubiMap.equals("fail")){
		if("fail".equals(ubiMap.getString("result"))){
			LOG.debug("계약서 URF 생성에 실패하였습니다.");
			trans.put("msg", "계약서URF 생성에 실패하였습니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		/*** 계약 PDF 파일 정보 셋팅 ***/		
		String filePath = export_dir+File.separator+temp_file+".xml";
		File file = new File(filePath);
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();		//urf파일
		List<Map<String, Object>> pfileList = new ArrayList<Map<String,Object>>();		//pdf파일

		//urf파일정보
		FwkParameterMap map = new FwkParameterMap();
		map.put("P_FILE_SZ",  file.length());
		map.put("P_SV_FILE_NM",  temp_file+".xml");
		map.put("P_SYS_FILE_NM",  export_file+".xml");
		map.put("P_FILE_DOC_NM",   "계약서");
		map.put("P_FILE_LCTN",  export_dir);
		
		//pdf파일정보
		FwkParameterMap pmap = new FwkParameterMap();
		pmap.put("P_FILE_SZ",  file.length());
		pmap.put("P_SV_FILE_NM",  temp_file+".pdf");
		pmap.put("P_SYS_FILE_NM",  export_file+".pdf");
		pmap.put("P_FILE_DOC_NM",   "계약서pdf");
		pmap.put("P_FILE_LCTN",  export_dir);
		//map.put("P_ATCHMNFL_EXTSN_NM",  "application/pdf");
		
		
		System.out.println("map >>>>>>>>>>> "+map);
		
		
		
		//List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		
		
		fileList.add(map);	
		pfileList.add(pmap);
		
		//urf(계약서파일)삭제 및 등록
		String atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
		parameterMap.put("P_FILE_DOC_SECD", "A");
		comAtmaAtchFileDao.deleteAtchFileDelByGrpNo(parameterMap);	//DEL_AT ='Y'처리
		
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileList", fileList);
		parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		
		//pdf파일 삭제 및 등록
		parameterMap.put("P_FILE_DOC_SECD", "A1");
		comAtmaAtchFileDao.deleteAtchFileDelByGrpNo(parameterMap);	//DEL_AT ='Y'처리
		
		parameterMap.put("fileList", pfileList);
		comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
		
		/***  공통 파일정보 저장 ***/
		// 첨부파일 저장
		//parameterMap.put("P_SIGN_ESS_YN", "Y");
		
		
		if(atchFileGroupNo != null && !atchFileGroupNo.equals("") ){
			/***  생성된 URF(XML) 계약서 파일 정보 저장 ***/
			parameterMap.put("P_CTDC_YN", "Y");
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			parameterMap.put("P_SIGN_ESS_YN", "Y");
			parameterMap.put("fileList", fileList);
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			
			parameterMap.put("P_CONT_FSCD", "A");
			//설계 파일테이블에 저장 T_CT_FILE 
//			iproPrpoContReqDao.deleteContReqFile(parameterMap);
//			iproPrpoContReqDao.insertContReqFile(parameterMap);
			
			/***  생성된 PDF 파일 정보 저장 ***/
			parameterMap.put("P_CTDC_YN", "Y");
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			parameterMap.put("P_SIGN_ESS_YN", "N");
			parameterMap.put("fileList", pfileList);
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("P_CONT_FSCD", "A1");
//			iproPrpoContReqDao.deleteContReqFile(parameterMap);
//			iproPrpoContReqDao.insertContReqFile(parameterMap);
		}				
		
		return trans;
	}
	
	/**
	 * 로그 등록
	 */
	@Override
	public FwkTransferObject sendLOG(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();

		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */

		String sysConnSecd = parameterMap.getTrimString("P_SYS_CONN_SECD");
		
		
		//if(!"SSO_LOGIN".equals(sysConnSecd)){
		if(!"LOGIN".equals(sysConnSecd) && !"CMTM_EMGNC_LOGIN".equals(sysConnSecd)){
			// 세션정보
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
			if(session != null){
				parameterMap.put("P_USR_ID", session.get("USR_ID"));
				parameterMap.put("P_USR_NM", session.get("USR_NM"));
				parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
			}
		}
		
		
		iproCommDefaultDao.sendLOG(parameterMap);

		return trans;
	}
	
	/**
	 * 엑셀 기본세팅
	 */
	@Override
	public FwkTransferObject excelCommSetting(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();

		Object P_EXCEL_TH = parameterMap.get("P_EXCEL_TH");
		Object P_EXCEL_TH_COL = parameterMap.get("P_EXCEL_TH_COL");
		Object P_EXCEL_TH_ROW = parameterMap.get("P_EXCEL_TH_ROW");
		Object P_EXCEL_TD = parameterMap.get("P_EXCEL_TD");
		
		//x,y 좌표
		Object P_EXCEL_X = parameterMap.get("P_EXCEL_X");
		Object P_EXCEL_Y = parameterMap.get("P_EXCEL_Y");
		
		ArrayList<String> P_EXCEL_TH_LIST = new ArrayList<String>();
		ArrayList<String> P_EXCEL_TH_COL_LIST = new ArrayList<String>();
		ArrayList<String> P_EXCEL_TH_ROW_LIST = new ArrayList<String>();
		ArrayList<String> P_EXCEL_TD_LIST = new ArrayList<String>();
		
		ArrayList<String> P_EXCEL_X_LIST = new ArrayList<String>();
		ArrayList<String> P_EXCEL_Y_LIST = new ArrayList<String>();
		
		
		
		if(P_EXCEL_TH instanceof String) {
			P_EXCEL_TH_LIST.add(P_EXCEL_TH.toString());
			P_EXCEL_TH_COL_LIST.add(P_EXCEL_TH_COL.toString());
			P_EXCEL_TH_ROW_LIST.add(P_EXCEL_TH_ROW.toString());
			if( parameterMap.get("P_EXCEL_TD") != null) { P_EXCEL_TD_LIST.add(P_EXCEL_TD.toString()); }
			if( parameterMap.get("P_EXCEL_X") != null) { P_EXCEL_TD_LIST.add(P_EXCEL_X.toString()); }
			if( parameterMap.get("P_EXCEL_Y") != null) { P_EXCEL_TD_LIST.add(P_EXCEL_Y.toString()); }
		}else {
			if(P_EXCEL_TH instanceof ArrayList) {
				ArrayList<String> P_EXCEL_THList = (ArrayList) P_EXCEL_TH;
				ArrayList<String> P_EXCEL_TH_COLList = (ArrayList) P_EXCEL_TH_COL;
				ArrayList<String> P_EXCEL_TH_ROWList = (ArrayList) P_EXCEL_TH_ROW;
				ArrayList<String> P_EXCEL_TDList = new ArrayList<String>();
				ArrayList<String> P_EXCEL_XList = new ArrayList<String>();
				ArrayList<String> P_EXCEL_YList = new ArrayList<String>();
				
				if( parameterMap.get("P_EXCEL_TD") != null) { P_EXCEL_TDList = (ArrayList) P_EXCEL_TD; }
				if( parameterMap.get("P_EXCEL_X") != null) { P_EXCEL_XList = (ArrayList) P_EXCEL_X; }
				if( parameterMap.get("P_EXCEL_Y") != null) { P_EXCEL_YList = (ArrayList) P_EXCEL_Y; }
				
				
				for (int idx = 0; idx < P_EXCEL_THList.size(); idx++) {
					P_EXCEL_TH_LIST.add(P_EXCEL_THList.get(idx));
					P_EXCEL_TH_COL_LIST.add(P_EXCEL_TH_COLList.get(idx));
					P_EXCEL_TH_ROW_LIST.add(P_EXCEL_TH_ROWList.get(idx));
					if( parameterMap.get("P_EXCEL_TD") != null) { P_EXCEL_TD_LIST.add(P_EXCEL_TDList.get(idx)); }
					if( parameterMap.get("P_EXCEL_X") != null) { P_EXCEL_X_LIST.add(P_EXCEL_XList.get(idx)); }
					if( parameterMap.get("P_EXCEL_Y") != null) { P_EXCEL_X_LIST.add(P_EXCEL_XList.get(idx)); }
					
				}
			}else if(P_EXCEL_TH instanceof String[]) {
				String[] P_EXCEL_THList = (String[]) P_EXCEL_TH;
				String[] P_EXCEL_TH_COLList = (String[]) P_EXCEL_TH_COL;
				String[] P_EXCEL_TH_ROWList = (String[]) P_EXCEL_TH_ROW;
				String[] P_EXCEL_TDList = null;
				String[] P_EXCEL_XList = null;
				String[] P_EXCEL_YList = null;
				
				if( parameterMap.get("P_EXCEL_TD") != null) { P_EXCEL_TDList = (String[]) P_EXCEL_TD; }
				if( parameterMap.get("P_EXCEL_X") != null) { P_EXCEL_XList = (String[]) P_EXCEL_X; }
				if( parameterMap.get("P_EXCEL_Y") != null) { P_EXCEL_YList = (String[]) P_EXCEL_Y; }
				
				for (int idx = 0; idx < P_EXCEL_THList.length; idx++) {
					P_EXCEL_TH_LIST.add(P_EXCEL_THList[idx]);
					P_EXCEL_TH_COL_LIST.add(P_EXCEL_TH_COLList[idx]);
					P_EXCEL_TH_ROW_LIST.add(P_EXCEL_TH_ROWList[idx]);
					if( parameterMap.get("P_EXCEL_TD") != null) { P_EXCEL_TD_LIST.add(P_EXCEL_TDList[idx]); }
					if( parameterMap.get("P_EXCEL_X") != null) { P_EXCEL_X_LIST.add(P_EXCEL_XList[idx]); }
					if( parameterMap.get("P_EXCEL_Y") != null) { P_EXCEL_Y_LIST.add(P_EXCEL_YList[idx]); }
					
				}
			}
		}
		
		if(P_EXCEL_TH_LIST.size() > 0){
			parameterMap.put("P_EXCEL_TH_LIST", P_EXCEL_TH_LIST);
			parameterMap.put("P_EXCEL_TH_COL_LIST", P_EXCEL_TH_COL_LIST);
			parameterMap.put("P_EXCEL_TH_ROW_LIST", P_EXCEL_TH_ROW_LIST);
			if( parameterMap.get("P_EXCEL_TD") != null) { parameterMap.put("P_EXCEL_TD_LIST", P_EXCEL_TD_LIST); }
			if( parameterMap.get("P_EXCEL_X") != null) { parameterMap.put("P_EXCEL_X_LIST", P_EXCEL_X_LIST); }
			if( parameterMap.get("P_EXCEL_Y") != null) { parameterMap.put("P_EXCEL_Y_LIST", P_EXCEL_Y_LIST); }
			
			List<FwkDataEntity> excelTHList = iproCommDefaultDao.selectExcelTHList(parameterMap);
			List<FwkDataEntity> excelTHColList = iproCommDefaultDao.selectExcelTHColList(parameterMap);
			List<FwkDataEntity> excelTHRowList = iproCommDefaultDao.selectExcelTHRowList(parameterMap);
			if( parameterMap.get("P_EXCEL_TD") != null) {
				List<FwkDataEntity> excelTDList = iproCommDefaultDao.selectExcelTDList(parameterMap);
				trans.put("EXCEL_TD_LIST", excelTDList);
			}
			
			if( parameterMap.get("P_EXCEL_X") != null) {
				List<FwkDataEntity> excelXList = iproCommDefaultDao.selectExcelXList(parameterMap);
				trans.put("EXCEL_X_LIST", excelXList);
			}
			
			if( parameterMap.get("P_EXCEL_Y") != null) {
				List<FwkDataEntity> excelYList = iproCommDefaultDao.selectExcelYList(parameterMap);
				trans.put("EXCEL_Y_LIST", excelYList);
			}
			
			trans.put("EXCEL_TH_LIST", excelTHList);
			trans.put("EXCEL_TH_COL_LIST", excelTHColList);
			trans.put("EXCEL_TH_ROW_LIST", excelTHRowList);

			
		}

		return trans;
	}
	
	
	
}
