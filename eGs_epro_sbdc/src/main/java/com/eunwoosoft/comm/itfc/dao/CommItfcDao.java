package com.eunwoosoft.comm.itfc.dao;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 인터페이스 통신 관련 구현 클래스
 */
public interface CommItfcDao {
	
	/**
	 * <pre>
	 * 1.개요 : 알림톡 메시지 발송대기등록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : insertTsmsMsgRegist
	 * @author : 맹경열
	 * @date : 2018. 05. 07. 
	 */	
	void insertTsmsMsgRegist(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1.개요 : 낙찰정보 연계 프로시저 호출 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : sendSbidInfoToMIS
	 * @author : 맹경열
	 * @date : 2018. 05. 07. 
	 */		
	void sendSbidInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 계약정보 연계 프로시저 호출 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : sendContInfoToMIS
	 * @author : 맹경열
	 * @date : 2018. 05. 07. 
	 */		
	FwkDataEntity sendContInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : ERP 첨부파일 번호 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : selectErpFileBrdnNo
	 * @author : 하성윤
	 * @date : 2018. 07. 11. 
	 */		
	FwkDataEntity selectErpFileBrdnNo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 계약보증정보 연계 프로시저 호출 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : sendContGrntInfoToMIS
	 * @author : 맹경열
	 * @date : 2018. 05. 07. 
	 */		
	void sendContGrntInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 업체정보 연계 프로시저 호출 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : sendVendInfoToMIS
	 * @author : 맹경열
	 * @date : 2018. 05. 07. 
	 */		
	void sendVendInfoToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 알림톡 메시지 템플릿 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : selectMsgForm
	 * @author : 맹경열
	 * @date : 2018. 05. 07. 
	 */		
	FwkDataEntity selectMsgForm(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 단문 메시지 발송대기등록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : insertSsmsMsgRegist
	 * @author : 맹경열
	 * @date : 2020. 04. 27. 
	 */	
	void insertSsmsMsgRegist(final FwkParameterMap parameterMap);	
	
	/**
	 * <pre>
	 * 1.개요 : IF 업체 SEQ_NO 조회  
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : selectTrdplcSeqNoToIF
	 * @author : 맹경열
	 * @date : 2020. 05. 18. 
	 */		
	FwkDataEntity selectTrdplcSeqNoToIF(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 구매접수 상태값 MIS 연계 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : sendPrcqStatToMIS
	 * @author : 맹경열
	 * @date : 2020. 05. 28. 
	 */		
	void sendPrcqStatToMIS(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : IF 업체 코드 조회  
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : selectTrdplcCdFromIF
	 * @author : 맹경열
	 * @date : 2020. 06. 03. 
	 */		
	FwkDataEntity selectTrdplcCdFromIF(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : IF 파일 INSERT
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : intsertAtchFileToIF
	 * @author : 맹경열
	 * @date : 2020. 06. 19. 
	 */		
	void intsertAtchFileToIF(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 계약 업체정보 TRDPLC UPDT 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : sendTrdplcToMISCom
	 * @author : 맹경열
	 * @date : 2020. 06. 30. 
	 */		
	void sendTrdplcToMISCom(final FwkParameterMap parameterMap);	
	
	
	
}
