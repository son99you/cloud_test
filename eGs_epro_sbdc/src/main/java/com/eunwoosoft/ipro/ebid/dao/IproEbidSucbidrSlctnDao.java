package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 전자입찰 > 낙찰자선정 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidSucbidrSlctnDao.java
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 10:04:30
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidSucbidrSlctnDao {

	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectSucbidrSlctnListWithPgng
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectSucbidrSlctnListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectSucbidrSlctnListTotcnt
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectSucbidrSlctnListTotcnt(final FwkParameterMap parameterMap);
	
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
	
	List<FwkDataEntity> selectNtElgbVendList(final FwkParameterMap parameterMap);
	
	
	
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
	 * 1. 개요 : 협상 통보 등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNtatDspthDetail
	 * @date : 2015. 9. 9.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 9.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectNtatDspthDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 정보 협상 통보 상세 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsInfoNtatDspthDetail
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectEntrpsInfoNtatDspthDetail(final FwkParameterMap parameterMap);
	
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
	 * 1. 개요 : 사업참여이력 목록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBsnsPartcptnHistList
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
	
	List<FwkDataEntity> selectBsnsPartcptnHistList(final FwkParameterMap parameterMap);
	
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
	 * 1. 개요 : 협상 통보 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertNtatDspthRegist
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	void insertNtatDspthRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 결격사유 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateSucbidrSlctnBrdoResnRegist
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
	 */
	
	void updateSucbidrSlctnBrdoResnRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 진행 이력 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertEtrpsProgrsHistRegist
	 * @date : 2015. 4. 28.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 28.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertEtrpsProgrsHistRegist(final FwkParameterMap parameterMap);
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 이메일전송대상 등록 (업체참여진행상태 수정)
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
	 * 1. 개요 : 적격심사 가격점수 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidPc
	 * @date : 2015. 3. 3.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 10. 3.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	String selectBidPc(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사평가내용 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertProperJdgmnEvlCnRegist
	 * @date : 2015. 3. 19.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 19.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertProperJdgmnEvlCnRegist(final FwkParameterMap parameterMap);
	
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
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상 적격 심사 통보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertNtatProperJdgmnDspthRegist
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
	 */
	
	void insertNtatProperJdgmnDspthRegist (final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 적격 심사 점수 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateEntrpsProperJdgmnTotScoreRegist
	 * @date : 2015. 4. 6.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 6.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateEntrpsProperJdgmnTotScoreRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  낙찰자 정보 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateSucbidInfoUpdt
	 * @date : 2015. 4. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateSucbidInfoUpdt(final FwkParameterMap parameterMap); 
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰진행상태 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateBidPrstUpdt
	 * @date : 2015. 4. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateBidPrstUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  입찰 진행이력상태 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBidProgrsHistSttusRegist
	 * @date : 2015. 4. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertBidProgrsHistSttusRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 협상통보
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidSucbidrSlctnDao.java
	 * @Method : updateNegoUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 7. 
	 * @param parameterMap
	 */
	void updateNegoUpdt(final FwkParameterMap parameterMap);
	
	void updateProperJdgmnEvlNtElgbUpdt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectSameRankVendList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectDatpVendList(final FwkParameterMap parameterMap);
	
	void updateDatpVend(final FwkParameterMap parameterMap);
}