package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproSytmEstmSpheMngDao {

	List<FwkDataEntity> selectEstmSpheMngList(final FwkParameterMap parameterMap);
	int selectEstmSpheMngListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmSpheMngDetail(final FwkParameterMap parameterMap);
	
	void insertEstmSpheMng(final FwkParameterMap parameterMap);
	void updateEstmSpheMng(final FwkParameterMap parameterMap);
	
	void deleteEstmSpheMng(final FwkParameterMap parameterMap);


}
