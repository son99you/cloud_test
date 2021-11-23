package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmMssgeMngeDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
public interface IproSytmMssgeMngeDao {

	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmMssgeMngeDao.java
	 * @Method : selectMssgeLogListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMssgeLogListWithPgng(FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmMssgeMngeDao.java
	 * @Method : selectMssgeLogListTotCnt
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	int selectMssgeLogListTotCnt(FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmMssgeMngeDao.java
	 * @Method : selectMssgeLogDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMssgeLogDetail(FwkParameterMap parameterMap);
}
