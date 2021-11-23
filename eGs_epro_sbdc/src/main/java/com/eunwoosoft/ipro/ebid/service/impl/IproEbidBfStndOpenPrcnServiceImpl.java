package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.mail.service.CommMailService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidBfStndOpenPrcnDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidBfStndOpenPrcnService;

/**
 * 사전규격공개진행 서비스 구현 클래스
 */
@Service("iproEbidBfStndOpenPrcnService")
public class IproEbidBfStndOpenPrcnServiceImpl extends AbstractFwkService implements IproEbidBfStndOpenPrcnService {
	
	static final String contextPath = "prpo/bfan";
	
	@Resource(name="iproEbidBfStndOpenPrcnDao")
	private IproEbidBfStndOpenPrcnDao iproEbidBfStndOpenPrcnDao;

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	@Resource(name="commMailService")
	private CommMailService commMailService; 
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao; 
	@Override
	public FwkTransferObject bfStndOpenPrcnList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		parameterMap.put("P_DEPT_NO", user.get("DEPT_NO"));
		
		trans.put(IproEbidBfStndOpenPrcnService.BF_STND_OPEN_PRCN_LIST, iproEbidBfStndOpenPrcnDao.selectBfStndOpenPrcnListWithPgng(parameterMap));
		trans.put(IproEbidBfStndOpenPrcnService.BF_STND_OPEN_LIST_PRCN_TOT_CNT, iproEbidBfStndOpenPrcnDao.selectBfStndOpenPrcnListTotCnt(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject bfStndOpenPrcnDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		parameterMap.put("P_USR_ID",user.get("USR_ID"));
		
		FwkDataEntity stndOpenPrcnDetail = iproEbidBfStndOpenPrcnDao.selectBfStndOpenPrcnDetail(parameterMap);
		trans.put(IproEbidBfStndOpenPrcnService.BF_STND_OPEN_PRCN_DETAIL, stndOpenPrcnDetail);
		
		parameterMap.put("P_BFAN_PSCD", stndOpenPrcnDetail.get("BFAN_PSCD"));
		if(parameterMap.get("P_BFAN_PSCD").equals("B005") || parameterMap.get("P_BFAN_PSCD").equals("B007") || parameterMap.get("P_BFAN_PSCD").equals("B013")){
			trans.put("bfStndPrcnRtn", iproEbidBfStndOpenPrcnDao.selectBfStndRtnRsn(parameterMap));
		}
		
		//T_PR_ITEM
		trans.put(IproEbidBfStndOpenPrcnService.BF_STND_OPEN_ITEM_LIST, iproEbidBfStndOpenPrcnDao.selectBfStndOpenItemList(parameterMap));
		//T_PR_FILE
		trans.put(IproEbidBfStndOpenPrcnService.BFAN_FILE, iproEbidBfStndOpenPrcnDao.selectBfStndOpenFile(parameterMap));
		
		//품목정보 재산유형 조회
		parameterMap.put("P_CD_ID_S","ASTS_TYCD");
		trans.put("astsTycdList", iproCommDefaultDao.selectCodeList(parameterMap));
		//품목정보 부가세 조회
		parameterMap.put("P_CD_ID_S","STAX_SECD");
		trans.put("staxSecdList", iproCommDefaultDao.selectCodeList(parameterMap));
		
		trans.put("currentDate", FwkDateUtil.getCurrentDateAsString());
		return trans;
	}

	@Override
	public FwkTransferObject bidBfanStatUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		try{
			FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
			parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
			parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
			parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
			parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
			parameterMap.put("P_DEL_AT"			, "N"); 
			iproEbidBfStndOpenPrcnDao.updateBfanStat(parameterMap);
			
			if(!"".equals(parameterMap.getString("P_RTRN_RSN"))){
				parameterMap.put("P_HIST_RMK", parameterMap.get("P_RTRN_RSN"));
			}
			
			iproEbidBfStndOpenPrcnDao.insertBfStndOpenProgHist(parameterMap);
			
			trans.put("param",parameterMap);
			trans.put("stateUpdt","succ");
			
		}catch(Exception ex){
			trans.put("stateUpdt","fail");
			throw new Exception(ex.toString());
		}
		
		return trans;
	}

