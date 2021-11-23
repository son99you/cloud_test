package com.eunwoosoft.comm.code.dao; 

import java.util.List;


import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 지역정보 DAO
 * <pre>
 * com.eunwoosoft.comm.code.dao 
 *    |_ ComCmcdLegaldongDao.java
 * 
 * </pre>
 * @date : 2015. 2. 13.
 * @version : 1.0
 * @author : 야긴스텍 정윤교
 */
public interface ComCmcdLegaldongDao {

	/**
	 * <pre>
	 * 1. 개요 : 시도지역목록조회
	 * 2. 처리내용 : 
	 *     
	 * </pre>
	 * @Method Name : legaldongBrtcListInqire
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
	List<FwkDataEntity> legaldongBrtcListInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 시군구목록조회
	 * 2. 처리내용 : 
	 *     
	 * </pre>
	 * @Method Name : legaldongSignguListInqire
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
	List<FwkDataEntity> legaldongSignguListInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 읍면동목록조회
	 * 2. 처리내용 : 
	 *     
	 * </pre>
	 * @Method Name : legaldongEmdListInqire
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
	List<FwkDataEntity> legaldongEmdListInqire(final FwkParameterMap parameterMap);

}
