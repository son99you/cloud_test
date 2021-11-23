package com.eunwoosoft.ipro.vend.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 협력사관리 > 협력사평가 
 * <pre>
 *   IproVendEvalService.java
 * 
 * </pre>
 * @date : 2018. 02. 19
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 */
public interface IproVendEvalService {
	
	/**
	 * 평가기준
	 */
	static final String VEND_EVAL_STND_LIST= "vendEvalStndList";
	static final String VEND_EVAL_STND_LIST_TOTCNT= "vendEvalStndListTotCnt";
	static final String VEND_EVAL_STND_DETAIL= "vendEvalStndDetail";
	static final String VEND_EVAL_STND_DETAIL_LIST= "vendEvalStndDetailList";
	
	/**
	 * 세부평가기준
	 */
	static final String VEND_DTL_EVAL_STND_LIST= "vendDtlEvalStndList";
	static final String VEND_DTL_EVAL_STND_LIST_TOTCNT= "vendDtlEvalStndListTotCnt";
	static final String VEND_DTL_EVAL_STND_DETAIL= "vendDtlEvalStndDetail";
	static final String VEND_DTL_EVAL_STND_DETAIL_LIST= "vendDtlEvalStndDetailList";
	
	/**
	 * 유관부서평가기준
	 */
	static final String VEND_EVAL_DEPT_STND_LIST= "vendEvalDeptStndList";
	static final String VEND_EVAL_DEPT_STND_LIST_TOTCNT= "vendEvalDeptStndListTotCnt";
	static final String VEND_EVAL_DEPT_STND_DETAIL= "vendEvalDeptStndDetail";
	static final String VEND_EVAL_DEPT_STND_DETAIL_LIST= "vendEvalDeptStndDetailList";
	
	/**
	 * 평가종합관리 
	 */
	static final String VEND_EVAL_LIST= "vendEvalList";
	static final String VEND_EVAL_LIST_TOTCNT= "vendEvalListTotCnt";
	static final String VEND_EVAL_CODE1_LIST= "vendEvalCode1List";
	static final String VEND_EVAL_SG_CODE_LIST= "vendEvalSgCodeList";
	static final String VEND_EVAL_SG_CODE1_LIST= "vendEvalSgCode1List";
	static final String VEND_EVAL_MASTER_DETAIL= "vendEvalMasterDetail";
	static final String VEND_EVAL_VENDOR_LIST= "vendEvalVendorList";
	static final String VEND_EVAL_VENDOR_ADD_LIST= "vendEvalVendorAddList";
	static final String VEND_EVAL_VENDOR_DETAIL= "vendEvalVendorDetail";
	static final String VEND_EVAL_VENDOR_CODE_LIST= "vendEvalVendorCodeList";
	static final String VEND_EVAL_EV_CODE_LENGTH= "vendEvalEvCodeLength";
	static final String VEND_EVAL_EV_DYYYY_CODE3_LIST = "vendEvalEvYyyyCode3List";
	
	/**
	 * 유관부서평가
	 **/
	static final String VEND_EVAL_DEPT_LIST= "vendEvalDeptList";
	static final String VEND_EVAL_DEPT_LIST_TOTCNT= "vendEvalDeptListTotcnt";
	static final String VENDOR_DEPT_LIST = "vendorDeptList";
	static final String VEND_EVAL_VENDOR_DEPT_DETAIL = "vendEvalVendorDeptDetail";
	static final String VEND_EVAL_VENDOR_DEPT_CODE_LIST1 = "vendEvalVendorDeptCodeList1";
	static final String VEND_EVAL_VENDOR_DEPT_CODE_LIST2 = "vendEvalVendorDeptCodeList2";
	/**
	 * 평가결과
	 **/
	static final String VEND_EVAL_RESULT_LIST= "vendEvalResultList";
	static final String VEND_EVAL_RESULT_LIST_TOTCNT= "vendEvalResultListTotcnt";
	static final String VEND_EVAL_VENDOR_RSLT_LIST= "vendEvalVendorRsltList";
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */

	FwkTransferObject vendEvalStndRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 평가기준 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalStndListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가기준 및 평가항목 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectVendEvalStndDetail(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가분야 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalStndModify
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectVendEvalStndModify(final FwkParameterMap parameterMap)throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 평기기준 및 항목 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndDelete
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalStndDelete(final FwkParameterMap parameterMap)throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendDtlEvalStndListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 및 평가항목 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendDtlEvalStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectVendDtlEvalStndDetail(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 등록 및 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndModify
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendDtlEvalStndModify(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 세부평가기준 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndDelete
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptStndListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception;
	
	FwkTransferObject vendDtlEvalStndDelete(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptStndRegist(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 및 평가항목 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDeptStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectVendEvalDeptStndDetail(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptStndModify(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 및 항목 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndDelete
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptStndDelete(final FwkParameterMap parameterMap)throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalListInqireWithPgng(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 분야조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectTEvCode1List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectTEvCode1List(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalRegist
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalRegist(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalView
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectVendEvalView(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalModify
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalModify(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject selectVendEvalDetail(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가진행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalProceed
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalProceed(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 상반기현업평가완료
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalComplate
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalComplate(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 하반기평가업체추가
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalVenComp2Add
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalVenComp2Add(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptList(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서평가상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptDetail(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 평가수행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDetailEvalView(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가종합관리 평가수행저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDetailEvalSave
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDetailEvalSave(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서 업체상세 평가수행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptEvalView
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptEvalView(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 유관부서 업체상세 평가수행 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptInsert
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalDeptInsert(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가결과 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalResultList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalResultList(final FwkParameterMap parameterMap)throws Exception;
	/**
	 * <pre>
	 * 1. 개요 : 평가결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalResultDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일  		    최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception 
	 */
	FwkTransferObject vendEvalResultDetail(final FwkParameterMap parameterMap)throws Exception;
}
