package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 입찰결과 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidResultDao.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 02:01:30
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidResultDao {

	/**
	 * <pre>
	 * 1. 개요 : 입찰결과 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectBidResultListWithPgng
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
	List<FwkDataEntity> selectBidResultListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰결과 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidResultListTotcnt
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
	int selectBidResultListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 목록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidResultList
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidResultList(final FwkParameterMap parameterMap);
	
	
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
	 * 1. 개요 : 품목별 세부입찰사항 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPrdlstAcctoDetailBidDtlsInqire
	 * @date : 2015. 8. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectPrdlstAcctoDetailBidDtlsInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 	
	 * <pre>
	 * 1. 개요 : 제안 요약서 다운로드
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEnatpaDownInqire
	 * @date : 2017. 8. 18.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2017. 8. 18.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEnatpaDownInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰 품목 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidThngList
	 * @date : 2015. 8. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidThngList(final FwkParameterMap parameterMap);
	
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
	 * 1. 개요 : 낙찰자선정 결격사유 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertSucbidrSlctnBrdoResnRegist
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
	 * 1. 개요 : 유찰 낙찰 예정자 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectFibScsBidPrearngerList
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
	 * @return
	 */
	List<FwkDataEntity> selectFibScsBidPrearngerList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰업체마스터 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertG2BVendInfoMst
	 * @date : 2018. 10. 12.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 12.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void insertG2BVendInfoMst(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰업체개찰정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertG2BVendBiopInfo
	 * @date : 2018. 10. 12.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 12.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void insertG2BVendBiopInfo(final FwkParameterMap parameterMap);	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 개찰정보 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : deleteBiVendBiop
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 */
	void deleteBiVendBiop(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 예가마스터 등록 및 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : insertResultBiEstcMstRegist
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 */
	void insertResultBiEstcMstRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 업체 마스터 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : deleteDatpMst
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 */
	void deleteDatpMst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 업체 마스터 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : insertDatpMst
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 */
	void insertDatpMst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 지급각서 보기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : selectGrntDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectGrntDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 청렴이행각서 보기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : selectCleanDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectCleanDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 라운드 리스트 정보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidRoundList
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
     *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidRoundList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 라운드별 마스터 정보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidVendMstList
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
     *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidVendMstList(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 라운드 정보 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : selectBidRoundDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 5. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidRoundDetail(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 품목별 업체 투찰금액 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidResultDao.java
	 * @Method : selectTndrItemAmtList
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 13. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectTndrItemAmtList(final FwkParameterMap parameterMap);	
}