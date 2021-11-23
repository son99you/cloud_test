package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmBatchMngeDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
public interface IproSytmBatchMngeDao {

	/**
	 * 
	 * <pre>
	 * 1.개요 : 배치조회 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmBatchMngeDao.java
	 * @Method : selectSytmBatchFormListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectSytmBatchFormListWithPgng(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 배치조회 목록 총 건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmBatchMngeDao.java
	 * @Method : selectSytmBatchFormListTotCnt
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	int selectSytmBatchFormListTotCnt(final FwkParameterMap parameterMap);

}
