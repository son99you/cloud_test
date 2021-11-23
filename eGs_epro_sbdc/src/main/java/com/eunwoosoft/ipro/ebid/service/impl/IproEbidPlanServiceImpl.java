package com.eunwoosoft.ipro.ebid.service.impl; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.itfc.dao.CommItfcDao;
import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrdprcManageDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPrdprcManageService;

/**
 * 입찰계획 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidPlanService")
public class IproEbidPlanServiceImpl extends AbstractFwkService implements IproEbidPlanService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidPlanServiceImpl.class);
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproEbidPrdprcManageDao")
	private IproEbidPrdprcManageDao iproEbidPrdprcManageDao;
	
	@Resource(name="iproEbidPrdprcManageService")
	private IproEbidPrdprcManageService iproEbidPrdprcManageService;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao;
	
	@Resource(name="iproEbidPblancDao")
	private IproEbidPblancDao iproEbidPblancDao;
	
	@Resource(name="commItfcDao")
	private CommItfcDao commItfcDao; 
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanListInqireWithPgng
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
	public FwkTransferObject bidPlanListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(IproEbidPlanService.BID_PLAN_LIST, iproEbidPlanDao.selectBidPlanListWithPgng(parameterMap));
		trans.put(IproEbidPlanService.BID_PLAN_LIST_TOTCNT, iproEbidPlanDao.selectBidPlanListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanDetailInqire
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
	public FwkTransferObject bidPlanDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰마스터조회
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
		//입찰품목조회
		trans.put(IproEbidPlanService.BID_PRDLS_LIST,  iproEbidPlanDao.selectBidPrdlstList(parameterMap));   // T_BI_ITEM SELECT
		//입찰업체조회
		trans.put(IproEbidPlanService.BID_NMFPC_ENTRPS_LIST, iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
		parameterMap.put("P_BID_FSCD", "DI01");
		//입찰첨부파일조회
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);   // T_BI_FILE SELECT
		trans.put(IproEbidPlanService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
			//parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			//trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		/*
		parameterMap.put("P_BID_FSCD", "DI00");
		FwkDataEntity redFileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);
		trans.put(IproEbidPlanService.BID_ATCH_DOC+"Red", redFileDataEntity);
		
		if(redFileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", redFileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST+"Red", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		*/
		parameterMap.put("P_BID_APPR_TSCD", "A");
		trans.put("biApprCntc",iproEbidPlanDao.selectBiApprCntc(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 사용자 정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : loginEmplyrInfoInqire
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
	 */
	@Override
	public FwkTransferObject bidPlanRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//유찰 데이터
		if(parameterMap.get("P_ANNC_NO") != null && !"".equals(parameterMap.getString("P_ANNC_NO"))){
			FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectBidInfoDetail(parameterMap);
			
			trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
			
			trans.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPlanDao.selectBidPrdlstList(parameterMap));   // T_BI_ITEM SELECT
			trans.put(IproEbidPlanService.BID_NMFPC_ENTRPS_LIST, iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
			parameterMap.put("P_BID_FSCD", "DI01");
			FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);   // T_BI_FILE SELECT
			trans.put(IproEbidPlanService.BID_ATCH_DOC, fileDataEntity);
			
			if(fileDataEntity != null){
				dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
				trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
			}
			
		//계약의뢰 데이터
		}else if(parameterMap.get("P_BID_WAIT_NO") != null && !"".equals(parameterMap.getString("P_BID_WAIT_NO"))){
			FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectPaMstInfo(parameterMap);
			trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
			
			List<FwkDataEntity> itemList = iproEbidPlanDao.selectPaItemInfo(parameterMap);
			trans.put(IproEbidPlanService.BID_PRDLS_LIST, itemList);
			
		}
		
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
	public FwkTransferObject bidPlanUpdtForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		//입찰마스터 조회
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidPlanService.BID_PLAN_DETAIL, dataEntity);
		//입찰품목조회
		trans.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPlanDao.selectBidPrdlstList(parameterMap));   // T_BI_ITEM SELECT
		//입찰지명업체조회
		trans.put(IproEbidPlanService.BID_NMFPC_ENTRPS_LIST, iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
		
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);   // T_BI_FILE SELECT 
		trans.put(IproEbidPlanService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
		}		
		
		if(!"".equals(dataEntity.getString("IFC_ID"))){
			parameterMap.put("P_IFC_ID", dataEntity.getString("IFC_ID"));
			trans.put(IproEbidPlanService.BFAN_INFO, iproEbidPlanDao.selectBfanMst(parameterMap));   // T_BI_BFAN_MST SELECT
		}
		
		return trans;
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 현재 진행중인 재공고건이 있는지 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanUpdtForm
	 * @date : 2019. 08. 08.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 08. 08.    	은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject reAnnoCheck(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectReAnnoCount(parameterMap);
		
		trans.put("P_CNT", dataEntity.getString("P_CNT"));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 수기등록 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanRegist
	 * @date : 2015. 01. 22.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 01. 22.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 * @throws IOException 
	 */
	@Override
	public FwkTransferObject bidPlanRegist(final FwkParameterMap parameterMap) throws IOException {
		FwkTransferObject trans = new FwkTransferObject();
		
		// Format 변경
		//계약기간시작
		if(!"".equals(parameterMap.getString("P_CONT_STDE")) ){
			parameterMap.put("P_CONT_STDE", FwkFormatUtil.formatDate(parameterMap.get("P_CONT_STDE").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		//계약기간종료
		if(!"".equals(parameterMap.getString("P_CONT_ENDE"))){
			parameterMap.put("P_CONT_ENDE", FwkFormatUtil.formatDate(parameterMap.get("P_CONT_ENDE").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		//현장설명회일시
		if(!"".equals(parameterMap.getString("P_SITE_BRFS_DT")) && !"".equals(parameterMap.getString("P_SITE_BRFS_DT_HH")) && !"".equals(parameterMap.getString("P_SITE_BRFS_DT_MM"))){
			parameterMap.put("P_SITE_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_SITE_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_SITE_BRFS_DT_HH")+parameterMap.get("P_SITE_BRFS_DT_MM")+"00"); 
		}
		//입찰설명회일시
		if(!"".equals(parameterMap.getString("P_BRFS_DT")) && !"".equals(parameterMap.getString("P_BRFS_DT_HH")) && !"".equals(parameterMap.getString("P_BRFS_DT_MM"))){
			parameterMap.put("P_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BRFS_DT_HH")+parameterMap.get("P_BRFS_DT_MM")+"00"); 
		}
		
		// 공고번호 생성
		if(parameterMap.get("P_ANNC_NO") == null || "".equals(parameterMap.get("P_ANNC_NO"))){
			if(parameterMap.get("P_BID_MTCD").equals("EST")) {
				parameterMap.put("P_CHAR", "Q");
			}else {
				parameterMap.put("P_CHAR", "BD");
			}
/*			if(parameterMap.get("P_BID_MTCD").equals("BID")) {
				parameterMap.put("P_CHAR", "P");
			}else if(parameterMap.get("P_BID_MTCD").equals("EST")) {
				parameterMap.put("P_CHAR", "E");
			}else if(parameterMap.get("P_BID_MTCD").equals("PRVT")) {
				parameterMap.put("P_CHAR", "R");
			}else {
				parameterMap.put("P_CHAR", "P");
			}
*/			String pblancNo = iproEbidPlanDao.selectPblancNoCreat(parameterMap);
			parameterMap.put("P_ANNC_NO", pblancNo);
			trans.put("P_ANNC_NO", pblancNo);
		}
		if(parameterMap.get("P_ANNC_NGR") == null || "".equals(parameterMap.get("P_ANNC_NGR"))){
			parameterMap.put("P_ANNC_NGR", "1");
			trans.put("P_ANNC_NGR", 1);
		}else{
			parameterMap.put("P_ANNC_NGR_OLD", parameterMap.getInt("P_ANNC_NGR"));
			parameterMap.put("P_ANNC_NGR", parameterMap.getInt("P_ANNC_NGR")+1);
			trans.put("P_ANNC_NGR", parameterMap.getInt("P_ANNC_NGR")+1);
			parameterMap.put("P_LT_NGR_YN","N");
			iproEbidPlanDao.updateLtNgr(parameterMap);
		}
		parameterMap.put("P_LT_NGR_YN", "Y");
		
		if(parameterMap.get("P_ROUND_NO") != null && !"".equals(parameterMap.get("P_ROUND_NO"))){
			parameterMap.put("P_ROUND_NO_OLD", parameterMap.getInt("P_ROUND_NO"));
		}
		parameterMap.put("P_ROUND_NO", "1");
		trans.put("P_ROUND_NO", 1);
		
		// 삭제여부 (기본값 N)
		parameterMap.put("P_DEL_AT", "N");
		//계약구분 공사 아니면 현장설명회 N
		parameterMap.put("P_SITE_BRFS_YN","");
		//계약방법 수의계약시 수의계약여부
		parameterMap.put("P_PRVT_CONT_YN","N");
		//입찰 구분코드 작성
		parameterMap.put("P_PRCDC_SBMT_YN", "Y");	//가격서 제출 여부 YN 고정		
		
		
		if(parameterMap.get("P_EST") != null && !"".equals(parameterMap.get("P_EST"))){
			// 입찰진행상태코드 (공고중)
			parameterMap.put("P_BID_PSCD", "PF20");
		}else{
			// 입찰진행상태코드 (공고전)
			parameterMap.put("P_BID_PSCD", "PF02");
		}
		
		//입찰설명회 N 이면 입찰설명회참석필수여부 값 지우기
		if("N".equals(parameterMap.getString("P_BID_BRFS_YN"))){
			parameterMap.put("P_BID_BRFS_ATND_YN","");
		}
		//비예가 기초금액을 추정금액으로
//		if("180002".equals(parameterMap.get("P_ESTPC_SECD"))){
//			parameterMap.put("P_BASE_AMT", parameterMap.get("P_ESTT_AMT"));
//		}
		if("5".equals(parameterMap.getString("P_CONT_SECD"))){
			parameterMap.put("P_SBID_MTCD","60");
		}		
		
		iproEbidPlanDao.insertBidPlanRegist(parameterMap);   // T_BI_MST INSERT
		
		parameterMap.put("P_RMK", "");
		
		// 입찰 일정 등록
		if(!"".equals(parameterMap.getString("P_PRTC_APLY_ENDT")) && !"".equals(parameterMap.getString("P_PRTC_APLY_ENDT_HH")) && !"".equals(parameterMap.getString("P_PRTC_APLY_ENDT_MM"))){
			parameterMap.put("P_PRTC_APLY_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRTC_APLY_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRTC_APLY_ENDT_HH")+parameterMap.get("P_PRTC_APLY_ENDT_MM")+"00"); 
		}
		//입찰공고일시
		if(!"".equals(parameterMap.getString("P_ANNC_DT")) && !"".equals(parameterMap.getString("P_ANNC_DT_HH")) && !"".equals(parameterMap.getString("P_ANNC_DT_MM"))){
			parameterMap.put("P_ANNC_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_ANNC_DT_HH")+parameterMap.get("P_ANNC_DT_MM")+"00"); 
		}
		//입찰서제출기간시작
		if(!"".equals(parameterMap.getString("P_BIDC_SBMT_STDT")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_STDT_HH")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_STDT_MM"))){
			parameterMap.put("P_BIDC_SBMT_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BIDC_SBMT_STDT_HH")+parameterMap.get("P_BIDC_SBMT_STDT_MM")+"00"); 
		}
		//입찰서제출기간종료
		if(!"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT_HH")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT_MM"))){
			parameterMap.put("P_BIDC_SBMT_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BIDC_SBMT_ENDT_HH")+parameterMap.get("P_BIDC_SBMT_ENDT_MM")+"00"); 
		}
		//제안서제출기간시작
		if(!"".equals(parameterMap.getString("P_PRPDC_SBMT_STDT")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_STDT_HH")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_STDT_MM"))){
			parameterMap.put("P_PRPDC_SBMT_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRPDC_SBMT_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRPDC_SBMT_STDT_HH")+parameterMap.get("P_PRPDC_SBMT_STDT_MM")+"00"); 
		}
		//제안서제출기간종료
		if(!"".equals(parameterMap.getString("P_PRPDC_SBMT_ENDT")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_ENDT_HH")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_ENDT_MM"))){
			parameterMap.put("P_PRPDC_SBMT_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRPDC_SBMT_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRPDC_SBMT_ENDT_HH")+parameterMap.get("P_PRPDC_SBMT_ENDT_MM")+"00"); 
		}
		//개찰일시
		if(!"".equals(parameterMap.getString("P_OPNG_DT")) && !"".equals(parameterMap.getString("P_OPNG_DT_HH")) && !"".equals(parameterMap.getString("P_OPNG_DT_MM"))){
			parameterMap.put("P_OPNG_DT", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_OPNG_DT_HH")+parameterMap.get("P_OPNG_DT_MM")+"00"); 
		}
		iproEbidPlanDao.insertBidSchdulRegist(parameterMap);   // T_BI_ROUND INSERT
		
		//재공고 예가복사 위한 처리
		if((parameterMap.get("P_BF_ANNC_NO") != null && !"".equals(parameterMap.get("P_BF_ANNC_NO"))) || (parameterMap.get("P_ANNC_NGR_OLD") != null && !"".equals(parameterMap.get("P_ANNC_NGR_OLD")))){
			if(parameterMap.get("P_BF_ANNC_NO") != null && !"".equals(parameterMap.get("P_BF_ANNC_NO"))) {
				parameterMap.put("P_ANNC_NO_TEMP", parameterMap.get("P_ANNC_NO"));
				parameterMap.put("P_ANNC_NO", parameterMap.get("P_BF_ANNC_NO"));
				parameterMap.put("P_ANNC_NGR_OLD", parameterMap.get("P_BF_ANNC_NGR"));
			}
			iproEbidPlanDao.insertPrdprcRepblancRegist(parameterMap);   // T_BI_ESTC_MST INSERT
			iproEbidPlanDao.insertCompnoPrdprcRepblancRegist(parameterMap);   // T_BI_PLRL_ESTC INSERT
			if(parameterMap.get("P_BF_ANNC_NO") != null && !"".equals(parameterMap.get("P_BF_ANNC_NO"))) {
				//공고번호 원복
				parameterMap.put("P_ANNC_NO", parameterMap.get("P_ANNC_NO_TEMP"));
			}
		}else{
			if(!"".equals(parameterMap.getString("P_BASE_AMT"))){
				// 예가등록
				parameterMap.put("P_BASE_ESTPC_AMT", parameterMap.get("P_BASE_AMT"));
			}
			parameterMap.put("P_FILE_GRP_NO", "");
			parameterMap.put("P_ESTC_PSCD", "ES00");
			iproEbidPrdprcManageDao.insertPrdprcRegist(parameterMap);   // T_BI_ESTC_MST INSERT
		}
		
		if("10001".equals(parameterMap.get("P_CONT_MTCD")) || "10005".equals(parameterMap.get("P_CONT_MTCD")) || "10003".equals(parameterMap.get("P_CONT_MTCD")) || "10006".equals(parameterMap.get("P_CONT_MTCD"))){
			//입찰 지명업체 등록
			Object entrpsRegistNo = parameterMap.get("P_VEND_REG_NO");
			if(entrpsRegistNo != null){
				if(entrpsRegistNo instanceof String){
					parameterMap.put("P_VEND_REG_NO", entrpsRegistNo);
					iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);   // T_BI_VEND_MST INSERT
				}else if(entrpsRegistNo instanceof ArrayList){
					ArrayList<String> entrpsRegistNoList = (ArrayList<String>) entrpsRegistNo;
					for(int i =0; i < entrpsRegistNoList.size(); i++){
						parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList.get(i));
						iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);   // T_BI_VEND_MST INSERT
					}
				}else if(entrpsRegistNo instanceof String[]){
					String[] entrpsRegistNoList = (String[]) entrpsRegistNo;
					for(int i =0; i < entrpsRegistNoList.length; i++){
						parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList[i]);
						iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);   // T_BI_VEND_MST INSERT
						
					}
				}
			}
		}
		
		if(parameterMap.get("P_ITEM_NO") != null) {
			//입찰 품목 등록
			Object P_ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object P_ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object P_STND_NM = parameterMap.get("P_STND_NM");
			Object P_ITEM_UNNM = parameterMap.get("P_ITEM_UNNM");
			Object P_ITEM_QTY = parameterMap.get("P_ITEM_QTY");
			Object P_RQST_UNIT = parameterMap.get("P_RQST_UNIT");
			Object P_SCH_UNIT = parameterMap.get("P_SCH_UNIT");
			Object P_PCRQ_NO = parameterMap.get("P_PCRQ_NO");
			Object P_PCRQ_ITEM_SN = parameterMap.get("P_PCRQ_ITEM_SN");
			
			if(P_ITEM_NO != null){
				if(P_ITEM_NO instanceof String){
					iproEbidPlanDao.insertIfBidPrdlstRegist(parameterMap);   // T_BI_ITEM INSERT
				}else if(P_ITEM_NO instanceof ArrayList){
					ArrayList<String> itemNoList = (ArrayList<String>) P_ITEM_NO;
					ArrayList<String> itemNmList = (ArrayList<String>) P_ITEM_NM;
					ArrayList<String> stndNmList = (ArrayList<String>) P_STND_NM;
					ArrayList<String> itemUnnmList = (ArrayList<String>) P_ITEM_UNNM;
					ArrayList<String> itemQtyList = (ArrayList<String>) P_ITEM_QTY;
					ArrayList<String> rqstUnitList = (ArrayList<String>) P_RQST_UNIT;
					ArrayList<String> schUnitList = (ArrayList<String>) P_SCH_UNIT;
					ArrayList<String> pcrqList = (ArrayList<String>) P_PCRQ_NO;
					ArrayList<String> pcrqItemSn = (ArrayList<String>) P_PCRQ_ITEM_SN;
					for(int i =0; i < itemNoList.size(); i++){
						parameterMap.put("P_ITEM_NO", itemNoList.get(i));
						parameterMap.put("P_ITEM_NM", itemNmList.get(i));
						parameterMap.put("P_STND_NM", stndNmList.get(i));
						parameterMap.put("P_ITEM_UNNM", itemUnnmList.get(i));
						parameterMap.put("P_ITEM_QTY", itemQtyList.get(i));
						parameterMap.put("P_RQST_UNIT", rqstUnitList.get(i));
						parameterMap.put("P_SCH_UNIT", schUnitList.get(i));
						parameterMap.put("P_PCRQ_NO", pcrqList.get(i));
						parameterMap.put("P_PCRQ_ITEM_SN", pcrqItemSn.get(i));
						
						iproEbidPlanDao.insertIfBidPrdlstRegist(parameterMap);   // T_BI_ITEM INSERT
					}
				}else if(P_ITEM_NO instanceof String[]){
					String[] itemNoList = (String[]) P_ITEM_NO;
					String[] itemNmList = (String[]) P_ITEM_NM;
					String[] stndNmList = (String[]) P_STND_NM;
					String[] itemUnnmList = (String[]) P_ITEM_UNNM;
					String[] itemQtyList = (String[]) P_ITEM_QTY;
					String[] rqstUnitList = (String[]) P_RQST_UNIT;
					String[] schUnitList = (String[]) P_SCH_UNIT;
					String[] pcrqList = (String[]) P_PCRQ_NO;
					String[] pcrqItemSn = (String[]) P_PCRQ_ITEM_SN;
					for(int i =0; i < itemNoList.length; i++){
						parameterMap.put("P_ITEM_NO", itemNoList[i]);
						parameterMap.put("P_ITEM_NM", itemNmList[i]);
						parameterMap.put("P_STND_NM", stndNmList[i]);
						parameterMap.put("P_ITEM_UNNM", itemUnnmList[i]);
						parameterMap.put("P_ITEM_QTY", itemQtyList[i]);
						parameterMap.put("P_RQST_UNIT", rqstUnitList[i]);
						parameterMap.put("P_SCH_UNIT", schUnitList[i]);
						parameterMap.put("P_PCRQ_NO", pcrqList[i]);
						parameterMap.put("P_PCRQ_ITEM_SN", pcrqItemSn[i]);
						
						iproEbidPlanDao.insertIfBidPrdlstRegist(parameterMap);   // T_BI_ITEM INSERT
					}
				}
			}
		}	
								
		if(!"".equals(parameterMap.getString("P_BID_RNG_CNTN"))){
			parameterMap.put("P_RPLY_CNTN",parameterMap.getString("P_BID_RNG_CNTN"));
			iproEbidPblancDao.updateBidNotiUpdt(parameterMap);
		}
	
		//재공고 시 파일 복사
		if((parameterMap.get("P_BF_ANNC_NO") != null && !"".equals(parameterMap.get("P_BF_ANNC_NO"))) || (parameterMap.get("P_ANNC_NGR_OLD") != null && !"".equals(parameterMap.get("P_ANNC_NGR_OLD")))){
			parameterMap.put("P_BID_FSCD","DI01");
			iproEbidPblancDao.insertBiFileRegist(parameterMap);
		}
		
		// 입찰진행이력 등록 (입찰계획 - 작성완료)
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);   // T_BI_PROG_HIST INSERT
		
		if("Y".equals(parameterMap.getString("P_BID_REG_YN"))){
			iproEbidPlanDao.updateIfBidRegYn(parameterMap);   // T_IF_CTRQ_MST UPDATE
		}
		
		FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);	//입찰기본
		
		//알림톡 메시지 발송
		try {
			if(biMst.get("BID_MTCD").equals("EST") && biMst.get("CONT_MTCD").equals("10005")) {
				// 카카오 알림톡 : 견적요청(13593)
				parameterMap.put("P_MSG_SN", "13593");
				FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
				
				parameterMap.put("P_VEND_REG_NO", "");
				List<FwkDataEntity> vendList = iproEbidPblancDao.selectSelectVendUsrInfo(parameterMap);
				for (FwkDataEntity vendInfo : vendList) {
					vendInfo.put("P_CHRGR_NM", vendInfo.get("CHRGR_NM"));
					vendInfo.put("P_CHRGR_TEL_NO", vendInfo.get("CHRGR_TEL_NO"));
					vendInfo.put("P_VEND_NM", vendInfo.get("VEND_NM"));
					vendInfo.put("P_ANNC_NO", vendInfo.get("ANNC_NO"));
					vendInfo.put("P_BID_NM", vendInfo.get("BID_NM"));
					vendInfo.put("P_BIDC_SBMT_STDT_STR", vendInfo.get("BIDC_SBMT_STDT_STR_3"));						
					vendInfo.put("P_BIDC_SBMT_ENDT_STR", vendInfo.get("BIDC_SBMT_ENDT_STR_3"));
					parameterMap.put("P_MSG_ENTITY", vendInfo);
					parameterMap.put("P_MSG_CNTN", msgEntity.get("MSG_CNTN"));
					parameterMap.put("P_RECEIVE_MOBILE_NO", vendInfo.get("CP_NO"));
					parameterMap.put("P_TEMPLATE_CODE", "13593");
					
					//알림톡 DB 입력
					commItfcService.insertTsmsMsgInfo(parameterMap);				
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//DEXT5 업로드 용 리턴값 회신
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ANNC_NO", parameterMap.get("P_ANNC_NO"));
		map.put("ANNC_NGR", parameterMap.get("P_ANNC_NGR"));
		map.put("ROUND_NO", parameterMap.get("P_ROUND_NO"));
		trans.put("rtnAnncInfo", map);
		
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
	public FwkTransferObject bidPlanUpdt(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		// Format 변경
		//계약기간시작
		if(!"".equals(parameterMap.getString("P_CONT_STDE")) ){
			parameterMap.put("P_CONT_STDE", FwkFormatUtil.formatDate(parameterMap.get("P_CONT_STDE").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		//계약기간종료
		if(!"".equals(parameterMap.getString("P_CONT_ENDE"))){
			parameterMap.put("P_CONT_ENDE", FwkFormatUtil.formatDate(parameterMap.get("P_CONT_ENDE").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		//현장설명회일시
		if(!"".equals(parameterMap.getString("P_SITE_BRFS_DT")) && !"".equals(parameterMap.getString("P_SITE_BRFS_DT_HH")) && !"".equals(parameterMap.getString("P_SITE_BRFS_DT_MM"))){
			parameterMap.put("P_SITE_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_SITE_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_SITE_BRFS_DT_HH")+parameterMap.get("P_SITE_BRFS_DT_MM")+"00"); 
		}
		//입찰설명회일시
		if(!"".equals(parameterMap.getString("P_BRFS_DT")) && !"".equals(parameterMap.getString("P_BRFS_DT_HH")) && !"".equals(parameterMap.getString("P_BRFS_DT_MM"))){
			parameterMap.put("P_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BRFS_DT_HH")+parameterMap.get("P_BRFS_DT_MM")+"00"); 
		}
		
		//입찰설명회 N 이면 입찰설명회참석필수여부 값 지우기
		if("N".equals(parameterMap.getString("P_BID_BRFS_YN"))){
			parameterMap.put("P_BID_BRFS_ATND_YN","");
		}
		//계약구분 공사 아니면 현장설명회 N
		if(!"2".equals(parameterMap.getString("P_CONT_SECD"))){
			parameterMap.put("P_SITE_BRFS_YN","");
		}
		//계약방법 수의계약시 수의계약여부
		if("10003".equals(parameterMap.getString("CONT_MTCD")) || "10006".equals(parameterMap.getString("CONT_MTCD"))){
			parameterMap.put("P_PRVT_CONT_YN","Y");
		}else{
			parameterMap.put("P_PRVT_CONT_YN","N");
		}
		//비예가 기초금액을 추정금액으로
//		if("180002".equals(parameterMap.get("P_ESTPC_SECD"))){
//			parameterMap.put("P_BASE_AMT", parameterMap.get("P_ESTT_AMT"));
//		}
		parameterMap.put("P_PRCDC_SBMT_YN", "Y");	//가격서 제출 여부 YN 고정
		
		if(parameterMap.get("P_EST") != null && !"".equals(parameterMap.get("P_EST"))){
			// 입찰진행상태코드 (공고중)
			parameterMap.put("P_BID_PSCD", "PF20");
		}else{
			// 입찰진행상태코드 (공고전)
			parameterMap.put("P_BID_PSCD", "PF02");
		}
		
		
		iproEbidPlanDao.updateBidPlanUpdt(parameterMap);   // T_BI_MST UPDATE
		
		// 입찰 일정 등록
		//참가마감일시
		if(!"".equals(parameterMap.getString("P_PRTC_APLY_ENDT")) && !"".equals(parameterMap.getString("P_PRTC_APLY_ENDT_HH")) && !"".equals(parameterMap.getString("P_PRTC_APLY_ENDT_MM"))){
			parameterMap.put("P_PRTC_APLY_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRTC_APLY_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRTC_APLY_ENDT_HH")+parameterMap.get("P_PRTC_APLY_ENDT_MM")+"00"); 
		}
		//입찰공고일시
		if(!"".equals(parameterMap.getString("P_ANNC_DT")) && !"".equals(parameterMap.getString("P_ANNC_DT_HH")) && !"".equals(parameterMap.getString("P_ANNC_DT_MM"))){
			parameterMap.put("P_ANNC_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_ANNC_DT_HH")+parameterMap.get("P_ANNC_DT_MM")+"00"); 
		}
		//입찰서제출기간시작
		if(!"".equals(parameterMap.getString("P_BIDC_SBMT_STDT")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_STDT_HH")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_STDT_MM"))){
			parameterMap.put("P_BIDC_SBMT_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BIDC_SBMT_STDT_HH")+parameterMap.get("P_BIDC_SBMT_STDT_MM")+"00"); 
		}
		//입찰서제출기간종료
		if(!"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT_HH")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT_MM"))){
			parameterMap.put("P_BIDC_SBMT_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BIDC_SBMT_ENDT_HH")+parameterMap.get("P_BIDC_SBMT_ENDT_MM")+"00"); 
		}
		//제안서제출기간시작
		if(!"".equals(parameterMap.getString("P_PRPDC_SBMT_STDT")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_STDT_HH")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_STDT_MM"))){
			parameterMap.put("P_PRPDC_SBMT_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRPDC_SBMT_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRPDC_SBMT_STDT_HH")+parameterMap.get("P_PRPDC_SBMT_STDT_MM")+"00"); 
		}
		//제안서제출기간종료
		if(!"".equals(parameterMap.getString("P_PRPDC_SBMT_ENDT")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_ENDT_HH")) && !"".equals(parameterMap.getString("P_PRPDC_SBMT_ENDT_MM"))){
			parameterMap.put("P_PRPDC_SBMT_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRPDC_SBMT_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRPDC_SBMT_ENDT_HH")+parameterMap.get("P_PRPDC_SBMT_ENDT_MM")+"00"); 
		}
		//개찰일시
		if(!"".equals(parameterMap.getString("P_OPNG_DT")) && !"".equals(parameterMap.getString("P_OPNG_DT_HH")) && !"".equals(parameterMap.getString("P_OPNG_DT_MM"))){
			parameterMap.put("P_OPNG_DT", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_OPNG_DT_HH")+parameterMap.get("P_OPNG_DT_MM")+"00"); 
		}
		iproEbidPlanDao.updateBidSchdulUpdt(parameterMap);   // T_BI_ROUND UPDATE
		
		//복수예가 삭제
		iproEbidPlanDao.deleteCompnoPrdprc(parameterMap);   // T_BI_PLRL_ESTC DELETE
		// 예가 삭제
		//iproEbidPlanDao.deletePrdprc(parameterMap);   // T_BI_ESTC_MST DELETE
		if(!"".equals(parameterMap.getString("P_BASE_AMT"))){
			// 예가등록
			parameterMap.put("P_BASE_ESTPC_AMT", parameterMap.get("P_BASE_AMT"));
		}
		String P_FILE_GRP_NO = parameterMap.getString("P_FILE_GRP_NO");
		parameterMap.put("P_FILE_GRP_NO", "");			
		//iproEbidPrdprcManageDao.insertPrdprcRegist(parameterMap);   // T_BI_ESTC_MST INSERT
		iproEbidPrdprcManageDao.updatePrdprcInfoUpdt(parameterMap);
		parameterMap.put("P_FILE_GRP_NO", P_FILE_GRP_NO);
		
		
		//입찰 지명업체 삭제
		iproEbidPlanDao.deleteBidNmfpcEntrps(parameterMap);   // T_BI_VEND_MST DELETE
		if("10001".equals(parameterMap.get("P_CONT_MTCD")) || "10005".equals(parameterMap.get("P_CONT_MTCD")) || "10003".equals(parameterMap.get("P_CONT_MTCD")) || "10006".equals(parameterMap.get("P_CONT_MTCD"))){
			//입찰 지명업체 등록
			Object entrpsRegistNo = parameterMap.get("P_VEND_REG_NO");
			if(entrpsRegistNo != null){
				if(entrpsRegistNo instanceof String){
					parameterMap.put("P_VEND_REG_NO", entrpsRegistNo);
					iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);   // T_BI_VEND_MST INSERT
				}else if(entrpsRegistNo instanceof ArrayList){
					ArrayList<String> entrpsRegistNoList = (ArrayList<String>) entrpsRegistNo;
					for(int i =0; i < entrpsRegistNoList.size(); i++){
						parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList.get(i));
						iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);   // T_BI_VEND_MST INSERT
					}
				}else if(entrpsRegistNo instanceof String[]){
					String[] entrpsRegistNoList = (String[]) entrpsRegistNo;
					for(int i =0; i < entrpsRegistNoList.length; i++){
						parameterMap.put("P_VEND_REG_NO", entrpsRegistNoList[i]);
						iproEbidPlanDao.insertBidNmfpcEntrpsRegist(parameterMap);   // T_BI_VEND_MST INSERT
						
					}
				}
			}
		}
		
		//입찰 품목 삭제
		iproEbidPlanDao.deleteBidPrdlst(parameterMap);   // T_BI_ITEM DELETE
		if(parameterMap.get("P_ITEM_NO") != null) {
			//입찰 품목 등록
			Object P_ITEM_NO = parameterMap.get("P_ITEM_NO");
			Object P_ITEM_NM = parameterMap.get("P_ITEM_NM");
			Object P_STND_NM = parameterMap.get("P_STND_NM");
			Object P_ITEM_UNNM = parameterMap.get("P_ITEM_UNNM");
			Object P_ITEM_QTY = parameterMap.get("P_ITEM_QTY");
			Object P_RQST_UNIT = parameterMap.get("P_RQST_UNIT");
			Object P_SCH_UNIT = parameterMap.get("P_SCH_UNIT");
			Object P_PCRQ_NO = parameterMap.get("P_PCRQ_NO");
			Object P_PCRQ_ITEM_SN = parameterMap.get("P_PCRQ_ITEM_SN");
			
			if(P_ITEM_NO != null){
				if(P_ITEM_NO instanceof String){
					iproEbidPlanDao.insertIfBidPrdlstRegist(parameterMap);   // T_BI_ITEM INSERT
				}else if(P_ITEM_NO instanceof ArrayList){
					ArrayList<String> itemNoList = (ArrayList<String>) P_ITEM_NO;
					ArrayList<String> itemNmList = (ArrayList<String>) P_ITEM_NM;
					ArrayList<String> stndNmList = (ArrayList<String>) P_STND_NM;
					ArrayList<String> itemUnnmList = (ArrayList<String>) P_ITEM_UNNM;
					ArrayList<String> itemQtyList = (ArrayList<String>) P_ITEM_QTY;
					ArrayList<String> rqstUnitList = (ArrayList<String>) P_RQST_UNIT;
					ArrayList<String> schUnitList = (ArrayList<String>) P_SCH_UNIT;
					ArrayList<String> pcrqList = (ArrayList<String>) P_PCRQ_NO;
					ArrayList<String> pcrqItemSn = (ArrayList<String>) P_PCRQ_ITEM_SN;
					for(int i =0; i < itemNoList.size(); i++){
						parameterMap.put("P_ITEM_NO", itemNoList.get(i));
						parameterMap.put("P_ITEM_NM", itemNmList.get(i));
						parameterMap.put("P_STND_NM", stndNmList.get(i));
						parameterMap.put("P_ITEM_UNNM", itemUnnmList.get(i));
						parameterMap.put("P_ITEM_QTY", itemQtyList.get(i));
						parameterMap.put("P_RQST_UNIT", rqstUnitList.get(i));
						parameterMap.put("P_SCH_UNIT", schUnitList.get(i));
						parameterMap.put("P_PCRQ_NO", pcrqList.get(i));
						parameterMap.put("P_PCRQ_ITEM_SN", pcrqItemSn.get(i));
						
						iproEbidPlanDao.insertIfBidPrdlstRegist(parameterMap);   // T_BI_ITEM INSERT
					}
				}else if(P_ITEM_NO instanceof String[]){
					String[] itemNoList = (String[]) P_ITEM_NO;
					String[] itemNmList = (String[]) P_ITEM_NM;
					String[] stndNmList = (String[]) P_STND_NM;
					String[] itemUnnmList = (String[]) P_ITEM_UNNM;
					String[] itemQtyList = (String[]) P_ITEM_QTY;
					String[] rqstUnitList = (String[]) P_RQST_UNIT;
					String[] schUnitList = (String[]) P_SCH_UNIT;
					String[] pcrqList = (String[]) P_PCRQ_NO;
					String[] pcrqItemSn = (String[]) P_PCRQ_ITEM_SN;
					for(int i =0; i < itemNoList.length; i++){
						parameterMap.put("P_ITEM_NO", itemNoList[i]);
						parameterMap.put("P_ITEM_NM", itemNmList[i]);
						parameterMap.put("P_STND_NM", stndNmList[i]);
						parameterMap.put("P_ITEM_UNNM", itemUnnmList[i]);
						parameterMap.put("P_ITEM_QTY", itemQtyList[i]);
						parameterMap.put("P_RQST_UNIT", rqstUnitList[i]);
						parameterMap.put("P_SCH_UNIT", schUnitList[i]);
						parameterMap.put("P_PCRQ_NO", pcrqList[i]);
						parameterMap.put("P_PCRQ_ITEM_SN", pcrqItemSn[i]);
						
						iproEbidPlanDao.insertIfBidPrdlstRegist(parameterMap);   // T_BI_ITEM INSERT
					}
				}
			}
		}
		
		FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);	//입찰기본
		
		//알림톡 메시지 발송
		try {
			if(biMst.get("BID_MTCD").equals("EST") && biMst.get("CONT_MTCD").equals("10005")) {
				// 카카오 알림톡 : 견적요청(13593)
				parameterMap.put("P_MSG_SN", "13593");
				FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
				List<FwkDataEntity> vendList = iproEbidPblancDao.selectSelectVendUsrInfo(parameterMap);
				for (FwkDataEntity vendInfo : vendList) {
					vendInfo.put("P_CHRGR_NM", vendInfo.get("CHRGR_NM"));
					vendInfo.put("P_CHRGR_TEL_NO", vendInfo.get("CHRGR_TEL_NO"));
					vendInfo.put("P_VEND_NM", vendInfo.get("VEND_NM"));
					vendInfo.put("P_ANNC_NO", vendInfo.get("ANNC_NO"));
					vendInfo.put("P_BID_NM", vendInfo.get("BID_NM"));
					vendInfo.put("P_BIDC_SBMT_ENDT_STR", vendInfo.get("BIDC_SBMT_ENDT_STR"));
					vendInfo.put("P_BIDC_SBMT_STDT_STR", vendInfo.get("BIDC_SBMT_STDT_STR"));						
					parameterMap.put("P_MSG_ENTITY", vendInfo);
					parameterMap.put("P_MSG_CNTN", msgEntity.get("MSG_CNTN"));
					parameterMap.put("P_RECEIVE_MOBILE_NO", vendInfo.get("CP_NO"));
					parameterMap.put("P_TEMPLATE_CODE", "13593");
					//알림톡 DB 입력
					commItfcService.insertTsmsMsgInfo(parameterMap);				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(!"".equals(parameterMap.getString("P_BID_RNG_CNTN"))){
			parameterMap.put("P_OPNN_SN", 1);
			parameterMap.put("P_RPLY_CNTN",parameterMap.getString("P_BID_RNG_CNTN"));
			iproEbidPblancDao.updateBidNotiUpdt(parameterMap);
		}
		
		// 입찰진행이력 등록 (입찰계획 - 작성완료)
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);   // T_BI_PROG_HIST INSERT
		
		//DEXT5 업로드 용 리턴값 회신
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ANNC_NO", parameterMap.get("P_ANNC_NO"));
		map.put("ANNC_NGR", parameterMap.get("P_ANNC_NGR"));
		map.put("ROUND_NO", parameterMap.get("P_ROUND_NO"));
		trans.put("rtnAnncInfo", map);
		
		return trans;
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPlanDelete
	 * @date : 2015. 02. 03.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 03.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public void bidPlanDelete(final FwkParameterMap parameterMap) {
//		iproEbidPlanDao.updateBidPlanDeleteAtUpdt(parameterMap);
//		
//		// 입찰진행이력 등록 (입찰계획 - 계획삭제)
//		parameterMap.put("P_BID_PSCD", "");
//		parameterMap.put("P_PROC_RSN", "삭제");
//		
//		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);   // T_BI_PROG_HIST INSERT
//		parameterMap.put("P_BID_REG_YN", "N");
//		//iproEbidPlanDao.updateIfBidRegYn(parameterMap);
		
		// 2019-08-12 은잔디 수정 : 입찰공고 삭제 DELETE
		
		// 입찰결재연계 삭제
		iproEbidPlanDao.deleteBidApprCntc(parameterMap);// T_BI_APPR_CNTC DELETE

		// 입찰설명회업체 삭제
		iproEbidPlanDao.deleteBidPartcptEntrps(parameterMap);   // T_BI_BRFS_VEND DELETE
		
		// 예가마스터 삭제
		iproEbidPlanDao.deletePrdprc(parameterMap);   // T_BI_ESTC_MST DELETE
		
		// 입찰첨부파일 삭제
		iproEbidPlanDao.deleteBidFile(parameterMap);   // T_BI_FILE DELETE
		
		// 입찰품목 삭제
		iproEbidPlanDao.deleteBidPrdlst(parameterMap);   // T_BI_ITEM DELETE
		
		// 복수예가 삭제
		iproEbidPlanDao.deleteCompnoPrdprc(parameterMap);   // T_BI_PLRL_ESTC DELETE
		
		// 입찰진행이력 삭제
		iproEbidPlanDao.deleteBidProgHist(parameterMap);   // T_BI_PROG_HIST
		
		// 입찰라운드 삭제
		iproEbidPlanDao.deleteBidRound(parameterMap);   // T_BI_ROUND DELETE
		
		// 투찰품목 삭제
		iproEbidPlanDao.deleteBidTndrItem(parameterMap);   // T_BI_TNDR_ITEM DELETE
		
		// 업체공동 삭제
		iproEbidPlanDao.deleteBidVendAsso(parameterMap);   // T_BI_VEND_ASSO DELETE
		
		// 업체개찰 삭제
		iproEbidPlanDao.deleteBidVendBiop(parameterMap);   // T_BI_VEND_BIOP DELETE
		
		// 입찰업체상세 삭제
		iproEbidPlanDao.deleteEntrpsPartcptnDetail(parameterMap);   // T_BI_VEND_DTL DELETE

		// 업체예가추첨 삭제
		iproEbidPlanDao.deleteBidVendEsse(parameterMap);   // T_BI_VEND_ESSE DELETE
		
		// 입찰업체파일 삭제
		iproEbidPlanDao.deleteVendFileDel(parameterMap);   // T_BI_VEND_FILE DELETE
		
		// 입찰업체보증 삭제
		iproEbidPlanDao.deleteVendGrntDel(parameterMap);   // T_BI_VEND_GRNT DELETE
		
		// 업체진행이력 삭제
		iproEbidPlanDao.deleteEntrpsProgrsHist(parameterMap);   // T_BI_VEND_HIST DELETE

		// 입찰업체마스터 삭제
		iproEbidPlanDao.deleteBidVendMst(parameterMap);   // T_BI_VEND_MST DELETE
		
		// 입찰마스터 삭제
		iproEbidPlanDao.deleteBidMst(parameterMap);   // T_BI_MST DELETE
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 상태 변경
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidSttusChange
	 * @date : 2015. 04. 07.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 07.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public void bidSttusChange(final FwkParameterMap parameterMap) {
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);   // T_BI_MST UPDATE
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);   // T_BI_ROUND UPDATE
		
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);   // T_BI_PROG_HIST INSERT
		
//		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
//		if(!"".equals(biMst.getString("BASE_AMT")) && "180000".equals(biMst.getString("ESTPC_SECD"))){
//			parameterMap.put("P_BASE_ESTPC_AMT", biMst.getString("BASE_AMT"));
//			parameterMap.put("P_ESTPC_DCSN_SECD", biMst.getString("PLR_ESTPC_RNG_CD"));
//			iproEbidPrdprcManageService.prdprcCreatRegist(parameterMap);
//		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 유찰 공고 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fibListInqireWithPgng
	 * @date : 2015. 07. 07.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 07.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject fibListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidPlanService.FIB_LIST, iproEbidPlanDao.selectFibListWithPgng(parameterMap));
		trans.put(IproEbidPlanService.FIB_LIST_TOTCNT, iproEbidPlanDao.selectFibListTotcnt(parameterMap));
		
		return trans;
	}

	@Override
	public FwkTransferObject ebidApprSendPlan(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap htmlMap = new FwkParameterMap();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		// 계약수정
		parameterMap.put("P_BID_PSCD", "PF02");			//진행상태코드
		parameterMap.put("P_APPR_STCD", "NEW");	//결재진행상태코드	
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);   // T_BI_MST UPDATE
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);   // T_BI_ROUND UPDATE
		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		parameterMap.put(IproEbidPlanService.BID_PLAN_DETAIL, biMst);
		parameterMap.put("P_IFC_ID", biMst.getString("IFC_ID"));
		parameterMap.put("BFAN_MST", iproEbidPlanDao.selectBfanMst(parameterMap));   // T_BI_BFAN_MST SELECT
		
		parameterMap.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPlanDao.selectBidPrdlstList(parameterMap));   // T_BI_ITEM SELECT
		
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);   // T_BI_FILE SELECT 
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			List<FwkDataEntity>	fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
			parameterMap.put(IproEbidPlanService.BID_ATCH_DOC_LIST, fileList);
		}
		
		parameterMap.put("P_BID_FSCD", "DI00");
		FwkDataEntity redFileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);   // T_BI_FILE SELECT 
		if(redFileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", redFileDataEntity.getString("FILE_GRP_NO"));
			List<FwkDataEntity>	fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
			parameterMap.put(IproEbidPlanService.BID_ATCH_DOC_LIST+"Red", fileList);
		}
		
		
		/*
		 *	전자결재 진행 
		 */
		//Form Html 양식 불러오기
		parameterMap.put("P_FRM_ID", "ERP_BID_PLAN_EDIT");
		FwkDataEntity applHtmlMap = iproCommDefaultDao.selectApplFormHtml(parameterMap);   // T_MM_APPL_FORM SELECT
		parameterMap.put("P_APPL_HTML", applHtmlMap.get("HTML_CNTN"));
		
		//결재문서 매핑
		//htmlMap= CmmnUtil.ebidApprovalHtmlCreate(parameterMap);
		
		//LEGACY 테이블 저장
		parameterMap.put("P_PREFIX_STR", "EBID_ANNC_PLAN_");
		parameterMap.put("P_HTML_DATA1", htmlMap.get("APPL_HTML_END"));
		parameterMap.put("P_DRAFT_SABUN", user.getString("SABUN"));					//사번 : V_SSO_USER 
		parameterMap.put("P_DRAFT_DEPT", user.getString("DEPT_NO"));					//부서번호	: V_SSO_USER LEGACY_CODE
		parameterMap.put("P_TITLE", biMst.getString("BID_NM")+ " 입찰계획");			//NULL 안됨
		parameterMap.put("P_MAIN_TITLE", biMst.getString("BID_NM")+ " 입찰계획");	//NULL 안됨
		iproCommDefaultDao.insertIfLegacyMst(parameterMap);
		
		parameterMap.put("P_DOC_NO", biMst.getString("DOC_NO"));							// 문서번호
		FwkDataEntity ifLegacyMst = iproCommDefaultDao.selectIfLegacyMst(parameterMap);
		if(ifLegacyMst != null){
			parameterMap.put("P_APP_ID", ifLegacyMst.getString("APP_ID"));				// APP_ID
			iproCommDefaultDao.insertIfLegacyDoc(parameterMap);
		}
		
