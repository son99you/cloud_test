package com.eunwoosoft.opro.noti.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.noti.dao
 * OproNotiGnrlDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 29.
 *
 */
public interface OproNotiGnrlDao {
	
	List<FwkDataEntity> notiGnrlListWithPgng(final FwkParameterMap parameterMap);

	List<FwkDataEntity> notiGnrlExcelList(final FwkParameterMap parameterMap);
	
	int notiGnrlListTotCnt(final FwkParameterMap parameterMap);
	 
	FwkDataEntity notiGnrlDetail(final FwkParameterMap parameterMap);
	
	void updateInqCntBaMst(final FwkParameterMap parameterMap);
}
  