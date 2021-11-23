package com.eunwoosoft.comm.code.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 공통코드조회 서비스 인터페이스
 * 
 * @author : 
 * @date : 2014. 11. 14.
 * @version : 1.0
 */
public interface ComCmcdCmmnCdInqireService {
	
	/**
	 * 공통코드목록 Key
	 */
	static final String CMMN_CD_LIST = "cmmnCdList";
	
	/**
	 * 공통코드상세 Key
	 */
	static final String CMMN_CD_DETAIL = "cmmnCdDetail";
	
	
	/**
     * 공통코드목록총건수 Key
     */
    static final String CMMN_CD_LIST_TOTCNT = "cmmnCdListTotcnt";
	
	/**
     * <pre>
     * 1. 개요 : 공통코드목록조회_페이징
     * 2. 처리내용 :      
     * </pre>
     * @Method Name : cmmnCdListInqireWithPgng
     * @date : 2014. 12. 9.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2014. 12. 9.                      최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return {@link TransferObject}
     */
    FwkTransferObject cmmnCdListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드목록조회
	 * 2. 처리내용 :	  
	 * </pre>
	 * @Method Name : cmmnCdListInqire
	 * @date : 2014. 12. 9.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 9.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
    FwkTransferObject cmmnCdListInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드상세조회
     * 2. 처리내용 :      
	 * </pre>
	 * @Method Name : cmmnCdDetailInqire
	 * @date : 2014. 12. 9.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 9.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
    FwkTransferObject cmmnCdDetailInqire(final FwkParameterMap parameterMap);
}
