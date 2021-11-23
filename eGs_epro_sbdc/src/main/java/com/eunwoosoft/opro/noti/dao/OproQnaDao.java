package com.eunwoosoft.opro.noti.dao;

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
public interface OproQnaDao {
	
	List<FwkDataEntity> qnaListWithPgng(final FwkParameterMap parameterMap);

	List<FwkDataEntity> qnaExcelList(final FwkParameterMap parameterMap);
	
	int qnaListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity qnaDetail(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectFileList(final FwkParameterMap parameterMap);
	
	void qnaBoardRegist(final FwkParameterMap parameterMap);
	
	void qnaBoardUpdate(final FwkParameterMap parameterMap); 
	
	void qnaBoardDelete(final FwkParameterMap parameterMap); 
	
	void deleteFileDelete(final FwkParameterMap parameterMap); 

	void qnaReplyDelete(final FwkParameterMap parameterMap); 
	
	FwkDataEntity usrInfoDetail(final FwkParameterMap parameterMap);
	
	void updateInqCntBaMst(final FwkParameterMap parameterMap);
}
  