package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmCmtmRnkSlctCmplDao {

	List<FwkDataEntity> selectCmtmRnkSlctCmplList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectCmtmRnkSlctCmplListExcelDwld(final FwkParameterMap parameterMap);
	
	int selectCmtmRnkSlctCmplListTotCnt(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmMngMstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmFile(final FwkParameterMap parameterMap);
	
	void updateEstmCmtmPrioRnk(final FwkParameterMap parameterMap);

}