//		parameterMap.put("P_BID_FSCD", "DI01");
//		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap); 
//		if(fileDataEntity != null){
//			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
//			List<FwkDataEntity>	fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
//			for (int i = 0; i < fileList.size(); i++) {
//				FwkDataEntity file = fileList.get(i);
//				parameterMap.put("P_APP_FILEPATH", file.getString("FILE_LCTN")+File.separator+file.getString("SV_FILE_NM"));
//				parameterMap.put("P_APP_FILENAME", file.getString("SYS_FILE_NM"));
////				iproCommDefaultDao.insertIfLegacyFile(parameterMap);
//			}
//		}
//		
//		parameterMap.put("P_BID_FSCD", "DI00");
//		FwkDataEntity redFileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap); 
//		if(redFileDataEntity != null){
//			parameterMap.put("P_FILE_GRP_NO", redFileDataEntity.getString("FILE_GRP_NO"));
//			List<FwkDataEntity>	fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
//			for (int i = 0; i < fileList.size(); i++) {
//				FwkDataEntity file = fileList.get(i);
//				parameterMap.put("P_APP_FILEPATH", file.getString("FILE_LCTN")+File.separator+file.getString("SV_FILE_NM"));
//				parameterMap.put("P_APP_FILENAME", file.getString("SYS_FILE_NM"));
////				iproCommDefaultDao.insertIfLegacyFile(parameterMap);
//			}
//		}
		
//		parameterMap.put("P_APP_FILEPATH", "/EBID/WebRoot/data/upload/2018/10/19/bid/");
//		parameterMap.put("P_APP_FILENAME", "eecb511991104c4ba6a53eb2a0518b8f.pdf");
//		iproCommDefaultDao.insertIfLegacyFile(parameterMap);
		
		//연동정보 테이블 저장
		parameterMap.put("P_BID_APPR_TSCD", "A");
		iproEbidPlanDao.insertBiApprCntc(parameterMap);
		
		trans.put("P_INTERFACE_ID", parameterMap.get("P_INTERFACE_ID"));
		trans.put("P_STATUS", "NEW");
		trans.put("P_DRAFT_SABUN", user.getString("SABUN"));
		
		return trans;
	}

	@Override
	public FwkTransferObject elgbEstmKd(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproEbidPlanService.ELGB_ESTM_KD, iproEbidPlanDao.selectElgbEstmKd(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰대기목록 아이템 리스트 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPaItemList
	 * @date : 2019. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 08.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject selectPaItemList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproEbidPlanService.PA_ITEM_LIST, iproEbidPlanDao.selectPaItemInfo(parameterMap));
		return trans;
	}	
}