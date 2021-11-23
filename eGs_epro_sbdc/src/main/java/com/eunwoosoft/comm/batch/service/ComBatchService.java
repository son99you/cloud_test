package com.eunwoosoft.comm.batch.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;


/**
 * <pre>
 * com.eunwoosoft.comm.batch.service 
 *    |_ ComBatchService.java
 * 
 * </pre>
 * @date : 2015. 6. 15. 오전 11:19:58
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
public interface ComBatchService {

	
	/**
	 * <pre>
	 * 1.개요 : 일배치
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.comm.batch.service.ComBatchService.java
	 * @Method : bereNotiClose
	 * @author : 맹경열
	 * @date : 2019. 05. 08. 
	 * @return
	 */
	void bereNotiClose();	
	
	/**
	 * <pre>
	 * 1.개요 : 일배치
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.comm.batch.service.ComBatchService.java
	 * @Method : payClose
	 * @author : 맹경열
	 * @date : 2019. 05. 08. 
	 * @return
	 */
	void payClose();	
	
}