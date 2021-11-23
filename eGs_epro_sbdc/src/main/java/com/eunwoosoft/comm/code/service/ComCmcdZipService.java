package com.eunwoosoft.comm.code.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 우편번호 서비스 인터페이스
 * 
 * @author : 
 * @date : 2015. 1. 30.
 * @version : 1.0
 */
public interface ComCmcdZipService {

	/**
	 * 우편번호목록 Key
	 */
	static final String ZIP_LIST = "zipList";


	/**
	 * 우편번호목록총건수 Key
	 */
	static final String ZIP_LIST_TOTCNT = "zipListTotcnt";


	/**
	 * <pre>
	 * 1. 개요 : 지번 주소 조회 목록_페이징
	 * 2. 처리내용 :      
	 * </pre>
	 * @Method Name : lmnZipListInqireWithPgng
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject lmnZipListInqireWithPgng(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 도로명 주소 조회 목록_페이징
	 * 2. 처리내용 :      
	 * </pre>
	 * @Method Name : rnZipListInqireWithPgng
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject rnZipListInqireWithPgng(final FwkParameterMap parameterMap);

}
