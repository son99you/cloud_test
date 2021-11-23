package com.eunwoosoft.opro.ebid.service.impl; 

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyNtatDspthDao;
import com.eunwoosoft.opro.ebid.service.OproEbidMyNtatDspthService;

/**
 * 나의 협상통보 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 03. 24.
 * @version : 1.0
 */
@Service("oproEbidMyNtatDspthService")
public class OproEbidMyNtatDspthServiceImpl extends AbstractFwkService implements OproEbidMyNtatDspthService {
	
	@Resource(name="oproEbidMyNtatDspthDao")
	private OproEbidMyNtatDspthDao oproEbidMyNtatDspthDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 적격심사 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnListInqireWithPgng
	 * @date : 2015. 03. 23.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject myNtatDspthListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		trans.put(OproEbidMyNtatDspthService.MY_NTAT_DSPTH_LIST, 
				oproEbidMyNtatDspthDao.selectMyNtatDspthListWithPgng(parameterMap));
		trans.put(OproEbidMyNtatDspthService.MY_NTAT_DSPTH_LIST_TOTCNT, 
				oproEbidMyNtatDspthDao.selectMyNtatDspthListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상통보 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthDetailInqire
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	
	@Override
	public FwkTransferObject myNtatDspthDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		trans.put(OproEbidMyNtatDspthService.MY_NTAT_DSPTH_DETAIL
				,oproEbidMyNtatDspthDao.selectMyNtatDspthDetail(parameterMap));
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상 통보 응답 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthRspnsRegist
	 * @date : 2015. 3. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 25.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see OproEbidMyNtatDspthService.OepElbiMyNtatDspthService.elbi.service.OepElbiMyNtatDspthService#myNtatDspthRspnsRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject myNtatDspthRspnsRegist(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_ENTRPS_REGIST_NO", user.get("LOGIN_ID")); // 세션 업체등록번호
		
		oproEbidMyNtatDspthDao.insertMyNtatDspthRspnsRegist(parameterMap);
		
		return trans;
	}
	
}