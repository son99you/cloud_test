package com.eunwoosoft.ipro.vend.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.dao
 * IproVendItemMngeDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 22.
 *
 */
public interface IproVendItemMngeDao {
	
	/**
	 * <pre>
	 * 1.개요 : 협력사별품목관리 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendItemMngeDao.java
	 * @Method : selectVendItemMngeListInqireWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 22. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendItemMngeListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 협력사별품목관리 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendItemMngeDao.java
	 * @Method : selectVendItemMngeListTotcnt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 22. 
	 * @param parameterMap
	 * @return
	 */
	int selectVendItemMngeListTotcnt(final FwkParameterMap parameterMap);

}
