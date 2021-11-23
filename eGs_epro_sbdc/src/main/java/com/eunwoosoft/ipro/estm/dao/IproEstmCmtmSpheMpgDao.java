package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmCmtmSpheMpgDao {

	
	List<FwkDataEntity> selectEstmCmtmSpheMpgList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmCmtmSpheMpgItemList(final FwkParameterMap parameterMap);
	
	int selectEstmCmtmSpheMpgListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmCmtmSpheMpgDetail(final FwkParameterMap parameterMap);
	
	void deleteCmtmSpheMpgDelt(final FwkParameterMap parameterMap);
	
	void insertCmtmSpheMpgItemRegist(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectRealEstmYn(FwkParameterMap parameterMap);

	List<FwkDataEntity> selectCmtmSpheMpgListExcelDwld(final FwkParameterMap parameterMap);
	
}
