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
public interface IproInNotiDao {
	
	List<FwkDataEntity> inNoticeBoardListWithPgng(final FwkParameterMap parameterMap);
	 
	int inNoticeBoardListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity inNoticeBoardDetail(final FwkParameterMap parameterMap);
	
	void inNoticeBoardRegist(final FwkParameterMap parameterMap);
	
	void inNoticeBoardUpdate(final FwkParameterMap parameterMap);
	
	void inNoticeBoardDelete(final FwkParameterMap parameterMap); 
}
  