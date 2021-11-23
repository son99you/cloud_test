package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmContFormDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 26.
 *
 */
public interface IproSytmEstmFrmDao {
	
	
		void insertDataBaseRegist(final FwkParameterMap parameterMap);
	
	// 시작------------------------------------------------------------------------------------------ //
	
		//서식번호 생성
		String selectpFrmNoCreat();
		
		// 평가서식마스터 등록
		void insertEstmFrmMstRegist(final FwkParameterMap parameterMap);
		
		
		// 평가서식항목 등록
		void insertEstmFrmMstItemRegist(final FwkParameterMap parameterMap);
		
		// 평가서식 목록
		List<FwkDataEntity> selectEstmFrmList(final FwkParameterMap parameterMap);
		
		// 평가서식 목록 카운트
		int selectEstmFrmListTotCnt(final FwkParameterMap parameterMap);
		
		// 라이센스 목록
		List<FwkDataEntity> selectLicenseList(final FwkParameterMap parameterMap);
		
		// 라이센스 목록 카운트
		int selectLicenseListTotCnt(final FwkParameterMap parameterMap);

		//라이센스번호 생성
		String selectpLicenseNoCreat();
		
		// 라이센스 등록
		void insertLicenseMstRegist(final FwkParameterMap parameterMap);
		
		// 라이센스 수정
		void updateLicenseMstUpdt(final FwkParameterMap parameterMap);
		
		// 라이센스 삭제
		void updateLicenseMstDelt(final FwkParameterMap parameterMap);
		
		// 라이센스 진행상태 업데이트
		void updateLicensePscdUpdt(final FwkParameterMap parameterMap);
		
		
		// 라이센스 엑셀리스트
		List<FwkDataEntity> selectLicenseExcelList(final FwkParameterMap parameterMap);
		
		void mergeFileMstRegist(final FwkParameterMap parameterMap);
		
		
		// 라이센스 상세
		FwkDataEntity selectLicenseDetail(final FwkParameterMap parameterMap);
		
		// 평가서식상세
		FwkDataEntity selectEstmFrmDetail(final FwkParameterMap parameterMap);
		
		// 평가서식항목 목록
		List<FwkDataEntity> selectEstmFrmItemList(final FwkParameterMap parameterMap);
		
		// 평가서식마스터 수정
		void updateEstmFrmMstUpdt(final FwkParameterMap parameterMap);
		
		// 평가서식마스터 삭제
		void deleteEstmFrmMstDelt(final FwkParameterMap parameterMap);
		
		// 평가서식항목 삭제
		void deleteEstmFrmItemDelt(final FwkParameterMap parameterMap);
		
		List<FwkDataEntity> selectEstmFrmExcelList(final FwkParameterMap parameterMap);
		
		
		// 종료 ------------------------------------------------------------------------------------------ //
	
	
}
