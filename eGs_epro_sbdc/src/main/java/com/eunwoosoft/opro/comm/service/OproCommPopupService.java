package com.eunwoosoft.opro.comm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.comm.service
 * OproCommPopupService.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 26.
 *
 */
public interface OproCommPopupService { 
	
	//업체목록
	static final String ENTRPS_INQIRE_LIST = "entrpsInqireList";
	static final String ENTRPS_INQIRE_LIST_TOTCNT = "entrpsInqireListTotcnt";
	
	//부서목록
	static final String DEPT_INQIRE_LIST = "deptInqireList";
	static final String DEPT_INQIRE_LIST_TOTCNT = "deptInqireListTotcnt";
	
	//담당자목록
	static final String CHARGER_LIST = "chargerList";		
	static final String CHARGER_LIST_TOTCNT = "chargerListTotcnt";
	
	//품목목록
	static final String ITEM_LIST = "itemList";		
	static final String ITEM_LIST_TOTCNT = "itemListTotcnt";
	
	//품목리스트
	FwkTransferObject itemListInqireWithPgng(final FwkParameterMap parameterMap);
	
	//업체조회 목록
	FwkTransferObject entrpsInqireList(final FwkParameterMap parameterMap);
	
	/**
	 * 주요취급품목 목록
	 */
	static final String MJR_HNDL_ITEM_LIST = "mjrHndlItemList";
	
	/**
	 * 주요취급품목 목록 총 건수
	 */
	static final String MJR_HNDL_ITEM_LIST_TOTCNT = "mjrHndlItemListTotcnt";
	
	
	//부서 목록 조회
	public FwkTransferObject deptInqireList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 담당자조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : chargerListInqireWithPgng
	 * @date : 2018. 02. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 20.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	public FwkTransferObject chargerListInqireWithPgng(final FwkParameterMap parameterMap);

	FwkTransferObject mjrHndlItemListWithPgng(final FwkParameterMap parameterMap);
	
	//용어 상세팝업
	FwkTransferObject trmDetail(final FwkParameterMap parameterMap);
	
	//외부 평가위원평가표
	FwkTransferObject estmCmtmEstmFrmDetail(final FwkParameterMap parameterMap);

	FwkTransferObject estmCmtmEstmFrmSave(final FwkParameterMap parameterMap);
	
	// 평가대상정보 첨부파일 상세 팝업
	FwkTransferObject estmObjFileView(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCrtrDetail(final FwkParameterMap parameterMap);
	
	// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
	FwkTransferObject estmObjImstarsMainView(final FwkParameterMap parameterMap);

	// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
	FwkTransferObject estmObjImstarsDetailView(final FwkParameterMap parameterMap);

	// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
	FwkTransferObject estmObjImstarsFileView(final FwkParameterMap parameterMap);
	
	// 평가완료현황 - 상세 : 설문조사 팝업
	FwkTransferObject estmCmtmSvyDetail(final FwkParameterMap parameterMap);

	// 평가완료현황 - 상세 : 설문조사 저장
	FwkTransferObject estmCmtmSvySave(final FwkParameterMap parameterMap);
}
