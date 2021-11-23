package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmMenuDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
public interface IproSytmMenuDao {
	
	List<FwkDataEntity> selectMenuLrgListInqire(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectMenuMgrListInqireWithPgng(final FwkParameterMap parameterMap);
	
	int selectMenuMgrListTotcnt(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectMenuMgrExcelList(final FwkParameterMap parameterMap);
}
