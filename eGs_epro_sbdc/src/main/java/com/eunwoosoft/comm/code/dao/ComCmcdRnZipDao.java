package com.eunwoosoft.comm.code.dao; 

import java.util.List;


import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 도로명 우편번호 DAO
 * <pre>
 * com.eunwoosoft.comm.code.dao 
 *    |_ ComCmcdRnZipDao.java
 * 
 * </pre>
 * @date : 2015. 2. 13.
 * @version : 1.0
 * @author : 야긴스텍 정윤교
 */
public interface ComCmcdRnZipDao {

	/**
	 * <pre>
	 * 1. 개요 : 도로명 우편번호목록조회_페이징
	 * 2. 처리내용 : 
	 *     
	 * </pre>
	 * @Method Name : selectRnZipListWithPgng
	 * @date : 2015. 2. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *  -----------------------------------------------------------------------
	 *  변경일             작성자                     변경내용  
	 *  ----------- ------------------- ---------------------------------------
	 *  2015. 2. 13.        야긴스텍 정윤교              최초 작성 
	 *  -----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectRnZipListWithPgng(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 도로명 우편번호목록조회_총건수
	 * 2. 처리내용 : 
	 *     
	 * </pre>
	 * @Method Name : selectRnZipListTotcnt
	 * @date : 2015. 2. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *  -----------------------------------------------------------------------
	 *  변경일             작성자                     변경내용  
	 *  ----------- ------------------- ---------------------------------------
	 *  2015. 2. 13.        야긴스텍 정윤교              최초 작성 
	 *  -----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectRnZipListTotcnt(final FwkParameterMap parameterMap);

}
