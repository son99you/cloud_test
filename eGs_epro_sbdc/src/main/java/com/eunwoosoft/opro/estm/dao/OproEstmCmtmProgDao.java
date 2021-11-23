package com.eunwoosoft.opro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface OproEstmCmtmProgDao {

	List<FwkDataEntity> selectEstmCmtmProgList(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectEstmCmtmProgExcelList(final FwkParameterMap parameterMap);

	int selectEstmCmtmProgListTotCnt(final FwkParameterMap parameterMap);

	FwkDataEntity selectEstmMngMstDetail(final FwkParameterMap parameterMap);

	FwkDataEntity selectEstmFile(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectEstmTabList(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectCmtmFileList(final FwkParameterMap parameterMap);

	void updateEstmMngMst(final FwkParameterMap parameterMap);

	FwkDataEntity selectEstmProcdDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmObjValueList(final FwkParameterMap parameterMap);
	
	int selectEstmObjValueListTotCnt(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectEstmChrgrList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmChrgrValueList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmCmtmLastList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmValueList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmMngProgVidoMtngList(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectFileHashcdValueListInqire(final FwkParameterMap parameterMap);

	void updateEstmFileSignRegist(final FwkParameterMap parameterMap);

	void insertEstmProgHist(final FwkParameterMap parameterMap);

	FwkDataEntity selectCmtmFileSignYn(final FwkParameterMap parameterMap);
}
