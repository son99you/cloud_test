package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrdprcManageDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidProconService;

/**
 * 입찰계획 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidProconService")
public class IproEbidProconServiceImpl extends AbstractFwkService implements IproEbidProconService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidProconServiceImpl.class);
	
	@Resource(name="iproEbidProconDao")
	private IproEbidProconDao iproEbidProconDao;
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="iproEbidPrdprcManageDao")
	private IproEbidPrdprcManageDao iproEbidPrdprcManageDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanAprvlListInqireWithPgng
	 * @date : 2015. 01. 14.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 01. 14.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidPlanAprvlListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_BID_LMT_BEGIN_AMOUNT_S") != null && !"".equals(parameterMap.get("P_BID_LMT_BEGIN_AMOUNT_S"))){ // 입찰한도액 시작
			parameterMap.put("P_BID_LMT_BEGIN_AMOUNT_S", parameterMap.get("P_BID_LMT_BEGIN_AMOUNT_S")+"000000"); // 기본 백만원
		}
		if(parameterMap.get("P_BID_LMT_END_AMOUNT_S") != null && !"".equals(parameterMap.get("P_BID_LMT_END_AMOUNT_S"))){ // 입찰한도액 종료
			parameterMap.put("P_BID_LMT_END_AMOUNT_S", parameterMap.get("P_BID_LMT_END_AMOUNT_S")+"000000"); // 기본 백만원
		}
		if(parameterMap.get("P_PBLANC_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_PBLANC_BEGIN_DT_S"))){ // 공고일자 시작
			parameterMap.put("P_PBLANC_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_PBLANC_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_PBLANC_END_DT_S") != null && !"".equals(parameterMap.get("P_PBLANC_END_DT_S"))){ // 공고일자 종료
			parameterMap.put("P_PBLANC_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_PBLANC_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(IproEbidProconService.BID_PLAN_APRVL_LIST, 
				iproEbidProconDao.selectBidPlanAprvlListWithPgng(parameterMap));
		trans.put(IproEbidProconService.BID_PLAN_APRVL_LIST_TOTCNT, 
				iproEbidProconDao.selectBidPlanAprvlListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanAprvlDetailInqire
	 * @date : 2015. 01. 28.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 01. 28.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidPlanAprvlDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
//		SDBCrypto crypto = null;
//		crypto = new SDBCrypto();
//		crypto = SDBCrypto.getInstanceDomain("devkoica","192.168.1.8", 9003);
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		/************ DB복호화 시작 *****************/
		
//		dataEntity.put("CHRGR_EMAL", crypto.decrypt("DBSEC","TB_KEY","ARIA", (String) dataEntity.get("CHRGR_EMAL")));
//		dataEntity.put("RQSTR_EMAL", crypto.decrypt("DBSEC","TB_KEY","ARIA", (String) dataEntity.get("RQSTR_EMAL")));
		
