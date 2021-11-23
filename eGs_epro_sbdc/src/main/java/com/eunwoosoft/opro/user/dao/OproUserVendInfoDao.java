package com.eunwoosoft.opro.user.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.opro.user.dao
 * OproUserVendInfoDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 8.
 *
 */
public interface OproUserVendInfoDao {
	
	// 자사정보 상세 - 업체정보
	FwkDataEntity selectVendInfoDetail(final FwkParameterMap parameterMap);

	// 자사정보 상세 - 업체담당자정보
	FwkDataEntity selectVendChrgrInfoDetail(final FwkParameterMap parameterMap);

	// 자사정보 상세 - 계좌정보
	List<FwkDataEntity> selectVendAcctInfoList(final FwkParameterMap parameterMap);
	FwkDataEntity selectVendAcctInfo(final FwkParameterMap parameterMap);
	
	// 자사정보 상세  - 계좌정보 첨부파일
	FwkDataEntity selectVendAcctFileGrpNoInqire(final FwkParameterMap parameterMap);
	
	// 자사정보 상세 - 기업정보
	List<FwkDataEntity> selectVendAcqsInfoList(final FwkParameterMap parameterMap);
	
	// 자사정보 상세  - 기업정보 첨부파일
	FwkDataEntity selectVendAcqsFileGrpNoInqire(final FwkParameterMap parameterMap);
	
	// 업체상태코드
	void updateVendStcd(final FwkParameterMap parameterMap);

	// 업체승인이력
	void insertApprVendProgHist(final FwkParameterMap parameterMap);

	// 업체마스터 수정
	void updateVendInfo(final FwkParameterMap parameterMap);

	// 업체사용자 수정
	void updateVendChrgrInfo(final FwkParameterMap parameterMap);

	// 업체계좌 삭제
	void deleteVendAcctInfoList(final FwkParameterMap parameterMap);

	// 업체계좌 등록
	void insertVendAcctInfoList(final FwkParameterMap parameterMap);

	FwkDataEntity getBknm(final FwkParameterMap parameterMap);
	
	// 기업정보 삭제
	void deleteVendAcqsInfoList(final FwkParameterMap parameterMap);

	FwkDataEntity getCttNm(final FwkParameterMap parameterMap);
	
	// 기업정보 등록
	void insertVendAcqsInfoList(final FwkParameterMap parameterMap);

	// 업체물품정보 조회
	List<FwkDataEntity> selectVendItemList(final FwkParameterMap parameterMap);

	// 업체물품정보 삭제
	void deleteVendItemList(final FwkParameterMap parameterMap);

	// 업체물품등록 등록
	void insertVendItemList(final FwkParameterMap parameterMap);

	// 업체첨부문서 목록
	FwkDataEntity selectVendAtchDocList(final FwkParameterMap parameterMap);

	// 업체첨부문서 등록
	void insertVendAtchDocList(final FwkParameterMap parameterMap);

	// 업체첨부문서 삭제
	void deleteVendAtchDocList(final FwkParameterMap parameterMap);

	// 업체첨부문서 수정
	void updateVendAtchDocList(final FwkParameterMap parameterMap);

	// 업체 비밀번호 변경
	void updtVendPwd(final FwkParameterMap parameterMap);
	
	// 업체 반려 내역
	FwkDataEntity selectVendRtnProgList(final FwkParameterMap parameterMap);

}
