package com.eunwoosoft.opro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 전자입찰 > 나의 협상통보 DAO
 * <pre>
 * com.eunwoosoft.opro.ebid.dao 
 *    |_ OproEbidMyNtatDspthDao.java
 * 
 * </pre>
 * @date : 2015. 03. 24. 오후 7:19:35
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface OproEbidMyNtatDspthDao {

	/**
	 * <pre>
	 * 1. 개요 : 나의 협상통보 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectMyNtatDspthListWithPgng
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectMyNtatDspthListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 협상통보 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectMyNtatDspthListTotcnt
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectMyNtatDspthListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상통보 상세 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyNtatDspthDetail
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectMyNtatDspthDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상 통보 응답 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertMyNtatDspthRspnsRegist
	 * @date : 2015. 3. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 25.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertMyNtatDspthRspnsRegist(final FwkParameterMap parameterMap); 
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰정보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidInfoDetail
	 * @date : 2015. 2. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectBidInfoDetail(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  복수예가 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCompnoPrdprcList
	 * @date : 2015. 3. 12.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectCompnoPrdprcList(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 최저 낙찰 예정자 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectLwetScsbidPrearngerList
	 * @date : 2015. 3. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */

	List<FwkDataEntity> selectLwetScsbidPrearngerList(final FwkParameterMap parameterMap);
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상 낙찰 예정자 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNtatScsbidPrearngerList
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectNtatScsbidPrearngerList(final FwkParameterMap parameterMap);
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체정보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsInfoDetail
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectEntrpsInfoDetail(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectProperJdgmnEntrpsDetail
	 * @date : 2015. 3. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectProperJdgmnEntrpsDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  주요사업 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMainBnsnList
	 * @date : 2015. 3. 20.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 20.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectMainBnsnList(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 심사 평가 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsJdgmnEvlList
	 * @date : 2015. 3. 20.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 20.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectEntrpsJdgmnEvlList(final FwkParameterMap parameterMap);
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 이메일전송대상 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateEntrpsPartcptnPrstUpdt
	 * @date : 2015. 3. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateEntrpsPartcptnPrstUpdt(final FwkParameterMap parameterMap);
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  적격심사 평가등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertProperJdgmnEvlRegist
	 * @date : 2015. 3. 20.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 20.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateProperJdgmnEvlRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 예가선택업체 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidResultPrdprcChoiseEntrpsInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectBidResultPrdprcChoiseEntrpsInqire(final FwkParameterMap parameterMap);
	
	
	
}