//		dataEntity.put("CHRGR_EMAL", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("CHRGR_EMAL")));
//		dataEntity.put("RQSTR_EMAL", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("RQSTR_EMAL")));
		
		/************ DB복호화 종료 *****************/
		
		trans.put(IproEbidProconService.BID_PLAN_APRVL_DETAIL
				,dataEntity);
		
		
		trans.put(IproEbidProconService.BID_PRDLS_LIST, 
				iproEbidPlanDao.selectBidPrdlstList(parameterMap));
		trans.put(IproEbidProconService.BID_NMFPC_ENTRPS_LIST, 
				iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);
		trans.put(IproEbidProconService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidProconService.BID_ATCH_DOC_LIST, 
					comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 전자결재 여부
//		FwkParameterMap sanctnParameterMap = new FwkParameterMap();
//		sanctnParameterMap.put("P_ANNC_NO", parameterMap.get("P_ANNC_NO"));
//		sanctnParameterMap.put("P_ANNC_NGR", parameterMap.get("P_ANNC_NGR"));
//		sanctnParameterMap.put("P_SANCTN_JOB_CD", "CPR010");
//		trans.put("elsaInfo",  iepPrcmPrcureCmmnService.elctrnSanctnInfoInqire(sanctnParameterMap).getDataEntity("sanctnInfoInqire"));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 수정 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanUpdtForm
	 * @date : 2015. 01. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 01. 28.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidPlanAprvlUpdtForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		/************ DB복호화 시작 *****************/
		
//			dataEntity.put("CHRGR_EMAL", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("CHRGR_EMAL")));
//			dataEntity.put("RQSTR_EMAL", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("RQSTR_EMAL")));
		
		/************ DB복호화 종료 *****************/
		
		trans.put(IproEbidProconService.BID_PLAN_APRVL_DETAIL
				,dataEntity);
		
		
		trans.put(IproEbidProconService.BID_PRDLS_LIST, 
				iproEbidPlanDao.selectBidPrdlstList(parameterMap) );
		trans.put(IproEbidProconService.BID_NMFPC_ENTRPS_LIST, 
				iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
		parameterMap.put("P_BID_FSCD", "DI01");
		
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap); 
		trans.put(IproEbidProconService.BID_ATCH_DOC, fileDataEntity);
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidProconService.BID_ATCH_DOC_LIST, 
					comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanUpdt
	 * @date : 2015. 02. 02.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 02.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	@Override
	public void bidPlanAprvlUpdt(final FwkParameterMap parameterMap) throws Exception {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "bid";
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_FILE", contextPath);
		
		// Format 변경 및 NULL 처리
		if(parameterMap.get("P_OPNG_DT") != null){
			parameterMap.put("P_OPNG_DT", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 개찰일시
		}
		if(parameterMap.get("P_ANNC_DT") != null){
			parameterMap.put("P_ANNC_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 공고일시
		}
//		if(parameterMap.get("P_TECHEVL_DOCSU_BEGIN_DT") != null){
//			parameterMap.put("P_TECHEVL_DOCSU_BEGIN_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TECHEVL_DOCSU_BEGIN_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 기술심사서류 시작일시
//		}
//		if(parameterMap.get("P_TECHEVL_DOCSU_END_DT") != null){
//			parameterMap.put("P_TECHEVL_DOCSU_END_DT", FwkFormatUtil.formatDate(parameterMap.get("P_TECHEVL_DOCSU_END_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 기술심사서류 종료일시
//		}
		if(parameterMap.get("P_BIDC_SBMT_STDT") != null){
			parameterMap.put("P_BIDC_SBMT_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 입찰서 제출 시작일시
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT") != null){
			parameterMap.put("P_BIDC_SBMT_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 입찰서 제출 종료일시
		}
		if(parameterMap.get("P_BRFS_DT") != null){
			parameterMap.put("P_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 입찰설명회 일시
		}
		if(parameterMap.get("P_PRTC_APLY_STDT") != null){
			parameterMap.put("P_PRTC_APLY_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRTC_APLY_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 입찰설명회 시작 일시
		}
		if(parameterMap.get("P_PRTC_APLY_ENDT") != null){
			parameterMap.put("P_PRTC_APLY_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRTC_APLY_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")); // 입찰설명회 종료 일시
		}
		
		// 입찰계획진행상태코드 (작성완료)
		parameterMap.put("P_BID_PSCD", "PE20");
		// 수정일시
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
		
		iproEbidPlanDao.updateBidPlanUpdt(parameterMap);
		
		// 입찰 일정 수정
		// 입찰공고일시 시 분 합치기
		if(parameterMap.get("P_ANNC_DT") != null && parameterMap.get("P_BID_PBLANC_HH") != null && parameterMap.get("P_BID_PBLANC_MM") != null && parameterMap.get("P_ANNC_DT").toString().length() > 7 && parameterMap.get("P_BID_PBLANC_HH").toString().length() > 1 && parameterMap.get("P_BID_PBLANC_MM").toString().length() > 1){
			parameterMap.put("P_ANNC_DT", parameterMap.get("P_ANNC_DT").toString() + parameterMap.get("P_BID_PBLANC_HH").toString() + parameterMap.get("P_BID_PBLANC_MM").toString()+"00");
		}
		// 입찰설명회일시 시 분 합치기
		if(parameterMap.get("P_BRFS_DT") != null && parameterMap.get("P_BID_DC_PEO_HH") != null && parameterMap.get("P_BID_DC_PEO_MM") != null && parameterMap.get("P_BRFS_DT").toString().length() > 7 && parameterMap.get("P_BID_DC_PEO_HH").toString().length() > 1 && parameterMap.get("P_BID_DC_PEO_MM").toString().length() > 1){
			parameterMap.put("P_BRFS_DT", parameterMap.get("P_BRFS_DT").toString() + parameterMap.get("P_BID_DC_PEO_HH").toString() + parameterMap.get("P_BID_DC_PEO_MM").toString()+"00");
		}
		// 참가신청서 제출기간 시 분 합치기
		if(parameterMap.get("P_PRTC_APLY_STDT") != null && parameterMap.get("P_PARE_BEGIN_HH") != null && parameterMap.get("P_PARE_BEGIN_MM") != null && parameterMap.get("P_PRTC_APLY_STDT").toString().length() > 7 && parameterMap.get("P_PARE_BEGIN_HH").toString().length() > 1 && parameterMap.get("P_PARE_BEGIN_MM").toString().length() > 1){
			parameterMap.put("P_PRTC_APLY_STDT", parameterMap.get("P_PRTC_APLY_STDT").toString() + parameterMap.get("P_PARE_BEGIN_HH").toString() + parameterMap.get("P_PARE_BEGIN_MM").toString()+"00");
		}
		if(parameterMap.get("P_PRTC_APLY_ENDT") != null && parameterMap.get("P_PARE_END_HH") != null && parameterMap.get("P_PARE_END_MM") != null && parameterMap.get("P_PRTC_APLY_ENDT").toString().length() > 7 && parameterMap.get("P_PARE_END_HH").toString().length() > 1 && parameterMap.get("P_PARE_END_MM").toString().length() > 1){
			parameterMap.put("P_PRTC_APLY_ENDT", parameterMap.get("P_PRTC_APLY_ENDT").toString() + parameterMap.get("P_PARE_END_HH").toString() + parameterMap.get("P_PARE_END_MM").toString()+"00");
		}
		// 입찰서 제출기간 시 분 합치기
		if(parameterMap.get("P_BIDC_SBMT_STDT") != null && parameterMap.get("P_BIPA_PRESENTN_BEGIN_HH") != null && parameterMap.get("P_BIPA_PRESENTN_BEGIN_MM") != null && parameterMap.get("P_BIDC_SBMT_STDT").toString().length() > 7 && parameterMap.get("P_BIPA_PRESENTN_BEGIN_HH").toString().length() > 1 && parameterMap.get("P_BIPA_PRESENTN_BEGIN_MM").toString().length() > 1){
			parameterMap.put("P_BIDC_SBMT_STDT", parameterMap.get("P_BIDC_SBMT_STDT").toString() + parameterMap.get("P_BIPA_PRESENTN_BEGIN_HH").toString() + parameterMap.get("P_BIPA_PRESENTN_BEGIN_MM").toString()+"00");
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT") != null && parameterMap.get("P_BIPA_PRESENTN_END_HH") != null && parameterMap.get("P_BIPA_PRESENTN_END_MM") != null && parameterMap.get("P_BIDC_SBMT_STDT").toString().length() > 7 && parameterMap.get("P_BIPA_PRESENTN_END_HH").toString().length() > 1 && parameterMap.get("P_BIPA_PRESENTN_END_MM").toString().length() > 1){
			parameterMap.put("P_BIDC_SBMT_ENDT", parameterMap.get("P_BIDC_SBMT_ENDT").toString() + parameterMap.get("P_BIPA_PRESENTN_END_HH").toString() + parameterMap.get("P_BIPA_PRESENTN_END_MM").toString()+"00");
		}
		// 기술심사 제출일자 등록 ( 입찰서 제출일자와 동일 )
//		if(parameterMap.get("P_BIDC_SBMT_STDT") != null && parameterMap.get("P_BIDC_SBMT_STDT").toString().length() > 0){
//			parameterMap.put("P_TECHEVL_DOCSU_BEGIN_DT", parameterMap.get("P_BIDC_SBMT_STDT").toString() );
//		}
//		if(parameterMap.get("P_BIDC_SBMT_ENDT") != null && parameterMap.get("P_BIDC_SBMT_ENDT").toString().length() > 0){
//			parameterMap.put("P_TECHEVL_DOCSU_END_DT", parameterMap.get("P_BIDC_SBMT_ENDT").toString() );
//		}
		// 개찰일시 시 분 합치기
		if(parameterMap.get("P_OPNG_DT") != null && parameterMap.get("P_OPENG_HH") != null && parameterMap.get("P_OPENG_MM") != null && parameterMap.get("P_OPNG_DT").toString().length() > 7 && parameterMap.get("P_OPENG_HH").toString().length() > 1 && parameterMap.get("P_OPENG_MM").toString().length() > 1){
			parameterMap.put("P_OPNG_DT", parameterMap.get("P_OPNG_DT").toString() + parameterMap.get("P_OPENG_HH").toString() + parameterMap.get("P_OPENG_MM").toString()+"00");
		}
		
		iproEbidPlanDao.updateBidSchdulUpdt(parameterMap);
		
		//입찰 지명업체 삭제
		iproEbidPlanDao.deleteBidNmfpcEntrps(parameterMap);
		
		if("10001".equals(parameterMap.get("CONT_MTCD"))){
			//입찰 지명업체 등록
			Object entrpsRegistNo = parameterMap.get("P_VEND_REG_NO");
			if(entrpsRegistNo != null){
				if(entrpsRegistNo instanceof String){
					parameterMap.put("P_VEND_REG_NO", entrpsRegistNo);
					iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);
				}else if(entrpsRegistNo instanceof ArrayList){
					ArrayList<String> entrpsRegistNoList = (ArrayList<String>) entrpsRegistNo;
					for(int i =0; i < entrpsRegistNoList.size(); i++){
						parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList.get(i));
						iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);
					}
				}else if(entrpsRegistNo instanceof String[]){
					String[] entrpsRegistNoList = (String[]) entrpsRegistNo;
					for(int i =0; i < entrpsRegistNoList.length; i++){
						parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList[i]);
						iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);
					}
				}
			}
		}
		
		// 비예가 일때 입찰한도금액(ESTT_AMT) 저장
		if("180002".equals(parameterMap.get("P_ESTPC_SECD"))){
			//복수예가 삭제
			iproEbidPlanDao.deleteCompnoPrdprc(parameterMap);
			// 예가 삭제
			iproEbidPlanDao.deletePrdprc(parameterMap);
			
			// 예가등록
			parameterMap.put("P_BASE_ESTPC_AMT", parameterMap.get("P_ESTT_AMT"));
			parameterMap.put("P_ESTPC_AMT", parameterMap.get("P_ESTT_AMT"));
			parameterMap.put("ESTPC_SV_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
			parameterMap.put("ESTPC_REGR_ID", parameterMap.get("P_USR_ID")); 
			iproEbidPrdprcManageDao.insertPrdprcRegist(parameterMap);
		}
		
		//입찰 품목 삭제
		iproEbidPlanDao.deleteBidPrdlst(parameterMap);
		
		// 입찰구분이 구매 일 경우만 품목 정보 저장 
		if("0".equals(parameterMap.get("P_CONT_SECD"))){
			//입찰 품목 등록
			Object g2bPrdlstCd = parameterMap.get("P_ITEM_NO");
			Object g2bUnitNm = parameterMap.get("P_ITEM_UNNM");
			Object qlyNm = parameterMap.get("P_ITEM_NM");
			Object prdlstQy = parameterMap.get("P_ITEM_QTY");
//			Object prdlstRefrnCn = parameterMap.get("P_PRDLST_REFRN_CN");
			if(g2bPrdlstCd != null){
				if(g2bPrdlstCd instanceof String){
					parameterMap.put("P_ITEM_NO", g2bPrdlstCd);
					parameterMap.put("P_ITEM_UNNM", g2bUnitNm);
					parameterMap.put("P_ITEM_NM", qlyNm);
					parameterMap.put("P_ITEM_QTY", prdlstQy);
//					parameterMap.put("P_PRDLST_REFRN_CN", prdlstRefrnCn);
					iproEbidPlanDao.insertBidPrdlstRegist(parameterMap);
				}else if(g2bPrdlstCd instanceof ArrayList){
					ArrayList<String> g2bPrdlstCdList = (ArrayList<String>) g2bPrdlstCd;
					ArrayList<String> g2bUnitNmList = (ArrayList<String>) g2bUnitNm;
					ArrayList<String> qlyNmList = (ArrayList<String>) qlyNm;
					ArrayList<String> prdlstQyList = (ArrayList<String>) prdlstQy;
//					ArrayList<String> prdlstRefrnCnList = (ArrayList<String>) prdlstRefrnCn;
					for(int i =0; i < g2bPrdlstCdList.size(); i++){
						parameterMap.put("P_ITEM_NO", g2bPrdlstCdList.get(i));
						if(g2bUnitNmList != null && g2bUnitNmList.get(i) != null){
							parameterMap.put("P_ITEM_UNNM", g2bUnitNmList.get(i));
						}
						if(qlyNmList != null && qlyNmList.get(i) != null){
							parameterMap.put("P_ITEM_NM", qlyNmList.get(i));
						}
						if(prdlstQyList != null && prdlstQyList.get(i) != null){
							parameterMap.put("P_ITEM_QTY", prdlstQyList.get(i));
						}
//						if(prdlstRefrnCnList != null && prdlstRefrnCnList.get(i) != null){
//							parameterMap.put("P_PRDLST_REFRN_CN", prdlstRefrnCnList.get(i));
//						}
						iproEbidPlanDao.insertBidPrdlstRegist(parameterMap);
					}
				}else if(g2bPrdlstCd instanceof String[]){
					String[] g2bPrdlstCdList = (String[]) g2bPrdlstCd;
					String[] g2bUnitNmList = (String[]) g2bUnitNm;
					String[] qlyNmList = (String[]) qlyNm;
					String[] prdlstQyList = (String[]) prdlstQy;
//					String[] prdlstRefrnCnList = (String[]) prdlstRefrnCn;
					for(int i =0; i < g2bPrdlstCdList.length; i++){
						parameterMap.put("P_ITEM_NO", g2bPrdlstCdList[i]);
						if(g2bUnitNmList != null && g2bUnitNmList[i] != null){
							parameterMap.put("P_ITEM_UNNM", g2bUnitNmList[i]);
						}
						if(qlyNmList != null && qlyNmList[i] != null){
							parameterMap.put("P_ITEM_NM", qlyNmList[i]);
						}
						if(prdlstQyList != null && prdlstQyList[i] != null){
							parameterMap.put("P_ITEM_QTY", prdlstQyList[i]);
						}
//						if(prdlstRefrnCnList != null && prdlstRefrnCnList[i] != null){
//							parameterMap.put("P_PRDLST_REFRN_CN", prdlstRefrnCnList[i]);
//						}
						iproEbidPlanDao.insertBidPrdlstRegist(parameterMap);
					}
				}
			}
		}
		
		if(parameterMap.get("P_DEL_SN") != null && !"".equals(parameterMap.get("P_DEL_SN"))){
			String delSn = parameterMap.getString("P_DEL_SN");
			String[] delSnArray = delSn.split(",");
			List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < delSnArray.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("P_ATCHMNFL_SN", delSnArray[i]);
				fileList.add(map);
			}
			parameterMap.put("fileList", fileList);
			comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);		
		}
		
		// 수정페이지에서 첨부파일이 신규로 등록되는 경우 처리
		
		if(fileParameterMap != null){
			String atchFileGroupNo = "";
			if(parameterMap.get("P_FILE_GRP_NO") == null || "".equals(parameterMap.get("P_FILE_GRP_NO"))){
				atchFileGroupNo = fileParameterMap.getString("atchFileGroupNo");
			}else{
				atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");
			}

			List<Map<String, Object>> list = (List<Map<String, Object>>)fileParameterMap.get("list");
				
			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("fileList", list);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			if(parameterMap.get("P_FILE_GRP_NO") == null || "".equals(parameterMap.get("P_FILE_GRP_NO"))){
				comAtmaAtchFileDao.insertAtchFileGroupRegist(parameterMap);
			}
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
			
			if(parameterMap.get("P_FILE_GRP_NO") == null || "".equals(parameterMap.get("P_FILE_GRP_NO"))){
				parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
				// 첨부파일 저장
				parameterMap.put("P_BID_FSCD", "DI01");
				iproEbidPlanDao.insertBidAtchDocRegist(parameterMap);
			}
		}
		
	}
	
}