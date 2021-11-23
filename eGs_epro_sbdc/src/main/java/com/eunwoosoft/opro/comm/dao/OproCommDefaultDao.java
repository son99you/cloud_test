package com.eunwoosoft.opro.comm.dao;

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
public interface OproCommDefaultDao {

	/**
	 * <pre>
	 * 1. 개요 : 공통 첨부 파일 조회(단건)
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 */
	FwkDataEntity selectCmmnFileInfoInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 공통 첨부 파일 조회(다건)
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 */
	List<FwkDataEntity> selectCmmnFileListInqire(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectCodeList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : sendLOG
	 * @Author : joo
	 * @Date   : 2020. 11. 17.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 11. 17.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 *
	 */
	void sendLOG(final FwkParameterMap parameterMap);
}

