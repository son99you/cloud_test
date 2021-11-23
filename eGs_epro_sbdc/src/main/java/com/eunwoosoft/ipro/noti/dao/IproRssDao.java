package com.eunwoosoft.ipro.noti.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.noti.dao
 * IproRssDao.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 15.
 *
 */
public interface IproRssDao {
	
	List<FwkDataEntity> rssListWithPgng(final FwkParameterMap parameterMap);
	 
	int rssListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity rssDetail(final FwkParameterMap parameterMap);
	
	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);
	
	void rssRegist(final FwkParameterMap parameterMap);
	
	void rssUpdt(final FwkParameterMap parameterMap); 
	
	void rssDelete(final FwkParameterMap parameterMap); 

	void updateInqCntBaMst(final FwkParameterMap parameterMap); 
}
  