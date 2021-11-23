package com.eunwoosoft.ipro.ebid.service.impl; 

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrdprcManageDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPrdprcManageService;
import com.yettiesoft.vestsign.external.Enveloper;

/**
 * 예가관리 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidPrdprcManageService")
public class IproEbidPrdprcManageServiceImpl extends AbstractFwkService implements IproEbidPrdprcManageService {
	
	@Resource(name="iproEbidPrdprcManageDao")
	private IproEbidPrdprcManageDao iproEbidPrdprcManageDao;

	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao; 
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가등록관리 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcReqRegListInqireWithPgng
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcReqRegListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S"))){ 
			parameterMap.put("P_BIDC_SBMT_ENDT_STDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S"))){
			parameterMap.put("P_BIDC_SBMT_ENDT_ENDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(IproEbidPrdprcManageService.PRDPRC_MANAGE_LIST, iproEbidPrdprcManageDao.selectPrdprcRegReqManageListWithPgng(parameterMap));
		trans.put(IproEbidPrdprcManageService.PRDPRC_MANAGE_LIST_TOTCNT, iproEbidPrdprcManageDao.selectPrdprcRegReqManageListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcManageListInqireWithPgng
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcManageListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S"))){ 
			parameterMap.put("P_BIDC_SBMT_ENDT_STDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S"))){
			parameterMap.put("P_BIDC_SBMT_ENDT_ENDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
    	FwkDataEntity user = parameterMap.getLoginResult();
    	if(user != null){
    		parameterMap.put("P_USR_ID", user.get("USR_ID"));
    	}
		
		trans.put(IproEbidPrdprcManageService.PRDPRC_MANAGE_LIST, iproEbidPrdprcManageDao.selectPrdprcManageListWithPgng(parameterMap));
		trans.put(IproEbidPrdprcManageService.PRDPRC_MANAGE_LIST_TOTCNT, iproEbidPrdprcManageDao.selectPrdprcManageListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcManageRegistForm
	 * @date : 2015. 06. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 06. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcManageRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 입찰정보상세
		FwkDataEntity bidInfo = iproEbidPrdprcManageDao.selectBidEstcInfoDetail(parameterMap);
		trans.put(IproEbidPrdprcManageService.BID_PLAN_DETAIL, bidInfo);
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcManageRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcManageRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 예가 정보 등록
		//iproEbidPrdprcManageDao.insertPrdprcInfoRegist(parameterMap);
		
		// 예가 정보 업데이트
		iproEbidPrdprcManageDao.updateBidBaseAmt(parameterMap);   // T_BI_MST UPDATE 
		iproEbidPrdprcManageDao.updatePrdprcInfoUpdt(parameterMap);   // T_BI_ESTC_MST UPDATE
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 수정 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcManageUpdtForm
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcManageUpdtForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 입찰정보상세
		trans.put(IproEbidPrdprcManageService.BID_PLAN_DETAIL, 
				iproEbidPlanDao.selectBidInfoDetail(parameterMap));

		// 예가 정보 조회
		trans.put(IproEbidPrdprcManageService.PRDPRC_INFO_INQIRE, 
				iproEbidPrdprcManageDao.selectPrdprcInfoInqire(parameterMap));
		
		FwkDataEntity fileDataEntity = (FwkDataEntity)trans.get(IproEbidPrdprcManageService.PRDPRC_INFO_INQIRE);
		
		if(fileDataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST, 
					comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcManageUpdt
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcManageUpdt(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "bid";
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_FILE", contextPath);
		
		if(fileParameterMap != null){
			List<Map<String, Object>> list = (List<Map<String, Object>>)fileParameterMap.get("list");
			List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
			
			Object P_FILE_DOC_NM =  parameterMap.get("P_FILE_DOC_NM");
			if(P_FILE_DOC_NM instanceof String) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", P_FILE_DOC_NM.toString());
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof String[]) {
				String[] fileDocNmArray = (String[])P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmArray[i]);
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof ArrayList) {
				ArrayList<String> fileDocNmList = (ArrayList<String>)P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
					newList.add(map);
				}
			}
			String atchFileGroupNo = ""; 
			if("".equals(parameterMap.getString("P_FILE_GRP_NO"))){
				atchFileGroupNo = fileParameterMap.getString("atchFileGroupNo");
			}else{
				atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");	
			}
			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("fileList", list);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
		}
		
		String delFileSn = parameterMap.getString("P_DEL_SN");
		if(!"".equals(delFileSn)){
			String[] snArray = delFileSn.split(",");
			List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < snArray.length; i++) {
				FwkParameterMap delMap = new FwkParameterMap();
				delMap.put("P_FILE_SN", snArray[i]);
				delList.add(delMap);
			}
			parameterMap.put("fileList", delList);
			comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
		}
		
		Object P_ORG_FILE_DOC_NM = parameterMap.get("P_ORG_FILE_DOC_NM");
		Object P_FILE_SN = parameterMap.get("P_FILE_SN");
		if (P_FILE_SN instanceof String) {
			parameterMap.put("P_FILE_DOC_NM", parameterMap.getString("P_ORG_FILE_DOC_NM"));
			comAtmaAtchFileDao.updateFileDocNm(parameterMap);
		}else if(P_FILE_SN instanceof String[]){
			String[] fileDocNmArray = (String[])P_ORG_FILE_DOC_NM;
			String[] fileSnArray = (String[])P_FILE_SN;
			for (int i = 0; i < fileSnArray.length; i++) {
				parameterMap.put("P_FILE_DOC_NM", fileDocNmArray[i]);
				parameterMap.put("P_FILE_SN", fileSnArray[i]);
				comAtmaAtchFileDao.updateFileDocNm(parameterMap);
			}
		}else if(P_FILE_SN instanceof ArrayList){
			ArrayList<String> fileDocNmList = (ArrayList<String>)P_ORG_FILE_DOC_NM;
			ArrayList<String> fileSnList = (ArrayList<String>)P_FILE_SN;
			for (int i = 0; i < fileSnList.size(); i++) {
				parameterMap.put("P_FILE_DOC_NM", fileDocNmList.get(i));
				parameterMap.put("P_FILE_SN", fileSnList.get(i));
				comAtmaAtchFileDao.updateFileDocNm(parameterMap);
			}
		}
		
		// 예가 정보 수정
		iproEbidPrdprcManageDao.updatePrdprcInfoUpdt(parameterMap);   // T_BI_ESTC_MST UPDATE
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcManageDetailInqire
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcManageDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 입찰정보상세
		FwkDataEntity bidInfo = iproEbidPrdprcManageDao.selectBidEstcInfoDetail(parameterMap);
		trans.put(IproEbidPrdprcManageService.BID_PLAN_DETAIL, bidInfo);
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가생성 등록(복수예가)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcCreatRegist
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcCreatRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		// 콤마제거
		parameterMap.put("P_BASE_ESTPC_AMT", FwkStringUtil.deleteComma(parameterMap.get("P_BASE_ESTPC_AMT").toString()));
		
		// 예가 생성 등록
		long bsisPreparPc = Long.parseLong(parameterMap.get("P_BASE_ESTPC_AMT").toString());	// 기초예정가격 
		int preparPcCo = Integer.parseInt(FwkMessageUtil.getMessage("EW.CON.DRPRREPR")); 		// 예비가격갯수
		if(parameterMap.get("P_ESTPC_SECD") != null && parameterMap.get("P_ESTPC_SECD").equals("180000")) {
			trans = compnoPrdprcCreatNdRegist(bsisPreparPc, preparPcCo, parameterMap);					// 복수예가 15개 등록 및 기초예가 등록	
		}else if(parameterMap.get("P_ESTPC_SECD") != null && parameterMap.get("P_ESTPC_SECD").equals("180001")) {
			trans = prdprcRegist(parameterMap);					// 단일예가	
		}
		
		// 예가 정보 업데이트
		parameterMap.put("P_ESTC_PSCD", "ES03");
		iproEbidPrdprcManageDao.updatePrdprcInfoUpdt(parameterMap);		
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록(단일예가)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcRegist
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject prdprcRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 콤마제거
		String plnprcAmount = FwkStringUtil.deleteComma(parameterMap.get("P_SCH_PRCE_AMT").toString());
		
		//서버 인증서로 암호화
//		EwSignBiz esb = new EwSignBiz();
//		byte[] encAmount = esb.envelopData(plnprcAmount.getBytes());
		// 예가 등록
//		parameterMap.put("P_SCH_PRCE_ENC", new String(encAmount));
		
		Enveloper enveloper = new Enveloper();
		String encAmount = enveloper.envelope(plnprcAmount, 0, "utf-8");  // 평문 , 인코딩( 0-base64, 1-hex) , 캐릭터셋 

		parameterMap.put("P_SCH_PRCE_ENC", encAmount);
		
		iproEbidPrdprcManageDao.updatePrdprcPrceEncUpdt(parameterMap);
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 생성 시 임의의 숫자 생성
	 * 2. 처리내용 : 최소값과 최대값 범위(최소값, 최대값 포함)의 임의의 숫자를 갯수만큼 생성한다.
	 * </pre>
	 * @Method Name : getRandomValues
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject compnoPrdprcCreatNdRegist(long bsisPreparPc, int preparPcCo, final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		Random ran = new Random();		
		if(preparPcCo < 0){
			preparPcCo = 0;
		}
		double [] randomValues = new double[preparPcCo];
		
		double min = 0;
		double max = 0;
		int count = 0;
		boolean isOverlap = false;
		String certErrMsg = "";
		
		try{
			//DB에서 생성할 예가의 범위를 조회한다.
			List<FwkDataEntity> compnoPrdprcStdrInfo = iproEbidPrdprcManageDao.selectcompnoPrdprcStdrInfoList(parameterMap);
			
			for(int i=0;i<compnoPrdprcStdrInfo.size();i++){
				
				FwkDataEntity prepscpoe = (FwkDataEntity) compnoPrdprcStdrInfo.get(i);

				min = Double.parseDouble(prepscpoe.get("SCTN_ST_VAL").toString())*1000;
		        max =  Double.parseDouble(prepscpoe.get("SCTN_END_VAL").toString())*1000;
				
				double currValue = 0;                      // 생성된 임의의 값을 임시로 저장할 공간
				randomValues[count] = 0;
				double dMaxValue = Double.MAX_VALUE; 
				
				if(dMaxValue >  ran.nextDouble()* (max - min + 1)){
					currValue = ran.nextDouble()* (max - min + 1);
					
					for (int k = 0; k < count; k++) {
						/*if (randomValues[k] == min + currValue) {   // 기존에 생성한 임의의 값과 중복되면 다시 생성
							isOverlap = true;
							break;
						}*/
					}
					if (!isOverlap) {   // 중복이 아니면 현재 생성된 임의의 값을 저장하고 다음 임의의 값 생성
						double randomRate = roundFormatDouble((currValue+ min),3)/1000;						
						randomValues[count] = roundFormatDouble(bsisPreparPc* (randomRate)/100,0);
						count++;
					}
				}else{
					LOG.debug("double 최대값 초과 예외");
				}
			}
			//랜덤으로 저장할 15개의 순번 생성
			double [] randomValues_org = randomValues;
			double [] randomValues_after = new double[preparPcCo];
			
			int selnum = 0;
			
			for(int i=0;i<preparPcCo;i++){
				selnum = ran.nextInt(preparPcCo);
				if(randomValues_org[selnum] == 0)
					i--;
				else{
					randomValues_after[i] = randomValues_org[selnum];
					randomValues_org[selnum] = 0;
				}
			}
			
			DecimalFormat formatter = new DecimalFormat("####################.######"); 

			// 복수예가 삭제
        	iproEbidPrdprcManageDao.deleteCompnoPrdprc(parameterMap);
			
            for (int i = 0; i < 15; i++) {
            	BigDecimal preparPcAmount = new BigDecimal(formatter.format(randomValues_after[i]));
            	
        		/** 인증서 **/
        		// 서버 인증서로 암호화
//            	EwSignBiz esb = new EwSignBiz();
//        		byte[] encAmount = esb.envelopData(preparPcAmount.toString().getBytes());
//            	parameterMap.put("P_PLR_ESTPC_NO", i+1);						// 복수예가번호
//            	parameterMap.put("P_PREP_PRCE_ENC", new String(encAmount));			// 예비가격암호화값
            	Enveloper enveloper = new Enveloper();
        		String encAmount = enveloper.envelope(preparPcAmount.toString(), 0, "utf-8");  // 평문 , 인코딩( 0-base64, 1-hex) , 캐릭터셋

        		parameterMap.put("P_PLR_ESTPC_NO", i+1);						// 복수예가번호
            	parameterMap.put("P_PREP_PRCE_ENC", encAmount);			// 예비가격암호화값
            	
            	// 복수예가 등록
            	
            	FwkDataEntity user = parameterMap.getLoginResult();
            	if(user == null){
            		parameterMap.put("USR_ID", "SYSTEM");
            		parameterMap.put("USR_NM", "시스템");
            		parameterMap.put("CONN_IP", "127.0.0.1");
            	}
            	
            	iproEbidPrdprcManageDao.insertPrdprcCreatRegist(parameterMap);
            }

		}catch(Exception e){
			LOG.debug(parameterMap.get("P_ANNC_NO")+"-"+parameterMap.get("P_ANNC_NGR")+" | 복수예가 생성시 오류 ");
			LOG.debug("ERROR :: " + e.getMessage());
			 StackTraceElement[] st =e.getStackTrace();
		        for (StackTraceElement element : st) {
		        	LOG.debug(element.toString());
		        }
		    LOG.debug("====================");
			trans.put("msg", "복수예가 생성시 오류");
			return trans;
		}
		
		return trans;

	}
	
	/**
	 * <pre>
	 * 1. 개요 : double형을 지정한 수만큼 반올림
	 * 2. 처리내용 : double형을 지정한 수만큼 반올림
	 * </pre>
	 * @Method Name : roundFormatDouble
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	public static double roundFormatDouble(double d1, int i1) {
		double d2 = Math.pow(10D, i1);
	      double d3 = Math.ceil(d1 * d2) /d2;
	      return d3;
	}

	@Override
	public FwkTransferObject ebidApprSendPrice(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkParameterMap htmlMap = new FwkParameterMap();
		
		FwkDataEntity user = parameterMap.getLoginResult();
		
		parameterMap.put("P_APPR_STCD", "NEW");	//결재진행상태코드	
		iproEbidPlanDao.updateBidSttusChangeUpdt(parameterMap);
		iproEbidPlanDao.updateBidRndSttusChangeUpdt(parameterMap);
		
		FwkDataEntity biMst = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		parameterMap.put(IproEbidPlanService.BID_PLAN_DETAIL, biMst);
		FwkDataEntity prdprc = iproEbidPrdprcManageDao.selectPrdprcInfoInqire(parameterMap);
		parameterMap.put(IproEbidPrdprcManageService.PRDPRC_INFO_INQIRE, prdprc);
		
		if(prdprc != null){
			parameterMap.put("P_FILE_GRP_NO", prdprc.getString("FILE_GRP_NO"));
			parameterMap.put(IproEbidPlanService.BID_ATCH_DOC_LIST, 
					comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		/*
		 *	전자결재 진행 
		 */
		//Form Html 양식 불러오기
		if("180001".equals(biMst.getString("ESTPC_SECD"))){ //단일예가
			parameterMap.put("P_FRM_ID", "ERP_PRICE_PLAN_EDIT");
		}else if("180000".equals(biMst.getString("ESTPC_SECD"))){ //복수예가
			parameterMap.put("P_FRM_ID", "ERP_PRICE_PLAN_PLURAL_EDIT");
		}
		FwkDataEntity applHtmlMap = iproCommDefaultDao.selectApplFormHtml(parameterMap);
		parameterMap.put("P_APPL_HTML", applHtmlMap.get("HTML_CNTN"));
		
		//결재문서 매핑
		//htmlMap= CmmnUtil.ebidApprovalHtmlCreate(parameterMap);
		
		//LEGACY 테이블 저장
		parameterMap.put("P_PREFIX_STR", "EBID_ANNC_PRICE_");
		parameterMap.put("P_HTML_DATA1", htmlMap.get("APPL_HTML_END"));
		parameterMap.put("P_DRAFT_SABUN", user.getString("SABUN"));						//사번 : V_SSO_USER 
		parameterMap.put("P_DRAFT_DEPT", user.getString("DEPT_NO"));			//부서번호	: V_SSO_USER LEGACY_CODE
		parameterMap.put("P_TITLE", biMst.getString("BID_NM")+ " 예가조서");			//NULL 안됨
		parameterMap.put("P_MAIN_TITLE", biMst.getString("BID_NM")+ " 예가조서");	//NULL 안됨
		iproCommDefaultDao.insertIfLegacyMst(parameterMap);
		
		parameterMap.put("P_BID_APPR_TSCD", "A");
		
		FwkDataEntity apprCntc = iproEbidPlanDao.selectBiApprCntc(parameterMap);
		int P_ANNC_NGR = parameterMap.getInt("P_ANNC_NGR");
		int i = P_ANNC_NGR;
		while (apprCntc == null){
			parameterMap.put("P_ANNC_NGR", i-1 );
			i -= 1;
			if(i == 0){
				apprCntc = new FwkSimpleDataEntity();
			}else{
				apprCntc = iproEbidPlanDao.selectBiApprCntc(parameterMap);
			}
		};
		parameterMap.put("P_ANNC_NGR", P_ANNC_NGR );
		parameterMap.put("P_DOC_NO", apprCntc.getString("APPR_CNTC_KEY"));							// 문서번호
		FwkDataEntity ifLegacyMst = iproCommDefaultDao.selectIfLegacyMst(parameterMap);
		if(ifLegacyMst != null){
			parameterMap.put("P_APP_ID", ifLegacyMst.getString("APP_ID"));				// APP_ID
			iproCommDefaultDao.insertIfLegacyDoc(parameterMap);
		}
		
		//연동정보 테이블 저장
		parameterMap.put("P_BID_APPR_TSCD", "D");
		iproEbidPlanDao.insertBiApprCntc(parameterMap);
		
		trans.put("P_INTERFACE_ID", parameterMap.get("P_INTERFACE_ID"));
		trans.put("P_STATUS", "NEW");
		trans.put("P_DRAFT_SABUN", user.getString("SABUN"));
		
		return trans;
	}

	@Override
	public void rebach(FwkParameterMap parameterMap) {
		List<FwkDataEntity>prdprcList = iproEbidPrdprcManageDao.selectCompnoPrdprcList(parameterMap);
		List<FwkDataEntity>newPrdprcList = new ArrayList<FwkDataEntity>();
		Random random = new Random();
		int size = prdprcList.size();
		for (int i = 0; i < size; i++) {
			int rInt = random.nextInt(prdprcList.size());
			newPrdprcList.add(prdprcList.get(rInt));
			prdprcList.remove(rInt);
		}
		
		iproEbidPrdprcManageDao.deleteCompnoPrdprc(parameterMap);
		for (int i = 0; i < newPrdprcList.size(); i++) {
			FwkDataEntity prdprc = newPrdprcList.get(i);
			parameterMap.put("P_PLR_ESTPC_NO", i+1);
			parameterMap.put("P_PREP_PRCE_ENC", prdprc.getString("PREP_PRCE_ENC"));
			iproEbidPrdprcManageDao.insertPrdprcCreatRegist(parameterMap);
		}
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 반려
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdprcReject
	 * @date : 2020. 03. 16.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 03. 16.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	public FwkTransferObject prdprcReject(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 예가 정보 업데이트
		iproEbidPrdprcManageDao.updatePrdprcInfoUpdt(parameterMap);   // T_BI_ESTC_MST UPDATE
		
		return trans;		
	}	
	
}