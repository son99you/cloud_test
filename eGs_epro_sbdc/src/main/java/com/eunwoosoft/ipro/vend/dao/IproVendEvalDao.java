package com.eunwoosoft.ipro.vend.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 협력사관리 > 협력사평가
 * <pre>
 *   IproVendEvalDao.java
 * 
 * </pre>
 * @date : 2018. 02. 19
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 */
public interface IproVendEvalDao {
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 목록조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> vendEvalStndListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectVendEvalStndDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가항목 상세리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalStndDetailList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectVendEvalStndDetailList(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 목록 총건수 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int vendEvalStndListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void insertT_Ev_Code1(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Code2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void insertT_Ev_Code2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가기준 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateT_ev_code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void updateT_ev_code1(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가분야 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteT_ev_code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void deleteT_ev_code1(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가항목 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteT_ev_code2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void deleteT_ev_code2(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 세부평가기준 목록조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> vendDtlEvalStndListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 세부평가기준 목록 총건수 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendDtlEvalStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int vendDtlEvalStndListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 세부평가기준 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendDtlEvalStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectVendDtlEvalStndDetail(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 세부평가항목 상세리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendDtlEvalStndDetailList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectVendDtlEvalStndDetailList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 세부평가항목 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteT_Ev_Code3
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void deleteT_Ev_Code3(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 세부평가항목 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Code3
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void insertT_Ev_Code3(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 유관부서 목록조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> vendEvalDeptStndListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 유관부서 목록 총건수 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalDeptStndListTotcnt
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int vendEvalDeptStndListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 유관부서평가기준 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDeptStndDetail
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectVendEvalDeptStndDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 유관부서평가항목 상세리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendEvalDeptStndDetailList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectVendEvalDeptStndDetailList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가종합관리 목록조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> vendEvalListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가종합관리 목록 총건수 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vendEvalListTotcnt
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int vendEvalListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가종합관리 분야 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectT_Ev_Code1_List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectT_Ev_Code1_List(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가종합관리 마스터 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Master
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertT_Ev_Master(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 년도별 평가 등록 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Yyyy_Code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertT_Ev_Yyyy_Code1(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  년도별 평가 항목 저장 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Yyyy_Code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertT_Ev_Yyyy_Code2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  년도별 세부 평가 기준 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Yyyy_Code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertT_Ev_Yyyy_Code3(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  임시업체저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertT_Ev_Vendor_Temp
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertT_Ev_Vendor_Temp(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 평가종합관리 마스터 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectT_Ev_Master
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectT_Ev_Master(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : ev_master에 저장된 소싱그룹 가져오기 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSgT_Ev_Yyyy_Code1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectSgT_Ev_Yyyy_Code1(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : ev_master에 저장된 소싱그룹 전체 목록 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectT_Ev_Yyyy_Code1List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectT_Ev_Yyyy_Code1List(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : deleteEvYyyyCode1
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvYyyyCode1
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvYyyyCode1(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : deleteEvYyyyCode2
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvYyyyCode2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvYyyyCode2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : deleteEvYyyyCode3
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvYyyyCode3
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvYyyyCode3(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :deleteEvVendor
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvVendor
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvVendor(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : deleteEvVendorDept
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvVendorDept
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvVendorDept(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : deleteEvVendorTemp
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvVendorTemp
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvVendorTemp(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : deleteEvMaster
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEvMaster
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvMaster(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : selectT_ev_vendor_List
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectT_ev_vendor_List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectT_ev_vendor_List(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : insertEvVendorDept
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertEvVendorDept
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertEvVendorDept(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : insertEvVendorCode2
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertEvVendorCode2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertEvVendorCode2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : insertEvVendor
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertEvVendor
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertEvVendor(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : evalStateUpdate
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : evalStateUpdate
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void evalStateUpdate(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : getT_ev_vendor_add_List
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getT_ev_vendor_add_List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getT_ev_vendor_add_List(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :vendEvalDeptListInqireWithPgng
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :vendEvalDeptListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> vendEvalDeptListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :vendEvalDeptListTotcnt
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :vendEvalDeptListTotcnt
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int vendEvalDeptListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getEvMasterEvState
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getEvMasterEvState
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity getEvMasterEvState(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor_deptList
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getEvMasterEvState
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getT_ev_vendor_deptList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getT_ev_vendor
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity getT_ev_vendor(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor_codeList
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getT_ev_vendor_codeList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getT_ev_vendor_codeList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getEv_code_length
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getEv_code_length
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int getEv_code_length(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getEvYyyyCode3List
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getEvYyyyCode3List
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getEvYyyyCode3List(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :deleteEvVendorCode2
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :deleteEvVendorCode2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteEvVendorCode2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :uploadT_Ev_Vendor
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :uploadT_Ev_Vendor
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void uploadT_Ev_Vendor(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor_dept
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getT_ev_vendor_dept
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity getT_ev_vendor_dept(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor_dept_codeList
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getT_ev_vendor_dept_codeList
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getT_ev_vendor_dept_codeList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :evalDeptDeleteAll
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :evalDeptDeleteAll
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void evalDeptDeleteAll(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getEv_gubun1A
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getEv_gubun1A
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity getEv_gubun1A(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :evalDeptInsert
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :evalDeptInsert
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void evalDeptInsert(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :evalDeptInsert
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :evalDeptInsert
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	Double getT_ev_vendor_dept_code2Sum(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :deleteT_ev_vendor_evalCode2
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :deleteT_ev_vendor_evalCode2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteT_ev_vendor_evalCode2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :insertT_ev_vendor_evalCode2
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :insertT_ev_vendor_evalCode2
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertT_ev_vendor_evalCode2(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :uploadT_Ev_Vendor_Dept
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :uploadT_Ev_Vendor_Dept
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void uploadT_Ev_Vendor_Dept(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :evalListInqireWithPgng
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :evalListInqireWithPgng
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> evalListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :evalListTotcnt
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :evalListTotcnt
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int evalListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor_rslt_list
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getT_ev_vendor_rslt_list
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getT_ev_vendor_rslt_list(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :getT_ev_vendor_rslt_sub_list
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name :getT_ev_vendor_rslt_sub_list
	 * @date : 2018. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getT_ev_vendor_rslt_sub_list(final FwkParameterMap parameterMap);
}
