package com.eunwoosoft.ipro.comm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.main.service
 * IproMainLoginFormService.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
public interface IproCommDefaultService {
	/**
	 * 첨부파일 정보 목록
	 */
	static final String ATCHMNFL_INFO_LIST = "atchmnflInfoList";
	/**
	 * 공통 첨부파일 정보(단건)
	 */
	static final String CMMN_FILE_INFO = "cmmnFileInfo";
	
	/**
	* 공통 첨부파일 정보(단건) - 아임스타즈 연계
	*/
	static final String CMMN_IMSTARS_FILE_INFO = "cmmnImstarsFileInfo";
	static final String CMMN_IMSTARS_DETAIL_FILE_INFO = "cmmnImstarsDetailFileInfo";
	
	/**
	 * 업체정보(상세)
	 */	
	static final String ENTRPS_INQIRE_DETAIL = "entrpsInqireDetail";
	
	/**
	 * 결과값
	 */	
	static final String ENTRPS_INQIRE_RESULT_CODE = "entrpsInqireResultCode"; 
	
	/**
	 * <pre>
	 * 1. 개요 : 공통 파일 정보 조회(단건)
	 * 2. 처리내용 : 
	 * </pre>
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject cmmnFileInfoInqire(final FwkParameterMap parameterMap);
	
	FwkTransferObject cmmnImstarsFileInfo(final FwkParameterMap parameterMap);
	FwkTransferObject cmmnImstarsDetailFileInfo(final FwkParameterMap parameterMap);
	
	FwkTransferObject downloadZip(final FwkParameterMap parameterMap);
	
	//낙찰자 변경 조회 
	FwkTransferObject comCmcdScsCdInqireByCdId(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체정보상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsInqireDetail
	 * @date : 2018. 11. 28
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 28	은우소프트 맹경열				최초 작성 
	 *	----------- ------------------- --------------------------------------- 
	 * @param parameterMap
	 * @return
	 */	
	FwkTransferObject entrpsInqireDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : UBI REPORT 파일 생성 공통
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : makeUbiReportFile
	 * @date : 2018. 12. 26
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 12. 26	은우소프트 맹경열				최초 작성 
	 *	----------- ------------------- --------------------------------------- 
	 * @param parameterMap
	 * @return
	 */	
	FwkTransferObject makeUbiReportFile(final FwkParameterMap parameterMap);
	
	//LOG 등록
	FwkTransferObject sendLOG(final FwkParameterMap parameterMap);
	
	
	// 엑셀템플릿 기본세팅
	FwkTransferObject excelCommSetting(final FwkParameterMap parameterMap);
	
}
