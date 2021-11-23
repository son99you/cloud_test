package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.itfc.dao.CommItfcDao;
import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidOpengManageDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPartcptSttusDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrdprcManageDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPartcptSttusService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.g2b.dao.IproG2bDao;

/**
 * 입찰공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidPblancService")
public class IproEbidPblancServiceImpl extends AbstractFwkService implements IproEbidPblancService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidPblancServiceImpl.class);
	
	@Resource(name="iproEbidPblancDao")
	private IproEbidPblancDao iproEbidPblancDao;
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;

	@Resource(name="iproEbidPartcptSttusDao")
	private IproEbidPartcptSttusDao iproEbidPartcptSttusDao; 
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="iproEbidPrdprcManageDao")
	private IproEbidPrdprcManageDao iproEbidPrdprcManageDao;
	
	@Resource(name="iproEbidOpengManageDao")
	private IproEbidOpengManageDao iproEbidOpengManageDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao;
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;
	
	@Resource(name="commItfcDao")
	private CommItfcDao commItfcDao; 
	
	@Resource(name="iproG2bDao")
	private IproG2bDao iproG2bDao; 
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancListInqireWithPgng
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidPblancListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(IproEbidPblancService.BID_PBLANC_LIST, iproEbidPblancDao.selectBidPblancListWithPgng(parameterMap));
		trans.put(IproEbidPblancService.BID_PBLANC_LIST_TOTCNT, iproEbidPblancDao.selectBidPblancListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 입찰상세 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancDetailInqire
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidPblancDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
		
		trans.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPlanDao.selectBidPrdlstList(parameterMap));
		trans.put(IproEbidPlanService.BID_NMFPC_ENTRPS_LIST, iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap);
		trans.put(IproEbidPlanService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
			/*
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			*/
		}

		//정정공고사유 목록
		parameterMap.put("P_BID_PSCD", "PF30");
		trans.put(IproEbidPblancService.PBLANC_PF30_HIST_LIST, iproEbidPblancDao.selectBiProgHistList(parameterMap));
		
		// 업체 참가신청 여부
