package com.eunwoosoft.opro.comm.dao;

import java.util.List;




import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.acpr.dao
 * OproAcprDao.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 26.
 *
 */
public interface OproCommLogDao {
	/**
	 * <pre>
	 * 1. 개요 : logInfoInsert
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : logInfoInsert
	 * @date : 2018.08.23
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	 2018.08.23.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 */
	void logInfoInsert(final FwkParameterMap parameterMap);
}
  