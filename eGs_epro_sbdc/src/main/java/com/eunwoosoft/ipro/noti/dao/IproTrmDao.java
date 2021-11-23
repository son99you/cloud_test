package com.eunwoosoft.ipro.noti.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.dao
 * IproNotiDao.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface IproTrmDao {
	
	List<FwkDataEntity> trmListWithPgng(final FwkParameterMap parameterMap);
	
	int trmListTotCnt(final FwkParameterMap parameterMap);
	
	void trmRegist(final FwkParameterMap parameterMap);

	FwkDataEntity trmDetail(final FwkParameterMap parameterMap);
	
	void trmUpdt(final FwkParameterMap parameterMap);
	
	void trmDelete(final FwkParameterMap parameterMap);
	
	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectTrmAll(final FwkParameterMap parameterMap);

	List<FwkDataEntity> trmExcelList(final FwkParameterMap parameterMap);
}
  