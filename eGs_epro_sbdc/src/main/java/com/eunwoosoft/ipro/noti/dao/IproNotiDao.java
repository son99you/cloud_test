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
public interface IproNotiDao {
	
	List<FwkDataEntity> notiListWithPgng(final FwkParameterMap parameterMap);
	
	int notiListTotCnt(final FwkParameterMap parameterMap);
	
	void notiRegist(final FwkParameterMap parameterMap);

	FwkDataEntity notiDetail(final FwkParameterMap parameterMap);
	
	void notiUpdt(final FwkParameterMap parameterMap);
	
	void notiDelete(final FwkParameterMap parameterMap);
	
	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);

	List<FwkDataEntity> notiExcelList(final FwkParameterMap parameterMap);

	void updateInqCntBaMst(final FwkParameterMap parameterMap);
}
  