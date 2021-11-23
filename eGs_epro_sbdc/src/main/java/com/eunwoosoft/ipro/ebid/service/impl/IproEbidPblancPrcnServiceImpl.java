package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancPrcnDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancPrcnService;

/**
 * 입찰공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidPblancPrcnService")
public class IproEbidPblancPrcnServiceImpl extends AbstractFwkService implements IproEbidPblancPrcnService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidPblancPrcnServiceImpl.class);
	
	static final String contextPath = "ebid/bid";
	
	@Resource(name="iproEbidPblancPrcnDao")
	private IproEbidPblancPrcnDao iproEbidPblancPrcnDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="commMailService")
	private CommMailService commMailService; 
	
	@Override
	public FwkTransferObject bidPblancPrcnListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		/*FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		// 계약부서에 따라 목록 보이게 
		parameterMap.put("P_DEPT_NO", user.get("DEPT_NO"));*/
		
		if(parameterMap.get("P_RQR_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_RQR_BEGIN_DT_S"))){ // 공고일자 시작
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_RQR_END_DT_S") != null && !"".equals(parameterMap.get("P_RQR_END_DT_S"))){ // 공고일자 종료
			parameterMap.put("P_RQR_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproEbidPblancPrcnService.BID_PBLANC_PRCN_LIST, iproEbidPblancPrcnDao.selectBidPblancPrcnListWithPgng(parameterMap));
		trans.put(IproEbidPblancPrcnService.BID_PBLANC_PRCN_LIST_TOTCNT, iproEbidPblancPrcnDao.selectBidPblancPrcnListTotcnt(parameterMap));
		return trans;
	}
	
	
	//기술평가등록
	@Override
	public FwkTransferObject tchnEstmRegist(FwkParameterMap parameterMap)  throws Exception {
		FwkTransferObject  trans = new FwkTransferObject();
		
		//T_BI_VEND_MST UPDT
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());  
		parameterMap.put("P_DEL_AT", "N");
		
		iproEbidPblancPrcnDao.updateBidVendMst(parameterMap); 
		
		// 평가결과첨부파일
		if(!parameterMap.get("P_FILE_GRP_NO").equals("")){ 
			parameterMap.put("P_BID_TPI_SECD", "OP06");  	//업체평가문서
			parameterMap.put("P_BID_SBMT_FSCD", "DO01"); 	//평가결과
			iproEbidPblancPrcnDao.deleteBidVendFile(parameterMap);
			iproEbidPblancPrcnDao.insertBidVendFile(parameterMap);
		}
		 
		return trans;
	}
	
	/**
	 * 적격심사 등록
	 */
	@Override
	public FwkTransferObject elgbEstmRegist(FwkParameterMap parameterMap)  throws Exception {
		FwkTransferObject  trans = new FwkTransferObject();
		
		//T_BI_VEND_BIOP UPDT
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());  
		parameterMap.put("P_DEL_AT", "N");
		
		iproEbidPblancPrcnDao.updateBidBiopMst(parameterMap); 
		 
		// 평가결과첨부파일
		if(!parameterMap.get("P_FILE_GRP_NO").equals("")){ 
			parameterMap.put("P_BID_TPI_SECD", "OP06");  	//업체평가문서
			parameterMap.put("P_BID_SBMT_FSCD", "DO05"); 	//적격심사결과
			iproEbidPblancPrcnDao.deleteBidVendFile(parameterMap);
			iproEbidPblancPrcnDao.insertBidVendFile(parameterMap);
		}
		 
		return trans;
	}
	
	
	@Override
	public FwkTransferObject bidReqRtnStatUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		try{
			FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
			parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
			parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
			parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
			parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
			parameterMap.put("P_DEL_AT"			, "N"); 
			iproEbidPblancPrcnDao.updateBidStat(parameterMap);
			
			parameterMap.put("P_RMK", parameterMap.get("P_RTRN_RSN"));
			iproEbidPblancPrcnDao.insertBidProgHist(parameterMap);
			
			if("A024".equals(parameterMap.get("P_BID_PSCD"))){	//낙찰취소
//				iproPrpoBidReqDao.vendBiopCancleUpdate(parameterMap);	//낙찰업체 취소 처리 
			}
			trans.put("stateUpdt","succ");
		}catch(Exception ex){
			trans.put("stateUpdt","fail");
			throw new Exception(ex.toString());
		}
		
		return trans;
	}
	
	/**
	 * 제안/규격서 서류 등록 
	 * 2020-09-21
	 * joo
	 */
	@Override
	public FwkTransferObject tchnFileRegist(FwkParameterMap parameterMap)  throws Exception {
		FwkTransferObject  trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());  
		parameterMap.put("P_DEL_AT", "N");
		
		// 제안/규격서 첨부파일 
		if(!parameterMap.get("P_FILE_GRP_NO").equals("")){ 
			parameterMap.put("P_BID_TPI_SECD", "OP06");  	//업체평가문서
			parameterMap.put("P_BID_SBMT_FSCD", "DO02"); 	//제안/규격서
			iproEbidPblancPrcnDao.deleteBidVendFile(parameterMap);
			iproEbidPblancPrcnDao.insertBidVendFile(parameterMap);
		}
		 
		return trans;
	}

	/**
	 * 산출내역서 등록
	 * 2020-09-21
	 * joo
	 * 
	 */
	@Override
	public FwkTransferObject clcCntnFileRegist(FwkParameterMap parameterMap)  throws Exception {
		FwkTransferObject  trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());  
		parameterMap.put("P_DEL_AT", "N");
		
		// 제안/규격서 첨부파일  
		if(!parameterMap.get("P_FILE_GRP_NO").equals("")){ 
			parameterMap.put("P_BID_TPI_SECD", "OP06");  	//업체평가문서
			parameterMap.put("P_BID_SBMT_FSCD", "DO03"); 	//산출내역서
			iproEbidPblancPrcnDao.deleteBidVendFile(parameterMap); 
			iproEbidPblancPrcnDao.insertBidVendFile(parameterMap);
		} 
		 
		return trans;
	}
	
	
	/**
	 * 협상결과 등록
	 * 2020-09-21
	 * joo
	 */
	@Override
	public FwkTransferObject negoFileRegist(FwkParameterMap parameterMap)  throws Exception {
		FwkTransferObject  trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());  
		parameterMap.put("P_DEL_AT", "N");
		
		// 협상결과서 첨부파일   
		if(!parameterMap.get("P_FILE_GRP_NO").equals("")){ 
			parameterMap.put("P_BID_TPI_SECD", "OP06");  	//업체평가문서
			parameterMap.put("P_BID_SBMT_FSCD", "DO04"); 	//협상결과서
			iproEbidPblancPrcnDao.deleteBidVendFile(parameterMap); 
			iproEbidPblancPrcnDao.insertBidVendFile(parameterMap);
		} 
		 
		return trans; 
	}

	
	
	/**
	 * 개찰결과 등록
	 * 2020-10-08
	 * joo
	 */
	@Override
	public FwkTransferObject openResultRegist(FwkParameterMap parameterMap)  throws Exception {
		FwkTransferObject  trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_REGR_ID"			, user.get("USR_ID"));
		parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
		parameterMap.put("P_CONN_IP"		, user.get("CONN_IP"));
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());  
		parameterMap.put("P_DEL_AT", "N");
		
		
		

		Object vendRegNo = parameterMap.get("P_VEND_REG_NO");
		Object bizrNo = parameterMap.get("P_BIZRNO");
		Object vendNm = parameterMap.get("P_VEND_NM"); 
		Object chrgrNm = parameterMap.get("P_CHRGR_NM"); 
		Object telNo = parameterMap.get("P_TEL_NO");
		Object emal = parameterMap.get("P_EMAL_ADDR");

		//투찰금액
		Object tndrAmt = parameterMap.get("P_TNDR_AMT");
		//기술점수
		Object tchnEstmScr = parameterMap.get("P_TCHN_ESTM_SCR");
		//순위
		Object opngRnk = parameterMap.get("P_OPNG_RNK");
		//가격점수 
		Object prceScr = parameterMap.get("P_PRCE_SCR");
		//낙찰여부
		Object sbidYn = parameterMap.get("P_SBID_YN");
		
		
		int chk = 0;
		int biopChk = 0;
		
		if(vendRegNo != null){
			if(vendRegNo instanceof String){
				
				//업체가 등록이 되어있는지 없는지 체크
//				chk = iproPrpoBidReqDao.vendBizChk(parameterMap); 
				
				if(chk == 0){	//등록되어있지 않으면 등록 
//					iproPrpoBidReqDao.vendRegist(parameterMap);
					//담당자 정보 저장 
//					iproPrpoBidReqDao.vendDtlRegist(parameterMap);
				}else{
//					iproPrpoBidReqDao.vendUpdate(parameterMap);
					//담당자 정보 저장 
//					iproPrpoBidReqDao.vendDtlUpdate(parameterMap);
				}
				
				
//				biopChk = iproPrpoBidReqDao.vendBizBiopChk(parameterMap);
//				if(opngRnk !=null){
//					if(biopChk == 0){	//업체개찰테이블
//						iproPrpoBidReqDao.insertBidOpenRegist(parameterMap);
//					}else{
//						iproPrpoBidReqDao.vendBiopUpdate(parameterMap);
//					}
//				}
				
				
			}else if(vendRegNo instanceof ArrayList){
				ArrayList vendRegNoArr = (ArrayList) vendRegNo;
				ArrayList bizrNoArr = (ArrayList) bizrNo;
				ArrayList vendNmArr = (ArrayList) vendNm;
				ArrayList chrgrNmArr = (ArrayList) chrgrNm;
				ArrayList telNoArr = (ArrayList) telNo;
				ArrayList emalArr = (ArrayList) emal;
				
				ArrayList tndrAmtArr = (ArrayList) tndrAmt;	//투찰금액
				ArrayList opngRnkArr = (ArrayList) opngRnk; //개찰순위
				ArrayList tchnEstmScrArr = (ArrayList) tchnEstmScr; //기술점수
				ArrayList prceScrArr = (ArrayList) prceScr;	//가격점수
				//낙찰탭
				ArrayList sbidYnArr = (ArrayList) sbidYn;	//낙찰여부
				
				for(int i =0; i < vendRegNoArr.size(); i++){
					parameterMap.put("P_VEND_REG_NO", vendRegNoArr.get(i));
					parameterMap.put("P_BIZRNO", bizrNoArr.get(i));	 
					parameterMap.put("P_VEND_NM", vendNmArr.get(i));	
					parameterMap.put("P_CHRGR_NM", chrgrNmArr.get(i));	
					parameterMap.put("P_TEL_NO", telNoArr.get(i));	
					parameterMap.put("P_EMAL_ADDR", emalArr.get(i));	
					if(tndrAmtArr != null && tndrAmtArr.get(i) != null){
						parameterMap.put("P_TNDR_AMT", tndrAmtArr.get(i));	
					}
					if(prceScrArr != null && prceScrArr.get(i) != null){
						parameterMap.put("P_PRCE_SCR", prceScrArr.get(i));	
					}
					if(opngRnkArr != null && opngRnkArr.get(i) != null){
						parameterMap.put("P_OPNG_RNK", opngRnkArr.get(i));	
					}
					if(tchnEstmScrArr != null && tchnEstmScrArr.get(i) != null){
						parameterMap.put("P_TCHN_ESTM_SCR", tchnEstmScrArr.get(i));
					}
					//낙찰탭
					if(sbidYnArr != null && sbidYnArr.get(i) != null){
						parameterMap.put("P_SBID_YN", sbidYnArr.get(i));
					}
					//업체가 등록이 되어있는지 없는지 체크
//					chk = iproPrpoBidReqDao.vendBizChk(parameterMap);
//					if(chk == 0){	//등록되어있지 않으면 등록 
//						iproPrpoBidReqDao.vendRegist(parameterMap); 
//						//담당자 정보 저장 
//						iproPrpoBidReqDao.vendDtlRegist(parameterMap);
//					}else{ 
//						iproPrpoBidReqDao.vendUpdate(parameterMap);
//						//담당자 정보 저장 
//						iproPrpoBidReqDao.vendDtlUpdate(parameterMap);
//					}
					
					
//					biopChk = iproPrpoBidReqDao.vendBizBiopChk(parameterMap);
//					if(opngRnk !=null){
//						if(biopChk == 0){	//업체개찰테이블
//							iproPrpoBidReqDao.insertBidOpenRegist(parameterMap);
//						}else{
//							iproPrpoBidReqDao.vendBiopUpdate(parameterMap);
//						}
//					}
				}
				
			}else if(vendRegNo instanceof String[]){
				String[] vendRegNoArr = (String[]) vendRegNo;
				String[] bizrNoArr = (String[]) bizrNo;
				String[] vendNmArr = (String[]) vendNm;
				String[] chrgrNmArr = (String[]) chrgrNm;
				String[] telNoArr = (String[]) telNo;
				String[] emalArr = (String[]) emal;
				
				String[]  tndrAmtArr = (String[]) tndrAmt; 
				String[]  tchnEstmScrArr = (String[] ) tchnEstmScr; 
				String[]  opngRnkArr = (String[] ) opngRnk; 
				String[]  prceScrArr = (String[]) prceScr;
				//낙찰탭
				String[] sbidYnArr = (String[]) sbidYn;	//낙찰여부
				
				for(int i =0; i < vendRegNoArr.length; i++){
					parameterMap.put("P_VEND_REG_NO", vendRegNoArr[i]);	
					parameterMap.put("P_BIZRNO", bizrNoArr[i]);	
					parameterMap.put("P_VEND_NM", vendNmArr[i]);	
					parameterMap.put("P_CHRGR_NM", chrgrNmArr[i]);	
					parameterMap.put("P_TEL_NO", telNoArr[i]);	 
					parameterMap.put("P_EMAL_ADDR", emalArr[i]);	

					if(tndrAmtArr != null && tndrAmtArr[i] != null){
						parameterMap.put("P_TNDR_AMT", tndrAmtArr[i]);	
					}
					if(prceScrArr != null && prceScrArr[i] != null){
						parameterMap.put("P_PRCE_SCR", prceScrArr[i]);	
					}
					if(opngRnkArr != null && opngRnkArr[i] != null){
						parameterMap.put("P_OPNG_RNK", opngRnkArr[i]);	
					}
					if(tchnEstmScrArr != null && tchnEstmScrArr[i] != null){
						parameterMap.put("P_TCHN_ESTM_SCR", tchnEstmScrArr[i]);
					}
					//낙찰탭
					if(sbidYnArr != null && sbidYnArr[i] != null){
						parameterMap.put("P_SBID_YN", sbidYnArr[i]);
					}
					//업체가 등록이 되어있는지 없는지 체크
//					chk = iproPrpoBidReqDao.vendBizChk(parameterMap);
//					if(chk == 0){	//등록되어있지 않으면 등록 
//						iproPrpoBidReqDao.vendRegist(parameterMap);
//						//담당자 정보 저장 
//						iproPrpoBidReqDao.vendDtlRegist(parameterMap);
//					}else{
//						iproPrpoBidReqDao.vendUpdate(parameterMap);
//						//담당자 정보 저장 
//						iproPrpoBidReqDao.vendDtlUpdate(parameterMap);
//					} 
					
					
//					biopChk = iproPrpoBidReqDao.vendBizBiopChk(parameterMap);
//					if(opngRnk !=null){
//						if(biopChk == 0){	//업체개찰테이블 
//							iproPrpoBidReqDao.insertBidOpenRegist(parameterMap);
//						}else{
//							iproPrpoBidReqDao.vendBiopUpdate(parameterMap);
//						}
//					}
					 
				}
				 
			}
			 
		}
		 
		
		//T_BI_ROUND 저장
