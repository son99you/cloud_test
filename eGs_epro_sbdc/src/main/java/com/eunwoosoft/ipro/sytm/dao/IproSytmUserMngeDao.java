package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmUserMngeDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 26.
 *
 */
public interface IproSytmUserMngeDao {
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmUserMngeDao.java
	 * @Method : selectUserMgrListWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectUserMgrListWithPgng(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectUserMgrExcelList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmUserMngeDao.java
	 * @Method : selectUserMgrListTotcnt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	int selectUserMgrListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmUserMngeDao.java
	 * @Method : selectUserMgrDetail
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectUserMgrDetail(final FwkParameterMap parameterMap);   // TMUR_KOAC SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmUserMngeDao.java
	 * @Method : updateUserMgrUpdt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void userMgrUpdt(final FwkParameterMap parameterMap) throws Exception;
	
}
