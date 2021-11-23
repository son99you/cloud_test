package com.eunwoosoft.opro.ebid.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.ebid.service
 * OproEbidSttlmntService.java
 *
 * @Author : chanil_Hong
 * @Date   : 2018. 3. 20.
 *
 */
public interface OproEbidSttlmntService {
	
	/**
	 * 수의시담 목록 Key
	 */
	static final String VLTRN_PRVSTL_LIST = "vltrnPrvstlList";	
	
	/**
	 * 수의시담 목록 총건수
	 */
	static final String VLTRN_PRVSTL_LIST_TOTCNT = "vltrnPrvstlListTotcnt";
	
	/**
	 * 수의시담 상세 
	 */
	static final String VLTRN_PRVSTL_DETAIL =  "vltrnPrvstlDetail";
	
	/**
	 * 수의시담 진행 목록 조회
	 */
	static final String VLTRN_PRVSTL_PROGRS_LIST = "vltrnPrvstlProgrsList";
	
	/**
	 * 조달의뢰정보 상세
	 */
	static final String PRCURE_REQEST_INFO_DETAIL = "prcureReqestInfoDetail";
	
	/**
	 * 수의시담 협상금액 목록
	 */
	static final String VLTRN_PRVSTL_PLNPRC_AMOUNT_LIST = "vltrnPrvstlPlnprcAmountList";
	
	/**
	 * 수의시담 최종 금액
	 */
	static final String VLTRN_PRVSTL_LAST_AMOUNT  = "vltrnPrvstlLastAmount";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 나의수의시담목록 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.opro.ebid.service.OproEbidSttlmntService.java
	 * @Method : vltrnPrvstlListInqireWithPgng
	 * @author : chanil_Hong
	 * @date : 2018. 3. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlListInqireWithPgng(final FwkParameterMap parameterMap );
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlDetailInqire
	 * @date : 2018. 03. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject vltrnPrvstlDetailInqire(final FwkParameterMap parameterMap);
	
	
	
	
	
	

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세-시담진행 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlProgrsDetailInqire
	 * @date : 2018. 03. 19
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 19		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject vltrnPrvstlProgrsDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 내용 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlCnRegist
	 * @date : 2018. 03. 19
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 19		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject vltrnPrvstlCnRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 협상 금액 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlNtatAmountRegist
	 * @date : 2018. 03. 19
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 19		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject vltrnPrvstlNtatAmountRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 최종 금액 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlLastAmountRegist
	 * @date : 2015. 6. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlLastAmountRegist(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 전체 수의시담 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : allVltrnPrvstlListInqireWithPgng
	 * @date : 2018. 03. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject allVltrnPrvstlListInqireWithPgng(final FwkParameterMap parameterMap);
	
}
