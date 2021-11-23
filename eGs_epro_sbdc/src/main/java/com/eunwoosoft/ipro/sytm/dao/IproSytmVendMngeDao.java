package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.dao
 * IproVendMngeDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 21.
 *
 */
public interface IproSytmVendMngeDao {
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeListInqireWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 목록 총 건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeListTotcnt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	int selectVendMngeListTotcnt(final FwkParameterMap parameterMap);

	FwkDataEntity selectVendMngeDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectVendMngeExcelList(final FwkParameterMap parameterMap);
}