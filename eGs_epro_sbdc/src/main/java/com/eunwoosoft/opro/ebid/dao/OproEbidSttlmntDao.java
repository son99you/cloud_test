package com.eunwoosoft.opro.ebid.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.main.dao
 * OproMainLoginFormDao.java
 *
 * @Author : chanil_Hong
 * @Date   : 2018. 2. 20.
 *
 */
public interface OproEbidSttlmntDao {

	/**
	 * <pre>
	 * 1. 개요 : 나의 수의시담 목록조회 페이징
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlListWithPgng
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018.03.20	 은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectVltrnPrvstlListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 수의시담 목록 총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidDcPeoListTotcnt
	 * @date : 2018.03.20
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018.03.20	 은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectVltrnPrvstlListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlDetail
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectVltrnPrvstlDetail(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  조달의뢰정보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPrcureReqestInfoDetail
	 * @date : 2015. 3. 3.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 3.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectPrcureReqestInfoDetail(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 번호 생성 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlNoCreat
	 * @date : 2015. 3. 3.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 3.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	String selectVltrnPrvstlNoCreat(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 요청 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlRequstRegist
	 * @date : 2015. 3. 3.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 3.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void insertVltrnPrvstlRequstRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 업체 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlEntrpsRegist
	 * @date : 2015. 3. 4.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 4.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertVltrnPrvstlEntrpsRegist(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 이행상태 수정 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updatePrvstlFlflSttusUpdt
	 * @date : 2015. 3. 3.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 3.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updatePrvstlFlflSttusUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 진행상태 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updatePrvstlProgrsSttusUpdt
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updatePrvstlProgrsSttusUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 진행이력 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlHistRegist
	 * @date : 2015. 3. 3.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 3.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void insertVltrnPrvstlHistRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 신규 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlNewRegist
	 * @date : 2015. 3. 4.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 4.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertVltrnPrvstlNewRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  수의시담 진행 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlProgrsList
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectVltrnPrvstlProgrsList(final FwkParameterMap parameterMap);
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 내용 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlCnRegist
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertVltrnPrvstlCnRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의 시담 협상 금액 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlNtatAmountRegist
	 * @date : 2015. 6. 17.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 17.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertVltrnPrvstlNtatAmountRegist(final FwkParameterMap parameterMap); 
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 협상 금액 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlNtatAmountList
	 * @date : 2015. 6. 17.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 17.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectVltrnPrvstlNtatAmountList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 예가 이하 입력 금액 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsPrdprcBelowInputAmount
	 * @date : 2015. 6. 29.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 29.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectEntrpsPrdprcBelowInputAmount(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 최종 금액 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updatePrvstlLastAmountRegist
	 * @date : 2015. 6. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 19.		은우소프트 홍찬일			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void updatePrvstlLastAmountRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 예가 이하 여부 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateVltrnPrvstlPrdprcBelowAtRegist
	 * @date : 2015. 7. 7.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 7. 7.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateVltrnPrvstlPrdprcBelowAtRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 전자서명 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertPrvstlSignRegist
	 * @date : 2015. 11. 03.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 11. 03.		은우소프트 홍찬일			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	void insertPrvstlSignRegist(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 전체 수의시담 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectAllVltrnPrvstlListWithPgng
	 * @date : 2016. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectAllVltrnPrvstlListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 전체 수의시담 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectAllVltrnPrvstlListTotcnt
	 * @date : 2016. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectAllVltrnPrvstlListTotcnt(final FwkParameterMap parameterMap);
	
}
