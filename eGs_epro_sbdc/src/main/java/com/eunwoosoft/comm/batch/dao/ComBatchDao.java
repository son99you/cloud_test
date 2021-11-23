package com.eunwoosoft.comm.batch.dao;

import java.util.List;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * <pre>
 * com.eunwoosoft.comm.batch.dao 
 *    |_ ComBatchDao.java
 * 
 * </pre>
 * @date : 2015. 6. 15. 오전 11:26:56
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
public interface ComBatchDao {
	
	
	/**
	 * <pre>
	 * 1.개요 : 사전공고 마감 배치
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.comm.batch.dao.ComBatchDao.java
	 * @Method : updtBereNotiClosePscd
	 * @author : 천재
	 * @date : 2019. 09. 05. 
	 * @return
	 */
	void updtBereNotiClosePscd(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 대급지급 마감 배치
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.comm.batch.dao.ComBatchDao.java
	 * @Method : updtPayClosePscd
	 * @author : 천재
	 * @date : 2019. 09. 05. 
	 * @return
	 */
	void updtPayClosePscd(final FwkParameterMap parameterMap);
}
