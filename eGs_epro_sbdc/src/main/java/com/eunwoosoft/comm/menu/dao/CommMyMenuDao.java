package com.eunwoosoft.comm.menu.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface CommMyMenuDao {
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자별 메뉴 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyMenuListInqire
	 * @date : 2018. 05. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 05. 28.	은우소프트 맹경열		최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMyMenuListInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자별 서브 메뉴 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyMenuSubListInqire
	 * @date : 2018. 05. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 05. 28.	은우소프트 맹경열		최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMyMenuSubListInqire(final FwkParameterMap parameterMap);
	
}
