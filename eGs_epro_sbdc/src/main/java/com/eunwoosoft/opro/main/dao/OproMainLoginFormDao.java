package com.eunwoosoft.opro.main.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.main.dao
 * OproMainLoginFormDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
public interface OproMainLoginFormDao {
	
	//계약설계_검토요청 상태 
	int selectMyContReqMainTotcnt(final FwkParameterMap parameterMap);
	//계약관리_검토요청 상태 
	int selectMyContPrcnMainTotcnt(final FwkParameterMap parameterMap);
	//계약_서명요청 상태 
	int selectMyContSignMainTotcnt(final FwkParameterMap parameterMap);
	//대금_지급요청 상태 
	int selectMyPayReqMainTotcnt(final FwkParameterMap parameterMap);
	
	// 사업자번호로 업체 조회
	FwkDataEntity selectEntrpsMberInqireByBizrNo(final FwkParameterMap parameterMap); 
	
	//업체 등록
	void joinEnpaRegist(final FwkParameterMap parameterMap);
	
	//로그인ID 등록
	void loginIdRegist(final FwkParameterMap parameterMap);
	
	//담당자 기본정보 등록
	void enpaUserRegist(final FwkParameterMap parameterMap); 
	
	// ID로 업체 조회
	FwkDataEntity selectEntrpsMberInqireById(final FwkParameterMap parameterMap);
	
	// ID+PW로 업체 조회
	FwkDataEntity selectEntrpsMberInqireByIdAndPassword(final FwkParameterMap parameterMap);
	
	// 업체 회원가입여부 조회
	FwkDataEntity joinEnpaCheck(final FwkParameterMap parameterMap);
	
	// 업체 필수 파일 체크
	FwkDataEntity selectVendFileYn(final FwkParameterMap parameterMap);

	// 업체마스터 등록
	void insertVendInfoRegist(final FwkParameterMap parameterMap);   // T_CU_MST

	// 업체사용자 등록
	void insertVendChrgrInfoRegist(final FwkParameterMap parameterMap);   // T_CU_USER

	// 
	FwkDataEntity getBknm(final FwkParameterMap parameterMap);

	// 업체계좌 등록
	void insertVendAcctInfoList(final FwkParameterMap parameterMap);   // T_CU_ACCT

	// 
	FwkDataEntity getCttNm(final FwkParameterMap parameterMap);

	// 기업정보 등록
	void insertVendAcqsInfoList(final FwkParameterMap parameterMap);   // T_CU_CTT_ACQS

	// 업체물품등록 등록
	void insertVendItemList(final FwkParameterMap parameterMap);   // T_CU_VEND_ITEM

	// 업체첨부문서 등록
	void insertVendAtchDocList(final FwkParameterMap parameterMap);   // T_CU_FILE_MST

	// 업체승인이력
	void insertApprVendProgHist(final FwkParameterMap parameterMap);   // T_CU_APPR_HIST

	// TODOLIST
	List<FwkDataEntity> selectToDoList(final FwkParameterMap parameterMap);

	// ID, PW로 사용자 확인
	FwkDataEntity selectPasswordChk(final FwkParameterMap parameterMap);
	
	// 주민등록번호로 평가위원 확인
	FwkDataEntity selectEstmCmtmInqireByRsdnNo(final FwkParameterMap parameterMap);   // V_MM_ESTM_CMTM_MST

	// 평가구분에 따른 평가공고 조회
	List<FwkDataEntity> selectEstmAnncMainList(final FwkParameterMap parameterMap);   // T_ESTM_MNG_MST
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 유저 정보 조회 (id)
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao.java
	 * @Method : selectEmplyrInqireById
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 12. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEmplyrInqireById(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 유저 정보 조회 (id+pw)
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao.java
	 * @Method : selectEmplyrInqireByIdAndPassword
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 12. 
	 * @param parameterMap
	 */
	FwkDataEntity selectEmplyrInqireByIdAndPassword(final FwkParameterMap parameterMap);
	
	
}
