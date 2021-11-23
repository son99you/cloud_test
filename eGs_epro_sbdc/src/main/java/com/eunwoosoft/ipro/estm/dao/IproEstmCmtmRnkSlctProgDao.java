package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmCmtmRnkSlctProgDao {

	List<FwkDataEntity> selectCmtmRnkSlctProgList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectCmtmRnkSlctProgListExcelDwld(final FwkParameterMap parameterMap);
	
	int selectCmtmRnkSlctProgListTotCnt(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmMngMstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmFile(final FwkParameterMap parameterMap);
	
	void updateEstmCmtmPrioRnk(final FwkParameterMap parameterMap);

}
