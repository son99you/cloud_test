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
public interface IproSytmEstmSeMngDao {
	
	// 시작------------------------------------------------------------------------------------------ //
	
	// 평가구분관리 목록
	List<FwkDataEntity> selectEstmSeMngList(final FwkParameterMap parameterMap);

	// 평가구분관리 엑셀목록
	List<FwkDataEntity> selectEstmSeMngExcelList(final FwkParameterMap parameterMap);
	
	// 평가구분관리 목록 카운트
	int selectEstmSeMngListTotCnt(final FwkParameterMap parameterMap);
	
	// 평가구분관리 상세	
	FwkDataEntity selectEstmSeMngDetail(final FwkParameterMap parameterMap);
	
	// 평가구분절차 목록	
	List<FwkDataEntity> selectEstmSeProcdList(final FwkParameterMap parameterMap);
	
	String selectEstmSecdMax(final FwkParameterMap parameterMap);
	
	
	void updateTestmSeMstUpdt(final FwkParameterMap parameterMap);
	void updateTestmSeProcdUpdt(final FwkParameterMap parameterMap);
	void updateTestmSeFrmUpdt(final FwkParameterMap parameterMap);
	void updateTmmCodeMstEstmSecdUpdt(final FwkParameterMap parameterMap);
	
	// 평가결과산술시 저장
	void updateEstmSeMngClcRulSave(final FwkParameterMap parameterMap);
	
	void insertCodeMstEstmSecd(final FwkParameterMap parameterMap);
	
	// 평가구분마스터 등록
	void insertEstmSeMngMstRegist(final FwkParameterMap parameterMap);
	
	// 평가구분절차 등록
	void insertEstmSeProcdRegist(final FwkParameterMap parameterMap);
	
	// 평가구분마스터 수정
	void updateEstmSeMngMstUpdt(final FwkParameterMap parameterMap);
	
	// 평가구분절차 삭제
	void deleteEstmSeProcdDelt(final FwkParameterMap parameterMap);
	
	
	// 종료 ------------------------------------------------------------------------------------------ //
	
	
	
	//서식번호 생성
	String selectpFrmNoCreat();
	

	

	

	
	// 평가서식상세
	FwkDataEntity selectEstmFrmDetail(final FwkParameterMap parameterMap);
	
	// 평가서식항목 목록
	List<FwkDataEntity> selectEstmFrmItemList(final FwkParameterMap parameterMap);
	
	// 평가서식마스터 수정
	void updateEstmFrmMstUpdt(final FwkParameterMap parameterMap);
	
	// 평가서식항목 삭제
	void deleteEstmFrmItemDelt(final FwkParameterMap parameterMap);
	
}
