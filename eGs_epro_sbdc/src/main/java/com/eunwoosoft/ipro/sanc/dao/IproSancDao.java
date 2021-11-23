package com.eunwoosoft.ipro.sanc.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * IproSancDao
 * 
 * 
 * @author juyeon_Lee
 *
 */
public interface IproSancDao {
	
	//결재테이블 저장_계약설계
	//T_PR_APPR_CNTC
	void apprPrInfoRegist(final FwkParameterMap parameterMap);
	FwkDataEntity selectApprInfo(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectPrFileInfo(final FwkParameterMap parameterMap);
	
	
	void apprBfInfoRegist(final FwkParameterMap parameterMap);
	FwkDataEntity selectBfApprInfo(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectBfFileInfo(final FwkParameterMap parameterMap);
	
	
	void apprBiInfoRegist(final FwkParameterMap parameterMap);
	FwkDataEntity selectBiApprInfo(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectBiFileInfo(final FwkParameterMap parameterMap);
	
	void apprCtInfoRegist(final FwkParameterMap parameterMap);
	FwkDataEntity selectCtApprInfo(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectCtFileInfo(final FwkParameterMap parameterMap);

	// 결의서 html조회
	FwkDataEntity selectContForm(final FwkParameterMap parameterMap);
	
	//결재 첨부파일 저장 T_IF_APPR_CNTC_FILE
	void deleteIfApprCntcFile(final FwkParameterMap parameterMap);
	void insertIfApprCntcFile(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectContDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectVendList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectItemList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectContFile(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectApprFileData(final FwkParameterMap parameterMap);
}