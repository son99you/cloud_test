package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 입찰공고 서비스
 * <pre>
 * oda.iep.elbi.service 
 *    |_ IepEbiBidPblancService.java
 * 
 * </pre>
 * @date : 2015. 01. 14. 오전 11:39:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproMyEbidPblancService {

	/**
	 * 입찰공고목록 Key
	 */
	static final String MY_BID_PBLANC_LIST = "myBidPblancList";
	
	/**
	 * 입찰공고목록 총건수
	 */
	static final String MY_BID_PBLANC_LIST_TOTCNT = "myBidPblancListTotcnt";

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancListInqireWithPgng
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject myBidPblancListInqireWithPgng(final FwkParameterMap parameterMap);

}
