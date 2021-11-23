package com.eunwoosoft.opro.recr.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.recr.dao
 * OproRecrDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 29.
 *
 */
public interface OproRecrDao {

	
	List<FwkDataEntity> recrGnrlListWithPgng(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> recrAnncListExcelDwld(final FwkParameterMap parameterMap);
	

	int recrGnrlListTotCnt(final FwkParameterMap parameterMap);
	 
	FwkDataEntity recrGnrlDetail(final FwkParameterMap parameterMap);
	
	FwkDataEntity recrAnncDetail(final FwkParameterMap parameterMap);

	FwkDataEntity recrGnrlFile(final FwkParameterMap parameterMap);

	void recrAnncReg(final FwkParameterMap parameterMap);

	void insertRecrObj(final FwkParameterMap parameterMap);

	void insertRecrEstmObj(final FwkParameterMap parameterMap);

	FwkDataEntity recrAnncCheck(final FwkParameterMap parameterMap);

	FwkDataEntity  recrResdNo(final FwkParameterMap parameterMap);

	void recrAnncUpdt(final FwkParameterMap parameterMap);

	void recrObjUpdt(final FwkParameterMap parameterMap);
	
	//중복
	//	FwkDataEntity estmGnrlDetail(FwkParameterMap parameterMap);

//	void insertRecrFile(final FwkParameterMap parameterMap); 

//	void recrAnncFile(final FwkParameterMap parameterMap);
//
//	FwkDataEntity selectRecrDetail( final FwkParameterMap parameterMap);
//
//	void selectRecrAtchFileInsert(final FwkParameterMap parameterMap);
//
//	void insertRecrFile(final FwkParameterMap parameterMap);

//	void recrInsertObj(final FwkParameterMap parameterMap);


	
//	void updateInqCntBaMst(final FwkParameterMap parameterMap);

}
  