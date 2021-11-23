package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmCmtmMngPoolDao {

	List<FwkDataEntity> selectCmtmMngPoolList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectCmtmMngPoolListExcelDwld(final FwkParameterMap parameterMap);
	
	int selectCmtmMngPoolListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmCmtmPoolMstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmCrqfList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmOfcCareList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmEducList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmCareList(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmPoolMst(final FwkParameterMap parameterMap);
	void insertEstmCmtmCrqf(final FwkParameterMap parameterMap);
	void insertEstmCmtmOfcCare(final FwkParameterMap parameterMap);
	void insertEstmCmtmEduc(final FwkParameterMap parameterMap);
	void insertEstmCmtmCare(final FwkParameterMap parameterMap);

	void updateEstmCmtmPoolMst(final FwkParameterMap parameterMap);
	void deleteEstmCmtmCrqf(final FwkParameterMap parameterMap);
	void deleteEstmCmtmOfcCare(final FwkParameterMap parameterMap);
	void deleteEstmCmtmEduc(final FwkParameterMap parameterMap);
	void deleteEstmCmtmCare(final FwkParameterMap parameterMap);

	int selectEducSeq(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectRealEstmYnList(final FwkParameterMap parameterMap);

	void updateCmtmMngPoolDelAt(final FwkParameterMap parameterMap);
}
