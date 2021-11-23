package com.eunwoosoft.ipro.ebid.service; 

import java.io.IOException;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.ebid.service
 * IproEbidPlanService.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
public interface IproEbidProconService {
	
	/**
	 * 입찰계획목록 Key
	 */
	static final String BID_PLAN_APRVL_LIST = "bidPlanAprvlList";
	
	/**
	 * 입찰계획목록 총건수
	 */
	static final String BID_PLAN_APRVL_LIST_TOTCNT = "bidPlanAprvlListTotcnt";
	
	/**
	 * 입찰계획상세
	 */
	static final String BID_PLAN_APRVL_DETAIL = "bidPlanAprvlDetail";
	
	/**
	 * 입찰품목 목록 조회
	 */
	static final String BID_PRDLS_LIST = "bidPrdlsList";
	
	/**
	 * 입찰지명업체 조회
	 */
	static final String BID_NMFPC_ENTRPS_LIST = "bidNmfpcEntrpsList";
	
	/**
	 * 입찰첨부문서 조회
	 */
	static final String BID_ATCH_DOC = "bidAtchDoc";
	
	/**
	 * 입찰첨부문서 목록 조회
	 */
	static final String BID_ATCH_DOC_LIST = "bidAtchDocList";
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidProconService.java
	 * @Method : bidPlanAprvlListInqireWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanAprvlListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidProconService.java
	 * @Method : bidPlanAprvlDetailInqire
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanAprvlDetailInqire(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 수정 폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidProconService.java
	 * @Method : bidPlanAprvlUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidPlanAprvlUpdtForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidProconService.java
	 * @Method : bidPlanAprvlUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void bidPlanAprvlUpdt(final FwkParameterMap parameterMap) throws Exception;
}
