package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmAuthMngeDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2018. 2. 28.
 *
 */
public interface IproSytmAuthMngeDao {
	
	/**
	 * <pre>
	 * 1.개요 : 권한관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmAuthMngeDao.java
	 * @Method : selectVendMngeListInqireWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectAuthMgrListWithPgng(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1.개요 : 권한관리 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.dao.IproSytmAuthMngeDao.java
	 * @Method : selectVendMngeListTotcnt
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	int selectAuthMgrListTotcnt(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectAuthMgrExcelList(final FwkParameterMap parameterMap);
	
	
	FwkDataEntity selectAuthMenuMgrDetail(final FwkParameterMap parameterMap);
	
	void deleteAuthMgrDelt(final FwkParameterMap parameterMap);
	
	void insertAuthMgrItemRegist(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1.개요 : 권한별메뉴관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectAuthMenuMgrListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectAuthMenuMgrListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 권한별메뉴관리 목록 총 건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectAuthMenuMgrListTotcnt
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	int selectAuthMenuMgrListTotcnt(final FwkParameterMap parameterMap);
	
	
}
