package com.eunwoosoft.ipro.noti.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.dao
 * IproBidNotiDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 24.
 *
 */
public interface IproBidNotiDao {

	List<FwkDataEntity> bidNotiListWithPgng(final FwkParameterMap parameterMap);

	int bidNotiListTotCnt(final FwkParameterMap parameterMap);

	FwkDataEntity bidNotiDetail(final FwkParameterMap parameterMap);

	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);

	void bidNotiRegist(final FwkParameterMap parameterMap);

	void bidNotiDelete(final FwkParameterMap parameterMap);

	void bidNotiUpdt(final FwkParameterMap parameterMap);

}