	public FwkTransferObject bfStndOpenUpdt(FwkParameterMap parameterMap) throws Exception{
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
		parameterMap.put("P_DEL_AT", "N");
		
		if("B007".equals(parameterMap.get("P_BFAN_PSCD"))){
			parameterMap.put("P_BFAN_PSCD","B004");
		}
		//mst 저장
		iproEbidBfStndOpenPrcnDao.updateBfStndOpen(parameterMap);
		//품목정보 삭제
		iproEbidBfStndOpenPrcnDao.deleteBfStndOpenItem(parameterMap);
		//품목정보 저장
		ArrayList astsTycd =  CmmnUtil.paramSet(parameterMap.get("P_ASTS_TYCD"));
		ArrayList itemNo =  CmmnUtil.paramSet(parameterMap.get("P_ITEM_NO"));
		ArrayList itemNm = CmmnUtil.paramSet(parameterMap.get("P_ITEM_NM"));
		ArrayList itemDtl = CmmnUtil.paramSet(parameterMap.get("P_ITEM_DTL"));
		ArrayList itemUncd = CmmnUtil.paramSet(parameterMap.get("P_ITEM_UNCD"));
		ArrayList stndNm = CmmnUtil.paramSet(parameterMap.get("P_STND_NM"));
		ArrayList itemQty = CmmnUtil.paramSet(parameterMap.get("P_ITEM_QTY"));
		ArrayList esttUprc =CmmnUtil.paramSet( parameterMap.get("P_ESTT_UPRC"));
		ArrayList esttPrce = CmmnUtil.paramSet(parameterMap.get("P_ESTT_PRCE"));
		ArrayList staxSecd = CmmnUtil.paramSet(parameterMap.get("P_STAX_SECD"));
		ArrayList itemStax = CmmnUtil.paramSet(parameterMap.get("P_ITEM_STAX"));
		ArrayList esttAmt = CmmnUtil.paramSet(parameterMap.get("P_ESTT_AMT"));
		ArrayList bdgYr = CmmnUtil.paramSet(parameterMap.get("P_BDG_YR"));
		ArrayList bdgMm = CmmnUtil.paramSet(parameterMap.get("P_BDG_MM"));
		ArrayList bsnsCd = CmmnUtil.paramSet(parameterMap.get("P_BSNS_CD"));
		ArrayList deptNo = CmmnUtil.paramSet(parameterMap.get("P_DEPT_NO"));
		ArrayList acntNm = CmmnUtil.paramSet(parameterMap.get("P_ACNT_NM"));
		ArrayList acntCd = CmmnUtil.paramSet(parameterMap.get("P_ACNT_CD"));
		ArrayList acntItemCd = CmmnUtil.paramSet(parameterMap.get("P_ACNT_ITEM_CD"));
		
		for (int i = 0; i < astsTycd.size(); i++) {
			parameterMap.put("P_ASTS_TYCD", astsTycd.get(i));
			parameterMap.put("P_ITEM_NO", itemNo.get(i));
			parameterMap.put("P_ITEM_NM", itemNm.get(i));
			parameterMap.put("P_ITEM_DTL", itemDtl.get(i));
			parameterMap.put("P_ITEM_UNCD", itemUncd.get(i));
			parameterMap.put("P_STND_NM", stndNm.get(i));
			parameterMap.put("P_ITEM_QTY", itemQty.get(i));
			parameterMap.put("P_ESTT_UPRC", esttUprc.get(i));
			parameterMap.put("P_ESTT_PRCE", esttPrce.get(i));
			parameterMap.put("P_STAX_SECD", staxSecd.get(i));
			parameterMap.put("P_ITEM_STAX", itemStax.get(i));
			parameterMap.put("P_ESTT_AMT", esttAmt.get(i));
			parameterMap.put("P_BDG_YR", bdgYr.get(i));
			parameterMap.put("P_BDG_MM", bdgMm.get(i));
			parameterMap.put("P_BSNS_CD", bsnsCd.get(i));
			parameterMap.put("P_DEPT_NO", deptNo.get(i));
			parameterMap.put("P_ACNT_NM", acntNm.get(i));
			parameterMap.put("P_ACNT_CD", acntCd.get(i));
			parameterMap.put("P_ACNT_ITEM_CD", acntItemCd.get(i));
			iproEbidBfStndOpenPrcnDao.insertBfStndOpenItem(parameterMap);
		}
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		
		//구분에 따라 파일업로드
		FwkParameterMap fileParameterMap =  CmmnUtil.fileGbnMultiUpload(multipartRequest, "P_FILE", contextPath, parameterMap);
		
		if(parameterMap.getString("P_CONT_SECD").equals(parameterMap.getString("contSecd"))
				/*&& parameterMap.get("P_CONT_MTCD").equals(parameterMap.get("contMtcd"))*/
				&& parameterMap.get("P_ESTT_PRCE_M").equals(parameterMap.get("esttPrceM"))
				&& parameterMap.get("P_EMRG_YN").equals(parameterMap.get("emrgYn"))
				&& parameterMap.get("P_INF_BSNS_YN").equals(parameterMap.get("infBsnsYn"))){
			
			//파일 수정한게 있다면
			if(fileParameterMap != null) {
				List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
				
				parameterMap.put("atchFileGroupNo", fileParameterMap.get("atchFileGroupNo"));
				parameterMap.put("fileList", pfileList);
				parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
				
				//공통 첨부파일에 저장 T_MM_FILE_MST
				for(int i=0; i< pfileList.size(); i++){
					parameterMap.put("P_FILE_DOC_SECD", pfileList.get(i).get("P_FILE_DOC_SECD"));
					comAtmaAtchFileDao.deleteAtchFileUpdt(parameterMap);	//DEL_AT ='Y'처리
				}
				// atchFileGroupNo는 같더라도 FILE_SN이 늘어나므로 INSERT하는데 상관없음
				comAtmaAtchFileDao.insertMMFileRegist(parameterMap);	
				
				for(int i=0; i< pfileList.size(); i++){ 
					parameterMap.put("P_FILE_DOC_SECD", pfileList.get(i).get("P_FILE_DOC_SECD"));
					// 실제삭제
					iproEbidBfStndOpenPrcnDao.deleteBfStndOpenFile(parameterMap);
					// 다시 insert
					//설계 파일테이블에 저장 T_BI_FILE
					iproEbidBfStndOpenPrcnDao.insertBfStndOpenFile(parameterMap);
				} 
			}  
		}else{
			//파일 수정한게 있다면
			if(fileParameterMap != null) {
				List<Map<String, Object>> pfileList = (List<Map<String, Object>>) fileParameterMap.get("list");
				
				parameterMap.put("atchFileGroupNo", fileParameterMap.get("atchFileGroupNo"));
				parameterMap.put("fileList", pfileList);
				parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
				
				//계약구분 바뀌기전 저장된 파일들 조회 해서 삭제 처리
				List<FwkDataEntity> contDsgnFileList = iproEbidBfStndOpenPrcnDao.selectBfStndOpenFile(parameterMap);
				for(int i=0; i<contDsgnFileList.size(); i++){
					parameterMap.put("P_FILE_DOC_SECD", contDsgnFileList.get(i).get("BFAN_FSCD"));
					comAtmaAtchFileDao.deleteAtchFileUpdt(parameterMap);	//DEL_AT ='Y'처리
					// 실제삭제
					iproEbidBfStndOpenPrcnDao.deleteBfStndOpenFile(parameterMap);
				} 
				
				// 새로추가된 첨부파일 
				//공통 첨부파일에 저장 T_MM_FILE_MST
				comAtmaAtchFileDao.insertMMFileRegist(parameterMap);	
				
				for(int i=0; i< pfileList.size(); i++){ 
					parameterMap.put("P_FILE_DOC_SECD", pfileList.get(i).get("P_FILE_DOC_SECD"));
					// 다시 insert
					//설계 파일테이블에 저장 T_BI_FILE
					iproEbidBfStndOpenPrcnDao.insertBfStndOpenFile(parameterMap);
				} 
			}  
		}
		
		//기타첨부파일
		if(!parameterMap.get("P_FILE_GRP_NO_ETC").equals("") && parameterMap.getString("P_FILE_GRP_NO_ETC")!= null){ 
			parameterMap.put("P_FILE_DOC_SECD", "ETC");
			iproEbidBfStndOpenPrcnDao.deleteBfStndOpenEtcFile(parameterMap);		
			iproEbidBfStndOpenPrcnDao.insertBfStndOpenEtcFile(parameterMap); 
		}
		
		//이력
		parameterMap.put("P_HIST_RMK","수정");
		iproEbidBfStndOpenPrcnDao.insertBfStndOpenProgHist(parameterMap);
		
		trans.put("P_BFAN_NO_TRANS", parameterMap.get("P_BFAN_NO"));
		return trans;
	}

