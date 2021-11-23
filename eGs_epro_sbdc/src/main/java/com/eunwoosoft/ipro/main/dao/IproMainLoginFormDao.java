package com.eunwoosoft.ipro.main.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.main.dao
 * IproMainLoginFormDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 12.
 *
 */
public interface IproMainLoginFormDao {
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 유저 정보 조회 (id)
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao.java
	 * @Method : selectEmplyrInqireById
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 12. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEmplyrInqireById(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao.java
	 * @Method : selectEntrpsMberInqireByBizrNo
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 12. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEntrpsMberInqireByBizrNo(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 유저 정보 조회 (id+pw)
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao.java
	 * @Method : selectEmplyrInqireByIdAndPassword
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 12. 
	 * @param parameterMap
	 */
	FwkDataEntity selectEmplyrInqireByIdAndPassword(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.dao.IproMainLoginFormDao.java
	 * @Method : updateLastLoginDt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	void updateLastLoginDt(final FwkParameterMap parameterMap);

	//메인_평가진행현황
	FwkDataEntity selectMainEstmCnt(final FwkParameterMap parameterMap);
	
	//메인_평가진행상세
	List<FwkDataEntity> selectEstmMainList(final FwkParameterMap parameterMap);
}
