package com.eunwoosoft.comm.itfc.service;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 인터페이스 통신 관련 구현 클래스
 */
public interface CommItfcService {
	
	/**
	 * <pre>
	 * 1. 개요 : 알림톡 테이블 내용 저장 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertTsmsMsgInfo
	 * @date : 2019. 04. 02.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 02.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	FwkTransferObject insertTsmsMsgInfo(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendSbidInfoToMIS
	 * @date : 2019. 04. 05.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkTransferObject sendSbidInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 계약 생성정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendContInfoToMIS
	 * @date : 2019. 04. 05.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkDataEntity sendContInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : ERP 첨부파일 번호 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectErpFileBrdnNo
	 * @date : 2019. 07. 11.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 07. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkDataEntity selectErpFileBrdnNo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 계약 보증 정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendContGrntInfoToMIS
	 * @date : 2019. 04. 05.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkTransferObject sendContGrntInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendVendInfoToMIS
	 * @date : 2019. 04. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 09.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkTransferObject sendVendInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 프로시저 호출 후 타 시스템 주소 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : callOtherSysURL
	 * @date : 2019. 04. 22
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 09.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkTransferObject callOtherSysURL(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 단문 메시지 테이블 내용 저장 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertSsmsMsgInfo
	 * @date : 2020. 04. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 04. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	FwkTransferObject insertSsmsMsgInfo(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 프로시저 호출 후 타 시스템 주소 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : callOtherSysURLForVend
	 * @date : 2020. 06. 10
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 06. 10.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	FwkTransferObject callOtherSysURLForVend(final FwkParameterMap parameterMap) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 계약업체 TRDPLC_CD 업데이트 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendContTrdplcToMIS
	 * @date : 2020. 06. 30
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 06. 30.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	void sendContTrdplcToMIS(final FwkParameterMap parameterMap) throws Exception;
	
}