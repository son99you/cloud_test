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
public interface ComCmcdDetailCdInqireService extends ComCmcdCmmnCdInqireService {
	
	/**
	 * 코드값목록 Key
	 */
	static final String CD_VALUE_LIST = "cdValueList";
	
	
	
	/**
     * <pre>
     * 1. 개요 : 코드ID값에 의한 코드값목록조회
     * 2. 처리내용 :      
     * </pre>
     * @Method Name : cdValueListInqireByCdId
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
	FwkTransferObject cdValueListInqireByCdId(final FwkParameterMap parameterMap);
	
	//낙찰자 변경 조회 
	FwkTransferObject comCmcdScsCdInqireByCdId(final FwkParameterMap parameterMap);
	
	//테이블컬럼을 콤보박스로 불러오기
	FwkTransferObject cdTableValueListInqireByCdId(final FwkParameterMap parameterMap) throws Exception;
	
	
}
