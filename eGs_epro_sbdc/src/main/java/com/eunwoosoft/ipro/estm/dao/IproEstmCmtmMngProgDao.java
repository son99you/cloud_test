package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmCmtmMngProgDao {

	List<FwkDataEntity> selectCmtmMngProgList(final FwkParameterMap parameterMap);
	int selectCmtmMngProgListTotCnt(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectCmtmMngProgExcelList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmCmtmPoolFileList(final FwkParameterMap parameterMap);
	
	void updateEstmCmtmPoolMstFileGrpNoUpdt(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmCntcObj(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmPoolMst(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmEduc(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmCare(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmCrqf(final FwkParameterMap parameterMap);
	
	void insertEstmCmtmOfcCare(final FwkParameterMap parameterMap);
	
	void deleteEstmCmtmPool(final FwkParameterMap parameterMap);
	
	void deleteEstmCmtmEduc(final FwkParameterMap parameterMap);
	
	void deleteEstmCmtmCare(final FwkParameterMap parameterMap);
	
	void deleteEstmCmtmCrqf(final FwkParameterMap parameterMap);
	
	void deleteEstmCmtmOfcCare(final FwkParameterMap parameterMap);
	
	void updateEstmCmtmPoolSlctYn(final FwkParameterMap parameterMap);
	
	
}
