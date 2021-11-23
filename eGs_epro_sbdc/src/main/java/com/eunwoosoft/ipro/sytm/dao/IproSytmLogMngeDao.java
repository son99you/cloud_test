package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmLogMngeDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
public interface IproSytmLogMngeDao {
	
	/**
	 * <pre>
	 * 1.개요 : 로그관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmLogMngeDao.java
	 * @Method : selectLogMngeListWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectLogMngeListWithPgng(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectLogMngeExcelList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 로그관리 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmLogMngeDao.java
	 * @Method : selectLogMngeListTotCnt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	int selectLogMngeListTotCnt(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmLogMngeDao.java
	 * @Method : selectLogMngeDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectLogMngeDetail(final FwkParameterMap parameterMap);

}
