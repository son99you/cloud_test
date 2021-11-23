package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmIfcMngeDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
public interface IproSytmIfcMngeDao {

	/**
	 * 
	 * <pre>
	 * 1.개요 : 인터페이스 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmIfcMngeDao.java
	 * @Method : selectIntfMngeListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectIntfMngeListWithPgng(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 인터페이스 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmIfcMngeDao.java
	 * @Method : selectIntfMngeListTotCnt
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	int selectIntfMngeListTotCnt(final FwkParameterMap parameterMap);

}
