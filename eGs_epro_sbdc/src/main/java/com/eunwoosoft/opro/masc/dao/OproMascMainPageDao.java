package com.eunwoosoft.opro.masc.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.opro.masc.dao 
 *    |_ OproMascMainPageDao.java
 * 
 * </pre>
 * @date : 2015. 3. 23. 오후 4:02:17
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
public interface OproMascMainPageDao {
	
	/**
	 * <pre>
	 * 1. 개요 : 공고 업무 카운트 조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectPblancJobCntInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 공고 업무 카운트 조회조건
	 * @return - 공고 업무 카운트 목록
	 */
	List<FwkDataEntity> selectPblancJobCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공고 전 업무 카운트 조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectPblancBefJobCntInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 공고 전 업무 카운트 조회조건
	 * @return - 공고 전 업무 카운트 목록
	 */
	List<FwkDataEntity> selectPblancBefJobCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰 후 업무 카운트 조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectOpengAfJobCntInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 개찰 후 업무 카운트 조회조건
	 * @return - 개찰 후 업무 카운트 목록
	 */
	List<FwkDataEntity> selectOpengAfJobCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 계약 업무 카운트 조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCntrctJobCntInqire
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 계약 업무 카운트 조회조건
	 * @return - 계약 업무 카운트 목록
	 */
	List<FwkDataEntity> selectCntrctJobCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 발주계획 카운트 조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoOrderPlanCntInqire
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 발주계획 카운트 조회조건
	 * @return - 부서별 발주계획 카운트 목록
	 */
	FwkDataEntity selectDeptAcctoOrderPlanCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 입찰공고 카운트 조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoBidPblancCntInqire
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 입찰공고 카운트 조회조건
	 * @return - 부서별 입찰공고 카운트 목록
	 */
	FwkDataEntity selectDeptAcctoBidPblancCntInqire(final FwkParameterMap paramterMap);
	
	
	/**
	 * <pre>
	 * 1. 개요 : 일반행정 카운트 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectGnrlAdministCntInqire
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 일반행정 카운트 조회조건
	 * @return - 일반행정 카운트 목록
	 */
	FwkDataEntity selectGnrlAdministCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 기타공고 카운트 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectEtcPblancCntInqire
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 기타공고 카운트 조회조건
	 * @return - 기타공고 카운트 목록
	 */
	FwkDataEntity selectEtcPblancCntInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoPblancList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별공고 목록 조회조건
	 * @return - 부서별공고 목록
	 */
	List<FwkDataEntity> selectDeptAcctoPblancList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 발주계획 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoPblancList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 발주계획 목록 조회조건
	 * @return - 부서별 발주계획 목록
	 */
	List<FwkDataEntity> selectDeptAcctoOrderPlanList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 일반 공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoEtcPblancList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 일반 공고 목록 조회조건
	 * @return - 부서별 일반 공고 목록
	 */
	List<FwkDataEntity> selectDeptAcctoGnrlPblancList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 일반 발주계획 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoEtcOrderPlanList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 일반 발주계획 목록 조회조건
	 * @return - 부서별 일반 발주계획 목록
	 */
	List<FwkDataEntity> selectDeptAcctoGnrlOrderPlanList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 기타 공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoEtcPblancList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 기타 공고 목록 조회조건
	 * @return - 부서별 기타 공고 목록
	 */
	List<FwkDataEntity> selectDeptAcctoEtcPblancList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별 기타 발주계획 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectDeptAcctoEtcOrderPlanList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 부서별 기타 발주계획 목록 조회조건
	 * @return - 부서별 기타 발주계획 목록
	 */
	List<FwkDataEntity> selectDeptAcctoEtcOrderPlanList(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 달력 사전공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrBeffatPblancList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 사전공고 목록 조회조건
	 * @return - 달력 사전공고 목록
	 */
	List<FwkDataEntity> selectCldrBeffatPblancList(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 달력 입찰공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrBidPblancList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 입찰공고 목록 조회조건
	 * @return - 달력 입찰공고 목록
	 */
	List<FwkDataEntity> selectCldrBidPblancList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 달력 입찰설명회 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrBeffatPblancList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 입찰설명회 목록 조회조건
	 * @return - 달력 입찰설명회 목록
	 */
	List<FwkDataEntity> selectCldrBidDcPeoList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 달력 참가마감 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrPareEndList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 참가마감 목록 조회조건
	 * @return - 달력 참가마감 목록
	 */
	List<FwkDataEntity> selectCldrPareEndList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 달력 입찰서제출 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrBidDocsuList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 입찰서제출 목록 조회조건
	 * @return - 달력 입찰서제출 목록
	 */
	List<FwkDataEntity> selectCldrBidDocsuList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 달력 개찰 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrOpengList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 개찰 목록 조회조건
	 * @return - 달력 개찰 목록
	 */
	List<FwkDataEntity> selectCldrOpengList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : TODOLIST
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectToDoList
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - TODOLIST 조회조건
	 * @return - TODOLIST
	 */
	List<FwkDataEntity> selectToDoList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 메인 공지 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectMainGnrlNoticeList
	 * @date : 2015. 5. 28.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 5. 28.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 메인 공지 목록 조회조건
	 * @return - 메인 공지 목록
	 */
	List<FwkDataEntity> selectMainNoticeList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : db시간
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDbTime
	 * @date : 2015. 8. 26.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 26.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	String selectDbTime(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 메인 입찰공고 TAB 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectMainPblancTabList
	 * @date : 2016. 02. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 메인 입찰공고 TAB 목록 조회조건
	 * @return - 메인 입찰공고 TAB 목록
	 */
	List<FwkDataEntity> selectMainPblancTabList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 메인 사전공고 TAB 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectMainBeffatPblancTabList
	 * @date : 2016. 02. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 메인 사전공고 TAB 목록 조회조건
	 * @return - 메인 사전공고 TAB 목록
	 */
	List<FwkDataEntity> selectMainBeffatPblancTabList(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 달력 기술평가 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectCldrTchqvlnList
	 * @date : 2016. 12. 13.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 12. 13.		KOICA 김경희		최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 달력 기술평가 목록 조회조건
	 * @return - 달력 기술평가 목록
	 */
	List<FwkDataEntity> selectCldrTchqvlnList(final FwkParameterMap parameterMap);
}
