package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmContFormDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 26.
 *
 */
public interface IproSytmDsgnRecmDao {
	
	List<FwkDataEntity> selectDsgnRecmListWithPgng(final FwkParameterMap parameterMap);
	
	int selectDsgnRecmListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectDsgnRecmDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectDsgnRecmFileList(final FwkParameterMap parameterMap);

	//서식마스터 등록
	void insertDsgnRecmMst(final FwkParameterMap parameterMap); 
	
	//서식마스터 수정 
	void updateDsgnRecmMst(final FwkParameterMap parameterMap);
	
	void updateDsgnRecmFileDel(final FwkParameterMap parameterMap);


}
