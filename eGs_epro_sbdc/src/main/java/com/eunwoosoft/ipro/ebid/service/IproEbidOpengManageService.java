package com.eunwoosoft.ipro.ebid.service; 

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 개찰관리 > 개찰관리 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidOpengManageService.java
 * 
 * </pre>
 * @date : 2015. 02. 27. 오후 07:03:53
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidOpengManageService {

	/**
	 * 개찰관리목록 Key
	 */
	static final String OPENG_MANAGE_LIST = "opengManageList";
	
	/**
	 * 개찰관리목록 총건수
	 */
	static final String OPENG_MANAGE_LIST_TOTCNT = "opengManageListTotcnt";
	
	/**
	 * 개찰관리상세
	 */
	static final String BID_PLAN_DETAIL = "bidPlanDetail";
	
	/**
	 * 업체참여목록
	 */
	static final String BID_PARTCPT_ENTRPS_LIST = "bidPartcptEntrpsList";
	
	/**
	 * 업체 입찰 정보 조회
	 */
	static final String ENTRPS_BID_INFO_INQIRE = "entrpsBidInfoInqire";
	
	/**
	 * 협력업체관리 상세
	 */
	static final String CCPY_MANAGE_DETAIL = "ccpyManageDetail";
	
	/**
	 * 업체신용평가정보 목록
	 */
	static final String ENTRPS_CDTL_LIST = "entrpsCdtlList";
	
	/**
	 * 업체상태정보 목록
	 */
	static final String ENTRPS_STTUS_LIST = "entrpsSttusList";
	
	/**
	 * 공급물품정보 목록
	 */
	static final String SUPLY_THNG_LIST = "suplyThngList";
	
	/**
	 * 공장정보 목록
	 */
	static final String FCTRY_LIST = "fctryList";
	
	/**
	 * 제조물품정보 목록
	 */
	static final String PRODCT_QLY_LIST = "prodctQlyList";
	
	/**
	 * 업종정보 목록
	 */
	static final String INDUTY_LIST = "indutyList";
	
	/**
	 * 입찰대리인정보 목록
	 */
	static final String BID_AGENT_LIST = "bidAgentList";
	
	/**
	 * 지문보안토큰정보 목록
	 */
	static final String FNGPRT_SCRTY_TKN_LIST = "fngprtScrtyTknList";
	
	/**
	 * 대표자정보 목록
	 */
	static final String ENTRPS_RPRSNTV_LIST = "entrpsRprsntvList";
	
	/**
	 * 기술평가여부
	 */
	static final String TCHN_COMP_AT = "tchnCompAt";
	
	/**
	 * 예가정보
	 */
	static final String PRDPRC_INFO = "prdprcInfo";
	
	/**
	 * 협상정보
	 */
	static final String NEGO_RSLT_INFO = "negoRsltInfo";
	
	/**
	 * 협상정보
	 */
	static final String ASSO_ENTRPS_LIST = "assoEntrpsList";
	
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject opengManageListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : opengManageDetailInqire
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
	FwkTransferObject opengManageDetailInqire(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject opengInfoRegist(final FwkParameterMap parameterMap) ;
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject entrpsProperExmntRegistForm(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject entrpsProperExmntRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 협력업체관리 상세
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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject ccpyManageDetail(final FwkParameterMap parameterMap) ;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기술점수 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidOpengManageService.java
	 * @Method : tchnScrRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 3. 
	 * @param parameterMap
	 */
	void tchnScrRegist(final FwkParameterMap parameterMap) ; 
	
	FwkTransferObject ebidApprSendEval(final FwkParameterMap parameterMap) ; 
	
	FwkTransferObject estmElcdRegist(final FwkParameterMap parameterMap) ;
	
	FwkTransferObject datpOpengInfoRegist(final FwkParameterMap parameterMap) ;
	
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
	FwkTransferObject negoRsltDetailInquire(final FwkParameterMap parameterMap);
	
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
	void updateNegoRsltInfo(final FwkParameterMap parameterMap);

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
	FwkTransferObject selectEntrpsAssoInfoINquire(final FwkParameterMap parameterMap);
}
