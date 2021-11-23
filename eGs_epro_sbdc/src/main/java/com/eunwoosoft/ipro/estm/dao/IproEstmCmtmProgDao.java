package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmCmtmProgDao {

	
	List<FwkDataEntity> selectEstmCmtmProgList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmCmtmProgListExcelDwld(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmVidoMtngCreatMeetList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmVidoMtngCmtmCreatMeetList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmVidoMtngObjCreatMeetList(final FwkParameterMap parameterMap);
	
	void updateEstmVidoMtngEntrKey(final FwkParameterMap parameterMap);
	void updateEstmVidoMtngCmtmEntrKey(final FwkParameterMap parameterMap);
	void updateEstmVidoMtngObjEntrKey(final FwkParameterMap parameterMap);
	
	int selectEstmCmtmProgListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmMngMstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmMngProgVidoMtngList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmProcdList(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmFile(final FwkParameterMap parameterMap);
	
	void insertEstmProgHist(final FwkParameterMap parameterMap);
	void updateEstmDelAt(final FwkParameterMap parameterMap);
	void updateEstmPscd(final FwkParameterMap parameterMap);
	
	void updateEstmMngMst(final FwkParameterMap parameterMap);
	void deleteEstmProcd(final FwkParameterMap parameterMap);
	void deleteEstmFile(final FwkParameterMap parameterMap);
	
	void insertEstmObj(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmObjList(final FwkParameterMap parameterMap);
	
	void deleteEstmObj(final FwkParameterMap parameterMap);
	void insertEstmCntcObj(final FwkParameterMap parameterMap);
	void updateEstmObjFileGrpNo(final FwkParameterMap parameterMap);
	void updateEstmObjDelAt(final FwkParameterMap parameterMap);
	void updateEstmProcdPscd(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmSpheSecdList(final FwkParameterMap parameterMap);
	

	
	void insertEstmCmtm(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmList(final FwkParameterMap parameterMap);
	void updateEstmCmtmSlctYnUpdt(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmTabList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmSearchColList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultObjAllList(final FwkParameterMap parameterMap);
	
	int selectEstmResultObjAllListTotCnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultCmtmAllList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultProcdSeqList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultItemNoList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultItemAllList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultSearchCheckList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmCmtmLastList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmChrgrList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmValueList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmChrgrValueList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmObjValueList(final FwkParameterMap parameterMap);
	
	int selectEstmObjValueListTotCnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultProcdObjSlctList(final FwkParameterMap parameterMap);
	
	
	
	FwkDataEntity selectEstmProcdDetail(final FwkParameterMap parameterMap);
	
	
	FwkDataEntity selectEstmSeMngDetail(final FwkParameterMap parameterMap);
	void selectAtchFileInsert(final FwkParameterMap parameterMap);
	void insertEstmCmtmAutoSlct(final FwkParameterMap parameterMap);
	void deleteEstmCmtm(final FwkParameterMap parameterMap);
	
	
	
	int selectEstmSearchColLength(final FwkParameterMap parameterMap);
	
	
	
	
}
