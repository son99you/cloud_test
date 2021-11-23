package com.eunwoosoft.ipro.noti.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.dao
 * IproFaqDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
public interface IproFaqDao {
	
	List<FwkDataEntity> faqListWithPgng(final FwkParameterMap parameterMap);

	List<FwkDataEntity> faqExcelList(final FwkParameterMap parameterMap);
	 
	int faqListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity faqDetail(final FwkParameterMap parameterMap);
	
	void faqRegist(final FwkParameterMap parameterMap);
	
	void faqUpdt(final FwkParameterMap parameterMap); 
	
	void faqDelete(final FwkParameterMap parameterMap); 
	
	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);

	void updateInqCntBaMst(final FwkParameterMap parameterMap);
}
  