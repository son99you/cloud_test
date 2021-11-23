package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 예가관리 > 예가관리 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidPrdprcManageService.java
 * 
 * </pre>
 * @date : 2015. 02. 23. 오후 07:03:53
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidPrdprcManageService {

	/**
	 * 예가관리목록 Key
	 */
	static final String PRDPRC_MANAGE_LIST = "prdprcManageList";
	
	/**
	 * 예가관리목록 총건수
	 */
	static final String PRDPRC_MANAGE_LIST_TOTCNT = "prdprcManageListTotcnt";
	
	/**
	 * 예가관리상세
	 */
	static final String BID_PLAN_DETAIL = "bidPlanDetail";
	
	/**
	 * 예가관리상세
	 */
	static final String COMPNO_PRDPRC_LIST = "compnoPrdprcList";
	
	/**
	 * 예가관리상세
	 */
	static final String PRDPRC_REGIST_INQIRE = "prdprcRegistInqire";
	
	/**
	 * 예가항목조회
	 */
	static final String PRDPRC_IEM_LIST = "prdprcIemList";
	
	/**
	 * 예가정보조회
	 */
	static final String PRDPRC_INFO_INQIRE = "prdprcInfoInqire";
	
	/**
	 * 예가상세조회
	 */
	static final String PRDPRC_DETAIL_LIST = "prdprcDetailList";

	/**
	 * <pre>
	 * 1. 개요 : 예가등록요청 목록조회_페이징
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
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcReqRegListInqireWithPgng(final FwkParameterMap parameterMap);
	
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
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcManageListInqireWithPgng(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcManageRegistForm(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcManageRegist(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcManageUpdtForm(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 수정
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcManageUpdt(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcManageDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가생성 등록
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcCreatRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prdprcRegist(final FwkParameterMap parameterMap);
	
	FwkTransferObject ebidApprSendPrice(final FwkParameterMap parameterMap);
	
	void rebach(final FwkParameterMap parameterMap);
	
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
	FwkTransferObject prdprcReject(final FwkParameterMap parameterMap);	
}
