package com.eunwoosoft.ipro.estm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEstmProgDao {

	
	List<FwkDataEntity> selectEstmProgList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmProgListExcelDwld(final FwkParameterMap parameterMap);
	
	
	
	int selectEstmProgListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmMngMstDetail(final FwkParameterMap parameterMap);
	
	void insertEstmObjList(final FwkParameterMap parameterMap);
	
	void updateEstmMngMstClcRulSave(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectMxMnScrExcpYnColumn(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmNextProcdSeqAt(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmProcdSecdDetail(final FwkParameterMap parameterMap);
	void deleteEstmObjNextProcdSeq(final FwkParameterMap parameterMap);
	void insertEstmObjNextProcdSeq(final FwkParameterMap parameterMap);
	void updateEstmObjSlctYn(final FwkParameterMap parameterMap);
	void insertEstmObjSlct(final FwkParameterMap parameterMap);
	void updateEstmObjSlctRankUpdt(final FwkParameterMap parameterMap);
	void updateEstmAslctYn(final FwkParameterMap parameterMap);
	void updateEstmAslctAllN(final FwkParameterMap parameterMap);
	
	void deleteEstmObjSlct(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmProgVidoMtngList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmProgVidoMtngMemberList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmProcdList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmFile(final FwkParameterMap parameterMap);
	
	void insertEstmMngMst(final FwkParameterMap parameterMap);
	void insertEstmProcd(final FwkParameterMap parameterMap);
	void insertEstmFile(final FwkParameterMap parameterMap);
	void insertEstmProgHist(final FwkParameterMap parameterMap);
	void updateEstmDelAt(final FwkParameterMap parameterMap);
	void updateEstmRsdnNoDelete(final FwkParameterMap parameterMap);
	
	void updateEstmPscd(final FwkParameterMap parameterMap);
	
	void updateEstmEndObjSlctYn(final FwkParameterMap parameterMap);
	
	
	void deleteVidoMtngDelt(final FwkParameterMap parameterMap);
	void deleteVidoMtngCmtmDelt(final FwkParameterMap parameterMap);
	void deleteVidoMtngObjDelt(final FwkParameterMap parameterMap);
	void insertVidoMtngRegist(final FwkParameterMap parameterMap);
	
	
	void updateEstmMngMst(final FwkParameterMap parameterMap);
	void deleteEstmProcd(final FwkParameterMap parameterMap);
	void deleteEstmProcdFrm(final FwkParameterMap parameterMap);
	
	void deleteEstmFile(final FwkParameterMap parameterMap);
	
	void insertEstmObj(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmObjList(final FwkParameterMap parameterMap);
	
	void deleteEstmObj(final FwkParameterMap parameterMap);
	void insertEstmCntcObj(final FwkParameterMap parameterMap);
	void updateEstmObjFileGrpNo(final FwkParameterMap parameterMap);
	void updateEstmVidoFileGrpNo(final FwkParameterMap parameterMap);
	void updateEstmObjDelAt(final FwkParameterMap parameterMap);
	void updateEstmProcdPscd(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmSpheSecdList(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmObjSlctList(final FwkParameterMap parameterMap);

	
	void insertEstmCmtm(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmList(final FwkParameterMap parameterMap);
	void updateEstmCmtmSlctYn(final FwkParameterMap parameterMap);
	void updateEstmCmtmRsdnNo(final FwkParameterMap parameterMap);
	void updateEstmCmtmHldfYn(final FwkParameterMap parameterMap);
	void updateEstmCmtmPoolHldfYn(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmTabList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultObjAllList(final FwkParameterMap parameterMap);
	
	int selectEstmResultObjAllListTotCnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmResultObjTotScrList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectSanSoolData(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmSearchColList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> estmResultProcdSeqList(final FwkParameterMap parameterMap);
	
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
	
	List<FwkDataEntity> selectEstmVidoMtngCreatMeetList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmVidoMtngCmtmCreatMeetList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmVidoMtngObjCreatMeetList(final FwkParameterMap parameterMap);
	
	void updateEstmVidoMtngEntrKey(final FwkParameterMap parameterMap);
	void updateEstmVidoMtngCmtmEntrKey(final FwkParameterMap parameterMap);
	void updateEstmVidoMtngObjEntrKey(final FwkParameterMap parameterMap);
	void updateEstmVidoPrst(final FwkParameterMap parameterMap);
	
	
	
	
	
	FwkDataEntity selectEstmProcdDetail(final FwkParameterMap parameterMap);
	
	
	FwkDataEntity selectEstmSeMngDetail(final FwkParameterMap parameterMap);
	void selectAtchFileInsert(final FwkParameterMap parameterMap);
	void insertEstmCmtmAutoSlct(final FwkParameterMap parameterMap);
	void deleteEstmCmtm(final FwkParameterMap parameterMap);
	
	
	
	int selectEstmSearchColLength(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmCmtmSetDetail(final FwkParameterMap parameterMap);
	void updateEstmCmtmSlctCmplYn(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmSecdProcdSetList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmSecdSetList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmResultProcdObjSlctList(final FwkParameterMap parameterMap);
	
	void insertEstmProcdFrm(final FwkParameterMap parameterMap);
	
	int selectVidoMtngSeq(final FwkParameterMap parameterMap);
	void insertVidoMtng(final FwkParameterMap parameterMap);
	void insertVidoMtngCmtm(final FwkParameterMap parameterMap);
	void insertVidoMtngObj(final FwkParameterMap parameterMap);

	FwkDataEntity selectEstmCmtmAutoBtnCtr(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectEstmCmtmAllList(final FwkParameterMap parameterMap);

	void deleteEstmFileSign(final FwkParameterMap parameterMap);
	void insertEstmFileSign(final FwkParameterMap parameterMap);

	FwkDataEntity selectCmtmRsdnNoRegistPsblChck(final FwkParameterMap parameterMap);

	int selectOutCmtmRsdnNoNullCnt(final FwkParameterMap parameterMap);

	int selectEstmObjCnt(final FwkParameterMap parameterMap);

	void insertEstmSvyFrm(final FwkParameterMap parameterMap);


}
