package com.eunwoosoft.ipro.ebid.service.impl; 

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidOpengManageDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPartcptSttusDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPblancDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrdprcManageDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidSucbidrSlctnDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidOpengManageService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPartcptSttusService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPrdprcManageService;
import com.eunwoosoft.ipro.g2b.dao.IproG2bDao;
import com.yettiesoft.vestsign.external.Enveloper;
import com.yettiesoft.vestsign.external.SignVerifier;

/**
 * 개찰관리 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 02. 27.
 * @version : 1.0
 */
@Service("iproEbidOpengManageService")
public class IproEbidOpengManageServiceImpl extends AbstractFwkService implements IproEbidOpengManageService {
	
	@Resource(name="iproEbidOpengManageDao")
	private IproEbidOpengManageDao iproEbidOpengManageDao;
	
	@Resource(name="iproEbidSucbidrSlctnDao")
	private IproEbidSucbidrSlctnDao iproEbidSucbidrSlctnDao;

	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="iproEbidPartcptSttusDao")
	private IproEbidPartcptSttusDao iproEbidPartcptSttusDao;
	
	@Resource(name="iproEbidPrdprcManageDao")
	private IproEbidPrdprcManageDao iproEbidPrdprcManageDao;
	
	@Resource(name="iproEbidPblancDao")
	private IproEbidPblancDao iproEbidPblancDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao; 
	
	@Resource(name="iproEbidPrdprcManageService")
	private IproEbidPrdprcManageService iproEbidPrdprcManageService;
	
	@Resource(name="comCmcdDetailCdDao")
	private ComCmcdDetailCdDao comCmcdDetailCdDao;
	
	@Resource(name="iproG2bDao")
	private IproG2bDao iproG2bDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : opengManageListInqireWithPgng
	 * @date : 2015. 02. 27.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 27.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject opengManageListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S"))){ // 공고일자 시작
			parameterMap.put("P_BIDC_SBMT_ENDT_STDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S"))){ // 공고일자 종료
			parameterMap.put("P_BIDC_SBMT_ENDT_ENDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(IproEbidOpengManageService.OPENG_MANAGE_LIST, 
		        iproEbidOpengManageDao.selectOpengManageListWithPgng(parameterMap));
		trans.put(IproEbidOpengManageService.OPENG_MANAGE_LIST_TOTCNT, 
		        iproEbidOpengManageDao.selectOpengManageListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : opengManageDetailInqire
	 * @date : 2015. 02. 27.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 27.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject opengManageDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		// 입찰정보상세
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidOpengManageService.BID_PLAN_DETAIL, biMst);
		
		trans.put(IproEbidOpengManageService.PRDPRC_INFO, iproEbidPrdprcManageDao.selectPrdprcInfoInqire(parameterMap));
		
		// 참가업체 목록 조회
		List<FwkDataEntity> dataEntity = null;
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		dataEntity = (List<FwkDataEntity>) iproEbidPartcptSttusDao.selectBidPartcptEntrpsList(parameterMap);		
		trans.put(IproEbidPartcptSttusService.BID_PARTCPT_ENTRPS_LIST ,dataEntity);
		
		/* 입찰 적격 수에 따른 재입찰 플래그 */
		trans.put("NOT_ELCD_EXT_YN", "N");
		int elgb = 0;
		int ntElgb = 0;
		boolean notFlag = true;
		if(dataEntity != null && dataEntity.size() > 0) {	//제출업체 있을 때
			for (FwkDataEntity fde : dataEntity) {
				if(fde.get("BID_ELCD") == null || fde.get("BID_ELCD").equals("")) {
					notFlag = false;
				}else if(fde.get("BID_ELCD").equals("ELGB")) {
					elgb++;
				}else if(fde.get("BID_ELCD").equals("NT_ELGB")) {
					ntElgb++;
				}else if(fde.get("ESTM_ELCD").equals("NT_ELGB")) {
					ntElgb++;
				}else if(fde.get("ESTM_ELCD").equals("ELGB")) {
					elgb++;
				}
			}
			
			if(!notFlag) {	//적격판단 미업체 존재 시
				trans.put("NOT_ELCD_EXT_YN", "Y");
			}else {
				if(elgb < 2) {	//적격 업체 수 2개 미만
					trans.put("NOT_ELCD_EXT_YN", "Y");
				}else {
					trans.put("NOT_ELCD_EXT_YN", "N");
				}	
			}
			
		}else {	//제출업체 없을 때 -> 없으면 유찰만
			trans.put("NOT_ELCD_EXT_YN", "Y");
		}			
		
		trans.put(IproEbidOpengManageService.TCHN_COMP_AT , iproEbidOpengManageDao.selectTchqvlnComptAtInqire(parameterMap));
		
		//trans.put("biApprCntc",iproEbidPlanDao.selectBiApprCntc(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : opengInfoRegist
	 * @date : 2015. 03. 03.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 03.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject opengInfoRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		Enveloper enveloper = new Enveloper(); 
		int encoding = 0; // 0 - base64 , 1 - hex
		String charset = "utf-8";
		
		/*
	     * 	public static final int CERT_STATUS_CRL	= 0;
	     *  public static final int CERT_STATUS_OCSP = 1;
	     *  public static final int CERT_STATUS_NONE = 2;
	     */
		
		parameterMap.put("P_CD_ID", "CERT_STATUS");
		parameterMap.put("P_ETC_VAL1", "Y");
		
		List<FwkDataEntity> certList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		
		int cert_verify = 0;         
		if( certList != null && certList.size() > 0){
			FwkDataEntity cert = certList.get(0);
			cert_verify = cert.getInt("CD_VALUE_NM");
		}
		cert_verify = 2;
		
		// 입찰정보상세
		FwkDataEntity bidInfoDetail =	iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		if("180000".equals(bidInfoDetail.getString("ESTPC_SECD"))){
			iproEbidPrdprcManageService.rebach(parameterMap);
		}
		
		// 예가정보 조회
		FwkDataEntity prdprcInfo = iproEbidPrdprcManageDao.selectPrdprcInfoInqire(parameterMap);
		
		/***** 개찰 시간 체크 *****/
		if(Long.parseLong(bidInfoDetail.get("OPNG_DT").toString()) >  Long.parseLong(FwkDateUtil.getCurrentDateTimeAsString())){ 
			LOG.debug("개찰예정시간 이후 개찰이 가능합니다.");
			trans.put("msg", "개찰예정시간 이후 개찰이 가능합니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		/***** 입찰 진행상태 체크 *****/
		if(!"PF20".equals(bidInfoDetail.get("BID_PSCD")) && !"PF30".equals(bidInfoDetail.get("BID_PSCD"))){
			LOG.debug("입찰이 현재 공고중이 아닙니다.");
			trans.put("msg", "입찰이 현재 공고중이 아닙니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		/***** 오프라인 계약 처리 후 바로 리턴 *****/
		if("OFF".equals(bidInfoDetail.get("BID_MTCD"))){
			// 공고 진행 상태 코드 수정
			parameterMap.put("P_BID_PSCD", "PF50");
			parameterMap.put("P_APPR_STCD", "");
			parameterMap.put("P_OPNG_YN", "Y");
			iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
			iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
			
			// 입찰진행이력 등록 (입찰공고 - 개찰)
			iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
			
			LOG.debug("개찰 완료");
			trans.setResultCode("SUCC");
			return trans;
		}
		
		/***** 기초예가 등록 체크 *****/
		if("180001".equals(bidInfoDetail.get("ESTPC_SECD"))){
			if("".equals(prdprcInfo.getString("SCH_PRCE_ENC"))){
				LOG.debug("예가가 등록되지 않았습니다.");
				trans.put("msg", "예가가 등록되지 않았습니다.");
				trans.setResultCode("ERR");
				return trans;
			}
		}
		
		/******************
		 * 참여 업체 정보 조회
		 * 1. 포기업체 제외
		 * 2. 입찰부적격 업체 제외
		 * 3. 입찰서 제출 업체
		 ******************/
		parameterMap.put("reload", "N");
		List<FwkDataEntity> partcptnEntrpsInfoList = iproEbidOpengManageDao.selectPartcptnEntrpsInfoInqire(parameterMap);
		
		// 협상에 의한 계약(기술가격종합평가) 일때 가격점수 입력
		if("40".equals(bidInfoDetail.get("SBID_MTCD"))){
			if(partcptnEntrpsInfoList != null){
				for(int i=0; i < partcptnEntrpsInfoList.size(); i++){
					FwkDataEntity partcptnEntrpsInfo = partcptnEntrpsInfoList.get(i);
					
					parameterMap.put("P_VEND_REG_NO", partcptnEntrpsInfo.get("VEND_REG_NO")); // 업체등록번호
					
					if(partcptnEntrpsInfo.get("ESTM_SCR") == null || "".equals(partcptnEntrpsInfo.get("ESTM_SCR"))){
						LOG.debug("기술평가 점수가 등록되지 않았습니다.");
						trans.put("msg", "기술평가 점수가 등록되지 얺았습니다.");
						trans.setResultCode("ERR");
						return trans;
					}
				}
			}
		}
		
		
		/*************************************************
		 *  참여 업체 체크
		 *  1. 수의 업체는 업체 투찰이 한건도 없을 시 유찰 처리
		 *  2. 일반, 경쟁, 지명은 투찰 업체가 2군데 미만 일 경우 유찰 처리
		 *     - 부적격인 업체는 투찰업체에서 제외
		 *************************************************/
		// 10000 일반경쟁 , 10001 지명경쟁, 10002 제한경쟁, 10003 수의계약
		if("Y".equals(bidInfoDetail.getString("AUTO_FBID_YN"))){ // 자동유찰여부
			//수의이거나 2단계 동시 분리이거나 소액경쟁-지명 이거나 : 1개업체여도 진행
			if("10003".equals(bidInfoDetail.get("CONT_MTCD")) || "10006".equals(bidInfoDetail.get("CONT_MTCD")) 
					|| "35".equals(bidInfoDetail.getString("SBID_MTCD")) || "10005".equals(bidInfoDetail.get("CONT_MTCD"))){
				if(partcptnEntrpsInfoList == null || partcptnEntrpsInfoList.size() < 1){
					
					// 유찰 처리
					
					// 공고 진행상태 코드 수정 
					parameterMap.put("P_BID_PSCD", "PF99");
					iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
					iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
					
					// 입찰진행이력 등록 (입찰공고 - 유찰)
					iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
	
					//throw new ();
					LOG.debug("업체 투찰 정보가 존재하지 않습니다.");
					trans.put("msg", "업체 투찰 정보가 존재하지 않습니다.");
					trans.setResultCode("FBI");
					return trans;
				}
			}else{
				if(partcptnEntrpsInfoList == null || partcptnEntrpsInfoList.size() < 2){
					
					// 유찰 처리
					
					// 공고 진행상태 코드 수정 
					parameterMap.put("P_BID_PSCD", "PF99");
					iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
					iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
					
					// 입찰진행이력 등록 (입찰공고 - 유찰)
					iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
					
					// 업체 진행 이력 등록 
					if(partcptnEntrpsInfoList != null){
						for(int i=0; i < partcptnEntrpsInfoList.size(); i++){
							
							FwkDataEntity partcptnEntrpsInfo = partcptnEntrpsInfoList.get(i);
							
							parameterMap.put("P_VEND_REG_NO", partcptnEntrpsInfo.get("VEND_REG_NO"));
							parameterMap.put("P_BID_VEND_PSCD", "OP11");
							iproEbidOpengManageDao.insertEntrpsProgrsHistRegist(parameterMap);
						}
					}
					//throw new ();
					LOG.debug("참가 업체가 2개 미만입니다.");
					trans.put("msg", "참가 업체가 2개 미만입니다.");
					trans.setResultCode("FBI");
					return trans;
				}
			}
		}
		
		/****** 기본정보 ******/
		String drprRepr = "4"; // 추첨 예가 개수
//		if("Y".equals(bidInfoDetail.getString("UPR_8CNT_YN"))){
//			drprRepr = "8";
//		}else{
//			drprRepr = "7";
//		}
		parameterMap.put("P_DRPR_REPR", drprRepr);
		
		
		/****** 예가 복호화 ******/
		/*************************
		 *  단일예가인 경우
		 *  1. 예비가격 복호화
		 *************************/
		if("180001".equals(bidInfoDetail.get("ESTPC_SECD"))){
			String lnprcEncptValue = prdprcInfo.get("SCH_PRCE_ENC").toString(); // 암호화된 예정가격금액
			
			try
			{
				// 서버인증서로 복호화된 예정가격금액
        		String decAmount = enveloper.deEnvelope(lnprcEncptValue, encoding, charset);
        		
				parameterMap.put("P_SCH_PRCE_AMT", new String(decAmount));
				// 예가금액수정
				iproEbidOpengManageDao.updatePrdprcAmountUpdt(parameterMap);
			}
			catch (Exception e)
			{
				trans.put("msg", "예가복호화에 실패하였습니다.");
				trans.setResultCode("ERR");
				e.printStackTrace();
				return trans;
			}
			
		
		/******************************
		 * 	복수예가 인 경우 
		 * 	1. 예비가격 복호화
		 * 	2. 업체 추첨 횟수/ 선택여부/ 예가순위 등록
		 * 3. 가장 많이 선택된 예비가격의 평균 계산
		 ******************************/
		}else if("180000".equals(bidInfoDetail.get("ESTPC_SECD"))){
			// 복수예가일때 투찰 업체가 0개 면 복호화 및 예정가격생성 안함
			/*if(partcptnEntrpsInfoList.size() > 0 || partcptnEntrpsInfoList != null){
				
				//// 1. 예비가격 복호화 ////
				
				// 복수예가 목록 조회
				List<FwkDataEntity> compnoPrdprcList = iproEbidPrdprcManageDao.selectCompnoPrdprcList(parameterMap);
	
				if(compnoPrdprcList == null || compnoPrdprcList.size() == 0){
					LOG.debug("복수예가가 등록되지 않았습니다.");
					trans.put("msg", "복수예가가 등록되지 않았습니다.");
					trans.setResultCode("ERR");
					return trans;
				}
				
				try{
					for(int i=0; i < compnoPrdprcList.size(); i++){
						FwkDataEntity compnoPrdprc = compnoPrdprcList.get(i);
						String preparPcEncptValue = compnoPrdprc.get("PREP_PRCE_ENC").toString(); // 예비가격 암호화 값 
	
		        		String decAmount = enveloper.deEnvelope(preparPcEncptValue, encoding, charset);
		        		
		        		if(decAmount == null){
		        			trans.put("msg", "예가복호화에 실패하였습니다.");
							trans.setResultCode("ERR");
							LOG.debug(" Error =================== 복호화실패 "  );
							return trans;
		        		}
		        		
						// 복호화 된 예비가격금액
	//					parameterMap.put("P_PREP_PRCE_AMT", new String(decAmount));
		        		parameterMap.put("P_PREP_PRCE_AMT", decAmount);
						parameterMap.put("P_PLR_ESTPC_NO", compnoPrdprc.get("PLR_ESTPC_NO").toString());
						// 복수예가 금액 수정
						iproEbidOpengManageDao.updateCompnoPrdprcAmountUpdt(parameterMap);
						
					}
				}catch (Exception e){
					trans.put("msg", "예가복호화에 실패하였습니다.");
					trans.setResultCode("ERR");
					e.printStackTrace();
					return trans;
				}
				
				//// 2. 업체 추첨 횟수/ 선택여부/ 예가순위 등록  ////
				
				// 추첨 복수예가 저장
				iproEbidOpengManageDao.updateDrwtCompnoPrdprcStre(parameterMap);
				
				// 복수 예가 순위 조회
				List<FwkDataEntity> compnoPrdpcRankList = iproEbidOpengManageDao.selectCompnoPrdprcRankInqire(parameterMap);
				
				if(compnoPrdpcRankList != null){
					for(int i=0; i < compnoPrdpcRankList.size(); i++){
						FwkDataEntity compnoPrdpcRanck = compnoPrdpcRankList.get(i);
						parameterMap.put("P_ESTPC_RNK", compnoPrdpcRanck.get("RNUM"));
						parameterMap.put("P_PLR_ESTPC_NO", compnoPrdpcRanck.get("PLR_ESTPC_NO"));
						// 복수 예가 순위 저장
						iproEbidOpengManageDao.updateCompnoPrdprcRankStre(parameterMap);
					}
				}
				
				// 복수 예가 선택 저장
				iproEbidOpengManageDao.updateCompnoPrdprcChoiseStre(parameterMap);
				
				// 예정가격금액 저장
				iproEbidOpengManageDao.updatePrearngePcAmountStre(parameterMap);
			}*/
		}
		

		/****** 업체 투찰 금액 복호화 ******/
		if(partcptnEntrpsInfoList != null){
			for(int i=0; i < partcptnEntrpsInfoList.size(); i++){
				FwkDataEntity partcptnEntrpsInfo = partcptnEntrpsInfoList.get(i);
				
				parameterMap.put("P_VEND_REG_NO", partcptnEntrpsInfo.get("VEND_REG_NO")); // 업체등록번호
				
				if(partcptnEntrpsInfo.get("TNDR_AMT_ENC") == null){
					// 업체 투찰 금액이 없습니다.
					LOG.debug("업체 투찰 금액이 없습니다. "+partcptnEntrpsInfo.get("VEND_REG_NO"));
					trans.put("msg", "업체 투찰 금액이 없습니다.");
					trans.setResultCode("ERR");
					return trans;
				}else{
					// 투찰 금액 복호화
						
					//서명검증
					String sm = partcptnEntrpsInfo.get("TNDR_AMT_ENC").toString();
					
				    SignVerifier sv = new SignVerifier(	sm 					/*전자서명문*/
				            											,cert_verify		/*유효성검증 방법*/
				            											,encoding);		/*서명문 인코딩 룰
				            																		base64 = 0
				            																		hex = 1          */ 
				    sv.verify();
					
				    // 0 : 성공
				    // -10 : 만료 
				    // -20 : 폐기
				    // -30 : 기타오류
			        
				    int nVerifierResult = sv.getLastErrorCode();
				    
				    if(nVerifierResult == 0) {
				    	String recvData = sv.getSignedMessageText();
				    	//복호화
						// deEnvelope ( 	String value - 암호화된값
						//						int encoding - 인코딩    0 = base64 / 1 = Hex
						//						String charset - 캐릭터셋  utf-8, euc-kr )
		        		String decData = enveloper.deEnvelope(recvData, encoding, charset);
		        		
		        		if(decData == null){
		        			LOG.debug("업체 투찰 금액 복호화에 실패하였습니다. "+partcptnEntrpsInfo.get("VEND_REG_NO"));
							trans.put("msg", "업체 투찰 금액 복호화에 실패하였습니다.");
							trans.setResultCode("ERR");
							return trans;
		        		}
		        		parameterMap.put("P_TNDR_AMT", decData);
				    }else if(nVerifierResult == -10){
				    	System.out.println("인증서가 만료되었습니다.");
				    	trans.setResultCode("ERR");
				    	trans.put("code", sv.getLastErrorCode());
				    	trans.put("msg", sv.getLastErrorMsg());      
				    	return trans;
				    }else if(nVerifierResult == -20){
				    	System.out.println("인증서가 폐기되었습니다.");
				    	trans.setResultCode("ERR");
				    	trans.put("code", sv.getLastErrorCode());
				    	trans.put("msg", sv.getLastErrorMsg());
				    	return trans;
				    }else if(nVerifierResult == -30){ //OCSP. CRL 오류
				    	System.out.println("서명문에 문제(검증)가 있습니다.");
				    	trans.setResultCode("ERR");
				    	trans.put("code", sv.getLastErrorCode());
				    	trans.put("msg", sv.getLastErrorMsg());
				    	return trans;
				    }else { // VestSign 라이브러리 에러
				    	System.out.println("서명문에 문제(라이브러리)가 있습니다.");
				    	trans.setResultCode("ERR");
				    	trans.put("code", sv.getLastErrorCode());
				    	trans.put("msg", sv.getLastErrorMsg());
				    	return trans;
				    }

					// 복호화 투찰금액 저장
					iproEbidOpengManageDao.updateDecdBddprAmountStre(parameterMap);

					// 입찰구분이 [구매] 이거나 [용역],[공사]가 기자재 포함 일 경우 투찰 품목 단가 복호화
					//if("0".equals(bidInfoDetail.getString("CONT_SECD")) || ("2".equals(bidInfoDetail.getString("CONT_SECD")) && !"".equals(bidInfoDetail.getString("IFC_ID")))){
					
					if("0".equals(bidInfoDetail.getString("CONT_SECD")) || "1".equals(bidInfoDetail.getString("CONT_SECD")) || "2".equals(bidInfoDetail.getString("CONT_SECD")) || "5".equals(bidInfoDetail.getString("CONT_SECD")) ){
						// 투찰 품목 조회
						List<FwkDataEntity> bddprPrdlsList =  iproEbidOpengManageDao.selectBddprPrdlstListInqire(parameterMap);
						
						if(bddprPrdlsList == null){
							// 투찰 품목 없을 시 부적격
							LOG.debug("투찰 품목이 없습니다.");
							trans.put("msg", "투찰 품목이 없습니다.");
							trans.setResultCode("ERR");
							return trans;
						}else{
							for(int j=0; j < bddprPrdlsList.size(); j++){
								FwkDataEntity bddprPrdls = bddprPrdlsList.get(j);
								
								String bidUntpcEncptValue = bddprPrdls.get("ITEM_UNIT_ENC").toString(); // 입찰 단가 암호화 값
								String itemSn = bddprPrdls.get("ITEM_SN").toString();// 물품번호
								
								//서명검증
							    SignVerifier svItme = new SignVerifier(	bidUntpcEncptValue 		/*전자서명문*/
							            											,cert_verify							/*유효성검증 방법*/
									            									,encoding);							/*서명문 인코딩 룰
												            																		base64 = 0
												            																		hex = 1          */ 
							    svItme.verify();
								
							    // 0 : 성공
							    // -10 : 만료 
							    // -20 : 폐기
							    // -30 : 기타오류
						        
							    int nVerifierResultItme = svItme.getLastErrorCode();
							    
							    if(nVerifierResultItme == 0) {
							    	String recvDataItem = svItme.getSignedMessageText();
							    	//복호화
									// deEnvelope ( 	String value - 암호화된값
									//						int encoding - 인코딩    0 = base64 / 1 = Hex
									//						String charset - 캐릭터셋  utf-8, euc-kr )
					        		String decDataItme = enveloper.deEnvelope(recvDataItem, encoding, charset);
					        		
					        		if(decDataItme == null){
					        			LOG.debug("품목 단가 복호화에 실패하였습니다.");
										trans.put("msg", "품목 단가 복호화에 실패하였습니다.");
										trans.setResultCode("ERR");
										return trans;
					        		}
					        		parameterMap.put("P_ITEM_UNIT_AMT", decDataItme);
							    	parameterMap.put("P_ITEM_SN", itemSn);
							    }else if(nVerifierResultItme == -10){
							    	System.out.println("인증서가 만료되었습니다.");
							    	trans.setResultCode("ERR");
							    	trans.put("code", sv.getLastErrorCode());
							    	trans.put("msg", sv.getLastErrorMsg());      
							    	return trans;
							    }else if(nVerifierResultItme == -20){
							    	System.out.println("인증서가 폐기되었습니다.");
							    	trans.setResultCode("ERR");
							    	trans.put("code", sv.getLastErrorCode());
							    	trans.put("msg", sv.getLastErrorMsg());
							    	return trans;
							    }else if(nVerifierResultItme == -30){ //OCSP. CRL 오류
							    	System.out.println("서명문에 문제(검증)가 있습니다.");
							    	trans.setResultCode("ERR");
							    	trans.put("code", sv.getLastErrorCode());
							    	trans.put("msg", sv.getLastErrorMsg());
							    	return trans;
							    }else { // VestSign 라이브러리 에러
							    	System.out.println("서명문에 문제(라이브러리)가 있습니다.");
							    	trans.setResultCode("ERR");
							    	trans.put("code", sv.getLastErrorCode());
							    	trans.put("msg", sv.getLastErrorMsg());
							    	return trans;
							    }
								
								// 복호화 품목 단가금액 저장
								iproEbidOpengManageDao.updateDecdPrdlstUntpcAmountStre(parameterMap);
							}
						}
					}
				}
				
				// 투찰내역서  복호화
				if("Y".equals(bidInfoDetail.getString("PRCDC_SBMT_YN"))){ // 가격서 제출 여부
					/****** 업체 투찰 내역서 파일 복호화 ******/
					parameterMap.put("P_BID_SBMT_FSCD", "DO04");
					parameterMap.put("P_BID_TPI_SECD", "OP03");
					FwkDataEntity entAtchDoc = iproEbidPblancDao.selectEntrpsAtchDoc(parameterMap);
					String FILE_GRP_NO = "";
					if(entAtchDoc !=null){
						FILE_GRP_NO = entAtchDoc.getString("FILE_GRP_NO");
						parameterMap.put("P_FILE_GRP_NO", FILE_GRP_NO);
						List<FwkDataEntity> fileList = comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap);
						if(fileList.size() > 0){
						//if(fileList != null){
							
							FwkDataEntity file = fileList.get(0);
							String dir = file.getString("FILE_LCTN");
							String bidFileSysFileName = file.getString("SV_FILE_NM");
							
							try {
								File bidSendFile= new File(dir+File.separator+bidFileSysFileName);
								byte[] bidSendFileByte = FileUtils.readFileToByteArray(bidSendFile);
								byte[] bidFileStr = enveloper.deEnvelope(bidSendFileByte);
								if(bidFileStr == null){
									//LOG.debug("업체 투찰 내역서 파일 복호화에 실패하였습니다.");
									//trans.put("msg", "업체 투찰 내역서 파일 복호화에 실패하였습니다.");
									//trans.setResultCode("ERR");
									//return trans;
								}
								//FileUtils.writeByteArrayToFile(bidSendFile, bidFileStr); 
							} catch (Exception e) {
								LOG.debug("업체 투찰 내역서 파일 복호화에 실패하였습니다.");
								trans.put("msg", "업체 투찰 내역서 파일 복호화에 실패하였습니다.");
								trans.setResultCode("ERR");
								e.printStackTrace();
								return trans;
							}
						}
					}
					/****** 업체 투찰 내역서 파일 복호화 END ******/
				}
			}
		}
		
		/****************************
		 *  복호화 끝난 후 개찰 처리
		 ****************************/
		// 예가 다시 조회
		prdprcInfo = iproEbidPrdprcManageDao.selectPrdprcInfoInqire(parameterMap);
		
		// 투찰 업체 다시 조회
		parameterMap.put("reload", "N");
		partcptnEntrpsInfoList = iproEbidOpengManageDao.selectPartcptnEntrpsInfoInqire(parameterMap);
		
		/***********************************************
		 *  낙찰방법이 [	협상에 의한 계약 : 40]일 경우
		 *  1. 기술+ 가격 점수가 높은 순으로 순위
		 *  2. 가격 점수는 협상의 계약일 경우에만 입력
		 *  
		 *  낙찰방법이 [적격심사 : 31]일 경우
		 *  1. 투찰율이 낙찰하한율보다 높은 업체 대상 최저가 기준으로 순위
		 *  2. 투찰율은 소수점 4째자리에서 반올림
		 *  3. 낙찰하한율보다 낮은 업체는 순위가 없다.
		 *  
		 *  낙찰방법이 [최저가 : 10]일 경우
		 *  1. 최저가로 순위
		 ***********************************************/
//		HashSet<String> vendRegNoSet = new HashSet<String>();
//		HashSet<String> tndrAmtSet = new HashSet<String>();
		
		if(partcptnEntrpsInfoList != null){
			for(int i=0; i < partcptnEntrpsInfoList.size(); i++){
				FwkDataEntity partcptnEntrpsInfo = partcptnEntrpsInfoList.get(i);
				
				if("OP04".equals(partcptnEntrpsInfo.getString("BID_VEND_PSCD"))){
					
					parameterMap.put("P_VEND_REG_NO", partcptnEntrpsInfo.get("VEND_REG_NO")); // 업체등록번호
					// 협상에 의한 계약 일때 가격점수 입력
					if("40".equals(bidInfoDetail.get("SBID_MTCD"))){
						String pcScore = iproEbidOpengManageDao.selectPcScoreComput(parameterMap);
						parameterMap.put("P_PRCE_SCR", pcScore);
						iproEbidOpengManageDao.updatePcScoreUpdt(parameterMap);
					}
					
					// 업체 진행상태 변경
					parameterMap.put("P_BID_VEND_PSCD", "OP06");
					iproEbidOpengManageDao.updateEntrpsProgrsSttusUpdt(parameterMap);
					iproEbidOpengManageDao.insertEntrpsProgrsHistRegist(parameterMap);
				}
				
			}
		
		}
		
		parameterMap.put("P_SBID_MTCD", bidInfoDetail.get("SBID_MTCD"));
		iproEbidOpengManageDao.insertEntrpsOpengInfoRegist(parameterMap);
		
		//견적일 때 낙찰까지 처리
		/*
		if(bidInfoDetail.get("BID_MTCD").equals("EST")) {
			parameterMap.put("P_BID_VEND_PSCD","OP09");
			parameterMap.put("P_BID_PSCD","PF60");
			
			List<FwkDataEntity> list = iproEbidSucbidrSlctnDao.selectLwetScsbidPrearngerList(parameterMap);
			for (FwkDataEntity fde : list) {
				if(fde.get("OPNG_RNK") != null && Integer.parseInt(fde.get("OPNG_RNK").toString()) == 1) {
					parameterMap.put("P_VEND_REG_NO", fde.get("VEND_REG_NO"));
					parameterMap.put("P_TNDR_AMT", fde.get("TNDR_AMT"));
					//낙찰자정보 수정 
					iproEbidSucbidrSlctnDao.updateSucbidInfoUpdt(parameterMap);
					//업체참여진행상태 수정 
					iproEbidSucbidrSlctnDao.updateEntrpsPartcptnPrstUpdt(parameterMap);
					//업체 진행이력상태 등록 
					iproEbidSucbidrSlctnDao.insertEtrpsProgrsHistRegist(parameterMap);
					//입찰진행상태 수정 
					iproEbidSucbidrSlctnDao.updateBidPrstUpdt(parameterMap);		
					//입찰 진행이력상태 등록 
					iproEbidSucbidrSlctnDao.insertBidProgrsHistSttusRegist(parameterMap);					
				}
			}
		}else {
			// 공고 진행 상태 코드 수정
			parameterMap.put("P_BID_PSCD", "PF50");
			parameterMap.put("P_APPR_STCD", "");
			parameterMap.put("P_OPNG_YN", "Y");
			iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
			iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
			
			// 입찰진행이력 등록 (입찰공고 - 개찰)
			iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);			
		}
		*/
		
		// 공고 진행 상태 코드 수정
		parameterMap.put("P_BID_PSCD", "PF50");
		parameterMap.put("P_APPR_STCD", "");
		parameterMap.put("P_OPNG_YN", "Y");
		iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		// 입찰진행이력 등록 (입찰공고 - 개찰)
		iproEbidPlanDao.insertBidProgrsHistRegist(parameterMap);
		
		
		FwkDataEntity biMst = iproEbidPblancDao.selectBiMstDetail(parameterMap);	//입찰기본
		
		if(biMst.get("BID_MTCD").equals("BID") &&  biMst.get("G2B_NTFY_YN").equals("Y")) {
			try {
				
				// G2B 헤더정보조회
				//iproG2bDao.selectG2bHeaderInfoBid(parameterMap);
				
				// 개찰정보 G2B 연계
				FwkDataEntity fde = iproG2bDao.selectG2bCreateOpng(parameterMap);   // 기본정보
				List<FwkDataEntity> list = iproG2bDao.selectLwetScsbidPrearngerList(parameterMap);
				
//				CreateXML.CreateOpengXml(fde, list);
				
				LOG.debug("G2B Success ===================<");
				
			} catch (Exception e1) {
				LOG.debug("G2B Fail Msg Start===================<");
				LOG.debug(e1.toString());
				LOG.debug(">>> 나라장터연계에 실패하였습니다");
				LOG.debug("G2B Fail Msg End===================>");
			}
		}
		
		
		LOG.debug("개찰 완료");
		trans.setResultCode("SUCC");
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 업체 적격검토 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsProperExmntRegistForm
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject entrpsProperExmntRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
	
		// 업체입찰정보 조회
		FwkDataEntity dataEntity = iproEbidOpengManageDao.selectEntrpsBidInfoInqire(parameterMap);
		if(dataEntity != null) {
			parameterMap.put("P_BID_TPI_SECD", "OP06");
			parameterMap.put("P_BID_SBMT_FSCD", "DO06");
			//파일정보 조회
			FwkDataEntity fileFde = iproEbidPblancDao.selectEntrpsAtchDoc(parameterMap);
			if(fileFde != null && !fileFde.get("FILE_GRP_NO").equals("")) {
				dataEntity.put("FILE_GRP_NO", fileFde.get("FILE_GRP_NO"));
			}
		}
		trans.put(IproEbidOpengManageService.ENTRPS_BID_INFO_INQIRE, dataEntity);
		
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 적격검토 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsProperExmntRegist
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject entrpsProperExmntRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		iproEbidOpengManageDao.updateEntrpsProperExmntRegist(parameterMap);
		
		return trans;
	}
	
	 /**
     * description p7방식으로 암호화된 파일을 복호화 한다.
     *
     * @method name p7DecryptFile()
     * @param byteDecServerKmKey   byte[] 복호화시 사용
     * @param decServerKeyPassword String 복호화시 사용
     * @param saveFullPath         String 암(복)호화된 파일이(을) 저장된(할) 절대경로
     * @param encryptedFilename    String 암호화된 파일명
     * @param decryptedFilename    String 복호화된 파일명
     * @return boolean
     */
    private boolean p7DecryptFile(byte[] byteDecServerKmKey, String decServerKeyPassword, String saveFullPath, String encryptedFilename, String decryptedFilename) throws Exception {
    	//>>>>>인증서변경<<<<<
//		PKCS7Util p7util = new PKCS7Util();
//		String envPassword = CipherUtil.envDecrypt(decServerKeyPassword);
//		boolean bVerify = p7util.verifyFromFile(saveFullPath + encryptedFilename, saveFullPath + decryptedFilename, byteDecServerKmKey, decServerKeyPassword, 0);

//		if (!bVerify) {
            //System.out.println(p7util.getErrorMsg());
//        }

//        return bVerify;
    	return true;
    }

    /**
	 * <pre>
	 * 1. 개요 : 협력업체관리 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ccpyManageDetail
	 * @date : 2016. 02. 23.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 23.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject ccpyManageDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity3 = (FwkDataEntity) iproEbidOpengManageDao.selectCcpyManageDetail(parameterMap);
		
		/************ DB복호화 시작 *****************/
		
//		dataEntity3.put("VEND_DTL_ADDR", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity3.get("VEND_DTL_ADDR")));
//		dataEntity3.put("TEL_NO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity3.get("TEL_NO")));
//		dataEntity3.put("FAX_TEL_NO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity3.get("FAX_TEL_NO")));
		
		trans.put(IproEbidOpengManageService.CCPY_MANAGE_DETAIL ,dataEntity3);
		
		trans.put(IproEbidOpengManageService.ENTRPS_CDTL_LIST,
				iproEbidOpengManageDao.selectEntrpsCdtlList(parameterMap));
		trans.put(IproEbidOpengManageService.ENTRPS_STTUS_LIST,
				iproEbidOpengManageDao.selectEntrpsSttusList(parameterMap));
		trans.put(IproEbidOpengManageService.SUPLY_THNG_LIST,
				iproEbidOpengManageDao.selectSuplyThngList(parameterMap));
		
		
		/*trans.put(IproEbidOpengManageService.FCTRY_LIST,
				iproEbidOpengManageDao.selectFctryList(parameterMap));*/
		
		/*SDBCrypto crypto = null;
		
		crypto = new SDBCrypto();
		crypto = SDBCrypto.getInstanceDomain("devkoica","192.168.1.8", 9003);*/
		
		List<FwkDataEntity> dataEntity = (List<FwkDataEntity>) iproEbidOpengManageDao.selectFctryList(parameterMap);
		
		/************ DB복호화 시작 *****************/
//		for(int i = 0; i<dataEntity.size(); i++)
//		{
//			dataEntity.get(i).put("FAX_TEL_NO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get(i).get("FAX_TEL_NO")));
//			dataEntity.get(i).put("TEL_NO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get(i).get("TEL_NO")));
//		}
		/************ DB복호화 종료 *****************/
		
		trans.put(IproEbidOpengManageService.FCTRY_LIST ,dataEntity);
		
		trans.put(IproEbidOpengManageService.PRODCT_QLY_LIST,
				iproEbidOpengManageDao.selectProdctQlyList(parameterMap));
		trans.put(IproEbidOpengManageService.INDUTY_LIST,
				iproEbidOpengManageDao.selectIndutyList(parameterMap));
		
		
		/*trans.put(IproEbidOpengManageService.BID_AGENT_LIST,
				iproEbidOpengManageDao.selectBidAgentList(parameterMap));*/
		
		/*SDBCrypto crypto = null;
		
		crypto = new SDBCrypto();
		crypto = SDBCrypto.getInstanceDomain("devkoica","192.168.1.8", 9003);*/
		
		List<FwkDataEntity> dataEntity2 = (List<FwkDataEntity>) iproEbidOpengManageDao.selectBidAgentList(parameterMap);
		
		/*,TEL_NO<!-- 암호화 -->
		,CP_NO<!-- 암호화 -->
		,EMAL_ADDR<!-- 암호화 -->*/
		
		/************ DB복호화 시작 *****************/
		
		//System.err.println((String) dataEntity.get("CHARGER_EMAL_ADDR") + " :::::::  " +crypto.decrypt("DBSEC","TB_KEY","ARIA", (String)dataEntity.get("CHARGER_EMAL_ADDR")));
		
//		for(int i = 0; i<dataEntity2.size(); i++)
//		{
//			dataEntity2.get(i).put("TEL_NO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity2.get(i).get	("TEL_NO")));
//			dataEntity2.get(i).put("CP_NO", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity2.get(i).get("CP_NO")));
//			dataEntity2.get(i).put("EMAL_ADDR", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity2.get(i).get("EMAL_ADDR")));
//			
//		}
		
		
		/************ DB복호화 종료 *****************/
		
		trans.put(IproEbidOpengManageService.BID_AGENT_LIST ,dataEntity2);
		
		trans.put(IproEbidOpengManageService.FNGPRT_SCRTY_TKN_LIST,
				iproEbidOpengManageDao.selectFngprtScrtyTknList(parameterMap));
		
		List<FwkDataEntity> entrpsRprsntvList =  iproEbidOpengManageDao.selectEntrpsRprsntvList(parameterMap);
		
		/************ DB복호화 시작 *****************/
//		if(entrpsRprsntvList != null){
//			for(int i = 0; i<entrpsRprsntvList.size(); i++)
//			{
//				entrpsRprsntvList.get(i).put("RPRS_EMAL", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) entrpsRprsntvList.get(i).get("RPRS_EMAL")));
//			}
//		}
		/************ DB복호화 종료 *****************/
		trans.put(IproEbidOpengManageService.ENTRPS_RPRSNTV_LIST ,entrpsRprsntvList);
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기술점수 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.impl.IproEbidOpengManageServiceImpl.java
	 * @Method : tchnScrRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 3. 
	 * @param parameterMap
	 */
	public void tchnScrRegist(final FwkParameterMap parameterMap){
		BigDecimal tchnScrRt = new BigDecimal(parameterMap.getInt("P_TCHN_SCR_RT")).divide(new BigDecimal(10), 1 , BigDecimal.ROUND_HALF_UP);
		Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
		if(P_VEND_REG_NO instanceof String){
			if("".equals(parameterMap.getString("P_ESTM_SCR"))){
				parameterMap.put("P_ESTM_SCR", 
						new BigDecimal(parameterMap.getString("P_ESTM_SCR_BASE")).multiply(tchnScrRt).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			iproEbidOpengManageDao.updateTchnScrRegist(parameterMap);
		}else if(P_VEND_REG_NO instanceof String[]){
			String[] P_VEND_REG_NO_LIST = (String[])P_VEND_REG_NO;
			String[] P_TCHN_ESTM_SCR_LIST = (String[])parameterMap.get("P_ESTM_SCR");
			String[] P_ESTM_SCR_BASE_LIST = (String[])parameterMap.get("P_ESTM_SCR_BASE");
			for (int i = 0; i < P_VEND_REG_NO_LIST.length; i++) {
				parameterMap.put("P_VEND_REG_NO", P_VEND_REG_NO_LIST[i]);
				if(!"X".equals(P_TCHN_ESTM_SCR_LIST[i])){
					if("".equals(P_TCHN_ESTM_SCR_LIST[i])){
						parameterMap.put("P_ESTM_SCR", 
								new BigDecimal(P_ESTM_SCR_BASE_LIST[i]).multiply(tchnScrRt).setScale(2, BigDecimal.ROUND_HALF_UP));
					}else{
						parameterMap.put("P_ESTM_SCR", P_TCHN_ESTM_SCR_LIST[i]);
					}
					iproEbidOpengManageDao.updateTchnScrRegist(parameterMap);
				}
			}
		}else if(P_VEND_REG_NO instanceof ArrayList){
			ArrayList<String> P_VEND_REG_NO_LIST = (ArrayList)P_VEND_REG_NO;
			ArrayList<String> P_TCHN_ESTM_SCR_LIST = (ArrayList)parameterMap.get("P_ESTM_SCR");
			ArrayList<String> P_ESTM_SCR_BASE_LIST = (ArrayList)parameterMap.get("P_ESTM_SCR_BASE");
			for (int i = 0; i < P_VEND_REG_NO_LIST.size(); i++) {
				parameterMap.put("P_VEND_REG_NO", P_VEND_REG_NO_LIST.get(i));
				if(!"X".equals(P_TCHN_ESTM_SCR_LIST.get(i))){
					if("".equals(P_TCHN_ESTM_SCR_LIST.get(i))){
						parameterMap.put("P_ESTM_SCR", 
								new BigDecimal(P_ESTM_SCR_BASE_LIST.get(i)).multiply(tchnScrRt).setScale(2, BigDecimal.ROUND_HALF_UP));
					}else{
						parameterMap.put("P_ESTM_SCR", P_TCHN_ESTM_SCR_LIST.get(i));
					}
					iproEbidOpengManageDao.updateTchnScrRegist(parameterMap);
				}
			}
		}
	}

	@Override
	public FwkTransferObject ebidApprSendEval(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap htmlMap = new FwkParameterMap();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		// 계약수정
		parameterMap.put("P_APPR_STCD", "NEW");	//결재진행상태코드	
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		parameterMap.put(IproEbidPlanService.BID_PLAN_DETAIL, biMst);
		
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		parameterMap.put(IproEbidPartcptSttusService.BID_PARTCPT_ENTRPS_LIST ,iproEbidPartcptSttusDao.selectBidPartcptEntrpsList(parameterMap));
		
		/*
		 *	전자결재 진행 
		 */
		//Form Html 양식 불러오기
		parameterMap.put("P_FRM_ID", "ERP_REQ_EVAL_PROP_EDIT");
		FwkDataEntity applHtmlMap = iproCommDefaultDao.selectApplFormHtml(parameterMap);
		parameterMap.put("P_APPL_HTML", applHtmlMap.get("HTML_CNTN"));
		
		//결재문서 매핑
		//htmlMap= CmmnUtil.ebidApprovalHtmlCreate(parameterMap);
		
		//LEGACY 테이블 저장
		parameterMap.put("P_PREFIX_STR", "EBID_ANNC_EVAL_");
		parameterMap.put("P_HTML_DATA1", htmlMap.get("APPL_HTML_END"));
		parameterMap.put("P_DRAFT_SABUN", user.getString("SABUN"));						//사번 : V_SSO_USER 
		parameterMap.put("P_DRAFT_DEPT", user.getString("DEPT_NO"));			//부서번호	: V_SSO_USER LEGACY_CODE
		parameterMap.put("P_TITLE", biMst.getString("BID_NM")+ " 제안서평가요청");			//NULL 안됨
		parameterMap.put("P_MAIN_TITLE", biMst.getString("BID_NM")+ " 제안서평가요청");	//NULL 안됨
		iproCommDefaultDao.insertIfLegacyMst(parameterMap);
		
		parameterMap.put("P_DOC_NO", biMst.getString("DOC_NO"));							// 문서번호
		FwkDataEntity ifLegacyMst = iproCommDefaultDao.selectIfLegacyMst(parameterMap);
		if(ifLegacyMst != null){
			parameterMap.put("P_APP_ID", ifLegacyMst.getString("APP_ID"));				// APP_ID
			iproCommDefaultDao.insertIfLegacyDoc(parameterMap);
		}
		
		//연동정보 테이블 저장
		parameterMap.put("P_BID_APPR_TSCD", "E");
		iproEbidPlanDao.insertBiApprCntc(parameterMap);
		
		trans.put("P_INTERFACE_ID", parameterMap.get("P_INTERFACE_ID"));
		trans.put("P_STATUS", "NEW");
		trans.put("P_DRAFT_SABUN", user.getString("SABUN"));
		
		return trans;
	}

	@Override
	public FwkTransferObject estmElcdRegist(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
		Object P_ESTM_ELCD = parameterMap.get("P_ESTM_ELCD");
		if(P_VEND_REG_NO instanceof String){
			iproEbidOpengManageDao.updateEstmElcdRegist(parameterMap);
		}else if(P_VEND_REG_NO instanceof String[]){
			String[] P_VEND_REG_NO_LIST = (String[])P_VEND_REG_NO;
			String[] P_ESTM_ELCD_LIST = (String[])parameterMap.get("P_ESTM_ELCD");
			for (int i = 0; i < P_VEND_REG_NO_LIST.length; i++) {
				if(!"X".equals(P_ESTM_ELCD_LIST[i])){
					parameterMap.put("P_VEND_REG_NO", P_VEND_REG_NO_LIST[i]);
					parameterMap.put("P_ESTM_ELCD", P_ESTM_ELCD_LIST[i]);
					iproEbidOpengManageDao.updateEstmElcdRegist(parameterMap);
				}
			}
		}else if(P_VEND_REG_NO instanceof ArrayList){
			ArrayList<String> P_VEND_REG_NO_LIST = (ArrayList)P_VEND_REG_NO;
			ArrayList<String> P_ESTM_ELCD_LIST = (ArrayList)parameterMap.get("P_ESTM_ELCD");
			for (int i = 0; i < P_VEND_REG_NO_LIST.size(); i++) {
				if(!"X".equals(P_ESTM_ELCD_LIST.get(i))){
					parameterMap.put("P_VEND_REG_NO", P_VEND_REG_NO_LIST.get(i));
					parameterMap.put("P_ESTM_ELCD", P_ESTM_ELCD_LIST.get(i));
					iproEbidOpengManageDao.updateEstmElcdRegist(parameterMap);
				}
			}
		}
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 개찰 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.impl.IproEbidOpengManageServiceImpl.java
	 * @Method : datpOpengInfoRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject datpOpengInfoRegist(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 입찰정보상세
		FwkDataEntity bidInfoDetail =	iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		/***** 개찰 시간 체크 *****/
		if(Long.parseLong(bidInfoDetail.get("OPNG_DT").toString()) >  Long.parseLong(FwkDateUtil.getCurrentDateTimeAsString())){ 
			LOG.debug("개찰예정시간 이후 개찰이 가능합니다.");
			trans.put("msg", "개찰예정시간 이후 개찰이 가능합니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		/***** 입찰 진행상태 체크 *****/
		if(!"PF20".equals(bidInfoDetail.get("BID_PSCD")) && !"PF30".equals(bidInfoDetail.get("BID_PSCD"))){
			LOG.debug("입찰이 현재 공고중이 아닙니다.");
			trans.put("msg", "입찰이 현재 공고중이 아닙니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		/******************
		 * 참여 업체 정보 조회
		 * 1. 포기업체 제외
		 * 2. 입찰부적격 업체 제외
		 * 3. 입찰서 제출 업체
		 ******************/
		int datpCnt = iproEbidOpengManageDao.selectDatpCnt(parameterMap);
		
		List<FwkDataEntity> datpList = iproEbidOpengManageDao.selectDatpList(parameterMap);
		
		
		/*************************************************
		 *  참여 업체 체크
		 *  1. 수의 업체는 업체 투찰이 한건도 없을 시 유찰 처리
		 *  2. 일반, 경쟁, 지명은 투찰 업체가 2군데 미만 일 경우 유찰 처리
		 *     - 부적격인 업체는 투찰업체에서 제외
		 *************************************************/
		// 10000 일반경쟁 , 10001 지명경쟁, 10002 제한경쟁, 10003 수의계약
		if("Y".equals(bidInfoDetail.getString("AUTO_FBID_YN"))){ // 자동유찰여부
			if("10003".equals(bidInfoDetail.get("CONT_MTCD")) || "10006".equals(bidInfoDetail.get("CONT_MTCD"))){
				if(datpCnt < 1){
					
					// 유찰 처리
					
					// 공고 진행상태 코드 수정 
					parameterMap.put("P_BID_PSCD", "PF99");
					iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
					iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
					
					//throw new ();
					LOG.debug("업체 투찰 정보가 존재하지 않습니다.");
					trans.put("msg", "업체 투찰 정보가 존재하지 않습니다.");
					trans.setResultCode("FBI");
					return trans;
				}
			}else{
				if(datpCnt < 2){
					
					// 유찰 처리
					
					// 공고 진행상태 코드 수정 
					parameterMap.put("P_BID_PSCD", "PF99");
					iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
					iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
					
					//throw new ();
					LOG.debug("참가 업체가 2개 미만입니다.");
					trans.put("msg", "참가 업체가 2개 미만입니다.");
					trans.setResultCode("FBI");
					return trans;
				}
			}
		}
		
		/****** 업체 투찰 금액 복호화 ******/
		if(datpList != null){
			parameterMap.put("P_BID_VEND_PSCD", "OP06");
			for(int i=0; i < datpList.size(); i++){
				FwkDataEntity datp = datpList.get(i);
				
				// 투찰 업체 복호화
				try {
					//복호화
					String key = parameterMap.getString("P_ANNC_NO")+parameterMap.getString("P_ANNC_NGR")+"EBS"+parameterMap.getString("P_ROUND_NO");
					String iv = key.substring(0,16);
				    byte[] keyData = key.getBytes();
				    SecretKey secureKey = new SecretKeySpec(keyData, "AES");
					
					Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
					c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes("UTF-8")));
					
					byte[] byteStr = Base64.decodeBase64(datp.getString("VEND_REG_NO_ENC").getBytes());
					
					parameterMap.put("P_VEND_REG_NO", new String(c.doFinal(byteStr),"UTF-8"));
					
				} catch (Exception e) {
					LOG.debug("업체등록번호 복호화에 실패하였습니다. "+datp.get("VEND_REG_NO_ENC"));
					trans.put("msg", "업체등록번호 복호화에 실패하였습니다.");
					trans.setResultCode("ERR");
					return trans;
				}
				
				parameterMap.put("P_TPI_SN", datp.getString("TPI_SN"));
				parameterMap.put("P_OPNG_RNK", i+1);
				
				// 업체등록번호 복호화 저장
				iproEbidOpengManageDao.updateDatpOpen(parameterMap);

			}
		}
		
		// 공고 진행 상태 코드 수정
		parameterMap.put("P_BID_PSCD", "PF50");
		parameterMap.put("P_APPR_STCD", "");
		parameterMap.put("P_OPNG_YN", "Y");
		iproEbidOpengManageDao.updatePblancPrstCdUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		LOG.debug("개찰 완료");
		trans.setResultCode("SUCC");
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 협상결과정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : negoRsltDetailInquire
	 * @date : 2019. 02. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 */
	@Override
	public FwkTransferObject negoRsltDetailInquire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//협상결과 정보
		trans.put(IproEbidOpengManageService.NEGO_RSLT_INFO, iproEbidOpengManageDao.negoRsltDetailInquire(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 협상결과업데인트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateNegoRsltInfo
	 * @date : 2019. 02. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- 
	 */
	@Override
	public void updateNegoRsltInfo(final FwkParameterMap parameterMap) {
		//협상결과 업데이트
		iproEbidOpengManageDao.updateNegoRsltInfo(parameterMap);
		
		//업체진행상태 변경
		parameterMap.put("P_BID_VEND_PSCD", "OP08");
		iproEbidSucbidrSlctnDao.updateEntrpsPartcptnPrstUpdt(parameterMap);
		//업체 진행이력 저장
		iproEbidSucbidrSlctnDao.insertEtrpsProgrsHistRegist(parameterMap);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공동업체 정보 보기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsAssoInfoINquire
	 * @date : 2019. 05. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 09.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return JSP 화면명- 
	 */	
	public FwkTransferObject selectEntrpsAssoInfoINquire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 진행중인 공고 상세
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);		
		trans.put(IproEbidOpengManageService.BID_PLAN_DETAIL, biMst);
		// 업체 정보 조회
		FwkDataEntity dataEntity = iproEbidOpengManageDao.selectEntrpsBidInfoInqire(parameterMap);
		trans.put(IproEbidOpengManageService.ENTRPS_BID_INFO_INQIRE, dataEntity);
		// 참여업체 마스터
		
		//공동수급업체 정보
		trans.put(IproEbidOpengManageService.ASSO_ENTRPS_LIST, iproEbidPblancDao.selectCopertnSdenList(parameterMap));
		
		return trans;
	}	
}