package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmCodeMngeDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 30.
 *
 */
public interface IproSytmCodeMngeDao {
	
	List<FwkDataEntity> codeMngeListWithPgng(final FwkParameterMap parameterMap);
	
	int codeMngeListTotCnt(final FwkParameterMap parameterMap);

	List<FwkDataEntity> codeMngeExcelList(final FwkParameterMap parameterMap);
}
