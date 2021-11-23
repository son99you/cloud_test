package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproSytmPrdtMngeDao {

	List<FwkDataEntity> selectPrdtMngeListInqireWithPgng(final FwkParameterMap parameterMap);

	int selectPrdtMngeListTotcnt(final FwkParameterMap parameterMap);

	FwkDataEntity selectPrdtMngDetail( final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectPrdtMngeExcelList(final FwkParameterMap parameterMap);


}
