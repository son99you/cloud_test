package com.eunwoosoft.opro.masc.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.opro.masc.service 
 *    |_ OepMascMainPageService.java
 * 
 * </pre>
 * @date : 2015. 3. 23. 오후 4:01:03
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
public interface OproMascMainPageService {
	
	/**
	 * 공고업무 카운트 목록
	 */
	static final String PBLANC_JOB_CNT_LIST = "pblancJobCntList"; 
	/**
	 * 공고전업무 카운트 목록
	 */
	static final String PBLANC_BEF_JOB_CNT_LIST = "pblancBefJobCntList";
	/**
	 * 개찰후업무 카운트 목록
	 */
	static final String OPENG_AT_JOB_CNT_LIST = "opengAtJobCntList";
	/**
	 * 계약업무 카운트 목록
	 */
	static final String CNTRCT_JOB_CNT_LIST = "cntrctJobCntList";
	/**
	 * 부서별 발주계획 카운트 조회
	 */
	static final String DEPT_ACCTO_ORDER_PLAN_CNT_DETAIL = "deptAcctoOrderPlanCntDetail";
	/**
	 * 부서별 입찰공고 카운트 조회
	 */
	static final String DEPT_ACCTO_BID_PBLANC_CNT_DETAIL = "deptAcctoBidPblancCntDetail";
	/**
	 * 일반행정 카운트 조회
	 */
	static final String GNRL_ADMINIST_CNT_DETAIL = "gnrlAdministCntDetail";
	/**
	 * 기타공고 카운트 조회
	 */
	static final String ETC_PBLANC_CNT_DETAIL = "etcPblancCntDetail";
	/**
	 * 부서별공고 목록
	 */
	static final String DEPT_ACCTO_PBLANC_LIST = "deptAcctoPblancList";
	/**
	 * 달력 생성 HTML
	 */
	static final String CLDR_CREAT_HTML = "cldrCreatHtml";
	/**
	 * toDoList 생성 HTML
	 */
	static final String TO_DO_LIST_CREAT_HTML = "toDoListCreatHtml";
	/**
	 * 메인일반공지목록
	 */
	static final String MAIN_GNRL_NOTICE_LIST = "mainGnrlNoticeList";
	/**
	 * 메인입찰공지목록
	 */
	static final String MAIN_BID_NOTICE_LIST = "mainBidNoticeList";
	/**
	 * 메인입찰공고목록
	 */
	static final String MAIN_PBLANC_LIST = "mainPblancList";
	/**
	 * 메인사전공고목록
	 */
	static final String MAIN_BEFFAT_PBLANC_LIST = "mainBeffatPblancList";
	
	/**
	 * <pre>
	 * 1. 개요 : 메인화면 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : mainPageInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject mainPageInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : deptAcctoPblancList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject deptAcctoPblancList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 달력 생성 HTML
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : cldrCreatHtml
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject cldrCreatHtml(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : TODOLIST 생성 HTML
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : toDoListCreatHtml
	 * @date : 2015. 3. 31.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 31.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkTransferObject toDoListCreatHtml(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : DB시간
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : dbTime
	 * @date : 2015. 9. 14.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 14.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */	
	String dbTime(final FwkParameterMap parameterMap);
	
}
