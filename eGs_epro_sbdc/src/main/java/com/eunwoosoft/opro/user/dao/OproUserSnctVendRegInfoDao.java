package com.eunwoosoft.opro.user.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.opro.user.dao
 * OproUserSnctVendRegInfoDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 8.
 *
 */
public interface OproUserSnctVendRegInfoDao {

	// 부정당업체등록정보 목록
	List<FwkDataEntity> selectSnctVendRegInfoListWithPgng(FwkParameterMap parameterMap);

	// 부정당업체등록정보 목록 총 건수
	int selectSnctVendRegInfoListTotCnt(FwkParameterMap parameterMap);

	// 부정당업체등록정보 상세 
	FwkDataEntity selectSnctVendRegInfoDetail(FwkParameterMap parameterMap);

}