//		iproPrpoBidReqDao.updateBidRound(parameterMap);  

		/*---------------첨부파일 업로드 ---------------*/
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) parameterMap.get("multipartRequest");
		//구분에 따라 파일업로드
		FwkParameterMap fileParameterMap =  CmmnUtil.fileGbnMultiUpload(multipartRequest, "P_FILE", contextPath, parameterMap);

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
				parameterMap.put("P_FILE_GRP_NO", fileParameterMap.get("atchFileGroupNo"));
				// 실제삭제
//				iproPrpoBidReqDao.deleteBidFile(parameterMap); 
				// 다시 insert
				//설계 파일테이블에 저장 T_BI_FILE
//				iproPrpoBidReqDao.insertBidFile(parameterMap);
			} 
		}  
		
		//진행상태수정 
//		iproPrpoBidReqDao.updateBidMstStat(parameterMap);
		//이력 등록
//		iproPrpoBidReqDao.insertBidProgHist(parameterMap);
		  
		return trans; 
	}


	@Override
	public FwkTransferObject bidPblancPrcnExcelList(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		if(parameterMap.get("P_RQR_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_RQR_BEGIN_DT_S"))){ // 작성일자 시작
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		if(parameterMap.get("P_RQR_END_DT_S") != null && !"".equals(parameterMap.get("P_RQR_END_DT_S"))){ // 작성일자 종료
			parameterMap.put("P_RQR_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_RQR_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		} 
		
		List<FwkDataEntity> bidPblancPrcnList = iproEbidPblancPrcnDao.selectBidPblancPrcnExcelList(parameterMap);
		
		for(int i=0; i < bidPblancPrcnList.size(); i++){
			FwkDataEntity bidPblancPrcn= bidPblancPrcnList.get(i);

			bidPblancPrcnList.get(i).put("ESTT_AMT", FwkFormatUtil.formatMoney(bidPblancPrcn.getString("ESTT_AMT")));
			bidPblancPrcnList.get(i).put("RQR_DE", FwkFormatUtil.formatDate(bidPblancPrcn.getString("RQR_DE"), "yyyyMMdd", "yyyy-MM-dd"));
		}
		
		trans.put(IproEbidPblancPrcnService.BID_PBLANC_PRCN_LIST, bidPblancPrcnList);
		
		return trans;
	}
	
	
}