	@Override
	public FwkTransferObject bfStndOpenPrcnExcelList(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_RQR_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_RQR_BEGIN_DT_S"))){ // 작성일자 시작
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		if(parameterMap.get("P_RQR_END_DT_S") != null && !"".equals(parameterMap.get("P_RQR_END_DT_S"))){ // 작성일자 종료
			parameterMap.put("P_RQR_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		} 
		List<FwkDataEntity> stndOpenList = iproEbidBfStndOpenPrcnDao.selectBfStndOpenExcelList(parameterMap);
		
		for(int i=0; i < stndOpenList.size(); i++){
			FwkDataEntity stndOpen = stndOpenList.get(i);

    		stndOpenList.get(i).put("BSNS_BDG_AMT", FwkFormatUtil.formatMoney(stndOpen.getString("BSNS_BDG_AMT")));
    		stndOpenList.get(i).put("BFAN_STDE", FwkFormatUtil.formatDate(stndOpen.getString("BFAN_STDE"), "yyyyMMdd", "yyyy-MM-dd"));
    		stndOpenList.get(i).put("BFAN_ENDE", FwkFormatUtil.formatDate(stndOpen.getString("BFAN_ENDE"), "yyyyMMdd", "yyyy-MM-dd"));
    		stndOpenList.get(i).put("RQR_DE", FwkFormatUtil.formatDate(stndOpen.getString("RQR_DE"), "yyyyMMdd", "yyyy-MM-dd"));
		}
		
		trans.put(IproEbidBfStndOpenPrcnService.BF_STND_OPEN_PRCN_LIST, stndOpenList);
		
		return trans;
	}
	
}