package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPartcptSttusDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrdprcManageDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidSucbidrSlctnDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.ebid.service.IproEbidResultService;
import com.eunwoosoft.ipro.ebid.service.IproEbidSucbidrSlctnService;
import com.eunwoosoft.pki.EwSignBiz;


/**
 * 입찰결과 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 02. 13.
 * @version : 1.0
 */
@Service("iproEbidResultService")
public class IproEbidResultServiceImpl extends AbstractFwkService implements IproEbidResultService {
	
	@Resource(name="iproEbidResultDao")
	private IproEbidResultDao iproEbidResultDao;
	
	@Resource(name="iproEbidSucbidrSlctnDao")
	private IproEbidSucbidrSlctnDao iproEbidSucbidrSlctnDao;
	
	@Resource(name="iproEbidPrdprcManageDao")
	private IproEbidPrdprcManageDao iproEbidPrdprcManageDao;
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="iproEbidPblancDao")
	private IproEbidPblancDao iproEbidPblancDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproEbidPartcptSttusDao")
	private  IproEbidPartcptSttusDao iproEbidPartcptSttusDao;

	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnListInqireWithPgng
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidResultListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		if(parameterMap.get("P_OPNG_STDT_S") != null && !"".equals(parameterMap.get("P_OPNG_STDT_S"))){ // 개찰일자 시작
			parameterMap.put("P_OPNG_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_OPNG_ENDT_S") != null && !"".equals(parameterMap.get("P_OPNG_ENDT_S"))){ // 개찰일자 종료
			parameterMap.put("P_OPNG_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_OPNG_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproEbidResultService.BID_RESULT_LIST, iproEbidResultDao.selectBidResultListWithPgng(parameterMap));
		trans.put(IproEbidResultService.BID_RESULT_LIST_TOTCNT, 	iproEbidResultDao.selectBidResultListTotcnt(parameterMap));
			
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 목록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultListInqire
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl#bidResultListInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */

	@Override
	public FwkTransferObject bidResultListInqire(final FwkParameterMap parameterMap) {
		
		if(parameterMap.get("P_MUMM_SBID_AMT_S") != null && parameterMap.get("P_MUMM_SBID_AMT_S") != "")
		{
			parameterMap.put("P_MUMM_SBID_AMT_S", parameterMap.get("P_MUMM_SBID_AMT_S") + "000000");
			if("000000".equals(parameterMap.get("P_MUMM_SBID_AMT_S"))){
				parameterMap.put("P_MUMM_SBID_AMT_S","");
			}
		}
		if(parameterMap.get("P_MXMM_SBID_AMT_S") != null 
				&& !"".equals(parameterMap.get("P_MXMM_SBID_AMT_S")))
		{
			parameterMap.put("P_MXMM_SBID_AMT_S", parameterMap.get("P_MXMM_SBID_AMT_S") + "000000");
			if("000000".equals(parameterMap.get("P_MXMM_SBID_AMT_S"))){
				parameterMap.put("P_MXMM_SBID_AMT_S","");
			}
		}
		
		FwkTransferObject trans = new FwkTransferObject();
			
			trans.put(IproEbidResultService.BID_RESULT_LIST,
					iproEbidResultDao.selectBidResultList(parameterMap));
		
		return trans;
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnDetailInqire
	 * @date : 2015. 04. 03.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 03.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl#bidDcPeoDetailInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidResultDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰정보상세
		FwkDataEntity biMst = iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL, biMst);
		
		/*
		int anncNgr = parameterMap.getInt("P_ANNC_NGR");
		parameterMap.put("P_ANNC_NGR", anncNgr+1);
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL+2, iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap));
		parameterMap.put("P_ANNC_NGR", anncNgr);
		*/
		
		//개찰일 경우 복수예가 목록조회
		FwkDataEntity bidPlanEntity = (FwkDataEntity) trans.get("bidInfoDetail");
		if("180000".equals(bidPlanEntity.get("ESTPC_SECD"))){
			List<FwkDataEntity> mList =  iproEbidSucbidrSlctnDao.selectCompnoPrdprcList(parameterMap);
			
			ArrayList aList = new ArrayList();
			ArrayList bList = new ArrayList();
			ArrayList cList = new ArrayList();
			ArrayList dList = new ArrayList();
			for(int i=0; i<mList.size(); i++){
				FwkDataEntity cEntity = mList.get(i);
				aList.add(cEntity.get("PLR_ESTPC_NO"));
				bList.add(cEntity.get("PREP_PRCE_AMT"));
				cList.add(cEntity.get("DRLT_CNT"));
				dList.add(cEntity.get("ESTPC_RNK"));
			}
			trans.put("aList", aList);
			trans.put("bList", bList);
			trans.put("cList", cList);
			trans.put("dList", dList);
		}
		
		//낙찰 시 보여줄 업체 목록
		trans.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_LIST, iproEbidSucbidrSlctnDao.selectLwetScsbidPrearngerList(parameterMap));
		
		//유찰 시 보여줄 업체 목록
		if(biMst.get("SBID_MTCD").equals("34")) {
			parameterMap.put("P_BID_TPI_SECD", "OP05");
		}else {
			parameterMap.put("P_BID_TPI_SECD", "OP03");
		}
		trans.put(IproEbidResultService.FIB_SCSBID_PREARNGER_LIST, iproEbidPartcptSttusDao.selectBidPartcptEntrpsList(parameterMap));
		
		
		trans.put(IproEbidSucbidrSlctnService.NT_ELGB_VEND_LIST, iproEbidSucbidrSlctnDao.selectNtElgbVendList(parameterMap));
		
		parameterMap.put("P_BID_PSCD", "PF99");
		List<FwkDataEntity> histList = iproEbidPblancDao.selectBiProgHistList(parameterMap);
		if(histList != null && histList.size() > 0){
			trans.put( "procRsnDetail", histList.get(0));
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가결과 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlInqire
	 * @date : 2015. 4. 3.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 3.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl#properJdgmnEvlInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject properJdgmnEvlInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidSucbidrSlctnService.PROPER_JDGMN_ENTRPS_DETAIL, iproEbidResultDao.selectProperJdgmnEntrpsDetail(parameterMap));
		
		//****************************** 주 사업내용 시작 ******************************/
		List<FwkDataEntity> mainBsnsList = iproEbidResultDao.selectMainBnsnList(parameterMap);
		
		String mainBsns = "";
		
		for(int i = 0; i< mainBsnsList.size(); i++)
		{
			if( i == 0){
				mainBsns = (String) mainBsnsList.get(i).get("INDUTY_NM");
			}else{
				mainBsns = mainBsns + "," + (String) mainBsnsList.get(i).get("INDUTY_NM");
			}
		}
		
		trans.put("MAIN_BSNS", mainBsns);
		//****************************** 주 사업내용 종료 ******************************/
		
		//****************************** 사업참여이력 시작 ******************************/
		trans.put(IproEbidSucbidrSlctnService.BSNS_PARTCPTN_HIST_LIST, iproEbidResultDao.selectBsnsPartcptnHistList(parameterMap));
		//****************************** 사업참여이력 종료 ******************************/
		
		//****************************** 심사평가 리스트 시작 ******************************/
//		trans.put(IproEbidSucbidrSlctnService.ENTRPS_JDGMN_EVL_LIST
//				, iproEbidResultDao.selectEntrpsJdgmnEvlList(parameterMap));
		//****************************** 심사평가 리스트 종료 ******************************/
		//****************************** 업체 합산 점수 시작 ******************************/
//		List<FwkDataEntity> entrpsJdgmnEvlList = iproEbidResultDao.selectEntrpsJdgmnEvlList(parameterMap);
		
		double entrpsEvlTot = 0;
		
		trans.put("entrpsEvlTot", entrpsEvlTot);
		//****************************** 업체 합산 점수 종료 ******************************/
		return trans;
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 - 결격사유 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultBrdoResnInqire
	 * @date : 2015. 4. 6.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 6.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl#bidResultBrdoResnInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param praFwkParameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidResultBrdoResnInqire(final FwkParameterMap parameterMap) {
		
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰정보상세
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL, 
				iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap));
						
		//업체정보상세
		trans.put(IproEbidSucbidrSlctnService.ENTRPS_INFO_DETAIL, 
						iproEbidSucbidrSlctnDao.selectEntrpsInfoDetail(parameterMap));
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 - 품목별 세부입찰 내역 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdlstAcctoDetailBidDtlsInqire
	 * @date : 2015. 8. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl#prdlstAcctoDetailBidDtlsInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdlstAcctoDetailBidDtlsInqire(final FwkParameterMap parameterMap){
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidResultDao.selectPrdlstAcctoDetailBidDtlsInqire(parameterMap);
		
		//List<FwkDataEntity> bidThngList = (List<FwkDataEntity>) iproEbidResultDao.selectBidThngList(parameterMap);
		
		trans.put(IproEbidResultService.BID_THNG_LIST, iproEbidResultDao.selectBidThngList(parameterMap));
		trans.put(IproEbidSucbidrSlctnService.PRDLST_ACCTO_DETAIL_BID_DTLS_INQIRE, dataEntity);
		
		parameterMap.put("P_BID_SBMT_FSCD", "DO02");
		FwkDataEntity enatpaInqireDO02 = iproEbidResultDao.selectEnatpaDownInqire(parameterMap);
		trans.put(IproEbidResultService.ENATPA_INQIRE+"DO02", enatpaInqireDO02);
		
		if(enatpaInqireDO02 != null){
			parameterMap.put("P_FILE_GRP_NO", enatpaInqireDO02.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST+"DO02", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		parameterMap.put("P_BID_SBMT_FSCD", "DO04");
		FwkDataEntity enatpaInqireDO04 = iproEbidResultDao.selectEnatpaDownInqire(parameterMap);
		trans.put(IproEbidResultService.ENATPA_INQIRE+"DO04",  enatpaInqireDO04);
		
		if(enatpaInqireDO04 != null){
			parameterMap.put("P_FILE_GRP_NO", enatpaInqireDO04.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST+"DO04", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
				
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 복수예가 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : compnoPrdprcListInqire
	 * @date : 2016. 02. 29.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 29.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidResultService.iep.elbi.service.IproEbidResultService#compnoPrdprcListInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject compnoPrdprcListInqire(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		// 복수예가 목록 조회
		List<FwkDataEntity> compnoPrdprcList = iproEbidPrdprcManageDao.selectCompnoPrdprcList(parameterMap);

		if(compnoPrdprcList == null){
			LOG.debug("복수예가가 등록되지 않았습니다.");
			trans.put("msg", "복수예가가 등록되지 않았습니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		try
		{
			for(int i=0; i < compnoPrdprcList.size(); i++){
				FwkDataEntity compnoPrdprc = compnoPrdprcList.get(i);
				String preparPcEncptValue = compnoPrdprc.get("PREP_PRCE_ENC").toString(); // 예비가격 암호화 값 

				// 서버인증서로 복호화된 예정가격금액
				
        		byte[] decAmount = null; 
        		
        		EwSignBiz esb = new EwSignBiz();
        		decAmount = esb.decryptEnvelopData(preparPcEncptValue.getBytes());
				
				// 복호화 된 예비가격금액
        		compnoPrdprcList.get(i).put("RNUM",i+1);
        		compnoPrdprcList.get(i).put("PREP_PRCE_AMT", FwkFormatUtil.formatMoney(new String(decAmount)));
			}
			
			trans.put(IproEbidResultService.COMPNO_PRDPRC_LIST, compnoPrdprcList);
		}
		catch (Exception e)
		{
			trans.put("msg", "예가복호화에 실패하였습니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 업체투찰정도 수기등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertG2BVendInfo
	 * @date : 2018. 10. 12.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 12.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject insertG2BVendInfo(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
		Object P_TNDR_AMT = parameterMap.get("P_TNDR_AMT");
		Object P_ELGB_ESTM_SCR = parameterMap.get("P_ELGB_ESTM_SCR");
		Object P_ESTM_SCR = parameterMap.get("P_ESTM_SCR");
		
		String sbidNo = parameterMap.getString("P_SBID_YN");
		
		if(!"70".equals(parameterMap.getString("P_SBID_MTCD"))){
			iproEbidPlanDao.deleteBidNmfpcEntrps(parameterMap);
			iproEbidResultDao.deleteBiVendBiop(parameterMap);
			
			if(P_VEND_REG_NO instanceof String){
				if(sbidNo.equals(parameterMap.getString("P_VEND_REG_NO"))){
					parameterMap.put("P_SBID_YN", "Y");
				}else{
					parameterMap.put("P_SBID_YN", "");
				}
				iproEbidResultDao.insertG2BVendInfoMst(parameterMap);
				iproEbidResultDao.insertG2BVendBiopInfo(parameterMap);
			}else if(P_VEND_REG_NO instanceof String[]){
				String[] vendRegNoList = (String[])P_VEND_REG_NO;
				String[] tndrAmtList = (String[])P_TNDR_AMT;
				String[] elgbEstmScrList = null;
				if(P_ELGB_ESTM_SCR != null){
					elgbEstmScrList = (String[])P_ELGB_ESTM_SCR;
				}
				String[] estmScrList = null;
				if(P_ESTM_SCR != null){
					estmScrList = (String[])P_ESTM_SCR;
				}
				
				for (int i = 0; i < vendRegNoList.length; i++) {
					parameterMap.put("P_VEND_REG_NO", vendRegNoList[i]);
					parameterMap.put("P_TNDR_AMT", tndrAmtList[i]);
					if(P_ELGB_ESTM_SCR != null){
						parameterMap.put("P_ELGB_ESTM_SCR", elgbEstmScrList[i]);
					}
					if(P_ESTM_SCR != null){
						parameterMap.put("P_ESTM_SCR", estmScrList[i]);
					}
					if(sbidNo.equals(parameterMap.getString("P_VEND_REG_NO"))){
						parameterMap.put("P_SBID_YN", "Y");
					}else{
						parameterMap.put("P_SBID_YN", "");
					}
					
					iproEbidResultDao.insertG2BVendInfoMst(parameterMap);
					iproEbidResultDao.insertG2BVendBiopInfo(parameterMap);
				}
			}else if(P_VEND_REG_NO instanceof ArrayList){
				ArrayList vendRegNoList = (ArrayList)P_VEND_REG_NO;
				ArrayList tndrAmtList = (ArrayList)P_TNDR_AMT;
				ArrayList elgbEstmScrList = null;
				if(P_ELGB_ESTM_SCR != null){
					elgbEstmScrList = (ArrayList)P_ELGB_ESTM_SCR;
				}
				ArrayList estmScrList = null;
				if(P_ESTM_SCR != null){
					estmScrList = (ArrayList)P_ESTM_SCR;
				}
				
				for (int i = 0; i < vendRegNoList.size(); i++) {
					parameterMap.put("P_VEND_REG_NO", vendRegNoList.get(i));
					parameterMap.put("P_TNDR_AMT", tndrAmtList.get(i));
					if(P_ELGB_ESTM_SCR != null){
						parameterMap.put("P_ELGB_ESTM_SCR", elgbEstmScrList.get(i));
					}
					if(P_ESTM_SCR != null){
						parameterMap.put("P_ESTM_SCR", estmScrList.get(i));
					}
					if(sbidNo.equals(parameterMap.getString("P_VEND_REG_NO"))){
						parameterMap.put("P_SBID_YN", "Y");
					}else{
						parameterMap.put("P_SBID_YN", "");
					}
					
					iproEbidResultDao.insertG2BVendInfoMst(parameterMap);
					iproEbidResultDao.insertG2BVendBiopInfo(parameterMap);
				}
			}
			
			iproEbidResultDao.insertResultBiEstcMstRegist(parameterMap);
		}else{
			iproEbidResultDao.deleteDatpMst(parameterMap);
			
			if(P_VEND_REG_NO instanceof String){
				if(sbidNo.equals(parameterMap.getString("P_VEND_REG_NO"))){
					parameterMap.put("P_SBID_YN", "Y");
				}else{
					parameterMap.put("P_SBID_YN", "");
				}
				iproEbidResultDao.insertDatpMst(parameterMap);
			}else if(P_VEND_REG_NO instanceof String[]){
				String[] vendRegNoList = (String[])P_VEND_REG_NO;
				String[] tndrAmtList = (String[])P_TNDR_AMT;
				
				for (int i = 0; i < vendRegNoList.length; i++) {
					parameterMap.put("P_VEND_REG_NO", vendRegNoList[i]);
					parameterMap.put("P_TNDR_AMT", tndrAmtList[i]);
					if(sbidNo.equals(parameterMap.getString("P_VEND_REG_NO"))){
						parameterMap.put("P_SBID_YN", "Y");
					}else{
						parameterMap.put("P_SBID_YN", "");
					}
					iproEbidResultDao.insertDatpMst(parameterMap);
				}
			}else if(P_VEND_REG_NO instanceof ArrayList){
				ArrayList vendRegNoList = (ArrayList)P_VEND_REG_NO;
				ArrayList tndrAmtList = (ArrayList)P_TNDR_AMT;
				
				for (int i = 0; i < vendRegNoList.size(); i++) {
					parameterMap.put("P_VEND_REG_NO", vendRegNoList.get(i));
					parameterMap.put("P_TNDR_AMT", tndrAmtList.get(i));
					if(sbidNo.equals(parameterMap.getString("P_VEND_REG_NO"))){
						parameterMap.put("P_SBID_YN", "Y");
					}else{
						parameterMap.put("P_SBID_YN", "");
					}
					iproEbidResultDao.insertDatpMst(parameterMap);
				}
			}
		}
		
		if(!"".equals(parameterMap.getString("P_PROC_RSN"))){
			parameterMap.put("P_BID_PSCD", "PF99");
		}else{
			parameterMap.put("P_BID_PSCD", "PF61");
		}
		
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 지급각서 보기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl.java
	 * @Method : grntDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject grntDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("grntDetail", iproEbidResultDao.selectGrntDetail(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 청렴이행각서 보기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl.java
	 * @Method : cleanDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject cleanDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("cleanDetail", iproEbidResultDao.selectCleanDetail(parameterMap));
		return trans;
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 구매관리이관 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : pvctSendUpdt
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
     *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject pvctSendUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		iproEbidPlanDao.updateBidPvctCnvrY(parameterMap);
		return trans;
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 라운드 리스트 정보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidRoundList
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
     *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidRoundList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidResultService.BID_RESULT_ROUND_LIST, iproEbidResultDao.selectBidRoundList(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 라운드 마스터 정보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : BidVendMstList
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject BidVendMstList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidResultService.BID_RESULT_VEND_MST_LIST, iproEbidResultDao.selectBidRoundList(parameterMap));
		
		return trans;
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 라운드 정보 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl.java
	 * @Method : roundInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 5. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject roundInfoDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity roundInfoDetail = iproEbidResultDao.selectBidRoundDetail(parameterMap);
		
		// 재입찰사유
		trans.put("roundRsn", roundInfoDetail.get("ROUND_RSN"));
		
		//입찰정보상세
		parameterMap.put("P_ROUND_NO", parameterMap.get("P_ROUND_NO_S"));
		
		FwkDataEntity biMst = iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL, biMst);
		
		// 복수예가
		FwkDataEntity bidPlanEntity = (FwkDataEntity) trans.get("bidInfoDetail");
		if("180000".equals(bidPlanEntity.get("ESTPC_SECD"))){
			List<FwkDataEntity> mList =  iproEbidSucbidrSlctnDao.selectCompnoPrdprcList(parameterMap);
			
			ArrayList aList = new ArrayList();
			ArrayList bList = new ArrayList();
			ArrayList cList = new ArrayList();
			ArrayList dList = new ArrayList();
			for(int i=0; i<mList.size(); i++){
				FwkDataEntity cEntity = mList.get(i);
				aList.add(cEntity.get("PLR_ESTPC_NO"));
				bList.add(cEntity.get("PREP_PRCE_AMT"));
				cList.add(cEntity.get("DRLT_CNT"));
				dList.add(cEntity.get("ESTPC_RNK"));
			}
			trans.put("aList", aList);
			trans.put("bList", bList);
			trans.put("cList", cList);
			trans.put("dList", dList);
		}
		
		trans.put(IproEbidSucbidrSlctnService.SCSBID_PREARNGER_LIST, iproEbidSucbidrSlctnDao.selectLwetScsbidPrearngerList(parameterMap));
		trans.put(IproEbidSucbidrSlctnService.NT_ELGB_VEND_LIST, iproEbidSucbidrSlctnDao.selectNtElgbVendList(parameterMap));
		
		return trans;
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 품목별 업체 투찰금액 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.service.impl.IproEbidResultServiceImpl.java
	 * @Method : tndrItemAmtList
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 13. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject tndrItemAmtList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity biMst = iproEbidSucbidrSlctnDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidSucbidrSlctnService.BID_INFO_DETAIL, biMst);

		List<FwkDataEntity> tndrItemAmtList =  iproEbidResultDao.selectTndrItemAmtList(parameterMap);
		trans.put("tndrItemAmtList", tndrItemAmtList);
		
		return trans;
	}	
	
	
}