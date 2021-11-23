package com.eunwoosoft.ipro.sytm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.ipro.sytm.dao
 * IproSytmG2bCntcDao.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 22.
 *
 */
public interface IproSytmG2bCntcDao {

	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmG2bCntcDao.java
	 * @Method : selectG2bCntcHstyListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectG2bCntcHstyListWithPgng(FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 목록 총 건수 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmG2bCntcDao.java
	 * @Method : selectG2bCntcHstyListTotCnt
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param parameterMap
	 * @return
	 */
	int selectG2bCntcHstyListTotCnt(FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.dao.IproSytmG2bCntcDao.java
	 * @Method : selectG2bCntcHstyDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectG2bCntcHstyDetail(FwkParameterMap parameterMap);
	
	
}
