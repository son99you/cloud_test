package com.eunwoosoft.ipro.info.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 기준정보 > 결재관리
 * <pre>
 * com.eunwoosoft.ipro.info.service 
 *    |_ IproInfoApprMngeService.java
 * 
 * </pre>
 * @date : 2018. 02. 26
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */
public interface IproInfoApprMngeService {
	
	public static final String T_MM_APPL_LIST = "tMmApplList";
	
	public static final String T_MM_APPL_DETAIL = "tMmApplDetail";
	
	public static final String T_MM_APPL_LIST_TOTCNT = "tMmApplListTotCnt";
	
	public static final String T_MM_APRP_LIST = "tMmAprpList";
	
	public static final String T_MM_APRP_LIST_TOTCNT = "tMmAprpListTotCnt";
	
	public static final String T_APPR_MST_LIST = "tApprMstList";
	
	public static final String T_APPR_MST_LIST_TOTCNT = "tApprMstListTotCnt";
	
	public static final String T_APPR_TGL = "tApprTgl";
	
	public static final String T_APPR_TGL_LIST = "tApprTglList";
	
	public static final String T_APPR_TGL_LIST_TOTCNT = "tApprTglListTotCnt";

	public static final String T_APPR_EMART = "tApprEmart";
	
	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_페이징 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : getMmApplMstList
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject getMmApplMstList(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재선등록 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : infoApprlineRegist
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprlineRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재선삭제
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : infoApprlineDelete
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprlineDelete(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_상세 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : getMmAprpList
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */	
	FwkTransferObject getMmAprpList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재선수정
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : infoApprlineUpdate
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprlineUpdate(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재대상 목록 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : getApplTargetList
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject getApplTargetList(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재대상_상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getApprTargetDocu
	 * @date : 2018. 03. 21.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 21.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */	
	FwkTransferObject getApprTargetDocu(final FwkParameterMap parameterMap);
	
	FwkTransferObject getBizrnoChk(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재대상_의견등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : infoApprRsnUpdate
	 * @date : 2018. 03. 23.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 23.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 * @throws Exception 
	 */
	void infoApprRsnUpdate(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 결재대상_상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getApprTarget
	 * @date : 2018. 03. 26.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 26.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */	
	public FwkTransferObject getApprTarget(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재완료내역 목록 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : getApplResultList
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject getApplResultList(final FwkParameterMap parameterMap);
}