//		int entrpsBddprAt = iproEbidPlanDao.selectEntrpsBddprAt(parameterMap);
		
		List<FwkDataEntity> vendMstHist = iproEbidPblancDao.selectVendMstHistList(parameterMap);
		
		if(vendMstHist != null && vendMstHist.size() > 0) {
			trans.put("VEND_TNDR_YN", "Y");
		}else{
			trans.put("VEND_TNDR_YN", "N");
		}
		
		trans.put(IproEbidPblancService.BID_NOTI_LIST, iproEbidPblancDao.selectBidNotiList(parameterMap));
		
		parameterMap.put("P_BID_APPR_TSCD", "B");
		trans.put("biApprCntcB", iproEbidPlanDao.selectBiApprCntc(parameterMap));
		parameterMap.put("P_BID_APPR_TSCD", "C");
		trans.put("biApprCntcC", iproEbidPlanDao.selectBiApprCntc(parameterMap));
		parameterMap.put("P_BID_APPR_TSCD", "D");
		trans.put("biApprCntcD", iproEbidPlanDao.selectBiApprCntc(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 정정공고 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updtPblancRegistForm
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject updtPblancRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰마스터 조회
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidPlanService.BID_PLAN_DETAIL, dataEntity);
		//입찰품목조회
		trans.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPlanDao.selectBidPrdlstList(parameterMap));
		//입찰지명업체조회
		trans.put(IproEbidPlanService.BID_NMFPC_ENTRPS_LIST, iproEbidPlanDao.selectBidNmfpcEntrpsList(parameterMap));
		
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap); 
		trans.put(IproEbidPlanService.BID_ATCH_DOC, fileDataEntity);
		
		if(fileDataEntity != null){
			dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_PLAN_DETAIL ,dataEntity);
		}	
		
		if(!"".equals(dataEntity.getString("IFC_ID"))){
			parameterMap.put("P_IFC_ID", dataEntity.getString("IFC_ID"));
			trans.put(IproEbidPlanService.BFAN_INFO, iproEbidPlanDao.selectBfanMst(parameterMap));
		}
		
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 정정공고 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updtPblancRegist
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	@Override
	public FwkTransferObject updtPblancRegist(final FwkParameterMap parameterMap) throws Exception {
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
		
		//기존입찰 비활성화
		parameterMap.put("P_BID_PSCD", "PF99");
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);

		//입찰마스터 저장
		parameterMap.put("P_BID_PSCD", "PF30");
		parameterMap.put("P_ANNC_NGR", parameterMap.getInt("P_ANNC_NGR")+1);
		parameterMap.put("P_ROUND_NO", 1);
		iproEbidPblancDao.insertBiMstRegist(parameterMap);
		
		// 입찰 일정 등록
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
		
		//입찰라운드 저장
		iproEbidPblancDao.insertBiRoundRegist(parameterMap);
		//iproEbidPlanDao.updateBidSchdulUpdt(parameterMap);
		
		if(!"".equals(parameterMap.getString("P_BASE_AMT"))){
			// 예가등록
			parameterMap.put("P_BASE_ESTPC_AMT", parameterMap.get("P_BASE_AMT"));
			String P_FILE_GRP_NO = parameterMap.getString("P_FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", "");//			baseFileGrpNo
			iproEbidPrdprcManageDao.insertPrdprcRegist(parameterMap);
			parameterMap.put("P_FILE_GRP_NO", P_FILE_GRP_NO);
		}
		
		
		//입찰 품목 등록
		iproEbidPlanDao.deleteBidPrdlst(parameterMap);
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

		// 입찰진행이력 등록 (입찰계획 - 작성완료)
		//parameterMap.put("P_PROC_RSN", parameterMap.get("P_RMK"));
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ANNC_NO", parameterMap.get("P_ANNC_NO"));
		map.put("ANNC_NGR", parameterMap.get("P_ANNC_NGR"));
		map.put("ROUND_NO", parameterMap.get("P_ROUND_NO"));
		trans.put("rtnAnncInfo", map);
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 취소공고 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : canclPblancRegistForm
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject canclPblancRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		// 공고 상세
		trans.put(IproEbidPblancService.BID_PBLANC_DETAIL, iproEbidPlanDao.selectBidInfoDetail(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 취소공고 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : canclPblancRegist
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public void canclPblancRegist(final FwkParameterMap parameterMap) {

		// 입찰 상태값 변경
		parameterMap.put("P_BID_PSCD", "PF40");	// 입찰진행상태코드 ( PF40 : 취소공고 )
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
		iproEbidPblancDao.updatebidPlanProgrsSttusUpdt(parameterMap);
		
		// 공고알림정보 등록
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
		parameterMap.put("P_NTCN_DE", FwkFormatUtil.formatDate(FwkDateUtil.getCurrentDateTimeAsString(), "yyyyMMddHHmmss", "yyyyMMdd")); 
		parameterMap.put("P_BID_CHANGE_NTCN_CD", "NOT02");  // NOT02 : 취소공고
		parameterMap.put("P_DEL_AT", "N");
//		iproEbidPblancDao.insertPblancNtcnInfoRegist(parameterMap);
		
		// 입찰진행이력 등록 (입찰공고 - 취소공고)
		parameterMap.put("P_BID_PSCD", "PF40");
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
	}

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견답변 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidNotiRegistForm
	 * @date : 2015. 02. 12.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 12.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidNotiRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPblancDao.selectBidNotiDetail(parameterMap);
		
		trans.put(IproEbidPblancService.BID_NOTI_DETAIL ,dataEntity);
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견답변 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancOpinionAnswerUpdt
	 * @date : 2015. 02. 12.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 12.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidNotiUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		iproEbidPblancDao.updateBidNotiUpdt(parameterMap);
		return trans;
	}
	
	@Override
	public void bidNotiDel(final FwkParameterMap parameterMap) {
		//FwkTransferObject trans = new FwkTransferObject();
		iproEbidPblancDao.deleteBidNotiDel(parameterMap);
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공동수급협정서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 6. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidPblancService.iep.elbi.service.IepElbiBidPblancService#copertnSpldmdTrtyOfeInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject copertnSpldmdTrtyOfeInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 업체 입찰 기본정보 조회
		trans.put(IproEbidPblancService.ENTRPS_BID_BASS_INFO_INQIRE,
				iproEbidPblancDao.selectEntrpsBidBassInfoInqire(parameterMap));
		
		// 공동수급업체 목록 조회
		trans.put(IproEbidPblancService.COPERTN_SDEN_LIST, 
				iproEbidPblancDao.selectCopertnSdenList(parameterMap));
		
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		parameterMap.put("P_BID_SBMT_FSCD", "DO07");
		FwkDataEntity dataEntity = iproEbidPblancDao.selectEntrpsAtchDoc(parameterMap);
		if(dataEntity != null ){
			parameterMap.put("P_FILE_GRP_NO", dataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPblancService.BID_ATCH_DOC_LIST,comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 관심입찰 등록한 업체 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidEntrpsList
	 * @date : 2016. 01. 05.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 01. 05.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidPblancService.iep.elbi.service.IepElbiBidPblancService#copertnSpldmdTrtyOfeInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject intrstBidEntrpsList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 관심입찰업체목록
		trans.put(IproEbidPblancService.INTRST_BID_ENTRPS_LIST, iproEbidPblancDao.selectIntrstBidEntrpsList(parameterMap));
		
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 재공고 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fibRegist
	 * @date : 2019. 02. 11.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 11.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject fibRegist(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 입찰진행이력 등록
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		// 입찰계획마스터 입찰진행상태 변경 'PF99' = 유찰
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		
		//if(iproEbidPlanDao.selectBidFibCnt(parameterMap) >= 2){ //의미없음 -> 재공고는 새 번호
		//}
		//iproEbidPlanDao.updateBidPvctCnvrAllN(parameterMap);
		//iproEbidPlanDao.updateBidPvctCnvrY(parameterMap);
		
		parameterMap.put("P_ANNC_NO_BEF", parameterMap.get("P_ANNC_NO"));
		parameterMap.put("P_ANNC_NGR_BEF", parameterMap.get("P_ANNC_NGR"));		
		parameterMap.put("P_ROUND_NO_BEF", parameterMap.get("P_ROUND_NO"));		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		//MIS 유찰 시 상태코드 전송 : 32(낙찰취소 동일)
		parameterMap.put("P_PCH_DMND_NO", biMst.get("PCAC_NO"));
		parameterMap.put("P_PRCRE_PRG_STE_CD", "32");
		parameterMap.put("P_REGR_ID", session.get("USR_ID"));
		//commItfcDao.sendPrcqStatToMIS(parameterMap);
		trans.put("P_PCRQ_NO", biMst.get("PCAC_NO"));
		
		//견적 혹은 입찰 시 2회유찰 시 수의전환 - SELECT TO INSERT
		if(biMst.get("BF_ANNC_NO") != null && !biMst.get("BF_ANNC_NO").equals("") && (biMst.get("BID_MTCD").equals("BID") || biMst.get("BID_MTCD").equals("PRVT"))) {
			// 공고번호 생성
			parameterMap.put("P_CHAR", "R");
			String pblancNo = iproEbidPlanDao.selectPblancNoCreat(parameterMap);
			parameterMap.put("P_ANNC_NO", pblancNo);
			parameterMap.put("P_ANNC_NGR", "1");
			parameterMap.put("P_ROUND_NO", "1");
			parameterMap.put("P_LT_NGR_YN", "Y");
			parameterMap.put("P_SITE_BRFS_YN", "N");
			parameterMap.put("P_DEL_AT", "N");
			parameterMap.put("P_BID_MTCD", "PRVT");
			// 낙찰자 선정방법 수의 고정
			parameterMap.put("P_SBID_MTCD", "55");
			// 계약방법 수의 고정
			parameterMap.put("P_CONT_MTCD", "10006");		//2회유찰 수의계약으로 변경
			//계약방법 수의계약시 수의계약여부
			parameterMap.put("P_PRVT_CONT_YN", "Y");
			//입찰 구분코드 작성
			parameterMap.put("P_PRCDC_SBMT_YN", "Y");
			parameterMap.put("P_BID_PSCD", "PF02");
			if("N".equals(biMst.getString("BID_BRFS_YN"))){
				parameterMap.put("P_BID_BRFS_ATND_YN","");
			}
			parameterMap.put("P_FBID_YN", "Y");
			
			//입찰마스터 복사
			iproEbidPblancDao.insertBiMstToPrvt(parameterMap);
			//입찰라운드 복사
			iproEbidPblancDao.insertBiRoundToPrvt(parameterMap);
			//입찰 예가 복사
			iproEbidPblancDao.insertBiEstcToPrvt(parameterMap);
			//입찰 아이템 복사
			iproEbidPblancDao.insertBiItemToPrvt(parameterMap);
			//입찰파일 복사
			iproEbidPblancDao.insertBiFileToPrvt(parameterMap);

		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 재입찰 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : reBid
	 * @date : 2019. 02. 11.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 11.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public void reBid(FwkParameterMap parameterMap) {
		// 낙찰취소
		if("PF63".equals(parameterMap.getString("P_BID_PSCD"))){
			if(!"70".equals(parameterMap.getString("P_SBID_MTCD"))){
				iproEbidPblancDao.updateVendMstSbidN(parameterMap);
				iproEbidPblancDao.updateBiopVendSbidN(parameterMap);
			}else{
				iproEbidPblancDao.updateDatpVendSbidN(parameterMap);
			}
		}
		
		//1. 이전공고 사유등록 & 상태 변경
		parameterMap.put("P_APPR_STCD", "");
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		parameterMap.put("P_BID_PSCD", "PF99");
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		parameterMap.put("P_BID_PSCD", "PF20");
		
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
		//2. 다음차수공고 생성 (마스터, 라운드(기간변경해서), 품목, 첨부파일, 예가마스터, 복수예가, 지명일경우 업체마스터(껍데기만) )
		
		parameterMap.put("P_OLD_ANNC_NGR", parameterMap.getString("P_ANNC_NGR"));
		parameterMap.put("P_ANNC_NGR", parameterMap.getInt("P_ANNC_NGR")+1);
		parameterMap.put("P_LT_NGR_YN","N");
		iproEbidPlanDao.updateLtNgr(parameterMap);
		parameterMap.put("P_LT_NGR_YN","Y");
		
		//참가마감일시
		if(!"".equals(parameterMap.getString("P_PRTC_APLY_ENDT")) && !"".equals(parameterMap.getString("P_PRTC_APLY_ENDT_HH")) && !"".equals(parameterMap.getString("P_PRTC_APLY_ENDT_MM"))){
			parameterMap.put("P_PRTC_APLY_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_PRTC_APLY_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_PRTC_APLY_ENDT_HH")+parameterMap.get("P_PRTC_APLY_ENDT_MM")+"00"); 
		}
		//입찰공고일시
		if(!"".equals(parameterMap.getString("P_ANNC_DT")) && !"".equals(parameterMap.getString("P_ANNC_DT_HH")) && !"".equals(parameterMap.getString("P_ANNC_DT_MM"))){
			parameterMap.put("P_ANNC_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_ANNC_DT_HH")+parameterMap.get("P_ANNC_DT_MM")+"00"); 
		}
		//입찰설명회일시
		if(!"".equals(parameterMap.getString("P_BRFS_DT")) && !"".equals(parameterMap.getString("P_BRFS_DT_HH")) && !"".equals(parameterMap.getString("P_BRFS_DT_MM"))){
			parameterMap.put("P_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BRFS_DT_HH")+parameterMap.get("P_BRFS_DT_MM")+"00"); 
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
		
		parameterMap.put("P_BID_PSCD", "PF20");
		parameterMap.put("P_APPR_STCD", "");
		
		iproEbidPblancDao.insertBiMstNextNgr(parameterMap);
		iproEbidPblancDao.insertBiRoundNextNgr(parameterMap);
		iproEbidPblancDao.insertBiItemNextNgr(parameterMap);
		iproEbidPblancDao.insertBiFileNextNgr(parameterMap);
		iproEbidPblancDao.insertBiEstcMstNextNgr(parameterMap);
		iproEbidPblancDao.insertBiPlrlEstcNextNgr(parameterMap);
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		if("10001".equals(biMst.getString("CONT_MTCD")) || "10005".equals(biMst.getString("CONT_MTCD"))){
			iproEbidPblancDao.insertBiVendMstNextNgr(parameterMap);
		}
		iproEbidPblancDao.insertBiBrfsVendNextNgr(parameterMap);
		
		
		parameterMap.put("P_PROC_RSN", "");
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
	}	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.impl.IproEbidPblancServiceImpl.java
	 * @Method : roundUpdateForm
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 5. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject roundUpdtForm(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproEbidPblancService.BID_PBLANC_DETAIL ,iproEbidPlanDao.selectBidInfoDetail(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.impl.IproEbidPblancServiceImpl.java
	 * @Method : roundUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 5. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject roundUpdt(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		// 입찰 일정 등록
		//공고일시
		if(!"".equals(parameterMap.getString("P_ANNC_DT")) && !"".equals(parameterMap.getString("P_ANNC_DT_HH")) && !"".equals(parameterMap.getString("P_ANNC_DT_MM"))){
			parameterMap.put("P_ANNC_DT", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_ANNC_DT_HH")+parameterMap.get("P_ANNC_DT_MM")+"00"); 
		}		
		//입찰참가자격제출일시
		if(!"".equals(parameterMap.getString("P_BRFS_DT")) && !"".equals(parameterMap.getString("P_BRFS_DT_HH")) && !"".equals(parameterMap.getString("P_BRFS_DT_MM"))){
			parameterMap.put("P_BRFS_DT", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BRFS_DT_HH")+parameterMap.get("P_BRFS_DT_MM")+"00"); 
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

		//투찰업체에 따른 재입찰 불가 처리
		// 2019-09-05 은잔디 수정 : 견적은 업체가 2개 미만 있어도 재입찰 가능하게 처리
		if(!"EST".equals(parameterMap.get("P_BID_GBN")) && !"PRVT".equals(parameterMap.get("P_BID_GBN"))){
			if(parameterMap.get("P_ROUND_RE_BID_YN") != null && parameterMap.get("P_ROUND_RE_BID_YN").equals("Y")) {
				ArrayList<String> bidVendPscdList = new ArrayList<String>();
				bidVendPscdList.add("OP04");
				bidVendPscdList.add("OP06");
				parameterMap.put("P_BID_VEND_PSCD_LIST", bidVendPscdList);
				List<FwkDataEntity> partcptnEntrpsInfoList = iproEbidOpengManageDao.selectPartcptnEntrpsInfoInqire(parameterMap);
				if(partcptnEntrpsInfoList == null || partcptnEntrpsInfoList.size() < 2) {
					trans.put("msg", "2개업체 미만 참가로 재입찰 할 수 없습니다.");
					trans.setResultCode("ERR");
					return trans;
				}
			}
		}

		//개찰여부 수정
		parameterMap.put("P_OPNG_YN", "");
		iproEbidPlanDao.updateBidPlanUpdt(parameterMap);
		//입찰 마스터 설명회 일시 업데이트
		iproEbidPlanDao.updateBiMstBrfsDtUpdt(parameterMap);
		//입찰 라운드 시간 업데이트
		//iproEbidPlanDao.updateBidSchdulUpdt(parameterMap);
		
		parameterMap.put("P_APPR_STCD", "");
		//입찰 마스터 상태 업데이트
		parameterMap.put("P_BID_PSCD", "PF20");
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		
		//입찰 라운드 상태 업데이트
		parameterMap.put("P_BID_PSCD", "PF99");
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		parameterMap.put("P_BID_PSCD", "PF20");
		
		if(parameterMap.get("P_ROUND_RE_BID_YN") != null && parameterMap.get("P_ROUND_RE_BID_YN").equals("Y")) {	//재입찰
			//입찰 라운드 입력
			parameterMap.put("P_ROUND_NO_BEF", parameterMap.getInt("P_ROUND_NO"));
			parameterMap.put("P_ROUND_NO", parameterMap.getInt("P_ROUND_NO")+1);
			iproEbidPlanDao.insertBidRndSelectRegist(parameterMap);
			
			//입찰 업체 마스터 입력 - 평가점수만 입력
			iproEbidPblancDao.insertBiVendMstSelectInsert(parameterMap);
			
			//입찰 파일 복사(라운드 변경)
			iproEbidPblancDao.insertBiFileNextNgr(parameterMap);
			
			//예가 마스터 입력
			iproEbidPblancDao.insertBiEstcMstSelectInsert(parameterMap);
			
			//복수예가 복사
			iproEbidPblancDao.insertBiPlrlEstcSelectInsert(parameterMap);
			
			//업체 제출 제안서 파일 복사(제안서는 재제출 미대상이므로)
			parameterMap.put("P_BID_TPI_SECD", "OP05");
			iproEbidPblancDao.insertBiVendFileSelectInsert(parameterMap);
			
			//입찰 히스토리 입력
			iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
			
			//알림톡 메시지 발송
			try {
				FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);	//입찰기본
				if(biMst.get("BID_MTCD").equals("EST")) {
					// 카카오 알림톡 : 재견적요청(13594)
					parameterMap.put("P_MSG_SN", "13594");
					FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
					
					parameterMap.put("P_BID_TPI_SECD", "OP03");   // 입찰참여구분코드 ( OP03 : 투찰 )
					List<FwkDataEntity> vendList = iproEbidPblancDao.selectVendDtlUsrInfo(parameterMap);
					for (FwkDataEntity vendInfo : vendList) {
						vendInfo.put("P_CHRGR_NM", vendInfo.get("CHRGR_NM"));
						vendInfo.put("P_CHRGR_TEL_NO", vendInfo.get("CHRGR_TEL_NO"));
						vendInfo.put("P_VEND_NM", vendInfo.get("VEND_NM"));
						vendInfo.put("P_ANNC_NO", vendInfo.get("ANNC_NO"));
						vendInfo.put("P_BID_NM", vendInfo.get("BID_NM"));
						vendInfo.put("P_BIDC_SBMT_STDT_STR", vendInfo.get("BIDC_SBMT_STDT_STR_2"));
						vendInfo.put("P_BIDC_SBMT_ENDT_STR", vendInfo.get("BIDC_SBMT_ENDT_STR_2"));
						parameterMap.put("P_MSG_ENTITY", vendInfo);
						parameterMap.put("P_MSG_CNTN", msgEntity.get("MSG_CNTN"));
						parameterMap.put("P_RECEIVE_MOBILE_NO", vendInfo.get("CP_NO"));
						parameterMap.put("P_TEMPLATE_CODE", "13594");
						//알림톡 DB 입력
						commItfcService.insertTsmsMsgInfo(parameterMap);				
					}	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else {	//공고 게시 등
			//입찰 라운드 업데이트
			iproEbidPlanDao.updateBidSchdulUpdt(parameterMap);
			
			//입찰 히스토리 입력
			iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);	
			
			
			FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);	//입찰기본
			
			if(biMst.get("BID_MTCD").equals("BID") &&  biMst.get("G2B_NTFY_YN").equals("Y") && !biMst.get("SBID_MTCD").equals("34")) {
				try {
					
					// 공고게시 G2B 연계
					FwkDataEntity fde = iproG2bDao.selectG2bCreateBid(parameterMap);
					
//					CreateXML.CreateBidXml(fde);
					
					LOG.debug("G2B Success ===================<");
					
				} catch (Exception e1) {
					LOG.debug("G2B Fail Msg Start===================<");
					e1.printStackTrace();
					LOG.debug(">>> 나라장터연계에 실패하였습니다");
					LOG.debug("G2B Fail Msg End===================>");
				}
			}
			
			//알림톡 메시지 발송
			try {
//				if(biMst.get("BID_MTCD").equals("EST") && biMst.get("CONT_MTCD").equals("10005")) {
//					// 카카오 알림톡 : 견적요청(13593)
//					parameterMap.put("P_MSG_SN", "13593");
//					FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
//					List<FwkDataEntity> vendList = iproEbidPblancDao.selectSelectVendUsrInfo(parameterMap);
//					for (FwkDataEntity vendInfo : vendList) {
//						vendInfo.put("P_CHRGR_NM", vendInfo.get("CHRGR_NM"));
//						vendInfo.put("P_CHRGR_TEL_NO", vendInfo.get("CHRGR_TEL_NO"));
//						vendInfo.put("P_VEND_NM", vendInfo.get("VEND_NM"));
//						vendInfo.put("P_ANNC_NO", vendInfo.get("ANNC_NO"));
//						vendInfo.put("P_BID_NM", vendInfo.get("BID_NM"));
//						vendInfo.put("P_BIDC_SBMT_ENDT_STR", vendInfo.get("BIDC_SBMT_ENDT_STR"));
//						vendInfo.put("P_BIDC_SBMT_STDT_STR", vendInfo.get("BIDC_SBMT_STDT_STR"));						
//						parameterMap.put("P_MSG_ENTITY", vendInfo);
//						parameterMap.put("P_MSG_CNTN", msgEntity.get("MSG_CNTN"));
//						parameterMap.put("P_RECEIVE_MOBILE_NO", vendInfo.get("CP_NO"));
//						parameterMap.put("P_TEMPLATE_CODE", "13593");
//						//알림톡 DB 입력
//						commItfcService.insertTsmsMsgInfo(parameterMap);				
//					}	
//				}
//				else 
				if(biMst.get("BID_MTCD").equals("BID") && (biMst.get("CONT_MTCD").equals("10001") || biMst.get("CONT_MTCD").equals("10002"))) {					
					// 카카오 알림톡 : 입찰공고(13589)
					parameterMap.put("P_MSG_SN", "13589");
					FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
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
						parameterMap.put("P_TEMPLATE_CODE", "13589");
						//알림톡 DB 입력
						commItfcService.insertTsmsMsgInfo(parameterMap);				
					}
				}else if(biMst.get("BID_MTCD").equals("EST") && biMst.get("CONT_MTCD").equals("10005")) {
					// 카카오 알림톡 : 견적지명(13593)
					parameterMap.put("P_MSG_SN", "13593");
					FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
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

		}
		
		trans.setResultCode("SUCC");
		return trans;
	}

	@Override
	public void updtAnncSttusChange(FwkParameterMap parameterMap) {
		//결제 상태값 변경
		iproEbidPblancDao.updateBiMstStChng(parameterMap);
		iproEbidPblancDao.updateBiRoundStChng(parameterMap);
		
		//완료후 데이터 변경
		iproEbidPblancDao.updateBiMstBiMst(parameterMap);
		iproEbidPblancDao.updateBiRoundBiRound(parameterMap);
		iproEbidPlanDao.deleteBidPrdlst(parameterMap);
		List<FwkDataEntity> biItemList = iproEbidPblancDao.selectBiItemList(parameterMap);
		for (int i = 0; i < biItemList.size(); i++) {
			FwkDataEntity biItme = biItemList.get(i);
			parameterMap.put("P_ITEM_SN", biItme.getString("ITEM_SN"));
			iproEbidPblancDao.insertBiItemBiItem(parameterMap);
		}
		parameterMap.put("P_BID_FSCD", "DI00");
		iproEbidPblancDao.updateBiFileBiFile(parameterMap);
		parameterMap.put("P_BID_FSCD", "DI01");
		iproEbidPblancDao.updateBiFileBiFile(parameterMap);
		List<FwkDataEntity> biProgHistList = iproEbidPblancDao.selectBiProgHistList(parameterMap);
		for (int i = 0; i < biProgHistList.size(); i++) {
			FwkDataEntity biProgHist = biProgHistList.get(i);
			parameterMap.put("P_HSTY_SN", biProgHist.getString("HSTY_SN"));
			iproEbidPblancDao.insertBiProgHistBiProgHist(parameterMap);
		}
		iproEbidPblancDao.updateBiMstCmpl(parameterMap);
		
		//업체 재투찰여부 Y
		iproEbidPblancDao.updateBiVendMstReTndr(parameterMap);
	}

	@Override
	public void updtAnncDelete(FwkParameterMap parameterMap) {
		iproEbidPblancDao.deleteBiMst(parameterMap);
	}

	@Override
	public FwkTransferObject vendTndrList(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if("PF30".equals(parameterMap.getString("P_BID_PSCD"))){
			List<FwkDataEntity> dataEntity = (List<FwkDataEntity>) iproEbidPblancDao.selectVendMstHistList(parameterMap);
			trans.put(IproEbidPartcptSttusService.BID_PARTCPT_ENTRPS_LIST ,dataEntity);
		}else{
			parameterMap.put("P_BID_TPI_SECD", "OP03");
			List<FwkDataEntity> dataEntity = (List<FwkDataEntity>) iproEbidPartcptSttusDao.selectBidPartcptEntrpsList(parameterMap);
			trans.put(IproEbidPartcptSttusService.BID_PARTCPT_ENTRPS_LIST ,dataEntity);
		}
		return trans;
	}

	@Override
	public FwkTransferObject ebidApprSendCrrc(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap htmlMap = new FwkParameterMap();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		// 계약수정
		parameterMap.put("P_BID_PSCD", "PF20");			//진행상태코드
		parameterMap.put("P_APPR_STCD", "NEW");	//결재진행상태코드	
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);
		parameterMap.put(IproEbidPlanService.BID_PLAN_DETAIL, biMst);
		parameterMap.put("P_IFC_ID", biMst.getString("IFC_ID"));
		parameterMap.put("BFAN_MST", iproEbidPlanDao.selectBfanMst(parameterMap));
		
		//정정공고사유 목록
		parameterMap.put("P_BID_PSCD", "PF30");
		parameterMap.put(IproEbidPblancService.PBLANC_PF30_HIST_LIST, iproEbidPblancDao.selectBiProgHistList(parameterMap));
		
		parameterMap.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPblancDao.selectBiItemList(parameterMap));
		
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPblancDao.selectBiFileDetail(parameterMap); 
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			List<FwkDataEntity>	fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
			parameterMap.put(IproEbidPlanService.BID_ATCH_DOC_LIST, fileList);
		}
		
		parameterMap.put("P_BID_FSCD", "DI00");
		FwkDataEntity redFileDataEntity = iproEbidPblancDao.selectBiFileDetail(parameterMap); 
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
		FwkDataEntity applHtmlMap = iproCommDefaultDao.selectApplFormHtml(parameterMap);
		parameterMap.put("P_APPL_HTML", applHtmlMap.get("HTML_CNTN"));
		
		//결재문서 매핑
		//htmlMap= CmmnUtil.ebidApprovalHtmlCreate(parameterMap);
		
		//LEGACY 테이블 저장
		parameterMap.put("P_PREFIX_STR", "EBID_ANNC_CRRC_");
		parameterMap.put("P_HTML_DATA1", htmlMap.get("APPL_HTML_END"));
		parameterMap.put("P_DRAFT_SABUN", user.getString("SABUN"));						//사번 : V_SSO_USER 
		parameterMap.put("P_DRAFT_DEPT", user.getString("DEPT_NO"));			//부서번호	: V_SSO_USER LEGACY_CODE
		parameterMap.put("P_TITLE", biMst.getString("BID_NM")+ " 정정공고");			//NULL 안됨
		parameterMap.put("P_MAIN_TITLE", biMst.getString("BID_NM")+ " 정정공고");	//NULL 안됨
		iproCommDefaultDao.insertIfLegacyMst(parameterMap);
		
		parameterMap.put("P_DOC_NO", biMst.getString("DOC_NO"));							// 문서번호
		FwkDataEntity ifLegacyMst = iproCommDefaultDao.selectIfLegacyMst(parameterMap);
		if(ifLegacyMst != null){
			parameterMap.put("P_APP_ID", ifLegacyMst.getString("APP_ID"));				// APP_ID
			iproCommDefaultDao.insertIfLegacyDoc(parameterMap);
		}
		
		//연동정보 테이블 저장
		parameterMap.put("P_BID_APPR_TSCD", "B");
		iproEbidPlanDao.insertBiApprCntc(parameterMap);
		
		trans.put("P_INTERFACE_ID", parameterMap.get("P_INTERFACE_ID"));
		trans.put("P_STATUS", "NEW");
		trans.put("P_DRAFT_SABUN", user.getString("SABUN"));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject ebidApprSendCncl(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap htmlMap = new FwkParameterMap();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		// 계약수정
		parameterMap.put("P_BID_PSCD", "PF40");			//진행상태코드
		parameterMap.put("P_APPR_STCD", "NEW");	//결재진행상태코드	
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		parameterMap.put(IproEbidPlanService.BID_PLAN_DETAIL, biMst);
		parameterMap.put("P_IFC_ID", biMst.getString("IFC_ID"));
		parameterMap.put("BFAN_MST", iproEbidPlanDao.selectBfanMst(parameterMap));
		
		//정정공고사유 목록
		parameterMap.put("P_BID_PSCD", "PF40");
		parameterMap.put(IproEbidPblancService.PBLANC_PF40_HIST_LIST, iproEbidPblancDao.selectBiProgHistList(parameterMap));
		
		parameterMap.put(IproEbidPlanService.BID_PRDLS_LIST, iproEbidPlanDao.selectBidPrdlstList(parameterMap));
		
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap); 
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			List<FwkDataEntity>	fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
			parameterMap.put(IproEbidPlanService.BID_ATCH_DOC_LIST, fileList);
		}
		
		parameterMap.put("P_BID_FSCD", "DI00");
		FwkDataEntity redFileDataEntity = iproEbidPlanDao.selectBidAtchDocList(parameterMap); 
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
		FwkDataEntity applHtmlMap = iproCommDefaultDao.selectApplFormHtml(parameterMap);
		parameterMap.put("P_APPL_HTML", applHtmlMap.get("HTML_CNTN"));
		
		//결재문서 매핑
		//htmlMap= CmmnUtil.ebidApprovalHtmlCreate(parameterMap);
		
		//LEGACY 테이블 저장
		parameterMap.put("P_PREFIX_STR", "EBID_ANNC_CNCL_");
		parameterMap.put("P_HTML_DATA1", htmlMap.get("APPL_HTML_END"));
		parameterMap.put("P_DRAFT_SABUN", user.getString("SABUN"));						//사번 : V_SSO_USER 
		parameterMap.put("P_DRAFT_DEPT", user.getString("DEPT_NO"));			//부서번호	: V_SSO_USER LEGACY_CODE
		parameterMap.put("P_TITLE", biMst.getString("BID_NM")+ " 공고취소");			//NULL 안됨
		parameterMap.put("P_MAIN_TITLE", biMst.getString("BID_NM")+ " 공고취소");	//NULL 안됨
		iproCommDefaultDao.insertIfLegacyMst(parameterMap);
		
		parameterMap.put("P_DOC_NO", biMst.getString("DOC_NO"));							// 문서번호
		FwkDataEntity ifLegacyMst = iproCommDefaultDao.selectIfLegacyMst(parameterMap);
		if(ifLegacyMst != null){
			parameterMap.put("P_APP_ID", ifLegacyMst.getString("APP_ID"));				// APP_ID
			iproCommDefaultDao.insertIfLegacyDoc(parameterMap);
		}
		
		//연동정보 테이블 저장
		parameterMap.put("P_BID_APPR_TSCD", "C");
		iproEbidPlanDao.insertBiApprCntc(parameterMap);
		
		trans.put("P_INTERFACE_ID", parameterMap.get("P_INTERFACE_ID"));
		trans.put("P_STATUS", "NEW");
		trans.put("P_DRAFT_SABUN", user.getString("SABUN"));
		
		return trans;
	}

	@Override
	public FwkTransferObject canclPblancDel(FwkParameterMap parameterMap) {
		parameterMap.put("P_BID_PSCD", "PF40");
		List<FwkDataEntity> list = iproEbidPblancDao.selectBiProgHistList(parameterMap);
		FwkDataEntity hist = list.get(0);
		parameterMap.put("P_HSTY_SN", hist.getInt("HSTY_SN")-1);
		FwkDataEntity data = iproEbidPblancDao.selectBiProgHist(parameterMap);
		parameterMap.put("P_BID_PSCD", data.getString("BID_PSCD"));
		
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
		return null;
	}

	/**
	 * <pre>
	 * 1. 개요 :  입찰시간 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : tndrTimeUpdt
	 * @date : 2019. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject tndrTimeUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰서제출기간시작
		if(!"".equals(parameterMap.getString("P_BIDC_SBMT_STDT")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_STDT_HH")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_STDT_MM"))){
			parameterMap.put("P_BIDC_SBMT_STDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_STDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BIDC_SBMT_STDT_HH")+parameterMap.get("P_BIDC_SBMT_STDT_MM")+"00"); 
		}
		//입찰서제출기간종료
		if(!"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT_HH")) && !"".equals(parameterMap.getString("P_BIDC_SBMT_ENDT_MM"))){
			parameterMap.put("P_BIDC_SBMT_ENDT", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_BIDC_SBMT_ENDT_HH")+parameterMap.get("P_BIDC_SBMT_ENDT_MM")+"00"); 
		}
		//개찰일시
		if(!"".equals(parameterMap.getString("P_OPNG_DT")) && !"".equals(parameterMap.getString("P_OPNG_DT_HH")) && !"".equals(parameterMap.getString("P_OPNG_DT_MM"))){
			parameterMap.put("P_OPNG_DT", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_DT").toString(), "yyyy-MM-dd", "yyyyMMdd")+parameterMap.get("P_OPNG_DT_HH")+parameterMap.get("P_OPNG_DT_MM")+"00"); 
		}		

		iproEbidPblancDao.insertBiRoundRegist(parameterMap);		
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
		//알림톡 메시지 발송
		FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);	//입찰기본
		parameterMap.put("P_BID_TPI_SECD", "OP05");	//적격
		if(biMst.get("SBID_MTCD").equals("34")) {

			// 카카오 알림톡 : 기술평가완료(13591)
			parameterMap.put("P_MSG_SN", "13591");	//적격
			FwkDataEntity msgEntityElgb = commItfcDao.selectMsgForm(parameterMap);
			
			// 카카오 알림톡 : 기술평가완료(부적합)(13592)
			parameterMap.put("P_MSG_SN", "13592");	//부적격
			FwkDataEntity msgEntityNt = commItfcDao.selectMsgForm(parameterMap);
			
			List<FwkDataEntity> vendList = iproEbidPblancDao.selectVendDtlUsrInfo(parameterMap);
			for (FwkDataEntity vendInfo : vendList) {
				if(vendInfo.get("ESTM_ELCD").equals("ELGB")) {
					parameterMap.put("P_MSG_CNTN", msgEntityElgb.get("MSG_CNTN"));
					parameterMap.put("P_TEMPLATE_CODE", "13591");
				}else if(vendInfo.get("ESTM_ELCD").equals("NT_ELGB")) {
					parameterMap.put("P_MSG_CNTN", msgEntityNt.get("MSG_CNTN"));
					parameterMap.put("P_TEMPLATE_CODE", "13592");
				}
				vendInfo.put("P_CHRGR_NM", vendInfo.get("CHRGR_NM"));
				vendInfo.put("P_CHRGR_TEL_NO", vendInfo.get("CHRGR_TEL_NO"));
				vendInfo.put("P_VEND_NM", vendInfo.get("VEND_NM"));
				vendInfo.put("P_ANNC_NO", vendInfo.get("ANNC_NO"));
				vendInfo.put("P_BID_NM", vendInfo.get("BID_NM"));
				vendInfo.put("P_BIDC_SBMT_ENDT_STR", vendInfo.get("BIDC_SBMT_ENDT_STR"));
				vendInfo.put("P_BIDC_SBMT_STDT_STR", vendInfo.get("BIDC_SBMT_STDT_STR"));
				parameterMap.put("P_MSG_ENTITY", vendInfo);
				parameterMap.put("P_RECEIVE_MOBILE_NO", vendInfo.get("CP_NO"));
				//알림톡 DB 입력
				try {
					commItfcService.insertTsmsMsgInfo(parameterMap);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(biMst.get("BID_MTCD").equals("BID") &&  biMst.get("G2B_NTFY_YN").equals("Y")) {
			try {
				// 공고게시 G2B 연계
				FwkDataEntity fde = iproG2bDao.selectG2bCreateBid(parameterMap);
				
//				CreateXML.CreateBidXml(fde);
				
				LOG.debug("G2B Success ===================<");
				
			} catch (Exception e1) {
				LOG.debug("G2B Fail Msg Start===================<");
				e1.printStackTrace();
				LOG.debug(">>> 나라장터연계에 실패하였습니다");
				LOG.debug("G2B Fail Msg End===================>");
			}	
		}	
		
		return trans;
		
	}	
}