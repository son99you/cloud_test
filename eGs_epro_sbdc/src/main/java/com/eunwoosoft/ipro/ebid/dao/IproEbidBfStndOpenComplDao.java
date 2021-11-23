package com.eunwoosoft.ipro.ebid.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 사전공고 > 사전공고 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao
 *    |_ IproEbidBfStndOpenComplDao.java
 * 
 * </pre>
 * @date : 2018. 02. 19. 
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */
public interface IproEbidBfStndOpenComplDao {
	
	List<FwkDataEntity> selectBfStndOpenList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectBfStndOpenExcelList(final FwkParameterMap parameterMap);
	
	int selectBfStndOpenListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBfStndOpenDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBfStndOpenItemList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBfStndOpenFile(final FwkParameterMap parameterMap);
	
}
