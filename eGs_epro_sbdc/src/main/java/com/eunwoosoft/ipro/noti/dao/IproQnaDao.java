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
public interface IproQnaDao {
	
	List<FwkDataEntity> qnaBoardListWithPgng(final FwkParameterMap parameterMap);
	 
	int qnaBoardListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity qnaBoardDetail(final FwkParameterMap parameterMap);
	
	void qnaReplyUpdate(final FwkParameterMap parameterMap); 
	
	void qnaReplyDelete(final FwkParameterMap parameterMap); 
	
	void deleteFileDelete(final FwkParameterMap parameterMap); 
	
	FwkDataEntity iproQnaReplyInfo(final FwkParameterMap parameterMap);

	void qnaReplyRegist(final FwkParameterMap parameterMap); 
	
	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);
	
	void updateInqCntBaMst(final FwkParameterMap parameterMap);

	List<FwkDataEntity> qnaBoardExcelList(final FwkParameterMap parameterMap);
}
  