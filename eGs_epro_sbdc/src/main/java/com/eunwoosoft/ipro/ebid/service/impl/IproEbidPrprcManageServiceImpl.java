package com.eunwoosoft.ipro.ebid.service.impl; 

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
import com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPrprcManageDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPrprcManageService;

/**
 * 예가관리 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidPrprcManageService")
public class IproEbidPrprcManageServiceImpl extends AbstractFwkService implements IproEbidPrprcManageService {
	
	@Resource(name="iproEbidPrprcManageDao")
	private IproEbidPrprcManageDao iproEbidPrprcManageDao;

	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommDefaultDao")
	private IproCommDefaultDao iproCommDefaultDao; 
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	
	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 검토 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcManageListInqireWithPgng
	 * @date : 2019. 02. 18.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 18.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject prprcManageListInqireWithPgng(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S"))){ 
			parameterMap.put("P_BIDC_SBMT_ENDT_STDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S"))){
			parameterMap.put("P_BIDC_SBMT_ENDT_ENDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		
		if(parameterMap.get("P_PRPDC_SBMT_ENDT_STDE_S") != null && !"".equals(parameterMap.get("P_PRPDC_SBMT_ENDT_STDE_S"))){ 
			parameterMap.put("P_PRPDC_SBMT_ENDT_STDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_PRPDC_SBMT_ENDT_STDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_PRPDC_SBMT_ENDT_ENDE_S") != null && !"".equals(parameterMap.get("P_PRPDC_SBMT_ENDT_ENDE_S"))){
			parameterMap.put("P_PRPDC_SBMT_ENDT_ENDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_PRPDC_SBMT_ENDT_ENDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproEbidPrprcManageService.PRPRC_MANAGE_LIST, iproEbidPrprcManageDao.selectPrprcManageListWithPgng(parameterMap));
		trans.put(IproEbidPrprcManageService.PRPRC_MANAGE_LIST_TOTCNT, iproEbidPrprcManageDao.selectPrprcManageListTotcnt(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcManageDetailInqire
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject prprcManageDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰상세
		iproEbidPrprcManageDao.selectBidPrprcInfoDetail(parameterMap);
		FwkDataEntity dataEntity = iproEbidPrprcManageDao.selectBidPrprcInfoDetail(parameterMap);
		trans.put(IproEbidPrprcManageService.BID_PRPRC_DETAIL, dataEntity);
		
		//제안서 제출 업체 목록
		List<FwkDataEntity> dataEntityList = null;
		parameterMap.put("P_BID_TPI_SECD", "OP05");
		dataEntityList = (List<FwkDataEntity>) iproEbidPrprcManageDao.selectBidPrprcEntrpsList(parameterMap);
		trans.put(IproEbidPrprcManageService.BID_PRPRC_ENTRPS_LIST ,dataEntityList);
		
		/* 제안서 평가 수에 따른 재입찰 플래그 */
		trans.put("NOT_ELCD_EXT_YN", "N");
		//2단계 경쟁의 경우
		//제안서 미평가 시 업체 존재 시 재입찰 및 유찰 불가
		if(dataEntity.get("SBID_MTCD").equals("34")) {
			int elgb = 0;
			int ntElgb = 0;
			boolean notFlag = true;
			if(dataEntityList != null && dataEntityList.size() > 0) {	//제출업체 있을 때
				for (FwkDataEntity fde : dataEntityList) {
					if(fde.get("ESTM_ELCD") == null || fde.get("ESTM_ELCD").equals("")) {
						notFlag = false;
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
		}else {			//나머지 경우
			
		}
		
		return trans;
	}	
	
}