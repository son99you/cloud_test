package com.eunwoosoft.ipro.stts.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.dao
 * IproVendMngeDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 21.
 *
 */
public interface IproSttsEstmMngDao {
	
	FwkDataEntity selectSttsInfoDetail(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectSttsList(final FwkParameterMap parameterMap);

	int selectSttsListTotcnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> getColList(final FwkParameterMap parameterMap);
	
}